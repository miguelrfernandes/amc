import java.util.Arrays;

	public class WeightedGraph{
			
		int dim;
		double [][] ma;
		
		public WeightedGraph(int dim) {
			
			this.dim=dim;
			this.ma = new double[dim][dim];}
			
		//cada aresta para al�m de ter destino tem um peso temos de criar uma estrutura em java
		// sempre que h� a situa��o de heran�a, primeira coisa a fzer no metodo construtor da classe que extende outra classe � chamar o m�todo 
		// construtor da classe que estou a extender utilizando o m�todo super
		
		
		public int getDim() {
			return this.dim;
		}
		
		public String toString() {
			return "weightedgraph [dim=" + dim + ", ma=" + Arrays.toString(ma) + "]";
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
	
		
		/*public int getWeight(int i, int j) {
			//se necess�rio implementar
		}*/
		
		public Tree MST() {
			Tree maximal = new Tree(dim); //dim nao esta correto, e o numero de vertices
			// determina a MST
			return maximal;
		}

	}
