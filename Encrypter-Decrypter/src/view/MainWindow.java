package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileInputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class MainWindow extends JFrame {

	private JButton butEncrypt;
	private JButton butDecrypt;

	public MainWindow() {

		setTitle("Encrypter/Decrypter");
		setLayout(new GridLayout(2, 1));
		setSize(400, 250);
		setResizable(false);
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

		butEncrypt = new JButton("Encrypt");
		butEncrypt.setFont(font);
		add(butEncrypt);

		butDecrypt = new JButton("Decrypt");
		butDecrypt.setFont(font);
		add(butDecrypt);

	}

	private void listeners() {

		butEncrypt.addActionListener(e -> {

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.showOpenDialog(this);

			FileInputStream fs = null;

			try {
				if (fileChooser.getSelectedFile() != null) {
					fs = new FileInputStream(fileChooser.getSelectedFile());
					
					String pass = JOptionPane.showInputDialog("Enter password");
					
					
					// Encrypt
					
					
					
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

			FileInputStream fs = null;

			try {
				if (fileChooser.getSelectedFile() != null) {
					fs = new FileInputStream(fileChooser.getSelectedFile());
					
					String pass = JOptionPane.showInputDialog("Enter password");
					
					// Decrypt
					
					
					
					
					
					
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
