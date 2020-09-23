import java.awt.Color;

public class Flower4 extends Plant{
	
	public Flower4(int x, int y, int boarder) {
		super(Color.RED,"Flower4",x,y, boarder);
		this.growRate = 2;
		this.pollenRate = 0.03;
		this.maxAge = 130;
		this.pollenCount= 20;
		this.pollenReach = 20;
		this.spread = 5;
	}
}