
public class datasetOld {
	//Implementação com array de arrays não é possível pois é necessário alterar o tamanho
	//do dataset (método add)
	int data[][];
	
	public datasetOld(int[][] data) {
		super();
		this.data = data;
	}

	// Count:recebe uma lista de variáveis e valores destas e retorna o número
	//de vezes que estas variáveis tomam simultaneamente os esses valores no dataset.
	public int count(int[] vars, int[] val) {
		int r = 0;
		for (int[] row : data) {
			boolean c = true;
			for (int var : vars) {
				if (row[var] != val[var]) c = false;
			}
			if (c) r++;
		}
		return r;
	}
	
	// Add: adiciona um vetor ao dataset.
	public void add(int[] v) {
		data[0] = v;
	}
	
	// Fiber: dado um valor da classe retorna a fibra (Dataset) associada
	// a esse valor da classe.
	// O que é uma fibra (Dataset)?
}
