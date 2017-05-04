package test;

public class ArraySpliter {
	private void splitArray(String[] arrContents){
		int arrayCount = arrContents.length%3 == 0 ? arrContents.length/3 : arrContents.length/3 + 1;
		boolean lastArray = arrContents.length%3 != 0;
		String[][] splitarrays = new String[arrayCount-1][3];
		String[] lastArrayContents = new String[arrContents.length%3];
		for(int i = 0; i < arrayCount-1; i++){
			for(int j = 0; j < 3; j++){
				int temp = i*3 + j;
				if(temp<arrContents.length){
					splitarrays[i][j] = arrContents[temp];
				}
			}
		}
		if(lastArray){
			for(int i = 0; i < lastArrayContents.length; i++){
				int temp = (arrayCount-1)*3 + i;
				lastArrayContents[i] = arrContents[temp];
			}
		}
	}
	public static void main(String args[]){
		ArraySpliter AS = new ArraySpliter();
		String[] arrContents = {"hi","Hello","How","you","doing","?","Whatsup","??","trew","jhbdf","ft"};
		AS.splitArray(arrContents);
	}
}
