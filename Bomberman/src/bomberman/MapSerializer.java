package bomberman;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class MapSerializer implements JsonSerializer<Mapa> {

	@Override
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
	        
	}

}
