package xupt.se.ttms.view.movieInformation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;





import xupt.se.util.Data_DictInformation;
import xupt.se.util.PlayInformation;
import xupt.se.util.ScheduleInformation;
import xupt.se.util.Schedule_TimeInformation;
import xupt.se.util.SeatInformation;
import xupt.se.util.StudioInformation;
import xupt.se.util.TicketInformation;
import xupt.se.ttms.sql.Data_Dict;
import xupt.se.ttms.sql.MovieConnection;
import xupt.se.ttms.sql.Play;
import xupt.se.ttms.sql.Schedule;
import xupt.se.ttms.sql.Schedule_Time;
import xupt.se.ttms.sql.Seat;
import xupt.se.ttms.sql.Studio;
import xupt.se.ttms.sql.Ticket;
import xupt.se.ttms.view.login.LoginUI;
import xupt.se.ttms.view.progressBar.ProgressBarDemo;



public class MovieInformationUI extends JFrame{

	private String path = "D:/Android Data/CLWZZ/src/iamge/";
	private JTabbedPane jTabbedPane_Main;
	private JTabbedPane jTabbedPane_DingPioa;
	private String string = "";
	
	private DefaultTableModel tableModel_renqi1;
	private JTable jTable_renqi1;
	
	private DefaultTableModel tableModel_xinpin;
	private JTable jTable_Xinpin;
	
	private DefaultTableModel tableModel_YingPian;
	private JTable jTable_YingPian;
	
	private DefaultTableModel tableModel_tuijian;
	private JTable jTable_tuijian;
	
	private JLabel jLabel_Yingming = new JLabel();
	private JLabel jLabel_leixing = new JLabel();
	private JLabel jLabel_changdi = new JLabel();
	private JLabel jLabel_shichang = new JLabel();
	private JLabel jLabel_piaojia = new JLabel();
	private JLabel jLabel_fangyingshijian = new JLabel();
	private JLabel jLabel_fangyingshijian1 = new JLabel();
	private JLabel jLabel_piaojia1 = new JLabel();
	private JLabel jLabel_shichang1 = new JLabel();
	private JLabel jLabel_Yingming1 = new JLabel();
	
	private JLabel jLabel_fangyingshijian2 = new JLabel();
	private JLabel jLabel_piaojia2 = new JLabel();
	private JLabel jLabel_shichang2 = new JLabel();
	private JLabel jLabel_Yingming2 = new JLabel();
	
	private JLabel jLabel_fangyingshijian3 = new JLabel();
	private JLabel jLabel_piaojia3 = new JLabel();
	private JLabel jLabel_shichang3 = new JLabel();
	private JLabel jLabel_Yingming3 = new JLabel();
	
	private JLabel jLabel_fangyingshijian4 = new JLabel();
	private JLabel jLabel_piaojia4 = new JLabel();
	private JLabel jLabel_shichang4 = new JLabel();
	private JLabel jLabel_Yingming4 = new JLabel();
	
	private JLabel jLabel_Yingmingbofang = new JLabel();
	
	private String zuowei ;
	private JLabel jLabel_weizhi1 = new JLabel();
	private JLabel jLabel_weizhi2 = new JLabel();
	private JLabel jLabel_weizhi3 = new JLabel();
	private JLabel jLabel_weizhi4 = new JLabel();
	
	private JButton[][] buttons1;
	private JButton[][] buttons2;
	private JButton[][] buttons3;
	private JButton[][] buttons4;
	
	int row_count;//演出厅的总行数
	int column_count;//演出厅的总列数
	public static String sched_id = null; //演出计划id
	public static int ticket_status;
	public static String price;
	public static int play_status;
	
	public MovieInformationUI(){
		this.setTitle("309国际剧院票务管理系统");
//		this.setSize(800, 600);			//大小
		this.setSize(1366, 750);
		this.setLocationRelativeTo(null);		//窗体出现居中
		this.setDefaultCloseOperation(3);		//设置关闭操作
		this.setResizable(true);
//		this.setIconImage(new ImageIcon(path+"tiele.bmp").getImage());
		
		jTabbedPane_Main = new JTabbedPane();
		
		jTabbedPane_Main.setBackground(Color.green);
		
		jTabbedPane_Main.add("影片信息", YingPianXinXi());
//		jTabbedPane_Main.add("放映信息", YingPiaoXiXin());
//		jTabbedPane_Main.add("订票",DingPiaoXinXi());
//		jTabbedPane_Main.add(null, "放映信息");
//		jTabbedPane_Main.add(null,"订票");
		
		jTabbedPane_Main.setTabPlacement(JTabbedPane.LEFT);
		this.add(jTabbedPane_Main); 
		this.setVisible(true);
	}
	
	
	public JTabbedPane YingPianXinXi(){
		JTabbedPane jTabbedPane = new JTabbedPane();
		
		String[] strings_xinpin  = {"剧目id","类型id","语言id","名称","简介","图片","时长","票价"
				,"状态"};
		/**
		 * 获取影片信息
		 */
//		Play mplay = new Play();
//		List<PlayInformation> list = mplay.prinfPlays();
//		for(int i = 0; i < list.size(); i++){
//			PlayInformation playInformation = list.get(i);
//			System.out.println("name = " + playInformation.getPlay_name());
//		}
		Data_Dict data_Dict = new Data_Dict();
		Play mplay_xinpin = new Play();
		List<PlayInformation> list_xinpin = mplay_xinpin.prinfPlays();
		Object[][] objects_xinpin = new Object[list_xinpin.size()][9];
		for(int i = 0; i < list_xinpin.size(); i++){
			PlayInformation playInformation = list_xinpin.get(i);
			objects_xinpin[i][0] = playInformation.getPlay_id().toString();
//			objects_xinpin[i][1] = playInformation.getPlay_type_id().toString();
//			objects_xinpin[i][2] = playInformation.getPlay_lang_id().toString();
			objects_xinpin[i][1] = data_Dict.printfDictName(Integer.parseInt(playInformation.getPlay_type_id()));
			objects_xinpin[i][2] = data_Dict.printfDictName(Integer.parseInt(playInformation.getPlay_lang_id()));
			objects_xinpin[i][3] = playInformation.getPlay_name().toString();
			objects_xinpin[i][4] = playInformation.getPlay_introduction().toString();
			objects_xinpin[i][5] = playInformation.getPlay_image().toString();
			objects_xinpin[i][6] = playInformation.getPlay_length().toString();
			objects_xinpin[i][7] = playInformation.getPlay_ticket_price().toString();
			objects_xinpin[i][8] = playInformation.getPlay_status().toString();
		}
		

		String[] strings_renqi  = {"剧目id","类型id","语言id","名称","简介","图片","时长","票价"
				,"状态"};
		Play mplay_renqi = new Play();
		List<PlayInformation> list_renqi = mplay_renqi.prinfPlays();
		Object[][] objects_renqi = new Object[list_renqi.size()][9];
		for(int i = 0; i < list_renqi.size(); i++){
			PlayInformation playInformation = list_renqi.get(i);
			objects_renqi[i][0] = playInformation.getPlay_id().toString();
//			objects_renqi[i][1] = playInformation.getPlay_type_id().toString();
//			objects_renqi[i][2] = playInformation.getPlay_lang_id().toString();
			objects_renqi[i][1] = data_Dict.printfDictName(Integer.parseInt(playInformation.getPlay_type_id()));
			objects_renqi[i][2] = data_Dict.printfDictName(Integer.parseInt(playInformation.getPlay_lang_id()));
			objects_renqi[i][3] = playInformation.getPlay_name().toString();
			objects_renqi[i][4] = playInformation.getPlay_introduction().toString();
			objects_renqi[i][5] = playInformation.getPlay_image().toString();
			objects_renqi[i][6] = playInformation.getPlay_length().toString();
			objects_renqi[i][7] = playInformation.getPlay_ticket_price().toString();
			objects_renqi[i][8] = playInformation.getPlay_status().toString();
		}
		
		String[] strings_tuijian  = {"剧目id","类型id","语言id","名称","简介","图片","时长","票价"
				,"状态"};
		Play mplay_tuijian = new Play();
		List<PlayInformation> list_tuijian = mplay_tuijian.prinfPlays();
		Object[][] objects_tuijian = new Object[list_tuijian.size()][9];
		for(int i = 0; i < list_tuijian.size(); i++){
			PlayInformation playInformation = list_tuijian.get(i);
			objects_tuijian[i][0] = playInformation.getPlay_id().toString();
//			objects_tuijian[i][1] = playInformation.getPlay_type_id().toString();
//			objects_tuijian[i][2] = playInformation.getPlay_lang_id().toString();
			objects_tuijian[i][1] = data_Dict.printfDictName(Integer.parseInt(playInformation.getPlay_type_id()));
			objects_tuijian[i][2] = data_Dict.printfDictName(Integer.parseInt(playInformation.getPlay_lang_id()));
			objects_tuijian[i][3] = playInformation.getPlay_name().toString();
			objects_tuijian[i][4] = playInformation.getPlay_introduction().toString();
			objects_tuijian[i][5] = playInformation.getPlay_image().toString();
			objects_tuijian[i][6] = playInformation.getPlay_length().toString();
			objects_tuijian[i][7] = playInformation.getPlay_ticket_price().toString();
			objects_tuijian[i][8] = playInformation.getPlay_status().toString();
		}

		jTabbedPane.add("新品排行", JuMu_XinPin(strings_xinpin, objects_xinpin));
		jTabbedPane.add("人气排行", JuMu_Renqi(strings_renqi, objects_renqi));
		jTabbedPane.add("推荐排行", JuMu_Tuijian(strings_tuijian, objects_tuijian));
		
		return jTabbedPane;
	}
	
