package bomberman;

public class TileMap {
	private Tile tile;
	private Potenciador potenciador;
	public TileMap(Tile tile, Potenciador obj) {
		this.tile = tile;
		if(obj != null)
			this.potenciador = obj;
	}
	
	public Potenciador getObjeto() {
		return potenciador;
	}
	
	public void setPotenciador(Potenciador potenciador) {
		this.potenciador = potenciador;
	}
	
	public Tile getTile() {
		return tile;
	}
}
