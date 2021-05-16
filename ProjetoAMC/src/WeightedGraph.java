import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

	public class WeightedGraph {
			
		private int dim;
		private double [][] ma;
		
		public WeightedGraph(int dim) {
			
			this.dim=dim;
			this.ma = new double[dim][dim];}
		
		public WeightedGraph(int dim, Dataset T) {
			
			this.dim=dim;
			this.ma = new double[dim][dim];
			
			for (int k = 0; k < dim; k++) {
				for (int l = 0; l < dim; l++) {
					// calculamos a informação mútua de cada variável e guardamos
					// este valor na aresta deste grafo pesado completo
					double I = 0.0;
					
					int m = dim;// ?
					
					for (int i = 0; i < dim; i++) { //TODO verificar a matematica
						for (int j = 0; j < dim; j++) {
							double prxixj = T.Count(new int[] {k,l}, new int[] {i, j}) / m; // ?? ou i j   k l??
							double prxi = T.Count(new int[] {k}, new int[] {i}) / m; // ?? ou i k??
							double prxj = T.Count(new int[] {l}, new int[] {j}) / m; // ?? ou j l??
							if (prxixj == 0 && (prxixj / (prxi * prxj)) == 0) {
								I = I + 0;
							} else {
								I = I + prxixj * Math.log(prxixj / (prxi * prxj));
							}
						}
					}
					this.ma[k][l] = I;
				}
			}
		}
				
		
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
		
		// TODO verificar se está correto
		public Tree MST() {  // arvore de extensao maxima
			Tree maximal = new Tree(dim); //dim nao esta correto, e o numero de vertices
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
				double max = C[no];
				for (int i = 0; i < dim; i++) {
					if (!visited[i] && this.getWeight(i, no) > max) {
						max = this.getWeight(i, no);
						C[no] = i;
					}
				}
				maximal.addEdge(no, C[no]);
			}
			
			return maximal;
		}

	}
