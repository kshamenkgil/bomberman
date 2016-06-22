package bomberman;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;


public class SonidoManager {
	private static SonidoManager instancia;
	private Hashtable<String,Sonido> sonidos = new Hashtable<String,Sonido>();
	//private Queue<Sonido> cola = new LinkedList<Sonido>();
	private static int MAX_SONIDOS = 5;
	private int cSonidos = 0;
	
	public void dispose(){
		Iterator<Map.Entry<String,Sonido>> it = sonidos.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String,Sonido> entry = it.next();
			entry.getValue().stopSound();
		}
	}
	
	public static SonidoManager getInstancia() {
		if(instancia == null){			
			instancia = new SonidoManager();
		}
		return instancia;
	}
	
	public void agregarSonido(String nombre, Sonido sonido){
		sonidos.put(nombre, sonido);
	}
	
	public void quitarSonido(String sonido){
		sonidos.remove(sonido);
	}
	
	public void playSound(final String sonido){
		/*if(cSonidos >= MAX_SONIDOS)
			return;
		cSonidos++;*/
		sonidos.get(sonido).playSound();		
	}
	
	public void pauseSound(String sonido){
		sonidos.get(sonido).pauseSound();
	}
	
	public void stopSound(String sonido){
		sonidos.get(sonido).stopSound();
	}
	
	public synchronized void setcSonidos(int cSonidos) {
		this.cSonidos = cSonidos;
	}
	
	public int getcSonidos() {
		return cSonidos;
	}
	
	public Hashtable<String, Sonido> getSonidos() {
		return sonidos;
	}
}
