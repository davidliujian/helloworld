package ccfTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/*
 * ���ǰ�һ������Ϊ��Ȥ�ģ����ҽ�����
1. ��������ֻ����0, 1, 2, 3�������ĸ����ֶ����ֹ�����һ�Ρ�
2. ���е�0�����������е�1֮ǰ�������е�2�����������е�3֮ǰ��
3. ���λ���ֲ�Ϊ0��
��ˣ��������Ƕ������С����Ȥ������2013���������⣬4λ����Ȥ��������������2031��2301��
�����ǡ����nλ����Ȥ�����ĸ��������ڴ𰸿��ܷǳ���ֻ��Ҫ����𰸳���1000000007��������
 */
public class FunnyNumber {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int nums = 0;
		
		ArrayList<String> list = new ArrayList<String>();
		geneStr(list, "2", "", n);
//		System.out.println(list.size());
//		int start = (int) (2*Math.pow(10, n-1));
//		int end =  (int) (3*Math.pow(10, n-1));
//		for(;start<end;start++){
		Iterator it = list.iterator();
		while(it.hasNext()){
			String s = (String) it.next();
			
			if(s.contains("0")&&s.contains("1")&&s.contains("2")&&s.contains("3")){
				int ocu0 = s.lastIndexOf('0');
				int ocu1 = s.indexOf('1');
				int ocu2 = s.lastIndexOf('2');
				int ocu3 = s.indexOf('3');
				if(ocu0<ocu1 && ocu2<ocu3){
			//		System.out.println(s);
					nums++;
				}
			}
			
		}
		System.out.println(nums%1000000007);

	}
	
	public static void geneStr(ArrayList<String> list,String s,String x,int n){
		if(n==1){
			list.add(s+x);
			return ;
		}
		
			geneStr(list,s+x,"0",n-1);
			geneStr(list,s+x,"1",n-1);
			geneStr(list,s+x,"2",n-1);
			geneStr(list,s+x,"3",n-1);
		
	}
	
//	public static boolean isValid(String s,int n){
//		if(s.contains("0")&&s.contains("1")&&s.contains("2")&&s.contains("3"))
//			return true;
//		return false;
//	}

}
