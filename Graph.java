public interface Graph<V> {

	public int getSize();

	public java.util.List<V> getVertices();

	public V getVertex(int index);

	public int getIndex(V v);

	public java.util.List<Integer> getNeighbors(int index);

	public int getDegree(int v);

	public void printEdges();

	public void clear();

	public boolean addVertex(V vertex);

	public boolean addEdge(int u, int v);

	public AbstractGraph<V>.Tree dfs(int v);

	public AbstractGraph<V>.Tree bfs(int v);
}