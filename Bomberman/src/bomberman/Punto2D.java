package bomberman;

public class Punto2D {
	protected double x;
	protected double y;		
	
	public Punto2D(double x, double y) {		
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double distancia(Object p){
		Punto2D p2 = (Punto2D)p;
		return Math.sqrt(Math.pow(this.x - p2.x,2) + Math.pow(this.y - p2.y,2));		
	}
	
	public double modulo(){
		return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y,2));
	}
	
	public String toString() {
		return "Punto2D [x=" + this.x + ", y=" + this.y + "]";
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Punto2D other = (Punto2D) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
		
	public void desplazamiento(Object p){
		Punto2D p2 = (Punto2D)p;
		this.x += p2.x;
		this.y += p2.y;
	}
	
	public Object suma(Object p2){
		Punto2D p = (Punto2D)p2;
		return new Punto2D(this.x+p.x, this.y+p.y);
	}	

}
