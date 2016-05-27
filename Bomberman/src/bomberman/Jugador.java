package bomberman;

public class Jugador extends Personaje {
	private int id; //aleatorio segun ingreso al servidor	
	private int vidas; 
	private float potenciaBomba;
	
	
	public Jugador() {
		this.vidas = 4;
		this.potenciaBomba = 10.0f;
		this.velocidad = 10.0f;
	}	
	
	@Override
	public void atacar() {
		// TODO Auto-generated method stub
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setVidas(int vidas) {
		this.vidas = vidas;
	}
	
	public int getVidas() {
		return vidas;
	}
	
	public void setPotenciaBomba(float potenciaBomba) {
		this.potenciaBomba = potenciaBomba;
	}
	
	public float getPotenciaBomba() {
		return potenciaBomba;
	}
	
	
}
