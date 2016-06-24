package bomberman;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public class Tile {
	private Sprite tileSprite; 
	//private double width, height; // 32x32, 64x64 , etc	
	private boolean seRompe;
	private boolean colisionable;
	
	
	public Tile(boolean seRompe, boolean colisionable, Sprite tileSprite) {
		this.seRompe = seRompe;
		this.colisionable = colisionable;
		this.tileSprite = tileSprite;
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
	

	
	
}
