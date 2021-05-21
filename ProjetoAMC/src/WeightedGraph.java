import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

	public class WeightedGraph {
			
		private int dim;
		private double[][] ma;
		
		public WeightedGraph(int dim) {
			
			this.dim = dim;
			this.ma = new double[dim][dim];
			
			for (int i = 0; i < dim; i++) {
				for (int j = 0; j < dim; j++) ma[i][j] = -1.0; // nao ter peso == -1.0
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
		
		
		public Tree MST() {  
			
	
			Tree maximal = new Tree(dim); 
			
		
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
						C[no] = i; // novo n� "C[no]" a partir do qual se vai continuar a MST
					}
				}
				maximal.addEdge(no, C[no]); //adicionar a MST a aresta com o peso m�ximo, em que o pai � o n� "no" e o filho o "C[no]"
			}
			
			return maximal;
		}

	}
