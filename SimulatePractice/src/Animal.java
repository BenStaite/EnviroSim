import java.awt.Color;

public abstract class Animal {
	public String name;
	public int age;
	public int maxAge;
	public double speed;
	public Color colour;
	public double energy;
	public int x;
	public int y;
	public double bearing;
	public boolean alive;
	public double turnmod;
	public double manouver;
	public int boarder;
	public int previousx;
	public int previousy;
	public boolean bred;
	public boolean mature;
	public int regenTime;
	public boolean targeted;
	public double restThresh;
	
	public  Animal(String n, Color c,int x, int y,int boarder) {
		this.bred = false;
		this.turnmod = 0.1;
		this.name = n;
		this.manouver = 45;
		this.targeted = false;
		this.age = 0;
		this.colour = c;
		this.energy = 100;
		this.bearing =(Math.random()*(359));
		this.x = x;
		this.y = y;
		this.maxAge = 1000;
		this.alive = true;
		this.speed = 1;
		this.boarder = boarder;
		this.mature = false;
		this.regenTime = 0;
		this.restThresh = 0.8;
	}
	
	public void step() {}
	
	public void eat() {}
	
	public boolean checkPos() {
		if (this.x>this.boarder-2) {
			this.bearing = 270;
			if (this.x>this.boarder-1) {
				System.out.println("Right");
				this.x = this.boarder -2;
			}
			return true;
		}
		else if (this.x<2) {
			this.bearing = 90;
			if (this.x<1) {
				System.out.println("Left");
				this.x = 2;
			}
			return true;
		}
		else if (this.y>this.boarder-2) {
			this.bearing = 180;
			if (this.y>this.boarder-1) {
				System.out.println("Above");
				this.y = this.boarder - 2;
			}
			return true;
		}
		else if (this.y<2) {
			this.bearing = 0;
			if (this.y<1) {
				System.out.println("Below");
				this.y = 2;
			}
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public void move() {
		double newx = speed * Math.sin(Math.toRadians(this.bearing)); 
		double newy = speed * Math.cos(Math.toRadians(this.bearing)); 
		this.previousx = this.x;
		this.previousy = this.y;
		this.x += Math.round(newx);
		this.y += Math.round(newy);
		this.energy-= (speed/2);
	}
	
	public void wander() {
		double randTurn = Math.random()+turnmod;
		if (randTurn > 0.75) {
			turn();
		}
	}
	public void directToTarget(int x, int y) {
		if (x> this.x) {
			if (y > this.y) {
				this.bearing = 45;
			}
			else if(y<this.y) {
				this.bearing = 135;
			}
			else {
				this.bearing = 90;
			}
		}
		else if (x< this.x) {
			if (y > this.y) {
				this.bearing = 315;
			}
			else if(y<this.y) {
				this.bearing = 225;
			}
			else {
				this.bearing = 270;
			}
		}
		else if(y>this.y) {
			this.bearing = 0;
		}
		else {
			this.bearing = 180;
		}
	}
	
	public void update() {
		if(this.regenTime>200) {
			this.bred = false;
		}
		if (this.age>maxAge) {
			this.alive = false;
		}
		if (this.energy <= 0) {
			this.alive = false;
		}
		if (this.age>(this.maxAge/2)) {
			this.mature = true;
		}
		this.regenTime++;
		this.age++;
	}
	
	public abstract Animal breed(Animal a);
	
	public void turn() {
		double degree = Math.random() * manouver;
		double dir = Math.random();
		if (dir <0.5) {
			degree = - degree;
		}
		bearing = (int)(bearing + degree);
		if ((bearing < 0) || (bearing > 360)) {
			bearing = bearing % 360;
		}
	}
	
}
