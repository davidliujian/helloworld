package Sorting;
/*
 * 基数排序
 */
public class RadixSort {
 
	static int radix=10;
	
	public  static int getDigit(int x,int d){
		int radix[]={1,1,10,100};
		return (x/radix[d])%10;
	}
	
	public static void countingsort(int A[], int n, int B[], int d)// 应用计数排序(依据元素的第d位数字)对元素排序
	{
		int C[] = new int[radix];
	    for (int i = 0; i < radix; i++)    // 初始化,将数组C中的元素置0
	    {
	        C[i] = 0;
	    }
	    for (int i = 0; i < n; i++)        // 使C[i]保存着当前位等于i的元素个数
	    {
	        C[getDigit(A[i], d)]++;
	    }
	    for (int i = 1; i < radix; i++)    // 使C[i]保存着当前位小于等于i的元素个数,排序后元素i就放在第C[i]个输出位置上
	    {
	        C[i] = C[i] + C[i - 1];
	    }
	    for (int i = n - 1; i >= 0; i--)   // 从后向前扫描保证计数排序的稳定性(重复元素相对次序不变)
	    {
	        int j = getDigit(A[i], d);     // 元素A[i]当前位数字为j   
	        B[C[j] - 1] = A[i];            // 根据当前位数字,把每个元素A[i]放到它在输出数组B中的正确位置上
	        C[j]--;                        // 当再遇到当前位数字同为j的元素时,会将其放在当前元素的前一个位置上保证计数排序的稳定性
	    }
	}
	
	public static void lsd_radixsort(int A[],int dn)         // 最低位优先基数排序,dn是数字位数
	{
		int n = A.length;
	    int B [] =new int[n];// 分配临时空间,长度为n,用来暂存中间数据
	    for (int d = 1; d <= dn; d++)          // 从最低位开始到最高位
	    {
	        countingsort(A, n, B, d);          // 依据第d位调用计数排序
	        for (int i = 0; i < n; i++)        // 把临时空间B中的数据拷贝回A,针对更高位的计数排序在此基础上继续对元素排序
	        {
	            A[i] = B[i];
	        }
	    }
	}

}
