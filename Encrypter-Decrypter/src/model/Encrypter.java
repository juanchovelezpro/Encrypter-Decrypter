package model;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import tools.FileManager;

public class Encrypter {

	String pass;
	File file;
	KeyGeneratorFile generator;

	public Encrypter(String pass, File file) {

		this.pass = pass;
		this.file = file;
		generator = new KeyGeneratorFile(pass);

	}

	public void encrypt() {

	

		try {

			Path path2 = Paths.get(file.getPath());
			final byte[] contents = Files.readAllBytes(path2);

			byte[] raw = generator.getKey();

			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

			Cipher cipher = Cipher.getInstance("AES");

			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

			byte[] encrypted = cipher.doFinal(contents.toString().getBytes());

			final Path newFile = Paths.get(FileManager.PATH+"Encrypted"+file.getName());

			System.out.println("encrypted string: " + encrypted.toString());
			Files.write(newFile, encrypted, StandardOpenOption.CREATE);
			System.out.println(newFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}
}
