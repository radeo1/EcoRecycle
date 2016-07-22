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
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.project.EcoRe.Constant;
import com.project.EcoRe.RecycleItem;
import com.project.actions.LoginAction;
import com.project.actions.RCMAction;
import com.project.actions.RMOSAction;
import com.project.actions.RMOSUsageManager;
import com.project.dbsql.SelectQueries;
import com.project.logic.BackendLogic;
import com.project.logic.Validation;

public class RMOSDisplay extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1;

	private RMOSUsageManager rmosUsageManager = new RMOSUsageManager();
	
	private RecycleItem recycleItem = new RecycleItem();
        
        
	private Container contentPane;
	private JPanel panel1, panel2, panel3, cpanel1, cpanel2;

	private JTextField textUsername, textRcmId, textRefillAmount,
			textRcmLocation;
	private JTextField textRcm, textRcmLoc, textItemType, textUnitPrice;
	private JPasswordField password;
	private JButton buttonLogin, addRCM, removeRCM, activateRCM, refillFunds,refillCoupon,
			deactivateRCM, clearRCM, showRCM, currentWeight, currentCash,
			currentCoupon, btnLastEmptied, btnMonthlyTransactions,btnRcmLocation,
			btnCashDebitedPerMonth, btnWeightPerMonth, btnRcmStatus,
			btnCurrItemsRecycled, btnItemsRecycledByMonth, addItem, removeItem,
			updatePrice, listItem, mostUsed, monthlyUsageGraph,btnShowGraph, btnMonthlyUsageTimes,
                        btnMonthlyWeightStatistics;
	private JLabel rcmPlaceLabel, rmosPlaceLabel;
	private JComboBox rcmCombo, comboRcmList, rmosCombo;
	static JTextArea textDisplayOutput;
	static JTextArea errorDisplayOutput;
	BackendLogic logic = new BackendLogic();
	RCMAction rcmAction = new RCMAction();
	RMOSAction rmosAction = new RMOSAction();
	List<String> rcmIds = new ArrayList<String>();
	List<String> rmosIds = new ArrayList<String>();
	DefaultComboBoxModel model = null;
	DefaultComboBoxModel modelForRmos = null;
	Vending v=new Vending("RCM");

	public RMOSDisplay() {

		super("RMOS");
		contentPane = getContentPane();
		contentPane.setBackground(Color.BLUE);
		Border outline1 = BorderFactory.createEmptyBorder(15, 15, 15, 15);
		((JComponent) contentPane).setBorder(outline1);
		// ((JComponent)contentPane).setBorder(new EmptyBorder(15, 15, 15, 15));
		// Border outline = BorderFactory.createLineBorder(Color.RED);

		loadLoginPanel();
		loadOperationsPanel();
		loadOutputPanel();

		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2
				- getHeight() / 2);
		setSize(size.width/2, size.height/2);

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
		controlPanel4.setPreferredSize(new Dimension(600, 155));
		controlPanel4.setBorder(new TitledBorder(new EtchedBorder(),
				"RMOS Usage Manager"));
		panel2.add(controlPanel4);

		// MostUsed Button
		mostUsed = new JButton("Most Used RCM");
		mostUsed.setForeground(new Color(50, 0, 200));
		mostUsed.setFont(new Font("Arial", Font.BOLD, 14));
		//mostUsed.setBounds(8, 24, 180, 22);
                mostUsed.setBounds(288, 37, 226, 45);
               
		mostUsed.addActionListener(this);
		controlPanel4.add(mostUsed);
		// controlPanel4.setLayout(null);


                btnShowGraph = new JButton("Current weight in each RCM");
		btnShowGraph.setFont(new Font("Arial", Font.BOLD, 14));
		btnShowGraph.setForeground(new Color(50, 0, 200));
		btnShowGraph.setBounds(288, 97, 226, 45);
		btnShowGraph.addActionListener((ActionListener) this);
		controlPanel4.add(btnShowGraph);

		btnMonthlyUsageTimes = new JButton("Current Cash Available in each RCM");
		btnMonthlyUsageTimes.setFont(new Font("Arial", Font.BOLD, 14));
		btnMonthlyUsageTimes.setForeground(new Color(50, 0, 200));
		btnMonthlyUsageTimes.setBounds(288, 201, 226, 45);
		btnMonthlyUsageTimes.addActionListener((ActionListener) this);
		controlPanel4.add(btnMonthlyUsageTimes);

		btnMonthlyWeightStatistics = new JButton("Current Coupon Available in each RCM");
		btnMonthlyWeightStatistics.setFont(new Font("Arial", Font.BOLD, 14));
		btnMonthlyWeightStatistics.setForeground(new Color(50, 0, 200));
		btnMonthlyWeightStatistics.setBounds(288, 149, 226, 45);
		btnMonthlyWeightStatistics.addActionListener((ActionListener) this);
		controlPanel4.add(btnMonthlyWeightStatistics);
		/****** End of RMOS Usage Manager ******/

	}

	private void loadRCMMonitorPanel() {

		/****** RCM Monitor Panel ******/
		JPanel controlPanel3;

		controlPanel3 = new JPanel();
		controlPanel3.setBackground(Color.ORANGE);
		controlPanel3.setPreferredSize(new Dimension(600, 300));
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

		btnRcmLocation = new JButton("RCM Location ");
		btnRcmLocation.setForeground(new Color(50, 0, 200));
		btnRcmLocation.setFont(new Font("Arial", Font.BOLD, 14));
		btnRcmLocation.setBounds(400, 22, 140, 26);
		btnRcmLocation.addActionListener(this);
		controlPanel3.add(btnRcmLocation);


		/*JLabel labelRcm = new JLabel("RCM ID");
		labelRcm.setFont(new Font("Arial", Font.BOLD, 14));
		labelRcm.setForeground(new Color(30, 0, 200));
		labelRcm.setBackground(new Color(0, 204, 153));
		labelRcm.setBounds(240, 24, 50, 22);
		controlPanel3.add(labelRcm);
		controlPanel3.setLayout(null);

		textRcm = new JTextField();
		textRcm.setBounds(300, 22, 70, 26);
		controlPanel3.add(textRcm);*/

		

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
		btnWeightPerMonth = new JButton("Weight Accumulated per Month");
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
		controlPanel2.setPreferredSize(new Dimension(600, 145));
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
		controlPanel2.add(removeItem);

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
		controlPanel1.setPreferredSize(new Dimension(600, 205));
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
		labelRefillAmount.setBounds(350, 24, 100, 22);
		controlPanel1.add(labelRefillAmount);
		controlPanel1.setLayout(null);

		textRefillAmount = new JTextField();
		textRefillAmount.setBounds(470, 22, 100, 26);
		controlPanel1.add(textRefillAmount);

		JLabel labelRcmLocation = new JLabel("RCM Location");
		labelRcmLocation.setFont(new Font("Arial", Font.BOLD, 14));
		labelRcmLocation.setForeground(new Color(30, 0, 200));
		labelRcmLocation.setBackground(new Color(0, 204, 153));
		labelRcmLocation.setBounds(148, 24, 110, 22);
		controlPanel1.add(labelRcmLocation);
		controlPanel1.setLayout(null);

		textRcmLocation = new JTextField();
		textRcmLocation.setBounds(265, 22, 70, 26);
				
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
		activateRCM.setBounds(280, 66, 125, 26);
		activateRCM.addActionListener(this);
		controlPanel1.add(activateRCM);

		// DeactivateRCM Button
		deactivateRCM = new JButton("Deactivate RCM ");
		deactivateRCM.setForeground(new Color(50, 0, 200));
		deactivateRCM.setFont(new Font("Arial", Font.BOLD, 14));
		deactivateRCM.setBounds(440, 66, 140, 26);
		deactivateRCM.addActionListener(this);
		controlPanel1.add(deactivateRCM);

		// RefillFunds Button
		refillFunds = new JButton("Refill Funds ");
		refillFunds.setForeground(new Color(50, 0, 200));
		refillFunds.setFont(new Font("Arial", Font.BOLD, 14));
		refillFunds.addActionListener(this);
		refillFunds.setBounds(16, 100, 120, 26);
		controlPanel1.add(refillFunds);
		
		refillCoupon = new JButton("Refill Coupon");
		refillCoupon.setForeground(new Color(50, 0, 200));
		refillCoupon.setFont(new Font("Arial", Font.BOLD, 14));
		refillCoupon.setBounds(145, 100, 140, 26);
		refillCoupon.addActionListener(this);
		controlPanel1.add(refillCoupon);

		// ClearRCM Button
		clearRCM = new JButton("Vacate Machine ");
		clearRCM.setForeground(new Color(50, 0, 200));
		clearRCM.setFont(new Font("Arial", Font.BOLD, 14));
		clearRCM.setBounds(280, 100, 140, 26);
		clearRCM.addActionListener(this);
		controlPanel1.add(clearRCM);
		// clearRCM.addActionListener((ActionListener) this);

		// ShowRCM Button
		showRCM = new JButton("Update RCM List ");
		showRCM.setForeground(new Color(50, 0, 200));
		showRCM.setFont(new Font("Arial", Font.BOLD, 14));
		showRCM.setBounds(440, 100, 150, 26);
		showRCM.addActionListener(this);
		controlPanel1.add(showRCM);

		/* End of RMOS Manager Panel */

	}

	private void loadOperationsPanel() {
		/****** Operations Panel ******/
		panel2 = new JPanel(new GridLayout(1, 0));
		panel2.setBackground(new Color(153, 255, 153));
		panel2.setPreferredSize(new Dimension(1100, 500));
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
		panel1 = new JPanel(new GridLayout(1, 2));
		panel1.setBackground(new Color(204, 204, 0));
		panel1.setPreferredSize(new Dimension(1200, 60));
		// panel1.setBorder(new TitledBorder(new EtchedBorder(),
		// "Admin Login"));
		contentPane.add(panel1, BorderLayout.NORTH);

		cpanel1 = new JPanel();
		cpanel1.setBackground(new Color(204, 204, 0));
		cpanel1.setPreferredSize(new Dimension(600, 60));
		cpanel1.setBorder(new TitledBorder(new EtchedBorder(), "Admin Login"));
		panel1.add(cpanel1);

		cpanel2 = new JPanel();
		cpanel2.setBackground(new Color(204, 204, 0));
		cpanel2.setPreferredSize(new Dimension(600, 60));
		cpanel2.setBorder(new TitledBorder(new EtchedBorder(), "RMOS Panel"));
		panel1.add(cpanel2);

		JLabel labelUsername = new JLabel("UserName");
		labelUsername.setFont(new Font("Arial", Font.BOLD, 14));
		labelUsername.setForeground(new Color(30, 0, 200));
		labelUsername.setBounds(10, 25, 70, 22);
		cpanel1.add(labelUsername);
		cpanel1.setLayout(null);

		textUsername = new JTextField();
		textUsername.setBounds(90, 20, 100, 28);
		cpanel1.add(textUsername);
		textUsername.setColumns(10);

		JLabel labelPassword = new JLabel("Password");
		labelPassword.setFont(new Font("Arial", Font.BOLD, 14));
		labelPassword.setForeground(new Color(30, 0, 200));
		labelPassword.setBounds(220, 25, 100, 22);
		cpanel1.add(labelPassword);

		password = new JPasswordField();
		password.setBounds(290, 20, 100, 28);
		cpanel1.add(password);

		buttonLogin = new JButton("Login");
		buttonLogin.setForeground(new Color(200, 0, 0));
		buttonLogin.setFont(new Font("Arial Black", Font.BOLD, 15));
		buttonLogin.setBounds(440, 19, 90, 29);
		buttonLogin.addActionListener(this);
		cpanel1.add(buttonLogin);

		if (Validation.isNotNullorEmplty(textUsername.getText())) {
			rmosIds = rmosAction.getAllRmosId(textUsername.getText());
		} else {
			rmosIds = new ArrayList<String>();
		}
		modelForRmos = new DefaultComboBoxModel(rmosIds.toArray());
		rmosCombo = new JComboBox(modelForRmos);

		rmosCombo.setForeground(Color.BLACK);
		rmosCombo.setFont(new Font("Arial", Font.BOLD, 10));
		rmosCombo.setBounds(10, 24, 90, 22);
		cpanel2.add(rmosCombo);
		cpanel2.setLayout(null);
		rmosPlaceLabel = new JLabel();
		rmosPlaceLabel.setForeground(new Color(30, 0, 200));
		rmosPlaceLabel.setFont(new Font("Arial", Font.BOLD, 14));
		rmosPlaceLabel.setBounds(120, 24, 60, 22);
		cpanel2.add(rmosPlaceLabel);

		// add the event handler, anonymous handler is used
		rmosCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox c = (JComboBox) e.getSource();
				String rname = (String) c.getSelectedItem();
				rmosPlaceLabel.setText(rname);
			}
		});

		/* End of Login Panel */
	}

	/* Part of RCM Monitor Operations Panel */
	public JComboBox createComboBox() {
		rcmIds = rcmAction.getAllRcmId(rmosPlaceLabel.getText());
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

		if(source != buttonLogin){
			if(!Constant.SUCESS_LOGIN){
				textDisplayOutput.setText("Please Login");
				return;
			}
		}
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
			if ("success".equals(canLogIn)) {
				rmosIds = rmosAction.getAllRmosId(loginAction.getUsername());//load rmosid
				modelForRmos.removeAllElements();
				for (String rmoId : rmosIds) {
					modelForRmos.addElement(rmoId);
				}
			}
			textDisplayOutput.setText(loginAction.getMessage());
		}

		/********* Start of RMOS Manager Activities **************/

		// *********** Admin add RCM **************
		else if (source == addRCM) {
			String msg = rcmAction.addRCM(textRcmLocation.getText(), rmosPlaceLabel.getText());
			textDisplayOutput.setText(msg);
		}
		// ********* Remove a RCM from RMOS group *********
		else if (source == removeRCM) {
			String msg = rcmAction.removeRCM(textRcmId.getText(), rmosPlaceLabel.getText());
			textDisplayOutput.setText(msg);
		}
		// ********** Activate a RCM ****************
		else if (source == activateRCM) {
			String msg = rcmAction.activateRCM(textRcmId.getText(), rmosPlaceLabel.getText());
			textDisplayOutput.setText(msg);
			//v.setRcmselect(v.createComboBox());
			//vmodel.setModel( v.rcmList );
			//v.rcmselect.setModel( model );
		}
		// ********** Deactivate a RCM ****************
		else if (source == deactivateRCM) {
			String msg = rcmAction.deActivateRCM(textRcmId.getText(), rmosPlaceLabel.getText());
			textDisplayOutput.setText(msg);
		}

		// *********** Refill Money ******************
		else if (source == refillFunds) {
			String msg = rcmAction.refillRCM(textRcmId.getText(),
					textRefillAmount.getText(), rmosPlaceLabel.getText());
			textDisplayOutput.setText(msg);
		}
		// *********** Refill coupon ******************
		else if (source == refillCoupon) {
			String msg = rcmAction.refillRCMCoupon(textRcmId.getText(),
					textRefillAmount.getText(), rmosPlaceLabel.getText());
			textDisplayOutput.setText(msg);
			 
		}
		// *********** Clear Recycale item from RCM ******************
		else if (source == clearRCM) {
			String msg = rcmAction.clearRcmWeight(textRcmId.getText(), rmosPlaceLabel.getText());
			textDisplayOutput.setText(msg);
		}
		// *********** Showlist of RCM under an RMOS******************
		else if (source == showRCM) {//displayrcm
			rcmIds = rcmAction.getAllRcmId(rmosPlaceLabel.getText());
			model.removeAllElements();
			for (String rcmid : rcmIds) {
				model.addElement(rcmid);
			}

			textDisplayOutput.setText("RCM List is updated");
		}
		/* ********* End of RMOS Manager Activities ***** */

		/********* Start of RCM Monitor Activities **************/
		else if (source == btnRcmLocation) {
			String msg = rcmAction.getLocation(rcmPlaceLabel.getText());
			textDisplayOutput.setText(msg);
		}

		else if (source == currentWeight) {
			String msg = rcmAction.currentWeight(rcmPlaceLabel.getText());
			textDisplayOutput.setText(msg);
		}

		// *********** Display current cash in RCM ******************
		else if (source == currentCash) {
			System.out.println(rcmPlaceLabel.getText());
			String msg = rcmAction.getCurrentCash(rcmPlaceLabel.getText());
			textDisplayOutput.setText(msg);
		}

		// *********** Display current coupon in RCM ******************
		else if (source == currentCoupon) {
			String msg = rcmAction.getCurrentCoupon(rcmPlaceLabel.getText());
			textDisplayOutput.setText(msg);
		}
		// *********** Display No of times RCM was operated in a month
	
		else if (source == btnMonthlyTransactions) {
			String msg = rcmAction.getMonthlyTransactions(rcmPlaceLabel
					.getText());
			textDisplayOutput.setText(msg);
		}
		// *********** Display current status of RCM ******************
		else if (source == btnRcmStatus) {
			String msg = rcmAction.getCurrentStaus(rcmPlaceLabel.getText());
			textDisplayOutput.setText(msg);
		}

		// *********** Display the time when RCM was last Emptied
		// ******************
		else if (source == btnLastEmptied) {
			String msg = rcmAction.getLastEmptied(rcmPlaceLabel.getText());
			textDisplayOutput.setText(msg);

		}

		// *********** Display the amount of money issued by RCM in a month
		// ******************
		else if (source == btnCashDebitedPerMonth) {
			String msg = rcmAction.getCashDebitedPerMonth(rcmPlaceLabel
					.getText());
			textDisplayOutput.setText(msg);

		}

		// *********** Display the total volume of item recycled by RCM in a
		// month ******************
		else if (source == btnWeightPerMonth) {
			String msg = rcmAction.getWeightPerMonth(rcmPlaceLabel.getText());
			textDisplayOutput.setText(msg);

		}

		// *********** Display Number of item recycled by RCM at present
		// ******************
		else if (source == btnCurrItemsRecycled) {
			String msg = rcmAction
					.getCurrItemsRecycled(rcmPlaceLabel.getText());
			textDisplayOutput.setText(msg);

		}

		// *********** Display Number of item recycled by RCM in a month
		// ******************
		else if (source == btnItemsRecycledByMonth) {
			String msg = rcmAction.getItemsRecycledByMonth(rcmPlaceLabel
					.getText());
			textDisplayOutput.setText(msg);

		}
		// *********** Adds new Item in RCM
		// ******************
		else if (source == addItem) {
			// get Item Type
			String itemType = textItemType.getText();
			double itemWeightUnit = 1;
			double unitPrice = Double.parseDouble(textUnitPrice.getText());
			System.out.println("itemType " + itemType + ", unit weight "
					+ itemWeightUnit + ", itemPrice" + unitPrice);
			recycleItem.addItem(itemType, itemWeightUnit, unitPrice);

			// logic.activateRCM(temp);
			textDisplayOutput.setText("Item Successfully Added");
		}
		// *********** Removes Item from RCM
		// ******************
		else if (source == removeItem) {
			// get Item Type
			String itemType = textItemType.getText();
			System.out.println("itemType " + itemType);
			recycleItem.removeItem(itemType);

			// logic.activateRCM(temp);
			textDisplayOutput.setText("Item Removed");
		}
		// *********** Updates Item price
		// ******************
		else if (source == updatePrice) {
			// get Item Type
			double unitPrice = Double.parseDouble(textUnitPrice.getText());
			String itemType = textItemType.getText();
			System.out.println("itemType " + itemType + " itemPrice "
					+ unitPrice);
			recycleItem.updatePrice(itemType, unitPrice);

			// logic.updatePrice(temp);
			textDisplayOutput.setText("Item Price Updated");
		}

		// *********** Show list of Recycable Items ******************
		else if (source == listItem) {
			recycleItem.getItemList();
			logic.getItemList();
			textDisplayOutput.setText("List of Recyclable Items are"
					+ logic.getItemList());
		}

		// *********** Show Most Used RCM ******************
		else if (source == mostUsed) {
			rmosUsageManager.getMostUsedRCM();
			//logic.getItemList();
			textDisplayOutput.setText("Most Used RCM is "+ rmosUsageManager.getMostUsedRCM() );
		}

                // ******** Show Monthly money issued pie chart **
		else if (source == btnShowGraph) {

			// create pie object
			DefaultPieDataset cashPie = new DefaultPieDataset();

			Map<Integer, Double> cashMap = new LinkedHashMap<Integer, Double>();

                    try {
                        cashMap = SelectQueries.cashPieInDB();
                    } catch (SQLException ex) {
                        Logger.getLogger(RMOSDisplay.class.getName()).log(Level.SEVERE, null, ex);
                    }

			// load cashPie, cashMap
			for (Iterator it = cashMap.keySet().iterator(); it.hasNext();) {
				// store in local variable
				Object key = it.next();
				String RcmNum = "RCM " + key;
				double moCash = 0;
				moCash = cashMap.get(key);

				// add local VAR into cashPie
				cashPie.setValue(RcmNum, moCash);
			}

			JFreeChart chart = ChartFactory
					.createPieChart("Current Weight in RCMs - Pie Chart", cashPie,
							true, true, false);

			// display percentage
			PiePlot pp = (PiePlot) chart.getPlot();
			pp.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}",
					new DecimalFormat("0.0"), new DecimalFormat("0.0%")));

			ChartFrame chartFrame = new ChartFrame("Pie Chart", chart);
			chartFrame.setVisible(true);
			chartFrame.setSize(500, 500);

		}

		// show monthly usage times
		else if (source == btnMonthlyUsageTimes) {
			// create pie object
			DefaultPieDataset usagePie = new DefaultPieDataset();

			Map<Integer, Integer> usageMap = new LinkedHashMap<Integer, Integer>();

                    try {
                        usageMap = SelectQueries.usagePieInDB();
                    } catch (SQLException ex) {
                        Logger.getLogger(RMOSDisplay.class.getName()).log(Level.SEVERE, null, ex);
                    }

			// load cashPie, cashMap
			for (Iterator it = usageMap.keySet().iterator(); it.hasNext();) {
				// store in local variable
				Object key = it.next();
				String RcmNum = "RCM " + key;
				double moUsage = 0;
				moUsage = usageMap.get(key);

				// add local VAR into cashPie
				usagePie.setValue(RcmNum, moUsage);
			}

			JFreeChart chart = ChartFactory.createPieChart(
					"Current Cash Available in RCMs - Pie Chart", usagePie, true, true,
					false);

			// display the actual number
			PiePlot pp2 = (PiePlot) chart.getPlot();
			pp2.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}"));

			ChartFrame chartFrame = new ChartFrame("Pie Chart", chart);
			chartFrame.setVisible(true);
			chartFrame.setSize(500, 500);

		}

		// show monthly weight statistics
		else if (source == btnMonthlyWeightStatistics) {
			// create pie object
			DefaultPieDataset usagePie = new DefaultPieDataset();

			Map<Integer, Double> weightMap = new LinkedHashMap<Integer, Double>();

                    try {
                        weightMap = SelectQueries.weightPieInDB();
                    } catch (SQLException ex) {
                        Logger.getLogger(RMOSDisplay.class.getName()).log(Level.SEVERE, null, ex);
                    }

			// load cashPie, cashMap
			for (Iterator it = weightMap.keySet().iterator(); it.hasNext();) {
				// store in local variable
				Object key = it.next();
				String RcmNum = "RCM " + key;
				double moWeight = 0;
				moWeight = weightMap.get(key);

				// add local VAR into cashPie
				usagePie.setValue(RcmNum, moWeight);
			}

			JFreeChart chart = ChartFactory.createPieChart(
					"Current Coupon Available in RCMs - Pie Chart", usagePie, true, true,
					false);

			// display the actual number
//			PiePlot pp2 = (PiePlot) chart.getPlot();
//			pp2.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}"));

			ChartFrame chartFrame = new ChartFrame("Pie Chart", chart);
			chartFrame.setVisible(true);
			chartFrame.setSize(500, 500);
		}

	}// end of actionPerform

}