	public JPanel YingPiaoXiXin(){
		JPanel jPanel = new JPanel();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		jPanel.setLayout(gridBagLayout);
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		Insets insets = new Insets(5,5,5,5);
		gridBagConstraints.insets = insets;
		
		JPanel jPanel2 = DianYingPIaoBoFangXinxi();
		jPanel2.setBackground(Color.green);
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx=0;
		gridBagConstraints.gridy=0;
		gridBagConstraints.weightx=100;
		gridBagConstraints.weighty=15;
		jPanel.add(jPanel2,gridBagConstraints);
		
		
		String[] biaotou = {"演出计划id","放映厅id","剧目id","放映时间","票价"};
		/**
		 * 获取放映信息
		 */
		Schedule schedule = new Schedule();
		List<ScheduleInformation> list_schedule = schedule.prinfSchedules();
		Object[][] objects_schedule = new Object[list_schedule.size()][5];
		for(int i = 0; i < list_schedule.size(); i++){
			ScheduleInformation scheduleInformation = list_schedule.get(i);
			objects_schedule[i][0] = scheduleInformation.getSched_id().toString();
			objects_schedule[i][1] = scheduleInformation.getStudio_id().toString();
			objects_schedule[i][2] = scheduleInformation.getPlay_id().toString();
			objects_schedule[i][3] = scheduleInformation.getSched_time().toString();
			objects_schedule[i][4] = scheduleInformation.getSched_ticket_price().toString();

		}
		
		
		JPanel jPanel3 = JuMu_yingpiaoBoFnag(biaotou, objects_schedule);
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx=0;
		gridBagConstraints.gridy=1;
		gridBagConstraints.weightx=100;
		gridBagConstraints.weighty=100;
		jPanel.add(jPanel3,gridBagConstraints);
		
		jPanel.setBackground(Color.orange);
		
		return jPanel;
	}
	
