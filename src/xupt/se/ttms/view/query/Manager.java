package xupt.se.ttms.view.query;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


import xupt.se.ttms.model.DataDict;
//import query.TicketQuery;
import xupt.se.ttms.sql.Data_Dict;
import xupt.se.ttms.sql.Play;
import xupt.se.util.Data_DictInformation;
import xupt.se.util.PlayInformation;

public class Manager {

	public static JButton button1;
	public static JButton button2;
	public static JButton button3;
	public static JTextField text1;
	public static JTextField text2;
	public static JTextField text3;
	public static JTabbedPane jTabbedPane_chaxun;
	private DefaultTableModel tableModel_xinpin;
	private JTable jTable_Xinpin;
	private JTabbedPane jTabbedPane = new JTabbedPane();
	private JPanel tmp;

	
	
	public JTabbedPane Manager(){

		jTabbedPane_chaxun = new JTabbedPane();
		
//		text1 = new JTextField(20);
//		button1 = new JButton("查询");
		
		text2 = new JTextField(20);
		button2 = new JButton("查询");
		
		text3 = new JTextField(20);
		button3 = new JButton("查询");
		
//		button1.addActionListener(new ActionListener() {
//			    int id=0;
//			//@Override
//			public void actionPerformed(ActionEvent arg0) {
//
//				// TODO Auto-generated method stub
//				String type=text1.getText();   
//				System.out.println("type="+type);  
//				Data_Dict data_Dict=new Data_Dict();
//				Play play=new Play();
//				DataDict dataDict = new DataDict();
//				id= data_Dict.printfDictName1(DataDict.getDict_name(type));
////				System.out.println("id="+id);  
//				init(String.valueOf(id));
////				init(type);
//				jTabbedPane_chaxun.remove(0);
//				jTabbedPane_chaxun.add("新品上市", tmp);
//				
//				jTabbedPane_chaxun.repaint();
//				jTabbedPane_chaxun.validate();
////				Manager.this.repaint();
////				Manager.this.validate();
//			}
//
//		});		
		
		button2.addActionListener(new ActionListener() {
		    
		//@Override
		public void actionPerformed(ActionEvent arg0) {

			// TODO Auto-generated method stub
			String type=text2.getText();   
			System.out.println("type="+type);  
		
			init(type);
			jTabbedPane_chaxun.remove(0);
			jTabbedPane_chaxun.add("新品上市", tmp);
			
			jTabbedPane_chaxun.repaint();
			jTabbedPane_chaxun.validate();
//			Manager.this.repaint();
//			Manager.this.validate();
		}

	});		
		
		
		button3.addActionListener(new ActionListener() {
		    int id=0;
		//@Override
		public void actionPerformed(ActionEvent arg0) {

			// TODO Auto-generated method stub
			String type=text3.getText();   
			System.out.println("type="+type);  
			Data_Dict data_Dict=new Data_Dict();
			Play play=new Play();
			DataDict dataDict = new DataDict();
			id= data_Dict.printfDictName1(DataDict.getDict_name(type));
//			System.out.println("id="+id);  
			init(String.valueOf(id));
//			init(type);
			jTabbedPane_chaxun.remove(0);
			jTabbedPane_chaxun.add("新品上市", tmp);
			
			jTabbedPane_chaxun.repaint();
			jTabbedPane_chaxun.validate();
//			Manager.this.repaint();
//			Manager.this.validate();
		}

	});		
		
		
		
		
		
		init(null);
		
		jTabbedPane_chaxun.add("新品上市", tmp);
		jTabbedPane_chaxun.setBackground(Color.red);
		jTabbedPane_chaxun.setTabPlacement(JTabbedPane.TOP);
		
		
		jTabbedPane.add("查询", jTabbedPane_chaxun);
		jTabbedPane.setBackground(Color.green);
		
		jTabbedPane.setTabPlacement(JTabbedPane.LEFT);
		
		jTabbedPane_chaxun.repaint();
		jTabbedPane_chaxun.validate();
//		this.add(jTabbedPane); 
//		this.setVisible(true);
		
		return jTabbedPane_chaxun;
	}
        //dict_id=play_type_id
	private void init(String type){
		Data_Dict data_Dict=new Data_Dict();
		Play play = new Play();
		List<PlayInformation> list = null;
		if(type==null)
			list = new ArrayList<PlayInformation>();
		else
			list = play.prinfPlay2(type);
		    list = play.prinfPlay1(type);
		    list = play.prinfPlay3(type);
		Object[][] objects_xinpin = new Object[list.size()][6];
		for(int i = 0; i < list.size(); i++){
			PlayInformation playInformation = list.get(i);

				objects_xinpin[i][0] = playInformation.getPlay_name();
//				objects_xinpin[i][1] = playInformation.getPlay_type_id();
//				objects_xinpin[i][2] = playInformation.getPlay_lang_id();
				//printfDictName根据id得name
				objects_xinpin[i][1] = data_Dict.printfDictName(Integer.parseInt(playInformation.getPlay_type_id()));
				objects_xinpin[i][2] = data_Dict.printfDictName(Integer.parseInt(playInformation.getPlay_lang_id()));
				objects_xinpin[i][3] = playInformation.getPlay_length();
				objects_xinpin[i][4] = playInformation.getPlay_introduction();
				objects_xinpin[i][5] = playInformation.getPlay_ticket_price();
				

		}
		
		String[] strings_xinpin  = {"影片名称","影片类型","语言","时长","影片介绍","票价"};
		tmp=JuMu_XinPin(strings_xinpin, objects_xinpin);
				
	}
	
