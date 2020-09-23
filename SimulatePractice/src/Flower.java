import java.awt.Color;

public class Flower extends Plant{
	
	public Flower(int x, int y, int boarder) {
		super(new Color(33,166,215),"Flower",x,y, boarder);
		this.growRate = 1;
		this.pollenRate = 0.03;
		this.maxAge = 250;
		this.pollenCount = 18;
		this.pollenReach = 30;
		this.spread = 10;
	}
}
