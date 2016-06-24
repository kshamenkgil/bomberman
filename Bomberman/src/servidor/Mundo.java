package servidor;

import java.nio.charset.Charset;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bomberman.Enemigo;
import bomberman.Jugador;
import bomberman.Mapa;
import bomberman.Punto2D;

public class Mundo {
	private ArrayList<Enemigo> enemigos;
	/*private ArrayList<Tile> bloquesRompibles;
	private ArrayList<Tile> bloqueados;*/
	private Mapa map;
	private ArrayList<ThreadServer> connections;
	
	public void cargar(){
		//cargar enemigos y bloques que se pueden romper
		Punto2D size = new Punto2D(100, 100);
		this.map = new MapAutoGeneration(size, 10).getMap();
	}

	public void setMap(Mapa map) {
		this.map = map;
	}
	
	public synchronized void sendMapa(){
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(bomberman.Punto2D.class, new bomberman.Punto2DSerializer())				
				.registerTypeAdapter(bomberman.Potenciador.class, new bomberman.PotenciadorSerializer())
				.registerTypeAdapter(bomberman.Tile.class, new bomberman.TileSerializer())
				.registerTypeAdapter(bomberman.TileMap.class, new bomberman.TileMapSerializer())
				.registerTypeAdapter(bomberman.Mapa.class, new bomberman.MapSerializer())
				.create();
		
		String mapa = gson.toJson(this.map);		
		for (ThreadServer ts : connections) {
			ts.sendData(mapa.getBytes(Charset.forName("UTF-8")));
		}		
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
