import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Classifier implements Serializable {  
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
		double m = 0;
		for (int freq : FreqList) m += Double.valueOf(freq);  // tamanho do dataset original, donde se tirou a fiber
		
		ArrayList<Double> odds = new ArrayList<Double>();
		for (int C=0; C < FreqList.size(); C++) {  // C = variavel classe C
			double PrV = (Double.valueOf(FreqList.get(C))/m) * (MRFTList.get(C).prob(amostra));
			odds.add(PrV);
		}
		
		// determinamos a classe mais provavel, isto e, que tem maior probabilidade para amostra dada
		int r = 0;
		boolean erro = false; // para garantir que encontramos a classe mais provavel
		for (int c=0; c < odds.size() && !erro; c++) {
			if (odds.get(c) == Collections.max(odds)) {
				r = c;
				erro = true;
			}
		}
		//System.out.println(odds.toString()); // para ver probabilidades ao correr testes
		if (erro) return r;
		else { throw new AssertionError("Erro");} 
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
	}
}

