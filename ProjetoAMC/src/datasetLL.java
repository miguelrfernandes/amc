import java.util.LinkedList;

public class datasetLL {
	//Implementação com Listas Ligadas
	LinkedList<int[]> data = new LinkedList<int[]>();
	
	public datasetLL() {
		super();
	}

	// Count:recebe uma lista de variáveis e valores destas e retorna o número
	//de vezes que estas variáveis tomam simultaneamente os esses valores no dataset.
	public int count(int[] vars, int[] val) {
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
	public void add(int[] v) {
		data.add(v);
	}
	
	// Fiber: dado um valor da classe retorna a fibra (Dataset) associada
	// a esse valor da classe.
	// O que é uma fibra (Dataset)?
	// novo comentário
}
