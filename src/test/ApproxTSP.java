package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//ŷʽ�ռ��������ۻ�Ա���� (TSP����) ��2-�����㷨
/*
 (1)ѡ��g����һ����r��
 (2)��Prim�㷨�ҳ���Ȩͼg��һ����rΪ������С������T��
 (3)ǰ�������T�õ��Ķ����L��
 (4)��r�ӵ���L��ĩβ������L�ж��������ɻ�·H����Ϊ���������أ�
 */
public class ApproxTSP {

	static int M = 100000;
	static int VNUM = 5; // ����û��IDΪ0�ĵ�,so id�ŷ�Χ��1��ʼ
/*
	static int edge[][] = { { 0, 0, 0, 0, 0, 0, 0, 0 },
							{ 0, M, 6, M, 5, M, M, M }, 
							{ 0, 6, M, 8, 9, 7, M, M },
							{ 0, M, 8, M, M, 5, M, M },
							{ 0, 5, 9, M, M, 15, 7, M },
							{ 0, M, 7, 5, 15, M, 8, 9 },
							{ 0, M, M, M, 7, 8, M, 11 },
							{ 0, M, M, M, M, 9, 11, M } };/* ������ڽӾ��� */

	static int edge[][] = { { 0, 0, 0, 0, 0 },
		{ 0, M,2,4,4}, 
		{ 0, 2,M,4,3 },
		{ 0, 4,4,M,4 },
		{ 0, 4, 3, 4, M } };/* ������ڽӾ��� */
	
	public static void prim(int start) {
		int lowcost[] = new int[VNUM]; // ��¼Vnew��ÿ���㵽V���ڽӵ����̱�
		int addvnew[] = new int[VNUM]; // ���ĳ���Ƿ����Vnew
		int adjecent[] = new int[VNUM]; // ��¼V����Vnew���ڽ��ĵ�

		int sumweight = 0;
		int i, j, k = 0;
		int seq =1;

//		Arrays.fill(lowcost, 0);
//		Arrays.fill(addvnew, 0);
		Arrays.fill(adjecent,start);

		for (i = 1; i < VNUM; i++) // �����Ǵ�1��ʼ
		{
			lowcost[i] = edge[start][i];
			addvnew[i] = -1; // �����е�����Vnew֮��,V֮�ڣ�����ֻҪ��Ӧ��Ϊ-1���ͱ�ʾ��Vnew֮��
		}

		addvnew[start] = 0; // ����ʼ��start����Vnew
		adjecent[start] = start;

		for (i = 1; i < VNUM - 1; i++) {
			int min = M;
			int v = -1;
			for (j = 1; j < VNUM; j++) {
				if (addvnew[j] == -1 && lowcost[j] < min) // ��Vnew֮��Ѱ�����·��
				{
	//				System.out.println(j);
					min = lowcost[j];
					v = j;
				}
			}
			if (v != -1) {
				System.out.println(adjecent[v] + " " + v + " " + lowcost[v]);

				addvnew[v] = seq++;				 // ��v��Vnew��

				sumweight += lowcost[v];	 // ����·������֮��
				for (j = 1; j < VNUM; j++) {
					if (addvnew[j] == -1 && edge[v][j] < lowcost[j]) {
						lowcost[j] = edge[v][j]; // ��ʱv�����Vnew ��Ҫ����lowcost
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
	 * ���ϸ����ҵ���Ԫ���±���ΪԪ��ֵ��������������������ң�ͬʱ�ݹ�ʵ�ֻ��ݣ�ʵ��ǰ�����
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
	 * ��Ϊ��adjecent������洢��ֵ�����±�֮������ϵ��������ϵ���ǣ���Ԫ�ص��±���Ǵ�Ԫ�ص���һ�����ڵĽڵ�
	 * ����Ԫ��ֵѰ�����������ж�Ӧ���±꣬������Ӧ���±걣����list��
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
	{ 0, 4, 3, 4, M } };/* ������ڽӾ��� */
