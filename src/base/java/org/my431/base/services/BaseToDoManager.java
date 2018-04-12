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
import org.my431.base.model.BaseToDo;
import org.my431.base.model.BaseUser;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.my431.taglib.My431Function;
import org.my431.util.DateFormater;
import org.my431.util.MMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

@Repository
public class BaseToDoManager extends HibernateXsqlBuilderQueryDao<BaseToDo>{

	@Autowired
	private BaseUserManager baseUserManager;
	
	public Class getEntityClass() {
		return BaseToDo.class;
	}
	
	public Page findPage(BaseToDo query, int pageSize, int pageNo) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from BaseToDo t where 1=1 "
			  	+ "/~ and t.todoType = {todoType} ~/"
			  	+ "/~ and t.resourceParameter = {resourceParameter} ~/"
			  	+ "/~ and t.reourceUrl = {reourceUrl} ~/"
			  	+ "/~ and t.belongToUserId = {belongToUserId} ~/"
			  	+ "/~ and t.belongToProvinceId = {belongToProvinceId} ~/"
			  	+ "/~ and t.belongToCityId = {belongToCityId} ~/"
			  	+ "/~ and t.belongToCountyId = {belongToCountyId} ~/"
			  	+ "/~ and t.belongToSchoolId = {belongToSchoolId} ~/"
			  	+ "/~ and t.belongToInstitutionId = {belongToInstitutionId} ~/"
			  	+ "/~ and t.belongToDepartmentType = {belongToDepartmentType} ~/"
			  	+ "/~ and t.creUser = {creUser} ~/"
				+ "/~ and t.creTime >= {creTimeBegin} ~/"
				+ "/~ and t.creTime <= {creTimeEnd} ~/"
				+ "/~ and t.expiredTime >= {expiredTimeBegin} ~/"
				+ "/~ and t.expiredTime <= {expiredTimeEnd} ~/"
			  	+ "/~ and t.todoDealStatus = {todoDealStatus} ~/"
			  	+ "/~ and t.comeFromProvinceId = {comeFromProvinceId} ~/"
			  	+ "/~ and t.comeFromCityId = {comeFromCityId} ~/"
			  	+ "/~ and t.comeFromCountyId = {comeFromCountyId} ~/"
			  	+ "/~ and t.comeFromSchoolId = {comeFromSchoolId} ~/"
			  	+ "/~ and t.comeFromInstitutionId = {comeFromInstitutionId} ~/"
			  	+ "/~ and t.comeDepartmentType = {comeDepartmentType} ~/"
			  	+ "/~ and t.deleteFlag = {deleteFlag} ~/"
			  	+ "/~ and t.deleteUser = {deleteUser} ~/"
			  	+ "/~ and t.deleteComment = {deleteComment} ~/"
				+ "/~ and t.dealLastTime >= {dealLastTimeBegin} ~/"
				+ "/~ and t.dealLastTime <= {dealLastTimeEnd} ~/"
			  	+ "/~ and t.dealLastUser = {dealLastUser} ~/"
				+ "/~ order by [sortColumns] ~/";

		Map filters = new HashMap();  
				
		XsqlBuilder xsqlBuilder=new XsqlBuilder();
		
