/*
 * Created on Oct 29, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pj;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

/**
 * @author Philip_Jose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OpenFileAction extends AbstractAction {
	
	EncriptionFileSelector frame; 
	JFileChooser chooser; 
	
	OpenFileAction(EncriptionFileSelector frame, JFileChooser chooser) {
		super("Open..."); 
		this.chooser = chooser; 
		this.frame = frame;        
	} 
	
	public void actionPerformed(ActionEvent evt) { 
		// Show dialog; this method does not return until dialog is closed 
		chooser.showOpenDialog(frame); 
		
		// Get the selected file 
		File file = chooser.getSelectedFile(); 
		if (null!= file){
			frame.setSelectedFile(file);
		} else{
			//			System.out.println("No file was selected");
		}
	} 
}
