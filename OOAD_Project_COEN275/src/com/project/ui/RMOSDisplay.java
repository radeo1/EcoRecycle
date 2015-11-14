package com.project.ui;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import com.project.EcoRe.RMOS;

public class RMOSDisplay extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1;

	//private static final int FRAME_WIDTH = 1200;
	//private static final int FRAME_HEIGHT = 900;
	private RMOS rmos = new RMOS();
	
	private Container contentPane;
	private JPanel panel1,panel2,panel3;

	private JTextField textUsername,textRcmId,textRcmNo,textRcmLocation,textRcmStatus;
	private JPasswordField password;
	private JButton buttonLogin,addRCM,removeRCM,activateRCM,refillRCM,clearRCM,showRCM,currentWeight,currentCash,currentCoupon,
					btnLastEmptied,btnMonthlyTransactions,btnCashDebitedPerMonth,btnWeightPerMonth,btnCurrItemsRecycled,btnItemsRecycledByMonth;
	private JLabel  rcmPlaceLabel;
	private JComboBox rcmCombo;
	static JTextArea textDisplayOutput;
	
	public RMOSDisplay()
	   {
		super( "RMOS" );
		
		contentPane = getContentPane();
		contentPane.setBackground(Color.BLUE);
		//contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		
		/******	Login Panel ******/
		panel1 = new JPanel();
		panel1.setBackground (new Color(204,204,0));
		panel1.setPreferredSize( new Dimension( 1200,60) );
		panel1.setBorder(new TitledBorder(new EtchedBorder(), "Admin Login"));
	    contentPane.add(panel1, BorderLayout.NORTH);
	    
	    JLabel labelUsername = new JLabel("UserName");
	    labelUsername.setFont(new Font("Arial", Font.BOLD, 14));
	    labelUsername.setForeground(new Color(30, 0,  200));
	    labelUsername.setBounds(50, 25, 95, 22);
		panel1.add(labelUsername);
		panel1.setLayout(null);
	    
		textUsername = new JTextField();
		textUsername.setBounds(140, 20, 200, 28);
		panel1.add(textUsername);
		textUsername.setColumns(10);
		
		JLabel labelPassword = new JLabel("Password");
		labelPassword.setFont(new Font("Arial", Font.BOLD, 14));
		labelPassword.setForeground(new Color(30, 0,  200));
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
		/*	End of Login Panel */
		
		/**>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
		
		/******	Operations Panel ******/
	    panel2 =  new JPanel();
	    panel2.setBackground(new Color(153,255,153));
	    panel2.setPreferredSize( new Dimension(1200,600));
	    panel2.setLayout(new GridLayout(2,2));
	    
	    JPanel controlPanel1, controlPanel2, controlPanel3, controlPanel4;
	    
	    /******	RMOS Manager Panel ******/
	    controlPanel1 = new JPanel();
	    controlPanel1.setBackground(Color.ORANGE);
	    controlPanel1.setPreferredSize( new Dimension(600,245));
	    controlPanel1.setBorder(new TitledBorder(new EtchedBorder(), "RMOS Manager"));
    	panel2.add(controlPanel1);
	    
	    JLabel labelRcmId = new JLabel("RCM ID");
		labelRcmId.setFont(new Font("Arial", Font.BOLD, 14));
		labelRcmId.setForeground(new Color(30, 0,  200));
		labelRcmId.setBackground(new Color(0, 204, 153));
		labelRcmId.setBounds(12, 24, 70, 22);
	    controlPanel1.add(labelRcmId);
	    controlPanel1.setLayout(null);
	    
	    textRcmId = new JTextField();
		textRcmId.setBounds(80,22, 60, 26);
		controlPanel1.add(textRcmId);
		
	    JLabel labelRcmNo = new JLabel("RCM Number");
	    labelRcmNo.setFont(new Font("Arial", Font.BOLD, 14));
	    labelRcmNo.setForeground(new Color(30, 0,  200));
	    labelRcmNo.setBackground(new Color(0, 204, 153));
	    labelRcmNo.setBounds(168, 24, 90, 22);
	    controlPanel1.add(labelRcmNo);
	    controlPanel1.setLayout(null);
	    
	    textRcmNo = new JTextField();
		textRcmNo.setBounds(270,22, 60, 26);
		controlPanel1.add(textRcmNo);		
	    
	    JLabel labelRcmLocation = new JLabel("RCM Location");
	    labelRcmLocation.setFont(new Font("Arial", Font.BOLD, 14));
	    labelRcmLocation.setForeground(new Color(30, 0,  200));
	    labelRcmLocation.setBackground(new Color(0, 204, 153));
	    labelRcmLocation.setBounds(350, 24, 100, 22);
	    controlPanel1.add(labelRcmLocation);
	    controlPanel1.setLayout(null);
	    
	    textRcmLocation = new JTextField();
	    textRcmLocation.setBounds(470,22, 100, 26);
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
		//clearRCM.addActionListener((ActionListener) this);
		
		// ShowRCM Button
		showRCM = new JButton("Display RCM List ");
		showRCM.setForeground(new Color(50, 0, 200));
		showRCM.setFont(new Font("Arial", Font.BOLD, 14));
		showRCM.setBounds(295,  100, 180, 26);
		showRCM.addActionListener(this);
		controlPanel1.add(showRCM);
		
		/*	End of RMOS Manager Panel */
		
		/************************************************************************/
		
		/****** ITEM Daemon Panel ******/
		
    	controlPanel2 = new JPanel();
	    controlPanel2.setBackground(Color.ORANGE);
	    controlPanel2.setPreferredSize(new Dimension(600,245));
	    controlPanel2.setBorder(new TitledBorder(new EtchedBorder(), "ITEM Daemon"));
    	panel2.add(controlPanel2);
    	
    	/****** End of ITEM Daemon ******/
    	
    	/************************************************************************/
    	
    	/****** RCM Monitor Panel ******/
    	controlPanel3 = new JPanel();
	    controlPanel3.setBackground(Color.ORANGE);
	    controlPanel3.setPreferredSize( new Dimension(600,345));
	    controlPanel3.setBorder(new TitledBorder(new EtchedBorder(), "RCM Monitor"));
    	
        JLabel labelRcmList = new JLabel("RCM List");
    	labelRcmList.setFont(new Font("Arial", Font.BOLD, 14));
    	labelRcmList.setForeground(new Color(30, 0,  200));
    	labelRcmList.setBackground(new Color(0, 204, 153));
    	labelRcmList.setBounds(12, 24, 70, 22);
 	    controlPanel3.add(labelRcmList);
 	    controlPanel3.setLayout(null);
 	    
 	    JComboBox comboRcmList = createComboBox();
 	    comboRcmList.setBounds(100, 24, 95, 22);
 	   	controlPanel3.add(comboRcmList);
 	   	controlPanel3.setLayout(null);
   
 		rcmPlaceLabel = new JLabel();
	    rcmPlaceLabel.setForeground(new Color(30, 0,  200));
	    rcmPlaceLabel.setFont(new Font("Arial", Font.BOLD, 14));
	    rcmPlaceLabel.setBounds(205, 24, 95, 22);
	    controlPanel3.add(rcmPlaceLabel);
	    
	    JLabel labelRcmNumber = new JLabel("RCM Number");
	    labelRcmNumber.setForeground(new Color(30, 0,  200));
	    labelRcmNumber.setFont(new Font("Arial", Font.BOLD, 14));
	    labelRcmNumber.setBounds(362, 24, 90, 22);
	    controlPanel3.add(labelRcmNumber);
	    
	    JTextField textRcmNumber = new JTextField();
	    textRcmNumber.setBounds(460,24, 100, 22);
		controlPanel3.add(textRcmNumber);
		controlPanel3.setLayout(null);
		
		JLabel labelRcmLoc = new JLabel("Location");
		labelRcmLoc.setFont(new Font("Arial", Font.BOLD, 14));
		labelRcmLoc.setForeground(new Color(30, 0,  200));
		labelRcmLoc.setBackground(new Color(0, 204, 153));
		labelRcmLoc.setBounds(12, 60, 90, 22);
	    controlPanel3.add(labelRcmLoc);
	    
	    JTextField textRcmLoc = new JTextField();
	    textRcmLoc.setBounds(100,58, 95, 26);
		controlPanel3.add(textRcmLoc);
		
		JLabel labelRcm = new JLabel("RCM ID");
		labelRcm.setFont(new Font("Arial", Font.BOLD, 14));
		labelRcm.setForeground(new Color(30, 0,  200));
		labelRcm.setBackground(new Color(0, 204, 153));
		labelRcm.setBounds(205, 60, 60, 22);
	    controlPanel3.add(labelRcm);
	    controlPanel3.setLayout(null);
	    
	    JTextField textRcm = new JTextField();
	    textRcm.setBounds(270,58, 80, 26);
		controlPanel3.add(textRcm);		
	    
		JLabel rcmStatusLabel = new JLabel("Status");
		rcmStatusLabel.setFont(new Font("Arial", Font.BOLD, 14));
		rcmStatusLabel.setForeground(new Color(30, 0,  200));
		rcmStatusLabel.setBackground(new Color(0, 204, 153));
	    rcmStatusLabel.setBounds(362, 60, 96, 22);
	    controlPanel3.add(rcmStatusLabel);
	    controlPanel3.setLayout(null);
	    
	    textRcmStatus = new JTextField();
	    textRcmStatus.setBounds(460,58, 100, 26);
		controlPanel3.add(textRcmStatus);    		
		
	// CurrentWeight Button
		currentWeight = new JButton("Current Weight ");
		currentWeight.setForeground(new Color(50, 0, 200));
		currentWeight.setFont(new Font("Arial", Font.BOLD, 14));
		currentWeight.setBounds(12, 100, 145, 26);
		currentWeight.addActionListener(this);
		controlPanel3.add(currentWeight);
	
		
	// CurrentCash Button
		currentCash = new JButton("Current Cash ");
		currentCash.setForeground(new Color(50, 0, 200));
		currentCash.setFont(new Font("Arial", Font.BOLD, 14));
		currentCash.setBounds(200, 100, 145, 26);
		currentCash.addActionListener(this);
		controlPanel3.add(currentCash);
		;		
		
	// CurrentCoupon Button
		currentCoupon = new JButton("Current Coupon ");
		currentCoupon.setForeground(new Color(50, 0, 200));
		currentCoupon.setFont(new Font("Arial", Font.BOLD, 14));
		currentCoupon.setBounds(400, 100, 180, 26);
		currentCoupon.addActionListener(this);
		controlPanel3.add(currentCoupon);
		
	// TimeLastEmptied Button
		btnLastEmptied = new JButton("Time Last Emptied");
		btnLastEmptied.setForeground(new Color(50, 0, 200));
		btnLastEmptied.setFont(new Font("Arial", Font.BOLD, 14));
		btnLastEmptied.setBounds(12, 145,280, 30);
		btnLastEmptied.addActionListener(this);
		controlPanel3.add(btnLastEmptied);	

	// MonthlyTransactions Button
		btnMonthlyTransactions = new JButton("Monthly Transactions");
		btnMonthlyTransactions.setForeground(new Color(50, 0, 200));
		btnMonthlyTransactions.setFont(new Font("Arial", Font.BOLD, 14));
		btnMonthlyTransactions.setBounds(300, 145, 280, 30);
		btnMonthlyTransactions.addActionListener(this);
		controlPanel3.add(btnMonthlyTransactions);
		
	// CashDebitedPerMonth Button
		btnCashDebitedPerMonth = new JButton("Cash Debited per Month");
		btnCashDebitedPerMonth.setForeground(new Color(50, 0, 200));
		btnCashDebitedPerMonth.setFont(new Font("Arial", Font.BOLD, 14));
		btnCashDebitedPerMonth.setBounds(12, 190,280, 30);
		btnCashDebitedPerMonth.addActionListener(this);
		controlPanel3.add(btnCashDebitedPerMonth);	

	// WeightAccumulatedPerMonth Button
		btnWeightPerMonth = new JButton("Weight Accumulated per Mounth");
		btnWeightPerMonth.setForeground(new Color(50, 0, 200));
		btnWeightPerMonth.setFont(new Font("Arial", Font.BOLD, 14));
		btnWeightPerMonth.setBounds(300, 190, 280, 30);
		btnWeightPerMonth.addActionListener(this);
		controlPanel3.add(btnWeightPerMonth);	
		
	 // CurrItemsRecycled Button
		btnCurrItemsRecycled = new JButton("Current Number of Items Recycled");
		btnCurrItemsRecycled.setForeground(new Color(50, 0, 200));
		btnCurrItemsRecycled.setFont(new Font("Arial", Font.BOLD, 14));
		btnCurrItemsRecycled.setBounds(12, 240,280, 30);
		btnCurrItemsRecycled.addActionListener(this);
		controlPanel3.add(btnCurrItemsRecycled);	

	// ItemsRecycledByMonth Button
		btnItemsRecycledByMonth = new JButton("Num of Items Recycled per Mounth");
		btnItemsRecycledByMonth.setForeground(new Color(50, 0, 200));
		btnItemsRecycledByMonth.setFont(new Font("Arial", Font.BOLD, 14));
		btnItemsRecycledByMonth.setBounds(300, 240, 280, 30);
		controlPanel3.add(btnItemsRecycledByMonth);	
		btnItemsRecycledByMonth.addActionListener(this);
		panel2.add(controlPanel3);
		
        /****** End of RCM Monitor ******/
    	
    	/***********************************************************************/
	    
	    /****** RMOS Usage Manager Panel******/
    	controlPanel4 = new JPanel();
	    controlPanel4.setBackground(Color.ORANGE);
	    controlPanel4.setPreferredSize( new Dimension(600,345));
	    controlPanel4.setBorder(new TitledBorder(new EtchedBorder(), "RMOS Usage Manager"));
    	panel2.add(controlPanel4);
 
    	 /****** End of RMOS Usage Manager ******/
    	
	    contentPane.add(panel2, BorderLayout.CENTER);
	    
	    /****** End of Operations Panel ******/
	    
	    /**>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	    
	    /*	Output Panel */
	    panel3 =  new JPanel();
	    panel3.setBackground(new Color(204,204,0));
	    panel3.setPreferredSize( new Dimension(1200,250));
	    panel3.setBorder(new TitledBorder(new EtchedBorder(), "Output Screen"));
	 	contentPane.add(panel3, BorderLayout.SOUTH);
		panel3.setLayout(null);
		
		textDisplayOutput = new JTextArea();
	 	textDisplayOutput.setBounds(6, 26, 1188, 244);
	 	textDisplayOutput.setEditable(false);
	 	textDisplayOutput.setBackground(new Color(204,204,0));
		textDisplayOutput.setForeground(new Color(0, 51, 255));
		textDisplayOutput.setFont(new Font("Arial", Font.BOLD, 14));
		panel3.add(textDisplayOutput);
		
		/******	End of Output Panel ******/
	 	
	 	/**>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	      
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2,size.height/2 - getHeight()/2);
		setSize(size.width, size.height);
	 	setVisible(true);
		//System.out.println("Width "+size.width +" Height "+ size.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   }
	
	 /*	Part of RCM Monitor Operations Panel */
	    public JComboBox createComboBox()
	    {    
	      String [] RCMachine = {"RCM01","RCM02","RCM03","RCM04","RCM05",
	  						     "RCM06","RCM07","RCM08","RCM09","RCM10"};
	      rcmCombo = new JComboBox(RCMachine);
	      rcmCombo.setForeground(Color.BLACK);
	      rcmCombo.setFont(new Font("Arial", Font.BOLD, 10));
	 
	     
	   // add the event handler, anonymous handler is used
	      rcmCombo.addActionListener(new ActionListener()
	        {
	        	public void actionPerformed(ActionEvent e)
	        	{
	        		 JComboBox cb = (JComboBox)e.getSource();
	        	     String rcmNo = (String)cb.getSelectedItem(); 
	        	     rcmPlaceLabel.setText(rcmNo);
	        	}
	        }
	        );
	      return rcmCombo;
	   }
	  /*End of RCM Monitor Operations Panel */
	 
//*********************************************************************************************************
	@Override
	
	public void actionPerformed(ActionEvent e) 
	{
		// store command from event e
		Object source = e.getSource();

		// *********** Manager Log in **************
			if (source == buttonLogin) 
			{
				String userName = textUsername.getText();
				String passWord = "";

				char[] passwd = password.getPassword();
				passWord = String.valueOf(passwd);

				boolean canLogIn = false;
				canLogIn = rmos.logIn(userName, passWord);

				if (canLogIn) 
				{
					textDisplayOutput.setText("Log In Successful !");
				} 
				else
				{
					textDisplayOutput.setText("Please input the valid username or password");
								
				}

			}
	   }
		
//*********************************************************************************************************
					
	public static void main(String[] args) 
	
	{
		  RMOSDisplay rmosDisplay = new RMOSDisplay();
		  rmosDisplay.setVisible(true);      
	}
}
	
