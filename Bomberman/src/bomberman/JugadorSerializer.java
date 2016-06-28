package bomberman;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class JugadorSerializer implements JsonSerializer<Jugador> {

	@Override
	public JsonElement serialize(Jugador j, Type arg1, JsonSerializationContext context) {
		final JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("header", "pos");        
        jsonObject.addProperty("id", j.getId());
        jsonObject.add("pos", context.serialize(j.getPosicion()));        
        return jsonObject;
	}

}
