package com.dansheng.window;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class DanshengFrame extends JFrame{

	private static final long serialVersionUID = 5253026381934567312L;

	public void initUI(){
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("数据源配置", new IPConfigView());
		tabbedPane.addTab("分析结果展示", initResultViewUI());
		tabbedPane.addTab("元素阈值设置", initThresholdSettingUI());
		this.add(tabbedPane);
		
		
		this.setTitle("Danisun Corp detecting instrument");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screen.width;
		int screenHeight = screen.height;
		this.setBounds(screenWidth/4, screenHeight/4, screenWidth/2, screenHeight/2);
		
	}
	
	private Component initResultViewUI(){
		return new JPanel();
	}
	
	private Component initThresholdSettingUI(){
		return new JPanel();
	}
}
