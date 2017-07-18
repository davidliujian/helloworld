package Sorting;

import java.util.Arrays;

/*
 * 计数排序
 * 1.统计数组A中每个值A[i]出现的次数，存入C[A[i]]
 * 2.从前向后，使数组C中的每个值等于其与前一项相加，这样数组C[A[i]]就代表了数组A中小于等于A[i]的元素个数
 * 3.反向填充目标数组B：将数组元素A[i]放在数组B的第C[A[i]]项（即B[C[A[i]] - 1]），每放一个元素就将C[A[i]]递减
 
 */
public class CountingSort {

	public static void countSort(int [] arr,int jishu){
		
		int c[] = new int[jishu];
		Arrays.fill(c, 0);	// 初始化,将数组C中的元素置0(此步骤可省略,整型数组元素默认值为0)
		for(int x : arr)	// 使C[i]保存着等于i的元素个数
			c[x]++;
		for(int i=1;i<jishu;i++){	// 使C[i]保存着小于等于i的元素个数,排序后元素i就放在第C[i]个输出位置上
			c[i] = c[i] + c[i-1];
		}
		int b[] = new int[arr.length];
		for(int j=arr.length-1;j>=0;j--){	// 从后向前扫描保证计数排序的稳定性(重复元素相对次序不变)
			b[c[arr[j]]-1] = arr[j];			 // 把每个元素A[i]放到它在输出数组B中的正确位置上
			c[arr[j]]--;					// 当再遇到重复元素时会被放在当前元素的前一个位置上保证计数排序的稳定性
		}
		for(int k=0;k<arr.length;k++){
			arr[k] = b[k];
		}
		
	}
}
