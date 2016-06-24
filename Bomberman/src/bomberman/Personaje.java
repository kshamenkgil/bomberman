package bomberman;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;


public abstract class Personaje {	
	protected Sprite personajeN;
	protected Sprite personajeS;
	protected Sprite personajeE;
	protected Sprite personajeO;
	protected Sprite personajeMuerte;
	protected byte direccion;
	protected Punto2D posicion;
	protected Punto2D posicionRelativa;
	protected float velocidad; 	
	private int tolerancia = 3;
	
	public Personaje(Punto2D posicion, Punto2D posicionRelativa) {
		this.posicion = posicion;
		this.direccion = Protocolo.ESTE;
		this.velocidad = 1f;
		this.posicionRelativa = posicionRelativa;
	}
		
	public void setPosicionRelativa(Punto2D posicionRelativa) {
		this.posicionRelativa = posicionRelativa;
	}
	
	public Punto2D getPosicionRelativa() {
		return posicionRelativa;
	}
	
	public abstract void atacar();
	
	public void setPosicion(Punto2D posicion) {
		this.posicion = posicion;
	}
	
	public Punto2D getPosicion() {
		return posicion;
	}
	
	public synchronized void playAnimation(){
		switch(direccion){
		case Protocolo.NORTE:
			personajeN.setLooping(true);
			break;
		case Protocolo.SUR:
			personajeS.setLooping(true);
			break;
		case Protocolo.ESTE:
			personajeE.setLooping(true);
			break;
		case Protocolo.OESTE:
			personajeO.setLooping(true);
			break;
		}
	}
	
	public synchronized void stopAnimating(){
		switch(direccion){
		case Protocolo.NORTE:
			personajeN.setLooping(false);
			break;
		case Protocolo.SUR:
			personajeS.setLooping(false);
			break;
		case Protocolo.ESTE:
			personajeE.setLooping(false);
			break;
		case Protocolo.OESTE:
			personajeO.setLooping(false);
			break;
		}
	}
	
	public void dibujar(Graphics2D g ,ImageObserver io){
		switch(direccion){
			case Protocolo.NORTE:
				personajeN.dibujar(g, io, posicion);
				break;
			case Protocolo.SUR:
				personajeS.dibujar(g, io, posicion);
				break;
			case Protocolo.ESTE:
				personajeE.dibujar(g, io, posicion);
				break;
			case Protocolo.OESTE:
				personajeO.dibujar(g, io, posicion);
				break;
		}
	}
	
	public synchronized void mover(int direccion){ // norte sur este oeste
		switch(direccion){
			case Protocolo.NORTE: //cambiar por protocolo.norte
				//if(!colision(new Punto2D(posicionRelativa.x, posicionRelativa.y-1))){
				this.direccion = Protocolo.NORTE;		
				if(!colision(direccion)){
					this.posicion = new Punto2D(this.posicion.getX(), this.posicion.getY() - (1*this.velocidad));
							
				}
				break;
			case Protocolo.SUR: //cambiar por protocolo.sur
				//if(!colision(new Punto2D(posicionRelativa.x, posicionRelativa.y+1))){
				this.direccion = Protocolo.SUR;
				if(!colision(direccion)){
					this.posicion = new Punto2D(this.posicion.getX(), this.posicion.getY() + (1*this.velocidad));
					
				}
				break;
			case Protocolo.ESTE: //cambiar por protocolo.este
				//if(!colision(new Punto2D(posicionRelativa.x+1, posicionRelativa.y))){
				this.direccion = Protocolo.ESTE;
				if(!colision(direccion)){
					this.posicion = new Punto2D(this.posicion.getX() + (1*this.velocidad), this.posicion.getY());
					
				}
				break;
			case Protocolo.OESTE: //cambiar por protocolo.oeste
				//if(!colision(new Punto2D(posicionRelativa.x-1, posicionRelativa.y))){
				this.direccion = Protocolo.OESTE;
				if(!colision(direccion)){
					this.posicion = new Punto2D(this.posicion.getX() - (1*this.velocidad), this.posicion.getY());
					
				}
				break;
		}		
	}
	
	public boolean colision(int direccion) {
		for(int x = 0 ; x < Mundo.getInstance().getMap().getSize().getX(); x++){
			for(int y = 0 ; y < Mundo.getInstance().getMap().getSize().getY(); y++){
				Tile t = Mundo.getInstance().getMap().getMapa()[x][y].getTile();
				if(t.getTileSprite() != null){
					if(getBounds().intersects(t.getBounds()) && t.isColisionable()){
						if(posicion.y <= t.getPosicion().getY() - (t.getTileSprite().getTileHeight()/2))//Hit was from below the brick
							if(direccion == Protocolo.SUR)
								return true;
						if(posicion.y >= t.getPosicion().getY() + (t.getTileSprite().getTileHeight()/2))//Hit was from above the brick
							if(direccion == Protocolo.NORTE)
								return true;
						if(posicion.x >t.getPosicion().getX())//Hit was on right
							if(direccion == Protocolo.OESTE)
								return true;
						if(posicion.x < t.getPosicion().getX())//Hit was on left
							if(direccion == Protocolo.ESTE)
								return true;
					}
				}	
			}			
		}
		
		for (Bomba bomba : Mundo.getInstance().getBombas()) {			
				if(getBounds().intersects(bomba.getBounds())){
					if(posicion.y <= bomba.getPosicion().getY() - (bomba.getBombaSprite().getTileHeight()/2))//Hit was from below the brick
						if(direccion == Protocolo.SUR)
							return true;
					if(posicion.y >= bomba.getPosicion().getY() + (bomba.getBombaSprite().getTileHeight()/2))//Hit was from above the brick
						if(direccion == Protocolo.NORTE)
							return true;
					if(posicion.x >bomba.getPosicion().getX())//Hit was on right
						if(direccion == Protocolo.OESTE)
							return true;
					if(posicion.x < bomba.getPosicion().getX())//Hit was on left
						if(direccion == Protocolo.ESTE)
							return true;
				}			
		}
				
		return false;
	}
		
	public Rectangle getBounds(){		
		return new Rectangle((int)posicion.getX(),(int)posicion.getY(),Engine.TILE_WIDTH-tolerancia,Engine.TILE_HEIGHT-tolerancia);//(int)personajeN.getTileHeight(),(int)personajeN.getTileWidth());
	}
	
	public float getVelocidad() {
		return velocidad;
	}
	
	public void setVelocidad(float velocidad) {
		this.velocidad = velocidad;
	}
	
}
