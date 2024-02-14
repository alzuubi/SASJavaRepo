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
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * Method to input the file contents and insert them into a LinkedList
	 * Also uses the scanner class to read the contents
	 * @return the data linkedlist
	 * @throws FileNotFoundException
	 */
	public LinkedList<String> inputFile() throws FileNotFoundException{
		
		LinkedList<String> data = new LinkedList<String>();
		
		Scanner fileScanner = new Scanner(new File("testdata"));
		
		fileScanner.useDelimiter(" ");	
		
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
	public LinkedList<String> validData(LinkedList<String> list){
		
		LinkedList<String> newList = new LinkedList<String>();
		
		int index = 0;
		
		Iterator<String> iterate = list.iterator();
		
		String data = "";
		
		
		while(iterate.hasNext()) {
		
		//while(index < list.size()) {
			
		data = iterate.next();
		
			//checks the year - if the format is not correct, it will remove the current list entry from the linkedlist
			if(convertString(data.substring(0, 3)) > 9999 || convertString(data.substring(0, 3)) < 0) {
				
		         //list.remove(index);
				iterate.remove();
				
			}
			
			//checks for the dash between year and month; If it's not the correct format, it will remove the current list entry from the linkedlist
			else if(!(data.substring(4).equals(":"))) {
				
				//list.remove(index);
				iterate.remove();
				
			}
			
			//checks if the correct format is used for the month; if it's not correct, it will remove the current list entry from the linkedlist
			else if(convertString(data.substring(5, 6)) < 1 || convertString(data.substring(5, 6)) > 12) {
				
				//list.remove(index);
				iterate.remove();
				
			}
		    
			//checks for the dash between month and day; if it's not the correct format, it will remove the current list entry from the linkedlist
			else if(!(data.substring(7).equals("-"))) {
				
				//list.remove(index);
				iterate.remove();
				
			}
				
			//checks of the correct format is used for the day; if it's not correct, it will remove the current list entry from the linkedlist
			else if(convertString(data.substring(8, 9)) < 1 || convertString(data.substring(8, 9)) > 31) {
				
				//list.remove(index);
				iterate.remove();
				
			}
			
			//check to make sure the 'T' character is there; if it's not correct, it will remove the current list entry from the linkedlist
			else if(!(data.substring(10).equals("T"))) {
				
				//list.remove(index);
				iterate.remove();
				
			}
			
			//checks to make sure the hour component is correct; if it's not correct, it will remove the current list entry from the linkedlist
			else if(convertString(data.substring(11, 12)) < 1 || convertString(data.substring(11, 12)) > 31) {
				
				//list.remove(index);
				iterate.remove();
				
			}
			
			//checks to make sure the colon exists - if not, the current list entry will get removed from the linkedlist
			else if(!(data.substring(13).equals(":"))) {
				
				//list.remove(index);
				iterate.remove();
				
			}
			
			//checks to make sure the minute component is correct; if not, the current list entry will get removed from the linkedlist
			else if(convertString(data.substring(14, 15)) < 0 || convertString(data.substring(14, 15)) > 59) {
				
				//list.remove(index);
				iterate.remove();
				
			}
			
			//Checks to make sure the colon exists; if not, it will remove the current list entry from the linkedlist
			else if(!(data.substring(16).equals(":"))) {
				
				//list.remove(index);
				iterate.remove();
			}
			
			//Checks to make sure the second component is correct; if not, the current list entry will get removed from the linkedlist
			else if(convertString(data.substring(17, 18)) < 0 || convertString(data.substring(17, 18)) > 59) {
				
				//list.remove(index);
				iterate.remove();
				
			}
			
			else if()
			//index++;
		}
		
		
		
		return newList;
		
		
	}
	
	
	/**
	 * Method converts string into an integer
	 * @param array
	 * @return returns an integer
	 */
	public int convertString(String array) {
		
		int number = 0;
		
		number = Integer.valueOf(array);		
		
		
		return number;
			
	}
	
	
	
	
	
	
	
}
