// import java.util.ArrayList;

public class MRFTree {
	// consideramos como no de partida o primeiro no (no 0)
	int[] e; // aresta especial
	int mc;
	int m; // tamanho do dataset
	int n;
	int[] D;
	double delta = 0.2;
	Dataset ds;
	Dataset tfiber;
	
	// Método Construtor que recebe uma arvore (um grafo em forma de arvore), e um dataset e coloca os 𝜙(𝑥#, 𝑥$) em cada arvore.
	public MRFTree(Tree arvore, Dataset ds, int c) { // trocar para apenas receber o dataset tfiber
		Dataset tfiber = ds.Fiber(c);
		n = tfiber.getN();
		mc = tfiber.data.size();
		
		D = ds.getVar();
		this.ds = ds;
		
		WeightedTree markovtree = new WeightedTree(n);
		
		e = new int[2]; //aresta especial
		e[0] = 0;
		e[1] = 0;
		
		int d = 1;
		while (e[1] == 0) {
			if (arvore.EdgeQ(0, d)) e[1] = d; // a aresta especial e a aresta que liga o no 0 a outra no (sendo este o no minimo...)
			d++;
		}
		
		// adicionamos os phi a cada aresta da nova arvore
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
	
	public double[][] phi(int i, int j) {  // calculo do phi
		double[][] phiv = new double[n][n];
		
		if (i == e[0] && j == e[1]) { // verifica se esta e a aresta especial
			for (int xi = 0; xi < n; xi++) {
				for (int xj = 0; xj < n; xj++) {
					int[] vars = {i, j};
					int[] vals = {xi, xj};					         ;
					phiv[xi][xj] = (tfiber.Count(vars, vals) + delta) / (mc + delta * D[i] * D[j]);
				}
			}
		} else { // esta nao e a aresta especial
			for (int xi = 0; xi < n; xi++) {
				for (int xj = 0; xj < n; xj++) {
					int[] vars = {i, j};
					int[] vals = {xi, xj};	
					int[] var = {i};
					int[] val = {xi};
					phiv[xi][xj] = (tfiber.Count(vars, vals) + delta) / (tfiber.Count(var, val) + delta * D[j]);
				}
			}
		}
		return phiv; // TODO, não está feito
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
