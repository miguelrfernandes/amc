import java.util.Arrays;

public class WeightedTree{ //PhiTree
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
		return "WeightedTree [ma=" + Arrays.toString(ma) + ", dim=" + dim + "]";
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
}
