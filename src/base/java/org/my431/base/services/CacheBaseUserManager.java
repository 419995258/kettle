package org.my431.base.services;

import java.util.Map;

import org.my431.base.model.BaseUser;
import org.my431.plugin.redis.services.RedisManager;

public class CacheBaseUserManager {
	private static String userPrekey="global.base.BaseUser.id.";
	private static String managerPrekey="global.base.BaseUser.id.areaId.role.";
	private static String schoolManagerPrekey="global.base.BaseUser.id.schoolId.role.";
	
	/**
	 * 往memcached中存储学校信息。
	 * <pre>
	 * key={@value #userPrekey}
	 * value={@link BaseUser}
	 * </pre>
	 * @author wangzhen
	 * @param id 用户id
	 * @param school 用户对象
	 */
	public static void setBaseUser(BaseUser user){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		redisManager.setOValue(user.getId(),user);
	}
	
	/**
	 * 
	 * @param id 用户id
	 * @return 用户对象 {@link BaseUser}
	 */
	public static BaseUser getBaseUser(String id){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		if(redisManager.objectHasKey(id)){
			return (BaseUser)redisManager.getOValue(id);
		}else{
			return null;
		}
	}
	
	
	public static void delBaseUser(String id){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		if(redisManager.objectHasKey(id)){
			redisManager.removeOValue(id);
		}
	}
	
	/**
	 * 获得地区管理员
	 * @author 90
	 * @date    2017-6-21
	 * @param areaId
	 * @param role role.cityManager role.countryManager role.provinceManager 
	 * @return
	 */
	public static Map<String,Object> getManagerByAreaIdAndRole(String areaId,String role){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		String key=managerPrekey+areaId+"."+role;
		if(redisManager.objectHasKey(key)){
			return (Map<String, Object>) redisManager.getOValue(key);
		}else{
			BaseUserMisManager baseUserMisManager=(BaseUserMisManager)org.my431.platform.utils.ContextUtils.getBean("baseUserMisManager");
			Map<String,Object> userMap=baseUserMisManager.getManagerByAreaIdAndRole(areaId, role);
			if(userMap!=null){
				redisManager.setOValue(key, userMap);
			}
			return userMap;
		}
	}
	
	/**
	 * 移出管理员缓存
	 * @author 90
	 * @date    2017-6-21
	 * @param areaId
	 * @param role role.cityManager role.countryManager role.provinceManager 
	 */
	public static void delManager(String areaId,String role){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		String key=managerPrekey+areaId+"."+role;
		if(redisManager.objectHasKey(key)){
			redisManager.removeOValue(key);
		}
	}
	
	/**
	 * 获得学校管理员
	 * @author 90
	 * @date    2017-6-21
	 * @param schoolId
	 * @param role role.schoolManager
	 * @return
	 */
	public static Map<String,Object> getSchoolManagerByAreaIdAndRole(String schoolId,String role){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		String key=schoolManagerPrekey+schoolId+"."+role;
		if(redisManager.objectHasKey(key)){
			return (Map<String, Object>) redisManager.getOValue(key);
		}else{
			BaseUserMisManager baseUserMisManager=(BaseUserMisManager)org.my431.platform.utils.ContextUtils.getBean("baseUserMisManager");
			Map<String,Object> userMap=baseUserMisManager.getSchoolManagerByAreaIdAndRole(schoolId, role);
			if(userMap!=null){
				redisManager.setOValue(key, userMap);
			}
			return userMap;
		}
	}
	
	/**
	 * 删除学校管理员
	 * @author 90
	 * @date    2017-6-21
	 * @param schoolId
	 * @param role role.schoolManager
	 */
	public static void delSchoolManager(String schoolId,String role){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		String key=schoolManagerPrekey+schoolId+"."+role;
		if(redisManager.objectHasKey(key)){
			redisManager.removeOValue(key);
		}
	}
}
