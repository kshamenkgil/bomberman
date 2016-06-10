package bomberman;

public class Mapa {
	private TileMap mapa[][];
	private int sizeX, sizeY;
		
	public Mapa(String path) {
		//cargar tamaño y mapa puede ser en un xml json o algo asi
		for (int x = 0; x < this.sizeX; x++) {
			for (int y = 0; y < this.sizeY; y++) {
				//if(loQueLeiDelArchivo == explotable)
					//if(loQueLeiDelArchivo.potenciador == false)
						mapa[x][y] = new TileMap(new Tile(true, true, new Sprite("ex", false)),null);
			}
		}
	}
}
