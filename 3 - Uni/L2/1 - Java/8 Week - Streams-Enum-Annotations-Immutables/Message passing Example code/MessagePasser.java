package messages;

public class MessagePasser implements Runnable {
	
	private MessageCentre centre;
	private String message;
	
	public MessagePasser(MessageCentre centre, int i) {
		this.centre = centre;
		this.message = String.valueOf(i);
	}

	
	@Override
	public void run() {
		while (true) {
			System.out.println(Thread.currentThread().getId() + ": waiting for the lock");
			try {
				centre.messageLock.lockInterruptibly();
			} catch (InterruptedException e1) {
				System.out.println(Thread.currentThread().getId() + ": interrupted while waiting for lock");
				break;
			}
			try {
				System.out.println(Thread.currentThread().getId() + ": got the lock");
				if (centre.message == null) {
					centre.message = message;
					System.out.println(Thread.currentThread().getId() + ": Added message " + message);
					// We have put our message in the box; we wait for someone to retrieve it
					try {
						System.out.println(Thread.currentThread().getId() + ": Waiting for someone to take my message");
						centre.messageCondition.await();
					} catch (InterruptedException e) {
						System.out.println(Thread.currentThread().getId() + ": interrupted while waiting for someone to take message");
						break;
					}
					System.out.println(Thread.currentThread().getId() + ": Someone took my message");
				} else {
					// There is already a message in the box:
					// Retrieve it, pause for 200ms, and then notify the thread that was waiting
					String prevMessage = centre.message;
					centre.message = null;
					System.out.println(Thread.currentThread().getId() + ": Got message: " + prevMessage);
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						System.out.println(Thread.currentThread().getId() + ": interrupted in sleep()");
						break;
					}
					centre.messageCondition.signal();
				}
			} finally {
				System.out.println(Thread.currentThread().getId() + ": released the lock");
				centre.messageLock.unlock();
			}
		}
	}
}
