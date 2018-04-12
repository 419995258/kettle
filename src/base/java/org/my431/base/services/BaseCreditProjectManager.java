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

import org.apache.commons.lang.StringUtils;
import org.my431.base.model.BaseArea;
import org.my431.base.model.BaseCreditProject;
import org.my431.base.model.BaseCreditTransRule;
import org.my431.base.model.BaseUser;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.my431.plugin.redis.services.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

@Repository
public class BaseCreditProjectManager extends HibernateXsqlBuilderQueryDao<BaseCreditProject>{
	private static String ProjectPrekey="global.base.BaseCreditProject.id.";
	
	@Autowired
	public BaseProAmountDistributeManager baseProAmountDistributeManager;
	
	public Class getEntityClass() {
		return BaseCreditProject.class;
	}
	

	/** (非 Javadoc)  
	*   
	*   
	* @param entity  
	* @see org.my431.platform.dao.extend.HibernateEntityExtendDao#onValid(java.lang.Object)  
	*/
	@Override
	protected void onValid(BaseCreditProject entity) {
		// TODO Auto-generated method stub
		if (entity.getId() == null) {
			
		}
	}	
	/**
	 * 重载
	 * @author 90
	 * @date    2017-6-8
	 * @param id
	 * @return
	 */
	public BaseCreditProject reload(String id){
		BaseCreditProject bp=this.get(id);
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		redisManager.setOValue(ProjectPrekey+id, bp);
		return bp;
	}
	
	/**
	 * 放入内存
	 * @author 90
	 * @date    2017-6-8
	 * @param bp
	 * @return
	 */
	public BaseCreditProject saveCacheBaseCreditProject(BaseCreditProject bp){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		redisManager.setOValue(ProjectPrekey+bp.getId(), bp);
		return bp;
	}
	/**
	 * 缓存获取
	 * @author 90
	 * @date    2017-6-8
	 * @param id
	 * @return
	 */
	public BaseCreditProject getCacheBaseCreditProject(String id){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		redisManager.removeOValue(ProjectPrekey+id);
		BaseCreditProject bp=null;
		if(redisManager.hasKey(ProjectPrekey+id)){
			redisManager.removeOValue(ProjectPrekey+id);
			bp=(BaseCreditProject) redisManager.getOValue(ProjectPrekey+id);
		}else{
			bp=this.get(id);
			redisManager.setOValue(ProjectPrekey+id, bp);
		}
		return bp;
	}
	public Page findPage(BaseCreditProject query, int pageSize, int pageNo) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from BaseCreditProject t where 1=1 "
			  	+ "/~ and t.org3rdId = {org3rdId} ~/"
			  	+ "/~ and t.proRefCode = {proRefCode} ~/"
			  	+ "/~ and t.proName = {proName} ~/"
			  	+ "/~ and t.proLevel = {proLevel} ~/"
			  	+ "/~ and t.proType = {proType} ~/"
			  	+ "/~ and t.tyear = {tyear} ~/"
			  	+ "/~ and t.proDealType = {proDealType} ~/"
			  	+ "/~ and t.faceStudySet = {faceStudySet} ~/"
			  	+ "/~ and t.facePostSet = {facePostSet} ~/"
			  	+ "/~ and t.proMoneyType = {proMoneyType} ~/"
			  	+ "/~ and t.proMoney = {proMoney} ~/"
			  	+ "/~ and t.proTotalNum = {proTotalNum} ~/"
			  	+ "/~ and t.planResource = {planResource} ~/"
			  	+ "/~ and t.planResourceType = {planResourceType} ~/"
			  	+ "/~ and t.ruCreditNum = {ruCreditNum} ~/"
			  	+ "/~ and t.ru1InputAmount = {ru1InputAmount} ~/"
			  	+ "/~ and t.ru1OutputAmount = {ru1OutputAmount} ~/"
			  	+ "/~ and t.ru2InputAmount = {ru2InputAmount} ~/"
			  	+ "/~ and t.ru2OutputAmount = {ru2OutputAmount} ~/"
			  	+ "/~ and t.ru3InputAmount = {ru3InputAmount} ~/"
			  	+ "/~ and t.ru3OutputAmount = {ru3OutputAmount} ~/"
			  	+ "/~ and t.ru4InputAmount = {ru4InputAmount} ~/"
			  	+ "/~ and t.ru4OutputAmount = {ru4OutputAmount} ~/"
			  	+ "/~ and t.ru5InputAmount = {ru5InputAmount} ~/"
			  	+ "/~ and t.ru5OutputAmount = {ru5OutputAmount} ~/"
			  	+ "/~ and t.proDetail = {proDetail} ~/"
			  	+ "/~ and t.provinceId = {provinceId} ~/"
			  	+ "/~ and t.cityId = {cityId} ~/"
			  	+ "/~ and t.countyId = {countyId} ~/"
			  	+ "/~ and t.schoolId = {schoolId} ~/"
			  	+ "/~ and t.creDepartId = {creDepartId} ~/"
			  	+ "/~ and t.creUserId = {creUserId} ~/"
				+ "/~ and t.creTime >= {creTimeBegin} ~/"
				+ "/~ and t.creTime <= {creTimeEnd} ~/"
			  	+ "/~ and t.auditStatus = {auditStatus} ~/"
				+ "/~ and t.auditTime >= {auditTimeBegin} ~/"
				+ "/~ and t.auditTime <= {auditTimeEnd} ~/"
			  	+ "/~ and t.auditUserId = {auditUserId} ~/"
			  	+ "/~ and t.auditType = {auditType} ~/"
			  	+ "/~ and t.processStaus = {processStaus} ~/"
				+ "/~ and t.processStausTime >= {processStausTimeBegin} ~/"
				+ "/~ and t.processStausTime <= {processStausTimeEnd} ~/"
			  	+ "/~ and t.delFlag = {delFlag} ~/"
				+ "/~ and t.delTime >= {delTimeBegin} ~/"
				+ "/~ and t.delTime <= {delTimeEnd} ~/"
			  	+ "/~ and t.delUserId = {delUserId} ~/"
			  	+ "/~ and t.sfItAbilityFlag = {sfItAbilityFlag} ~/"
				+ "/~ order by [sortColumns] ~/";

