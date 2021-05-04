//import java.util.ArrayList;
//import java.util.Stack;

public class Tree {
	int[] pais;
	
	public Tree(int dim) {
		pais = new int[dim];
	}
	
	public void addEdge(int i, int pai) {
		pais[i] = pai;
	}
	
	public boolean EdgeQ(int i, int j) {
		return pais[i] == j || pais[j] == i;
	}
	
	/*
	 public ArrayList<Integer> DFS() { // Depth First Search
		ArrayList<Integer> r = new ArrayList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		stack.add(0);
		return r;
	}
	*/
}
