package view;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import model.Encrypter;
import tools.FileManager;

public class MainWindow extends JFrame {

	private JButton butEncrypt;
	private JButton butDecrypt;

	public MainWindow() {

		FileManager.createDirectory();
		FileManager.loadResources();

		setTitle("Encrypter/Decrypter");
		setLayout(new GridLayout(2, 1));
		setSize(400, 250);
		setResizable(false);
		setIconImage(FileManager.imagenes.get("ICON"));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		try {

			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		setComponents();
		listeners();

	}

	private void setComponents() {

		Font font = new Font("Garamond", 1, 32);

		butEncrypt = new JButton("Encrypt", new ImageIcon(FileManager.imagenes.get("ENCRYPT")));
		butEncrypt.setVerticalTextPosition(AbstractButton.CENTER);
		butEncrypt.setHorizontalTextPosition(AbstractButton.RIGHT);
		butEncrypt.setFont(font);
		add(butEncrypt);

		butDecrypt = new JButton("Decrypt", new ImageIcon(FileManager.imagenes.get("DECRYPT")));
		butDecrypt.setVerticalTextPosition(AbstractButton.CENTER);
		butDecrypt.setHorizontalTextPosition(AbstractButton.RIGHT);
		butDecrypt.setFont(font);
		add(butDecrypt);

	}

	private void listeners() {

		butEncrypt.addActionListener(e -> {

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.showOpenDialog(this);

			try {
				if (fileChooser.getSelectedFile() != null) {

					String pass = JOptionPane.showInputDialog("Enter password");

					// Encrypt

					Encrypter encrypter = new Encrypter(pass, fileChooser.getSelectedFile());
					encrypter.encrypt();
					JOptionPane.showMessageDialog(this,
							"The encrypted file was saved in\n" + FileManager.PATH + "/EncryptedFiles/", "File Encrypted",
							JOptionPane.INFORMATION_MESSAGE);

					//

				} else {

					JOptionPane.showMessageDialog(null, "No se ha seleccionado un archivo", "Error",
							JOptionPane.ERROR_MESSAGE);

				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}

		});

		butDecrypt.addActionListener(e -> {

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.showOpenDialog(this);

			try {
				if (fileChooser.getSelectedFile() != null) {

					String pass = JOptionPane.showInputDialog("Enter password");

					// Decrypt

					// SOLAMENTE ES CAMBIAR LO DE NOSOTROS Y YA :)
					// Y HACERLO EN LA CLASE DECRYPTER :)
					//

				} else {

					JOptionPane.showMessageDialog(null, "No se ha seleccionado un archivo", "Error",
							JOptionPane.ERROR_MESSAGE);

				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}

		});

	}

}