	public JPanel DingPiaoXinXi(){
		JPanel jPanel = new JPanel();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		jPanel.setLayout(gridBagLayout);
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		Insets insets = new Insets(5,5,5,5);
		gridBagConstraints.insets = insets;
		
		JPanel jPanel2 = DianYingPIaoXinxi();
		jPanel2.setBackground(Color.green);
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx=0;
		gridBagConstraints.gridy=0;
		gridBagConstraints.weightx=100;
		gridBagConstraints.weighty=15;
		jPanel.add(jPanel2,gridBagConstraints);
		
		JTabbedPane jPanel3 = JuYuanXinXi();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx=0;
		gridBagConstraints.gridy=1;
		gridBagConstraints.weightx=100;
		gridBagConstraints.weighty=100;
		jPanel.add(jPanel3,gridBagConstraints);
		
		
		jPanel.setBackground(Color.orange);
		return jPanel;
	}
	
	
	public JPanel DianYingPIaoXinxi(){
		JPanel jPanel = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		jPanel.setLayout(gridBagLayout);
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		Insets insets = new Insets(5,5,5,5);
		gridBagConstraints.insets = insets;
		
		JPanel jPanel2 = new JPanel();
		jPanel2.setBackground(Color.pink);
		jPanel2.setLayout(new GridLayout(2,1));
		
		JPanel jPanel21 = new JPanel();
		jPanel21.setBackground(Color.pink);
		jPanel21.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
				
		JPanel jPanel211 = new JPanel();
		jPanel211.setBackground(Color.pink);
		jPanel211.setLayout(new FlowLayout(FlowLayout.CENTER));
		jPanel211.add(new JLabel("影片名称："));
		jLabel_Yingming.setFont(new java.awt.Font("Dialog", 1, 20)); 
		jLabel_Yingming.setForeground(Color.blue);
		jPanel211.add(jLabel_Yingming);
		
		JPanel jPanel212 = new JPanel();
		jPanel212.setBackground(Color.pink);
		jPanel212.setLayout(new FlowLayout(FlowLayout.CENTER));
		jPanel212.add(new JLabel("类型："));
		jLabel_leixing.setFont(new java.awt.Font("Dialog", 1, 20));
		jLabel_leixing.setForeground(Color.blue);
		jPanel212.add(jLabel_leixing);
		
		JPanel jPanel213 = new JPanel();
		jPanel213.setBackground(Color.pink);
		jPanel213.setLayout(new FlowLayout(FlowLayout.CENTER));
		jPanel213.add(new JLabel("语言："));
		jLabel_changdi.setFont(new java.awt.Font("Dialog", 1, 20));
		jLabel_changdi.setForeground(Color.blue);
		jPanel213.add(jLabel_changdi);
		
		jPanel21.add(jPanel211);
		jPanel21.add(jPanel212);
		jPanel21.add(jPanel213);
		
		JPanel jPanel22 = new JPanel();
		jPanel22.setBackground(Color.pink);
		jPanel22.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
		
		JPanel jPanel221 = new JPanel();
		jPanel221.setBackground(Color.pink);
		jPanel221.setLayout(new FlowLayout(FlowLayout.CENTER));
		jPanel221.add(new JLabel("时长："));
		jLabel_shichang.setFont(new java.awt.Font("Dialog", 1, 20));
		jLabel_shichang.setForeground(Color.blue);
		jPanel221.add(jLabel_shichang);
		JPanel jPanel222 = new JPanel();
		jPanel222.setBackground(Color.pink);
		jPanel222.setLayout(new FlowLayout(FlowLayout.CENTER));
		jPanel222.add(new JLabel("票价"));
		jLabel_piaojia.setFont(new java.awt.Font("Dialog", 1, 20));
		jLabel_piaojia.setForeground(Color.blue);
		jPanel222.add(jLabel_piaojia);
		JPanel jPanel223 = new JPanel();
		jPanel223.setBackground(Color.pink);
		jPanel223.setLayout(new FlowLayout(FlowLayout.CENTER));
		jPanel223.add(new JLabel("放映时间："));
		jLabel_fangyingshijian.setFont(new java.awt.Font("Dialog", 1, 20));
		jLabel_fangyingshijian.setForeground(Color.blue);
		jPanel223.add(jLabel_fangyingshijian);
		
		jPanel22.add(jPanel221);
		jPanel22.add(jPanel222);
		jPanel22.add(jPanel223);
		
		jPanel2.add(jPanel21);
		jPanel2.add(jPanel22);
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx=0;
		gridBagConstraints.gridy=0;
		gridBagConstraints.weightx=10;
		gridBagConstraints.weighty=10;
		jPanel.add(jPanel2,gridBagConstraints);
		
		JPanel jPanel3 = new JPanel();
		jPanel3.setBackground(Color.pink);
		JButton jButton_fanhui = new JButton("返回");
		jButton_fanhui.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				jTabbedPane_Main.setSelectedIndex(1);
				jTabbedPane_Main.removeTabAt(2);
			}
		});
		jPanel3.add(jButton_fanhui,BorderLayout.CENTER);
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx=1;
		gridBagConstraints.gridy=0;
		gridBagConstraints.weightx=2;
		gridBagConstraints.weighty=10;
		jPanel.add(jPanel3,gridBagConstraints);
		
		return jPanel;
	}
	
	public JPanel DianYingPIaoBoFangXinxi(){
		JPanel jPanel = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		jPanel.setLayout(gridBagLayout);
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		Insets insets = new Insets(5,5,5,5);
		gridBagConstraints.insets = insets;
		
		JPanel jPanel2 = new JPanel();
		jPanel2.setBackground(Color.pink);
		jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		
				
		JPanel jPanel211 = new JPanel();
		jPanel211.setBackground(Color.pink);
		jPanel211.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		jLabel_Yingmingbofang.setFont(new java.awt.Font("Dialog", 1, 20)); 
		jLabel_Yingmingbofang.setForeground(Color.blue);
		jPanel211.add(jLabel_Yingmingbofang);
		jPanel211.add(new JLabel("放映信息一览表"));
		
		
		JPanel jPanel22 = new JPanel();
		jPanel22.setBackground(Color.pink);
		jPanel22.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
		
		jPanel2.add(jPanel211);
		jPanel2.add(jPanel22);
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx=0;
		gridBagConstraints.gridy=0;
		gridBagConstraints.weightx=10;
		gridBagConstraints.weighty=10;
		jPanel.add(jPanel2,gridBagConstraints);
		
		JPanel jPanel3 = new JPanel();
		jPanel3.setBackground(Color.pink);
		jPanel3.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		JButton jButton_fanhui = new JButton("返回");
		jButton_fanhui.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				jTabbedPane_Main.setSelectedIndex(0);
				jTabbedPane_Main.removeTabAt(1);
			}
		});
		jPanel3.add(jButton_fanhui);
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx=1;
		gridBagConstraints.gridy=0;
		gridBagConstraints.weightx=2;
		gridBagConstraints.weighty=10;
		jPanel.add(jPanel3,gridBagConstraints);
		
		return jPanel;
	}
	
	public JTabbedPane JuYuanXinXi(){
		jTabbedPane_DingPioa = new JTabbedPane();
		
		jTabbedPane_DingPioa.add("1号演出厅",YanChuTing1());
		jTabbedPane_DingPioa.add("2号演出厅",YanChuTing2());
		jTabbedPane_DingPioa.add("3号演出厅",YanChuTing3());
		jTabbedPane_DingPioa.add("4号演出厅",YanChuTing4());
		
		return jTabbedPane_DingPioa;
	}
	
	public JPanel JuMu_Tuijian(String[] biaotou,Object[][] chengyuan){
		JPanel jPanel = new JPanel();
		jPanel.setBackground(Color.orange);
		
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		Insets insets = new Insets(10, 5, 0, 5);
		gridBagConstraints.insets = insets;
		
		JLabel jLabel = new JLabel("推荐列表");
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		jPanel.add(jLabel, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		jTable_tuijian = ZhiBiao_tuijian(biaotou,chengyuan);
		jTable_tuijian.setBackground(Color.pink);
		jTable_tuijian.addMouseListener(new MouseAdapter() {//表格添加点击事件

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
//				YingPiaoXiXin();
				
				int select1 = jTable_tuijian.getSelectedRow();  	//获取被选中的行
				Object oa = tableModel_tuijian.getValueAt(select1, 3);//影名
				Object ob = tableModel_tuijian.getValueAt(select1, 1);//类型
				Object oc = tableModel_tuijian.getValueAt(select1, 2);//语言
				Object oe = tableModel_tuijian.getValueAt(select1, 6);//时长
				Object oh = tableModel_tuijian.getValueAt(select1, 7);//票价
				Object oi = tableModel_tuijian.getValueAt(select1, 8);//状态
				
				
				jLabel_Yingming.setText(oa.toString());
				jLabel_Yingming1.setText(oa.toString());
				jLabel_Yingming2.setText(oa.toString());
				jLabel_Yingming3.setText(oa.toString());
				jLabel_Yingming4.setText(oa.toString());
				jLabel_Yingmingbofang.setText(oa.toString());
				jLabel_leixing.setText(ob.toString());
				jLabel_changdi.setText(oc.toString());
				jLabel_piaojia.setText(oh.toString());
				jLabel_piaojia1.setText(oh.toString());
				jLabel_piaojia2.setText(oh.toString());
				jLabel_piaojia3.setText(oh.toString());
				jLabel_piaojia4.setText(oh.toString());
				jLabel_shichang.setText(oe.toString());
				jLabel_shichang1.setText(oe.toString());
				jLabel_shichang2.setText(oe.toString());
				jLabel_shichang3.setText(oe.toString());
				jLabel_shichang4.setText(oe.toString());
					
				jTabbedPane_Main.setSelectedIndex(1);
			
				
			}	
			
		});
		
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 120;
		gridBagConstraints.weighty = 100;
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(jTable_tuijian);
		jScrollPane.setBackground(Color.yellow);
		jPanel.add(jScrollPane, gridBagConstraints);
		
		
		JPanel jPanel5 = new JPanel();
		jPanel5.setBackground(Color.orange);
		
		gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=2;
        gridBagConstraints.weightx = 10;
		gridBagConstraints.weighty = 10;
        jPanel.add(jPanel5, gridBagConstraints);
		
		return jPanel;
	}
	
	public JPanel JuMu_yingpiaoBoFnag(String[] biaotou,Object[][] chengyuan){
		JPanel jPanel = new JPanel();
		jPanel.setBackground(Color.orange);
		
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		Insets insets = new Insets(10, 5, 0, 5);
		gridBagConstraints.insets = insets;
		
		JLabel jLabel = new JLabel("放映信息");
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		jPanel.add(jLabel, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		jTable_YingPian = ZhiBiao_tuijian(biaotou,chengyuan);
		jTable_YingPian.setBackground(Color.pink);
		jTable_YingPian.addMouseListener(new MouseAdapter() {//表格添加点击事件

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				//调用进度条
				ProgressBarDemo progressBarDemo = new ProgressBarDemo();
				//开启线程，执行加载任务
				new Thread(){
					public void run(){
						int select1 = jTable_YingPian.getSelectedRow();  	//获取被选中的行
						Object od = tableModel_tuijian.getValueAt(select1, 0);
						Object oa = tableModel_tuijian.getValueAt(select1, 1);
						Object ob = tableModel_tuijian.getValueAt(select1, 2);
						Object oc = tableModel_tuijian.getValueAt(select1, 3);
						Object oe = tableModel_tuijian.getValueAt(select1, 4);
						
						sched_id = od.toString();
						System.out.println("sched_id的获取---"+od.toString());
						
						jTabbedPane_Main.add("订票",DingPiaoXinXi());
						
						jLabel_fangyingshijian.setText(oc.toString());
						if (oa.toString().charAt(0) == '1') {
							jTabbedPane_DingPioa.setSelectedIndex(0);
							jLabel_fangyingshijian1.setText(ob.toString());
						}else if(oa.toString().charAt(0) == '2'){
							jTabbedPane_DingPioa.setSelectedIndex(1);
							jLabel_fangyingshijian2.setText(ob.toString());
						}else if(oa.toString().charAt(0) == '3'){
							jTabbedPane_DingPioa.setSelectedIndex(2);
							jLabel_fangyingshijian3.setText(ob.toString());
						}else if(oa.toString().charAt(0) == '4'){
							jTabbedPane_DingPioa.setSelectedIndex(3);
							jLabel_fangyingshijian4.setText(ob.toString());
						}
						
						jTabbedPane_Main.setSelectedIndex(2);
					}
				}.start();
				
				
//				int select1 = jTable_YingPian.getSelectedRow();  	//获取被选中的行
//				Object od = tableModel_tuijian.getValueAt(select1, 0);
//				Object oa = tableModel_tuijian.getValueAt(select1, 1);
//				Object ob = tableModel_tuijian.getValueAt(select1, 2);
//				Object oc = tableModel_tuijian.getValueAt(select1, 3);
//				Object oe = tableModel_tuijian.getValueAt(select1, 4);
//				
//				sched_id = od.toString();
//				System.out.println("sched_id的获取---"+od.toString());
//				
//				jTabbedPane_Main.add("订票",DingPiaoXinXi());
//				
//				jLabel_fangyingshijian.setText(oc.toString());
//				if (oa.toString().charAt(0) == '1') {
//					jTabbedPane_DingPioa.setSelectedIndex(0);
//					jLabel_fangyingshijian1.setText(ob.toString());
//				}else if(oa.toString().charAt(0) == '2'){
//					jTabbedPane_DingPioa.setSelectedIndex(1);
//					jLabel_fangyingshijian2.setText(ob.toString());
//				}else if(oa.toString().charAt(0) == '3'){
//					jTabbedPane_DingPioa.setSelectedIndex(2);
//					jLabel_fangyingshijian3.setText(ob.toString());
//				}else if(oa.toString().charAt(0) == '4'){
//					jTabbedPane_DingPioa.setSelectedIndex(3);
//					jLabel_fangyingshijian4.setText(ob.toString());
//				}
//				
//				jTabbedPane_Main.setSelectedIndex(2);
				
			}	
			
		});
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 120;
		gridBagConstraints.weighty = 100;
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(jTable_YingPian);
		jScrollPane.setBackground(Color.yellow);
		jPanel.add(jScrollPane, gridBagConstraints);
		
		
		JPanel jPanel5 = new JPanel();
		jPanel5.setBackground(Color.orange);
		
		gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=2;
        gridBagConstraints.weightx = 10;
		gridBagConstraints.weighty = 10;
        jPanel.add(jPanel5, gridBagConstraints);
		
		return jPanel;
	}
	
	public JPanel JuMu_Renqi(String[] biaotou,Object[][] chengyuan){
		JPanel jPanel = new JPanel();
		jPanel.setBackground(Color.orange);
		
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		Insets insets = new Insets(10, 5, 0, 5);
		gridBagConstraints.insets = insets;
		
		JLabel jLabel = new JLabel("排行列表");
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		jPanel.add(jLabel, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		jTable_renqi1 = ZhiBiao_renqi(biaotou,chengyuan);
		jTable_renqi1.setBackground(Color.magenta);
		jTable_renqi1.addMouseListener(new MouseAdapter() {//表格添加点击事件

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				int select1 = jTable_renqi1.getSelectedRow();  	//获取被选中的行
				Object oa1 = tableModel_renqi1.getValueAt(select1, 3);
				Object ob1 = tableModel_renqi1.getValueAt(select1, 1);
				Object oc1 = tableModel_renqi1.getValueAt(select1, 2);
				Object oe1 = tableModel_renqi1.getValueAt(select1, 6);
				Object oh1 = tableModel_renqi1.getValueAt(select1, 7);
				
				jLabel_Yingmingbofang.setText(oa1.toString());
				jLabel_Yingming.setText(oa1.toString());
				jLabel_Yingming1.setText(oa1.toString());
				jLabel_Yingming2.setText(oa1.toString());
				jLabel_Yingming3.setText(oa1.toString());
				jLabel_Yingming4.setText(oa1.toString());
				jLabel_leixing.setText(ob1.toString());
				jLabel_changdi.setText(oc1.toString());
				jLabel_piaojia.setText(oh1.toString());
				jLabel_shichang.setText(oe1.toString());
				jLabel_piaojia1.setText(oh1.toString());
				jLabel_shichang1.setText(oe1.toString());
				jLabel_piaojia2.setText(oh1.toString());
				jLabel_shichang2.setText(oe1.toString());
				jLabel_piaojia3.setText(oh1.toString());
				jLabel_shichang3.setText(oe1.toString());
				jLabel_piaojia4.setText(oh1.toString());
				jLabel_shichang4.setText(oe1.toString());
				
				jTabbedPane_Main.setSelectedIndex(1);
				
			}	
			
		});
		
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 120;
		gridBagConstraints.weighty = 100;
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(jTable_renqi1);
		jScrollPane.setBackground(Color.yellow);
		jPanel.add(jScrollPane, gridBagConstraints);
		
		
		JPanel jPanel5 = new JPanel();
		jPanel5.setBackground(Color.orange);
		
		gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=2;
        gridBagConstraints.weightx = 10;
		gridBagConstraints.weighty = 10;
        jPanel.add(jPanel5, gridBagConstraints);
		
		return jPanel;
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
		jTable_Xinpin.setBackground(Color.pink);
		jTable_Xinpin.addMouseListener(new MouseAdapter() {//表格添加点击事件

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				int select1 = jTable_Xinpin.getSelectedRow();  	//获取被选中的行
				Object oa = tableModel_xinpin.getValueAt(select1, 3);//电影名
				Object ob = tableModel_xinpin.getValueAt(select1, 1);//类型
				Object oc = tableModel_xinpin.getValueAt(select1, 2);//语言
				Object oe = tableModel_xinpin.getValueAt(select1, 6);//时长
				Object oh = tableModel_xinpin.getValueAt(select1, 7);//票价
				Object oi = tableModel_xinpin.getValueAt(select1, 8);//状态
				
				//给全局变量price赋值
				price = oh.toString();
				play_status = Integer.parseInt(oi.toString());
				System.out.println("测试play_status:"+play_status);
				if(play_status == 0){
					System.out.println("该电影还未上线！！！");
					JOptionPane.showMessageDialog(null, "该电影还未上映，敬请期待哦 ^_^","消息", JOptionPane.WARNING_MESSAGE);
				}else if(play_status == -1){
					System.out.println("该电影已经下线！！！");
					JOptionPane.showMessageDialog(null, "对不起！该电影已下线 *_*","消息", JOptionPane.WARNING_MESSAGE);
				}else{
//					jLabel_fangyingshijian.setText(oi.toString());
//					jLabel_fangyingshijian1.setText(oi.toString());
					jLabel_Yingming.setText(oa.toString());
					jLabel_Yingmingbofang.setText(oa.toString());
					jLabel_Yingming1.setText(oa.toString());
					jLabel_Yingming2.setText(oa.toString());
					jLabel_Yingming3.setText(oa.toString());
					jLabel_Yingming4.setText(oa.toString());
					jLabel_leixing.setText(ob.toString());
					jLabel_changdi.setText(oc.toString());
					jLabel_piaojia.setText(oh.toString());
					jLabel_shichang.setText(oe.toString());
					jLabel_piaojia1.setText(oh.toString());
					jLabel_shichang1.setText(oe.toString());
					
					jLabel_piaojia2.setText(oh.toString());
					jLabel_shichang2.setText(oe.toString());
					jLabel_piaojia3.setText(oh.toString());
					jLabel_shichang3.setText(oe.toString());
					jLabel_piaojia4.setText(oh.toString());
					jLabel_shichang4.setText(oe.toString());
					
					jTabbedPane_Main.add("放映信息", YingPiaoXiXin());
					jTabbedPane_Main.setSelectedIndex(1);
				}
				
				
			}	
			
		});
		
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 120;
		gridBagConstraints.weighty = 100;
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(jTable_Xinpin);
		jScrollPane.setBackground(Color.orange);
		jPanel.add(jScrollPane, gridBagConstraints);
		
		
		JPanel jPanel5 = new JPanel();
		jPanel5.setBackground(Color.orange);
		
		gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=2;
        gridBagConstraints.weightx = 10;
		gridBagConstraints.weighty = 10;
        jPanel.add(jPanel5, gridBagConstraints);
		
		return jPanel;
	}
	
	public JTable ZhiBiao_YingPian(String[] biaotou,Object[][] chengyuan){
		
		tableModel_YingPian = new DefaultTableModel(chengyuan, biaotou);
		JTable jTable1 = new JTable(tableModel_renqi1);
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
	
	public JTable ZhiBiao_renqi(String[] biaotou,Object[][] chengyuan){
		
		tableModel_renqi1 = new DefaultTableModel(chengyuan, biaotou);
		JTable jTable1 = new JTable(tableModel_renqi1);
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
	
public JTable ZhiBiao_tuijian(String[] biaotou,Object[][] chengyuan){
		
		tableModel_tuijian = new DefaultTableModel(chengyuan, biaotou);
		JTable jTable1 = new JTable(tableModel_tuijian);
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


	public JTable ZhiBiao_xinpin(String[] biaotou,Object[][] chengyuan){
	
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
	
	
	
	public JPanel ShnegChengZuoWei(int a,int b){
		JPanel jPanel_shengchengZuowei = new JPanel();
		jPanel_shengchengZuowei.setLayout(new GridLayout(a, b,10,10));
		int i;
		int j;
		buttons1 = new JButton[a][b];
		for (i = 0; i < a; i++) {
			for (j = 0; j < b; j++) {
				
				String row = String.valueOf(i);
				String column = String.valueOf(j);
				//获取seat_id
				Seat seat1 = new Seat();
				String string1 = seat1.prinfPiaoSeat("1", row, column);
				int seat_id = Integer.valueOf(string1);
				//接收sched_id
				Ticket ticket1 = new Ticket();
				int sched_id1 = Integer.valueOf(sched_id);
				//获取ticket_status
				int ticket_status = ticket1.printfTicketStatus(seat_id, sched_id1);
				//获取ticket_id
				int ticket_id = ticket1.printfTicketId(seat_id, sched_id1);
				
				buttons1[i][j] = new JButton();
				int aa = i+1;
				int bb = j+1;
				buttons1[i][j].setName("第"+aa+"排 ,第"+bb+"座");
				buttons1[i][j].setBackground(Color.pink);
				if(ticket_status == 1){
					buttons1[i][j].setBackground(Color.red);
				}
				buttons1[i][j].addActionListener(new ActionListener() {
					int count;
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						 JButton button = (JButton) e.getSource();
						 jLabel_weizhi1.setText(button.getName());
						 string = button.getName();
//						 System.out.println("测试string1:"+string);
						 jLabel_weizhi1.setFont(new java.awt.Font("Dialog", 1, 20));
						 jLabel_weizhi1.setForeground(Color.blue);
						 count++;
						 //如果票已售出，则点击不能进行颜色的修改
						 if(ticket_status == 1){
							 button.setBackground(Color.red);
						 }else{//如果票未售出，点击则可以修改颜色
							 if (count%2==0) {
									button.setBackground(Color.pink);
								}else {
									button.setBackground(Color.red);
								}
						 }
					}
				});
				jPanel_shengchengZuowei.add(buttons1[i][j]);
			}
		}
		
		return jPanel_shengchengZuowei;
	}
	
	public JPanel ShnegChengZuoWei2(int a,int b){
		JPanel jPanel_shengchengZuowei = new JPanel();
		jPanel_shengchengZuowei.setLayout(new GridLayout(a, b,10,10));
		int i;
		int j;
		
		buttons2 = new JButton[a][b];
		for (i = 0; i < a; i++) {
			for (j = 0; j < b; j++) {
				
				String row = String.valueOf(i);
				String column = String.valueOf(j);
				//获取seat_id
				Seat seat1 = new Seat();
				String string1 = seat1.prinfPiaoSeat("2", row, column);
//				System.out.println("---测试seat_id=["+string1+"]");
				int seat_id = Integer.valueOf(string1);
				//接收sched_id
				Ticket ticket1 = new Ticket();
//				System.out.println("---测试sched_id="+sched_id);
				int sched_id1 = Integer.valueOf(sched_id);
				//获取ticket_status
				int ticket_status = ticket1.printfTicketStatus(seat_id, sched_id1);
//				System.out.println("---测试ticket_status="+ticket_status);
				//获取ticket_id
				int ticket_id = ticket1.printfTicketId(seat_id, sched_id1);
//				System.out.println("---测试ticket_id="+ticket_id);
				
				buttons2[i][j] = new JButton();
				int aa = i+1;
				int bb = j+1;
				buttons2[i][j].setName("第"+aa+"排 ,第"+bb+"座");
				buttons2[i][j].setBackground(Color.pink);
				if(ticket_status == 1){
					buttons2[i][j].setBackground(Color.red);
				}
				buttons2[i][j].addActionListener(new ActionListener() {
					int count;
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						 JButton button = (JButton) e.getSource();
						 jLabel_weizhi2.setText(button.getName());
						 string = button.getName();
//						 System.out.println("测试string2:"+string);
						 jLabel_weizhi2.setFont(new java.awt.Font("Dialog", 1, 20));
						 jLabel_weizhi2.setForeground(Color.blue);
						 count++;
						//如果票已售出，则点击不能进行颜色的修改
						 if(ticket_status == 1){
							 button.setBackground(Color.red);
						 }else{//如果票未售出，点击则可以修改颜色
							 if (count%2==0) {
									button.setBackground(Color.pink);
								}else {
									button.setBackground(Color.red);
								}
						 }
					}
				});
				jPanel_shengchengZuowei.add(buttons2[i][j]);
			}
		}
		
		return jPanel_shengchengZuowei;
	}
	
	public JPanel ShnegChengZuoWei3(int a,int b){
		JPanel jPanel_shengchengZuowei = new JPanel();
		jPanel_shengchengZuowei.setLayout(new GridLayout(a, b,10,10));
		int i;
		int j;
		
		buttons3 = new JButton[a][b];
		for (i = 0; i < a; i++) {
			for (j = 0; j < b; j++) {
				
				String row = String.valueOf(i);
				String column = String.valueOf(j);
				//获取seat_id
				Seat seat1 = new Seat();
				String string1 = seat1.prinfPiaoSeat("3", row, column);
				int seat_id = Integer.valueOf(string1);
//				System.out.println("---测试seat_id=["+string1+"]");
				//接收sched_id
				Ticket ticket1 = new Ticket();
//				System.out.println("---测试sched_id="+sched_id);
				int sched_id1 = Integer.valueOf(sched_id);
				//获取ticket_status
				int ticket_status = ticket1.printfTicketStatus(seat_id, sched_id1);
//				System.out.println("---测试ticket_status="+ticket_status);
				//获取ticket_id
				int ticket_id = ticket1.printfTicketId(seat_id, sched_id1);
//				System.out.println("---测试ticket_id="+ticket_id);
				
				buttons3[i][j] = new JButton();
				int aa = i+1;
				int bb = j+1;
				buttons3[i][j].setName("第"+aa+"排 ,第"+bb+"座");
				buttons3[i][j].setBackground(Color.pink);
				if(ticket_status == 1){
					buttons3[i][j].setBackground(Color.red);
				}
				buttons3[i][j].addActionListener(new ActionListener() {
					int count;
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						 JButton button = (JButton) e.getSource();
						 jLabel_weizhi3.setText(button.getName());
						 string = button.getName();
//						 System.out.println("测试string3:"+string);
						 jLabel_weizhi3.setFont(new java.awt.Font("Dialog", 1, 20));
						 jLabel_weizhi3.setForeground(Color.blue);
						 count++;
						//如果票已售出，则点击不能进行颜色的修改
						 if(ticket_status == 1){
							 button.setBackground(Color.red);
						 }else{//如果票未售出，点击则可以修改颜色
							 if (count%2==0) {
									button.setBackground(Color.pink);
								}else {
									button.setBackground(Color.red);
								}
						 }
					}
				});
				jPanel_shengchengZuowei.add(buttons3[i][j]);
			}
		}
		
		return jPanel_shengchengZuowei;
	}
	
	public JPanel ShnegChengZuoWei4(int a,int b){
		JPanel jPanel_shengchengZuowei = new JPanel();
		jPanel_shengchengZuowei.setLayout(new GridLayout(a, b,10,10));
		int i;
		int j;
		
		buttons4 = new JButton[a][b];
		for (i = 0; i < a; i++) {
			for (j = 0; j < b; j++) {
				
				String row = String.valueOf(i);
				String column = String.valueOf(j);
				//获取seat_id
				Seat seat1 = new Seat();
				String string1 = seat1.prinfPiaoSeat("4", row, column);
				int seat_id = Integer.valueOf(string1);
//				System.out.println("---测试seat_id=["+string1+"]");
				//接收sched_id
				Ticket ticket1 = new Ticket();
//				System.out.println("---测试sched_id="+sched_id);
				int sched_id1 = Integer.valueOf(sched_id);
				//获取ticket_status
				int ticket_status = ticket1.printfTicketStatus(seat_id, sched_id1);
//				System.out.println("---测试ticket_status="+ticket_status);
				//获取ticket_id
				int ticket_id = ticket1.printfTicketId(seat_id, sched_id1);
//				System.out.println("---测试ticket_id="+ticket_id);
				
				buttons4[i][j] = new JButton();
				int aa = i+1;
				int bb = j+1;
				buttons4[i][j].setName("第"+aa+"排 ,第"+bb+"座");
				buttons4[i][j].setBackground(Color.pink);
				if(ticket_status == 1){
					buttons4[i][j].setBackground(Color.red);
				}
				buttons4[i][j].addActionListener(new ActionListener() {
					int count;
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						 JButton button = (JButton) e.getSource();
						 jLabel_weizhi4.setText(button.getName());
						 string = button.getName();
//						 System.out.println("测试string4:"+string);
						 jLabel_weizhi4.setFont(new java.awt.Font("Dialog", 1, 20));
						 jLabel_weizhi4.setForeground(Color.blue);
						 count++;
						//如果票已售出，则点击不能进行颜色的修改
						 if(ticket_status == 1){
							 button.setBackground(Color.red);
						 }else{//如果票未售出，点击则可以修改颜色
							 if (count%2==0) {
									button.setBackground(Color.pink);
								}else {
									button.setBackground(Color.red);
								}
						 }
					}
				});
				jPanel_shengchengZuowei.add(buttons4[i][j]);
			}
		}
		
		return jPanel_shengchengZuowei;
	}
	
