package bomberman;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Protocolo {
	/*
	 * Protocolo
	 * movimiento: se recibe cabecera + id + direccion de movimiento, cliente calcula la nueva posicion con la velocidad(previamente enviada)
	 * tiles explotados: se envia cabecera + cantidad + posiciones
	 * enemigos?: se actualiza posicion
	 * bombas: cabecera + posicion + potencia (explosion calculada en servidor?)
	 * muertes: cabecera + id jugador
	 * fin de juego: cabecera
	 * comienzo de juego: cabecera + estado inicial(incluye posicion de personajes, enemigos, etc) 
	 * estados:
	 * 	listo: cabecera
	 * 	
	 */
		
	//Cabeceras
	public static final byte MOVIMIENTO = 0;
	public static final byte MOVIMIENTO_ENEMIGO = 1;
	public static final byte EXPLOTARON_TILES = 2;
	public static final byte COLOCO_BOMBA = 3;
	public static final byte MURIO_JUGADOR = 4;
	public static final byte COMIENZO_JUEGO = 5;
	public static final byte FIN_JUEGO = 6;
	public static final byte LISTO = 7;
	public static final byte DESCONEXION = 8;
	public static final byte GET_POTENCIADOR = 9;
	public static final byte CONEXION = 10;
	public static final byte JSON = 123;
	
	//Direcciones
	public static final byte NORTE = 1;
	public static final byte SUR = 2;
	public static final byte ESTE = 3;
	public static final byte OESTE = 4;
	
	public Protocolo() {
		// TODO Auto-generated constructor stub
	}
	
	public void procesarEntrada(byte[] data){
		byte header = data[0];
		switch(header){
			case CONEXION:
				setParametrosIniciales(data[1]);
				break;
			/*case COMIENZO_JUEGO:
				//COMENZAR JUEGO
				//enviar mapa la primera vez.
				//enviar posiciones
				//se puede poner en un metodo y llamarlo
				
				break;*/
			case MOVIMIENTO:
				moverJugador(data);
				break;
			case DESCONEXION:
				byte id = data[1];
				if(id == Mundo.getInstance().getJugador().getId()){				
					Bomberman.getInstancia().setCanClose(true);
				}else{
					Jugador j1 = null;
					for (Jugador j : Mundo.getInstance().getJugadores()) {
						if(j.getId() == id)
							j1 = j;
					}
					Mundo.getInstance().getJugadores().remove(j1);
				}
				break;
			case JSON:
				parseJSON(new String(data));				
				break;
		}			
	}
	
	private void setParametrosIniciales(byte id1) {
		int id = (int)id1;
		id = id + 1;
		Jugador j = new Jugador(new Punto2D(0,0));
		j.setSprites(new Sprite("p"+id+"n", true),new Sprite("p"+id+"s", true),
					 new Sprite("p"+id+"e", true),new Sprite("p"+id+"o", true),
					 new Sprite("p"+id+"m", true));
		
		Mundo.getInstance().setJugador(j);
		Mundo.getInstance().getJugador().id = id1;
		
	}

	private void parseJSON(String json){
		JsonParser parser = new JsonParser();
		JsonObject o = parser.parse(json).getAsJsonObject();
		if(o.get("header").toString() == "startInfo"){
			JsonArray ja = o.getAsJsonArray("jugadores");
			for (JsonElement jsonElement : ja) {				
				//String nombre = jsonElement.getAsJsonObject().get("name").getAsString();
				byte id = (byte)jsonElement.getAsJsonObject().get("id").getAsInt();
				int x = jsonElement.getAsJsonObject().get("x").getAsInt();
				int y = jsonElement.getAsJsonObject().get("y").getAsInt();				
				
				if(id == Mundo.getInstance().getJugador().getId())
					Mundo.getInstance().getJugador().setPosicion(new Punto2D(x, y));
				else{
					Jugador j = new Jugador(new Punto2D(x, y));
					j.setId(id);
					j.setSprites(new Sprite("p"+id+"n", true),new Sprite("p"+id+"s", true),
							 new Sprite("p"+id+"e", true),new Sprite("p"+id+"o", true),
							 new Sprite("p"+id+"m", true));
					Mundo.getInstance().getJugadores().add(j);
				}
			}
		}
		//System.out.println(o.get("header"));
	}
	
	private void moverJugador(byte[] data){
		for (Jugador jugador : Mundo.getInstance().getJugadores()) {
			if(jugador.id == data[1])
				jugador.mover(data[2]);
		}
	}
	
	public static void moverJugador(byte direccion){
		byte[] t = new byte[2];
        t[0] = Protocolo.MOVIMIENTO;
        t[1] = direccion;
        Bomberman.getInstancia().getCliente().sendData(t);
	}	
}
