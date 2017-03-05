package com.dabai.util;

import java.util.HashMap;
import java.util.Map;

public class ValueUtils {
	private Map<String, Integer> modelMap = new HashMap<String,Integer>();
	private static ValueUtils valueUtils = new ValueUtils();
	
	private ValueUtils(){
		modelMap.put("easy",-1);	
		modelMap.put("diff",1);
	}
	
	public static ValueUtils getInstance(){
		return valueUtils;
	}

	public Map<String, Integer> getModelMap() {
		return modelMap;
	}
	
	
}
