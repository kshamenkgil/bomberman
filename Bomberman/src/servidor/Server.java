package servidor;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import bomberman.Engine;
import bomberman.Jugador;
import bomberman.Punto2D;

public class Server implements Runnable{
	
	private boolean isRunning;
	private int connectedUsers;
	private byte lastId;
	private ArrayList<ThreadServer> connections = new ArrayList<ThreadServer>();
	public Server() {
		this.isRunning = false;
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
            System.out.println("Servidor escuchando en puerto 24556");
            
        } catch (IOException e) {
        	System.out.println("No se puede escuchar en el puerto 24556");
        	Thread.currentThread().interrupt();
        }
		
		isRunning = true;
		MapAutoGeneration mAG = new MapAutoGeneration(new Punto2D(50, 50), 10);
		while(isRunning){
			Socket entrante = null;
			try {
				entrante = serverSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ThreadServer t = new ThreadServer(entrante,"Usuario" + connectedUsers);
			
			Punto2D p = new Punto2D(0, 0);
			
			switch(lastId){
				case 0:
					p = new Punto2D(1*Engine.TILE_WIDTH, 1*Engine.TILE_HEIGHT);
					break;
				case 1:
					p = new Punto2D((mAG.getMap().getSize().getX()-2)*Engine.TILE_WIDTH, 1*Engine.TILE_HEIGHT);
					break;
				case 2:
					
					p = new Punto2D(1*Engine.TILE_WIDTH, (mAG.getMap().getSize().getY()-2)*Engine.TILE_HEIGHT);
					break;
				case 3:
					p = new Punto2D((mAG.getMap().getSize().getX()-2)*Engine.TILE_WIDTH, (mAG.getMap().getSize().getY()-2)*Engine.TILE_HEIGHT);					
					break;
			}
			
			Jugador j = new Jugador(p);
			j.setId(lastId);	
			
			t.setJugador(j);
			
			t.start();
			
			byte data[] = new byte[2];
			data[0] = Protocolo.CONEXION;
			data[1] = lastId;
			
			t.sendData(data);
												
			connections.add(t);
			
			this.lastId++;
			this.connectedUsers++;
			if(this.connectedUsers == 2)
				setRunning(false);
			
		}
		
		//si el juego comenzó enviar info inicial e iniciar update
		Mundo.getInstance().setConnections(connections);
		Mundo.getInstance().setConnectedUsers(connectedUsers);
		//mAG.saveMap(); //se guarda el mapa
		Mundo.getInstance().setMap(mAG.getMap());
		Mundo.getInstance().sendMapa();
		Mundo.getInstance().sendStartInfo();
		
		
		update();
		
		//cierre del servidor
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public synchronized void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
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
