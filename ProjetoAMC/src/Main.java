import java.util.ArrayList;
import java.util.Arrays;

// para testar o funcionamento do programa
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	// CLASSE DATASET
		
		 System.out.println();
		 System.out.println("------------------------------CLASSE DATASET------------------------------");
		 System.out.println();
		
	 // Exemplo 1 - 9 medicoes, tamanha, cor, regularidade..., classes: beningno (0) ou maligno (1)
		int[][] exemplo1 = {{5,100,5,6,3,5,6,7,3,0},
		{5,2,5,2,3,5,6,7,3,1},
		{1,2,5,6,4,5,6,7,9,1},
		{5,2,2,6,3,5,6,7,3,0}};
		
	 // Exemplo 2 - 14 medicoes, tamanha, cor, regularidade..., classes: carcinoma (0), adenoma (1), sarcoma (2)
		int[][] exemplo2 = {{5,2,5,6,3,5,6,7,3,5,2,5,6,4,1},
		{5,2,8,6,3,5,6,7,3,5,2,5,9,4,0},
		{5,2,5,6,3,7,6,7,3,5,2,5,6,4,2},
		{5,2,5,6,3,5,6,7,3,5,2,5,6,4,2}}; 
		
		System.out.println(Arrays.deepToString(exemplo1));
		System.out.println();
		System.out.println(Arrays.deepToString(exemplo2));
		System.out.println();
		
		Dataset dataeg1 = new Dataset(9);
		Dataset dataeg2 = new Dataset(14);
		
	
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
	    System.out.println();
	    dataeg2.Fiber(2).print();
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
	    System.out.println(Arrays.toString(dataeg1.getD()));
	    System.out.println(Arrays.toString(dataeg2.getD()));
		
	    System.out.println();
	    System.out.println("---------------------------CLASSE WEIGHTEDGRAPH---------------------------------");
	    System.out.println();
		
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

		 System.out.println();
	    System.out.println("---------------------------CLASSE WEIGHTEDTREE---------------------------------");
	    System.out.println();
		
	// CLASSE WEIGHTEDTREE
		
		WeightedTree WT = new WeightedTree(10); // 10 v.a.s
		System.out.println(WT);
		
		double[][] phi1 = {{0.5,0.5,0.5,0.54,0.52,0.5,0.5,0.5,0.5,0.5},
		{0.5,0.5,0.5,0.5,0.51,0.5,0.5,0.5,0.5,0.5},
		{0.5,0.2,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5},
		{0.5,0.3,0.5,0.1,0.5,0.5,0.5,0.5,0.5,0.5}};
		
		WT.Add(0, 1, phi1);
		WT.Add(5, 4, phi1);
		WT.Add(6, 7, phi1);
		WT.Add(3, 8, phi1);
		
		System.out.println(WT);
		
		System.out.println();
	    System.out.println("------------------------------CLASSE TREE------------------------------");
	    System.out.println();
					
	// CLASS TREE
		
		Tree T = new Tree(10);
		System.out.println(T);
		T.addEdge(1, 0);
		T.addEdge(2, 0);
		T.addEdge(3, 0);
		T.addEdge(4, 0);
		T.addEdge(5, 1);
		T.addEdge(6, 1);
		T.addEdge(7, 1);
		T.addEdge(8, 2);
		T.addEdge(9, 2);
		System.out.println();
		System.out.println(T);
		System.out.println();
		System.out.println(T.EdgeQ(0, 9)); 
		System.out.println(T.EdgeQ(0, 1)); 
		System.out.println(T.EdgeQ(2, 9));
		
		 System.out.println();
		 System.out.println("-------------------------CLASSE MRFT-----------------------------------");
		 System.out.println();
		
	// CLASSE MRFT
		
		Tree arvore1 = new Tree(9); //dataeg1 tem 9 v.a.s (x1, ..., x9)
		MRFTree MTdataeg1 = new MRFTree(arvore1, dataeg1.Fiber(0)); //print de MT em falta
		//int[] v1 = {4,20,5,6,3,5,6,7,3};  //falta corrigir no MRFTree
		int[] v2 = {1,2,5,6,4,5,6,7,8};
		//int[] v3 = {5,400,2,8,3,8,6,2,8}; //deve dar erro pq o valor maximo de x2 e 100
		//System.out.println(MTdataeg1.prob(v1));
		System.out.println(MTdataeg1.prob(v2));
		//System.out.println(MTdataeg1.prob(v3));
		
		System.out.println();
		
		Tree arvore2 = new Tree(14); //dataeg1 tem 14 v.a.s (x1, ..., x14)
		MRFTree MTdataeg2 = new MRFTree(arvore2, dataeg2.Fiber(2)); //print de MT em falta
		//int[] w1 = {5,20,5,6,3,3,6,7,3,5,2,5,6,4}; //falta corrigir no MRFTree
		int[] w2 = {5,2,5,2,3,5,6,7,3,5,2,5,6,4};
		//int[] w3 = {10,2,5,6,3,5,6,7,3,5,2,5,6,4}; //deve dar erro pq o valor maximo e 5
		//System.out.println(MTdataeg2.prob(w1));
		System.out.println(MTdataeg2.prob(w2));
		//System.out.println(MTdataeg2.prob(w3));
		
		System.out.println();
	    System.out.println("------------------------------CLASSE CLASSIFIER------------------------------");
	    System.out.println();
					
	// CLASS CLASSIFIER 
	    /*
	    
	    // com dataset do exemplo1 
	    MRFTree MT0eg1 = new MRFTree(arvore1, dataeg1.Fiber(0));
	    MRFTree MT1eg1 = new MRFTree(arvore1, dataeg1.Fiber(1));
	    
	    ArrayList<MRFTree> listamrft1 = new ArrayList<MRFTree>();
	    listamrft1.add(MT0eg1);
	    listamrft1.add(MT1eg1);
	    ArrayList<Integer> classfreq1 = dataeg1.getFreqlist();
	    
	    int[] amostra1 = {5,2,5,2,3,5,6,7,3}; //tirada do exemplo 1. a classe do vetor e 1
		Classifier CFdataeg1 = new Classifier(listamrft1, classfreq1);
		System.out.println(CFdataeg1.classify(amostra1)); 
		
		
		// com dataset do exemplo2
		MRFTree MT0eg2 = new MRFTree(arvore2, dataeg2.Fiber(0));
	    MRFTree MT1eg2 = new MRFTree(arvore2, dataeg2.Fiber(1));
	    MRFTree MT2eg2 = new MRFTree(arvore2, dataeg2.Fiber(2));
	    
	    ArrayList<MRFTree> listamrft2 = new ArrayList<MRFTree>();
	    listamrft2.add(MT0eg2);
	    listamrft2.add(MT1eg2);
	    listamrft2.add(MT2eg2);
	    
	    ArrayList<Integer> classfreq2 = dataeg2.getFreqlist();
	    
	    int[] amostra2 = {5,2,5,6,3,7,6,7,3,5,2,5,6,4}; //tirada do exemplo 2. a classe do vetor e 2
		Classifier CFdataeg2 = new Classifier(listamrft2, classfreq2);
		System.out.println(CFdataeg2.classify(amostra2));
		
		
		
		 
		Tree maximal = grafo.MST();
		ArrayList<MRFTree> listamrft= new ArrayList<MRFTree>(2);
		
		for (int i = 0; i < 2; i++) {
			MRFTree markov = new MRFTree(maximal, dataset2.Fiber(i));
			listamrft.add(markov);
		}
		
		Classifier classificador = new Classifier(listamrft, classfreq); */
	}
}
