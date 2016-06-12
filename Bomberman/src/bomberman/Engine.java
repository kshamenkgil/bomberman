package bomberman;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

public class Engine {
	//public static int TILE_WIDTH = 16;
	//public static int TILE_HEIGHT = 16;
	public static int FPS = 60;
	public static int MS_PER_UPDATE = 16;
	private static Engine instancia;
	private ArrayList<Tile> tiles = new ArrayList<Tile>();	
	private GameScreen juego;
	private boolean startUpdate = false;
	//private boolean isRunning = false;
	private Hashtable<String, Textura> texturas = new Hashtable<String,Textura>();
	public static Engine getInstancia() {
		// TODO Auto-generated constructor stub
		if(instancia == null){
			instancia = new Engine();
		}
		return instancia;
	}
	
	public synchronized void setStartUpdate(boolean startUpdate) {
		this.startUpdate = startUpdate;
	}
	
	public void dispose()
	{
		//cerrar muchas cosas y esto		
		setStartUpdate(false);
	}
	
	public void inicializarVentana(){
		/*new Thread(new Runnable() {
			public void run() {
				juego = new GameScreen();
				setStartUpdate(true);
				juego.setVisible(true);				
			}
		}).start();*/
		
		addTexturas("ex", new Textura("assets/graficos/bomberman1/tiles/explotable.png"));
		

		//addTexturas("bl", new Textura("assets/graficos/bomberman1/tiles/bloqueado.png"));
		
		for (int i = 0; i < 475; i++) {
			Tile t = new Tile(true, true, new Sprite("ex", false));		
			t.getTileSprite().setLooping(false);
			tiles.add(t);
		}
		
		juego = new GameScreen();
		juego.setVisible(true);
		setStartUpdate(true);
		
	}
		
	public void cargarTexturas(String set){
		//personajes
		addTexturas("p1n", new Textura(set+"personaje/1/norte.png"));
		addTexturas("p1s", new Textura(set+"personaje/1/sur.png"));
		addTexturas("p1e", new Textura(set+"personaje/1/este.png"));
		addTexturas("p1o", new Textura(set+"personaje/1/oeste.png"));
		addTexturas("p1m", new Textura(set+"personaje/1/muerte.png"));
		
		addTexturas("p2n", new Textura(set+"personaje/2/norte.png"));
		addTexturas("p2s", new Textura(set+"personaje/2/sur.png"));
		addTexturas("p2e", new Textura(set+"personaje/2/este.png"));
		addTexturas("p2o", new Textura(set+"personaje/2/oeste.png"));
		addTexturas("p2m", new Textura(set+"personaje/2/muerte.png"));
		
		addTexturas("p3n", new Textura(set+"personaje/3/norte.png"));
		addTexturas("p3s", new Textura(set+"personaje/3/sur.png"));
		addTexturas("p3e", new Textura(set+"personaje/3/este.png"));
		addTexturas("p3o", new Textura(set+"personaje/3/oeste.png"));
		addTexturas("p3m", new Textura(set+"personaje/3/muerte.png"));
		
		addTexturas("p4n", new Textura(set+"personaje/4/norte.png"));
		addTexturas("p4s", new Textura(set+"personaje/4/sur.png"));
		addTexturas("p4e", new Textura(set+"personaje/4/este.png"));
		addTexturas("p4o", new Textura(set+"personaje/4/oeste.png"));
		addTexturas("p4m", new Textura(set+"personaje/4/muerte.png"));
				
		//tiles		
		addTexturas("bl", new Textura(set+"bloqueado.png"));
		addTexturas("va", new Textura(set+"vacio.png"));
		addTexturas("ex", new Textura(set+"explotable.png"));
		
		//enemigos
		addTexturas("e1n", new Textura(set+"enemigos/1/norte.png"));
		addTexturas("e1s", new Textura(set+"enemigos/1/sur.png"));
		addTexturas("e1e", new Textura(set+"enemigos/1/este.png"));
		addTexturas("e1o", new Textura(set+"enemigos/1/oeste.png"));
		addTexturas("e1m", new Textura(set+"enemigos/1/muerte.png"));
				
		//bomba
		
		//potenciadores
		addTexturas("potb", new Textura(set+"potenciadores/bomba.png"));
		addTexturas("potv", new Textura(set+"potenciadores/vida.png"));
		addTexturas("potc", new Textura(set+"potenciadores/correr.png"));
		addTexturas("potmb", new Textura(set+"potenciadores/masbomba.png"));
		
	}
	
	public boolean isStartUpdate() {
		return startUpdate;
	}
	
	public void addTexturas(String name, Textura textura) {
		if(texturas.get(name) == null)
			texturas.put(name, textura);
	}
	
	public Textura getTextura(String name){					
		return texturas.get(name);
	}
	
	public synchronized void dibujar(Graphics2D g, ImageObserver io){		
							
			int c = 0;
			int t = 0;
			for (Tile tile : tiles) {
				tile.dibujar(g, io, new Punto2D(c, t));
				c++;
				if(c == 25){
					c= 0;
					t++;
				}
			}
			//if(Mundo.getInstance().getJugador().personajeS != null)
			Mundo.getInstance().getJugador().personajeN.dibujar(g, io, new Punto2D(0, 0));
			Mundo.getInstance().getJugador().personajeS.dibujar(g, io, new Punto2D(1, 0));
			Mundo.getInstance().getJugador().personajeE.dibujar(g, io, new Punto2D(2, 0));
			Mundo.getInstance().getJugador().personajeO.dibujar(g, io, new Punto2D(3, 0));
			Mundo.getInstance().getJugador().personajeMuerte.dibujar(g, io, new Punto2D(4, 0));

	}
	
	public void update(){
		
			while(!Engine.getInstancia().isStartUpdate()){
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
			

			while(startUpdate)
			{		
				
								

			    //processInput();

			    //update
				
				//fin update
			    
				  
				 
				//repaint
				juego.repaint();
				
				/*try {
					Thread.sleep(elapsed);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/				
			}		
							
	}
		
}
