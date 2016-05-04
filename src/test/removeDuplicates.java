package test;

import java.util.ArrayList;

public class removeDuplicates {
	public static void main(String args[]) {
		String[] arr = {"1","2","3",null,"1","2",null};
		removeDuplicates rd = new removeDuplicates();
		arr = rd.RD(arr);
		System.out.println(" ========== ");
		for(String strValue : arr){
			System.out.println(strValue);
		}
		arr = rd.nullvalues(arr);
		System.out.println(" ========== ");
		for(String strValue : arr){
			System.out.println(strValue);
		}
	}

	public String[] RD(String[] Arr){		
		ArrayList<String> arrList = new ArrayList<String>();
		for(int i=0;i<Arr.length;i++){									
			if(!arrList.contains(Arr[i])){
				arrList.add(Arr[i]);	
			}	
		}			
		String[] outputArray = new String[arrList.size()];    
		outputArray = arrList.toArray(outputArray);		
		return (outputArray);
	}
	
	public String[] nullvalues(String[] Arr){		
		ArrayList<String> arrList = new ArrayList<String>();
		for(int i=0;i<Arr.length;i++){								
			if(Arr[i]!=null){
				arrList.add(Arr[i]);	
			}	
		}			
		String[] outputArray = new String[arrList.size()];    
		outputArray = arrList.toArray(outputArray);		
		return (outputArray);
	}
}