package bomberman;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class Bomba {
	private Sprite bombaSprite;
	private int potencia;
	public static float tiempoExplosion = 2;
	private Punto2D posicion; //no deberia tambien tener atributo ubicacion?
	private Jugador jugadorPlantoBomba;
	private int tolerancia = 5;
	private boolean exploto = false;
	private boolean terminoExplosion = false;
	private Explosion explosion;
		
	
	public Bomba(int potencia, float tiempoExplosion, Punto2D ubic, Jugador jugadorPlantoBomba, boolean noSprites) {		
		this.posicion = ubic;
		this.potencia = potencia;
		//Bomba.tiempoExplosion = tiempoExplosion;
		this.jugadorPlantoBomba = jugadorPlantoBomba;
		explosion = new Explosion(potencia, ubic);
	}
	
	public Bomba(int potencia, float tiempoExplosion, Punto2D ubic, Jugador jugadorPlantoBomba) {
		this.posicion = ubic;
		this.potencia = potencia;
		//Bomba.tiempoExplosion = tiempoExplosion;
		this.bombaSprite = new Sprite("bomba", true);
		this.jugadorPlantoBomba = jugadorPlantoBomba;
		explosion = new Explosion(potencia, ubic);
	}
		
	public Explosion getExplosion() {
		return explosion;
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
	
	public static float getTiempoExplosion() {
		return tiempoExplosion;
	}
	
	public boolean isTerminoExplosion() {
		return terminoExplosion;
	}
	
	public synchronized void setTerminoExplosion(boolean terminoExplosion) {
		this.terminoExplosion = terminoExplosion;
	}
	
	private synchronized void setExploto(boolean exploto) {
		this.exploto = exploto;
	}
	
	public void dibujarExplosion(Graphics2D g, ImageObserver io){				
		
		if(explosion.getExplosionMedio().isPasoUnCiclo()){
			terminoExplosion = true;			
		}				
		
		if(terminoExplosion){			
			//sacar el hayBomba();
			Mundo.getInstance().getMap().getMapa()[(int)posicion.x/Engine.TILE_WIDTH][(int)posicion.y/Engine.TILE_HEIGHT].getTile().setHayBomba(false);
			//incrementar cant bombas			
			if(Mundo.getInstance().getJugador().getId() == this.jugadorPlantoBomba.getId()){
				Mundo.getInstance().getJugador().setCantBombasActual(Mundo.getInstance().getJugador().getCantBombasActual()-1);
			}
			//remover la bomba			
		}else{
			explosion.dibujar(g, io);
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
		//return new Rectangle((int)this.posicion.getX(),(int)this.posicion.getY(),(int)this.bombaSprite.getTileHeight()-tolerancia,(int)this.bombaSprite.getTileWidth()-tolerancia);
		return new Rectangle((int)posicion.getX(),(int)posicion.getY(),Engine.TILE_WIDTH-tolerancia,Engine.TILE_HEIGHT-tolerancia);
	}
}
