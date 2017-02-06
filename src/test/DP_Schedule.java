package test;

import java.util.Stack;


/*
 * 用动态规划求解下述调度问题：
有n个工件，j1, j2, …, jn, 每个工件有一个给定的最小加工时间pj , 以及一个权重wj
1<=j<=n。有一个用来加工这些工件的机器。假设这个机器一次可以加工任意多的工件，　同时加工的工件组成一个批Ｂ。
在同一批B里加工的工件必须同时开始，同时结束，即它们有同样的开始时间sj, 同样的结束时间Ｃj = sj + p(B)，j属于B，这里p(B)=max{pj}(j属于B)  。
 从一批工件开始加工到结束中间不允许中断。

请问：如何给这些工件分批，以及如何对这些批进行排序使得所有工件的加权完成时间之和 最小。

请设计一个算法并求解下面的实例：

n = 50
最小加工时间pj依次是：
1，5，2，4，5，9，5，10，5，6，6，7，8，7，11，6，6，7，11，10，7，12，
8，8，8，6，5，13，14，9，8，9，3，3，11，2，9，13，4，6，4，4，8，9，6，15，5，7，9，3

权重wj依次是：
2，3，2，5，4，4，6，8，5，7，7，4，6，9，12，4，4，3，5，5，3，6，
9，5，9，12，3，7，12，11，11，3，4，5，3，12，2，6，6，4，9，15，12，4，8，31，1，12，3，4

 * 
 */

/*
 * 思路
 * 1.按照最小加工时间从小到大排序
 * 2.找到状态转移方程
 */
//第一次的DPsolve有问题！！！！！！！！！！！！！！！！！！！！！！
//DPsolve是改进版，正确！！！
public class DP_Schedule {
	
	static int [] time ={1,5,2,4,5,9,5,10,5,6,6,7,8,7,11,6,6,7,11,10,7,12,
		8,8,8,6,5,13,14,9,8,9,3,3,11,2,9,13,4,6,4,4,8,9,6,15,5,7,9,3};
	static int [] weight = {2,3,2,5,4,4,6,8,5,7,7,4,6,9,12,4,4,3,5,5,3,6,
		9,5,9,12,3,7,12,11,11,3,4,5,3,12,2,6,6,4,9,15,12,4,8,31,1,12,3,4};

//	static int [] time={1,2,3};
//	static int [] weight={5,3,1};
//	static int [] index=new int[time.length];
	static job [] jobs = new job[time.length];
	
	static int finish[] =new int[jobs.length];		//记录有i个工件时完成时候的时间
	static int minTime[] =new int[jobs.length];		//记录有i个工件时需要的最小时间
	static int index [] = new int[jobs.length];		//记录第i个工件那批的上一批的最后一个的下标（排好序之后的下标）
	
	static int [][] res =new int[jobs.length][jobs.length];
	static int [][] fin = new int[jobs.length][jobs.length];
	static int [][] pre=new int[jobs.length][jobs.length];
	
	public static void main(String[] args) {
		job [] jobs = generate(time,weight);
		sort(jobs);
		DPsolve2();
		int [] result = findMIn(50-1);
		System.out.println("*******************************************");
		System.out.println(result[0]);
		System.out.println("注意：批次的顺序是从下往上的");
		divide2(pre, result[1], result[2]);
	}
	
	
	
	public static void DP1(String[] args) {
		// TODO Auto-generated method stub
		job [] jobs = generate(time,weight);
		sort(jobs);
		System.out.println();
//		for(int i=0;i<index.length;i++){
//			System.out.print(index[i]+" ");
//		}
		
		DPsolve();
		divide(index,jobs.length-1);
//		for(int i=0;i<index.length;i++){
//			System.out.print(index[i]+" ");
//		}
//		System.out.println();
		for(int i=0;i<minTime.length;i++){
			System.out.print(minTime[i]+" ");
		}
		System.out.println();
//		for(int i=0;i<finish.length;i++){
//			System.out.print(finish[i]+" ");
//		}
		System.out.println(minTime[jobs.length-1]);
	}
	
