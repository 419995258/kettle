/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2015
 */

package org.my431.base.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.my431.base.model.BaseArea;
import org.my431.base.model.BaseProperties;
import org.my431.base.model.BaseSchool;
import org.my431.base.model.StatLoginData;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.my431.taglib.My431Function;
import org.my431.util.DateFormater;
import org.my431.util.HibernateToolsUtil;
import org.my431.util.MapToObject;
import org.my431.util.StringUtil;
import org.springframework.stereotype.Repository;

import net.sourceforge.jeval.Evaluator;

@Repository
@SuppressWarnings("unchecked")
public class StatSchoolManager extends HibernateXsqlBuilderQueryDao<BaseSchool>{

	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public Class getEntityClass() {
		return StatLoginData.class;
	}

	/**
	 * 获得两个map相加,值为Integer 或者Double
	 * @author 90    
	 * @param map
	 * @param map0
	 * @return
	 * @date 2015年9月17日
	 */
	public Map<String,Object> getAddMap(Map<String,Object> map,Map<String,Object> map0){
		//Map<String,Object> mapRlt=new HashMap<String, Object>();
		if(!map0.isEmpty()){
			for(Map.Entry<String, Object> entry:map0.entrySet()){
				String key=entry.getKey();
				if(!key.equals("CITY_ID")&&!key.equals("COUNTY_ID")&&!key.equals("SCHOOL_ID")){
					Double value=entry.getValue()==null? 0: Double.valueOf(entry.getValue().toString());
					Double value0=0.0;
					if(map==null){
						map=new HashMap<String,Object>();
					}
					
					if(map.containsKey(key)){
						value0=map.get(key)==null? 0: Double.valueOf(map.get(key).toString());
						map.put(key, value+value0);
					}else{
						map.put(key, value);
					}
				}
				
				
			}
		}
		
		return map;
	}

	

	/**
	 * 取得分组的结果
	 * @author 90    
	 * @param list
	 * @return
	 * @date 2015年9月21日
	 */
	public Map<String, Object> getResultGroupById(List<Map<String, Object>>  list){
		Map<String, Object> map=new HashMap<String, Object>();
		if(list!=null){
			for (int i = 0; i <list.size(); i++) {
				Map<String, Object> mapRlt=list.get(i);
				if(mapRlt.get("ID")!=null){
					map.put(mapRlt.get("ID").toString(), mapRlt.get("RLT_CNT"));
				}
				
			}
		}
		return map;
	}

