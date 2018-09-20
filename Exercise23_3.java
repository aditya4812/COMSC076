
// Exercise 23.3 by Aditya Mehta


import java.util.Comparator;

public class Exercise23_3 {

	public static <E> void quickSort(E[] data, Comparator<? super E> comparator) {
		quickSort(data, 0, data.length - 1, comparator);
	}

	public static <E> void quickSort(E[] data, int start, int end, Comparator<? super E> comparator) {
		if (start < end) {
			int index = partition(data, start, end, comparator);
			quickSort(data, start, index - 1, comparator);
			quickSort(data, index + 1, end, comparator);
		}
	}

	public static <E> int partition(E[] data, int start, int end, Comparator<? super E> comparator) {
		E p = data[start];
		int low = start++;
		int high = end;

		while (low < high) {

			while (low <= high && comparator.compare(data[low], p) <= 0) {
				low = low + 1;
			}
			while (comparator.compare(data[high], p) > 0 && low <= high)
				high = high - 1;

			if (low < high) {
				E temp = data[low];
				data[low] = data[high];
				data[high] = temp;
			}
		}

		while (high > start && comparator.compare(data[high], p) >= 0) {
			high = high - 1;
		}
		if (!(0 < comparator.compare(p, data[high]))) {
			return start;
		} else {
			data[start] = data[high];
			data[high] = p;
			return high;
		}
	}

	public static <E extends Comparable<E>> void quickSort(E[] data) {
		quickSort(data, 0, data.length - 1);
	}

	public static <E extends Comparable<E>> void quickSort(E[] data, int start, int end) {
		if (!(end < start)) {
			int index = partition(data, start, end);
			quickSort(data, start, index - 1);
			quickSort(data, index + 1, end);
		}
	}

	public static <E extends Comparable<E>> int partition(E[] data, int start, int end) {
		E p = data[start];
		int high = end;
		int low = start++;

		while (high > low) {
			while (data[low].compareTo(p) <= 0 && low <= high) {
				low = low + 1;
			}

			while (data[high].compareTo(p) > 0 && low <= high) {
				high = high - 1;
			}

			if (!(low > high)) {
				E temp = data[low];
				data[low] = data[high];
				data[high] = temp;
			}
		}

		while (data[high].compareTo(p) >= 0 && start < high) {
			high = high - 1;
		}

		if (0 < p.compareTo(data[high])) {
			data[start] = data[high];
			data[high] = p;
			return high;
		} else {
			return start;
		}
	}
}