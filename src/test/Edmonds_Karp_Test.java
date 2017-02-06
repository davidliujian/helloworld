package test;

import java.util.Arrays;
import java.util.LinkedList;

public class Edmonds_Karp_Test {

	public static void main(String[] args) {
		//输入一个图
		int [][] cap ={  {0,5,0,0,0},
					  	{0,0,6,0,1},
				        {0,0,0,5,2},
				        {0,0,0,0,1},
				        {0,0,0,0,0},
				        };//有向图的初始容量
		int max=EdmondsKarp(0, 4, cap);
		System.out.println(max);
		
	}
	public static int EdmondsKarp(int start,int end,int[][] cap){
		int [][] flow =new int [cap.length][cap[0].length];	//记录流量
		int [] pre = new int[cap.length];	//记录前一个节点
		int [] rest = new int[cap.length];	//记录剩余的流量，残量
		
		int maxflow = 0;
		
		for(int i=0;i<flow.length;i++){
			Arrays.fill(flow[i], 0);		
		}
		
		Arrays.fill(pre, 0);	
		
		LinkedList list = new LinkedList<Integer>();
		
		while(true){
//			System.out.println("yici");
			for(int j =0 ;j<rest.length;j++){//设置残量都为0
				rest[j] =0;
			}
			rest[start] = 10000000;	//设置起始位置的残量为一个很大的值
			list.add(start);
			while(!list.isEmpty()){			//BFS 寻找增广路
//				System.out.println("yici");
				int u = (int)list.pollFirst();
				for(int v =0;v<=end;v++){
					if(rest[v]==0 && cap[u][v]-flow[u][v] > 0){
						pre[v] =u;		//设置前面的节点
						rest[v] = Math.min(rest[u], cap[u][v]-flow[u][v]);//获取start到v节点的最小残量
						list.add(v);
					}
				}
			}
//			System.out.println(rest[end]+"rest[end]");
			
			maxflow += rest[end];
			
			if(rest[end] == 0){
				return maxflow;
			}
			
			for(int u=end;u!=start;u=pre[u]){	//从汇点往回走
				flow[pre[u]][u] += rest[end];	//更新正向流
				flow[u][pre[u]] -= rest[end];	//更新反向流
				System.out.print(u+" ");
			}
			
			System.out.print(start+"  ");
			
			System.out.println("寻找至本次的流为： "+maxflow);
		}
		
		
		
//		return maxflow;
	}
	
	//
	
}
