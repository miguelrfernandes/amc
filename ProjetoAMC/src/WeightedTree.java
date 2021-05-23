import java.io.Serializable;
import java.util.Arrays;

public class WeightedTree implements Serializable { 
	//default serialVersion id
    private static final long serialVersionUID = 1L;
    
	double [][][][] ma;
	int dim;
	
	public WeightedTree(int dim) {
		this.dim = dim;
		int i = 1;
		int j = 1;
		this.ma = new double[dim][dim][i][j]; 
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

	public void Add(int i, int j, double[][] phi) {
			if (i>=0 && i<this.dim && j>=0 && j<this.dim) {
				this.ma[i][j] = phi;
				this.ma[j][i] = phi;
			} 
			else {
				if (i >= this.dim) {
					throw new AssertionError("O nó" + i + "não pode estar numa árvore com dimensão" + this.dim); 
				}
				if (j >= this.dim) {
					throw new AssertionError("O nó" + j + "não pode estar numa árvore com dimensão" + this.dim); 
				}
				else {
					throw new AssertionError("Nó é negativo"); 
				}
			} 
		}

	
	public double[][] getWeight(int i, int j) {
		if (i>=0 && i<this.dim && j>=0 && j<this.dim) return ma[i][j]; 
		else {
			if (i >= this.dim) {
				throw new AssertionError("O nó" + i + "não pode estar numa árvore com dimensão" + this.dim); 
			}
			if (j >= this.dim) {
				throw new AssertionError("O nó" + j + "não pode estar numa árvore com dimensão" + this.dim); 
			}
			else {
				throw new AssertionError("Nó é negativo"); 
			}
		} 
	}
}
