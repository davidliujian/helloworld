package Sorting;

public class Swap {

	public static void swap(int arr [] ,int i ,int j){
		int temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
	}
}
