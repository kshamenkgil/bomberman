package bomberman;

public class CorrerMasRapido extends Potenciador{

	public CorrerMasRapido(String textura) {
		super(textura);
	}
	
	@Override
	public void potenciar(Jugador jugador) {
		jugador.setVelocidad(jugador.getVelocidad()+1.0f);		
	}

}
