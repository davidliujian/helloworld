package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Dijkstra {

	static int M = 100000;
	static int edge[][] = new int[3][3];

	/*{ { 0, 0, 0, 0, 0, 0, 0, 0 },
							{ 0, M, 6, M, 5, M, M, M }, 
							{ 0, 6, M, 8, 9, 7, M, M },
							{ 0, M, 8, M, M, 5, M, M },
							{ 0, 5, 9, M, M, 15, 7, M },
							{ 0, M, 7, 5, 15, M, 8, 9 },
							{ 0, M, M, M, 7, 8, M, 11 },
							{ 0, M, M, M, M, 9, 11, M } };/* 输入的邻接矩阵 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readFileByLines("C:\\Users\\David\\Desktop\\算法导论实验\\dijkstra输入文件.txt");
		int [] dist =new int[edge.length] ;
		int [] path =new int[edge.length] ;
		System.out.println(djsktra('A','H', edge, dist, path));
		showPath('A', 'H', path);
		
//		System.out.println(edge[4][1]);
/*		for(int i=0;i< edge.length;i++){
			for(int j=0;j<edge[i].length;j++){
				System.out.print(edge[i][j]+" ");
			}
			System.out.println();
		}
	*/
	}

	public static void readFileByLines(String fileName) {

		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				line++;
				
				char [] ch = tempString.toCharArray();
				
				if(!tempString.startsWith("*")){
					if(tempString.charAt(1)!='-'){	//获得是节点一行
						int VNUM = (ch.length+1)/2;	//获取节点数目
						edge= new int[VNUM][VNUM];	//建立邻接矩阵数组，初始化为0
					}else{	//获得有向边的一行
						
				//		System.out.println((int)ch[5]);
						edge[(int)(ch[0])-65][(int)(ch[3])-65] = (int)ch[5]-48;	//把取到的大写字母转化为数字，
														//然后减去'A'的ASCII码，将ABC D转化为０１２３	..得到的ch[5]是'1',ASCII值是49
					}
				}
		
			}
			/*************************************************/
			//将值为0的变成一个很大的值M
			for(int i=0;i< edge.length;i++){
				for(int j=0;j<edge[i].length;j++){
					if(edge[i][j] == 0)
						edge[i][j] = M;
				}
			}
			/************************************************/
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}
	
	public static int djsktra(char A,char B,int [][] ed,int dist [],int path[]){
		//将char改成int
		int a = (int)A-65;
		int b = (int)B-65;
		
		//建立3个数组,visited同来标记每个顶点是否被找到最短路径，dist用来存放剩余的顶点的当前发现的最短路径值（即没有找到确定最短路径的顶点），path[i]用来存放i的上一个节点
		boolean [] visited = new boolean[ed.length];
//		dist =new int[ed.length];
//		path = new int[ed.length];
		
		//初始化
		for(int i =0 ;i<ed[a].length;i++){
			if(ed[a][i] < M && i!=a){	
				dist[i] = ed[a][i];
				path[i] = a;
			}else{
				dist[i] = M;
				path[i] = -1;
			}
			visited[i] = false;
			dist[a] = 0;
			path[a] = a;
		}
		visited[a] = true;
		
		for(int i = 1;i<ed[a].length ;i++){
			
			//先找到当前dist值最小的节点
			int min = M;
			int index=0;
			for(int j=0;j<dist.length;j++){
				if(!visited[j] && dist[j]<min){//如果节点没有被访问过，并且节点的值小于当前的最小值
					min = dist[j];
					index = j;
				}
			}
			
			//更新dist数组和path
			visited[index] = true;
			for(int k=0;k<ed[index].length;k++){
				if(!visited[k] && ed[index][k]<M && index!=k && dist[index]+ed[index][k] < dist[k]){
					dist[k] = dist[index]+ed[index][k];		//更新dist
					path[k] = index;				//更新path
				}
			}
			
		}
		
		return dist[b];
	}

	public static void showPath(char A ,char B ,int path[]){
		//将char改成int
		int a = (int)A-65;
		int b = (int)B-65;
				
		
		Stack<Integer> stack = new Stack<Integer>();
		int u =b;
		while(b!=a){
			stack.push(b);
			b = path[b];
		}
		stack.push(a);
		while(!stack.isEmpty()){
			int pp = stack.pop();
			char ppp =(char) (pp+65); 
			System.out.print(ppp+" ");
		}
	}
}
