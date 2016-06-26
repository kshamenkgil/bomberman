package bomberman;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public class Sprite {
	private String textura;
	private boolean looping;
	private int cantImg;
	private int actualImg;
	private long tiempo;
	private long lastTiempo;
	private int tileWidth;
	private int tileHeight;
	private boolean dibujar;	
	private boolean hasToLoadSizes;
	private boolean pasoUnCiclo = false;	
	
	public Sprite(String textura, boolean loop) {
		this.hasToLoadSizes = false;
		this.looping = loop;
		this.textura = textura;
		this.tileHeight = Engine.getInstancia().getTextura(textura).getImagen().getHeight();
		this.tileWidth = this.tileHeight;
		this.cantImg = Engine.getInstancia().getTextura(textura).getImagen().getWidth() / this.tileWidth;
		this.actualImg = 0;
		this.lastTiempo = Engine.MS_PER_UPDATE / 2;
		this.dibujar = false;
		this.looping = loop;				
	}
	
	public void setLastTiempo(long lastTiempo) {
		this.lastTiempo = lastTiempo;
	}
	
	public Sprite(String textura, boolean loop, boolean hasToLoadSizes) {
		this.hasToLoadSizes = hasToLoadSizes;
		this.looping = loop;
		this.textura = textura;
		//this.tileHeight = Engine.getInstancia().getTextura(textura).getImagen().getHeight();
		//this.tileWidth = this.tileHeight;
		//this.cantImg = Engine.getInstancia().getTextura(textura).getImagen().getWidth() / this.tileWidth;
		this.actualImg = 0;
		this.lastTiempo = Engine.MS_PER_UPDATE;
		this.dibujar = false;
		this.looping = loop;				
	}

	
	public boolean isPasoUnCiclo() {
		return pasoUnCiclo;
	}
	
	public boolean HasToLoadSizes() {
		return hasToLoadSizes;
	}
	
	public void setSizes() {
		this.tileHeight = Engine.getInstancia().getTextura(textura).getImagen().getHeight();
		this.tileWidth = this.tileHeight;
		this.cantImg = Engine.getInstancia().getTextura(textura).getImagen().getWidth() / this.tileWidth;
	}
	
	
	public int getTileWidth() {
		return tileWidth;
	}
	
	public int getTileHeight() {
		return tileHeight;
	}
	
	public void dibujarTile(Graphics2D g, ImageObserver io, Punto2D pos){
		int x = (int)pos.getX()*this.tileWidth;
		int y = (int)pos.getY()*this.tileHeight;
		int frameX = (actualImg % cantImg) * this.tileWidth;
		int frameY = (actualImg / cantImg) * this.tileHeight;
		
		if(this.looping){
			
			//dibujar			
			g.drawImage(Engine.getInstancia().getTextura(textura).getImagen(), x, y, x+this.tileWidth, y+this.tileHeight,
		              frameX, frameY, frameX+this.tileWidth, frameY+this.tileHeight, io);
			
			//BufferedImage img = Engine.getInstancia().getTextura(textura).getImagen().getSubimage(this.tileWidth*actualImg, 0, this.tileWidth, this.tileHeight);				
			//g.drawImage(img, (int)pos.getX()*this.tileWidth, (int)pos.getY()*this.tileHeight, io);
			
			this.tiempo++;
			if(this.tiempo > this.lastTiempo){				
				this.tiempo = 0;
							
				if(actualImg < cantImg-1)
					actualImg++;
				else{
					pasoUnCiclo = true;
					actualImg = 0;
				}
			}
		}else{		
			//dibujar esto
			g.drawImage(Engine.getInstancia().getTextura(textura).getImagen(), x, y, x+this.tileWidth, y+this.tileHeight,
		              frameX, frameY, frameX+this.tileWidth, frameY+this.tileHeight, io);
			
			//BufferedImage img = Engine.getInstancia().getTextura(textura).getImagen().getSubimage(this.tileWidth*actualImg, 0, this.tileWidth, this.tileHeight);
			//g.drawImage(img, (int)pos.getX()*this.tileWidth, (int)pos.getY()*this.tileHeight, io);
			
		}
	}
	
	
	public void dibujar(Graphics2D g, ImageObserver io, Punto2D pos){
		int x = (int)pos.getX();
		int y = (int)pos.getY();
		int frameX = (actualImg % cantImg) * this.tileWidth;
		int frameY = (actualImg / cantImg) * this.tileHeight;
		if(this.looping){			
			//dibujar			
			g.drawImage(Engine.getInstancia().getTextura(textura).getImagen(), x, y, x+this.tileWidth, y+this.tileHeight,
		              frameX, frameY, frameX+this.tileWidth, frameY+this.tileHeight, io);
			//BufferedImage img = Engine.getInstancia().getTextura(textura).getImagen().getSubimage(this.tileWidth*actualImg, 0, this.tileWidth, this.tileHeight);				
			//g.drawImage(img, (int)pos.getX(), (int)pos.getY(), io);
			
			this.tiempo++;
			if(this.tiempo > this.lastTiempo){				
				this.tiempo = 0;
							
				if(actualImg < cantImg-1)
					actualImg++;
				else{
					pasoUnCiclo = true;
					actualImg = 0;
				}
			}
		}else{		
			//dibujar esto
			g.drawImage(Engine.getInstancia().getTextura(textura).getImagen(), x, y, x+this.tileWidth, y+this.tileHeight,
		              frameX, frameY, frameX+this.tileWidth, frameY+this.tileHeight, io);
			//BufferedImage img = Engine.getInstancia().getTextura(textura).getImagen().getSubimage(this.tileWidth*actualImg, 0, this.tileWidth, this.tileHeight);				
			//g.drawImage(img, (int)pos.getX(), (int)pos.getY(), io);
			
		}
	}
	
	public int getCantImg() {
		return cantImg;
	}
	
	public int getActualImg() {
		return actualImg;
	}
	
	public synchronized void setLooping(boolean looping) {
		this.looping = looping;
	}
	
	public boolean isLooping() {
		return looping;
	}
	
	public boolean isDibujar() {
		return dibujar;
	}
	
	public void setDibujar(boolean dibujar) {
		this.dibujar = dibujar;
	}
	
}
