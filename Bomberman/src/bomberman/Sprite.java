package bomberman;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
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
	
	
	public Sprite(String textura, boolean loop) {
		this.looping = loop;
		this.textura = textura;
		this.tileHeight = Engine.getInstancia().getTextura(textura).getImagen().getHeight();
		this.tileWidth = this.tileHeight;
		this.cantImg = Engine.getInstancia().getTextura(textura).getImagen().getWidth() / this.tileWidth;
		this.actualImg = 0;
		this.lastTiempo = Engine.MS_PER_UPDATE;
		this.dibujar = false;
		this.looping = false;
	}

	public void dibujarTile(Graphics2D g, ImageObserver io, Punto2D pos){
		//if(this.dibujar){
			if(this.looping){
				
				/*try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				//dibujar			
				
				BufferedImage img = Engine.getInstancia().getTextura(textura).getImagen().getSubimage(this.tileWidth*actualImg, 0, this.tileWidth, this.tileHeight);
				g.drawImage(img, (int)pos.getX()*this.tileWidth, (int)pos.getY()*this.tileHeight, io);
				
				this.tiempo++;
				if(this.tiempo > this.lastTiempo){				
					this.tiempo = 0;
								
					if(actualImg < cantImg-1)
						actualImg++;
					else
						actualImg = 0;
				}
			}else{		
				//dibujar esto
				BufferedImage img = Engine.getInstancia().getTextura(textura).getImagen().getSubimage(this.tileWidth*actualImg, 0, this.tileWidth, this.tileHeight);
				g.drawImage(img, (int)pos.getX()*this.tileWidth, (int)pos.getY()*this.tileHeight, io);
				
			}
		//}
	}
	
	public void dibujar(Graphics2D g, ImageObserver io, Punto2D pos){
		//if(this.dibujar){
			if(this.looping){
				
				/*try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				//dibujar			
				
				BufferedImage img = Engine.getInstancia().getTextura(textura).getImagen().getSubimage(this.tileWidth*actualImg, 0, this.tileWidth, this.tileHeight);
				g.drawImage(img, (int)pos.getX(), (int)pos.getY(), io);
				
				this.tiempo++;
				if(this.tiempo > this.lastTiempo){				
					this.tiempo = 0;
								
					if(actualImg < cantImg-1)
						actualImg++;
					else
						actualImg = 0;
				}
			}else{		
				//dibujar esto
				BufferedImage img = Engine.getInstancia().getTextura(textura).getImagen().getSubimage(this.tileWidth*actualImg, 0, this.tileWidth, this.tileHeight);
				g.drawImage(img, (int)pos.getX(), (int)pos.getY(), io);
				
			}
		//}
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
