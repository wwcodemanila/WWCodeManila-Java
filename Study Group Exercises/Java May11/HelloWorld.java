import java.util.Scanner;


class HelloWorld {

	private int z;
	private String s;

	public HelloWorld() {
		this.z = 2;
	}

	public HelloWorld(int z) {
		this.z = z * 2;
	}

	public HelloWorld(String s) {
		this.s = s;
	}

	public int getZ() {
		return this.z;
	}
	public setZ(int z) {
		this.z = z;
	}

	public int getS() {
		return this.s;
	}
	public setS(int s) {
		this.s = s;
	}


	public void printLine() {
		System.out.println("Hello world");
	}
}
