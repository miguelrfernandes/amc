import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;


import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;



public class JanelaAprendizagem {
	private JFrame frmJanelaAprendizagem;
	private JTextField txtDsPath;
	private JTextField txtSavePath;
	private JTextField txtModelName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaAprendizagem window = new JanelaAprendizagem();
					window.frmJanelaAprendizagem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	 // Create the application.
	 
	public JanelaAprendizagem() {
		initialize();
	}
	
	public ArrayList<MRFTree> ChowLiu(Dataset ds) {
		ArrayList<MRFTree> mrftList = new ArrayList<MRFTree>();
		
		int[] D = ds.getD();
		
		for (int k = 0; k < ds.Freqlist.size(); k++) { 
			
			Dataset dsfiber = ds.Fiber(k);
		
			WeightedGraph wg = new WeightedGraph(ds.getN());
			double mC = ds.Freqlist.get(k);
			
			for (int j = 0; j < wg.getDim(); j++) { 
				for (int i = 0; i < j; i++) {
					double I = 0;

					for (int xi = 0; xi <= D[i]; xi++) { 
						for (int xj = 0; xj <= D[j]; xj++) { 
							double prxixj = Double.valueOf(dsfiber.Count(new int[] {i,j}, new int[] {xi, xj})) / mC;  
							double prxi = Double.valueOf(dsfiber.Count(new int[] {i}, new int[] {xi})) / mC; 
							double prxj = Double.valueOf(dsfiber.Count(new int[] {j}, new int[] {xj})) / mC; 
							
							/*if (prxixj == 0 || prxi * prxj == 0) System.out.println("Erro: wg com NaN causado por log(0). prxixj = " +
							prxixj + ", prxi * prxj = " + (prxi * prxj) + ", prxixj = dsfiber.Count(new int[] {" + i + "," + j +
							"}, new int[] {" + xi + "," + xj + "}) / " + mC);
							*/
							
							if (prxixj == 0.0 || (prxixj / (prxi * prxj)) == 0.0) { // TODO trocar || por &&, analisar enunciado e metodo de chowliu
								I = I + 0.0;
							}
							else {
								I = I + prxixj * Math.log(prxixj / (prxi * prxj)); 
							}
						}
					}
					if (i!=j) {wg.Add(i,  j,  I);}
					else {wg.Add(i, j, -1.0);}
				}
			}
			
			mrftList.add(new MRFTree(wg.MST(), dsfiber)); 	
		}
		
		return mrftList;
		
	}
	
	public void testes_old(Dataset ds, Classifier classificador, int limit) {
		System.out.println(classificador);
		
		System.out.println("Beginning tests...");
		//int limit = 1000000;
		int rtests = 0;
		for (int i = 0; i < ds.getData().size() && i < limit; i++) {
			int[] sample = Arrays.copyOfRange(ds.getData().get(i), 0, ds.getData().get(i).length-1);
			int expected = ds.getData().get(i)[ds.getData().get(i).length-1];
			int predicted = classificador.classify(sample);
			boolean result = expected==predicted;
			if (result) rtests++;
			System.out.println("" + result + ", expected = " + expected + ", predicted = " + predicted);
		}
		System.out.println("Finished. " + rtests + "/" + Math.min(limit, ds.getData().size()));
	}
	
	public void testes(Dataset ds, int limit) {		
		System.out.println("Beginning tests...");
		int rtests = 0;
		
		if (limit == 0) limit = Integer.MAX_VALUE;
		
		for (int i = 0; i < ds.getData().size() && i < limit; i++) {
			int[] sample = ds.getData().get(i);
			ds.getData().remove(i);
			int l = ds.Freqlist.get(sample[ds.getN()]);
			ds.Freqlist.set(sample[ds.getN()], --l);
					
			ArrayList<MRFTree> mrftList = ChowLiu(ds);
			Classifier classificador = new Classifier(mrftList, ds.Freqlist); 
			
			int expected = sample[sample.length-1];
			int predicted = classificador.classify(sample);
			boolean result = expected==predicted;
			if (result) rtests++;
			System.out.println("" + result + ", expected = " + expected + ", predicted = " + predicted);
			
			ds.Add(sample);
		}
		
		System.out.println("Finished. " + rtests + "/" + Math.min(limit, ds.getData().size()));
	}
	
	// Initialize the contents of the frame.

