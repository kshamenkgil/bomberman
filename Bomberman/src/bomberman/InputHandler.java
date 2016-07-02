package bomberman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;

import javax.swing.Timer;

public class InputHandler implements KeyListener {
	
	private Hashtable<Integer, Boolean> keys = new Hashtable<Integer,Boolean>();
	
	private int stepCounter = 0;
	
	private static int C_STEPS = 80;
	
	public int getStepCounter() {
		return stepCounter;
	}
	
	public synchronized void setStepCounter(int stepCounter) {
		this.stepCounter = stepCounter;
	}
	
	public InputHandler() {
		keys.put(KeyEvent.VK_UP,false);
		keys.put(KeyEvent.VK_DOWN,false);
		keys.put(KeyEvent.VK_RIGHT,false);
		keys.put(KeyEvent.VK_LEFT,false);
		keys.put(KeyEvent.VK_ESCAPE,false);
		keys.put(KeyEvent.VK_SPACE,false);
	}
		
	public void update(){
		if(!Mundo.getInstance().getJugador().isMuerto() || !Mundo.getInstance().isFinJuego()){
			if(keys.get(KeyEvent.VK_UP)){			
				/*Mundo.getInstance().getJugador().mover(Protocolo.NORTE);	        
		        Mundo.getInstance().getJugador().playAnimation();*/
		        Protocolo.moverJugador(Protocolo.NORTE);
		        stepCounter++;
		        if(stepCounter >= C_STEPS){
		        	//SonidoManager.getInstancia().playSound("pasos");
		        	setStepCounter(0);
		        }
		    }
		    
		    if(keys.get(KeyEvent.VK_DOWN)){
		        /*Mundo.getInstance().getJugador().mover(Protocolo.SUR);	        
		        Mundo.getInstance().getJugador().playAnimation();*/
		        Protocolo.moverJugador(Protocolo.SUR);
		        stepCounter++;
		        if(stepCounter >= C_STEPS){
		        	//SonidoManager.getInstancia().playSound("pasos");
		        	setStepCounter(0);
		        }
		    }
		    
		    if(keys.get(KeyEvent.VK_RIGHT)){
		        /*Mundo.getInstance().getJugador().mover(Protocolo.ESTE);	        
		        Mundo.getInstance().getJugador().playAnimation();*/
		        Protocolo.moverJugador(Protocolo.ESTE);
		        stepCounter++;
		        if(stepCounter >= C_STEPS){
		        	//SonidoManager.getInstancia().playSound("pasos");
		        	setStepCounter(0);
		        }
		    }
		    
		    if(keys.get(KeyEvent.VK_LEFT)){
		       /* Mundo.getInstance().getJugador().mover(Protocolo.OESTE);	        
		        Mundo.getInstance().getJugador().playAnimation();*/
		        Protocolo.moverJugador(Protocolo.OESTE);
		        stepCounter++;
		        if(stepCounter >= C_STEPS){
		        	//SonidoManager.getInstancia().playSound("pasos");
		        	setStepCounter(0);
		        }
		    }
		    
		    if(keys.get(KeyEvent.VK_SPACE)){	        
		        Mundo.getInstance().getJugador().atacar();
		    }
		}
		
	    if(keys.get(KeyEvent.VK_ESCAPE)){
	    	Engine.getInstancia().setStartUpdate(false);
	    }
	}
	
	@Override
	public void keyPressed(KeyEvent e) { 
	    keys.put(e.getKeyCode(),true);	    
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//Mundo.getInstance().getJugador().stopAnimating();
		Protocolo.moverJugador(Protocolo.IDLE);
		keys.put(e.getKeyCode(),false);		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
