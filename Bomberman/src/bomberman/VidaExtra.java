package bomberman;

public class VidaExtra extends Potenciador {

	@Override
	public void potenciar(Jugador jugador) {
		// TODO Auto-generated method stub
		jugador.setVidas(jugador.getVidas()+1);
	}

}
