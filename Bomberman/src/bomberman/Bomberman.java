package bomberman;

import java.util.ArrayList;


public class Bomberman {
	
	private Cliente cliente = null;
	
	public Bomberman() {
		Configuracion.getInstancia().leerConfiguracion();
		System.out.println(Configuracion.getInstancia().isFullscreen());
	}
	
	public void update(){
		//actualizar mundo
	}
	
	public void dibujar(){
		//dibujar mundo
	}

	public void run() {
		cliente = new Cliente(Configuracion.getInstancia().getIp(), Configuracion.getInstancia().getPuerto());
		Mundo.getInstance().setJugador(new Jugador());
		cliente.recieveData();	
	}
}
