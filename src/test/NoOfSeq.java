package test;

public class NoOfSeq {
	public static void main(String args[]) {
		String data = ";;;;;";
//		char[] num = {'t','e','s','t'};
//		int arrlength=num.length;
//		int[] num1=new int[arrlength];
//		//		int[] num2=new int[3];
//		//		int pointer2=0;
//		for(int i=0;i<arrlength;i++){
//			System.out.print(num[i]);
//			for(int j=0;j<arrlength;j++){
//				if(!(j==i)){
//				System.out.print(num[j]);
//				}
//			}		
//		}
		String[] split = data.split("\\;", -1);
		for(int i=0;i<split.length;i++){
		System.out.println("Split No : "+i+" "+split[i]);
		}
		System.out.println(" Length : "+(split.length-1));
	}					
}

