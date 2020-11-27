package model;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

import tools.FileManager;

/**
 * Clase para desencriptar un archivo con una password.
 */
public class Decrypter {
	
	/**
	 * La password con la que se desencriptará el archivo.
	 */
	private String pass;
	
	/**
	 * El archivo que se desea desencriptar.
	 */
	private File file;
	
	/**
	 * El objeto de tipo KeyGenerator que genera la clave con la que se desencriptará el archivo.
	 */
	private KeyGenerator generator;

	
	/**
	 * Crea un objeto de tipo Decrypter con el se pueden desencriptar los archivos.
	 * @param pass La password con la que se va a encriptar el archivo {@code file}.
	 * @param file El archivo que se desea desencriptar.
	 */
	public Decrypter(String pass, File file) {

		this.pass = pass;
		this.file = file;
		generator = new KeyGenerator(pass);

	}

	
	/**
	 * Metodo que desencripta el archivo que se desea, por medio de la contraseña.
	 */
	public void decrypt() {

		try {

			Path path = Paths.get(file.getPath());
			final byte[] contents = Files.readAllBytes(path);

			byte[] raw = generator.getKey();

			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

			Cipher cipher = Cipher.getInstance("AES");

			cipher.init(Cipher.DECRYPT_MODE, skeySpec);

			byte[] decrypted = cipher.doFinal(contents);
			System.out.println(""+ decrypted.length);
			
			byte[] hashBytes= new byte[40];
			byte[] pureData= new byte[decrypted.length-hashBytes.length];
			
			
			//This for has been created to separate the hash bytes from the bytes of contents, so to be able 
			//to do the checksum and compare it with the one in the encrypted file.
			int aux=0;
			for(int i=0; i<decrypted.length; i++) {
				if(i<pureData.length) {
					pureData[i]= decrypted[i];
				}
				else {
					
					hashBytes[aux]=decrypted[i];
					aux++;
				}
			}
				
			
			String pathDecryptedFiles = FileManager.PATH + "DecryptedFiles/";
			File folder = new File(pathDecryptedFiles);
			folder.mkdirs();			

			final Path newFile = Paths.get(pathDecryptedFiles + file.getName());

			System.out.println("Decrypted string: " + decrypted.toString());
			Files.write(newFile, pureData, StandardOpenOption.CREATE);
			System.out.println(newFile);
			
			
			JOptionPane.showMessageDialog(null,
					"The decrypted file was saved in\n" + FileManager.PATH + "/DecryptedFiles/", "File Decrypted",
					JOptionPane.INFORMATION_MESSAGE);
			
			String preEnc= new String(hashBytes);
			String postDes=Checksum.checksum(pathDecryptedFiles + file.getName(), MessageDigest.getInstance("SHA-1"));
			
			if(preEnc.equals(postDes)) {
				JOptionPane.showMessageDialog(null,
						"The hashes match, there has been no modification of the file", "Success!",
						JOptionPane.INFORMATION_MESSAGE);
				
			}else {
				JOptionPane.showMessageDialog(null,
						"The hashes doesnt match, there has been a modification of the file", "Error",
						JOptionPane.INFORMATION_MESSAGE);
				
				
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "A problem has ocurred while decrypting the file");
			
		}

	}

}