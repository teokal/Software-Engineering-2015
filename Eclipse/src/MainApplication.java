import java.awt.EventQueue;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.*;
import java.util.Calendar;
import java.util.Date;
import java.awt.Component;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;


public class MainApplication {

	private JFrame frame;
	private JTextField textField;
	private JPanel new_book_panel_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_1;

	
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel(false);
		tabbedPane.addTab("New Book", null, panel, null);
		panel.setLayout(null);
		
		new_book_panel_1 = new JPanel();
		new_book_panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		
		new_book_panel_1.setBounds(0, 0, 729, 433);
		panel.add(new_book_panel_1);
		new_book_panel_1.setLayout(null);
		
		JLabel lblNumberOfPersons = new JLabel("Number of People:");
		lblNumberOfPersons.setBounds(178, 43, 135, 37);
		lblNumberOfPersons.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblNumberOfPersons.setVerifyInputWhenFocusTarget(false);
		new_book_panel_1.add(lblNumberOfPersons);
		
		textField = new JTextField();
		textField.setBounds(285, 51, 86, 20);
		new_book_panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblDateCheckIn = new JLabel("Date Check In:");
		lblDateCheckIn.setBounds(178, 85, 97, 14);
		new_book_panel_1.add(lblDateCheckIn);
		
		JLabel lblNewLabel = new JLabel("Date Check Out:");
		lblNewLabel.setBounds(178, 122, 97, 14);
		new_book_panel_1.add(lblNewLabel);
		
		JLabel lblExtras = new JLabel("Extras:");
		lblExtras.setBounds(180, 172, 46, 14);
		new_book_panel_1.add(lblExtras);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(285, 82, 103, 20);
		new_book_panel_1.add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(285, 122, 103, 20);
		new_book_panel_1.add(dateChooser_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(285, 169, 182, 99);
		new_book_panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNext = new JButton("NEXT");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new_book_panel_1.setVisible(false);
			
				
				
				
			
			}
		});
		btnNext.setBounds(73, 328, 166, 23);
		new_book_panel_1.add(btnNext);
		
		JPanel new_book_panel_2 = new JPanel();
		new_book_panel_2.setBorder(new CompoundBorder());
		new_book_panel_2.setBounds(0, 0, 719, 433);
		panel.add(new_book_panel_2);
		new_book_panel_2.setLayout(null);
		
		JButton btnNextPage = new JButton("NEXT PAGE");
		btnNextPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new_book_panel_2.setVisible(false);
				
			}
		});
		btnNextPage.setBounds(305, 381, 146, 23);
		new_book_panel_2.add(btnNextPage);
		
		JLabel lblRooms = new JLabel("ROOMS:   ");
		lblRooms.setBounds(99, 144, 72, 14);
		new_book_panel_2.add(lblRooms);
		
		JPanel new_book_panel_3 = new JPanel();
		new_book_panel_3.setBounds(0, 0, 729, 433);
		panel.add(new_book_panel_3);
		new_book_panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setBounds(10, 11, 46, 14);
		new_book_panel_3.add(lblNewLabel_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(109, 11, 86, 20);
		new_book_panel_3.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Surname:");
		lblNewLabel_2.setBounds(10, 44, 46, 14);
		new_book_panel_3.add(lblNewLabel_2);
		
		textField_4 = new JTextField();
		textField_4.setBounds(107, 41, 86, 20);
		new_book_panel_3.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("TEL:");
		lblNewLabel_3.setBounds(10, 101, 46, 14);
		new_book_panel_3.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Email:");
		lblNewLabel_4.setBounds(10, 166, 46, 14);
		new_book_panel_3.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("ID Number:");
		lblNewLabel_5.setBounds(10, 221, 86, 14);
		new_book_panel_3.add(lblNewLabel_5);
		
		textField_5 = new JTextField();
		textField_5.setBounds(107, 98, 86, 20);
		new_book_panel_3.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(107, 163, 86, 20);
		new_book_panel_3.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(107, 218, 86, 20);
		new_book_panel_3.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblPaymentMethod = new JLabel("Payment Method:");
		lblPaymentMethod.setBounds(10, 267, 86, 14);
		new_book_panel_3.add(lblPaymentMethod);
		
		textField_8 = new JTextField();
		textField_8.setBounds(109, 264, 86, 20);
		new_book_panel_3.add(textField_8);
		textField_8.setColumns(10);
		
				JLabel label = new JLabel("");
				label.setBounds(22, 326, 46, 14);
				new_book_panel_3.add(label);
				
				JLabel lblNotes = new JLabel("Notes:");
				lblNotes.setBounds(22, 326, 46, 14);
				new_book_panel_3.add(lblNotes);
				
				textField_9 = new JTextField();
				textField_9.setBounds(107, 320, 207, 20);
				new_book_panel_3.add(textField_9);
				textField_9.setColumns(10);
				
				JButton btnEpomeno = new JButton("EPOMENO");
				btnEpomeno.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new_book_panel_3.setVisible(false);
						
					}
				});
				btnEpomeno.setBounds(209, 349, 89, 23);
				new_book_panel_3.add(btnEpomeno);
		
		JPanel new_book_panel_5 = new JPanel();
		new_book_panel_5.setBounds(0, 0, 729, 433);
		panel.add(new_book_panel_5);
		new_book_panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewbookpanel = new JLabel("Book has been successfully completed!");
		new_book_panel_5.add(lblNewbookpanel);
		
		JButton btnExit = new JButton("EXIT!!!");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		new_book_panel_5.add(btnExit);
		
		JPanel new_book_panel_4 = new JPanel();
		new_book_panel_4.setBounds(0, 0, 729, 433);
		panel.add(new_book_panel_4);
		new_book_panel_4.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Book Preview");
		lblNewLabel_6.setBounds(331, 11, 64, 14);
		new_book_panel_4.add(lblNewLabel_6);
		
		JButton btnNex = new JButton("COMPLETE");
		btnNex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new_book_panel_4.setVisible(false);
				new_book_panel_5.setVisible(true);
			}
		});
		btnNex.setBounds(306, 297, 85, 23);
		new_book_panel_4.add(btnNex);
		
		JPanel panel_1 = new JPanel(false);
		tabbedPane.addTab("Bookings", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("Oi kratsis einai oi e3is:");
		label_1.setBounds(0, 209, 106, 14);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_1);
		
		JPanel panel_2 = new JPanel(false);
		tabbedPane.addTab("Rooms", null, panel_2, null);
		panel_2.setLayout(new GridLayout(1, 1));
		
		JLabel label_2 = new JLabel("Panel #1");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_2);
	
	}
	
	

	protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(1, 1));
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.add(filler);
        return panel;
    }
}
