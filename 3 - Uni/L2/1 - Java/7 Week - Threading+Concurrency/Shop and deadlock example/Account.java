package deadlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

	private Lock lock = new ReentrantLock();

	/**
	 * Transferring money, using a reentrant lock and tryLock() in a loop to avoid deadlock.
	 */
	public static void transferMoneyFancy(Account fromAccount, Account toAccount, double amountToTransfer) {
		while (true) {
			if (fromAccount.lock.tryLock()) {
				try {
					if (toAccount.lock.tryLock()) {
						try {
							fromAccount.debit(amountToTransfer);
							toAccount.credit(amountToTransfer);
							break;
						} finally {
							toAccount.lock.unlock();
						}
					}
				} finally {
					fromAccount.lock.unlock();
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// Ignore it
			}
		}
	}

	/**
	 * Transferring money. This will lead to deadlock when run as in the main method!
	 */
	public static void transferMoney(Account fromAccount, Account toAccount, double amountToTransfer) {
		synchronized (fromAccount) {
			System.out.println("Got lock on " + fromAccount + "; waiting for " + toAccount);
			synchronized (toAccount) {
				System.out.println("Got lock on " + toAccount + " too");
				if (fromAccount.hasSufficientBalance(amountToTransfer)) {
					fromAccount.debit(amountToTransfer);
					toAccount.credit(amountToTransfer);
				}
			}
		}
	}

	private void credit(double amountToTransfer) {
	}

	private void debit(double amountToTransfer) {
	}

	private boolean hasSufficientBalance(double amountToTransfer) {
		return true;
	}

	public static void main(String[] args) {
		Account a1 = new Account();
		Account a2 = new Account();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				transferMoney(a1, a2, 100);
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				transferMoney(a2, a1, 100);
			}
		});

		t1.start();
		t2.start();

		// t1 and t2 are deadlocked now with the default implementation
		
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Done");
	}

}
