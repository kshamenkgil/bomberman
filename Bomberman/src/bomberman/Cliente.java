package bomberman;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Cliente {
	private Socket socket = null;
	private Thread receiver = null;
	private Receiver runnable = null;
	private boolean isReceiving = false;
	private boolean isLogged = false;
	private boolean errorLog = false;
	private String userName;
	private int puntosJugador = 0;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getPuntosJugador() {
		return puntosJugador;
	}
	
	public void setPuntosJugador(int puntosJugador) {
		this.puntosJugador = puntosJugador;
	}
	
	public boolean isErrorLog() {
		return errorLog;
	}
	
	public void setErrorLog(boolean errorLog) {
		this.errorLog = errorLog;
	}
	
	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
	
	public boolean isLogged() {
		return isLogged;
	}
	
	public Cliente(String host, int port) {
		try {
			this.socket = new Socket(host, port);
			this.isReceiving = false;
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "No se pudo conectar con el servidor");
			System.exit(0);
			//e.printStackTrace();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "No se pudo conectar con el servidor");
			System.exit(0);
			//e.printStackTrace();		
			//System.exit(0);
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
