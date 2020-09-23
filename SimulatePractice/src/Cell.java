import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Cell extends JPanel {
	    	public boolean containsplant;
	    	public Plant plant;
	    	public boolean isDirt;
	    	public int y;
	    	public int x;
	    	public double scent;
	    	public Herbivore herb;
	    	public Carnivore carn;
	    	
	
	        public Cell(int x, int y) {
	        	this.x = x;
	        	this.y = y;
	        	this.scent = 0;
	        	this.isDirt = true;
	        	this.containsplant = false;
	        	this.herb = null;
	        	this.carn = null;
	        }
	        
	      
	        
	        public void removePlant() {
    			this.plant = null;
    			this.containsplant = false;
    			this.isDirt = false;
    			this.setBackground(Color.BLACK);
	        }
	        
	        public void addHerb(Herbivore h) {
	        	this.herb = h;
	        }
	        
	        public void addCarn(Carnivore c) {
	        	this.carn = c;
	        }
	        
	        public void addPlant(Plant p) {
	        	this.plant = p;
	        	this.containsplant = true;
	        	this.isDirt = false;
	        }
	
	        @Override
	        public Dimension getPreferredSize() {
	            return new Dimension(3, 3);
	        }
	        
	        public void colourCell(Color c) {
	        	setBackground(c);
	        }
	    }
