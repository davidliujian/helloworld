package stringTie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

import stringTie.ReadGraph.Node;

public class Edmonds_Karp {

	// static ReadGraph re = new ReadGraph();
	static Node[] nodes;
	static float[][] cap;// ����ͼ�ĳ�ʼ����
	static int Line=0;	//�ڼ���ͼ
	static int Counter=1;	//�ܵ�transcript������
	
	public static void main(String[] args) {
		// ����һ��ͼ
		readHeadFile("C:\\Users\\David\\Desktop\\�㷨����ʵ��\\splicing_graphs\\splicing_graph_name.list");
//			test1();
	}
	public static void test1(){
		ReadGraph.readFileByLines("C:\\Users\\David\\Desktop\\�㷨����ʵ��\\splicing_graphs\\splicing_graph1.rg");
		nodes = ReadGraph.nodes;
		cap = ReadGraph.cap;// ����ͼ�ĳ�ʼ����

		ReadGraph.modify(cap, ReadGraph.start, ReadGraph.end);
		for (int i = 0; i < cap.length; i++) {
			for (int j = 0; j < cap.length; j++) {
				System.out.print(cap[i][j] + "	");
			}
			System.out.println();
		}

		float max = EdmondsKarp(ReadGraph.start, ReadGraph.end, nodes, cap);
		System.out.print("���ASGͼ������·����cov�������ǣ�  ");
		System.out.println(max);
	}
	
	public static void readHeadFile(String fileName){
		File file = new File(fileName);
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
			while ((tempString = reader.readLine()) != null) {
				ReadGraph.readFileByLines("C:\\Users\\David\\Desktop\\�㷨����ʵ��\\splicing_graphs\\"+tempString+".rg");
				nodes = ReadGraph.nodes;
				cap = ReadGraph.cap;// ����ͼ�ĳ�ʼ����

				ReadGraph.modify(cap, ReadGraph.start, ReadGraph.end);
//				for (int i = 0; i < cap.length; i++) {
//					for (int j = 0; j < cap.length; j++) {
//						System.out.print(cap[i][j] + "	");
//					}
//					System.out.println();
//				}

				
				
				float max = EdmondsKarp(ReadGraph.start, ReadGraph.end, nodes, cap);
				Line++;
				System.out.print("���ASGͼ������·����cov�������ǣ�  ");
				System.out.println(max);

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
	
	public static float EdmondsKarp(int start, int end, Node[] nodess,
			float[][] cap) {
		float[][] flow = new float[cap.length][cap[0].length]; // ��¼����
		int[] pre = new int[cap.length]; // ��¼ǰһ���ڵ�
		float[] rest = new float[cap.length]; // ��¼ʣ�������������

		float maxflow = 0;
		int routerCun=1;
		for (int i = 0; i < flow.length; i++) {
			Arrays.fill(flow[i], 0);
		}

		Arrays.fill(pre, 0);

		LinkedList list = new LinkedList<Integer>();

		while (true) {
			// System.out.println("yici");
			for (int j = 0; j < rest.length; j++) {// ���ò�����Ϊ0
				rest[j] = 0;
			}
			rest[start] = 10000000; // ������ʼλ�õĲ���Ϊһ���ܴ��ֵ
			list.add(start);
			while (!list.isEmpty()) { // BFS Ѱ������·
			// System.out.println("yici");
				int u = (int) list.pollFirst();
				for (int v = 0; v <= end; v++) {
					if (rest[v] == 0 && cap[u][v] - flow[u][v] > 0) {
						pre[v] = u; // ����ǰ��Ľڵ�
						rest[v] = Math.min(rest[u], cap[u][v] - flow[u][v]);// ��ȡstart��v�ڵ����С����
						list.add(v);
					}
				}
			}
			// System.out.println(rest[end]+"rest[end]");

			

			if (rest[end] == 0) {
				return maxflow;
			}

			Stack stack = new Stack();
			for (int u = end; u != start; u = pre[u]) { // �ӻ��������
				flow[pre[u]][u] += rest[end]; // ����������
				flow[u][pre[u]] -= rest[end]; // ���·�����
				stack.add(u);
				// System.out.print(u+" ");
			}
			stack.add(start);
			
			
			int len = 0;
			String str = "";
			WriteTranscript.write(">trans"+Counter+"_sg"+Line+"_"+routerCun+"  ");
			while (!stack.isEmpty()) {
				int id = (int) stack.pop();
				len += getlen(id, nodess);
				str += getStr(id, nodess);
				System.out.print(id + "  ");
				
			}
			System.out.println();
			System.out.println("len = " + len);
			WriteTranscript.write("len = " + len+"  ");
			
			if(rest[end]==1.001f)
				rest[end]=0;
			System.out.println("cov = " + rest[end]);
			WriteTranscript.write("cov = " + rest[end]+"  ");
			
			System.out.println("sequence =");
			WriteTranscript.write("sequence ="+"\r\n");
			System.out.println(str.substring(5, str.length() - 3));
			WriteTranscript.write(str.substring(5, str.length() - 3)+"\r\n");
			
			routerCun++;
			Counter++;
			maxflow += rest[end];
		}
	}

	public static int getlen(int id, Node[] nodes) {
		int len = 0;
		for (int i = 0; i < nodes.length; i++) {
			// System.out.println(nodes[i].getId());
			if (nodes[i].getId() == id) {
				return nodes[i].getLength();
			}
		}
		return len;
	}

	public static String getStr(int id, Node[] nodes) {
		String str = "";
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i].getId() == id) {
				return nodes[i].getSequence();
			}
		}
		return str;
	}

}
