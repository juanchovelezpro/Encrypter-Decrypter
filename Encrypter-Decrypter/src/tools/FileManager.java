package tools;

import java.awt.Image;
import java.util.HashMap;

public class FileManager {

	public static final HashMap<String, Image> imagenes = new HashMap<>();

	public static void loadResources() {

		imagenes.put("ICON", ImageLoader.cargarImagen("images/icon.png"));
		imagenes.put("ENCRYPT", ImageLoader.cargarImagen("images/encrypt.png"));
		imagenes.put("DECRYPT", ImageLoader.cargarImagen("images/decrypt.png"));

	}

}
