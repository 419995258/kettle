package org.my431.platform.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.poi.ss.formula.functions.T;
import org.my431.base.json.JsonUtil;
import org.my431.base.model.BaseSchool;
import org.my431.util.DateFormater;
import org.my431.util.HibernateToolsUtil;

/**
 * 
 * @author hmc 自动封装javaBean的Util
 *
 */
public class SetValueUtil {
	/**
	 * 需要配合xsql封装返回的map使用，单独使用未测试
	 * 
	 * @author hmc 2015年4月10日 下午2:42:17
	 * @param map
	 * @param isReal为true需要返回真正的key
	 *            (map中的key) 为false需要返回编辑过的
	 * @return
	 */
	public static String[] getKey(Map map, boolean isReal) {
		JSONArray jsonArray = JSONArray.fromObject(map);
		String str = jsonArray.toString();
		// System.out.println(str);
		/*
		 * Set s=map.keySet(); for (Iterator it = s.iterator(); it.hasNext();) {
		 * Object key = it.next(); } 将map中的key直接取出存到key[]中
		 */
		str = str.replace("{", "");
		str = str.replace("[", "");
		str = str.replace("]", "");
		str = str.replace("}", "");

		String AllZD[] = str.split(",");
		String key[] = new String[AllZD.length];
		String value[] = new String[AllZD.length];
		if (isReal) {
			for (int i = 0; i < AllZD.length; i++) {
				String schange = (AllZD[i].split(":")[0]).replace("\"", "");
				key[i] = schange;
			}
			return key;
		}
		for (int i = 0; i < AllZD.length; i++) {
			// key[i]=(AllZD[i].split(":")[0]).replace("\"", "");
			String schange = (AllZD[i].split(":")[0]).replace("\"", "")
					.toLowerCase();
			char c[] = schange.toCharArray();
			String cs[] = new String[c.length];
			for (int k = 0; k < cs.length; k++) {
				cs[k] = (c[k] + "").toLowerCase();
			}
			String temp1 = "";
			for (int j = 0; j < cs.length; j++) {
				if (cs[j].equals("_") && j > 0 && j < cs.length - 1) {
					cs[j + 1] = cs[j + 1].toUpperCase();
					cs[j] = "";
				}
				if (cs[j].equals("\"")) {
					cs[j] = "";
				}
				temp1 += cs[j];
			}
			// key[i]=(AllZD[i].split(":")[0]).replace("_", "");
			key[i] = temp1;
			temp1 = "";
			value[i] = AllZD[i].split(":")[1];
			// System.out.println("key:"+key[i]+"    value:"+value[i]);
		}
		return key;
	}

	// ========================================分割线
	// 自动给javabean赋值==========================

	/** 正则表达式 用于匹配属性的第一个字母 [a-zA-Z]} **/
	private static final String REGEX = "[a-zA-Z0-9]";
	static Object tempObj;

