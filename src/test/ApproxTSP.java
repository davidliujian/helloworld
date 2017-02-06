package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//欧式空间上旅行售货员问题 (TSP问题) 的2-近似算法
/*
 (1)选择g的任一顶点r；
 (2)用Prim算法找出带权图g的一棵以r为根的最小生成树T；
 (3)前序遍历树T得到的顶点表L；
 (4)将r加到表L的末尾，按表L中顶点次序组成回路H，作为计算结果返回；
 */
public class ApproxTSP {

	static int M = 100000;
	static int VNUM = 5; // 这里没有ID为0的点,so id号范围从1开始
/*
	static int edge[][] = { { 0, 0, 0, 0, 0, 0, 0, 0 },
							{ 0, M, 6, M, 5, M, M, M }, 
							{ 0, 6, M, 8, 9, 7, M, M },
							{ 0, M, 8, M, M, 5, M, M },
							{ 0, 5, 9, M, M, 15, 7, M },
							{ 0, M, 7, 5, 15, M, 8, 9 },
							{ 0, M, M, M, 7, 8, M, 11 },
							{ 0, M, M, M, M, 9, 11, M } };/* 输入的邻接矩阵 */

	static int edge[][] = { { 0, 0, 0, 0, 0 },
		{ 0, M,2,4,4}, 
		{ 0, 2,M,4,3 },
		{ 0, 4,4,M,4 },
		{ 0, 4, 3, 4, M } };/* 输入的邻接矩阵 */
	
	public static void prim(int start) {
		int lowcost[] = new int[VNUM]; // 记录Vnew中每个点到V中邻接点的最短边
		int addvnew[] = new int[VNUM]; // 标记某点是否加入Vnew
		int adjecent[] = new int[VNUM]; // 记录V中与Vnew最邻近的点

		int sumweight = 0;
		int i, j, k = 0;
		int seq =1;

//		Arrays.fill(lowcost, 0);
//		Arrays.fill(addvnew, 0);
		Arrays.fill(adjecent,start);

		for (i = 1; i < VNUM; i++) // 顶点是从1开始
		{
			lowcost[i] = edge[start][i];
			addvnew[i] = -1; // 将所有点至于Vnew之外,V之内，这里只要对应的为-1，就表示在Vnew之外
		}

		addvnew[start] = 0; // 将起始点start加入Vnew
		adjecent[start] = start;

		for (i = 1; i < VNUM - 1; i++) {
			int min = M;
			int v = -1;
			for (j = 1; j < VNUM; j++) {
				if (addvnew[j] == -1 && lowcost[j] < min) // 在Vnew之外寻找最短路径
				{
	//				System.out.println(j);
					min = lowcost[j];
					v = j;
				}
			}
			if (v != -1) {
				System.out.println(adjecent[v] + " " + v + " " + lowcost[v]);

				addvnew[v] = seq++;				 // 将v加Vnew中

				sumweight += lowcost[v];	 // 计算路径长度之和
				for (j = 1; j < VNUM; j++) {
					if (addvnew[j] == -1 && edge[v][j] < lowcost[j]) {
						lowcost[j] = edge[v][j]; // 此时v点加入Vnew 需要更新lowcost
						adjecent[j] = v;
					}
				}
			}
		}
		System.out.println("the minmum weight is" + sumweight);
		
		for (i = 1; i < VNUM ; i++) {
			System.out.print(adjecent[i]+" ");
		}
		System.out.println();
/*		
		List list = new ArrayList();
		for(int pp=1;pp<adjecent.length;pp++){
			list.add(adjecent[pp]);
		}
		*/
/*		
		Iterator it =list.iterator();
		while(it.hasNext()){
			System.out.print(it.next()+" ");
		}
*/
//		System.out.println(list.indexOf(5)+1);
		
		System.out.println("");
		find(start,adjecent);
//		System.out.println(start);
	}
	
	/*
	 * 不断根据找到的元素下标作为元素值，就能沿着这棵树向下找，同时递归实现回溯，实现前序遍历
	 */
	public static void find(int start , int [] array){
		
		ArrayList li = nextNum(start, array);
		
		Iterator it =li.iterator();
		
		if(!li.isEmpty()){
			while(it.hasNext()){
				int x = (int)it.next();
				System.out.print(x);
				if(x!=start)
					find(x,array);
			}
			
		}
	}
	
	/*
	 * 因为在adjecent数组里存储的值与其下标之间有联系，这种联系就是，此元素的下标就是此元素的下一个相邻的节点
	 * 根据元素值寻找其在数组中对应的下标，并将对应的下标保存在list里
	 * */
	public static ArrayList nextNum(int num,int [] arr){
		ArrayList li = new ArrayList();
		for(int q = 1;q<arr.length;q++){
			if(num == arr[q]){
				li.add(q);
			}
		}
		
		return li;
	}	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		prim(1);
	}

	

}

/*
static int edge[][] = { { 0, 0, 0, 0, 0 },
	{ 0, M,2,4,4}, 
	{ 0, 2,M,4,3 },
	{ 0, 4,4,M,4 },
	{ 0, 4, 3, 4, M } };/* 输入的邻接矩阵 */
