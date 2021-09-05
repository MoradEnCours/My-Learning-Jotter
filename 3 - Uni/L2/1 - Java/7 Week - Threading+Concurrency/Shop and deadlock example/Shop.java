package shop;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Shop {
	
	private Lock lock = new ReentrantLock();
	private Condition queue = lock.newCondition();
	
	private Set<Customer> curCustomers = new HashSet<>();
	
	public boolean enter(Customer c) {
		try {
			try {
				lock.lockInterruptibly();
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + ": Shop closed before " + c + " could enter queue");
				return false;
			}
			
			while (curCustomers.size() >= SHOP_CAPACITY) {
				System.out.println(Thread.currentThread().getName() + ": " + c + " entering queue");
				try {
					queue.await();
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName() + ": Shop closed while " + c + " was waiting");
					return false;
				}
			}

			// If we get here, we can enter the shop ourself
			curCustomers.add(c);
			System.out.println(Thread.currentThread().getName() + ": " + c + " entered shop; customers now " + curCustomers);
			return true;
		} finally {
			lock.unlock();
		}
	}
	
	public void exit(Customer c) {
		try {
			try {
				lock.lockInterruptibly();
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + ": Shop closed before " + c + " could exit shop");
				return;
			}
			
			curCustomers.remove(c);
			System.out.println(Thread.currentThread().getName() + ": " + c + " exited shop; customers now " + curCustomers);
			queue.signal();
		} finally {
			lock.unlock();
		}
	}
	
	private static final int NUM_CUSTOMERS = 20;
	private static final int SHOP_CAPACITY = 3;
	
	public static void main(String[] args) throws InterruptedException {
		Shop shop = new Shop();
		
		Thread[] threads = new Thread[NUM_CUSTOMERS];
		for (int i = 0; i < NUM_CUSTOMERS; i++) {
			threads[i] = new Thread (new Customer (shop, i));
			threads[i].start();
		}
		
		Thread.sleep(5000);
		
		System.out.println("SHOP CLOSING!!!");
		
		for (Thread thread : threads) {
			if (thread.isAlive()) {
				thread.interrupt();
			}
		}

		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
