package ccfTest;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ISBN {

	public static void main(String[] args) {
		Pattern p = Pattern.compile("[0-9]-[0-9]{3}-[0-9]{5}-[0-9]");
		Scanner scan = new Scanner(System.in);
		
		String str = scan.nextLine();
		Matcher m=p.matcher(str);
		int sum=0;
		int mul =1;
		if(m.matches()){
			for(int i=0;i<11;i++){
				if(str.charAt(i)!='-'){
					int x= Integer.parseInt(str.charAt(i)+"");
					sum+=x*mul;
					mul++;
				}
			}
			
			int mod = (sum%11);
//			System.out.println(mod);
			String end = str.charAt(12)+"";
			String mo= mod+"";
			if(mod!=10){
				if(mo.equals(end) )
					System.out.println("Right");
				else{
					String nstr = str.substring(0, 12)+mod;
					System.out.println(nstr);
				}
			}else if(mo.equals(10+"")){
				if(str.charAt(12) == 'X'){
					System.out.println("Right");
				}else{
					String nstr = str.substring(0, 12)+"X";
					System.out.println(nstr);
				}
			}
		}else{
			System.out.println("nononono");
		}
			
	}

}
