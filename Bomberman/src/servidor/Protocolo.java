package servidor;

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
		
	//Direcciones
	public static final byte NORTE = 1;
	public static final byte SUR = 2;
	public static final byte ESTE = 3;
	public static final byte OESTE = 4;
	
	public Protocolo() {
		// TODO Auto-generated constructor stub
	}
	
	public void procesarEntrada(byte[] data, Jugador jugador){
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
		}			
	}
	
	private void moverJugador(Jugador jugador, byte direccion){
		jugador.mover(direccion);
		//Mundo.getInstance().actualizarPosicion(jugador, direccion);
	}
		
}
