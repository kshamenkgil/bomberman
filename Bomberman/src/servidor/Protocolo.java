package servidor;

import java.util.LinkedList;
import java.util.Queue;

import bomberman.Jugador;

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
		
	private boolean isRunning = true;
	private Queue<byte[]> colaMensajes = new LinkedList<>();
	private Jugador jugador;
	
	public Protocolo() {
		// TODO Auto-generated constructor stub
	}
	
	public synchronized void stop(){
		isRunning = false;
	}
	
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
					
					byte header = data[0];
					switch(header){					
						case COMIENZO_JUEGO:
							//COMENZAR JUEGO
							//enviar mapa la primera vez.
							//enviar posiciones
							//se puede poner en un metodo y llamarlo
							
							break;
						case MOVIMIENTO:
							moverJugador(jugador, data[1]);
							break;
							
						case DESCONEXION:
							//enviar desconexion a los clientes
							Mundo.getInstance().desconectarJugador(jugador);
							isRunning = false;				
					}		
				//}
			//}
		//},"protocolo").start();		
	}
	
	private void moverJugador(Jugador jugador, byte direccion){
		jugador.mover(direccion);
		
		//H+ID+D
		byte[] data = new byte[3];
		data[0] = Protocolo.MOVIMIENTO;
		data[1] = (byte)jugador.getId();
		data[2] = direccion;
		
		Mundo.getInstance().actualizarPosicion(jugador, data);	
	}
	
	public Queue<byte[]> getColaMensajes() {
		return colaMensajes;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
}
