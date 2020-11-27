package model;

import org.apache.commons.codec.binary.Hex;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Clase para generar una clave para encriptar a partir de una password.
 */
public class KeyGenerator {

	/**
	 * La password para generar la clave.
	 */
	private String password;
	
	/**
	 * Informacion adicional para reforzar la encripcion.
	 */
	private String salt;
	
	/**
	 * Numero de iteraciones para reforzar la encripcion.
	 */
	private int iterations;
	
	/**
	 * El tamanio de la clave.
	 */
	private int keyLength;
	
	/**
	 * La contrasenia en chars.
	 */
	private char[] passwordChars;
	
	/**
	 * El salt como un arreglo de bytes.
	 */
	private byte[] saltBytes;
	
	/**
	 * La password como bytes.
	 */
	private byte[] key;

	/**
	 * Crea una KeyGenerator con una password.
	 * @param password La password con la que se generara la clave.
	 */
	public KeyGenerator(String password) {

		this.password = password;
		salt = "1234";
		iterations = 10000;
		keyLength = 128;
		passwordChars = password.toCharArray();
		saltBytes = salt.getBytes();

		hashPassword();
		printKey();
	}

	/**
	 * Genera la clave a partir de la password. La clave queda disponible en la variable {@code key}
	 */
	private void hashPassword() {

		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, iterations, keyLength);
			SecretKey key = skf.generateSecret(spec);
			byte[] res = key.getEncoded();
			this.key = res;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
	}

	
	/**
	 * Imprime la clave generada
	 */
	private void printKey() {

		String hashedString = Hex.encodeHexString(key);

		System.out.println("Key Generated = " + hashedString);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public int getKeyLength() {
		return keyLength;
	}

	public void setKeyLength(int keyLength) {
		this.keyLength = keyLength;
	}

	public char[] getPasswordChars() {
		return passwordChars;
	}

	public void setPasswordChars(char[] passwordChars) {
		this.passwordChars = passwordChars;
	}

	public byte[] getSaltBytes() {
		return saltBytes;
	}

	public void setSaltBytes(byte[] saltBytes) {
		this.saltBytes = saltBytes;
	}

	public byte[] getKey() {
		return key;
	}

	public void setKey(byte[] key) {
		this.key = key;
	}

}