
// Exercise 21.8 by Aditya Mehta

import java.util.*;
import java.io.*;

public class Excercise21_8 {

	private static void store(Map<String, Integer> map, String[] str) {
		// method to sort the words in a set
		int x = 0;
		while (x < str.length) {
			String s = str[x].toLowerCase();

			if (s.length() > 0 && Character.isLetter(s.charAt(0))) {
				if (!map.containsKey(s)) {
					map.put(s, 1);
				} else {
					int value = map.get(s);
					value++;
					map.put(s, value);
				}
			}
			x++;
		}
	}

	public static void main(String[] args) throws Exception {
		// makes sure file exists
		File file = new File(args[0]);
		if (file.exists() == false) {
			System.out.println("The file " + args[0] + " doesn't exist.");
			System.exit(1);
		}

		if (args.length != 1) {
			System.exit(1);
		}

		Map<String, Integer> map = new TreeMap<>();

		try (Scanner scan = new Scanner(file);) {
			while (scan.hasNext()) {
				String[] words = scan.nextLine().split("[ \n\t\r\"\'.,;:!?()]");
				store(map, words);
			}
		}

		// put all values into a set
		Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
		// get value and key of each entry
		for (Map.Entry<String, Integer> entry : entrySet)
			System.out.println(entry.getValue() + "\t" + entry.getKey());
	}

}