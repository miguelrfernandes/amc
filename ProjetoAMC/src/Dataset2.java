import java.util.ArrayList;

public class Dataset2 {
	//Implementação com ArrayList
	ArrayList<int[]> data = new ArrayList<int[]>();
	
	public Dataset2() {
		super();
	}
	
	// Count: recebe uma lista de variáveis e valores destas e retorna o número
	//de vezes que estas variáveis tomam simultaneamente esses valores no dataset.
	public int Count(int[] vars, int[] val) {
		int r = 0;
		for (int i = 0; i < data.size(); i++) {
			boolean c = true;
			for (int var : vars) { //adicionar c à guarda para aumentar eficiência
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
	
	
}
