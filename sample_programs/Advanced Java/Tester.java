
public class Tester {
	public static void main(String[] args) {
		Exercise2 ex = new Exercise2();
		boolean reset = false;

		ex.lines = ex.readFile();

		//validate lines here
		////if invalid format or empty
		if(ex.lines.isEmpty()) {
		////suggest resetting
			//do you want to reset? else exit
			reset = true;
		}

		System.out.println("\n  -------------\n | Collections |\n  -------------\n");
		
		ex.menu(reset, ex.lines);
	}//
}