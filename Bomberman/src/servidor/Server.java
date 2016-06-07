package servidor;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import bomberman.Jugador;

public class Server implements Runnable{
	
	private boolean isRunning;
	private int connectedUsers;
	private int lastId;
	private ArrayList<ThreadServer> connections;
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
            
        } catch (IOException e) {
        	System.out.println("No se puede escuchar en el puerto 24556");            
        }
		
		isRunning = true;
		while(isRunning){
			Socket entrante = null;
			try {
				entrante = serverSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ThreadServer t = new ThreadServer(entrante,"Usuario" + connectedUsers);
			Jugador j = new Jugador();
			j.setId(lastId);
			t.setJugador(j);			
			t.start();
			
			connections.add(t);
			//players.add(new Player(t));
			this.lastId++;
			this.connectedUsers++;			
		}
		
		//si el juego comenzó enviar info inicial e iniciar update
		Mundo.getInstance().setConnections(connections);
		
		update();
		
		//cierre del servidor
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<ThreadServer> getConnections() {
		return connections;
	}
	
	public int getConnectedUsers() {
		return this.connectedUsers;
	}
	
	private void update(){
		
	}
	
}
