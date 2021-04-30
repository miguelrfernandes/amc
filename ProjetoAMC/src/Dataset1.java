import java.util.Arrays;
import java.util.LinkedList;

public class Dataset1 {
	//Implementação com Listas Ligadas
	LinkedList<int[]> data = new LinkedList<int[]>();
	
	public Dataset1() {
		super();
	}

	// Count: recebe uma lista de variáveis e valores destas e retorna o número
	//de vezes que estas variáveis tomam simultaneamente esses valores no dataset.
	public int Count(int[] vars, int[] val) {
		int r = 0;
		for (int i = 0; i < data.size(); i++) {
			boolean c = true;
			for (int var : vars) {
				if (data.get(i)[var] != val[var]) c = false;
			}
			if (c) r++;
		}
		return r;
	}
	
	// Add: adiciona um vetor ao dataset.
	public void Add(int[] v) {
		data.add(v);
	}
	
	// Fiber: dado um valor da classe retorna a fibra (Dataset) associada
	// a esse valor da classe.
	// O que é uma fibra (Dataset)?
	public int[] fiber(int v) {
		return data.get(v);
	}
	
	
	
	@Override
	public String toString() {
		return "Dataset1 [data=" + data.toArray() + "]";
	}

	public static void main(String[] args) {
		Dataset1 ds = new Dataset1();
		
		int[] v1 = {1,2,3};
		
		ds.Add(v1);
		ds.Add(v1);
		
		System.out.println(ds);
	}


}
