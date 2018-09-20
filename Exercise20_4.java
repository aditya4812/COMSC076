import java.util.Comparator;
import java.util.Arrays;

//Exercise 20.4 by Aditya Mehta

public class Exercise20_4 {

	public static void main(String[] args) {

		// randomly generate 100 points
		Point[] p = new Point[100];
		int x = 0;
		while (x < p.length) {
			p[x] = new Point();
			p[x].x = (Math.random() * 100);
			p[x].y = (Math.random() * 100);
			x++;
		}
		// calls the sort method, and prints the sorted x values
		Arrays.sort(p);
		for (Point po : p) {
			System.out.println(po.toString());
		}

		// calls the sort method, and prints the sorted y values
		Arrays.sort(p, new CompareY());
		for (Point po : p) {
			System.out.println(po.toString());
		}

	}
}

class Point implements Comparable<Point> { // compares x coordinates; if x is
											// same, compares y coordinates

	public double x;
	public double y;

	@Override
	public int compareTo(Point p1) {

		if (x > p1.x) {
			return 1;
		} else if (x < p1.x) {
			return -1;
		} else if (y > p1.y) {
			return 1;
		} else if (y < p1.y) {
			return -1;
		} else {
			return 0;
		}
	}

	@Override
	public String toString() { // custom toString method
		return "(" + x + ", " + y + ")";
	}
}

class CompareY implements Comparator<Point> { // compares y coordinates; if y is
												// same, compares x coordinates

	@Override
	public int compare(Point p2, Point p3) {

		if (p2.y > p3.y) {
			return 1;
		} else if (p2.y < p3.y) {
			return -1;
		} else if (p2.x > p3.x) {
			return 1;
		} else if (p2.x < p3.x) {
			return -1;
		} else {
			return 0;
		}
	}

}
