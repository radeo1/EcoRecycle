/**
 * @author Rachana Deolikar
 * @version 1.0
 */
package com.project.tester;

import javax.swing.JFrame;

import com.project.ui.Vending;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
