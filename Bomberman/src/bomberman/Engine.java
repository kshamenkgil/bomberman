package bomberman;
import java.awt.Color;
import java.awt.Font;
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
	private InputHandler input = new InputHandler();
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
		juego.dispose();
	}
	
	public void inicializarVentana(){
		
		addTexturas("ex", new Textura("assets/graficos/bomberman1/tiles/explotable.png"));

		//addTexturas("bl", new Textura("assets/graficos/bomberman1/tiles/bloqueado.png"));
		
		for (int i = 0; i < 475; i++) {
			Tile t = new Tile(true, true, new Sprite("ex", false));		
			t.getTileSprite().setLooping(false);
			tiles.add(t);
		}
		
		
		
		juego = new GameScreen();
		juego.setVisible(true);
		//setStartUpdate(true);
		
	}
		
	public void cargarTexturas(String set){
		//personajes
		
		addTexturas("p1n", new Textura("assets/graficos/"+set+"personaje/1/norte.png"));
		addTexturas("p1s", new Textura("assets/graficos/"+set+"personaje/1/sur.png"));
		addTexturas("p1e", new Textura("assets/graficos/"+set+"personaje/1/este.png"));
		addTexturas("p1o", new Textura("assets/graficos/"+set+"personaje/1/oeste.png"));
		addTexturas("p1m", new Textura("assets/graficos/"+set+"personaje/1/muerte.png"));
		
		addTexturas("p2n", new Textura("assets/graficos/"+set+"personaje/2/norte.png"));
		addTexturas("p2s", new Textura("assets/graficos/"+set+"personaje/2/sur.png"));
		addTexturas("p2e", new Textura("assets/graficos/"+set+"personaje/2/este.png"));
		addTexturas("p2o", new Textura("assets/graficos/"+set+"personaje/2/oeste.png"));
		addTexturas("p2m", new Textura("assets/graficos/"+set+"personaje/2/muerte.png"));
		
		addTexturas("p3n", new Textura("assets/graficos/"+set+"personaje/3/norte.png"));
		addTexturas("p3s", new Textura("assets/graficos/"+set+"personaje/3/sur.png"));
		addTexturas("p3e", new Textura("assets/graficos/"+set+"personaje/3/este.png"));
		addTexturas("p3o", new Textura("assets/graficos/"+set+"personaje/3/oeste.png"));
		addTexturas("p3m", new Textura("assets/graficos/"+set+"personaje/3/muerte.png"));
		
		addTexturas("p4n", new Textura("assets/graficos/"+set+"personaje/4/norte.png"));
		addTexturas("p4s", new Textura("assets/graficos/"+set+"personaje/4/sur.png"));
		addTexturas("p4e", new Textura("assets/graficos/"+set+"personaje/4/este.png"));
		addTexturas("p4o", new Textura("assets/graficos/"+set+"personaje/4/oeste.png"));
		addTexturas("p4m", new Textura("assets/graficos/"+set+"personaje/4/muerte.png"));
				
		//tiles		
		addTexturas("bl", new Textura("assets/graficos/"+set+"tiles/bloqueado.png"));
		//addTexturas("va", new Textura("assets/graficos/"+set+"tiles/vacio.png"));
		addTexturas("ex", new Textura("assets/graficos/"+set+"tiles/explotable.png"));
		
		//enemigos
		/*addTexturas("e1n", new Textura(set+"enemigos/1/norte.png"));
		addTexturas("e1s", new Textura(set+"enemigos/1/sur.png"));
		addTexturas("e1e", new Textura(set+"enemigos/1/este.png"));
		addTexturas("e1o", new Textura(set+"enemigos/1/oeste.png"));
		addTexturas("e1m", new Textura(set+"enemigos/1/muerte.png"));*/
				
		//bomba
		
		//potenciadores
		addTexturas("potb", new Textura("assets/graficos/"+set+"potenciadores/bomba.png"));
		addTexturas("potv", new Textura("assets/graficos/"+set+"potenciadores/vida.png"));
		addTexturas("potc", new Textura("assets/graficos/"+set+"potenciadores/correr.png"));
		//addTexturas("potmb", new Textura("assets/graficos/"+set+"potenciadores/masbomba.png"));
		
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
	
	public void dibujarTexto(String texto, int size, Graphics2D g, Punto2D pos){
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 0, size));
		g.drawString(texto, (int)pos.getX(), (int)pos.getY());
		//g.setColor(Color.);
	}
	
	public synchronized void dibujar(Graphics2D g, ImageObserver io){		
			
		if(this.isStartUpdate()){
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
			Mundo.getInstance().getJugador().dibujar(g, io);
			
		}else{
			dibujarTexto("Esperando por los otros jugadores",20, g, new Punto2D(250, 300));
		}

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
			
			long lastTime = Calendar.getInstance().getTimeInMillis();
			long lag = 0;
			while(startUpdate)
			{				
				long current = Calendar.getInstance().getTimeInMillis();
				long elapsed = current - lastTime;
				if(elapsed < 0 || elapsed > Engine.MS_PER_UPDATE)
					elapsed = 0;
				
				try {
					Thread.sleep(Engine.MS_PER_UPDATE - elapsed);	
				} catch (Exception e) {
					e.printStackTrace();
				}
								
				//procesar input
				input.update();			    

			    //update
				
				//fin update
			    
				 
				//repaint
				juego.repaint();
				
				lastTime = current;	
			}	
			Bomberman.getInstancia().dispose();
							
	}

	public synchronized InputHandler getInput() {
		return input;
	}	
}
