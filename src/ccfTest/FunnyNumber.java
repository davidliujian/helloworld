package ccfTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/*
 * 我们把一个数称为有趣的，当且仅当：
1. 它的数字只包含0, 1, 2, 3，且这四个数字都出现过至少一次。
2. 所有的0都出现在所有的1之前，而所有的2都出现在所有的3之前。
3. 最高位数字不为0。
因此，符合我们定义的最小的有趣的数是2013。除此以外，4位的有趣的数还有两个：2031和2301。
请计算恰好有n位的有趣的数的个数。由于答案可能非常大，只需要输出答案除以1000000007的余数。
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
