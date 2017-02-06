package test;
import java.util.Scanner;

public class ErFenGraph {  
  
	public static boolean find(int x,int [] arr){
		for(int i = 0;i<arr.length;i++){
			if(x == arr[i])
				return true;
		}
		return false;
	}
    //无向图的邻接表  
    public static void main(String[] args) {  
          
        Scanner scan = new Scanner(System.in);  
        int V = scan.nextInt();//顶点的个数  
        int E = scan.nextInt();//边的条数  
          
        Node adj[] = new Node[V];  
        for(int i=0;i<V;i++){  
            adj[i] = null;  
        }  
          
        for(int i=0;i<E;i++){  
            String input = scan.next();  
            int x = Integer.parseInt(input.substring(1, 2));  
            int y = Integer.parseInt(input.substring(3, 4));  
            adj[y] = new Node(x,adj[y]);  
            adj[x] = new Node(y,adj[x]);  
        }  
        
        int one[] = new int[V];
        int oth[] = new int[V];
        int onee=0,othh=0;//两个数组的下表跟踪
        for(int i =0;i< V;i++){
        	one[i] = -1;
        	oth[i] = -1;
        }
        for(int i=0;i<V;i++){
        	if(find(i,oth)){
        		for(Node temp=adj[i];temp!=null;temp=temp.next){  
        			if(!find(temp.val,oth)&&!find(temp.val,one)){
        				one[onee++] = temp.val;
        			}else if(find(temp.val,oth)){
        				System.out.println("不是二分图！！！");
        				return;
        			}
        		}
        	}
        	else{
        		if(!find(i,one)){
        			one[onee++] = i;
        		}
        		for(Node temp=adj[i];temp!=null;temp=temp.next){  
        			if(!find(temp.val,oth)&&!find(temp.val,one)){
        				oth[othh++] = temp.val;
        			}else if(find(temp.val,one)){
        				System.out.println("不是二分图！！！");
        				return;
        			}
        	//		System.out.print(temp.val+" ");  
        		} 
        	}
     //       System.out.println();  
        }  
        System.out.println("是二分图~~~~~~~~");
        System.out.println("第一部分：");
        for(int i =0;i< V;i++){
        	System.out.print(one[i]+" ");
        }
        System.out.println();
        System.out.println("第二部分：");
        for(int i =0;i< V;i++){
        	System.out.print(oth[i]+" ");
        }
    }  
static class Node{  
    int val;  
    Node next;  
    public Node(int val,Node next){  
        this.val = val;  
        this.next = next;  
    }  
}  
}  