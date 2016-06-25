package bomberman;

public class Enemigo extends Personaje {

	public Enemigo(Punto2D posicion, Punto2D posicionRelativa) {
		super(posicion, posicionRelativa);
	}
	
	@Override
	public void atacar() {
		// TODO Auto-generated method stub
		
	}
	
	public void mover(){
		//ver para donde se mueve, esquivar bombas, seguir personaje (AI)
		super.mover(0); //mover alguna direccion
	}
}
