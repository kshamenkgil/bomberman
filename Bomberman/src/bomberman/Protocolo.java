package bomberman;

public class Protocolo {
	/*
	 * Protocolo
	 * movimiento: se recibe cabecera + id + direccion de movimiento, cliente calcula la nueva posicion con la velocidad(previamente enviada)
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
		
	//Direcciones
	public static final byte NORTE = 1;
	public static final byte SUR = 2;
	public static final byte ESTE = 3;
	public static final byte OESTE = 4;
	
	public Protocolo() {
		// TODO Auto-generated constructor stub
	}
	
	public void procesarEntrada(byte[] data){
		byte header = data[0];
		switch(header){
			case CONEXION:
				Mundo.getInstance().getJugador().id = data[1];
				break;
			case COMIENZO_JUEGO:
				//COMENZAR JUEGO
				//enviar mapa la primera vez.
				//enviar posiciones
				//se puede poner en un metodo y llamarlo
				
				break;
			case MOVIMIENTO:
				moverJugador(data);
				break;
			case DESCONEXION:
				byte id = data[1];
				if(id == Mundo.getInstance().getJugador().getId()){				
					Bomberman.getInstancia().setCanClose(true);
				}else{
					Jugador j1 = null;
					for (Jugador j : Mundo.getInstance().getJugadores()) {
						if(j.getId() == id)
							j1 = j;
					}
					Mundo.getInstance().getJugadores().remove(j1);
				}
				break;
		}			
	}
	
	private void moverJugador(byte[] data){
		for (Jugador jugador : Mundo.getInstance().getJugadores()) {
			if(jugador.id == data[1])
				jugador.mover(data[2]);
		}
	}
	
	public static void moverJugador(byte direccion){
		byte[] t = new byte[2];
        t[0] = Protocolo.MOVIMIENTO;
        t[1] = direccion;
        Bomberman.getInstancia().getCliente().sendData(t);
	}	
}
