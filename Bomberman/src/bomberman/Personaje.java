package bomberman;

public abstract class Personaje {	
	protected Sprite personaje;
	protected Punto2D posicion;
	protected float velocidad; 
	
	public abstract void atacar();
	
	public void mover(int direccion){ // norte sur este oeste
		// mover en x o y * velocidad 
	}
	
	public float getVelocidad() {
		return velocidad;
	}
	
	public void setVelocidad(float velocidad) {
		this.velocidad = velocidad;
	}
	
}
