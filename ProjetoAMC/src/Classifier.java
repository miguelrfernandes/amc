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
	 
	public int classify(int[] amostra) {  
		int m = 0;
		for (int freq : FreqList) {
			m += freq;  // tamanho do dataset original, donde se tirou a fiber
		}
		ArrayList<Double> odds = new ArrayList<Double>();
		for (int C=0; C < FreqList.size(); C++) {  // C = variavel classe C
			double PrV = (FreqList.get(C)/m) * (MRFTList.get(C).prob(amostra));
			odds.add(PrV);
		}
		int cmax = 0;
		boolean b = false;
		for (int c=0; c < odds.size(); c++) {
			if (odds.get(c) == Collections.max(odds)) {
				cmax = c;
				b = true;
			}
		}
		if (b) {return cmax;}
		else { throw new AssertionError("erro");}
	}
}

