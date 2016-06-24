package bomberman;

import java.util.ArrayList;

public class Mundo {	
	
	private Jugador jugador;
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	
	private Mapa map;
	//private ArrayList<Tile> bloquesRompibles;
	
	private ArrayList<Enemigo> enemigos;			
	
	public void setMap(Mapa map) {
		this.map = map;
	}	
	
	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}
	
	public Jugador getJugador() {
		return jugador;
	}
	
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	public ArrayList<Enemigo> getEnemigos() {
		return enemigos;
	}
	
	public Mapa getMap() {
		return map;
	}
	
	//thread safe
	public static Mundo getInstance() {
		return MundoHolder.INSTANCE;			
	}
		
	private static class MundoHolder {
        static final Mundo INSTANCE = new Mundo();
    }	   
    
}
