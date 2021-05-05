import java.util.ArrayList;
import java.util.Arrays;

// para testar o funcionamento do programa
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	// CLASSE DATASET
		
		// Exemplo 1 — 9 medições, tamanha, cor, regularidade..., beningno (0) ou maligno (1)
		int[][] exemplo1 = {{5,2,5,6,3,5,6,7,3,0},
		{5,2,5,2,3,5,6,7,3,1},
		{1,2,5,6,4,5,6,7,9,1},
		{5,2,2,6,3,5,6,7,3,0}};
		
		// Exemplo 2 — 14 medições, tamanha, cor, regularidade..., carcinoma (0), adenoma (1), sarcoma (2)
		int[][] exemplo2 = {{5,2,5,6,3,5,6,7,3,5,2,5,6,4,1},
		{5,2,8,6,3,5,6,7,3,5,2,5,9,4,0},
		{5,2,5,6,3,7,6,7,3,5,2,5,6,4,2},
		{5,2,5,6,3,5,6,7,3,5,2,5,6,4,2}};
		
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
		
		
		//dataeg1.Add(vetor2);;
		//dataeg2.print();
	
		
		// EXPERIMENTAR FUNCAO FIBER
		
		System.out.println();
	    dataeg1.Fiber(0).print();
	    System.out.println();
	    dataeg1.Fiber(1).print();
	    //System.out.println();
	    //dataeg1.Fiber(2).print();
	   
	 // EXPERIMENTAR LISTA COM FREQUENCIAS DAS CLASSES 
	    System.out.println();
	    System.out.println(dataeg1.getFreqlist());
	    System.out.println(dataeg2.getFreqlist());
	    
	 // EXPERIMENTAR LISTA COM VALORES DA VARIAVEL CLASSE 
	    System.out.println();
	    System.out.println(dataeg1.getclassvalues());
	    System.out.println(dataeg2.getclassvalues());
	    
	 // EXPERIMENTAR LISTA COM VALORES MAXIMOS DE CADA VARIAVEL ALEATORIA
	    System.out.println();
	    System.out.println(Arrays.toString(dataeg1.getVar()));
	    System.out.println(Arrays.toString(dataeg2.getVar()));
		
	   
		
	// CLASSE WEIGHTED GRAPH
		
		WeightedGraph grafo = new WeightedGraph(10);
		grafo.print();
		System.out.println();
		
		// FUNCAO ADD
		grafo.Add(0,  1, 4.5);
		grafo.Add(4,  7, 2.5);
		grafo.Add(8,  6, 1.7);
		grafo.Add(9,  2, 2.4);
		grafo.print();
		System.out.println();
		System.out.println(grafo.getWeight(4,5));
		System.out.println(grafo.getWeight(4,7));
		System.out.println(grafo.getWeight(7,4));
		System.out.println();
		 /*
		Tree maximal = grafo.MST();
		ArrayList<MRFTree> listamrft= new ArrayList<MRFTree>(2);
		
		for (int i = 0; i < 2; i++) {
			MRFTree markov = new MRFTree(maximal, dataset2.Fiber(i));
			listamrft.add(markov);
		}
		
		//Classifier classificador = new Classifier(listamrft, classfreq); */
	}
}
