package bomberman;

public abstract class Potenciador {
	protected int textura;
	
	public int getTextura() {
		return textura;
	}
	
	public abstract void potenciar(Jugador jugador);
	
}
