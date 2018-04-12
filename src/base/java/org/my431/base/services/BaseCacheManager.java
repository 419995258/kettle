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

import org.my431.base.model.BaseCache;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.springframework.stereotype.Repository;

@Repository
public class BaseCacheManager extends HibernateXsqlBuilderQueryDao<BaseCache>{

	public Class getEntityClass() {
		return BaseCache.class;
	}
	
	/**
	 * 系统启动载入BaseCache表对象。
	 * BaseCache表对象保存系统中所有缓存key的规划值。
	 * @author wangzhen
	 */
	public void init(){
		List<BaseCache> list=this.getAll();
		for(BaseCache baseCache:list){
			CacheBaseCacheManager.setValue(baseCache.getCacheName(), baseCache);
		}
	}
	
	/**
	 * 删除一个对象。
	 * @author wangzhen
	 * @param baseCache
	 */
	public void del(BaseCache baseCache){
		CacheBaseCacheManager.removeValue(baseCache.getCacheName());
	}
	
	/**
	 * 新增一个对象。
	 * @author wangzhen
	 * @param baseCache
	 */
	public void add(BaseCache baseCache){
		CacheBaseCacheManager.setValue(baseCache.getCacheName(), baseCache);
	}
	
	/**
	 * 重载一个对象。
	 * @author wangzhen
	 * @param id
	 */
	public void reload(String id){
		BaseCache baseCache=getById(id);
		CacheBaseCacheManager.setValue(baseCache.getCacheName(), baseCache);
	}
	
	/**
	 * 获取一个对象。
	 * @param id
	 * @return
	 */
	public BaseCache getById(String id){
		String hql="select t from BaseCache t where t.id=?";
		List<BaseCache> list=find(hql,id);
		if(list!=null&&list.size()==1){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public Page findPage(BaseCache query, int pageSize, int pageNo) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from BaseCache t where 1=1 "
			  	+ "/~ and t.cacheName = {cacheName} ~/"
			  	+ "/~ and t.cachePreKey = {cachePreKey} ~/"
			  	+ "/~ and t.cacheAftKey = {cacheAftKey} ~/"
			  	+ "/~ and t.cacheTable = {cacheTable} ~/"
			  	+ "/~ and t.note = {note} ~/"
				+ "/~ and t.creTime >= {creTimeBegin} ~/"
				+ "/~ and t.creTime <= {creTimeEnd} ~/"
			  	+ "/~ and t.creUser = {creUser} ~/"
				+ "/~ and t.modTime >= {modTimeBegin} ~/"
				+ "/~ and t.modTime <= {modTimeEnd} ~/"
			  	+ "/~ and t.modUser = {modUser} ~/"
				+ "/~ order by [sortColumns] ~/";

		Map filters = new HashMap();
		XsqlBuilder xsqlBuilder=new XsqlBuilder();
        XsqlFilterResult result = xsqlBuilder.generateHql(sql,filters); 
		return pageQuery(result.getXsql(), pageNo, pageSize,result.getAcceptedFilters());
	}

}
