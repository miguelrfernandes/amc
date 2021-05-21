import java.util.ArrayList;
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
		
		
		public Tree MST() {  // determina a MST com o Algoritmo de Prim
			Tree maximal = new Tree(dim); 
			
			// lista dos custos maximos para cada no
			// nao e necessario para o nosso projeto
			double[] C = new double[dim];
			
			// lista da aresta com esse custo maximo para no 
			int[] E = new int[dim];
			// inicializamos todas as arestas a null
			// definimos null como -1, uma vez que nao ha nenhuma aresta -1
			for (int i = 0; i < E.length; i++) E[i] = -1;
						
			
			// inicializamos todos os custos maximos a -inf
			// definindo -inf como int min_value
			for (int i = 0; i < C.length; i++) C[i] = Integer.MIN_VALUE;
			
			boolean[] visited = new boolean[dim];
			for (int i = 0; i < visited.length; i++) visited[i] = false;
			
			for (int i = 0; i < dim; i++) {
				for (int j = i; j < dim; j++) { // pode ser j = i? ou tem de ser j = 0
					if (!visited[i] && this.getWeight(i, j) > C[i]) {
						C[i] = this.getWeight(i, j); // novo peso maximo
						E[i] = i;
					}
				}
			}
			
			// define-se o no 0 como o no inicial u
			C[0] = 0;
			
			// adicionamos todos os nos exceto u a lista
			Queue<Integer> Q = new LinkedList<Integer>();
			for (int i = 1; i < dim; i++) Q.add(i); 
			
			// repomos a listas de visitados
			for (int i = 0; i < visited.length; i++) visited[i] = false;
			
			ArrayList<Integer> F = new ArrayList<Integer>();
			F.add(0);
			
			while (!Q.isEmpty()) {
				//int x 
				double max = Double.NEGATIVE_INFINITY;
				int[] e = new int[2]; // aresta maximal
				for (int x : F) {
					for (int v :Q) {
						if (this.getWeight(x, v) > max) {
							e = new int[] {x,v};
						}
					}
				}
				Q.remove(e[0]); //F.add(v);
				E[e[1]]=e[0]; // C[v] = g.weight(e)
				F.add(e[1]);
				maximal.addEdge(e[0], e[1]);
			}
			/*
			while (!Q.isEmpty()) {
				int no = Q.remove();
				double max = C[no]; // C = [0,-inf,-inf,-inf,...]
				for (int i = 0; i < dim; i++) {
					if (!visited[i] && this.getWeight(i, no) > max) {
						max = this.getWeight(i, no); // novo peso maximo
						C[no] = i; // novo n� "C[no]" a partir do qual se vai continuar a MST
					}
				}
				maximal.addEdge(no, C[no]); //adicionar a MST a aresta com o peso m�ximo, em que o pai � o n� "no" e o filho o "C[no]"
			}
			*/
			
			return maximal;
		}

	}
