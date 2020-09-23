import java.awt.Color;
import java.util.ArrayList;

public class Carnivore extends Animal{
	public Carnivore(int x, int y,int boarder) {
		super("Carni", Color.WHITE, x, y, boarder);
		this.maxAge = 2000;
	}
	
	public void eat(Herbivore h) {
		this.energy += h.energy;
		h.alive = false;
	}
	
	public void step(ArrayList<Cell> surroundings) {
		this.targeted = false;
		if (!checkPos()) {
			if ((!this.targeted)&&(this.energy < 200)) {
				checkFood(surroundings);
				move();
			}
			else if ((!this.bred)&&(!this.targeted)) {
				checkMate(surroundings);
				move();
			}
			else {
				double randMove = Math.random();
				if (randMove<this.restThresh) {
					wander();
					move();
				}
			}
		}
		else {
			move();
		}
		updateCell(surroundings);
		update();
	}
	
	public void updateCell(ArrayList<Cell> surroundings) {
		for (Cell c: surroundings) {
			if((c.x== previousx)&&(c.y == previousy)){
				c.addCarn(null);
			}
			if((c.x== this.x)&&(c.y == this.y)){
				c.addCarn(this);
			}
		}
	}
	
	public void checkFood(ArrayList<Cell> surroundings) {
		Cell nextcell = new Cell(0,0);
		double heighestEnergy = 0;
		for (Cell c: surroundings) {
			if (c.herb != null) {
				if ((c.herb.energy>heighestEnergy)) {
					heighestEnergy = c.herb.energy;
					nextcell = c;
				}
			}
		}
		
		if (heighestEnergy > 0){
			directToTarget(nextcell.x,nextcell.y);
			this.targeted = true;
		}
	}
	
	public void checkMate(ArrayList<Cell> surroundings) {
		Cell nextcell = new Cell(0,0);
		boolean found = false;
		for (Cell c: surroundings) {
			if ((c.carn != null)&&(!(c.carn.equals(this))&&(c.carn.mature))) {
				nextcell = c;
				found = true;
			}
		}
		if(found) {
			directToTarget(nextcell.x,nextcell.y);
			this.targeted = true;
		}
	}
	
	@Override
	public Carnivore breed(Animal a) {
		this.bred = true;
		Carnivore Son = new Carnivore(this.x, this.y,this.boarder);
		Son.manouver = ((a.manouver+this.manouver)/2)+Math.round((Math.random()*5)-2.5);
		Son.speed = ((a.speed+this.speed)/2)+(Math.random()-0.5);
		Son.turnmod = ((a.turnmod+this.turnmod)/2)+((Math.random()/2)-0.25);
		return Son;
	}

}
