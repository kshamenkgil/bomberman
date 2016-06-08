package servidor;

import java.util.ArrayList;

import bomberman.Enemigo;
import bomberman.Jugador;
import bomberman.Tile;

public class Mundo {
	private ArrayList<Enemigo> enemigos;
	private ArrayList<Tile> bloquesRompibles;
	private ArrayList<ThreadServer> connections;
	
	public static void cargar(){
		//cargar enemigos y bloques que se pueden romper
	}
	
	//thread safe
	public static Mundo getInstance() {
		return MundoHolder.INSTANCE;			
	}
		
	private static class MundoHolder {
        static final Mundo INSTANCE = new Mundo();
    }	   
    
	public void setConnections(ArrayList<ThreadServer> connections) {
		this.connections = connections;
	}
	
	public void actualizarPosicion(Jugador jugador, byte[] data){
		for (ThreadServer t: connections) {
			if(t.getJugador().getId() != jugador.getId())
				t.sendData(data);
		}
	}
	
}
