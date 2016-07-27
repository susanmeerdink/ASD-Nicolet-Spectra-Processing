import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



public class Input_ASD_Dialog implements ActionListener{
	private JFrame frame = new JFrame("Program to batch average ASD spectra");
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

	private String dir = System.getProperty("user.dir"); //Get current working directory
	
	public Input_ASD_Dialog(){
		
		pane.setLayout(new GridBagLayout()); //Use GridBagLayout for GUI set up
		GridBagConstraints c = new GridBagConstraints();
		
		Font font = new Font("Helvetica", Font.BOLD,14);
		
		JLabel text2 = new JLabel("Step 1: Upload ASD Spectra");
		JLabel text3 = new JLabel("The input ASD file must be created in ViewSpecPro available at www.asdi.com. When creating the file in ASCII Export make sure to set these settings:");
		JLabel text4 = new JLabel("Data Format: Reflectance,  Derivative: None,  Data Organization: Row,  Field Seperator: Comma with Output to a Single File");
		JLabel text5 = new JLabel("No Header Information,  Do not Print X-Axis,  No Description or Note,  Print Row Title with FileName(s) to Left of Row");
		
		JLabel text6 = new JLabel("Step 2: Upload Spectra File List");
		JLabel text7 = new JLabel("This file must be a .csv file with the first column containing the Sample ID, second column containing the first ASD filenumber associated");
		JLabel text8 = new JLabel("with that spectra, and the third column containing the last ASD file number associated with that spectra. For example: VH001, 314, 320.");
		JLabel textSF1 = new JLabel("Selected File");
		JLabel textSF2 = new JLabel("Selected File");
		JLabel text9 = new JLabel("Step 3: Set Output File");
		JLabel textCF = new JLabel("Create File");
		
		text2.setFont(font);
		text6.setFont(font);
		text9.setFont(font);
		
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

    	c.gridx = 1;
    	c.gridy = 2;
    	pane.add(text2,c); 
    	c.insets = new Insets(5,5,5,5);  //padding
    	c.gridx = 1;
    	c.gridy = 3;
    	pane.add(text3,c); 
    	c.gridx = 1;
    	c.gridy = 4;
    	pane.add(text4,c); 
    	c.gridx = 1;
    	c.gridy = 5;
    	pane.add(text5,c); 

    	c.gridx = 0;
    	c.gridy = 6;
    	pane.add(textSF1,c); 

    	c.gridx = 1;
    	c.gridy = 6;
    	pane.add(textBox1,c); 
    	c.gridx = 2;
    	c.gridy = 6;
    	pane.add(button1,c); 
    	
    	c.insets = new Insets(10,5,5,5);  //top padding
    	c.gridx = 1;
    	c.gridy = 7;
    	pane.add(text6,c); //Add Label for Displayed Samples
    	c.insets = new Insets(5,5,5,5);  //padding
    	c.gridx = 1;
    	c.gridy = 8;
    	pane.add(text7,c); 
    	c.gridx = 1;
    	c.gridy = 9;
    	pane.add(text8,c); 

    	c.gridx = 0;
    	c.gridy = 10;
    	pane.add(textSF2,c); 

    	c.gridx = 1;
    	c.gridy = 10;
    	pane.add(textBox2,c);
    	c.gridx = 2;
    	c.gridy = 10;
    	pane.add(button2,c);
    	
    	c.insets = new Insets(10,5,5,5);  // padding
    	c.gridx = 1;
    	c.gridy = 11;
    	pane.add(text9,c);   
    	c.insets = new Insets(5,5,5,5);  //padding
    	c.gridx = 0;
    	c.gridy = 12;
    	pane.add(textCF,c); 
    	c.gridx = 1;
    	c.gridy = 12;
    	pane.add(textBox3,c);
    	c.gridx = 2;
    	c.gridy = 12;
    	pane.add(button3,c);
    	
    	c.gridx = 0;
    	c.gridy = 13;
    	pane.add(buttonCancel,c);
    	c.gridx = 2;
    	c.gridy = 13;
    	pane.add(buttonNext,c);
		
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Browses to file that contains the spectra, opens it, and saves it as variable
		if(e.getSource() == button1){
			JFileChooser selectfile = new JFileChooser(dir); //create a file chooser window
			int result = selectfile.showOpenDialog(frame); //and an integer representing the button clicked
			if(result == JFileChooser.APPROVE_OPTION) {
				try{
					dir = selectfile.getSelectedFile().getPath(); //set working directory to path name
					dir = dir.replace(selectfile.getSelectedFile().getName(),"");
					int lineNumber = 0; //used to skip the first line of the file
					for (String line : Files.readAllLines(Paths.get(selectfile.getSelectedFile().getPath()))){
						if (lineNumber != 0){ //If it isn't the first line execute
							String[] record;
							record = line.split(","); //Split the line based on commas
							//Each line of the file contains a spectra, with the first index as Sample Name
							double[] tempArray = new double[record.length -1];
							for( int i = 1;i<record.length;i++){ //Read in spectral values
								tempArray[i-1] = (Double.parseDouble((record[i])));
							}
							allSpectra.add(new Spectra(record[0],tempArray)); //create a spectra object and add to allSpectra list
						}
						lineNumber = lineNumber +1; //Advance conter
					}
					//reader.close();
					textBox1.setText(selectfile.getSelectedFile().getPath());
					
				}
				catch (IOException ex) {
					if (ex.getMessage() != null) System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		//Browses to file that contains the spectra file list, opens, and saves it as variable
		if(e.getSource() == button2){
			JFileChooser selectfile = new JFileChooser(dir); //create a file chooser window
			int result = selectfile.showOpenDialog(frame); //and an integer representing the button clicked
			if(result == JFileChooser.APPROVE_OPTION) {
				try{
					String[] record;
					for (String line : Files.readAllLines(Paths.get(selectfile.getSelectedFile().getPath()))){
						record = line.split(","); //Split the line based on commas
						if (record[0].isEmpty() == false){
							allSpectraFileList.add(new SpectraFileList(record[0],Integer.parseInt(record[1]), Integer.parseInt(record[2]))); //create a spectra object and add to allSpectra list
						}
					}
					textBox2.setText(selectfile.getSelectedFile().getPath());
					
				}
				catch (IOException ex) {
					if (ex.getMessage() != null) System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		//Creates a file which will be used to save averaged spectra
		if(e.getSource() == button3){
			JFileChooser selectfile = new JFileChooser(dir); //create a file chooser window
			int result = selectfile.showSaveDialog(frame); //and an integer representing the button clicked
			if(result == JFileChooser.APPROVE_OPTION) {
				try{
					if (selectfile.getSelectedFile().getPath().contains(".csv")){ //If the file name already has .csv don't add it

						fout = new FileOutputStream(selectfile.getSelectedFile());
						textBox3.setText(selectfile.getSelectedFile().getPath());
					}
					else{ //If the file path is missing .csv add it
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
		//Closes program
		if(e.getSource() == buttonCancel){
			System.exit(0);	
		}
		//Moves on to the Visualizing GUI
		if(e.getSource() == buttonNext){
			if(allSpectra.isEmpty() || allSpectraFileList.isEmpty() || fout == null){
				JOptionPane.showMessageDialog(frame,"Please fill in all 3 inputs.", "Input error",JOptionPane.ERROR_MESSAGE);
			}
			else {
				try {
					GUI_Avg_Spectra gui = new GUI_Avg_Spectra(1);
					gui.setVar(allSpectra, allSpectraFileList, fout);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(false);
			}
		}
	}
}
