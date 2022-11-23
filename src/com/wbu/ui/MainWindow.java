package com.wbu.ui;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MainWindow extends JPanel {

	/**
	 * Create the panel.
	 */public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						
						JFrame frame =new JFrame("test windows application");
				        frame.setContentPane(new MainWindow());
				        frame.pack();
				        frame.setVisible(true);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	public MainWindow() {
		setLayout(null);
		
		JButton btnNewButton = new JButton("tanoshine!!");
		btnNewButton.setBounds(102, 139, 189, 69);
		add(btnNewButton);

	}
}
