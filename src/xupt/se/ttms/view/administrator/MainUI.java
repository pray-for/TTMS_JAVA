package xupt.se.ttms.view.administrator;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;





import xupt.se.ttms.view.jumuguanli.ShouPiaoYuanZhangHaoGuanLi_Ui;
import xupt.se.ttms.view.jumuguanli.ZuoWeiGuanLi_Ui;
//import schedule.Schedule1;
import xupt.se.ttms.view.play.JuMuGuanLiUI;
import xupt.se.ttms.view.schedule.Schedule1;
import xupt.se.ttms.view.yanchuting.YanChuTingGuanLi_Ui;


public class MainUI extends JFrame
{

    private String path = "D:/Android Data/CLWZZ/src/iamge/";
    JTabbedPane jTabbedPane;

    public MainUI()
    {
        this.setTitle("309国际剧院管理系统");

        this.setSize(1366, 750); // 大小

        this.setLocationRelativeTo(null); // 窗体出现居中

        this.setDefaultCloseOperation(3); // 设置关闭操作

        this.setResizable(true);

        this.setIconImage(new ImageIcon(path + "tiele.bmp").getImage());

        JuMuGuanLiUI juMuGuanLi_Ui = new JuMuGuanLiUI();

        jTabbedPane = new JTabbedPane();

        jTabbedPane.add("剧目管理", juMuGuanLi_Ui.JuMuGuanLi());

        Schedule1 sch = new Schedule1();

        jTabbedPane.add("演出计划", sch.schedu());
        
        ZuoWeiGuanLi_Ui zuoweiguanli_Ui=new ZuoWeiGuanLi_Ui();

        jTabbedPane.add("座位管理", zuoweiguanli_Ui.zuoweiguanli());
        
        ShouPiaoYuanZhangHaoGuanLi_Ui shouPiaoYuanZhangHaoGuanLi_Ui = new ShouPiaoYuanZhangHaoGuanLi_Ui();

        jTabbedPane.add("员工信息管理", shouPiaoYuanZhangHaoGuanLi_Ui.ShouPiaoYuanZhangHaoGuanLi());
        
        YanChuTingGuanLi_Ui yanchuting = new YanChuTingGuanLi_Ui();

        jTabbedPane.add("演出厅管理",yanchuting.YanChuTingGuanLi());

        jTabbedPane.setBackground(Color.green);

        jTabbedPane.setTabPlacement(JTabbedPane.LEFT);

        this.add(jTabbedPane);

        this.setVisible(true);

    }

    public void setSelectedPan(int index)
    {
        jTabbedPane.setSelectedIndex(index);
    }

}
