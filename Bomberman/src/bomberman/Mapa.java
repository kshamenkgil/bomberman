package bomberman;

public class Mapa {
	public static String header = "mapa";
	private TileMap mapa[][];
	//private int sizeX, sizeY;
	private Punto2D size;
	
	public Mapa() {
		// TODO Auto-generated constructor stub
	}
	
	public Mapa(TileMap mapa[][], Punto2D size) {		
		this.mapa = new TileMap[(int)size.x][(int)size.y];
		this.mapa = mapa;		
		this.size = size;		
	}
	
	public Punto2D getSize() {
		return size;
	}
	
	public TileMap[][] getMapa() {
		return mapa;
	}
	
	public void setMapa(TileMap[][] mapa) {
		this.mapa = mapa;
	}
	
	public void setSize(Punto2D size) {
		this.size = size;
	}
}
