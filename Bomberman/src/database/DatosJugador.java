package database;

import java.sql.*;

public class DatosJugador {
	private String id,pass;
	private int puntos;
	private int estado;
	
	public DatosJugador(String nombre, String password){
		id = nombre;
		pass = password;
		puntos = 0;
		estado = 0;
	}
	
	public String getId() {
		return id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public int getEstado(){
		return estado;
	}
	public void setEstado(int estado){
		this.estado = estado;
	}
}
