package xupt.se.ttms.view.schedule;

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
import javax.swing.JComboBox;
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

import xupt.se.ttms.dao.ScheduleDAO;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.service.DataDictSrv;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.view.administrator.MainUI;


public class Schedule1 extends JFrame
{
    public JTabbedPane schedu()
    {
        ScheduleDAO st = new ScheduleDAO();

        String[] strings_schedule = { "演出id", "演出厅id", "剧目id", "演出时间", "影片价格" };

        Object[][] objects_schedule = new Object[50][5];

        st.select(objects_schedule);

        JTabbedPane jTabbedPane_schedule = new JTabbedPane();

        schedule sched = new schedule();

        jTabbedPane_schedule.add("演出计划", sched.JuMu_schedule(strings_schedule, objects_schedule));

        jTabbedPane_schedule.setBackground(Color.red);

        jTabbedPane_schedule.setTabPlacement(JTabbedPane.TOP);

        return jTabbedPane_schedule;

    }
}

class schedule
{
    private DefaultTableModel tableModel_schedule;
    private JTable jTable_schedule;
    private JTextField jTextField_schedule_schedid;
    private JComboBox<Object> studioid;
    private JComboBox<Object> playid;
    private JTextField jTextField_schedule_schedtime;
    private JTextField jTextField_shedule_price;
    DataDictSrv dataDictSrv = new DataDictSrv();

    public schedule()
    {
        ScheduleSrv scheduleSrv = new ScheduleSrv();
        Object[] liststudio = new Object[100];
        scheduleSrv.findByID(liststudio);
        Object[] listplay = new Object[100];
        scheduleSrv.findPlayByID(listplay);
        Object[] studio = new Object[liststudio.length];
        Object[] play = new Object[listplay.length];
        Schedule d;
        for (int i = 0; i < liststudio.length; i++)
        {
            studio[i] = liststudio[i];
        }
        for (int i = 0; i < listplay.length; i++)
        {
            play[i] = listplay[i];
        }

        studioid = new JComboBox<Object>(studio);
        playid = new JComboBox<Object>(play);
    }

