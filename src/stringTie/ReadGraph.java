package stringTie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ReadGraph {
	
	static Node nodes[];
	static float cap[][];
	static int start=0;
	static int VNUM=0;//节点数目
	static int end=0;
//	public static void  main(String args[]){
//		readFileByLines("C:\\Users\\David\\Desktop\\算法导论实验\\splicing_graphs\\splicing_graph1.rg");
////		for(int i =0 ;i<cap.length;i++){
////			for(int j =0 ;j<cap.length;j++){
////				System.out.print(cap[i][j]+"	");
////			}
////			System.out.println();
////		}
//		for(int j =0 ;j<nodes.length;j++){
//			System.out.print(nodes[j].getId()+"	");
//		}
//	}
	
	public static void readFileByLines(String fileName) {

		File file = new File(fileName);
		BufferedReader reader = null;
		try {
	//		System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			int index =0 ;	//Node数组的下标
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
			//	System.out.println("line " + line + ": " + tempString);
				String tempS = tempString.replace(" ", "").replace("	", "");
		//		System.out.println(tempS);
				char [] ch = tempS.toCharArray();
				
				if(line==2){
					
					for(int i=0;i<ch.length;i++){//获取节点数目
						if(ch[i]==','){
							VNUM++;
						}
					}
					VNUM++;		//节点的数目要比逗号的数目多一个
					nodes = new Node[VNUM];
					
					int de = tempS.indexOf(',');
					start = Integer.valueOf(tempS.substring(0,de));
	//				System.out.println(start);
					int dex = tempS.lastIndexOf(',');
					end = Integer.valueOf(tempS.substring(dex+1));
	//				System.out.println(VNUM);
					
					cap = new float[end+1][end+1];
				}
				
				
				if(tempString.startsWith("n") | tempString.startsWith("0n")){//声明节点
					int one = tempS.indexOf("=");
					int two =one+1+ tempS.substring(one+1).indexOf("=");
					int three =two+1+ tempS.substring(two+1).indexOf("=");
//					System.out.println(one);
//					System.out.println(two);
//
//					System.out.println(three);

					String fir= tempS.substring(one+1,two);
					String sec = tempS.substring(two+1, three);
					String third = tempS.substring(three+1);
					
					int end1 = 0;
					for(int i=0;i<fir.length();i++){
						if(((int)(fir.charAt(i)))>47 && ((int)fir.charAt(i))<58){
							continue;
						}else{
							end1 = i;
							break;
						}
					}
					int id = Integer.valueOf(fir.substring(0, end1));
					
					int end2 = 0;
					for(int i=0;i<sec.length();i++){
						if(((int)(sec.charAt(i)))>47 && ((int)sec.charAt(i))<58){
							continue;
						}else{
							end2 = i;
							break;
						}
					}
					int length = Integer.valueOf(sec.substring(0, end2));
					
					int end3 = 0;
					for(int i=0;i<third.length();i++){
						if((((int)(third.charAt(i)))>47 && ((int)third.charAt(i))<58) | third.charAt(i)=='.'){
							continue;
						}else{
							end3 = i;
							break;
						}
					}
					float cov = Float.valueOf(third.substring(0, end3));
					String sequence  = reader.readLine();
					line++;
//					System.out.println(id+"    "+length+"   "+cov+"   "+sequence);
					Node node =new Node(id, length, cov, sequence);
					nodes[index] = node;
					index++;
				}
				
				if(tempS.contains("->")){	//声明边
					findEdge(tempS);
				}

				line++;
			}

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
	
	public static void findEdge(String tempS){	//判断这一行是否只声明了一条边还是声明了多条边
		
		int quote = tempS.indexOf(',');
		if(quote==-1){	//这一行只有一条边
			int s = tempS.indexOf('-');
			int start_s = Integer.valueOf(tempS.substring(0, s));
			int ss = tempS.indexOf(':');
			int end_e = Integer.valueOf(tempS.substring(s+2, ss));
			int sss = tempS.indexOf('.')+3;
			float weight = Float.valueOf(tempS.substring(ss+1, sss));
			if(start_s==start || end_e ==end){
				weight = -1.00f;
			}
			cap[start_s][end_e] = weight;
		}else{
			int s = tempS.indexOf('-');
			int start_s = Integer.valueOf(tempS.substring(0, s));
			int ss = tempS.indexOf(':');
			int end_e = Integer.valueOf(tempS.substring(s+2, ss));
			int sss = tempS.indexOf('.')+3;
			float weight = Float.valueOf(tempS.substring(ss+1, sss));
			if(start_s==start || end_e ==end){
				weight = -1.00f;
			}
			cap[start][end_e] = weight;
			findEdge(tempS.substring(quote+1));
		}
	}
	
	public static void modify(float [][] cap,int start,int end){	//修改从源点出发的边以及到达汇点的边的权重
		LinkedList list =new LinkedList();
		
		for(int i=0;i<cap[start].length;i++){
			if(cap[start][i] ==-1){
				float sum=0;
				list.add(i);
				while(!list.isEmpty()){	//BFS求源点出发的边的权重
					int u = (int)list.pollFirst();
					for(int j=0;j<cap.length;j++){
						
						if(cap[u][j]!=0&&j!=end){
							sum+=cap[u][j];
							list.add(j);
						}
					}
					
				}
				if(sum!=0)
					cap[start][i] = sum;
				else{
					cap[start][i] = 1.001f;
				}
			}
		}
		
		for(int i=0;i<cap.length;i++){
			if(cap[i][end]==-1){
				float sum=0;
				list.add(i);
				while(!list.isEmpty()){	//BFS求源点出发的边的权重
					int v = (int)list.pollFirst();
					for(int j=0;j<cap.length;j++){
						
						if(cap[j][v]!=0&&j!=start){
							sum+=cap[j][v];
							list.add(j);
						}
					}
					
				}
				if(sum!=0)
					cap[i][end] = sum;
				else{
					cap[i][end] = 1.001f;
				}
			}
		}
	}
	
	
	
	static class Node{
		private int id;
		private int length;
		private float cov;
		private String sequence;
		public Node(int id ,int length,float cov,String sequence){
			this.id = id;
			this.length = length;
			this.cov = cov;
			this.sequence = sequence;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getLength() {
			return length;
		}
		public void setLength(int length) {
			this.length = length;
		}
		public float getCov() {
			return cov;
		}
		public void setCov(float cov) {
			this.cov = cov;
		}
		public String getSequence() {
			return sequence;
		}
		public void setSequence(String sequence) {
			this.sequence = sequence;
		}
		
	}
}
