package com.kit.util.map;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * object->map
 * @author LENOVO
 *
 */
public class MapUtil {
	public static Map<String,Object> object2Map(Object object){
	       Map<String,Object> result=new HashMap<>();
	       //获得类的的属性名 数组
	       Field[] fields=object.getClass().getDeclaredFields();
	       try {
	 
	 
			   for (Field field : fields) {
				   field.setAccessible(true);
				   String name = new String(field.getName());
				   result.put(name, field.get(object));
	 
			   }
		   }catch (Exception e){
	       	e.printStackTrace();
		   }
		   return result;
		}
	
	public static JSONObject mapToJson(Map<String,Object> map){
		JSONObject json2=new JSONObject();
		for (Entry<String,Object> entry : map.entrySet()) {
			json2.put(entry.getKey(),entry.getValue());
		}
		   return json2;
		}
	
	
	public static JSONObject objectToJson(Object object){
		 Map<String,Object> map=new HashMap<>();
	       //获得类的的属性名 数组
	       Field[] fields=object.getClass().getDeclaredFields();
	       try {
			   for (Field field : fields) {
				   field.setAccessible(true);
				   String name = new String(field.getName());
				   map.put(name, field.get(object));
			   }
		   }catch (Exception e){
	       	e.printStackTrace();
		   }
		JSONObject json2=new JSONObject();
		for (Entry<String,Object> entry : map.entrySet()) {
			json2.put(entry.getKey(),entry.getValue());
		}
		   return json2;
		}
}
