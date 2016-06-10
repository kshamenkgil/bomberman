package bomberman;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Textura {
	private BufferedImage imagen;
	private double height;
	private double width;
	
	public Textura(String path) {
		try {
			this.imagen = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.width = this.imagen.getWidth();
		this.height = this.imagen.getHeight();	
		
	}

	public BufferedImage getImagen() {
		return imagen;
	}

	public double getHeight() {
		return height;
	}	
	
	public double getWidth() {
		return width;
	}
	
}
