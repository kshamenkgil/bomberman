package bomberman;

public class AgarroPotenciador {
	private Punto2D pos;
	private Potenciador pot;
	private byte id;
	
	public byte getId() {
		return id;
	}
	
	public void setId(byte id) {
		this.id = id;
	}
	
	public AgarroPotenciador() {
		// TODO Auto-generated constructor stub
	}
	
	public AgarroPotenciador(Punto2D pos, Potenciador pot, byte id) {
		this.pos = pos;
		this.pot = pot;
		this.id = id;
	}
	
	public void setPot(Potenciador pot) {
		this.pot = pot;
	}
	public void setPos(Punto2D pos) {
		this.pos = pos;
	}
	public Potenciador getPot() {
		return pot;
	}
	public Punto2D getPos() {
		return pos;
	}
	
}
