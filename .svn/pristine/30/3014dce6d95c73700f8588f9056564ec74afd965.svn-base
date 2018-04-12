package org.my431.plugin.redis.clients.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import redis.clients.jedis.JedisCluster;

public abstract class AbstractBaseRedisDao<K, V> { 
    @Autowired  
    protected RedisTemplate<K, V> redisTemplate;  
    
    @Autowired  
    protected StringRedisTemplate stringRedisTemplate;  
    
	protected JedisCluster jedisCluster;
  
    /**
     * 集群方式
     * @param jedisCluster
     */
    public void setJedisCluster(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}

	/** 
     * 设置redisTemplate 
     * @param redisTemplate the redisTemplate to set 
     */  
    public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {  
        this.redisTemplate = redisTemplate;  
    }  
      
    /** 
     * 获取 RedisSerializer 
     * <br>------------------------------<br> 
     */  
    protected RedisSerializer<String> getRedisSerializer() {  
        return redisTemplate.getStringSerializer();  
    }  
    
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;
	}

}
