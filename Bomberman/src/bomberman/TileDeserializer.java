package bomberman;
import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class TileDeserializer implements JsonDeserializer<Tile> {

		@Override
		public Tile deserialize(JsonElement json, Type arg1, JsonDeserializationContext context) throws JsonParseException {
			JsonObject jsonObject = json.getAsJsonObject();
			boolean seRompe = jsonObject.get("sR").getAsBoolean();
			boolean colisionable = jsonObject.get("c").getAsBoolean();
			Punto2D pos = context.deserialize(jsonObject.get("Pos"),Punto2D.class);			
			if(seRompe && colisionable)
				return new Tile(seRompe, colisionable, new Sprite("ex", false),pos);
			else if(colisionable)
				return new Tile(seRompe, colisionable, new Sprite("bl", false),pos);
			else
				return new Tile(seRompe, colisionable, null,pos);
		}
	
/*		@Override
		public JsonElement serialize(Tile tile, Type arg1, JsonSerializationContext arg2) {
			final JsonObject jsonObject = new JsonObject();
	        //jsonObject.addProperty("seRompe", tile.isSeRompe());
	        //jsonObject.addProperty("colisionable", tile.isColisionable());	        
			jsonObject.addProperty("sR", tile.isSeRompe());
	        jsonObject.addProperty("c", tile.isColisionable());

	        return jsonObject;			
		}*/
		  
	}