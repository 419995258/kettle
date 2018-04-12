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


import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import oracle.net.aso.s;

import org.hibernate.Session;
import org.my431.base.model.BaseCreditProject;
import org.my431.base.model.BaseProTeacherMap;
import org.my431.base.model.BaseProperties;
import org.my431.base.model.BaseTeacherCreditsSum;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.my431.platform.utils.ContextUtils;
import org.my431.util.DateFormater;
import org.my431.util.FileUtil;
import org.my431.util.MMap;
import org.springframework.stereotype.Repository;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

@Repository
public class BaseTeacherCreditsSumManager extends HibernateXsqlBuilderQueryDao<BaseTeacherCreditsSum>{

	public Class getEntityClass() {
		return BaseTeacherCreditsSum.class;
	}
	/**
	 * 
	* @Description: 将学分信息导入到BASE_TEACHER_CREDITS_SUM表中
	* @author hyl     
	* 教师id和年度确定一条记录，每个老师一个年度只能有一条记录
	 */
	public void init() {
		executeUpdateByName("misbase::BaseTeacherCreditsSum::delete", null);
		//培训类别
		List<BaseProperties> pxjbList = CacheBasePropertiesManager.getPropertiesByGroupKey("PXJB");
		if (MMap.validateList(pxjbList)) {
			for (BaseProperties baseProperties : pxjbList) {
				String pkey = baseProperties.getPropertyKey();
				//机构
				Map<String, Object> maps = new HashMap<String, Object>();
				maps.put("chaxunziduan", " t.jsid,t.pxnd,sum(nvl(t.pxxs2,0)) as xs,sum(nvl(t.pxhdxf,0)) as xf ");
				maps.put("pxjb", pkey);
				//培训方式
				maps.put("otherQuery", " and (t.Pxxs='PXXS@GJ@1' or t.Pxxs='PXXS@GJ@2') and t.pxnd is not null ");
				maps.put("groupBy", " group by t.jsid,t.pxnd ");
				List<Map<String, Object>>  listJiGou = getNamedQuery("misbase::queryTbBizJzggnpxxxList::query", maps);
				if (MMap.validateList(listJiGou)) {//a3
					for (Map<String, Object> map : listJiGou) {
						String jsid = map.get("JSID").toString();
						String pxnd = map.get("PXND").toString();
						String xs = map.get("XS").toString();
						String xf = map.get("XF").toString();
						Map<String, Object> colmunMap = new HashMap<String, Object>();
						if ("PXJB@GJ@10".equals(pkey)) {//国家级
							colmunMap.put("JG_QZ_NATL_HOURS", xs);//机构国培学时数
							colmunMap.put("JG_QZ_NATL_CREDITS", xf);
						}
						if ("PXJB@GJ@20".equals(pkey)) {//省级
							colmunMap.put("JG_QZ_PRCE_HOURS", xs);//机构省培学时数
							colmunMap.put("JG_QZ_RRCE_CREDITS", xf);
						}
						if ("PXJB@GJ@30".equals(pkey)) {//地市级
							colmunMap.put("JG_QZ_CITY_HOURS", xs);//机构市培学时数
							colmunMap.put("JG_QZ_CITY_CREDITS", xf);
						}
						if ("PXJB@GJ@40".equals(pkey)) {//县级
							colmunMap.put("JG_QZ_CNTY_HOURS", xs);//机构县培学时数
							colmunMap.put("JG_QZ_CNTY_CREDITS", xf);
						}
						if ("PXJB@GJ@60".equals(pkey)) {//校级
							colmunMap.put("JG_QZ_SCHOOL_HOURS", xs);//
							colmunMap.put("JG_QZ_SCHOOL_CREDITS", xf);
						}
						if ("PXJB@GJ@70".equals(pkey)) {//自主研修	
							colmunMap.put("JG_QZ_SELF_HOURS", xs);
							colmunMap.put("JG_QZ_SELF_CREDITS", xf);
						}
						if ("PXJB@GJ@99".equals(pkey)) {//其他
							colmunMap.put("JG_QZ_OTHER_HOURS", xs);
							colmunMap.put("JG_QZ_OTHER_CREDITS", xf);
						}
						//省市县不加，直接从stat_base_teacher_info 中取值
						//教师ID和年度联合查记录
						Map<String,Object> values=new HashMap<String,Object>();
						values.put("teacherId", jsid);
						values.put("tyear", pxnd);
						List<Map<String,Object>> creditsSumList = getBaseTeacherCreditsSumList(values);
						if (MMap.validateList(creditsSumList)) {//存在值则做更新
							colmunMap.put("MOD_TIME", "sysdate");
							Map<String,Object> mapValues=new HashMap<String,Object>();
							values.put("TEACHER_ID", jsid);
							values.put("T_YEAR", pxnd);
							updateData(colmunMap, mapValues, "BASE_TEACHER_CREDITS_SUM");
						}else {//做新增
							colmunMap.put("SUMCID", UUID.randomUUID());
							colmunMap.put("TEACHER_ID", jsid);
							colmunMap.put("T_YEAR", pxnd);
							colmunMap.put("CRE_TIME", "sysdate");
							insertDataV2(colmunMap, "BASE_TEACHER_CREDITS_SUM");
						}
					}
				}//a3
				//网络
				maps.put("otherQuery", " and t.Pxxs='PXXS@GJ@3'  and t.pxnd is not null ");
				List<Map<String, Object>>  listWangLuo = getNamedQuery("misbase::queryTbBizJzggnpxxxList::query", maps);
				if (MMap.validateList(listWangLuo)) {//a2
					for (Map<String, Object> map : listWangLuo) {
						String jsid = map.get("JSID").toString();
						String pxnd = map.get("PXND").toString();
						String xs = map.get("XS").toString();
						String xf = map.get("XF").toString();
						Map<String, Object> colmunMap = new HashMap<String, Object>();
						if ("PXJB@GJ@10".equals(pkey)) {//国家级
							colmunMap.put("NET_QZ_NATL_HOURS", xs);
							colmunMap.put("NET_QZ_NATL_CREDITS", xf);
						}
						if ("PXJB@GJ@20".equals(pkey)) {//省级
							colmunMap.put("NET_QZ_PRCE_HOURS", xs);
							colmunMap.put("NET_QZ_RRCE_CREDITS", xf);
						}
						if ("PXJB@GJ@30".equals(pkey)) {//地市级
							colmunMap.put("NET_QZ_CITY_HOURS", xs);
							colmunMap.put("NET_QZ_CITY_CREDITS", xf);
						}
						if ("PXJB@GJ@40".equals(pkey)) {//县级
							colmunMap.put("NET_QZ_CNTY_HOURS", xs);
							colmunMap.put("NET_QZ_CNTY_CREDITS", xf);
						}
						if ("PXJB@GJ@60".equals(pkey)) {//校级
							colmunMap.put("NET_QZ_SCHOOL_HOURS", xs);
							colmunMap.put("NET_QZ_SCHOOL_CREDITS", xf);
						}
						if ("PXJB@GJ@70".equals(pkey)) {//自主研修	
							colmunMap.put("NET_QZ_SELF_HOURS", xs);
							colmunMap.put("NET_QZ_SELF_CREDITS", xf);
						}
						if ("PXJB@GJ@99".equals(pkey)) {//其他
							colmunMap.put("NET_QZ_OTHER_HOURS", xs);
							colmunMap.put("NET_QZ_OTHER_CREDITS", xf);
						}
						//省市县不加，直接从stat_base_teacher_info 中取值
						//教师ID和年度联合查记录
						Map<String,Object> values=new HashMap<String,Object>();
						values.put("teacherId", jsid);
						values.put("tyear", pxnd);
						List<Map<String,Object>> creditsSumList = getBaseTeacherCreditsSumList(values);
						if (MMap.validateList(creditsSumList)) {//存在值则做更新
							colmunMap.put("MOD_TIME", "sysdate");
							Map<String,Object> mapValues=new HashMap<String,Object>();
							values.put("TEACHER_ID", jsid);
							values.put("T_YEAR", pxnd);
							updateData(colmunMap, mapValues, "BASE_TEACHER_CREDITS_SUM");
						}else {//做新增
							colmunMap.put("SUMCID", UUID.randomUUID());
							colmunMap.put("TEACHER_ID", jsid);
							colmunMap.put("T_YEAR", pxnd);
							colmunMap.put("CRE_TIME", "sysdate");
							insertDataV2(colmunMap, "BASE_TEACHER_CREDITS_SUM");
						}
					}
				}//a2
				//面授培训和网络研修相结合学时
				maps.put("otherQuery", " and t.Pxxs='PXXS@GJ@4'  and t.pxnd is not null ");
				List<Map<String, Object>>  listUnion = getNamedQuery("misbase::queryTbBizJzggnpxxxList::query", maps);
				if (MMap.validateList(listUnion)) {//a1
					for (Map<String, Object> map : listUnion) {
						String jsid = map.get("JSID").toString();
						String pxnd = map.get("PXND").toString();
						String xs = map.get("XS").toString();
						String xf = map.get("XF").toString();
						Map<String, Object> colmunMap = new HashMap<String, Object>();
						if ("PXJB@GJ@10".equals(pkey)) {//国家级
							colmunMap.put("UNION_QZ_NATL_HOURS", xs);
							colmunMap.put("UNION_QZ_NATL_CREDITS", xf);
						}
						if ("PXJB@GJ@20".equals(pkey)) {//省级
							colmunMap.put("UNION_QZ_PRCE_HOURS", xs);
							colmunMap.put("UNION_QZ_RRCE_CREDITS", xf);
						}
						if ("PXJB@GJ@30".equals(pkey)) {//地市级
							colmunMap.put("UNION_QZ_CITY_HOURS", xs);
							colmunMap.put("UNION_QZ_CITY_CREDITS", xf);
						}
						if ("PXJB@GJ@40".equals(pkey)) {//县级
							colmunMap.put("UNION_QZ_CNTY_HOURS", xs);
							colmunMap.put("UNION_QZ_CNTY_CREDITS", xf);
						}
						if ("PXJB@GJ@60".equals(pkey)) {//校级
							colmunMap.put("UNION_QZ_SCHOOL_HOURS", xs);
							colmunMap.put("UNION_QZ_SCHOOL_CREDITS", xf);
						}
						if ("PXJB@GJ@70".equals(pkey)) {//自主研修	
							colmunMap.put("UNION_QZ_SELF_HOURS", xs);
							colmunMap.put("UNION_QZ_SELF_CREDITS", xf);
						}
						if ("PXJB@GJ@99".equals(pkey)) {//其他
							colmunMap.put("UNION_QZ_OTHER_HOURS", xs);
							colmunMap.put("UNION_QZ_OTHER_CREDITS", xf);
						}
						//省市县不加，直接从stat_base_teacher_info 中取值
						//教师ID和年度联合查记录
						Map<String,Object> values=new HashMap<String,Object>();
						values.put("teacherId", jsid);
						values.put("tyear", pxnd);
						List<Map<String,Object>> creditsSumList = getBaseTeacherCreditsSumList(values);
						if (MMap.validateList(creditsSumList)) {//存在值则做更新
							colmunMap.put("MOD_TIME", "sysdate");
							Map<String,Object> mapValues=new HashMap<String,Object>();
							values.put("TEACHER_ID", jsid);
							values.put("T_YEAR", pxnd);
							updateData(colmunMap, mapValues, "BASE_TEACHER_CREDITS_SUM");
						}else {//做新增
							colmunMap.put("SUMCID", UUID.randomUUID());
							colmunMap.put("TEACHER_ID", jsid);
							colmunMap.put("T_YEAR", pxnd);
							colmunMap.put("CRE_TIME", "sysdate");
							insertDataV2(colmunMap, "BASE_TEACHER_CREDITS_SUM");
						}
					}
				}//a1
				//其他
				maps.put("otherQuery", " and t.Pxxs='PXXS@GJ@9'  and t.pxnd is not null ");
				List<Map<String, Object>>  listOther = getNamedQuery("misbase::queryTbBizJzggnpxxxList::query", maps);
				if (MMap.validateList(listOther)) {//aa
					for (Map<String, Object> map : listOther) {
						String jsid = map.get("JSID").toString();
						String pxnd = map.get("PXND").toString();
						String xs = map.get("XS").toString();
						String xf = map.get("XF").toString();
						Map<String, Object> colmunMap = new HashMap<String, Object>();
						if ("PXJB@GJ@10".equals(pkey)) {//国家级
							colmunMap.put("OTHER_QZ_NATL_HOURS", xs);
							colmunMap.put("OTHER_QZ_NATL_CREDITS", xf);
						}
						if ("PXJB@GJ@20".equals(pkey)) {//省级
							colmunMap.put("OTHER_QZ_PRCE_HOURS", xs);
							colmunMap.put("OTHER_QZ_RRCE_CREDITS", xf);
						}
						if ("PXJB@GJ@30".equals(pkey)) {//地市级
							colmunMap.put("OTHER_QZ_CITY_HOURS", xs);
							colmunMap.put("OTHER_QZ_CITY_CREDITS", xf);
						}
						if ("PXJB@GJ@40".equals(pkey)) {//县级
							colmunMap.put("OTHER_QZ_CNTY_HOURS", xs);
							colmunMap.put("OTHER_QZ_CNTY_CREDITS", xf);
						}
						if ("PXJB@GJ@60".equals(pkey)) {//校级
							colmunMap.put("OTHER_QZ_SCHOOL_HOURS", xs);
							colmunMap.put("OTHER_QZ_SCHOOL_CREDITS", xf);
						}
						if ("PXJB@GJ@70".equals(pkey)) {//自主研修	
							colmunMap.put("OTHER_QZ_SELF_HOURS", xs);
							colmunMap.put("OTHER_QZ_SELF_CREDITS", xf);
						}
						if ("PXJB@GJ@99".equals(pkey)) {//其他
							colmunMap.put("OTHER_QZ_OTHER_HOURS", xs);
							colmunMap.put("OTHER_QZ_OTHER_CREDITS", xf);
						}
						//省市县不加，直接从stat_base_teacher_info 中取值
						//教师ID和年度联合查记录
						Map<String,Object> values=new HashMap<String,Object>();
						values.put("teacherId", jsid);
						values.put("tyear", pxnd);
						List<Map<String,Object>> creditsSumList = getBaseTeacherCreditsSumList(values);
						if (MMap.validateList(creditsSumList)) {//存在值则做更新
							colmunMap.put("MOD_TIME", "sysdate");
							Map<String,Object> mapValues=new HashMap<String,Object>();
							values.put("TEACHER_ID", jsid);
							values.put("T_YEAR", pxnd);
							updateData(colmunMap, mapValues, "BASE_TEACHER_CREDITS_SUM");
						}else {//做新增
							colmunMap.put("SUMCID", UUID.randomUUID());
							colmunMap.put("TEACHER_ID", jsid);
							colmunMap.put("T_YEAR", pxnd);
							colmunMap.put("CRE_TIME", "sysdate");
							insertDataV2(colmunMap, "BASE_TEACHER_CREDITS_SUM");
						}
					}
				}//aa
			}//
			//-------------------------------------------循环结束分割线----------------------------------------------------------------------
			//报刊发表学分-教职工论文信息表（TB_BIZ_JZGKJLWXX）   27043条数据
			//每一篇报刊发表给3分
			List<Map<String, Object>>  listBaokan = getNamedQuery("misbase::querytbBizJzgkjlwxx::query");
			if (MMap.validateList(listBaokan)) {
				for (Map<String, Object> map : listBaokan) {
					String jsid = map.get("JSID").toString();
					String count = map.get("CNT").toString();
					String year = map.get("TYEAR").toString();
					Map<String, Object> colmunMap = new HashMap<String, Object>();
					colmunMap.put("BK_PAPERS", MMap.isnullInt(count));//报刊发表篇数
					colmunMap.put("BK_CREDITS", MMap.isnullInt(count)*3);//每个3分
					//省市县不加，直接从stat_base_teacher_info 中取值
					//教师ID和年度联合查记录
					Map<String,Object> values=new HashMap<String,Object>();
					values.put("teacherId", jsid);
					values.put("tyear", year);
					List<Map<String,Object>> creditsSumList = getBaseTeacherCreditsSumList(values);
					if (MMap.validateList(creditsSumList)) {//存在值则做更新
						colmunMap.put("MOD_TIME", "sysdate");
						Map<String,Object> mapValues=new HashMap<String,Object>();
						values.put("TEACHER_ID", jsid);
						values.put("T_YEAR", year);
						updateData(colmunMap, mapValues, "BASE_TEACHER_CREDITS_SUM");
					}else {//做新增
						colmunMap.put("SUMCID", UUID.randomUUID());
						colmunMap.put("TEACHER_ID", jsid);
						colmunMap.put("T_YEAR", year);
						colmunMap.put("CRE_TIME", "sysdate");
						insertDataV2(colmunMap, "BASE_TEACHER_CREDITS_SUM");
					}
				}
			}
			//教材专著-教职工著作信息表（TB_BIZ_JZGKJZZXX）  34992条数据
			//一篇专著给10分
			List<Map<String, Object>>  listZhuZuo = getNamedQuery("misbase::querytbBizJzgkjzzxx::query");
			if (MMap.validateList(listZhuZuo)) {//a
				for (Map<String, Object> map : listZhuZuo) {
					String jsid = map.get("JSID").toString();
					String count = map.get("CNT").toString();
					String year = map.get("TYEAR").toString();
					Map<String, Object> colmunMap = new HashMap<String, Object>();
					colmunMap.put("JCZZ_BOOKS", MMap.isnullInt(count));//教材专著
					colmunMap.put("JCZZ_CREDITS", MMap.isnullInt(count)*10);//每个10分
					//省市县不加，直接从stat_base_teacher_info 中取值
					//教师ID和年度联合查记录
					Map<String,Object> values=new HashMap<String,Object>();
					values.put("teacherId", jsid);
					values.put("tyear", year);
					List<Map<String,Object>> creditsSumList = getBaseTeacherCreditsSumList(values);
					if (MMap.validateList(creditsSumList)) {//存在值则做更新
						colmunMap.put("MOD_TIME", "sysdate");
						Map<String,Object> mapValues=new HashMap<String,Object>();
						values.put("TEACHER_ID", jsid);
						values.put("T_YEAR", year);
						updateData(colmunMap, mapValues, "BASE_TEACHER_CREDITS_SUM");
					}else {//做新增
						colmunMap.put("SUMCID", UUID.randomUUID());
						colmunMap.put("TEACHER_ID", jsid);
						colmunMap.put("T_YEAR", year);
						colmunMap.put("CRE_TIME", "sysdate");
						insertDataV2(colmunMap, "BASE_TEACHER_CREDITS_SUM");
					}
				}
			}//a
			//课题研究或教改项目数--教职工项目(课题)信息表（TB_BIZ_JZGKJXMXX） 46236条数据
			//每个 5分
			List<Map<String, Object>>  listJiaoCai = getNamedQuery("misbase::querytbBizJzgkjxmxx::query");
			if (MMap.validateList(listJiaoCai)) {//a
				for (Map<String, Object> map : listJiaoCai) {
					String jsid = map.get("JSID").toString();
					String count = map.get("CNT").toString();
					String year = map.get("TYEAR").toString();
					Map<String, Object> colmunMap = new HashMap<String, Object>();
					colmunMap.put("KTJG_WORKS", MMap.isnullInt(count));//
					colmunMap.put("KTJG_CREDITS", MMap.isnullInt(count)*5);//每个5分
					//省市县不加，直接从stat_base_teacher_info 中取值
					//教师ID和年度联合查记录
					Map<String,Object> values=new HashMap<String,Object>();
					values.put("teacherId", jsid);
					values.put("tyear", year);
					List<Map<String,Object>> creditsSumList = getBaseTeacherCreditsSumList(values);
					if (MMap.validateList(creditsSumList)) {//存在值则做更新
						colmunMap.put("MOD_TIME", "sysdate");
						Map<String,Object> mapValues=new HashMap<String,Object>();
						values.put("TEACHER_ID", jsid);
						values.put("T_YEAR", year);
						updateData(colmunMap, mapValues, "BASE_TEACHER_CREDITS_SUM");
					}else {//做新增
						colmunMap.put("SUMCID", UUID.randomUUID());
						colmunMap.put("TEACHER_ID", jsid);
						colmunMap.put("T_YEAR", year);
						colmunMap.put("CRE_TIME", "sysdate");
						insertDataV2(colmunMap, "BASE_TEACHER_CREDITS_SUM");
					}
				}
			}//a
		}//mmap pxjbList
		
		//教师学分相加第一步
		executeUpdateByName("misBase::addBaseTeacherCreditsSum01::update", null);
		//教师学分相加第二步
		executeUpdateByName("misBase::addBaseTeacherCreditsSum02::update", null);
	}
	/**
	 * 
	* @Description: 将学分信息导入到BASE_TEACHER_CREDITS_SUM表中
	* @author hyl     
	* 教师id和年度确定一条记录，每个老师一个年度只能有一条记录
	 */
	public void init1() {
		long t1 = System.currentTimeMillis();
		executeUpdateByName("misbase::BaseTeacherCreditsSum::delete", null);
		//培训类别
		List<BaseProperties> pxjbList = CacheBasePropertiesManager.getPropertiesByGroupKey("PXJB");
		Session session = getSession();
		Connection conn = session.connection();
		CallableStatement call = null;
		System.out.println("PRO_BASETEACHERCREDITSSUM-----------------开始执行学分计算,时间："+DateFormater.DateTimeToString(new Date()));
		if (MMap.validateList(pxjbList)) {
			try {
			   call = conn.prepareCall("{call PRO_BASETEACHERCREDITSSUM}");
			   //执行
			   call.execute();
			}catch (Exception e) {
			      e.printStackTrace();
			}
			System.out.println("PRO_BASETEACHERCREDITSSUM-----------------执行完成,时间："+DateFormater.DateTimeToString(new Date()));
			try {
				call = conn.prepareCall("{call PRO_BASETEACHERCREDITSSUM_NET}");
				// 执行
				call.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("PRO_BASETEACHERCREDITSSUM_NET-----------------执行完成,时间："+DateFormater.DateTimeToString(new Date()));
			try {
				call = conn.prepareCall("{call PRO_BASETEACHERCREDITSSUM_UN}");
				// 执行
				call.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("PRO_BASETEACHERCREDITSSUM_UN-----------------执行完成,时间："+DateFormater.DateTimeToString(new Date()));
			try {
				call = conn.prepareCall("{call PRO_BASETEACHERCREDITSSUM_OTH}");
				// 执行
				call.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("PRO_BASETEACHERCREDITSSUM_OTH-----------------执行完成,时间："+DateFormater.DateTimeToString(new Date()));
			//-------------------------------------------循环结束分割线----------------------------------------------------------------------
		}//mmap pxjbList
		//报刊发表学分-教职工论文信息表（TB_BIZ_JZGKJLWXX）   27043条数据
		//每一篇报刊发表给3分
		try {
			call = conn.prepareCall("{call PRO_BASETEACHERCREDITSSUM_BAO}");
			// 执行
			call.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("PRO_BASETEACHERCREDITSSUM_BAO-----------------执行完成,时间："+DateFormater.DateTimeToString(new Date()));
		//教材专著-教职工著作信息表（TB_BIZ_JZGKJZZXX）  34992条数据
		//一篇专著给10分
		try {
			call = conn.prepareCall("{call PRO_BASETEACHERCREDITSSUM_JCZZ}");
			// 执行
			call.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("PRO_BASETEACHERCREDITSSUM_JCZZ-----------------执行完成,时间："+DateFormater.DateTimeToString(new Date()));
		//课题研究或教改项目数--教职工项目(课题)信息表（TB_BIZ_JZGKJXMXX） 46236条数据
		//每个 5分
		try {
			call = conn.prepareCall("{call PRO_BASETEACHERCREDITSSUM_KTJG}");
			// 执行
			call.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("PRO_BASETEACHERCREDITSSUM_KTJG-----------------执行完成,时间："+DateFormater.DateTimeToString(new Date()));
		
		//教师学分相加第一步
		executeUpdateByName("misBase::addBaseTeacherCreditsSum01::update", null);
		//教师学分相加第二步
		executeUpdateByName("misBase::addBaseTeacherCreditsSum02::update", null);
		long t2 = System.currentTimeMillis();
		try {
			FileUtil.writeLogTxt("log.txt","The BaseTeacherCreditsSum use time:"+ (t2-t1)/1000 +"s");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Description: 列表  
	* @author hyl  
	* select t.* from BASE_TEACHER_CREDITS_SUM t where 1=1  /~ and t.TEACHER_ID={teacherId}~/  /~and t.T_YEAR={tyear}~/ /~and t.SCHOOL_ID={schoolId}~/ /~and t.STEP_TYPE={stepType}~/  /~and t.CAID={hdId}~/    
	 */
	public List<Map<String,Object>> getBaseTeacherCreditsSumList(Map<String, Object> map) {
		Map<String,Object> values=new HashMap<String,Object>();
		for (String key:map.keySet()) {
			Object thisValue=map.get(key);
			if(isNotEmpty(thisValue)){
				values.put(key, thisValue);
			}
		}
		return getNamedQuery("misBase::BaseTeacherCreditsSum::getBaseTeacherCreditsSumPage", values);
	}
	
	/**
	 * 获得学分登记
	 * @author 90
	 * @date    2017-6-16
	 * @param map
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public Page getBaseTeacherCreditsSumPage(Map<String, Object> map,int pageSize,int pageNo){
		Map<String,Object> values=new HashMap<String,Object>();
		for (String key:map.keySet()) {
			Object thisValue=map.get(key);
			if(isNotEmpty(thisValue)){
				values.put(key, thisValue);
			}
		}
		return this.getPagedNamedQuery("misBase::BaseTeacherCreditsSum::getBaseTeacherCreditsSumPage", pageNo, pageSize, values);
	}
	
	
	/**
	 * 查询当前教师是否已经存在学分登记记录
	 * @author 90
	 * @date    2017-6-16
	 * @param map
	 * @return
	 */
	public Integer getBaseTeacherCreditsSumNum(Map<String, Object> map){
		Integer rlt=0;
		Map<String,Object> values=new HashMap<String,Object>();
		for (String key:map.keySet()) {
			Object thisValue=map.get(key);
			if(isNotEmpty(thisValue)){
				values.put(key, thisValue);
			}
		}
		List<Map<String, Object>> list= this.getNamedQuery("misBase::BaseCreditProject::getBaseTeacherCreditsSumNum", values);
		if(list!=null&&list.size()>0) {
			rlt=Integer.valueOf(list.get(0).get("NUM").toString());
		}
		return rlt;
	}
	
	/**
	 * 更新学分登记  有事务
	 * @author 90
	 * @date    2017-6-16
	 * operateFlag 标识，是加还是减
	 * @return
	 */
	public String updateXfdj(String xfId,Map<String, Object> xfDjMap, String operateFlag){
		BaseProTeacherMapManager baseProTeacherMapManager=(BaseProTeacherMapManager) ContextUtils.getBean("baseProTeacherMapManager");
		BaseCreditProjectManager baseCreditProjectManager=(BaseCreditProjectManager) ContextUtils.getBean("baseCreditProjectManager");
		Map<String, Object> btValues=new HashMap<String, Object>();
		btValues.put("id", xfId);
		Map<String, Object>  btpMap=baseProTeacherMapManager.getBaseTeacherMap(btValues);//学分
		Double xs=Double.valueOf(btpMap.get("T_PRO_ACTUAL_AMOUNT").toString());//学时就是投入量
		Double xf=Double.valueOf(btpMap.get("T_PRO_BASE_CREDITS").toString())
				+Double.valueOf(btpMap.get("T_PRO_AWARD_CREDITS").toString());//奖励学分和基础学分之和
		
		
		BaseCreditProject bp=baseCreditProjectManager.getCacheBaseCreditProject(btpMap.get("PRO_ID").toString());//项目
		String pLevel=bp.getProLevel()==null?"":bp.getProLevel();//项目级别 6 种
		String pType=bp.getProType()==null?"":bp.getProType();//项目类型 13种
		Integer xxjsNl=bp.getSfItAbilityFlag()==null?0:1;//是否属于信息技术能力
		Map<String, Object> columValues=new HashMap<String, Object>();
		Map<String, Object> termValues=new HashMap<String, Object>();
		termValues.put("SUMCID", xfDjMap.get("SUMCID"));
		
		//总的学分学时，不管任何类型，任何级别，这些值都要加。
		columValues=this.fzMap(columValues, xfDjMap, "SUM_HOURS", xs,operateFlag);//总学时
		columValues=this.fzMap(columValues, xfDjMap, "SUM_CREDITS", xf,operateFlag);//总学分
		if(xxjsNl.equals("1")){//如果是信息技术能力
			columValues=this.fzMap(columValues, xfDjMap, "SUM_QZ_IT_ABILITY_HOURS", xs,operateFlag);//总学时
			columValues=this.fzMap(columValues, xfDjMap, "SUM_QZ_IT_ABILITY_CREDITS", xf,operateFlag);//总学分
		}
		
		//判断项目的级别，类型
		if("pro.type.net_s".equals(pType)){//网络研修
			
			//网络学分学时
			columValues=this.fzMap(columValues, xfDjMap, "NET_HOURS", xs,operateFlag);//总学时
			columValues=this.fzMap(columValues, xfDjMap, "NET_CREDITS", xf,operateFlag);//总学分
			if(xxjsNl.equals("1")){//如果是信息技术能力
				columValues=this.fzMap(columValues, xfDjMap, "NET_QZ_IT_ABILITY_HOURS", xs,operateFlag);//总学时
				columValues=this.fzMap(columValues, xfDjMap, "NET_QZ_IT_ABILITY_CREDITS", xf,operateFlag);//总学分
			}
			if("pro.level.national".equals(pLevel)){//国家级
				//网络国家学分学时
				columValues=this.fzMap(columValues, xfDjMap, "NET_QZ_NATL_HOURS", xs,operateFlag);//总学时
				columValues=this.fzMap(columValues, xfDjMap, "NET_QZ_NATL_CREDITS", xf,operateFlag);//总学分
				if(xxjsNl.equals("1")){//如果是信息技术能力
					columValues=this.fzMap(columValues, xfDjMap, "NET_QZ_NATL_IT_ABILITY_HOURS", xs,operateFlag);//总学时
					columValues=this.fzMap(columValues, xfDjMap, "NET_QZ_NATL_IT_ABILITY_CREDITS", xf,operateFlag);//总学分
				}
			}else if("pro.level.province".equals(pLevel)){//省级
				//网络国家学分学时
				columValues=this.fzMap(columValues, xfDjMap, "NET_QZ_PRCE_HOURS", xs,operateFlag);//总学时
				columValues=this.fzMap(columValues, xfDjMap, "NET_QZ_RRCE_CREDITS", xf,operateFlag);//总学分
				if(xxjsNl.equals("1")){//如果是信息技术能力
					columValues=this.fzMap(columValues, xfDjMap, "NET_QZ_PRCE_IT_ABILITY_HOURS", xs,operateFlag);//总学时
					columValues=this.fzMap(columValues, xfDjMap, "NET_QZ_PRCE_IT_ABILITY_CREDITS", xf,operateFlag);//总学分
				}
			}else if("pro.level.city".equals(pLevel)){//市级
				//网络国家学分学时
				columValues=this.fzMap(columValues, xfDjMap, "NET_QZ_CITY_HOURS", xs,operateFlag);//总学时
				columValues=this.fzMap(columValues, xfDjMap, "NET_QZ_CITY_CREDITS", xf,operateFlag);//总学分
				if(xxjsNl.equals("1")){//如果是信息技术能力
					columValues=this.fzMap(columValues, xfDjMap, "NET_QZ_CITY_IT_ABILITY_HOURS", xs,operateFlag);//总学时
					columValues=this.fzMap(columValues, xfDjMap, "NET_QZ_CITY_IT_ABILITY_CREDITS", xf,operateFlag);//总学分
				}
			}
		}else if("pro.type.jigou_s".equals(pType)||"pro.type.jigou_t".equals(pType)){//机构
			//机构学分学时
			columValues=this.fzMap(columValues, xfDjMap, "JG_HOURS", xs,operateFlag);//总学时
			columValues=this.fzMap(columValues, xfDjMap, "JG_CREDITS", xf,operateFlag);//总学分
			if(xxjsNl.equals("1")){//如果是信息技术能力
				columValues=this.fzMap(columValues, xfDjMap, "JG_QZ_IT_ABILITY_HOURS", xs,operateFlag);//总学时
				columValues=this.fzMap(columValues, xfDjMap, "JG_QZ_IT_ABILITY_CREDITS", xf,operateFlag);//总学分
			}
			
			if("pro.level.national".equals(pLevel)){//国培
				//机构国家学分学时
				columValues=this.fzMap(columValues, xfDjMap, "JG_QZ_NATL_HOURS", xs,operateFlag);//总学时
				columValues=this.fzMap(columValues, xfDjMap, "JG_QZ_NATL_CREDITS", xf,operateFlag);//总学分
				if(xxjsNl.equals("1")){//如果是信息技术能力
					columValues=this.fzMap(columValues, xfDjMap, "JG_QZ_NATL_IT_ABILITY_HOURS", xs,operateFlag);//总学时
					columValues=this.fzMap(columValues, xfDjMap, "JG_QZ_NATL_IT_ABILITY_CREDITS", xf,operateFlag);//总学分
				}
		    }else if("pro.level.province".equals(pLevel)){//省培
				//机构国家学分学时
				columValues=this.fzMap(columValues, xfDjMap, "JG_QZ_PRCE_HOURS", xs,operateFlag);//总学时
				columValues=this.fzMap(columValues, xfDjMap, "JG_QZ_RRCE_CREDITS", xf,operateFlag);//总学分
				if(xxjsNl.equals("1")){//如果是信息技术能力
					columValues=this.fzMap(columValues, xfDjMap, "JG_QZ_PRCE_IT_ABILITY_HOURS", xs,operateFlag);//总学时
					columValues=this.fzMap(columValues, xfDjMap, "JG_QZ_PRCE_IT_ABILITY_CREDITS", xf,operateFlag);//总学分
				}
		    }else if("pro.level.city".equals(pLevel)){//市培
				//机构国家学分学时
				columValues=this.fzMap(columValues, xfDjMap, "JG_QZ_CITY_HOURS", xs,operateFlag);//总学时
				columValues=this.fzMap(columValues, xfDjMap, "JG_QZ_CITY_CREDITS", xf,operateFlag);//总学分
				if(xxjsNl.equals("1")){//如果是信息技术能力
					columValues=this.fzMap(columValues, xfDjMap, "JG_QZ_CITY_IT_ABILITY_HOURS", xs,operateFlag);//总学时
					columValues=this.fzMap(columValues, xfDjMap, "JG_QZ_CITY_IT_ABILITY_CREDITS", xf,operateFlag);//总学分
				}
		    }else if("pro.level.county".equals(pLevel)){//县培
				//机构国家学分学时
				columValues=this.fzMap(columValues, xfDjMap, "JG_QZ_CNTY_HOURS", xs,operateFlag);//总学时
				columValues=this.fzMap(columValues, xfDjMap, "JG_QZ_CNTY_CREDITS", xf,operateFlag);//总学分
				if(xxjsNl.equals("1")){//如果是信息技术能力
					columValues=this.fzMap(columValues, xfDjMap, "JG_QZ_CNTY_IT_ABILITY_HOURS", xs,operateFlag);//总学时
					columValues=this.fzMap(columValues, xfDjMap, "JG_QZ_CNTY_IT_ABILITY_CREDITS", xf,operateFlag);//总学分
				}
		    }
		}else if("pro.type.xiaoben_s".equals(pType)||"pro_type.xiaoben_t".equals(pType)){//学校
			//学校学分学时
			columValues=this.fzMap(columValues, xfDjMap, "XB_HOURS", xs,operateFlag);//总学时
			columValues=this.fzMap(columValues, xfDjMap, "XB_CREDITS", xf,operateFlag);//总学分
			if(xxjsNl.equals("1")){//如果是信息技术能力
				columValues=this.fzMap(columValues, xfDjMap, "XB_QZ_IT_ABILITY_HOURS", xs,operateFlag);//总学时
				columValues=this.fzMap(columValues, xfDjMap, "XB_QZ_IT_ABILITY_CREDITS", xf,operateFlag);//总学分
			}
		}
		
		this.updateData(columValues, termValues, "BASE_TEACHER_CREDITS_SUM");
		//学分表也需要更新 学分登记Id
		
		Map<String, Object> pcolumValues=new HashMap<String, Object>();
		Map<String, Object> ptermValues=new HashMap<String, Object>();
		ptermValues.put("PRO_MAP_ID", xfId);
		if("tj".equals(operateFlag)){
			pcolumValues.put("XFDJ_ID", xfDjMap.get("SUMCID"));
		}else{
			pcolumValues.put("XFDJ_ID", "");
		}
		this.updateData(pcolumValues, ptermValues, "BASE_PRO_TEACHER_MAP");
		return  "success";
	}
	
	
	//累加学分汇总的指标
	public Map<String,Object> fzMap(Map<String, Object> columValues,Map<String, Object> xfDjMap,String column,Double value,String operateFlag){
		if("tj".equals(operateFlag)){//累加
			columValues.put(column, xfDjMap.get(column)==null?value:(value+Double.valueOf(xfDjMap.get(column).toString())));
		}else{//移出
			if(xfDjMap.get(column)==null||(Double.valueOf(xfDjMap.get(column).toString())-value)<0){//该字段值为空，或者减去指标值为负
				columValues.put(column, 0);
			}else{
				columValues.put(column, Double.valueOf(xfDjMap.get(column).toString())-value);
			}
		}
		return columValues;
	}
	
	/**
	 * 获得学分汇总
	 * @author 90
	 * @date    2017-6-16
	 * @param map
	 * @return
	 */
	public Map<String,Object> getBaseTeacherCredits(Map<String, Object> map){
		Map<String,Object> mapRlt =null;
		Map<String,Object> values=new HashMap<String,Object>();
		for (String key:map.keySet()) {
			Object thisValue=map.get(key);
			if(isNotEmpty(thisValue)){
				values.put(key, thisValue);
			}
		}
		List<Map<String, Object>> list= this.getNamedQuery("misBase::BaseCreditProject::getBaseTeacherCredits", values);
		if(list!=null&&list.size()>0) {
			mapRlt=list.get(0);
		}
		return mapRlt;
	}

	/**
	 * 获取步骤学分登记
	 * @author 90
	 * @date    2017-6-26
	 * @param map
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public Page getBaseTeacherCreditsSumStepPage(Map<String, Object> map,int pageSize,int pageNo,String stepType){
		//String sqlStr="misBase::BaseTeacherCreditsSum::getBaseTeacherCreditsSumStepPage";//有步骤
		Map<String,Object> values=new HashMap<String,Object>();
		for (String key:map.keySet()) {
			Object thisValue=map.get(key);
			if(isNotEmpty(thisValue)){
				values.put(key, thisValue);
			}
		}
		if(isNotEmpty(stepType)){
			values.put("stepType", stepType);
		}
		return this.getPagedNamedQuery("misBase::BaseTeacherCreditsSum::getBaseTeacherCreditsSumPage", pageNo, pageSize, values);
	}
	
	/**
	 * //更新学分的步骤状态
	 * @author 90
	 * @date    2017-6-27
	 * @param id
	 * @return
	 */
	public String  updateXfStepType(String id,String stepType){
		BaseTeacherCreditsSum  sum=this.get(id);
		sum.setStepType(stepType);
		this.update(sum);
		return "success";
	}
	
	
	/**
	 * 获取核定教师数
	 * @author 90
	 * @date    2017-6-28
	 * @param map
	 * @param pageSize
	 * @param pageNo
	 * @param stepType
	 * @return
	 */
	public Page getHdTeacherPage(Map<String, Object> map,int pageSize,int pageNo,String startYear,String endYear,String hdLimit){
		//String sqlStr="misBase::BaseTeacherCreditsSum::getBaseTeacherCreditsSumStepPage";//有步骤
		Map<String,Object> values=new HashMap<String,Object>();
		for (String key:map.keySet()) {
			Object thisValue=map.get(key);
			if(isNotEmpty(thisValue)){
				values.put(key, thisValue);
			}
		}
		if(isNotEmpty(startYear)){
			values.put("startYear", startYear);
		}
		if(isNotEmpty(endYear)){
			values.put("endYear", endYear);
		}
        if(isNotEmpty(hdLimit)){
        	values.put("hdLimit", hdLimit);
		}
		
		return this.getPagedNamedQuery("misBase::BaseTeacherCreditsSum::getHdTeacherPage", pageNo, pageSize, values);
	}
}
