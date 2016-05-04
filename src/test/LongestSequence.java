package test;

public class LongestSequence {
	public static void main(String args[]) {
		int[] num = {1,6,7,4,-1,3,5,-2,2,3,4,5,6,7,8,-3,3};
		int arrlength=num.length;
		int pointer=0,pointer2=0;
		int[][] num1 = new int[arrlength][arrlength];  				
		for(int i=pointer2;i<arrlength;i++){
//			pointer=0;
			for(int j=pointer2;j<arrlength;j++){					
				if(num[j]>0){
					pointer++;					
					num1[i][j]=num[j];
					System.out.println(num1[i][j]);					
				}
				else{							
					break;
				}				
			}
			pointer2=pointer2+pointer+1;
			pointer=0;
			System.out.println(pointer);
		}
//		for(int i=0;i<arrlength;i++){
//			System.out.println();
//			for(int j=0;i<arrlength;i++){
//				System.out.print(num1[i][j]);
//			}
//		}
	}
}
