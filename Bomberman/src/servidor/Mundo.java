package servidor;

import java.nio.charset.Charset;
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
	
	public synchronized void sendStartInfo(){
		int index = 1;
		String s = "{'header' : 'startInfo','jugadores':[";
		for (ThreadServer t : connections) {			
			s+="{'name':'Jugador";
			s+=t.getJugador().getId();
			s+="', 'id':";
			s+=t.getJugador().getId();
			s+=", 'x':";
			s+=t.getJugador().getPosicion().getX();
			s+=", 'y':";
			s+=t.getJugador().getPosicion().getY();
			s+="}";
			if(index != connections.size())
				s+=",";
			index++;
		}
		s+="]}";
		
		for (ThreadServer ts : connections) {
			ts.sendData(s.getBytes(Charset.forName("UTF-8")));
		}
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
	
	public synchronized void desconectarJugador(Jugador jugador){
		
		ThreadServer t1 = null;
		
		for (ThreadServer t: connections) {
			byte d[] = new byte[2];
			d[0] = Protocolo.DESCONEXION;
			d[1] = jugador.getId();
			t.sendData(d);
			
			if(t.getJugador().getId() == jugador.getId()){
 				t1 = t;
 			} 			
		}
		
		t1.closeSocket();
		connections.remove(t1);
	}
	
	public synchronized void actualizarPosicion(Jugador jugador, byte[] data){
		for (ThreadServer t: connections) {
 			if(t.getJugador().getId() != jugador.getId())
				t.sendData(data);
		}
	}
	
}
