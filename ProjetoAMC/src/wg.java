
public class wg {
	int[][] ma;
	int dim;
	
	public wg(int dim) {
		this.dim = dim;
		ma = new int[dim][dim];
	}
	
	public void addEdge(int origem, int destino, int peso) {
		ma[origem][destino] = peso;
	}
}
