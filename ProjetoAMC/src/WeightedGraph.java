import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

	public class WeightedGraph {
			
		private int dim;
		private double[][] ma;
		
		public WeightedGraph(int dim) {
			
			this.dim = dim;
			this.ma = new double[dim][dim];
		
		}
		/*
		public WeightedGraph(int dim, Dataset T) {  // construcao grafo completo e pesado - PASSO 1 
													// Está correto.
			this.dim = dim;
			this.ma = new double[dim][dim];
				
			for (int i = 0; i < dim; i++) { // ciclo para atribuir peso a cada aresta entre variavel i e variavel j
				for (int j = 0; j < dim; j++) {
					// calculamos a informacao mutua de cada variavel e guardamos
					// este valor na aresta deste grafo pesado completo
					double I = 0;
					
					int m = dim;
					// PASSO 2 - alterei as variaveis. acho que fica mais percetivel assim, de acordo com o enunciado
					for (int xi = 0; xi < dim; xi++) { // ciclo que calcula o I, tendo em conta o dataset T
						for (int xj = 0; xj < dim; xj++) { 
							double prxixj = T.Count(new int[] {i,j}, new int[] {xi, xj}) / m;  
							double prxi = T.Count(new int[] {i}, new int[] {xi}) / m; 
							double prxj = T.Count(new int[] {j}, new int[] {xj}) / m; 
							if (prxixj == 0 && (prxixj / (prxi * prxj)) == 0) { // indeterminacao 0*-inf
								I = I + 0;
							}
							else {
								I = I + prxixj * Math.log(prxixj / (prxi * prxj)); // correto
							}
						}
					}
					this.ma[i][j] = I; // atribuir peso I a cada aresta entre i e j
				}
			}
		}
		*/		
		
		public int getDim() {
			return this.dim;
		}
		
		public String toString() {  
			String r = "Weighted Graph\n";
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
					this.ma[i][j] = w;
					this.ma[j][i] = w;
				} else {
					throw new AssertionError("node not in graph");
			} 
		}
	
		
		public double getWeight(int i, int j) {
			if (i >= this.dim || j >= this.dim){
				throw new AssertionError("node not in graph");
	}	
			else{
					return ma[i][j];		}
		}
		
		// MST() está correta e funciona -> ver exemplo no main
		// faria sentido retornar uma WeightedTree e nao uma Tree?
		public Tree MST() {  // arvore de extensao maxima - soma dos pesos das arestas e maximal
			Tree maximal = new Tree(dim); //dim nao esta correto, e o numero de vertices //nao percebo, faz me sentido como esta
			// determina a MST com o Algoritmo de Prim
			
			// lista dos custos maximos para cada no
			int[] C = new int[dim];
			// inicializamos todos os custos maximos a -inf
			// definindo -inf como -99
			for (int i = 0; i < C.length; i++) C[i] = -99;
			
			// lista da aresta com esse custo maximo para no 
			int[] E = new int[dim];
			// inicializamos todas as arestas a null
			// definimos null como -1, uma vez que nao ha nenhuma aresta -1
			for (int i = 0; i < E.length; i++) E[i] = -1;
			
			// define-se o no 0 como o no inicial
			C[0] = 0;
			
			Queue<Integer> Q = new LinkedList<Integer>();
			
			for (int i = 1; i < dim; i++) Q.add(i); 
			
			boolean[] visited = new boolean[dim];
			for (int i = 0; i < visited.length; i++) visited[i] = false;
			
			while (!Q.isEmpty()) {
				int no = Q.remove();
				double max = C[no]; // C = [0,-99,-99,-99,...]
				for (int i = 0; i < dim; i++) {
					if (!visited[i] && this.getWeight(i, no) > max) {
						max = this.getWeight(i, no); // novo peso maximo
						C[no] = i; // novo nó "C[no]" a partir do qual se vai continuar a MST
					}
				}
				maximal.addEdge(no, C[no]); //adicionar a MST a aresta com o peso máximo, em que o pai é o nó "no" e o filho o "C[no]"
			}
			
			return maximal;
		}

	}
