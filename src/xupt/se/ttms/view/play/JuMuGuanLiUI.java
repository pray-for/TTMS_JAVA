package xupt.se.ttms.view.play;


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

import xupt.se.ttms.dao.JuMuGuanLiDAO;
import xupt.se.ttms.model.DataDict;
import xupt.se.ttms.service.DataDictSrv;
import xupt.se.ttms.view.administrator.MainUI;


public class JuMuGuanLiUI extends JFrame
{

    public JTabbedPane JuMuGuanLi()
    {
        JuMuGuanLiDAO st = new JuMuGuanLiDAO();

        String[] strings_xinpin = { "剧目ID", "剧目类型", "语言类型", "影片名称", "影片介绍", "影片时长", "影片价格" };

        Object[][] objects_xinpin = new Object[50][7];

        st.select(objects_xinpin);

        JTabbedPane jTabbedPane_jumuguanli = new JTabbedPane();

        JuMu juMu = new JuMu();

        jTabbedPane_jumuguanli.add("新品上市", juMu.JuMu_XinPin(strings_xinpin, objects_xinpin));

        jTabbedPane_jumuguanli.setBackground(Color.red);

        jTabbedPane_jumuguanli.setTabPlacement(JTabbedPane.TOP);

        return jTabbedPane_jumuguanli;

    }
}

class JuMu
{
    private DefaultTableModel tableModel_xinpin;
    private JTable jTable_Xinpin;
    private JTextField jTextField_xinpin_playid;
    private JComboBox<String> txtType;
    private JComboBox<String> txtLang;
    private JTextField jTextField_xinpin_name;
    private JTextField jTextField_xinpin_introduce;
    private JTextField jTextField_xinpin_length;
    private JTextField jTextField_xinpin_price;
    DataDictSrv dataDictSrv = new DataDictSrv();

    public JuMu()
    {
        DataDictSrv dataDictSrv = new DataDictSrv();
        List<DataDict> listPlayType = dataDictSrv.findByID(3);
        List<DataDict> listPlayLang = dataDictSrv.findByID(2);
        String[] type = new String[listPlayType.size()];
        String[] lang = new String[listPlayLang.size()];
        DataDict d;
        for (int i = 0; i < listPlayType.size(); i++)
        {
            d = listPlayType.get(i);
            type[i] = d.getName();
        }
        for (int i = 0; i < listPlayLang.size(); i++)
        {
            d = listPlayLang.get(i);
            lang[i] = d.getName();
        }

        txtType = new JComboBox<String>(type);

        txtLang = new JComboBox<String>(lang);
    }

