package servidor;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.google.gson.Gson;

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
		while(isRunning){
			Socket entrante = null;
			try {
				entrante = serverSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ThreadServer t = new ThreadServer(entrante,"Usuario" + connectedUsers);
			
			Jugador j = new Jugador(new Punto2D(0, 0));
			j.setId(lastId);
			
			t.setJugador(j);
			
			t.start();
			
			byte data[] = new byte[2];
			data[0] = Protocolo.CONEXION;
			data[1] = lastId;
			
			t.sendData(data);
						
			String s = "{'header' : 'jugadores'}";
			
			t.sendData(s.getBytes(Charset.forName("UTF-8")));
			
			connections.add(t);
			
			this.lastId++;
			this.connectedUsers++;			
			Mundo.getInstance().setConnections(connections);
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
