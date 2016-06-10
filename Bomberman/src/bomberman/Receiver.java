package bomberman;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receiver implements Runnable{
	private volatile boolean running = true;
	private Socket socket = null;
	
	public Receiver(Socket socket) {
		this.socket = socket;
	}
	
	public void terminate(){
		this.running = false;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	@Override
	public void run() {
		Protocolo p = new Protocolo();
		while(running){
			DataInputStream dIn;
			try {
				dIn = new DataInputStream(socket.getInputStream());
				int length = dIn.readInt();                    // read length of incoming message
				if(length>0) {
				    byte[] message = new byte[length];
				    dIn.readFully(message, 0, message.length); // read the message
				    p.procesarEntrada(message);
				}						
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}				
	}		    
}