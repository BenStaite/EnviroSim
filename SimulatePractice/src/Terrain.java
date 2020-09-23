
import javax.swing.*;
import javax.swing.border.LineBorder;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
//BEN STAITE
public class Terrain extends JFrame {
	
	public TestPane terrainPane;
	public TestPane plantPane;
	public TestPane pollenPane;
	public final int size;
	public int time;
	public int plantCount;
	public int flowerCount;
	public int flower2Count;
	public int flower3Count;
	public int flower4Count;
	public int treeCount;
	public ArrayList<Plant> plants;
	public ArrayList<Herbivore> herbis;
	public ArrayList<Carnivore> carnis;
	
	public int flowerLimit = 1000;
	public int flower2Limit = 1000;
	public int flower3Limit = 1000;
	public int flower4Limit = 1000;
	public int treeLimit = 1000;
	public int herbLimit = 75;
	public int carnLimit = 5;
	
    public Terrain(final int size) {
    	this.size = size;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	plants = new ArrayList<Plant>();
            	herbis = new ArrayList<Herbivore>();
            	carnis = new ArrayList<Carnivore>();
            	JPanel container = new JPanel();
            	container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
                JFrame frame = new JFrame("Terrain");
                
                terrainPane = new TestPane(size);
                terrainPane.fill();
                container.add(terrainPane);
                
                //plantPane = new PlantPane(size);
                //container.add(plantPane);
                
                pollenPane = new PollenPane(size);
                container.add(pollenPane);
                
                frame.add(container);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
    
    
    //returns the neighbouring cells of a coordinate where v is the distance
    public ArrayList<Cell> getSurroundings(int x, int y, int v) {
    	ArrayList<Cell> neighb = new ArrayList<Cell>();
    	for(int i = y-v; i <y+v+1; i++) {
    		for(int j = x-v; j <x+v+1; j++) {
    			if ((i<this.size)&&(i>0)&&(j>0)&&(j<this.size)) {
    				neighb.add(terrainPane.c[i][j]);
    			}
        	}
    	}
    	return neighb;
    }
    /*
    public void refreshAnimals() {
    	updateScent();
    	//HERBIVORES
    	ArrayList<Herbivore> rem = new ArrayList<Herbivore>();
    	ArrayList<Herbivore> add = new ArrayList<Herbivore>();
    	double avgSpeed = 0;
    	double avgRest = 0;
    	double avgTurn = 0;
    	double avgMan = 0;
    	for (Herbivore a: herbis) {
    		avgSpeed += a.speed;
    		avgRest += a.restThresh;
    		avgTurn += a.turnmod;
    		avgMan += a.manouver;
    		if (!a.alive) {
    			if ((a.y<this.size)&&(a.y>0)&&(a.x>0)&&(a.x<this.size)) {
    				if ((a.previousy<this.size)&&(a.previousy>0)&&(a.previousx>0)&&(a.previousx<this.size)) {
    					terrainPane.c[a.previousy][a.previousx].setBackground(Color.BLACK);
    				}
        			terrainPane.c[a.y][a.x].setBackground(Color.BLACK);
        		}
    			rem.add(a);
    		}
    		else {
	    		// Eating
	    		if ((a.y<this.size)&&(a.y>0)&&(a.x>0)&&(a.x<this.size)) {
		    		if ((terrainPane.c[a.y][a.x].containsplant)&&(a.energy<100)) {
		    			a.eat(terrainPane.c[a.y][a.x].plant);
		    			terrainPane.c[a.y][a.x].removePlant();
		    			plants.remove(terrainPane.c[a.y][a.x].plant);
		    		}
		    		terrainPane.c[a.y][a.x].setBackground(a.colour);
	    		}
	    		//Delete trail
	    		if ((a.previousy<this.size)&&(a.previousy>0)&&(a.previousx>0)&&(a.previousx<this.size)) {
	    			if (!((a.x == a.previousx)&&(a.y == a.previousy))) {
	    				terrainPane.c[a.previousy][a.previousx].setBackground(Color.BLACK);
	    				terrainPane.c[a.previousy][a.previousx].scent = 1;
	    			}
	    		}
	    		//Breeding
	    		if(herbis.size() < herbLimit) {
		    		if ((a.mature)&&(!a.bred)) {
			    		for (Herbivore b: herbis) {
			    			if ((!a.equals(b))&&(b.mature)) {
			    				if ( (Math.abs(a.x-b.x)<5) &&(Math.abs(a.y-b.y)<5)) {
			    					add.add(a.breed(b));
			    				}
			    			}
			    		}
			    	}
	    		}
	    		a.step(getSurroundings(a.x,a.y,20));
    		}
    	}
    	avgTurn = avgTurn/herbis.size();
    	avgRest = avgRest/herbis.size();
    	avgSpeed = avgSpeed/herbis.size();
    	avgMan = avgMan/herbis.size();
    	//System.out.println("Speed: "+avgSpeed+" Rest:"+avgRest+" Turn:"+avgTurn+" Manouvre:"+avgMan+" Size:"+herbis.size());
    	for (Herbivore a: add) {
    		herbis.add(a);
    	}
    	for (Herbivore a: rem) {
    		if ((a.y<this.size)&&(a.y>0)&&(a.x>0)&&(a.x<this.size)) {
    			terrainPane.c[a.y][a.x].setBackground(Color.BLACK);
    		}
    		herbis.remove(a);
    	}
    	
    	//CARNIVORES
    	
    	ArrayList<Carnivore> addc = new ArrayList<Carnivore>();
    	ArrayList<Carnivore> remc = new ArrayList<Carnivore>();
    	for (Carnivore a: carnis) {
    		if (!a.alive) {
    			if ((a.y<this.size)&&(a.y>0)&&(a.x>0)&&(a.x<this.size)) {
    				if ((a.previousy<this.size)&&(a.previousy>0)&&(a.previousx>0)&&(a.previousx<this.size)) {
    					terrainPane.c[a.previousy][a.previousx].setBackground(Color.BLACK);
    				}
        			terrainPane.c[a.y][a.x].setBackground(Color.BLACK);
        		}
    			remc.add(a);
    		}
    		else {
    		//eating 
	    		for(Herbivore h: herbis) {
	    			if ( (Math.abs(a.x-h.x)<3) &&(Math.abs(a.y-h.y)<3)&&(a.energy<200)) {
	    				a.eat(h);
	    			}
	    			terrainPane.c[a.y][a.x].setBackground(a.colour);
	    		}
	    		//show
	    		if ((a.y<this.size)&&(a.y>0)&&(a.x>0)&&(a.x<this.size)) {
	    			terrainPane.c[a.y][a.x].setBackground(a.colour);
	    		}
	    		//remove trail
	    		if ((a.previousy<this.size)&&(a.previousy>0)&&(a.previousx>0)&&(a.previousx<this.size)) {
	    			if (!((a.x == a.previousx)&&(a.y == a.previousy))) {
	    				terrainPane.c[a.previousy][a.previousx].setBackground(Color.BLACK);
	    			}
	    		}
	    		//breed
	    		if(carnis.size() < carnLimit) {
		    		if (a.mature) {
		    			if(!a.bred) {
				    		for (Carnivore b: carnis) {
				    			if ((!a.equals(b))&&(b.mature)) {
				    				if ( (Math.abs(a.x-b.x)<5) &&(Math.abs(a.y-b.y)<5)) {
				    					addc.add(a.breed(b));
				    				}
				    			}
				    		}
		    			}
			    	}
	    		}
	    		a.step(getSurroundings(a.x,a.y,50));
    		}		
    	}
    	for (Carnivore a: addc) {
    		carnis.add(a);
    	}
    	for (Carnivore a: remc) {
    		carnis.remove(a);
    		if ((a.y<this.size)&&(a.y>0)&&(a.x>0)&&(a.x<this.size)) {
    			terrainPane.c[a.y][a.x].setBackground(Color.BLACK);
    		}
    	}
    }*/
    
    /*
    public void updateScent() {
    	for (int i = 0; i<this.size; i++) {
    		for (int j = 0; j<this.size; j++) {
    			if (terrainPane.c[i][j].scent>0) {
    				terrainPane.c[i][j].scent-=0.1;
    			}
    		}
    	}
    }*/
    
    public void limitPlants() {
    	plantCount = 0;
    	flowerCount = 0;
    	flower2Count = 0;
    	flower3Count = 0;
    	flower4Count = 0;
    	treeCount = 0;
    	for (Plant plant: plants) {
    		plantCount++;
    		if (plant.name == "Flower") {
    			flowerCount++;
    		}
    		else if(plant.name == "Flower2") {
    			flower2Count++;
    		}
    		else if(plant.name == "Flower3") {
    			flower3Count++;
    		}
    		else if(plant.name == "Flower4") {
    			flower4Count++;
    		}
    		else {
    			treeCount++;
    		}
    		
    		double modifier = (plant.size/100);
    		int r = 255-plant.colour.getRed()+1;
    		int g = 255-plant.colour.getGreen()+1;
    		int b = 255-plant.colour.getBlue()+1;
    		int red = (int) (255-(r*modifier));
    		int green = (int) (255-(g*modifier));
    		int blue = (int) (255-(b*modifier));
    		if (red>255) {
    			red = 255;
    		}
    		if (red<0) {
    			red = 0;
    		}
    		if (green>255) {
    			green = 255;
    		}
    		if (green<0) {
    			green = 0;
    		}
    		if (blue>255) {
    			blue = 255;
    		}
    		if (blue<0) {
    			blue = 0;
    		}
    		terrainPane.c[plant.y][plant.x].setBackground(plant.colour);
    	}
    	//System.out.println(flowerCount+" "+flower2Count+" "+flower3Count+" "+flower4Count+" "+treeCount);
    	while (flowerCount>flowerLimit) {
    		getRandom("Flower").alive = false;
    		flowerCount--;
    	}
    	while (flower2Count>flower2Limit) {
    		getRandom("Flower2").alive = false;
    		flower2Count--;
    	}
    	while (flower3Count>flower3Limit) {
    		getRandom("Flower3").alive = false;
    		flower3Count--;
    	}
    	while (flower4Count>flower4Limit) {
    		getRandom("Flower4").alive = false;
    		flower4Count--;
    	}
    	while (treeCount>treeLimit) {
    		getRandom("Tree").alive = false;
    		treeCount--;
    	}
    	//System.out.println(flowerCount+" "+flower2Count+" "+flower3Count+" "+treeCount);
    }

    //returns a random plant
    public Plant getRandom(String name) {
    	while (true) {
        	int rand = (int)Math.round(Math.random()*(plants.size()-1));
        	Plant p = plants.get(rand);
        	if (p.name == name) {
        		return p;
        	}
    	}
    }
    
    public Plant getOldest(String name) {
    	int old = 0;
    	Plant oldest = new Plant(Color.BLACK, " ",0,0,size);
    	for(Plant plant : plants) {
    		if ((plant.age > old)&&(plant.name == name)&&(plant.alive)) {
    			oldest = plant;
    			old = plant.age;
    		}
        }
    	return oldest;
    }
    
    public void handlePollen() {
    	for (int y = 0; y < this.size; y++) {
            for (int x = 0; x < this.size; x++) {
            	pollenPane.c[y][x].setBackground(Color.GRAY);
            }
        }
		ArrayList<Pollen> rem = new ArrayList<Pollen>();
		ArrayList<Plant> PreviousPlants = new ArrayList<Plant>();
		PreviousPlants.addAll(plants);
		for (Plant plant : PreviousPlants) {
			for (Pollen p: plant.pollens) { //each pollen of plant
				Color col = plant.colour;
				if((p.y<this.size-1)&&(p.y>=0)&&(p.x<this.size-1)&&(p.x>=0)) {
					int px = (int) Math.round(p.x);
					int py = (int) Math.round(p.y);
					pollenPane.c[py][px].setBackground(col);
					if ((terrainPane.c[py][px].containsplant)) {
						if(plant.name != "Tree") {
							rem.add(p);
						}
						if((terrainPane.c[py][px].plant.name == p.type)&&(terrainPane.c[py][px].plant.mature)) {
							if(roomForMore(plant)) {
								int dirx = 0;
								int diry = 0;
								if (Math.random() > 0.5) {
									dirx = -1;
								}
								else {
									dirx = 1;
								}
								if (Math.random() > 0.5) {
									diry = -1;
								}
								else {
									diry = 1;
								}
								Random rand = new Random();
								int disx = (int) Math.round((rand.nextGaussian()*p.parent.spread));
								int disy = (int) Math.round((rand.nextGaussian()*p.parent.spread));
								int newx = px-(disx*dirx);
								int newy = py-(disy*diry);
								
								//Create new plant
								if ((newx<this.size)&&(newx>=0)&&(newy<this.size)&&(newy>=0)) {
									if(!(terrainPane.c[newy][newx].containsplant)) {
										Plant newplant = p.parent.son(newx,newy);
										plants.add(newplant);
										terrainPane.c[newy][newx].addPlant(newplant);
										pollenPane.c[py][px].setBackground(Color.WHITE);
										//System.out.println("ADDED: Plant: "+ newplant.name+ "  Age: "+ newplant.age+"  Time: "+ time +"  PCount: "+newplant.pollenCount+"  MaxAge: "+newplant.maxAge+" Mature: "+newplant.mature);
									}
		    					}
							}
						}
					}
				}
				else {
					rem.add(p);
				}
			}
			for(Pollen p: rem) {
				plant.pollens.remove(p);
			}
        }  
	}
    
    public boolean roomForMore(Plant plant) {
    	if (plant.name == "Flower") {
    		return (flowerCount<flowerLimit);
    	}
    	else if (plant.name == "Flower2") {
    		return (flower2Count<flower2Limit);
    	}
    	else if (plant.name == "Flower3") {
    		return (flower3Count<flower3Limit);
    	}
    	else if (plant.name == "Flower4") {
    		return (flower4Count<flower4Limit);
    	}
    	else if (plant.name == "Tree") {
    		return (treeCount<treeLimit);
    	}
    	else {
    		return false;
    	}
    }
    
    public void refresh() {
    	limitPlants();
    	//plantPane.refresh();
    	handlePollen();
    }
    
    public void stepPlants() {
    	ArrayList<Plant> rem = new ArrayList<Plant>();
    	refresh();
    	for (Plant plant: plants) {
    		if(plant.alive) {
    			plant.step();
    		}
    		else {
    			//System.out.println("REMOVED: Plant: "+ plant.name+ "  Age: "+ plant.age+"  Time: "+ time +"  PCount: "+plant.pollenCount+"  MaxAge: "+plant.maxAge+" Mature: "+plant.mature);
    			rem.add(plant);
    		}
    	}
    	for (Plant plant: rem) {
    		terrainPane.c[plant.y][plant.x].removePlant();
    		plants.remove(plant);
    	}
    	time++;
    }
    
 //   public void updateP(Map map) {
 //   	pane.updatePane(map);
 //   }

    public class TestPane extends JPanel {
    	
    	public int size;
    	public Cell[][] c;
    	
        public TestPane(int size) {
        	this.size = size;
        	c = new Cell[size][size];
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            for (int y = 0; y < size; y++) {
                gbc.gridy = y;
                for (int x = 0; x < size; x++) {
                    gbc.gridx = x;
                    c[y][x] = new Cell(x,y);
                    add(c[y][x], gbc);
                }
            }
        }
        
        public void fill() {
        	for (int y = 0; y < this.size; y++) {
                for (int x = 0; x < this.size; x++) {
                	double rand = Math.random();
                	if (rand > 0.999) {
                		Plant p = new Tree(x,y,this.size);
                		p.size = (int)Math.round(Math.random()*100);
                		c[y][x].addPlant(p);
                		plants.add(p);
                	}
                	else if(rand > 0.996) {
                		Plant p = new Flower(x,y,this.size);
                		p.size = (int)Math.round(Math.random()*100);
                		c[y][x].addPlant(p);
                		plants.add(p);
                	}
                	else if(rand > 0.994) {
                		Plant p = new Flower2(x,y,this.size);
                		p.size = (int)Math.round(Math.random()*100);
                		c[y][x].addPlant(p);
                		plants.add(p);
                	}
                	else if(rand > 0.992) {
                		Plant p = new Flower3(x,y,this.size);
                		p.size = (int)Math.round(Math.random()*100);
                		c[y][x].addPlant(p);
                		plants.add(p);
                	}
                	else if(rand > 0.990) {
                		Plant p = new Flower4(x,y,this.size);
                		p.size = (int)Math.round(Math.random()*100);
                		c[y][x].addPlant(p);
                		plants.add(p);
                	}
                	else  {
                		c[y][x].isDirt = false;
                		c[y][x].setBackground(Color.BLACK);
                	}
                }
        	}
        	/*
		    for(int i = 0; i < 30; i++) {
			    int randx = (int)Math.round(Math.random()*(this.size-2));
			    int randy = (int)Math.round(Math.random()*(this.size-2));
			    herbis.add(new Herbivore(randx,randy,this.size));
		    }
		    for(int i = 0; i < 10; i++) {
			    int randx = (int)Math.round(Math.random()*(this.size-2));
			    int randy = (int)Math.round(Math.random()*(this.size-2));
			    carnis.add(new Carnivore(randx,randy,this.size));
		    }
		    */
        }
    }
    
    
    //A panel to show plants exclusively
    public class PlantPane extends TestPane{
    	public PlantPane(int size) {
    		super(size);
    	}
    	
    	public void refresh() {
    		for (int y = 0; y < this.size; y++) {
                for (int x = 0; x < this.size; x++) {
                	if (terrainPane.c[y][x].containsplant) {
                		Plant plant = terrainPane.c[y][x].plant;
                		double modifier = (plant.size)/100;
                		int r = 255-plant.colour.getRed()+1;
                		int g = 255-plant.colour.getGreen()+1;
                		int b = 255-plant.colour.getBlue()+1;
                		int red = (int) (255-(r*modifier));
                		int green = (int) (255-(g*modifier));
                		int blue = (int) (255-(b*modifier));
                		if (red>255) {
                			red = 255;
                		}
                		if (red<0) {
                			red = 0;
                		}
                		if (green>255) {
                			green = 255;
                		}
                		if (green<0) {
                			green = 0;
                		}
                		if (blue>255) {
                			blue = 255;
                		}
                		if (blue<0) {
                			blue = 0;
                		}
                		c[y][x].setBackground(new Color(red,green,blue));
                	}
                	else {
                		c[y][x].setBackground(Color.BLACK);
                	}
                }
    		}
    	}
    }
    
    public class PollenPane extends TestPane{
    	public PollenPane(int size) {
    		super(size);
    	}
    }
}
