package bomberman;
import servidor.Server;
public class Main {

	public static void main(String[] args) {
		Server server = null;
		boolean serverOnly = false;
		for (int i = 0; i < args.length; i++) {
			if(args[i].compareTo("-server") == 0){
				if(server == null){					
					server = new Server();
					new Thread(server, "Servidor").start();
				}
			}else if(args[i].compareTo("-serveronly") == 0){
				server = new Server();
				new Thread(server,"Servidor").start();
				serverOnly = true;
			}
		}
		
		if(!serverOnly)
			Bomberman.getInstancia().run();		
		
	}

}
