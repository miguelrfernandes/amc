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
import java.io.FileOutputStream;
import java.io.FileReader;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


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

	/**
	 * Create the application.
	 */
	public JanelaAprendizagem() {
		initialize();
	}

	
	 // Initialize the contents of the frame.

	private void initialize() {
		
		// janela e suas caracteristicas
		frmJanelaAprendizagem = new JFrame();
		frmJanelaAprendizagem.setTitle("Janela de Aprendizagem");
		frmJanelaAprendizagem.setBounds(100, 100, 450, 325);
		frmJanelaAprendizagem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJanelaAprendizagem.getContentPane().setLayout(null);
		
		JLabel lblStatus = new JLabel("");
		lblStatus.setBounds(38, 210, 388, 81);
		frmJanelaAprendizagem.getContentPane().add(lblStatus);
		
		JLabel lblModelName = new JLabel("ModelName");
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
				
				// TODO passar este codigo para a classe dataset?
				
				try (BufferedReader br = new BufferedReader(new FileReader(dspath))) { // leitura do dataset "ds" de um ficheiro .csv
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
				       //lblStatus.setText(lblStatus.getText().substring(0, lblStatus.getText().length()-7)+ "" + v.size() + "" + ds.getN()
				    	//	   + "" + ret  + "Reading dataset<br></html>");
				       
				       ds.Add(ret);
				    }
				    br.close();
				} catch (FileNotFoundException e1) {
		
					e1.printStackTrace();
				} catch (IOException e1) {
			
					e1.printStackTrace();
				}
				
				lblStatus.setText(lblStatus.getText().substring(0, lblStatus.getText().length()-7)  + "Training the model<br></html>");
				
				// PASSO 2
				ArrayList<MRFTree> mrftList = new ArrayList<MRFTree>();
				
				for (int k = 0; k < ds.Freqlist.size(); k++) { 
					
					Dataset dsfiber = ds.Fiber(k);
					WeightedGraph wg = new WeightedGraph(ds.getN());
					
					int[] D = dsfiber.getD(); //TODO isto aqui nao faz nada pq nos assumimos fibra.D = this.D no dataset
											  // e correto assumir valores maximos do dataset fibrado, tendo em conta o dataset original?
											  // assim temos valores xi e xj que nao ocorrem na fibra, logo prxi,prxj = 0 e ptt temos log(x/0) = NaN
					
					int m = ds.Freqlist.get(k); // m = dimens�o da fibra
				
					for (int i = 0; i < wg.getDim(); i++) { // ciclo para atribuir peso a cada aresta entre variavel i e variavel j
						for (int j = 0; j < wg.getDim(); j++) {
							
							double I = 0;
						
							for (int xi = 0; xi <= D[i]; xi++) { 
								for (int xj = 0; xj <= D[j]; xj++) { 
									double prxixj = dsfiber.Count(new int[] {i,j}, new int[] {xi, xj}) / m;  
									double prxi = dsfiber.Count(new int[] {i}, new int[] {xi}) / m; 
									double prxj = dsfiber.Count(new int[] {j}, new int[] {xj}) / m; 
									if (prxixj == 0 && (prxixj / (prxi * prxj)) == 0) { 
										I = I + 0;
									}
									else {
										I = I + prxixj * Math.log(prxixj / (prxi * prxj)); // correto
									}
									// para debugging:
									if (prxixj == 0 && prxi * prxj == 0) System.out.println("Erro: wg NaN causado por log(0). prxixj = " + prxixj + ", prxi * prxj = " + (prxi * prxj));
								}
							}
							wg.Add(i,  j,  I); // atribuir peso I a cada aresta entre i e j
						}
					}
					System.out.println(wg); //TODO output d� NaN
					Tree mst = wg.MST(); 
					mrftList.add(new MRFTree(mst, dsfiber));
				}
				
				Classifier classificador = new Classifier(mrftList, ds.Freqlist); // --> Outra Janela
				
				lblStatus.setText(lblStatus.getText().substring(0, lblStatus.getText().length()-7)  + "Saving the model<br></html>");
				
				String savePath = txtSavePath.getText();
				
				// TODO passar este codigo para a classe classifier?
				try {
				      File myObj = new File(savePath + txtModelName.getText());
				      if (myObj.createNewFile()) {
				        System.out.println("File created: " + myObj.getName());
				      } else {
				        System.out.println("File already exists.");
				      }
			    } catch (IOException error) {
			      System.out.println("An error occurred.");
			      error.printStackTrace();
			    }
				
				try {
					 
		            FileOutputStream fileOut = new FileOutputStream(savePath + txtModelName.getText());
		            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		            objectOut.writeObject(classificador); 
		            objectOut.close();
		            fileOut.close();
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
				
				lblStatus.setText(lblStatus.getText().substring(0, lblStatus.getText().length()-7)  + "The Classifier model was succesfully written to a file<br></html>");
			}
		});
		
		// Caixas de texto dentro da janela, para colocacao de diretorios
		
		btnNewButton.setBounds(309, 144, 117, 29);
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
		            txtModelName.setText(file.getName().substring(0, lblModelName.getText().length()-2) + ".ser");
		        }
			}
		});
		txtDsPath.setText("Click here to select the dataset path");
		txtDsPath.setBounds(48, 43, 253, 26);
		frmJanelaAprendizagem.getContentPane().add(txtDsPath);
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
		txtSavePath.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Save path for the model");
		lblNewLabel_1.setBounds(38, 81, 149, 16);
		frmJanelaAprendizagem.getContentPane().add(lblNewLabel_1);
		

	}
}
