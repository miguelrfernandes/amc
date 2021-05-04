import java.util.ArrayList;
import java.util.Arrays;

// para testar o funcionamento do programa
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Exemplo 1 — 9 medições, tamanha, cor, regularidade..., beningno (0) ou maligno (1)
		int[][] exemplo1 = {{5,2,5,6,3,5,6,7,3,0},
		{5,2,5,2,3,5,6,7,3,0},
		{1,2,5,6,4,5,6,7,3,1},
		{5,2,2,6,3,5,6,7,3,0}};
		
		// Exemplo 2 — 14 medições, tamanha, cor, regularidade..., carcinoma (0), adenoma (1), sarcoma (2)
		int[][] exemplo2 = {{5,2,5,6,3,5,6,7,3,5,2,5,6,4,0},
		{5,2,5,6,3,5,6,7,3,5,2,5,6,4,0},
		{5,2,5,6,3,5,6,7,3,5,2,5,6,4,1},
		{5,2,5,6,3,5,6,7,3,5,2,5,6,4,0}};
		
		System.out.println(Arrays.deepToString(exemplo1));
		System.out.println();
		System.out.println(Arrays.deepToString(exemplo2));
		System.out.println();
		
		Dataset dataeg1 = new Dataset(10);
		Dataset dataeg2 = new Dataset(15);
	
	
		for (int i=0; i < exemplo1.length; i++) {
			dataeg1.Add(exemplo1[i]);
		}
		
		for (int i=0; i < exemplo2.length; i++) {
			dataeg2.Add(exemplo2[i]);
		}
		
		dataeg1.print();
		System.out.println();
		dataeg2.print();
		System.out.println();
		
		// EXPERIMENTAR FUNCAO COUNT
		int[] vars = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}; // n = 10
		int[] val = {5,2,5,6,3,5,6,7,3,0};
		System.out.println(dataeg1.Count(vars, val));
		System.out.println();
		System.out.println(dataeg2.Count(vars, val));
		System.out.println();
		
		// EXPERIMENTAR FUNCAO ADD
		int[] vetor1 = {5,2,5,2,3,5,6,7,3,0};
		int[] vetor2 = {5,2,5,2,3,5,6,7,0};
		
		dataeg1.Add(vetor1);
		dataeg1.print();
		
		/*
		dataeg1.Add(vetor2);;
		dataeg2.print();*/
	
		
		// EXPERIMENTAR FUNCAO FIBER
		
		System.out.println();
	    dataeg1.Fiber(0).print();
	    System.out.println();
	    dataeg1.Fiber(1).print();
	    System.out.println();
	    dataeg1.Fiber(2).print();
		
		
		
		//ArrayList<int[]> fibra2 = dataeg2.Fiber(1);
		
		
		//WeightedGraph grafo = new WeightedGraph(10);
		// adicionar pesos ao grafico
		//Tree maximal = grafo.MST();
		ArrayList<MRFTree> listamrft= new ArrayList<MRFTree>(2);
		
		for (int i = 0; i < 2; i++) {
			//MRFTree markov = new MRFTree(maximal, dataset2.Fiber(i));
			//listamrft.add(markov);
		}
		
		//Classifier classificador = new Classifier(listamrft, classfreq); */
		
	}
}
