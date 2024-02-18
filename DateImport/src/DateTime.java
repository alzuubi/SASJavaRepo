
/**
 * @author Abdulrahman Alzuubi
 * Program purpose: The purpose of this program is to input ISO8601 date/time format data values from a file, check for duplicates and correct format
 * and then export the correct and non-duplicate values to a file.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 */
public class DateTime {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		LinkedHashSet<String> textData = new LinkedHashSet<String>();

		Scanner fileName = new Scanner(System.in);

		// gets the file name from the user and uses a try/catch statement to gracefully
		// handle an error
		try {

			System.out.println("Which file would you like to input?");

			String name = fileName.nextLine();

			// inputfile method is called to input the data into a linked hash set
			textData = inputFile(name);

		} catch (FileNotFoundException exception) {

			System.out.println("The file does not exist");

		} catch (Exception exception) {

			System.out.println("An error occurred");

		}

		// calls the validData method with the Linked Hash Set data
		validData(textData);

		// checks if the original file has any correct values after validation or is
		// empty
		if (textData.isEmpty()) {

			System.out.println("Input file has no correct values or is empty");

		} else {

			// try/catch statement to get the file name for the output file and to call the
			// writeToFile method
			try {

				System.out.println("Please enter a name for output file:");

				String outputFileName = fileName.nextLine();

				// calls the writeToFile method with the validated linked hash list and the name
				// of the chosen file
				writeToFile(textData, outputFileName);


			} catch (IOException exception) {

				System.out.println("Program could not write to file");

			} catch (NoSuchElementException exception) {

				System.out.println("Program failed due to a file name error");

			} catch (Exception exception) {

				System.out.println("An error occurred");

			}

			// closes the scanner to prevent a memory leak
			fileName.close();

		}

	}

	/**
	 * Method to input the file contents and insert them into a hash set. The linked
	 * hash set will remove any duplicate entries and will maintain the order Also
	 * uses the scanner class to read the contents from the file
	 * 
	 * @param fileName - the name of the file that is being used
	 * @return the data in a LinkedHashSet
	 * @throws FileNotFoundException
	 */
	public static LinkedHashSet<String> inputFile(String fileName) throws FileNotFoundException {

		LinkedHashSet<String> data = new LinkedHashSet<String>();

		// change to ask the user for the file name
		Scanner fileScanner = new Scanner(new File(fileName));

		fileScanner.useDelimiter("\r\n");

		while (fileScanner.hasNext()) {
			String dataLine = fileScanner.next();

			data.add(dataLine);

		}

		/* closes the scanner to prevent memory leaks */
		fileScanner.close();

		return data;

	}

	/**
	 * Method takes in a linked hash set and validates it according to the ISO 8601
	 * format
	 * 
	 * @param list - a linked hash list
	 * @return validated linked hash set
	 */
	public static LinkedHashSet<String> validData(LinkedHashSet<String> list) {

		Iterator<String> iterate = list.iterator();

		String data = "";

		while (iterate.hasNext()) {

			data = iterate.next();

			// checks if the length of the string is not 20 or 25
			if (!(data.length() == 20 || data.length() == 25)) {

				iterate.remove();

			}

			// checks the year - if the format is not correct, it will remove the current
			// set entry from the linked hash set
			else if (convertString(data.substring(0, 4)) > 9999 || convertString(data.substring(0, 4)) < 0) {

				// list.remove(index);
				iterate.remove();

			}

			// checks for the dash between year and month; If it's not the correct format,
			// it will remove the current set entry from the linked hash set
			else if (!(data.substring(4, 5).equals("-"))) {

				// list.remove(index);
				iterate.remove();

			}

			// checks if the correct format is used for the month; if it's not correct, it
			// will remove the current set entry from the linked hash set
			else if (convertString(data.substring(5, 7)) < 1 || convertString(data.substring(5, 7)) > 12) {

				// list.remove(index);
				iterate.remove();

			}

			// checks for the dash between month and day; if it's not the correct format, it
			// will remove the current set entry from the linked hash set
			else if (!(data.substring(7, 8).equals("-"))) {

				// list.remove(index);
				iterate.remove();

			}

			// checks of the correct format is used for the day; if it's not correct, it
			// will remove the current set entry from the linked hash set
			else if (convertString(data.substring(8, 10)) < 1 || convertString(data.substring(8, 10)) > 31) {

				// list.remove(index);
				iterate.remove();

			}

			// check to make sure the 'T' character is there; if it's not correct, it will
			// remove the current set entry from the linked hash set
			else if (!(data.substring(10, 11).equals("T"))) {

				// list.remove(index);
				iterate.remove();

			}

			// checks to make sure the hour component is correct; if it's not correct, it
			// will remove the current set entry from the linked hash set
			else if (convertString(data.substring(11, 13)) < 1 || convertString(data.substring(11, 13)) > 31) {

				// list.remove(index);
				iterate.remove();

			}

			// checks to make sure the colon exists - if not, the current set entry will
			// get removed from the linked hash set
			else if (!(data.substring(13, 14).equals(":"))) {

				// list.remove(index);
				iterate.remove();

			}

			// checks to make sure the minute component is correct; if not, the current set
			// entry will get removed from the linked hash set
			else if (convertString(data.substring(14, 16)) < 0 || convertString(data.substring(14, 16)) > 59) {

				// list.remove(index);
				iterate.remove();

			}

			// Checks to make sure the colon exists; if not, it will remove the current set
			// entry from the linked hash list
			else if (!(data.substring(16, 17).equals(":"))) {

				// list.remove(index);
				iterate.remove();
			}

			// Checks to make sure the seconds component is correct; if not, the current
			// set entry will get removed from the linked hash set
			else if (convertString(data.substring(17, 19)) < 0 || convertString(data.substring(17, 19)) > 59) {

				// list.remove(index);
				iterate.remove();

			}

			// checks for the correct time zone format which is either a "Z" for GMT or -/+
			// for a time format; if not, it will be removed from the linked hash set
			else if (!(data.substring(19, 20).equals("Z") || data.substring(19, 20).equals("+")
					|| data.substring(19, 20).equals("-"))) {

				       iterate.remove();

			}

			// if the time zone designator is not Z, complete more checks for the hours and
			// minutes format
			else if (data.substring(19, 20).equals("+") || data.substring(19, 20).equals("-")) {

				// check to see if the hour component format is correct; if not, current set
				// entry will be removed from the linked hash set
				if (convertString(data.substring(20, 22)) < 0 || convertString(data.substring(20, 22)) > 59) {

					iterate.remove();

				}

				// Check to see if the colon exists - if not, the current set entry will be
				// removed from the linked hashed set
				else if (!(data.substring(22, 23).equals(":"))) {

					iterate.remove();

				}

				// check to see if the minute component format is correct; if not, current set
				// entry will be removed from the linked hash set
				else if (convertString(data.substring(23)) < 0 || convertString(data.substring(23)) > 59) {

					iterate.remove();

				}
			}

		}

		return list;

	}

	/**
	 * Method takes in a linked hash set and writes the data to a file using a file
	 * name the user wants - 
	 * @exception throws general exception which is handled in the main method
	 * @param list - the linked hash set
	 * @param name - name of the file
	 */
	public static void writeToFile(LinkedHashSet<String> list, String name) throws Exception {

		// iterator to iterate over linked hash set
		Iterator<String> iterate = list.iterator();

		FileWriter outputFile = new FileWriter(name);

		while (iterate.hasNext()) {

			outputFile.write(iterate.next() + "\r\n");

		}

		outputFile.close();
	}

	/**
	 * Method converts a string into an integer - has a try-catch statement to
	 * gracefully handle exceptions
	 * 
	 * @param array
	 * @return returns an integer or -1 if there is an exception
	 */
	public static int convertString(String array) {

		try {
			int number = 0;

			number = Integer.valueOf(array);

			return number;

		} catch (NumberFormatException exception) {

			return -1;

		}

	}

}
