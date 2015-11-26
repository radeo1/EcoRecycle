package com.project.EcoRe;


import com.project.ui.RMOSDisplay;
import java.awt.CardLayout;
import javax.swing.JPanel;
import com.project.ui.Vending;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Rachana
 */
public class Main extends javax.swing.JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               
                Vending vend = new Vending("RCM");
                vend.setSize(800, 800);
                vend.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                vend.setVisible(true);
		vend.setFocusable(true);
		
            }
        });
    }

}

