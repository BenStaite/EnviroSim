import java.awt.Color;
import java.util.ArrayList;

public class Herbivore extends Animal{
	
	public Herbivore(int x, int y, int boarder) {
		super("Herbi", Color.GREEN, x, y, boarder);
	}
	
	public void eat(Plant p) {
		this.energy += p.age;
		p.alive = false;
	}
	
	public void step(ArrayList<Cell> surroundings) {
		this.targeted = false;
		if (!checkPos()) {
			if ((!this.targeted)&&(this.energy < 100)) {
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
				c.addHerb(null);
			}
			if((c.x== this.x)&&(c.y == this.y)){
				c.addHerb(this);
			}
		}
	}
	
	public void checkMate(ArrayList<Cell> surroundings) {
		Cell nextcell = new Cell(0,0);
		boolean found = false;
		for (Cell c: surroundings) {
			if ((c.herb != null)&&(!(c.herb.equals(this))&&(c.herb.mature))) {
				nextcell = c;
				found = true;
			}
		}
		if(found) {
			directToTarget(nextcell.x,nextcell.y);
			this.targeted = true;
		}
	}
	
	public void checkFood(ArrayList<Cell> surroundings) {
		Cell nextcell = new Cell(0,0);
		double heighestEnergy = 0;
		for (Cell c: surroundings) {
			if (c.containsplant) {
				if ((c.plant.age>heighestEnergy)&&(c.plant.mature)) {
					heighestEnergy = c.plant.age;
					nextcell = c;
				}
			}
		}
		
		if (heighestEnergy > 0){
			directToTarget(nextcell.x,nextcell.y);
			this.targeted = true;
		}
	}
	
	@Override
	public Herbivore breed(Animal a) {
		this.bred = true;
		a.bred = true;
		this.regenTime = 0;
		Herbivore Son = new Herbivore(this.x, this.y,this.boarder);
		Son.manouver = ((a.manouver+this.manouver)/2)+Math.round((Math.random()*5)-2.5);
		Son.turnmod = ((a.turnmod+this.turnmod)/2)+((Math.random()/2)-0.25);
		Son.restThresh = ((a.restThresh+this.restThresh)/2)+((Math.random()/5)-0.1);
		return Son;
	}

}
