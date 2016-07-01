package servidor;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Timer;
import java.util.TimerTask;

import bomberman.Jugador;
//import bomberman.Mundo;
import bomberman.Punto2D;
import bomberman.Sprite;
import bomberman.Tile;
import bomberman.TileMap;
import bomberman.Engine;
import bomberman.ExplotoBomba;

public class Bomba {
	private Sprite bombaSprite;
	private int potencia;
	public static float tiempoExplosion = 3;
	private Punto2D posicion; //no deberia tambien tener atributo ubicacion?
	private Jugador jugadorPlantoBomba;
	private int tolerancia = 5;

	public Bomba(int potencia, float tiempoExplosion, Punto2D ubic, Jugador jugadorPlantoBomba, boolean noSprites) {		
		this.posicion = ubic;
		this.potencia = potencia;
		this.tiempoExplosion = tiempoExplosion;		
		this.jugadorPlantoBomba = jugadorPlantoBomba;
	}
	
	public Bomba(int potencia, float tiempoExplosion, Punto2D ubic, Jugador jugadorPlantoBomba) {
		this.posicion = ubic;
		this.potencia = potencia;
		this.tiempoExplosion = tiempoExplosion;
		this.bombaSprite = new Sprite("bomba", true);
		this.jugadorPlantoBomba = jugadorPlantoBomba;
	}
	
	public void setBombaSprite(Sprite bombaSprite) {
		this.bombaSprite = bombaSprite;
	}
	
	public Jugador getJugadorPlantoBomba() {
		return jugadorPlantoBomba;
	}
	
