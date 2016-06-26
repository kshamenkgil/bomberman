package bomberman;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class ExplotoBombaDeserializer implements JsonDeserializer<ExplotoBomba> {

	@Override
	public ExplotoBomba deserialize(JsonElement json, Type arg1, JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		
		ExplotoBomba ex = new ExplotoBomba();
		
		Punto2D posicionBomba;
		Byte[] jugadores;						
		Tile[] tilesAfectados;
		posicionBomba = context.deserialize(jsonObject.get("pos"),Punto2D.class);
		tilesAfectados = context.deserialize(jsonObject.get("Tiles"), Tile[].class);
		jugadores = context.deserialize(jsonObject.get("Jugadores"), Byte[].class);
				
		ex.setPosicion(posicionBomba);
		ex.setTilesAfectados(new ArrayList<Tile>(Arrays.asList(tilesAfectados)));		
		ex.setJugadoresMuertos(new ArrayList<Byte>(Arrays.asList(jugadores)));
		
		return ex;
	}

}
