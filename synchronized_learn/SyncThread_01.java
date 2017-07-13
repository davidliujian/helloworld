package test;

public class SyncThread_01 implements Runnable{

	   private static int count;
	 
	   public SyncThread_01() {
	      count = 0;
	   }
	 
	   //synchronized 修饰static方法，锁定的是这个类的所有对象
	   public synchronized static void method() {
		      for (int i = 0; i < 5; i ++) {
		         try {
		            System.out.println(Thread.currentThread().getName() + ":" + (count++));
		            Thread.sleep(100);
		         } catch (InterruptedException e) {
		            e.printStackTrace();
		         }
		      }
		   }
	   
	   public  void run() {
	      synchronized(this) {
	    	  method();
	      }
	   }
	 
	   public int getCount() {
	      return count;
	   }
	}