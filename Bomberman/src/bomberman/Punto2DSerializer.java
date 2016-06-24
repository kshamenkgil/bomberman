package bomberman;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class Punto2DSerializer implements JsonSerializer<Punto2D> {

	@Override
	public JsonElement serialize(Punto2D p, Type arg1, JsonSerializationContext arg2) {
		final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("x", p.getX());
        jsonObject.addProperty("y", p.getY());
        
		return jsonObject;
	}
	

}
