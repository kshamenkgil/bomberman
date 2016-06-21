package servidor;

import java.net.*;

import bomberman.Jugador;

import java.io.*;

public class ThreadServer extends Thread {
	private Socket socket = null;
	private Jugador jugador = null;
	private Protocolo protocolo = null;
	private boolean isRunning = false;
	public ThreadServer(Socket socket, String name) {
		super(name);
		this.socket = socket;
		this.isRunning = true;
		this.protocolo = new Protocolo();
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
		
	    /*try {
			socket.setSoTimeout(1);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
	    		
		
		while(isRunning){
			/*try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			try {			
				byte[] message = null;								
				int length = dIn.readInt();                    // read length of incoming message
				if(length>0) {
				    message = new byte[length];
				    dIn.readFully(message, 0, message.length); // read the message				    
				}
								
				protocolo.getColaMensajes().add(message);
				protocolo.procesarEntrada();
				//dIn.close();
								
				//protocolo.procesarEntrada(message, jugador);				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}
		closeSocket();
	}
	
	public void sendData(final byte[] data){
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
			
		}
		/*new Thread(new Runnable() {			
			@Override
			public void run() {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}).start();*/
	}
	
	public Jugador getJugador() {
		return jugador;
	}
	
	public void setJugador(Jugador jugador) {		
		this.jugador = jugador;
		this.protocolo.setJugador(jugador);
	}
		
	
	public Socket getSocket() {
		return socket;
	}
	
	public void closeSocket(){
		terminate();
	}
	
}
