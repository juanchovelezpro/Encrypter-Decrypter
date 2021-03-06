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
 *  Clase para encriptar un archivo con una password.
 */
public class Encrypter {

	/**
	 * La password con la que se encriptara un archivo.
	 */
	private String pass;
	
	/**
	 * El archivo que se desea encriptar.
	 */
	private File file;
	
	/**
	 * El objeto {@code KeyGenerator} que se utiliza para generar la clave con la que se encriptara el archivo {@code file}
	 */
	private KeyGenerator generator;

	
	/**
	 * Crea un objeto {@code Encrypter} con el se pueden encriptar los archivos.
	 * @param pass La password con la que se va a encriptar el archivo {@code file}
	 * @param file El archivo que se va a encriptar
	 */
	public Encrypter(String pass, File file) {

		this.pass = pass;
		this.file = file;
		generator = new KeyGenerator(pass);

	}

	
	/**
	 * Metodo que encripta el archivo {@code file} con la password {@code password}
	 */
	public void encrypt() {

		try {

			Path path = Paths.get(file.getPath());
			byte[] contents = Files.readAllBytes(path);
			
			String hash = Checksum.checksum(file.getPath(), MessageDigest.getInstance("SHA-1"));

			System.out.println("HASH = " + hash);
			System.out.println("Length Hash = "   + hash.getBytes().length);
			
			byte[] hashBytes = hash.getBytes();
			
			byte[] contentsWithHash = new byte[contents.length+hashBytes.length];
			
			
			int aux = 0;
			
			//This for its made in order to merge the bytes resulting from the calculated 
			//hash with the ones of the encrypted file 
			for(int i = 0; i< contentsWithHash.length; i++) {
				
				if(i < contents.length) {
					
					contentsWithHash[i] = contents[i];
					
				}else {
					
					contentsWithHash[i] = hashBytes[aux];
					aux++;
					
				}
				
			}
			
			
			byte[] raw = generator.getKey();

			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

			Cipher cipher = Cipher.getInstance("AES");

			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

			byte[] encrypted = cipher.doFinal(contentsWithHash);
			
			String pathEncryptedFiles = FileManager.PATH + "EncryptedFiles/";
			File folder = new File(pathEncryptedFiles);
			folder.mkdirs();			

			final Path newFile = Paths.get(pathEncryptedFiles + file.getName());

			System.out.println("Encrypted string: " + encrypted.toString());
			Files.write(newFile, encrypted, StandardOpenOption.CREATE);
			System.out.println(newFile);
			
			JOptionPane.showMessageDialog(null,
					"The encrypted file was saved in\n" + FileManager.PATH + "/EncryptedFiles/", "File Encrypted",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "A problem has ocurred while encrypting the file");
		}

	}
}