	public int getPotencia() {
		return potencia;
	}
	
	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}
		
	
	public void setPosicion(Punto2D posicion) {
		this.posicion = posicion;
	}
	
	public void explotar(float tiempoExplocion){
		//Mundo.getInstance().getBombas();
		final Bomba b = this;
		Timer t = new Timer();
		t.schedule(new TimerTask() {

            @Override
            public void run() {
            	ExplotoBomba exB = new ExplotoBomba();
            	exB.setPosicion(posicion);
            	            	
            	int x = (int)(posicion.getX()/Engine.TILE_WIDTH);
            	int y = (int)(posicion.getY()/Engine.TILE_HEIGHT);
            	
            	int px;
            	int py;
            	
            	int pot = 1;
            	//for (Bomba bomba : Mundo.getInstance().getBombas()) {
            		//if(bomba.getPosicion().equals(posicion)){
        				for (ThreadServer p1 : Mundo.getInstance().getConnections()){
        					if(getBounds(new Punto2D(posicion.getX(),posicion.getY())).intersects(new Rectangle((int)p1.getJugador().getPosicion().getX(), (int)p1.getJugador().getPosicion().getY(), Engine.TILE_WIDTH, Engine.TILE_HEIGHT))){
        						p1.getJugador().setVidas(0);
        						exB.getJugadoresMuertos().add(p1.getJugador().getId());
        					}
        				}
            			pot =1;
            		//en X positivo
                    	while( pot < potencia +1 && pot != 0 && x+pot <(int) Mundo.getInstance().getMap().getSize().getX()){
                			Tile t = Mundo.getInstance().getMap().getMapa()[x+pot][y].getTile();
            				if(t.getTileSprite() != null){
            					if(t.isSeRompe()){
	            					//t.setColisionable(false);
	            					Mundo.getInstance().getMap().getMapa()[((int)posicion.getX()/Engine.TILE_WIDTH)+pot][(int)posicion.getY()/Engine.TILE_HEIGHT].getTile().setColisionable(false);
	            					//t.setSeRompe(true);
	            					Mundo.getInstance().getMap().getMapa()[((int)posicion.getX()/Engine.TILE_WIDTH)+pot][(int)posicion.getY()/Engine.TILE_HEIGHT].getTile().setSeRompe(false);
	            					exB.getTilesAfectados().add(Mundo.getInstance().getMap().getMapa()[((int)posicion.getX()/Engine.TILE_WIDTH)+pot][(int)posicion.getY()/Engine.TILE_HEIGHT].getTile());
	            					pot=-1;
            					}
                    		}else
                    			{
                    				for (ThreadServer p : Mundo.getInstance().getConnections()){
                    					if(getBounds(new Punto2D(posicion.getX()+Engine.TILE_WIDTH*pot,posicion.getY())).intersects(new Rectangle((int)p.getJugador().getPosicion().getX(), (int)p.getJugador().getPosicion().getY(), Engine.TILE_WIDTH, Engine.TILE_HEIGHT))){
                    						p.getJugador().setVidas(0);
                    						exB.getJugadoresMuertos().add(p.getJugador().getId());
                    					}
                    					/*px = (int)( p.getJugador().getPosicion().getX()/Engine.TILE_WIDTH);
                    					py = (int)( p.getJugador().getPosicion().getY()/Engine.TILE_WIDTH);
                    					if(x+pot == px && y == py){
                    						p.getJugador().setVidas(0);
                    						exB.getJugadoresMuertos().add(p.getJugador().getId());
                    						pot=0;
                    					}*/
                    				}
                    			}
                    		pot++;
                    	}
                    	pot = 1;
                    	//en x neg
                    	while( pot < potencia +1 && pot != 0 && x-pot >0){
                			Tile t = Mundo.getInstance().getMap().getMapa()[x-pot][y].getTile();
            				if(t.getTileSprite() != null){
            					if(t.isSeRompe()){
	            					//t.setColisionable(false);
	            					Mundo.getInstance().getMap().getMapa()[((int)posicion.getX()/Engine.TILE_WIDTH)-pot][(int)posicion.getY()/Engine.TILE_HEIGHT].getTile().setColisionable(false);
	            					//t.setSeRompe(true);
	            					Mundo.getInstance().getMap().getMapa()[((int)posicion.getX()/Engine.TILE_WIDTH)-pot][(int)posicion.getY()/Engine.TILE_HEIGHT].getTile().setSeRompe(false);
	            					exB.getTilesAfectados().add(Mundo.getInstance().getMap().getMapa()[((int)posicion.getX()/Engine.TILE_WIDTH)-pot][(int)posicion.getY()/Engine.TILE_HEIGHT].getTile());
	            					pot=-1;
            					}
                    		}else
                			{
                				for (ThreadServer p : Mundo.getInstance().getConnections()){
                					if(getBounds(new Punto2D(posicion.getX()-Engine.TILE_WIDTH*pot,posicion.getY())).intersects(new Rectangle((int)p.getJugador().getPosicion().getX(), (int)p.getJugador().getPosicion().getY(), Engine.TILE_WIDTH, Engine.TILE_HEIGHT))){
                						p.getJugador().setVidas(0);
                						exB.getJugadoresMuertos().add(p.getJugador().getId());
                					}
                					/*px = (int)( p.getJugador().getPosicion().getX()/Engine.TILE_WIDTH);
                					py = (int)( p.getJugador().getPosicion().getY()/Engine.TILE_WIDTH);
                					if(x-pot == px && y == py){
                						p.getJugador().setVidas(0);
                						exB.getJugadoresMuertos().add(p.getJugador().getId());
                						pot=0;
                					}*/
                				}
                			}
                    		pot++;
                    	}
                    	pot = 1;
                    	//en Y pos
                    	while( pot < potencia +1 && pot != 0 && y+pot <(int) Mundo.getInstance().getMap().getSize().getY()){
                			Tile t = Mundo.getInstance().getMap().getMapa()[x][y+pot].getTile();
            				if(t.getTileSprite() != null){
            					if(t.isSeRompe()){
	            					//t.setColisionable(false);
	            					Mundo.getInstance().getMap().getMapa()[(int)posicion.getX()/Engine.TILE_WIDTH][((int)posicion.getY()/Engine.TILE_HEIGHT)+pot].getTile().setColisionable(false);
	            					//t.setSeRompe(true);
	            					Mundo.getInstance().getMap().getMapa()[(int)posicion.getX()/Engine.TILE_WIDTH][((int)posicion.getY()/Engine.TILE_HEIGHT)+pot].getTile().setSeRompe(false);
	            					exB.getTilesAfectados().add(Mundo.getInstance().getMap().getMapa()[((int)posicion.getX()/Engine.TILE_WIDTH)][(int)posicion.getY()/Engine.TILE_HEIGHT+pot].getTile());
	            					pot = -1;
            					}
                    		}else
                			{
                				for (ThreadServer p : Mundo.getInstance().getConnections()){
                					if(getBounds(new Punto2D(posicion.getX(),posicion.getY()+Engine.TILE_WIDTH*pot)).intersects(new Rectangle((int)p.getJugador().getPosicion().getX(), (int)p.getJugador().getPosicion().getY(), Engine.TILE_WIDTH, Engine.TILE_HEIGHT))){
                						p.getJugador().setVidas(0);
                						exB.getJugadoresMuertos().add(p.getJugador().getId());
                					}
                					/*px = (int)( p.getJugador().getPosicion().getX()/Engine.TILE_WIDTH);
                					py = (int)( p.getJugador().getPosicion().getY()/Engine.TILE_WIDTH);
                					if(x == px && y+ pot == py){
                						p.getJugador().setVidas(0);
                						exB.getJugadoresMuertos().add(p.getJugador().getId());                						
                					}*/
                				}
                			}
                    		pot++;
                    	}
                    	pot = 1;
                    	//en Y neg
                    	while( pot < potencia +1 && pot != 0 && y-pot >0){
                			Tile t = Mundo.getInstance().getMap().getMapa()[x][y-pot].getTile();
            				if(t.getTileSprite() != null){
            					if(t.isSeRompe()){
	            					//t.setColisionable(false);
	            					Mundo.getInstance().getMap().getMapa()[(int)posicion.getX()/Engine.TILE_WIDTH][((int)posicion.getY()/Engine.TILE_HEIGHT)-pot].getTile().setColisionable(false);
	            					//t.setSeRompe(true);
	            					Mundo.getInstance().getMap().getMapa()[(int)posicion.getX()/Engine.TILE_WIDTH][((int)posicion.getY()/Engine.TILE_HEIGHT)-pot].getTile().setSeRompe(false);
	            					exB.getTilesAfectados().add(Mundo.getInstance().getMap().getMapa()[((int)posicion.getX()/Engine.TILE_WIDTH)][(int)posicion.getY()/Engine.TILE_HEIGHT-pot].getTile());            					
	            					pot=-1;
            					}
                    		}else
                			{
                				for (ThreadServer p : Mundo.getInstance().getConnections()){
                					if(getBounds(new Punto2D(posicion.getX(),posicion.getY()-Engine.TILE_WIDTH*pot)).intersects(new Rectangle((int)p.getJugador().getPosicion().getX(), (int)p.getJugador().getPosicion().getY(), Engine.TILE_WIDTH, Engine.TILE_HEIGHT))){
                						p.getJugador().setVidas(0);
                						exB.getJugadoresMuertos().add(p.getJugador().getId());
                					}
                					/*px = (int)( p.getJugador().getPosicion().getX()/Engine.TILE_WIDTH);
                					py = (int)( p.getJugador().getPosicion().getY()/Engine.TILE_WIDTH);
                					if(x == px && y-pot == py){
                						p.getJugador().setVidas(0);
                						exB.getJugadoresMuertos().add(p.getJugador().getId());
                					}*/
                				}
                			}
                    		pot++;
                    	}
                    	//}
                    		
            			//b = bomba;
				//}
            	
            	Mundo.getInstance().sendExplotoBomba(exB);
            	
            	Mundo.getInstance().getBombas().remove(b);
            	//this.cancel();
            }
        }, (long)(Bomba.tiempoExplosion*1000));
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
	
	public Rectangle getBounds(Punto2D p){
		return new Rectangle((int)p.getX(),(int)p.getY(),Engine.TILE_WIDTH/2,Engine.TILE_HEIGHT/2);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)this.getPosicion().getX(),(int)this.getPosicion().getY(),Engine.TILE_WIDTH,Engine.TILE_HEIGHT);
	}
}
