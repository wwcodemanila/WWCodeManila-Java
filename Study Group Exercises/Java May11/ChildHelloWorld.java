
class ChildHelloWorld extends HelloWorld {
	int c = 10;

	public static void main(String[] args) {
		c = super.getZ();
		System.out.println(c);
	}


	public void printLine() {
		System.out.println("Child");
	}
}