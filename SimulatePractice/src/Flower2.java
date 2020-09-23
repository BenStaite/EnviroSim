import java.awt.Color;

public class Flower2 extends Plant{
	
	public Flower2(int x, int y, int boarder) {
		super(new Color(196,0,255),"Flower2",x,y, boarder);
		this.growRate = 2;
		this.pollenRate = 0.03;
		this.maxAge = 150;
		this.pollenCount= 20;
		this.pollenReach = 15;
		this.spread = 5;
	}
}
