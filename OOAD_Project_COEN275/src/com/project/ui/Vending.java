package com.project.ui;

import com.project.EcoRe.RCMRecycle;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class Vending extends JFrame implements ActionListener{

   
    
	public static JButton[] reitemTextButtons;
        private RCMRecycle rcm = new RCMRecycle();
    
    public Vending(String s) {
        super(s);
        
		itemChooser itemSelect = new itemChooser();
		focusSet focus = new focusSet();
		addMouseListener(focus);
		addKeyListener(itemSelect);
                Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2
				- getHeight() / 2);
		setSize(size.width/2, size.height/2);
                
        JMenuBar menubar = new JMenuBar();
       
        JMenu menu = new JMenu("RCM list");
        menu.setForeground(Color.BLUE);
	menu.setFont(new Font("Verdana", Font.PLAIN, 16));
	menu.setBounds(6, 6, 90, 21);
        
        menubar.add(menu);
        
        // The panel of price
        JPanel pPrice = new JPanel();
        
        pPrice.setLayout(new BoxLayout(pPrice, BoxLayout.Y_AXIS));
        JPanel pPriceText = new JPanel();
        pPriceText.setLayout(new FlowLayout());
        pPriceText.setForeground(new Color(50, 0, 200));
	pPriceText.setFont(new Font("Arial", Font.BOLD, 14));
	pPriceText.setBounds(12, 100, 280, 30);
        
        JLabel priceTextLabel = new JLabel("Price");
        priceTextLabel.setForeground(new Color(50, 0, 200));
	priceTextLabel.setFont(new Font("Arial", Font.BOLD, 14));
	priceTextLabel.setBounds(12, 100, 280, 30);
        
        JTextField amountTextField = new JTextField("$1.50");
        amountTextField.setForeground(new Color(50, 0, 200));
	amountTextField.setFont(new Font("Arial", Font.BOLD, 14));
	amountTextField.setBounds(12, 100, 280, 30);
        
        pPriceText.add(priceTextLabel);
        pPriceText.add(amountTextField);
        pPriceText.setBackground(new Color(148, 226, 79));
        JTextField enterMoneyTextField = new JTextField("Enter Weight Here ..");
        
        JButton additem = new JButton("Recycle item");
        additem.setForeground(new Color(50, 0, 200));
	additem.setFont(new Font("Arial", Font.BOLD, 14));
	additem.setBounds(12, 100, 280, 30);
        
        pPrice.add(pPriceText);
        pPrice.add(additem);
        pPrice.setBackground(new Color(148, 226, 79));
        // The panel of "return amount"
        JTextField returnAmountTextField = new JTextField("Return amount ..");
        
        // The "choose one" panel
        JPanel pChooseOne = new JPanel();
        pChooseOne.setLayout(new GridLayout(4,1));
        pChooseOne.setCursor(new Cursor(Cursor.HAND_CURSOR));
        reitemTextButtons = new JButton[itemType];
        
        pChooseOne.setBorder(new TitledBorder("Choose one"));

        
        //transaction panel
       JButton cash = new JButton("Cash");
       /* cash.setForeground(new Color(50, 0, 200));
	cash.setFont(new Font("Arial", Font.BOLD, 14));
	cash.setBounds(12, 100, 280, 30);
        cash.addActionListener(this);
             */       
		
       


        JButton coupon = new JButton("Coupon");
       /* coupon.setForeground(new Color(50, 0, 200));
	coupon.setFont(new Font("Arial", Font.BOLD, 14));
	coupon.setBounds(12, 100, 280, 30);
        coupon.addActionListener(this);
        
    */
        
        // Add the above four panels together
        JPanel p1 = new JPanel();
        
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
        p1.setPreferredSize(new Dimension (300, 600));
        pPrice.setBackground(new Color(148, 226, 79));
        p1.add(pPrice);
        pChooseOne.setBackground(new Color(148, 226, 79));
        p1.add(pChooseOne);
        returnAmountTextField.setBackground(new Color(148, 226, 79));
        p1.add(returnAmountTextField);
//        p1.add(cash, BorderLayout.WEST);
 //       p1.add(coupon, BorderLayout.WEST);
        
        

        // The panel of recycling items
        JPanel recycleItem = new JPanel();
        recycleItem.setLayout(new GridLayout(4,1,0,0));
        recycleItem.setBorder(new TitledBorder("Recycling items"));
        ((TitledBorder) recycleItem.getBorder()).setTitleColor(Color.black);
        int itemImgWidth = 50, itemImgHeight = 50;
        JButton[] itemImageButtons = new JButton[itemType];
        
        
        DisplayPricesActionListener displayPriceListener = new DisplayPricesActionListener(amountTextField, itemImageButtons);
        for (int i = 0; i < itemImageButtons.length; i++) {

            ImageIcon image1 = new ImageIcon(itemImageNames[i]);
            ImageIcon image2 = new ImageIcon(image1.getImage().getScaledInstance((int)itemImgWidth, (int)itemImgHeight, java.awt.Image.SCALE_SMOOTH));			
            itemImageButtons[i] = new JButton(image2);
            itemImageButtons[i].setContentAreaFilled(false);
            itemImageButtons[i].setBorderPainted(true);
            recycleItem.add(itemImageButtons[i]);
            // ActionListener attached to each button
            itemImageButtons[i].addActionListener(displayPriceListener);
           
        }

        // The panel of "where you get your item"
        JButton whereButton = new JButton("This is where you insert your item!");
       
        whereButton.setPreferredSize(new Dimension(250, 100));
        whereButton.setBackground(new Color(148, 226, 79));
        whereButton.setOpaque(true);
        

        // Add everything onto the frame
        this.add(recycleItem, BorderLayout.CENTER);
        recycleItem.setBackground(new Color(148, 226, 79));
        this.add(p1, BorderLayout.EAST);
        p1.setBackground(new Color(148, 226, 79));
        this.add(whereButton, BorderLayout.SOUTH);
        whereButton.setBackground(new Color(148, 226, 79));
        
        this.add(menubar, BorderLayout.PAGE_START);
        menubar.setBackground(new Color(148, 226, 79));
        
        
        
        // the action listener
        PayForGoodyActionListener payitemListener = 
            new PayForGoodyActionListener(reitemTextButtons, whereButton,
                    additem, returnAmountTextField, cash,coupon);
        
        for (int i = 0; i < itemType; i++) {
            reitemTextButtons[i] = new JButton(itemNames[i]);
            
            
            reitemTextButtons[i].setToolTipText(itemTips[i]);
          
            // ActionListener attached to each button
            reitemTextButtons[i].addActionListener(payitemListener); 
			switch(itemNames[i])
			{
			case("0"):
					reitemTextButtons[i].setMnemonic(KeyEvent.VK_0);
					break;
				case("1"):
					reitemTextButtons[i].setMnemonic(KeyEvent.VK_1);
					break;
				case("2"):
					reitemTextButtons[i].setMnemonic(KeyEvent.VK_2);
					break;
				case("3"):
					reitemTextButtons[i].setMnemonic(KeyEvent.VK_3);
					break;
				case("4"):
					reitemTextButtons[i].setMnemonic(KeyEvent.VK_4);
					break;
                                case("5"):
					reitemTextButtons[i].setMnemonic(KeyEvent.VK_5);
					break;
                                case("6"):
					reitemTextButtons[i].setMnemonic(KeyEvent.VK_6);
					break;
                                case("7"):
					reitemTextButtons[i].setMnemonic(KeyEvent.VK_7);
					break;
                                
				default:
					break;
			}
            pChooseOne.add(reitemTextButtons[i]);
            
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


	public class itemChooser implements KeyListener {
                
		public void keyPressed(KeyEvent e){
			switch(e.getKeyCode())
			{
				case KeyEvent.VK_0:
					reitemTextButtons[0].doClick();
					break;
				case KeyEvent.VK_1:
					reitemTextButtons[1].doClick();
					break;
				case KeyEvent.VK_2:
					reitemTextButtons[2].doClick();
					break;
				case KeyEvent.VK_3:
					reitemTextButtons[3].doClick();
					break;
				case KeyEvent.VK_4:
					reitemTextButtons[4].doClick();
					break;
                                case KeyEvent.VK_5:
					reitemTextButtons[5].doClick();
					break;
                                case KeyEvent.VK_6:
					reitemTextButtons[6].doClick();
					break;
                                case KeyEvent.VK_7:
					reitemTextButtons[7].doClick();
					break;    
                                   
				default:
					break;
			}
		}
		
		public void keyReleased(KeyEvent e){}
		public void keyTyped(KeyEvent e){}
	}
	
	public class focusSet extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			requestFocusInWindow();
			System.out.println("FOCUS");
                        setBackground(new Color(148, 226, 79));
		}
	}

    // ActionListener class to display Goody prices
    private class DisplayPricesActionListener implements ActionListener{
        final JTextField priceField;	// Where to diplay the price
        final JButton[] reitemImages;	// What button objects can be clicked
        // These are in the same order as the
        // itemImageNames & itemPrices

        
        public DisplayPricesActionListener(JTextField priceField,
                JButton[] reitemImages) {
            super();
            this.priceField = priceField;
            this.reitemImages = reitemImages;
            setBackground(new Color(148, 226, 79));
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            setBackground(new Color(148, 226, 79));
            for (int i=0; i<itemNames.length; i++){	// Check which button was clicked
                // and display appropriate price
                if (e.getSource().equals(reitemImages[i])){
                    DecimalFormat df = new DecimalFormat("#.00##");
                    String priceToDisplay = df.format(itemPrices[i]);
                    priceField.setText("$" + priceToDisplay);
                }
            }	
        }
    }

    // ActionListener class to display "here is your goody" when 
    // the right price amount is entered
    private class PayForGoodyActionListener implements  ActionListener {

        final JButton[] selectButtons;		// Array of buttons to choose goodies
        final JButton whereButton;
        final JButton additem;		// Button where item is added
        final JTextField outputChangeTF; // Text Field where "here is your goody is displayed" 
        JButton cash = new JButton("Cash");
        
      
        JButton coupon = new JButton("Coupon");
        
        
        public PayForGoodyActionListener(JButton[] selectButtons,
                JButton whereButton,
                JButton additem, JTextField outputChangeTF, JButton cash, JButton coupon) {
            super();
            this.whereButton = whereButton;
            
            this.selectButtons = selectButtons;
            this.additem = additem;
            
            this.outputChangeTF = outputChangeTF;
            this.cash = cash;
            this.coupon = coupon;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedButtonPos = -1;
            String itemtype ="";
            int rcmnum = 1;
            String rcmid = "RCMO1";
            Object source = e.getSource();
            for (int i=0; i<selectButtons.length; i++){
                if (e.getSource().equals(selectButtons[i])){
                    selectedButtonPos = i;
                    itemtype =itemTips[selectedButtonPos];
                    break;
                }
                
            }
            
            //String priceEntered = JButton additem.getText();
            double inputMoney = 0.0;
            double change = 0.0; 
            try{
                //inputMoney = Double.parseDouble(priceEntered);
                double random = Math.random()*9;
                change = random * itemPrices[selectedButtonPos];
                
                DecimalFormat df = new DecimalFormat("#.00");
                    double transweight = Double.parseDouble(df.format(random));
                    DecimalFormat df1 = new DecimalFormat("#.00");
                    double transamount= Double.parseDouble(df1.format(change));
                    double transcoupon= Double.parseDouble(df1.format(change));
                    
                    
                if (transamount >= 5.0) {
                    whereButton.setText("You inserted " + itemTips[selectedButtonPos]);
                    
                    rcm.transactionamount(rcmid, itemtype, transweight, transamount);
                    outputChangeTF.setText("Cash value: " + df1.format(change) + "\n"+" Weight of item: "+df.format(random));
                
                }
                else if(transamount <5.0)
                    whereButton.setText("You inserted " + itemTips[selectedButtonPos]);
               
                    rcm.transactioncoupon(rcmid, itemtype, transweight, transcoupon);
                    outputChangeTF.setText("Coupon value: " + df1.format(change) + "\n"+" Weight of item: "+df.format(random));
                    //outputChangeTF.setText("Not enough for " + itemTips[selectedButtonPos]);
                //inputPriceTF.setText("Enter Weight Here ..");
            } catch (Exception exception) {}
        }

    }

    
    

    final int itemType = 8;
    final String[] itemImageNames = {
        "src/paper.jpg",
        "src/plastic.jpeg",
        "src/cartons.jpg",
        "src/glass.jpg",
        "src/aluminiumcans.png",
        "src/metal.jpeg",
        "src/books.png",
        "src/electronicwaste.png"
    };
    final String[] itemNames = { "Paper", "Plastic containers", "Cartons and Juice Boxes","Glass bottles & Containers", "Aluminium and Aerosol Cans",
                                "Tin/Steel Cans", "Books of all kinds", "Electronic Waste" };
    final String[] itemTips = {"Paper", "Plastic containers", "Cartons and Juice Boxes","Glass bottles & Containers", "Aluminium and Aerosol Cans",
                                "Tin/Steel Cans", "Books of all kinds", "Electronic Waste"
    };
    final double[] itemPrices = {
        1.50,
        1,
        0.5,
        2.50,
        2.50,
        1.50, 
        2.00,
        5.00
    };

    

    /**
     * @param args
     */
  /*  public static void main(String[] args) {
        // TODO Auto-generated method stub
        Vending v = new Vending("RCM");
        v.setSize(600, 800);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setVisible(true);
		v.setFocusable(true);
    }*/

}
