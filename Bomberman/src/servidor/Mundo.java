package servidor;

import java.nio.charset.Charset;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bomberman.AgarroPotenciador;
import bomberman.Enemigo;
import bomberman.ExplotoBomba;
import bomberman.Jugador;
import bomberman.Mapa;
import bomberman.Potenciador;
import bomberman.Punto2D;

public class Mundo {
	private static Mundo instance;
	private ArrayList<Enemigo> enemigos;	
	private Mapa map;
	private ArrayList<ThreadServer> connections;
	private ArrayList<Bomba> bombas = new ArrayList<Bomba>();	
	private int connectedUsers = 0;
	private int cantPlayers = 0;
	private int readyUsers = 0;
	private int cantVivos = 0;
	
	public synchronized int getCantVivos() {
		return cantVivos;
	}
	
	public synchronized void restarCantVivos() {
		setCantVivos(getCantVivos()-1);
	}
	
	public synchronized void setCantVivos(int cantVivos) {
		this.cantVivos = cantVivos;
	}

	public synchronized void getAndSetReadyUsers() {		
		setReadyUsers(getReadyUsers()+1);
	}
	
	public synchronized int getReadyUsers() {
		return readyUsers;
	}
	
	public synchronized void setReadyUsers(int readyUsers) {
		this.readyUsers = readyUsers;
	}
	
	public int getCantPlayers() {
		return cantPlayers;
	}
	
	public void setCantPlayers(int cantPlayers) {
		this.cantPlayers = cantPlayers;
	}
	
	public ArrayList<Bomba> getBombas() {
		return bombas;
	}
	
	public void setBombas(ArrayList<Bomba> bombas) {
		this.bombas = bombas;
	}
	
	public synchronized void disconnectConnectedUser() {		
		setConnectedUsers(getConnectedUsers()-1);
	}
	
	public synchronized void getAndSetConnectedUsers() {		
		setConnectedUsers(getConnectedUsers()+1);
	}
	
	public int getConnectedUsers() {
		return connectedUsers;
	}
	
	public synchronized void setConnectedUsers(int connectedUsers) {
		this.connectedUsers = connectedUsers;
	}
	
	/*public void cargar(){
		//cargar enemigos y bloques que se pueden romper
		Punto2D size = new Punto2D(100, 100);
		this.map = new MapAutoGeneration(size, 10).getMap();
	}*/

	public void setMap(Mapa map) {
		this.map = map;
	}
	
	
	public synchronized void sendPotenciador(AgarroPotenciador pot){
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(bomberman.Punto2D.class, new bomberman.Punto2DSerializer())								
				.registerTypeAdapter(bomberman.Potenciador.class, new bomberman.PotenciadorSerializer())
				.registerTypeAdapter(bomberman.AgarroPotenciador.class, new bomberman.AgarroPotenciadorSerializer())
				.create();
		
		String potenciador = gson.toJson(pot);
		for (ThreadServer ts : connections) {
			ts.sendData(potenciador.getBytes(Charset.forName("UTF-8")));
		}		
	}
	
	public synchronized void sendExplotoBomba(ExplotoBomba exB){
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(bomberman.Punto2D.class, new bomberman.Punto2DSerializer())								
				.registerTypeAdapter(bomberman.Tile.class, new bomberman.TileSerializer())
				.registerTypeAdapter(bomberman.ExplotoBomba.class, new ExplotoBombaSerializer())
				.create();
		
		String explotoBomba = gson.toJson(exB);
		for (ThreadServer ts : connections) {
			ts.sendData(explotoBomba.getBytes(Charset.forName("UTF-8")));
		}		
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
			s+="{'name':'";
			s+=t.getJugador().getNombre();
			s+="', 'id':";
			s+=t.getJugador().getId();
			s+=", 'x':";
			s+=t.getJugador().getPosicion().getX();
			s+=", 'y':";
			s+=t.getJugador().getPosicion().getY();
			s+=", 'rx':";
			s+=t.getJugador().getPosicionRelativa().getX();
			s+=", 'ry':";
			s+=t.getJugador().getPosicionRelativa().getY();
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
	
	
	public static Mundo getInstance() {
		if(instance == null)
			instance = new Mundo();
		
		return instance;
	}
	
	//thread safe
	/*public static Mundo getInstance() {
		return MundoHolder.INSTANCE;			
	}
		
	private static class MundoHolder {
        static final Mundo INSTANCE = new Mundo();
    }*/	   
    
	public ArrayList<ThreadServer> getConnections() {
		return connections;
	}
	
	public synchronized void setConnections(ArrayList<ThreadServer> connections) {
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
		setConnectedUsers(connectedUsers-1);
	}

	public synchronized void actualizarPotenciadores(Jugador jugador, byte[] data){
		for (ThreadServer t: connections) {
 			if(t.getJugador().getId() != jugador.getId())
				t.sendData(data);
		}
	}
	
	public synchronized void actualizarPosicion(Jugador jugador, byte[] data){
		for (ThreadServer t: connections) {
 			//if(t.getJugador().getId() != jugador.getId())
				t.sendData(data);
		}
	}
	
	public synchronized void actualizarMuertes(Jugador jugador, byte[] data){
		for (ThreadServer t: connections) {
 			if(t.getJugador().getId() != jugador.getId())
				t.sendData(data);
 			else
 				t.getJugador().setMuerto(true);
		}
	}
	
	public synchronized void enviarBomba(String json,byte id) {
		for (ThreadServer t: connections) {
 			if(t.getJugador().getId() != id)
				t.sendData(json.getBytes(Charset.forName("UTF-8")));
		}
	}
	
	public Mapa getMap() {
		return map;
	}
	
}
