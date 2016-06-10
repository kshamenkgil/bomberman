package bomberman;

public class Sprite {
	private String textura;
	private boolean looping;
	private int cantImg;
	private int actualImg;
	private long tiempo;
	private int tileWidth;
	private int tileHeight;
	public Sprite(String textura, boolean loop) {
		this.looping = loop;
		this.textura = textura;
		this.tileWidth = Engine.getInstancia().getTextura(textura).getImagen().getHeight();
		this.tileWidth = this.tileHeight;
		this.cantImg = Engine.getInstancia().getTextura(textura).getImagen().getWidth() / this.tileWidth;
		this.actualImg = 0;
		this.tiempo = this.cantImg * 1000 / Engine.FPS;
	}
			
	public void dibujar(){
		if(this.looping){
			
			try {
				Thread.sleep(this.tiempo);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//dibujar
			Engine.getInstancia().getTextura(textura).getImagen().getSubimage(this.tileWidth*actualImg, 0, this.tileWidth, this.tileHeight);
			if(actualImg < cantImg-1)
				actualImg++;
			else
				actualImg = 0;
		}
		
		//dibujar esto
		Engine.getInstancia().getTextura(textura).getImagen().getSubimage(this.tileWidth*actualImg, 0, this.tileWidth, this.tileHeight);
	}
	
	public void setLooping(boolean looping) {
		this.looping = looping;
	}
	
	public boolean isLooping() {
		return looping;
	}
	
}
