package bomberman;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public abstract class Potenciador {
	protected String textura;
	protected boolean visible;
	protected Punto2D posicion;
		
	public Punto2D getPosicion() {
		return posicion;
	}
	
	public void setPosicion(Punto2D posicion) {
		this.posicion = posicion;
	}
	
	public String getTextura() {
		return textura;
	}
	
	public Potenciador() {
		this.posicion = new Punto2D(0, 0);
	}
	
	public Potenciador(String textura) {
		this.textura = textura;
		this.visible = false;
		this.posicion = new Punto2D(0, 0);
	}
	
	public void dibujar(Graphics2D g, ImageObserver io, Punto2D pos){
		if(isVisible())
			g.drawImage(Engine.getInstancia().getTextura(textura).getImagen(),(int)pos.getX()*Engine.TILE_WIDTH,(int)pos.getY()*Engine.TILE_HEIGHT,Engine.TILE_WIDTH,Engine.TILE_HEIGHT,io);
	}
	
	public abstract void potenciar(Jugador jugador);

	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public Rectangle getBounds(int x, int y){
		return new Rectangle(x,y,Engine.TILE_WIDTH,Engine.TILE_HEIGHT);
	}
	
}
