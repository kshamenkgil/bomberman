package bomberman;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public class Explosion {
	
	private Sprite explosionAbajo;
	private Sprite explosionArriba;
	private Sprite explosionDerecha;
	private Sprite explosionHorizontal;
	private Sprite explosionIzquierda;
	private Sprite explosionMedio;
	private Sprite explosionVertical;
	private float potencia;
	private Punto2D posicion;
	
	public Explosion(float potencia,Punto2D posicion) {
		this.explosionAbajo = new Sprite("eabajo", true);
		this.explosionArriba = new Sprite("earriba", true); 
		this.explosionDerecha = new Sprite("eder", true); 
		this.explosionHorizontal = new Sprite("ehoriz", true);
		this.explosionIzquierda = new Sprite("eizq", true);
		this.explosionMedio = new Sprite("emedio", true);
		this.explosionVertical = new Sprite("evertic", true);
		this.potencia = potencia;
		this.posicion = new Punto2D((int)posicion.getX()/Engine.TILE_WIDTH, (int)posicion.getY()/Engine.TILE_HEIGHT);		
	}
	
	public void dibujar(Graphics2D g, ImageObserver io){		
		explosionMedio.dibujarTile(g, io, posicion);		
		for(int x = 0 ; x < potencia; x++);{
			
		}
		
	}
	
}
