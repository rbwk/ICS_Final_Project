package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import common.TextFileReader;

public class mainMenu{

	TextFileReader reader = new TextFileReader("questions.txt");
	
	static JLayeredPane mainlayer = new JLayeredPane();
	static JPanel mainPanel = new JPanel(new GridBagLayout());
	static JPanel leaderboardPanel = new JPanel(new GridBagLayout());
	static GridBagConstraints constraints = new GridBagConstraints();

	static JFrame f = new JFrame("Exciting Guess Who Game!!!"); // JFrame for the whole menu
	

	public static void main(String[] args) throws InterruptedException {
		setFrame();
	}

	public static void setFrame() {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setMinimumSize(new Dimension(1000, 800));
		f.setSize(1000, 600);
		
		f.setLayout(new GridLayout(1, 1));

		//f.add(mainlayer);
		mainScreen(); //Adding the main Menu
		//f.setLayeredPane(mainlayer);
		//leaderboardScreen();

		f.setVisible(true);
	}

	public static void mainScreen() {

		JButton startButton = new JButton(); // Button for starting the game
		JButton leaderboardButton = new JButton(); // Button for leaderboard
		JButton creditsButton = new JButton(); // Button for credits
		JButton exitButton = new JButton(); // Button for exiting the program

		startButton = new JButton("Start Game!");
		startButton.addActionListener(new startButton());
		//startButton.setMaximumSize(new Dimension(20, 20));
		setConstrants(1, 10);
		mainPanel.add(startButton, constraints);
		

		leaderboardButton = new JButton("Leaderboard");
		leaderboardButton.addActionListener(new leaderboardButton());
		setConstrants(1, 25);
		mainPanel.add(leaderboardButton, constraints);

		creditsButton = new JButton("Credits");
		creditsButton.addActionListener(new creditsButton());
		setConstrants(1, 40);
		mainPanel.add(creditsButton, constraints);

		exitButton = new JButton("Exit");
		exitButton.addActionListener(new exitButton());
		setConstrants(1, 55);
		mainPanel.add(exitButton, constraints);

		ImageIcon logo = new ImageIcon("logo.png");
		setConstrants(1, 0);
		mainPanel.add(new JLabel(logo), constraints);

		f.add(mainPanel);
		//mainlayer.setPosition(mainPanel, 0);

	}

	public static void leaderboardScreen() {
		
		JButton backButton = new JButton();

		backButton = new JButton("Back");
		backButton.addActionListener(new backButton());
		setConstrants(1, 25);
		leaderboardPanel.add(backButton, constraints);
		
		//mainlayer.add(leaderboardPanel);
		//leaderboardPanel.setVisible(false);
	}

	public static void componentResized() { // Make Better lock ratio method
		int W = 5;
		int H = 3;
		Rectangle b = f.getBounds();
		f.setBounds(b.x, b.y, b.width, b.width * H / W);

	}

	static void setConstrants(int x, int y) {
		constraints.gridx = x;
		constraints.gridy = y;
	}

	static class startButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Start Game");
		}
	}

	static class leaderboardButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//mainPanel.setVisible(false);
			//leaderboardPanel.setVisible(true);
			mainlayer.moveToFront(leaderboardPanel);;

		}
	}

	static class creditsButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Credits");
		}
	}

	static class exitButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			f.dispose();
		}
	}

	static class backButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			mainPanel.setVisible(true);
			leaderboardPanel.setVisible(false);
		}
	}

}
