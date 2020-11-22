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

import model.Decrypter;
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

					if(pass != null && !pass.equals("")) {
					Encrypter encrypter = new Encrypter(pass, fileChooser.getSelectedFile());
					encrypter.encrypt();
					}
					//

				} else {

					JOptionPane.showMessageDialog(null, "A file hasnt been selected", "Error",
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
					
					if(pass != null && !pass.equals("")) {
					Decrypter decrypter = new Decrypter (pass, fileChooser.getSelectedFile());
					decrypter.decrypt();
					}
					
					//
					
					
				

				} else {

					JOptionPane.showMessageDialog(null, "A file hasnt been selected", "Error",
							JOptionPane.ERROR_MESSAGE);

				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}

		});

	}

}
