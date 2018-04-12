package org.my431.base.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonUtil {
	
	@SuppressWarnings("rawtypes")
	public static String mapToJSON(Map map){
		JSONObject jsonObject = JSONObject.fromObject(map);  
		return jsonFormatter(jsonObject.toString());
	}
	
	@SuppressWarnings("rawtypes")
	public static String listToJSON(List map){
		JSONArray jsonObject = JSONArray.fromObject(map);  
		return jsonFormatter(jsonObject.toString());
	}
	
	public static String jsonFormatter(String uglyJSONString){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(uglyJSONString);
		String prettyJsonString = gson.toJson(je);
		return prettyJsonString;
	}
	public static void main(String[] args) {
		List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("label", "2014年");list.add(map);
		map = new HashMap<String, Object>();
		map.put("label", "2015年");list.add(map);
		map = new HashMap<String, Object>();
		map.put("label", "2016年");list.add(map);
		map = new HashMap<String, Object>();
		map.put("label", "2017年");list.add(map);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("category", list);
		map1.put("seriesname", "0-99");
		list1.add(map1);
		System.out.println(listToJSON(list1));
	}
}
