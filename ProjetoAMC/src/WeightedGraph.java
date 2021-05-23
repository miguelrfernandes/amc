import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

	public class WeightedGraph { // grafo pesado
		private int dim;
		private double[][] ma;
		
		public WeightedGraph(int dim) { // metodo construtor
			this.dim = dim;
			this.ma = new double[dim][dim];
			
			for (int i = 0; i < dim; i++) {
				for (int j = i; j < dim; j++) ma[i][j] = Double.NEGATIVE_INFINITY; // nao ter peso e definido como peso igual a Double.NEGATIVE_INFINITY
			}
		}
		
		public int getDim() {
			return this.dim;
		}
		
		public String toString() {  
			String r = "Weighted Graph, ma =\n";
			for (int i = 0; i < dim; i++) {
				if (i==0 && dim != 1) {
					r = r + ("[" + Arrays.toString(this.ma[0]) + ",\n");
				}
				if (i==0 && dim == 1) {
					r = r + ("[" + Arrays.toString(this.ma[0]) + "]\n");
				}
				if (i !=0 && i == dim - 1) {
					r = r + (Arrays.toString(this.ma[i]) + "]\n");
				}
				if (i != 0 && i != dim - 1){
					r = r + (Arrays.toString(this.ma[i]) + ",\n");
				}
			}
			return r;
		}
		
		public void Add(int i, int j, double w){
				if (i>=0 && i<this.dim && j>=0 && j<this.dim) {
					if (i<=j) this.ma[i][j] = w;
					else this.ma[j][i] = w;
				} else {
					throw new AssertionError("node not in graph");
			} 
		}
	
		public double getWeight(int i, int j) {
			if (i >= this.dim || j >= this.dim) {
				throw new AssertionError("node not in graph");
			}	
			else {
				if (i<=j) return ma[i][j];
				else return ma[j][i];
			}
		}
		
		public Tree MST() {
			// para ser a maximal e não a minimal, trocamos o sinal de todos os pesos
			for (int i = 0; i < dim; i++) {
				for (int j = 0; j < dim; j++) {
					ma[i][j] = -ma[i][j];
				}
			}
			
			//definimos 0 como a raiz
			Tree maximal = this.MST(0);
			
			// revertemos o que tinhamos feito no inicio
			for (int i = 0; i < dim; i++) {
				for (int j = 0; j < dim; j++) {
					ma[i][j] = -ma[i][j];
				}
			}
			
			return maximal;	
		}
		
		public Tree MST(int r) {  // determina a MST com o Algoritmo de Prim,
			// onde r e a raiz da arvore, a partir da qual vamos comecar

			Tree minimal = new Tree(dim); 
			
			// lista dos custos minimos para cada no
			double[] C = new double[dim];
			
			// lista da aresta com esse custo minimo para no 
			int[] E = new int[dim];
			
			// inicializamos todas as arestas a null, definimos null como -1, uma vez que nao ha nenhuma aresta -1
			// inicializamos todos os custos maximos a +inf
			for (int i = 0; i < dim; i++) {
				E[i] = -1;
				C[i] = Double.POSITIVE_INFINITY;
			}
			
			// define-se o custo do no r como 0 para este ser o primeiro
			C[r] = 0; // E[0] = -1;
			
			// adicionamos todos os nos exceto r a lista
			Queue<Integer> Q = new LinkedList<Integer>();
			for (int i = 0; i < dim; i++) if (i!= r) Q.add(i);
			
			while (!Q.isEmpty()) {
				int u = ExtractMin(Q, C);
				// como o grafo e completo, todos os nos sao adjacentes a u
				for (int v = 0; v < dim; v++) {
					if (Q.contains(v) && this.getWeight(u, v) < C[v]) {
						minimal.addEdge(v, u);
						C[v] = this.getWeight(u, v);
					}
				}
			}
			
			return minimal;
		}
		
		// metodo auxiliar para o metodo MST
		public int ExtractMin(Queue<Integer> Q, double[] C) {
			double min = Double.POSITIVE_INFINITY;
			int r = 0; // o java obriga a que a variavel seja iniciada
			for (int i : Q) {
				if (C[i] < min) {
					min = C[i];
					r = i;
				}
			}
			Q.remove(r);
			return r;
		}
	}
