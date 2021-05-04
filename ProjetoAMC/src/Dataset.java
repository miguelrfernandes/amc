
import java.util.ArrayList;
import java.util.Arrays;
public class Dataset{
	ArrayList<int[]> data;
	int n;// o java começa a conta do 0; este n refere-se ao numero de elementos de cada vetor de data
	int [] var; // é possível que precisemos mais tarde
	
	
	public Dataset(int n){
		this.n=n;  // numero de variaveis aleatorias + a vari�vel da classe
		this.var = new int[n]; //lista com os valores maximos das variaveis aleatorias, sem a classe vari�vel da classe
		this.data = new ArrayList<int[]>();
}
	public ArrayList<int[]> getData() {
		return data;
	}

	public void setData(ArrayList<int[]> data) {
		this.data = data;
	}


	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int[] getVar() {
		return var;
	}

	public void setVar(int[] var) {
		this.var = var;
	}

	@Override
	public String toString() {
		return "Dataset [data=" + data + ", n=" + n + ", var=" + Arrays.toString(var) + "]";// TODO toString cada elemento de data fazer ciclo for
	
	}
	
	// PRIMEIRA FUNCAO
	
	public void Add(int[] v) {
		
		if (v.length != this.n) {
			throw new AssertionError(" wrong dimension ");
		}
		
		else{
		
		data.add(v);
		this.var = var_max(v, this.var); // aqui estamos a atualizar o conjuntos de valores máximos das variaveis aleatorias do dataset
		
		}
	}
	
	private int[] var_max(int[] v, int[] var) {
		for (int i=0; i < var.length; i++) {
			if(var[i]<v[i]){ 
				var[i]=v[i];
			}
		} 
		return var;
		}
	
	public int Count(int[] vars, int[] val) {// pensar se quero adicionar a verificação de tamanho
		int r = 0; 
		for (int i = 0; i < data.size(); i++ )  {
			boolean c = true;
			for (int j=0; j < vars.length; j++) { //adicionar c Ã  guarda para aumentar eficiÃªncia
				if (data.get(i)[vars[j]] != val[j]) c = false;
			}
			if (c) r++;
		}
		return r;
	}
	
	public ArrayList<int[]> Fiber(int c) {
		ArrayList<int[]> fibra = new ArrayList<int[]>();
		for (int i = 0; i < data.size(); i++ ) {
			if (data.get(i)[this.n-1]==c) {
				fibra.add(Arrays.copyOf(data.get(i), data.get(i).length -1));
			}
		}
		if (!fibra.isEmpty()) {
		return fibra;
	}
		else {
			throw new AssertionError("classe is not verified");
		}
	
	}
	
	
	
	
}

	

	
