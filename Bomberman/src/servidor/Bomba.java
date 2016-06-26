package servidor;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Timer;
import java.util.TimerTask;

import bomberman.Jugador;
import bomberman.Punto2D;
import bomberman.Sprite;
import bomberman.Tile;
import bomberman.TileMap;
import bomberman.Engine;
import bomberman.ExplotoBomba;

public class Bomba {
	private Sprite bombaSprite;
	private int potencia;
	public static float tiempoExplosion = 5;
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
	Mundo.getInstance().getBombas();
		
		Timer t = new Timer();
		t.schedule(new TimerTask() {

            @Override
            public void run() {
            	ExplotoBomba exB = new ExplotoBomba();
            	exB.setPosicion(posicion);
            	
            	Bomba b = null;
            	int x = (int)(posicion.getX()/Engine.TILE_WIDTH);
            	int y = (int)(posicion.getY()/Engine.TILE_HEIGHT);
            	
            	int px;
            	int py;
            	
            	int pot = potencia;
            	for (Bomba bomba : Mundo.getInstance().getBombas()) { 
            		if(bomba.getPosicion() == posicion)
            			pot = potencia;
            		//en X positivo
                    	while( pot > 0){
                			Tile t = Mundo.getInstance().getMap().getMapa()[x+pot][y].getTile();
            				if(t.getTileSprite() != null){
            					//t.setColisionable(false);
            					Mundo.getInstance().getMap().getMapa()[((int)posicion.getX()/Engine.TILE_WIDTH)+pot][(int)posicion.getY()/Engine.TILE_HEIGHT].getTile().setColisionable(false);
            					//t.setSeRompe(true);
            					Mundo.getInstance().getMap().getMapa()[((int)posicion.getX()/Engine.TILE_WIDTH)+pot][(int)posicion.getY()/Engine.TILE_HEIGHT].getTile().setSeRompe(false);
            					exB.getTilesAfectados().add(Mundo.getInstance().getMap().getMapa()[((int)posicion.getX()/Engine.TILE_WIDTH)+pot][(int)posicion.getY()/Engine.TILE_HEIGHT].getTile());
            					pot=0;
                    		}else
                    			{
                    				for (ThreadServer p : Mundo.getInstance().getConnections()){
                    					px = (int)( p.getJugador().getPosicion().getX()/Engine.TILE_WIDTH);
                    					py = (int)( p.getJugador().getPosicion().getY()/Engine.TILE_WIDTH);
                    					if(x+pot == px && y == py){
                    						p.getJugador().setVidas(0);
                    						exB.getJugadoresMuertos().add(p.getJugador().getId());
                    						pot=0;
                    					}
                    				}
                    			}
                    		pot--;
                    	}
                    	pot = potencia;
                    	//en x neg
                    	while( pot > 0){
                			Tile t = Mundo.getInstance().getMap().getMapa()[x-pot][y].getTile();
            				if(t.getTileSprite() != null){
            					//t.setColisionable(false);
            					Mundo.getInstance().getMap().getMapa()[((int)posicion.getX()/Engine.TILE_WIDTH)-pot][(int)posicion.getY()/Engine.TILE_HEIGHT].getTile().setColisionable(false);
            					//t.setSeRompe(true);
            					Mundo.getInstance().getMap().getMapa()[((int)posicion.getX()/Engine.TILE_WIDTH)-pot][(int)posicion.getY()/Engine.TILE_HEIGHT].getTile().setSeRompe(false);
            					exB.getTilesAfectados().add(Mundo.getInstance().getMap().getMapa()[((int)posicion.getX()/Engine.TILE_WIDTH)+pot][(int)posicion.getY()/Engine.TILE_HEIGHT].getTile());
            					pot=0;
                    		}else
                			{
                				for (ThreadServer p : Mundo.getInstance().getConnections()){
                					px = (int)( p.getJugador().getPosicion().getX()/Engine.TILE_WIDTH);
                					py = (int)( p.getJugador().getPosicion().getY()/Engine.TILE_WIDTH);
                					if(x-pot == px && y == py){
                						p.getJugador().setVidas(0);
                						exB.getJugadoresMuertos().add(p.getJugador().getId());
                						pot=0;
                					}
                				}
                			}
                    		pot--;
                    	}
                    	pot = potencia;
                    	//en Y pos
                    	while( pot > 0){
                			Tile t = Mundo.getInstance().getMap().getMapa()[x][y+pot].getTile();
            				if(t.getTileSprite() != null){
            					//t.setColisionable(false);
            					Mundo.getInstance().getMap().getMapa()[(int)posicion.getX()/Engine.TILE_WIDTH][((int)posicion.getY()/Engine.TILE_HEIGHT)+pot].getTile().setColisionable(false);
            					//t.setSeRompe(true);
            					Mundo.getInstance().getMap().getMapa()[(int)posicion.getX()/Engine.TILE_WIDTH][((int)posicion.getY()/Engine.TILE_HEIGHT)+pot].getTile().setSeRompe(false);
            					exB.getTilesAfectados().add(Mundo.getInstance().getMap().getMapa()[((int)posicion.getX()/Engine.TILE_WIDTH)+pot][(int)posicion.getY()/Engine.TILE_HEIGHT].getTile());
            					pot = 0;
                    		}else
                			{
                				for (ThreadServer p : Mundo.getInstance().getConnections()){
                					px = (int)( p.getJugador().getPosicion().getX()/Engine.TILE_WIDTH);
                					py = (int)( p.getJugador().getPosicion().getY()/Engine.TILE_WIDTH);
                					if(x == px && y+ pot == py){
                						p.getJugador().setVidas(0);
                						exB.getJugadoresMuertos().add(p.getJugador().getId());                						
                					}
                				}
                			}
                    		pot--;
                    	}
                    	pot = potencia;
                    	//en Y neg
                    	while( pot > 0){
                			Tile t = Mundo.getInstance().getMap().getMapa()[x][y-pot].getTile();
            				if(t.getTileSprite() != null){
            					//t.setColisionable(false);
            					Mundo.getInstance().getMap().getMapa()[(int)posicion.getX()/Engine.TILE_WIDTH][((int)posicion.getY()/Engine.TILE_HEIGHT)-pot].getTile().setColisionable(false);
            					//t.setSeRompe(true);
            					Mundo.getInstance().getMap().getMapa()[(int)posicion.getX()/Engine.TILE_WIDTH][((int)posicion.getY()/Engine.TILE_HEIGHT)-pot].getTile().setSeRompe(false);
            					exB.getTilesAfectados().add(Mundo.getInstance().getMap().getMapa()[((int)posicion.getX()/Engine.TILE_WIDTH)+pot][(int)posicion.getY()/Engine.TILE_HEIGHT].getTile());            					
            					pot=0;
                    		}else
                			{
                				for (ThreadServer p : Mundo.getInstance().getConnections()){
                					px = (int)( p.getJugador().getPosicion().getX()/Engine.TILE_WIDTH);
                					py = (int)( p.getJugador().getPosicion().getY()/Engine.TILE_WIDTH);
                					if(x == px && y-pot == py){
                						p.getJugador().setVidas(0);
                						exB.getJugadoresMuertos().add(p.getJugador().getId());
                					}
                				}
                			}
                    		pot--;
                    	}
                    		
            			b = bomba;
				}
            	
            	Mundo.getInstance().sendExplotoBomba(exB);
            	
            	Mundo.getInstance().getBombas().remove(b);
            	//this.cancel();
            }
        }, 5000);
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
	
	public Rectangle getBounds(){
		return new Rectangle((int)this.posicion.getX(),(int)this.posicion.getY(),(int)this.bombaSprite.getTileHeight()-tolerancia,(int)this.bombaSprite.getTileWidth()-tolerancia);
	}
}
