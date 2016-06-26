package bomberman;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class TileMapDeserializer implements JsonDeserializer<TileMap> {

	@Override
	public TileMap deserialize(JsonElement json, Type arg1, JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		
		
		final JsonElement tile = jsonObject.get("T");
		Tile t = context.deserialize(tile,Tile.class);
		
		Potenciador p = null;
		final JsonElement potenciador = jsonObject.get("P");		
		if(potenciador != null)
			p = context.deserialize(potenciador,Potenciador.class);
								
		final TileMap tm = new TileMap(t, p);		
		return tm;
	}	

}
