package Sorting;
/*
 * ��������
 */
public class RadixSort {
 
	static int radix=10;
	
	public  static int getDigit(int x,int d){
		int radix[]={1,1,10,100};
		return (x/radix[d])%10;
	}
	
	public static void countingsort(int A[], int n, int B[], int d)// Ӧ�ü�������(����Ԫ�صĵ�dλ����)��Ԫ������
	{
		int C[] = new int[radix];
	    for (int i = 0; i < radix; i++)    // ��ʼ��,������C�е�Ԫ����0
	    {
	        C[i] = 0;
	    }
	    for (int i = 0; i < n; i++)        // ʹC[i]�����ŵ�ǰλ����i��Ԫ�ظ���
	    {
	        C[getDigit(A[i], d)]++;
	    }
	    for (int i = 1; i < radix; i++)    // ʹC[i]�����ŵ�ǰλС�ڵ���i��Ԫ�ظ���,�����Ԫ��i�ͷ��ڵ�C[i]�����λ����
	    {
	        C[i] = C[i] + C[i - 1];
	    }
	    for (int i = n - 1; i >= 0; i--)   // �Ӻ���ǰɨ�豣֤����������ȶ���(�ظ�Ԫ����Դ��򲻱�)
	    {
	        int j = getDigit(A[i], d);     // Ԫ��A[i]��ǰλ����Ϊj   
	        B[C[j] - 1] = A[i];            // ���ݵ�ǰλ����,��ÿ��Ԫ��A[i]�ŵ������������B�е���ȷλ����
	        C[j]--;                        // ����������ǰλ����ͬΪj��Ԫ��ʱ,�Ὣ����ڵ�ǰԪ�ص�ǰһ��λ���ϱ�֤����������ȶ���
	    }
	}
	
	public static void lsd_radixsort(int A[],int dn)         // ���λ���Ȼ�������,dn������λ��
	{
		int n = A.length;
	    int B [] =new int[n];// ������ʱ�ռ�,����Ϊn,�����ݴ��м�����
	    for (int d = 1; d <= dn; d++)          // �����λ��ʼ�����λ
	    {
	        countingsort(A, n, B, d);          // ���ݵ�dλ���ü�������
	        for (int i = 0; i < n; i++)        // ����ʱ�ռ�B�е����ݿ�����A,��Ը���λ�ļ��������ڴ˻����ϼ�����Ԫ������
	        {
	            A[i] = B[i];
	        }
	    }
	}

}
