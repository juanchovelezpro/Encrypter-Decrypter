package tools;

import java.awt.Image;
import java.io.File;
import java.util.HashMap;

/**
 * Clase para manejar los archivos.
 *
 */
public class FileManager {

	/**
	 * Ruta en la que se guardaran los archivos encriptados.
	 */
	public static final String PATH = System.getProperty("user.home") + "/Encrypter-Decrypter/";

	/**
	 * Hashmap para almacenar y referencias las imagenes del proyecto.
	 */
	public static final HashMap<String, Image> imagenes = new HashMap<>();

	/**
	 * Carga los recursos (imagenes) del proyecto
	 */
	public static void loadResources() {

		imagenes.put("ICON", ImageLoader.cargarImagen("images/icon.png"));
		imagenes.put("ENCRYPT", ImageLoader.cargarImagen("images/encrypt.png"));
		imagenes.put("DECRYPT", ImageLoader.cargarImagen("images/decrypt.png"));

	}

	/**
	 * Crea el directorio donde se guardaran los archivos encriptados y
	 * desencriptados.
	 */
	public static void createDirectory() {

		File file = new File(PATH);
		file.mkdirs();

	}

}
