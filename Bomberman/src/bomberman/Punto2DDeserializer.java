package bomberman;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class Punto2DDeserializer implements JsonDeserializer<Punto2D> {

	@Override
	public Punto2D deserialize(JsonElement json, Type arg1, JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		
		final JsonElement jsonX = jsonObject.get("x");
		final JsonElement jsonY = jsonObject.get("y");
		final double x = jsonX.getAsDouble();
		final double y = jsonY.getAsDouble();				
		
		final Punto2D punto = new Punto2D(x,y);		
		return punto;
	}	

}