	public Page getAllSchoolList(String areaCode,String querySchoolName, String queryField,String[] queryFieldDbs,String[] queryFieldWdbs, String[] querySchoolTypes, String[] querySchoolJsxss, String[] querySchoolCxflbms, String[] querySchoolBjgms, String[] querySchoolPxbsls, String[] querySchoolXsgms,Integer pageNo,Integer pageSize) {
		Map<String,String> map=new HashMap<String,String>();
		
		map.put("selectStr", "t.* ,b.school_name,b.AREA_ID,b.primary_teacher_num,b.junior_teacher_num,b.primary_student_num,b.junior_student_num,b.teacher_num");
		
		StringBuffer whereStr=new StringBuffer();
		whereStr.append(" where t.school_id=b.school_id");
		
//		if(queryField.equals("all")){
//			whereStr.append(" and t.is_db='"+1+"'");
//		}else if(queryField.startsWith("item_type")){
//			whereStr.append(" and t.is_db_"+queryField.substring("item_type_".length(), queryField.length())+"='"+1+"'");
//		}else{
//			if(queryField.startsWith("S_")){
//				queryField=queryField.substring("S_".length(),queryField.length());
//			}
//			queryField=queryField+"_GAP";
//			whereStr.append(" and t."+queryField+">="+0+"");
//		}
		
		if(queryFieldDbs!=null&&queryFieldDbs.length>0){
			if(queryFieldDbs[0]==null||queryFieldDbs[0].equals("")){
				queryFieldDbs=null;
			}else{
				int i=0;
				for(String queryFieldDb:queryFieldDbs){
					if(queryFieldDb!=null&&!queryFieldDb.equals("")){
						whereStr.append(" and ");
						if(queryFieldDb.equals("all")){
							whereStr.append("t.is_db='"+1+"'");
						}else if(queryFieldDb.startsWith("item_type")){
							whereStr.append("t.is_db_"+queryFieldDb.substring("item_type_".length(), queryFieldDb.length())+"='"+1+"'");
						}else{
							if(queryFieldDb.startsWith("S_")){
								queryFieldDb=queryFieldDb.substring("S_".length(),queryFieldDb.length());
							}
							queryFieldDb=queryFieldDb+"_GAP";
							whereStr.append("t."+queryFieldDb+">="+0+"");
						}
						i=i+1;
					}
				}
			}
		}
		
		if(queryFieldWdbs!=null&&queryFieldWdbs.length>0){
			int i=0;
			for(String queryFieldWdb:queryFieldWdbs){
				whereStr.append(" and ");
				if(queryFieldWdb.equals("all")){
					whereStr.append("t.is_db<>'"+1+"'");
				}else if(queryFieldWdb.startsWith("item_type")){
					whereStr.append("t.is_db_"+queryFieldWdb.substring("item_type_".length(), queryFieldWdb.length())+"<>'"+1+"'");
				}else{
					if(queryFieldWdb.startsWith("S_")){
						queryFieldWdb=queryFieldWdb.substring("S_".length(),queryFieldWdb.length());
					}
					queryFieldWdb=queryFieldWdb+"_GAP";
					whereStr.append("t."+queryFieldWdb+"<"+0+"");
				}
				i=i+1;
			}
		}
		
		if(querySchoolTypes!=null&&querySchoolTypes.length>0){
			int i=0;
			whereStr.append(" and (");
			for(String querySchoolType:querySchoolTypes){
				if(i!=0){
					whereStr.append(" or ");
				}
				whereStr.append("t.school_type='"+querySchoolType+"'");
				i=i+1;
			}
			whereStr.append(")");
		}
		if(querySchoolJsxss!=null&&querySchoolJsxss.length>0){
			int i=0;
			whereStr.append(" and (");
			for(String querySchoolJsxs:querySchoolJsxss){
				if(i!=0){
					whereStr.append(" or ");
				}
				whereStr.append("t.JS_TYPE='"+querySchoolJsxs+"'");
				i=i+1;
			}
			whereStr.append(")");
		}
		if(querySchoolCxflbms!=null&&querySchoolCxflbms.length>0){
			int i=0;
			whereStr.append(" and (");
			for(String querySchoolCxflbm:querySchoolCxflbms){
				if(i!=0){
					whereStr.append(" or ");
				}
				whereStr.append("t.CX_TYPE='"+querySchoolCxflbm+"'");
				i=i+1;
			}
			whereStr.append(")");
		}
		if(querySchoolBjgms!=null&&querySchoolBjgms.length>0){
			int i=0;
			whereStr.append(" and (");
			for(String querySchoolBjgm:querySchoolBjgms){
				if(i!=0){
					whereStr.append(" or ");
				}
				whereStr.append("t.BJGM='"+querySchoolBjgm+"'");
				i=i+1;
			}
			whereStr.append(")");
		}
		if(querySchoolPxbsls!=null&&querySchoolPxbsls.length>0){
			int i=0;
			whereStr.append(" and (");
			for(String querySchoolPxbsl:querySchoolPxbsls){
				if(i!=0){
					whereStr.append(" or ");
				}
				whereStr.append("t.PXBSL='"+querySchoolPxbsl+"'");
				i=i+1;
			}
			whereStr.append(")");
		}
		if(querySchoolXsgms!=null&&querySchoolXsgms.length>0){
			int i=0;
			whereStr.append(" and (");
			for(String querySchoolXsgm:querySchoolXsgms){
				if(i!=0){
					whereStr.append(" or ");
				}
				whereStr.append("t.XSGM='"+querySchoolXsgm+"'");
				i=i+1;
			}
			whereStr.append(")");
		}
		
		BaseArea area=CacheBaseAreaManager.getNodeByCode(areaCode);
		if(areaCode.length()==3){
			//当前地区 省
			whereStr.append(" and t.province_id='"+area.getId()+"'");
		}
		if(areaCode.length()==6){
			//当前地区 市
			whereStr.append(" and t.city_id='"+area.getId()+"'");
		}
		if(areaCode.length()==9){
			//当前地区 县
			whereStr.append(" and t.county_id='"+area.getId()+"'");
		}
		if(querySchoolName!=null && !querySchoolName.equals("")){
			whereStr.append(" and b.school_name like '%"+querySchoolName+"%'");
		}
		map.put("whereStr", whereStr.toString());
		return this.getPagedNamedQuery("misBase::getSchoolListPage::query", pageNo, pageSize, map);
	}


