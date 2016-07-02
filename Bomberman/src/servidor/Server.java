package servidor;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

import bomberman.Engine;
import bomberman.Jugador;
import bomberman.Mapa;
import bomberman.Punto2D;
import database.Conector;
import database.DatosJugador;

public class Server implements Runnable{
	
	private static boolean isRunning,isRunning2;
	private int connectedUsers;	
	private static byte lastId;
	private ArrayList<ThreadServer> connections = new ArrayList<ThreadServer>();
	private int cantPlayers = 4;
	private static ServerScreen pantalla;
	private ServerSocket serverSocket = null;
		
	public synchronized int getCantPlayers() {
		return cantPlayers;
	}
	
	public synchronized void setCantPlayers(int cantPlayers) {
		this.cantPlayers = cantPlayers;
	}
	
	public static ServerScreen getPantalla() {
		return pantalla;
	}
	
	public synchronized void dispose(){
		//guardar stats
		getPantalla().consola.append("Guardando info...");
		Conector c = new Conector();
		for (ThreadServer threadServer : connections) {
			c.modificarEstado(threadServer.getName(), 0);
		}		
		//cerrar sockets
	/*	if(serverSocket != null){
			for (ThreadServer threadServer : connections) {
				threadServer.terminate();
				//threadServer.closeSocket();
			}
			try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//salir
		setRunning2(false);
		setRunning(false);*/
		System.exit(0);
	}
	
	public static void setPantalla(ServerScreen pantalla) {
		Server.pantalla = pantalla;
	}
	
	public Server(ServerScreen pantalla,int cantJugadores) {
		setCantPlayers(cantJugadores);
		Mundo.getInstance().setCantPlayers(cantJugadores);
		Mundo.getInstance().setCantVivos(cantJugadores);
		setPantalla(pantalla);
		setRunning(false);
		this.connectedUsers = 0;
		setLastId((byte) -1);
	}

	public synchronized static void disconnectUser(){
		Mundo.getInstance().disconnectConnectedUser();
		setLastId((byte) (getLastId()-1));				
	}
	
	public synchronized static byte getAndSetLastId(){
		setLastId((byte) (getLastId()+1));
		return getLastId();
	}
	
	public synchronized static byte getLastId() {
		return lastId;
	}
	
	public synchronized static void setLastId(byte lastId) {
		Server.lastId = lastId;
	}
	
	public void run(){
		if(isRunning){
			System.out.println("El servidor ya esta en ejecución");
			return;
		}
		
		serverSocket = null;
		
        try {
            serverSocket = new ServerSocket(24556);
            getPantalla().consola.append("Servidor escuchando en puerto 24556\n");            
            getPantalla().consola.append("Ingrese /help para mas informacion\n");
            //System.out.println("Servidor escuchando en puerto 24556");
            
        } catch (IOException e) {
        	//System.out.println("No se puede escuchar en el puerto 24556");
        	getPantalla().consola.append("No se puede escuchar en el puerto 24556\n");
        	Thread.currentThread().interrupt();
        }
		
		isRunning2 = true;
		
		//Mundo.getInstance().setCantPlayers(cantPlayers);
		
		MapAutoGeneration mAG = new MapAutoGeneration(new Punto2D(30, 30), 0.1);// , 0.05);
		Mapa tMap = mAG.getMap();
		Mundo.getInstance().setMap(tMap);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(isRunning2()){
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
					
					Jugador j = new Jugador(p,pR);
					//j.setId(lastId);
					
					t.setJugador(j);
					
					t.start();
					
					connections.add(t);
					Mundo.getInstance().setConnections(connections);						
					
					//if(this.connectedUsers == this.cantPlayers)			
					
				}
			}
		}).start();
		
		
		Mundo.getInstance().setConnections(connections);
		//Mundo.getInstance().setConnectedUsers(connectedUsers);
		
		while(Mundo.getInstance().getReadyUsers() < getCantPlayers()){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//si el juego comenzó enviar info inicial e iniciar update		
		//mAG.saveMap(); //se guarda el mapa
		
		bomberman.Mundo.getInstance().setMap(tMap);
		Mundo.getInstance().sendMapa();
		Mundo.getInstance().sendStartInfo();
				
		update();
		
		//cierre del servidor
		/*try {
			serverSocket.close();
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public static void setRunning2(boolean isRunning2) {
		Server.isRunning2 = isRunning2;
	}
	
	public boolean isRunning2() {
		return isRunning2;
	}
	
	public boolean isRunning() {
		return isRunning;
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
		byte id_winner = -1;
		setRunning(true);
		Mundo.getInstance().setCantVivos(Mundo.getInstance().getCantPlayers());
		while(isRunning){			
			if(Mundo.getInstance().getCantVivos()<=1){
				for (ThreadServer threadServer : connections) {
					if(!threadServer.getJugador().isMuerto())
						id_winner = threadServer.getJugador().getId();
				}
				
				if(id_winner == -1){
					//nadie gana
					String s = "{'header' : 'fin_juego', 'g_id': '-1'}";
					for (ThreadServer threadServer : connections) {
						threadServer.sendData(s.getBytes(Charset.forName("UTF-8")));
					}	
				}else{
					//gano x persona
					for (ThreadServer threadServer : connections) {
						String s = "{'header' : 'fin_juego', 'g_id': '"+ id_winner + "'}";						
						if(threadServer.getJugador().getId() == id_winner){
							Conector c = new Conector();
							DatosJugador dj = new DatosJugador(threadServer.getJugador().getNombre(), "");
//							dj.setPuntos(c.puntosJugador(dj.getId())+1);
	//						c.grabarPuntos(dj);
						}
						threadServer.sendData(s.getBytes(Charset.forName("UTF-8")));
					}					
				}
			}
		}
	}
	
}
