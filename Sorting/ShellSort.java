package Sorting;
/*
 * 希尔排序
 *　希尔排序是基于插入排序的以下两点性质而提出改进方法的：
 * 插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率
 * 但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位
 * 
 * 希尔排序通过将比较的全部元素分为几个区域来提升插入排序的性能。这样可以让一个元素可以一次性地朝最终位置前进一大步。
 * 然后算法再取越来越小的步长进行排序，算法的最后一步就是普通的插入排序，但是到了这步，需排序的数据几乎是已排好的了（此时插入排序较快）。
 */
public class ShellSort {

	public static void shellSort(int [] A){
		int n = A.length;
		int i, j, get;
	    int h = 0;
	    while (h <= n)                          // 生成初始增量
	    {
	        h = 3*h + 1;
	    }
	    while (h >= 1)
	    {
	        for (i = h; i < n; i++)
	        {
	            j = i - h;
	            get = A[i];
	            while ((j >= 0) && (A[j] > get))
	            {
	                A[j + h] = A[j];
	                j = j - h;
	            }
	            A[j + h] = get;
	            for(int x: A){
	            	System.out.print(x+"  ");
	            }
	            System.out.println();
	        }
	        h = (h - 1) / 3;                    // 递减增量
	    }
	}
}
