package bomberman;

public class Bomba {
	private Sprite bombaSprite;
	private float potencia;
	private float tiempoExplosion;
	private Punto2D ubicacion; //no deberia tambien tener atributo ubicacion?
	
		
	public Bomba(float potencia, float tiempoExplosion,Punto2D ubic) {
		ubicacion = new Punto2D(ubic.getX(),ubic.getY());
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
