import java.util.Arrays;

//import java.util.ArrayList;
//import java.util.Stack;


public class Tree {
	int[] pais;
	
	public Tree(int dim) {
		pais = new int[dim]; // gostei desta implementa��o compacta
		for (int i = 0; i < dim; i++) pais[i] = -1; // iniciamos esta matriz com -1
	}
	
	public void addEdge(int i, int pai) {
		pais[i] = pai;
	}
	
	public boolean EdgeQ(int i, int j) {
		return pais[i] == j || pais[j] == i;
	}
	
	public int pai(int i) {
		return pais[i];
	}

	@Override
	public String toString() { 
		return "Tree [pais=" + Arrays.toString(pais) + "]";
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
