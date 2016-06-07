package tests;

import org.junit.*;

import bomberman.Jugador;
import bomberman.Punto2D;
import servidor.Protocolo;

public class ServidorMuevePersonaje {
	@Test
	public void ServidorMuevePersonaje(){
		Jugador j = new Jugador();
		j.setPosicion(new Punto2D(10, 15));
		Protocolo p = new Protocolo();
		
		//H+ID+D
		byte[] data = new byte[3];
		data[0] = Protocolo.MOVIMIENTO;
		data[1] = 2;
		data[2] = Protocolo.ESTE;
		
		p.procesarEntrada(data, j);
		Assert.assertEquals(10+(1*1), j.getPosicion().getX(),0.01);
	}
}