        if(isNotEmpty(query.getTodoType())) {
            filters.put("todoType", query.getTodoType()); 
        }
        if(isNotEmpty(query.getResourceParameter())) {
            filters.put("resourceParameter", query.getResourceParameter()); 
        }
        if(isNotEmpty(query.getReourceUrl())) {
            filters.put("reourceUrl", query.getReourceUrl()); 
        }
        if(isNotEmpty(query.getBelongToUserId())) {
            filters.put("belongToUserId", query.getBelongToUserId()); 
        }
        if(isNotEmpty(query.getBelongToProvinceId())) {
            filters.put("belongToProvinceId", query.getBelongToProvinceId()); 
        }
        if(isNotEmpty(query.getBelongToCityId())) {
            filters.put("belongToCityId", query.getBelongToCityId()); 
        }
        if(isNotEmpty(query.getBelongToCountyId())) {
            filters.put("belongToCountyId", query.getBelongToCountyId()); 
        }
        if(isNotEmpty(query.getBelongToSchoolId())) {
            filters.put("belongToSchoolId", query.getBelongToSchoolId()); 
        }
        if(isNotEmpty(query.getBelongToInstitutionId())) {
            filters.put("belongToInstitutionId", query.getBelongToInstitutionId()); 
        }
        if(isNotEmpty(query.getBelongToDepartmentType())) {
            filters.put("belongToDepartmentType", query.getBelongToDepartmentType()); 
        }
        if(isNotEmpty(query.getCreUser())) {
            filters.put("creUser", query.getCreUser()); 
        }
        if(isNotEmpty(query.getCreTime())) {
            filters.put("creTime", query.getCreTime()); 
        }
        if(isNotEmpty(query.getExpiredTime())) {
            filters.put("expiredTime", query.getExpiredTime()); 
        }
        if(isNotEmpty(query.getTodoDealStatus())) {
            filters.put("todoDealStatus", query.getTodoDealStatus()); 
        }
        if(isNotEmpty(query.getComeFromProvinceId())) {
            filters.put("comeFromProvinceId", query.getComeFromProvinceId()); 
        }
        if(isNotEmpty(query.getComeFromCityId())) {
            filters.put("comeFromCityId", query.getComeFromCityId()); 
        }
        if(isNotEmpty(query.getComeFromCountyId())) {
            filters.put("comeFromCountyId", query.getComeFromCountyId()); 
        }
        if(isNotEmpty(query.getComeFromSchoolId())) {
            filters.put("comeFromSchoolId", query.getComeFromSchoolId()); 
        }
        if(isNotEmpty(query.getComeFromInstitutionId())) {
            filters.put("comeFromInstitutionId", query.getComeFromInstitutionId()); 
        }
        if(isNotEmpty(query.getComeDepartmentType())) {
            filters.put("comeDepartmentType", query.getComeDepartmentType()); 
        }
        if(isNotEmpty(query.getDeleteFlag())) {
            filters.put("deleteFlag", query.getDeleteFlag()); 
        }
        if(isNotEmpty(query.getDeleteUser())) {
            filters.put("deleteUser", query.getDeleteUser()); 
        }
        if(isNotEmpty(query.getDeleteComment())) {
            filters.put("deleteComment", query.getDeleteComment()); 
        }
        if(isNotEmpty(query.getDealLastTime())) {
            filters.put("dealLastTime", query.getDealLastTime()); 
        }
        if(isNotEmpty(query.getDealLastUser())) {
            filters.put("dealLastUser", query.getDealLastUser()); 
        }
        
        XsqlFilterResult result = xsqlBuilder.generateHql(sql,filters); 
        