	/**
	 * 所有学校 及 学生统计
	 * @param area
	 * @return
	 */
	public List<Map<String, Object>> statSchoolStudentAll(BaseArea area) {
		Map<String,String> map=new HashMap<String,String>();
		if(area.getAreaCode().length()<=3){
			map.put("provinceId", area.getId());
		}
		if(area.getAreaCode().length()==6){
			map.put("cityId", area.getId());
		}
		if(area.getAreaCode().length()==9){
			map.put("countryId", area.getId());
		}
		return getNamedQuery("misBase::statSchoolStudentAll::query", map);
	}


	
	/**
	 * map工具
	 * @param map
	 * @param key
	 * @param value
	 * @return
	 */
	public Map<String, Double> setMapValues(Map<String, Double> map, String key, Double value) {
		if(map.containsKey(key)){
			value=value+map.get(key);
		}
		map.put(key, value);
		return map;
	}


	/**
	 * 县级项目学校数
	 * @return
	 */
	public Map<String, Object> getBxHuiZongProSchoolCounty(){
		Map<String,String> map=new HashMap<String,String>();
		map.put("groupByLimit", "group by COUNTY_ID ");
		map.put("selectAreaId", " ,COUNTY_ID  AS ID ");
		List<Map<String, Object>> list= this.getNamedQuery("misBase::getBxHuiZongProSchool::query",map);
		return this.getResultGroupById(list);
	}
	

	
	public String getEval(String eval,Map<String,String> values){
		String pk=null;
		Evaluator eva = new Evaluator();
		try {
			for (Map.Entry<String, String> entry : values.entrySet()) {
				eva.putVariable(entry.getKey(), entry.getValue());
			}
			pk=eva.evaluate(eval);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(pk==null){
			pk="0";
		}
		BigDecimal mData = new BigDecimal(pk).setScale(4, BigDecimal.ROUND_HALF_UP);
		return mData.toPlainString();
	}
	
	/**
	 * 请空所有表中的数据
	 * @return
	 */
	public String clearTableInfo(String tableName)
	{
		String sql= "delete "+tableName;
		Session session = this.getSession(true);
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
		this.releaseSession(session);
		return "success";
	}
	
	
	/**归档*/
	public void bakStatLoginData() {
		String nowYear=DateFormater.DateToString(DateFormater.getAfterDay(new Date(), -1), "yyyy_MM");
		nowYear=nowYear.substring(2, nowYear.length());
		Session session = this.getSession();
		String sql= "create table STAT_LOGIN_DATA_"+nowYear+" as select * from STAT_LOGIN_DATA";
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
		this.releaseSession(session);
		
	}
	/**BASE_TEACHER_CREDITS_SUM归档*/
	public void bakBaseTeacherCreditsSum() {
		String nowYear=DateFormater.DateToString(DateFormater.getAfterDay(new Date(), -1), "yyyy_MM");
		nowYear=nowYear.substring(2, nowYear.length());
		Session session = this.getSession();
		String sql= "create table BASE_TEACHER_CREDITS_SUM_"+nowYear+" as select * from BASE_TEACHER_CREDITS_SUM";
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
		this.releaseSession(session);
		
	}
	/**STAT_BASE_TEACHER_INFO归档*/
	public void bakStatBaseTeacherInfo() {
		String nowYear=DateFormater.DateToString(DateFormater.getAfterDay(new Date(), -1), "yyyy_MM");
		nowYear=nowYear.substring(2, nowYear.length());
		Session session = this.getSession();
		String sql= "create table STAT_BASE_TEACHER_INFO_"+nowYear+" as select * from STAT_BASE_TEACHER_INFO";
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
		this.releaseSession(session);
	}
	/**BASE_SCHOOL归档*/
	public void bakBaseSchool() {
		String nowYear=DateFormater.DateToString(DateFormater.getAfterDay(new Date(), -1), "yyyy_MM");
		nowYear=nowYear.substring(2, nowYear.length());
		Session session = this.getSession();
		String sql= "create table BASE_SCHOOL_"+nowYear+" as select * from BASE_SCHOOL";
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
		this.releaseSession(session);
	}
	/**BASE_SCHOOL_FSB归档*/
	public void bakBaseSchoolFsb() {
		String nowYear=DateFormater.DateToString(DateFormater.getAfterDay(new Date(), -1), "yyyy_MM");
		nowYear=nowYear.substring(2, nowYear.length());
		Session session = this.getSession();
		String sql= "create table BASE_SCHOOL_FSB_"+nowYear+" as select * from BASE_SCHOOL_FSB";
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
		this.releaseSession(session);
	}
	/**STAT_JSGDYXX_SUM  教职工基本待遇统计表归档*/
	public void bakStatJsgdyxxSum() {
		String nowYear=DateFormater.DateToString(DateFormater.getAfterDay(new Date(), -1), "yyyy_MM");
		nowYear=nowYear.substring(2, nowYear.length());
		Session session = this.getSession();
		String sql= "create table STAT_JSGDYXX_SUM_"+nowYear+" as select * from STAT_JSGDYXX_SUM";
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
		this.releaseSession(session);
	}
	/**
	 * 将map对象转换为Object
	 * author  90  
	 * on 2016-3-29
	 * @param Id
	 * @param tableName
	 * @param limit
	 * @return
	 */
	public <T> T  getMap2Object(Class thisClass,String tableName,String limit){
		T t = null;
		try {
			t = (T) thisClass.newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Map<String,String> map=new HashMap<String,String>();
		map.put("tableName", tableName);
		map.put("limit", limit);
		Object w2 =null;
		List<Map<String,Object>> list=getNamedQuery("misBase::getMap2Object::query",map);
		Map<String, String> columnMap=HibernateToolsUtil.getFields(thisClass);
		if(list!=null&&list.size()>0){
	        Map<String,Object> mapRlt= list.get(0);
			for(String key : columnMap.keySet()){
				String tableZd=columnMap.get(key);//数据库中的字段 ； key 是实体中的字段
				mapRlt.put(key, mapRlt.get(tableZd));
				mapRlt.remove(tableZd);
			}
			try {
				 t =  (T) MapToObject.doWrapper(thisClass, mapRlt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return t;
	}
	
	
	/**
	 * 
	 * author  90  
	 * on 2016-6-29
	 * @param type 属性组名
	 * @param typeStrs  属性key 列表
	 * @return 属性名称的字符串
	 */
	public String getPropertiesValueByType(String type,String [] typeStrs){
		String queryNameStr="";
		if(typeStrs!=null&&typeStrs.length>0){
			String queryStr=StringUtil.StringArray2String(typeStrs);
			List<BaseProperties> rowList=My431Function.getPropertiesByGroupKey(type);
			for(BaseProperties row:rowList){
				if(queryStr.contains(row.getPropertyKey())){
					if(queryNameStr.equals("")){
						queryNameStr=row.getPropertyValue();
					}else{
						queryNameStr=queryNameStr+","+row.getPropertyValue();
					}
				}
			}
		}
		return queryNameStr;
	}
	

	
	
}
