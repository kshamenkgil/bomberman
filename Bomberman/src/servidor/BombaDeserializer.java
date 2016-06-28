package servidor;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import bomberman.Jugador;
import bomberman.Punto2D;

public class BombaDeserializer implements JsonDeserializer<Bomba> {

	@Override
	public Bomba deserialize(JsonElement json, Type arg1, JsonDeserializationContext context)
			throws JsonParseException {
		
		JsonObject jsonObject = json.getAsJsonObject();
		int potencia = jsonObject.get("potencia").getAsInt();
		byte id = jsonObject.get("id").getAsByte();
		Punto2D pos = context.deserialize(jsonObject.get("pos"),Punto2D.class);
		Jugador j = null;
		for (ThreadServer ts : Mundo.getInstance().getConnections()) {
			if(ts.getJugador().getId() == id)
				j = ts.getJugador();
		}
		Bomba b = new Bomba(potencia, Bomba.tiempoExplosion, pos, j,true);
		
		return b;
	}		

}
