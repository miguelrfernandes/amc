
public class Tree {
	int[] pais;
	
	public Tree(int dim) {
		pais = new int[dim];
	}
	
	public void addEdge(int i, int pai) {
		pais[i] = pai;
	}
}
