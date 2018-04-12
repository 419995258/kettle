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


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.my431.base.model.BaseCreditProject;
import org.my431.base.model.BaseProAmountDistribute;
import org.my431.base.model.BaseUser;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.my431.util.MMap;
import org.springframework.stereotype.Repository;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

@Repository
public class BaseProAmountDistributeManager extends HibernateXsqlBuilderQueryDao<BaseProAmountDistribute>{

	public Class getEntityClass() {
		return BaseProAmountDistribute.class;
	}
	
	public Page findPage(BaseProAmountDistribute query, int pageSize, int pageNo) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from BaseProAmountDistribute t where 1=1 "
			  	+ "/~ and t.proId = {proId} ~/"
			  	+ "/~ and t.lockStatus = {lockStatus} ~/"
			  	+ "/~ and t.departmentType = {departmentType} ~/"
			  	+ "/~ and t.departmentId = {departmentId} ~/"
			  	+ "/~ and t.nodeType = {nodeType} ~/"
			  	+ "/~ and t.totalAmount = {totalAmount} ~/"
			  	+ "/~ and t.parentProadId = {parentProadId} ~/"
			  	+ "/~ and t.rootProadId = {rootProadId} ~/"
			  	+ "/~ and t.rootProadLevel = {rootProadLevel} ~/"
			  	+ "/~ and t.totalAmountSignedUp = {totalAmountSignedUp} ~/"
			  	+ "/~ and t.thisGradeAmountRemained = {thisGradeAmountRemained} ~/"
			  	+ "/~ and t.delFlag = {delFlag} ~/"
				+ "/~ and t.creTime >= {creTimeBegin} ~/"
				+ "/~ and t.creTime <= {creTimeEnd} ~/"
			  	+ "/~ and t.creUser = {creUser} ~/"
				+ "/~ and t.modTime >= {modTimeBegin} ~/"
				+ "/~ and t.modTime <= {modTimeEnd} ~/"
				+ "/~ and t.modUser >= {modUserBegin} ~/"
				+ "/~ and t.modUser <= {modUserEnd} ~/"
			  	+ "/~ and t.provinceId = {provinceId} ~/"
			  	+ "/~ and t.cityId = {cityId} ~/"
			  	+ "/~ and t.countyId = {countyId} ~/"
			  	+ "/~ and t.schoolId = {schoolId} ~/"
			  	+ "/~ and t.tyear = {tyear} ~/"
			  	+ "/~ and t.proadComment = {proadComment} ~/"
				+ "/~ order by [sortColumns] ~/";

		Map filters = new HashMap();  
				
		XsqlBuilder xsqlBuilder=new XsqlBuilder();
		
