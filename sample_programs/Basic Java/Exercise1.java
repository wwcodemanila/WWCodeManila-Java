import java.io.Console;
//import java.util.Random;

public class Exercise1 {

	static Console console = System.console();

	static String arr[][] = null;
	static int iFirstDime = 0;
	static int iSecondDime = 0;
	static int iAction = 0;

	final static String strAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public static void main (String args[]) {

		//init
		//generate 3 random chars per cell
		randomizeArray();

		//print array
		printArray();

		while(iAction != 5) {
			//printMenu
			printMenu();
		}

		//exit
		//System.exit(0);

	}//end main

	static void printMenu() {
		
		System.out.println("\nActions (Enter the number of the desired action) :");
		System.out.print("(1) Search\n(2) Edit\n(3) Print \n(4) Reset\n(5) Exit\n\n");

		//validate
		iAction = enterInteger("Action no.: ");

		//switch case call corresponding method
		switch(iAction) {

			case 1 : //call search method
				searchArray();
				break;
			case 2 : //call edit method
				editArray();
				printArray();
				break;
			case 3 : //call print method
				printArray();
				break;
			case 4 : //call reset/randomize method
				System.out.println("Reset");
				randomizeArray();
				printArray();
				break;
			case 5 : //exit
				//System.exit(0);
				System.out.println("Exit\n");
				break;
			default : 
				System.out.println("There is no action for that number.");	//then print menu again
				break;
		}

	}//end printMenu

	static void printArray() {

		System.out.println("\n\n==========\n");

		for(int i = 0; i < iFirstDime + 1; i++) {

			for(int j = 0; j < iSecondDime + 1; j++) {
				if(i == 0) {
					if(j != 0) {
						System.out.print(j);
					}

					System.out.print("\t");
				}
				else if(j == 0) {
					System.out.print(i + "   |   ");
				}
				else{
					System.out.print(arr[i - 1][j - 1] + "\t");
				}
			}

			if(i == 0) {
				System.out.print("\n     ");

				for(int h = 0; h < iSecondDime; h++) {
					System.out.print("--------");
				}
			}

			System.out.print("\n");
		}

		System.out.println("\n==========\n");

	}//end printArray

	static void randomizeArray() {

		//Random random = new Random();
		int iRandomNum = 0;

		//init display
		System.out.println("\n  ----------\n | 2D Array |\n  ----------\n");
		System.out.println("Please enter dimensions for array");
		
		iFirstDime = enterInteger("No. of rows : ");
		iSecondDime = enterInteger("No. of columns : ");

		arr = new String[iFirstDime][iSecondDime];

		for(int arr1 = 0; arr1 < iFirstDime; arr1++) {
			for(int arr2 = 0; arr2 < iSecondDime; arr2++) {
				arr[arr1][arr2] = "";
				
				for(int i = 0; i < 3; i++) {

					//iRandomNum = Math.abs(random.nextInt()) % strAlphabet.length();	//study why this is coded like this
					iRandomNum = (int) (Math.random() * strAlphabet.length());
					arr[arr1][arr2] += strAlphabet.charAt(iRandomNum);	//study CharAt
					
				}
			}
		}
		
	}//end randomizeArray

	static void editArray() {

		int iRowIndex = 0;
		int iColIndex = 0;
		boolean existing = false;
		String strNewValue = "";

		//validate if indexes are existing
		while(!existing) {
			System.out.print("\nEnter the indexes of the cell to edit\n");
		
			iRowIndex = enterInteger("Row index : ");
			iColIndex = enterInteger("Column index : ");

			if(iRowIndex <= iFirstDime && iRowIndex > 0
				&& iColIndex <= iSecondDime && iColIndex > 0) {
				existing = true;
			}
			else {
				System.out.println("The indexes entered does not exist.");
			}
		}

		//validate to 3 chars only and no special characters
		//arr[iRowIndex - 1][iColIndex - 1] = console.readLine();
		while(strNewValue.length() > 3 || strNewValue.length() == 0) {
			System.out.print("\nEdit \"" + arr[iRowIndex - 1][iColIndex - 1] + "\" to : ");

			strNewValue = console.readLine();

			if(strNewValue.length() > 3) {
				System.out.println("Maximum length for each value is 3.");
			}
			if(strNewValue.length() == 0) {
				System.out.println("Please enter new value.");
			}
			if(strNewValue.matches("^.*[^a-zA-Z0-9 ].*$")) {
				System.out.println("Special characters are not allowed.");
				strNewValue = "";
			}
		}

		arr[iRowIndex - 1][iColIndex - 1] = strNewValue;

	}//end editArray

	static void searchArray() {
		String strSearch = null;
		boolean blFound = false;

		System.out.print("\nSearch for : ");
		strSearch = console.readLine();

		System.out.println("\nSearch results for \"" + strSearch + "\"\n");

		for(int i = 0; i < iFirstDime; i++) {
			for (int j = 0; j < iSecondDime; j++) {
				if(arr[i][j].toLowerCase().contains(strSearch.toLowerCase())) {
					System.out.println("\"" + arr[i][j] + "\"\tRow " + (i+1) + ", Column " + (j+1));
					blFound = true;
				}
			}
		}

		if(!blFound) {
			System.out.println("No results found.");
		}

	}//end searchArray

	static int enterInteger(String label) {

		int value = 0;
		boolean success = false;

		while(!success) {
			try {
				System.out.print(label);
				value = Integer.parseInt(console.readLine());
				success = true;
			}
			catch(NumberFormatException ex) {
				System.out.println("Please enter an integer.");
			}
		}

		return value;

	}//end enterInteger

}//end class