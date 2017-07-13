package test;

public class Main extends bird{

	public Main(String color, String fly) {
		super(color, fly);
		// TODO Auto-generated constructor stub
	}

	public  void say(){
		super.say();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * 1. 修饰一个代码块，被修饰的代码块称为同步语句块，其作用的范围是大括号{}括起来的代码，
		 *    作用的对象是调用这个代码块的对象；
		 */
		SyncThread sync  = new SyncThread();
//		Thread thread1 = new Thread(sync,"sync1");
//		Thread thread2 = new Thread(sync,"sync2");
		Thread thread1 = new Thread(new SyncThread(), "SyncThread1");
		Thread thread2 = new Thread(new SyncThread(), "SyncThread2");
		thread1.start();
		thread2.start();
	}

}
