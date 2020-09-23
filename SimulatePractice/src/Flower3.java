import java.awt.Color;

public class Flower3 extends Plant{
	
	public Flower3(int x, int y, int boarder) {
		super(Color.ORANGE,"Flower3",x,y, boarder);
		this.growRate = 2;
		this.pollenRate = 0.03;
		this.maxAge = 130;
		this.pollenCount= 20;
		this.pollenReach = 10;
		this.spread = 15;
	}
}
