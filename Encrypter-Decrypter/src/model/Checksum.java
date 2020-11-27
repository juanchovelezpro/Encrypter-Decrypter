package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;

/**
 * Clase que genera el valor de Checksum que se usa en el programa.
 */
public class Checksum {

	/**
	 * 
	 * @param filepath Ruta donde se encuentra el archivo.
	 * @param md Objeto de tipo MessageDigest que proporciona el algoritmo SHA-1
	 * @return Cadena de tipo String con el valor del checksum
	 * @throws IOException Exception que controla algun problema al momento de leer el archivo.
	 */
	public static String checksum(String filepath, MessageDigest md) throws IOException {

		// file hashing with DigestInputStream
		try (DigestInputStream dis = new DigestInputStream(new FileInputStream(filepath), md)) {
			while (dis.read() != -1)
				; // empty loop to clear the data
			md = dis.getMessageDigest();
		}

		// bytes to hex
		StringBuilder result = new StringBuilder();
		for (byte b : md.digest()) {
			result.append(String.format("%02x", b));
		}
		return result.toString();

	}

}