		Map filters = new HashMap();  
				
		XsqlBuilder xsqlBuilder=new XsqlBuilder();
        if(isNotEmpty(query.getOrg3rdId())) {
            filters.put("org3rdId", query.getOrg3rdId()); 
        }
        if(isNotEmpty(query.getProRefCode())) {
            filters.put("proRefCode", query.getProRefCode()); 
        }
        if(isNotEmpty(query.getProName())) {
            filters.put("proName", query.getProName()); 
        }
        if(isNotEmpty(query.getProLevel())) {
            filters.put("proLevel", query.getProLevel()); 
        }
        if(isNotEmpty(query.getProType())) {
            filters.put("proType", query.getProType()); 
        }
        if(isNotEmpty(query.getTyear())) {
            filters.put("tyear", query.getTyear()); 
        }
        if(isNotEmpty(query.getProDealType())) {
            filters.put("proDealType", query.getProDealType()); 
        }
        if(isNotEmpty(query.getFaceStudySet())) {
            filters.put("faceStudySet", query.getFaceStudySet()); 
        }
        if(isNotEmpty(query.getFacePostSet())) {
            filters.put("facePostSet", query.getFacePostSet()); 
        }
        if(isNotEmpty(query.getProMoneyType())) {
            filters.put("proMoneyType", query.getProMoneyType()); 
        }
        if(isNotEmpty(query.getProMoney())) {
            filters.put("proMoney", query.getProMoney()); 
        }
        if(isNotEmpty(query.getProTotalNum())) {
            filters.put("proTotalNum", query.getProTotalNum()); 
        }
        if(isNotEmpty(query.getPlanResource())) {
            filters.put("planResource", query.getPlanResource()); 
        }
        if(isNotEmpty(query.getPlanResourceType())) {
            filters.put("planResourceType", query.getPlanResourceType()); 
        }
        if(isNotEmpty(query.getRuCreditNum())) {
            filters.put("ruCreditNum", query.getRuCreditNum()); 
        }
        if(isNotEmpty(query.getRu1InputAmount())) {
            filters.put("ru1InputAmount", query.getRu1InputAmount()); 
        }
        if(isNotEmpty(query.getRu1OutputAmount())) {
            filters.put("ru1OutputAmount", query.getRu1OutputAmount()); 
        }
        if(isNotEmpty(query.getRu2InputAmount())) {
            filters.put("ru2InputAmount", query.getRu2InputAmount()); 
        }
        if(isNotEmpty(query.getRu2OutputAmount())) {
            filters.put("ru2OutputAmount", query.getRu2OutputAmount()); 
        }
        if(isNotEmpty(query.getRu3InputAmount())) {
            filters.put("ru3InputAmount", query.getRu3InputAmount()); 
        }
        if(isNotEmpty(query.getRu3OutputAmount())) {
            filters.put("ru3OutputAmount", query.getRu3OutputAmount()); 
        }
        if(isNotEmpty(query.getRu4InputAmount())) {
            filters.put("ru4InputAmount", query.getRu4InputAmount()); 
        }
        if(isNotEmpty(query.getRu4OutputAmount())) {
            filters.put("ru4OutputAmount", query.getRu4OutputAmount()); 
        }
        if(isNotEmpty(query.getRu5InputAmount())) {
            filters.put("ru5InputAmount", query.getRu5InputAmount()); 
        }
        if(isNotEmpty(query.getRu5OutputAmount())) {
            filters.put("ru5OutputAmount", query.getRu5OutputAmount()); 
        }
        if(isNotEmpty(query.getProDetail())) {
            filters.put("proDetail", query.getProDetail()); 
        }
        if(isNotEmpty(query.getProvinceId())) {
            filters.put("provinceId", query.getProvinceId()); 
        }
        if(isNotEmpty(query.getCityId())) {
            filters.put("cityId", query.getCityId()); 
        }
        if(isNotEmpty(query.getCountyId())) {
            filters.put("countyId", query.getCountyId()); 
        }
        if(isNotEmpty(query.getSchoolId())) {
            filters.put("schoolId", query.getSchoolId()); 
        }
        if(isNotEmpty(query.getCreDepartId())) {
            filters.put("creDepartId", query.getCreDepartId()); 
        }
        if(isNotEmpty(query.getCreUserId())) {
            filters.put("creUserId", query.getCreUserId()); 
        }
        if(isNotEmpty(query.getCreTime())) {
            filters.put("creTime", query.getCreTime()); 
        }
        if(isNotEmpty(query.getAuditStatus())) {
            filters.put("auditStatus", query.getAuditStatus()); 
        }
        if(isNotEmpty(query.getAuditTime())) {
            filters.put("auditTime", query.getAuditTime()); 
        }
        if(isNotEmpty(query.getAuditUserId())) {
            filters.put("auditUserId", query.getAuditUserId()); 
        }
        if(isNotEmpty(query.getAuditType())) {
            filters.put("auditType", query.getAuditType()); 
        }
        if(isNotEmpty(query.getProcessStaus())) {
            filters.put("processStaus", query.getProcessStaus()); 
        }
        if(isNotEmpty(query.getProcessStausTime())) {
            filters.put("processStausTime", query.getProcessStausTime()); 
        }
        if(isNotEmpty(query.getDelFlag())) {
            filters.put("delFlag", query.getDelFlag()); 
        }
        if(isNotEmpty(query.getDelTime())) {
            filters.put("delTime", query.getDelTime()); 
        }
        if(isNotEmpty(query.getDelUserId())) {
            filters.put("delUserId", query.getDelUserId()); 
        }
        if(isNotEmpty(query.getSfItAbilityFlag())) {
            filters.put("sfItAbilityFlag", query.getSfItAbilityFlag()); 
        }
        
