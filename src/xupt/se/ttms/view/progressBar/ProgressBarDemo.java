package xupt.se.ttms.view.progressBar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

public class ProgressBarDemo extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JProgressBar progressBar;
	
	public ProgressBarDemo(){
		this.setTitle("进度条");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300,150);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 5, 5, 5));//设置内容面板边框
		setContentPane(contentPane);//应用内容面板
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));//设置为流式布局
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);//设置进度条上的字符串显示，false则不能显示
		progressBar.setPreferredSize(new Dimension(200, 20));
		progressBar.setBackground(Color.green);
		progressBar.setVisible(true);
		JLabel jLabel = new JLabel("正在加载，请耐心等待哦 ^_^......");
		
		
		//创建线程显示进度
		new Thread(){
			public void run(){
				for(int i = 0; i < 100; i++){
					try{
						Thread.sleep(150);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					progressBar.setValue(i);//设置进度条数值
				}
				progressBar.setString("信息加载完成");//设置提示信息
				dispose();
			}
		}.start();
		contentPane.add(progressBar);
		contentPane.add(jLabel);
		contentPane.setVisible(true);
		
	}
	
	public static void main(String[] args){
		ProgressBarDemo progressBarDemo = new ProgressBarDemo();
		progressBarDemo.setVisible(true);
	}

}
