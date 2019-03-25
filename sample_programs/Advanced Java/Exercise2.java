import java.io.*;
import java.util.*;

public class Exercise2 {
	final String FILENAME;
	final File file;
	ArrayList<String> lines;

	Console console = System.console();

	String temp = "";

	public Exercise2() {
		FILENAME = "File.txt";
		file = new File(FILENAME);
		lines = new ArrayList<String>();
	}//

	ArrayList<String> readFile() {
	    ArrayList<String> filelines = new ArrayList<String>();

		try {
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			BufferedReader br = new BufferedReader(new FileReader(file));
		    String line;
		    
		    while ((line = br.readLine()) != null) {
		    	filelines.add(line);
		    }
		}
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                file + "'");                
        }
        catch (IOException e) {
			e.printStackTrace();
		}

	    return filelines;
	}//

	void writetoFile(ArrayList<String> stringArray) {

		try {
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(file));

			for(String str : stringArray) {
				bw.write(str);
				bw.newLine();
			}
			
			bw.close();
		}
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                file + "'");                
        }
        catch (IOException e) {
			e.printStackTrace();
		}
	}//

	public void menu(boolean toreset, ArrayList<String> lines) {
		int iAction = 0;
		ClassMap cm = new ClassMap();

		////validate contents of the file

		//if data is to reset
		if(toreset) {
			System.out.println("Contents are reset.");
			cm.reset();
		}
		else {	//create list of array from file
			//make sure the line is in correct format
			try {
				boolean valid = false;

				for(String str : lines) {
					//validate if columns are 3
					valid = cm.addRow(true, str);

					if(!valid) {
						System.out.println("Contents of the file are invalid.\nContents are reset.");
						cm.reset();
						break;
					}
				}
			}
			catch(Exception ex) {
				System.out.println("Contents of the file are invalid.\nContents are reset.");
				cm.reset();
			}
		}
		////validate contents of the file

		cm.print();
		this.writetoFile(cm.getStringArray());

		while(iAction != 7) {
			System.out.println("\nActions (Enter the number of the desired action) :");
			System.out.print("(1) Search\n(2) Edit\n(3) Print \n(4) Reset\n(5) Add Row\n(6) Sort Row\n(7) Exit\n\n");

			//validate
			iAction = Validation.enterInteger("Action no.: ");

			//switch case call corresponding method
			switch(iAction) {

				case 1 : //call search method
					System.out.println("Search");
					cm.search();
					break;
				case 2 : //call edit method
					System.out.println("Edit");
					cm.edit();
					cm.print();
					this.writetoFile(cm.getStringArray());
					break;
				case 3 : //call print method
					cm.print();
					break;
				case 4 : //call reset/randomize method
					System.out.println("Reset");
					cm.reset();
					cm.print();
					this.writetoFile(cm.getStringArray());
					break;
				case 5 : //add row
					System.out.println("Add Row");
					cm.addRow(false, null);
					cm.print();
					this.writetoFile(cm.getStringArray());
					break;
				case 6 : //sort row
					System.out.println("Sort Row");
					cm.sort();
					cm.print();
					this.writetoFile(cm.getStringArray());
					break;
				case 7 : //exit
					//System.exit(0);
					System.out.println("Exit\n");
					break;
				default : 
					System.out.println("There is no action for that number.");	//then print menu again
					break;
			}//switch
		}//while

	}//

}//