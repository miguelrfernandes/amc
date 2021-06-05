import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Dataset implements Serializable{
	//default serialVersion id
    private static final long serialVersionUID = 1L; 
	
	protected ArrayList<int[]> data; // lista de amostras (dados)
	private int n; // numero de variaveis aleatorias, o java comeca a contar do 0
	private int[] D; // lista com os valores maximos das variaveis aleatorias, sem a classe variavel da classe
	protected ArrayList<Integer> Freqlist; // lista de frequencias de cada classe do dataset

	
	public Dataset(int n){
		this.n = n;  
		this.D = new int[n]; 
		this.data = new ArrayList<int[]>();
		this.Freqlist = new ArrayList<Integer>();
	
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

	public int[] getD() {
		return D;
	}
	
	public String toString() {  
		System.out.println("Dataset");
		String r = "";
		for (int i = 0; i < this.data.size(); i++) {
			if (i==0 && this.data.size() != 1) {
				r += ("[" + Arrays.toString(this.data.get(0)) + ",\n");
			}
			if (i==0 && this.data.size() == 1) {
				r += ("[" + Arrays.toString(this.data.get(0)) + "]\n");
			}
			if (i !=0 && i == this.data.size() - 1) {
				r += (Arrays.toString(this.data.get(i)) + "]\n");
			}
			if (i != 0 && i != this.data.size() - 1){
				r += (Arrays.toString(this.data.get(i)) + ",\n");
			}
		}
		return r;
	}
	
	// Metodo para adicionar um vetor ao dataset
	public void Add(int[] v) {
		
		if (v.length != this.n + 1) {
			throw new AssertionError(" wrong dimension ");
		}
		
		else {
			if(this.data.isEmpty()) {
				data.add(v);
			}
			else {  // estou a ordenar o dataset utilizando uma funcao do java, em que escolho a posicao com base no numero 
				int aux=0;
				for(int i = 0; i<v[this.n]; i++) aux = aux + this.Freqlist.get(i);
				data.add(aux, v);
			}
		
		this.D = D_max(v, this.D); // aqui estamos a atualizar o conjuntos de valores maximos das variaveis aleatorias do dataset
		
		this.Freqlist = AtualizaFreqList(v); // estamos a atualizar o vetor das frequencias da classe, nomeadamente, a da classe do vetor adicionado
		}
	}
	
	// Metodo auxiliar para adicionar vetores a fibras 
	private void fiberAdd(int[] v) {
		
		if (v.length != this.n) {
		
			throw new AssertionError("Vector has the wrong dimension ");
		}
		
		else {
			data.add(v);
		}
	}
	
	// Metodo auxiliar do metodo Add que atualiza a lista de frequencias de cada classe
	private ArrayList<Integer> AtualizaFreqList(int[] v) {
		int i = v[this.n];
		
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
 	
	// Metodo auxiliar do metodo Add para calcular atualizar o dominio das variaveis
	private int[] D_max(int[] v, int[] D) {
		for (int i=0; i < D.length; i++) {
			if(D[i]<v[i]){ 
				D[i]=v[i];
			}
		} 
		return D;
		}
	
	// Metodo que recebe um lista de variaveis e uma lista dos seus valores respetivos e faz uma contagem
	// recebe uma lista de variaveis e valores destas e retorna o numero de vezes que estas variaveis tomam simultaneamente esses valores no dataset.
	public int Count(int[] vars, int[] val) {
		int r = 0; 
		for (int i = 0; i < data.size(); i++ )  {
			boolean c = true;
			for (int j=0; j < vars.length && c; j++) {
				if (data.get(i)[vars[j]] != val[j]) c = false;
			}
			if (c) r++;
		}
		return r;
	}
	
	// metodo que dado um valor da classe retorna a fibra (Dataset) associada a esse valor da classe.
	 public Dataset Fiber(int c) {
	
		Dataset fibra = new Dataset(this.n);
		fibra.D = this.D;
		
		if (this.Freqlist.size()> c){
			int i = 0;
			for (int k = 0; k < this.Freqlist.size(); k++){
				if ( k < c ) i = i + this.Freqlist.get(k);
			}
			for (int j=i; j < i + Freqlist.get(c); j++) {
					int[] aux = Arrays.copyOf(this.data.get(j), this.data.get(j).length-1);
					fibra.fiberAdd(aux);
				}
			
			return fibra;
			}
		else throw new AssertionError("Class is not in dataset");
	}
}
