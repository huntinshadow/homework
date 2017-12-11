package com.dansheng.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dansheng.constant.Constant;
import com.dansheng.constant.Element;

public class ResourceManager {
	
	private static Logger logger = LoggerFactory.getLogger(ResourceManager.class);
	
	private static DataUrls dataUrls = new DataUrls();
	
	private static ElementThreshold threshold = new ElementThreshold();

	private ResourceManager() throws Exception{
		throw new Exception("can't instance ResourceManager!");
	}
	
	public static void loadParameters() throws IOException{
		FileInputStream inputStream = new FileInputStream(Constant.CONFIG_FILE);
		Properties props = new Properties();
		props.load(inputStream);
		inputStream.close();
		
		for(Entry<Object, Object> entry : props.entrySet()){
			String key = (String) entry.getKey();
			String thresholdValue = (String) entry.getValue();
			
			for(Element element : Element.values()){
				if(StringUtils.equalsIgnoreCase(key, element.name())){
					if(StringUtils.isNumeric(thresholdValue)){
						threshold.getElementContentLimits().put(element, Double.parseDouble(thresholdValue));
					}else{
						logger.info("配置文件 元素:{} 阈值非数字:{}", key, thresholdValue);
					}
					break;
				}
			}
			
			if(StringUtils.equalsIgnoreCase(key, Constant.TEMPERATURE_PREFIX)){
				if(StringUtils.isNumeric(thresholdValue)){
					threshold.setTemperatureLimit(Integer.parseInt(thresholdValue));
				}else{
					logger.info("配置文件 测温仪参数:{} 阈值非数值:{}", key, thresholdValue);
				}
			}
		}
		
	}
	
	public static DataUrls getDataUrls(){
		return dataUrls;
	}
	
	public static ElementThreshold getThreshold(){
		return threshold;
	}
	
	public static void writeParam2Disk() throws IOException{
		Properties props = new Properties();
		props.putAll(threshold.getElementContentLimits());
		props.put(Constant.TEMPERATURE_PREFIX, threshold.getTemperatureLimit());
		
		FileOutputStream outputStream = new FileOutputStream(Constant.CONFIG_FILE);
		props.store(outputStream, "comment : last modify "+DateTime.now().toString("yyyyMMdd HHmmss"));
		outputStream.close();
	}
	
	public static void elementThresholdReset(String element, double thresholdVal){
		if(!Arrays.asList(Element.values()).contains(element)){
			logger.error("未知元素 :{}", element);
			return;
		}
			
		threshold.getElementContentLimits().put(Element.valueOf(element), thresholdVal);
	}
	
	public static void temperatureLimitionReset(int newTemperature){
		threshold.setTemperatureLimit(newTemperature);
	}
	
}
