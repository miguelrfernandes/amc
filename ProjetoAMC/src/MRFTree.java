import java.util.ArrayList;

public class MRFTree {
	// consideramos como no de partida o primeiro no (no 0)
	int[] e; // aresta especial
	int m; // tamanho do dataset
	
	
	// Método Construtor que recebe uma arvore (um grafo em forma de arvore), e um dataset e coloca os 𝜙(𝑥#, 𝑥$) em cada arvore.
	public MRFTree(Tree arvore, ArrayList<int[]> tfiber) {
		m = tfiber.size();
		// fixa-se a aresta especial e como a primeira aresta do no 0
		int e = 0;
		// adiciona-se uma matriz com os valor de phi(x_i, x_j) a cada aresta da árvore
		for (int i = 0, i < m, i++) {
			g.addEdge(0, 0, phi(e)); // alterar para .add
		}
	}
	
	X = (1,5,2,6,7,3,5)
	filter(X, x%2 == 0)
	
	public int getDatabaseSize(){
		return m;
	}
	
	public double[][] phi(double i) { 
		return i; // TODO, não está feito
	}
	
	
	public double size() {
		return m;
	}

	// Prob: dado um vetor de dados 𝑥1, ... , 𝑥( retorna a probabilidade destes dados no dataset.
	public double prob(int[] v) {
		//return phi[v[0]] + phi[v[1]]; //não faço ideia
		return -1.0;
	}
}
