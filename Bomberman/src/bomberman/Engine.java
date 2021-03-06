package bomberman;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

import java.util.Timer;
import java.util.TimerTask;

public class Engine {
	public static int TILE_WIDTH = 32;
	public static int TILE_HEIGHT = 32;	
	public static int FPS = 60;
	public static long MS_PER_UPDATE = 16;
	
	private float offsetMaxX = 0;
	private float offsetMaxY = 0;
	private float offsetMinX = 0;
	private float offsetMinY = 0;
	
	private ArrayList<String> msg = new ArrayList<String>();
	private int t = 10;
	
	private float camX = 0;
	private float camY = 0;
	
	private static Engine instancia;
	private InputHandler input = new InputHandler();
	
	private pantallaIngreso pantalla;
	private GameScreen juego;
	private boolean startUpdate = false;
	private int fpsCounter = 0;
	private int fps;
	private long fpsTime = 0;
	
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
		
		//addTexturas("ex", new Textura("assets/graficos/bomberman1/tiles/explotable.png"));

		//addTexturas("bl", new Textura("assets/graficos/bomberman1/tiles/bloqueado.png"));
		
		/*for (int i = 0; i < 475; i++) {
			Tile t = new Tile(true, true, new Sprite("ex", false));		
			t.getTileSprite().setLooping(false);
			tiles.add(t);
		}*/
		juego = new GameScreen();
		juego.setVisible(true);
		

