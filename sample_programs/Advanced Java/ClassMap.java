import java.util.*;

public class ClassMap {

	private ArrayList<LinkedHashMap> mapArray;
	private final int ROW = 3;
	private final int COL = 3;

	public ClassMap() {
		mapArray = new ArrayList<LinkedHashMap>();
	}//

	private void randomize() {
		mapArray = new ArrayList<LinkedHashMap>();
		LinkedHashMap hm = new LinkedHashMap();
		int iRandomNum;
		String temp = "";
		String key = "";
		String value = "";

		try {
			for(int i = 0; i < ROW; i++) {
				for (int j = 0; j < COL; j++) {
					for (int k = 0; k < 6; k++) {	
						iRandomNum = ((int) (Math.random() * 95)) + 32;	//95 ascii characters starting from 32
						temp += (char) iRandomNum;	//get character

						if(k == 2) {
							key = temp;
							temp = "";
						}
						if(k == 5) {
							value = temp;
							temp = "";
						}
					}//for cell

					hm.put(key, value);
				}//for column

				mapArray.add(i, hm);
				hm = new LinkedHashMap();
				temp = "";
			}//for row
		}
		catch(Exception ex) {

		}

		//System.out.println(mapArray);
	}//randomize

	public void reset() {
		mapArray = new ArrayList<LinkedHashMap>();

		randomize();
	}//reset

	public ArrayList<String> getStringArray() {
		ArrayList<String> lstString = new ArrayList<String>();

		//save to list
		for(HashMap map : mapArray) {
			lstString.add(map.toString());
		}
		
		return lstString;
	}//getStringArray

	public void print() {
		ArrayList<String> lstPrintString = getStringArray();
		int rowNum = 1;
		System.out.println("\nKEY = VALUE\n\t1\t\t2\t\t3");

		for(String str : lstPrintString) {
			
			System.out.print(rowNum++ + "\t");
			String value = str.substring(1, str.length()-1);
			String[] keyValue = value.split(", ");

			for(String pair : keyValue)
			{
				System.out.print(pair.substring(0, 3) + " = " + pair.substring(4, 7) + "\t");
			}

			System.out.print("\n");
		}
	}//print

	//add row | true if existing from file, false if manual
	public boolean addRow(boolean existing, String strMapline) { //validate at exercise2 if line is correct format
		LinkedHashMap hm = new LinkedHashMap();
		boolean success = false;
		
		if(existing) {
			strMapline = strMapline.substring(1, strMapline.length()-1);			
			String[] keyValue = strMapline.split(", ");

			//validate if cells from file have 3 columns
			if(keyValue.length == 3) {
				success = true;
			}
			else {
				success = false;
			}

			for(String pair : keyValue)
			{
				hm.put(pair.substring(0, 3), pair.substring(4, 7));
			}
		}
		else {
			String key, value;
			boolean size3 = false;

			//ask user for input
			while(!size3) {
				for(int i = 0; i < 3; i++) {
					key = Validation.enter3Chars("\nEnter key for column " + (i+1) + " : ");
					value = Validation.enter3Chars("\nEnter value for column " + (i+1) + " : ");

					hm.put(key, value);
				}

				if(hm.size() == 3) {
					size3 = true;
					success = true;
				}
				else {
					System.out.println("Two or more keys were the same, please try again.");
					hm = new LinkedHashMap();
				}
			}
		}

		if(success) {
			mapArray.add(hm);
		}

		return success;
	}//addRow

	public void edit() {
		//
		int iRowIndex = 0;
		int iColIndex = 0;
		boolean existing = false;
		String editCell = "";
		String strNewkey = "";
		String strNewValue = "";

		//validate if indexes are existing
		while(!existing) {
			System.out.print("\nEnter the indexes of the cell to edit\n");
		
			iRowIndex = Validation.enterInteger("Row index : ");
			iColIndex = Validation.enterInteger("Column index : ");

			if(iRowIndex <= mapArray.size() && iColIndex <= COL
				&& iRowIndex > 0 && iColIndex > 0) {
				existing = true;
			}
			else {
				System.out.println("The indexes entered does not exist.");
			}
		}

		//locate key / cell
		LinkedHashMap lhm = mapArray.get(iRowIndex-1);
		String str = lhm.toString();
		str = str.substring(1, str.length()-1);
		String[] keyValue = str.split(", ");

		//display
		System.out.println("\nEdit \"" + keyValue[iColIndex-1].substring(0, 3)
			+ " = " + keyValue[iColIndex-1].substring(4, 7) + "\" to :");

		//get new values
		strNewkey = Validation.enter3Chars("Enter new key : ");
		strNewValue = Validation.enter3Chars("Enter new value : ");

		//save to the map
		keyValue[iColIndex-1] = strNewkey + "=" + strNewValue;
		lhm.clear();

		for(String kv : keyValue) {
			lhm.put(kv.substring(0,3), kv.substring(4, 7));
		}
	}//edit

	public void search() {

		String strSearch = null;
		boolean found = false;
		int searchRow = 0;
		int searchCol = 0;

		strSearch = Validation.acceptSearchString("\nSearch for : ");

		System.out.println("\nSearch results for \"" + strSearch + "\"\n");

		for(LinkedHashMap lhm : mapArray) {
			searchRow++;
			searchCol = 0;
			String str = lhm.toString();
			str = str.substring(1, str.length()-1);
			String[] keyValue = str.split(", ");

			for (String kv : keyValue) {
				searchCol++;
				kv = kv.substring(0,3) + kv.substring(4,7);

				if(kv.toLowerCase().contains(strSearch.toLowerCase())) {
					System.out.println("\"" + kv.substring(0,3) + " = " + kv.substring(3,6) 
						+ "\"\tRow " + searchRow + ", Column " + searchCol);
					found = true;
				}
			}
		}

		if(!found) {
			System.out.println("No results found.");
		}
	}//search

	public void sort() {
		//
		int rowNum = 0;
		boolean existing = false;

		while(!existing) {
			rowNum = Validation.enterInteger("\nEnter row index to sort : ");

			if(rowNum <= mapArray.size() && rowNum > 0) {
				existing = true;
			}
			else {
				System.out.println("Row does not exist.");
			}
		}

		//locate row
		LinkedHashMap lhm = mapArray.get(rowNum-1);
		String str = lhm.toString();
		str = str.substring(1, str.length()-1);
		String[] keyValue = str.split(", ");

		//sort cells
		Arrays.sort(keyValue);
		lhm.clear();

		for (String kv : keyValue) {
			lhm.put(kv.substring(0,3), kv.substring(4, 7));
		}

	}//sort

}//
