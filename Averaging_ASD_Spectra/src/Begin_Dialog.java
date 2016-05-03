import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Begin_Dialog implements ActionListener{
	private JFrame frame = new JFrame("Average Spectra");
	private Container pane = frame.getContentPane();
	private JButton buttonASD = new JButton("ASD (VSWIR)");
	private JButton buttonNicolet = new JButton("Nicolet (TIR)");
	
	public Begin_Dialog(){
		
		pane.setLayout(new GridBagLayout()); //Use GridBagLayout for GUI set up
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel textTitle = new JLabel("What type of spectra would you like to average?");
		buttonASD.addActionListener(this);
		buttonNicolet.addActionListener(this);
		
		c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10,5,5,5);  //top padding
    	c.gridwidth = 2;
    	c.gridheight = 1;
    	c.gridx = 0;
    	c.gridy = 0;
    	pane.add(textTitle,c); //Add Label for Displayed Samples
		c.gridwidth = 1;
    	c.gridx = 0;
    	c.gridy = 1;
    	pane.add(buttonASD,c); //Add Label for Displayed Samples
    	c.gridwidth = 1;
    	c.gridx = 1;
    	c.gridy = 1;
    	pane.add(buttonNicolet,c); //Add Label for Displayed Samples

		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == buttonASD){
			Input_ASD_Dialog input = new Input_ASD_Dialog();
			frame.setVisible(false);
		}
		if(e.getSource() == buttonNicolet){
			Input_Nicolet_Dialog input = new Input_Nicolet_Dialog();
			frame.setVisible(false);
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Begin_Dialog input = new Begin_Dialog();
		
	}
}
