package bomberman;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;

public class Musica {
	//private boolean loop; 
	private Sequence musica;	
	public Musica(String path) {
		try {						
			musica = getSequence(new FileInputStream(path));
			            			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public Sequence getMusica() {
		return musica;
	}
	
    /**
	    Loads a sequence from an input stream. Returns null if
	    an error occurs.
	*/
	private Sequence getSequence(InputStream is) {
	    try {
	        if (!is.markSupported()) {
	            is = new BufferedInputStream(is);
	        }
	        Sequence s = MidiSystem.getSequence(is);
	        is.close();
	        return s;
	    }
	    catch (InvalidMidiDataException ex) {
	        ex.printStackTrace();
	        return null;
	    }
	    catch (IOException ex) {
	        ex.printStackTrace();
	        return null;
	    }
	}
}
