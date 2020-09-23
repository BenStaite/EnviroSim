import java.awt.Color;

public class Tree extends Plant {
	
	public Tree(int x, int y, int boarder) {
		super(new Color(142,49,49),"Tree",x,y,boarder);
		this.growRate = 0.1;
		this.pollenRate = 0.002;
		this.maxAge = 1000;
		this.pollenCount= 80;
		this.pollenReach = 100;
		this.spread = 30;
	}
	
}
