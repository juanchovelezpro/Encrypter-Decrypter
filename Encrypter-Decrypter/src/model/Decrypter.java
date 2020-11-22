package model;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

import tools.FileManager;

public class Decrypter {
	
	private String pass;
	
	
	private File file;
	
	
	private KeyGenerator generator;

	
	
	public Decrypter(String pass, File file) {

		this.pass = pass;
		this.file = file;
		generator = new KeyGenerator(pass);

	}

	
	
	public void decrypt() {

		try {

			Path path = Paths.get(file.getPath());
			final byte[] contents = Files.readAllBytes(path);

			byte[] raw = generator.getKey();

			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

			Cipher cipher = Cipher.getInstance("AES");

			cipher.init(Cipher.DECRYPT_MODE, skeySpec);

			byte[] decrypted = cipher.doFinal(contents);
			
			String pathDecryptedFiles = FileManager.PATH + "DecryptedFiles/";
			File folder = new File(pathDecryptedFiles);
			folder.mkdirs();			

			final Path newFile = Paths.get(pathDecryptedFiles + file.getName());

			System.out.println("Decrypted string: " + decrypted.toString());
			Files.write(newFile, decrypted, StandardOpenOption.CREATE);
			System.out.println(newFile);
			
			JOptionPane.showMessageDialog(null,
					"The decrypted file was saved in\n" + FileManager.PATH + "/DecryptedFiles/", "File Decrypted",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al desencriptar el archivo");
		}

	}

}
