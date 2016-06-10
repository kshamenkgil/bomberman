package bomberman;

public class MasDeUnaBomba extends Potenciador {

	@Override
	public void potenciar(Jugador jugador) {
		jugador.setCantBombas(jugador.getCantBombas()+1);		
	}

}
