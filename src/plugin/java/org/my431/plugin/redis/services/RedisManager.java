package org.my431.plugin.redis.services;

import java.util.concurrent.TimeUnit;

import org.my431.base.model.BaseUser;
import org.my431.plugin.redis.clients.jedis.AbstractBaseRedisDao;
import org.my431.taglib.My431Function;

/**
 * redis manager
 * @author wangzhen
 */
public class RedisManager extends AbstractBaseRedisDao<String, Object>{
	
	public void setOValueInit(String key,Object value){
		redisTemplate.opsForValue().set(key, value);
	}
	
	public void setOValue(String key,Object value){
		redisTemplate.opsForValue().set(key, value);
	}
	
	public Object getOValue(String key){
		Object o=new Object();
		if(!objectHasKey(key)){
			return null;
		}

		o=redisTemplate.opsForValue().get(key);
		return o;
	}
	
	public void removeOValue(String key){
		redisTemplate.delete(key);
	}

	public boolean objectHasKey(String key){
		return redisTemplate.hasKey(key);
	}
	
	/**redis 保存{@link #BaseUser}*/
	public BaseUser saveBaseUser(BaseUser baseUser){
		if(hasKey(baseUser.getId())){
			//redisUser=(RedisUser)redisTemplate.opsForValue().get(baseUser.getId());//出问题的时候把它注释掉
			removeOValue(baseUser.getId());//把redis内的对象删掉
		}
		redisTemplate.opsForValue().set(baseUser.getId(), baseUser);
		if(baseUser!=null&&baseUser.getSchoolId()!=null){
			//学校管理员
			String key=My431Function.schoolUserPreKey+baseUser.getSchoolId();
			setOValue(key,baseUser);
		}
		
		return baseUser;
	}
	/**redis 删除{@link #BaseUser}*/
	public void removeRedisUser(String userId){
		redisTemplate.delete(userId);
	}
	/**redis 获取{@link #BaseUser}*/
	public BaseUser getRedisUser(String userId){
		BaseUser user=new BaseUser();
		if(hasKey(userId)){//出问题的时候注释掉
			user=(BaseUser) redisTemplate.opsForValue().get(userId);//出问题的时候注释掉
		}//出问题的时候注释掉
		if(user==null){
			return null;
		}

		return user;
	}
	/**redis 判断key是否存在*/
	public boolean hasKey(String userId){
		
		return redisTemplate.hasKey(userId);
	}
	
	public Object getHash(String key){
		return stringRedisTemplate.opsForHash().entries(key);
	}
	
	/**
	 * 设置redis 固定时间自动消除
	 * @param key
	 * @param time
	 */
	public void expire(String key,long time)
	{
		redisTemplate.expire(key,time,TimeUnit.SECONDS);//以秒为单位
	}
}