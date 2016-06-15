package tests;

import org.junit.*;
import bomberman.*;

public class JugadorAgarraPotenciadores {
		
	/*
	 * Dado un jugador con cuatro vidas que agarra un potenciador de vida extra,
	 * el jugador incrementa su cantidad de vidas en uno. 
	 * */
	@Test
	public void queUnJugadorAgarreUnaVida(){
		Jugador p1 = new Jugador(new Punto2D(0, 0));
		Assert.assertEquals(4, p1.getVidas());
		
		Potenciador vidaExtra = new VidaExtra();
		vidaExtra.potenciar(p1);
		
		Assert.assertEquals(5, p1.getVidas());				
	}
	
	/*
	 * Dado un jugador con potencia de bomba 10 que agarra un potenciador de bomba,
	 * se incrementa la potencia en 10.
	 * */
	@Test
	public void queUnJugadorAgarreBombaMasPotente(){
		Jugador p1 = new Jugador(new Punto2D(0, 0));
		Assert.assertEquals(10.0f, p1.getPotenciaBomba(),0.001);
		
		Potenciador bombaMasPotente = new BombaMasPotente();
		bombaMasPotente.potenciar(p1);
		
		Assert.assertEquals(20.0f, p1.getPotenciaBomba(),0.001);				
	}
		
	/*
	 * Dado un jugador con velocidad 10 que agarra un potenciador para correr mas rapido,
	 * el jugador incrementa su velocidad en 1.
	 * */
	@Test
	public void queUnJugadorAgarreCorrerMasRapido(){
		Jugador p1 = new Jugador(new Punto2D(0, 0));
		Assert.assertEquals(10.0f, p1.getVelocidad(),0.001);
		
		Potenciador correrMasRapido = new CorrerMasRapido();
		correrMasRapido.potenciar(p1);
		
		Assert.assertEquals(11.0f, p1.getVelocidad(),0.001);				
	}
	
}
