import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MRFTree {
	// consideramos como no de partida o primeiro no (no 0)
	int[] e = new int[2]; // aresta especial
	int mc; // dimensao da fibra do dataset
	int m; // tamanho do dataset
	int n; // numero de medicoes
	int[] D; // dimensao do dominio do dataset
	double delta = 0.2; // pseudo-contagem
	ArrayList<int[]> E; // arestas
	Dataset tfiber;
	WeightedTree markovtree;
	
	// Metodo Construtor que recebe uma arvore (um grafo em forma de arvore), e um dataset (fibras) e coloca os phi(xi, xj) em cada aresta da arvore.
	public MRFTree(Tree arvore, Dataset tfiber) { // �rvore prov�m do MST do Weightedgraph
		
		this.tfiber = tfiber;
		this.E = new ArrayList<int[]>();
		this.D = new int[n];
		
		n = tfiber.getN();
		mc = tfiber.data.size();
		
		
		markovtree = new WeightedTree(n);
		
		
		// fixa-se o primeiro no da aresta especial como o no que nao tem pai
		// isto nao funciona
		e[0] = 0;
		// while (arvore.pai(e[0]) != -1) e[0] = arvore.pai(e[0]);
		
		// criamos uma fila que vamos usar de seguida
		Queue<Integer> q = new LinkedList<Integer>();
		
		// adicionamos o primeiro no a fila
		q.add(e[0]);
		
		// adicionamos as arestas segundo a direcao definida pelo no fixado
		
		boolean[] visited = new boolean[n];
		
		// usamos uma condicao para fazer um passo especial a primeira vez que encontra um filho ou pai deste no que foi fixado
		boolean especial = true;
		while (!q.isEmpty()) {
			int o = q.remove();
			// TODO podemos aumentar a eficiencia ao nao ver os nos que ja foram vistos
			for (int i = 0; i < n; i++) { // para cada no da arvore
				if (arvore.EdgeQ(i, o) && !visited[i]) { // se o no i e filho do no o
					if (especial) {
						// fixa-se a aresta especial como a aresta que liga o no definido anteriormente ao no de menor ordem (que esteja ligado)
						e[1] = i;
						especial = false;
					}
					
					// adiciona-se esta aresta a lista de arestas
					E.add(new int[] {o, i});
					
					// e adiciona-se este no a queue
					q.add(i);
					
					// calcula-se o phi para esta aresta
					// e guarda-se os resultados deste calculo numa matriz
					// com os diferentes valores de phi(xi,xj) nesta aresta da arvore
					markovtree.Add(o, i, phi(o,i));
				}
			}
			visited[o] = true;
		}
	}
	
	
	
	@Override
	public String toString() {
		return "MRFTree [Aresta especial e =" + Arrays.toString(e) + ", mc=" + mc + ", m=" + m + ", n=" + n + ", D=" + Arrays.toString(D)
				+ ", delta=" + delta + ", E=" + Arrays.deepToString(E.toArray()) + ", tfiber=" + tfiber + ", markovtree=" + markovtree + "]";
	}



	public int getDatabaseSize() {
		return m;
	}

	public double[][] phi(int i, int j) {  // metodo para calculo do phi de uma aresta
		double[][] phiv = new double[D[i]+1][D[j]+2];
		
		if (i == e[0] && j == e[1]) { // verifica se esta e a aresta especial
			for (int xi = 0; xi <= D[i]; xi++) {//Bea: acho que aqui os valores de xi e xj devem estar majorados n�o por n mas por Di e Dj, ou n�o? Porque s�o valores e Dj pode ser maior que n.
				for (int xj = 0; xj <= D[j]; xj++) {
					int[] vars = {i, j};
					int[] vals = {xi, xj};
					phiv[xi][xj] = (tfiber.Count(vars, vals) + delta) / (mc + delta * D[i] * D[j]);
				}
			}
		} else { // esta nao e a aresta especial
			for (int xi = 0; xi <= D[i]; xi++) {
				for (int xj = 0; xj <= D[j]; xj++) {
					int[] vars = {i, j};
					int[] vals = {xi, xj};	//Bea: acho que aqui os valores de xi e xj devem estar majorados n�o por n mas por Di e Dj, ou n�o? Porque s�o valores e Dj pode ser maior que n.
					int[] var = {i};
					int[] val = {xi};
					phiv[xi][xj] = (tfiber.Count(vars, vals) + delta) / (tfiber.Count(var, val) + delta * D[j]);
				}
			}
		}
		return phiv;
	}
	
	// Prob: dado um vetor de dados (x1, ..., xn) retorna a probabilidade destes dados no dataset - ou seja, P_Mc
	public double prob(int[] v) {// acrescentar erro para o caso de valor da variavel n�o estar no dominio
		double r;
		r = 1;
		// System.out.println(E.toString());
		boolean c = true;
		for (int i = 0; i < n && c; i++) {
			c = v[i] < D[i];
		}
		if (c) {
			for (int[] a : E) {
				System.out.println(Arrays.deepToString(markovtree.getWeight(a[0],a[1])));
				r = r * markovtree.getWeight(a[0],a[1])[v[a[0]]][v[a[1]]];
			}
			return r;
		} else {
			throw new AssertionError("valor fora do dominio");
		}
	}
	
	
}
