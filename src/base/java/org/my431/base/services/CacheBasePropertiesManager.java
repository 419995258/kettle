package org.my431.base.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.my431.base.model.BaseProperties;
import org.my431.base.model.BasePropertiesGroup;
import org.my431.plugin.redis.services.RedisManager;

/**
 * 封装memcached中properties相关操作
 * @author wangzhen
 *
 */
public class CacheBasePropertiesManager {
	//所有属性map的key，map结构：Map<String, List<BaseProperties>> 属性组code-属性list
	private static String PropertiesListkey="global.base.BaseProperties.groupKey.Hashtable.list";
	
	//单个属性map前缀 key：PrePropertieskey+PropertyKey
	private static String PrePropertieskey="global.base.BaseProperties.key.";
	
	private static String PropertiesGroupListkey="global.base.BasePropertiesGroup.list";
	private static String ProducerGroupListkey="global.base.BasePropertiesGroup.ProducerGroupList";
	
	public static void setProducerGroupList(List<BasePropertiesGroup> list){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		redisManager.setOValue(ProducerGroupListkey,list);
	}
	public static List<BasePropertiesGroup> getProducerGroupList(){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		Object o=redisManager.getOValue(ProducerGroupListkey);
		if(o!=null){
			return (List<BasePropertiesGroup>)o;
		}else{
			return null;
		}
	}
	

	public static LinkedHashMap<String, List<BaseProperties>> getAllPropertiesMap(){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		Object o=redisManager.getOValue(PropertiesListkey);
		if(o!=null){
			return (LinkedHashMap<String, List<BaseProperties>>)o;
		}else{
			return null;
		}
	}
	
	public static void removeByGroupKey(String groupKey){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		Object o=redisManager.getOValue(PropertiesListkey);
		if(o!=null){
			((LinkedHashMap<String, List<BaseProperties>>)o).remove(groupKey);
		}
	}
	
	public static List<BaseProperties> getPropertiesByGroupKey(String groupKey){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		Object o=redisManager.getOValue(PropertiesListkey);
		if(o!=null){
			return ((LinkedHashMap<String, List<BaseProperties>>)o).get(groupKey);
		}
		return null;
	}
	
	public static void setAllPropertiesMap(Map<String, List<BaseProperties>> map){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		redisManager.setOValue(PropertiesListkey,map);
	}
	
	public static void setProperties(BaseProperties baseProperties){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		redisManager.setOValue(PrePropertieskey+baseProperties.getPropertyKey(), baseProperties);
	}
	
	public static BaseProperties getPropertiesByPropertyKey(String propertyKey){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		Object o=redisManager.getOValue(PrePropertieskey+propertyKey);
		if(o!=null){
			return (BaseProperties)o;
		}else{
			return null;
		}
	}
	
	public static void removePropertiesByPropertyKey(String propertyKey){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		Object o=redisManager.getOValue(PrePropertieskey+propertyKey);
		if(o!=null){
			redisManager.removeOValue(PrePropertieskey+propertyKey);
		}
	}
	
	public static String getValueByPropertyKey(String propertyKey){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		Object o=redisManager.getOValue(PrePropertieskey+propertyKey);
		if(o!=null){
			return ((BaseProperties)o).getPropertyValue();
		}else{
			return null;
		}
	}
	
	//设置nodeType=0的PropertiesGroupList
	public static void setAllPropertiesGroupList(List<BasePropertiesGroup> list){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		redisManager.setOValue(PropertiesGroupListkey,list);
	}
	
	public static List<BasePropertiesGroup> getAllPropertiesGroupList(){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		Object o=redisManager.getOValue(PropertiesGroupListkey);
		if(o!=null){
			return (List<BasePropertiesGroup>)o;
		}else{
			return null;
		}
	}
}
