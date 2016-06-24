package bomberman;
import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class TileSerializer implements JsonSerializer<Tile> {

		@Override
		public JsonElement serialize(Tile tile, Type arg1, JsonSerializationContext arg2) {
			final JsonObject jsonObject = new JsonObject();
	        //jsonObject.addProperty("seRompe", tile.isSeRompe());
	        //jsonObject.addProperty("colisionable", tile.isColisionable());	        
			jsonObject.addProperty("sR", tile.isSeRompe());
	        jsonObject.addProperty("c", tile.isColisionable());

	        return jsonObject;			
		}
		  
	}