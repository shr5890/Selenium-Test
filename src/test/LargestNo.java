package test;

public class LargestNo {	
	public static void main(String args[]) {
		int[] num = {1,6,7,4,3,5,2};
		int arrlength=num.length;
		int[] num1=new int[arrlength];
		int[] num2=new int[3];
		int pointer2=0;
		for(int i=0;i<arrlength;i++){
			int pointer=0;
			for(int j=0;j<arrlength;j++){
				if(num[i]>num[j]){
					pointer++;
					if(pointer==(arrlength-2)){
						num1[i]=num[i];
						System.out.println(num1[i]);
					}
				}			
			}					
		}	
	}
}
