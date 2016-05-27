package bomberman;

import java.util.ArrayList;

public class Bomberman {
	private Mapa map;
	private ArrayList<Jugador> jugadores;
	private ArrayList<Enemigo> enemigos;
	
	public Bomberman() {
		//cargar todo
		Mapa map = new Mapa("path");
	}
	
	public void update(){
		//actualizar mundo
	}
	
	public void dibujar(){
		//dibujar mundo
	}
}
