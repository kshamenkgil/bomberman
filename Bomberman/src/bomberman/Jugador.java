package bomberman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public class Jugador extends Personaje {
	protected byte id; //aleatorio segun ingreso al servidor	
	private int vidas; 
	private int potenciaBomba;
	private int cantBombas;
	private int cantBombasActual;
	private Color color;
	private String nombre = "Prueba";
	
	public Jugador(Punto2D posicion, Punto2D posicionRelativa) {
		super(posicion,posicionRelativa);
		this.vidas = 4;
		this.potenciaBomba = 10;
		this.velocidad = 1.0f;
		this.color = Color.WHITE;
		this.cantBombas = 1;
		this.cantBombasActual = 0;
		
	}	
		
	public synchronized void setCantBombasActual(int cantBombasActual) {
		this.cantBombasActual = cantBombasActual;
	}
	
	public int getCantBombasActual() {
		return cantBombasActual;
	}
	
	public void dibujar(Graphics2D g ,ImageObserver io){		
		super.dibujar(g, io);
		Color color;
		switch(id){
			case 0:
				color = Color.CYAN;
				break;
			case 1:
				color = Color.RED;
				break;
			case 2:
				color = Color.GREEN;
				break;
			case 3:
				color = Color.YELLOW;
				break;
			default:
				color = Color.WHITE;
				break;
		}
		
		Engine.getInstancia().dibujarTexto(nombre, 8, color, g, new Punto2D(posicion.x, posicion.y-(personajeE.getTileWidth()/4)));
		
	}
	
	public void setSprites(Sprite norte, Sprite sur, Sprite este ,Sprite oeste, Sprite muerte){
		this.personajeN = norte;
		this.personajeS = sur;
		this.personajeE = este;
		this.personajeO = oeste;
		this.personajeMuerte = muerte;	
	}
	
	@Override
	public synchronized void atacar() { // poner bomba
		if(getCantBombasActual() < getCantBombas()){
			setCantBombasActual(cantBombasActual+1);
			Mundo.getInstance().getBombas().add(new Bomba(1, 1, posicion, this));
			//Mundo.getInstance().
		}		
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
	
	public synchronized void setCantBombas(int cantBombas) {
		this.cantBombas = cantBombas;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
