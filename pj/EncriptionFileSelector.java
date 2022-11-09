/*
 * Created on Oct 29, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pj;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Philip_Jose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EncriptionFileSelector extends JFrame {
	
	private javax.swing.JPanel jContentPane = null;
	
	private File selectedFile = null;
	
	private String fileName = null;
	
	private String password = null;
	
	private JPasswordField jpwName = null;
	
	private JTextField encrptFileName  = null;
	
	public static void main(String[] args) {
		
		new EncriptionFileSelector();
	}
	/**
	 * This is the default constructor
	 */
	public EncriptionFileSelector() {
		super();
		initialize();
		addingComponents();
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// setting Look to that of windows.
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		// setting jFrame properties
		this.setSize(400,120);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Philips Encriptor/Decriptor");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private void addingComponents(){
		
		/* fileChooser */
		JFileChooser fileChooser = new JFileChooser();
		// button to invoke fileChooser
		JButton encriptButton= new JButton("Select File ");
		
		//action class for file chooser
		encriptButton.addActionListener(new OpenFileAction(this,fileChooser));
		
		// lable to give instruction of file chooser.
		JLabel jlbEncriptInst = new JLabel("Select the file to be encripted/decripted");
		
		/* Decript Or Encript FileName*/
		encrptFileName = new JTextField("EncriptFileName");
		
		JLabel fileNameLable = new JLabel("Enter file name of encripted/decripted file:");
		
		/* Password*/
		JLabel jlbPassword = new JLabel("Enter your password: ");
		jpwName = new JPasswordField(10);
		jpwName.setEchoChar('*');
		
		//left Panel
		JPanel leftPanel = new JPanel(new GridLayout(0,1,2,2));
		leftPanel.add(jlbEncriptInst);
		leftPanel.add(fileNameLable);
		leftPanel.add(jlbPassword);
		
		
		//Right Panel
		JPanel rightPanel = new JPanel(new GridLayout(0,1,2,2));
		rightPanel.add(encriptButton);
		rightPanel.add(encrptFileName);
		rightPanel.add(jpwName);
		
		JButton submit = new JButton("Submit"); 
		submit.addActionListener(new SubmitAction(this));
		
		this.getContentPane().add(leftPanel,BorderLayout.LINE_START);
		this.getContentPane().add(rightPanel,BorderLayout.LINE_END);
		this.getContentPane().add(submit,BorderLayout.PAGE_END);
		this.setVisible(true);
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new java.awt.BorderLayout());
		}
		return jContentPane;
	}
	
	/**
	 * @param selectedFile The selectedFile to set.
	 */
	public void setSelectedFile(File selectedFile) {
		this.selectedFile = selectedFile;
	}
	
	private boolean validateInputs(){
		
		// check if we have selected a file
		boolean isValid = false;
		if(null == selectedFile){
			JOptionPane.showMessageDialog(this,"Please select the file to encript/decript.", "User Input Error",				    JOptionPane.ERROR_MESSAGE);
		} else if(null == fileName || "".equals(fileName.trim())){
			JOptionPane.showMessageDialog(this,"Please enter a name for the file which is to be encripted/decripted", "User Input Error",
					JOptionPane.ERROR_MESSAGE);
		}  else if(-1 !=fileName.indexOf("!") || -1 !=fileName.indexOf("@") ||-1 !=fileName.indexOf("#") ||-1 !=fileName.indexOf("$")
				||-1 !=fileName.indexOf("%") ||-1 !=fileName.indexOf("^") ||-1 !=fileName.indexOf("&")
				||-1 !=fileName.indexOf("*") ||-1 !=fileName.indexOf("(") ||-1 !=fileName.indexOf(")")
				||-1 !=fileName.indexOf("+") ||-1 !=fileName.indexOf("~") ||-1 !=fileName.indexOf("`")
				||-1 !=fileName.indexOf("<") ||-1 !=fileName.indexOf(">") ||-1 !=fileName.indexOf("?")
				||-1 !=fileName.indexOf("/") ||-1 !=fileName.indexOf("\\") ||-1 !=fileName.indexOf("|")
				||-1 !=fileName.indexOf("{") ||-1 !=fileName.indexOf("}") ||-1 !=fileName.indexOf("{")
				||-1 !=fileName.indexOf("}") ){
			JOptionPane.showMessageDialog(this,"No special characters allowed for the file name", "User Input Error",
					JOptionPane.ERROR_MESSAGE);
		}  else if(null == password || "".equals(password.trim())){
			JOptionPane.showMessageDialog(this,"Please enter password for encription/decription", "User Input Error",
					JOptionPane.ERROR_MESSAGE);
		}  else {
			isValid = true;
		}
		
		return isValid;
		
	}
	
	/*clearing the value of instance variables*/
	private void clearVariables(){
		jpwName.setText("");
		encrptFileName.setText("");
		fileName = null;
		selectedFile = null;
		password = null;
	}
	
	/* made as inner class to keep controller function here itself*/
	private class SubmitAction extends AbstractAction{
		
		private EncriptionFileSelector selector = null;
		
		SubmitAction(EncriptionFileSelector selector){
			this.selector = selector;
		}
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Encriptor encriptor = new Encriptor(selector);
			
			StringBuffer psdBuilder = new StringBuffer();
			try{
				psdBuilder.append(jpwName.getPassword());
				password = psdBuilder.toString();
				
				fileName = encrptFileName.getText();
				
				if(validateInputs()){
					if(encriptor.process(selectedFile,fileName,password)){
						JOptionPane.showMessageDialog(selector,"File was processed successfully, use the same password when reverting or else the file will be corrupted", "Success!",
								JOptionPane.INFORMATION_MESSAGE);	
					}
					
				}
			}catch(Exception except){
				System.out.println("EXECPTION");
				JOptionPane.showMessageDialog(selector,(except.toString()), "Application Error",
						JOptionPane.ERROR_MESSAGE);
				System.out.println("EXECPTION");
			}
			clearVariables();
		}
		
	}
}
