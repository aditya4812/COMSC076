
//Exercise 22.4 by Aditya Mehta

import java.util.Scanner;

public class Exercise22_4 {
	public static void main(String[] args) {
		Scanner scan;
		scan = new Scanner(System.in);
		// ask user for strings
		System.out.print("Enter a string s1: ");
		String s1 = scan.nextLine();
		System.out.print("Enter a string s2: ");
		String s2 = scan.nextLine();
		boolean same = false; // is s2 substring of s1?
		int index = -1; // string index
		int match = 0; // number of matched char

		// calculate matches
		int x = 0;
		while (x < s1.length()) {
			if (match == s2.length()) {
				same = true;
				break;
			}
			if (s1.charAt(x) == s2.charAt(match)) {
				if (match == 0) {
					index = x;
				}
				match++;
			}

			if (s1.charAt(x) != s2.charAt(match)) {
				match = 0;
			}

			x++;
		}

		// print result
		if (same == false) {
			System.out.println("\"" + s2 + "\" is not a substring of \"" + s1 + "\"" + ".");
		} else {
			System.out.println("same at index " + index);
		}

	}
}