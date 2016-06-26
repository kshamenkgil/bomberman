package bomberman;

import java.util.ArrayList;

public class Mundo {	
	
	private Jugador jugador;
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	private ArrayList<Bomba> bombas = new ArrayList<Bomba>();
	
	private Mapa map;
	//private ArrayList<Tile> bloquesRompibles;
	
	private ArrayList<Enemigo> enemigos;			
	
	public synchronized ArrayList<Bomba> getBombas() {
		return bombas;
	}
	
	public synchronized void setBombas(ArrayList<Bomba> bombas) {
		this.bombas = bombas;
	}
	
	public synchronized void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	public synchronized void setMap(Mapa map) {
		this.map = map;		
	}	
	
	public synchronized ArrayList<Jugador> getJugadores() {
		return jugadores;
	}
	
	public synchronized Jugador getJugador() {
		return jugador;
	}
	
	public synchronized void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	public synchronized ArrayList<Enemigo> getEnemigos() {
		return enemigos;
	}
	
	public synchronized Mapa getMap() {
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
