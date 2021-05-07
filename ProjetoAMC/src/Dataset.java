import java.util.ArrayList;
import java.util.Arrays;

public class Dataset{
	protected ArrayList<int[]> data;
	private int n; // numero de variaveis aleatorias, o java começa a conta do 0
	private int[] D; // lista com os valores maximos das variaveis aleatorias, sem a classe vari�vel da classe
	protected ArrayList<Integer> Freqlist; // lista de frequencias de cada classe do dataset

	
	public Dataset(int n){
		this.n=n;  
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
	
	public String toString() {  // para melhor visualizacao do output
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
			else {  // estou a ordenar o dataset utilizando uma fun��o do java, em que escolho a posi��o com base no n�mero 
				int aux=0;
				for(int i = 0; i<v[this.n]; i++) {
					aux = aux + this.Freqlist.get(i);
				}
				data.add(aux, v);
			}
		
		this.D = D_max(v, this.D); // aqui estamos a atualizar o conjuntos de valores máximos das variaveis aleatorias do dataset
		
		this.Freqlist = FreqList(v); // nao faz sentido nas fibras, porque nao tem a classe nas amostras 
		}
	}
	
	// metodo auxiliar para adicionar vetores a fibras 
	private void fiberAdd(int[] v) {
		
		if (v.length != this.n) {
			// TODO trocar tipo de erro
			throw new AssertionError("Vector has the wrong dimension ");
		}
		
		else {
		data.add(v);
		}
	}
	
	// metodo auxiliar do metodo Add que atualiza a lista de frequencias de cada classe
	private ArrayList<Integer> FreqList(int[] v) {
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
 	
	// metodo auxiliar do metodo Add para calcular atualizar o dominio das variaveis
	private int[] D_max(int[] v, int[] D) {
		for (int i=0; i < D.length; i++) {
			if(D[i]<v[i]){ 
				D[i]=v[i];
			}
		} 
		return D;
		}
	
	// metodo que recebe um lista de variaveis e uma lista dos seus valores respetivos e faz uma contagem
	// recebe uma lista de variáveis e valores destas e retorna o número de vezes que estas variáveis tomam simultaneamente os esses valores no dataset.
	public int Count(int[] vars, int[] val) {
		// TODO verificar se os valores esta no dominio e variaveis sao todas menores que n
		int r = 0; 
		for (int i = 0; i < data.size(); i++ )  {
			boolean c = true;
			for (int j=0; j < vars.length; j++) { // TODO adicionar && c ou || c a guarda para aumentar eficiÃªncia
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
			// percorremos a lista de frequencias para determinar o indice a partir do qual começa as entradas da classe c pois o dataset esta ordenado
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