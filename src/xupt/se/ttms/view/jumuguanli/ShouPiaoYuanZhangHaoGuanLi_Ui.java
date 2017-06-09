package xupt.se.ttms.view.jumuguanli;


import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
//import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
//import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import xupt.se.ttms.dao.shoupiaoyuanDAO;

public class ShouPiaoYuanZhangHaoGuanLi_Ui {
	
	private DefaultTableModel tableModel_ShouPiaoYuan;
	private JTable jTable_shoupiaoyuan;
	private JTextField jTextField_id;
	private JTextField jTextField_gonghao;
	private JTextField jTextField_xingming;
	private JTextField jTextField_dianhua;
	private JTextField jTextField_dizhi;
	private JTextField jTextField_youxiang;

	public JTabbedPane ShouPiaoYuanZhangHaoGuanLi(){
		
		String[] biaotou = {"id","工号","姓名","电话号码","地址",
				"邮箱"};
		shoupiaoyuanDAO sp= new shoupiaoyuanDAO();
		Object[][] objects_shou = new Object[100][6];
		int row = sp.select(objects_shou);
		objects_shou = new Object[row][6];
		sp.select(objects_shou);
//		JTabbedPane jPanel = ShouPiaoYuan(biaotou, objects_shou);
		JTabbedPane jTabbedPane = new JTabbedPane();
		jTabbedPane.add("员工信息", ShouPiaoYuan(biaotou, objects_shou));
		return jTabbedPane;
	}
	
