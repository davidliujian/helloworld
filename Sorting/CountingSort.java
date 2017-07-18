package Sorting;

import java.util.Arrays;

/*
 * ��������
 * 1.ͳ������A��ÿ��ֵA[i]���ֵĴ���������C[A[i]]
 * 2.��ǰ���ʹ����C�е�ÿ��ֵ��������ǰһ����ӣ���������C[A[i]]�ʹ���������A��С�ڵ���A[i]��Ԫ�ظ���
 * 3.�������Ŀ������B��������Ԫ��A[i]��������B�ĵ�C[A[i]]���B[C[A[i]] - 1]����ÿ��һ��Ԫ�ؾͽ�C[A[i]]�ݼ�
 
 */
public class CountingSort {

	public static void countSort(int [] arr,int jishu){
		
		int c[] = new int[jishu];
		Arrays.fill(c, 0);	// ��ʼ��,������C�е�Ԫ����0(�˲����ʡ��,��������Ԫ��Ĭ��ֵΪ0)
		for(int x : arr)	// ʹC[i]�����ŵ���i��Ԫ�ظ���
			c[x]++;
		for(int i=1;i<jishu;i++){	// ʹC[i]������С�ڵ���i��Ԫ�ظ���,�����Ԫ��i�ͷ��ڵ�C[i]�����λ����
			c[i] = c[i] + c[i-1];
		}
		int b[] = new int[arr.length];
		for(int j=arr.length-1;j>=0;j--){	// �Ӻ���ǰɨ�豣֤����������ȶ���(�ظ�Ԫ����Դ��򲻱�)
			b[c[arr[j]]-1] = arr[j];			 // ��ÿ��Ԫ��A[i]�ŵ������������B�е���ȷλ����
			c[arr[j]]--;					// ���������ظ�Ԫ��ʱ�ᱻ���ڵ�ǰԪ�ص�ǰһ��λ���ϱ�֤����������ȶ���
		}
		for(int k=0;k<arr.length;k++){
			arr[k] = b[k];
		}
		
	}
}
