package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import common.Initialization;
import common.TextFileReader;
import common.game;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JPanel mainPanel = new JPanel();
	private static JPanel selectorPanel = new JPanel();
	private static JPanel leaderboardPanel = new JPanel();
	private static JPanel exitPanel = new JPanel();
	private static JPanel creditsPanel = new JPanel();
	private static JPanel difficultyPanel = new JPanel();
	private static JPanel gameExitPanel = new JPanel();
	private static JPanel playerselectionPanel = new JPanel();
	private static JPanel namePanel1 = new JPanel();
	private static JPanel namePanel2 = new JPanel();
	private static JPanel player1_nextPanel = new JPanel(); //Panel that shows during player 2 to 1 commision
	private static JPanel player2_nextPanel = new JPanel(); //Panel that shows during player 1 to 2 commision

	private static boolean pvpMode = false; //Recognizing if player selected PVP or PVE Mode
	private static String p1_character = null; //Setting the character that Player 1 chose
	private static String p2_character = null; //Setting the character that Player 2 chose
	private static boolean p1_ongoing = true; //Variable the set if the player 1 is controlling or player 2
	private static boolean p2_select = false; //Recognizing if player 2 need to set character that is being guessed by player 1
	private static String p1_questionAsked = null;
	private static String p2_questionAsked = null;
	private static boolean result;

	private static JPanel charactersPanel = new JPanel();
	private static JPanel questionPanel = new JPanel();
	private static JPanel scorePanel = new JPanel();
	private static JPanel gamePanel = new JPanel();
	private static JPanel enemyPanel = new JPanel();
	private static JPanel opAskPanel = new JPanel(); //Panel where the opposition askes u the question and u have to answer it 
	private static JLabel opQuestion = new JLabel();
	private static JLabel questioning = new JLabel("Are you sure about that?");
	private static JButton[] selectionCharacterButtons = new JButton[24];
	private static JButton[] characterButtons = new JButton[24]; // Use 1D Array as the data is 1d and easier to switch
																	// between
	private static JComboBox<String> questionList = new JComboBox<>();
	private static String fileName;
	private static JLabel player_character = new JLabel();

	private static TextFileReader characterReader = new TextFileReader("src\\resources\\data.txt"); // Testing Purposes
																									// only
	// private static TextFileReader questionReader = new
	// TextFileReader("src\\resources\\questions.txt"); //Testing Purposes Only
	private static TextFileReader p1_questions = new TextFileReader("src\\resources\\p1_questions_remaining.txt");
	private static ArrayList<String> Characters = new ArrayList<String>(); // Testing Purposes only
	private static ArrayList<String> Questions = new ArrayList<String>(); // Testing Purposes only
	private static String[] data; // Testing Purposes only

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

		characterReader.readFile(); // Testing Purposes only
		p1_questions.readFile(); // Testing Purposes only

		Characters = characterReader.getname(); // Testing Purposes only
		Questions = p1_questions.getQuestions();

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

		/////////////////////////////////// Get Name
		/////////////////////////////////// Screen///////////////////////////////////
		namePanel1.setBounds(0, 0, 1920, 1080);
		layeredPane.add(namePanel1);
		namePanel1.setLayout(null);
		namePanel1.setVisible(false);

		JLabel enterNameLabel1 = new JLabel("Enter Player 1's Name");
		enterNameLabel1.setBounds(760, 250, 400, 100);
		enterNameLabel1.setFont(new Font("Trebuchet MS", Font.PLAIN, 31));
		namePanel1.add(enterNameLabel1);

		JTextField nameTextField1 = new JTextField(20);
		nameTextField1.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		nameTextField1.setBounds(760, 400, 400, 50);
		namePanel1.add(nameTextField1);

		JButton enterButton1 = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/enter_btn.png")));
		enterButton1.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		enterButton1.setContentAreaFilled(true);
		enterButton1.setBounds(810, 600, 300, 100);
		enterButton1.addActionListener(new enterButton1());
		namePanel1.add(enterButton1);

		namePanel2.setBounds(0, 0, 1920, 1080);
		layeredPane.add(namePanel2);
		namePanel2.setLayout(null);
		namePanel2.setVisible(false);

		JLabel enterNameLabel2 = new JLabel("Enter Player 2's Name");
		enterNameLabel2.setBounds(760, 250, 400, 100);
		enterNameLabel2.setFont(new Font("Trebuchet MS", Font.PLAIN, 31));
		namePanel2.add(enterNameLabel2);

		JTextField nameTextField2 = new JTextField(20);
		nameTextField2.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		nameTextField2.setBounds(760, 400, 400, 50);
		namePanel2.add(nameTextField2);

		JButton enterButton2 = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/enter_btn.png")));
		enterButton2.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		enterButton2.setContentAreaFilled(true);
		enterButton2.setBounds(810, 600, 300, 100);
		enterButton2.addActionListener(new enterButton2());
		namePanel2.add(enterButton2);

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

		JButton btnEasy = new JButton("    Easy");
		btnEasy.setBounds(400, 150, 500, 150);
		btnEasy.setContentAreaFilled(false);
		btnEasy.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		btnEasy.addActionListener(new EasyButton());
		btnEasy.setHorizontalAlignment(SwingConstants.LEFT);
		btnEasy.setIcon(new ImageIcon(MainGUI.class.getResource("/resources/easy_btn.png")));
		difficultyPanel.add(btnEasy);

		JButton btnMid = new JButton("    Medium");
		btnMid.setBounds(400, 400, 500, 150);
		btnMid.setContentAreaFilled(false);
		btnMid.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		btnMid.addActionListener(new MedButton());
		btnMid.setHorizontalAlignment(SwingConstants.LEFT);
		btnMid.setIcon(new ImageIcon(MainGUI.class.getResource("/resources/mid_btn.png")));
		difficultyPanel.add(btnMid);

		JButton btnHard = new JButton("    Hard");
		btnHard.setBounds(400, 650, 500, 160);
		btnHard.setContentAreaFilled(false);
		btnHard.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		btnHard.addActionListener(new HardButton());
		btnHard.setHorizontalAlignment(SwingConstants.LEFT);
		btnHard.setIcon(new ImageIcon(MainGUI.class.getResource("/resources/hard_btn.png")));
		difficultyPanel.add(btnHard);

		JButton btnBackDifficulty = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/back_btn.png")));
		btnBackDifficulty.setContentAreaFilled(false);
		btnBackDifficulty.setBounds(1250, 20, 100, 100);
		btnBackDifficulty.addActionListener(new BackDifficultyButton());
		difficultyPanel.add(btnBackDifficulty);

		/////////////////////////////////// Player 1 Select Next ///////////////////////////////////

		player1_nextPanel.setBounds( 0, 0, 1920, 1080);
		layeredPane.add(player1_nextPanel);
		player1_nextPanel.setLayout(null);
		player1_nextPanel.setVisible(false);

		JLabel wait_for_player_1 = new JLabel("Player 1, Please press Confirm to go to the next Screen");
		player1_nextPanel.add(wait_for_player_1);
		wait_for_player_1.setBounds( 300, 200, 800, 400);
		wait_for_player_1.setFont(new Font("STXihei", Font.PLAIN, 30));

		JButton p1_Confirm = new JButton("CONFIRM");
		p1_Confirm.setBounds( 400, 500, 600, 200);
		p1_Confirm.setContentAreaFilled(false);
		p1_Confirm.addActionListener(new player1_intermission());
		p1_Confirm.setFont(new Font("STXihei", Font.PLAIN, 30));
		player1_nextPanel.add(p1_Confirm);

		/////////////////////////////////// Player 2 Select Next ///////////////////////////////////

		player2_nextPanel.setBounds( 0, 0, 1920, 1080);
		layeredPane.add(player2_nextPanel);
		player2_nextPanel.setLayout(null);
		player2_nextPanel.setVisible(false);

		JLabel wait_for_player_2 = new JLabel("Player 2, Please press Confirm to go to the next Screen");
		player2_nextPanel.add(wait_for_player_2);
		wait_for_player_2.setBounds(300,200,800,400);
		wait_for_player_2.setFont(new Font("STXihei", Font.PLAIN, 30));

		JButton p2_Confirm = new JButton("CONFIRM");
		p2_Confirm.setBounds(400,500,600,200);
		p2_Confirm.setContentAreaFilled(false);
		p2_Confirm.addActionListener(new player2_intermission());
		p2_Confirm.setFont(new Font("STXihei", Font.PLAIN, 30));
		player2_nextPanel.add(p2_Confirm);


		/////////////////////////////////// Game Exit Panel
		/////////////////////////////////// ///////////////////////////////////

		gameExitPanel.setBackground(Color.GRAY);
		gameExitPanel.setBounds(500, 400, 500, 200);
		layeredPane.add(gameExitPanel);
		gameExitPanel.setLayout(null);
		gameExitPanel.setVisible(false);

		JLabel persuasionText = new JLabel("  Are you sure leaving mid game???");
		persuasionText.setBounds(0, 0, 500, 100);
		persuasionText.setFont(new Font("Trebuchet MS", Font.PLAIN, 31));
		gameExitPanel.add(persuasionText);

		JButton btnBackGameMenu = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/exit_btn.png")));
		btnBackGameMenu.addActionListener(new backGameMenuButton());
		btnBackGameMenu.setContentAreaFilled(false);
		btnBackGameMenu.setBounds(300, 90, 100, 100);
		gameExitPanel.add(btnBackGameMenu);

		JButton btnLeaveGame = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/accept_btn.png")));
		btnLeaveGame.setBackground(new Color(250, 128, 114));
		btnLeaveGame.addActionListener(new backGameButton());
		btnLeaveGame.setBounds(100, 90, 100, 100);
		btnLeaveGame.setBorderPainted(false);
		gameExitPanel.add(btnLeaveGame);

		/////////////////////////////////// Question From Other Player ///////////////////////////////////

		opAskPanel.setBackground(Color.LIGHT_GRAY);
		opAskPanel.setBounds(320, 200, 720, 400);;
		layeredPane.add(opAskPanel);
		opAskPanel.setLayout(null);
		opAskPanel.setVisible(false);

		opQuestion.setBounds(100, 100, 500, 100);
		opAskPanel.add(opQuestion);
		opQuestion.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));

		JButton yesButton = new JButton("Yep");
		yesButton.setContentAreaFilled(false);
		yesButton.setBounds( 100, 200, 100, 100);
		yesButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 21));
		yesButton.addActionListener(new opYesButton());
		opAskPanel.add(yesButton);

		JButton noButton = new JButton("Nope");
		noButton.setContentAreaFilled(false);
		noButton.setBounds( 400, 200, 100, 100);
		noButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 21));
		noButton.addActionListener(new opNoButton());
		opAskPanel.add(noButton);

		questioning.setBounds( 100, 300, 500, 100);
		opAskPanel.add(questioning);
		questioning.setFont(new Font("Trebuchet MS", Font.PLAIN, 21));
		questioning.setVisible(false);


		/////////////////////////////////// Game
		/////////////////////////////////// Screen///////////////////////////////////

		gamePanel.setBounds(0, 0, 1920, 1080);
		layeredPane.add(gamePanel);
		gamePanel.setLayout(null);
		gamePanel.setVisible(false);

		JButton btnBackGame = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/back_btn.png")));
		btnBackGame.setContentAreaFilled(false);
		btnBackGame.setBounds(1320, 20, 100, 100);
		btnBackGame.addActionListener(new menuGameButton());
		gamePanel.add(btnBackGame);

		charactersPanel.setBounds(0, 0, 1000, 800);
		charactersPanel.setLayout(null);
		charactersPanel.setVisible(true);
		gamePanel.add(charactersPanel);

		/////////////////////////////////// Adding Characters
		/////////////////////////////////// ///////////////////////////////////
		int num = 0;
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 6; y++) {
				String name = Characters.get(num);
				String namepicture = "/resources/characters/" + name + ".png";
				characterButtons[num] = new JButton();
				characterButtons[num].setIcon(new ImageIcon(MainGUI.class.getResource(namepicture)));
				characterButtons[num].setBorderPainted(false);
				int ylocation = 15 + (x) * 200;
				int xlocation = 15 + (y) * 170;
				characterButtons[num].setBounds(xlocation, ylocation, 120, 170);
				characterButtons[num].setHorizontalAlignment(SwingConstants.CENTER);
				characterButtons[num].addActionListener(new charactersButton());
				characterButtons[num].setHideActionText(true);
				charactersPanel.add(characterButtons[num]);
				num++;
			}
		}

		/////////////////////////////////// Score Screen
		/////////////////////////////////// ///////////////////////////////////

		scorePanel.setBounds(1000, 0, 440, 900);
		scorePanel.setLayout(null);
		scorePanel.setVisible(true);
		gamePanel.add(scorePanel);

		JLabel titleScore = new JLabel("SCORE");
		titleScore.setFont(new Font("STXihei", Font.PLAIN, 52));
		titleScore.setBounds(130, 50, 200, 50);
		scorePanel.add(titleScore);

		JLabel actualScore = new JLabel("12345");
		actualScore.setFont(new Font("STXihei", Font.PLAIN, 52));
		actualScore.setBounds(90, 120, 250, 70);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		actualScore.setBorder(blackline);
		actualScore.setHorizontalAlignment(SwingConstants.CENTER);
		scorePanel.add(actualScore);

		player_character.setHorizontalAlignment(SwingConstants.CENTER);
		player_character.setBounds(65, 220, 300, 400);
		player_character.setBorder(blackline);
		scorePanel.add(player_character);

		enemyPanel.setBackground(Color.RED);
		enemyPanel.setBounds(45, 650, 340, 220);
		scorePanel.add(enemyPanel);

		/////////////////////////////////// Question Screen
		/////////////////////////////////// ///////////////////////////////////

		questionPanel.setBounds(0, 800, 1000, 100);
		questionPanel.setLayout(null);
		questionPanel.setVisible(true);
		gamePanel.add(questionPanel);

		questionList.setBounds(10, 11, 820, 80);
		questionList.setFont(new Font("STXihei", Font.PLAIN, 32));
		questionList.getSelectedItem();
		questionList.removeAllItems();
		questionPanel.add(questionList);

		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(840, 11, 150, 80);
		btnConfirm.setFont(new Font("STXihei", Font.PLAIN, 30));
		btnConfirm.addActionListener(new confirmButton());
		questionPanel.add(btnConfirm);

		/////////////////////////////////// Character Selection Screen [PVC]
		/////////////////////////////////// ///////////////////////////////////

		playerselectionPanel.setBounds(0, 0, 1980, 1080);
		layeredPane.add(playerselectionPanel);
		playerselectionPanel.setLayout(null);
		playerselectionPanel.setVisible(false);

		JButton btnCharacterConfirm = new JButton("Confirm Character");
		btnCharacterConfirm.setFont(new Font("STXihei", Font.PLAIN, 30));
		btnCharacterConfirm.setBounds(500, 800, 400, 100);
		btnCharacterConfirm.addActionListener(new characterConfirmButton());
		playerselectionPanel.add(btnCharacterConfirm);

		/////////////////////////////////// Adding Characters
		/////////////////////////////////// ///////////////////////////////////
		int num1 = 0;
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 6; y++) {
				String name = Characters.get(num1);
				String namepicture = "/resources/characters/" + name + ".png";
				selectionCharacterButtons[num1] = new JButton();
				selectionCharacterButtons[num1].setIcon(new ImageIcon(MainGUI.class.getResource(namepicture)));
				int ylocation = 15 + (x) * 200;
				int xlocation = 140 + (y) * 200;
				selectionCharacterButtons[num1].setBounds(xlocation, ylocation, 120, 170);
				selectionCharacterButtons[num1].setHorizontalAlignment(SwingConstants.CENTER);
				selectionCharacterButtons[num1].addActionListener(new charactersButton());
				selectionCharacterButtons[num1].setHideActionText(true);
				playerselectionPanel.add(selectionCharacterButtons[num1]);
				num1++;
			}
		}


		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { exitPanel, contentPane, layeredPane,
				btnStart, lbLogo, mainPanel, selectorPanel, btnBackSelector, btnPVP, leaderboardPanel, btnExit,
				btnLeaderboard, btnCredits, creditsPanel, difficultyPanel, btnEasy, btnMid, btnHard, btnBackDifficulty,
				questionList, playerselectionPanel }));
	}

	private void createEvents() {

	}

	static class startButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mainPanel.setVisible(false);
			difficultyPanel.setVisible(true);
			exitPanel.setVisible(false);
			game.restartGame();
			game.initializeGame();
			p1_character = null;
			p2_character = null;
			p1_ongoing = true;
			p2_select = false;
			p1_questions.readFile();
			Questions = p1_questions.getQuestions(); // Testing Purposes only
			data = Questions.toArray(new String[Questions.size()]);
			for (int i = 0; i < data.length; i++) {
				questionList.addItem(data[i]);
			}
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
			playerselectionPanel.setVisible(true);
			characterButtons[15].setVisible(true);
			characterButtons[16].setVisible(true);
			characterButtons[17].setVisible(true);
			pvpMode = true;

		}
	}

	static class PVCButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			pvpMode = false;
			selectorPanel.setVisible(false);
			namePanel1.setVisible(true);
			// playerselectionPanel.setVisible(true);
			// // gamePanel.setVisible(true);
			// characterButtons[15].setVisible(true);
			// characterButtons[16].setVisible(true);
			// characterButtons[17].setVisible(true);
		}
	}

	static class menuGameButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			characterButtons[15].setVisible(false);
			characterButtons[16].setVisible(false);
			characterButtons[17].setVisible(false);
			gameExitPanel.setVisible(true);
		}
	}

	static class backGameMenuButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			characterButtons[15].setVisible(true);
			characterButtons[16].setVisible(true);
			characterButtons[17].setVisible(true);
			gameExitPanel.setVisible(false);
		}
	}

	static class backGameButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			gameExitPanel.setVisible(false);
			gamePanel.setVisible(false);
			mainPanel.setVisible(true);
			Initialization.resetGame();
		}
	}

	static class charactersButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton clickedButton = (JButton) e.getSource();
			String FilePath = clickedButton.getIcon().toString();
			File file = new File(FilePath);
			fileName = file.getName();
			int index = fileName.lastIndexOf(".png");
			fileName = fileName.substring(0, index); // The actual name of the character
		}
	}

	static class characterConfirmButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println(fileName);
			if(pvpMode){
				if(p1_ongoing){
					p1_character = fileName;
					p1_ongoing = false;
					player2_nextPanel.setVisible(true);
					playerselectionPanel.setVisible(false);
					p2_select = true;
					game.characterChoice(1, p1_character);
				}else{
					p2_character = fileName;
					p1_ongoing = true;
					gameSetCharacterScreen();
					playerselectionPanel.setVisible(false);
					player1_nextPanel .setVisible(true);
					game.characterChoice(2, p2_character);
				}
			}else{
				p1_character = fileName;
				gameSetCharacterScreen();
				playerselectionPanel.setVisible(false);
				gamePanel.setVisible(true);	
			}
			
			System.out.println("Player 1 - "+p1_character); //Testing
			System.out.println("Player 2 - "+p2_character); //Testing
		}
	}

	static class confirmButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {                                                                                                                                               
			String question = questionList.getSelectedItem().toString();
			questionList.removeAllItems(); 
			if(p1_ongoing){
				result = game.checkAnswer(question, 1);
				p1_questionAsked = question;
				TextFileReader check_questions = new TextFileReader("src\\resources\\p2_questions_remaining.txt"); 
				check_questions.readFile();
				Questions = check_questions.getQuestions();
				data = Questions.toArray(new String[Questions.size()]);
				for (int i = 0; i < data.length; i++) {questionList.addItem(data[i]);}
				player2_nextPanel.setVisible(true);
				gamePanel.setVisible(false);
				p1_ongoing = false;
				gameSetCharacterScreen();
				System.out.print("Player 1 - "); //Testing
			}else{
				result = game.checkAnswer(question, 2);
				p2_questionAsked = question;
				TextFileReader check_questions = new TextFileReader("src\\resources\\p1_questions_remaining.txt"); 
				check_questions.readFile();
				Questions = check_questions.getQuestions();
				data = Questions.toArray(new String[Questions.size()]);
				for (int i = 0; i < data.length; i++) {questionList.addItem(data[i]);}
				player1_nextPanel.setVisible(true);
				gamePanel.setVisible(false);
				p1_ongoing = true;
				gameSetCharacterScreen();
				System.out.print("Player 2 - "); //Testing
			}
			opQuestion.setText(question);
			System.out.println(questionList.getSelectedItem()); //Testing
			System.out.println(result); //Testing Purposes
			
		}
	}

	static class player1_intermission implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(p2_questionAsked != null){
				opAskPanel.setVisible(true);
				opAskingHide(false);
			}
			player1_nextPanel.setVisible(false);
			gamePanel.setVisible(true);
			String namepicture = "/resources/characters/" + fileName + ".png";
		}
	}

	static class player2_intermission implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(p2_select){
				playerselectionPanel.setVisible(true);
				player2_nextPanel.setVisible(false);
				p2_select = false;
			}else{
				gamePanel.setVisible(true);
				opAskPanel.setVisible(true);
				opAskingHide(false);
				player2_nextPanel.setVisible(false);
			}
		}
	}

	static class opYesButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(pvpMode){//PVP Mode Confirming yes
				if(result){
					opAskPanel.setVisible(false);
					opAskingHide(true);
					questioning.setVisible(false);
				}else{
					questioning.setVisible(true);
				}
			}else{//PVE Mode No Confirming 

			}
			
		}

	}

	static class opNoButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(pvpMode){//PVP Mode Confirming yes
				if(!result){
					opAskPanel.setVisible(false);
					opAskingHide(true);
					questioning.setVisible(false);
				}else{
					questioning.setVisible(true);
				}
			}else{//PVP Mode No Confirming

			}
			
		}
	}

	static void gameSetCharacterScreen(){
		String namepicture;
		if(p1_ongoing){
			namepicture = "/resources/characters/" + p1_character + ".png";
		}else{
			namepicture = "/resources/characters/" + p2_character + ".png";
		}
			// player_character.setIcon(new
			// ImageIcon(MainGUI.class.getResource(namepicture)));
			ImageIcon imageIcon = new ImageIcon(MainGUI.class.getResource(namepicture));
			Image image = imageIcon.getImage();
			Image newimg = image.getScaledInstance(283, 375, java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			player_character.setIcon(imageIcon);
	}

	static void opAskingHide(boolean nah){
		characterButtons[8].setVisible(nah);
		characterButtons[9].setVisible(nah);
		characterButtons[10].setVisible(nah);
		characterButtons[11].setVisible(nah);
		characterButtons[14].setVisible(nah);
		characterButtons[15].setVisible(nah);
		characterButtons[16].setVisible(nah);
		characterButtons[17].setVisible(nah);
	}

	static class enterButton1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			namePanel1.setVisible(false);
			namePanel2.setVisible(true); // if pvc, don't show second name panel screen
		}
	}

	static class enterButton2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			namePanel2.setVisible(false);
			playerselectionPanel.setVisible(true);
			// gamePanel.setVisible(true);
			characterButtons[15].setVisible(true);
			characterButtons[16].setVisible(true);
			characterButtons[17].setVisible(true);
		}
	}
}
