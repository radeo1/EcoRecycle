/**
 * @author Pragati Shrivastava
 * @version 1.0
 */
package com.project.tester;
import javax.swing.SwingUtilities;

import com.project.ui.RMOSDisplay;
import com.project.event.batch.BatchProcessor;

/** The entry main() method */
public class ProjectTester 
{
	public static void main(String[] args) 
	{
	 // Run GUI codes on the Event-Dispatcher Thread for thread safety
		SwingUtilities.invokeLater(new Runnable() 
		{
		      @Override
		      public void run() 
		      {	
		    	  // open RMOS UI
		    	RMOSDisplay rmosdisplay = new RMOSDisplay();// Let the constructor do the job
		  		rmosdisplay.setVisible(true) ;
		      }
		   });		
		
		BatchProcessor bp = new BatchProcessor();
		bp.executeEventsPeriodically();//scheduler starts fro here
		
		/*
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               
                Vending vend = new Vending("RCM");
                vend.setSize(800, 800);
                vend.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                vend.setVisible(true);
		vend.setFocusable(true);
		
            }
        });*/
	}
}
