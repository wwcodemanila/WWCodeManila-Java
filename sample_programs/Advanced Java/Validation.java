//validation
import java.io.Console;

public class Validation {

	private static Console console = System.console();

	//validate input to integer only
	public static int enterInteger(String label) {

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

	//validate input to approx. 3 characters
	public static String enter3Chars(String label) {
		String value = "";
		boolean success = false;

		while(!success) {
			System.out.print(label);
			value = console.readLine();

			if(value.length() == 3) {
				success = true;
			}
			else {
				System.out.println("Please enter 3 characters.");
			}
		}

		return value;
	}//end enter3Chars

	public static String acceptSearchString(String label) {
		System.out.print(label);
		return console.readLine();
	}//end acceptSearchString

	public static void main(String args[]) {
		//System.out.println("\n" + enter3Chars());
	}
}
