package com.dansheng.window;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import org.apache.commons.lang3.StringUtils;

public class IPConfigView extends JPanel{

	private static final long serialVersionUID = 5169037651900539818L;
	
	public IPConfigView(){
		this.setLayout(new GridLayout(3, 1));
		this.add(createSubPanel("碳硅仪"));
		this.add(createSubPanel("光谱仪"));
		this.add(createSubPanel("测温仪"));
	}
	
	private Component createSubPanel(final String panelName){
		JPanel panel = new JPanel();
		panel.add(new JLabel(panelName));
		final JTextField textField = new JTextField(20);
		panel.add(textField);
		final JButton button = new JButton("连通性检测");
		button.setForeground(Color.RED);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String dataDir = textField.getText();
				if(StringUtils.isEmpty(dataDir)){
					JOptionPane.showMessageDialog(null, "请设置源数据文件目录", panelName, JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				new SwingWorker<Void, Void>() {

					@Override
					protected Void doInBackground() throws Exception {
						TimeUnit.SECONDS.sleep(3);
						return null;
					}

					@Override
					protected void done() {
						button.setForeground(Color.GREEN);
						JOptionPane.showMessageDialog(null, panelName + "：路径检测成功!");
					}
					
					
				}.execute();
			}
		});
		panel.add(button);
		return panel;
	}
}