	//往右走是0
	//往下走是1
	public static void DPsolve2(){
		
		
		for(int i=0,weight=0;i<jobs.length;i++){
			weight+=jobs[i].weight;
			res[0][i] =jobs[i].time *weight;
			pre[0][i]=0;
			fin[0][i] =jobs[i].time;
		}
		pre[0][0]=-1;		//设置第一个点的前面的节点为空，即把第一个点是来自的方向设置为-1
		for(int i=1,time=0;i<jobs.length;i++){
			time+=jobs[i].time;
			res[i][0] =res[i-1][0] + time *jobs[i].weight;
			pre[i][0]=1;
			fin[i][0] =time;
		}
		
		for(int j=1;j<jobs.length;j++){
			for(int k=1;k<jobs.length-j;k++){
				if(res[j-1][k]<=res[j][k-1]){
					res[j][k] = res[j-1][k]+(fin[j-1][k]+jobs[j+k].time)*jobs[j+k].weight;
					pre[j][k] =1;
					fin[j][k] =fin[j-1][k]+jobs[j+k].time;
				}else{
					int x=k-1;
					int we =jobs[j+x].weight+jobs[j+k].weight;
					while(pre[j][x]==0&&x>0){
						x--; 
						we +=jobs[x+j].weight;
					}
				//	we +=jobs[x+k].weight;
					res[j][k] = res[j-1][x]+we*(jobs[j+k].time+fin[j-1][x]);
					fin[j][k] = jobs[j+k].time+fin[j-1][x];
					pre[j][k] =0;
				}
			}
		}
		
		System.out.println("**********************************");
		for(int i=0;i<res.length;i++){
			for(int j=0;j<res[0].length;j++){
				System.out.print(res[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println("**********************************");

		
	}
	public static int[] findMIn(int n){
		int [] ret = new int[3];
		int min =res[0][n];
		//System.out.println(min);
		ret[0] =min;
		for(int i=1;i<=n;i++){
			if(res[i][n-i]<min){
				min=res[i][n-i];
				ret[0] = min;
				ret[1] = i;
				ret[2] = n-i;
			}
		}
		System.out.println(ret[0]+"=========="+ret[1]+"--------------"+ret[2]);
		return ret;
	}
	
	//根据动态规划2求解批次
	public static void divide2(int [][] pre,int i,int j){
		
		if(pre[i][j] ==0){
			System.out.print(jobs[i+j].time+"  ");
			divide2(pre,i,j-1);
		}else if(pre[i][j] ==1){
			System.out.println(jobs[i+j].time+" 	  ****第 "+(i+1)+"批****");
			divide2(pre, i-1, j);
		}else if(pre[i][j]==-1){
			System.out.println(jobs[i+j].time+ "      ****第1"+"批****");
			return;
		}
		
//		while(pre[i][j]!= -1){
//			System.out.print(jobs[i+j].index+" ");
//			while(pre[i][j]==0){
//				j--;
//				System.out.print(jobs[i+j].index+" ");
//			}
//			System.out.println();
//			i--;
//		}
	}
	
	//动态规划算法求解问题
	public static void DPsolve(){
		minTime[0] = jobs[0].time * jobs[0].weight;
		finish[0] = jobs[0].time;
		index[0] = 0;
		for(int i = 1;i<jobs.length;i++){
			int w = jobs[i].weight;
			
			//第一个   初始化
			minTime[i] = -1 ; //初始化				//minTime[i-1] + (finish[i-1]+jobs[i].time)*w;
//			index[i] = i-1;
//			finish[i] = finish[i-1] + jobs[i].time;
//			
			//合并两个工件以上  一直到 合并i-1个工件
			for(int j=1;j<=i;j++){

				int min = minTime[i-j] + (finish[i-j]+jobs[i].time)*w;
				w = w + jobs[i-j].weight;		//计算下一次的w
				if(min <= minTime[i] || minTime[i]<0){	//小于等于 ，取等于的原因是会使结束时间变小
					minTime[i] = min;
					index[i] = i-j;
					finish[i] = finish[i-j]+jobs[i].time;
				}
			}
			
			//全部放在一起的情况
	//		w = w+jobs[0].weight;
			int m = w*jobs[i].time;
			if(m<minTime[i]){
				minTime[i] = m;
				index[i] = 0;
				finish[i] = jobs[i].time;
			}
			
		}
	}
	
	//逐步寻找这些批的分割的那一个下标
	public static void divide(int [] arr,int n){
		Stack<Integer> s =new Stack<Integer>();
		//用一个栈循环将这些工件的每一批的最后一个的下标加入，就会找到分批的位置
		s.push(n);
		while(arr[n] != 0){
			s.push(arr[n]);
//			System.out.println(arr[n]);
			n = arr[n];
//			System.out.println(arr[n]);
		}
		
		int groupNum = 1,count = 1;
		int start = 0;	//输出的位置
		while(!s.empty()){
	//		System.out.println(s.size());
			int len = s.pop();
	//		System.out.println(len);
			System.out.println("**********************************************************************");
			System.out.println("第 "+groupNum+" 批如下：");
			for(int i =start;i<=len ;i++){
				System.out.println("第 "+jobs[i].index +" 个工件：    所需时间： "+jobs[i].time+"    权重：  "+jobs[i].weight+"     "+count++);
			}
			start = len+1;	//下一次从len这个位置开始输出
			groupNum++;		//批次加一
		}
		
	}
	
	//计算最小值的选择表达式
//	public static void cal(int n){
//		int 
//	}
	
	//根据输入的时间、权重两个数组建立一个job的数组
	public static job [] generate(int time[] ,int weight[]){
		
		for(int i = 0;i<time.length;i++){
			jobs[i] = new job(time[i],weight[i],i+1);
		}
		return jobs;
	}
	
	//排序
	public static void sort( job  [] arr){
		
		int temp = 0;
		job jb =new job(1,1,1);
		//当对time排序时，记录下标的位置变换，对arr进行同样的下标变换
		for(int i = 0 ;i<time.length;i++){
			
			for(int j = 0;j<time.length - i - 1;j++){
				if(time[j] > time[j+1]){
					temp = time [j+1];
					time[j+1] = time[j];
					time[j] = temp;
					
					jb = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = jb;
				}
			}
		}
		
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i].time+"\t");
		}
		System.out.println();
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i].weight+"\t");
		}
		System.out.println();
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i].index+"\t");
		}
		System.out.println();
	}

//	public static void 
	
static class job{
	int time;
	int weight;
	int index;
	
	public job(int time ,int weight,int index){
		this.time = time;
		this.weight = weight;
		this.index = index;
	}
	
}

}
