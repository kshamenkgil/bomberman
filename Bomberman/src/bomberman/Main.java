package bomberman;
import servidor.Server;
public class Main {

	public static void main(String[] args) {
		Server server = null;
		Bomberman bomberman = null;
		for (int i = 0; i < args.length; i++) {
			if(args[i].compareTo("-server") == 0){
				if(server == null){					
					server = new Server();
					new Thread(server, "Servidor").start();
				}
			}
		}
		
		bomberman = new Bomberman();
		bomberman.run();
		
	}

}
