package xupt.se.ttms.view.statistics;

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
import java.util.List;

import javax.swing.JButton;
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

import xupt.se.ttms.sql.Employee;
import xupt.se.ttms.sql.Play;
import xupt.se.ttms.sql.Schedule;
import xupt.se.ttms.sql.Studio;
import xupt.se.ttms.sql.Ticket;
import xupt.se.ttms.view.movieInformation.MovieInformationUI;
import xupt.se.util.EmployeeInformation;
import xupt.se.util.PlayInformation;
import xupt.se.util.PlayStatisticsInformation;
import xupt.se.util.ScheduleInformation;
import xupt.se.util.StudioInformation;
import xupt.se.util.StudioStatisticsInformation;

public class Statistics {
	
 
	public static String price = null;
	
	public JTabbedPane Statistics(){

		Play play = new Play();
		Schedule schedule = new Schedule();
		Ticket ticket = new Ticket();
		StatisticsPicture statisticsPicture = new StatisticsPicture();
		String[] strings_play = {"电影名称","售票总数","售票金额总数"};
		//对play和schedule进行操作
		List<PlayInformation> list_play = play.prinfPlays();
		Object[][] objects_play = new Object[list_play.size()][3];
		for(int i = 0; i < list_play.size(); i++){//显示play_name，获取play_id
			PlayInformation playInformation = list_play.get(i);
			objects_play[i][0] = playInformation.getPlay_name();
			String play_id = String.valueOf(playInformation.getPlay_id());
			String play_price = String.valueOf(playInformation.getPlay_ticket_price());
			price = play_price;
			//对schedule和ticket进行操作
			List<PlayStatisticsInformation> list_play_sta = schedule.getSched_id_play(play_id);
			int sum = 0;
			for(int j = 0; j < list_play_sta.size(); j++){//获取每个play_id的sched_id
				PlayStatisticsInformation playStatisticsInformation = list_play_sta.get(j);
				int sched_id_play = playStatisticsInformation.getSched_id();
				System.out.println("sched_id_play="+sched_id_play);
				List<PlayStatisticsInformation> list_play_tic = ticket.StatisticsPlay(sched_id_play);
				int count = list_play_tic.size();
				sum = sum + count;
			}
			objects_play[i][1] = sum;
			objects_play[i][2] = sum * Float.valueOf(play_price);
			System.out.println("----------sum_play="+sum);
			statisticsPicture.getDataSet((int)(sum*Float.valueOf(play_price)), playInformation.getPlay_name());
		}

		System.out.println("---------------------------------");
		
		String[] strings_studio = {"演出厅名称","售票总数","售票金额总数"};
		Studio studio = new Studio();
		//对studio和schedule进行操作
		List<StudioInformation> list_studio = studio.prinfStudios();
		Object[][] objects_studio = new Object[list_studio.size()][3];
		for(int i = 0; i < list_studio.size(); i++){
			StudioInformation studioInformation = list_studio.get(i);
			objects_studio[i][0] = studioInformation.getStudio_name();
			String studio_id = String.valueOf(studioInformation.getStudio_id());
			//对schedule和ticket进行操作
			List<StudioStatisticsInformation> list_studio_sta = schedule.getSched_id_studio(studio_id);
			int sum1 = 0;
			for(int j = 0; j < list_studio_sta.size(); j++){
				StudioStatisticsInformation studioStatisticsInformation = list_studio_sta.get(j);
				int sched_id_studio = studioStatisticsInformation.getSched_id();
				System.out.println("sched_id_studio="+sched_id_studio);
				List<StudioStatisticsInformation> list_studio_tic = ticket.StatisticsStudio(sched_id_studio);
				int count = list_studio_tic.size();
				sum1 = sum1 + count;
			}
			objects_studio[i][1] = sum1;
			objects_studio[i][2] = sum1 * Float.valueOf(price);
			System.out.println("----------sum_stdio="+sum1);
		}
		
		JTabbedPane jTabbedPane_Statistics = new JTabbedPane();
		StatisticsSale statisticsSale = new StatisticsSale();
		jTabbedPane_Statistics.add("按剧目统计", statisticsSale.Statistics_Play(strings_play, objects_play));
		jTabbedPane_Statistics.add("按演出厅统计", statisticsSale.Statistics_Studio(strings_studio, objects_studio));
//		jTabbedPane_Statistics.add("按销售员统计", statisticsSale.Statistics_Employee(strings_employee, objects_employee));
		jTabbedPane_Statistics.setBackground(Color.red);
		jTabbedPane_Statistics.setTabPlacement(JTabbedPane.TOP);
		
		return jTabbedPane_Statistics;
	}

}
class StatisticsSale{
	
