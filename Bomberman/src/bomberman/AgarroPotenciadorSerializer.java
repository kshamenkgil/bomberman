package bomberman;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


public class AgarroPotenciadorSerializer implements JsonSerializer<AgarroPotenciador> {

	@Override
	public JsonElement serialize(AgarroPotenciador ap, Type arg1, JsonSerializationContext context) {
		final JsonObject jsonObject = new JsonObject();		
		jsonObject.addProperty("header", "agarro_potenciador");    
		jsonObject.addProperty("id", ap.getId());
        jsonObject.add("posicion", context.serialize(ap.getPos()));        
                        
        jsonObject.add("potenciador", context.serialize(ap.getPot()));        
        
        return jsonObject;
	}

}
