/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2012
 */

package org.my431.base.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import org.my431.base.model.BaseQuery;
import org.my431.platform.dao.extend.HibernateNamedQueryDao;
import org.my431.platform.dao.support.Page;
import org.my431.plugin.redis.services.RedisManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

@Repository
public class BaseQueryManager extends HibernateNamedQueryDao<BaseQuery> implements InitializingBean{

	private RedisManager redisManager;
	
	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}
	public Class getEntityClass() {
		return BaseQuery.class;
	}

	public void init(){
		CacheBaseQueryManager.removeValue("",redisManager);
		List<BaseQuery> list=this.getAll();
		for(BaseQuery baseQuery:list){
			CacheBaseQueryManager.setValue(baseQuery.getQueryName(), baseQuery,redisManager);
		}
	}
	public List<BaseQuery> getBaseQueryByQueryName(String queryName) {
		return  find("from BaseQuery t where t.queryName='"+queryName+"' ");
	}
	
	@SuppressWarnings("unchecked")
	public Page findPage(BaseQuery query, int pageSize, int pageNo) {
		String sql = "select t from BaseQuery t where 1=1 "
			+ "/~ and t.queryString = {queryString} ~/"
			+ "/~ and t.queryTitle = {queryTitle} ~/"
			+ "/~ and t.queryName like {queryName} ~/"
			+ "/~ order by [orderBy] ~/";
		Map filters = new HashMap(); 
		XsqlBuilder xsqlBuilder=new XsqlBuilder();
		if(isNotEmpty(query.getQueryString())) {
			filters.put("queryString", query.getQueryString());
		}
		if(isNotEmpty(query.getQueryTitle())) {
			filters.put("queryTitle", query.getQueryTitle());
		}
        if(isNotEmpty(query.getQueryName())) {
            filters.put("queryName", "%"+query.getQueryName()+"%");
        }
        filters.put("orderBy","t.creTime desc ");
        XsqlFilterResult result = xsqlBuilder.generateHql(sql,filters); 
		return pageQuery(result.getXsql(), pageNo, pageSize,result.getAcceptedFilters());
	}
	
	public List test(){
		List list=getNamedQuery("baseRole::List");
		return list;
	}
}
