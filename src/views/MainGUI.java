package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JPanel mainScreen = new JPanel();
	private static JPanel selectorScreen = new JPanel();

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
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainGUI.class.getResource("/resources/icon_logo.png"))); //Create the icon image 
		setTitle("Exiting Guess Who Game !!!");
		
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
		selectorScreen.setVisible(false);
		
		///////////////////////////////////Selector Screen///////////////////////////////////
	
		selectorScreen.setBounds(0, 0, 1920, 1080);
		layeredPane.add(selectorScreen);
		selectorScreen.setLayout(null);
		
		JButton btnBack = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/back_btn.png")));
		btnBack.setContentAreaFilled(false);
		btnBack.setBounds(1250, 20, 100, 100);
		btnBack.addActionListener(new backSelectorButton());
		selectorScreen.add(btnBack);
		
		///////////////////////////////////Main Screen///////////////////////////////////
		

		mainScreen.setBounds(0, 0, 1920, 1080);
		layeredPane.add(mainScreen);
		mainScreen.setLayout(null);
	
		JLabel lbLogo = new JLabel(new ImageIcon(MainGUI.class.getResource("/resources/logo.png")));
		lbLogo.setBounds(275, 197, 850, 278);
		mainScreen.add(lbLogo);
		
		JButton btnStart = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/start_btn.png")));
		btnStart.setContentAreaFilled(false);
		btnStart.setBounds(410, 500, 550, 250);
		btnStart.addActionListener(new startButton());
		mainScreen.add(btnStart);
		
		JButton btnLeaderboard = new JButton(new ImageIcon(MainGUI.class.getResource("/resources/leaderboard_btn.png")));
		btnLeaderboard.setContentAreaFilled(false);
		btnLeaderboard.setBounds(970, 520, 100, 100);
		mainScreen.add(btnLeaderboard);
		
		mainScreen.setVisible(true);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane, layeredPane, btnStart, lbLogo, btnLeaderboard, mainScreen, selectorScreen, btnBack}));
		
	}
	
	private void createEvents() {
		
	}
	
	static class startButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mainScreen.setVisible(false);
			selectorScreen.setVisible(true);
		}
	}
	static class backSelectorButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mainScreen.setVisible(true);
			selectorScreen.setVisible(false);
		}
	}
}
