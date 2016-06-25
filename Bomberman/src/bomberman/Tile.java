package bomberman;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class Tile {
	private Sprite tileSprite; 
	//private double width, height; // 32x32, 64x64 , etc	
	private boolean seRompe;
	private boolean colisionable;
	private Punto2D posicion;
	private int tolerancia = 5;
	
	public Tile(boolean seRompe, boolean colisionable, Sprite tileSprite, Punto2D posicion) {
		this.seRompe = seRompe;
		this.colisionable = colisionable;
		this.tileSprite = tileSprite;
		this.posicion = posicion;
	}

	public Punto2D getPosicion() {
		return posicion;
	}
	
	public void dibujar(Graphics2D g, ImageObserver io, Punto2D pos){
		if(tileSprite != null)
			tileSprite.dibujarTile(g, io, pos);
	}
	
	public boolean isColisionable() {
		return colisionable;
	}
	
	public Sprite getTileSprite() {
		return tileSprite;
	}	
	
	public void setTileSprite(Sprite tileSprite) {
		this.tileSprite = tileSprite;
	}
	
	public boolean isSeRompe() {
		return seRompe;
	}

	public Rectangle getBounds(){
		return new Rectangle((int)posicion.getX(),(int)posicion.getY(),(int)tileSprite.getTileHeight()-tolerancia,(int)tileSprite.getTileWidth()-tolerancia);
	}
	
}
