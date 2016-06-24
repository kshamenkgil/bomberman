package bomberman;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class TileMapSerializer implements JsonSerializer<TileMap>{

	@Override
	public JsonElement serialize(TileMap tilemap, Type arg1, JsonSerializationContext context) {
		final JsonObject jsonObject = new JsonObject();
        //jsonObject.add("Tile", context.serialize(tilemap.getTile()));
        //jsonObject.add("Potenciador", context.serialize(tilemap.getObjeto()));
		jsonObject.add("T", context.serialize(tilemap.getTile()));
        jsonObject.add("P", context.serialize(tilemap.getObjeto()));

        return jsonObject;			
	}

}
