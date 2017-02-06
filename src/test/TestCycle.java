package test;
import java.util.ArrayList;
import java.util.Arrays;
public class TestCycle {
     private int n;
     private int[] visited;//�ڵ�״̬,ֵΪ0����δ���ʵ�
     private int[][] e;//����ͼ���ڽӾ���
     private ArrayList<Integer> trace=new ArrayList<Integer>();//�ӳ����ڵ㵽��ǰ�ڵ�Ĺ켣
     private boolean hasCycle=false;

     public TestCycle(int n,int[][] e){
         this.n=n;
         visited=new int[n];
         Arrays.fill(visited,0);
         this.e=e;
     }
    
     void findCycle(int v)   //�ݹ�DFS
    {
        if(visited[v]==1)
        {
            int j;
            if((j=trace.indexOf(v))!=-1)
            {
                hasCycle=true;
                System.out.print("Cycle:");
                while(j<trace.size())
                {
                    System.out.print(trace.get(j)+" ");
                    j++;
                }
                System.out.print("\n");
                return;
            }
        }
        visited[v]=1;
        trace.add(v);
        
        for(int i=0;i<n;i++)
        {
            if(e[v][i]==1){
            	findCycle(i);
            }
        }
        trace.remove(trace.size()-1);
    }
  
  public boolean getHasCycle(){
      return hasCycle;
  }

   public static void main(String[] args) {
        int n=6;
        int[][] e={
                {0,1,1,0,0,0},
                {0,0,1,1,0,0},
                {0,0,0,1,0,0},
                {0,0,0,0,1,1},
                {0,0,0,0,0,0},
                {0,1,0,0,0,0}};//����ͼ���ڽӾ���       
        TestCycle tc=new TestCycle(n,e);
        tc.findCycle(1);
        if(!tc.hasCycle) 
            System.out.println("No Cycle.");
    }
}

/*
int[][] e={
        {0,1,0,1},
        {0,0,1,0},
        {1,0,0,0},
        {0,0,1,0}};//����ͼ���ڽӾ���
  int[][] e={
                    {0,1,1,0,0,0},
                    {0,0,1,1,0,0},
                    {0,0,0,1,0,0},
                    {0,0,0,0,1,1},
                    {0,0,0,0,0,0},
                    {0,1,0,0,0,0}};//����ͼ���ڽӾ���       
        */





