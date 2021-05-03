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
		{5,2,5,6,3,5,6,7,3,5,2,5,6,4,0},
		{5,2,5,6,3,5,6,7,3,5,2,5,6,4,0}};
		
		Dataset dataset2 = new Dataset(15);
		
		for (int i=0; i<exemplo2.length; i++) {
			dataset2.Add(exemplo2[i]);
		}
	
		ArrayList<int[]> fibra2 = dataset2.Fiber(1);
		

	// para ver se a fibra est� a funcionar
		for ( int[] ve: fibra2) {
			for(int i: ve) {
				System.out.println(i);
			}
		}
	}
		

}
