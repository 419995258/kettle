package org.my431.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.JoinColumn;




public class HibernateToolsUtil{  
    /** 
     * <pre> 
     * 获取实体所有字段 
     * @param clazz 实体类型 
     * @return Map<String, Method> 其中key实体字段名称，value为数据库字段名称 
     * </pre> 
     */  
    public static Map<String, String> getFields(Class<?> clazz) {  
        Map<String, String> map = new HashMap<String, String>();  
        Field[] methods = clazz.getDeclaredFields();  
        for (Field method : methods) {
            Column c = method.getAnnotation(Column.class);  
            if (null != c) {
                map.put(method.getName(), c.name());  
            } else {
                JoinColumn jc = method.getAnnotation(JoinColumn.class);  
                if (null != jc) {
                    map.put(method.getName(), jc.name());  
                }  
            }  
        }  
        return map;  
    }  
    /** 
     * <pre> 
     * 获取实体所有字段 
     * @param clazz 实体类型 
     * @return Map<String, Method> 其中key实体字段名称，value为字段类型
     * </pre> 
     */  
    public static Map<String, Object> getFieldsType(Class<?> clazz) {  
    	Map<String, Object> map = new HashMap<String, Object>();  
    	Field[] methods = clazz.getDeclaredFields();  
    	for (Field method : methods) {
    		map.put(method.getName(), method.getType().getName());
    	}  
    	return map;  
    }  

    /** 
     * <pre> 
     * 获取实体所有字段 
     * @param clazz 实体类型 
     * @return Map<String, Method> 其中key数据库字段名称 ，value为实体字段名称
     * </pre> 
     */  
    public static Map<String, String> getHFields(Class<?> clazz) {  
        Map<String, String> map = new HashMap<String, String>();  
        Field[] methods = clazz.getDeclaredFields();  
        for (Field method : methods) {
            Column c = method.getAnnotation(Column.class);  
            if (null != c) {
                map.put(c.name(), method.getName());  
            } else {
                JoinColumn jc = method.getAnnotation(JoinColumn.class);  
                if (null != jc) {
                    map.put(jc.name(), method.getName());  
                }  
            }  
        } 
        return map;  
    } 
    /** 
     * <pre> 
     * 获取实体所有字段 
     * @param clazz 实体类型 
     * @return Map<String, Method> 其中key数据库字段名称 ，value为实体字段类型
     * </pre> 
     */  
    public static Map<String, String> getTableFieldsType(Class<?> clazz) {  
        Map<String, String> map = new HashMap<String, String>();  
        Field[] methods = clazz.getDeclaredFields();  
        for (Field method : methods) {
            Column c = method.getAnnotation(Column.class);  
            if (null != c) {
                map.put(c.name(), method.getType().getName());  
            } else {
                JoinColumn jc = method.getAnnotation(JoinColumn.class);  
                if (null != jc) {
                    map.put(jc.name(), method.getType().getName());  
                }  
            }  
        } 
        return map;  
    } 
    /*** 
     * get all field ,including fields in father/super class 
     *  
     * @param clazz 
     * @return 
     */  
    public static List<Field> getAllFieldList(Class<?> clazz) {  
        List<Field> fieldsList = new ArrayList<Field>();// return object  
        if (clazz == null) {  
            return null;  
        }  
  
        Class<?> superClass = clazz.getSuperclass();// father class  
        if (!superClass.getName().equals(Object.class.getName())){//java.lang.Object 
            // System.out.println("has father");  
            fieldsList.addAll(getAllFieldList(superClass));// Recursive  
        }  
        Field[] fields = clazz.getDeclaredFields();  
        for (int i = 0; i < fields.length; i++) {  
            Field field = fields[i];  
            // 排除因实现Serializable 接口而产生的属性serialVersionUID  
            if (!field.getName().equals("serialVersionUID")) {  
                fieldsList.add(field);  
            }  
        }  
        return fieldsList;  
    }  
    /*** 
     * 获取指定对象的属性值 
     *  
     * @param obj 
     * @param name 
     *            :Field 
     * @return 
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     */  
    public static Object getObjectValue(Object obj, Field name)  
            throws SecurityException, NoSuchFieldException,  
            IllegalArgumentException, IllegalAccessException {  
  
        // Field f = getSpecifiedField(obj.getClass(), name.getName());  
        if (name == null) {  
            System.out.println("[ReflectHWUtils.getObjectValue]"  
                    + obj.getClass().getName() + " does not has field " + name);  
            return null;  
        }  
        name.setAccessible(true);  
        return name.get(obj);  
    }
}
