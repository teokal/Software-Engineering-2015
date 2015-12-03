import java.awt.EventQueue;
import javax.swing.*;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.Color;

public class MainApplication {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainApplication window = new MainApplication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	JOptionPane.showMessageDialog(null, "Ouuu! O petros exei nevra!!");	
	}

	/**
	 * Create the application.
	 */
	public MainApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 500);
		frame.setTitle("EasyBook");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel NavigationPanel = new JPanel();
		frame.getContentPane().add(NavigationPanel, BorderLayout.NORTH);
		NavigationPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JButton btn_newBook = new JButton(Messages.getString("MainApplication.btn_newBook")); //$NON-NLS-1$
		NavigationPanel.add(btn_newBook);
		
		//optionsMenu.add(jmiLogin = new JMenuItem("Login", 'L'));

		
		JButton btn_bookings = new JButton(Messages.getString("MainApplication.btn_bookings")); //$NON-NLS-1$
		NavigationPanel.add(btn_bookings);
	}

}
