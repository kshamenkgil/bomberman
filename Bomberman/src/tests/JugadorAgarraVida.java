package tests;

import org.junit.*;
import bomberman.*;

public class JugadorAgarraVida {
	
	@Test
	public void queUnJugadorAgarreUnaVida(){
		Jugador p1 = new Jugador();
		Assert.assertEquals(4, p1.getVidas());
		
		VidaExtra vidaExtra = new VidaExtra();
		vidaExtra.potenciar(p1);
		
		Assert.assertEquals(5, p1.getVidas());				
	}
}
