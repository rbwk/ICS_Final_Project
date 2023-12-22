import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class mainMenu {

	static JFrame f = new JFrame("Exciting Guess Who Game!!!"); //JFrame for the whole menu
	static JButton exitButton = new JButton(); //Button for exiting the program
	
	

	
	
	public static void main(String[] args) throws InterruptedException {
		setFrame();	
	}
	
	public static void setFrame() {	
		f.setSize(1000,600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		exitButton = new JButton("Exit");
		exitButton.setBounds(0, 330, 100, 30);
		exitButton.addActionListener(new buttonClick());
		f.add(exitButton);
		

		
		f.setLayout(new SpringLayout());
		f.setVisible(true);
	}
	
	
	public static void componentResized() { //Make Better lock ratio method
	    int W = 5;  
	    int H = 3;  
	    Rectangle b = f.getBounds();
	    f.setBounds(b.x, b.y, b.width, b.width*H/W);

	}
	
	static class buttonClick implements ActionListener{
		public void actionPerformed(ActionEvent e){
			f.dispose();
		}	}
}
