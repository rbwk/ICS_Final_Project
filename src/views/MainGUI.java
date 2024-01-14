/**ICS4U Final Project: Guess Who?
 * Nathan Chu, Victoria Chi, Aryan Alipanahi
 * Jan 12, 2024
 * 
 * The MainGUI class serves as the primary interface for the Guess Who game.
 * It handles the creation and interaction of all GUI components, including
 * panels, buttons, labels, and game logic integration. This class orchestrates
 * the game flow and user interactions.
 * 
  */
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
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import AI.MainAI;
import common.Initialization;
import common.TextFileReader;
import common.game;
import common.GameCharacter;

public class MainGUI extends JFrame {

	// ublic static MainAI aiPlayer; // AI player instance

	static MainAI aiPlayer = new MainAI(1); // Defauly aiPlayer defaulty
	private static ArrayList<GameCharacter> characters;

	// initializing GUI elements

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
	private static JPanel namePanel = new JPanel();
	private static JPanel endGamePanel = new JPanel();
	private static JPanel instructionsPanel = new JPanel();
	private static JPanel player1_nextPanel = new JPanel(); // Panel that shows during player 2 to 1 commision
	private static JPanel player2_nextPanel = new JPanel(); // Panel that shows during player 1 to 2 commision

	private static boolean pvpMode = false; // Recognizing if player selected PVP or PVE Mode
	private static String p1_character = null; // Setting the character that Player 1 chose
	private static String p2_character = null; // Setting the character that Player 2 chose
	private static boolean p1_ongoing = true; // Variable the set if the player 1 is controlling or player 2
	private static boolean p2_select = false; // Recognizing if player 2 need to set character that is being guessed by
	private static boolean pvc_win = false; // true if character won player 1
	private static String question = null;
	private static String p1_questionAsked = null; // question player 1 asks player 2 in a turn
	private static String p2_questionAsked = null; // question player 2 asks player 1 in a turn
	private static boolean result;
	private static boolean guess1;
	@SuppressWarnings("unused")
	private static boolean guess2;
	private static String playerName;
	private static int amountCount = 0;
	private static String leader1 = "Name \t\t\t Average\n\n";
	private static String leader2 = "Name \t\t\t Won\n\n";
	private static JTextArea avgLeader = new JTextArea();
	private static JTextArea gamesWon = new JTextArea();

	// panels for game screen

	private static JPanel charactersPanel = new JPanel();
	private static JPanel questionPanel = new JPanel();
	private static JPanel scorePanel = new JPanel();
	private static JPanel gamePanel = new JPanel();
	private static JPanel enemyPanel = new JPanel();
	private static JPanel opAskPanel = new JPanel(); // Panel where the opposition askes u the question and u have to
	private static JLabel opQuestion = new JLabel();
	private static JLabel resultLabel = new JLabel();
	private static JLabel actualScore = new JLabel("12345");
	private static JLabel questioning = new JLabel("Are you sure about that?");
	private static JButton[] selectionCharacterButtons = new JButton[24];
	private static JButton[] characterButtons = new JButton[24]; // Use 1D Array as the data is 1d and easier to switch
	private static JComboBox<String> questionList = new JComboBox<>();
	private static String fileName;
	private static JLabel player_character = new JLabel();

	private static JLabel enterNameLabel = new JLabel();
	private static JTextField nameTextField = new JTextField(20);
	private static JLabel instructionsTitle = new JLabel("HOW TO PLAY");

	private static TextFileReader characterReader = new TextFileReader("src\\resources\\data.txt"); // Testing Purposes
	private static TextFileReader p1_questions = new TextFileReader("src\\resources\\p1_questions_remaining.txt");
	private static ArrayList<String> Characters = new ArrayList<String>(); // Testing Purposes only
	private static TextFileReader questions = new TextFileReader("src\\resources\\questions.txt");
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
	 */
	public MainGUI() throws IOException {

		characterReader.readFile(); // Testing Purposes only
		p1_questions.readFile(); // Testing Purposes only
		questions.readFile();

		Characters = characterReader.getname(); // Testing Purposes only
		Questions = questions.getQuestions();

		initComponents();

	}

	// setting up all GUI elements

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

		/////////////////////////////////// Leaderboard
		/////////////////////////////////// Screen///////////////////////////////////

		// shows stats on leaderboard
		leaderboardPanel.setBounds(0, 0, 1920, 1080);
		layeredPane.add(leaderboardPanel);
		leaderboardPanel.setLayout(null);
		leaderboardPanel.setVisible(false);

		// button that goes back to the exit menu
		JButton btnBackLeaderboard = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/back_btn.png")));
		btnBackLeaderboard.setContentAreaFilled(false);
		btnBackLeaderboard.setBounds(1250, 20, 100, 100);
		btnBackLeaderboard.addActionListener(new backLeaderboardButton());
		leaderboardPanel.add(btnBackLeaderboard);

