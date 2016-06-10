package bomberman;

public class Jugador extends Personaje {
	protected int id; //aleatorio segun ingreso al servidor	
	private int vidas; 
	private int potenciaBomba;
	private int cantBombas;
	private byte color;
	
	public Jugador() {
		this.vidas = 4;
		this.potenciaBomba = 10;
		this.velocidad = 1.0f;
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
