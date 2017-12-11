package com.dansheng.util;

import java.util.HashMap;
import java.util.Map;

import com.dansheng.constant.Element;

public class ElementThreshold {
	
	private Map<Element, Double> elementContentLimits = new HashMap<>(); 
	
	private int temperatureLimit;

	public Map<Element, Double> getElementContentLimits() {
		return elementContentLimits;
	}

	public void setElementContentLimits(Map<Element, Double> elementContentLimits) {
		this.elementContentLimits = elementContentLimits;
	}

	public int getTemperatureLimit() {
		return temperatureLimit;
	}

	public void setTemperatureLimit(int temperatureLimit) {
		this.temperatureLimit = temperatureLimit;
	}

}
