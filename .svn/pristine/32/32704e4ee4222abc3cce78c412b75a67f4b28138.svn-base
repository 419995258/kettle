package org.my431.base.services;

import java.util.List;

import org.my431.base.model.BaseSchool;
import org.my431.plugin.redis.services.RedisManager;

/**
 * 学校信息memcached存取处理类。
 * @author wangzhen
 */
public class CacheBaseSchoolManager {
	private static String SchoolPrekey="global.base.BaseSchool.id.";
	private static String SchoolQTTypePrekey="global.base.BaseSchool.type.qt.List";
	
	/**
	 * 往memcached中存储学校信息。
	 * <pre>
	 * key={@value #SchoolPrekey}
	 * value={@link BaseSchool}
	 * </pre>
	 * @author wangzhen
	 * @param id 学校id
	 * @param school 学校对象
	 */
	public static void setSchool(String id,BaseSchool school){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		redisManager.setOValue(SchoolPrekey+id,school);
	}
	
	/**
	 * 
	 * @param id 学校id
	 * @return 学校对象 {@link BaseSchool}
	 */
	public static BaseSchool getSchool(String id){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		return (BaseSchool)redisManager.getOValue(SchoolPrekey+id);
	}
	
	/**
	 * 获取学校非常用类型 (除去，教学点，小学，初中，9年 ，12年，完中)
	 * author  90  
	 * on 2016-7-8
	 * @param id
	 * @return
	 */
	public static void setSchoolQtType(List list){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		redisManager.setOValue(SchoolQTTypePrekey,list);
	}
	/**
	 * 获取学校非常用类型 (除去，教学点，小学，初中，9年 ，12年，完中)
	 * author  90  
	 * on 2016-7-8
	 * @param id
	 * @return
	 */
	public static List getSchoolQtType(){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		List list=(List) redisManager.getOValue(SchoolQTTypePrekey);
		return list;
	}
	
}