package servidor;


import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bomberman.BombaMasPotente;
import bomberman.CorrerMasRapido;
import bomberman.Engine;
import bomberman.MapSerializer;
import bomberman.Mapa;
import bomberman.MasDeUnaBomba;
import bomberman.Potenciador;
import bomberman.PotenciadorSerializer;
import bomberman.Punto2D;
import bomberman.Punto2DSerializer;
import bomberman.Sprite;
import bomberman.Tile;
import bomberman.TileMap;
import bomberman.TileMapSerializer;
import bomberman.TileSerializer;
import bomberman.VidaExtra;

public class MapAutoGeneration {
	
	private TileMap tileMap[][];	
	private Punto2D size;
	private Mapa map;
	
	
	/*private ArrayList<Tile> explotables = new ArrayList<Tile>();
	private ArrayList<Tile> rompibles = new ArrayList<Tile>();*/
	public MapAutoGeneration(Punto2D size, double porcPot){//, double porcBloq) {
		long seed = Calendar.getInstance().getTimeInMillis();
		Random rnd = new Random();
		rnd.setSeed(seed);
		//int potCounter = 0;
		this.size = size; 
		tileMap = new TileMap[(int)size.getX()][(int)size.getY()];
		for(int x = 0 ; x < size.getX(); x++){
			for(int y = 0 ; y < size.getY(); y++){
				int op = rnd.nextInt(3);
				//int hasPotenciador = rnd.nextInt(2);
				boolean hasPotenciador = rnd.nextDouble() < porcPot;
				//boolean esNoRompible = rnd.nextDouble() < porcBloq;
				boolean seRompe = rnd.nextDouble() < 0.5;
				TileMap t = null;
				//if(potCounter < nPotenciadores)
				if(seRompe){
					if(hasPotenciador == true){
						switch (op) {
							case 0:
								t = new TileMap(new Tile(true, true, new Sprite("ex", false, true),new Punto2D(x*Engine.TILE_WIDTH, y*Engine.TILE_HEIGHT)), new BombaMasPotente("potb"));
								break;				
							case 1:
								t = new TileMap(new Tile(true, true, new Sprite("ex", false, true),new Punto2D(x*Engine.TILE_WIDTH, y*Engine.TILE_HEIGHT)), new CorrerMasRapido("potc"));
								break;
							case 2:
								t = new TileMap(new Tile(true, true, new Sprite("ex", false, true),new Punto2D(x*Engine.TILE_WIDTH, y*Engine.TILE_HEIGHT)), new MasDeUnaBomba("potmb"));
								break;
							/*case 3:
								t = new TileMap(new Tile(true, true, new Sprite("ex", false, true),new Punto2D(x*Engine.TILE_WIDTH, y*Engine.TILE_HEIGHT)), new VidaExtra("potv"));
								break;*/
						}
					}else{
							/*if(esNoRompible == true)
								t = new TileMap(new Tile(false, true, new Sprite("bl", false, true),new Punto2D(x*Engine.TILE_WIDTH, y*Engine.TILE_HEIGHT)), null);
							else*/
								t = new TileMap(new Tile(true, true, new Sprite("ex", false, true),new Punto2D(x*Engine.TILE_WIDTH, y*Engine.TILE_HEIGHT)), null);
					}
					//potCounter++;
				}else{
					/*if(hasPotenciador == 0)
						t = new TileMap(new Tile(true, true, new Sprite("ex", false, true),new Punto2D(x*Engine.TILE_WIDTH, y*Engine.TILE_HEIGHT)), null);*/
					//else
						t = new TileMap(new Tile(false, false, null,new Punto2D(x*Engine.TILE_WIDTH, y*Engine.TILE_HEIGHT)), null);
				}
				
												
				if(x <= 4 && x >= 1 && y >= 1 && y <= 4 && x != 0 && x != size.getX()-1 && y != 0 && y != size.getY()-1){
					tileMap[x][y] = new TileMap(new Tile(false, false, null,new Punto2D(x*Engine.TILE_WIDTH, y*Engine.TILE_HEIGHT)), null);							
				}else if(x >= (size.getX()-2)-4 && x <= size.getX()-2 && y >= 1 && y <= 4 && x != 0 && x != size.getX()-1 && y != 0 && y != size.getY()-1){
					tileMap[x][y] = new TileMap(new Tile(false, false, null,new Punto2D(x*Engine.TILE_WIDTH, y*Engine.TILE_HEIGHT)), null);				
				}else if(x <= 4 && x >= 1 && y >= (size.getY()-2)-4 && y <= (size.getY()-2) && x != 0 && x != size.getX()-1 && y != 0 && y != size.getY()-1){
					tileMap[x][y] = new TileMap(new Tile(false, false, null,new Punto2D(x*Engine.TILE_WIDTH, y*Engine.TILE_HEIGHT)), null);
				}else if(x >= (size.getX()-2)-4 && x <= size.getX()-2 && y >= (size.getY()-2)-4 && y <= (size.getY()-2) && x != 0 && x != size.getX()-1 && y != 0 && y != size.getY()-1){
					tileMap[x][y] = new TileMap(new Tile(false, false, null,new Punto2D(x*Engine.TILE_WIDTH, y*Engine.TILE_HEIGHT)), null);							
				}else if(x == 0 || x == size.getX()-1){
					tileMap[x][y] = new TileMap(new Tile(false, true, new Sprite("bl", false, true),new Punto2D(x*Engine.TILE_WIDTH, y*Engine.TILE_HEIGHT)), null);
				}else if(y == 0 || y == size.getY()-1){
					tileMap[x][y] = new TileMap(new Tile(false, true, new Sprite("bl", false, true),new Punto2D(x*Engine.TILE_WIDTH, y*Engine.TILE_HEIGHT)), null);
				}else{
					tileMap[x][y] = t;
				}
			}
		}
		map = new Mapa(tileMap, size);		
	}
	
	public void saveMap(){
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(bomberman.Punto2D.class, new bomberman.Punto2DSerializer())				
				.registerTypeAdapter(bomberman.Potenciador.class, new bomberman.PotenciadorSerializer())
				.registerTypeAdapter(bomberman.Tile.class, new bomberman.TileSerializer())
				.registerTypeAdapter(bomberman.TileMap.class, new bomberman.TileMapSerializer())
				.registerTypeAdapter(bomberman.Mapa.class, new bomberman.MapSerializer())
				.create();
		
		File f = null;
		FileWriter fw = null;
		PrintWriter pw = null;
		
		try {
			String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			f = new File("server/maps/map"+date);
			fw = new FileWriter(f);
			pw = new PrintWriter(fw);
			pw.println(gson.toJson(map));
			System.out.println("Mapa guardado.");			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ocurrió un prblema al guardar el mapa.");
		}finally {
			try {
				if(pw != null)
					pw.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}		
	}
	
	public Mapa getMap() {
		return map;
	}
	
}
