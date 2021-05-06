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
	
	// Metodo Construtor que recebe uma arvore (um grafo em forma de arvore), e um dataset (fibras) e coloca os phi(xi, xj) em cada aresta da arvore.
	public MRFTree(Tree arvore, Dataset tfiber) { // árvore provém do MST do Weightedgraph
		
		this.tfiber = tfiber;
		this.E = new ArrayList<int[]>();
		
		n = tfiber.getN();
		mc = tfiber.data.size();
		
		
		markovtree = new WeightedTree(n);
		
		e = new int[2]; //aresta especial 
		e[0] = 0;
		e[1] = 0;
		
		int d = 1;
		while (e[1] == 0) {
			if (arvore.EdgeQ(0, d)) e[1] = d; // fixa-se a aresta especial como a aresta que liga o no 0 a outra no (sendo este o no minimo...)
			d++; 
		}
		
		// TODO aumentar eficiencia
		// adicionamos uma matriz com os valores de phi(xi,xj) a cada aresta da nova arvore
		for (int i = 0; i < n; i++) { // for aresta in arvore
			for (int j = 0; j < n; j++) {
				if (arvore.EdgeQ(i, j)) {
					markovtree.Add(i, j, phi(i,j));
					int[] a = new int[2];
					a[0] = i;
					a[1] = j;
					E.add(a);
				}
			}
		}
	}
	
	// fazer metodo toString para MRFTree?
	
	public int getDatabaseSize() {
		return m;
	}

	public double[][] phi(int i, int j) {  // calculo do phi
		double[][] phiv = new double[n][n];
		
		if (i == e[0] && j == e[1]) { // verifica se esta e a aresta especial
			for (int xi = 0; xi < n; xi++) {//Bea: acho que aqui os valores de xi e xj devem estar majorados não por n mas por Di e Dj, ou não? Porque são valores e Dj pode ser maior que n.
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
					int[] vals = {xi, xj};	//Bea: acho que aqui os valores de xi e xj devem estar majorados não por n mas por Di e Dj, ou não? Porque são valores e Dj pode ser maior que n.
					int[] var = {i};
					int[] val = {xi};
					phiv[xi][xj] = (tfiber.Count(vars, vals) + delta) / (tfiber.Count(var, val) + delta * D[j]);
				}
			}
		}
		return phiv;
	}
	
	// Prob: dado um vetor de dados (x1, ..., xn) retorna a probabilidade destes dados no dataset - ou seja, P_Mc
	public double prob(int[] v) {// acrescentar erro para o caso de valor da variavel não estar no dominio
		double r;
		r = 1;
		for (int[] a : E) {
			r = r * markovtree.getWeight(a[0],a[1])[v[a[0]]][v[a[1]]]; 
		}
		return r;
	}
	
	
}
