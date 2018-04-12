/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package org.my431.base.services;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.my431.base.model.BaseCreditTransRule;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.my431.plugin.redis.services.RedisManager;
import org.my431.util.MMap;
import org.springframework.stereotype.Repository;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

@Repository
public class BaseCreditTransRuleManager extends HibernateXsqlBuilderQueryDao<BaseCreditTransRule>{
	private static String BaseCreditTransRulePrekey="global.base.BaseCreditTransRule.id.";
	public void init() {
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		List<BaseCreditTransRule> list = getAll();
		if (MMap.validateList(list)) {
			//不使用
			/*for (BaseCreditTransRule baseCreditTransRule : list) {
				redisManager.setOValue(BaseCreditTransRulePrekey+baseCreditTransRule.getProLevel()+"."+baseCreditTransRule.getProType(), baseCreditTransRule.getRresultType());
			}*/
		}
		
	}
	//不使用
	@Deprecated
	public String  getRResultType(String proLevel,String proType) {
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		Object obj = redisManager.getOValue(BaseCreditTransRulePrekey+proLevel+"."+proType);
		return obj==null?"":obj.toString();
	}
	public Class getEntityClass() {
		return BaseCreditTransRule.class;
	}
	
	public Page findPage(BaseCreditTransRule query, int pageSize, int pageNo) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from BaseCreditTransRule t where 1=1 "
			  	+ "/~ and t.proLevel = {proLevel} ~/"
			  	+ "/~ and t.proType = {proType} ~/"
			  	+ "/~ and t.rresultType = {rresultType} ~/"
			  	+ "/~ and t.rinputUnit = {rinputUnit} ~/"
			  	+ "/~ and t.rinputAmount = {rinputAmount} ~/"
			  	+ "/~ and t.routputUnit = {routputUnit} ~/"
			  	+ "/~ and t.routputAmount = {routputAmount} ~/"
			  	+ "/~ and t.creUserId = {creUserId} ~/"
				+ "/~ and t.creTime >= {creTimeBegin} ~/"
				+ "/~ and t.creTime <= {creTimeEnd} ~/"
			  	+ "/~ and t.modUserId = {modUserId} ~/"
				+ "/~ and t.modTime >= {modTimeBegin} ~/"
				+ "/~ and t.modTime <= {modTimeEnd} ~/"
			  	+ "/~ and t.rstatus = {rstatus} ~/"
				+ "/~ order by [sortColumns] ~/";

		Map filters = new HashMap();  
				
		XsqlBuilder xsqlBuilder=new XsqlBuilder();
		
        if(isNotEmpty(query.getProLevel())) {
            filters.put("proLevel", query.getProLevel()); 
        }
        if(isNotEmpty(query.getProType())) {
            filters.put("proType", query.getProType()); 
        }
        if(isNotEmpty(query.getRresultType())) {
            filters.put("rresultType", query.getRresultType()); 
        }
        if(isNotEmpty(query.getRinputUnit())) {
            filters.put("rinputUnit", query.getRinputUnit()); 
        }
        if(isNotEmpty(query.getRinputAmount())) {
            filters.put("rinputAmount", query.getRinputAmount()); 
        }
        if(isNotEmpty(query.getRoutputUnit())) {
            filters.put("routputUnit", query.getRoutputUnit()); 
        }
        if(isNotEmpty(query.getRoutputAmount())) {
            filters.put("routputAmount", query.getRoutputAmount()); 
        }
        if(isNotEmpty(query.getCreUserId())) {
            filters.put("creUserId", query.getCreUserId()); 
        }
        if(isNotEmpty(query.getCreTime())) {
            filters.put("creTime", query.getCreTime()); 
        }
        if(isNotEmpty(query.getModUserId())) {
            filters.put("modUserId", query.getModUserId()); 
        }
        if(isNotEmpty(query.getModTime())) {
            filters.put("modTime", query.getModTime()); 
        }
        if(isNotEmpty(query.getRstatus())) {
            filters.put("rstatus", query.getRstatus()); 
        }
        
        XsqlFilterResult result = xsqlBuilder.generateHql(sql,filters); 
        
		return pageQuery(result.getXsql(), pageNo, pageSize,result.getAcceptedFilters());
	}
	

}
