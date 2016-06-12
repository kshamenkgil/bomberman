package bomberman;


public class Bomberman {
	
	private Cliente cliente = null;
	
	public Bomberman() {
		Configuracion.getInstancia().leerConfiguracion();		
	}
	
	public void run() {
		cliente = new Cliente(Configuracion.getInstancia().getIp(), Configuracion.getInstancia().getPuerto());
		//cambiar por "p"+(id+1)+"s", etc
		
		Engine.getInstancia().addTexturas("p1n", new Textura("assets/graficos/bomberman1/personaje/norte.png"));
		Engine.getInstancia().addTexturas("p1s", new Textura("assets/graficos/bomberman1/personaje/sur.png"));
		Engine.getInstancia().addTexturas("p1e", new Textura("assets/graficos/bomberman1/personaje/este.png"));
		Engine.getInstancia().addTexturas("p1o", new Textura("assets/graficos/bomberman1/personaje/oeste.png"));
		Engine.getInstancia().addTexturas("p1m", new Textura("assets/graficos/bomberman1/personaje/muerte.png"));
		
		Jugador j = new Jugador();
		j.setSprites(new Sprite("p1n", true),new Sprite("p1s", true),new Sprite("p1e", true),new Sprite("p1o", true),new Sprite("p1m", true));
		Mundo.getInstance().setJugador(j);
				
		cliente.recieveData();
		Engine.getInstancia().inicializarVentana();
		Engine.getInstancia().update();
		
	}
}
