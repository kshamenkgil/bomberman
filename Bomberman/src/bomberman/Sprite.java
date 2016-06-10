package bomberman;

public class Sprite {
	private String textura;
	private boolean looping;
	public Sprite(String textura, boolean loop) {
		this.looping = loop;
		this.textura = textura;
	}
			
	public void dibujar(){
		
	}
	
	public void setLooping(boolean looping) {
		this.looping = looping;
	}
	
	public boolean isLooping() {
		return looping;
	}
	
}
