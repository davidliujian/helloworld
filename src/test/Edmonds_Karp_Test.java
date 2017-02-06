package test;

import java.util.Arrays;
import java.util.LinkedList;

public class Edmonds_Karp_Test {

	public static void main(String[] args) {
		//����һ��ͼ
		int [][] cap ={  {0,5,0,0,0},
					  	{0,0,6,0,1},
				        {0,0,0,5,2},
				        {0,0,0,0,1},
				        {0,0,0,0,0},
				        };//����ͼ�ĳ�ʼ����
		int max=EdmondsKarp(0, 4, cap);
		System.out.println(max);
		
	}
	public static int EdmondsKarp(int start,int end,int[][] cap){
		int [][] flow =new int [cap.length][cap[0].length];	//��¼����
		int [] pre = new int[cap.length];	//��¼ǰһ���ڵ�
		int [] rest = new int[cap.length];	//��¼ʣ�������������
		
		int maxflow = 0;
		
		for(int i=0;i<flow.length;i++){
			Arrays.fill(flow[i], 0);		
		}
		
		Arrays.fill(pre, 0);	
		
		LinkedList list = new LinkedList<Integer>();
		
		while(true){
//			System.out.println("yici");
			for(int j =0 ;j<rest.length;j++){//���ò�����Ϊ0
				rest[j] =0;
			}
			rest[start] = 10000000;	//������ʼλ�õĲ���Ϊһ���ܴ��ֵ
			list.add(start);
			while(!list.isEmpty()){			//BFS Ѱ������·
//				System.out.println("yici");
				int u = (int)list.pollFirst();
				for(int v =0;v<=end;v++){
					if(rest[v]==0 && cap[u][v]-flow[u][v] > 0){
						pre[v] =u;		//����ǰ��Ľڵ�
						rest[v] = Math.min(rest[u], cap[u][v]-flow[u][v]);//��ȡstart��v�ڵ����С����
						list.add(v);
					}
				}
			}
//			System.out.println(rest[end]+"rest[end]");
			
			maxflow += rest[end];
			
			if(rest[end] == 0){
				return maxflow;
			}
			
			for(int u=end;u!=start;u=pre[u]){	//�ӻ��������
				flow[pre[u]][u] += rest[end];	//����������
				flow[u][pre[u]] -= rest[end];	//���·�����
				System.out.print(u+" ");
			}
			
			System.out.print(start+"  ");
			
			System.out.println("Ѱ�������ε���Ϊ�� "+maxflow);
		}
		
		
		
//		return maxflow;
	}
	
	//
	
}
