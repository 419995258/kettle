package org.my431.base.services;

import java.util.List;
import java.util.Map;

import org.my431.base.model.BaseCache;
import org.my431.plugin.redis.services.RedisManager;

public class CacheBaseCacheManager {
	private static String BaseAreaAccountListByAreaIdkey="global.base.BaseAreaAccountList.key.areaId.";
	/**
	 * 缓存key统一规划，保存在BaseCache表中，并将key存入缓存备用。
	 * @author wangzhen
	 * @param key
	 * @param cache
	 */
	public static void setValue(String key,BaseCache cache){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		if(redisManager.objectHasKey(key)){
			redisManager.removeOValue(key);
			redisManager.setOValue(key, cache);
		}else{
			redisManager.setOValue(key, cache);
		}
	}
	
	/**
	 * 删除缓存中的BaseCache表对象。
	 * @author wangzhen
	 * @param key
	 */
	public static void removeValue(String key){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		redisManager.removeOValue(key);
	}
	
	/**
	 * 获取key的值BaseCache表对象。
	 * @author wangzhen
	 * @param key
	 * @return
	 */
	public static BaseCache getValue(String key){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		if(redisManager.objectHasKey(key)){
			return (BaseCache)redisManager.getOValue(key);
		}else{
			return null;
		}
	}
	
	/**
	 * 获取地区下的账户信息
	 * author  90  
	 * on 2015-4-28
	 * @param areaId
	 * @return
	 */
	public static List<Map> getRedisAcountListByAreaId(String areaId){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		 List<Map> acountList=null;
		 acountList=(List<Map>) redisManager.getOValue(BaseAreaAccountListByAreaIdkey+areaId);
	     return acountList;
	} 
}
