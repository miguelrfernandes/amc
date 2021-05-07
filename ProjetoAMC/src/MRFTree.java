import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MRFTree {
	// consideramos como no de partida o primeiro no (no 0)
	private int[] e = new int[2]; // aresta especial
	private int mc; // dimensao da fibra do dataset
	private int m; // tamanho do dataset
	private int n; // numero de medicoes
	private int[] D; // dimensao do dominio do dataset
	private double delta = 0.2; // pseudo-contagem
	private ArrayList<int[]> E; // arestas
	private Dataset tfiber;
	private WeightedTree markovtree;
	
	// Metodo Construtor que recebe uma arvore (um grafo em forma de arvore), e um dataset (fibras) e coloca os phi(xi, xj) em cada aresta da arvore.
	public MRFTree(Tree arvore, Dataset tfiber) { // arvore provem do MST do Weightedgraph
		
		this.tfiber = tfiber;
		this.E = new ArrayList<int[]>();
		this.D = new int[n];
		n = tfiber.getN();
		mc = tfiber.data.size();
		D = tfiber.getD();
		markovtree = new WeightedTree(n);
		
		
		// fixa-se o primeiro no como um no da aresta especial
		e[0] = 0;
		
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
				if (!visited[i] && arvore.EdgeQ(i, o)) { // se o no i e filho do no o
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
				+ ", delta=" + delta + ",\n E=" + Arrays.deepToString(E.toArray()) + ",\n tfiber=" + tfiber + ",\n markovtree=" + markovtree + "]";
	}
	
	// metodo para calculo do phi de uma aresta
	public double[][] phi(int i, int j) {  
		double[][] phiv = new double[D[i]+1][D[j]+1]; 
		
		if (i == e[0] && j == e[1]) { // verifica se esta e a aresta especial
			for (int xi = 0; xi <= D[i]; xi++) {
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
					int[] vals = {xi, xj};
					int[] var = {i};
					int[] val = {xi};
					phiv[xi][xj] = (tfiber.Count(vars, vals) + delta) / (tfiber.Count(var, val) + delta * D[j]);
				}
			}
		}
		return phiv;
	}
	
	// Prob: dado um vetor de dados (x1, ..., xn) retorna a probabilidade destes dados no dataset - ou seja, P_Mc
	public double prob(int[] v) {
		double r;
		r = 1;
		boolean c = true;
		for (int i = 0; i < n && c; i++) {
			c = v[i] <= D[i];
		}
		if (c) {
			for (int[] a : E) {
				r = r * markovtree.getWeight(a[0],a[1])[v[a[0]]][v[a[1]]];
			}
			return r;
		} else {
			System.out.println(Arrays.toString(v) + " (>) " + Arrays.toString(D));
			throw new AssertionError("Valor fora do dominio");
		}
	}
	
	
}
