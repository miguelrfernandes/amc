import java.util.Arrays;

	public class WeightedGraph {
			
		int dim;
		double [][] ma;
		
		public WeightedGraph(int dim) {
			
			this.dim=dim;
			this.ma = new double[dim][dim];}
				
		
		public int getDim() {
			return this.dim;
		}
		
		public void print() {  // para melhor visualizacao do output
			System.out.println("Weighted Graph");
			for (int i = 0; i < dim; i++) {
				if (i==0 && dim != 1) {
					System.out.println("[" + Arrays.toString(this.ma[0]) + ",");
				}
				if (i==0 && dim == 1) {
					System.out.println("[" + Arrays.toString(this.ma[0]) + "]");
				}
				if (i !=0 && i == dim - 1) {
					System.out.println(Arrays.toString(this.ma[i]) + "]");
				}
				if (i != 0 && i != dim - 1){
					System.out.println(Arrays.toString(this.ma[i]) + ",");
				}
			}
		}
		//como grafo � completo n�o � necess�rio questionar se existe aresta entre dois n�s pois haver�		
		
		public void Add(int i, int j, double w){
				if (i>=0 && i<this.dim && j>=0 && j<this.dim) {
					this.ma[i][j] = w;
					this.ma[j][i] = w;// apesar de direcionado talvez seja melhor colocar aqui tb pois para pesquisas futuras pode dar jeito
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
		
		/*
		public Tree MST() {  // arvore de extensao maxima
			Tree maximal = new Tree(dim); //dim nao esta correto, e o numero de vertices
			// determina a MST
			return maximal;
		}*/

	}
