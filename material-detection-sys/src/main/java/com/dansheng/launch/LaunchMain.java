package com.dansheng.launch;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dansheng.util.ResourceManager;
import com.dansheng.window.DanshengFrame;

public class LaunchMain {
	
	private static Logger logger = LoggerFactory.getLogger(LaunchMain.class);
	
	public static void main(String[] args) {
		
		try {
			ResourceManager.loadParameters();
		} catch (IOException e) {
			logger.error("配置文件加载失败");
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		new DanshengFrame().initUI();
	}
}