	public JPanel JuMu_XinPin(String[] biaotou,Object[][] chengyuan){
		JPanel jPanel = new JPanel();
		jPanel.setBackground(Color.orange);
		
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		Insets insets = new Insets(10, 5, 0, 5);
		gridBagConstraints.insets = insets;
		
		JLabel jLabel = new JLabel("新品列表");
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		jPanel.add(jLabel, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		jTable_Xinpin = ZhiBiao_xinpin(biaotou,chengyuan);
		
		if(chengyuan!=null){
			
		
		jTable_Xinpin.setBackground(Color.pink);
		
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 120;
		gridBagConstraints.weighty = 800;
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(jTable_Xinpin);
		jScrollPane.setBackground(Color.orange);
		jPanel.add(jScrollPane, gridBagConstraints);

		}
		
		//下面的面板
		JPanel jPanel5 = new JPanel();
		jPanel5.setBackground(Color.gray);
		jPanel5.setLayout(new GridLayout(3,0));
		
//		JPanel jPanel2 = new JPanel();
//		jPanel2.setLayout(new FlowLayout());
//		jPanel2.add(new JLabel("影片类型:"));
//		jPanel2.add(text1);
//		jPanel2.add(button1);
//		jPanel5.add(jPanel2);
		
		JPanel jPanel3 = new JPanel();
		jPanel3.setLayout(new FlowLayout());
		jPanel3.add(new JLabel("影片名称:"));
		jPanel3.add(text2);
		jPanel3.add(button2);
		jPanel5.add(jPanel3);
		
		JPanel jPanel4 = new JPanel();
		jPanel4.setLayout(new FlowLayout());
		jPanel4.add(new JLabel("影片语言:"));
		jPanel4.add(text3);
		jPanel4.add(button3);
		jPanel5.add(jPanel4);
	
		gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=2;
        gridBagConstraints.weightx = 10;
		gridBagConstraints.weighty = 10;
        jPanel.add(jPanel5, gridBagConstraints);
		
		return jPanel;
	}

	public JTable ZhiBiao_xinpin(String[] biaotou,Object[][] chengyuan){   //表的信息
		
		if(chengyuan==null)
			return null;
	
		tableModel_xinpin = new DefaultTableModel(chengyuan, biaotou);
		JTable jTable1 = new JTable(tableModel_xinpin);
		JTableHeader header = new JTableHeader();
		header = jTable1.getTableHeader();
		header.setBackground(Color.blue);
		jTable1.setRowHeight(30);
		jTable1.setGridColor(Color.red);
	
		//设置表格为单选模式
		jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
		DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		jTable1.setDefaultRenderer(Object.class,   r);
	
		//设置表头居中
		JTableHeader tbh = jTable1.getTableHeader();
		DefaultTableCellRenderer  renderer = (DefaultTableCellRenderer)tbh.getDefaultRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
	
		//设置选定后的颜色
		jTable1.setSelectionBackground(Color.green);
	
		//表周围的颜色
		Container c = jTable1.getParent();
		if(c instanceof JViewport){
			JViewport jp = (JViewport)c;
			jp.setBackground(new Color(100, 123, 313));
	}	
		return jTable1;
	}
}
