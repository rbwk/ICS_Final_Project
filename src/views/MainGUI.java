package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JPanel mainPanel = new JPanel();
	private static JPanel selectorPanel = new JPanel();
	private static JPanel leaderboardPanel = new JPanel();
	private static JPanel exitPanel = new JPanel();
	private static JPanel creditsPanel = new JPanel();
	private static JPanel difficultyPanel = new JPanel();

	private static JPanel charactersPanel = new JPanel();
	private static JPanel questionPanel = new JPanel();
	private static JPanel scorePanel = new JPanel();
	private static JPanel gamePanel = new JPanel();
	private static JButton[][] characterButtons = new JButton[4][6];
	private static JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public MainGUI() throws IOException {

		initComponents();
		createEvents();

	}

	private void initComponents() throws IOException {

		setBackground(Color.WHITE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);

		setIconImage(Toolkit.getDefaultToolkit().getImage(MainGUI.class.getResource("/resources/icon_logo.png"))); // Create
																													// the
																													// icon
																													// image
		setTitle("Exciting Guess Who Game !!!");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(Color.DARK_GRAY);
		layeredPane.setBounds(0, 0, 1920, 1080);
		contentPane.add(layeredPane);
		selectorPanel.setVisible(false);

		/////////////////////////////////// Exit
		/////////////////////////////////// Panel///////////////////////////////////

		exitPanel.setBackground(Color.GRAY);
		exitPanel.setBounds(485, 250, 400, 300);
		layeredPane.add(exitPanel);
		exitPanel.setLayout(null);
		exitPanel.setVisible(false);

		JButton btnBackMenu = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/back_btn.png")));
		btnBackMenu.addActionListener(new backMenuButton());
		btnBackMenu.setContentAreaFilled(false);
		btnBackMenu.setBounds(330, 0, 75, 75);
		exitPanel.add(btnBackMenu);

		JButton btnExit = new JButton("EXIT TO DESKTOP");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBorderPainted(false);
		btnExit.setBackground(new Color(250, 128, 114));
		btnExit.setFont(new Font("STXihei", Font.PLAIN, 31));
		btnExit.setBounds(50, 210, 300, 50);
		exitPanel.add(btnExit);

		JButton btnLeaderboard = new JButton("LEADERBOARD");
		btnLeaderboard.setBackground(Color.LIGHT_GRAY);
		btnLeaderboard.setBorderPainted(false);
		btnLeaderboard.setBounds(50, 60, 300, 50);
		btnLeaderboard.setFont(new Font("STXihei", Font.PLAIN, 31));
		btnLeaderboard.addActionListener(new leaderboardButton());
		exitPanel.add(btnLeaderboard);

		JButton btnCredits = new JButton("CREDITS");
		btnCredits.setBackground(Color.LIGHT_GRAY);
		btnCredits.setBorderPainted(false);
		btnCredits.setBounds(50, 135, 300, 50);
		btnCredits.setFont(new Font("STXihei", Font.PLAIN, 31));
		btnCredits.addActionListener(new creditsButton());
		exitPanel.add(btnCredits);

		/////////////////////////////////// Main
		/////////////////////////////////// Screen///////////////////////////////////

		mainPanel.setBounds(0, 0, 1920, 1080);
		layeredPane.add(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.setVisible(true);

		JLabel lbLogo = new JLabel(new ImageIcon(MainGUI.class.getResource("/resources/logo.png")));
		lbLogo.setBounds(275, 230, 850, 278);
		mainPanel.add(lbLogo);

		JButton btnStart = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/start_btn.png")));
		btnStart.setContentAreaFilled(false);
		btnStart.setBounds(410, 550, 550, 250);
		btnStart.addActionListener(new startButton());
		mainPanel.add(btnStart);

		JButton btnMenu = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/menu_btn.png")));
		btnMenu.addActionListener(new menuButton());
		btnMenu.setContentAreaFilled(false);
		btnMenu.setBounds(1250, 20, 100, 100);
		mainPanel.add(btnMenu);

		/////////////////////////////////// Selector
		/////////////////////////////////// Screen ///////////////////////////////////

		selectorPanel.setBounds(0, 0, 1920, 1080);
		layeredPane.add(selectorPanel);
		selectorPanel.setLayout(null);
		selectorPanel.setVisible(false);

		JButton btnBackSelector = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/back_btn.png")));
		btnBackSelector.setContentAreaFilled(false);
		btnBackSelector.setBounds(1250, 20, 100, 100);
		btnBackSelector.addActionListener(new backSelectorButton());
		selectorPanel.add(btnBackSelector);

		JButton btnPVP = new JButton("    Local Player Versus Player");
		btnPVP.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		btnPVP.addActionListener(new PVPButton());
		btnPVP.setHorizontalAlignment(SwingConstants.LEFT);
		btnPVP.setIcon(new ImageIcon(MainGUI.class.getResource("/resources/pvp_btn.png")));
		btnPVP.setBounds(300, 250, 600, 150);
		btnPVP.setContentAreaFilled(false);
		selectorPanel.add(btnPVP);

		JButton btnPVC = new JButton("    Local Player Versus Computer");
		btnPVC.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		btnPVC.addActionListener(new PVCButton());
		btnPVC.setHorizontalAlignment(SwingConstants.LEFT);
		btnPVC.setIcon(new ImageIcon(MainGUI.class.getResource("/resources/pvc_btn.png")));
		btnPVC.setBounds(300, 500, 600, 150);
		btnPVC.setContentAreaFilled(false);
		selectorPanel.add(btnPVC);

		/////////////////////////////////// Leaderboard
		/////////////////////////////////// Screen///////////////////////////////////

		leaderboardPanel.setBounds(0, 0, 1920, 1080);
		layeredPane.add(leaderboardPanel);
		leaderboardPanel.setLayout(null);
		leaderboardPanel.setVisible(false);

		JButton btnBackLeaderboard = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/back_btn.png")));
		btnBackLeaderboard.setContentAreaFilled(false);
		btnBackLeaderboard.setBounds(1250, 20, 100, 100);
		btnBackLeaderboard.addActionListener(new backLeaderboardButton());
		leaderboardPanel.add(btnBackLeaderboard);

		/////////////////////////////////// Credits
		/////////////////////////////////// Screen///////////////////////////////////

		creditsPanel.setBounds(0, 0, 1920, 1080);
		layeredPane.add(creditsPanel);
		creditsPanel.setLayout(null);
		creditsPanel.setVisible(false);

		JButton btnBackCredits = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/back_btn.png")));
		btnBackCredits.setContentAreaFilled(false);
		btnBackCredits.setBounds(1250, 20, 100, 100);
		btnBackCredits.addActionListener(new backLeaderboardButton());
		creditsPanel.add(btnBackCredits);

		/////////////////////////////////// Difficulty
		/////////////////////////////////// Screen///////////////////////////////////

		difficultyPanel.setBounds(0, 0, 1920, 1080);
		layeredPane.add(difficultyPanel);
		difficultyPanel.setLayout(null);
		difficultyPanel.setVisible(false);

		JButton btnEasy = new JButton("Easy");
		btnEasy.setBounds(300, 250, 600, 150);
		btnEasy.setContentAreaFilled(false);
		btnEasy.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		btnEasy.addActionListener(new EasyButton());
		difficultyPanel.add(btnEasy);

		JButton btnMed = new JButton("Medium");
		btnMed.setBounds(300, 500, 600, 150);
		btnMed.setContentAreaFilled(false);
		btnMed.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		btnMed.addActionListener(new MedButton());
		difficultyPanel.add(btnMed);

		JButton btnHard = new JButton("Hard");
		btnHard.setBounds(300, 750, 600, 160);
		btnHard.setContentAreaFilled(false);
		btnHard.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		btnHard.addActionListener(new HardButton());
		difficultyPanel.add(btnHard);

		JButton btnBackDifficulty = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/back_btn.png")));
		btnBackDifficulty.setContentAreaFilled(false);
		btnBackDifficulty.setBounds(1250, 20, 100, 100);
		btnBackDifficulty.addActionListener(new BackDifficultyButton());
		difficultyPanel.add(btnBackDifficulty);

		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { exitPanel, contentPane, layeredPane,
				btnStart, lbLogo, mainPanel, selectorPanel, btnBackSelector, btnPVP, leaderboardPanel, btnExit,
				btnLeaderboard, btnCredits, creditsPanel, difficultyPanel, btnEasy, btnMed, btnHard,
				btnBackDifficulty }));

		/////////////////////////////////// Game
		/////////////////////////////////// Screen///////////////////////////////////

		gamePanel.setBounds(0, 0, 1920, 1080);
		layeredPane.add(gamePanel);
		gamePanel.setLayout(null);
		gamePanel.setVisible(false);

		charactersPanel.setBounds(0, 0, 600, 400);
		layeredPane.add(charactersPanel);
		charactersPanel.setLayout(null);
		charactersPanel.setVisible(false);

		// for (int i = 0; i < 4; i++) {
		// for (int j = 0; j < 6; j++) {
		// charactersPanel.add(characterButtons[i][j]);
		// characterButtons[i][j].setBackground(Color.decode("#FFFFFF"));

		// }
		// } idk why this doesn't work

		scorePanel.setBounds(600, 0, 400, 800);
		layeredPane.add(scorePanel);
		scorePanel.setLayout(null);
		scorePanel.setVisible(false);

		questionPanel.setBounds(0, 400, 1000, 400);
		layeredPane.add(questionPanel);
		questionPanel.setLayout(null);
		questionPanel.setVisible(false);

		gamePanel.add(charactersPanel);
		gamePanel.add(scorePanel);
		gamePanel.add(questionPanel);
	}

	private void createEvents() {

	}

	static class startButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mainPanel.setVisible(false);
			difficultyPanel.setVisible(true);
		}
	}

	static class leaderboardButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mainPanel.setVisible(false);
			leaderboardPanel.setVisible(true);
			exitPanel.setVisible(false);
		}
	}

	static class creditsButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mainPanel.setVisible(false);
			creditsPanel.setVisible(true);
			exitPanel.setVisible(false);
		}
	}

	static class backSelectorButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			difficultyPanel.setVisible(true);
			selectorPanel.setVisible(false);
		}
	}

	static class backLeaderboardButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mainPanel.setVisible(true);
			leaderboardPanel.setVisible(false);
		}
	}

	static class backCreditsButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mainPanel.setVisible(true);
			creditsPanel.setVisible(false);
		}
	}

	static class BackDifficultyButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mainPanel.setVisible(true);
			difficultyPanel.setVisible(false);
		}
	}

	static class backMenuButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			exitPanel.setVisible(false);
		}
	}

	static class menuButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			exitPanel.setVisible(true);
		}
	}

	static class EasyButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			difficultyPanel.setVisible(false);
			selectorPanel.setVisible(true);
		}
	}

	static class MedButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			difficultyPanel.setVisible(false);
			selectorPanel.setVisible(true);
		}
	}

	static class HardButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			difficultyPanel.setVisible(false);
			selectorPanel.setVisible(true);
		}
	}

	static class PVPButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			selectorPanel.setVisible(false);
			gamePanel.setVisible(true);
		}
	}

	static class PVCButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			selectorPanel.setVisible(false);
			gamePanel.setVisible(true);
		}
	}
}
