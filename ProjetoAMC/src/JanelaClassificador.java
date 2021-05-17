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

public class JanelaClassificador {

	private JFrame frame;
	private JTextField txtModelPath;
	private JTextField txtSample;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaClassificador window = new JanelaClassificador();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Model");
		lblNewLabel.setBounds(6, 6, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		//Create a file chooser
		final JFileChooser fc = new JFileChooser();
		
		txtModelPath = new JTextField();
		txtModelPath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//In response to a button click:
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal = fc.showOpenDialog(fc); //showOpenDialog(aComponent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            //log.append("Opening: " + file.getName() + "." + newline);
		            txtModelPath.setText(file.getPath());
		        } else {
		            //log.append("Open command cancelled by user." + newline);
		        }
			}
		});
		txtModelPath.setText("Click here to select the path for the file");
		txtModelPath.setBounds(6, 34, 259, 26);
		frame.getContentPane().add(txtModelPath);
		txtModelPath.setColumns(10);
		
		JButton btnNewButton = new JButton("Load");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String modelPath = txtModelPath.getText();
		            FileInputStream fileIn = new FileInputStream(modelPath);
		            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
		 
		            Object obj = objectIn.readObject(); // nao esta correto
		            // classificador = objectIn.readObject();
		 
		            System.out.println("The Object has been read from the file");
		            objectIn.close();
		 
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
			}
		});
		btnNewButton.setBounds(16, 72, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Sample");
		lblNewLabel_1.setBounds(6, 113, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtSample = new JTextField();
		txtSample.setText("x1,x2,...,xn");
		txtSample.setBounds(6, 141, 130, 26);
		frame.getContentPane().add(txtSample);
		txtSample.setColumns(10);
		
		JLabel lblResult = new JLabel("Result: ");
		lblResult.setBounds(26, 220, 61, 16);
		frame.getContentPane().add(lblResult);
		
		JButton btnClassify = new JButton("Classify");
		btnClassify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String input = txtSample.getText();
				//int[] sample = new int[];
				//String result = classificador.classify(sample);
				//lblResult.setText(result);
			}
		});
		btnClassify.setBounds(16, 179, 117, 29);
		frame.getContentPane().add(btnClassify);
		
		
		
		JButton btnNewButton_2 = new JButton("Reset");
		btnNewButton_2.setBounds(148, 141, 117, 29);
		frame.getContentPane().add(btnNewButton_2);
		
		JLabel lblModelStatus = new JLabel("Model not yet loaded");
		lblModelStatus.setBounds(145, 77, 160, 16);
		frame.getContentPane().add(lblModelStatus);
	}
}
