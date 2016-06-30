package bomberman;

public class CorrerMasRapido extends Potenciador{

	public CorrerMasRapido() {
		// TODO Auto-generated constructor stub
	}
	
	public CorrerMasRapido(String textura) {
		super(textura);
	}
	
	@Override
	public void potenciar(Jugador jugador) {
		//jugador.setVelocidad(jugador.getVelocidad()+0.1f);
		if(jugador.isNoCollide()==false)
			jugador.setNoCollide(true);
		else
			jugador.setNoCollide(false);
	}

}
