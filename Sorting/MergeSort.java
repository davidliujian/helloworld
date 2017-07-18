package Sorting;

public class MergeSort {

	public static void mergeSort(int arr [],int left ,int right){
		int middle = (left+right)/2;
		if(left < right){
			mergeSort(arr,left,middle);
			mergeSort(arr,middle+1,right);
			merge(arr,left,middle,right);
		}
	}
	
	public static void merge(int arr[] , int left,int middle ,int right ){
		int L[] = new int[middle-left+2];
		int R[] = new int[right-middle+1];
		for(int i =0;i<middle-left+1;i++)
			L[i] = arr[left+i];
		for(int j=0;j<right-middle;j++)
			R[j] = arr[middle+j+1];
		L[middle-left+1] = Integer.MAX_VALUE;	//使用无穷大作为哨兵值放在子数组的末尾
		R[right-middle] = Integer.MAX_VALUE;   // 这样可以免去检查某个子数组是否已读完的步骤
		int i=0;
		int j = 0;
		for(int k = left;k<=right;k++){
			if(L[i]<=R[j]){
				arr[k] = L[i];
				i++;
			}else{
				arr[k] = R[j];
				j++;
			}
		}
	}
	
}
