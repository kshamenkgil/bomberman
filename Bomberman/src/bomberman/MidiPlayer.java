package bomberman;

import java.io.*;
import java.util.Hashtable;

import javax.sound.midi.*;

public class MidiPlayer implements MetaEventListener {

    // Midi meta event
    public static final int END_OF_TRACK_MESSAGE = 47;
    
    //hash de musicas
    private Hashtable<String, Musica> musica;
    
    private static MidiPlayer instancia; 
    private Sequencer sequencer;
    private boolean loop;
    private boolean paused;

    /**
        Creates a new MidiPlayer object.
    */
    private MidiPlayer() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addMetaEventListener(this);
            musica = new Hashtable<String,Musica>();
        }
        catch ( MidiUnavailableException ex) {
            sequencer = null;
        }
    }
    
    public static MidiPlayer getInstancia() {
    	if(instancia == null)
    		instancia = new MidiPlayer();
    	
		return instancia;
	}    


    /**
        Plays a sequence, optionally looping. This method returns
        immediately. The sequence is not played if it is invalid.
    */
    public void play(String musica, boolean loop) {
    	Sequence sequence = this.musica.get(musica).getMusica();
        if (sequencer != null && sequence != null && sequencer.isOpen()) {
            try {
                sequencer.setSequence(sequence);
                sequencer.start();
                this.loop = loop;
            }
            catch (InvalidMidiDataException ex) {
                ex.printStackTrace();
            }
        }
    }

	public void agregarMusica(String nombre, Musica musica){
		this.musica.put(nombre, musica);
	}
    

    /**
        This method is called by the sound system when a meta
        event occurs. In this case, when the end-of-track meta
        event is received, the sequence is restarted if
        looping is on.
    */
    /*public void meta(MetaMessage event) {
        if (event.getType() == END_OF_TRACK_MESSAGE) {
            if (sequencer != null && sequencer.isOpen() && loop) {
                sequencer.start();
            }
        }
    }*/
    
    public void meta(MetaMessage event) {
    	  if (event.getType() == END_OF_TRACK_MESSAGE) {
    	   if (sequencer != null && sequencer.isOpen() && loop) {
    	       sequencer.setTickPosition(0);
    	       sequencer.start();
    	   }
    	  }
    	}
    
    /**
        Stops the sequencer and resets its position to 0.
    */
    public void stop() {
         if (sequencer != null && sequencer.isOpen()) {
             sequencer.stop();
             sequencer.setMicrosecondPosition(0);
         }
    }


    /**
        Closes the sequencer.
    */
    public void close() {
         if (sequencer != null && sequencer.isOpen()) {
             sequencer.close();
         }
    }


    /**
        Gets the sequencer.
    */
    public Sequencer getSequencer() {
        return sequencer;
    }


    /**
        Sets the paused state. Music may not immediately pause.
    */
    public void setPaused(boolean paused) {
        if (this.paused != paused && sequencer != null && sequencer.isOpen()) {
            this.paused = paused;
            if (paused) {
                sequencer.stop();
            }
            else {
                sequencer.start();
            }
        }
    }


    /**
        Returns the paused state.
    */
    public boolean isPaused() {
        return paused;
    }

}
