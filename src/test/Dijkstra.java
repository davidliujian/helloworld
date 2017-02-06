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
							{ 0, M, M, M, M, 9, 11, M } };/* ������ڽӾ��� */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readFileByLines("C:\\Users\\David\\Desktop\\�㷨����ʵ��\\dijkstra�����ļ�.txt");
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
			System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
			while ((tempString = reader.readLine()) != null) {
				// ��ʾ�к�
				System.out.println("line " + line + ": " + tempString);
				line++;
				
				char [] ch = tempString.toCharArray();
				
				if(!tempString.startsWith("*")){
					if(tempString.charAt(1)!='-'){	//����ǽڵ�һ��
						int VNUM = (ch.length+1)/2;	//��ȡ�ڵ���Ŀ
						edge= new int[VNUM][VNUM];	//�����ڽӾ������飬��ʼ��Ϊ0
					}else{	//�������ߵ�һ��
						
				//		System.out.println((int)ch[5]);
						edge[(int)(ch[0])-65][(int)(ch[3])-65] = (int)ch[5]-48;	//��ȡ���Ĵ�д��ĸת��Ϊ���֣�
														//Ȼ���ȥ'A'��ASCII�룬��ABC Dת��Ϊ��������	..�õ���ch[5]��'1',ASCIIֵ��49
					}
				}
		
			}
			/*************************************************/
			//��ֵΪ0�ı��һ���ܴ��ֵM
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
		//��char�ĳ�int
		int a = (int)A-65;
		int b = (int)B-65;
		
		//����3������,visitedͬ�����ÿ�������Ƿ��ҵ����·����dist�������ʣ��Ķ���ĵ�ǰ���ֵ����·��ֵ����û���ҵ�ȷ�����·���Ķ��㣩��path[i]�������i����һ���ڵ�
		boolean [] visited = new boolean[ed.length];
//		dist =new int[ed.length];
//		path = new int[ed.length];
		
		//��ʼ��
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
			
			//���ҵ���ǰdistֵ��С�Ľڵ�
			int min = M;
			int index=0;
			for(int j=0;j<dist.length;j++){
				if(!visited[j] && dist[j]<min){//����ڵ�û�б����ʹ������ҽڵ��ֵС�ڵ�ǰ����Сֵ
					min = dist[j];
					index = j;
				}
			}
			
			//����dist�����path
			visited[index] = true;
			for(int k=0;k<ed[index].length;k++){
				if(!visited[k] && ed[index][k]<M && index!=k && dist[index]+ed[index][k] < dist[k]){
					dist[k] = dist[index]+ed[index][k];		//����dist
					path[k] = index;				//����path
				}
			}
			
		}
		
		return dist[b];
	}

	public static void showPath(char A ,char B ,int path[]){
		//��char�ĳ�int
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
