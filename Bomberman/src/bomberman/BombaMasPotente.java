package bomberman;

public class BombaMasPotente extends Potenciador {

	public BombaMasPotente(String textura) {
		super(textura);
	}
	
	@Override
	public void potenciar(Jugador jugador) {		
		jugador.setPotenciaBomba(jugador.getPotenciaBomba()+1);		
	}

}
