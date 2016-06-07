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
		this.isRunning = false;
	}
	
	public void run() {        		
		while(isRunning){
			try {			
				byte[] message = null;
				DataInputStream dIn = new DataInputStream(socket.getInputStream());				
				int length = dIn.readInt();                    // read length of incoming message
				if(length>0) {
				    message = new byte[length];
				    dIn.readFully(message, 0, message.length); // read the message
				}
				
				/*BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));				
				message = in.readLine();*/
				Protocolo protocolo = new Protocolo();
				protocolo.procesarEntrada(message, jugador);
				dIn.close();
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
				// TODO Auto-generated method stub
				send(data);
			}
		});
	}
	
	public Jugador getJugador() {
		return jugador;
	}
	
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	private void send(byte[] data){
		try {					
			DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
			dOut.writeInt(data.length); // write length of the message
			dOut.write(data);           // write the messag			
			dOut.close();
			//PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			//out.println();
			//out.close();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
