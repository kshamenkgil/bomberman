package bomberman;

public abstract class Personaje {	
	protected Sprite personaje;
	protected Punto2D posicion;
	protected float velocidad; 
	
	public abstract void atacar();
	
	public void setPosicion(Punto2D posicion) {
		this.posicion = posicion;
	}
	
	public Punto2D getPosicion() {
		return posicion;
	}
	
	public void mover(int direccion){ // norte sur este oeste
		switch(direccion){
			case 0: //cambiar por protocolo.norte
				this.posicion = new Punto2D(this.posicion.getX(), this.posicion.getY() + (1*this.velocidad));
				break;
			case 1: //cambiar por protocolo.sur
				this.posicion = new Punto2D(this.posicion.getX(), this.posicion.getY() - (1*this.velocidad));
				break;
			case 2: //cambiar por protocolo.este
				this.posicion = new Punto2D(this.posicion.getX() + (1*this.velocidad), this.posicion.getY());
				break;
			case 3: //cambiar por protocolo.oeste
				this.posicion = new Punto2D(this.posicion.getX() - (1*this.velocidad), this.posicion.getY());
				break;
		}		
	}
	
	public float getVelocidad() {
		return velocidad;
	}
	
	public void setVelocidad(float velocidad) {
		this.velocidad = velocidad;
	}
	
}
