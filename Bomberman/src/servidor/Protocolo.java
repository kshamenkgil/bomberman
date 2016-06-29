package servidor;

import java.util.LinkedList;
import java.util.Queue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import bomberman.AgarroPotenciador;
import bomberman.Engine;
import bomberman.Jugador;
import bomberman.Mapa;
import bomberman.Punto2D;
import servidor.Mundo;

public class Protocolo {
	/*
	 * Protocolo
	 * movimiento: se envia cabecera + id + direccion de movimiento, cliente calcula la nueva posicion con la velocidad(previamente enviada)
	 * 			   se recibe cabecera + direccion de movimiento
	 * tiles explotados: se envia cabecera + cantidad + posiciones
	 * enemigos?: se actualiza posicion
	 * bombas: cabecera + posicion + potencia (explosion calculada en servidor?)
	 * muertes: cabecera + id jugador
	 * fin de juego: cabecera
	 * comienzo de juego: cabecera + estado inicial(incluye posicion de personajes, enemigos, etc) 
	 * estados:
	 * 	listo: cabecera
	 * 	
	 */
		
	//Cabeceras
	public static final byte MOVIMIENTO = 0;
	public static final byte MOVIMIENTO_ENEMIGO = 1;
	public static final byte EXPLOTARON_TILES = 2;
	public static final byte COLOCO_BOMBA = 3;
	public static final byte MURIO_JUGADOR = 4;
	public static final byte COMIENZO_JUEGO = 5;
	public static final byte FIN_JUEGO = 6;
	public static final byte LISTO = 7;
	public static final byte DESCONEXION = 8;
	public static final byte GET_POTENCIADOR = 9;
	public static final byte CONEXION = 10;
	public static final byte JSON = 123;
	
	//Direcciones
	public static final byte NORTE = 1;
	public static final byte SUR = 2;
	public static final byte ESTE = 3;
	public static final byte OESTE = 4;
	
	//Potenciadores
	public static final byte BOMBA_MAS_POTENTE = 0;
	public static final byte CORRER_MAS_RAPIDO = 1;
	public static final byte MAS_DE_UNA_BOMBA = 2;
	
	//private boolean isRunning = true;
	private Queue<byte[]> colaMensajes = new LinkedList<>();
	private Jugador jugador;
	
	public Protocolo() {
		// TODO Auto-generated constructor stub
	}
	
	/*public synchronized void stop(){
		isRunning = false;
	}*/
	
	//public void procesarEntrada(byte[] data, Jugador jugador){		
	public void procesarEntrada(){
		//new Thread(new Runnable() {
			//public void run() {
				//while(isRunning)
				//{
					/*while(colaMensajes.isEmpty()){
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}*/
					if(colaMensajes.isEmpty())
						return;
					
					byte[] data = colaMensajes.poll();
					try {
						byte header = data[0];
						switch(header){	
							case GET_POTENCIADOR:
								
								break;	
							case MURIO_JUGADOR:
								matarJugador(jugador);
								break;
							case MOVIMIENTO:
								moverJugador(jugador, data[1]);
								break;
								
							case DESCONEXION:
								//enviar desconexion a los clientes
								Mundo.getInstance().desconectarJugador(jugador);
								if(Mundo.getInstance().getConnectedUsers() == 0)
									System.exit(0);
								
								break;								
							case JSON:
								parseJSON(new String(data));				
								break;
						}	
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("problema con el paquete");
					}
				//}
			//}
		//},"protocolo").start();		
	}
	
	private void matarJugador(Jugador jugador) {		
		byte[] data = new byte[3];
		data[0] = Protocolo.MURIO_JUGADOR;
		data[1] = (byte)jugador.getId();
		Mundo.getInstance().actualizarMuertes(jugador, data);
	}

	
	private void parseJSON(String json) {
		JsonParser parser = new JsonParser();
		JsonObject o = parser.parse(json).getAsJsonObject();
		if(o.get("header").getAsString().compareTo("bomba") == 0){
			Gson gson = new GsonBuilder()
					.registerTypeAdapter(bomberman.Punto2D.class, new bomberman.Punto2DDeserializer())				
					.registerTypeAdapter(Bomba.class, new servidor.BombaDeserializer())
					.create();
			
			Bomba bomba = gson.fromJson(json, Bomba.class);
			bomba.explotar(Bomba.tiempoExplosion);
			Mundo.getInstance().getBombas().add(bomba);
			Mundo.getInstance().enviarBomba(json,bomba.getJugadorPlantoBomba().getId());
			
		}		
	}
		
	
	private void moverJugador(Jugador jugador, byte direccion){
		if(jugador.moverServidor(direccion)){			
			//H+ID+D
			byte[] data = new byte[3];
			data[0] = Protocolo.MOVIMIENTO;
			data[1] = (byte)jugador.getId();
			data[2] = direccion;
			
			Mundo.getInstance().actualizarPosicion(jugador, data);
			int x = (int)Math.floor((jugador.getPosicion().getX()+16)/(Engine.TILE_WIDTH));
			int y = (int)Math.floor((jugador.getPosicion().getY()+16)/(Engine.TILE_HEIGHT));
			if(Mundo.getInstance().getMap().getMapa()[x][y].getObjeto() != null){				
				AgarroPotenciador ap = new AgarroPotenciador(new Punto2D(x, y), Mundo.getInstance().getMap().getMapa()[x][y].getObjeto(), jugador.getId());
				ap.getPot().potenciar(jugador);				
				Mundo.getInstance().sendPotenciador(ap);
				Mundo.getInstance().getMap().getMapa()[x][y].setPotenciador(null);
			}
		}
	}
	
	public Queue<byte[]> getColaMensajes() {
		return colaMensajes;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
}
