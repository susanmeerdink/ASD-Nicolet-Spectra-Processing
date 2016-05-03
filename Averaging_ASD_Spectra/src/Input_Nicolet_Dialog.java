import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.io.File;

import au.com.bytecode.opencsv.CSVReader;


public class Input_Nicolet_Dialog implements ActionListener{
	private JFrame frame = new JFrame("Program to batch average Nicolet spectra");
	private Container pane = frame.getContentPane();
	
	private JButton button1 = new JButton("Browse");
	private JButton button2 = new JButton("Browse");
	private JButton button3 = new JButton("Browse");
	private JButton buttonCancel = new JButton("Cancel");
	private JButton buttonNext = new JButton("Next");
	
	private JTextField textBox1 = new JTextField();
	private JTextField textBox2 = new JTextField();
	private JTextField textBox3 = new JTextField();
	
	private ArrayList<Spectra> allSpectra = new ArrayList<Spectra>();
	private ArrayList<SpectraFileList> allSpectraFileList = new ArrayList<SpectraFileList>();
	private FileOutputStream fout;
	
	public Input_Nicolet_Dialog(){
		
		pane.setLayout(new GridBagLayout()); //Use GridBagLayout for GUI set up
		GridBagConstraints c = new GridBagConstraints();
		
		Font font = new Font("Helvetica", Font.BOLD,14);
		
		//JLabel text1 = new JLabel("This program batch performs averaging of ASD spectra.");
		JLabel textStep1 = new JLabel("Step 1: Designate directory that contains Nicolet spectra");
		JLabel textStep1a = new JLabel("Files within directory should be .TAB or .txt files with 2 columns of data. These files have had JPL corrections completed.");
		JLabel textStep2 = new JLabel("Step 2: Upload Spectra File List");
		JLabel text2a = new JLabel("This file must be a .csv file with the first column containing the Sample ID, all other columns containing the file names associated");
		JLabel text2b = new JLabel("with that spectra. For example: VH001, 2013_04_02_001a, 2013_04_02_001b.");
		JLabel textStep3 = new JLabel("Step 3: Set Output File");
		JLabel textSF1 = new JLabel("Selected File");
		JLabel textSF2 = new JLabel("Selected File");
		JLabel textCF = new JLabel("Create File");
		
		textStep1.setFont(font);
		textStep2.setFont(font);
		textStep3.setFont(font);
		
		textBox1.setPreferredSize(new Dimension(800, 25));
		textBox2.setPreferredSize(new Dimension(800, 25));
		textBox3.setPreferredSize(new Dimension(800, 25));
		button1.addActionListener(this); 
		button2.addActionListener(this); 
		button3.addActionListener(this); 
		buttonCancel.addActionListener(this); 
		buttonNext.addActionListener(this);
		
		c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10,5,5,5);  //top padding
    	c.gridx = 0;
    	c.gridy = 0;
    	c.gridwidth = 3;
    	pane.add(textStep1,c); //Add Label 
    	c.insets = new Insets(5,5,5,5);  //padding
    	c.gridx = 0;
    	c.gridy = 1;
    	pane.add(textStep1a,c); //Add Label 
    	c.gridx = 0;
    	c.gridy = 2;
    	c.gridwidth = 1;
    	pane.add(textSF1,c); //Add Label 
    	c.gridx = 1;
    	c.gridy = 2;
    	pane.add(textBox1,c); //Add TextBox
    	c.gridx = 2;
    	c.gridy = 2;
    	pane.add(button1,c); 
    	
        c.insets = new Insets(10,5,5,5);  //top padding
    	c.gridx = 0;
    	c.gridy = 3;
    	c.gridwidth = 3;
    	pane.add(textStep2,c); //Add Label
    	c.insets = new Insets(5,5,5,5);  //padding
    	c.gridx = 0;
    	c.gridy = 4;
    	pane.add(text2a,c); //Add Label 
    	c.gridx = 0;
    	c.gridy = 5;
    	pane.add(text2b,c); //Add Label 
    	c.gridx = 0;
    	c.gridy = 6;
    	c.gridwidth = 1;
    	pane.add(textSF2,c); //Add Label 
    	c.gridx = 1;
    	c.gridy = 6;
    	pane.add(textBox2,c);
    	c.gridx = 2;
    	c.gridy = 6;
    	pane.add(button2,c);
    	
        c.insets = new Insets(10,5,5,5);  //top padding
    	c.gridx = 0;
    	c.gridy = 7;
    	c.gridwidth = 3;
    	pane.add(textStep3,c); //Add Label 
    	c.gridx = 0;
    	c.gridy = 8;
    	c.gridwidth=1;
    	pane.add(textCF,c); //Add Label for Displayed Samples
    	c.gridx = 1;
    	c.gridy = 8;
    	pane.add(textBox3,c);
    	c.gridx = 2;
    	c.gridy = 8;
    	pane.add(button3,c);
    	
