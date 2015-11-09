/**
 * @author Pragati Shrivastava
 *
 */
package com.project.ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
public class MyButtonListener implements ActionListener {
	LoginToRmos l;
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton source = (JButton) e.getSource();
		JOptionPane.showMessageDialog(source, source.getText() + " button has been pressed");
		
		
	}
}
