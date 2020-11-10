package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainWindow extends JFrame{

	public MainWindow() {
		
		setTitle("Encrypter/Decrypter");
		setSize(450,450);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
}
