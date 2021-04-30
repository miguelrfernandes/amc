
public class MRFTree {
	int[][] phi;
	int[] e;
	
	// Método Construtor que recebe uma árvore, e um dataset e coloca os 𝜙(𝑥#, 𝑥$) em cada árvore.
	public MRFTree(wg g, Dataset1 dataset) {
		// fixa-se a aresta especial e como a primeira aresta do nó 0
		
		g.addEdge(0, 0, 0);
	}
	
	public double phi(double i, double j) {
		return i;
	}

	// Prob: dado um vetor de dados 𝑥1, ... , 𝑥( retorna a probabilidade destes dados no dataset.
	public double prob(int[] v) {
		//return phi[v[0]] + phi[v[1]]; //não faço ideia
		return -1.0;
	}
}