	private void initialize() {
		
		// janela e suas caracteristicas
		frmJanelaAprendizagem = new JFrame();
		frmJanelaAprendizagem.setTitle("Janela de Aprendizagem");
		frmJanelaAprendizagem.setBounds(100, 100, 450, 335);
		frmJanelaAprendizagem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJanelaAprendizagem.getContentPane().setLayout(null);
		
		JLabel lblStatus = new JLabel("");
		lblStatus.setBounds(38, 210, 388, 81);
		frmJanelaAprendizagem.getContentPane().add(lblStatus);
		
		JLabel lblModelName = new JLabel("Model Name");
		lblModelName.setBounds(37, 149, 90, 16);
		frmJanelaAprendizagem.getContentPane().add(lblModelName);
		
		txtModelName = new JTextField();
		txtModelName.setBounds(47, 172, 250, 26);
		frmJanelaAprendizagem.getContentPane().add(txtModelName);
		txtModelName.setColumns(10);
		
		JButton btnNewButton = new JButton("Learn");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblStatus.setText("<html>Training initiated<br></html>");
				
				String dspath = txtDsPath.getText();
				
				Dataset ds = null;
				
				lblStatus.setText(lblStatus.getText().substring(0, lblStatus.getText().length()-7)  + "Reading dataset<br></html>");
				
				// leitura do dataset "ds" de um ficheiro .csv
				try (BufferedReader br = new BufferedReader(new FileReader(dspath))) { 
				    String line;
				    while ((line = br.readLine()) != null) {
				       String[] values = line.split(",");
				       ArrayList<Integer> v = new ArrayList<Integer>();
				       for (String a : values) { 
				    	   v.add(Integer.parseInt(a));
				       }
				       int[] ret = new int[v.size()];
				       for (int i=0; i < v.size(); i++)
				       {
				           ret[i] = v.get(i).intValue();
				       }
				       if (ds == null) {
				    	   ds = new Dataset(ret.length-1);
				       }
				       ds.Add(ret);
				    }
				    br.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				lblStatus.setText(lblStatus.getText().substring(0, lblStatus.getText().length()-7)  + "Training the model<br></html>");
				
				// PASSO 2 - Treinamento do Modelo | Chow-Liu Algorithm
				ArrayList<MRFTree> mrftList = ChowLiu(ds);
				
				Classifier classificador = new Classifier(mrftList, ds.Freqlist); 
				
				lblStatus.setText(lblStatus.getText().substring(0, lblStatus.getText().length()-7)  + "Saving the model </html>");
				
				String savePath = txtSavePath.getText();
				 
				try {
				      File myObj = new File(savePath + txtModelName.getText());
				      if (myObj.createNewFile()) {
				        System.out.println("File created: " + myObj.getName());
				        lblStatus.setText(lblStatus.getText().substring(0, lblStatus.getText().length()-7)  + "File created: " + myObj.getName() + ".<br></html>");
				      } else {
				        System.out.println("File already exists.");
				        lblStatus.setText(lblStatus.getText().substring(0, lblStatus.getText().length()-7)  + "File already exists. Overwriting.<br></html>");
				      }
			    } catch (IOException error) {
			      System.out.println("An error occurred.");
			      error.printStackTrace();
			    }
				
				classificador.writeFile(savePath + txtModelName.getText());
				
				lblStatus.setText(lblStatus.getText().substring(0, lblStatus.getText().length()-7)  + "The Classifier model was succesfully written to a file<br></html>");
				
				// TESTES
				//testes(ds, 0);
			}
		});
		
		// Caixas de texto dentro da janela, para colocacao de diretorios
		
		btnNewButton.setBounds(309, 169, 117, 29);
		frmJanelaAprendizagem.getContentPane().add(btnNewButton);
		
		//Create a file chooser
		final JFileChooser fc = new JFileChooser();
		
		txtDsPath = new JTextField();
		txtDsPath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//In response to a button click:
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal = fc.showOpenDialog(fc);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            txtDsPath.setText(file.getPath());
		            txtModelName.setText(file.getName().substring(0, file.getName().length()-4) + ".ser");
		        }
			}
		});
		txtDsPath.setText("Click here to select the dataset path");
		
		txtDsPath.setBounds(48, 43, 253, 26);
		frmJanelaAprendizagem.getContentPane().add(txtDsPath);
		//txtDsPath.setText("/Users/miguelfernandes/Documents/GitHub/amc/Datasets2021/bcancer.csv"); // TODO comentar e apagar no fim
		txtDsPath.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Dataset path");
		lblNewLabel.setBounds(38, 15, 89, 16);
		frmJanelaAprendizagem.getContentPane().add(lblNewLabel);
		
		txtSavePath = new JTextField();
		txtSavePath.setText("Click here to select a directory");
		txtSavePath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//In response to a button click:
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showOpenDialog(fc);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File directory = fc.getSelectedFile();
		            txtSavePath.setText(directory.getPath() + "/");
		        }
			}
		});
		txtSavePath.setBounds(48, 109, 253, 26);
		frmJanelaAprendizagem.getContentPane().add(txtSavePath);
		//txtSavePath.setText("/Users/miguelfernandes/Documents/GitHub/amc/Models2021/bcancer.ser"); // TODO comentar e apagar no fim
		txtSavePath.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Save path for the model");
		lblNewLabel_1.setBounds(38, 81, 149, 16);
		frmJanelaAprendizagem.getContentPane().add(lblNewLabel_1);
	}
}
