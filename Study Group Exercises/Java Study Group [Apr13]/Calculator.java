import java.util.Scanner;

class Calculator {
	public static void main(String args[]) {
		float number1;
		float number2;
		Scanner input = new Scanner(System.in);
		System.out.println("CALCULATOR");

		System.out.printf("Enter first number : ");
		number1 = input.nextFloat();

		System.out.printf("Enter second number : ");
		number2 = input.nextFloat();

		System.out.println("Enter the desired action:");
		System.out.println("Enter 'add' for addition");
		System.out.println("Enter 'subtract' for subtraction");
		System.out.println("Enter 'multiply' for multiplication");
		System.out.println("Enter 'divide' for division");

		String action;
		action = input.next();

		float result;

		if(action.equalsIgnoreCase("add")) {
			result = number1 + number2;
			System.out.println("Result is " + result);
		}

		switch(action) {
			case("add") : 
			result = number1 + number2;
			System.out.println("Result is " + result);
			break;

			case("subtract") :
			result = number1 - number2;
			System.out.println("Result is " + result);
			break;
		}
	}
}