package tools;

import java.awt.Image;
import java.io.File;
import java.util.HashMap;

public class FileManager {

	public static final String PATH = System.getProperty("user.home") + "/EncryptedFiles/";
	public static final HashMap<String, Image> imagenes = new HashMap<>();

	public static void loadResources() {

		imagenes.put("ICON", ImageLoader.cargarImagen("images/icon.png"));
		imagenes.put("ENCRYPT", ImageLoader.cargarImagen("images/encrypt.png"));
		imagenes.put("DECRYPT", ImageLoader.cargarImagen("images/decrypt.png"));

	}

	public static void createDirectory() {

		File file = new File(PATH);
		file.mkdirs();

	}

}
