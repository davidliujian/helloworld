package Sorting;
//O(n^2)
public class BubbleSort {
	
	public static void bubbleSort(int [] arr){
		boolean flag =true;
		for(int j=arr.length-1;j>0;j--){
			if(flag==false) return;
			flag = false;
			for(int i=0;i<arr.length-2;i++){
				if(arr[i]>arr[i+1]){
					Swap.swap(arr,i,i+1);
					flag = true;
				}
			}
		}
	}
	

}
