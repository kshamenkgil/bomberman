package bomberman;

import java.io.File;

import javax.sound.sampled.AudioInputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class Sonido { 
	private Clip clip; 	
	private String path; 
	public Sonido(String path) {
		this.path = path; 
	}	
	
	public void playSound(){
		/*if(this.loop)
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		else*/					
		//if(clip.getFramePosition() == clip.getFrameLength()-1 || clip.getFramePosition() == 0){
			try {

				AudioInputStream sound = AudioSystem.getAudioInputStream(new File(path));
				DataLine.Info info = new DataLine.Info(Clip.class,
			            sound.getFormat());			
			    clip = (Clip)AudioSystem.getLine(info);
			    clip.addLineListener(new LineListener() {						    
			    	
					@Override
					public void update(LineEvent event) {
						// TODO Auto-generated method stub
						if(event.getType() == LineEvent.Type.STOP){
							clip.setFramePosition(0);
							//SonidoManager.getInstancia().setcSonidos(SonidoManager.getInstancia().getcSonidos()-1);
						}
					}
				});
				clip.open(sound);       
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//clip.setFramePosition(0);
			clip.start();
	//	}
	}
	
	public void pauseSound(){
		clip.stop();
	}
	
	public void stopSound(){
		try {
			clip.stop();	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
		
	public Clip getClip() {
		return clip;
	}
	
}
