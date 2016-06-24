package bomberman;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class MapDeserializer implements JsonDeserializer<Mapa> {

	@Override
	public Mapa deserialize(JsonElement json, Type arg1, JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		//Mapa.header = jsonObject.get("header").getAsString();
		Mapa mapa = new Mapa();
		Punto2D size;
		TileMap[][] tm;
		size = context.deserialize(jsonObject.get("size"),Punto2D.class);
		tm = context.deserialize(jsonObject.get("TileMap"), TileMap[][].class);
		
		mapa.setMapa(tm);
		mapa.setSize(size);
		
		return mapa;
	}
	
	/*@Override
	public JsonElement serialize(Mapa mapa, Type arg1, JsonSerializationContext context) {
		final JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("header", Mapa.header);
        jsonObject.add("size", context.serialize(mapa.getSize()));
        
		final JsonArray filas = new JsonArray();
		JsonArray columnas = new JsonArray();
		
		for(int x = 0 ; x < mapa.getSize().getX(); x++){
			for(int y = 0 ; y < mapa.getSize().getY(); y++){
				
				columnas.add(context.serialize(mapa.getMapa()[x][y]));
				
			}			
			filas.add(columnas);
			columnas = new JsonArray();
		}
		
		jsonObject.add("TileMap", filas);
		
	    return jsonObject;		
	        
	}*/

}
