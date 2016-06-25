package tests;

import org.junit.*;

import bomberman.Jugador;
import bomberman.Punto2D;
import bomberman.Mundo;
import bomberman.Protocolo;

public class ClienteMuevePersonaje {
	@Test
	public void ClienteMuevePersonajeEste(){
				
		Jugador j = new Jugador(new Punto2D(10, 15),new Punto2D(10, 15));
		j.setPosicion(new Punto2D(10, 15));
		j.setId((byte) 2);
		Mundo.getInstance().getJugadores().add(j);
		
		Protocolo p = new Protocolo();
		
		//H+ID+D
		byte[] data = new byte[3];
		data[0] = Protocolo.MOVIMIENTO;
		data[1] = 2;
		data[2] = Protocolo.ESTE;
		
		p.procesarEntrada(data);
		Assert.assertEquals(10+(1*1), j.getPosicion().getX(),0.01);
	}
}
