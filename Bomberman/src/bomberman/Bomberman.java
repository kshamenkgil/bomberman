package bomberman;

import javax.swing.JOptionPane;

public class Bomberman {
	
	private Cliente cliente = null;
	private static Bomberman instancia = null;
	private boolean canClose = false;
	public static Bomberman getInstancia() {
		// TODO Auto-generated constructor stub
		if(instancia == null){
			instancia = new Bomberman();
			Configuracion.getInstancia().leerConfiguracion();
		}
		return instancia;
	}
	
	public void setCanClose(boolean canClose) {
		this.canClose = canClose;
	}
			
	public void dispose(){
		byte[] t = new byte[1];
		t[0] = Protocolo.DESCONEXION;
		Bomberman.getInstancia().getCliente().sendData(t);
		while(!Bomberman.getInstancia().canClose){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		Bomberman.getInstancia().getCliente().closeSocket();
		MidiPlayer.getInstancia().close();
		SonidoManager.getInstancia().dispose();
		Engine.getInstancia().dispose();
		System.exit(0);
	}
	
	private void cargarMusica(){
		MidiPlayer.getInstancia().agregarMusica("m1", new Musica("assets/musica/bombstg1.mid"));
	}
	
	private void cargarSonidos(){
		SonidoManager.getInstancia().agregarSonido("exp", new Sonido("assets/sonidos/explosion.wav"));
		SonidoManager.getInstancia().agregarSonido("pasos", new Sonido("assets/sonidos/pasos.wav"));
	}
	
	public void conectar(){
		
		Engine.getInstancia().cargarTexturas("bomberman1/");
				
		cargarSonidos();
		
		cargarMusica();
		
		try{
			cliente = new Cliente(Configuracion.getInstancia().getIp(), Configuracion.getInstancia().getPuerto());
			cliente.recieveData();
		}catch(Exception e){			
			JOptionPane.showMessageDialog(null, "No se pudo conectar con el servidor");			
		}
	}
	
	public void run() {		
							
		Engine.getInstancia().inicializarVentana();			
		
		Engine.getInstancia().update();
		
	}
	
	public Cliente getCliente() {
		return cliente;
	}
}
