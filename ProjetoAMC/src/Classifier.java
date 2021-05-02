import java.util.ArrayList;
import java.util.Collections;

public class Classifier {  // vou supor que a classe MRFT pedida no enunciado e a "MRFTree"

	// atributos
	
	ArrayList<MRFTree> MRFTList;
	ArrayList<Integer> FreqList;   
	
	// metodo construtor
	public Classifier(ArrayList<MRFTree> MRFTList, ArrayList<Integer> FreqList) {
		this.MRFTList = MRFTList;
		this.FreqList = FreqList;
		
	}
	
	// getter e setters
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

	// metodo toString
	@Override
	public String toString() {
		return "Classificador [MRFT List =" + MRFTList + ", Frequencia das Classes =" + FreqList + "]";
	}
	 
	public double Classify(int[] amostra) {  // cada classe (por ex. benigno) e representada por um unico valor certo?
		int m = 0; // tamanho do dataset
		for (int k=0; k < MRFTList.size(); k++) {
			m = m + MRFTList.get(k).size();
			if  (MRFTList.get(k).prob(amostra) == 0) { 				
				throw new AssertionError("Amostra nao esta no dataset");
			}
		}
		ArrayList<Double> odds = new ArrayList<Double>();
		for (int C=0; C < FreqList.size(); C++) {  // C = variavel classe C
			double PrV = (FreqList.get(C)/m) * (MRFTList.get(C).prob(amostra));
			odds.add(PrV);
		}
		
		for (int c=0; c < odds.size(); c++) {
			if (odds.get(c) == Collections.max(odds)) {
				return Dataset2.ClassValuesList.get(c);
			}
		}
		 
	}

}


/* Colocar no modulo MRFTree:
 * 
 * public int size() {
 * 		return this.Dataset2.size();
 * }
 * 
 * Colocar no m�dulo Dataset2: 
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

// Na funcao classify eu assumo que o array com as frequencias das classes, bem como o MRFTList estao ordenados da mesma forma
// Ou seja, que para a classe C1 por exemplo, a sua MRF e frequencia sao MRFTList.get(1) e FreqList.get(1), respetivamente
// Assumo tambem que a MRFTList nao tem o valor das classes, associadas a cada MRFTree. Se tiver, a ClassValuesList nao e necessaria
// Por exemplo, MRFTList = [[MRFTree1, c1], [MRFTree2, c2], ...] --> Eu assumo que nao temos c1, c2, etc. na MRFTList
// Assum que o conjunto de dados e do tipo
// data = [[x1,x2,...,xn,c1], [x1,x2,...,xn,c2], ...]
