/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package org.my431.base.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import org.my431.base.model.BaseUrl;
import org.my431.platform.dao.extend.HibernateEntityExtendDao;
import org.my431.platform.dao.support.Page;
import org.my431.plugin.redis.services.RedisManager;
import org.my431.util.MD5;
import org.springframework.stereotype.Repository;

@Repository
public class BaseUrlManager extends HibernateEntityExtendDao<BaseUrl>{
	public static String listPreKey="global.base.BaseUrl.key.id.";
	public static String urlPreKey="global.base.BaseUrl.key.url.";
	public static String urlObjectKey="global.base.BaseUrl.key.urlId.";
	public Class getEntityClass() {
		return BaseUrl.class;
	}
	private RedisManager redisManager;
	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}
	
	public void init() {
		List<BaseUrl> list = this.getAll("creTime",true);
		Map map=new HashMap();
		for (BaseUrl node : list) {
			List<BaseUrl> urlList=new ArrayList<BaseUrl>();
			if(map.containsKey(listPreKey+node.getModuleId())){
				if(map.get(listPreKey+node.getModuleId())!=null){
					urlList=(List<BaseUrl>)map.get(listPreKey+node.getModuleId());
				}
			}
			urlList.add(node);
			map.put(listPreKey+node.getModuleId(), urlList);
			redisManager.setOValue(urlPreKey+MD5.getMd5(node.getUrl()), node);
			redisManager.setOValue(urlObjectKey+node.getId(), node);
		}
		
		Set<String> key = map.keySet();
        for (Iterator it = key.iterator(); it.hasNext();) {
            String s = (String) it.next();
            redisManager.setOValue(s, map.get(s));
        }
		
	}
	
	public void reload(String mId) {
		List<BaseUrl> list = this.findBy("moduleId",mId,"creTime",true);
		redisManager.setOValue(listPreKey+mId, list);
	}
	
	public void reload() {
		this.init();
	}
	
	public static BaseUrl getObjByUrl(String url){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		if(redisManager.objectHasKey(urlPreKey+MD5.getMd5(url))){
			Object o=redisManager.getOValue(urlPreKey+MD5.getMd5(url));
			if(o!=null){
				return (BaseUrl)o;
			}else{
				return null;
			}
			
		}else{
			return null;
		}
	}
	
	@Override
	public void save(Object o){
		super.save(o);
		redisManager.setOValue(urlPreKey+MD5.getMd5(((BaseUrl)o).getUrl()), ((BaseUrl)o));
		redisManager.setOValue(urlObjectKey+((BaseUrl)o).getId(), ((BaseUrl)o));
		reload(((BaseUrl)o).getModuleId());
	}
	
	@Override
	public void update(Object o){
		super.update(o);
		redisManager.setOValue(urlPreKey+MD5.getMd5(((BaseUrl)o).getUrl()), ((BaseUrl)o));
		redisManager.setOValue(urlObjectKey+((BaseUrl)o).getId(), ((BaseUrl)o));
		reload(((BaseUrl)o).getModuleId());
	}
	
	@Override
	public void remove(Object o){
		super.remove(o);
		redisManager.removeOValue(urlPreKey+MD5.getMd5(((BaseUrl)o).getUrl()));
		redisManager.removeOValue(urlObjectKey+((BaseUrl)o).getId());
		reload(((BaseUrl)o).getModuleId());
	}
	
	@Override
	public void removeById(Serializable id){
		BaseUrl o=get(id);
		remove(o);
		redisManager.removeOValue(urlPreKey+MD5.getMd5(((BaseUrl)o).getUrl()));
		redisManager.removeOValue(urlObjectKey+((BaseUrl)o).getId());
		reload(((BaseUrl)o).getModuleId());
	}
	
	public Page findPage(BaseUrl query, int pageSize, int pageNo) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from BaseUrl t where 1=1 "
			  	+ "/~ and t.urlName = {urlName} ~/"
			  	+ "/~ and t.url = {url} ~/"
			  	+ "/~ and t.urlDesc = {urlDesc} ~/"
			  	+ "/~ and t.clickCount = {clickCount} ~/"
			  	+ "/~ and t.creUser = {creUser} ~/"
				+ "/~ and t.creTime >= {creTimeBegin} ~/"
				+ "/~ and t.creTime <= {creTimeEnd} ~/"
			  	+ "/~ and t.modUser = {modUser} ~/"
				+ "/~ and t.modTime >= {modTimeBegin} ~/"
				+ "/~ and t.modTime <= {modTimeEnd} ~/"
			  	+ "/~ and t.moduleId = {moduleId} ~/"
			  	+ "/~ and t.isAddLog = {isAddLog} ~/"
				+ "/~ order by [sortColumns] ~/";

		Map filters = new HashMap(); 
		XsqlBuilder xsqlBuilder=new XsqlBuilder();
		if(isNotEmpty(query.getModuleId())) {
			filters.put("moduleId", query.getModuleId());
		}
		if(isNotEmpty(query.getIsAddLog())) {
			filters.put("isAddLog", query.getIsAddLog());
		}
        filters.put("sortColumns", "t.creTime,t.modTime ");
        XsqlFilterResult result = xsqlBuilder.generateHql(sql,filters); 
		return pageQuery(result.getXsql(), pageNo, pageSize,result.getAcceptedFilters());
	}
	

}
