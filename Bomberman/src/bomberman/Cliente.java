package bomberman;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	private Socket socket = null;
	private Thread receiver = null;
	private Receiver runnable = null;
	private boolean isReceiving = false;
	public Cliente(String host, int port) {
		try {
			this.socket = new Socket(host, port);
			this.isReceiving = false;
		} catch (UnknownHostException e) {			
			e.printStackTrace();			
		} catch (IOException e) {
			// TODO Auto-generated catch block		
			e.printStackTrace();
			System.out.println("NO SE PUDO CONECTAR CON EL SERVIDOR");
			System.exit(0);
		}
		
	}
	
	public void closeSocket(){
		
		//parar thread recieveData
		try {						
			runnable.terminate();
			//receiver.join();
			isReceiving = false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//cerrar socket
		/*try {			
			socket.close();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
	public void recieveData(){
		//thread que recibe datos del server
		if(!isReceiving){
			isReceiving = true;
			runnable = new Receiver(socket);
			receiver = new Thread(runnable,"client data receiver thread");		
			receiver.start();	
		}
		
	}
	
	public synchronized void sendData(final byte[] data){
		try {					
			DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
			dOut.writeInt(data.length); // write length of the message
			dOut.write(data);           // write the messag
			dOut.flush();				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}
}
