import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
	
	public ArrayList<Integer> BFS(int o) {
		if (o>=0 && o<this.dim) {
			ArrayList<Integer> r = new ArrayList<Integer>();
			Queue<Integer> q = new LinkedList<Integer>();
			boolean[] visited = new boolean[this.dim];
			q.add(o);
			while(!q.isEmpty()) {
				int node = q.remove(); // remove o mais antigo
				if (!visited[node]) {
					r.add(node);
					visited[node]=true;
					for (int i : this.offspring(node)) {
						q.add(i);
					}
				}
			}
			return r;
		} else {
			throw new AssertionError("node not in graph");
		}
	}
	
	public ArrayList<Integer> DFS(int o) {
		if (o>=0 && o<this.dim) {
			ArrayList<Integer> r = new ArrayList<Integer>();
			Stack<Integer> stack = new Stack<Integer>();
			boolean[] visited = new boolean[this.dim];
			stack.push(o);
			while(!stack.isEmpty()) {
				int node = stack.pop(); // remove o mais recente
				if (!visited[node]) {
					r.add(node);
					visited[node]=true;
					for (int i : this.offspring(node)) {
						stack.push(i);
					}
				}
			}
			return r;
		} else {
			throw new AssertionError("node not in graph");
		}
	}
}
