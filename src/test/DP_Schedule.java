package test;

import java.util.Stack;


/*
 * �ö�̬�滮��������������⣺
��n��������j1, j2, ��, jn, ÿ��������һ����������С�ӹ�ʱ��pj , �Լ�һ��Ȩ��wj
1<=j<=n����һ�������ӹ���Щ�����Ļ����������������һ�ο��Լӹ������Ĺ�������ͬʱ�ӹ��Ĺ������һ�����¡�
��ͬһ��B��ӹ��Ĺ�������ͬʱ��ʼ��ͬʱ��������������ͬ���Ŀ�ʼʱ��sj, ͬ���Ľ���ʱ���j = sj + p(B)��j����B������p(B)=max{pj}(j����B)  ��
 ��һ��������ʼ�ӹ��������м䲻�����жϡ�

���ʣ���θ���Щ�����������Լ���ζ���Щ����������ʹ�����й����ļ�Ȩ���ʱ��֮�� ��С��

�����һ���㷨����������ʵ����

n = 50
��С�ӹ�ʱ��pj�����ǣ�
1��5��2��4��5��9��5��10��5��6��6��7��8��7��11��6��6��7��11��10��7��12��
8��8��8��6��5��13��14��9��8��9��3��3��11��2��9��13��4��6��4��4��8��9��6��15��5��7��9��3

Ȩ��wj�����ǣ�
2��3��2��5��4��4��6��8��5��7��7��4��6��9��12��4��4��3��5��5��3��6��
9��5��9��12��3��7��12��11��11��3��4��5��3��12��2��6��6��4��9��15��12��4��8��31��1��12��3��4

 * 
 */

/*
 * ˼·
 * 1.������С�ӹ�ʱ���С��������
 * 2.�ҵ�״̬ת�Ʒ���
 */
//��һ�ε�DPsolve�����⣡������������������������������������������
//DPsolve�ǸĽ��棬��ȷ������
public class DP_Schedule {
	
	static int [] time ={1,5,2,4,5,9,5,10,5,6,6,7,8,7,11,6,6,7,11,10,7,12,
		8,8,8,6,5,13,14,9,8,9,3,3,11,2,9,13,4,6,4,4,8,9,6,15,5,7,9,3};
	static int [] weight = {2,3,2,5,4,4,6,8,5,7,7,4,6,9,12,4,4,3,5,5,3,6,
		9,5,9,12,3,7,12,11,11,3,4,5,3,12,2,6,6,4,9,15,12,4,8,31,1,12,3,4};

//	static int [] time={1,2,3};
//	static int [] weight={5,3,1};
//	static int [] index=new int[time.length];
	static job [] jobs = new job[time.length];
	
	static int finish[] =new int[jobs.length];		//��¼��i������ʱ���ʱ���ʱ��
	static int minTime[] =new int[jobs.length];		//��¼��i������ʱ��Ҫ����Сʱ��
	static int index [] = new int[jobs.length];		//��¼��i��������������һ�������һ�����±꣨�ź���֮����±꣩
	
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
		System.out.println("ע�⣺���ε�˳���Ǵ������ϵ�");
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
	
	//��������0
	//��������1
	public static void DPsolve2(){
		
		
		for(int i=0,weight=0;i<jobs.length;i++){
			weight+=jobs[i].weight;
			res[0][i] =jobs[i].time *weight;
			pre[0][i]=0;
			fin[0][i] =jobs[i].time;
		}
		pre[0][0]=-1;		//���õ�һ�����ǰ��Ľڵ�Ϊ�գ����ѵ�һ���������Եķ�������Ϊ-1
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
	
	//���ݶ�̬�滮2�������
	public static void divide2(int [][] pre,int i,int j){
		
		if(pre[i][j] ==0){
			System.out.print(jobs[i+j].time+"  ");
			divide2(pre,i,j-1);
		}else if(pre[i][j] ==1){
			System.out.println(jobs[i+j].time+" 	  ****�� "+(i+1)+"��****");
			divide2(pre, i-1, j);
		}else if(pre[i][j]==-1){
			System.out.println(jobs[i+j].time+ "      ****��1"+"��****");
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
	
	//��̬�滮�㷨�������
	public static void DPsolve(){
		minTime[0] = jobs[0].time * jobs[0].weight;
		finish[0] = jobs[0].time;
		index[0] = 0;
		for(int i = 1;i<jobs.length;i++){
			int w = jobs[i].weight;
			
			//��һ��   ��ʼ��
			minTime[i] = -1 ; //��ʼ��				//minTime[i-1] + (finish[i-1]+jobs[i].time)*w;
//			index[i] = i-1;
//			finish[i] = finish[i-1] + jobs[i].time;
//			
			//�ϲ�������������  һֱ�� �ϲ�i-1������
			for(int j=1;j<=i;j++){

				int min = minTime[i-j] + (finish[i-j]+jobs[i].time)*w;
				w = w + jobs[i-j].weight;		//������һ�ε�w
				if(min <= minTime[i] || minTime[i]<0){	//С�ڵ��� ��ȡ���ڵ�ԭ���ǻ�ʹ����ʱ���С
					minTime[i] = min;
					index[i] = i-j;
					finish[i] = finish[i-j]+jobs[i].time;
				}
			}
			
			//ȫ������һ������
	//		w = w+jobs[0].weight;
			int m = w*jobs[i].time;
			if(m<minTime[i]){
				minTime[i] = m;
				index[i] = 0;
				finish[i] = jobs[i].time;
			}
			
		}
	}
	
	//��Ѱ����Щ���ķָ����һ���±�
	public static void divide(int [] arr,int n){
		Stack<Integer> s =new Stack<Integer>();
		//��һ��ջѭ������Щ������ÿһ�������һ�����±���룬�ͻ��ҵ�������λ��
		s.push(n);
		while(arr[n] != 0){
			s.push(arr[n]);
//			System.out.println(arr[n]);
			n = arr[n];
//			System.out.println(arr[n]);
		}
		
		int groupNum = 1,count = 1;
		int start = 0;	//�����λ��
		while(!s.empty()){
	//		System.out.println(s.size());
			int len = s.pop();
	//		System.out.println(len);
			System.out.println("**********************************************************************");
			System.out.println("�� "+groupNum+" �����£�");
			for(int i =start;i<=len ;i++){
				System.out.println("�� "+jobs[i].index +" ��������    ����ʱ�䣺 "+jobs[i].time+"    Ȩ�أ�  "+jobs[i].weight+"     "+count++);
			}
			start = len+1;	//��һ�δ�len���λ�ÿ�ʼ���
			groupNum++;		//���μ�һ
		}
		
	}
	
	//������Сֵ��ѡ����ʽ
//	public static void cal(int n){
//		int 
//	}
	
	//���������ʱ�䡢Ȩ���������齨��һ��job������
	public static job [] generate(int time[] ,int weight[]){
		
		for(int i = 0;i<time.length;i++){
			jobs[i] = new job(time[i],weight[i],i+1);
		}
		return jobs;
	}
	
	//����
	public static void sort( job  [] arr){
		
		int temp = 0;
		job jb =new job(1,1,1);
		//����time����ʱ����¼�±��λ�ñ任����arr����ͬ�����±�任
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
