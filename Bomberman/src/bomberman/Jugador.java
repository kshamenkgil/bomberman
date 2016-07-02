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
	private boolean noCollide;
	private Color color;
	private String nombre = "Prueba";
	
	public Jugador(Punto2D posicion, Punto2D posicionRelativa) {
		super(posicion,posicionRelativa);
		this.vidas = 4;
		this.potenciaBomba = 1;
		this.velocidad = 1.0f;
		this.color = Color.WHITE;
		this.cantBombas = 1;
		this.cantBombasActual = 0;		
	}	
		
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public synchronized void setCantBombasActual(int cantBombasActual) {
		if(getCantBombasActual() < 0){
			this.cantBombasActual = 0;
			return;
		}
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
		
		if(!isMuerto())
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
		/*if(getCantBombasActual() < 0)
			setCantBombas(0);*/
		
		if(getCantBombasActual() < getCantBombas()){
			
			int x = (int)Math.floor((posicion.x+16)/(Engine.TILE_WIDTH));
			int y = (int)Math.floor((posicion.y+16)/(Engine.TILE_HEIGHT));
			if(Mundo.getInstance().getMap().getMapa()[x][y].getTile().isColisionable() || Mundo.getInstance().getMap().getMapa()[x][y].getTile().hayBomba())
				return;			
			setCantBombasActual(getCantBombasActual()+1);
			Mundo.getInstance().getMap().getMapa()[x][y].getTile().setHayBomba(true);
			Bomba bomba = new Bomba(this.potenciaBomba, 1, new Punto2D(x*Engine.TILE_WIDTH,y*Engine.TILE_HEIGHT), this);
			Mundo.getInstance().getBombas().add(bomba);
			Protocolo.enviarBomba(bomba);
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
	
	public synchronized void setPotenciaBomba(int potenciaBomba) {
		this.potenciaBomba = potenciaBomba;
	}
	
	public synchronized int getPotenciaBomba() {
		return potenciaBomba;
	}
	
	public synchronized int getCantBombas() {
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
