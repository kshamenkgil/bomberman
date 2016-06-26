package bomberman;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class Bomba {
	private Sprite bombaSprite;
	private int potencia;
	public static float tiempoExplosion = 5;
	private Punto2D posicion; //no deberia tambien tener atributo ubicacion?
	private Jugador jugadorPlantoBomba;
	private int tolerancia = 5;
	private boolean exploto = false;
	private boolean terminoExplosion = false;
	
	public Bomba(int potencia, float tiempoExplosion, Punto2D ubic, Jugador jugadorPlantoBomba, boolean noSprites) {		
		this.posicion = ubic;
		this.potencia = potencia;
		//Bomba.tiempoExplosion = tiempoExplosion;		
		this.jugadorPlantoBomba = jugadorPlantoBomba;
	}
	
	public Bomba(int potencia, float tiempoExplosion, Punto2D ubic, Jugador jugadorPlantoBomba) {
		this.posicion = ubic;
		this.potencia = potencia;
		//Bomba.tiempoExplosion = tiempoExplosion;
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
	
	public synchronized void explotar(){
		setExploto(true);
	}
	
	private synchronized void setExploto(boolean exploto) {
		this.exploto = exploto;
	}
	
	public void dibujarExplosion(Graphics2D g, ImageObserver io){		
		if(terminoExplosion){					
			//sacar el hayBomba();
			Mundo.getInstance().getMap().getMapa()[(int)posicion.x/Engine.TILE_WIDTH][(int)posicion.y/Engine.TILE_HEIGHT].getTile().setHayBomba(false);
			//remover la bomba
			Mundo.getInstance().getBombas().remove(this);
		}
	}
		
	public void dibujarBomba(Graphics2D g, ImageObserver io){
		if(!exploto)
			bombaSprite.dibujar(g, io, posicion);
		else
			this.dibujarExplosion(g,io);
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
