package Sorting;

public class SelectionSort {

	public static void selectSort(int [] arr){
		int min = 0;
		for(int j = 0;j<arr.length-1;j++){
			min = j;
			for(int i=j+1;i<arr.length;i++){
				if(arr[i]<arr[min])
					min = i;
			}
			if(min != j)
				Swap.swap(arr,j,min);
		}
	}
}
