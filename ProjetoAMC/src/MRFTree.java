import java.util.ArrayList;

public class MRFTree {
	// consideramos como no de partida o primeiro no (no 0)
	int[] e; // aresta especial
	int mc; // dimensao da fibra do dataset
	int m; // tamanho do dataset
	int n; // numero de medicoes
	int[] D; // dimensao do dominio do dataset
	double delta = 0.2; // pseudo-contagem
	ArrayList<int[]> E; // arestas
	Dataset tfiber;
	WeightedTree markovtree;
	
	// Método Construtor que recebe uma arvore (um grafo em forma de arvore), e um dataset e coloca os 𝜙(𝑥#, 𝑥$) em cada aresta da arvore.
	public MRFTree(Tree arvore, Dataset tfiber) {
		
		n = tfiber.getN();
		mc = tfiber.data.size();
		
		D = tfiber.getVar();
		
		markovtree = new WeightedTree(n);
		
		e = new int[2]; //aresta especial
		e[0] = 0;
		e[1] = 0;
		
		int d = 1;
		while (e[1] == 0) {
			if (arvore.EdgeQ(0, d)) e[1] = d; // fixa-se a aresta especial como a aresta que liga o no 0 a outra no (sendo este o no minimo...)
			d++;
		}
		
		// adicionamos uma matriz com os valores de phi(xi,xj) a cada aresta da nova arvore
		for (int i = 0; i < n; i++) { // for aresta in arvore
			// TODO trocar para apenas as arestas da arvore dada
			for (int j = 0; j < n; j++) {
				markovtree.Add(i, j, phi(i,j));
				int[] a = new int[2];
				a[0] = i;
				a[1] = j;
				E.add(a);
			}
		}
	}

	public double[][] phi(int i, int j) {  // calculo do phi
		double[][] phiv = new double[n][n];
		
		if (i == e[0] && j == e[1]) { // verifica se esta e a aresta especial
			for (int xi = 0; xi < n; xi++) {
				for (int xj = 0; xj < n; xj++) {
					int[] vars = {i, j};
					int[] vals = {xi, xj};
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
		return phiv;
	}
	
	// TODO
	// Prob: dado um vetor de dados 𝑥1, ... , 𝑥( retorna a probabilidade destes dados no dataset.
	public double prob(int[] v) {
		double r;
		r = 1;
		for (int[] a : E) {
			r = r * markovtree.getWeight(a[0],a[1])[v[a[0]]][v[a[1]]];
		}
		return r;
	}
}
