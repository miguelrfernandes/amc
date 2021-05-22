import java.io.Serializable;
import java.util.Arrays;

public class WeightedTree implements Serializable{ 
	//default serialVersion id
    private static final long serialVersionUID = 1L;
    
	double [][][][] ma;
	int dim;
	
	public WeightedTree(int dim) {
		this.dim = dim;
		int i = 1;
		int j = 1;
		this.ma = new double[dim][dim][i][j]; // a aresta que liga o no i ao no j tem a matriz [dim][dim] associada   
			//B:acho que não é bem isso, porque são as entradas da matriz exterior que indicam as arestas;
	}
	
	public int getDim() {
		return this.dim;
	}
	
	@Override
	public String toString() {
		String r = "";
		for (int i = 0; i < dim; i++) {
			if (i==0 && dim != 1) {
				r = r + ("[" + Arrays.deepToString(this.ma[0]) + ",\n");
			}
			if (i==0 && dim == 1) {
				r = r + ("[" + Arrays.deepToString(this.ma[0]) + "]\n");
			}
			if (i !=0 && i == dim - 1) {
				r = r + (Arrays.deepToString(this.ma[i]) + "]\n");
			}
			if (i != 0 && i != dim - 1){
				r = r + (Arrays.deepToString(this.ma[i]) + ",\n");
			}
		}
		return "WeightedTree [ma=" + r + ", dim=" + dim + "]";
	}

	public void Add(int i, int j, double[][] phi){
			if (i>=0 && i<this.dim && j>=0 && j<this.dim) {
				this.ma[i][j] = phi;
				this.ma[j][i] = phi;
			} else {
				throw new AssertionError("node not in graph"); //TODO alterar erro
		} 
	}

	
	public double[][] getWeight(int i, int j) {
		return ma[i][j]; //aqui não se deveria veriricar se i e j pertencem à matriz?
	}
}