	private DefaultTableModel tableModel_play;
	private JTable jTable_play;
	
	private DefaultTableModel tableModel_studio;
	private JTable jTable_studio;
	JScrollPane jScrollPane;
	public static Float ticket_price;

	
	public JPanel Statistics_Play(String[] biaotou,Object[][] chengyuan){
		JPanel jPanel = new JPanel();
		jPanel.setBackground(Color.orange);
		
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		Insets insets = new Insets(10, 5, 0, 5);
		gridBagConstraints.insets = insets;
		
		JLabel jLabel = new JLabel("按剧目统计");
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		jPanel.add(jLabel, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		jTable_play = ZhiBiao_Play(biaotou,chengyuan);
		jTable_play.setBackground(Color.pink);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 120;
		gridBagConstraints.weighty = 100;
		jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(jTable_play);
		jScrollPane.setBackground(Color.orange);
		jPanel.add(jScrollPane, gridBagConstraints);
		
		
		JPanel jPanel5 = new JPanel();
		jPanel5.setBackground(Color.orange);
		
		jPanel5.setLayout(new GridLayout(2,0));
		
		JPanel jPanel3 = new JPanel();
		jPanel3.setLayout(new FlowLayout());
		
		final JButton jButton_jumu_zengjia = new JButton("当天销售情况");
		jButton_jumu_zengjia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				Ticket ticket = new Ticket();
				Play play = new Play();
//				System.out.println(ticket.TodayTime("8"));
		
				List<PlayInformation> list_play = play.prinfPlays();
				Object[][] objects_play = new Object[list_play.size()][3];
				
				jPanel.remove(jScrollPane);
				Float price = (float) 0;
				for(int i = 0; i < list_play.size(); i++){//显示play_name，获取play_id
					PlayInformation playInformation = list_play.get(i);
					objects_play[i][0] = playInformation.getPlay_name();
					String play_id = String.valueOf(playInformation.getPlay_id());
					price = Float.parseFloat(playInformation.getPlay_ticket_price());
					ticket_price = price;
					float countSum = (float)ticket.TodayTimePlay(play_id);
//					System.out.println("count="+countSum);
					objects_play[i][2] = countSum;
					int count = (int)Math.ceil(countSum/price);
					objects_play[i][1] = count;
				}

				jTable_play = ZhiBiao_Play(biaotou,objects_play);
				jTable_play.setBackground(Color.magenta);
				gridBagConstraints.gridx = 0;
				gridBagConstraints.gridy = 1;
				gridBagConstraints.weightx = 120;
				gridBagConstraints.weighty = 100;
				jScrollPane = new JScrollPane();
				jScrollPane.setViewportView(jTable_play);
				jScrollPane.setBackground(Color.orange);
				jPanel.add(jScrollPane, gridBagConstraints);
				jPanel.repaint();
				jPanel.validate();
				
			}
		});

		
		final JButton jButton_jumu_shanchu = new JButton("本月销售情况");
		jButton_jumu_shanchu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				int select = jTable_play.getSelectedRow();