public JPanel YanChuTing1(){
	
		Studio studio1 = new Studio();
		List<StudioInformation> list1 = studio1.prinfStudios();
		StudioInformation studioInformation1 = list1.get(0);
		row_count = Integer.parseInt(studioInformation1.getStudio_row_count());
		column_count = Integer.parseInt(studioInformation1.getStudio_col_count());
//		System.out.println("row = " + studioInformation.getStudio_row_count());
//		System.out.println("column = " + studioInformation.getStudio_col_count());
		
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		Insets insets = new Insets(10, 5, 0, 5);
		gridBagConstraints.insets = insets;
		
		JLabel jLabel = new JLabel("1号演出厅座位表");
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		jLabel.setFont(new java.awt.Font("Dialog", 1, 25));
		jLabel.setForeground(Color.red);
		jPanel.add(jLabel, gridBagConstraints);
		
		JLabel jLabel2 = new JLabel("订票信息");
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		jLabel2.setFont(new java.awt.Font("Dialog", 1, 25));
		jLabel2.setForeground(Color.red);
		jPanel.add(jLabel2, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		JPanel jPanel3 = NiaoKanTu(row_count,column_count);
		jPanel3.setBackground(Color.pink);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 80;
		gridBagConstraints.weighty = 100;
		jPanel.add(jPanel3, gridBagConstraints);
		
		JPanel jPanel4 = new JPanel();
		jPanel4.setLayout(new GridLayout(8, 1));
		
		
		JPanel jPanel41 = new JPanel();
		jPanel41.setBackground(Color.pink);
		jPanel41.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
		jPanel41.add(new JLabel("电影名称："));
		
		JPanel jPanel45 = new JPanel();
		jPanel45.setBackground(Color.pink);
		jPanel45.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
		jLabel_Yingming1.setFont(new java.awt.Font("Dialog", 1, 15));
		jLabel_Yingming1.setForeground(Color.blue);
		jPanel45.add(jLabel_Yingming1);
		
		JPanel jPanel48 = new JPanel();
		jPanel48.setBackground(Color.pink);
		jPanel48.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		jPanel48.add(new JLabel("放映厅："));
		JLabel jLabel_fangyingting = new JLabel();
		jLabel_fangyingting.setText("1号放映厅");
		jLabel_fangyingting.setFont(new java.awt.Font("Dialog", 1, 15));
		jLabel_fangyingting.setForeground(Color.blue);
		jPanel48.add(jLabel_fangyingting);
		
		JPanel jPanel46 = new JPanel();
		jPanel46.setBackground(Color.pink);
		jPanel46.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		jPanel46.add(new JLabel("票价："));
		jLabel_piaojia1.setFont(new java.awt.Font("Dialog", 1, 15));
		jLabel_piaojia1.setForeground(Color.blue);
		jPanel46.add(jLabel_piaojia1);
		
		JPanel jPanel47 = new JPanel();
		jPanel47.setBackground(Color.pink);
		jPanel47.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		jPanel47.add(new JLabel("时长："));
		jLabel_shichang1.setFont(new java.awt.Font("Dialog", 1, 15));
		jLabel_shichang1.setForeground(Color.blue);
		jPanel47.add(jLabel_shichang1);
		
		JPanel jPanel42 = new JPanel();
		jPanel42.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
		jPanel42.add(new JLabel("您目前所点击的位置："));
		
		JPanel jPanel43 = new JPanel();
		jPanel43.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
		jLabel_weizhi1.setText(zuowei);
		jPanel43.add(jLabel_weizhi1);
		jPanel42.setBackground(Color.pink);
		jPanel43.setBackground(Color.pink);
		
		JPanel jPanel44 = new JPanel();
		jPanel44.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
		JButton jButton_dingpiao = new JButton("订票");
		jButton_dingpiao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
//				JOptionPane.showMessageDialog( null,"订票成功！您的位置是："+string);
				int a = string.charAt(1)-49;//在二维数组中的行
				int b = string.charAt(6)-49;//在二维数组中的列
//				System.out.println("1测试a和b:"+a+"---"+b);
				buttons1[a][b].setBackground(Color.red);
				String row = String.valueOf(a);
				String column = String.valueOf(b);
				//获取seat_id
				Seat seat1 = new Seat();
				String string1 = seat1.prinfPiaoSeat("1", row, column);
				int seat_id = Integer.valueOf(string1);
				System.out.println("测试seat_id = "+string1);
				//接收sched_id
				Ticket ticket1 = new Ticket();
				System.out.println("测试sched_id="+sched_id);
				int sched_id1 = Integer.valueOf(sched_id);
				//获取ticket_status
				int ticket_status = ticket1.printfTicketStatus(seat_id, sched_id1);
				System.out.println("测试ticket_status="+ticket_status);
				//获取ticket_id
				int ticket_id = ticket1.printfTicketId(seat_id, sched_id1);
				System.out.println("测试ticket_id="+ticket_id);
				//如果票的状态为0，则进行购票，否则不能购票
				if(ticket_id != 0){
					if(ticket_status == 0){
						//卖票，修改票的状态
						boolean ceshi = ticket1.ticketSell(ticket_id);
						if(ceshi==true){
							JOptionPane.showMessageDialog( null,"您需要支付的金额为："+price);
							System.out.println("售票成功");
						}else{
							System.out.println("售票失败");
						}
					}else{
						JOptionPane.showMessageDialog( null,"对不起！该座位已被预订 ^_^","消息", JOptionPane.ERROR_MESSAGE);
						System.out.println("该座位的票已经卖出，不能进行购票");
					}
				}else{
					
					JOptionPane.showMessageDialog( null,"您需要支付的金额为："+price);
					boolean sellAdd = ticket1.TicketSellAdd(seat_id, sched_id1, price);
					System.out.println("sellAdd="+sellAdd);
				}
				
				
			}
		});
		jPanel44.add(jButton_dingpiao);
		jPanel44.setBackground(Color.pink);
		
		jPanel4.add(jPanel41);
		jPanel4.add(jPanel45);
		jPanel4.add(jPanel48);
		jPanel4.add(jPanel46);
		jPanel4.add(jPanel47);
		jPanel4.add(jPanel42);
		jPanel4.add(jPanel43);
		jPanel4.add(jPanel44);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0;
		gridBagConstraints.weighty = 0;
		jPanel.add(jPanel4, gridBagConstraints);
		
		JPanel jPanel5 = new JPanel();
		jPanel5.setBackground(Color.orange);
		gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=2;
        gridBagConstraints.gridwidth=2;
        jPanel.add(jPanel5, gridBagConstraints);
        
		
		jPanel.setBackground(Color.orange);
		return jPanel;
	}

