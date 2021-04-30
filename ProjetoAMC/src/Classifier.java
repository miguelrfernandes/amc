import java.util.ArrayList;
import java.util.Collections;

public class Classifier {  // vou supor que a classe MRFT pedida é a "MRFTree"

	// atributos
	
	ArrayList<MRFTree> MRFTList;
	ArrayList<Integer> FreqList;   
	
	public Classifier(ArrayList<MRFTree> MRFTList, ArrayList<Integer> FreqList) {
		this.MRFTList = MRFTList;
		this.FreqList = FreqList;
		
	}

	public ArrayList<MRFTree> getMRFTList() {
		return MRFTList;
	}

	public void setMRFTList(ArrayList<MRFTree> mRFTList) {
		MRFTList = mRFTList;
	}

	public ArrayList<Integer> getFreqList() {
		return FreqList;
	}

	public void setFreqList(ArrayList<Integer> freqList) {
		FreqList = freqList;
	}

	@Override
	public String toString() {
		return "Classificador [MRFT List =" + MRFTList + ", Frequencia das Classes =" + FreqList + "]";
	}
	 
	public double Classify(int[] amostra) {  // o valor da classe é sempre um número certo?
		if  (MRFTree.prob(amostra) == 0) { 				
			throw new AssertionError("Amostra não está no dataset");
		}
		int m = 0;
		for (int k=0; k < MRFTList.size(); k++) {
			m = m + MRFTree.size(k);
		}
		ArrayList<Double> odds = new ArrayList<Double>();
		for (int C=0; C < FreqList.size(); C++) {  // C = variável classe C
			double PrV = (FreqList.get(C)/m) * (MRFTList.get(C).prob(amostra));
			odds.add(PrV);
		}
		
		for (int c=0; c < Dataset2.ClassValuesList().size(); c++) {
			if (ClassValuesList.get(c) == Collections.max(odds)) {
				return ClassValuesList.get(c);
			}
		}
		 
	}

}


/* Colocar no módulo MRFTree:
 * 
 * public int size() {
 * 		return this.Dataset2.size();
 * }
 * 
 * Colocar no módulo Dataset2: 
 * 
 * public int size() {
 * 		return this.data.size();
 * }
 * 
 * public ArrayList<Double> ClassValuesList() {
 * 		ArrayList<Double> CVL = new ArrayList<Double>();
 * 		for(int i=0; i < data.size(); i++) {
 * 			CVL.add(data.get(i).get(data.get(i).size() - 1))
 *		}
 *		return CVL;
 *}
 */

// Na função classify eu assumo que o array com as frequências das classes, bem como o MRFTList estao ordenados da mesma forma
// Ou seja, que para a classe C1 por exemplo, a sua MRF e frequência são MRFTList.get(1) e FreqList.get(1), respetivamente
// Assumo também que a MRFTList nao tem as classes associadas a cada MRFTree. Se tiver, a ClassValuesList não é necessária
// Assum que o conjunto de dados é do tipo
// data = [[x1,x2,...,xn,c2], [x1,x2,...,xn,c2], ...]
