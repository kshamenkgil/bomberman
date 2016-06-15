package bomberman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;

import javax.swing.Timer;

public class InputHandler implements KeyListener {
	
	private Hashtable<Integer, Boolean> keys = new Hashtable<Integer,Boolean>();
	
	public InputHandler() {
		keys.put(KeyEvent.VK_UP,false);
		keys.put(KeyEvent.VK_DOWN,false);
		keys.put(KeyEvent.VK_RIGHT,false);
		keys.put(KeyEvent.VK_LEFT,false);
		keys.put(KeyEvent.VK_ESCAPE,false);
        
		new Timer(1, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
        		if(keys.get(KeyEvent.VK_UP)){	    
        			Mundo.getInstance().getJugador().mover(Protocolo.NORTE);
        	        Mundo.getInstance().getJugador().direccion = Protocolo.NORTE;
        	        Mundo.getInstance().getJugador().playAnimation();
        	        Protocolo.moverJugador(Protocolo.NORTE);
        	    }
        	    
        	    if(keys.get(KeyEvent.VK_DOWN)){
        	        Mundo.getInstance().getJugador().mover(Protocolo.SUR);
        	        Mundo.getInstance().getJugador().direccion = Protocolo.SUR;
        	        Mundo.getInstance().getJugador().playAnimation();
        	        Protocolo.moverJugador(Protocolo.SUR);
        	    }
        	    
        	    if(keys.get(KeyEvent.VK_RIGHT)){
        	        Mundo.getInstance().getJugador().mover(Protocolo.ESTE);
        	        Mundo.getInstance().getJugador().direccion = Protocolo.ESTE;
        	        Mundo.getInstance().getJugador().playAnimation();
        	        Protocolo.moverJugador(Protocolo.ESTE);
        	        
        	    }
        	    
        	    if(keys.get(KeyEvent.VK_LEFT)){
        	        Mundo.getInstance().getJugador().mover(Protocolo.OESTE);
        	        Mundo.getInstance().getJugador().direccion = Protocolo.OESTE;
        	        Mundo.getInstance().getJugador().playAnimation();
        	        Protocolo.moverJugador(Protocolo.OESTE);
        	    }
        	    
        	    if(keys.get(KeyEvent.VK_ESCAPE)){
        	    	Engine.getInstancia().setStartUpdate(false);
        	    }
        	    
            }
        }).start();
        
	}
	
	public void update(){
		

	}
	
	@Override
	public void keyPressed(KeyEvent e) { 
	    keys.put(e.getKeyCode(),true);	    
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Mundo.getInstance().getJugador().stopAnimating();
		keys.put(e.getKeyCode(),false);		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}