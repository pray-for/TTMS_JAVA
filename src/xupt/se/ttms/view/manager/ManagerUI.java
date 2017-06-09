package xupt.se.ttms.view.manager;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import xupt.se.ttms.view.query.Manager;
import xupt.se.ttms.view.statistics.Statistics;
import xupt.se.ttms.view.statistics.StatisticsPicture;
import xupt.se.ttms.view.statistics.StatisticsPicture1;
import xupt.se.ttms.view.statistics.StatisticsPicture2;

public class ManagerUI extends JFrame{

//	private String path = "D:/Android Data/CLWZZ/src/iamge/";
	
	public ManagerUI(){
		this.setTitle("309国际剧院票务管理系统");
		this.setSize(1366, 750);			//大小
		this.setLocationRelativeTo(null);		//窗体出现居中
		this.setDefaultCloseOperation(3);		//设置关闭操作
		this.setResizable(true);
//		this.setIconImage(new ImageIcon(path+"tiele.bmp").getImage());

		Statistics statistics = new Statistics();
		StatisticsPicture statisticsPicture = new StatisticsPicture();
		StatisticsPicture1 statisticsPicture1 = new StatisticsPicture1();
		StatisticsPicture2 statisticsPicture2 = new StatisticsPicture2();
		Manager manager = new Manager();
		
		JTabbedPane jTabbedPane = new JTabbedPane();
		
		jTabbedPane.add("统计销售额", statistics.Statistics());
		jTabbedPane.add("影片销售统计",statisticsPicture.getChartPanel());
		jTabbedPane.add("演出厅销售统计",statisticsPicture1.getChartPanel());
		jTabbedPane.add("月销售统计",statisticsPicture2.getChartPanel());
		jTabbedPane.addTab("查询演出票", manager.Manager());

		jTabbedPane.setBackground(Color.green);
		
		jTabbedPane.setTabPlacement(JTabbedPane.LEFT);
		this.add(jTabbedPane); 
		this.setVisible(true);
	}

}
