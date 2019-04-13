import java.util.Scanner;

class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!"); 

        int a = 4;
        int b = 5;
        String name = "WWCODE";
        System.out.println(a + b);
        System.out.println(name);

        Scanner input = new Scanner(System.in);
		int number;
		System.out.printf("Enter a number : ");
		number = input.nextInt();
		System.out.println("Number is " + number);

		System.out.printf("Enter your name : ");
		name = input.next();
		System.out.println("Welcome to WWCODE, " + name + "!");
	}
}
