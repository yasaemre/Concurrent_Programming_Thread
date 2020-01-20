import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class FoodTester {

	public static void main(String[] args) throws InterruptedException {
		List<Food> foodList = new ArrayList<>();
		foodList.add(new Food("Spinach Dip", 2, 1));
		foodList.add(new Food("Burger", 5, 1));
		foodList.add(new Food("Pasta", 4, 3));
		foodList.add(new Food("Baked Alaska", 6, 20));
		foodList.add(new Food("Salad", 1, 1));
		foodList.add(new Food("Bruchetta", 3, 1));
		foodList.add(new Food("Bread", 1, 1));
		foodList.add(new Food("Fried Green Tomatoes", 2, 1));
		
		// INITIALIZE AND START YOUR THREADS HERE!
		BlockingQueue<Food> queue = new ArrayBlockingQueue<>(3);
		Thread cooker = new Thread(new CookThread(foodList, queue));
		Thread server = new Thread(new ServerThread(foodList, queue));
		
		cooker.start();
		server.start();

      // IMPORTANT NOTE!!! 
		// Some IDEs have more than one thread active. You might need to 
		// change the >1 to >2 or similar in order to get the tester to
		// work in your IDE.
		int programTimeCounter=0;
		while(Thread.activeCount()>1 && server.isAlive()) {
			System.out.println("TIME " + programTimeCounter);
			programTimeCounter++;
			try {
				Thread.sleep(1000);
			} catch(InterruptedException ex) {
				
			}
		}
		
		// USE STREAMS HERE ON THE INITIAL LIST! 
		// NOTE: THIS PART HAS NOTHING TO DO WITH THE THREADS- JUST MORE STREAM PRACTICE! :)
		// USE METHOD REFERENCES!
		int totalCookTime = -1;
		int totalServeTime = -1;
		totalCookTime = foodList.stream().collect(Collectors.summingInt(Food::getCookTime));
		totalServeTime = foodList.stream().collect(Collectors.summingInt(Food::getServeTime));
		System.out.println("Total Cook Time = " + totalCookTime);
		System.out.println("Total Serve Time = " + totalServeTime);	
		System.out.println("Program Time = " + programTimeCounter);
	}

}
