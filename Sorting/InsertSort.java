package Sorting;

public class InsertSort {
	
	public static void insertSort(int arr[]){
		int temp = arr[0];
		int j = 0;
		for(int i=1;i<arr.length;i++){
			temp = arr[i];
			j = i-1;
			while(temp<arr[j] && j>0){
				arr [j+1] = arr[j];
				j--;
			}
			arr[j+1] = temp;
//			for(int j = i-1;j>= 0;j--){
//				if(temp<arr[j]){
//					arr[j+1]= arr[j];
//				}else{
//					arr[j+1] = temp;
//					break;
//				}
//			}
		}
	}
}
