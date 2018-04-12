package org.my431.base.services;

import org.my431.base.model.BaseQuery;
import org.my431.plugin.redis.services.RedisManager;

public class CacheBaseQueryManager {
	
	public static String idKey = "global::base::BaseQuery::idKey::";
	/**
	 * 缓存key统一规划，保存在BaseQuery表中，并将key存入缓存备用。
	 * @author wangzhen
	 * @param key
	 * @param cache
	 */
	public static void setValue(String key,BaseQuery query,RedisManager redisManager){
		if(redisManager.objectHasKey(key)){
			redisManager.removeOValue(key);
			redisManager.setOValue(key, query);
		}else{
			redisManager.setOValue(key, query);
			redisManager.objectHasKey(key);
		}
	}
	
	/**
	 * 删除缓存中的BaseQuery表对象。
	 * @author wangzhen
	 * @param key
	 */
	public static void removeValue(String key,RedisManager redisManager){
		redisManager.removeOValue(key);
	}
	
	/**
	 * 获取key的值BaseQuery表对象。
	 * @author wangzhen
	 * @param key
	 * @return
	 */
	public static BaseQuery getValue(String key){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		if(redisManager.objectHasKey(key)){
			return (BaseQuery)redisManager.getOValue(key);
		}else{
			return null;
		}
	}
}
