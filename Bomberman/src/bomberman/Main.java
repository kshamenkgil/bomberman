package bomberman;
import servidor.Server;
import servidor.ServerScreen;
public class Main {

	public static void main(String[] args) {		
		boolean serverOnly = false;
		for (int i = 0; i < args.length; i++) {
			if(args[i].compareTo("-server") == 0){								
				ServerScreen sc = new ServerScreen();
				sc.setVisible(true);			
			}else if(args[i].compareTo("-serveronly") == 0){
				ServerScreen sc = new ServerScreen();
				sc.setVisible(true);
				serverOnly = true;
			}
		}
		
		if(!serverOnly)
			Bomberman.getInstancia().run();		
		
	}

}
