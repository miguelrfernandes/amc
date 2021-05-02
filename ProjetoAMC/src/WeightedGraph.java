import java.util.Arrays;

	public class WeightedGraph{
			
		int dim;
		double [][] ma;
		
		public WeightedGraph(int dim) {
			
			this.dim=dim;
			this.ma = new double[dim][dim];}
			
		//cada aresta para além de ter destino tem um peso temos de criar uma estrutura em java
		// sempre que há a situação de herança, primeira coisa a fzer no metodo construtor da classe que extende outra classe é chamar o método 
		// construtor da classe que estou a extender utilizando o método super
		
		
		public int getDim() {
			return this.dim;
		}
		
		public String toString() {
			return "weightedgraph [dim=" + dim + ", ma=" + Arrays.toString(ma) + "]";
		}
		//como grafo é completo não é necessário questionar se existe aresta entre dois nós pois haverá		
		
		public void Add(int i, int j, double w){
				if (i>=0 && i<this.dim && j>=0 && j<this.dim) {
					this.ma[i][j] = w;
					this.ma[j][i] = w;// apesar de direcionado talvez seja melhor colocar aqui tb pois para pesquisas futuras pode dar jeito
				} else {
					throw new AssertionError("node not in graph");
			} 
		}
	
		
		/*public int getWeight(int i, int j) {
			//se necessário implementar
		}*/

	}
