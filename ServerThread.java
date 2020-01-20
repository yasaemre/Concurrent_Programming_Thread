import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ServerThread implements Runnable{

	private BlockingQueue<Food> queue;
	private Food food;
	private List<Food> foodList;

	public ServerThread(List<Food> foodList, BlockingQueue<Food> queue) {
		this.foodList = foodList;
		this.queue = queue;
	}

	@Override
	public void run() {
		for(int i =0; i< foodList.size(); i++) {
			try {
				System.out.println("SERVER READY");
				food = queue.take();
				System.out.println("SERVER STARTING: " + food.getName() + " (" + food.getCookTime() + " TO COOK, " 
				+ food.getServeTime() + " TO SERVE)");
				Thread.sleep(food.getServeTime() * 1000);
				System.out.println("SERVER ENDING: " + food.getName() + " (" + food.getCookTime() + " TO COOK, " 
						+ food.getServeTime() + " TO SERVE)");
			} catch(InterruptedException ex) {}
		}
	}
}