public JPanel YanChuTing2(){
	
	Studio studio2 = new Studio();
	List<StudioInformation> list2 = studio2.prinfStudios();
	StudioInformation studioInformation2 = list2.get(1);
	row_count = Integer.parseInt(studioInformation2.getStudio_row_count());
	column_count = Integer.parseInt(studioInformation2.getStudio_col_count());
	
	JPanel jPanel = new JPanel();
	jPanel.setLayout(new GridBagLayout());
	GridBagConstraints gridBagConstraints = new GridBagConstraints();
	Insets insets = new Insets(10, 5, 0, 5);
	gridBagConstraints.insets = insets;
	
	JLabel jLabel = new JLabel("2号演出厅座位表");
	gridBagConstraints.gridx = 0;
	gridBagConstraints.gridy = 0;
	jLabel.setFont(new java.awt.Font("Dialog", 1, 25));
	jLabel.setForeground(Color.red);
	jPanel.add(jLabel, gridBagConstraints);
	
	JLabel jLabel2 = new JLabel("订票信息");
	gridBagConstraints.gridx = 1;
	gridBagConstraints.gridy = 0;
	jLabel2.setFont(new java.awt.Font("Dialog", 1, 25));
	jLabel2.setForeground(Color.red);
	jPanel.add(jLabel2, gridBagConstraints);
	
	gridBagConstraints.fill = GridBagConstraints.BOTH;
	JPanel jPanel3 = NiaoKanTu2(row_count,column_count);
	jPanel3.setBackground(Color.pink);
	gridBagConstraints.gridx = 0;
	gridBagConstraints.gridy = 1;
	gridBagConstraints.weightx = 80;
	gridBagConstraints.weighty = 100;
	jPanel.add(jPanel3, gridBagConstraints);
	

	JPanel jPanel4 = new JPanel();
	jPanel4.setLayout(new GridLayout(8, 1));
	
	
	JPanel jPanel41 = new JPanel();
	jPanel41.setBackground(Color.pink);
	jPanel41.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
	jPanel41.add(new JLabel("电影名称："));
	
	JPanel jPanel45 = new JPanel();
	jPanel45.setBackground(Color.pink);
	jPanel45.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
	jLabel_Yingming2.setFont(new java.awt.Font("Dialog", 1, 15));
	jLabel_Yingming2.setForeground(Color.blue);
	jPanel45.add(jLabel_Yingming2);
	
	JPanel jPanel48 = new JPanel();
	jPanel48.setBackground(Color.pink);
	jPanel48.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	jPanel48.add(new JLabel("放映厅："));
	JLabel jLabel_fangyingting = new JLabel();
	jLabel_fangyingting.setText("2号放映厅");
	jLabel_fangyingting.setFont(new java.awt.Font("Dialog", 1, 15));
	jLabel_fangyingting.setForeground(Color.blue);
	jPanel48.add(jLabel_fangyingting);
	
	JPanel jPanel46 = new JPanel();
	jPanel46.setBackground(Color.pink);
	jPanel46.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	jPanel46.add(new JLabel("票价："));
	jLabel_piaojia2.setFont(new java.awt.Font("Dialog", 1, 15));
	jLabel_piaojia2.setForeground(Color.blue);
	jPanel46.add(jLabel_piaojia2);
	
	JPanel jPanel47 = new JPanel();
	jPanel47.setBackground(Color.pink);
	jPanel47.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	jPanel47.add(new JLabel("时长："));
	jLabel_shichang2.setFont(new java.awt.Font("Dialog", 1, 15));
	jLabel_shichang2.setForeground(Color.blue);
	jPanel47.add(jLabel_shichang2);
	
	JPanel jPanel42 = new JPanel();
	jPanel42.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
	jPanel42.add(new JLabel("您目前所点击的位置："));
	
	JPanel jPanel43 = new JPanel();
	jPanel43.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
	jLabel_weizhi2.setText(zuowei);
	jPanel43.add(jLabel_weizhi2);
	jPanel42.setBackground(Color.pink);
	jPanel43.setBackground(Color.pink);
	
	JPanel jPanel44 = new JPanel();
	jPanel44.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
	JButton jButton_dingpiao2 = new JButton("订票");
	jButton_dingpiao2.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int a = string.charAt(1)-49;//在二维数组中的行
			int b = string.charAt(6)-49;//在二维数组中的列
//			System.out.println("2测试a和b:"+a+"---"+b);
			buttons2[a][b].setBackground(Color.red);
			String row = String.valueOf(a);
			String column = String.valueOf(b);
			//获取seat_id
			Seat seat1 = new Seat();
			String string1 = seat1.prinfPiaoSeat("2", row, column);
			int seat_id = Integer.valueOf(string1);
			System.out.println("测试seat_id = "+string1);
			//接收sched_id
			Ticket ticket1 = new Ticket();
			System.out.println("测试sched_id="+sched_id);
			int sched_id1 = Integer.valueOf(sched_id);
			//获取ticket_status
			int ticket_status = ticket1.printfTicketStatus(seat_id, sched_id1);
			System.out.println("测试ticket_status="+ticket_status);
			//获取ticket_id
			int ticket_id = ticket1.printfTicketId(seat_id, sched_id1);
			System.out.println("测试ticket_id="+ticket_id);
			//如果票的状态为0，则进行购票，否则不能购票
			if(ticket_id != 0){
				if(ticket_status == 0){
					//卖票，修改票的状态
					boolean ceshi = ticket1.ticketSell(ticket_id);
					if(ceshi==true){
						JOptionPane.showMessageDialog( null,"您需要支付的金额为："+price);
						System.out.println("售票成功");
					}else{
						System.out.println("售票失败");
					}
				}else{
					JOptionPane.showMessageDialog( null,"对不起！该座位已被预订 ^_^","消息", JOptionPane.ERROR_MESSAGE);
					System.out.println("该座位的票已经卖出，不能进行购票");
				}
			}else{
				
				JOptionPane.showMessageDialog( null,"您需要支付的金额为："+price);
				boolean sellAdd = ticket1.TicketSellAdd(seat_id, sched_id1, price);
				System.out.println("sellAdd="+sellAdd);
			}
			
			
//			JOptionPane.showMessageDialog( null,"订票成功！您的位置是："+string);
//		
////			buttons2[string.charAt(1)-49][string.charAt(5)-49].setBackground(Color.blue);
////			System.out.println(string.charAt(6));
//			int a = string.charAt(1)-49;//在二维数组中的行
//			int b = string.charAt(5)-49;//在二维数组中的列
////			System.out.println("row = "+a);
////			System.out.println("row = "+b);
//			buttons2[a][b].setBackground(Color.red);
//			String row = String.valueOf(a);
//			String column = String.valueOf(b);
//			Seat seat2 = new Seat();
//			String list2 = seat2.prinfPiaoSeat("2", row, column);
//			System.out.println("seat_id = "+list2);
		}
	});
	jPanel44.add(jButton_dingpiao2);
	jPanel44.setBackground(Color.pink);
	
	jPanel4.add(jPanel41);
	jPanel4.add(jPanel45);
	jPanel4.add(jPanel48);
	jPanel4.add(jPanel46);
	jPanel4.add(jPanel47);
	jPanel4.add(jPanel42);
	jPanel4.add(jPanel43);
	jPanel4.add(jPanel44);

	gridBagConstraints.gridx = 1;
	gridBagConstraints.gridy = 1;
	gridBagConstraints.fill = GridBagConstraints.BOTH;
	gridBagConstraints.weightx = 0;
	gridBagConstraints.weighty = 0;
	jPanel.add(jPanel4, gridBagConstraints);
	
	JPanel jPanel5 = new JPanel();
	jPanel5.setBackground(Color.orange);
	gridBagConstraints.gridx=0;
    gridBagConstraints.gridy=2;
    gridBagConstraints.gridwidth=2;
    jPanel.add(jPanel5, gridBagConstraints);
    
	
	jPanel.setBackground(Color.blue);
	return jPanel;
}
public JPanel YanChuTing3(){
	
	Studio studio3 = new Studio();
	List<StudioInformation> list3 = studio3.prinfStudios();
	StudioInformation studioInformation3 = list3.get(2);
	row_count = Integer.parseInt(studioInformation3.getStudio_row_count());
	column_count = Integer.parseInt(studioInformation3.getStudio_col_count());
	
	JPanel jPanel = new JPanel();
	jPanel.setLayout(new GridBagLayout());
	GridBagConstraints gridBagConstraints = new GridBagConstraints();
	Insets insets = new Insets(10, 5, 0, 5);
	gridBagConstraints.insets = insets;
	
	JLabel jLabel = new JLabel("3号演出厅座位表");
	gridBagConstraints.gridx = 0;
	gridBagConstraints.gridy = 0;
	jLabel.setFont(new java.awt.Font("Dialog", 1, 25));
	jLabel.setForeground(Color.red);
	jPanel.add(jLabel, gridBagConstraints);
	
	JLabel jLabel2 = new JLabel("订票信息");
	gridBagConstraints.gridx = 1;
	gridBagConstraints.gridy = 0;
	jLabel2.setFont(new java.awt.Font("Dialog", 1, 25));
	jLabel2.setForeground(Color.red);
	jPanel.add(jLabel2, gridBagConstraints);
	
	gridBagConstraints.fill = GridBagConstraints.BOTH;
	JPanel jPanel3 = NiaoKanTu3(row_count,column_count);
	jPanel3.setBackground(Color.pink);
	gridBagConstraints.gridx = 0;
	gridBagConstraints.gridy = 1;
	gridBagConstraints.weightx = 80;
	gridBagConstraints.weighty = 100;
	jPanel.add(jPanel3, gridBagConstraints);
	

	JPanel jPanel4 = new JPanel();
	jPanel4.setLayout(new GridLayout(8, 1));
	
	
	JPanel jPanel41 = new JPanel();
	jPanel41.setBackground(Color.pink);
	jPanel41.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
	jPanel41.add(new JLabel("电影名称："));
	
	JPanel jPanel45 = new JPanel();
	jPanel45.setBackground(Color.pink);
	jPanel45.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
	jLabel_Yingming3.setFont(new java.awt.Font("Dialog", 1, 15));
	jLabel_Yingming3.setForeground(Color.blue);
	jPanel45.add(jLabel_Yingming3);
	
	JPanel jPanel48 = new JPanel();
	jPanel48.setBackground(Color.pink);
	jPanel48.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	jPanel48.add(new JLabel("放映厅："));
	JLabel jLabel_fangyingting = new JLabel();
	jLabel_fangyingting.setText("3号放映厅");
	jLabel_fangyingting.setFont(new java.awt.Font("Dialog", 1, 15));
	jLabel_fangyingting.setForeground(Color.blue);
	jPanel48.add(jLabel_fangyingting);
	
	JPanel jPanel46 = new JPanel();
	jPanel46.setBackground(Color.pink);
	jPanel46.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	jPanel46.add(new JLabel("票价："));
	jLabel_piaojia3.setFont(new java.awt.Font("Dialog", 1, 15));
	jLabel_piaojia3.setForeground(Color.blue);
	jPanel46.add(jLabel_piaojia3);
	
	JPanel jPanel47 = new JPanel();
	jPanel47.setBackground(Color.pink);
	jPanel47.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	jPanel47.add(new JLabel("时长："));
	jLabel_shichang3.setFont(new java.awt.Font("Dialog", 1, 15));
	jLabel_shichang3.setForeground(Color.blue);
	jPanel47.add(jLabel_shichang3);
	
	JPanel jPanel42 = new JPanel();
	jPanel42.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
	jPanel42.add(new JLabel("您目前所点击的位置："));
	
	JPanel jPanel43 = new JPanel();
	jPanel43.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
	jLabel_weizhi3.setText(zuowei);
	jPanel43.add(jLabel_weizhi3);
	jPanel42.setBackground(Color.pink);
	jPanel43.setBackground(Color.pink);
	
	JPanel jPanel44 = new JPanel();
	jPanel44.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
	JButton jButton_dingpiao3 = new JButton("订票");
	jButton_dingpiao3.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int a = string.charAt(1)-49;//在二维数组中的行
			int b = string.charAt(6)-49;//在二维数组中的列
			buttons3[a][b].setBackground(Color.red);
			String row = String.valueOf(a);
			String column = String.valueOf(b);
			//获取seat_id
			Seat seat1 = new Seat();
			String string1 = seat1.prinfPiaoSeat("3", row, column);
			int seat_id = Integer.valueOf(string1);
			System.out.println("测试seat_id = "+string1);
			//接收sched_id
			Ticket ticket1 = new Ticket();
			System.out.println("测试sched_id="+sched_id);
			int sched_id1 = Integer.valueOf(sched_id);
			//获取ticket_status
			int ticket_status = ticket1.printfTicketStatus(seat_id, sched_id1);
			System.out.println("测试ticket_status="+ticket_status);
			//获取ticket_id
			int ticket_id = ticket1.printfTicketId(seat_id, sched_id1);
			System.out.println("测试ticket_id="+ticket_id);
			//如果票的状态为0，则进行购票，否则不能购票
			if(ticket_id != 0){
				if(ticket_status == 0){
					//卖票，修改票的状态
					boolean ceshi = ticket1.ticketSell(ticket_id);
					if(ceshi==true){
						JOptionPane.showMessageDialog( null,"您需要支付的金额为："+price);
						System.out.println("售票成功");
					}else{
						System.out.println("售票失败");
					}
				}else{
					JOptionPane.showMessageDialog( null,"对不起！该座位已被预订 ^_^","消息", JOptionPane.ERROR_MESSAGE);
					System.out.println("该座位的票已经卖出，不能进行购票");
				}
			}else{
				
				JOptionPane.showMessageDialog( null,"您需要支付的金额为："+price);
				boolean sellAdd = ticket1.TicketSellAdd(seat_id, sched_id1, price);
				System.out.println("sellAdd="+sellAdd);
			}
			
			
//			JOptionPane.showMessageDialog( null,"订票成功！您的位置是："+string);
//		
////			buttons3[string.charAt(1)-49][string.charAt(5)-49].setBackground(Color.blue);
////			System.out.println(string.charAt(6));
//			int a = string.charAt(1)-49;//在二维数组中的行
//			int b = string.charAt(5)-49;//在二维数组中的列
////			System.out.println("row = "+a);
////			System.out.println("row = "+b);
//			buttons3[a][b].setBackground(Color.red);
//			String row = String.valueOf(a);
//			String column = String.valueOf(b);
//			Seat seat3 = new Seat();
//			String list3 = seat3.prinfPiaoSeat("3", row, column);
//			System.out.println("seat_id = "+list3);
		}
	});
	jPanel44.add(jButton_dingpiao3);
	jPanel44.setBackground(Color.pink);
	
	jPanel4.add(jPanel41);
	jPanel4.add(jPanel45);
	jPanel4.add(jPanel48);
	jPanel4.add(jPanel46);
	jPanel4.add(jPanel47);
	jPanel4.add(jPanel42);
	jPanel4.add(jPanel43);
	jPanel4.add(jPanel44);

	gridBagConstraints.gridx = 1;
	gridBagConstraints.gridy = 1;
	gridBagConstraints.fill = GridBagConstraints.BOTH;
	gridBagConstraints.weightx = 0;
	gridBagConstraints.weighty = 0;
	jPanel.add(jPanel4, gridBagConstraints);
	
	JPanel jPanel5 = new JPanel();
	jPanel5.setBackground(Color.orange);
	gridBagConstraints.gridx=0;
    gridBagConstraints.gridy=2;
    gridBagConstraints.gridwidth=2;
    jPanel.add(jPanel5, gridBagConstraints);
    
	
	jPanel.setBackground(Color.gray);
	return jPanel;
}
public JPanel YanChuTing4(){
	
	Studio studio4 = new Studio();
	List<StudioInformation> list4 = studio4.prinfStudios();
	StudioInformation studioInformation4 = list4.get(3);
	row_count = Integer.parseInt(studioInformation4.getStudio_row_count());
	column_count = Integer.parseInt(studioInformation4.getStudio_col_count());
	
	JPanel jPanel = new JPanel();
	jPanel.setLayout(new GridBagLayout());
	GridBagConstraints gridBagConstraints = new GridBagConstraints();
	Insets insets = new Insets(10, 5, 0, 5);
	gridBagConstraints.insets = insets;
	
	JLabel jLabel = new JLabel("4号演出厅座位表");
	gridBagConstraints.gridx = 0;
	gridBagConstraints.gridy = 0;
	jLabel.setFont(new java.awt.Font("Dialog", 1, 25));
	jLabel.setForeground(Color.red);
	jPanel.add(jLabel, gridBagConstraints);
	
	JLabel jLabel2 = new JLabel("订票信息");
	gridBagConstraints.gridx = 1;
	gridBagConstraints.gridy = 0;
	jLabel2.setFont(new java.awt.Font("Dialog", 1, 25));
	jLabel2.setForeground(Color.red);
	jPanel.add(jLabel2, gridBagConstraints);
	
	gridBagConstraints.fill = GridBagConstraints.BOTH;
	JPanel jPanel3 = NiaoKanTu4(row_count,column_count);
	jPanel3.setBackground(Color.pink);
	gridBagConstraints.gridx = 0;
	gridBagConstraints.gridy = 1;
	gridBagConstraints.weightx = 80;
	gridBagConstraints.weighty = 100;
	jPanel.add(jPanel3, gridBagConstraints);
	

	JPanel jPanel4 = new JPanel();
	jPanel4.setLayout(new GridLayout(8, 1));
	
	
	JPanel jPanel41 = new JPanel();
	jPanel41.setBackground(Color.pink);
	jPanel41.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
	jPanel41.add(new JLabel("电影名称："));
	
	JPanel jPanel45 = new JPanel();
	jPanel45.setBackground(Color.pink);
	jPanel45.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
	jLabel_Yingming4.setFont(new java.awt.Font("Dialog", 1, 15));
	jLabel_Yingming4.setForeground(Color.blue);
	jPanel45.add(jLabel_Yingming4);
	
	JPanel jPanel48 = new JPanel();
	jPanel48.setBackground(Color.pink);
	jPanel48.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	jPanel48.add(new JLabel("放映厅："));
	JLabel jLabel_fangyingting = new JLabel();
	jLabel_fangyingting.setText("4号放映厅");
	jLabel_fangyingting.setFont(new java.awt.Font("Dialog", 1, 15));
	jLabel_fangyingting.setForeground(Color.blue);
	jPanel48.add(jLabel_fangyingting);
	
	JPanel jPanel46 = new JPanel();
	jPanel46.setBackground(Color.pink);
	jPanel46.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	jPanel46.add(new JLabel("票价："));
	jLabel_piaojia4.setFont(new java.awt.Font("Dialog", 1, 15));
	jLabel_piaojia4.setForeground(Color.blue);
	jPanel46.add(jLabel_piaojia4);
	
	JPanel jPanel47 = new JPanel();
	jPanel47.setBackground(Color.pink);
	jPanel47.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	jPanel47.add(new JLabel("时长："));
	jLabel_shichang4.setFont(new java.awt.Font("Dialog", 1, 15));
	jLabel_shichang4.setForeground(Color.blue);
	jPanel47.add(jLabel_shichang4);
	
	JPanel jPanel42 = new JPanel();
	jPanel42.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
	jPanel42.add(new JLabel("您目前所点击的位置："));
	
	JPanel jPanel43 = new JPanel();
	jPanel43.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
	jLabel_weizhi4.setText(zuowei);
	jPanel43.add(jLabel_weizhi4);
	jPanel42.setBackground(Color.pink);
	jPanel43.setBackground(Color.pink);
	JPanel jPanel44 = new JPanel();
	jPanel44.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
	JButton jButton_dingpiao4 = new JButton("订票");
	jButton_dingpiao4.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int a = string.charAt(1)-49;//在二维数组中的行
			int b = string.charAt(6)-49;//在二维数组中的列
			buttons4[a][b].setBackground(Color.red);
			String row = String.valueOf(a);
			String column = String.valueOf(b);
			//获取seat_id
			Seat seat1 = new Seat();
			String string1 = seat1.prinfPiaoSeat("4", row, column);
			int seat_id = Integer.valueOf(string1);
			System.out.println("测试seat_id = "+string1);
			//接收sched_id
			Ticket ticket1 = new Ticket();
			System.out.println("测试sched_id="+sched_id);
			int sched_id1 = Integer.valueOf(sched_id);
			//获取ticket_status
			int ticket_status = ticket1.printfTicketStatus(seat_id, sched_id1);
			System.out.println("测试ticket_status="+ticket_status);
			//获取ticket_id
			int ticket_id = ticket1.printfTicketId(seat_id, sched_id1);
			System.out.println("测试ticket_id="+ticket_id);
			//如果票的状态为0，则进行购票，否则不能购票
			if(ticket_id != 0){
				if(ticket_status == 0){
					//卖票，修改票的状态
					boolean ceshi = ticket1.ticketSell(ticket_id);
					if(ceshi==true){
						JOptionPane.showMessageDialog( null,"您需要支付的金额为："+price);
						System.out.println("售票成功");
					}else{
						System.out.println("售票失败");
					}
				}else{
					JOptionPane.showMessageDialog( null,"对不起！该座位已被预订 ^_^","消息", JOptionPane.ERROR_MESSAGE);
					System.out.println("该座位的票已经卖出，不能进行购票");
				}
			}else{
				
				JOptionPane.showMessageDialog( null,"您需要支付的金额为："+price);
				boolean sellAdd = ticket1.TicketSellAdd(seat_id, sched_id1, price);
				System.out.println("sellAdd="+sellAdd);
			}
			
			
//			JOptionPane.showMessageDialog( null,"订票成功！您的位置是："+string);
//			
////			buttons4[string.charAt(1)-49][string.charAt(5)-49].setBackground(Color.blue);
//		
////			System.out.println(string.charAt(6));
//			int a = string.charAt(1)-49;//在二维数组中的行
//			int b = string.charAt(5)-49;//在二维数组中的列
////			System.out.println("row = "+a);
////			System.out.println("row = "+b);
//			buttons4[a][b].setBackground(Color.red);
//			String row = String.valueOf(a);
//			String column = String.valueOf(b);
//			Seat seat4 = new Seat();
//			String list4 = seat4.prinfPiaoSeat("4", row, column);
//			System.out.println("seat_id = "+list4);
		}
	});
	jPanel44.add(jButton_dingpiao4);
	jPanel44.setBackground(Color.pink);
	
	jPanel4.add(jPanel41);
	jPanel4.add(jPanel45);
	jPanel4.add(jPanel48);
	jPanel4.add(jPanel46);
	jPanel4.add(jPanel47);
	jPanel4.add(jPanel42);
	jPanel4.add(jPanel43);
	jPanel4.add(jPanel44);

	gridBagConstraints.gridx = 1;
	gridBagConstraints.gridy = 1;
	gridBagConstraints.fill = GridBagConstraints.BOTH;
	gridBagConstraints.weightx = 0;
	gridBagConstraints.weighty = 0;
	jPanel.add(jPanel4, gridBagConstraints);
	
	JPanel jPanel5 = new JPanel();
	jPanel5.setBackground(Color.orange);
	gridBagConstraints.gridx=0;
    gridBagConstraints.gridy=2;
    gridBagConstraints.gridwidth=2;
    jPanel.add(jPanel5, gridBagConstraints);
    
	
	jPanel.setBackground(Color.GREEN);
	return jPanel;
}
	
	public JScrollPane YingTingXinXi(int a,int b){
		
		JPanel jPanel_Zuowei = ShnegChengZuoWei(a,b);
		jPanel_Zuowei.setBackground(Color.pink);
		JScrollPane jScrollPane = new JScrollPane(jPanel_Zuowei);
		
		return jScrollPane;
	}
	public JScrollPane YingTingXinXi2(int a,int b){
		
		JPanel jPanel_Zuowei = ShnegChengZuoWei2(a,b);
		jPanel_Zuowei.setBackground(Color.pink);
		JScrollPane jScrollPane = new JScrollPane(jPanel_Zuowei);
		
		return jScrollPane;
	}

	public JScrollPane YingTingXinXi3(int a,int b){
	
		JPanel jPanel_Zuowei = ShnegChengZuoWei3(a,b);
		jPanel_Zuowei.setBackground(Color.pink);
		JScrollPane jScrollPane = new JScrollPane(jPanel_Zuowei);
	
		return jScrollPane;
	}

	public JScrollPane YingTingXinXi4(int a,int b){
	
		JPanel jPanel_Zuowei = ShnegChengZuoWei4(a,b);
		jPanel_Zuowei.setBackground(Color.pink);
		JScrollPane jScrollPane = new JScrollPane(jPanel_Zuowei);
	
		return jScrollPane;
	}
	
	public JPanel NiaoKanTu(int a,int b){
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		Insets insets = new Insets(10, 10, 10, 1);
		gridBagConstraints.insets = insets;
		
		JLabel jLabel = new JLabel();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		jPanel.add(jLabel, gridBagConstraints);
		
		JLabel jLabel3 = new JLabel();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		jPanel.add(jLabel3, gridBagConstraints);
		
		JPanel jPanel2 = new JPanel();
		JLabel jLabel2 = new JLabel("银  幕");
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		jPanel2.setBackground(Color.blue);
		jPanel2.add(jLabel2);
		jPanel.add(jPanel2, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		JScrollPane jScrollPane = YingTingXinXi(a,b);
		jScrollPane.setBackground(Color.pink);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 100;
		gridBagConstraints.weighty = 100;
		gridBagConstraints.gridwidth = 2;
		jPanel.add(jScrollPane, gridBagConstraints);
		
		return jPanel;
	}
	
	public JPanel NiaoKanTu2(int a,int b){
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		Insets insets = new Insets(10, 10, 10, 1);
		gridBagConstraints.insets = insets;
		
		JLabel jLabel = new JLabel();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		jPanel.add(jLabel, gridBagConstraints);
		
		JLabel jLabel3 = new JLabel();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		jPanel.add(jLabel3, gridBagConstraints);
		
		JPanel jPanel2 = new JPanel();
		JLabel jLabel2 = new JLabel("银  幕");
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		jPanel2.setBackground(Color.blue);
		jPanel2.add(jLabel2);
		jPanel.add(jPanel2, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		JScrollPane jScrollPane = YingTingXinXi2(a,b);
		jScrollPane.setBackground(Color.pink);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 100;
		gridBagConstraints.weighty = 100;
		gridBagConstraints.gridwidth = 2;
		jPanel.add(jScrollPane, gridBagConstraints);
		
		return jPanel;
	}
	
	public JPanel NiaoKanTu3(int a,int b){
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		Insets insets = new Insets(10, 10, 10, 1);
		gridBagConstraints.insets = insets;
		
		JLabel jLabel = new JLabel();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		jPanel.add(jLabel, gridBagConstraints);
		
		JLabel jLabel3 = new JLabel();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		jPanel.add(jLabel3, gridBagConstraints);
		
		JPanel jPanel2 = new JPanel();
		JLabel jLabel2 = new JLabel("银  幕");
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		jPanel2.setBackground(Color.blue);
		jPanel2.add(jLabel2);
		jPanel.add(jPanel2, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		JScrollPane jScrollPane = YingTingXinXi3(a,b);
		jScrollPane.setBackground(Color.pink);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 100;
		gridBagConstraints.weighty = 100;
		gridBagConstraints.gridwidth = 2;
		jPanel.add(jScrollPane, gridBagConstraints);
		
		return jPanel;
	}
	
	public JPanel NiaoKanTu4(int a,int b){
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		Insets insets = new Insets(10, 10, 10, 1);
		gridBagConstraints.insets = insets;
		
		JLabel jLabel = new JLabel();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		jPanel.add(jLabel, gridBagConstraints);
		
		JLabel jLabel3 = new JLabel();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		jPanel.add(jLabel3, gridBagConstraints);
		
		JPanel jPanel2 = new JPanel();
		JLabel jLabel2 = new JLabel("银  幕");
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		jPanel2.setBackground(Color.blue);
		jPanel2.add(jLabel2);
		jPanel.add(jPanel2, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		JScrollPane jScrollPane = YingTingXinXi4(a,b);
		jScrollPane.setBackground(Color.pink);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 100;
		gridBagConstraints.weighty = 100;
		gridBagConstraints.gridwidth = 2;
		jPanel.add(jScrollPane, gridBagConstraints);
		
		return jPanel;
	}



}

