package tests;

import org.junit.*;

import bomberman.Bomba;
import bomberman.Jugador;
import bomberman.Mapa;
import bomberman.Punto2D;
import bomberman.Sprite;
import bomberman.Tile;

public class FuncionamientoBomba {
	

@Test
public void queSeDestruyaUnaPared() {

	/*Regla: Cuando la explosión de una 
	 *bomba alcanza a una pared “destruible”
	 *la misma debe desaparecer del mapa
*/
	Jugador personaje = new Jugador(new Punto2D(5,5),new Punto2D(5,5));
	Tile pared = new Tile(true,true,new Sprite("ex", false),new Punto2D(0,0));
	Mapa mapa = new Mapa();
	Bomba bomba = new Bomba(1,3,new Punto2D(1,0),personaje);
/*
punto = pared.getPosicion();
	bomba.explotar(0,1);
	
Assert.assertEquals(false,mapa.hayPared(punto));
*/
}
	

 @Test
public void queUnJugadorMuera(){
		
	/*Regla: Los personajes tienen una cantidad
	* determinada de vidas, cuando este muere
	* se le descuenta una vida hasta llegar a 0
	*/
	 Jugador personaje = new Jugador(new Punto2D(5,5),new Punto2D(5,5));
	 personaje.setMuerto(true);
	 Assert.assertEquals(true,personaje.isMuerto());
/*
	Personaje personaje = new Personaje();
	Assert.assertEquals(4, p1.getVidas());
		
	personaje.quitarUnaVida();
	Assert.assertEquals(3, personaje.getVidas());*/
	 
	}

}
