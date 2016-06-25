package bomberman;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class BombaSerializer implements JsonSerializer<Bomba> {

	@Override
	public JsonElement serialize(Bomba bomba, Type arg1, JsonSerializationContext context) {
		final JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("header", "bomba");
        jsonObject.addProperty("potencia", bomba.getPotencia());
        jsonObject.addProperty("id", bomba.getJugadorPlantoBomba().getId());
        jsonObject.add("pos", context.serialize(bomba.getPosicion()));        
        return jsonObject;
	}

}
