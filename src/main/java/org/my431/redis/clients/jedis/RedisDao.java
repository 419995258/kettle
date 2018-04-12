package org.my431.redis.clients.jedis;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisDao {
	private StringRedisTemplate stringRedisTemplate;
	
	public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;
	}	
	
	public RedisConnection getConnection(){
		return stringRedisTemplate.getConnectionFactory().getConnection();
	}
	
	public void setValue(String key,String value){
		stringRedisTemplate.opsForValue().set(key, value);
	}

	public String getValue(String key){
		return stringRedisTemplate.opsForValue().get(key);
	}
	
	public void removeValue(String key){
		stringRedisTemplate.delete(key);
	}

	public void bgSave(){
		RedisConnection conn=getConnection();
		conn.bgSave();
		conn.close();
	}
}