        XsqlFilterResult result = xsqlBuilder.generateHql(sql,filters); 
        
		return pageQuery(result.getXsql(), pageNo, pageSize,result.getAcceptedFilters());
	}
	/**
	 * 
	* @Description: 分页查询  
	* @author hyl     
	* @date 2017-6-6 下午6:33:41  
	* @version V1.0 
	* @author user
	 */
	public Page getPageList(BaseCreditProject query, int pageSize, int pageNo,String areaCode,String otherQuery) {
		Map<String, Object>  mapValues = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(otherQuery)) {
			mapValues.put("otherQuery", otherQuery);
		}
		if(StringUtils.isNotBlank(areaCode)){
			BaseArea baseArea = CacheBaseAreaManager.getNodeByCode(areaCode);
			if(areaCode.length()==3){
				mapValues.put("provinceId", baseArea.getId());
			}
			if(areaCode.length()==6){
				mapValues.put("cityId", baseArea.getId());
			}
			if(areaCode.length()==9){
				mapValues.put("countyId", baseArea.getId());
			}
		}
		if (StringUtils.isNotBlank(query.getOrg3rdId())) {
			mapValues.put("org3rdId", query.getOrg3rdId());
		}
		if (StringUtils.isNotBlank(query.getProLevel())) {
			mapValues.put("proLevel", query.getProLevel());
		}
		if (StringUtils.isNotBlank(query.getProType())) {
			mapValues.put("proType", query.getProType());
		}
		return getPagedNamedQuery("misBase::getBaseCreditProjectList::query", pageNo, pageSize, mapValues);
	}
	/**
	 * 
	* @Description: 根据项目类型查询规则  
	* @author hyl     
	* @date 2017-6-13 下午7:58:49  
	* @version V1.0 
	* @author user
	 */
	public List<BaseCreditTransRule> getRuleByProType(String roleCode, String proType) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "";
		if ("role.schoolManager".equals(roleCode)) {
			hql = "from BaseCreditTransRule t where t.proLevel in('pro.level.school','pro.level.other') and t.proType =:proType ";
		}
		if ("role.provinceManager".equals(roleCode)) {
			hql = "from BaseCreditTransRule t where t.proLevel='pro.level.province' and t.proType =:proType ";
		}
		if ("role.cityManager".equals(roleCode)) {
			hql = "from BaseCreditTransRule t where t.proLevel='pro.level.city' and t.proType =:proType ";
		}
		if ("role.countryManager".equals(roleCode)) {
			hql = "from BaseCreditTransRule t where t.proLevel='pro.level.county' and t.proType =:proType ";
		}
		if ("role.nationalManager".equals(roleCode)) {
			hql = "from BaseCreditTransRule t where t.proLevel='pro.level.national' and t.proType =:proType ";
		}
		if (StringUtils.isNotBlank(proType)) {
			map.put("proType", proType);
		}
		List<BaseCreditTransRule> list = find(hql, map);
		return list;
	}
	/**
	 * 
	* @Description: 校本项目保存 
	* @author hyl     
	* @date 2017-6-14 下午7:50:23  
	* @version V1.0 
	* @author user
	 */
	public void saveCreditPro(BaseCreditProject baseCreditProject) {
		save(baseCreditProject);
		saveCacheBaseCreditProject(baseCreditProject);
	}
	/**
	 * 
	* @Description:同时保存一条记录到项目名额分配表
	* @author hyl     
	 */
	public void  saveCreditProAndProAmountDistribute(BaseCreditProject baseCreditProject,BaseUser baseUser) {
		save(baseCreditProject);
		saveCacheBaseCreditProject(baseCreditProject);
		baseProAmountDistributeManager.saveBaseProAmountDistributeAfterCreate(baseCreditProject, baseUser);
	}
	
	/**
	 * 查询项目
	 * @author 90
	 * @date    2017-6-14
	 * @param map
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public Page getBaseCreProjectPage(Map<String, Object> map,int pageSize,int pageNo){
		Map<String,Object> values=new HashMap<String,Object>();
		for (String key:map.keySet()) {
			Object thisValue=map.get(key);
			if(isNotEmpty(thisValue)){
				values.put(key, thisValue);
			}
		}
		return this.getPagedNamedQuery("misBase::BaseCreditProject::getBaseCreProjectPage", pageNo, pageSize, values);
	}
}
