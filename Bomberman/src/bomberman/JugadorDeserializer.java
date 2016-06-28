package bomberman;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import bomberman.Jugador;
import bomberman.Punto2D;

public class JugadorDeserializer implements JsonDeserializer<Jugador> {

	@Override
	public Jugador deserialize(JsonElement json, Type arg1, JsonDeserializationContext context)
			throws JsonParseException {
		
		JsonObject jsonObject = json.getAsJsonObject();		
		byte id = jsonObject.get("id").getAsByte();
		Punto2D pos = context.deserialize(jsonObject.get("pos"),Punto2D.class);
		Jugador j = null;
		j = new Jugador(pos, pos);
		j.setId(id);		
		
		return j;
	}		

}
