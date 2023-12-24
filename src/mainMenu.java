import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class mainMenu {
	
	static JPanel buttonPanel = new JPanel(new FlowLayout(1,1000,15));
	static JPanel blankPanel = new JPanel();
	
	static JFrame f = new JFrame("Exciting Guess Who Game!!!"); //JFrame for the whole menu
	static JButton startButton = new JButton(); //Button for starting the game
	static JButton leaderboardButton = new JButton(); //Button for leaderboard
	static JButton creditsButton = new JButton(); //Button for credits
	static JButton exitButton = new JButton(); //Button for exiting the program

	
	
	public static void main(String[] args) throws InterruptedException {
		setFrame();	
	}
	
	public static void setFrame() {	
		f.setSize(1000,600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new GridLayout(3,3));
		
		startButton = new JButton("Start Game!");
		startButton.addActionListener(new startButton());
		buttonPanel.add(startButton);
		
		leaderboardButton = new JButton("Leaderboard");
		leaderboardButton.addActionListener(new leaderboardButton());
		buttonPanel.add(leaderboardButton);
		
		creditsButton = new JButton("Credits");
		creditsButton.addActionListener(new creditsButton());
		buttonPanel.add(creditsButton);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new exitButton());
		buttonPanel.add(exitButton);
		
		f.add(blankPanel);
		f.add(buttonPanel);

		
		
		f.setVisible(true);
	}
	
	
	public static void componentResized() { //Make Better lock ratio method
	    int W = 5;  
	    int H = 3;  
	    Rectangle b = f.getBounds();
	    f.setBounds(b.x, b.y, b.width, b.width*H/W);

	}
	
	static class startButton implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("Start Game");
	}	}
	
	static class leaderboardButton implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("Leaderboard");
	}	}
	
	static class creditsButton implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("Credits");
	}	}
	
	static class exitButton implements ActionListener{
		public void actionPerformed(ActionEvent e){
			f.dispose();
	}	}
	

	
	
	
}
