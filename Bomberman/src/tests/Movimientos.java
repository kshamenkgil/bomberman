package tests;

import org.junit.*;

import bomberman.Bomba;
import bomberman.Jugador;
import bomberman.Personaje;
import bomberman.Punto2D;
import bomberman.Sprite;
import bomberman.Tile;
import bomberman.Protocolo;

public class Movimientos {
/*-------------------------PERSONAJE SE MUEVA---------------*/
 @Test
public void queElPersonajeSeMueva(){

/*		Direcciones
		public static final byte NORTE = 1;
		public static final byte SUR = 2;
		public static final byte ESTE = 3;
		public static final byte OESTE = 4;
*/
	Jugador personaje = new Jugador(new Punto2D(5,5),new Punto2D(5,5));
	
	Punto2D p1 = new Punto2D(6,5);
	personaje.mover(Protocolo.ESTE);
	Assert.assertEquals(p1, personaje.getPosicion());//derecha

	p1 = new Punto2D(4,5);
	personaje.setPosicion(new Punto2D(5,5));
	personaje.mover(Protocolo.OESTE);
	Assert.assertEquals(p1, personaje.getPosicion());//izquierda
	
	p1 = new Punto2D(5,4);
	personaje.setPosicion(new Punto2D(5,5));
	personaje.mover(Protocolo.NORTE);
	Assert.assertEquals(p1, personaje.getPosicion());//arriba
	
	p1 = new Punto2D(5,6);
	personaje.setPosicion(new Punto2D(5,5));
	personaje.mover(Protocolo.SUR);
	Assert.assertEquals(p1, personaje.getPosicion());//abajo
	
	
 }
	
/*-------------------------PERSONAJE NO ATRAVIESE UNA PARED---------------	*/
  @Test 
	public  void queUnPersonajeNoAtravieseUnaPared() {
	            
	// Regla: Un personaje no puede atravesar una pared
	            
	            
	 Jugador personaje = new Jugador(new Punto2D(5,5),new Punto2D(5,5));
	 Tile bomba = new Tile(true,true,new Sprite("ex", false),new Punto2D(5,6));
	 //Tile(boolean seRompe, boolean colisionable, Sprite tileSprite, Punto2D posicion) {
	 Punto2D punto = new Punto2D(5, 5);
	 personaje.mover(Protocolo.ESTE);
	 Assert.assertEquals(punto,personaje.getPosicion());
	 }
	  /*          
	Personaje personaje = new personaje();
	            personaje.ubicar(0,0);
	            Pared pared = new Pared(0,1);
	            Punto2D punto = new Punto2D(0,0);
	            punto = personaje.getPosicion();
	            
	/* Cuando el personaje se quiere mover hacía la posición
	 *donde se encuentra una pared, el método mover
	 * debería retornar el punto correspondiente a la posición
	 * previa del personaje
*/
/*
	Assert.assertEquals(punto,personaje.mover(1));
	}
*/
	
	

 @Test
public void queNoSeAtravieseUnaBomba() {

	/*Regla: Un personaje puede atravesar
	* una bomba, la misma bloquea el paso 
	* al igual que lo hace una pared
	*/
	 Jugador personaje = new Jugador(new Punto2D(5,5),new Punto2D(5,5));
	 Bomba bomba = new Bomba(1,3,new Punto2D(5,6),personaje);
	 Punto2D punto = new Punto2D(5, 5);
	 personaje.mover(Protocolo.ESTE);
	 Assert.assertEquals(punto,personaje.getPosicion());
	 /*
	Personaje personaje = new personaje();
            personaje.ubicar(0,0);
       	Bomba bomba = new Bomba(0,1);
            Punto2D punto = new Punto2D;
            
punto = personaje.getPosicion();

Assert.assertEquals(punto,personaje.mover(1));*/
	 
}
	
}