	public JPanel ShouPiaoYuan(String[] biaotou,Object[][] chengyuan){
		JPanel jPanel = new JPanel();
		jPanel.setBackground(Color.orange);
		
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		Insets insets = new Insets(10, 5, 0, 5);
		gridBagConstraints.insets = insets;
		
		JLabel jLabel = new JLabel("员工信息");
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		jPanel.add(jLabel, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		jTable_shoupiaoyuan = ZhiBiao_ShouPiaoYuan(biaotou,chengyuan);
		jTable_shoupiaoyuan.setBackground(Color.pink);
		jTable_shoupiaoyuan.addMouseListener(new MouseAdapter() {//表格添加点击事件

			@Override
		public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				int select1 = jTable_shoupiaoyuan.getSelectedRow();  	//获取被选中的行
				Object oa = tableModel_ShouPiaoYuan.getValueAt(select1, 0);
				Object ob = tableModel_ShouPiaoYuan.getValueAt(select1, 1);
				Object oc = tableModel_ShouPiaoYuan.getValueAt(select1, 2);
				Object od = tableModel_ShouPiaoYuan.getValueAt(select1, 3);
				Object oe = tableModel_ShouPiaoYuan.getValueAt(select1, 4);
				Object of = tableModel_ShouPiaoYuan.getValueAt(select1, 5);
				
				jTextField_id.setText(oa.toString());
				jTextField_gonghao.setText(ob.toString());
				jTextField_xingming.setText(oc.toString());
				jTextField_dianhua.setText(od.toString());
				jTextField_dizhi.setText(oe.toString());
				jTextField_youxiang.setText(of.toString());
				
			}	
			
		});
		
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 120;
		gridBagConstraints.weighty = 100;
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(jTable_shoupiaoyuan);
		jScrollPane.setBackground(Color.orange);
		jPanel.add(jScrollPane, gridBagConstraints);
		
		
		JPanel jPanel5 = new JPanel();
		jPanel5.setBackground(Color.orange);
		
		jPanel5.setLayout(new GridLayout(4,0));
		JPanel jPanel2 = new JPanel();
		jPanel2.setLayout(new FlowLayout());
		
		jPanel2.add(new JLabel("id:"));
		jTextField_id = new JTextField(12);
		jPanel2.add(jTextField_id);
		
		jPanel2.add(new JLabel("工号:"));
		jTextField_gonghao = new JTextField(15);
		jPanel2.add(jTextField_gonghao);
		
		jPanel2.add(new JLabel("姓名:"));
		jTextField_xingming = new JTextField(15);
		jPanel2.add(jTextField_xingming);
		
		JPanel jPanel21 = new JPanel();
		jPanel21.setLayout(new FlowLayout());
		
		jPanel21.add(new JLabel("电话号码:"));
		jTextField_dianhua = new JTextField(15);
		jPanel21.add(jTextField_dianhua);
		
		jPanel21.add(new JLabel("地址:"));
		jTextField_dizhi = new JTextField(15);
		jPanel21.add(jTextField_dizhi);
		
		jPanel21.add(new JLabel("邮箱:"));
		jTextField_youxiang = new JTextField(15);
		jPanel21.add(jTextField_youxiang);
		
		JPanel jPanel3 = new JPanel();
		jPanel3.setLayout(new FlowLayout());
		
		final JButton jButton_shoupiaoyuan = new JButton("添加售票员");
		jButton_shoupiaoyuan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
						String[] ra = {jTextField_id.getText(),
								jTextField_gonghao.getText(),
								jTextField_xingming.getText(),
								jTextField_dianhua.getText(),
								jTextField_dizhi.getText(),
								jTextField_youxiang.getText(),
						};				
						tableModel_ShouPiaoYuan.addRow(ra);
			            shoupiaoyuanDAO ss = new shoupiaoyuanDAO();
			            ss.insert(jTextField_gonghao.getText(),
			                    jTextField_xingming.getText(), jTextField_dianhua.getText(),
			                    jTextField_dizhi.getText(), jTextField_youxiang.getText()
			                    );
					}

				});
				

		
		final JButton jButton_shoupiaoyuan_Xiuagi = new JButton("售票员信息修改");
		jButton_shoupiaoyuan_Xiuagi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int select = jTable_shoupiaoyuan.getSelectedRow();//判断被选中的某行
				if(select != -1){

					tableModel_ShouPiaoYuan.setValueAt(jTextField_id.getText(),
							select, 0);
					tableModel_ShouPiaoYuan.setValueAt(jTextField_gonghao.getText(),
							select, 1);
					tableModel_ShouPiaoYuan.setValueAt(jTextField_xingming.getText(),
							select, 2);
					tableModel_ShouPiaoYuan.setValueAt(jTextField_dianhua.getText(),
							select, 3);
					tableModel_ShouPiaoYuan.setValueAt(jTextField_dizhi.getText(),
							select, 4);
					tableModel_ShouPiaoYuan.setValueAt(jTextField_youxiang.getText(),
							select, 5);
				}
                shoupiaoyuanDAO ss = new shoupiaoyuanDAO();
                ss.update(jTextField_id.getText(), jTextField_gonghao.getText(),
                        jTextField_xingming.getText(), jTextField_dianhua.getText(),
                        jTextField_dizhi.getText(), jTextField_youxiang.getText()
                        );

                //Main_Ui gongZuo_Ui = new Main_Ui();
			}
		});
		
		final JButton jButton_shoupiao_shanchu = new JButton("删除售票员");
		jButton_shoupiao_shanchu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int select = jTable_shoupiaoyuan.getSelectedRow();
				if(select != -1){
					tableModel_ShouPiaoYuan.removeRow(select);
				}
				shoupiaoyuanDAO ss=new shoupiaoyuanDAO();
				ss.delete(jTextField_id.getText());
			}
		});
		
		jPanel3.add(jButton_shoupiaoyuan);
		jPanel3.add(jButton_shoupiaoyuan_Xiuagi);
		jPanel3.add(jButton_shoupiao_shanchu);
		jPanel5.add(jPanel2);
		jPanel5.add(jPanel21);
		//jPanel5.add(jPanel22);
		jPanel5.add(jPanel3);
		
		gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=2;
        gridBagConstraints.weightx = 10;
		gridBagConstraints.weighty = 10;
        jPanel.add(jPanel5, gridBagConstraints);
		return jPanel;
	}
	
	public JTable ZhiBiao_ShouPiaoYuan(String[] biaotou,Object[][] chengyuan){
		
		tableModel_ShouPiaoYuan = new DefaultTableModel(chengyuan, biaotou);
		JTable jTable1 = new JTable(tableModel_ShouPiaoYuan);
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
