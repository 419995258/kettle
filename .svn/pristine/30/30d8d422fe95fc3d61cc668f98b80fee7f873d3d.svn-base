/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package org.my431.base.services;

import java.io.Serializable;
import java.util.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ice
 * @version 1.0
 * @since 1.0
 */


import org.apache.commons.lang.StringUtils;
import org.my431.platform.dao.extend.HibernateEntityExtendDao;
import org.springframework.stereotype.Repository;
import org.my431.base.model.*;
import org.my431.platform.dao.support.Page;
import org.my431.platform.listener.OnlineModel;
import org.my431.plugin.redis.services.RedisManager;
import org.my431.taglib.My431Function;

@Repository
public class BaseRoleUrlMapManager extends HibernateEntityExtendDao<BaseRoleUrlMap>{
	public static String listPreKey="global.base.BaseRoleUrlMap.key.id.";
	public static String urlObjectKey="global.base.BaseUrl.key.urlId.";
	private static String prePropertiesMapKey = "global.base.BaseProperties.key.";
	public Class getEntityClass() {
		return BaseRoleUrlMap.class;
	}
	private RedisManager redisManager;
	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}

	public void init() {
		List<BaseRoleUrlMap> list = this.getAll();
		int i=0;
		for (BaseRoleUrlMap node : list) {
			i++;
			//org.apache.log4j.Logger.getRootLogger().debug("进行加载第"+i+"条，共有"+list.size()+"条数据 urlId="+node.getUrlId());
			if(redisManager.objectHasKey(urlObjectKey+node.getUrlId())){
				if(redisManager.getOValue(urlObjectKey+node.getUrlId())!=null){
					BaseUrl baseUrl = (BaseUrl)redisManager.getOValue(urlObjectKey+node.getUrlId());
					redisManager.setOValue(listPreKey+node.getRoleCode()+baseUrl.getUrl(), 1);
				}else
				{
					super.remove(node);
				}
			}else
			{
				super.remove(node);
			}
			
		}
	}
	
	public void reload(String roleId) {
		List<BaseRoleUrlMap> list = this.findBy("roleId", roleId);
		for (BaseRoleUrlMap node : list) {
			BaseUrl baseUrl = (BaseUrl)redisManager.getOValue(urlObjectKey+node.getUrlId());
			redisManager.setOValue(listPreKey+node.getRoleCode()+baseUrl.getUrl(), 1);
		}
	}
	
	public static Boolean isPass(String roleCode,BaseUrl url){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		Boolean ispass=false;
		if(url!=null&&redisManager.objectHasKey(listPreKey+roleCode+url.getUrl())){
			if(redisManager.getOValue(listPreKey+roleCode+url.getUrl())!=null){
				if(redisManager.getOValue(listPreKey+roleCode+url.getUrl()).equals(1)){
					ispass=true;
				}
			}
		}
		return ispass;
	}
	@Override
	public void save(Object o){
		super.save(o);
		BaseUrl baseUrl = (BaseUrl)redisManager.getOValue(urlObjectKey+((BaseRoleUrlMap)o).getUrlId());
		redisManager.setOValue(listPreKey+((BaseRoleUrlMap)o).getRoleCode()+baseUrl.getUrl(), 1);
	}
	
	@Override
	public void update(Object o){
		super.update(o);
		BaseUrl baseUrl = (BaseUrl)redisManager.getOValue(urlObjectKey+((BaseRoleUrlMap)o).getUrlId());
		redisManager.setOValue(listPreKey+((BaseRoleUrlMap)o).getRoleCode()+baseUrl.getUrl(), 1);
	}
	
	@Override
	public void remove(Object o){
		super.remove(o);
		BaseUrl baseUrl = (BaseUrl)redisManager.getOValue(urlObjectKey+((BaseRoleUrlMap)o).getUrlId());
		redisManager.removeOValue(listPreKey+((BaseRoleUrlMap)o).getRoleCode()+baseUrl.getUrl());
	}
	
	@Override
	public void removeById(Serializable id){
		BaseRoleUrlMap o=this.get(id);
		BaseUrl baseUrl = (BaseUrl)redisManager.getOValue(urlObjectKey+((BaseRoleUrlMap)o).getUrlId());
		redisManager.removeOValue(listPreKey+((BaseRoleUrlMap)o).getRoleCode()+baseUrl.getUrl());
		super.removeById(id);
	}
	
	public Page findPage(BaseRoleUrlMap query, int pageSize, int pageNo) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from BaseRoleUrlMap t where 1=1 "
			  	+ "/~ and t.roleCode = {roleCode} ~/"
			  	+ "/~ and t.roleId = {roleId} ~/"
			  	+ "/~ and t.urlId = {urlId} ~/"
				+ "/~ order by [sortColumns] ~/";

        //生成sql2的原因是为了不喜欢使用xsqlbuilder的同学，请修改生成器模板，删除本段的生成
        StringBuilder sql2 = new StringBuilder("select t from BaseRoleUrlMap t where 1=1 ");
        if(isNotEmpty(query.getId())) {
            sql2.append(" and  t.id = :id ");
        }
        if(isNotEmpty(query.getRoleCode())) {
            sql2.append(" and  t.roleCode = :roleCode ");
        }
        if(isNotEmpty(query.getRoleId())) {
            sql2.append(" and  t.roleId = :roleId ");
        }
        if(isNotEmpty(query.getId())) {
            sql2.append(" and  t.urlId = :urlId ");
        }
        
		return pageQuery(sql, pageNo, pageSize);
	}
	

}
