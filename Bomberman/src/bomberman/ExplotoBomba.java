package bomberman;

import java.util.ArrayList;

public class ExplotoBomba {
	private Punto2D posicion;
	private ArrayList<Tile> tilesAfectados = new ArrayList<Tile>();
	private ArrayList<Byte> jugadoresMuertos = new ArrayList<Byte>(); 
	
	public void setTilesAfectados(ArrayList<Tile> tilesAfectados) {
		this.tilesAfectados = tilesAfectados;
	}
	
	public ArrayList<Tile> getTilesAfectados() {
		return tilesAfectados;
	}
	
	public ArrayList<Byte> getJugadoresMuertos() {
		return jugadoresMuertos;
	}
	
	public void setJugadoresMuertos(ArrayList<Byte> jugadoresMuertos) {
		this.jugadoresMuertos = jugadoresMuertos;
	}

	public Punto2D getPosicion() {
		return posicion;
	}
	
	public void setPosicion(Punto2D posicion) {
		this.posicion = posicion;
	}
}
