/**
 * 
 */
package xupt.se.ttms.view.tmpl;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Insets;

import xupt.se.ttms.view.system.*;
import xupt.se.ttms.service.LoginedUser;

/** 主面板
 * @author Administrator
 *
 */

public class MainUITmpl extends JFrame  {

	private static final long serialVersionUID = 1L;
	private int frmWidth=1024;
	private int frmHeight=700;
	protected final ImagePanel headPan = new ImagePanel("resource/image/header.jpg");
	protected final JPanel contPan = new JPanel();
	protected JLabel usrLabel = new JLabel("用户名");
	protected JLabel usrName = new JLabel("密码");
	protected JTextField userName = new JTextField();
	protected JTextField password = new JTextField();
	protected JButton btnModPwd = new JButton("修改密码");
	protected JButton btnExit = new JButton("返回");	

	public MainUITmpl(){
		this.setSize(frmWidth, frmHeight);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("汉唐剧院票务管理系统");
		this.setLayout(null);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				onWindowClosing();
			}
		});		
		
		headPan.setBounds(0, 0, frmWidth, 80);
		headPan.setLayout(null);
		this.add(headPan);
		
		contPan.setBounds(0, 80, frmWidth, this.frmHeight-100);
		contPan.setLayout(null);
		this.add(contPan);	
		
		initHeader();
		initContent();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainUITmpl().setVisible(true);;
					
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
					e.printStackTrace();
				}
			}
		});
		
	}
	public int getWidth(){
		return this.frmWidth;
		
	}
	public int getHeight(){
		return this.frmHeight;
		
	}
	
	private void initHeader() {
		try {

			usrLabel.setBounds(frmWidth-160, 5, 80, 30);
			usrLabel.setText("当前用户：");
			headPan.add(usrLabel);
			
			usrName.setBounds(frmWidth-80, 5, 80, 30);
			usrName.setText("匿名");
			usrName.setFont(new java.awt.Font("宋体", 1, 15));
			usrName.setForeground(Color.blue);				
			headPan.add(usrName);
			
			btnModPwd.setBounds(frmWidth-160, 40, 80, 30);
			btnModPwd.setMargin(new Insets(0,0,0,0));
			btnModPwd.setContentAreaFilled(false);
			headPan.add(btnModPwd);
			btnModPwd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent Event) {
					btnModUserClicked();
				}
			});
			
			btnExit.setBounds(frmWidth-80, 40, 80, 30);
			btnExit.setContentAreaFilled(false);
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent Event) {
					btnExitClicked(Event);
				}
			});
			
			headPan.add(btnExit);	
			
			//Show the information of current user
			showCurrentUser();
			
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
			e.printStackTrace();
		}
	}
	
	
	private void btnModUserClicked(){
		SysUserModUI dlgUserMod=new SysUserModUI();
		dlgUserMod.setModal(true);
		dlgUserMod.setVisible(true);
	}	
	
	private void showCurrentUser(){
		LoginedUser curUser=LoginedUser.getInstance();
		String name=curUser.getEmpName();
		if(null==name ||  name.isEmpty())
			usrName.setText("匿名用户");
		else
			usrName.setText(name);		
	}
	
	
	//To be override by the detailed business block interface 
	protected void onWindowClosing(){
		System.exit(0);
	}
	
	
	//To be override by the detailed business block interface 
	protected void initContent(){
	}
	
	//To be override by the detailed business block interface 
	protected void btnExitClicked(ActionEvent Event){
		System.exit(0);
	}	

}
