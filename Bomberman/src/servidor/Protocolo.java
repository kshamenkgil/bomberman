package servidor;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.LinkedList;
import java.util.Queue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import bomberman.AgarroPotenciador;
import bomberman.Engine;
import bomberman.Jugador;
import bomberman.Mapa;
import bomberman.Punto2D;
import bomberman.Sprite;
import servidor.Mundo;

import database.Conector;
import database.DatosJugador;

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
	public static final byte READY = 11;
	public static final byte INICIAR_SESION = 12;
	public static final byte DESCONEXION_USER = 13;
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
	private Conector con;
	
	public Protocolo() {
		// TODO Auto-generated constructor stub
	}
	
	/*public synchronized void stop(){
		isRunning = false;
	}*/
	
	//public void procesarEntrada(byte[] data, Jugador jugador){		
	public void procesarEntrada(ThreadServer ts){
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
							case READY:
								Mundo.getInstance().getAndSetReadyUsers();
							break;
							case GET_POTENCIADOR:
								
								break;	
							case MURIO_JUGADOR:
								matarJugador(jugador);
								break;
							case MOVIMIENTO:
								moverJugador(jugador, data[1]);
								break;
							case DESCONEXION_USER:	
								con = new Conector();
								con.connect();
								con.modificarEstado(jugador.getNombre(),0);
								Server.getPantalla().consola.append("El usuario "+ jugador.getNombre() +" se ha desconectado.\n");
								Server.disconnectUser();
								break;
							case DESCONEXION:
								//enviar desconexion a los clientes
								Mundo.getInstance().desconectarJugador(jugador);
								con = new Conector();
								con.connect();
								con.modificarEstado(jugador.getNombre(),0);
								if(Mundo.getInstance().getConnectedUsers() == 0)
									System.exit(0);
								
								break;								
							case JSON:
								parseJSON(new String(data),ts);				
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

	
	private void parseJSON(String json,ThreadServer ts) {
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
			
		}else if(o.get("header").getAsString().compareTo("iniciar_sesion") == 0){								
				String user = o.get("user").getAsString();
				String password = o.get("password").getAsString();
				Conector con =  new Conector();
				con.connect();
				if ( con.confirmarLogin(user,password))
				{	
					con.connect();
					con.modificarEstado(user,1);
					String s = "{'header' : 'iniciar_sesion', 'estado': 'ok','puntos': "+ con.puntosJugador(user) +"}";
					ts.sendData(s.getBytes(Charset.forName("UTF-8")));
					
					Punto2D p = new Punto2D(0, 0);
					Punto2D pR = new Punto2D(0, 0);
					Mapa mAG = Mundo.getInstance().getMap();
					
					byte lastId = Server.getAndSetLastId();
					Server.getPantalla().consola.append("Ingreso el jugador "+ user +" con id "+lastId+"\n");
					
					switch(lastId){
						case 0:
							p = new Punto2D(1*Engine.TILE_WIDTH, 1*Engine.TILE_HEIGHT);
							pR = new Punto2D(1, 1);
							break;
						case 1:					
							p = new Punto2D((mAG.getSize().getX()-2)*Engine.TILE_WIDTH, 1*Engine.TILE_HEIGHT);
							pR = new Punto2D((mAG.getSize().getX()-2), 1);
							break;
						case 2:
							pR = new Punto2D(1, (mAG.getSize().getY()-2));
							p = new Punto2D(1*Engine.TILE_WIDTH, (mAG.getSize().getY()-2)*Engine.TILE_HEIGHT);
							break;
						case 3:
							p = new Punto2D((mAG.getSize().getX()-2)*Engine.TILE_WIDTH, (mAG.getSize().getY()-2)*Engine.TILE_HEIGHT);					
							pR = new Punto2D((mAG.getSize().getX()-2), (mAG.getSize().getY()-2));
							break;
					}
					
					for (ThreadServer tts : Mundo.getInstance().getConnections()) {
						if(tts.equals(ts)){
							tts.getJugador().setId(lastId);
																	
							
							tts.getJugador().setPosicion(p);
							tts.getJugador().setPosicionRelativa(pR);
							tts.getJugador().setNombre(user);
							
							byte data[] = new byte[2];
							data[0] = Protocolo.CONEXION;
							data[1] = lastId;
							
							tts.sendData(data);
						}
					}					
										
					//this.lastId++;
					Mundo.getInstance().getAndSetConnectedUsers();					
					//this.connectedUsers++;
					if(Mundo.getInstance().getConnectedUsers() == Mundo.getInstance().getCantPlayers())
						Server.setRunning2(false);
					
				}else{
					String s = "{'header' : 'iniciar_sesion', 'estado' : 'error'}";
					ts.sendData(s.getBytes(Charset.forName("UTF-8")));
				}
			
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
			if(Mundo.getInstance().getMap().getMapa()[x][y].getObjeto() != null && !Mundo.getInstance().getMap().getMapa()[x][y].getTile().isColisionable()){				
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
