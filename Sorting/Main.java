package Sorting;

public class Main {

	public static void main(String args[]){
//		int aa [] = {3,4};
//		Swap.swap(aa,0,1);
//		System.out.println(aa[0]+"  "+aa[1]);
//		
		int a [] ={1,4,2,5,7,3,8,98,94,9,12,24};
		for(int i : a){
			System.out.print(i+"  ");
		}
		System.out.println();
		long start =  System.currentTimeMillis();
//		BubbleSort.bubbleSort(a);
//		SelectionSort.selectSort(a);
//		InsertSort.insertSort(a);
//		ShellSort.shellSort(a);
//		MergeSort.mergeSort(a, 0, a.length-1);
//		HeapSort.heapsort(a, a.length-1);
//		QuickSort.quickSort(a, 0, a.length-1);
		
//		CountingSort.countSort(a, 100);
		RadixSort.lsd_radixsort(a, 3);
		long stop =  System.currentTimeMillis();
		System.out.println(stop-start);
		for(int i : a){
			System.out.print(i+"  ");
		}
	}

}
