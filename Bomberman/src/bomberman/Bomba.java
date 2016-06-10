package bomberman;

public class Bomba {
	private Sprite bombaSprite;
	private int potencia;
	private float tiempoExplosion;
	private Punto2D ubicacion; //no deberia tambien tener atributo ubicacion?
	
		
	public Bomba(int potencia, float tiempoExplosion,Punto2D ubic) {		
		ubicacion = new Punto2D(ubic.getX(),ubic.getY());
		this.potencia = potencia;
		this.tiempoExplosion = tiempoExplosion;
	}
	
	public void explotar(float tiempoExplocion){
		
	}
	
	public void dibujarExplosion(){
		
	}
	
	public void dibujarBomba(){
		
	}
			
	public Sprite getBombaSprite() {
		return bombaSprite;
	}
}
