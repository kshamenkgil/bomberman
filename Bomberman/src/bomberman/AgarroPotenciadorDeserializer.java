package bomberman;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class AgarroPotenciadorDeserializer implements JsonDeserializer<AgarroPotenciador> {

	@Override
	public AgarroPotenciador deserialize(JsonElement json, Type arg1, JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
				
		AgarroPotenciador ap = new AgarroPotenciador();
		
		Punto2D posicion;
		Byte id;						
		Potenciador pot;
		posicion = context.deserialize(jsonObject.get("posicion"),Punto2D.class);
		pot = context.deserialize(jsonObject.get("potenciador"), Potenciador.class);
		id = jsonObject.get("id").getAsByte();
				
		ap.setPos(posicion);
		ap.setId(id);
		ap.setPot(pot);		
		
		return ap;
	}

}
