/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package org.my431.base.services;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.my431.base.model.BaseKpiGroup;
import org.my431.base.model.BaseKpiItem;
import org.my431.base.model.BaseSchool;
import org.my431.base.model.BaseUser;
import org.my431.platform.dao.support.Page;
import org.my431.plugin.redis.services.RedisManager;
import org.my431.util.MMap;

@Repository
public class BaseKpiGroupManager extends
		HibernateXsqlBuilderQueryDao<BaseKpiGroup> {

	@Autowired
	private RedisManager redisManager;

	public Class getEntityClass() {
		return BaseKpiGroup.class;
	}

	public Page findPage(BaseKpiGroup query, int pageSize, int pageNo) {
		// XsqlBuilder syntax,please see
		// http://code.google.com/p/rapid-xsqlbuilder
		// [column]为字符串拼接, {column}为使用占位符.
		// [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接
		// [column] 为PageRequest的属性
		String sql = "select t from BaseKpiGroup t where 1=1 "
				+ "/~ and t.tableName = {tableName} ~/"
				+ "/~ and t.kpiGroupName = {kpiGroupName} ~/"
				+ "/~ and t.valideItemCondition = {valideItemCondition} ~/"
				+ "/~ and t.useFlag = {useFlag} ~/"
				+ "/~ and t.pointYearColName = {pointYearColName} ~/"
				+ "/~ and t.pointTermColName = {pointTermColName} ~/"
				+ "/~ order by [sortColumns] ~/";

		Map filters = new HashMap();

		XsqlBuilder xsqlBuilder = new XsqlBuilder();

		/*
		 * if(isNotEmpty(query.getKpiGrpId())) { filters.put("kpiGrpId",
		 * query.getKpiGrpId()); }
		 */
		if (isNotEmpty(query.getTableName())) {
			filters.put("tableName", query.getTableName());
		}
		if (isNotEmpty(query.getKpiGroupName())) {
			filters.put("kpiGroupName", query.getKpiGroupName());
		}
		if (isNotEmpty(query.getValideItemCondition())) {
			filters.put("valideItemCondition", query.getValideItemCondition());
		}
		if (isNotEmpty(query.getUseFlag())) {
			filters.put("useFlag", query.getUseFlag());
		}
		if (isNotEmpty(query.getPointYearColName())) {
			filters.put("pointYearColName", query.getPointYearColName());
		}
		if (isNotEmpty(query.getPointTermColName())) {
			filters.put("pointTermColName", query.getPointTermColName());
		}

		XsqlFilterResult result = xsqlBuilder.generateHql(sql, filters);

		return pageQuery(result.getXsql(), pageNo, pageSize,
				result.getAcceptedFilters());
	}

	/**
	 * 初始化
	 */
	public void init() {
		getKpiGroupListRedis();
	}

	// 查询所有的kpi group以及item
	public void getKpiGroupListRedis() {
		Session session = this.getSession();
		// groupList
		Criteria cr = session.createCriteria(BaseKpiGroup.class);
		cr.add(Restrictions.eq("useFlag", 1));
		List<BaseKpiGroup> listGroup = cr.list();
		redisManager.setOValue("baseKpiGroupList", listGroup);
		if (listGroup.size() > 0) {
			// ItemList
			List<BaseKpiItem> baseKpiItems = new ArrayList<BaseKpiItem>();
			for (Iterator iterator = listGroup.iterator(); iterator.hasNext();) {
				BaseKpiGroup baseKpiGroup = (BaseKpiGroup) iterator.next();
				// group
				redisManager.setOValue(
						"baseKpiGroup" + baseKpiGroup.getTableName(),
						baseKpiGroup);
				cr = session.createCriteria(BaseKpiItem.class);
				cr.add(Restrictions.eq("tableName", baseKpiGroup.getTableName()));
				cr.add(Restrictions.eq("useFlag", 1));
				baseKpiItems = cr.list();
				redisManager.setOValue(
						"baseKpiItem" + baseKpiGroup.getTableName(),
						baseKpiItems);
			}
		}

	}

	/**
	 * 查询kpi item 的属性list
	 * 
	 * @param map
	 * @return
	 */
	public List getKpiItemChildList(Map<String, Object> map) {
		List list = (List) getNamedQuery(
				"misBase::BaseKpiGroup::getKpiItemChildList", map);
		return list;
	}

	/**
	 * 查询各个条件下老师的数量
	 * 
	 * @param map
	 *            是否带查询某个职位条件：(xrgwlbContent)XRGWLB = 'GWLB@GJ@3'//管理岗位 XRGWLB =
	 *            'GWLB@GJ@1'//教师岗位
	 * @return
	 */
	/*
	 * @SuppressWarnings("unchecked") public List getTeacherCount(Map<String,
	 * Object> map){ List<Map<String,Object>> list = new
	 * ArrayList<Map<String,Object>>(); //查询所有老师的数量 map.put("table",
	 * "TB_BIZ_JZGJBXX"); List<Map<String,Object>> list1 = new
	 * ArrayList<Map<String,Object>>(); list1 = (List)
	 * getNamedQuery("misBase::BaseKpiGroup::getTeacherCount", map); list.add(
	 * list1.get(0)); //查询教师岗位的数量 map = new HashMap<String, Object>();
	 * map.put("table", "TB_BIZ_JZGJBXX"); map.put("xrgwlbContent",
	 * "GWLB@GJ@1"); list1 = (List)
	 * getNamedQuery("misBase::BaseKpiGroup::getTeacherCount", map); list.add(
	 * list1.get(0)); //查询管理岗位的数量 map = new HashMap<String, Object>();
	 * map.put("table", "TB_BIZ_JZGJBXX"); map.put("xrgwlbContent",
	 * "GWLB@GJ@3"); list1 = (List)
	 * getNamedQuery("misBase::BaseKpiGroup::getTeacherCount", map); list.add(
	 * list1.get(0));
	 * 
	 * redisManager.setOValue("baseKpiPeopleCountList", list); return list; }
	 */

	/**
	 * 查询各个条件下老师的数量
	 * 
	 * @param map
	 *            是否带查询某个职位条件：(xrgwlbContent)XRGWLB = 'GWLB@GJ@3'//管理岗位 XRGWLB =
	 *            'GWLB@GJ@1'//教师岗位
	 * @return
	 */
	public Map<String, Object> getTeacherCount(Map<String, Object> map) {
		// 地区管理员判断
		BaseUser baseUser = (BaseUser) map.get("baus");
		boolean schoolManager = (Boolean) map.get("schoolManager");
		if(schoolManager == true){
			String schoolId = (String) map.get("schoolId");
			if(StringUtils.isNotBlank(schoolId)){
				map.put("XXJGID",schoolId);
			}
		}else{
			if (StringUtils.isNotBlank(baseUser.getProvinceId())) {
				map.put("provinceId", baseUser.getProvinceId());
			}
			if (StringUtils.isNotBlank(baseUser.getCityId())) {
				map.put("cityId", baseUser.getCityId());
			}
			if (StringUtils.isNotBlank(baseUser.getCountyId())) {
				map.put("countyId", baseUser.getCountyId());
			}
		}
		List list = (List) getNamedQuery("misBase::BaseKpiGroup::getTeacherCount",map);
		Map<String, Object> mapObj = new HashMap<String, Object>();
		BigDecimal allCount = new BigDecimal("0");
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> mm = (Map<String, Object>) iterator.next();
			if(StringUtils.isNotBlank((String) mm.get("XRGWLB"))){
				if("GWLB@GJ@1".equals(mm.get("XRGWLB"))){
					mapObj.put("tPeopleCount", mm.get("NUM"));
				}else if("GWLB@GJ@3".equals(mm.get("XRGWLB"))){
					mapObj.put("mPeopleCount", mm.get("NUM"));
				}
			}else{
				mapObj.put("null", mm.get("NUM"));
			}
			allCount = allCount.add((BigDecimal) mm.get("NUM"));
		}
		mapObj.put("allCount", allCount);
		
		return mapObj;
	}

	/**
	 * 通过条件查询各个种类老师的数量(属性下的自己选择的类别的数量) table : groupName valideItem:需要的条件
	 * itemChild： item+itemChild
	 * 
	 * @param map
	 * @return
	 */
	public BigDecimal getTeacherCountOne(Map<String, Object> map) {
		// 地区管理员判断
		BaseUser baseUser = (BaseUser) map.get("baus");
		boolean schoolManager = (Boolean) map.get("schoolManager");
		if(schoolManager == true){
			String schoolId = (String) map.get("schoolId");
			if(StringUtils.isNotBlank(schoolId)){
				map.put("XXJGID",schoolId);
			}
		}else{
			if (StringUtils.isNotBlank(baseUser.getProvinceId())) {
				map.put("provinceId", baseUser.getProvinceId());
			}
			if (StringUtils.isNotBlank(baseUser.getCityId())) {
				map.put("cityId", baseUser.getCityId());
			}
			if (StringUtils.isNotBlank(baseUser.getCountyId())) {
				map.put("countyId", baseUser.getCountyId());
			}
		}

		BigDecimal bigDecimal = new BigDecimal("0");
		List list = null;
		list = (List) getNamedQuery("misBase::BaseKpiGroup::getTeacherCountOne", map);
		if (list.size() > 0) {
			Map<String, Object> mapObj = (Map<String, Object>) list.get(0);
			bigDecimal = (BigDecimal) mapObj.get("NUM");
		}
		return bigDecimal;
	}

	/**
	 * 未填报的
	 * @param map
	 * @return
	 */
	public BigDecimal getTeacherCountOneWTB(Map<String, Object> map) {
		// 地区管理员判断
		BaseUser baseUser = (BaseUser) map.get("baus");
		boolean schoolManager = (Boolean) map.get("schoolManager");
		if(schoolManager == true){
			String schoolId = (String) map.get("schoolId");
			if(StringUtils.isNotBlank(schoolId)){
				map.put("XXJGID",schoolId);
			}
		}else{
			if (StringUtils.isNotBlank(baseUser.getProvinceId())) {
				map.put("provinceId", baseUser.getProvinceId());
			}
			if (StringUtils.isNotBlank(baseUser.getCityId())) {
				map.put("cityId", baseUser.getCityId());
			}
			if (StringUtils.isNotBlank(baseUser.getCountyId())) {
				map.put("countyId", baseUser.getCountyId());
			}
		}

		BigDecimal bigDecimal = new BigDecimal("0");
		List list = null;
		list = (List) getNamedQuery("misBase::BaseKpiGroup::getTeacherCountOneWTB", map);
		if (list.size() > 0) {
			Map<String, Object> mapObj = (Map<String, Object>) list.get(0);
			bigDecimal = (BigDecimal) mapObj.get("NUM");
		}
		return bigDecimal;
	}
	
	public Map<Object, MMap> getAreaTeacherCount(Map<String, Object> map) {
		String allItemChild = (String) map.get("allItemChild");
		Set<String> allItemChildSet = new HashSet<String>(Arrays.asList(allItemChild.split(",")));
		Map<Object, MMap> mapArea = new HashMap<Object, MMap>();
		MMap mmap = null;
		List listLeft = (List) getNamedQuery(
				"misBase::BaseKpiGroup::getAreaTeacherCountLeft", map);
		List listRight = null;
		if("1".equals(map.get("kpisfenum"))){
			listRight = (List) getNamedQuery(
					"misBase::BaseKpiGroup::getAreaTeacherCountRight", map);
		}else{
			listRight = (List) getNamedQuery(
					"misBase::BaseKpiGroup::getAreaTeacherCountRightIsNull", map);
		}
		List listAreaWTB = (List) getNamedQuery(
				"misBase::BaseKpiGroup::getTeacherCountAreaWTB", map);
		// 左侧的数据
		for (Iterator iterator = listLeft.iterator(); iterator.hasNext();) {
			Map<String, Object> mm = (Map<String, Object>) iterator.next();
			mmap = new MMap();
			String areaId = (String) mm.get("AREAID");
			if(areaId == null){
				areaId = "null";
			}
			if (mapArea.keySet().contains(areaId)) {
				// 已经存在该地区，先获取该地区
				mmap = mapArea.get(areaId);
			}
			// 不存在则新增操作 xrgwlb 0 空 1 教师岗位，2，3管理岗位，4，5 , 6 总人数
			if (StringUtils.isNotBlank((String) mm.get("XRGWLB"))) {
				if ("GWLB@GJ@1".equals(mm.get("XRGWLB"))) {
					mmap.setObj1(mm.get("NUM"));
				} else if ("GWLB@GJ@2".equals(mm.get("XRGWLB"))) {
					mmap.setObj2(mm.get("NUM"));
				} else if ("GWLB@GJ@3".equals(mm.get("XRGWLB"))) {
					mmap.setObj3(mm.get("NUM"));
				} else if ("GWLB@GJ@4".equals(mm.get("XRGWLB"))) {
					mmap.setObj4(mm.get("NUM"));
				} else if ("GWLB@GJ@5".equals(mm.get("XRGWLB"))) {
					mmap.setObj5(mm.get("NUM"));
				}
			} else {
				mmap.setObj0(mm.get("NUM"));
			}
			//计算总教师人数
			BigDecimal allPeople = (BigDecimal) mmap.getObj6(); 
			if(allPeople == null){
				allPeople = new BigDecimal("0");
			}
			allPeople = allPeople.add((BigDecimal) mm.get("NUM"));
			mmap.setObj6(allPeople);
			mapArea.put(areaId, mmap);
		}

		// 右侧的数据
		if("1".equals(map.get("kpisfenum"))){
			for (Iterator iterator = listRight.iterator(); iterator.hasNext();) {
				Map<String, Object> mm = (Map<String, Object>) iterator.next();
				mmap = new MMap();
				String areaId = (String) mm.get("AREAID");
				if(areaId == null){
					areaId = "null";
				}
				if (mapArea.keySet().contains(areaId)) {
					// 已经存在该地区，先获取该地区
					mmap = mapArea.get(areaId);
				}
				//NULL 空，未填报  OTHER 错误 
				Map<String, Object> m = null;
				if(mmap.getObj() != null){
					m = (Map<String, Object>) mmap.getObj();
				}else{
					m = new HashMap<String, Object>();
				}
				if(m.get("未知") == null){
					m.put("未知", new BigDecimal("0"));
				}
				if (StringUtils.isNotBlank((String) mm.get("QUERYCOL"))) {
					if(!allItemChildSet.contains(mm.get("QUERYCOL"))){
						m.put("未知", mm.get("NUM"));
					}else{
						m.put((String) mm.get("QUERYCOL"), mm.get("NUM"));
					}
				} else {
					//m.put("未填报", mm.get("NUM"));
					m.put("未知", mm.get("NUM"));
				}
				BigDecimal allPeople = (BigDecimal)mmap.getObj6();
				m.put("已填报",allPeople.subtract((BigDecimal)mm.get("NUM")));
				mmap.setObj(m);
				mapArea.put(areaId, mmap);
			}
		}else{
			for (Iterator iterator = listRight.iterator(); iterator.hasNext();) {
				Map<String, Object> mm = (Map<String, Object>) iterator.next();
				mmap = new MMap();
				String areaId = (String) mm.get("AREAID");
				if(areaId == null){
					areaId = "null";
				}
				if (mapArea.keySet().contains(areaId)) {
					// 已经存在该地区，先获取该地区
					mmap = mapArea.get(areaId);
				}
				//NULL 空，未填报  OTHER 错误 
				Map<String, Object> m = null;
				if(mmap.getObj() != null){
					m = (Map<String, Object>) mmap.getObj();
				}else{
					m = new HashMap<String, Object>();
				}
				if(m.get("已填报") == null){
					m.put("已填报", new BigDecimal("0"));
				}
				if (StringUtils.isNotBlank((String) mm.get("QUERYCOL"))) {
					if(!allItemChildSet.contains(mm.get("QUERYCOL"))){
						m.put("已填报", mm.get("NUM"));
					}else{
						m.put((String) mm.get("QUERYCOL"), mm.get("NUM"));
					}
				} else {
					//m.put("未填报", mm.get("NUM"));
					m.put("已填报", mm.get("NUM"));
				}
				mmap.setObj(m);
				mapArea.put(areaId, mmap);
			}
		}
		
		
		//地区未填报的数据
		for (Iterator iterator = listAreaWTB.iterator(); iterator.hasNext();) {
			Map<String, Object> mm = (Map<String, Object>) iterator.next();
			mmap = new MMap();
			String areaId = (String) mm.get("AREAID");
			if(areaId == null){
				areaId = "null";
			}
			if (mapArea.keySet().contains(areaId)) {
				// 已经存在该地区，先获取该地区
				mmap = mapArea.get(areaId);
			}
			Map<String, Object> m = null;
			if(mmap.getObj() != null){
				m = (Map<String, Object>) mmap.getObj();
			}else{
				m = new HashMap<String, Object>();
			}
			m.put("未填报", mm.get("NUM"));
			mmap.setObj(m);
			mapArea.put(areaId, mmap);
		}

		return mapArea;

	}
}
