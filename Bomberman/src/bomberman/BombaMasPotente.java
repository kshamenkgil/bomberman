package bomberman;

public class BombaMasPotente extends Potenciador {

	@Override
	public void potenciar(Jugador jugador) {
		jugador.setPotenciaBomba(jugador.getPotenciaBomba()+10.0f);		
	}

}
