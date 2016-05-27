package bomberman;

public class CorrerMasRapido extends Potenciador{

	@Override
	public void potenciar(Jugador jugador) {
		jugador.setVelocidad(jugador.getVelocidad()+1.0f);		
	}

}
