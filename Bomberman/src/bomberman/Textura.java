package bomberman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.*;
import java.awt.image.ImageFilter;
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
	
	public void dibujarTextura(Graphics2D g, ImageObserver io, Punto2D p){
		g.drawImage(this.imagen,(int)p.getX(),(int)p.getY(),io);		
	}
	
	public static Image makeColorTransparent(BufferedImage im, final Color color) {
	    ImageFilter filter = new RGBImageFilter() {

	        // the color we are looking for... Alpha bits are set to opaque
	        public int markerRGB = color.getRGB() | 0xFF000000;

	        public final int filterRGB(int x, int y, int rgb) {
	            if ((rgb | 0xFF000000) == markerRGB) {
	                // Mark the alpha bits as zero - transparent
	                return 0x00FFFFFF & rgb;
	            } else {
	                // nothing to do
	                return rgb;
	            }
	        }
	    };

	    ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
	    return Toolkit.getDefaultToolkit().createImage(ip);
	}
	
}
