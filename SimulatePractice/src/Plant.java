import java.awt.Color;
import java.util.ArrayList;

public class Plant {
	public Color colour;
	public String name;
	public String species;
	public double size; // up to 100
	public double growRate; //per day
	public double pollenRate; // in pollens/week
	public int pollenCount;
	public double pollenProgress; // up to 1
	public boolean mature;
	public int x;
	public int y;
	public ArrayList<Pollen> pollens;
	public int age;
	public int maxAge;
	public boolean alive;
	public int pollenReach;
	public int spread;
	public int boarder;
	
	public boolean pollinated;
	
	public Plant(Color c, String n,int x,int y,int boarder) {
		this.pollens = new ArrayList<Pollen>();
		this.name = n;
		this.colour = c;
		this.size =0;
		this.species = n; //temporary
		this.pollenProgress =  Math.random();
		this.x = x;
		this.y = y;
		this.age = 0;
		this.alive = true;
		this.pollinated = false;
		this.mature = false;
		this.boarder = boarder;
	}
	
	public void step() {
		if(!this.mature) {
			if(this.size < 100) {
				this.size += this.growRate;
			}
			else {
				this.size = 100;
				this.mature = true;
			}
		}
		else {
			if (this.age > this.maxAge){
				this.alive = false;
			}
			this.pollenProgress += (this.pollenRate);
			if ((this.pollenProgress>=1)&&(!this.pollinated)) {
				this.pollinate();
				this.pollenProgress = 0;
			}
			ArrayList<Pollen> rem = new ArrayList<Pollen>();
			for (Pollen p: this.pollens) {
				p.step();
				if ((p.x > this.boarder)|| (p.y > this.boarder) ||(p.x<0)||(p.y<0)||(p.age>this.pollenReach)) {
					rem.add(p);
				}
			}
			for (Pollen p: rem) {
				pollens.remove(p);
			}
			this.age ++;
		}
	}
	
	public Plant son(int x, int y) {
		Plant son = new Plant(this.colour, this.name, x,y,this.boarder);
		son.growRate = this.growRate;
		son.pollenRate = this.pollenRate;
		son.maxAge = this.maxAge;
		son.pollenCount = this.pollenCount;
		son.pollenReach = this.pollenReach;
		son.spread = this.spread;
		return son;
	}
	
	public void pollinate() {
		this.pollinated = true;
		for(int i = 0; i< pollenCount; i++) {
			this.pollens.add(new Pollen(this));
		}
	}
}
