package test;

import java.util.Stack;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Integer.MIN_VALUE);
		int a =65;
		char b =(char) (a);
		System.out.println(b);
		char d ='0';
		System.out.println(d-48);
		int x;
		String dd =d+"";
		System.out.println(Integer.parseInt(dd));
		String innt = 1+"";
		System.out.println(dd.equals(innt));
		System.out.println((int)d);
		
		
		int c[] = new int[5];
		c=new int[3];
		for(int i=0;i<c.length;i++){
			System.out.print(c[i]+" ");
		}
		System.out.println();
		
		char ch[] = "A->B:2".toCharArray();
		System.out.println(ch[5]);
		
		Stack s = new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		
		System.out.print(s.pop()+" ");
	}
	

}
