package bomberman;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/*
 * 
 * Habría que usar esto o llamar a leer cada cierto tiempo en el update??
 * 
 * */
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
	
	public void run() {
		Protocolo p = new Protocolo();
		DataInputStream dIn = null;
		try {			
			dIn = new DataInputStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		while(running){			
			/*try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			try {				
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
		try {
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		    
}
