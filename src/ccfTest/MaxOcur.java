package ccfTest;

import java.util.ArrayList;
import java.util.Scanner;

public class MaxOcur {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int [] nums =new int[n];
		for(int i=0;i<n;i++){
			nums[i] = scan.nextInt();
			
		}
		
		int [] index = new int[n];
		int max =0;
		for(int i=0;i<n;i++){
			index[i]=1;
			for(int j=0;j<i;j++){
				if(nums[i] == nums[j]){
					index[i]++;
				}
			}
			if(index[i]>max){
				max=index[i];				
			}
		}
		
		int min=10001;
		for(int i=0;i<n;i++){
			if(index[i] == max && nums[i]<min ){
				min =nums[i];
			}
		}
		
		System.out.println(min);
	}

}
