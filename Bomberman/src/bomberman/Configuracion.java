package bomberman;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Configuracion {
	private static Configuracion instancia;	
	private int screenX, screenY;
	private boolean fullscreen;
	private String ip;
	private int puerto;
	
	
	public static Configuracion getInstancia() {
		// TODO Auto-generated constructor stub
		if(instancia == null){
			instancia = new Configuracion();
		}
		return instancia;
	}
	
	public void leerConfiguracion(){
		File inputFile = new File("config.xml");
        DocumentBuilderFactory dbFactory 
           = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();

	        this.fullscreen = Boolean.parseBoolean(doc.getElementsByTagName("fullscreen").item(0).getTextContent());	        
	        this.screenX = Integer.parseInt(doc.getElementsByTagName("x").item(0).getTextContent());
	        this.setScreenY(Integer.parseInt(doc.getElementsByTagName("y").item(0).getTextContent()));
	        this.ip = doc.getElementsByTagName("ip").item(0).getTextContent();
	        this.puerto = Integer.parseInt(doc.getElementsByTagName("port").item(0).getTextContent());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
	}
	
	public void guardarConfiguracion(){
		
	}

	public int getScreenX() {
		return screenX;
	}

	public void setScreenX(int screenX) {
		this.screenX = screenX;
	}

	public boolean isFullscreen() {
		return fullscreen;
	}

	public void setFullscreen(boolean resolucion) {
		this.fullscreen = resolucion;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public void setInstancia(Configuracion instancia) {
		Configuracion.instancia = instancia;
	}

	public int getScreenY() {
		return screenY;
	}

	public void setScreenY(int screenY) {
		this.screenY = screenY;
	}
		
}
