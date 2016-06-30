package servidor;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import bomberman.Engine;
import bomberman.Jugador;
import bomberman.Mapa;
import bomberman.Punto2D;

public class Server implements Runnable{
	
	private static boolean isRunning;
	private int connectedUsers;
	private byte lastId;
	private ArrayList<ThreadServer> connections = new ArrayList<ThreadServer>();
	private int cantPlayers = 4;
	private static ServerScreen pantalla;
	
	public static ServerScreen getPantalla() {
		return pantalla;
	}
	
	public static void setPantalla(ServerScreen pantalla) {
		Server.pantalla = pantalla;
	}
	
	public Server(ServerScreen pantalla) {
		setPantalla(pantalla);
		setRunning(false);
		this.connectedUsers = 0;
		this.lastId = 0;
	}

	public void run(){
		if(isRunning){
			System.out.println("El servidor ya esta en ejecución");
			return;
		}
		
		ServerSocket serverSocket = null;
		
        try {
            serverSocket = new ServerSocket(24556);
            getPantalla().consola.append("Servidor escuchando en puerto 24556\n");
            //System.out.println("Servidor escuchando en puerto 24556");
            
        } catch (IOException e) {
        	//System.out.println("No se puede escuchar en el puerto 24556");
        	getPantalla().consola.append("No se puede escuchar en el puerto 24556\n");
        	Thread.currentThread().interrupt();
        }
		
		isRunning = true;
		
		Mundo.getInstance().setCantPlayers(cantPlayers);
		
		MapAutoGeneration mAG = new MapAutoGeneration(new Punto2D(30, 30), 0.1);
		
		while(isRunning){
			Socket entrante = null;
			try {
				entrante = serverSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				getPantalla().consola.append("No se puede escuchar en el puerto 24556\n");
				//e.printStackTrace();
			}
			ThreadServer t = new ThreadServer(entrante,"Usuario" + connectedUsers);
			
			Punto2D p = new Punto2D(0, 0);
			Punto2D pR = new Punto2D(0, 0);
			
			switch(lastId){
				case 0:
					p = new Punto2D(1*Engine.TILE_WIDTH, 1*Engine.TILE_HEIGHT);
					pR = new Punto2D(1, 1);
					break;
				case 1:					
					p = new Punto2D((mAG.getMap().getSize().getX()-2)*Engine.TILE_WIDTH, 1*Engine.TILE_HEIGHT);
					pR = new Punto2D((mAG.getMap().getSize().getX()-2), 1);
					break;
				case 2:
					pR = new Punto2D(1, (mAG.getMap().getSize().getY()-2));
					p = new Punto2D(1*Engine.TILE_WIDTH, (mAG.getMap().getSize().getY()-2)*Engine.TILE_HEIGHT);
					break;
				case 3:
					p = new Punto2D((mAG.getMap().getSize().getX()-2)*Engine.TILE_WIDTH, (mAG.getMap().getSize().getY()-2)*Engine.TILE_HEIGHT);					
					pR = new Punto2D((mAG.getMap().getSize().getX()-2), (mAG.getMap().getSize().getY()-2));
					break;
			}
			
			Jugador j = new Jugador(p,pR);
			j.setId(lastId);	
			
			t.setJugador(j);
			
			t.start();
			
			byte data[] = new byte[2];
			data[0] = Protocolo.CONEXION;
			data[1] = lastId;
			
			t.sendData(data);

			connections.add(t);
			getPantalla().consola.append("Ingreso jugador con id "+lastId+"\n");
			this.lastId++;
			this.connectedUsers++;
			if(this.connectedUsers == this.cantPlayers)				
				setRunning(false);
			
		}
		
		//si el juego comenzó enviar info inicial e iniciar update
		Mundo.getInstance().setConnections(connections);
		Mundo.getInstance().setConnectedUsers(connectedUsers);
		mAG.saveMap(); //se guarda el mapa
		Mapa tMap = mAG.getMap();
		Mundo.getInstance().setMap(tMap);
		bomberman.Mundo.getInstance().setMap(tMap);
		Mundo.getInstance().sendMapa();
		Mundo.getInstance().sendStartInfo();
		
		
		update();
		
		//cierre del servidor
		try {
			serverSocket.close();
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public synchronized static void setRunning(boolean isRunning) {
		Server.isRunning = isRunning;
	}
	
	public ArrayList<ThreadServer> getConnections() {
		return connections;
	}
	
	public int getConnectedUsers() {
		return this.connectedUsers;
	}
	
	private void update(){
		setRunning(true);
		while(isRunning){
			
		}
	}
	
}
