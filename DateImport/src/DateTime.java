/**
 * @author Abdulrahman Alzuubi
 * Program purpose: The purpose of this program is to input ISO8601 date/time format data values from a file, check for duplicates and correct format
 * and then export the correct and non-duplicate values to a file.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
/**
 * 
 */
public class DateTime {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedList<String> data = new LinkedList<String>();
		
		data.add("202g-05-31T12:3h:45+12:05");
		data.add("2021-02-15T12:34:56+12:34");
		
		validData(data);
		
		
		
		
         
	}
	
	
	/**
	 * Method to input the file contents and insert them into a LinkedList
	 * Also uses the scanner class to read the contents
	 * @return the data linkedlist
	 * @throws FileNotFoundException
	 */
	public LinkedList<String> inputFile() throws FileNotFoundException{
		
		LinkedList<String> data = new LinkedList<String>();
		
		//change to ask the user for the file name
		Scanner fileScanner = new Scanner(new File("testdata.txt"));
		
		fileScanner.useDelimiter("\n");	
		
		while(fileScanner.hasNext()) {
			String dataLine = fileScanner.next();
			   
			data.add(dataLine);
			
			/* closes the scanner to prevent memory leaks */
			fileScanner.close();
			
		}
		
		return data;
		
	}

	
	
	/**
	 * Method takes in a linkedlist and validates it according to the ISO 8601 format
	 * @param list
	 * @return New validated linkedlist
	 */
	public static LinkedList<String> validData(LinkedList<String> list){ 
		
		LinkedList<String> newList = new LinkedList<String>();
		
		int index = 0;
		
		Iterator<String> iterate = list.iterator();
		
		String data = "";		
		
		while(iterate.hasNext()) {
		
		//while(index < list.size()) {
			
		data = iterate.next();
		      
		    //checks if the length of the string is not 20 or 25
		    if(!(data.length() == 20 || data.length() == 25)) {
		    	
		    	iterate.remove();
		    	
		    }
		    
			//checks the year - if the format is not correct, it will remove the current list entry from the linkedlist
		    else if(convertString(data.substring(0, 4)) > 9999 || convertString(data.substring(0, 4)) < 0) {
				
		        //list.remove(index);
				iterate.remove();
				
			}
			
			//checks for the dash between year and month; If it's not the correct format, it will remove the current list entry from the linkedlist
			else if(!(data.substring(4,5).equals("-"))) {
				
				//list.remove(index);
				iterate.remove();
				
			}
			
			//checks if the correct format is used for the month; if it's not correct, it will remove the current list entry from the linkedlist
			else if(convertString(data.substring(5, 7)) < 1 || convertString(data.substring(5, 7)) > 12) {
				
				//list.remove(index);
				iterate.remove();
				
			}
		    
			//checks for the dash between month and day; if it's not the correct format, it will remove the current list entry from the linkedlist
			else if(!(data.substring(7,8).equals("-"))) {
				
				//list.remove(index);
				iterate.remove();
				
			}
				
			//checks of the correct format is used for the day; if it's not correct, it will remove the current list entry from the linkedlist
			else if(convertString(data.substring(8, 10)) < 1 || convertString(data.substring(8, 10)) > 31) {
				
				//list.remove(index);
				iterate.remove();
				
			}
			
			//check to make sure the 'T' character is there; if it's not correct, it will remove the current list entry from the linkedlist
			else if(!(data.substring(10,11).equals("T"))) {
				
				//list.remove(index);
				iterate.remove();
				
			}
			
			//checks to make sure the hour component is correct; if it's not correct, it will remove the current list entry from the linkedlist
			else if(convertString(data.substring(11, 13)) < 1 || convertString(data.substring(11, 13)) > 31) {
				
				//list.remove(index);
				iterate.remove();
				
			}
			
			//checks to make sure the colon exists - if not, the current list entry will get removed from the linkedlist
			else if(!(data.substring(13,14).equals(":"))) {
				
				//list.remove(index);
				iterate.remove();
				
			}
			
			//checks to make sure the minute component is correct; if not, the current list entry will get removed from the linkedlist
			else if(convertString(data.substring(14, 16)) < 0 || convertString(data.substring(14, 16)) > 59) {
				
				//list.remove(index);
				iterate.remove();
				
			}
			
			//Checks to make sure the colon exists; if not, it will remove the current list entry from the linkedlist
			else if(!(data.substring(16,17).equals(":"))) {
				
				//list.remove(index);
				iterate.remove();
			}
			
			//Checks to make sure the seconds component is correct; if not, the current list entry will get removed from the linkedlist
			else if(convertString(data.substring(17, 19)) < 0 || convertString(data.substring(17, 19)) > 59) {
				
				//list.remove(index);
				iterate.remove();
							
			}
			
		    //checks for the correct time zone format which is either a "Z" for GMT or -/+ for a time format; if not, it will be removed from the linkedlist
			else if(!(data.substring(19,20).equals("Z") || data.substring(19,20).equals("+") || data.substring(19,20).equals("-"))) {
				
				iterate.remove();
				
			}
		    
		    //if the time zone designator is not Z, complete more checks for the hours and minutes format
			else if(data.substring(19,20).equals("+") || data.substring(19,20).equals("-")) {
				
				 //check to see if the hour component format is correct; if not, current list entry will be removed from the linkedlist
				 if(convertString(data.substring(20,22)) < 0 || convertString(data.substring(20,22)) > 59) {
					 
					 iterate.remove();
					 
				 }
				 
				 //Check to see if the colon exists - if not, the current list entry will be removed from the linkedlist
				 else if(!(data.substring(22,23).equals(":"))) {
					 
					 iterate.remove();
					 
				 }
				  
				 //
				 else if(convertString(data.substring(23)) < 0 || convertString(data.substring(23)) > 59) {
					 
					 iterate.remove();
					 
				 }
			}
			
		}
		
		
		
		return list;
		
		
	}
	
	
	/**
	 * Method converts a string into an integer - has a try-catch statement to gracefully handle exceptions
	 * @param array
	 * @return returns an integer or -1 if there is an exception
	 */
	public static int convertString(String array) { 
		
		try {
			int number = 0;
			
			number = Integer.valueOf(array);		
			
			
			return number;
			
			
		} catch(NumberFormatException exception) {
			
			return -1;
			
		}
		
		//int number = 0;
		
		//number = Integer.valueOf(array);		
		
		
		//return number;
			
	}
	
	
	
	
	
	
	
}
