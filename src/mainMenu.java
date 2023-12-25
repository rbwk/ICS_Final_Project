import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class mainMenu {

	static JPanel buttonPanel = new JPanel(new GridBagLayout());
	static GridBagConstraints constraints = new GridBagConstraints();
	static JPanel blankPanel = new JPanel();

	static JFrame f = new JFrame("Exciting Guess Who Game!!!"); // JFrame for the whole menu
	static JButton startButton = new JButton(); // Button for starting the game
	static JButton leaderboardButton = new JButton(); // Button for leaderboard
	static JButton creditsButton = new JButton(); // Button for credits
	static JButton exitButton = new JButton(); // Button for exiting the program

	public static void main(String[] args) throws InterruptedException {
		setFrame();
	}

	public static void setFrame() {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//f.setLayout(new GridLayout(3, 3));

		startButton = new JButton("Start Game!");
		startButton.addActionListener(new startButton());
		startButton.setMaximumSize(new Dimension(20, 20));
		setConstrants(1, 10);
		buttonPanel.add(startButton, constraints);

		leaderboardButton = new JButton("Leaderboard");
		leaderboardButton.addActionListener(new leaderboardButton());
		setConstrants(1, 25);
		buttonPanel.add(leaderboardButton, constraints);

		creditsButton = new JButton("Credits");
		creditsButton.addActionListener(new creditsButton());
		setConstrants(1, 40);
		buttonPanel.add(creditsButton, constraints);

		exitButton = new JButton("Exit");
		exitButton.addActionListener(new exitButton());
		setConstrants(1, 55);
		buttonPanel.add(exitButton, constraints);

		//f.add(blankPanel);
		f.add(buttonPanel);

		f.setMinimumSize(new Dimension(1000, 800));
		f.setSize(1000, 600);
		f.setVisible(true);
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
			System.out.println("Leaderboard");
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

}
