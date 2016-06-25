package bomberman;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class BombaDeserializer implements JsonDeserializer<Bomba> {

	@Override
	public Bomba deserialize(JsonElement json, Type arg1, JsonDeserializationContext context)
			throws JsonParseException {
		
		JsonObject jsonObject = json.getAsJsonObject();
		int potencia = jsonObject.get("potencia").getAsInt();
		byte id = jsonObject.get("id").getAsByte();
		Punto2D pos = context.deserialize(jsonObject.get("pos"),Punto2D.class);
		Jugador j = null;
		for (Jugador tj : Mundo.getInstance().getJugadores()) {
			if(tj.getId() == id)
				j = tj;
		}
		Bomba b = new Bomba(potencia, Bomba.tiempoExplosion, pos, j);
		
		return b;
	}		

}
