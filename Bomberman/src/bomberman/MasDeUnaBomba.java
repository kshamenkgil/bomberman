package bomberman;

public class MasDeUnaBomba extends Potenciador {

	public MasDeUnaBomba() {
		// TODO Auto-generated constructor stub
	}
	
	public MasDeUnaBomba(String textura) {
		super(textura);
	}
	
	@Override
	public void potenciar(Jugador jugador) {
		jugador.setCantBombas(jugador.getCantBombas()+1);		
	}

}