//				if(select != -1){
//					tableModel_play.removeRow(select);
//				}
				
			}
		});
		
		jPanel3.add(jButton_jumu_zengjia);
		jPanel3.add(jButton_jumu_shanchu);
		
		jPanel5.add(jPanel3);
		
		gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=2;
        gridBagConstraints.weightx = 10;
		gridBagConstraints.weighty = 10;
        jPanel.add(jPanel5, gridBagConstraints);
		
		return jPanel;
	}
	
	
	public JPanel Statistics_Studio(String[] biaotou,Object[][] chengyuan){
		JPanel jPanel = new JPanel();
		jPanel.setBackground(Color.orange);
		
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		Insets insets = new Insets(10, 5, 0, 5);
		gridBagConstraints.insets = insets;
		
		JLabel jLabel = new JLabel("按演出厅统计");
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		jPanel.add(jLabel, gridBagConstraints);
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		jTable_studio = ZhiBiao_Studio(biaotou,chengyuan);
		jTable_studio.setBackground(Color.pink);
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 120;
		gridBagConstraints.weighty = 100;
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(jTable_studio);
		jScrollPane.setBackground(Color.yellow);
		jPanel.add(jScrollPane, gridBagConstraints);
		
		
		JPanel jPanel5 = new JPanel();
		jPanel5.setBackground(Color.orange);
		
		jPanel5.setLayout(new GridLayout(2,0));
		
		JPanel jPanel3 = new JPanel();
		jPanel3.setLayout(new FlowLayout());
		
		final JButton jButton_renqi_zengjia = new JButton("当天销售情况");
		jButton_renqi_zengjia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Ticket ticket = new Ticket();
				Studio studio = new Studio();
//				System.out.println("studio----"+ticket.TodayTimeStudio("4"));
				
				List<StudioInformation> list_studio = studio.prinfStudios();
				Object[][] objects_studio = new Object[list_studio.size()][3];
				Float price = (float)0;
				jPanel.remove(jScrollPane);
				
				for(int i = 0; i < list_studio.size(); i++){
					StudioInformation studioInformation = list_studio.get(i);
					objects_studio[i][0] = studioInformation.getStudio_name();
					String studio_id = String.valueOf(studioInformation.getStudio_id());
					float countSum = (float)ticket.TodayTimeStudio(studio_id);
//					price = ticket_price;
//					System.out.println("price="+price);
					System.out.println("count="+countSum);
					objects_studio[i][2] = countSum;
					int count = (int)Math.ceil(countSum/30.0);
					objects_studio[i][1] = count;
				}
				jTable_studio = ZhiBiao_Studio(biaotou,objects_studio);
				jTable_studio.setBackground(Color.magenta);
				gridBagConstraints.gridx = 0;
				gridBagConstraints.gridy = 1;
				gridBagConstraints.weightx = 120;
				gridBagConstraints.weighty = 100;
				JScrollPane jScrollPane = new JScrollPane();
				jScrollPane.setViewportView(jTable_studio);
				jScrollPane.setBackground(Color.yellow);
				jPanel.add(jScrollPane, gridBagConstraints);
				jPanel.repaint();
				jPanel.validate();
			}
		});
		
		final JButton jButton_renqi_shanchu1 = new JButton("本月销售情况");
		jButton_renqi_shanchu1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				int select = jTable_studio.getSelectedRow();
//				if(select != -1){
//					tableModel_studio.removeRow(select);
//				}
				
			}
		});
		
		jPanel3.add(jButton_renqi_zengjia);
		jPanel3.add(jButton_renqi_shanchu1);

		jPanel5.add(jPanel3);
		
		gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=2;
        gridBagConstraints.weightx = 10;
		gridBagConstraints.weighty = 10;
        jPanel.add(jPanel5, gridBagConstraints);
		
		return jPanel;
	}
	
	
	public JTable ZhiBiao_Play(String[] biaotou,Object[][] chengyuan){
		
		tableModel_play = new DefaultTableModel(chengyuan, biaotou);
		JTable jTable1 = new JTable(tableModel_play);
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
	
	public JTable ZhiBiao_Studio(String[] biaotou,Object[][] chengyuan){
		
		tableModel_studio = new DefaultTableModel(chengyuan, biaotou);
		JTable jTable1 = new JTable(tableModel_studio);
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
