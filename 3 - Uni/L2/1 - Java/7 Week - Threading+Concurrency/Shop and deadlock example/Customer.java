package shop;

public class Customer implements Runnable {

	private Shop shop;
	private int id;

	public Customer (Shop shop, int i) {
		this.shop = shop;
		this.id = i;
	}

	@Override
	public void run() {
		boolean inShop = false;
		try {
			Thread.sleep(200 * id);
			inShop = shop.enter(this);
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " Shop closed before " + this + " could join queue");
		}

		if (inShop) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + ": Shop closed while " + this + " was shopping");
			}
			shop.exit(this);
		}
	}
	
	@Override
	public String toString() {
		return "C" + id;
	}

}
