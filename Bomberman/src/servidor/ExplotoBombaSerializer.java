package servidor;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import bomberman.ExplotoBomba;
import bomberman.Tile;

public class ExplotoBombaSerializer implements JsonSerializer<ExplotoBomba> {

	@Override
	public JsonElement serialize(ExplotoBomba bomba, Type arg1, JsonSerializationContext context) {
		final JsonObject jsonObject = new JsonObject();
		JsonArray tiles = new JsonArray();
		JsonArray jugadores = new JsonArray();
		jsonObject.addProperty("header", "exploto_bomba");    
		
        jsonObject.add("posicion", context.serialize(bomba.getPosicion()));
        
        for (Tile tile : bomba.getTilesAfectados()) {
			tiles.add(context.serialize(tile));
		}
        
        for (byte id : bomba.getJugadoresMuertos()) {
			jugadores.add(context.serialize(id));
		}
        
        jsonObject.add("Tiles", tiles);
        jsonObject.add("Jugadores", jugadores);
        
        return jsonObject;
	}

}
