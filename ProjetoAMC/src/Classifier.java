import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.io.Serializable;

public class Classifier implements Serializable{  
	//default serialVersion id
    private static final long serialVersionUID = 1L;

	// atributos
	private ArrayList<MRFTree> MRFTList;
	private ArrayList<Integer> FreqList;   
	
	// metodo construtor
	public Classifier(ArrayList<MRFTree> MRFTList, ArrayList<Integer> FreqList) {
		this.MRFTList = MRFTList;
		this.FreqList = FreqList;
		
	}
	
	// metodo toString
	@Override
	public String toString() {
		return "Classificador [MRFT List =" + MRFTList + ", Frequencia das Classes =" + FreqList + "]";
	}
	 
	public int classify(int[] amostra) {  
		int m = 0;
		for (int freq : FreqList) m += freq;  // tamanho do dataset original, donde se tirou a fiber
		
		ArrayList<Double> odds = new ArrayList<Double>();
		for (int C=0; C < FreqList.size(); C++) {  // C = variavel classe C
			double PrV = (FreqList.get(C)/m) * (MRFTList.get(C).prob(amostra));
			odds.add(PrV);
		}
		
		// determinamos a classe mais provavel, isto e, que tem maior probabilidade para amostra dada
		int r = 0;
		boolean erro = false; // para garantir que encontramos a classe e mais provavel
		for (int c=0; c < odds.size() && !erro; c++) {
			if (odds.get(c) == Collections.max(odds)) {
				r = c;
				erro = true;
			}
		}
		
		if (erro) return r;
		else { throw new AssertionError("erro");}
	}
	
	public void writeFile(String path) {
		try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this); 
            objectOut.close();
            fileOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	public int getN() {
		return MRFTList.get(0).n;
		// return FreqList.size(); // TODO corrigi porque nao esta correto. Freq.List.size() da o numero de classes, nao de variaveis
	}
}

