package bomberman;

public class Tile {
	private Sprite tileSprite; 
	private double width, height; // 32x32, 64x64 , etc	
	private boolean seRompe;
	private boolean colisionable;
	
	
	public Tile(boolean seRompe, boolean colisionable, Sprite tileSprite) {
		this.seRompe = seRompe;
		this.colisionable = colisionable;
		this.tileSprite = tileSprite;		
	}

	public void dibujar(){
		
	}		
}
