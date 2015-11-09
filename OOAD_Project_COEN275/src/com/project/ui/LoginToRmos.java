/**
 * @author Pragati Shrivastava
 *
 */
package com.project.ui;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.project.EcoRe.RMOS;


public class LoginToRmos extends JFrame implements ActionListener { 
	
	private static final long serialVersionUID = 1L;
	private RMOS rmos = new RMOS();
	
	private JPanel contentPane;
	private JPanel panel;
	private JTextField txtUsername;
	static JTextArea txtrOutput;
	private JPasswordField pwdPassword;
	private JButton btnLogin;
	
	public LoginToRmos() {
		super("RMOS");
		setForeground(new Color(0, 51, 204));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 825);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 51, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel LeftPanel = new JPanel();
		LeftPanel.setBackground(new Color(255, 255, 0));
		LeftPanel.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "RCM Controller",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 51,
						204)));
		contentPane.add(LeftPanel);
		LeftPanel.setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 0));
		panel.setBounds(6, 24, 517, 38);
		LeftPanel.add(panel);
		panel.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblUsername.setForeground(new Color(0, 51, 255));
		lblUsername.setBackground(new Color(0, 204, 153));
		lblUsername.setBounds(6, 9, 95, 22);
		panel.add(lblUsername);

		txtUsername = new JTextField();
		txtUsername.setBounds(91, 6, 100, 28);
		panel.add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblPassword.setForeground(new Color(0, 51, 255));
		lblPassword.setBounds(203, 9, 75, 22);
		panel.add(lblPassword);

		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(279, 6, 100, 28);
		panel.add(pwdPassword);

		btnLogin = new JButton("LogIn");
		btnLogin.setForeground(new Color(204, 0, 0));
		btnLogin.setFont(new Font("Apple LiGothic", Font.BOLD, 15));
		btnLogin.setBounds(391, 7, 117, 29);
		btnLogin.addActionListener((ActionListener) this);
		panel.add(btnLogin);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// store command from event e
		Object source = e.getSource();

		// so many if-else to judge which event is being used
		// ******* From Left Panel ********

		// *********** Operations Panel ******************
		if (source == btnLogin) {
			String userName = txtUsername.getText();
			String passWord = "";

			char[] password = pwdPassword.getPassword();

			passWord = String.valueOf(password);

			boolean canLogIn = false;
			canLogIn = rmos.logIn(userName, passWord);

			if (canLogIn) {
				txtrOutput.setText("Log In Successful !");
			} else {
				txtrOutput.setText("Please input the valid username or password");
						
			}

		}
	}

}
