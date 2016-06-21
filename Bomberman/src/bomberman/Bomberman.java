package bomberman;


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
	}
	
	private void cargarMusica(){
		MidiPlayer.getInstancia().agregarMusica("m1", new Musica("assets/musica/bombstg1.mid"));
	}
	
	private void cargarSonidos(){
		SonidoManager.getInstancia().agregarSonido("exp", new Sonido("assets/sonidos/explosion.wav"));
		SonidoManager.getInstancia().agregarSonido("pasos", new Sonido("assets/sonidos/pasos.wav"));
	}
	
	public void run() {
		cliente = new Cliente(Configuracion.getInstancia().getIp(), Configuracion.getInstancia().getPuerto());
		//cambiar por "p"+(id+1)+"s", etc
		
		Engine.getInstancia().addTexturas("p1n", new Textura("assets/graficos/bomberman1/personaje/1/norte.png"));
		Engine.getInstancia().addTexturas("p1s", new Textura("assets/graficos/bomberman1/personaje/1/sur.png"));
		Engine.getInstancia().addTexturas("p1e", new Textura("assets/graficos/bomberman1/personaje/1/este.png"));
		Engine.getInstancia().addTexturas("p1o", new Textura("assets/graficos/bomberman1/personaje/1/oeste.png"));
		Engine.getInstancia().addTexturas("p1m", new Textura("assets/graficos/bomberman1/personaje/1/muerte.png"));
				
		cargarSonidos();
		cargarMusica();			
		
		Jugador j = new Jugador(new Punto2D(0,0));
		j.setSprites(new Sprite("p1n", true),new Sprite("p1s", true),new Sprite("p1e", true),new Sprite("p1o", true),new Sprite("p1m", true));
		Mundo.getInstance().setJugador(j);
		
		cliente.recieveData();
		Engine.getInstancia().inicializarVentana();
		
		//reproducir musica
		MidiPlayer.getInstancia().play("m1", true);
		
		Engine.getInstancia().update();
		
	}
	
	public Cliente getCliente() {
		return cliente;
	}
}
