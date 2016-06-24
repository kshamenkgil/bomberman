package bomberman;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class PotenciadorDeserializer implements JsonDeserializer<Potenciador> {

	@Override
	public Potenciador deserialize(JsonElement json, Type arg1, JsonDeserializationContext context)
			throws JsonParseException {
		
		JsonObject jsonObject = json.getAsJsonObject();
		String textura = jsonObject.get("textura").getAsString();
		Potenciador p = null;
		
		switch(textura){
			case "potc":
				p = new CorrerMasRapido(textura);
				break;
			case "potv":
				p = new VidaExtra(textura);
				break;
			case "potb":
				p = new BombaMasPotente(textura);
				break;
			case "potmb":
				p = new MasDeUnaBomba(textura);
				break;
		}
		
		return p;
	}		

}
