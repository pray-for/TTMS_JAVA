package xupt.se.ttms.view.statistics;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import xupt.se.ttms.sql.Play;
import xupt.se.ttms.sql.Schedule;
import xupt.se.ttms.sql.Ticket;
import xupt.se.util.PlayInformation;
import xupt.se.util.PlayStatisticsInformation;

public class StatisticsPicture {

	public static String play_name; 
	public static int sumSta;
	public static String play_price;
	
	
	ChartPanel frame1;
	public StatisticsPicture(){
		Play play = new Play();
		Schedule schedule = new Schedule();
		Ticket ticket = new Ticket();
		DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
		List<PlayInformation> list_play = play.prinfPlays();
		for(int i = 0; i < list_play.size(); i++){
			PlayInformation playInformation = list_play.get(i);
			String play_id = String.valueOf(playInformation.getPlay_id());
			play_name = playInformation.getPlay_name();
			play_price = playInformation.getPlay_ticket_price();
			List<PlayStatisticsInformation> list_play_sta = schedule.getSched_id_play(play_id);
			int sum = 0;
			for(int j = 0; j < list_play_sta.size(); j++){//获取每个play_id的sched_id
				PlayStatisticsInformation playStatisticsInformation = list_play_sta.get(j);
				int sched_id_play = playStatisticsInformation.getSched_id();
				List<PlayStatisticsInformation> list_play_tic = ticket.StatisticsPlay(sched_id_play);
				int count = list_play_tic.size();
				sum = sum + count;
			}
			sumSta = (int) (sum * Float.valueOf(play_price));
			defaultCategoryDataset.addValue(sumSta, play_name, play_name);
		}


//		CategoryDataset dataset = getDataSet(sumSta,play_name);
        JFreeChart chart = ChartFactory.createBarChart3D(
       		                 "影片销售统计", // 图表标题
                            "影片名称", // 目录轴的显示标签
                            "销售金额", // 数值轴的显示标签
                            defaultCategoryDataset, // 数据集
                            PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                            true,           // 是否显示图例(对于简单的柱状图必须是false)
                            false,          // 是否生成工具
                            false           // 是否生成URL链接
                            );
        
        //从这里开始
        CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis=plot.getDomainAxis();         //水平底部列表
        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
        domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
        
          
        frame1=new ChartPanel(chart,true);        //这里也可以用chartFrame,可以直接生成一个独立的Frame
         
	}
	public static CategoryDataset getDataSet(int sum,String name) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(sum, name, name);
        return dataset;
	}


	   public ChartPanel getChartPanel(){
		   return frame1;
	
	   }
	
}
