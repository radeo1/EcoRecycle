package com.project.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.project.EcoRe.RCMMonitor;
import com.project.EcoRe.RCMRecycle;
import com.project.EcoRe.RMOSUsageManager;
import com.project.EcoRe.RecycleItem;
import com.scu.actions.LoginAction;
import com.scu.actions.RCMAction;
import com.scu.actions.RMOSManager;
import com.scu.logic.BackendLogic;

public class RMOSDisplay extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1;

	private RMOSManager rmosManager = new RMOSManager();
	private RMOSUsageManager rmosUsageManager = new RMOSUsageManager();
	private RCMMonitor rcmMonitor = new RCMMonitor();
	private RecycleItem recycleItem = new RecycleItem();

	private Container contentPane;
	private JPanel panel1, panel2, panel3;

	private JTextField textUsername, textRcmId, textRefillAmount, textRcmLocation;
	private JTextField textRcm, textRcmLoc, textItemType, textUnitPrice ;
	private JPasswordField password;
	private JButton buttonLogin, addRCM, removeRCM, activateRCM, refillRCM,deactivateRCM,clearRCM, showRCM, currentWeight,
			currentCash, currentCoupon,btnLastEmptied, btnMonthlyTransactions, btnCashDebitedPerMonth,btnWeightPerMonth,btnRcmStatus,
			btnCurrItemsRecycled, btnItemsRecycledByMonth, addItem, removeItem, updatePrice, listItem, mostUsed, monthlyUsageGraph ;
	private JLabel rcmPlaceLabel;
	private JComboBox rcmCombo, comboRcmList;
	static JTextArea textDisplayOutput;
	static JTextArea errorDisplayOutput;
	BackendLogic logic = new BackendLogic();
	RCMAction rcmAction = new RCMAction();
	List<String> rcmIds = new ArrayList<String>();
	DefaultComboBoxModel model = null;

	public RMOSDisplay() {

		super("RMOS");
		contentPane = getContentPane();
		contentPane.setBackground(Color.BLUE);
		Border outline1 = BorderFactory.createEmptyBorder(15, 15, 15, 15);
		((JComponent)contentPane).setBorder(outline1);
		//((JComponent)contentPane).setBorder(new EmptyBorder(15, 15, 15, 15));
		//Border outline = BorderFactory.createLineBorder(Color.RED);
        
		loadLoginPanel();
		loadOperationsPanel();
		loadOutputPanel();

		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2
				- getHeight() / 2);
		setSize(size.width, size.height);
		
		System.out.println("Width " + size.width + " Height " + size.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pack();
	    setLocationRelativeTo(null);
	    setVisible(true);
	}

	private void loadOutputPanel() {
		/* Output Panel */
		panel3 = new JPanel(new GridLayout(1, 0));
		panel3.setBackground(new Color(204, 204, 0));
		panel3.setPreferredSize(new Dimension(1200, 200));
		panel3.setBorder(new TitledBorder(new EtchedBorder(), "Output Screen"));
		contentPane.add(panel3, BorderLayout.SOUTH);
		panel3.setLayout(null);

		textDisplayOutput = new JTextArea();
		textDisplayOutput.setBounds(6, 26, 1188, 234);
		textDisplayOutput.setEditable(false);
		textDisplayOutput.setBackground(new Color(204, 204, 0));
		textDisplayOutput.setForeground(new Color(0, 51, 255));
		textDisplayOutput.setFont(new Font("Arial", Font.BOLD, 14));
		panel3.add(textDisplayOutput);

		/****** End of Output Panel ******/
	}

	private void loadRMOsUsageManagerPanel() {
		/****** RMOS Usage Manager Panel ******/
		JPanel controlPanel4;

		controlPanel4 = new JPanel();
		controlPanel4.setBackground(Color.ORANGE);
		controlPanel4.setPreferredSize(new Dimension(600, 355));
		controlPanel4.setBorder(new TitledBorder(new EtchedBorder(),
				"RMOS Usage Manager"));
		panel2.add(controlPanel4);
		
		// MostUsed Button
		mostUsed = new JButton("Most Used RCM");
		mostUsed.setForeground(new Color(50, 0, 200));
		mostUsed.setFont(new Font("Arial", Font.BOLD, 14));
		mostUsed.setBounds(8, 24, 180, 22);
		mostUsed.addActionListener(this);
		controlPanel4.add(mostUsed);	
		//controlPanel4.setLayout(null);
		
		// MonthlyUsageGraph Button
		monthlyUsageGraph = new JButton("Monthly Usage Graph");
		monthlyUsageGraph.setForeground(new Color(50, 0, 200));
		monthlyUsageGraph.setFont(new Font("Arial", Font.BOLD, 14));
		monthlyUsageGraph.setBounds(222, 24, 240, 22);
		monthlyUsageGraph.addActionListener(this);
		controlPanel4.add(monthlyUsageGraph);	
		//controlPanel4.setLayout(null);

		/****** End of RMOS Usage Manager ******/

	}

	private void loadRCMMonitorPanel() {

		/****** RCM Monitor Panel ******/
		JPanel controlPanel3;

		controlPanel3 = new JPanel();
		controlPanel3.setBackground(Color.ORANGE);
		controlPanel3.setPreferredSize(new Dimension(600, 355));
		controlPanel3.setBorder(new TitledBorder(new EtchedBorder(),
				"RCM Monitor"));

		JLabel labelRcmList = new JLabel("RCM List");
		labelRcmList.setFont(new Font("Arial", Font.BOLD, 14));
		labelRcmList.setForeground(new Color(30, 0, 200));
		labelRcmList.setBackground(new Color(0, 204, 153));
		labelRcmList.setBounds(12, 24, 70, 22);
		controlPanel3.add(labelRcmList);
		controlPanel3.setLayout(null);

		comboRcmList = createComboBox();
		comboRcmList.setBounds(75, 24, 90, 22);
		controlPanel3.add(comboRcmList);
		controlPanel3.setLayout(null);

		rcmPlaceLabel = new JLabel();
		rcmPlaceLabel.setForeground(new Color(30, 0, 200));
		rcmPlaceLabel.setFont(new Font("Arial", Font.BOLD, 14));
		rcmPlaceLabel.setBounds(170, 24, 60, 22);
		controlPanel3.add(rcmPlaceLabel);

		/*JLabel labelRcmNumber = new JLabel("RCM Number");
		labelRcmNumber.setForeground(new Color(30, 0, 200));
		labelRcmNumber.setFont(new Font("Arial", Font.BOLD, 14));
		labelRcmNumber.setBounds(362, 24, 90, 22);
		controlPanel3.add(labelRcmNumber);

		textRcmNumber = new JTextField();
		textRcmNumber.setBounds(460, 24, 100, 22);
		controlPanel3.add(textRcmNumber);
		controlPanel3.setLayout(null); */

		JLabel labelRcmLoc = new JLabel("Location");
		labelRcmLoc.setFont(new Font("Arial", Font.BOLD, 14));
		labelRcmLoc.setForeground(new Color(30, 0, 200));
		labelRcmLoc.setBackground(new Color(0, 204, 153));
		labelRcmLoc.setBounds(385, 24, 70, 22);
		controlPanel3.add(labelRcmLoc);

		textRcmLoc = new JTextField();
		textRcmLoc.setBounds(465, 22,100, 26);
		controlPanel3.add(textRcmLoc);

		JLabel labelRcm = new JLabel("RCM ID");
		labelRcm.setFont(new Font("Arial", Font.BOLD, 14));
		labelRcm.setForeground(new Color(30, 0, 200));
		labelRcm.setBackground(new Color(0, 204, 153));
		labelRcm.setBounds(240, 24, 50, 22);
		controlPanel3.add(labelRcm);
		controlPanel3.setLayout(null);

		textRcm = new JTextField();
		textRcm.setBounds(300, 22, 70, 26);
		controlPanel3.add(textRcm);

		/*JLabel rcmStatusLabel = new JLabel("Status");
		rcmStatusLabel.setFont(new Font("Arial", Font.BOLD, 14));
		rcmStatusLabel.setForeground(new Color(30, 0, 200));
		rcmStatusLabel.setBackground(new Color(0, 204, 153));
		rcmStatusLabel.setBounds(362, 60, 96, 22);
		controlPanel3.add(rcmStatusLabel);
		controlPanel3.setLayout(null);

		textRcmStatus = new JTextField();
		textRcmStatus.setBounds(460, 58, 100, 26);
		controlPanel3.add(textRcmStatus);*/
		
		btnRcmStatus = new JButton("RCM Status ");
		btnRcmStatus.setForeground(new Color(50, 0, 200));
		btnRcmStatus.setFont(new Font("Arial", Font.BOLD, 14));
		btnRcmStatus.setBounds(450, 56, 120, 26);
		btnRcmStatus.addActionListener(this);
		controlPanel3.add(btnRcmStatus);

		// CurrentWeight Button
		currentWeight = new JButton("Current Weight ");
		currentWeight.setForeground(new Color(50, 0, 200));
		currentWeight.setFont(new Font("Arial", Font.BOLD, 14));
		currentWeight.setBounds(12, 56, 135, 26);
		currentWeight.addActionListener(this);
		controlPanel3.add(currentWeight);

		// CurrentCash Button
		currentCash = new JButton("Current Cash ");
		currentCash.setForeground(new Color(50, 0, 200));
		currentCash.setFont(new Font("Arial", Font.BOLD, 14));
		currentCash.setBounds(154, 56, 125, 26);
		currentCash.addActionListener(this);
		controlPanel3.add(currentCash);

		// CurrentCoupon Button
		currentCoupon = new JButton("Current Coupon ");
		currentCoupon.setForeground(new Color(50, 0, 200));
		currentCoupon.setFont(new Font("Arial", Font.BOLD, 14));
		currentCoupon.setBounds(290, 56, 140, 26);
		currentCoupon.addActionListener(this);
		controlPanel3.add(currentCoupon);

		// TimeLastEmptied Button
		btnLastEmptied = new JButton("Time Last Emptied");
		btnLastEmptied.setForeground(new Color(50, 0, 200));
		btnLastEmptied.setFont(new Font("Arial", Font.BOLD, 14));
		btnLastEmptied.setBounds(12, 100, 280, 30);
		btnLastEmptied.addActionListener(this);
		controlPanel3.add(btnLastEmptied);

		// MonthlyTransactions Button
		btnMonthlyTransactions = new JButton("Monthly Transactions");
		btnMonthlyTransactions.setForeground(new Color(50, 0, 200));
		btnMonthlyTransactions.setFont(new Font("Arial", Font.BOLD, 14));
		btnMonthlyTransactions.setBounds(300, 100, 280, 30);
		btnMonthlyTransactions.addActionListener(this);
		controlPanel3.add(btnMonthlyTransactions);

		// CashDebitedPerMonth Button
		btnCashDebitedPerMonth = new JButton("Cash Debited per Month");
		btnCashDebitedPerMonth.setForeground(new Color(50, 0, 200));
		btnCashDebitedPerMonth.setFont(new Font("Arial", Font.BOLD, 14));
		btnCashDebitedPerMonth.setBounds(12, 145, 280, 30);
		btnCashDebitedPerMonth.addActionListener(this);
		controlPanel3.add(btnCashDebitedPerMonth);

		// WeightAccumulatedPerMonth Button
		btnWeightPerMonth = new JButton("Weight Accumulated per Mounth");
		btnWeightPerMonth.setForeground(new Color(50, 0, 200));
		btnWeightPerMonth.setFont(new Font("Arial", Font.BOLD, 14));
		btnWeightPerMonth.setBounds(300, 145, 280, 30);
		btnWeightPerMonth.addActionListener(this);
		controlPanel3.add(btnWeightPerMonth);

		// CurrItemsRecycled Button
		btnCurrItemsRecycled = new JButton("Current Number of Items Recycled");
		btnCurrItemsRecycled.setForeground(new Color(50, 0, 200));
		btnCurrItemsRecycled.setFont(new Font("Arial", Font.BOLD, 14));
		btnCurrItemsRecycled.setBounds(12, 190, 280, 30);
		btnCurrItemsRecycled.addActionListener(this);
		controlPanel3.add(btnCurrItemsRecycled);

		// ItemsRecycledByMonth Button
		btnItemsRecycledByMonth = new JButton("Num of Items Recycled per Month");
		btnItemsRecycledByMonth.setForeground(new Color(50, 0, 200));
		btnItemsRecycledByMonth.setFont(new Font("Arial", Font.BOLD, 14));
		btnItemsRecycledByMonth.setBounds(300, 190, 280, 30);
		controlPanel3.add(btnItemsRecycledByMonth);
		btnItemsRecycledByMonth.addActionListener(this);
		panel2.add(controlPanel3);

		/****** End of RCM Monitor ******/

	}

	private void loadRecycleItemPanel() {
		/****** ITEM Daemon Panel ******/

		JPanel controlPanel2;
		controlPanel2 = new JPanel();
		controlPanel2.setBackground(Color.ORANGE);
		controlPanel2.setPreferredSize(new Dimension(600, 245));
		controlPanel2.setBorder(new TitledBorder(new EtchedBorder(),
				"Recycle Item "));
		panel2.add(controlPanel2);
		
		JLabel labelItemType = new JLabel("Item Type");
		labelItemType.setFont(new Font("Arial", Font.BOLD, 14));
		labelItemType.setForeground(new Color(30, 0, 200));
		labelItemType.setBackground(new Color(0, 204, 153));
		labelItemType.setBounds(16, 24, 90, 22);
		controlPanel2.add(labelItemType);
		controlPanel2.setLayout(null);

		textItemType = new JTextField();
		textItemType.setBounds(134, 24, 100, 22);
		controlPanel2.add(textItemType);

        JLabel labelUnitPrice = new JLabel("Unit Price");
		labelUnitPrice.setForeground(new Color(30, 0, 200));
		labelUnitPrice.setFont(new Font("Arial", Font.BOLD, 14));
		labelUnitPrice.setBounds(362, 24, 90, 22);
		controlPanel2.add(labelUnitPrice);

		textUnitPrice = new JTextField();
		textUnitPrice.setBounds(460, 24, 100, 22);
		controlPanel2.add(textUnitPrice);
		controlPanel2.setLayout(null);

		// AddITEM Button
		addItem = new JButton("Add ITEM");
		addItem.setForeground(new Color(50, 0, 200));
		addItem.setFont(new Font("Arial", Font.BOLD, 14));
		addItem.setBounds(16, 66, 100, 26);
		addItem.addActionListener(this);
		controlPanel2.add(addItem);

		// RemoveITEM Button
		removeItem = new JButton("Remove ITEM ");
		removeItem.setForeground(new Color(50, 0, 200));
		removeItem.setFont(new Font("Arial", Font.BOLD, 14));
		removeItem.setBounds(134, 66, 130, 26);
		removeItem.addActionListener(this);
		controlPanel2.add(removeItem );

		// UpdatePRICE Button
		updatePrice = new JButton("Update PRICE ");
		updatePrice.setForeground(new Color(50, 0, 200));
		updatePrice.setFont(new Font("Arial", Font.BOLD, 14));
		updatePrice.setBounds(300, 66, 130, 26);
		updatePrice.addActionListener(this);
		controlPanel2.add(updatePrice);

		// ListItem Button
		listItem = new JButton("List ITEM ");
		listItem.setForeground(new Color(50, 0, 200));
		listItem.setFont(new Font("Arial", Font.BOLD, 14));
		listItem.setBounds(460, 66, 110, 26);
		listItem.addActionListener(this);
		controlPanel2.add(listItem);
		
		/****** End of ITEM Daemon ******/
	}

	private void loadRMOSManagerPanel() {
		JPanel controlPanel1;
		/****** RMOS Manager Panel ******/
		controlPanel1 = new JPanel();
		controlPanel1.setBackground(Color.ORANGE);
		controlPanel1.setPreferredSize(new Dimension(600, 245));
		controlPanel1.setBorder(new TitledBorder(new EtchedBorder(),
				"RMOS Manager"));
		panel2.add(controlPanel1);

		JLabel labelRcmId = new JLabel("RCM ID");
		labelRcmId.setFont(new Font("Arial", Font.BOLD, 14));
		labelRcmId.setForeground(new Color(30, 0, 200));
		labelRcmId.setBackground(new Color(0, 204, 153));
		labelRcmId.setBounds(12, 24, 50, 22);
		controlPanel1.add(labelRcmId);
		controlPanel1.setLayout(null);

		textRcmId = new JTextField();
		textRcmId.setBounds(70, 22, 50, 26);
		controlPanel1.add(textRcmId);

		JLabel labelRefillAmount = new JLabel("Refill Amount");
		labelRefillAmount.setFont(new Font("Arial", Font.BOLD, 14));
		labelRefillAmount.setForeground(new Color(30, 0, 200));
		labelRefillAmount.setBackground(new Color(0, 204, 153));
		labelRefillAmount.setBounds(148, 24, 110, 22);
		controlPanel1.add(labelRefillAmount);
		controlPanel1.setLayout(null);

		textRefillAmount = new JTextField();
		textRefillAmount.setBounds(265, 22, 70, 26);
		controlPanel1.add(textRefillAmount);

		JLabel labelRcmLocation = new JLabel("RCM Location");
		labelRcmLocation.setFont(new Font("Arial", Font.BOLD, 14));
		labelRcmLocation.setForeground(new Color(30, 0, 200));
		labelRcmLocation.setBackground(new Color(0, 204, 153));
		labelRcmLocation.setBounds(350, 24, 100, 22);
		controlPanel1.add(labelRcmLocation);
		controlPanel1.setLayout(null);

		textRcmLocation = new JTextField();
		textRcmLocation.setBounds(470, 22, 100, 26);
		controlPanel1.add(textRcmLocation);

		// AddRCM Button
		addRCM = new JButton("Add RCM");
		addRCM.setForeground(new Color(50, 0, 200));
		addRCM.setFont(new Font("Arial", Font.BOLD, 14));
		addRCM.setBounds(16, 66, 110, 26);
		addRCM.addActionListener(this);
		controlPanel1.add(addRCM);

		// RemoveRCM Button
		removeRCM = new JButton("Remove RCM ");
		removeRCM.setForeground(new Color(50, 0, 200));
		removeRCM.setFont(new Font("Arial", Font.BOLD, 14));
		removeRCM.setBounds(144, 66, 130, 26);
		removeRCM.addActionListener(this);
		controlPanel1.add(removeRCM);

		// ActivateRCM Button
		activateRCM = new JButton("Activate RCM ");
		activateRCM.setForeground(new Color(50, 0, 200));
		activateRCM.setFont(new Font("Arial", Font.BOLD, 14));
		activateRCM.setBounds(300, 66, 130, 26);
		activateRCM.addActionListener(this);
		controlPanel1.add(activateRCM);
		
		// DeactivateRCM Button
		deactivateRCM = new JButton("Deactivate RCM ");
		deactivateRCM.setForeground(new Color(50, 0, 200));
		deactivateRCM.setFont(new Font("Arial", Font.BOLD, 14));
		deactivateRCM.setBounds(460, 66, 140, 26);
		deactivateRCM.addActionListener(this);
		controlPanel1.add(deactivateRCM);

		// RefillFunds Button
		refillRCM = new JButton("Refill Funds ");
		refillRCM.setForeground(new Color(50, 0, 200));
		refillRCM.setFont(new Font("Arial", Font.BOLD, 14));
		refillRCM.addActionListener(this);
		refillRCM.setBounds(16, 100, 120, 26);
		controlPanel1.add(refillRCM);

		// ClearRCM Button
		clearRCM = new JButton("Vacate Machine ");
		clearRCM.setForeground(new Color(50, 0, 200));
		clearRCM.setFont(new Font("Arial", Font.BOLD, 14));
		clearRCM.setBounds(145, 100, 140, 26);
		clearRCM.addActionListener(this);
		controlPanel1.add(clearRCM);
		// clearRCM.addActionListener((ActionListener) this);

		// ShowRCM Button
		showRCM = new JButton("Display RCM List ");
		showRCM.setForeground(new Color(50, 0, 200));
		showRCM.setFont(new Font("Arial", Font.BOLD, 14));
		showRCM.setBounds(295, 100, 180, 26);
		showRCM.addActionListener(this);
		controlPanel1.add(showRCM);

		/* End of RMOS Manager Panel */

	}

	private void loadOperationsPanel() {
		/****** Operations Panel ******/
		panel2 = new JPanel(new GridLayout(1, 0));
		panel2.setBackground(new Color(153, 255, 153));
		panel2.setPreferredSize(new Dimension(1200, 600));
		panel2.setLayout(new GridLayout(2, 2));
		loadRMOSManagerPanel();
		loadRecycleItemPanel();
		loadRCMMonitorPanel();
		loadRMOsUsageManagerPanel();

		contentPane.add(panel2, BorderLayout.CENTER);

		/****** End of Operations Panel ******/
	}

	private void loadLoginPanel() {
		/****** Login Panel ******/
		panel1 = new JPanel(new GridLayout(1, 0));
		panel1.setBackground(new Color(204, 204, 0));
		panel1.setPreferredSize(new Dimension(1200, 60));
		panel1.setBorder(new TitledBorder(new EtchedBorder(), "Admin Login"));
		contentPane.add(panel1, BorderLayout.NORTH);

		JLabel labelUsername = new JLabel("UserName");
		labelUsername.setFont(new Font("Arial", Font.BOLD, 14));
		labelUsername.setForeground(new Color(30, 0, 200));
		labelUsername.setBounds(50, 25, 95, 22);
		panel1.add(labelUsername);
		panel1.setLayout(null);

		textUsername = new JTextField();
		textUsername.setBounds(140, 20, 200, 28);
		panel1.add(textUsername);
		textUsername.setColumns(10);

		JLabel labelPassword = new JLabel("Password");
		labelPassword.setFont(new Font("Arial", Font.BOLD, 14));
		labelPassword.setForeground(new Color(30, 0, 200));
		labelPassword.setBounds(470, 25, 275, 22);
		panel1.add(labelPassword);

		password = new JPasswordField();
		password.setBounds(570, 20, 200, 28);
		panel1.add(password);

		buttonLogin = new JButton("Login");
		buttonLogin.setForeground(new Color(200, 0, 0));
		buttonLogin.setFont(new Font("Arial Black", Font.BOLD, 15));
		buttonLogin.setBounds(900, 19, 150, 29);
		buttonLogin.addActionListener(this);
		panel1.add(buttonLogin);

		errorDisplayOutput = new JTextArea();
		errorDisplayOutput.setBounds(900, 19, 150, 29);
		errorDisplayOutput.setEditable(false);
		errorDisplayOutput.setBackground(new Color(204, 204, 0));
		errorDisplayOutput.setForeground(new Color(0, 51, 255));
		errorDisplayOutput.setFont(new Font("Arial", Font.BOLD, 14));
		panel1.add(errorDisplayOutput);
		/* End of Login Panel */
	}

	/* Part of RCM Monitor Operations Panel */
	public JComboBox createComboBox() {
		rcmIds = rcmAction.getAllRcmId();
		model = new DefaultComboBoxModel(rcmIds.toArray());
		rcmCombo = new JComboBox(model);
		rcmCombo.setForeground(Color.BLACK);
		rcmCombo.setFont(new Font("Arial", Font.BOLD, 10));

		// add the event handler, anonymous handler is used
		rcmCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String rname = (String) cb.getSelectedItem();
				rcmPlaceLabel.setText(rname);
			}
		});
		return rcmCombo;
	}

	/* End of RCM Monitor Operations Panel */

	// *********************************************************************************************************
	@Override
	public void actionPerformed(ActionEvent e) {

		// store command from event e
		Object source = e.getSource();

		// *********** Admin Log in **************
		if (source == buttonLogin) {
			String userName = textUsername.getText();
			String passWord = "";

			char[] passwd = password.getPassword();
			passWord = String.valueOf(passwd);

			String canLogIn = "";
			LoginAction loginAction = new LoginAction();
			loginAction.setPassword(passWord);
			loginAction.setUsername(userName);
			canLogIn = loginAction.execute();
			textDisplayOutput.setText(loginAction.getMessage());
		}

		/********* Start of RMOS Manager Activities **************/

		// *********** Admin add RCM **************
		else if (source == addRCM) {
			String msg = rcmAction.addRCM(textRcmLocation.getText());
			textDisplayOutput.setText(msg);
		}
		// ********* Remove a RCM from RMOS group *********
		else if (source == removeRCM) {
			String msg = rcmAction.removeRCM(textRcmId.getText());
			textDisplayOutput.setText(msg);
		}
		// ********** Activate a RCM ****************
		else if (source == activateRCM) {
			String msg = rcmAction.activateRCM(textRcmId.getText());
			textDisplayOutput.setText(msg);
		}
		// ********** Deactivate a RCM ****************
		else if (source == deactivateRCM) {
			String msg = rcmAction.deActivateRCM(textRcmId.getText());
			textDisplayOutput.setText(msg);
		}
		
		// *********** Refill Money ******************
		else if (source == refillRCM) {
			//int amount = 1000;
			int amount =Integer.parseInt(textRefillAmount.getText());
			String msg = rcmAction.refillRCM(textRcmId.getText(),amount );
			textDisplayOutput.setText(msg);
		}
		// *********** Clear Recycale item from RCM ******************
		else if (source == clearRCM) {
			String msg = rcmAction.clearRcmWeight(textRcmId.getText());
			textDisplayOutput.setText(msg);
		}
		// *********** Showlist of RCM under an RMOS******************
		else if (source == showRCM) {
			rcmIds = rcmAction.getAllRcmId();
			model.removeAllElements();
			model.addElement(rcmIds.toArray());
			textDisplayOutput.setText("RCM List is updated");
		}
		/* ********* End of RMOS Manager Activities ***** */

		/********* Start of RCM Monitor Activities **************/

		else if (source == currentWeight) {
			String msg = rcmAction.currentWeight(textRcm.getText());
			textDisplayOutput.setText(msg);
		}

		// *********** Display current cash in RCM ******************
		else if (source == currentCash) {
			rcmMonitor.getCurrentCash();
			textDisplayOutput.setText("Current amount for Cash in RCM is");
		}
		
		// *********** Display current coupon in RCM ******************
		else if (source == currentCoupon) {
			rcmMonitor.getCurrentCash();
			textDisplayOutput.setText("Current amount for coupon in RCM is");
		}
		// *********** Display No of times RCM was operated in a month
		// ******************
		else if (source == btnMonthlyTransactions) {
			rcmMonitor.getMonthlyTransactions();
			textDisplayOutput.setText("No of times RCM was operated in a month is ");
		}
		// *********** Display current status of RCM ******************
		else if (source == btnRcmStatus) 
		{
			rcmMonitor.getStatus();
			textDisplayOutput.setText("Current status of RCM is");
		}
		// *********** Display No of times RCM was operated in a month
		// ******************
		else if (source == btnMonthlyTransactions) {
			rcmMonitor.getMonthlyTransactions();
			textDisplayOutput
					.setText("No of times RCM was operated in a month is ");

		}

		// *********** Display the time when RCM was last Emptied
		// ******************
		else if (source == btnLastEmptied) {
			rcmMonitor.getLastEmptied();
			textDisplayOutput.setText("Time when RCM was last emptied ");

		}

		// *********** Display the amount of money issued by RCM in a month
		// ******************
		else if (source == btnCashDebitedPerMonth) {
			rcmMonitor.getCashDebitedPerMonth();
			textDisplayOutput
					.setText("The amount of money issued by RCM in a month is ");

		}

		// *********** Display the total volume of item recycled by RCM in a
		// month ******************
		else if (source == btnWeightPerMonth) {
			rcmMonitor.getWeightPerMonth();
			textDisplayOutput
					.setText("The total volume of item recycled RCM in a month is ");

		}

		// *********** Display Number of item recycled by RCM at present
		// ******************
		else if (source == btnCurrItemsRecycled) {
			rcmMonitor.getCurrItemsRecycled();
			textDisplayOutput
					.setText("Number of item recycled by RCM at present is ");

		}

		// *********** Display Number of item recycled by RCM in a month
		// ******************
		else if (source == btnItemsRecycledByMonth) {
			rcmMonitor.getItemsRecycledPerMonth();
			textDisplayOutput
					.setText("Number of item recycled by RCM in a month is ");

		}
		// *********** Adds new Item in RCM
				// ******************
		else if (source == addItem) {
			// get Item Type
			String itemType = textItemType.getText();
			System.out.println("itemType " + itemType);
			int temp = recycleItem.addItem(itemType);

			//logic.activateRCM(temp);
			textDisplayOutput.setText("Item Successfully Added");
		}
		// *********** Removes Item from RCM
		// ******************
		else if (source == removeItem) {
			// get Item Type
			String itemType = textItemType.getText();
			System.out.println("itemType " + itemType);
			String temp = recycleItem.removeItem(itemType);

			//logic.activateRCM(temp);
			textDisplayOutput.setText("Item Removed Added");
		}
		// *********** Removes Item from RCM
				// ******************
		else if (source == updatePrice) {
					// get Item Type
			int uPrice = Integer.parseInt(textUnitPrice.getText());
			String itemType = textItemType.getText();
			System.out.println("itemType " + itemType + "itemPrice " +uPrice);
			String temp = recycleItem.updatePrice(itemType,uPrice);

			logic.updatePrice(temp);
			textDisplayOutput.setText("Item Price Updated");
		}
			
			// *********** Show list of Recycable Items ******************
		else if (source == listItem) {
			recycleItem.getItemList();
			logic.getItemList();
			textDisplayOutput.setText("List of Recycable Items are");
		}

			// *********** Show Most Used RCM  ******************
		else if (source == mostUsed) {
			rmosUsageManager.getMostUsedRCM();
			logic.getItemList();
			textDisplayOutput.setText("Most Used RCM is ");
		}

			// *********** Show Monthly Usage Statistics of RCMs ******************
		else if (source == monthlyUsageGraph) {
			rmosUsageManager.getMonthlyUsageGraph();
			logic.getItemList();
			textDisplayOutput.setText("Monthly Usage Statistics are ");
		}	

	}// end of actionPerform

}
