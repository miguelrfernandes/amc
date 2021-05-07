import java.util.Arrays;
import java.util.LinkedList;

public class WeightedTree { //PhiTree
	double [][][][] ma;
	int dim;
	
	public WeightedTree(int dim) {
		this.dim = dim;
		int i = 1;
		int j = 1;
		this.ma = new double[dim][dim][i][j]; // a aresta que liga o no i ao no j tem a matriz [dim][dim] associada
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
				throw new AssertionError("node not in graph");
		} 
	}

	
	public double[][] getWeight(int i, int j) {
		return ma[i][j];
	}
	
	public LinkedList<Integer> offspring(int o) {
		LinkedList<Integer> r = new LinkedList<Integer>();
		if (o>=0 && o<this.dim) {
			for (int d = 0; d<this.dim; d++) {
				if (ma[o][d][0][0] != 0.0){
					r.add(d);
				}
			}
			return r;
		} else {
			throw new AssertionError("node not in graph");
		}
	}
}
