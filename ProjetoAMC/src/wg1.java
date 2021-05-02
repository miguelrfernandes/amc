
public class wg1 {
	double[][][][] ma;
	int m;
	int n;
	
	public wg1(int m, int n) {
		this.m = m;
		this.n = n;
		ma = new double[m][m][n][n];
	}
	
	public void addEdge(int origem, int destino, double[][] peso) {
		ma[origem][destino] = peso;
	}
}