		return pageQuery(result.getXsql(), pageNo, pageSize,result.getAcceptedFilters());
	}
	/**
	 * 
	* @Description: 获取待办事项列表 ,map中key为pojo的各字段命名
	* @author hyl     
	* @date 2017-6-20 上午11:10:55  
	* @version V1.0 
	* @author user
	 */
	public List<Map<String, Object>> getBaseToDoList(Map<String, Object> map,String otherQuery,String orderBy) {
		Map<String,Object> values=new HashMap<String,Object>();
		if (values != null && !values.isEmpty()) {
			for (String key : map.keySet()) {
				Object thisValue=map.get(key);
				if(isNotEmpty(thisValue)){
					values.put(key, thisValue);
				}
			}
		}
		if (StringUtils.isNotBlank(otherQuery)) {
			values.put("otherQuery", otherQuery);
		}
		if (StringUtils.isNotBlank(orderBy)) {
			values.put("orderBy", orderBy);
		}
		return getNamedQuery("misBase::getBaseToDoList::query",values);
	}
	/**
	 * 
	* @Description: 保存或者更新待办事项  
	* @author hyl     
	* @date 2017-6-20 下午3:44:50  
	* @version V1.0 
	* @author user
	 */
	public void saveOrUpdateBaseTodo(BaseToDo baseToDo,BaseUser baseUser) { 
		baseToDo.setBelongToUserId(baseUser.getId());
		if (StringUtils.isBlank(baseToDo.getId())) {
			baseToDo.setCreUser(baseUser.getId());
			baseToDo.setCreTime(new Date());
		}
		baseToDo.setDealLastTime(new Date());
		baseToDo.setDealLastUser(baseUser.getId());
		
		baseToDo.setBelongToProvinceId(baseUser.getProvinceId());
		baseToDo.setBelongToCityId(baseUser.getCityId());
		baseToDo.setBelongToCountyId(baseUser.getCountyId());
		
		
		baseToDo.setComeFromProvinceId(baseUser.getProvinceId());
		baseToDo.setComeFromCityId(baseUser.getCityId());
		baseToDo.setComeFromCountyId(baseUser.getCountyId());
		
		save(baseToDo);
	}
	/**
	 * 
	* @Description: 待办业务类型方法 （如果result为1，则toUserId为下一步骤用户的userId,result为0，则toUserId为学分项目/资格注册／学分登记的创建者Id）
	* @author hyl     
	* @date 2017-6-20 下午7:30:20  
	* @version V1.0 
	* @author user
	 */
	public void toDoList(String objectId,String toDoType,String fromUserId,String toUserId,String url) {
		BaseUser fromBaseUser = baseUserManager.getBaseUserByUserId(fromUserId);
		BaseUser toBaseUser = baseUserManager.getBaseUserByUserId(toUserId);
		BaseToDo baseToDo = new BaseToDo();
		baseToDo.setTodoType(toDoType);
		baseToDo.setResourceParameter(objectId);
		baseToDo.setReourceUrl(url);
		baseToDo.setBelongToUserId(toBaseUser.getId());
		baseToDo.setBelongToProvinceId(toBaseUser.getProvinceId());
		baseToDo.setBelongToCityId(toBaseUser.getCityId());
		baseToDo.setBelongToCountyId(toBaseUser.getCountyId());
		if ("role.schoolManager".equals(toBaseUser.getDefaultRoleCode())) {
			baseToDo.setBelongToSchoolId(toBaseUser.getSchoolId());
		}
		if ("role.institutionManager".equals(toBaseUser.getDefaultRoleCode())) {
			baseToDo.setBelongToInstitutionId(toBaseUser.getInstitutionId());
		}
		//待办业务处理状态
		baseToDo.setComeFromProvinceId(fromBaseUser.getProvinceId());
		baseToDo.setComeFromCityId(fromBaseUser.getCityId());
		baseToDo.setComeFromCountyId(fromBaseUser.getCountyId());
		if ("role.schoolManager".equals(fromBaseUser.getDefaultRoleCode())) {
			baseToDo.setComeFromSchoolId(fromBaseUser.getSchoolId());
		}
		if ("role.institutionManager".equals(fromBaseUser.getDefaultRoleCode())) {
			baseToDo.setComeFromInstitutionId(fromBaseUser.getInstitutionId());
		}
		baseToDo.setTodoDealStatus(0);
		baseToDo.setCreUser(fromBaseUser.getId());
		baseToDo.setCreTime(new Date());
		baseToDo.setExpiredTime(DateFormater.getAfterDay(new Date(), 3));
		baseToDo.setDeleteFlag(0);
		baseToDo.setDealLastTime(new Date());
		baseToDo.setDealLastUser(fromBaseUser.getId());
		save(baseToDo);
	}
	/**
	 * 
	* @Description: 更新待办业务处理状态 ,result为处理结果，待办事项处理了就是1；objectId项目id|学分id;toDoType类型；CurrentUserId当前用户Id
	* @author hyl     
	* @date 2017-6-21 上午9:41:31  
	* @version V1.0 
	* @author user
	 */
	public void updateBaseToDoStatus(String objectId,String toDoType,String CurrentUserId) {
		Map<String, Object> columValues = new HashMap<String, Object>();
		columValues.put("TODO_DEAL_STATUS", "1");
		Map<String, Object> termValues = new HashMap<String, Object>();
		termValues.put("TODO_TYPE", toDoType);//类型
		termValues.put("RESOURCE_PARAMETER", objectId);//项目id/学分id...
		termValues.put("TODO_DEAL_STATUS", "0");
		BaseUser toBaseUser = baseUserManager.getBaseUserByUserId(CurrentUserId);
		termValues.put("BELONG_TO_PROVINCE_ID", toBaseUser.getProvinceId());
		termValues.put("BELONG_TO_CITY_ID", toBaseUser.getCityId());
		termValues.put("BELONG_TO_COUNTY_ID", toBaseUser.getCountyId());
		if ("role.schoolManager".equals(toBaseUser.getDefaultRoleCode())) {
			termValues.put("BELONG_TO_SCHOOL_ID", toBaseUser.getSchoolId());
		}
		if ("role.institutionManager".equals(toBaseUser.getDefaultRoleCode())) {
			termValues.put("BELONG_TO_INSTITUTION_ID", toBaseUser.getInstitutionId());
		}
		this.updateData(columValues, termValues, "BASE_TO_DO");
	}
	//这个是需要执行的方法
	public MMap  getMapOfBaseTeacherInfo(String menu,String fzzb,String bufferSql,String areaCode,String areaId) {

		MMap mMap = new MMap();
		
		Map<String, Object> maps = new HashMap<String, Object>();
		
		StringBuffer buffer_chaxunziduan1 = new StringBuffer("");
		
		StringBuffer buffer_groupBy1 = new StringBuffer("group by");
		
		StringBuffer buffer_chaxunziduan0 = new StringBuffer("");
		
		StringBuffer buffer_groupBy0 = new StringBuffer("group by");
		
		
		if (StringUtils.isNotBlank(areaCode)) {			
			if (areaCode.length() == 3) {
				buffer_chaxunziduan1.append(" t.city_id as areaId,");
				buffer_groupBy1.append(" t.city_id,");
				
				buffer_chaxunziduan0.append(" t.city_id as areaId,");
				
				buffer_groupBy0.append(" t.city_id");				
				maps.put("provinceId", areaId);
			}
			
			if (areaCode.length() == 6) {
				buffer_chaxunziduan1.append(" t.county_id as areaId,");
				buffer_groupBy1.append(" t.county_id,");
				
				buffer_chaxunziduan0.append(" t.county_id as areaId,");
				buffer_groupBy0.append(" t.county_id");
				
				maps.put("cityId", areaId);
			}
			if (areaCode.length() == 9) {
				maps.put("countyId", areaId);
			}
		}
		//分组方式
		if (StringUtils.isNotBlank(fzzb)) {
			if ("se_cxfl".equals(fzzb)) {
				buffer_chaxunziduan1.append("t.cxfl as zhibiao,");
				buffer_groupBy1.append(" t.cxfl");
			}
			if ("se_age".equals(fzzb)) {
				buffer_chaxunziduan1.append("t.age as zhibiao,");
				buffer_groupBy1.append(" t.age");
			}
		}
		buffer_chaxunziduan1.append("t.cxfl,sum(decode(t.xb,'XB@GJ@2',1)) as nvcnt,");
		buffer_chaxunziduan1.append("count(*) as cnt ");
		buffer_chaxunziduan0.append("t.cxfl,sum(decode(t.xb,'XB@GJ@2',1)) as nvcnt,");
		buffer_chaxunziduan0.append("count(*) as cnt ");
		
		StringBuffer buffer_tiaojian_1 = new StringBuffer("");
		StringBuffer buffer_tiaojian_0 = new StringBuffer("");
		
		buffer_tiaojian_0.append(bufferSql);
		buffer_tiaojian_1.append(bufferSql);
		
		if ("yey".equals(menu)) {//幼儿园
			//buffer_tiaojian_1.append(" and t.school_type like '1%' ");
			//buffer_tiaojian_0.append(" and t.school_type like '1%' ");
			
			buffer_tiaojian_1.append(" and t.ssxd = 'YEY' ");
			buffer_tiaojian_0.append(" and t.ssxd = 'YEY' ");
		}
		if ("zx".equals(menu)) {//中小学校
			buffer_tiaojian_1.append(" and t.ssxd = 'ZXX' ");
			buffer_tiaojian_0.append(" and t.ssxd = 'ZXX' ");
		}
		if ("te".equals(menu)) {//特教学校
			buffer_tiaojian_1.append(" and t.ssxd = 'TJ' ");
			buffer_tiaojian_0.append(" and t.ssxd = 'TJ' ");
		}
		if ("zz".equals(menu)) {//中职学校
			buffer_tiaojian_1.append(" and t.ssxd = 'ZZ' ");
			buffer_tiaojian_0.append(" and t.ssxd = 'ZZ' ");
		}
		if ("gz".equals(menu)) {//高职专科
			buffer_tiaojian_1.append(" and t.ssxd = 'GZ' ");
			buffer_tiaojian_0.append(" and t.ssxd = 'GZ' ");
		}
		maps.put("chaxunziduan", buffer_chaxunziduan1.toString());
		maps.put("tiaojian", buffer_tiaojian_1.toString());
		maps.put("groupBy", buffer_groupBy1.toString());
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = getNamedQuery("misBase::statBaseTeacherInfoChart::query", maps);
		
		
		Map<String, Integer> mapChart = new HashMap<String, Integer>();
		Map<String, Integer> mapAreaZhibiao = new HashMap<String, Integer>();
		Map<String, Integer> mapArea = new HashMap<String, Integer>();
		
		Integer allCnt = 0;		
		for (Map<String, Object> map : list) {
		   String areaIds = MMap.isnullStr(map.get("AREAID"));
		   String zhibiaos =  MMap.isnullStr(map.get("ZHIBIAO"));
		    if (StringUtils.isBlank(zhibiaos)) {
			      zhibiaos = "nullkey";
			   }
			    String cnts = MMap.isnullStr(map.get("CNT"));
			    allCnt = allCnt + MMap.isnullInt(cnts);
				mapChart = MMap.setMapValuesOfInteger(mapChart, zhibiaos, cnts);
				mapAreaZhibiao = MMap.setMapValuesOfInteger(mapAreaZhibiao, areaIds+"_"+zhibiaos, cnts);
				mapArea = MMap.setMapValuesOfInteger(mapArea, areaIds, cnts);
				}
		mMap.setObj(mapChart);
		mMap.setObj1(mapAreaZhibiao);
		mMap.setObj2(mapArea);
		mMap.setObj3(allCnt);
		return mMap;
	
	}
}
