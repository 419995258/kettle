package org.my431.util;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.my431.base.model.BaseSchool;
/**
 * 将map类型的数据封装到对象中
 * @author 90
 *
 */
public class MapToObject {

	// 把一个字符串的第一个字母大写、效率是最高的
    private static String getMethodName(String fildeName) {  
        byte[] items = fildeName.getBytes();  
        items[0] = (byte) ((char) items[0] - 'a' + 'A');  
        return new String(items);  
    }
    
	public  static <T> T doWrapper(Class c, Map<String, Object> map) throws Exception {  
        T t = (T) c.newInstance();  
        try {  
            Set<Map.Entry<String, Object>> set = map.entrySet();  
            for (Map.Entry<String, Object> entry : map.entrySet()) {  
                String fileName = entry.getKey();  
                Object value = entry.getValue();  
               
                Method get_Method = c.getMethod("get" + getMethodName(fileName));  //获取getMethod方法  
                Method set_Method = c.getMethod("set" + getMethodName(fileName), get_Method.getReturnType());//获得属性get方法  
                Class<?> clazz = get_Method.getReturnType();  
                String type = clazz.getName(); //获取返回值名称  
                //System.out.println("fileName：=================="+fileName+" methodname==="+getMethodName(fileName)+"=类型"+type);
                
                if (type.equals("long"))  
                    set_Method.invoke(t, Long.valueOf(value.toString()));  //对于类型 long  
                else if (type.equals("int") || type.equals("java.lang.Integer"))//对于int 类型  
                    set_Method.invoke(t, Integer.valueOf(value==null? "0":value.toString()));  
                else if ("java.lang.String".equals(type))  
                	 set_Method.invoke(t,value==null?null:value.toString());
                else if (type.equals("double") || type.equals("java.lang.Double"))//对于int 类型  
                    set_Method.invoke(t, Double.valueOf(value==null? "0.0":value.toString()));
                else if (type.equals("java.util.Date"))//对于date 类型  
                    set_Method.invoke(t,DateFormater.StringToDateTime(value+""));
                else set_Method.invoke(t, c.cast(value));//其他类型调用class.cast方法  
            }  
        } catch (Exception e) {  
        	e.printStackTrace();
            //log.equals("property is errorr!" + e.toString());  
        }  
        return t;  
    }  
	
	public static Map<String,Object> getValue(Object thisObj)  
	  {  
	    Map<String,Object> map = new HashMap<String,Object>();  
	    Class c;  
	    try  
	    {  
	      c = Class.forName(thisObj.getClass().getName());  
	      Method[] m = c.getMethods();  
	      for (int i = 0; i < m.length; i++)  
	      {  
	        String method = m[i].getName();  
	        if (method.startsWith("get"))  
	        {  
	          try{  
	          Object value = m[i].invoke(thisObj);  
	          if (value != null)  
	          {  
	            String key=method.substring(3);  
	            key=key.substring(0,1).toLowerCase()+key.substring(1);  
	            map.put(key, value);  
	          }  
	          }catch (Exception e) {  
	            // TODO: handle exception  
	            System.out.println("error:"+method);  
	          }  
	        }  
	      }  
	    }  
	    catch (Exception e)  
	    {  
	      // TODO: handle exception  
	      e.printStackTrace();  
	    }  
	    return map;  
	  }  
	
	
	
}
