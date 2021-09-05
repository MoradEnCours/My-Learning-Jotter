package messages;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageCentre {
	
	public String message = null;
	public Lock messageLock = new ReentrantLock();
	public Condition messageCondition = messageLock.newCondition();
	
	public static void main(String[] args) throws InterruptedException {
		MessageCentre centre = new MessageCentre();
		
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			threads.add(new Thread(new MessagePasser(centre, i)));
		}
		
		for (Thread t : threads) {
			t.start();
		}
		
		Thread.sleep(2000);
		
		for (Thread t : threads) {
			t.interrupt();
		}
		
		for (Thread t : threads) {
			t.join();
		}
		
		System.out.println("Threads are all done!");
	}

}
