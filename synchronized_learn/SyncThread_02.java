package test;

public class SyncThread_02 implements Runnable {
	private static int count;

	public SyncThread_02() {
		count = 0;
	}

	//synchronized作用于一个类T时，是给这个类T加锁，T的所有对象用的是同一把锁。
	public static void method() {
		synchronized (SyncThread.class) {
			for (int i = 0; i < 5; i++) {
				try {
					System.out.println(Thread.currentThread().getName() + ":" + (count++));
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public synchronized void run() {
		method();
	}
}