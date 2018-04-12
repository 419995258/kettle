package org.my431.util.sortUrl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.lang3.StringUtils;
import org.my431.platform.utils.ContextUtils;
import org.my431.plugin.redis.services.RedisManager;

public abstract class AbstractMapStoreShortUrlService implements ShortUrlService {

	// this two maps are for simulate a database table to store short-long
	// mapping
//	private Map<String, String> longShortUrlMap = new ConcurrentHashMap<String, String>();
//
//	private Map<String, String> shortLongUrlMap = new ConcurrentHashMap<String, String>();

	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	@Override
	public String lookupLong(String shortUrl,RedisManager redisManager) {
		String longUrl = null;
		Lock readLock = readWriteLock.readLock();
		try {
			readLock.lock();
			longUrl = redisManager.getOValue(shortUrl).toString();
		} catch (Exception e) {
		} finally {
			readLock.unlock();
		}
		return longUrl;
	}

	@Override
	public String convertShort(String longUrl,RedisManager redisManager,String DOMAIN_PREFIX) {
		String shortUrl = lookupShort(longUrl,redisManager);
		if (StringUtils.isEmpty(shortUrl)) {
			shortUrl = shorten(longUrl,redisManager);
			if (StringUtils.isEmpty(shortUrl)) {
				System.err.println("Cannot convert long url to short url");
			} else {
				synchronizedResult(longUrl, shortUrl,redisManager);
			}
		}
		return new StringBuilder(DOMAIN_PREFIX).append(shortUrl).toString();
	}

	protected String lookupShort(String longUrl,RedisManager redisManager) {
		String shortUrl = null;
		Lock readLock = readWriteLock.readLock();
		try {
			readLock.lock();
			shortUrl = redisManager.getOValue(longUrl).toString();
		} catch (Exception e) {
		} finally {
			readLock.unlock();
		}
		return shortUrl;
	}

	protected void synchronizedResult(String longUrl, String shortUrl,RedisManager redisManager) {
		Lock writeLock = readWriteLock.writeLock();
		try {
			writeLock.lock();
			redisManager.setOValue(longUrl, shortUrl);
			redisManager.setOValue(shortUrl, longUrl);
		} catch (Exception e) {
		} finally {
			writeLock.unlock();
		}
	}

	protected abstract String shorten(String longUrl,RedisManager redisManager);

}
