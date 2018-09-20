import java.io.FileReader;
import java.util.*;

//Exercise 28.1 by Aditya Mehta

public class UnweightedGraph<V> extends AbstractGraph<V> {

	public UnweightedGraph() {
	}

	public UnweightedGraph(V[] vertices, int[][] edges) {
		super(vertices, edges);
	}

	public UnweightedGraph(List<V> vertices, List<Edge> edges) {
		super(vertices, edges);
	}

	public UnweightedGraph(List<Edge> edges, int numberOfVertices) {
		super(edges, numberOfVertices);
	}

	public UnweightedGraph(int[][] edges, int numberOfVertices) {
		super(edges, numberOfVertices);
	}

	public static void main(String[] args) throws Exception {

		Scanner input = new Scanner(new FileReader("hi.txt"));
		int NUMBER_OF_VERTICES = input.nextInt();

		ArrayList<AbstractGraph.Edge> edgeList = new ArrayList<>();

		String[] vertices = new String[NUMBER_OF_VERTICES];

		input.nextLine();
		for (int j = 0; j < NUMBER_OF_VERTICES; j++) {
			String s = input.nextLine();
			String[] line = s.split("[\\s+]");
			String u = line[0];
			vertices[j] = u;

			for (int i = 1; i < line.length; i++) {
				edgeList.add(new AbstractGraph.Edge(Integer.parseInt(u), Integer.parseInt(line[i])));
			}
		}

		Graph<String> graph = new UnweightedGraph<>(Arrays.asList(vertices), edgeList);

		System.out.println("The number of vertices is " + graph.getSize());

		for (int u = 0; u < NUMBER_OF_VERTICES; u++) {
			System.out.print("Vertex " + graph.getVertex(u) + ":");
			for (Integer e : graph.getNeighbors(u))
				System.out.print(" (" + u + ", " + e + ")");
			System.out.println();
		}

		// Obtain an instance tree of AbstractGraph.Tree
		AbstractGraph.Tree tree = graph.dfs(0);

		// Test if graph is connected and print results
		System.out.println(
				"The graph is " + (tree.getNumberOfVerticesFound() != graph.getSize() ? "not " : "") + "connected");

		// Close the file
		input.close();
	}
}