        if(isNotEmpty(query.getProId())) {
            filters.put("proId", query.getProId()); 
        }
        if(isNotEmpty(query.getLockStatus())) {
            filters.put("lockStatus", query.getLockStatus()); 
        }
        if(isNotEmpty(query.getDepartmentType())) {
            filters.put("departmentType", query.getDepartmentType()); 
        }
        if(isNotEmpty(query.getDepartmentId())) {
            filters.put("departmentId", query.getDepartmentId()); 
        }
        if(isNotEmpty(query.getNodeType())) {
            filters.put("nodeType", query.getNodeType()); 
        }
        if(isNotEmpty(query.getTotalAmount())) {
            filters.put("totalAmount", query.getTotalAmount()); 
        }
        if(isNotEmpty(query.getParentProadId())) {
            filters.put("parentProadId", query.getParentProadId()); 
        }
        if(isNotEmpty(query.getRootProadId())) {
            filters.put("rootProadId", query.getRootProadId()); 
        }
        if(isNotEmpty(query.getRootProadLevel())) {
            filters.put("rootProadLevel", query.getRootProadLevel()); 
        }
        if(isNotEmpty(query.getTotalAmountSignedUp())) {
            filters.put("totalAmountSignedUp", query.getTotalAmountSignedUp()); 
        }
        if(isNotEmpty(query.getThisGradeAmountRemained())) {
            filters.put("thisGradeAmountRemained", query.getThisGradeAmountRemained()); 
        }
        if(isNotEmpty(query.getDelFlag())) {
            filters.put("delFlag", query.getDelFlag()); 
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
        if(isNotEmpty(query.getTyear())) {
            filters.put("tyear", query.getTyear()); 
        }
        if(isNotEmpty(query.getProadComment())) {
            filters.put("proadComment", query.getProadComment()); 
        }
        
        XsqlFilterResult result = xsqlBuilder.generateHql(sql,filters); 
        
		return pageQuery(result.getXsql(), pageNo, pageSize,result.getAcceptedFilters());
	}
	/**
	 * 
	* @Description: 创建项目后保存数据 
	* @author hyl     
	 */
	public  String saveBaseProAmountDistributeAfterCreate(BaseCreditProject baseCreditProject,BaseUser baseUser) {
		BaseProAmountDistribute baseProAmountDistribute = new BaseProAmountDistribute();
		baseProAmountDistribute.setProId(baseCreditProject.getId());
		baseProAmountDistribute.setLockStatus(0);
		baseProAmountDistribute.setDepartmentType(baseCreditProject.getProLevel());
		if ("pro.level.national".equals(baseCreditProject.getProLevel())) {
			baseProAmountDistribute.setDepartmentId("1");
			baseProAmountDistribute.setNodeType(0);
		}
		if ("pro.level.province".equals(baseCreditProject.getProLevel())) {
			baseProAmountDistribute.setDepartmentId(baseCreditProject.getProvinceId());
			baseProAmountDistribute.setNodeType(0);
		}
		if ("pro.level.city".equals(baseCreditProject.getProLevel())) {
			baseProAmountDistribute.setDepartmentId(baseCreditProject.getCityId());
			baseProAmountDistribute.setNodeType(0);
		}
		if ("pro.level.county".equals(baseCreditProject.getProLevel())) {
			baseProAmountDistribute.setDepartmentId(baseCreditProject.getCountyId());
			baseProAmountDistribute.setNodeType(0);
		}
		if ("pro.level.school".equals(baseCreditProject.getProLevel())) {
			baseProAmountDistribute.setDepartmentId(baseCreditProject.getSchoolId());
			baseProAmountDistribute.setNodeType(1);
		}
		baseProAmountDistribute.setTotalAmount(baseCreditProject.getProTotalNum());
		//baseProAmountDistribute.setParentProadId();//父账户
		//baseProAmountDistribute.setRootProadId();//根账户ID
		//baseProAmountDistribute.setRootProadLevel();//跟账户级别
		//baseProAmountDistribute.setTotalAmountSignedUp();//教师报名人数合计(教师报名/取消报名时用)
		baseProAmountDistribute.setThisGradeAmountRemained(baseCreditProject.getProTotalNum());//本级剩余名额
		baseProAmountDistribute.setDelFlag(0);
		baseProAmountDistribute.setCreTime(new Date());
		baseProAmountDistribute.setCreUser(baseUser.getId());
		baseProAmountDistribute.setProvinceId(baseCreditProject.getProvinceId());
		baseProAmountDistribute.setCityId(baseCreditProject.getCityId());
		baseProAmountDistribute.setCountyId(baseCreditProject.getCountyId());
		baseProAmountDistribute.setSchoolId(baseCreditProject.getSchoolId());
		baseProAmountDistribute.setTyear(baseCreditProject.getTyear());
		baseProAmountDistribute.setProadComment("112345566");
		save(baseProAmountDistribute);
		return "1";
	}
	/**
	 * 
	* @Description: 
	* @author hyl     
	 */
	public List<Map<String, Object>> getBaseProAmountDistributeList(Map<String, Object> values,String otherQuery) {
		Map<String,Object> map=new HashMap<String,Object>();
		if (StringUtils.isNotBlank(otherQuery)) {
			map.put("otherQuery", otherQuery);
		}
		map.put("chaxuntiaojian1", "t.*");
		if (!values.isEmpty()) {
			for (Map.Entry<String, Object> entry: values.entrySet()) {
				if (entry.getValue() != null) {
					map.put(entry.getKey(), entry.getValue());
				}
			}
		}
		return getNamedQuery("misBase::getBaseProAmountDistributeList::query", map);
		
	}
	/**
	 * 
	* @Description: 批量保存名额分配表  
	* @author hyl     
	 */
	public void saveBaseProAmountDistributeList(List<BaseProAmountDistribute> baseProAmountDistributeList ) {
		if (MMap.validateList(baseProAmountDistributeList)) {
			for (BaseProAmountDistribute baseProAmountDistribute : baseProAmountDistributeList) {
				save(baseProAmountDistribute);
			}
		}
	}
	/**
	 * 
	* @Description: 
	* @author hyl     
	 */
	public MMap getAreaDistributeMap(Map<String, Object> values,String otherQuery) {
		Map<String,Object> map=new HashMap<String,Object>();
		if (StringUtils.isNotBlank(otherQuery)) {
			map.put("otherQuery", otherQuery);
		}
		if (!values.isEmpty()) {
			for (Map.Entry<String, Object> entry: values.entrySet()) {
				if (entry.getValue() != null) {
					map.put(entry.getKey(), entry.getValue());
				}
			}
		}
		MMap allMMap = new MMap();
		Map<String,Object> allMapTotal=new HashMap<String,Object>();
		Map<String,Object> allMapRemained=new HashMap<String,Object>();
		Map<String,Object> allMapSigned=new HashMap<String,Object>();
		Integer areaCnt = 0;
		List<Map<String, Object>> list = getNamedQuery("misBase::getBaseProAmountDistributeList::query", map);
		if (MMap.validateList(list)) {
			for (Map<String, Object> map2 : list) {
				String areaId = map2.get("AREAID").toString();
				Object total = map2.get("TOTAL");
				Object remained = map2.get("REMAINED");
				Object signed = map2.get("SIGNCNT");
				String cnt = map2.get("CNT").toString();
				allMapTotal.put(areaId, MMap.isnullInt(total));
				allMapRemained.put(areaId, MMap.isnullInt(remained));
				allMapSigned.put(areaId, MMap.isnullInt(signed));
				areaCnt = areaCnt + MMap.isnullInt(cnt);
			}
		}
		allMMap.setObj(allMapTotal);
		allMMap.setObj1(allMapRemained);
		allMMap.setObj2(allMapSigned);
		allMMap.setObj3(areaCnt);
		return allMMap;
	}
	

}
