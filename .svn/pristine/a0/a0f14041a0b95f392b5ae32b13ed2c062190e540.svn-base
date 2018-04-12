package org.my431.platform.listener;

/**
 * @author jeffsang
 *
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.my431.plugin.redis.services.RedisManager;
import org.my431.util.DateFormater;


/**
 * Listener that keeps track of the online users of sessions that the Web
 * application is currently using and has ever used in its life cycle.
 */

public class OnlineListener implements HttpSessionListener,
		HttpSessionAttributeListener {
	private static int currentSessionCount = 0;

	private static int maxSessionCount = 0;
	
	private static String preKeyRedisList="sso.userList";

	// private static List<OnlineModel> logined = new ArrayList<OnlineModel>();
	// //members
	// private static Vector<OnlineModel> logined = new Vector<OnlineModel>();
	private static List<OnlineModel> logined = Collections
			.synchronizedList(new ArrayList<OnlineModel>());

	private final static Object lock = new Object();

	public OnlineListener() {
		System.out.println("OnlineListerner Constructor...");
	}

	/** The number of sessions currently in memory. */
	public static int getCurrentSessionCount() {
		return currentSessionCount;
	}

	/**
	 * The largest number of sessions ever in memory at any one time.
	 */
	public static int getMaxSessionCount() {
		return maxSessionCount;
	}

	public static List<OnlineModel> getLogined() {
		List<OnlineModel> list=new ArrayList<OnlineModel>();
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		if(redisManager.objectHasKey(preKeyRedisList)){
			list=(List<OnlineModel>)redisManager.getOValue(preKeyRedisList);
		}
		return list;
	}
	
	public static List<OnlineModel> getLoginedPage(Integer pageNo, Integer pageSize){
		List<OnlineModel> page = new ArrayList<OnlineModel>();
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		List<OnlineModel> all = (List<OnlineModel>)redisManager.getOValue(preKeyRedisList);
		pageNo = pageNo==null||pageNo > 0 ? pageNo : 1;
		for(int i=0;i<pageSize;i++){
			int index = (pageNo-1)*pageSize + i;
			if(index < all.size()){
				page.add(all.get(index));
			}else{
				break;
			}
		}
		return page;
	}

	public static int getLoginedCount() {
		List<OnlineModel> list=new ArrayList<OnlineModel>();
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		if(redisManager.objectHasKey(preKeyRedisList)){
			list=(List<OnlineModel>)redisManager.getOValue(preKeyRedisList);
		}
		return list.size();
	}

	public void sessionCreated(HttpSessionEvent event) {
		currentSessionCount++;
		if (currentSessionCount > maxSessionCount) {
			maxSessionCount = currentSessionCount;
		}
		// System.out.println("----sessionCreated----");
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		currentSessionCount--;
		// System.out.println("----sessionDestroyed----");
	}

	public void attributeAdded(HttpSessionBindingEvent se) {
		if ("logined".equals(se.getName())) {
			// synchronized(list)
			add((OnlineModel) (se.getValue()));
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		if ("logined".equals(se.getName())) {
			// synchronized(list)
			remove((OnlineModel) (se.getValue()));
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {
	}

	// synchronized(list)
	public static void remove(OnlineModel om) {
		if (om == null) {
			return;
		}
//		synchronized (lock) {
			RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
//			logined.remove(om);
			List<OnlineModel> list=new ArrayList<OnlineModel>();
			if(redisManager.objectHasKey(preKeyRedisList)){
				list=(List<OnlineModel>)redisManager.getOValue(preKeyRedisList);
			}
			list.remove(om);
			redisManager.setOValue(preKeyRedisList, list);
//		}
		System.out.println(om + " logout!");
	}

	// synchronized(list)
	public static void add(OnlineModel om) {
		if (om == null) {
			return;
		}
//		synchronized (lock) {
			RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
//			logined.add(om);
			List<OnlineModel> list=new ArrayList<OnlineModel>();
			if(redisManager.objectHasKey(preKeyRedisList)){
				list=(List<OnlineModel>)redisManager.getOValue(preKeyRedisList);
			}
			list.add(om);
			redisManager.setOValue(preKeyRedisList, list);
//		}
		System.out.println(om + " login!");
	}

	// synchronized(list)
	public static void updateLastAcessTime(String sessionId) {
		if (sessionId == null) {
			return;
		}
//		synchronized (lock) {
			List<OnlineModel> list=new ArrayList<OnlineModel>();
			RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
			if(redisManager.objectHasKey(preKeyRedisList)){
				list=(List<OnlineModel>)redisManager.getOValue(preKeyRedisList);
			}
			for (OnlineModel om : list) {
				if (sessionId.equals(om.getId())) {
					om.setLastAccessTime(DateFormater
							.DateTimeToString(new Date()));
					break;
				}
			}
			redisManager.setOValue(preKeyRedisList, list);
//		}
	}

	// synchronized(list)
	public static void killExpired(int timeout,HttpSession session) {
		Date activeTime;
//		synchronized (lock) {
			List<OnlineModel> rlist=new ArrayList<OnlineModel>();
			RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
			if(redisManager.objectHasKey(preKeyRedisList)){
				rlist=(List<OnlineModel>)redisManager.getOValue(preKeyRedisList);
			}
			for (OnlineModel om : rlist) {
				activeTime = DateFormater.StringToDateTime(om
						.getLastAccessTime());
				if (DateFormater.TimeDiff(new Date(), activeTime) > timeout) {
					rlist.remove(om);
					redisManager.setOValue(preKeyRedisList, rlist);
					if(session.getId().equals(om.getId())){
						session.invalidate();
					}
					System.out.println(om + " Expired!");
				}
			}
//		}
	}

	public static void main(String[] args) {
	}
}
