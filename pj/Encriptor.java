/*
 * Created on Sep 29, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pj;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * @author Philip_Jose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Encriptor {
	
	
	private EncriptionFileSelector selector = null;
	
	Encriptor(EncriptionFileSelector selector){
		this.selector = selector;
	}
	/**
	 * Encripts the originalFile, if the originalFile is not encripted.
	 * Decripts it otherwise.
	 * 
	 * @param originalFile - File which is to be encripted/decripted
	 * @param fileToProcess - path of the processed file
	 * @param password - password by which we encripted/decripted
	 * @return true if success and false otherwise
	 */
	private boolean encryptOrDecript(File originalFile,String fileToProcess, String password) throws Exception{
		boolean isSuccessful = true;
		BufferedOutputStream wBr = null;
		BufferedInputStream stream = null;
		FileInputStream fstream = null;
		
		if(originalFile.getPath().equals(fileToProcess)){
			JOptionPane.showMessageDialog(selector,"Do not use the same name for the file to be encripted and file being encripted", "User Error",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		try{
			// Open the file that is the first 
			fstream = new FileInputStream(originalFile);
			
			if (null != fstream){
				stream = new BufferedInputStream(fstream);
				wBr = new BufferedOutputStream(new FileOutputStream(getFile(fileToProcess)));
				
				byte[] passwordArr= password.getBytes();
				
				int strongPsw = strengthenPassword(passwordArr);
				
				int encodedFileContents=0;
				int readChar=0;
				
				while ((readChar = stream.read()) != -1)   {
					encodedFileContents = readChar^strongPsw;
					wBr.write(encodedFileContents);
				}
			}else{
				System.out.println("!No file chosen!");
			}
			
			if (wBr != null) {
				wBr.flush();
				wBr.close();
				
				stream.close();
				fstream.close();
			}
			
			// preventing from deleting the created file
			originalFile.delete();
		}catch (Exception e){
			throw e;
		}finally {
			try {
				if (wBr != null) {
					wBr.flush();
					wBr.close();
					
					stream.close();
					fstream.close();
				}
				return isSuccessful;
			} catch (IOException ex) {
				ex.printStackTrace();
				isSuccessful = false;
				
			}
		}
		return isSuccessful;
	}
	/**
	 * Instantiate the file and create a new file if not created yet.
	 * @param FileName
	 * @return file
	 */
	private File getFile(String FileName){
		File file = new File(FileName);
		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	
	/**
	 * Takes each byte and combines it to make the password more complex
	 * @param passwordArray - password as Byte array 
	 * @return Strong password
	 */
	private int strengthenPassword( byte[] passwordArray){
		int strongPsw = 1;
		int arraylength = passwordArray.length-1;
		for(int index = 0; index<arraylength; index++){
			strongPsw=strongPsw^passwordArray[index];
		}
		return strongPsw;
	}
	private String getFileExtension(File originalFile){
		int fileExtIndex = originalFile.getPath().lastIndexOf('.');
		return originalFile.getPath().substring(fileExtIndex);
	}
	
	private String getFilePath(File originalFile){
		int fileExtIndex = originalFile.getPath().lastIndexOf('\\');
		return originalFile.getPath().substring(0,fileExtIndex);
	}
	
	public boolean process(File originalFile,String encriptFileName,String password) throws Exception{
		
		String fileExt = getFileExtension(originalFile);
		String encriptFile = getFilePath(originalFile)+"\\"+encriptFileName+ fileExt;
		//String decriptFile = "D:/DecryptFilePJ"+ fileExt;
		boolean isSucessful = false;
		try { 
			isSucessful = encryptOrDecript(originalFile, encriptFile, password);
			return isSucessful;
			
		}catch (Exception e) {
			throw e;
		} finally{
			originalFile = null;
			//			System.exit(1);
		}
	}
	
}
