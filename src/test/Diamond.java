package test;

public class Diamond {
	public static void main(String args[]) {
		int no = 5;		
		for(int i=0;i<no;i++){
			int tempno = no/2;
			int startValue = tempno-i;
			int endValue = tempno+i;
			for(int j=0;j<no;j++){
				System.out.print("  ");
				if(j>=startValue){
					if(j<=endValue){
						System.out.print("*");
					}
				}
			}
			System.out.println();
		}			
	}			
}					

