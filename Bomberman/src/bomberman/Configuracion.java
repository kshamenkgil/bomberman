package bomberman;

public class Configuracion {
	private static Configuracion instancia;
	 
	public static Configuracion getInstancia() {
		// TODO Auto-generated constructor stub
		if(instancia == null){
			instancia = new Configuracion();
		}
		return instancia;
	}
	
	public void leerConfiguracion(){
		
	}
}
