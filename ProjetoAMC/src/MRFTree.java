import java.util.ArrayList;

public class MRFTree {
	// consideramos como no de partida o primeiro no (no 0)
	int[] e; // aresta especial
	int m; // tamanho do dataset
	int n;
	int[] D;
	double delta = 0.2;
	Dataset ds;
	
	// Método Construtor que recebe uma arvore (um grafo em forma de arvore), e um dataset e coloca os 𝜙(𝑥#, 𝑥$) em cada arvore.
	public MRFTree(Tree arvore, Dataset ds, int c) {
		ArrayList<int[]> tfiber = ds.Fiber(c);
		n = tfiber.get(0).length;
		D = ds.getVar();
		
		WeightedTree markovtree = new WeightedTree(n);
		e = new int[2];
		e[0] = 0;
		e[1] = 0;
		int d = 1;
		while (e[1] == 0) {
			if (arvore.EdgeQ(0, d)) e[1] = d;
			d++;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				markovtree.Add(i, j, phi(i,j)); // i j?
			}
		}
		
		// m= tfiber.size();
		// fixa-se a aresta especial e como a primeira aresta do no 0
		// int e = 0;
		// adiciona-se uma matriz com os valor de phi(x_i, x_j) a cada aresta da árvore
		/* for (int i = 0, i < m, i++) {
			g.addEdge(0, 0, phi(e)); // alterar para .add
		}*/
	}
	
	// X = (1,5,2,6,7,3,5)
	// filter(X, x%2 == 0)
	
	public int getDatabaseSize(){
		return m;
	}
	
	public double[][] phi(int k, int l) { 
		double[][] r = new double[n][n];
		if (e[0] == k && e[1] == l) { // verifica se esta e a aresta especial
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					r[k][l] = (ds.co + delta) / (mc + delta * D[i] * D[j]);
				}
			}
		} else {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					r[k][l] = (count + delta) / (mc + delta * D[i] * D[j]);
				}
			}
		}
		return r; // TODO, não está feito
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
