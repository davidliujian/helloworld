package Sorting;

public class HeapSort {

	static int heapsize; // 堆大小

	public static void heapsort(int A[], int n) {
		buildheap(A, n);
		for (int i = n - 1; i >= 1; i--) {
			Swap.swap(A, 0, i); // 将堆顶元素(当前最大值)与堆的最后一个元素互换(该操作很有可能把后面元素的稳定性打乱,所以堆排序是不稳定的排序算法)
			heapsize--; // 从堆中去掉最后一个元素
			heapify(A, 0); // 从新的堆顶元素开始进行堆调整
		}
	}

	public static void heapify(int A[], int i) // 堆调整函数(这里使用的是最大堆)
	{
		int leftchild = 2 * i + 1; // 左孩子索引
		int rightchild = 2 * i + 2; // 右孩子索引
		int largest; // 选出当前结点与左右孩子之中的最大值
		if (leftchild < heapsize && A[leftchild] > A[i])
			largest = leftchild;
		else
			largest = i;
		if (rightchild < heapsize && A[rightchild] > A[largest])
			largest = rightchild;
		
		if (largest != i) {
			Swap.swap(A, i, largest); // 把当前结点和它的最大(直接)子节点进行交换
			heapify(A, largest); // 递归调用，继续从当前结点向下进行堆调整
		}
	}

	public static void buildheap(int A[], int n) // 建堆函数
	{
		heapsize = n;
		for (int i = heapsize / 2 - 1; i >= 0; i--) // 对每一个非叶结点
			heapify(A, i); // 不断的堆调整
	}

}
