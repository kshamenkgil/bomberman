package bomberman;

public abstract class Potenciador {
	protected Textura textura;
	
	public Textura getTextura() {
		return textura;
	}
	
	public abstract void potenciar(Jugador jugador);
	
}
