package com.wbu.ui;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;

public class MainWindow extends JPanel {
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;

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
		setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("数据采集");
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("批量导入");
		toolBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("数据导出");
		toolBar.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("文件上传");
		toolBar.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("数据处理");
		toolBar.add(btnNewButton_4);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new CardLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, "name_9348581800000");
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("查询条件");
		lblNewLabel.setBounds(10, 21, 54, 15);
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(85, 18, 66, 21);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(170, 17, 34, 23);
		panel_1.add(comboBox);
		
		JButton btnNewButton_5 = new JButton("查询");
		btnNewButton_5.setBounds(219, 17, 93, 23);
		panel_1.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("导入");
		btnNewButton_6.setBounds(316, 17, 93, 23);
		panel_1.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("导出");
		btnNewButton_7.setBounds(419, 17, 93, 23);
		panel_1.add(btnNewButton_7);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(25, 64, 487, 257);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 5, 460, 242);
		panel_2.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "1", "1", "1", null},
			},
			new String[] {
				"\u6570\u636E\u91C7\u96C6", "\u6279\u91CF\u5BFC\u5165", "\u6570\u636E\u5BFC\u51FA", "\u6587\u4EF6\u4E0A\u4F20", "\u6570\u636E\u5904\u7406"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_3, null);
		panel_3.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 5, 666, 351);
		panel_3.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(60, 31, 54, 15);
		panel_4.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(143, 28, 66, 21);
		panel_4.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(60, 84, 54, 15);
		panel_4.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(143, 81, 66, 21);
		panel_4.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(60, 151, 54, 15);
		panel_4.add(lblNewLabel_3);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"空指针", "数据越界"}));
		comboBox_1.setBounds(143, 147, 34, 23);
		panel_4.add(comboBox_1);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(60, 212, 54, 15);
		panel_4.add(lblNewLabel_4);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.setBounds(153, 208, 121, 23);
		panel_4.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_1.setBounds(320, 208, 121, 23);
		panel_4.add(rdbtnNewRadioButton_1);
		
		JButton btnNewButton_8 = new JButton("New button");
		btnNewButton_8.setBounds(60, 282, 93, 23);
		panel_4.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("New button");
		btnNewButton_9.setBounds(223, 282, 93, 23);
		panel_4.add(btnNewButton_9);

	}
}
