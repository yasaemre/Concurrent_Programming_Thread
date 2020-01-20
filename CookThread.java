import java.util.List;
import java.util.concurrent.BlockingQueue;

public class CookThread implements Runnable {
	
	private BlockingQueue<Food> queue;
	private Food food;
	private List<Food> foodList;

	public CookThread(List<Food> foodList, BlockingQueue<Food> queue) {
		this.foodList = foodList;
		this.queue = queue;
	}

	@Override
	public void run() {
		for(int i =0; i< foodList.size(); i++) {
			try {
				System.out.println("COOK READY");
				food = foodList.get(i);				
				System.out.println("COOK STARTING: " + foodList.get(i).getName() + " (" + foodList.get(i).getCookTime() + " TO COOK, " 
				+ foodList.get(i).getServeTime() + " TO SERVE)");
				Thread.sleep(foodList.get(i).getCookTime() * 1000);
				System.out.println("COOK ENDING: " + foodList.get(i).getName() + " (" + foodList.get(i).getCookTime() + " TO COOK, " 
						+ foodList.get(i).getServeTime() + " TO SERVE)");
				queue.put(food);
			} catch(InterruptedException ex) {}	
		}
	}

}
