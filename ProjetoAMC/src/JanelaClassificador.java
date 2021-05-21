import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class JanelaClassificador {

	private JFrame frmJanelaClassificador;
	private JTextField txtModelPath;
	private JTextField txtSample;

	
	// Classifier
	Classifier classificador = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaClassificador window = new JanelaClassificador();
					window.frmJanelaClassificador.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JanelaClassificador() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJanelaClassificador = new JFrame();
		frmJanelaClassificador.setTitle("Janela do Classificador");
		frmJanelaClassificador.setBounds(100, 100, 450, 300);
		frmJanelaClassificador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJanelaClassificador.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Model");
		lblNewLabel.setBounds(6, 6, 61, 16);
		frmJanelaClassificador.getContentPane().add(lblNewLabel);
		

		
		//Create a file chooser
		final JFileChooser fc = new JFileChooser();
		
		txtModelPath = new JTextField();
		txtModelPath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // TODO adicionar restricao para ficheiros com a nossa implementacao modelo 
				//In response to a button click:
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal = fc.showOpenDialog(fc);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            txtModelPath.setText(file.getPath());
		        }
			}
		});
		txtModelPath.setText("Click here to select the path for the file");
		txtModelPath.setBounds(6, 34, 259, 26);
		frmJanelaClassificador.getContentPane().add(txtModelPath);
		txtModelPath.setColumns(10);
		
		JLabel lblModelStatus = new JLabel("Model not yet loaded");
		lblModelStatus.setBounds(145, 77, 160, 16);
		frmJanelaClassificador.getContentPane().add(lblModelStatus);
		
		JButton btnNewButton = new JButton("Load");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String modelPath = txtModelPath.getText();
		            FileInputStream fileIn = new FileInputStream(modelPath);
		            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
		 
		            // lemos o ficheiro e guardamos este Objeto na variável classificador
		            // e necessario usar um cast (Classifier) para definir qual vai ser a classe do objeto que vai ser lido
		            classificador = (Classifier) objectIn.readObject();
		 
		            System.out.println("The Classifier model has been read from the file");
		            lblModelStatus.setText("OK!");
		            
		            objectIn.close();
		            fileIn.close();
		            String newsample = "";
		            for (int i = 0; i < classificador.getN()-1; i++) newsample = newsample + "0,";
		            newsample = newsample + "0";
		            txtSample.setText(newsample);
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
			}
		});
		btnNewButton.setBounds(16, 72, 117, 29);
		frmJanelaClassificador.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("<html>Sample : x<sub>1</sub>,x<sub>2</sub>,...,x<sub>n</sub></html>"); //TODO nao da para por ao lado "n = valor"?
		lblNewLabel_1.setBounds(6, 115, 141, 24);
		frmJanelaClassificador.getContentPane().add(lblNewLabel_1);
		
		txtSample = new JTextField();
		txtSample.setText("0,...,0");
		txtSample.setBounds(6, 141, 299, 26);
		frmJanelaClassificador.getContentPane().add(txtSample);
		txtSample.setColumns(10);
		
		JLabel lblResult = new JLabel("Result: ");
		lblResult.setBounds(26, 228, 169, 16);
		frmJanelaClassificador.getContentPane().add(lblResult);
		
		JButton btnClassify = new JButton("Classify");
		btnClassify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String input = txtSample.getText();
					String[] values = input.split(",");
					ArrayList<Integer> v = new ArrayList<Integer>();
					for (String a : values) { 
			    	   v.add(Integer.parseInt(a));
					}
					int[] sample = new int[classificador.getN()]; //TODO d� erro. Nao da para ir buscar o n ao ficheiro do modelo? 
					if (v.size() != sample.length) {
						System.out.println("Erro: A amostra fornecida nao tem o numero de medicoes correto");
					}
					for (int i=0; i < v.size(); i++)
					{
			           sample[i] = v.get(i).intValue();
					}	
					String result = "Result : " + classificador.classify(sample); 
					lblResult.setText(result);
				} catch (Exception error) {
					lblResult.setText("Result : Error");
				}
			}
		});
		btnClassify.setBounds(16, 179, 117, 29);
		frmJanelaClassificador.getContentPane().add(btnClassify);
		
		
		
		JButton btnReset = new JButton("Reset");
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSample.setText("0,...,0");
				lblResult.setText("Result :");
				String newsample = "0,...,0";
	            if (classificador != null) {
	            	newsample = "";
					for (int i = 0; i < classificador.getN()-1; i++) newsample = newsample + "0,";
		            newsample = newsample + "0";
	            }
		        txtSample.setText(newsample);
			}
		});
		btnReset.setBounds(327, 141, 117, 29);
		frmJanelaClassificador.getContentPane().add(btnReset);
	}
}
