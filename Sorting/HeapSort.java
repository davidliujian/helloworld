package Sorting;

public class HeapSort {

	static int heapsize; // �Ѵ�С

	public static void heapsort(int A[], int n) {
		buildheap(A, n);
		for (int i = n - 1; i >= 1; i--) {
			Swap.swap(A, 0, i); // ���Ѷ�Ԫ��(��ǰ���ֵ)��ѵ����һ��Ԫ�ػ���(�ò������п��ܰѺ���Ԫ�ص��ȶ��Դ���,���Զ������ǲ��ȶ��������㷨)
			heapsize--; // �Ӷ���ȥ�����һ��Ԫ��
			heapify(A, 0); // ���µĶѶ�Ԫ�ؿ�ʼ���жѵ���
		}
	}

	public static void heapify(int A[], int i) // �ѵ�������(����ʹ�õ�������)
	{
		int leftchild = 2 * i + 1; // ��������
		int rightchild = 2 * i + 2; // �Һ�������
		int largest; // ѡ����ǰ��������Һ���֮�е����ֵ
		if (leftchild < heapsize && A[leftchild] > A[i])
			largest = leftchild;
		else
			largest = i;
		if (rightchild < heapsize && A[rightchild] > A[largest])
			largest = rightchild;
		
		if (largest != i) {
			Swap.swap(A, i, largest); // �ѵ�ǰ�����������(ֱ��)�ӽڵ���н���
			heapify(A, largest); // �ݹ���ã������ӵ�ǰ������½��жѵ���
		}
	}

	public static void buildheap(int A[], int n) // ���Ѻ���
	{
		heapsize = n;
		for (int i = heapsize / 2 - 1; i >= 0; i--) // ��ÿһ����Ҷ���
			heapify(A, i); // ���ϵĶѵ���
	}

}
