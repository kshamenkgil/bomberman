package servidor;

import java.net.*;

import bomberman.Jugador;

import java.io.*;

public class ThreadServer extends Thread {
	private Socket socket = null;
	private Jugador jugador = null;
	private boolean isRunning = false;
	public ThreadServer(Socket socket, String name) {
		super(name);
		this.socket = socket;
		this.isRunning = true;
	}
	
	public void terminate(){
		isRunning = false;
	}
	
	public void run() {        		
		DataInputStream dIn = null;
		try {
			dIn = new DataInputStream(socket.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(isRunning){
			try {			
				byte[] message = null;								
				int length = dIn.readInt();                    // read length of incoming message
				if(length>0) {
				    message = new byte[length];
				    dIn.readFully(message, 0, message.length); // read the message
				}
								
				//dIn.close();
				
				Protocolo protocolo = new Protocolo();
				protocolo.procesarEntrada(message, jugador);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}
		closeSocket();
	}
	
	public void sendData(final byte[] data){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {					
					DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
					dOut.writeInt(data.length); // write length of the message
					dOut.write(data);           // write the messag			
					//dOut.close();
					//PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					//out.println();
					//out.close();			
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public Jugador getJugador() {
		return jugador;
	}
	
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
		
	
	public Socket getSocket() {
		return socket;
	}
	
	public void closeSocket(){
		try {
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