    	c.gridx = 0;
    	c.gridy = 9;
    	pane.add(buttonCancel,c);
    	c.gridx = 2;
    	c.gridy = 9;
    	pane.add(buttonNext,c);
		
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Button that reads files in from a directory
		if(e.getSource() == button1){
			JFileChooser selectfile = new JFileChooser(); //create a file chooser window
			selectfile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int result = selectfile.showOpenDialog(frame); //and an integer representing the button clicked
			if(result == JFileChooser.APPROVE_OPTION) {
				File folder = new File(selectfile.getSelectedFile().getPath());
				File[] listOfFiles = folder.listFiles();
				textBox1.setText(selectfile.getSelectedFile().getPath());
				
				for (File file : listOfFiles) {
				    if (file.isFile()) {
				    	//if (file.getName().contains(".csv") ^ file.getName().contains(".CSV")){
				    		//System.out.println("File contains .csv in name");
							try {
								FileReader fileIN = new FileReader(file.getPath()); // read in the file
								BufferedReader reader = new BufferedReader(fileIN);
								double[] tempArray = new double[1869];
								int i = 0;
								String tempRecord = null;
								while((tempRecord = reader.readLine()) != null) {
									if(tempRecord.isEmpty()){
										break;
									}
									else{
										String[] record = tempRecord.split("\\s+");//first index is blank, second is wavelength, third is reflectance
										//System.out.println(i + " : " + Arrays.toString(record));
										double temp = Double.parseDouble((record[2]));
										tempArray[i] = temp;
										//System.out.println(i + " : " + tempArray[i]);
										i = i +1;
									}
								}	
								//Reverse order the array b/c the file goes from long to short wavelengths
								double[] tempRev = new double[1869];
								for(int j = 0; j < tempArray.length;j++){
									System.out.println(j + " , " + (tempArray.length-j -1));
									tempRev[j] = tempArray[tempArray.length-j -1];
									System.out.println(j + " : " + tempRev[j] + " , " + tempArray[tempArray.length-j -1]);
								}
								allSpectra.add(new Spectra(file.getName().split("\\.")[0],tempRev)); //create a spectra object and add to allSpectra list
								reader.close();
							} 
							catch (NumberFormatException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				    	//}
				    }
				}
			}
		}
		
		//Button that reads in spectra file list
		if(e.getSource() == button2){
			JFileChooser selectfile = new JFileChooser(); //create a file chooser window
			int result = selectfile.showOpenDialog(frame); //and an integer representing the button clicked
			if(result == JFileChooser.APPROVE_OPTION) {
				try{	
					CSVReader reader = new CSVReader(new FileReader(selectfile.getSelectedFile().getPath()), ','); // read in the file
					String[] record;
					while((record = reader.readNext()) != null) {
						if (record[0].isEmpty() == false){
							String[] inputRecord = Arrays.copyOfRange(record, 1, record.length);
							allSpectraFileList.add(new SpectraFileList(record[0],inputRecord)); //create a spectra object and add to allSpectra list
							//System.out.println("Filename: " + record[0]);
							//System.out.println("List of Files: " + Arrays.toString(inputRecord));
						}
					}
					reader.close();
					textBox2.setText(selectfile.getSelectedFile().getPath());
					
				}
				catch (IOException ex) {
					if (ex.getMessage() != null) System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		
		//Button that creates output file
		if(e.getSource() == button3){
			JFileChooser selectfile = new JFileChooser(); //create a file chooser window
			int result = selectfile.showSaveDialog(frame); //and an integer representing the button clicked
			if(result == JFileChooser.APPROVE_OPTION) {
				try{
					if (selectfile.getSelectedFile().getName().contains(".CSV")){
						fout = new FileOutputStream(selectfile.getSelectedFile());
						textBox3.setText(selectfile.getSelectedFile().getPath());
					}
					else{
						fout = new FileOutputStream(selectfile.getSelectedFile()+".csv");
						textBox3.setText(selectfile.getSelectedFile().getPath()+".csv");
					}					
				}
				catch (IOException ex) {
					if (ex.getMessage() != null) System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		
		//Button that closes the Program
		if(e.getSource() == buttonCancel){
			System.exit(0);	
		}
		
		//Button that moves onto Visualizing/Averaging GUI
		if(e.getSource() == buttonNext){
			if(allSpectra.isEmpty() || allSpectraFileList.isEmpty() || fout == null){
				JOptionPane.showMessageDialog(frame,"Please fill in all 3 inputs.", "Input error",JOptionPane.ERROR_MESSAGE);
			}
			else{
				try {
					GUI_Avg_Spectra gui = new GUI_Avg_Spectra(2);
					gui.setVar(allSpectra,allSpectraFileList,fout);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(false);
				
			}
		}
	}
}