package test;

public class Solution {
	    public static int lengthOfLongestSubstring(String s) {
	        if(s.equals(""))
	           return 0;
	        
	        int len = s.length();
//	        System.out.println(len);
	        char [] ss = new char[len];
	        ss = s.toCharArray();
	        String [] eva = new String[len+1];
	        
	        for(int a=0;a<len+1;a++){
	            eva[a] ="";
	        }
	        
	        int j =0;
	        for(int i =0;i<len;){
	            if(eva[j].indexOf(ss[i])==-1){
	                eva[j] = eva[j]+ss[i];
	                System.out.println("输出第"+j+"个字符串"+eva[j]);
	                i++;
	            }else{
	            	System.out.println("第一次的"+j);
	            	System.out.println("i改变之前："+i);
	            	i=s.indexOf(ss[i])+1;
	            	System.out.println("asdasdasdasds"+i);
	                j++;
	                System.out.println(j);
	            }
	        }
	        for(int a=0;a<len+1;a++){
	        	System.out.println(eva[a]);
	        }
	        
	        int l=0;
//	        System.out.println(j);
	        for(int k =0;k<len-1;k++){
	            if(eva[l].length()<eva[k+1].length())
	                l=k+1;
	        }
	        return eva[l].length();
	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int pp =lengthOfLongestSubstring("abcab");
		System.out.println(pp);
	}

}
