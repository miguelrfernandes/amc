import java.awt.EventQueue;

import javax.swing.JFrame;
//import java.awt.GridLayout;
//import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;


import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;

//import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileOutputStream;
import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Scanner; // Import the Scanner class to read text files

public class JanelaAprendizagem {

	private JFrame frmJanelaAprendizagem;
	private JTextField txtDsPath;
	private JTextField txtSavePath;

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
		frmJanelaAprendizagem.setTitle("Janela — Aprendizagem");
		frmJanelaAprendizagem.setBounds(100, 100, 450, 325);
		frmJanelaAprendizagem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJanelaAprendizagem.getContentPane().setLayout(null);
		
		JLabel lblStatus = new JLabel("");
		lblStatus.setBounds(37, 185, 388, 81);
		frmJanelaAprendizagem.getContentPane().add(lblStatus);
		
		JButton btnNewButton = new JButton("Learn");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblStatus.setText("<html>Training initiated<br></html>");
				
				
				String dspath = txtDsPath.getText();
				
				//int n = Integer.parseInt(txtSize.getText());
				
				Dataset ds = null;
				
				lblStatus.setText(lblStatus.getText().substring(0, lblStatus.getText().length()-7)  + "Reading dataset<br></html>");
				
				try (BufferedReader br = new BufferedReader(new FileReader(dspath))) { // leitura e gravação do dataset "ds" num ficheiro
				    String line;
				    while ((line = br.readLine()) != null) {
				       String[] values = line.split(",");
				       ArrayList<Integer> v = new ArrayList<Integer>();
				       // int v[] = new int[n];
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
				       
				       ds.Add(ret); // ArrayList<int[]>
				    }
				//TODO aqui nao falta br.close() ?
				// nao tenho a certeza se e necessario neste caso...
				} catch (FileNotFoundException e1) {
		
					e1.printStackTrace();
				} catch (IOException e1) {
			
					e1.printStackTrace();
				}
				
				lblStatus.setText(lblStatus.getText().substring(0, lblStatus.getText().length()-7)  + "Training the model<br></html>");
				
				WeightedGraph wg = new WeightedGraph(ds.getN());
				int[] D = ds.getD();
				int m = 0;
				for (int freq : ds.Freqlist) m += freq;
				
				// PASSO 2
				for (int i = 0; i < wg.getDim(); i++) { // ciclo para atribuir peso a cada aresta entre variavel i e variavel j
					for (int j = 0; j < wg.getDim(); j++) {
						// calculamos a informacao mutua de cada variavel e guardamos
						// este valor na aresta deste grafo pesado completo
						double I = 0;
						
						// PASSO 2 - as variaveis estao definidas de acordo com o enunciado
						for (int xi = 0; xi <= D[i]; xi++) { // ciclo que calcula o I, tendo em conta o dataset T
							for (int xj = 0; xj <= D[j]; xj++) { 
								double prxixj = ds.Count(new int[] {i,j}, new int[] {xi, xj}) / m;  
								double prxi = ds.Count(new int[] {i}, new int[] {xi}) / m; 
								double prxj = ds.Count(new int[] {j}, new int[] {xj}) / m; 
								if (prxixj == 0 && (prxixj / (prxi * prxj)) == 0) { 
									I = I + 0;
								}
								else {
									I = I + prxixj * Math.log(prxixj / (prxi * prxj)); // correto
								}
								// para debugging:
								if (prxixj / (prxi * prxj) == 0) System.out.println("Erro — wg NaN causado por log(0)");
							}
						}
						wg.Add(i,  j,  I); // atribuir peso I a cada aresta entre i e j
					}
				}
				System.out.println(wg); //TODO output d� NaN
				Tree mst = wg.MST(); 
				ArrayList<MRFTree> mrftList = new ArrayList<MRFTree>();
				for (int i = 0; i < ds.getClassifierDomain(); i++) { 
					mrftList.add(new MRFTree(mst, ds.Fiber(i)));
				}
				
				Classifier classificador = new Classifier(mrftList, ds.Freqlist); // --> Outra Janela
				//Model modelo = new Model(mrftList, ds.Freqlist);
				
				lblStatus.setText(lblStatus.getText().substring(0, lblStatus.getText().length()-7)  + "Saving the model<br></html>");
				
				String savePath = txtSavePath.getText();
				
				try {
				      File myObj = new File(savePath + "modelo");
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
					 
		            FileOutputStream fileOut = new FileOutputStream(savePath + "modelo");
		            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		            objectOut.writeObject(classificador); 
		            objectOut.close();
			    //TODO aqui nao falta fileOut.close() ?
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
				
				//TODO simplesmente pus o output na caixa em vez de na consola, mas nao consigo perceber se o conteudo esta a ser gravado no ficheiro
				
				lblStatus.setText(lblStatus.getText().substring(0, lblStatus.getText().length()-7)  + "The Object  was succesfully written to a file<br></html>");
			}
		});
		
		// Caixas de texto dentro da janela, para colocacao de diretorios
		
		btnNewButton.setBounds(174, 149, 117, 29);
		frmJanelaAprendizagem.getContentPane().add(btnNewButton);
		
		//Create a file chooser
		final JFileChooser fc = new JFileChooser();
		
		txtDsPath = new JTextField();
		txtDsPath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//In response to a button click:
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal = fc.showOpenDialog(fc); //showOpenDialog(aComponent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            //This is where a real application would open the file.
		            //log.append("Opening: " + file.getName() + "." + newline);
		            txtDsPath.setText(file.getPath());
		            
		        } else {
		            //log.append("Open command cancelled by user." + newline);
		        }
			}
		});
		txtDsPath.setText("Click here to select the path");
		txtDsPath.setBounds(38, 43, 253, 26);
		frmJanelaAprendizagem.getContentPane().add(txtDsPath);
		txtDsPath.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Dataset path");
		lblNewLabel.setBounds(38, 15, 89, 16);
		frmJanelaAprendizagem.getContentPane().add(lblNewLabel);
		
		txtSavePath = new JTextField();
		txtSavePath.setText("Click here to select the path");
		txtSavePath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//In response to a button click:
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showOpenDialog(fc); //showOpenDialog(aComponent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File directory = fc.getSelectedFile();
		            //This is where a real application would open the file.
		            txtSavePath.setText(directory.getPath() + "/");
		            
		        }
			}
		});
		txtSavePath.setBounds(38, 109, 253, 26);
		frmJanelaAprendizagem.getContentPane().add(txtSavePath);
		txtSavePath.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Save path for the model");
		lblNewLabel_1.setBounds(38, 81, 149, 16);
		frmJanelaAprendizagem.getContentPane().add(lblNewLabel_1);
		
		
	}
}
