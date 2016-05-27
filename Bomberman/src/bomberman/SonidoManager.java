package bomberman;

import java.util.ArrayList;

public class SonidoManager {
	private static SonidoManager instancia;
	private ArrayList<Sonido> sonidos = new ArrayList<Sonido>(); 
	public static SonidoManager getInstancia() {
		if(instancia == null){
			instancia = new SonidoManager();
		}
		return instancia;
	}
	
	public void agregarSonido(Sonido sonido){
		sonidos.add(sonido);
	}
	
	public void quitarSonido(Sonido sonido){
		sonidos.remove(sonido);
	}
	
	public void playSound(Sonido sonido){
		sonido.playSound();
	}
	
	public void pauseSound(Sonido sonido){
		sonido.pauseSound();
	}
	
	public void stopSound(Sonido sonido){
		sonido.stopSound();
	}

	public ArrayList<Sonido> getSonidos() {
		return sonidos;
	}
}
