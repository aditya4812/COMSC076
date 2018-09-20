
// Exercise 19.9 by Aditya Mehta

import java.util.ArrayList;

public class Exercise19_9 {

	public static <E extends Comparable<E>> void sort(ArrayList<E> list) {
		// loops through entire ArrayList and sorts it from smallest to largest or A-Z
		int x = 0;
		while (x < list.size() - 1) {

			E currentMinimum = list.get(x);
			int newMinimum = x;
			int y = x + 1;
			while (y < list.size()) {

				if (list.get(y).compareTo(currentMinimum) < 0) {
					currentMinimum = list.get(y);
					newMinimum = y;
				}
				y++;
			}

			if (newMinimum != x) {
				list.set(newMinimum, list.get(x));
				list.set(x, currentMinimum);
			}
			x++;
		}
	}

	public static void main(String[] args) {
		// sorts list of random integers
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add((int) (Math.random() * 10));
		}
		System.out.println("Integer List:");
		System.out.println(list);// original list
		sort(list);
		System.out.println(list);// new list

		// sorts list of random doubles
		ArrayList<Double> list2 = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list2.add(Math.random() * 10);
		}
		System.out.println("Double List:");
		System.out.println(list2);// original list
		sort(list2);
		System.out.println(list2);// new list

		// sorts list of names alphabetically
		ArrayList<String> list3 = new ArrayList<>();
		// add names into ArrayList not sorted alphabetically
		list3.add("Charles");
		list3.add("Jones");
		list3.add("Frank");
		list3.add("Adam");
		list3.add("Harry");
		list3.add("Bob");
		list3.add("Dan");
		list3.add("Kyle");
		list3.add("Earl");
		list3.add("George");
		System.out.println("String List:");
		System.out.println(list3); // original list
		sort(list3); // sorts the names alphabetically
		System.out.println(list3); // new list

	}

}