		JLabel title1 = new JLabel("Average Questions Asked");
		title1.setBounds(130, 30, 400, 100);
		title1.setFont(new Font("STXihei", Font.PLAIN, 31));
		leaderboardPanel.add(title1);

		JLabel title2 = new JLabel("Games Won");
		title2.setBounds(835, 30, 400, 100);
		title2.setFont(new Font("STXihei", Font.PLAIN, 31));
		leaderboardPanel.add(title2);

		avgLeader.setBounds(10, 150, 600, 600);
		avgLeader.setBackground(Color.LIGHT_GRAY);
		avgLeader.setEditable(false);
		avgLeader.setFont(new Font("STXihei", Font.PLAIN, 20));
		leaderboardPanel.add(avgLeader);
		avgLeader.setText(leader1);
		avgLeaderUpdate();

		gamesWon.setBounds(620, 150, 600, 600);
		gamesWon.setBackground(Color.LIGHT_GRAY);
		gamesWon.setEditable(false);
		gamesWon.setFont(new Font("STXihei", Font.PLAIN, 20));
		leaderboardPanel.add(gamesWon);
		gamesWon.setText(leader2);
		gamesWonUpdate();

		/////////////////////////////////// Exit
		/////////////////////////////////// Panel///////////////////////////////////

		// panel that displays credit, leaderboard and exit to desktop buttons
		exitPanel.setBackground(Color.GRAY);
		exitPanel.setBounds(485, 250, 400, 300);
		layeredPane.add(exitPanel);
		exitPanel.setLayout(null);
		exitPanel.setVisible(false);

		// button on the exit menu that allows player to go back to start screen
		JButton btnBackMenu = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/back_btn.png")));
		btnBackMenu.addActionListener(new backMenuButton());
		btnBackMenu.setContentAreaFilled(false);
		btnBackMenu.setBounds(330, 0, 75, 75);
		exitPanel.add(btnBackMenu);

