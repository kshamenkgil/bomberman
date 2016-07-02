package bomberman;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Mundo {	
	
	private Jugador jugador;
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	private ArrayList<Bomba> bombas = new ArrayList<Bomba>();
	private Queue<String> mensajes = new LinkedList<String>();
	private Mapa map;
	private boolean finJuego = false;
	private Jugador ganador = null;
	//private ArrayList<Tile> bloquesRompibles;
	
	public Jugador getGanador() {
		return ganador;
	}
	
	public void setGanador(Jugador ganador) {
		this.ganador = ganador;
	}
	
	public boolean isFinJuego() {
		return finJuego;
	}
	
	public void setFinJuego(boolean finJuego) {
		this.finJuego = finJuego;
	}
	
	private ArrayList<Enemigo> enemigos;			
	
	public Queue<String> getMensajes() {
		return mensajes;
	}
	
	public void setMensajes(Queue<String> mensajes) {
		this.mensajes = mensajes;
	}
	
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
