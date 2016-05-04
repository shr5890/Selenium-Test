package test;

public class Debug {	
	public static void main(String args[]) {
		String[] arr = {"1","2","3",null,"1","2",null};		
		Debug debug = new Debug();
		debug.printData(arr);
	}

	public void printData(String[] arr){
		String str = null;
		try{
			if(str.equals("Hi")){
				
			}else{
				str = "bye";
			}
			System.out.println(str);
			System.out.println(arr[arr.length]);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}		
}