    public JPanel JuMu_XinPin(String[] biaotou, Object[][] chengyuan)
    {

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

        jTable_Xinpin = ZhiBiao_xinpin(biaotou, chengyuan);

        jTable_Xinpin.setBackground(Color.pink);

        jTable_Xinpin.addMouseListener(new MouseAdapter()
        {// 表格添加点击事件

            @Override

            public void mouseClicked(MouseEvent arg0)
            {

                int select1 = jTable_Xinpin.getSelectedRow(); // 获取被选中的行

                Object oa = tableModel_xinpin.getValueAt(select1, 0);

                Object ob = tableModel_xinpin.getValueAt(select1, 1);

                Object oc = tableModel_xinpin.getValueAt(select1, 2);

                Object od = tableModel_xinpin.getValueAt(select1, 3);

                Object oe = tableModel_xinpin.getValueAt(select1, 4);

                Object of = tableModel_xinpin.getValueAt(select1, 5);

                Object og = tableModel_xinpin.getValueAt(select1, 6);

                jTextField_xinpin_playid.setText(oa.toString());

                jTextField_xinpin_name.setText(od.toString());

                jTextField_xinpin_introduce.setText(oe.toString());

                jTextField_xinpin_length.setText(of.toString());

                jTextField_xinpin_price.setText(og.toString());

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

        jPanel5.setLayout(new GridLayout(4, 0));

        JPanel jPanel2 = new JPanel();

        jPanel2.setLayout(new FlowLayout());

        jPanel2.add(new JLabel("剧目ID:"));

        jTextField_xinpin_playid = new JTextField(10);

        jPanel2.add(jTextField_xinpin_playid);

        jPanel2.add(new JLabel("剧目类型:"));

        jPanel2.add(txtType);

        jPanel2.add(new JLabel("语言类型:"));

        jPanel2.add(txtLang);

        jPanel2.add(new JLabel("影片名称:"));

        jTextField_xinpin_name = new JTextField(10);

        jPanel2.add(jTextField_xinpin_name);

        JPanel jPanel21 = new JPanel();

        jPanel21.setLayout(new FlowLayout());

        jPanel21.add(new JLabel("影片介绍:"));

        jTextField_xinpin_introduce = new JTextField(50);

        jPanel21.add(jTextField_xinpin_introduce);

        jPanel21.add(new JLabel("影片时长:"));

        jTextField_xinpin_length = new JTextField(2);

        jPanel21.add(jTextField_xinpin_length);

        jPanel21.add(new JLabel("影片价格:"));

        jTextField_xinpin_price = new JTextField(3);

        jPanel21.add(jTextField_xinpin_price);

        JPanel jPanel3 = new JPanel();

        jPanel3.setLayout(new FlowLayout());

        final JButton jButton_jumu_add = new JButton("添加影片");

        jButton_jumu_add.addActionListener(new ActionListener()
        {

            @Override

            public void actionPerformed(ActionEvent arg0)
            {

                String[] ra = {

                        jTextField_xinpin_playid.getText(),

                        jTextField_xinpin_name.getText(),

                        jTextField_xinpin_price.getText(),

                        jTextField_xinpin_length.getText(),

                        jTextField_xinpin_price.getText(), };

                JuMuGuanLiDAO ss = new JuMuGuanLiDAO();

                ss.insert(dataDictSrv.findSelfByName(txtType.getSelectedItem().toString()).getId(),
                        dataDictSrv.findSelfByName(txtLang.getSelectedItem().toString()).getId(),
                        jTextField_xinpin_name.getText(), jTextField_xinpin_introduce.getText(),
                        jTextField_xinpin_length.getText(), jTextField_xinpin_price.getText());

                MainUI gongZuo_Ui = new MainUI(); // 新建页面

                // JuMuGuanLiUI juMuGuanLi_Ui = new JuMuGuanLiUI();

            }

        });

        final JButton jButton_jumu_Xiugai = new JButton("修改影片");

        jButton_jumu_Xiugai.addActionListener(new ActionListener()
        {

            @Override

            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                JuMuGuanLiDAO ss = new JuMuGuanLiDAO();

                ss.update(jTextField_xinpin_playid.getText(),
                        dataDictSrv.findSelfByName(txtType.getSelectedItem().toString()).getId(),
                        dataDictSrv.findSelfByName(txtLang.getSelectedItem().toString()).getId(),
                        jTextField_xinpin_name.getText(), jTextField_xinpin_introduce.getText(),
                        jTextField_xinpin_length.getText(), jTextField_xinpin_price.getText());

                MainUI gongZuo_Ui = new MainUI();
                // JTabbedPane jTabbedPane = new JTabbedPane();

            }

        });

        final JButton jButton_jumu_shanchu = new JButton("删除影片");

        jButton_jumu_shanchu.addActionListener(new ActionListener()
        {

            @Override

            public void actionPerformed(ActionEvent e)
            {

                // TODO Auto-generated method stub
                JuMuGuanLiDAO ss = new JuMuGuanLiDAO();

                ss.delete(jTextField_xinpin_playid.getText());

                MainUI gongZuo_Ui = new MainUI();

            }

        });

        jPanel3.add(jButton_jumu_add);

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

    public JTable ZhiBiao_xinpin(String[] biaotou, Object[][] chengyuan)
    {

        tableModel_xinpin = new DefaultTableModel(chengyuan, biaotou);

        JTable jTable1 = new JTable(tableModel_xinpin);

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