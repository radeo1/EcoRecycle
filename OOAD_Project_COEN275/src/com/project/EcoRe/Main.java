package com.project.EcoRe;


import java.awt.CardLayout;
import javax.swing.JPanel;


/**
 *
 * @author Rachana
 */
public class Main extends javax.swing.JFrame {

    static private CardLayout cardLayout;
    static private JPanel cards;
    
    static int activityType=1; // Customer is default activity
    /**
     * Creates new form Main
     */
    public Main() {
        setTitle("EcoRecycle RCM");
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {
    }
    
    static public JPanel getCards() {
        return cards;
    }

    public void setCards(JPanel cards) {
        this.cards = cards;
    }

    static public CardLayout getCardLayout() {
        return cardLayout;
    }

    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }
    
    public static int getActivityType() {
        return activityType;
    }

    public static void setActivityType(int activityType) {
        Main.activityType = activityType;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Main main = new Main();
                main.setContentPane(cards);
                main.setLocationRelativeTo(null);
                main.setSize(750, 500);
                main.setVisible(true);
            }
        });
    }

}
