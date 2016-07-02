package bomberman;

import java.nio.charset.Charset;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.google.gson.Gson;
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
	public static final byte READY = 11;
	public static final byte INICIAR_SESION = 12;
	public static final byte DESCONEXION_USER = 13;
	public static final byte JSON = 123;
	
	//Direcciones
	public static final byte NORTE = 1;
	public static final byte SUR = 2;
	public static final byte ESTE = 3;
	public static final byte OESTE = 4;
	public static final byte IDLE = 5;
	
	//Potenciadores
	public static final byte BOMBA_MAS_POTENTE = 0;
	public static final byte CORRER_MAS_RAPIDO = 1;
	public static final byte MAS_DE_UNA_BOMBA = 2;
	
	public Protocolo() {
		// TODO Auto-generated constructor stub
	}
	
	public void procesarEntrada(byte[] data){
		byte header = data[0];
		switch(header){			
			case CONEXION:
				setParametrosIniciales(data[1]);
				break;
			case GET_POTENCIADOR:
				byte id0 = data[1];
				byte pot = data[2];
				setPotenciador(id0, pot);
				break;
			case MURIO_JUGADOR:
				byte id1 = data[1];				
				for (Jugador j : Mundo.getInstance().getJugadores()) {
					if(j.getId() == id1)
						j.setMuerto(true);
				}
				break;
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
	
	private void setPotenciador(byte id, byte pot){
		Potenciador pote = null;
		switch(pot){
			case BOMBA_MAS_POTENTE:
				pote = new BombaMasPotente();
				break;
			case CORRER_MAS_RAPIDO:
				pote = new CorrerMasRapido();
				break;
			case MAS_DE_UNA_BOMBA:
				pote = new MasDeUnaBomba();
				break;						
		}
		
		if(Mundo.getInstance().getJugador().getId() == id)
			pote.potenciar(Mundo.getInstance().getJugador());
		else{			
			for (Jugador j : Mundo.getInstance().getJugadores()) {
				if(j.getId() == id)
					pote.potenciar(j);
			}
		}
			
		
		
	}
	
	private void setParametrosIniciales(byte id1) {
		int id = (int)id1;
		id = id + 1;
		Jugador j = new Jugador(new Punto2D(0,0),new Punto2D(0,0));
		j.setSprites(new Sprite("p"+id+"n", true),new Sprite("p"+id+"s", true),
					 new Sprite("p"+id+"e", true),new Sprite("p"+id+"o", true),
					 new Sprite("p"+id+"m", true));
		
		Mundo.getInstance().setJugador(j);
		Mundo.getInstance().getJugador().id = id1;
		Mundo.getInstance().getJugador().setNombre(Bomberman.getInstancia().getCliente().getUserName());
		
	}

	private void parseJSON(String json){
		JsonParser parser = new JsonParser();
		JsonObject o = parser.parse(json).getAsJsonObject();
		if(o.get("header").getAsString().compareTo("startInfo") == 0){
			JsonArray ja = o.getAsJsonArray("jugadores");
			for (JsonElement jsonElement : ja) {				
				String nombre = jsonElement.getAsJsonObject().get("name").getAsString();				
				byte id = (byte)jsonElement.getAsJsonObject().get("id").getAsInt();
				int x = jsonElement.getAsJsonObject().get("x").getAsInt();
				int y = jsonElement.getAsJsonObject().get("y").getAsInt();				
				int rx = jsonElement.getAsJsonObject().get("rx").getAsInt();
				int ry = jsonElement.getAsJsonObject().get("ry").getAsInt();
				
				if(id == Mundo.getInstance().getJugador().getId()){					
					Mundo.getInstance().getJugador().setPosicion(new Punto2D(x, y));
					Mundo.getInstance().getJugador().setPosicionRelativa(new Punto2D(rx, ry));
				}
				else{
					Jugador j = new Jugador(new Punto2D(x, y),new Punto2D(rx, ry));
					j.setId(id);
					int idd = id + 1;
					j.setSprites(new Sprite("p"+idd+"n", true),new Sprite("p"+idd+"s", true),
							 new Sprite("p"+idd+"e", true),new Sprite("p"+idd+"o", true),
							 new Sprite("p"+idd+"m", true));
					j.setNombre(nombre);
					Mundo.getInstance().getJugadores().add(j);
				}
			}
			Engine.getInstancia().setStartUpdate(true);
			
		}else if(o.get("header").getAsString().compareTo("mapa") == 0){
			Gson gson = new GsonBuilder()
					.registerTypeAdapter(bomberman.Punto2D.class, new bomberman.Punto2DDeserializer())				
					.registerTypeAdapter(bomberman.Potenciador.class, new bomberman.PotenciadorDeserializer())
					.registerTypeAdapter(bomberman.Tile.class, new bomberman.TileDeserializer())
					.registerTypeAdapter(bomberman.TileMap.class, new bomberman.TileMapDeserializer())
					.registerTypeAdapter(bomberman.Mapa.class, new bomberman.MapDeserializer())
					.create();
			
			Mundo.getInstance().setMap(gson.fromJson(json, Mapa.class));
			
		}else if(o.get("header").getAsString().compareTo("bomba") == 0){
			Gson gson = new GsonBuilder()
					.registerTypeAdapter(bomberman.Punto2D.class, new bomberman.Punto2DDeserializer())				
					.registerTypeAdapter(bomberman.Bomba.class, new bomberman.BombaDeserializer())
					.create();
			
			Bomba bomba = gson.fromJson(json, Bomba.class);			
			Mundo.getInstance().getBombas().add(bomba);		
			
		}else if(o.get("header").getAsString().compareTo("exploto_bomba") == 0){
			Gson gson = new GsonBuilder()
				.registerTypeAdapter(bomberman.Punto2D.class, new bomberman.Punto2DDeserializer())
				.registerTypeAdapter(bomberman.Tile.class, new bomberman.TileDeserializer())
				.registerTypeAdapter(bomberman.Bomba.class, new bomberman.BombaDeserializer())
				.registerTypeAdapter(bomberman.ExplotoBomba.class, new bomberman.ExplotoBombaDeserializer())
				.create();
			
			ExplotoBomba exB = gson.fromJson(json, ExplotoBomba.class);
			for (Bomba bomba : Mundo.getInstance().getBombas()) {
				if(bomba.getPosicion().equals(exB.getPosicion())){					
					bomba.explotar();
				}				
			}
			
			for (Tile tileExplotado : exB.getTilesAfectados()) {
				int x = (int)tileExplotado.getPosicion().getX() / Engine.TILE_WIDTH; 
				int y = (int)tileExplotado.getPosicion().getY() / Engine.TILE_HEIGHT;
				Mundo.getInstance().getMap().getMapa()[x][y].getTile().setExploto(true);
			}
									
			for (byte id : exB.getJugadoresMuertos()) {
				for (Jugador jugador : Mundo.getInstance().getJugadores()) {
					if(jugador.getId() == id){
						jugador.setMuerto(true);
					}
				}
				if(id == Mundo.getInstance().getJugador().getId())
					Mundo.getInstance().getJugador().setMuerto(true);
			}	
			
		}else if(o.get("header").getAsString().compareTo("pos") == 0){
			Gson gson = new GsonBuilder()
					.registerTypeAdapter(bomberman.Punto2D.class, new bomberman.Punto2DDeserializer())
					.registerTypeAdapter(bomberman.Jugador.class, new bomberman.JugadorDeserializer())
					.create();
			
			Jugador offset = gson.fromJson(json, Jugador.class);
			
			for (Jugador j : Mundo.getInstance().getJugadores()) {
				if(j.getId() == offset.id){
					Punto2D p = new Punto2D(j.getPosicion().getX()+offset.posicion.getX(), j.getPosicion().getY()+offset.posicion.getY());
					j.setPosicion(p);
				}
			}
		}else if(o.get("header").getAsString().compareTo("agarro_potenciador") == 0){
			Gson gson = new GsonBuilder()
					.registerTypeAdapter(bomberman.Punto2D.class, new bomberman.Punto2DDeserializer())
					.registerTypeAdapter(bomberman.Potenciador.class, new bomberman.PotenciadorDeserializer())
					.registerTypeAdapter(bomberman.AgarroPotenciador.class, new bomberman.AgarroPotenciadorDeserializer())
					.create();
			
			AgarroPotenciador ap = gson.fromJson(json, AgarroPotenciador.class);			
			
			if(Mundo.getInstance().getJugador().getId() == ap.getId())
				ap.getPot().potenciar(Mundo.getInstance().getJugador());
			
			for (Jugador j : Mundo.getInstance().getJugadores()) {
				if(j.getId() == ap.getId()){
					ap.getPot().potenciar(j);
				}
			}
			
			Mundo.getInstance().getMap().getMapa()[(int)ap.getPos().getX()][(int)ap.getPos().getY()].setPotenciador(null);
			
		}else if(o.get("header").getAsString().compareTo("iniciar_sesion") == 0){
			//String s = "{'header' : 'iniciar_sesion', 'estado': 'ok', 'puntos': usuario.puntos}";
			if(o.get("estado").getAsString().compareTo("ok") == 0){
				Bomberman.getInstancia().getCliente().setLogged(true);
				Bomberman.getInstancia().getCliente().setPuntosJugador(o.get("puntos").getAsInt());
			}
			else{
				Bomberman.getInstancia().getCliente().setErrorLog(true);
			}
		}
		
	}
	
	private void moverJugador(byte[] data){		
		for (Jugador jugador : Mundo.getInstance().getJugadores()) {							
			if(jugador.id == data[1]){
				
				if(data[2] == Protocolo.IDLE){
					jugador.stopAnimating();
					return;
				}
				
				jugador.mover(data[2]);
				jugador.playAnimation();
			}			
		}
		if(data[1] == Mundo.getInstance().getJugador().getId()){
			if(data[2] == Protocolo.IDLE){
				Mundo.getInstance().getJugador().stopAnimating();
				return;
			}
			Mundo.getInstance().getJugador().mover(data[2]);	        
			Mundo.getInstance().getJugador().playAnimation();
		}
	}

	public static void enviarBomba(Bomba bomba){
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(bomberman.Punto2D.class, new bomberman.Punto2DSerializer())				
				.registerTypeAdapter(bomberman.Bomba.class, new bomberman.BombaSerializer())
				.create();
		
		String json = gson.toJson(bomba);
		
		Bomberman.getInstancia().getCliente().sendData(json.getBytes(Charset.forName("UTF-8")));
	}	
	
	public static void enviarMuerte(byte id){
		byte[] t = new byte[2];
        t[0] = Protocolo.MURIO_JUGADOR;
        t[1] = id;
        Bomberman.getInstancia().getCliente().sendData(t);
	}	
	
	public static void moverJugador(byte direccion){
		byte[] t = new byte[2];
        t[0] = Protocolo.MOVIMIENTO;
        t[1] = direccion;
        Bomberman.getInstancia().getCliente().sendData(t);
	}	
}
