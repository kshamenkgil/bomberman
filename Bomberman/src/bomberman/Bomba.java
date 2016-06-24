package bomberman;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class Bomba {
	private Sprite bombaSprite;
	private int potencia;
	private float tiempoExplosion;
	private Punto2D posicion; //no deberia tambien tener atributo ubicacion?
	private Jugador jugadorPlantoBomba;
	private int tolerancia = 5;
	
	public Bomba(int potencia, float tiempoExplosion, Punto2D ubic, Jugador jugadorPlantoBomba) {		
		this.posicion = ubic;
		this.potencia = potencia;
		this.tiempoExplosion = tiempoExplosion;
		this.bombaSprite = new Sprite("bomba", true);
		this.jugadorPlantoBomba = jugadorPlantoBomba;
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
