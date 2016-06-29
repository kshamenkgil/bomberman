package bomberman;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class PotenciadorSerializer implements JsonSerializer<Potenciador> {

	@Override
	public JsonElement serialize(Potenciador pot, Type arg1, JsonSerializationContext context) {
		final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("textura", pot.textura);
        jsonObject.add("Pos", context.serialize(pot.posicion));

        return jsonObject;
	}

}
