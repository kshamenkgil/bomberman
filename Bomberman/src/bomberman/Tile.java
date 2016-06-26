package bomberman;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class Tile {
	private Sprite tileSprite; 
	//private double width, height; // 32x32, 64x64 , etc	
	private boolean seRompe;
	private boolean colisionable;
	private boolean hayBomba = false;
	private boolean exploto = false;
	private Punto2D posicion;
	private int tolerancia = 5;
	
	public Tile(boolean seRompe, boolean colisionable, Sprite tileSprite, Punto2D posicion) {
		this.seRompe = seRompe;
		this.colisionable = colisionable;
		this.tileSprite = tileSprite;
		this.posicion = posicion;
	}
	
	public boolean isExploto() {
		return exploto;
	}
	
	public synchronized void setExploto(boolean exploto) {
		this.exploto = exploto;
	}
	
	public Punto2D getPosicion() {
		return posicion;
	}
	
	public void dibujar(Graphics2D g, ImageObserver io, Punto2D pos){
		if(tileSprite != null){
			if(tileSprite.isPasoUnCiclo()){
				Mundo.getInstance().getMap().getMapa()[(int)posicion.x/Engine.TILE_WIDTH][(int)posicion.y/Engine.TILE_HEIGHT].getTile().setColisionable(false);
				Mundo.getInstance().getMap().getMapa()[(int)posicion.x/Engine.TILE_WIDTH][(int)posicion.y/Engine.TILE_HEIGHT].getTile().setSeRompe(false);
				tileSprite = null;
			}
			
			if(tileSprite != null)
				if(isExploto())				
					tileSprite.setLooping(true);
			
			
			if(tileSprite != null)
				tileSprite.dibujarTile(g, io, pos);
		}
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
//		return new Rectangle((int)posicion.getX(),(int)posicion.getY(),(int)tileSprite.getTileHeight()-tolerancia,(int)tileSprite.getTileWidth()-tolerancia);
		return new Rectangle((int)posicion.getX(),(int)posicion.getY(),Engine.TILE_WIDTH-tolerancia,Engine.TILE_HEIGHT-tolerancia);
	}

	public synchronized boolean hayBomba() {
		return hayBomba;
	}

	public void setHayBomba(boolean hayBomba) {
		this.hayBomba = hayBomba;
	}
	
	public synchronized void setSeRompe(boolean seRompe) {
		this.seRompe = seRompe;
	}
	
	public synchronized void setColisionable(boolean colisionable) {
		this.colisionable = colisionable;
	}
}
