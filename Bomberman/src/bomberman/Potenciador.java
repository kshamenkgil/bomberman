package bomberman;

public abstract class Potenciador {
	protected String textura;
	
	public String getTextura() {
		return textura;
	}
	
	public Potenciador(String textura) {
		this.textura = textura;
	}
	
	public abstract void potenciar(Jugador jugador);
	
}
