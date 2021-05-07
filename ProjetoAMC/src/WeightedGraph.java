import java.util.Arrays;

	public class WeightedGraph {
			
		private int dim;
		private double [][] ma;
		
		public WeightedGraph(int dim) {
			
			this.dim=dim;
			this.ma = new double[dim][dim];}
				
		
		public int getDim() {
			return this.dim;
		}
		
		public String toString() {  // para melhor visualizacao do output
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
		//como grafo e completo nao e necessario questionar se existe aresta entre dois nos pois havera		
		
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