	/**
	 * 
	 * @author hmc
	 * @param obj
	 *            传入的对象
	 * @param attribute
	 *            传入的方法名
	 * @param value
	 *            传入的需要进行赋值的值
	 * @return
	 */
	public static Object setAttrributeValue(Object obj, String attribute,Object value) {
		tempObj = null;
		String method_name = convertToMethodName(attribute, obj.getClass(),
				true);
		Method[] methods = obj.getClass().getMethods();
		tempObj = obj;
		for (Method method : methods) {
			// System.out.println(method.getName()+"       methodName="+method_name);
			/**
			 * 因为这里只是调用bean中属性的set方法，属性名称不能重复 所以set方法也不会重复，所以就直接用方法名称去锁定一个方法
			 * （注：在java中，锁定一个方法的条件是方法名及参数）
			 * **/
			if (method.getName().equals(method_name)) {
				Class[] parameterC = method.getParameterTypes();
				if (null == value) {
					break;
				}
				try {
					/**
					 * 如果是基本数据类型时（如int、float、double、byte、char、boolean）
					 * 需要先将Object转换成相应的封装类之后再转换成对应的基本数据类型 否则会报ClassCastException
					 **/
					if (parameterC[0] == int.class) {
						method.invoke(obj, ((Integer) value).intValue());
						break;
					} else if (parameterC[0] == float.class) {
						method.invoke(obj, ((Float) value).floatValue());
						break;
					} else if (parameterC[0] == double.class) {
						method.invoke(obj, ((Double) value).doubleValue());
						break;
					} else if (parameterC[0] == byte.class) {
						method.invoke(obj, ((Byte) value).byteValue());
						break;
					} else if (parameterC[0] == char.class) {
						method.invoke(obj, ((Character) value).charValue());
						break;
					} else if (parameterC[0] == boolean.class) {
						method.invoke(obj, ((Boolean) value).booleanValue());
						break;
					} else if (parameterC[0] == Integer.class) {
						method.invoke(obj, ((Integer.parseInt(value + ""))));// 尝试装载为integer类型
						break;
					} else if (parameterC[0] == Date.class) {//装载成为日期类型
						method.invoke(obj,DateFormater.StringToDateTime(value+""));
						break;
					} else {
						String strTemp = value + "";
						if (strTemp.length() == 1) {// 长度为1 进行 integer char尝试装载
							try {
								method.invoke(obj,
										((Integer.parseInt(value + ""))));// 尝试装载为integer类型
							} catch (Exception e) {
								try {
									method.invoke(obj,
											((Character) value).charValue());// 尝试装载为Character类型
								} catch (Exception e2) {
									try {
										method.invoke(obj,
												parameterC[0].cast(value + ""));// 装载为string类型
									} catch (Exception e3) {
										e3.printStackTrace();
									}
								}

							}
						} else {
							method.invoke(obj, parameterC[0].cast(value));

						}
						break;
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
			}
		}
		return tempObj;
	}

	/**
	 * @author hmc
	 * @param attribute
	 * @param objClass
	 * @param isSet
	 * @return
	 */
	private static String convertToMethodName(String attribute, Class objClass,
			boolean isSet) {
		/** 通过正则表达式来匹配第一个字符 **/
		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(attribute);
		StringBuilder sb = new StringBuilder();
		/** 如果是set方法名称 **/
		if (isSet) {
			sb.append("set");
		} else {
			/** get方法名称 **/
			try {
				Field attributeField = objClass.getDeclaredField(attribute);
				/** 如果类型为boolean **/
				if (attributeField.getType() == boolean.class
						|| attributeField.getType() == Boolean.class) {
					sb.append("is");
				} else {
					sb.append("get");
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
		/** 针对以下划线开头的属性 **/
		if (attribute.charAt(0) != '_' && m.find()) {
			sb.append(m.replaceFirst(m.group().toUpperCase()));
		} else {
			sb.append(attribute);
		}
		return sb.toString();
	}

	/**
	 * @author hmc
	 * @param obj
	 * @param attribute
	 * @return
	 */
	public static Object getAttrributeValue(Object obj, String attribute) {
		String methodName = convertToMethodName(attribute, obj.getClass(),
				false);
		Object value = null;
		try {
			/** 由于get方法没有参数且唯一，所以直接通过方法名称锁定方法 **/
			Method methods = obj.getClass().getDeclaredMethod(methodName);
			if (methods != null) {
				value = methods.invoke(obj);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 通过request提交的信息自动封装属性,需要request中的name值与model实体类的值一致
	 * 
	 * @author hmc 2015年4月23日 下午5:53:11
	 * @param Obj
	 *            模型对象
	 * @param request
	 *            HttpRequest
	 * @return 返回封装好的对象模型
	 */
	public static Object autoWiredModelByRequest(Object Obj,HttpServletRequest request) {
		Map map = new HashMap();
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() != 0) {
					map.put(paramName, paramValue);
				}
			}
		}
		Set<Map.Entry<String, String>> set = map.entrySet();
		for (Map.Entry entry : set) {
			Obj = setAttrributeValue(Obj, (String) (entry.getKey()), entry.getValue());
		}
		return Obj;

	}

	/**
	 * 通过对比两个同一模型对象发现其不同的属性并封装到Map中返回
	 * @author    hmc    2015年4月23日  下午6:54:17
	 * @param oldModel	旧对象
	 * @param newModel	新对象
	 * @param objClass	当前比较的类
	 * @return 
	 */
	public static Map<String, Object> findDifferentByModelAndAutoWireMap(Object oldModel,Object newModel,Class<?> objClass) {
		//旧模型拿到所有值
		Map<String, Object> oldMap=getString(oldModel, oldModel.getClass());
		//新模型拿到所有值
		Map<String, Object> newMap=getString(newModel, newModel.getClass());
		//需要返回的Map
		Map<String, Object> returnMap=new HashMap<String, Object>();
		//拿到该模型的数据库字段值
		Map<String, String> dbColumnMap=HibernateToolsUtil.getFields(objClass);
		
		for(Map.Entry<String, Object> temp:oldMap.entrySet()){     
			 String str1=temp.getValue()+"";
		     String str2=newMap.get(temp.getKey())+"";
		     if(!str1.equals(str2)&&str2!=null&&!str2.equals("null")&&!str2.equals("")){
		    	 returnMap.put(dbColumnMap.get(temp.getKey()), str2);
		    	 
		     }else{
		    	 
		     }
		}   
		return returnMap;
	}

	/**
	 * @MethodName : getString
	 * @Description : 获取类中所有属性及属性值
	 * @param o
	 *            操作对象
	 * @param c
	 *            操作类，用于获取类中的方法
	 * @return
	 */
	private static Map<String,Object> getString(Object o, Class<?> c) {
		Map<String,Object> map=new LinkedHashMap<String,Object>();
		// 获取类中的所有定义字段
		Field[] fields = c.getDeclaredFields();
		// 循环遍历字段，获取字段对应的属性值
		for (Field field : fields) {
			// 如果不为空，设置可见性，然后返回
			field.setAccessible(true);
			try {
				// 设置字段可见，即可用get方法获取属性值。
				map.put(field.getName(), field.get(o));
			} catch (Exception e) {

			}
		}
		return map;
	}
	/**
	 * 自动对比传入的两个同一类型对象的值是否一致，不一致newModel的值将会代替oldModel的值 并将OldModel对象返回。
	 * @author    hmc    2015年4月24日  上午10:48:45
	 * @param oldModel
	 * @param newModel
	 * @return
	 */
	public static Object findDifferentAndAutoWireModel(Object oldModel,Object newModel) {
				//旧模型拿到所有值
				Map<String, Object> oldMap=getString(oldModel, oldModel.getClass());
				//新模型拿到所有值
				Map<String, Object> newMap=getString(newModel, newModel.getClass());
				//需要返回的Map
				Map<String, Object> tempMap=new HashMap<String, Object>();
				//拿到该模型的数据库字段值
				//Map<String, String> dbColumnMap=HibernateToolsUtil.getFields(objClass);
				String tempName[]=new String[oldMap.size()];
				int i=0;
				for(Map.Entry<String, Object> temp:oldMap.entrySet()){     
					
					 String str1=temp.getValue()+"";
				     String str2=newMap.get(temp.getKey())+"";
				     /*
				      * 如果传进来的值有变化就拿新的值，没有变化就拿以前的值
				      * */
				     if(!str1.equals(str2)&&str2!=null&&!str2.equals("null")&&!str2.equals("")){
				    	 tempMap.put(temp.getKey(), str2);
				    	
				     }else{
				    	 tempMap.put(temp.getKey(), str1);
				     }
				     tempName[i]=temp.getKey();//获取当前字段
				     i++;
				}
				for(int j=0;j<tempMap.size();j++){
					if(null==tempMap.get(tempName[j])||"".equals(tempMap.get(tempName[j]))||"null".equals(tempMap.get(tempName[j]))){
						continue;
					}
					oldModel=setAttrributeValue(oldModel, tempName[j], tempMap.get(tempName[j]));
				}
				
				return oldModel;
	}

	/**
	 * 自动中继request消息
	 * @author    hmc    2015年4月24日  上午11:36:51
	 * @param request
	 */
	public static void autoRequestSetAttribute(HttpServletRequest request) {
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() != 0) {
				//	System.out.println(paramName+">>>>>"+paramValue);
					request.setAttribute(paramName, paramValue);
				}
			}
		}
		
	}
	
}
