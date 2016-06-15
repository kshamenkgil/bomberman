package bomberman;

public class Jugador extends Personaje {
	protected byte id; //aleatorio segun ingreso al servidor	
	private int vidas; 
	private int potenciaBomba;
	private int cantBombas;
	private byte color;
	
	public Jugador(Punto2D posicion) {
		super(posicion);
		this.vidas = 4;
		this.potenciaBomba = 10;
		this.velocidad = 1.0f;			
		
	}	
		
	
	public void setSprites(Sprite norte, Sprite sur, Sprite este ,Sprite oeste, Sprite muerte){
		this.personajeN = norte;
		this.personajeS = sur;
		this.personajeE = este;
		this.personajeO = oeste;
		this.personajeMuerte = muerte;	
	}
	
	@Override
	public void atacar() {
		// TODO Auto-generated method stub
		
	}
	
	public byte getId() {
		return id;
	}
	
	public void setId(byte id) {
		this.id = id;
	}
	
	public void setVidas(int vidas) {
		this.vidas = vidas;
	}
	
	public int getVidas() {
		return vidas;
	}
	
	public void setPotenciaBomba(int potenciaBomba) {
		this.potenciaBomba = potenciaBomba;
	}
	
	public int getPotenciaBomba() {
		return potenciaBomba;
	}
	
	public int getCantBombas() {
		return cantBombas;
	}
	
	public void setCantBombas(int cantBombas) {
		this.cantBombas = cantBombas;
	}
	
	public byte getColor() {
		return color;
	}
	
	public void setColor(byte color) {
		this.color = color;
	}
}