		// button that closes the game window
		JButton btnExit = new JButton("EXIT TO DESKTOP");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.restartGame();
				dispose();
			}
		});
		btnExit.setBorderPainted(false);
		btnExit.setBackground(new Color(250, 128, 114));
		btnExit.setFont(new Font("STXihei", Font.PLAIN, 31));
		btnExit.setBounds(50, 210, 300, 50);
		exitPanel.add(btnExit);

		// button that displays the leaderboard
		JButton btnLeaderboard = new JButton("LEADERBOARD");
		btnLeaderboard.setBackground(Color.LIGHT_GRAY);
		btnLeaderboard.setBorderPainted(false);
		btnLeaderboard.setBounds(50, 60, 300, 50);
		btnLeaderboard.setFont(new Font("STXihei", Font.PLAIN, 31));
		btnLeaderboard.addActionListener(new leaderboardButton());
		exitPanel.add(btnLeaderboard);

		// button that displays the credits
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

		// image of Guess Who Logo
		JLabel lbLogo = new JLabel(new ImageIcon(MainGUI.class.getResource("/resources/logo.png")));
		lbLogo.setBounds(275, 230, 850, 278);
		mainPanel.add(lbLogo);

		// button that starts the game
		JButton btnStart = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/start_btn.png")));
		btnStart.setContentAreaFilled(false);
		btnStart.setBounds(410, 550, 550, 250);
		btnStart.addActionListener(new startButton());
		mainPanel.add(btnStart);

		// button that opens the instructions panel
		JButton btnInstructions = new JButton(
				new ImageIcon(MainGUI.class.getResource("/resources/instructions_btn.png")));
		btnInstructions.addActionListener(new instructionsButton());
		btnInstructions.setContentAreaFilled(false);
		btnInstructions.setBounds(100, 20, 100, 100);
		mainPanel.add(btnInstructions);
		ImageIcon buttonIcon = new ImageIcon(MainGUI.class.getResource("/resources/instructions_btn.png"));
		Image image = buttonIcon.getImage();
		Image newimg = image.getScaledInstance(130, 80, java.awt.Image.SCALE_SMOOTH);
		buttonIcon = new ImageIcon(newimg);
		btnInstructions.setIcon(buttonIcon);

		// button that opens up the exit menu
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

		// button on the game mode selector screen that goes back to the start screen
		JButton btnBackSelector = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/back_btn.png")));
		btnBackSelector.setContentAreaFilled(false);
		btnBackSelector.setBounds(1250, 20, 100, 100);
		btnBackSelector.addActionListener(new backSelectorButton());
		selectorPanel.add(btnBackSelector);

		// button that sets the mode to PVP
		JButton btnPVP = new JButton("    Local Player Versus Player");
		btnPVP.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		btnPVP.addActionListener(new PVPButton());
		btnPVP.setHorizontalAlignment(SwingConstants.LEFT);
		btnPVP.setIcon(new ImageIcon(MainGUI.class.getResource("/resources/pvp_btn.png")));
		btnPVP.setBounds(300, 250, 600, 150);
		btnPVP.setContentAreaFilled(false);
		selectorPanel.add(btnPVP);

		// button that sets the mode to PVC
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

		namePanel.setBounds(0, 0, 1920, 1080);
		layeredPane.add(namePanel);
		namePanel.setLayout(null);
		namePanel.setVisible(false);

		// asks player for their name
		enterNameLabel.setBounds(500, 250, 400, 100);
		enterNameLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 31));
		namePanel.add(enterNameLabel);

		// text field for player to enter their name
		nameTextField.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		nameTextField.setBounds(500, 400, 400, 50);
		namePanel.add(nameTextField);

		// gets player's name and continues to the game screen
		JButton enterButton = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/enter_btn.png")));
		enterButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		enterButton.setContentAreaFilled(true);
		enterButton.setBounds(550, 600, 300, 100);
		enterButton.addActionListener(new enterButton());
		namePanel.add(enterButton);

		// button on the enter button screen that goes back to the game mode selector
		// screen
		JButton btnBackName = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/back_btn.png")));
		btnBackName.setContentAreaFilled(false);
		btnBackName.setBounds(1250, 20, 100, 100);
		btnBackName.addActionListener(new backNameButton());
		namePanel.add(btnBackName);

		/////////////////////////////////// Credits
		/////////////////////////////////// Screen///////////////////////////////////

		// shows credits
		creditsPanel.setBounds(0, 0, 1920, 1080);
		layeredPane.add(creditsPanel);
		creditsPanel.setLayout(null);
		creditsPanel.setVisible(false);

		// goes back to the exit menu
		JButton btnBackCredits = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/back_btn.png")));
		btnBackCredits.setContentAreaFilled(false);
		btnBackCredits.setBounds(1250, 20, 100, 100);
		btnBackCredits.addActionListener(new backLeaderboardButton());
		creditsPanel.add(btnBackCredits);

		/////////////////////////////////// Instructions
		/////////////////////////////////// Screen///////////////////////////////////

		// shows instructions
		instructionsPanel.setBounds(0, 0, 1920, 1080);
		layeredPane.add(instructionsPanel);
		instructionsPanel.setLayout(null);
		instructionsPanel.setVisible(false);

		instructionsTitle.setBounds(500, 250, 400, 100);
		instructionsTitle.setFont(new Font("Trebuchet MS", Font.PLAIN, 31));
		instructionsPanel.add(instructionsTitle);

		// goes back to the start screen
		JButton btnBackInstructions = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/back_btn.png")));
		btnBackInstructions.setContentAreaFilled(false);
		btnBackInstructions.setBounds(1250, 20, 100, 100);
		btnBackInstructions.addActionListener(new backInstructionsButton());
		instructionsPanel.add(btnBackInstructions);

		JTextArea instructions = new JTextArea();
		instructions.setBackground(Color.LIGHT_GRAY);
		instructions.setBounds(150, 400, 1100, 200);
		instructions.setEditable(false);
		instructions.setFont(new Font("STXihei", Font.PLAIN, 26));
		instructions.setText(
				"1. Each player has a character that their opponent tries to guess\n2. Players take turns asking each other yes/no questions about their opponent's character\n3.Characters are eliminated from the board based on the answers to the questions\n4. If a player guesses their opponent's character correctly, they win\n5. If they guess incorrectly, they automatically lose");
		instructionsPanel.add(instructions);

		/////////////////////////////////// Difficulty
		/////////////////////////////////// Screen///////////////////////////////////

		// shows the difficulties for AI
		difficultyPanel.setBounds(0, 0, 1920, 1080);
		layeredPane.add(difficultyPanel);
		difficultyPanel.setLayout(null);
		difficultyPanel.setVisible(false);

		// sets difficulties

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

		// goes back to the enter name screen
		JButton btnBackDifficulty = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/back_btn.png")));
		btnBackDifficulty.setContentAreaFilled(false);
		btnBackDifficulty.setBounds(1250, 20, 100, 100);
		btnBackDifficulty.addActionListener(new BackDifficultyButton());
		difficultyPanel.add(btnBackDifficulty);

		/////////////////////////////////// Player 1 Select Next
		/////////////////////////////////// ///////////////////////////////////

		// hides game screen until player 2 confirms
		player1_nextPanel.setBounds(0, 0, 1920, 1080);
		layeredPane.add(player1_nextPanel);
		player1_nextPanel.setLayout(null);
		player1_nextPanel.setVisible(false);

		JLabel wait_for_player_1 = new JLabel("Player 1, Please press Confirm to go to the next Screen");
		player1_nextPanel.add(wait_for_player_1);
		wait_for_player_1.setBounds(300, 200, 800, 400);
		wait_for_player_1.setFont(new Font("STXihei", Font.PLAIN, 30));

		JButton p1_Confirm = new JButton("CONFIRM");
		p1_Confirm.setBounds(400, 500, 600, 200);
		p1_Confirm.setContentAreaFilled(false);
		p1_Confirm.addActionListener(new player1_intermission());
		p1_Confirm.setFont(new Font("STXihei", Font.PLAIN, 30));
		player1_nextPanel.add(p1_Confirm);

		/////////////////////////////////// Player 2 Select Next
		/////////////////////////////////// ///////////////////////////////////

		// hides game screen until player 1 confirms
		player2_nextPanel.setBounds(0, 0, 1920, 1080);
		layeredPane.add(player2_nextPanel);
		player2_nextPanel.setLayout(null);
		player2_nextPanel.setVisible(false);

		JLabel wait_for_player_2 = new JLabel("Player 2, Please press Confirm to go to the next Screen");
		player2_nextPanel.add(wait_for_player_2);
		wait_for_player_2.setBounds(300, 200, 800, 400);
		wait_for_player_2.setFont(new Font("STXihei", Font.PLAIN, 30));

		JButton p2_Confirm = new JButton("CONFIRM");
		p2_Confirm.setBounds(400, 500, 600, 200);
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

		// asks player to confirm exiting the game

		JLabel persuasionText = new JLabel("  Are you sure leaving mid game???");
		persuasionText.setBounds(0, 0, 500, 100);
		persuasionText.setFont(new Font("Trebuchet MS", Font.PLAIN, 31));
		gameExitPanel.add(persuasionText);

		// button that goes back to the game screen
		JButton btnBackGameMenu = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/exit_btn.png")));
		btnBackGameMenu.addActionListener(new backGameMenuButton());
		btnBackGameMenu.setContentAreaFilled(false);
		btnBackGameMenu.setBounds(300, 90, 100, 100);
		gameExitPanel.add(btnBackGameMenu);

		// button that goes back to the start menu
		JButton btnLeaveGame = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/accept_btn.png")));
		btnLeaveGame.setBackground(new Color(250, 128, 114));
		btnLeaveGame.addActionListener(new backGameButton());
		btnLeaveGame.setBounds(100, 90, 100, 100);
		btnLeaveGame.setBorderPainted(false);
		gameExitPanel.add(btnLeaveGame);

		/////////////////////////////////// Question From Other Player
		/////////////////////////////////// ///////////////////////////////////

		// displays the opponent's question
		opAskPanel.setBackground(Color.LIGHT_GRAY);
		opAskPanel.setBounds(320, 200, 720, 400);
		layeredPane.add(opAskPanel);
		opAskPanel.setLayout(null);
		opAskPanel.setVisible(false);

		opQuestion.setBounds(100, 100, 500, 100);
		opAskPanel.add(opQuestion);
		opQuestion.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));

		// buttons for player to confirm if the question is true or false
		JButton yesButton = new JButton("Yes");
		yesButton.setContentAreaFilled(false);
		yesButton.setBounds(100, 200, 100, 100);
		yesButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 21));
		yesButton.addActionListener(new opYesButton());
		opAskPanel.add(yesButton);

		JButton noButton = new JButton("No");
		noButton.setContentAreaFilled(false);
		noButton.setBounds(400, 200, 100, 100);
		noButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 21));
		noButton.addActionListener(new opNoButton());
		opAskPanel.add(noButton);

		// label that shows up if the player lies
		questioning.setBounds(100, 300, 500, 100);
		opAskPanel.add(questioning);
		questioning.setFont(new Font("Trebuchet MS", Font.PLAIN, 21));
		questioning.setVisible(false);

		/////////////////////////////////// Game
		/////////////////////////////////// Screen///////////////////////////////////

		gamePanel.setBounds(0, 0, 1920, 1080);
		layeredPane.add(gamePanel);
		gamePanel.setLayout(null);
		gamePanel.setVisible(false);

		// opens panel that asks player if they want to leave mid game
		JButton btnBackGame = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/back_btn.png")));
		btnBackGame.setContentAreaFilled(false);
		btnBackGame.setBounds(1320, 20, 100, 100);
		btnBackGame.addActionListener(new menuGameButton());
		gamePanel.add(btnBackGame);

		// panel that displays all the character icons
		charactersPanel.setBounds(0, 0, 1000, 800);
		charactersPanel.setLayout(null);
		charactersPanel.setVisible(true);
		gamePanel.add(charactersPanel);

		/////////////////////////////////// Adding Characters for Game Panel
		/////////////////////////////////// ///////////////////////////////////
		int num = 0;
		for (int x = 0; x < 4; x++) { // displaying the characters in a 4x6 grid format
			for (int y = 0; y < 6; y++) {
				String name = Characters.get(num); // going through the arraylist of names
				String namepicture = "/resources/characters/" + name + ".png"; // getting the filepath of the charact
				// create a button for the charactere //displayman rieht gnisu noci s'r
				characterButtons[num] = new JButton();
				// the button icon as the player ico
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

		// panel that shows the player's score
		scorePanel.setBounds(1000, 0, 440, 900);
		scorePanel.setLayout(null);
		scorePanel.setVisible(true);
		gamePanel.add(scorePanel);

		JLabel titleScore = new JLabel("SCORE");
		titleScore.setFont(new Font("STXihei", Font.PLAIN, 52));
		titleScore.setBounds(130, 50, 200, 50);
		scorePanel.add(titleScore);

		actualScore.setFont(new Font("STXihei", Font.PLAIN, 52));
		actualScore.setBounds(90, 120, 250, 70);

		Border blackline = BorderFactory.createLineBorder(Color.black);
		actualScore.setBorder(blackline);
		actualScore.setHorizontalAlignment(SwingConstants.CENTER);
		scorePanel.add(actualScore);

		player_character.setHorizontalAlignment(SwingConstants.CENTER);
		player_character.setBounds(65, 220, 300, 400);
		player_character.setBorder(blackline);
		player_character.setHorizontalTextPosition(SwingConstants.CENTER);
		player_character.setIcon(new ImageIcon(MainGUI.class.getResource("/resources/characters/default.png")));
		scorePanel.add(player_character);

		// panel that shows how many characters the opponent has left
		enemyPanel.setBackground(Color.RED);
		enemyPanel.setBounds(45, 650, 340, 220);
		scorePanel.add(enemyPanel);

		/////////////////////////////////// Question Screen
		/////////////////////////////////// ///////////////////////////////////

		// panel that displays the selected question the player wants to ask opponent
		questionPanel.setBounds(0, 800, 1000, 100);
		questionPanel.setLayout(null);
		questionPanel.setVisible(true);
		gamePanel.add(questionPanel);

		// dropdown menu that shows the possible questions to select
		questionList.setBounds(10, 11, 820, 80);
		questionList.setFont(new Font("STXihei", Font.PLAIN, 32));
		questionList.getSelectedItem();
		questionList.removeAllItems();
		questionPanel.add(questionList);

		// button that sends the selected question to the opponent to answer
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(840, 11, 150, 80);
		btnConfirm.setFont(new Font("STXihei", Font.PLAIN, 30));
		btnConfirm.addActionListener(new confirmButton());
		questionPanel.add(btnConfirm);

		/////////////////////////////////// Character Selection Screen [PVC]
		/////////////////////////////////// ///////////////////////////////////

		// displays all characters for the player to choose
		playerselectionPanel.setBounds(0, 0, 1980, 1080);
		layeredPane.add(playerselectionPanel);
		playerselectionPanel.setLayout(null);
		playerselectionPanel.setVisible(false);

		// goes to the game screen when clicked
		JButton btnCharacterConfirm = new JButton("Confirm Character");
		btnCharacterConfirm.setFont(new Font("STXihei", Font.PLAIN, 30));
		btnCharacterConfirm.setBounds(500, 800, 400, 100);
		btnCharacterConfirm.addActionListener(new characterConfirmButton());
		playerselectionPanel.add(btnCharacterConfirm);

		/////////////////////////////////// Adding Characters for Selection Screen
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

		/////////////////////////////////// End Game Screen [Finish Game]
		/////////////////////////////////// ///////////////////////////////////

		endGamePanel.setBounds(0, 0, 1980, 1080);
		layeredPane.add(endGamePanel);
		endGamePanel.setLayout(null);
		endGamePanel.setVisible(false);

		resultLabel.setBounds(500, 300, 400, 100);
		resultLabel.setFont(new Font("STXihei", Font.PLAIN, 50));
		endGamePanel.add(resultLabel);

		JButton btnFinishGame = new JButton("Leave Game");
		btnFinishGame.setFont(new Font("STXihei", Font.PLAIN, 30));
		btnFinishGame.setBounds(500, 450, 400, 100);
		btnFinishGame.setHorizontalAlignment(SwingConstants.CENTER);
		btnFinishGame.setContentAreaFilled(false);
		btnFinishGame.addActionListener(new finishButton());
		endGamePanel.add(btnFinishGame);

		JButton btnToLeaderboard = new JButton("Go to Leaderboard");
		btnToLeaderboard.setFont(new Font("STXihei", Font.PLAIN, 30));
		btnToLeaderboard.setBounds(500, 600, 400, 100);
		btnToLeaderboard.setHorizontalAlignment(SwingConstants.CENTER);
		btnToLeaderboard.setContentAreaFilled(false);
		btnToLeaderboard.addActionListener(new finishLeaderboardButton());
		endGamePanel.add(btnToLeaderboard);

		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { exitPanel, contentPane, layeredPane,
				btnStart, lbLogo, mainPanel, selectorPanel, btnBackSelector, btnPVP, leaderboardPanel, btnExit,
				btnLeaderboard, btnCredits, creditsPanel, difficultyPanel, btnEasy, btnMid, btnHard, btnBackDifficulty,
				questionList, playerselectionPanel }));
	}

	static class startButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mainPanel.setVisible(false);
			selectorPanel.setVisible(true);
			exitPanel.setVisible(false);
			game.restartGame();
			game.initializeGame();
			p1_character = null;
			p2_character = null;
			p1_ongoing = true;
			p2_select = false;
			guess1 = false;
			guess2 = false;
			p1_questions.readFile();
			// Questions = p1_questions.getQuestions(); // Testing Purposes only
			data = Questions.toArray(new String[Questions.size()]);
			questionList.removeAllItems();
			questionList.addItem("Guess Character");
			for (int i = 0; i < data.length; i++) {
				questionList.addItem(data[i]);
			}
		}
	}

	static class finishButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			endGamePanel.setVisible(false);
			mainPanel.setVisible(true);
			game.updateScores(playerName, amountCount, pvc_win);
			game.restartGame();
			avgLeaderUpdate();
			gamesWonUpdate();
			resetCharacter();
		}
	}

	static class finishLeaderboardButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			endGamePanel.setVisible(false);
			leaderboardPanel.setVisible(true);
			game.updateScores(playerName, amountCount, pvc_win);
			game.restartGame();
			avgLeaderUpdate();
			gamesWonUpdate();
			resetCharacter();
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
			mainPanel.setVisible(true);
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
			namePanel.setVisible(true);
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
			playerselectionPanel.setVisible(true);
			characterButtons[15].setVisible(true);
			characterButtons[16].setVisible(true);
			characterButtons[17].setVisible(true);
			aiPlayer.setDifficulty(3);
		}
	}

	static class MedButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			difficultyPanel.setVisible(false);
			playerselectionPanel.setVisible(true);
			characterButtons[15].setVisible(true);
			characterButtons[16].setVisible(true);
			characterButtons[17].setVisible(true);
			aiPlayer.setDifficulty(2);
		}
	}

	static class HardButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			difficultyPanel.setVisible(false);
			playerselectionPanel.setVisible(true);
			characterButtons[15].setVisible(true);
			characterButtons[16].setVisible(true);
			characterButtons[17].setVisible(true);
			aiPlayer.setDifficulty(1);
		}
	}

	static class PVPButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			selectorPanel.setVisible(false);
			pvpMode = true;
			playerselectionPanel.setVisible(true);
			characterButtons[15].setVisible(true);
			characterButtons[16].setVisible(true);
			characterButtons[17].setVisible(true);

			Questions = questions.getQuestions();
			questionList.removeAllItems();

		}
	}

	static class PVCButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			pvpMode = false;
			resetCharacter();
			selectorPanel.setVisible(false);
			namePanel.setVisible(true);
			enterNameLabel.setText("Enter Your Name:");
			nameTextField.setText(" ");

			Random rand = new Random();
			int random = rand.nextInt(24);
			p2_character = Characters.get(random);
			System.out.println("AI Character - " + p2_character);
			aiPlayer.logNewGame(); // Calls the method in mainAI to log the start of a new round.
			aiPlayer.logCharacterChoice(p2_character); // Stores the Ai's character choice for the new round.
			questionList.removeAllItems();
			TextFileReader check_questions = new TextFileReader("src\\resources\\questions.txt");
			check_questions.readFile();
			Questions = check_questions.getQuestions();
			data = Questions.toArray(new String[Questions.size()]);
			questionList.addItem("Guess Character");
			for (int i = 0; i < data.length; i++) {
				questionList.addItem(data[i]);
			}
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
			opAskPanel.setVisible(false);
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
			if (guess1 == true) {
				playerselectionPanel.setVisible(false);
				endGamePanel.setVisible(true);
				System.out.println("Character - " + p2_character);
				System.out.println("File - " + fileName);
				if (p2_character.equals(fileName)) {
					pvc_win = true;
					resultLabel.setText("YOU WIN!!!!");
				} else {
					pvc_win = false;
					resultLabel.setText("YOU LOST!!!");
				}
			} else {
				if (pvpMode) {
					if (p1_ongoing) {
						p1_character = fileName;
						p1_ongoing = false;
						player2_nextPanel.setVisible(true);
						playerselectionPanel.setVisible(false);
						p2_select = true;
						game.characterChoice(1, p1_character);
					} else {
						p2_character = fileName;
						p1_ongoing = true;
						gameSetCharacterScreen();
						playerselectionPanel.setVisible(false);
						player1_nextPanel.setVisible(true);
						game.characterChoice(2, p2_character);
					}
				} else {
					playerselectionPanel.setVisible(false);
					gamePanel.setVisible(true);
					game.characterChoice(2, p2_character);
				}
			}

		}
	}

	static class confirmButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			question = questionList.getSelectedItem().toString();
			if (question.equals("Guess Character")) {
				questionList.removeAllItems();
				if (p1_ongoing) {
					guess1 = true;

				} else {
					guess2 = true;

				}
				gamePanel.setVisible(false);
				playerselectionPanel.setVisible(true);
				characterButtons[15].setVisible(true);
				characterButtons[16].setVisible(true);
				characterButtons[17].setVisible(true);
			} else {
				questionList.removeAllItems();
				if (p1_ongoing) { // Player 1
					actualScore.setText("Player 1");
					if (pvpMode) {
						result = game.checkAnswer(question, 1);
						p1_questionAsked = question;
						TextFileReader check_questions = new TextFileReader(
								"src\\resources\\p2_questions_remaining.txt");
						check_questions.readFile();
						Questions = check_questions.getQuestions();
						data = Questions.toArray(new String[Questions.size()]);
						questionList.addItem("Guess Character");
						for (int i = 0; i < data.length; i++) {
							questionList.addItem(data[i]);
						}
						player2_nextPanel.setVisible(true);
						gamePanel.setVisible(false);
						p1_ongoing = false;
						gameSetCharacterScreen();
						System.out.print("Player 1 - "); // Testing
					} else { // PVC Mode thing
						amountCount++;
						p1_questionAsked = question;
						p1_ongoing = true;
						gameSetCharacterScreen();

						result = game.checkAnswer(p1_questionAsked, 1);

						gameUpdateCharacter();

						System.out.println("Player Q - " + p1_questionAsked);
						System.out.println("Result - " + result);

						TextFileReader aiCharcaters = new TextFileReader("src\\resources\\p2_characters_remaining.txt");
						TextFileReader aiQuestion = new TextFileReader("src\\resources\\p2_questions_remaining.txt");

						aiCharcaters.readFile();
						aiQuestion.readFile();

						characters = new ArrayList<>();

						for (int i = 0; i < aiCharcaters.getname().size(); i++) {
							GameCharacter character = new GameCharacter();
							character.setName(aiCharcaters.getname().get(i));
							character.setGender(aiCharcaters.getgender().get(i));
							character.setEyeColour(aiCharcaters.geteye_color().get(i));
							character.setSkinTone(aiCharcaters.getskin_tone().get(i));
							character.setHairColour(aiCharcaters.gethair_color().get(i));
							character.setFacialHair(aiCharcaters.getfacial_hair().get(i));
							character.setGlasses(aiCharcaters.getglasses().get(i));
							character.setShowingTeeth(aiCharcaters.getshowing_teeth().get(i));
							character.setWearingHat(aiCharcaters.getwearing_hat().get(i));
							character.setHairLength(aiCharcaters.gethair_length().get(i));
							character.setPiercings(aiCharcaters.getpiercings().get(i));
							characters.add(character);
						}

						aiPlayer.updateGameState(characters, aiQuestion.getQuestions());
						question = aiPlayer.selectQuestion();

						p2_questionAsked = question;

						System.out.println("AI Q - " + question);

						opAskPanel.setVisible(true);
						opAskingHide(false);

					}

				} else { // Player 2
					actualScore.setText("Player 2");
					result = game.checkAnswer(question, 2);
					p2_questionAsked = question;
					TextFileReader check_questions = new TextFileReader("src\\resources\\p1_questions_remaining.txt");
					check_questions.readFile();
					Questions = check_questions.getQuestions();
					data = Questions.toArray(new String[Questions.size()]);
					questionList.removeAllItems();
					questionList.addItem("Guess Character");
					for (int i = 0; i < data.length; i++) {
						questionList.addItem(data[i]);
					}
					player1_nextPanel.setVisible(true);
					gamePanel.setVisible(false);
					p1_ongoing = true;
					gameSetCharacterScreen();
					System.out.print("Player 2 - "); // Testing

				}
				opQuestion.setText(question);

			}

		}
	}

	static class player1_intermission implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (p2_questionAsked != null) {
				opAskPanel.setVisible(true);
				opAskingHide(false);
			}
			player1_nextPanel.setVisible(false);
			gamePanel.setVisible(true);
		}
	}

	static class player2_intermission implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (p2_select) {
				playerselectionPanel.setVisible(true);
				player2_nextPanel.setVisible(false);
				p2_select = false;
			} else {
				gamePanel.setVisible(true);
				opAskPanel.setVisible(true);
				opAskingHide(false);
				player2_nextPanel.setVisible(false);
			}
		}
	}

	static class opYesButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (question.lastIndexOf("?") != -1) {
				if (pvpMode) {
					if (result) {
						opAskPanel.setVisible(false);
						opAskingHide(true);
						questioning.setVisible(false);
					} else {
						questioning.setVisible(true);
					}
				} else { // No Confirming
					result = true;
					game.checkAnswerPVC(p2_questionAsked, 2, result);
					System.out.println("Result - " + result); // Testing Purposes
					opAskPanel.setVisible(false);
					opAskingHide(true);
					p2_questionAsked = question;
					TextFileReader check_questions = new TextFileReader("src\\resources\\p1_questions_remaining.txt");
					check_questions.readFile();
					Questions = check_questions.getQuestions();
					data = Questions.toArray(new String[Questions.size()]);
					questionList.removeAllItems();
					questionList.addItem("Guess Character");
					for (int i = 0; i < data.length; i++) {
						questionList.addItem(data[i]);
					}
				}
			} else {
				endGamePanel.setVisible(true);
				pvc_win = false;
				gamePanel.setVisible(false);
				opAskPanel.setVisible(false);
				resultLabel.setText("YOU LOSE!!!");
			}

		}

	}

	static class opNoButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (question.lastIndexOf("?") != -1) {
				if (pvpMode) {// PVP Mode Confirming yes
					if (!result) {
						opAskPanel.setVisible(false);
						opAskingHide(true);
						questioning.setVisible(false);
					} else {
						questioning.setVisible(true);
					}
				} else {// PVP Mode No Confirming
					result = false;
					game.checkAnswerPVC(p2_questionAsked, 2, result);
					System.out.println("Result - " + result); // Testing Purposes
					opAskPanel.setVisible(false);
					opAskingHide(true);
					p2_questionAsked = question;
					TextFileReader check_questions = new TextFileReader("src\\resources\\p1_questions_remaining.txt");
					check_questions.readFile();
					Questions = check_questions.getQuestions();
					data = Questions.toArray(new String[Questions.size()]);
					questionList.removeAllItems();
					questionList.addItem("Guess Character");
					for (int i = 0; i < data.length; i++) {
						questionList.addItem(data[i]);
					}
				}
			} else {
				endGamePanel.setVisible(true);
				pvc_win = true;
				gamePanel.setVisible(false);
				opAskPanel.setVisible(false);
				resultLabel.setText("You Won!!!");
			}

		}
	}

	static void gameSetCharacterScreen() {
		System.out.println("chara Q - " + p1_questionAsked);
		System.out.println("P2 Q - " + p2_questionAsked);

		if (!pvpMode) {
			player_character.setFont(new Font("STXihei", Font.PLAIN, 30));
		} else {
			String namepicture;
			if (p1_ongoing) {
				namepicture = "/resources/characters/" + p1_character + ".png";
			} else {
				namepicture = "/resources/characters/" + p2_character + ".png";
			}
			ImageIcon imageIcon = new ImageIcon(MainGUI.class.getResource(namepicture));
			Image image = imageIcon.getImage();
			Image newimg = image.getScaledInstance(283, 375, java.awt.Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(newimg);
			player_character.setIcon(imageIcon);
		}
	}

	static void gameUpdateCharacter() {
		if (!pvpMode) {
			TextFileReader chara = new TextFileReader("src\\resources\\p1_characters_removed.txt");
			chara.readFile();
			ArrayList<String> check = new ArrayList<String>();
			check = chara.getname();
			for (int x = 0; x < check.size(); x++) {
				for (int i = 0; i < Characters.size(); i++) {
					if (Characters.get(i).equals(check.get(x))) {
						String namepicture = "/resources/eliminated_characters/elim_" + check.get(x) + ".png";
						characterButtons[i].setIcon(new ImageIcon(MainGUI.class.getResource(namepicture)));
					}
				}
			}
		}
	}

	static void avgLeaderUpdate() {
		List<List<String>> update = new ArrayList<>();
		leader1 = "Name \t\t\t Average\n\n";
		update = TextFileReader.readLeaderboard("src\\resources\\average_questions_leaderboard");
		for (List<String> player : update) {
			leader1 = leader1 + (player.get(0) + " -                    \t\t" + player.get(1) + "\n");
		}
		avgLeader.setText(leader1);

	}

	static void gamesWonUpdate() {
		List<List<String>> update = new ArrayList<>();
		leader2 = "Name \t\t\t Won\n\n";
		update = TextFileReader.readLeaderboard("src\\resources\\games_won_leaderboard");
		for (List<String> player : update) {
			leader2 = leader2 + (player.get(0) + " -                    \t\t" + player.get(1) + "\n");
		}
		gamesWon.setText(leader2);

	}

	static void opAskingHide(boolean nah) {
		characterButtons[8].setVisible(nah);
		characterButtons[9].setVisible(nah);
		characterButtons[10].setVisible(nah);
		characterButtons[11].setVisible(nah);
		characterButtons[14].setVisible(nah);
		characterButtons[15].setVisible(nah);
		characterButtons[16].setVisible(nah);
		characterButtons[17].setVisible(nah);
	}

	static void resetCharacter() {
		questionList.removeAllItems();
		for (int i = 0; i < 24; i++) {
			String name = Characters.get(i);
			String namepicture = "/resources/characters/" + name + ".png";
			characterButtons[i].setIcon(new ImageIcon(MainGUI.class.getResource(namepicture)));
		}
	}

	static class enterButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			namePanel.setVisible(false);
			difficultyPanel.setVisible(true);
			playerName = nameTextField.getText().trim();
		}
	}

	static class backNameButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			namePanel.setVisible(false);
			selectorPanel.setVisible(true);
		}
	}

	static class backInstructionsButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			instructionsPanel.setVisible(false);
			mainPanel.setVisible(true);
		}
	}

	static class instructionsButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			instructionsPanel.setVisible(true);
			mainPanel.setVisible(false);
		}
	}

}