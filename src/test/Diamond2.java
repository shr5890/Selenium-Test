package test;

import java.io.IOException;

public class Diamond2 {
	public static void main(String args[]) throws IOException
	{
		int n=7;
		int size=n/2;
		int intInd=0;

		for(int i=0;i<n;i++) {
			if(i==size){
				for(int k=0;k<n;k++){
					System.out.print("*");
				}
				intInd=0;
			}
			else{
				for(int j=0;j<n;j++) {				
					if(j==size) {
						System.out.print("*");
					}
					else if(j==size-intInd) {
						System.out.print("*");
					}
					else if(j==size+intInd) {
						System.out.print("*");
					}
					else if(j<size+intInd && j>size-intInd) {
						System.out.print("*");
					}
					else {
						System.out.print(" ");
					}
				}
			}
			intInd++;
			if(intInd==size) {						
				intInd=0;
			}
			System.out.println();
		}
	}
}