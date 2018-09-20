
// Exercise 18.2 by Aditya Mehta

import java.util.Scanner;

public class Exercise18_12 {

	public static void main(String[] args) {

		System.out.print("Please enter a string: ");
		// declare new Scanner object
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		// run the reverse method using the string inputted by the user
		reverseDisplay(input);

	}

	private static void reverseDisplay(String value) {
		reverseDisplay(value, value.length()); // calls helper method
	}

	// helper method
	private static void reverseDisplay(String value, int high) {
		
		if (high != 0) { // base case is if high = 0, recursion stops
			System.out.print(value.substring(high - 1)); //prints last character of user's string
			reverseDisplay(value.substring(0, high - 1)); // calls method again but excludes last character
		}
	}
}