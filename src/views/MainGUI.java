package views;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.List;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
	public MainGUI() {
		setBounds(new Rectangle(0, 0, 1000, 600));
		
		initComponents();
		createEvents();
		

	}
	
	private void initComponents() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainGUI.class.getResource("/resources/icon_logo.png"))); //Create the icon image 
		setTitle("Exiting Guess Who Game !!!");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setLayout(new BorderLayout(0, 0));
		
		JPanel mainScreen = new JPanel();
		mainScreen.setBackground(Color.DARK_GRAY);
		desktopPane.add(mainScreen);
		mainScreen.setLayout(new BorderLayout(0, 0));
		
		JLabel logoLabel = new JLabel("");
		logoLabel.setBounds(new Rectangle(0, 0, 600, 300));
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainScreen.add(logoLabel, BorderLayout.NORTH);
		logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		ImageIcon logo = new ImageIcon("/resources/logo.png");
		Image scaledlogo = logo.getImage().getScaledInstance(logoLabel.getWidth(), logoLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon logoimage = new ImageIcon(scaledlogo);
		logoLabel.setIcon(logoimage);
		contentPane.add(desktopPane);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane, desktopPane, mainScreen, logoLabel}));
		
	}
	
	private void createEvents() {
		
	}
}
