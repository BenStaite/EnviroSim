import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class main {

	public static void main(String[] args) throws IOException, InterruptedException {
		Terrain ter = new Terrain(200);
		TimeUnit.MILLISECONDS.sleep(10000);
		ter.refresh();
		TimeUnit.MILLISECONDS.sleep(20000);
		int count = 0;
		while(true) {
			TimeUnit.MILLISECONDS.sleep(50);
			if (count%2==0) {
				ter.stepPlants();
			}
			//ter.refreshAnimals();
			count++;
		}
	}
}
