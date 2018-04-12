/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2011
 */

package org.my431.base.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import org.my431.base.model.BaseRole;
import org.my431.platform.dao.extend.HibernateEntityExtendDao;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.my431.plugin.redis.services.RedisManager;
import org.springframework.stereotype.Repository;

@Repository
public class BaseRoleManager extends HibernateEntityExtendDao<BaseRole>{
	private static String preRoleMapKey="global.base.BaseRole.map.key.";
	private RedisManager redisManager;
	
	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}
	public Class getEntityClass() {
		return BaseRole.class;
	}
	
	public void init() {
		List<BaseRole> roleList=this.getAll();
		for(BaseRole role:roleList){
			redisManager.setOValue(preRoleMapKey+role.getRoleCode(),role);
		}
	}
	
	public void reload(){
		init();
	}
	
	public void updateOne(BaseRole role){
		redisManager.setOValue(preRoleMapKey+role.getRoleCode(),role);
	}
	
	public void delOne(BaseRole role){
		redisManager.removeOValue(preRoleMapKey+role.getRoleCode());
	}
	
	public BaseRole getByCode(String code){
		Object o=redisManager.getOValue(preRoleMapKey+code);
		if(o!=null){
			return (BaseRole)o;
		}
		return null;
	}
	
	public Page findPage(BaseRole query, int pageSize, int pageNo) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from BaseRole t where 1=1 "
				+ "/~ and t.id = {id} ~/"
			  	+ "/~ and t.roleName = {roleName} ~/"
			  	+ "/~ and t.roleCode = {roleCode} ~/"
			  	+ "/~ and t.roleDesc = {roleDesc} ~/"
			  	+ "/~ and t.roleType = {roleType} ~/"
				+ "/~ and t.creTime >= {creTimeBegin} ~/"
				+ "/~ and t.creTime <= {creTimeEnd} ~/"
			  	+ "/~ and t.creUser = {creUser} ~/"
				+ "/~ and t.modTime >= {modTimeBegin} ~/"
				+ "/~ and t.modTime <= {modTimeEnd} ~/"
			  	+ "/~ and t.modUser = {modUser} ~/"
			  	+ "/~ and t.defaultUrl = {defaultUrl} ~/"
			  	+ "/~ and t.transformRole = {transformRole} ~/"
				+ "/~ order by [sortColumns] ~/";
		
		Map filters = new HashMap();  
		
		XsqlBuilder xsqlBuilder=new XsqlBuilder();
		
		XsqlFilterResult result = xsqlBuilder.applyFilters(sql,filters); 

        if(isNotEmpty(query.getId())) {
            filters.put("id", query.getId()); 
        }
        if(isNotEmpty(query.getRoleName())) {
        	filters.put("roleName", query.getRoleName()); 
        }
        if(isNotEmpty(query.getRoleCode())) {
            filters.put("roleCode", query.getRoleCode()); 
        }
        if(isNotEmpty(query.getRoleDesc())) {
            filters.put("roleDesc", query.getRoleDesc()); 
        }
        if(isNotEmpty(query.getRoleType())) {
            filters.put("roleType", query.getRoleType()); 
        }
        if(isNotEmpty(query.getCreTime())) {
            filters.put("creTime", query.getCreTime()); 
        }
        if(isNotEmpty(query.getCreUser())) {
            filters.put("creUser", query.getCreUser()); 
        }
        if(isNotEmpty(query.getModTime())) {
            filters.put("modTime", query.getModTime()); 
        }
        if(isNotEmpty(query.getModUser())) {
            filters.put("modUser", query.getModUser()); 
        }
        if(isNotEmpty(query.getDefaultUrl())) {
            filters.put("defaultUrl", query.getDefaultUrl()); 
        }
        if(isNotEmpty(query.getTransformRole())) {
            filters.put("transformRole", query.getTransformRole()); 
        }
        
		return pageQuery(result.getXsql(), pageNo, pageSize);
	}
	
	/**
	 * 角色管理中的新增修改页面中的其他角色的选择
	 * @param operater
	 * @param roleId
	 * @return
	 */
	public List<BaseRole> getBaseRoleList(String operater, String roleId) {
		if(operater.equals("add")){//add
			return getAll();
		}else{//edit
			String hql = "from BaseRole a where a.id <> '" + roleId + "'";
			return find(hql);
		}
	}
	/**
	 * 查询所有的身份 暂未使用
	 * @author    hmc    2015年4月9日  下午4:20:41
	 */
	public List getAllRole() {
		HibernateXsqlBuilderQueryDao xsql=new HibernateXsqlBuilderQueryDao<BaseRole>();
		Map m=new HashMap();
		List List=xsql.getNamedQuery("misBase:getAllRole:query", m);
		return List;
	}
}
