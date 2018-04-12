package org.my431.util.sortUrl;

import java.util.concurrent.atomic.AtomicLong;

import org.my431.plugin.redis.services.RedisManager;

public class SequenceBaseShortUrlService extends AbstractMapStoreShortUrlService {

	//this sequence count be a real database sequence or others
	private AtomicLong sequence = new AtomicLong(0);

	@Override
	protected String shorten(String longUrl,RedisManager redisManager) {
		long myseq = sequence.incrementAndGet();
		String shortUrl = to62RadixString(myseq);
		return shortUrl;
	}

	private String to62RadixString(long seq) {
		StringBuilder sBuilder = new StringBuilder();
		while (true) {
			int remainder = (int) (seq % 62);
			sBuilder.append(DIGITS[remainder]);
			seq = seq / 62;
			if (seq == 0) {
				break;
			}
		}
		return sBuilder.toString();
	}

}