		//setStartUpdate(true);
		
	}
	
	
		
	public GameScreen getJuego() {
		return juego;
	}

	public void setJuego(GameScreen juego) {
		this.juego = juego;
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
		addTexturas("bomba", new Textura("assets/graficos/"+set+"Bombas/BOMBA.png"));
		addTexturas("eabajo", new Textura("assets/graficos/"+set+"Bombas/EXPLOSION_ABAJO.png"));
		addTexturas("earriba", new Textura("assets/graficos/"+set+"Bombas/EXPLOSION_ARRIBA.png"));
		addTexturas("eder", new Textura("assets/graficos/"+set+"Bombas/EXPLOSION_DER.png"));
		addTexturas("ehoriz", new Textura("assets/graficos/"+set+"Bombas/EXPLOSION_HORIZ.png"));
		addTexturas("eizq", new Textura("assets/graficos/"+set+"Bombas/EXPLOSION_IZQ.png"));
		addTexturas("emedio", new Textura("assets/graficos/"+set+"Bombas/EXPLOSION_MEDIO.png"));
		addTexturas("evertic", new Textura("assets/graficos/"+set+"Bombas/EXPLOSION_VERTIC.png"));
		
		
		//potenciadores
		addTexturas("potb", new Textura("assets/graficos/"+set+"potenciadores/bomba.png"));
		addTexturas("potmb", new Textura("assets/graficos/"+set+"potenciadores/masbomba.png"));
		addTexturas("potv", new Textura("assets/graficos/"+set+"potenciadores/vida.png"));
		addTexturas("potc", new Textura("assets/graficos/"+set+"potenciadores/correr.png"));
		
		//Game over
		addTexturas("gameover", new Textura("assets/graficos/GAME OVER.png"));
		addTexturas("ganador", new Textura("assets/graficos/GANADOR.png"));
		
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
	
	public void dibujarTextoConSombra(String texto, int size, Graphics2D g, Punto2D pos){
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, size));
		g.drawString(texto, (int)pos.getX()-1, (int)pos.getY()-1);
		g.setColor(Color.white);		
		g.drawString(texto, (int)pos.getX(), (int)pos.getY());		
		//g.setColor(Color.);
	}
	
	public void dibujarTexto(String texto, int size, Graphics2D g, Punto2D pos){
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, size));
		g.drawString(texto, (int)pos.getX(), (int)pos.getY());
		//g.setColor(Color.);
	}

	public void dibujarTextoConSombra(String texto, int size, Color color ,Graphics2D g, Punto2D pos){
				
		g.setFont(new Font("Arial", Font.BOLD, size));
		g.setColor(Color.BLACK);
		g.drawString(texto, (int)pos.getX()-1, (int)pos.getY()-1);
		g.setColor(color);
		g.drawString(texto, (int)pos.getX(), (int)pos.getY());		
		//g.setColor(Color.);
	}
	
	public void dibujarTexto(String texto, int size, Color color ,Graphics2D g, Punto2D pos){
		g.setColor(color);
		g.setFont(new Font("Arial", Font.BOLD, size));
		g.drawString(texto, (int)pos.getX(), (int)pos.getY());
		//g.setColor(Color.);
	}
	
	private void mostrarMensaje(Graphics2D g){
		int c = 0;
		String t = null;		
		if(!Mundo.getInstance().getMensajes().isEmpty()){			
			msg.add(Mundo.getInstance().getMensajes().remove());
			Timer ti = new Timer();
			ti.schedule(new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if(!msg.isEmpty())
						msg.remove(0);
				}
			},6000);
		}
				
		for (String string : msg) {
			dibujarTextoConSombra(string, 40, g, new Punto2D(150*camX,100-(c*40)*camY));
			c++;
			if(c == 4)
				t = string;
		}		
		
		if(t != null)
			msg.remove(t);		
		
	}
	
	public synchronized void dibujar(Graphics2D g, ImageObserver io){
		//mapa
		if(this.isStartUpdate()){
			if(!Mundo.getInstance().isFinJuego()){
				g.translate(-camX, -camY);			
				for(int x = 0 ; x < Mundo.getInstance().getMap().getSize().getX(); x++){
					for(int y = 0 ; y < Mundo.getInstance().getMap().getSize().getY(); y++){
						Mundo.getInstance().getMap().getMapa()[x][y].getTile().dibujar(g, io, new Punto2D(x, y));
						if(Mundo.getInstance().getMap().getMapa()[x][y].getObjeto() != null)
							Mundo.getInstance().getMap().getMapa()[x][y].getObjeto().dibujar(g, io, new Punto2D(x, y));
					}
				}
				
				if(!Mundo.getInstance().getBombas().isEmpty()){
					for (Bomba bomba : Mundo.getInstance().getBombas()) {
						if(bomba != null)
							bomba.dibujarBomba(g,io);
					}
				}
				
				//jugadores
				Mundo.getInstance().getJugador().dibujar(g, io);
				for (Jugador j : Mundo.getInstance().getJugadores()) {
					j.dibujar(g, io);
				}
				
				//fps
				//g.translate(0, 0);
				//dibujarTexto("FPS: " + fps, 16, g, new Punto2D(5+camX, 15+camY));
				
				if(Mundo.getInstance().getJugador().isMuerto()){				
					getTextura("gameover").dibujarTextura(g, io, new Punto2D(camX, camY));
				}
				
				mostrarMensaje(g);
			}else{				
				if(Mundo.getInstance().getJugador().isGanador()){
					getTextura("ganador").dibujarTextura(g, io, new Punto2D(0, 0));
					dibujarTextoConSombra("El juego finaliza en: "+ t , 40, Color.RED, g, new Punto2D(200, 250));
				}else{
					getTextura("gameover").dibujarTextura(g, io, new Punto2D(0, 0));
					if(Mundo.getInstance().getGanador() != null)
						dibujarTextoConSombra("El ganador fue: "+ Mundo.getInstance().getGanador().getNombre() , 40, Color.RED, g, new Punto2D(200, 200));
					else
						dibujarTextoConSombra("Empate", 40, Color.RED, g, new Punto2D(200, 200));
					dibujarTextoConSombra("El juego finaliza en: "+ t , 40, Color.RED, g, new Punto2D(200, 250));
				}
				
				try {
					t--;
					Thread.sleep(1000);					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(t< 0){
					Bomberman.getInstancia().dispose();
				}
			}
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
		
		
		/*new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				fps = fpsCounter;
				//fpsTime = 0;
				fpsCounter = 0;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();*/
		
		
		
		offsetMaxX = (float) (Mundo.getInstance().getMap().getSize().x*TILE_WIDTH - Configuracion.getInstancia().getScreenX());
		offsetMaxY = (float) (Mundo.getInstance().getMap().getSize().y*TILE_HEIGHT - Configuracion.getInstancia().getScreenY());
		
		//reproducir musica
		MidiPlayer.getInstancia().play("m1", true);
		
		long lastTime = Calendar.getInstance().getTimeInMillis();
		//long lag = 0;
		while(startUpdate)
		{
			long current = Calendar.getInstance().getTimeInMillis();
			long elapsed = current - lastTime;
			
			fpsTime += elapsed;
			
			
			if(elapsed < 0 )
				elapsed = 0;
			
			if(elapsed > Engine.MS_PER_UPDATE)
				elapsed = Engine.MS_PER_UPDATE;
			
			try {
				Thread.sleep(Engine.MS_PER_UPDATE - elapsed);	
			} catch (Exception e) {
				e.printStackTrace();
			}
						
			//procesar input
			input.update();			    

		    //update
			
			camX = (float)Mundo.getInstance().getJugador().getPosicion().getX() - Configuracion.getInstancia().getScreenX() / 2;
			camY = (float)Mundo.getInstance().getJugador().getPosicion().getY() - Configuracion.getInstancia().getScreenY() / 2;	
						
			if(camX > offsetMaxX)
			    camX = offsetMaxX;
			else if(camX < offsetMinX)
			    camX = offsetMinX;
			
			if(camY > offsetMaxY)
			    camY = offsetMaxY;
			else if(camY < offsetMinY)
			    camY = offsetMinY;

			Bomba b = null;
			for (Bomba bomba : Mundo.getInstance().getBombas()) {				
				if(bomba.isTerminoExplosion())					
					b = bomba;
			}			
			
			Mundo.getInstance().getBombas().remove(b);
			
			//fin update
		    
			
			//repaint
			juego.repaint();
			
			lastTime = current;
			
						
			fpsCounter++;
		}	 
		
		Bomberman.getInstancia().dispose();
							
	}

	public synchronized InputHandler getInput() {
		return input;
	}	
}
