
public class Pollen {
	public String type;
	public Plant parent;
	public double x;
	public double y;
	public double bearing;
	public int speed;
	public int age;
	
	
	public Pollen(Plant parent) {
		this.speed = 1;
		this.parent = parent;
		this.type = parent.species;
		this.x = parent.x;
		this.y = parent.y;
		this.bearing = Math.random() * 360;
		this.age = 0;
	}
	
	public void step() {
		double newx = speed * Math.sin(Math.toRadians(this.bearing)); 
		double newy = speed * Math.cos(Math.toRadians(this.bearing)); 
		this.x += newx;
		this.y += newy;
		this.x = Math.round(this.x * 100.0) / 100.0;
		this.y = Math.round(this.y * 100.0) / 100.0;
		this.age ++;
	}
}
