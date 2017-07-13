package test;

public class bird {
	private static String color;
	private static String fly;
	
	public bird(String color,String fly){
		this.color=  color;
		this.fly = fly;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		color = color;
	}
	public String getFly() {
		return fly;
	}
	public void setFly(String fly) {
		this.fly = fly;
	}
	
	public synchronized void say(){
		System.out.println("I can eat xiao chongchong.");
	}
	
	public static synchronized void eat(){
		System.out.println("I am a "+color+"bird, and I "+fly+"fly");
	}
}
