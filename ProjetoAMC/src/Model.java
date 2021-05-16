import java.io.Serializable;
import java.util.ArrayList;

public class Model implements Serializable {
	//default serialVersion id
    private static final long serialVersionUID = 1L;
    
    public ArrayList<MRFTree> mrftList;
    public ArrayList<Integer> FreqList;
    
	public Model(ArrayList<MRFTree> mrftList, ArrayList<Integer> freqList) {
		super();
		this.mrftList = mrftList;
		FreqList = freqList;
	}
}
