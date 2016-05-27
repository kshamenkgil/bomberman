package bomberman;

public class Engine {
	private static Engine instancia;
	
	public static Engine getInstancia() {
		// TODO Auto-generated constructor stub
		if(instancia == null){
			instancia = new Engine();
		}
		return instancia;
	}
	
	public void dibujar(Punto2D pos){
		//rutina del engine para dibujar en la pantalla
	}
	
	public void update(){
		
	}
		
}
