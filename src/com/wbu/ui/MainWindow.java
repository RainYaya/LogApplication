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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.wbu.dao.LogRecordDao;
import com.wbu.entity.GeneralEnumObject;
import com.wbu.entity.LogInfo;
import com.wbu.entity.LogInfoVO;
import com.wbu.entity.LogTableModel;
import com.wbu.server.LogServer;
import com.wbu.service.LogService;
import com.wbu.utils.FileProcessUtil;
import com.wbu.utils.PropertiesSinletionVialnnerClass;

import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.LogRecord;
import java.awt.event.ActionEvent;

import javax.print.attribute.standard.Chromaticity;
import javax.swing.ButtonGroup;

public class MainWindow extends JPanel {
	private JTextField conditionTextField;
	private JTable table;
	private JTextField ipAddrText;
	private JTextField dateText;
	JScrollPane scrollPane = new JScrollPane();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	PropertiesSinletionVialnnerClass propinstance=PropertiesSinletionVialnnerClass.getInstance();   

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
		
		JButton btnNewButton = new JButton("????????????");
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("????????????");
		toolBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("????????????");
		toolBar.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("????????????");
		toolBar.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("????????????");
		toolBar.add(btnNewButton_4);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new CardLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, "name_9348581800000");
		
		JPanel panel_1 = new JPanel();
		panel_1.setToolTipText("");
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("????????????");
		lblNewLabel.setBounds(10, 21, 54, 15);
		panel_1.add(lblNewLabel);
		
		conditionTextField = new JTextField();
		conditionTextField.setBounds(85, 18, 66, 21);
		panel_1.add(conditionTextField);
		conditionTextField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ip??????", "????????????", "????????????", "????????????"}));
		comboBox.setBounds(161, 17, 87, 23);
		panel_1.add(comboBox);
		
		JButton btnNewButton_5 = new JButton("??????");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<String,String> conditionMap=new HashMap<String,String>();
				String conditionText=conditionTextField.getText().trim();
				if(null != conditionText && !"".equals(conditionText)){
					conditionMap.put(GeneralEnumObject.GetValue(
							String.valueOf(comboBox.getSelectedItem())),
							conditionText);
				}
				
				LogService logService=new LogService();
				LogTableModel logTableModel=new LogTableModel(logService.businessProcess(
					new LogInfoVO(conditionMap)));
				
				table = new JTable(logTableModel);
				scrollPane.setViewportView(table);	
			}
		});
		btnNewButton_5.setBounds(275, 17, 93, 23);
		panel_1.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("??????");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser=new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

				int returnValue=chooser.showOpenDialog(null);

				if(returnValue==JFileChooser.APPROVE_OPTION){
					System.out.println("select filename:"+chooser.getSelectedFile().getName());
					FileProcessUtil.doBufferReadFile(chooser.getSelectedFile(),FileProcessUtil.basePath);

					// String sqlValueArray[]= FileProcessUtil.fileTransfer2Array(new File(FileProcessUtil.basePath+chooser.getSelectedFile().getName()));
					String sqlValueArray[]= FileProcessUtil.fileTransfer2Array(chooser.getSelectedFile());

					LogRecordDao logRecordDao=new LogRecordDao();
					logRecordDao.batchRecordLogInsert(sqlValueArray);
				}
			}
		});
		btnNewButton_6.setBounds(392, 17, 93, 23);
		panel_1.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("??????");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogRecordDao logRecordDao=new LogRecordDao();				
				
				String sql = "{call func_export_log_file111(?,?)}";
				String tableName = propinstance.getValue("tableName");

				String mysqlLogRootUploadPaht= logRecordDao.getMysqlPaht();
				File recodelog_file=new File(mysqlLogRootUploadPaht+tableName+".txt");
				Boolean state= recodelog_file.exists();
				if(state){
					recodelog_file.deleteOnExit();					
				}
				Boolean logExportState= logRecordDao.ExportRecordLog(sql,propinstance.getValue("databaseName"),tableName);
				
				if(logExportState){
										
					Path path= Paths.get(mysqlLogRootUploadPaht, tableName+".txt");
					String filePath=mysqlLogRootUploadPaht+tableName+".txt";
					File file=new File(filePath);
					FileProcessUtil.doBufferReadFile(file,propinstance.getValue("exportPath"));
				}
			}
		});

		btnNewButton_7.setBounds(518, 17, 93, 23);
		panel_1.add(btnNewButton_7);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(25, 64, 603, 337);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		
		scrollPane.setBounds(17, 5, 576, 322);
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
		
		JLabel lblNewLabel_1 = new JLabel("ip??????");
		lblNewLabel_1.setBounds(60, 31, 54, 15);
		panel_4.add(lblNewLabel_1);
		
		ipAddrText = new JTextField();
		ipAddrText.setBounds(143, 28, 66, 21);
		panel_4.add(ipAddrText);
		ipAddrText.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("????????????");
		lblNewLabel_2.setBounds(60, 84, 54, 15);
		panel_4.add(lblNewLabel_2);
		
		dateText = new JTextField();
		dateText.setBounds(143, 81, 66, 21);
		panel_4.add(dateText);
		dateText.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("????????????");
		lblNewLabel_3.setBounds(60, 151, 54, 15);
		panel_4.add(lblNewLabel_3);
		
		JComboBox recordTypeCombox = new JComboBox();
		recordTypeCombox.setModel(new DefaultComboBoxModel(new String[] {"???????????????", "??????????????????", "?????????????????????", "?????????????????????"}));
		recordTypeCombox.setBounds(143, 147, 80, 23);
		panel_4.add(recordTypeCombox);
		
		JLabel lblNewLabel_4 = new JLabel("????????????");
		lblNewLabel_4.setBounds(60, 212, 54, 15);
		panel_4.add(lblNewLabel_4);
		
		JRadioButton exceptionCheckedRButton = new JRadioButton("??????");
		buttonGroup.add(exceptionCheckedRButton);
		exceptionCheckedRButton.setBounds(153, 208, 121, 23);
		panel_4.add(exceptionCheckedRButton);
		
		JRadioButton infoCheckedRButton = new JRadioButton("??????");
		buttonGroup.add(infoCheckedRButton);
		infoCheckedRButton.setBounds(320, 208, 121, 23);
		panel_4.add(infoCheckedRButton);
		
		JButton btnNewButton_8 = new JButton("??????");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ipAddr=ipAddrText.getText().trim();
				String dateTimeText=dateText.getText().trim();
				String exceptionContent=GeneralEnumObject.GetValue(
					String.valueOf(recordTypeCombox.getSelectedItem()));
					
				String exceptionType=exceptionCheckedRButton.isSelected()?"??????":"??????";

				LogInfo lgInfo=new LogInfo.LoginfosBuilder(-1, ipAddr, dateTimeText)
									.setExceptionType(exceptionType)
									.setExceptionContent(exceptionContent)
									.build();
								
				LogService logService=new LogService();
				LogInfoVO resultVO= logService.businessProcess(new LogInfoVO(lgInfo));
				
				if(null!=resultVO.getLogInfo()){
					JOptionPane.showMessageDialog(
						null,
						"????????????",
						"??????????????????",
						JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(
						null,
						"????????????",
						"??????????????????",
						JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton_8.setBounds(60, 282, 93, 23);
		panel_4.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("??????");
		btnNewButton_9.setBounds(223, 282, 93, 23);
		panel_4.add(btnNewButton_9);

	}
}
