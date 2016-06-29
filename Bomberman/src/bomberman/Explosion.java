package bomberman;

import java.awt.Graphics2D;
import java.awt.Rectangle;
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
	
	public Sprite getExplosionMedio() {
		return explosionMedio;
	}
	
	public void dibujar(Graphics2D g, ImageObserver io){		
		float pot = 1;
    	int x = (int)(posicion.getX());
    	int y = (int)(posicion.getY());
    	
		explosionMedio.dibujarTile(g, io, posicion);

    	if(Mundo.getInstance().getJugador().getBounds().intersects(new Rectangle((int)(posicion.getX()*Engine.TILE_WIDTH), (int)((posicion.getY()*Engine.TILE_HEIGHT)), Engine.TILE_WIDTH, Engine.TILE_HEIGHT))){
    		if(!Mundo.getInstance().getJugador().isMuerto()){
    			Mundo.getInstance().getJugador().setMuerto(true);
    			Protocolo.enviarMuerte(Mundo.getInstance().getJugador().getId());
    		}        		
    	}
		
		//en X positivo
        while( pot < potencia +1 && pot > 0 && x+pot <(int) Mundo.getInstance().getMap().getSize().getX()-1){
        	if(pot == potencia)
        		explosionDerecha.dibujarTile(g, io, new Punto2D(posicion.getX()+pot, posicion.getY()));
        	else
        		explosionHorizontal.dibujarTile(g, io, new Punto2D(posicion.getX()+pot, posicion.getY()));
        	
        	if(Mundo.getInstance().getMap().getMapa()[(int)(posicion.getX()+pot)][(int)(posicion.getY())].getTile().isColisionable())
        		pot = -1; 
        	else{
            	if(Mundo.getInstance().getJugador().getBounds().intersects(new Rectangle((int)((posicion.getX()*Engine.TILE_WIDTH)+pot*Engine.TILE_WIDTH), (int)(posicion.getY()*Engine.TILE_HEIGHT), Engine.TILE_WIDTH, Engine.TILE_HEIGHT))){
            		if(!Mundo.getInstance().getJugador().isMuerto()){
            			Mundo.getInstance().getJugador().setMuerto(true);
            			Protocolo.enviarMuerte(Mundo.getInstance().getJugador().getId());
            		}
            	}
        	}
        	
        	pot++;
        }
        
        pot = 1;
        //en X negativo
        while( pot < potencia +1 && pot > 0 && x-pot>0){
        	if(pot == potencia)
        		explosionIzquierda.dibujarTile(g, io, new Punto2D(posicion.getX()-pot, posicion.getY()));
        	else
        		explosionHorizontal.dibujarTile(g, io, new Punto2D(posicion.getX()-pot, posicion.getY()));        
        	
        	if(Mundo.getInstance().getMap().getMapa()[(int)(posicion.getX()-pot)][(int)(posicion.getY())].getTile().isColisionable())
        		pot = -1;
        	else{
            	if(Mundo.getInstance().getJugador().getBounds().intersects(new Rectangle((int)((posicion.getX()*Engine.TILE_WIDTH)-pot*Engine.TILE_WIDTH), (int)(posicion.getY()*Engine.TILE_HEIGHT), Engine.TILE_WIDTH, Engine.TILE_HEIGHT))){
            		if(!Mundo.getInstance().getJugador().isMuerto()){
            			Mundo.getInstance().getJugador().setMuerto(true);
            			Protocolo.enviarMuerte(Mundo.getInstance().getJugador().getId());
            		}
            	}
        	}
        	
        	pot++;
        }
        
        pot = 1;        
        //en Y positivo
        while( pot < potencia +1 && pot > 0 && y+pot<(int) Mundo.getInstance().getMap().getSize().getY()-1){
        	if(pot == potencia)
        		explosionAbajo.dibujarTile(g, io, new Punto2D(posicion.getX(), posicion.getY()+pot));
        	else
        		explosionVertical.dibujarTile(g, io, new Punto2D(posicion.getX(), posicion.getY()+pot));        	        	
        	
        	
        	if(Mundo.getInstance().getMap().getMapa()[(int)posicion.getX()][(int)(posicion.getY()+pot)].getTile().isColisionable())
        		pot = -1;
        	else{
            	if(Mundo.getInstance().getJugador().getBounds().intersects(new Rectangle((int)(posicion.getX()*Engine.TILE_WIDTH), (int)((posicion.getY()*Engine.TILE_HEIGHT)+pot*Engine.TILE_WIDTH), Engine.TILE_WIDTH, Engine.TILE_HEIGHT))){
            		if(!Mundo.getInstance().getJugador().isMuerto()){
            			Mundo.getInstance().getJugador().setMuerto(true);
            			Protocolo.enviarMuerte(Mundo.getInstance().getJugador().getId());
            		}        		
            	}
        	}
        	
        	pot++;
        }
        
        pot = 1;
        //en Y negativo
        while( pot < potencia +1 && pot > 0 && y-pot >0 ){
        	if(pot == potencia)
        		explosionArriba.dibujarTile(g, io, new Punto2D(posicion.getX(), posicion.getY()-pot));
        	else
        		explosionVertical.dibujarTile(g, io, new Punto2D(posicion.getX(), posicion.getY()-pot));
        	
        		        	
        	if(Mundo.getInstance().getMap().getMapa()[(int)posicion.getX()][(int)(posicion.getY()-pot)].getTile().isColisionable())
        		pot = -1;
        	else{
            	if(Mundo.getInstance().getJugador().getBounds().intersects(new Rectangle((int)(posicion.getX()*Engine.TILE_WIDTH), (int)((posicion.getY()*Engine.TILE_HEIGHT)-pot*Engine.TILE_WIDTH), Engine.TILE_WIDTH, Engine.TILE_HEIGHT))){
            		if(!Mundo.getInstance().getJugador().isMuerto()){
            			Mundo.getInstance().getJugador().setMuerto(true);
            			Protocolo.enviarMuerte(Mundo.getInstance().getJugador().getId());
            		}
            	}            	
        	}
        	
        	pot++;
        }
        
	}
	
}
