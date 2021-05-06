
import java.util.ArrayList;
import java.util.Arrays;

public class Dataset{
	ArrayList<int[]> data;
	int n;// o java come√ßa a conta do 0; este n refere-se ao numero de vari·veis aleatÛrias de cada vetor de data
	int[] D; // √© poss√≠vel que precisemos mais tarde
	ArrayList<Integer> Freqlist;

	
	public Dataset(int n){
		this.n=n;  // numero de variaveis aleatorias 
		this.D = new int[n]; //lista com os valores maximos das variaveis aleatorias, sem a classe vari·vel da classe
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

	public void setN(int n) {
		this.n = n;
	}

	public int[] getD() {
		return D;
	}

	public void setD(int[] D) {
		this.D = D;
	}

	
	public ArrayList<Integer> getFreqlist() {
		return Freqlist;
	}
	public void setFreqlist(ArrayList<Integer> freqlist) {
		Freqlist = freqlist;
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
		
		if (v.length != this.n + 1) {
			throw new AssertionError(" wrong dimension ");
		}
		
		else {
			if(this.data.isEmpty()) {
				data.add(v);
			}
			else {  // estou a ordenar o dataset utilizando uma funÁ„o do javae, em que escolho a posiÁ„o com base no n˙mero 
				int aux=0;
				for(int i = 0; i<v[this.n]; i++) {
					aux = aux + this.Freqlist.get(i);
				}
				data.add(aux, v);
			}
		
		this.D = var_max(v, this.D); // aqui estamos a atualizar o conjuntos de valores m√°ximos das variaveis aleatorias do dataset
		
		this.Freqlist = FreqList(v); // nao faz sentido nas fibras, pq nao tem a classe nas amostras 
		}
	}
	
private void fiberAdd(int[] v) {
		
		if (v.length != this.n) {
			throw new AssertionError(" wrong dimension ");
		}
		
		else {
		
		data.add(v);
		/*this.var = var_max(v, this.var);*/ // esta funÁ„o nao È necess·ria pois queremos que var da fibra seja igual a var do dataset
		
		}
	}
	
	
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
 	
	private int[] var_max(int[] v, int[] D) {
		for (int i=0; i < D.length; i++) {
			if(D[i]<v[i]){ 
				D[i]=v[i];
			}
		} 
		return D;
		}
	
	// SEGUNDA FUNCAO
	
	public int Count(int[] vars, int[] val) {// pensar se quero adicionar a verifica√ß√£o de tamanho
		int r = 0; 
		for (int i = 0; i < data.size(); i++ )  {
			boolean c = true;
			for (int j=0; j < vars.length; j++) { //adicionar c √É¬† guarda para aumentar efici√É¬™ncia
				if (data.get(i)[vars[j]] != val[j]) c = false;
			}
			if (c) r++;
		}
		return r;
	}
	
	// TERCEIRA FUNCAO
	
	public Dataset Fiber(int c) {
		Dataset fibra = new Dataset(this.n);
		fibra.D = this.D;
		
		for (int i = 0; i < data.size(); i++ ) {
			if (data.get(i)[this.n] == c) {
		
				int[] aux = Arrays.copyOf(data.get(i), data.get(i).length-1);
				fibra.fiberAdd(aux);
				
			}
		} if (fibra.data.size()!= 0) return fibra;
		  else {
			throw new AssertionError("class is not verified");
		}
}}

	
	
	/*public Dataset Fiber(int c) {
		
		Dataset fibra = new Dataset(this.n);
		fibra.D = this.D;
		
		if (this.Freqlist.size()> c){
			int i = 0;			
			for (int k = 0; k < this.Freqlist.size(); k++){
				if ( k < c ){
					i = i + this.Freqlist.get(k);}
				
				
			
			for (int j=i; j< i + Freqlist.get(c); j++) {
					int[] aux = Arrays.copyOf(this.data.get(j), this.data.get(j).length-1);
					fibra.fiberAdd(aux);
		}
	}
		return fibra;
}
		else 
		{throw new AssertionError("class is not verified");}	
	}
	
}*/

	

	