    public JPanel JuMu_schedule(String[] biaotou, Object[][] chengyuan)
    {

        JPanel jPanel = new JPanel();

        jPanel.setBackground(Color.orange);

        jPanel.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        Insets insets = new Insets(10, 5, 0, 5);

        gridBagConstraints.insets = insets;

        JLabel jLabel = new JLabel("演出计划列表");

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 0;

        jPanel.add(jLabel, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.BOTH;

        jTable_schedule = ZhiBiao_schedule(biaotou, chengyuan);

        jTable_schedule.setBackground(Color.pink);

        jTable_schedule.addMouseListener(new MouseAdapter()
        {// 表格添加点击事件

           

            public void mouseClicked(MouseEvent arg0)
            {

                int select1 = jTable_schedule.getSelectedRow(); // 获取被选中的行

                Object oa = tableModel_schedule.getValueAt(select1, 0);

                Object ob = tableModel_schedule.getValueAt(select1, 1);

                Object oc = tableModel_schedule.getValueAt(select1, 2);

                Object od = tableModel_schedule.getValueAt(select1, 3);

                Object oe = tableModel_schedule.getValueAt(select1, 4);

                jTextField_schedule_schedid.setText(oa.toString());

                jTextField_schedule_schedtime.setText(od.toString());

                jTextField_shedule_price.setText(oe.toString());

            }

        });

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.weightx = 120;

        gridBagConstraints.weighty = 100;

        JScrollPane jScrollPane = new JScrollPane();

        jScrollPane.setViewportView(jTable_schedule);

        jScrollPane.setBackground(Color.orange);

        jPanel.add(jScrollPane, gridBagConstraints);

        JPanel jPanel5 = new JPanel();

        jPanel5.setBackground(Color.orange);

        jPanel5.setLayout(new GridLayout(4, 0));

        JPanel jPanel2 = new JPanel();

        jPanel2.setLayout(new FlowLayout());

        jPanel2.add(new JLabel("演出ID:"));

        jTextField_schedule_schedid = new JTextField(10);

        jPanel2.add(jTextField_schedule_schedid);

        jPanel2.add(new JLabel("演出厅ID:"));

        jPanel2.add(studioid);

        jPanel2.add(new JLabel("剧目ID:"));

        jPanel2.add(playid);

        JPanel jPanel21 = new JPanel();

        jPanel21.setLayout(new FlowLayout());

        jPanel21.add(new JLabel("演出时间:"));

        jTextField_schedule_schedtime = new JTextField(30);

        jPanel21.add(jTextField_schedule_schedtime);

        jPanel21.add(new JLabel("影片价格:"));

        jTextField_shedule_price = new JTextField(5);

        jPanel21.add(jTextField_shedule_price);

        JPanel jPanel3 = new JPanel();

        jPanel3.setLayout(new FlowLayout());

        final JButton jButton_add = new JButton("添加影片");

        jButton_add.addActionListener(new ActionListener()
        {

            

            public void actionPerformed(ActionEvent arg0)
            {

                String[] ra = {

                        jTextField_schedule_schedid.getText(),

                        jTextField_schedule_schedtime.getText(),

                        jTextField_shedule_price.getText(),

                };

                ScheduleDAO ss = new ScheduleDAO();

                ss.insert(jTextField_schedule_schedid.getText(), studioid.getSelectedItem(), playid.getSelectedItem(),
                        jTextField_schedule_schedtime.getText(), jTextField_shedule_price.getText());

                MainUI gongZuo_Ui = new MainUI(); // 新建页面

                gongZuo_Ui.setSelectedPan(1);

            }

        });

        final JButton jButton_jumu_Xiugai = new JButton("修改影片");

        jButton_jumu_Xiugai.addActionListener(new ActionListener()
        {
           
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                ScheduleDAO ss = new ScheduleDAO();

                ss.update(jTextField_schedule_schedid.getText(), studioid.getSelectedItem(), playid.getSelectedItem(),
                        jTextField_schedule_schedtime.getText(), jTextField_shedule_price.getText());

                MainUI gongZuo_Ui = new MainUI();

                gongZuo_Ui.setSelectedPan(1);
            }

        });

        final JButton jButton_jumu_shanchu = new JButton("删除影片");

        jButton_jumu_shanchu.addActionListener(new ActionListener()
        {

          

            public void actionPerformed(ActionEvent e)
            {

                // TODO Auto-generated method stub
                ScheduleDAO ss = new ScheduleDAO();

                ss.delete(jTextField_schedule_schedid.getText());

                MainUI gongZuo_Ui = new MainUI();

                gongZuo_Ui.setSelectedPan(1);

            }

        });

        jPanel3.add(jButton_add);

        jPanel3.add(jButton_jumu_Xiugai);

        jPanel3.add(jButton_jumu_shanchu);

        jPanel5.add(jPanel2);

        jPanel5.add(jPanel21);

        jPanel5.add(jPanel3);

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.weightx = 10;

        gridBagConstraints.weighty = 10;

        jPanel.add(jPanel5, gridBagConstraints);

        return jPanel;

    }

    public JTable ZhiBiao_schedule(String[] biaotou, Object[][] chengyuan)
    {

        tableModel_schedule = new DefaultTableModel(chengyuan, biaotou);

        JTable jTable1 = new JTable(tableModel_schedule);

        JTableHeader header = new JTableHeader();

        header = jTable1.getTableHeader();

        header.setBackground(Color.blue);

        jTable1.setRowHeight(30);

        jTable1.setGridColor(Color.red);

        // 设置表格为单选模式

        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultTableCellRenderer r = new DefaultTableCellRenderer();

        r.setHorizontalAlignment(JLabel.CENTER);

        jTable1.setDefaultRenderer(Object.class, r);

        // 设置表头居中

        JTableHeader tbh = jTable1.getTableHeader();

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tbh.getDefaultRenderer();

        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        // 设置选定后的颜色

        jTable1.setSelectionBackground(Color.green);

        // 表周围的颜色

        Container c = jTable1.getParent();

        if (c instanceof JViewport)
        {

            JViewport jp = (JViewport) c;

            jp.setBackground(new Color(100, 123, 313));

        }

        return jTable1;
    }

}
