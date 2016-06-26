package bomberman;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class Bomba {
	private Sprite bombaSprite;
	private int potencia;
	public static float tiempoExplosion = 5;
	private Punto2D posicion; //no deberia tambien tener atributo ubicacion?
	private Jugador jugadorPlantoBomba;
	private int tolerancia = 5;

	public Bomba(int potencia, float tiempoExplosion, Punto2D ubic, Jugador jugadorPlantoBomba, boolean noSprites) {		
		this.posicion = ubic;
		this.potencia = potencia;
		//Bomba.tiempoExplosion = tiempoExplosion;		
		this.jugadorPlantoBomba = jugadorPlantoBomba;
	}
	
	public Bomba(int potencia, float tiempoExplosion, Punto2D ubic, Jugador jugadorPlantoBomba) {
		this.posicion = ubic;
		this.potencia = potencia;
		//Bomba.tiempoExplosion = tiempoExplosion;
		this.bombaSprite = new Sprite("bomba", true);
		this.jugadorPlantoBomba = jugadorPlantoBomba;
	}
	
	public void setBombaSprite(Sprite bombaSprite) {
		this.bombaSprite = bombaSprite;
	}
	
	public Jugador getJugadorPlantoBomba() {
		return jugadorPlantoBomba;
	}
	
	public int getPotencia() {
		return potencia;
	}
	
	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}
		
	
	public void setPosicion(Punto2D posicion) {
		this.posicion = posicion;
	}
	
	public void explotar(float tiempoExplocion){
		
	}
	
	public void dibujarExplosion(){
		
	}
		
	
	public void dibujarBomba(Graphics2D g, ImageObserver io){
		
		bombaSprite.dibujar(g, io, posicion);
	}
			
	public Punto2D getPosicion() {
		return posicion;
	}
	
	public Sprite getBombaSprite() {
		return bombaSprite;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)this.posicion.getX(),(int)this.posicion.getY(),(int)this.bombaSprite.getTileHeight()-tolerancia,(int)this.bombaSprite.getTileWidth()-tolerancia);
	}
}
