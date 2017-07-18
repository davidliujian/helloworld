package Sorting;

public class QuickSort {

	public static void quickSort(int [] arr,int left,int right){
		int pivot_index;
		if(left<right){
			pivot_index = partition(arr,left,right);
			quickSort(arr,left,pivot_index-1);
			quickSort(arr,pivot_index+1,right);
		}
	}
	
	public static int  partition(int arr [],int left,int right){
		int pivot = arr[right];
		int tail = left-1;
		for(int i=left;i<right;i++){
			if(arr[i]<=pivot){
				tail++;
				if(tail!=i)
					Swap.swap(arr, tail, i);
			}
		}
		Swap.swap(arr, tail+1, right);
		
		return tail+1;
	}
}
