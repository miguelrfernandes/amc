
import java.util.ArrayList;
import java.util.Arrays;

public class Dataset{
	ArrayList<int[]> data;
	int n;// o java começa a conta do 0; este n refere-se ao numero de elementos de cada vetor de data
	int[] var; // é possível que precisemos mais tarde
	ArrayList<Integer> Freqlist;
	ArrayList<Integer> classvalues;

	
	public Dataset(int n){
		this.n=n;  // numero de variaveis aleatorias + a vari�vel da classe
		this.var = new int[n-1]; //lista com os valores maximos das variaveis aleatorias, sem a classe vari�vel da classe
		this.data = new ArrayList<int[]>();
		this.Freqlist = new ArrayList<Integer>();
		this.classvalues = new ArrayList<Integer>();
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

	
	public ArrayList<Integer> getFreqlist() {
		return Freqlist;
	}
	public void setFreqlist(ArrayList<Integer> freqlist) {
		Freqlist = freqlist;
	}
	
	public ArrayList<Integer> getclassvalues() {
		return classvalues;
	}
	
	public void setclassvalues(ArrayList<Integer> classvalues) {
		this.classvalues = classvalues;
	}
	public void print() {  // para melhor visualizacao do output
		System.out.println("Dataset");
		for (int i = 0; i < this.data.size(); i++) {
			if (i==0 && this.data.size() != 1) {
				System.out.println("[" + Arrays.toString(this.data.get(0)) + ",");
			}
			if (i==0 && this.data.size() == 1) {
				System.out.println("[" + Arrays.toString(this.data.get(0)) + "]");
			}
			if (i !=0 && i == this.data.size() - 1) {
				System.out.println(Arrays.toString(this.data.get(i)) + "]");
			}
			if (i != 0 && i != this.data.size() - 1){
				System.out.println(Arrays.toString(this.data.get(i)) + ",");
			}
		}
	}
	
	// PRIMEIRA FUNCAO
	
	public void Add(int[] v) {
		
		if (v.length != this.n) {
			throw new AssertionError(" wrong dimension ");
		}
		
		else {
		
		data.add(v);
		this.var = var_max(v, this.var); // aqui estamos a atualizar o conjuntos de valores máximos das variaveis aleatorias do dataset
		this.classvalues = ClassValues(v); // nao faz sentido em fibras, pq nao tem a classe nas amostras
		this.Freqlist = FreqList(v); // nao faz sentido nas fibras, pq nao tem a classe nas amostras
		}
	}
	
public void fiberAdd(int[] v) {
		
		if (v.length != this.n) {
			throw new AssertionError(" wrong dimension ");
		}
		
		else {
		
		data.add(v);
		this.var = var_max(v, this.var); // aqui estamos a atualizar o conjuntos de valores máximos das variaveis aleatorias do dataset
		}
	}
	
	private ArrayList<Integer> ClassValues(int[] v) {
		boolean t = true;
		for (int classe : this.classvalues) {
			if (classe == v[this.n-1]) {
				t = false;
			}
		}
		if (t) { this.classvalues.add(v[this.n-1]); }
		return classvalues;
	}
	private ArrayList<Integer> FreqList(int[] v) {
		int i = v[this.n-1];
		
		if (this.Freqlist.size() > i) {
			
			int l = this.Freqlist.get(i);
			this.Freqlist.set(i, ++l);
		}
		else {
			int aux = this.Freqlist.size();
			for(int k=0; k <= i-aux; k++) {
				this.Freqlist.add(0);
			}
			
			int l = this.Freqlist.get(i);
			this.Freqlist.set(i, ++l);
			
		}
		return this.Freqlist;	
	}
 	
	private int[] var_max(int[] v, int[] var) {
		for (int i=0; i < var.length; i++) {
			if(var[i]<v[i]){ 
				var[i]=v[i];
			}
		} 
		return var;
		}
	
	// SEGUNDA FUNCAO
	
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
	
	// TERCEIRA FUNCAO
	
	public Dataset Fiber(int c) {
		Dataset fibra = new Dataset(this.n - 1);
		fibra.var = this.var;
		for (int i = 0; i < data.size(); i++ ) {
			if (data.get(i)[this.n-1] == c) {
		
				int[] aux = Arrays.copyOf(data.get(i), data.get(i).length-1);
				fibra.fiberAdd(aux);
				
			}
		} if (fibra.data.size()!= 0) return fibra;
		  else {
			throw new AssertionError("classe is not verified");
		}}

	
	
}

	

	
