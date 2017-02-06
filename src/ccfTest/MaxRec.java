package ccfTest;

import java.util.Scanner;

public class MaxRec {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n =scan.nextInt();
		int h[] = new int[n];
		int max=0;
		for(int i=0;i<n;i++){
			h[i]=scan.nextInt();
			if(h[i]>max)
				max=h[i];
		}

		int maxRec=0;
		int num=0;
		for(int i=1;i<=max;i++){
			num=0;
			for(int j =0;j<n;j++){
				if(h[j]>=i){
					num++;
					if(i*num>maxRec)
						maxRec=i*num;
				}else{
					num=0;
				}
				
				
			}
		}
		System.out.println(maxRec);
	}

}
