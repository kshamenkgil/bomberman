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
	private int tolerancia = 0;
	protected boolean muerto;
	protected boolean noCollide;
	
	public Personaje(Punto2D posicion, Punto2D posicionRelativa) {
		this.posicion = posicion;
		this.direccion = Protocolo.ESTE;
		this.velocidad = 1f;
		this.posicionRelativa = posicionRelativa;
		this.muerto = false;
		this.noCollide = false;
	}
	
	public boolean isNoCollide() {
		return noCollide;
	}

	public synchronized void setNoCollide(boolean noCollide) {
		this.noCollide = noCollide;
	}
	
	public boolean isMuerto() {
		return muerto;
	}
	
	public synchronized void setMuerto(boolean muerto) {
		this.muerto = muerto;
	}
	
	public void setPosicionRelativa(Punto2D posicionRelativa) {
		this.posicionRelativa = posicionRelativa;
	}
	
	public Punto2D getPosicionRelativa() {
		return posicionRelativa;
	}
	
	public abstract void atacar();
	
	public synchronized void setPosicion(Punto2D posicion) {
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
	
	private void dibujarMuerte(Graphics2D g ,ImageObserver io){
		if(personajeMuerte.isPasoUnCiclo())
			return;
		
		personajeMuerte.dibujar(g, io, posicion);
		/*if(personajeMuerte.getCantImg() != personajeMuerte.getActualImg())
			personajeMuerte.dibujar(g, io, posicion);
		else
			personajeMuerte.setLooping(false);*/
	}
	
	public void dibujar(Graphics2D g ,ImageObserver io){
		if(!isMuerto()){
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
		}else{
			dibujarMuerte(g, io);
		}
	}
	
	public synchronized void mover(int direccion){ // norte sur este oeste
		switch(direccion){
			case Protocolo.NORTE: //cambiar por protocolo.norte
				//if(!colision(new Punto2D(posicionRelativa.x, posicionRelativa.y-1))){
				this.direccion = Protocolo.NORTE;		
				this.posicion = new Punto2D(this.posicion.getX(), this.posicion.getY() - (1*this.velocidad));
				break;
			case Protocolo.SUR: //cambiar por protocolo.sur
				//if(!colision(new Punto2D(posicionRelativa.x, posicionRelativa.y+1))){
				this.direccion = Protocolo.SUR;								
				this.posicion = new Punto2D(this.posicion.getX(), this.posicion.getY() + (1*this.velocidad));				
				break;
			case Protocolo.ESTE: //cambiar por protocolo.este
				//if(!colision(new Punto2D(posicionRelativa.x+1, posicionRelativa.y))){
				this.direccion = Protocolo.ESTE;				
				this.posicion = new Punto2D(this.posicion.getX() + (1*this.velocidad), this.posicion.getY());
				break;
			case Protocolo.OESTE: //cambiar por protocolo.oeste
				//if(!colision(new Punto2D(posicionRelativa.x-1, posicionRelativa.y))){
				this.direccion = Protocolo.OESTE;				
				this.posicion = new Punto2D(this.posicion.getX() - (1*this.velocidad), this.posicion.getY());					
				break;
		}
	}		
	
	public synchronized boolean moverServidor(int direccion){ // norte sur este oeste
		switch(direccion){
			case Protocolo.NORTE: //cambiar por protocolo.norte
				//if(!colision(new Punto2D(posicionRelativa.x, posicionRelativa.y-1))){
				this.direccion = Protocolo.NORTE;		
				if(!colision(direccion,new Rectangle(getBounds().x, getBounds().y - (int)(1*this.velocidad), getBounds().width, getBounds().height))){
					this.posicion = new Punto2D(this.posicion.getX(), this.posicion.getY() - (1*this.velocidad));
					return true;
				}
				else
					return false;
					//this.posicion = new Punto2D(this.posicion.getX(), this.posicion.getY()+0);				
			case Protocolo.SUR: //cambiar por protocolo.sur
				//if(!colision(new Punto2D(posicionRelativa.x, posicionRelativa.y+1))){
				this.direccion = Protocolo.SUR;				
				if(!colision(direccion,new Rectangle(getBounds().x, getBounds().y + (int)(1*this.velocidad), getBounds().width, getBounds().height))){
					this.posicion = new Punto2D(this.posicion.getX(), this.posicion.getY() + (1*this.velocidad));
					
					return true;
				}
				else
					return false;
					//this.posicion = new Punto2D(this.posicion.getX(), this.posicion.getY()-0);				
			case Protocolo.ESTE: //cambiar por protocolo.este
				//if(!colision(new Punto2D(posicionRelativa.x+1, posicionRelativa.y))){
				this.direccion = Protocolo.ESTE;
				if(!colision(direccion,new Rectangle(getBounds().x + (int)(1*this.velocidad), getBounds().y , getBounds().width, getBounds().height))){
					this.posicion = new Punto2D(this.posicion.getX() + (1*this.velocidad), this.posicion.getY());
					
					return true;
				}
				else
					return false;
					//this.posicion = new Punto2D(this.posicion.getX()-0, this.posicion.getY());				
			case Protocolo.OESTE: //cambiar por protocolo.oeste
				//if(!colision(new Punto2D(posicionRelativa.x-1, posicionRelativa.y))){
				this.direccion = Protocolo.OESTE;
				if(!colision(direccion,new Rectangle(getBounds().x - (int)(1*this.velocidad), getBounds().y , getBounds().width, getBounds().height))){
					this.posicion = new Punto2D(this.posicion.getX() - (1*this.velocidad), this.posicion.getY());					
					return true;
				}
				else
					return false;
					//this.posicion = new Punto2D(this.posicion.getX()+0, this.posicion.getY());
			
		}	
		return true;
	}
	
	public boolean colision(int direccion,Rectangle dr) {		
		for(int x = 0 ; x < Mundo.getInstance().getMap().getSize().getX(); x++){
			for(int y = 0 ; y < Mundo.getInstance().getMap().getSize().getY(); y++){
				Tile t = Mundo.getInstance().getMap().getMapa()[x][y].getTile();
				if(t.getTileSprite() != null){
					if(dr.intersects(t.getBounds()) && t.isColisionable()){
						if(posicion.y <= t.getPosicion().getY() - (Engine.TILE_HEIGHT/2))//Hit was from below the brick
							if(direccion == Protocolo.SUR)
								return true;
						if(posicion.y >= t.getPosicion().getY() + (Engine.TILE_WIDTH/2))//Hit was from above the brick
							if(direccion == Protocolo.NORTE)
								return true;
						if(posicion.x >t.getPosicion().getX())//Hit was on right
							if(direccion == Protocolo.OESTE)
								return true;
						if(posicion.x < t.getPosicion().getX())//Hit was on left
							if(direccion == Protocolo.ESTE)
								return true;
					}else if(dr.intersects(t.getBounds()) && t.isColisionable() && t.isSeRompe() && isNoCollide()){
						return false;
					}
				}	
			}			
		}
		
		for (servidor.Bomba bomba : servidor.Mundo.getInstance().getBombas()) {			
				if(dr.intersects(bomba.getBounds())){
					if(posicion.y <= bomba.getPosicion().getY() - (Engine.TILE_HEIGHT/2))//Hit was from below the brick
						if(direccion == Protocolo.SUR)
							return true;
					if(posicion.y >= bomba.getPosicion().getY() + (Engine.TILE_WIDTH/2))//Hit was from above the brick
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
	
	/*public synchronized Punto2D moverServidor(int direccion){ // norte sur este oeste
		Punto2D t = colisionTile(direccion);
		switch(direccion){
			case Protocolo.NORTE:
				this.direccion = Protocolo.NORTE;				
				if(t != null){
					this.posicion = new Punto2D(this.posicion.getX(), this.posicion.getY() - (1*this.velocidad));
					return t;
				}				
				return t;				
			case Protocolo.SUR: //cambiar por protocolo.sur
				//if(!colision(new Punto2D(posicionRelativa.x, posicionRelativa.y+1))){
				this.direccion = Protocolo.SUR;
				if(t != null){
					this.posicion = new Punto2D(this.posicion.getX(), this.posicion.getY() + (1*this.velocidad));
					return t;
				}				
				return t;				
			case Protocolo.ESTE:				
				this.direccion = Protocolo.ESTE;
				if(t != null){
					this.posicion = new Punto2D(this.posicion.getX() + (1*this.velocidad), this.posicion.getY());
					return t;
				}				
				return t;							
			case Protocolo.OESTE:
				this.direccion = Protocolo.OESTE;
				if(t != null){
					this.posicion = new Punto2D(this.posicion.getX() - (1*this.velocidad), this.posicion.getY());
					return t;
				}				
				return t;		
		}	
		return t;
	}*/
	
	/*public Punto2D colisionTile(int direccion){
		double posX = (posicion.x + (Engine.TILE_WIDTH/2)) / (Engine.TILE_WIDTH);
		double posY = (posicion.y + (Engine.TILE_WIDTH/2)) / (Engine.TILE_HEIGHT);
		double offsetX = posX - (int)Math.floor(posX);
		double offsetY = posY - (int)Math.floor(posY);
		int tX = (int)Math.floor(posX);
		int tY = (int)Math.floor(posY);
		
		
		switch(direccion){
			case Protocolo.NORTE:
				if(!servidor.Mundo.getInstance().getMap().getMapa()[tX][tY-1].getTile().isColisionable())
					return new Punto2D(offsetX, offsetY);
				break;
			case Protocolo.SUR:	
				if(!servidor.Mundo.getInstance().getMap().getMapa()[tX][tY+1].getTile().isColisionable())
					return new Punto2D(offsetX, offsetY);
			case Protocolo.ESTE:
				if(!servidor.Mundo.getInstance().getMap().getMapa()[tX+1][tY].getTile().isColisionable())
					return new Punto2D(offsetX, offsetY);
			case Protocolo.OESTE:
				if(!servidor.Mundo.getInstance().getMap().getMapa()[tX-1][tY].getTile().isColisionable())
					return new Punto2D(offsetX, offsetY);			
		}
		
		for (servidor.Bomba bomba : servidor.Mundo.getInstance().getBombas()) {
			if(getBounds().intersects(bomba.getBounds())){
				if(posicion.y <= bomba.getPosicion().getY() - (Engine.TILE_WIDTH/2))//Hit was from below the brick
					if(direccion == Protocolo.SUR)
						return new Punto2D(0, 0);
				if(posicion.y >= bomba.getPosicion().getY() + (Engine.TILE_HEIGHT/2))//Hit was from above the brick
					if(direccion == Protocolo.NORTE)
						return new Punto2D(0, 0);
				if(posicion.x >bomba.getPosicion().getX())//Hit was on right
					if(direccion == Protocolo.OESTE)
						return new Punto2D(0, 0);
				if(posicion.x < bomba.getPosicion().getX())//Hit was on left
					if(direccion == Protocolo.ESTE)
						return new Punto2D(0, 0);
			}
		}
		
		return null;
								
	}*/
	
	/*public Punto2D colision(int direccion) {
		for(int x = 0 ; x < Mundo.getInstance().getMap().getSize().getX(); x++){
			for(int y = 0 ; y < Mundo.getInstance().getMap().getSize().getY(); y++){
				Tile t = Mundo.getInstance().getMap().getMapa()[x][y].getTile();
				if(t.getTileSprite() != null){
					if(getBounds().intersects(t.getBounds()) && t.isColisionable()){
						if(posicion.y <= t.getPosicion().getY() - (t.getTileSprite().getTileHeight()/2))//Hit was from below the brick
							if(direccion == Protocolo.SUR)
								return new Punto2D(0,0);
						if(posicion.y >= t.getPosicion().getY() + (t.getTileSprite().getTileHeight()/2))//Hit was from above the brick
							if(direccion == Protocolo.NORTE)
								return new Punto2D(0,0);
						if(posicion.x >t.getPosicion().getX())//Hit was on right
							if(direccion == Protocolo.OESTE)
								return new Punto2D(0,0);
						if(posicion.x < t.getPosicion().getX())//Hit was on left
							if(direccion == Protocolo.ESTE)
								return new Punto2D(0,0);
					}
				}	
			}			
		}
		
		for (Bomba bomba : Mundo.getInstance().getBombas()) {			
				if(getBounds().intersects(bomba.getBounds())){
					if(posicion.y <= bomba.getPosicion().getY() - (bomba.getBombaSprite().getTileHeight()/2))//Hit was from below the brick
						if(direccion == Protocolo.SUR)
							return new Punto2D(0,0);
					if(posicion.y >= bomba.getPosicion().getY() + (bomba.getBombaSprite().getTileHeight()/2))//Hit was from above the brick
						if(direccion == Protocolo.NORTE)
							return new Punto2D(0,0);
					if(posicion.x >bomba.getPosicion().getX())//Hit was on right
						if(direccion == Protocolo.OESTE)
							return new Punto2D(0,0);
					if(posicion.x < bomba.getPosicion().getX())//Hit was on left
						if(direccion == Protocolo.ESTE)
							return new Punto2D(0,0);
				}			
		}
				
		return null;
	}*/
		
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
