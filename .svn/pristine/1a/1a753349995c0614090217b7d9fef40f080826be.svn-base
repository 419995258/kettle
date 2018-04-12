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


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import org.apache.commons.lang.StringUtils;
import org.my431.platform.dao.extend.HibernateEntityExtendDao;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.springframework.stereotype.Repository;
import org.my431.base.model.BaseKpiItem;
import org.my431.base.model.BaseUser;
import org.my431.platform.dao.support.Page;
import org.my431.util.MMap;
import org.my431.util.StringUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@Repository
public class BaseKpiItemManager extends HibernateXsqlBuilderQueryDao<BaseKpiItem>{

	public Class getEntityClass() {
		return BaseKpiItem.class;
	}
	
	public Page findPage(BaseKpiItem query, int pageSize, int pageNo) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from BaseKpiItem t where 1=1 "
			  	+ "/~ and t.tableName = {tableName} ~/"
			  	+ "/~ and t.columnName = {columnName} ~/"
			  	+ "/~ and t.useFlag = {useFlag} ~/"
			  	+ "/~ and t.kpiName = {kpiName} ~/"
			  	+ "/~ and t.kpiSfEnum = {kpiSfEnum} ~/"
			  	+ "/~ and t.dataType = {dataType} ~/"
			  	+ "/~ and t.dataLength = {dataLength} ~/"
			  	+ "/~ and t.columnId = {columnId} ~/"
			  	+ "/~ and t.columnComments = {columnComments} ~/"
			  	+ "/~ and t.rcolumnName1 = {rcolumnName1} ~/"
			  	+ "/~ and t.rcolumnType1 = {rcolumnType1} ~/"
			  	+ "/~ and t.rcolumnName2 = {rcolumnName2} ~/"
			  	+ "/~ and t.rcolumnType2 = {rcolumnType2} ~/"
			  	+ "/~ and t.rcolumnName3 = {rcolumnName3} ~/"
			  	+ "/~ and t.rcolumnType3 = {rcolumnType3} ~/"
				+ "/~ order by [sortColumns] ~/";

		Map filters = new HashMap();  
				
		XsqlBuilder xsqlBuilder=new XsqlBuilder();
		
        /*if(isNotEmpty(query.getKpiId())) {
            filters.put("kpiId", query.getKpiId()); 
        }*/
        if(isNotEmpty(query.getTableName())) {
            filters.put("tableName", query.getTableName()); 
        }
        if(isNotEmpty(query.getColumnName())) {
            filters.put("columnName", query.getColumnName()); 
        }
        if(isNotEmpty(query.getUseFlag())) {
            filters.put("useFlag", query.getUseFlag()); 
        }
        if(isNotEmpty(query.getKpiName())) {
            filters.put("kpiName", query.getKpiName()); 
        }
        if(isNotEmpty(query.getKpiSfEnum())) {
            filters.put("kpiSfEnum", query.getKpiSfEnum()); 
        }
        if(isNotEmpty(query.getDataType())) {
            filters.put("dataType", query.getDataType()); 
        }
        if(isNotEmpty(query.getDataLength())) {
            filters.put("dataLength", query.getDataLength()); 
        }
        if(isNotEmpty(query.getColumnId())) {
            filters.put("columnId", query.getColumnId()); 
        }
        if(isNotEmpty(query.getColumnComments())) {
            filters.put("columnComments", query.getColumnComments()); 
        }
        if(isNotEmpty(query.getRcolumnName1())) {
            filters.put("rcolumnName1", query.getRcolumnName1()); 
        }
        if(isNotEmpty(query.getRcolumnType1())) {
            filters.put("rcolumnType1", query.getRcolumnType1()); 
        }
        if(isNotEmpty(query.getRcolumnName2())) {
            filters.put("rcolumnName2", query.getRcolumnName2()); 
        }
        if(isNotEmpty(query.getRcolumnType2())) {
            filters.put("rcolumnType2", query.getRcolumnType2()); 
        }
        if(isNotEmpty(query.getRcolumnName3())) {
            filters.put("rcolumnName3", query.getRcolumnName3()); 
        }
        if(isNotEmpty(query.getRcolumnType3())) {
            filters.put("rcolumnType3", query.getRcolumnType3()); 
        }
        
        XsqlFilterResult result = xsqlBuilder.generateHql(sql,filters); 
        
		return pageQuery(result.getXsql(), pageNo, pageSize,result.getAcceptedFilters());
	}
	
	/**
	 * 获取乡村教师的剧本情况统计
	 * @param map
	 * @return
	 */
	public Map<Object, Object> getCountryTeacher(Map<String, Object> map) {
		BaseUser baseUser = (BaseUser) map.get("baus");
		if (StringUtils.isNotBlank(baseUser.getProvinceId())) {
			map.put("provinceId", baseUser.getProvinceId());
		}
		if (StringUtils.isNotBlank(baseUser.getCityId())) {
			map.put("cityId", baseUser.getCityId());
		}
		if (StringUtils.isNotBlank(baseUser.getCountyId())) {
			map.put("countyId", baseUser.getCountyId());
		}
		List list = (List) getNamedQuery("misBase::BaseKpiItem::getCountryTeacher",map);
		Map<Object, Object> mapGet = new HashMap<Object, Object>();
		MMap mmap = null;
		//已经存在的
		BigDecimal bigDecimalHave = null;
		//新获取的
		BigDecimal bigDecimalNew = null;
		//TODO 其他统计 乡村教师总数：CCount 城镇教师总数：TCount 
		//TODO SFZB 乡村教师不在编总数 CSFZB0Count 乡村教师在编总数 CSFZB1Count 乡村教师在编未填总数 CSFZBNCount
		//TODO SFZB 城镇教师不在编总数 TSFZB0Count 乡村教师在编总数 TSFZB1Count 乡村教师在编未填总数TSFZBNCount
		//TODO SFXJJYSGGJS 乡村教师不骨干总数 CSFXJJYSGGJS0Count 乡村教师骨干总数 CSFXJJYSGGJS1Count 乡村教师骨干未填总数 CSFXJJYSGGJSNCount
		//TODO SFXJJYSGGJS 城镇教师不骨干总数 TSFXJJYSGGJS0Count 城镇教师骨干总数 TSFXJJYSGGJS1Count 城镇教师骨干未填总数 TSFXJJYSGGJSNCount
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> mm = (Map<String, Object>) iterator.next();
			mmap = new MMap();
			bigDecimalNew = (BigDecimal) mm.get("NUM");
			String areaId = (String) mm.get("AREAID");
			if(areaId == null){
				areaId = "null";
			}
			if (mapGet.keySet().contains(areaId)) {
				// 已经存在该地区，先获取该地区
				mmap = (MMap) mapGet.get(areaId);
			}
			/**=====================================是否乡村教师 SFXCJS 1，是===============**/
			if("1".equals(mm.get("SFXCJS").toString())){
				//乡村教师===============================
				/**=====================================是否在编 sfxb，SFZB@GJ@0，SFZB@GJ@1在，null===============**/
				//TODO sfzb 1-14 mapGet 
				//TODO sfzb乡村: 1:null， 2: 0， 3： 1， 4: 乡村教师的总人数，9：在编的总人数,10:不再编,11:未填报
				//TODO sfzb城镇: 5:null， 6: 0， 7： 1， 8: 城镇教师的总人数,12：在编的总人数,13:不再编,14:未填报
				if(StringUtils.isNotBlank((String) mm.get("SFZB"))){
					if("SFZB@GJ@0".equals(mm.get("SFZB"))){
						//不在编地区人数
						if(mmap.getObj2() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj2();
						}
						mmap.setObj2(bigDecimalHave.add(bigDecimalNew));
						//不在编乡村地区人数总数-地区
						if(mmap.getObj10() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj10();
						}
						mmap.setObj10(bigDecimalHave.add(bigDecimalNew));
						//不在编乡村地区人数总数-总
						if(mapGet.get("CSFZB0Count")== null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mapGet.get("CSFZB0Count");
						}
						mapGet.put("CSFZB0Count",(bigDecimalHave.add(bigDecimalNew)));
					}else if("SFZB@GJ@1".equals(mm.get("SFZB"))){
						//在编地区人数
						if(mmap.getObj3() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj3();
						}
						mmap.setObj3(bigDecimalHave.add(bigDecimalNew));
						//在编乡村地区人数总数-地区
						if(mmap.getObj9() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj9();
						}
						mmap.setObj9(bigDecimalHave.add(bigDecimalNew));
						//在编乡村地区人数总数-总
						if(mapGet.get("CSFZB1Count")== null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mapGet.get("CSFZB1Count");
						}
						mapGet.put("CSFZB1Count",(bigDecimalHave.add(bigDecimalNew)));
					}
				}else{
					//null 当前地区人数
					if(mmap.getObj1() == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj1();
					}
					mmap.setObj1(bigDecimalHave.add(bigDecimalNew));
					//null乡村地区人数总数
					if(mmap.getObj11() == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj11();
					}
					mmap.setObj11(bigDecimalHave.add(bigDecimalNew));
					//在编乡村地区人数总数-总
					if(mapGet.get("CSFZBNCount")== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mapGet.get("CSFZBNCount");
					}
					mapGet.put("CSFZBNCount",(bigDecimalHave.add(bigDecimalNew)));
				}
		/**=====================================是否骨干教师 SFXJJYSGGJS，SFXJJYSGGJS@GJ@0，SFXJJYSGGJS@GJ@1是，null===============**/
				//TODO SFXJJYSGGJS 15-28 mapGet 
				//TODO SFXJJYSGGJS乡村: 15:null， 16: 0， 17： 1， 18: 乡村教师的总人数(停用)，19：骨干的总人数,20:不骨干,21:未填报
				//TODO SFXJJYSGGJS城镇: 22:null， 23: 0， 24： 1， 25: 城镇教师的总人数（停用）,26：骨干的总人数,27:不骨干,28:未填报
				if(StringUtils.isNotBlank((String) mm.get("SFXJJYSGGJS"))){
					//非骨干
					if("SFXJJYSGGJS@GJ@0".equals(mm.get("SFXJJYSGGJS"))){
						//不骨干人数-地区
						if(mmap.getObj16() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj16();
						}
						mmap.setObj16(bigDecimalHave.add(bigDecimalNew));
						//不骨干人数总数-地区
						if(mmap.getObj20() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj20();
						}
						mmap.setObj20(bigDecimalHave.add(bigDecimalNew));
						//不骨干地区人数总数-总
						if(mapGet.get("CSFXJJYSGGJS0Count")== null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mapGet.get("CSFXJJYSGGJS0Count");
						}
						mapGet.put("CSFXJJYSGGJS0Count",(bigDecimalHave.add(bigDecimalNew)));
					}else if("SFXJJYSGGJS@GJ@1".equals(mm.get("SFXJJYSGGJS"))){
						//骨干地区人数-地区
						if(mmap.getObj17() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj17();
						}
						mmap.setObj17(bigDecimalHave.add(bigDecimalNew));
						//骨干乡村地区人数总数-地区
						if(mmap.getObj19() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj19();
						}
						mmap.setObj19(bigDecimalHave.add(bigDecimalNew));
						//骨干乡村地区人数总数-总
						if(mapGet.get("CSFXJJYSGGJS1Count")== null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mapGet.get("CSFXJJYSGGJS1Count");
						}
						mapGet.put("CSFXJJYSGGJS1Count",(bigDecimalHave.add(bigDecimalNew)));
					}
				}else{
					//null 当前地区人数
					if(mmap.getObj15() == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj15();
					}
					mmap.setObj15(bigDecimalHave.add(bigDecimalNew));
					//null乡村地区人数总数
					if(mmap.getObj21() == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj21();
					}
					mmap.setObj21(bigDecimalHave.add(bigDecimalNew));
					//在编乡村地区人数总数-总
					if(mapGet.get("CSFXJJYSGGJSNCount")== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mapGet.get("CSFXJJYSGGJSNCount");
					}
					mapGet.put("CSFXJJYSGGJSNCount",(bigDecimalHave.add(bigDecimalNew)));
				}
				
				
				
				
		/**=====================================是否高级教师 SFXJJYSGGJS，SFXJJYSGGJS@GJ@0，SFXJJYSGGJS@GJ@1是，null===============**/		
//TODO XRGWDJ 乡村教师高级总数 CXRGWDJHCount 乡村教师中级总数 CXRGWDJMCount 乡村教师初级总数 CXRGWDJLCount  乡村教师未知总数 CXRGWDJUCount 乡村教师在编未填总数 CXRGWDJNCount
//TODO XRGWDJ 城镇教师高级总数 TXRGWDJHCount 城镇教师中级总数 TXRGWDJMCount 城镇教师初级总数 TXRGWDJLCount  城镇教师未知总数 TXRGWDJUCount 城镇教师在编未填总数 TXRGWDJNCount
//TODO XRGWDJ 29-50 mapGet  H:高级  M:中级  L:初级  U:未知
//TODO XRGWDJ乡村: 29:null， 30: H， 31： M， 32：L， 33： U，  34: 乡村教师的总人数(停用)，35：高级的总人数,36:中级,37：初级，38:未知，39：未填报
//TODO XRGWDJ城镇: 40:null， 41: H， 42： M， 43：L， 44： U，  45: 乡村教师的总人数(停用)，46：高级的总人数,47:中级,48：初级，49:未知，50：未填报
				if(StringUtils.isNotBlank((String) mm.get("XRGWDJ_FMT"))){
					//高级
					if("post.level.high".equals(mm.get("XRGWDJ_FMT"))){
						//高级人数-地区
						if(mmap.getObj30() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj30();
						}
						mmap.setObj30(bigDecimalHave.add(bigDecimalNew));
						//高级人数总数-地区
						if(mmap.getObj35() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj35();
						}
						mmap.setObj35(bigDecimalHave.add(bigDecimalNew));
						//高级地区人数总数-总
						if(mapGet.get("CXRGWDJHCount")== null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mapGet.get("CXRGWDJHCount");
						}
						mapGet.put("CXRGWDJHCount",(bigDecimalHave.add(bigDecimalNew)));
					}else if("post.level.middle".equals(mm.get("XRGWDJ_FMT"))){
						//中级地区人数-地区
						if(mmap.getObj31() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj31();
						}
						mmap.setObj31(bigDecimalHave.add(bigDecimalNew));
						//中级地区人数总数-地区
						if(mmap.getObj36() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj36();
						}
						mmap.setObj36(bigDecimalHave.add(bigDecimalNew));
						//中级地区人数总数-总
						if(mapGet.get("CXRGWDJMCount")== null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mapGet.get("CXRGWDJMCount");
						}
						mapGet.put("CXRGWDJMCount",(bigDecimalHave.add(bigDecimalNew)));
					}else if("post.level.low".equals(mm.get("XRGWDJ_FMT"))){
						//初级地区人数-地区
						if(mmap.getObj32() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj32();
						}
						mmap.setObj32(bigDecimalHave.add(bigDecimalNew));
						//初级地区人数总数-地区
						if(mmap.getObj37() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj37();
						}
						mmap.setObj37(bigDecimalHave.add(bigDecimalNew));
						//初级地区人数总数-总
						if(mapGet.get("CXRGWDJLCount")== null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mapGet.get("CXRGWDJLCount");
						}
						mapGet.put("CXRGWDJLCount",(bigDecimalHave.add(bigDecimalNew)));
					}else if("post.level.unknown".equals(mm.get("XRGWDJ_FMT"))){
						//未知地区人数-地区
						if(mmap.getObj33() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj33();
						}
						mmap.setObj33(bigDecimalHave.add(bigDecimalNew));
						//未知地区人数总数-地区
						if(mmap.getObj38() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj38();
						}
						mmap.setObj38(bigDecimalHave.add(bigDecimalNew));
						//未知地区人数总数-总
						if(mapGet.get("CXRGWDJUCount")== null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mapGet.get("CXRGWDJUCount");
						}
						mapGet.put("CXRGWDJUCount",(bigDecimalHave.add(bigDecimalNew)));
					}
				}else{
					//null 当前地区人数
					if(mmap.getObj29() == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj29();
					}
					mmap.setObj29(bigDecimalHave.add(bigDecimalNew));
					//null乡村地区人数总数
					if(mmap.getObj39() == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj39();
					}
					mmap.setObj39(bigDecimalHave.add(bigDecimalNew));
					//在编乡村地区人数总数-总
					if(mapGet.get("CXRGWDJNCount")== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mapGet.get("CXRGWDJNCount");
					}
					mapGet.put("CXRGWDJNCount",(bigDecimalHave.add(bigDecimalNew)));
				}
				


				
		/**====================================================结束，计算总人数==============================================**/
				//计算教师的乡村人数总数-地区
				if(mmap.getObj4() == null){
					bigDecimalHave = new BigDecimal("0");
				}else{
					bigDecimalHave = (BigDecimal) mmap.getObj4();
				}
				mmap.setObj4(bigDecimalHave.add(bigDecimalNew));
				//计算教师的乡村人数总数-总
				if(mapGet.get("CCount") == null){
					bigDecimalHave = new BigDecimal("0");
				}else{
					bigDecimalHave = (BigDecimal) mapGet.get("CCount");
				}
				mapGet.put("CCount",bigDecimalHave.add(bigDecimalNew));
			}else{
				//城镇教师=========================
		/**=====================================是否在编 sfxb，SFZB@GJ@0，SFZB@GJ@1在，null===============**/
				if(StringUtils.isNotBlank((String) mm.get("SFZB"))){
					//城镇教师当前地区不再编的人数
					if("SFZB@GJ@0".equals(mm.get("SFZB"))){
						if(mmap.getObj6() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj6();
						}
						mmap.setObj6(bigDecimalHave.add(bigDecimalNew));
						//城镇教师不再编人数总数-地区
						if(mmap.getObj13() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj13();
						}
						mmap.setObj13(bigDecimalHave.add(bigDecimalNew));
						//在编城镇地区人数总数-总
						if(mapGet.get("TSFZB0Count")== null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mapGet.get("TSFZB0Count");
						}
						mapGet.put("TSFZB0Count",(bigDecimalHave.add(bigDecimalNew)));
					}else if("SFZB@GJ@1".equals(mm.get("SFZB"))){
						if(mmap.getObj7() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj7();
						}
						mmap.setObj7(bigDecimalHave.add(bigDecimalNew));
						//城镇教师再编人数总数
						if(mmap.getObj12() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj12();
						}
						mmap.setObj12(bigDecimalHave.add(bigDecimalNew));
						//在编城镇地区人数总数-总
						if(mapGet.get("TSFZB1Count")== null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mapGet.get("TSFZB1Count");
						}
						mapGet.put("TSFZB1Count",(bigDecimalHave.add(bigDecimalNew)));
					}
				}else{
					//null
					if(mmap.getObj5() == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj5();
					}
					mmap.setObj5(bigDecimalHave.add(bigDecimalNew));
					//城镇教师未填报人数总数
					if(mmap.getObj14() == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj14();
					}
					mmap.setObj14(bigDecimalHave.add(bigDecimalNew));
					//在编城镇地区人数总数-总
					if(mapGet.get("TSFZBNCount")== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mapGet.get("TSFZBNCount");
					}
					mapGet.put("TSFZBNCount",(bigDecimalHave.add(bigDecimalNew)));
				}
				
				/**=====================================是否骨干教师 SFXJJYSGGJS，SFXJJYSGGJS@GJ@0，SFXJJYSGGJS@GJ@1是，null===============**/
				if(StringUtils.isNotBlank((String) mm.get("SFXJJYSGGJS"))){
					//城镇教师当前地区不骨干的人数 -地区
					if("SFXJJYSGGJS@GJ@0".equals(mm.get("SFXJJYSGGJS"))){
						if(mmap.getObj23() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj23();
						}
						mmap.setObj23(bigDecimalHave.add(bigDecimalNew));
						//城镇教师不骨干人数总数-地区
						if(mmap.getObj27() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj27();
						}
						mmap.setObj27(bigDecimalHave.add(bigDecimalNew));
						//城镇地区骨干人数总数-总
						if(mapGet.get("TSFXJJYSGGJS0Count")== null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mapGet.get("TSFXJJYSGGJS0Count");
						}
						mapGet.put("TSFXJJYSGGJS0Count",(bigDecimalHave.add(bigDecimalNew)));
					}else if("SFXJJYSGGJS@GJ@1".equals(mm.get("SFXJJYSGGJS"))){
						if(mmap.getObj24() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj24();
						}
						mmap.setObj24(bigDecimalHave.add(bigDecimalNew));
						//城镇教师骨干人数总数-地区
						if(mmap.getObj26() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj26();
						}
						mmap.setObj26(bigDecimalHave.add(bigDecimalNew));
						//城镇地区骨干人数总数-总
						if(mapGet.get("TSFXJJYSGGJS1Count")== null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mapGet.get("TSFXJJYSGGJS1Count");
						}
						mapGet.put("TSFXJJYSGGJS1Count",(bigDecimalHave.add(bigDecimalNew)));
					}
				}else{
					//null
					if(mmap.getObj22() == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj22();
					}
					mmap.setObj22(bigDecimalHave.add(bigDecimalNew));
					//城镇教师未填报人数总数
					if(mmap.getObj28() == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj28();
					}
					mmap.setObj28(bigDecimalHave.add(bigDecimalNew));
					//在编城镇地区人数总数-总
					if(mapGet.get("TSFXJJYSGGJSNCount")== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mapGet.get("TSFXJJYSGGJSNCount");
					}
					mapGet.put("TSFXJJYSGGJSNCount",(bigDecimalHave.add(bigDecimalNew)));
				}
				
				/**=====================================是否高级教师 SFXJJYSGGJS，SFXJJYSGGJS@GJ@0，SFXJJYSGGJS@GJ@1是，null===============**/
				if(StringUtils.isNotBlank((String) mm.get("XRGWDJ_FMT"))){
					//高级
					if("post.level.high".equals(mm.get("XRGWDJ_FMT"))){
						//高级人数-地区
						if(mmap.getObj41() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj41();
						}
						mmap.setObj41(bigDecimalHave.add(bigDecimalNew));
						//高级人数总数-地区
						if(mmap.getObj46() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj46();
						}
						mmap.setObj46(bigDecimalHave.add(bigDecimalNew));
						//高级地区人数总数-总
						if(mapGet.get("TXRGWDJHCount")== null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mapGet.get("TXRGWDJHCount");
						}
						mapGet.put("TXRGWDJHCount",(bigDecimalHave.add(bigDecimalNew)));
					}else if("post.level.middle".equals(mm.get("XRGWDJ_FMT"))){
						//中级地区人数-地区
						if(mmap.getObj42() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj42();
						}
						mmap.setObj42(bigDecimalHave.add(bigDecimalNew));
						//中级地区人数总数-地区
						if(mmap.getObj47() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj47();
						}
						mmap.setObj47(bigDecimalHave.add(bigDecimalNew));
						//中级地区人数总数-总
						if(mapGet.get("TXRGWDJMCount")== null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mapGet.get("TXRGWDJMCount");
						}
						mapGet.put("TXRGWDJMCount",(bigDecimalHave.add(bigDecimalNew)));
					}else if("post.level.low".equals(mm.get("XRGWDJ_FMT"))){
						//初级地区人数-地区
						if(mmap.getObj43() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj43();
						}
						mmap.setObj43(bigDecimalHave.add(bigDecimalNew));
						//初级地区人数总数-地区
						if(mmap.getObj48() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj48();
						}
						mmap.setObj48(bigDecimalHave.add(bigDecimalNew));
						//初级地区人数总数-总
						if(mapGet.get("TXRGWDJLCount")== null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mapGet.get("TXRGWDJLCount");
						}
						mapGet.put("TXRGWDJLCount",(bigDecimalHave.add(bigDecimalNew)));
					}else if("post.level.unknown".equals(mm.get("XRGWDJ_FMT"))){
						//未知地区人数-地区
						if(mmap.getObj44() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj44();
						}
						mmap.setObj44(bigDecimalHave.add(bigDecimalNew));
						//未知地区人数总数-地区
						if(mmap.getObj49() == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mmap.getObj49();
						}
						mmap.setObj49(bigDecimalHave.add(bigDecimalNew));
						//未知地区人数总数-总
						if(mapGet.get("TXRGWDJUCount")== null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal) mapGet.get("TXRGWDJUCount");
						}
						mapGet.put("TXRGWDJUCount",(bigDecimalHave.add(bigDecimalNew)));
					}
				}else{
					//null 当前地区人数
					if(mmap.getObj40() == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj40();
					}
					mmap.setObj40(bigDecimalHave.add(bigDecimalNew));
					//null乡村地区人数总数
					if(mmap.getObj50() == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj50();
					}
					mmap.setObj50(bigDecimalHave.add(bigDecimalNew));
					//在编乡村地区人数总数-总
					if(mapGet.get("TXRGWDJNCount")== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mapGet.get("TXRGWDJNCount");
					}
					mapGet.put("TXRGWDJNCount",(bigDecimalHave.add(bigDecimalNew)));
				}
				/**====================================================结束，计算总人数==============================================**/
				//计算城镇教师的城镇人数总数-地区
				if(mmap.getObj8() == null){
					bigDecimalHave = new BigDecimal("0");
				}else{
					bigDecimalHave = (BigDecimal) mmap.getObj8();
				}
				mmap.setObj8(bigDecimalHave.add(bigDecimalNew));
				//计算教师的城镇人数总数-总
				if(mapGet.get("TCount") == null){
					bigDecimalHave = new BigDecimal("0");
				}else{
					bigDecimalHave = (BigDecimal) mapGet.get("TCount");
				}
				mapGet.put("TCount",bigDecimalHave.add(bigDecimalNew));
			}
			
			mapGet.put(areaId, mmap);
		}
		return mapGet;
		
	}
	/**
	 * 获取乡村教师基本情况统计的图标数据
	 * @param fxzbChar
	 * @param mapGet
	 * @return
	 */
	public Map<Object,Object> getCountryTeacherCharts(Map<Object, Object> mapGet){
		JSONArray array = null;
		JSONObject jsonObject = null;
		BigDecimal tcount = null;
		BigDecimal ccount = null;
		BigDecimal allcountPeople = null;
		/**==========================================是否在编图表===================================**/
		array = new JSONArray();
		//教师总数：
		tcount = (BigDecimal) mapGet.get("TCount");
		ccount = (BigDecimal) mapGet.get("CCount");
		if(tcount == null){
			tcount = new BigDecimal("0");
		}
		if(ccount == null){
			ccount = new BigDecimal("0");
		}
		allcountPeople = tcount.add(ccount);
		jsonObject = getDateJson("总数",tcount, ccount, allcountPeople);
		array.add(jsonObject);
		//在编：
		tcount = (BigDecimal) mapGet.get("TSFZB1Count");
		ccount = (BigDecimal) mapGet.get("CSFZB1Count");
		jsonObject = getDateJson("在编",tcount, ccount, allcountPeople);
		array.add(jsonObject);
		//不再编
		tcount = (BigDecimal) mapGet.get("TSFZB0Count");
		ccount = (BigDecimal) mapGet.get("CSFZB0Count");
		jsonObject = getDateJson("未在编",tcount, ccount, allcountPeople);
		array.add(jsonObject);
		//未填
		tcount = (BigDecimal) mapGet.get("TSFZBNCount");
		ccount = (BigDecimal) mapGet.get("CSFZBNCount");
		jsonObject = getDateJson("未填报",tcount, ccount, allcountPeople);
		array.add(jsonObject);
		mapGet.put("SFZBChart", array);
		/**==========================================骨干教师图表===================================**/
		array = new JSONArray();
		//教师总数：
		tcount = (BigDecimal) mapGet.get("TCount");
		ccount = (BigDecimal) mapGet.get("CCount");
		jsonObject = getDateJson("总数",tcount, ccount, allcountPeople);
		array.add(jsonObject);
		//骨干教师：
		tcount = (BigDecimal) mapGet.get("TSFXJJYSGGJS1Count");
		ccount = (BigDecimal) mapGet.get("CSFXJJYSGGJS1Count");
		jsonObject = getDateJson("骨干教师",tcount, ccount, allcountPeople);
		array.add(jsonObject);
		//非骨干教师
		tcount = (BigDecimal) mapGet.get("TSFXJJYSGGJS0Count");
		ccount = (BigDecimal) mapGet.get("CSFXJJYSGGJS0Count");
		jsonObject = getDateJson("非骨干教师",tcount, ccount, allcountPeople);
		array.add(jsonObject);
		//未填
		tcount = (BigDecimal) mapGet.get("TSFXJJYSGGJSNCount");
		ccount = (BigDecimal) mapGet.get("CSFXJJYSGGJSNCount");
		jsonObject = getDateJson("未填报",tcount, ccount, allcountPeople);
		array.add(jsonObject);
		mapGet.put("SFXJJYSGGJSChart", array);
		/**==========================================骨干教师图表===================================**/
		array = new JSONArray();
		//教师总数：
		tcount = (BigDecimal) mapGet.get("TCount");
		ccount = (BigDecimal) mapGet.get("CCount");
		jsonObject = getDateJson("总数",tcount, ccount, allcountPeople);
		array.add(jsonObject);
		//高级岗位教师：
		tcount = (BigDecimal) mapGet.get("TXRGWDJHCount");
		ccount = (BigDecimal) mapGet.get("CXRGWDJHCount");
		jsonObject = getDateJson("高级岗位（1-7）",tcount, ccount, allcountPeople);
		array.add(jsonObject);
		//中级岗位教师
		tcount = (BigDecimal) mapGet.get("TXRGWDJMCount");
		ccount = (BigDecimal) mapGet.get("CXRGWDJMCount");
		jsonObject = getDateJson("中级岗位教师（8-10）",tcount, ccount, allcountPeople);
		array.add(jsonObject);
		//初级级岗位教师
		tcount = (BigDecimal) mapGet.get("TXRGWDJLCount");
		ccount = (BigDecimal) mapGet.get("CXRGWDJLCount");
		jsonObject = getDateJson("初级岗位教师（11-13）",tcount, ccount, allcountPeople);
		array.add(jsonObject);
		//未知
		tcount = (BigDecimal) mapGet.get("TXRGWDJUCount");
		ccount = (BigDecimal) mapGet.get("CXRGWDJUCount");
		jsonObject = getDateJson("未知",tcount, ccount, allcountPeople);
		array.add(jsonObject);
		//未填
		tcount = (BigDecimal) mapGet.get("TSFXJJYSGGJSNCount");
		ccount = (BigDecimal) mapGet.get("CSFXJJYSGGJSNCount");
		jsonObject = getDateJson("未填报",tcount, ccount, allcountPeople);
		array.add(jsonObject);
		mapGet.put("XRGWDJChart", array);
		
		return mapGet;
		
	}
	
	/**
	 * 乡村教师基础情况图表数据进行json处理
	 * @param name
	 * @param tcount
	 * @param ccount
	 * @param allcountPeople
	 * @return
	 */
	public JSONObject getDateJson(String name,BigDecimal tcount,BigDecimal ccount,BigDecimal allcountPeople){
		if(tcount == null){
			tcount = new BigDecimal("0");
		}
		if(ccount == null){
			ccount = new BigDecimal("0");
		}
		if(allcountPeople == null){
			allcountPeople = new BigDecimal("0");
		}
		JSONObject jsonObject = new JSONObject();
		BigDecimal allcount = null;
		allcount = tcount.add(ccount);//总人数
		String percent = null;//百分比
		JSONArray jsonArray = null;
		JSONArray jsonArray2 = null;
		JSONObject json = null;
		//所有教师的数量
		if(!allcountPeople.equals(new BigDecimal("0"))){
			percent = allcount
					.multiply(new BigDecimal("100"))
					.divide(allcountPeople, 3, BigDecimal.ROUND_HALF_DOWN)
					.toString();
		}else{
			percent = "100";
		}
		jsonArray = new JSONArray();
		json = new JSONObject();
		json.put("value", allcount);
		json.put("toolText",
				"<b>"+name+"</b></br>$value 人</br>占总人数的： "+ percent +"%");
		jsonArray.add(json);
		//城镇
		if(!allcount.equals(new BigDecimal("0"))){
			percent = tcount
					.multiply(new BigDecimal("100"))
					.divide(allcountPeople, 3, BigDecimal.ROUND_HALF_DOWN)
					.toString();
		}else{
			percent = "100";
		}
		json = new JSONObject();
		json.put("value", tcount);
		json.put("toolText",
				"<b>"+name+"</b></br>$value 人</br>占总人数的： "+ percent +"%");
		jsonArray.add(json);
		//乡村
		if(!allcount.equals(new BigDecimal("0"))){
			percent = ccount
					.multiply(new BigDecimal("100"))
					.divide(allcountPeople, 3, BigDecimal.ROUND_HALF_DOWN)
					.toString();
		}else{
			percent = "100";
		}
		json = new JSONObject();
		json.put("value", ccount);
		json.put("toolText",
				"<b>"+name+"</b></br>$value 人</br>占总人数的： "+ percent +"%");
		jsonArray.add(json);
		jsonObject.put("seriesname", name);
		jsonObject.put("data",jsonArray);
		return jsonObject;
	}
	
	
	/**
	 * 获取乡村教师薪资基本情况统计
	 * @param map
	 * @return
	 */
	public Map<Object, Object> getTeacherMoneyTable(Map<String, Object> map) {
		Map<Object, Object> mapGet = new HashMap<Object, Object>();
		BaseUser baseUser = (BaseUser) map.get("baus");
		if (StringUtils.isNotBlank(baseUser.getProvinceId())) {
			map.put("provinceId", baseUser.getProvinceId());
		}
		if (StringUtils.isNotBlank(baseUser.getCityId())) {
			map.put("cityId", baseUser.getCityId());
		}
		if (StringUtils.isNotBlank(baseUser.getCountyId())) {
			map.put("countyId", baseUser.getCountyId());
		}
		
		List list = (List) getNamedQuery("misBase::BaseKpiItem::getTeacherMoneyTable",map);
		MMap mmap = null;
		//已经存在的
		BigDecimal bigDecimalHave = null;
		//新获取的
		BigDecimal bigDecimalNew = null;
		//已经存在的基本工资
		BigDecimal bigDecimalHaveJBGZ = null;
		//新获取的基本工资
		BigDecimal bigDecimalNewJBGZ = null;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> mm = (Map<String, Object>) iterator.next();
			mmap = new MMap();
			bigDecimalNew = (BigDecimal) mm.get("NUM");
			bigDecimalNewJBGZ = (BigDecimal) mm.get("JBGZ");
			if(bigDecimalNewJBGZ == null){
				bigDecimalNewJBGZ = new BigDecimal("0");
			}
			String areaId = (String) mm.get("AREAID");
			if(areaId == null){
				areaId = "null";
			}
			if (mapGet.keySet().contains(areaId)) {
				// 已经存在该地区，先获取该地区
				mmap = (MMap) mapGet.get(areaId);
			}
			/**===============================城乡薪资对比======================================**/
			if("1".equals(mm.get("SFXCJS").toString())){
				//乡村
				//乡村人数---地区
				if(mmap.getObj1()== null){
					bigDecimalHave = new BigDecimal("0");
				}else{
					bigDecimalHave = (BigDecimal) mmap.getObj1();
				}
				mmap.setObj1((bigDecimalHave.add(bigDecimalNew)));
				//基本工资总额
				if(mmap.getObj2()== null){
					bigDecimalHaveJBGZ = new BigDecimal("0");
				}else{
					bigDecimalHaveJBGZ = (BigDecimal) mmap.getObj2();
				}
				mmap.setObj2((bigDecimalHaveJBGZ.add(bigDecimalNewJBGZ)));
				//乡村生活补贴
				if("null".equals(mm.get("XCJSSHBZ"))){
					if(mmap.getObj3()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj3();
					}
					mmap.setObj3((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("NXCJSSHBZ") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("NXCJSSHBZ");
					}
					mapGet.put("NXCJSSHBZ",(bigDecimalHave.add(bigDecimalNew)));
				}else if("L".equals(mm.get("XCJSSHBZ"))){
					if(mmap.getObj4()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj4();
					}
					mmap.setObj4((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("LXCJSSHBZ") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("LXCJSSHBZ");
					}
					mapGet.put("LXCJSSHBZ",(bigDecimalHave.add(bigDecimalNew)));
				}else if("M".equals(mm.get("XCJSSHBZ"))){
					if(mmap.getObj5()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj5();
					}
					mmap.setObj5((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("MXCJSSHBZ") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("MXCJSSHBZ");
					}
					mapGet.put("MXCJSSHBZ",(bigDecimalHave.add(bigDecimalNew)));
				}else if("H".equals(mm.get("XCJSSHBZ"))){
					if(mmap.getObj6()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj6();
					}
					mmap.setObj6((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("HXCJSSHBZ") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("HXCJSSHBZ");
					}
					mapGet.put("HXCJSSHBZ",(bigDecimalHave.add(bigDecimalNew)));
				}
				
				//计算教师的乡村人数总数-总
				if(mapGet.get("CCount") == null){
					bigDecimalHave = new BigDecimal("0");
				}else{
					bigDecimalHave = (BigDecimal) mapGet.get("CCount");
				}
				mapGet.put("CCount",bigDecimalHave.add(bigDecimalNew));
				//计算教师的乡村薪资总数-总
				if(mapGet.get("CCountMoney") == null){
					bigDecimalHaveJBGZ = new BigDecimal("0");
				}else{
					bigDecimalHaveJBGZ = (BigDecimal) mapGet.get("CCountMoney");
				}
				mapGet.put("CCountMoney",bigDecimalHaveJBGZ.add(bigDecimalNewJBGZ));
			}else{
				//城镇
				//人数---地区
				if(mmap.getObj7()== null){
					bigDecimalHave = new BigDecimal("0");
				}else{
					bigDecimalHave = (BigDecimal) mmap.getObj7();
				}
				mmap.setObj7((bigDecimalHave.add(bigDecimalNew)));
				//基本工资总额
				if(mmap.getObj8()== null){
					bigDecimalHaveJBGZ = new BigDecimal("0");
				}else{
					bigDecimalHaveJBGZ = (BigDecimal) mmap.getObj8();
				}
				mmap.setObj8((bigDecimalHaveJBGZ.add(bigDecimalNewJBGZ)));
				
				//计算教师的乡村人数总数-总
				if(mapGet.get("TCount") == null){
					bigDecimalHave = new BigDecimal("0");
				}else{
					bigDecimalHave = (BigDecimal) mapGet.get("TCount");
				}
				mapGet.put("TCount",bigDecimalHave.add(bigDecimalNew));
				//计算教师的城镇薪资总数-总
				if(mapGet.get("TCountMoney") == null){
					bigDecimalHaveJBGZ = new BigDecimal("0");
				}else{
					bigDecimalHaveJBGZ = (BigDecimal) mapGet.get("TCountMoney");
				}
				mapGet.put("TCountMoney",bigDecimalHaveJBGZ.add(bigDecimalNewJBGZ));
			}
			/**===============================五险一金统计======================================**/
			String WXYJ = (String) mm.get("WXYJ");
			if(StringUtils.isNotBlank(WXYJ)){
				if(WXYJ.contains("WXYJ@GJ@1")){
					if(mmap.getObj9()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj9();
					}
					mmap.setObj9((bigDecimalHave.add(bigDecimalNew)));
					
					if("1".equals(mm.get("SFXCJS").toString())){
						//乡村
						if(mapGet.get("CWXYJ1") == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal)mapGet.get("CWXYJ1");
						}
						mapGet.put("CWXYJ1",(bigDecimalHave.add(bigDecimalNew)));
					}else{
						if(mapGet.get("TWXYJ1") == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal)mapGet.get("TWXYJ1");
						}
						mapGet.put("TWXYJ1",(bigDecimalHave.add(bigDecimalNew)));
					}
				}
				if(WXYJ.contains("WXYJ@GJ@2")){
					if(mmap.getObj10()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj10();
					}
					mmap.setObj10((bigDecimalHave.add(bigDecimalNew)));
					
					if("1".equals(mm.get("SFXCJS").toString())){
						//乡村
						if(mapGet.get("CWXYJ2") == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal)mapGet.get("CWXYJ2");
						}
						mapGet.put("CWXYJ2",(bigDecimalHave.add(bigDecimalNew)));
					}else{
						if(mapGet.get("TWXYJ2") == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal)mapGet.get("TWXYJ2");
						}
						mapGet.put("TWXYJ2",(bigDecimalHave.add(bigDecimalNew)));
					}
				}
				if(WXYJ.contains("WXYJ@GJ@3")){
					if(mmap.getObj11()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj11();
					}
					mmap.setObj11((bigDecimalHave.add(bigDecimalNew)));
					
					if("1".equals(mm.get("SFXCJS").toString())){
						//乡村
						if(mapGet.get("CWXYJ3") == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal)mapGet.get("CWXYJ3");
						}
						mapGet.put("CWXYJ3",(bigDecimalHave.add(bigDecimalNew)));
					}else{
						if(mapGet.get("TWXYJ3") == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal)mapGet.get("TWXYJ3");
						}
						mapGet.put("TWXYJ3",(bigDecimalHave.add(bigDecimalNew)));
					}
				}
				if(WXYJ.contains("WXYJ@GJ@4")){
					if(mmap.getObj12()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj12();
					}
					mmap.setObj12((bigDecimalHave.add(bigDecimalNew)));
					
					if("1".equals(mm.get("SFXCJS").toString())){
						//乡村
						if(mapGet.get("CWXYJ4") == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal)mapGet.get("CWXYJ4");
						}
						mapGet.put("CWXYJ4",(bigDecimalHave.add(bigDecimalNew)));
					}else{
						if(mapGet.get("TWXYJ4") == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal)mapGet.get("TWXYJ4");
						}
						mapGet.put("TWXYJ4",(bigDecimalHave.add(bigDecimalNew)));
					}
				}
				if(WXYJ.contains("WXYJ@GJ@5")){
					if(mmap.getObj13()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj13();
					}
					mmap.setObj13((bigDecimalHave.add(bigDecimalNew)));
					
					if("1".equals(mm.get("SFXCJS").toString())){
						//乡村
						if(mapGet.get("CWXYJ5") == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal)mapGet.get("CWXYJ5");
						}
						mapGet.put("CWXYJ5",(bigDecimalHave.add(bigDecimalNew)));
					}else{
						if(mapGet.get("TWXYJ5") == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal)mapGet.get("TWXYJ5");
						}
						mapGet.put("TWXYJ5",(bigDecimalHave.add(bigDecimalNew)));
					}
				}
				if(WXYJ.contains("WXYJ@GJ@6")){
					if(mmap.getObj14()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj14();
					}
					mmap.setObj14((bigDecimalHave.add(bigDecimalNew)));
					
					if("1".equals(mm.get("SFXCJS").toString())){
						//乡村
						if(mapGet.get("CWXYJ6") == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal)mapGet.get("CWXYJ6");
						}
						mapGet.put("CWXYJ6",(bigDecimalHave.add(bigDecimalNew)));
					}else{
						if(mapGet.get("TWXYJ6") == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal)mapGet.get("TWXYJ6");
						}
						mapGet.put("TWXYJ6",(bigDecimalHave.add(bigDecimalNew)));
					}
				}
				if(WXYJ.contains("WXYJ@GJ@0")){
					if(mmap.getObj15()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj15();
					}
					mmap.setObj15((bigDecimalHave.add(bigDecimalNew)));
					
					if("1".equals(mm.get("SFXCJS").toString())){
						//乡村
						if(mapGet.get("CWXYJ0") == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal)mapGet.get("CWXYJ0");
						}
						mapGet.put("CWXYJ0",(bigDecimalHave.add(bigDecimalNew)));
					}else{
						if(mapGet.get("TWXYJ0") == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal)mapGet.get("TWXYJ0");
						}
						mapGet.put("TWXYJ0",(bigDecimalHave.add(bigDecimalNew)));
					}
				}
				if(WXYJ.contains("WXYJ@GJ@1") && WXYJ.contains("WXYJ@GJ@2") && WXYJ.contains("WXYJ@GJ@3") && WXYJ.contains("WXYJ@GJ@4") && WXYJ.contains("WXYJ@GJ@5") && WXYJ.contains("WXYJ@GJ@6")){
					if(mmap.getObj16()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj16();
					}
					mmap.setObj16((bigDecimalHave.add(bigDecimalNew)));
					
					if("1".equals(mm.get("SFXCJS").toString())){
						//乡村
						if(mapGet.get("CWXYJA") == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal)mapGet.get("CWXYJA");
						}
						mapGet.put("CWXYJA",(bigDecimalHave.add(bigDecimalNew)));
					}else{
						if(mapGet.get("TWXYJA") == null){
							bigDecimalHave = new BigDecimal("0");
						}else{
							bigDecimalHave = (BigDecimal)mapGet.get("TWXYJA");
						}
						mapGet.put("TWXYJA",(bigDecimalHave.add(bigDecimalNew)));
					}
				}
			}else{
				//未填报
				if(mmap.getObj17()== null){
					bigDecimalHave = new BigDecimal("0");
				}else{
					bigDecimalHave = (BigDecimal) mmap.getObj17();
				}
				mmap.setObj17((bigDecimalHave.add(bigDecimalNew)));
				
				if("1".equals(mm.get("SFXCJS").toString())){
					//乡村
					if(mapGet.get("CWXYJN") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("CWXYJN");
					}
					mapGet.put("CWXYJN",(bigDecimalHave.add(bigDecimalNew)));
				}else{
					if(mapGet.get("TWXYJN") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("TWXYJN");
					}
					mapGet.put("TWXYJN",(bigDecimalHave.add(bigDecimalNew)));
				}
			}
			
			mapGet.put(areaId, mmap);
		}
		
		return mapGet;
	}
	
	
	/**
	 * 教师补助的饼图
	 * @param mapGet
	 * @return
	 */
	public Map<Object,Object> getTeacherMoneyXCJSSHBZCharts(Map<Object, Object> mapGet){
		JSONArray array = new JSONArray();
		JSONObject json = null;
		json = new JSONObject();
		json.put("label", "无补贴");
		json.put("value", mapGet.get("NXCJSSHBZ"));
		/*json.put("color", "#cccccc");*/
		array.add(json);
		json = new JSONObject();
		json.put("label", "小于100元");
		json.put("value", mapGet.get("LXCJSSHBZ"));
		array.add(json);
		json = new JSONObject();
		json.put("label", "100~300元");
		json.put("value", mapGet.get("MXCJSSHBZ"));
		array.add(json);
		json = new JSONObject();
		json.put("label", "大于300元");
		json.put("value", mapGet.get("HXCJSSHBZ"));
		array.add(json);
		mapGet.put("JSBZChart", array);
		return mapGet;
		
	}
	
	
	/**
	 * 教师基本工资的折线图
	 * @param mapGet
	 * @return
	 */
	public JSONArray getTeacherMoneyJBGZCharts(Map<String, Object> map){
		JSONArray array = new JSONArray();
		JSONArray jsonArray = null;
		JSONObject json = null;
		List list = (List) getNamedQuery("misBase::BaseKpiItem::getTeacherMoneyScrollline",map);
		if(list.size() > 0){
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map<String, Object> mm = (Map<String, Object>) iterator.next();
				jsonArray = new JSONArray();
				json = new JSONObject();
				json.put("value", mm.get("NUM1"));
				jsonArray.add(json);
				json = new JSONObject();
				json.put("value", mm.get("NUM2"));
				jsonArray.add(json);
				json = new JSONObject();
				json.put("value", mm.get("NUM3"));
				jsonArray.add(json);
				json = new JSONObject();
				json.put("value", mm.get("NUM4"));
				jsonArray.add(json);
				json = new JSONObject();
				json.put("value", mm.get("NUM5"));
				jsonArray.add(json);
				json = new JSONObject();
				json.put("value", mm.get("NUM6"));
				jsonArray.add(json);
				json = new JSONObject();
				json.put("value", mm.get("NUM7"));
				jsonArray.add(json);
				json = new JSONObject();
				json.put("value", mm.get("NUM8"));
				jsonArray.add(json);
				json = new JSONObject();
				json.put("value", mm.get("NUM9"));
				jsonArray.add(json);
				json = new JSONObject();
				json.put("value", mm.get("NUM10"));
				jsonArray.add(json);
				json = new JSONObject();
				json.put("value", mm.get("NUM11"));
				jsonArray.add(json);
				json = new JSONObject();
				json.put("value", mm.get("NUM12"));
				jsonArray.add(json);
				json = new JSONObject();
				json.put("data", jsonArray);
				if("1".equals(mm.get("SFXCJS").toString())){
					json.put("seriesname", "乡村教师");
				}else{
					json.put("seriesname", "城区教师");
				}
				array.add(json);
			}
		}
		return array;
	}
	
	/**
	 * 教师五险一金的柱状图
	 * @param mapGet
	 * @return
	 */
	public Map<Object,Object> getTeacherMoneyWXYJCharts(Map<Object, Object> mapGet){
		JSONArray fin = new JSONArray();
		JSONArray array = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		JSONObject json = null;
		BigDecimal tcount = (BigDecimal) mapGet.get("TCount");
		BigDecimal ccount = (BigDecimal) mapGet.get("CCount");
		//城市
		json = new JSONObject();
		json.put("value", mapGet.get("TWXYJA"));
		jsonArray.add(json);
		json = new JSONObject();
		json.put("value", mapGet.get("TWXYJ1"));
		jsonArray.add(json);
		json = new JSONObject();
		json.put("value", mapGet.get("TWXYJ2"));
		jsonArray.add(json);
		json = new JSONObject();
		json.put("value", mapGet.get("TWXYJ3"));
		jsonArray.add(json);
		json = new JSONObject();
		json.put("value", mapGet.get("TWXYJ4"));
		jsonArray.add(json);
		json = new JSONObject();
		json.put("value", mapGet.get("TWXYJ5"));
		jsonArray.add(json);
		json = new JSONObject();
		json.put("value", mapGet.get("TWXYJ6"));
		jsonArray.add(json);
		json = new JSONObject();
		json.put("data", jsonArray);
		json.put("seriesname", "城区教师缴纳");
		
		array.add(json);
		
		jsonArray = new JSONArray();
		json = new JSONObject();
		if(mapGet.get("TWXYJA")!= null){
			json.put("value", tcount.subtract((BigDecimal) mapGet.get("TWXYJA")));
		}else {
			json.put("value", tcount.subtract(new BigDecimal("0")));
		}
		jsonArray.add(json);
		json = new JSONObject();
		if(mapGet.get("TWXYJA")!= null){
			json.put("value", tcount.subtract((BigDecimal) mapGet.get("TWXYJ1")));
		}else {
			json.put("value", tcount.subtract(new BigDecimal("0")));
		}
		jsonArray.add(json);
		json = new JSONObject();
		if(mapGet.get("TWXYJA")!= null){
			json.put("value", tcount.subtract((BigDecimal) mapGet.get("TWXYJ2")));
		}else {
			json.put("value", tcount.subtract(new BigDecimal("0")));
		}
		jsonArray.add(json);
		json = new JSONObject();
		if(mapGet.get("TWXYJA")!= null){
			json.put("value", tcount.subtract((BigDecimal) mapGet.get("TWXYJ3")));
		}else {
			json.put("value", tcount.subtract(new BigDecimal("0")));
		}
		jsonArray.add(json);
		json = new JSONObject();
		if(mapGet.get("TWXYJA")!= null){
			json.put("value", tcount.subtract((BigDecimal) mapGet.get("TWXYJ4")));
		}else {
			json.put("value", tcount.subtract(new BigDecimal("0")));
		}
		jsonArray.add(json);
		json = new JSONObject();
		if(mapGet.get("TWXYJA")!= null){
			json.put("value", tcount.subtract((BigDecimal) mapGet.get("TWXYJ5")));
		}else {
			json.put("value", tcount.subtract(new BigDecimal("0")));
		}
		jsonArray.add(json);
		json = new JSONObject();
		if(mapGet.get("TWXYJA")!= null){
			json.put("value", tcount.subtract((BigDecimal) mapGet.get("TWXYJ6")));
		}else {
			json.put("value", tcount.subtract(new BigDecimal("0")));
		}
		jsonArray.add(json);
		json = new JSONObject();
		json.put("data", jsonArray);
		json.put("seriesname", "城区教师未缴纳");
		
		array.add(json);
		
		jsonObject = new JSONObject();
		jsonObject.put("dataset", array);
		fin.add(jsonObject);
		
		//乡村
		array = new JSONArray();
		jsonArray = new JSONArray();
		json = new JSONObject();
		json.put("value", mapGet.get("CWXYJA"));
		jsonArray.add(json);
		json = new JSONObject();
		json.put("value", mapGet.get("CWXYJ1"));
		jsonArray.add(json);
		json = new JSONObject();
		json.put("value", mapGet.get("CWXYJ2"));
		jsonArray.add(json);
		json = new JSONObject();
		json.put("value", mapGet.get("CWXYJ3"));
		jsonArray.add(json);
		json = new JSONObject();
		json.put("value", mapGet.get("CWXYJ4"));
		jsonArray.add(json);
		json = new JSONObject();
		json.put("value", mapGet.get("CWXYJ5"));
		jsonArray.add(json);
		json = new JSONObject();
		json.put("value", mapGet.get("CWXYJ6"));
		jsonArray.add(json);
		json = new JSONObject();
		json.put("data", jsonArray);
		json.put("seriesname", "乡村教师缴纳");
		
		array.add(json);
		
		jsonArray = new JSONArray();
		json = new JSONObject();
		if(mapGet.get("CWXYJA")!= null){
			json.put("value", ccount.subtract((BigDecimal) mapGet.get("CWXYJA")));
		}else {
			json.put("value", ccount.subtract(new BigDecimal("0")));
		}
		jsonArray.add(json);
		json = new JSONObject();
		if(mapGet.get("CWXYJA")!= null){
			json.put("value", ccount.subtract((BigDecimal) mapGet.get("CWXYJ1")));
		}else {
			json.put("value", ccount.subtract(new BigDecimal("0")));
		}
		jsonArray.add(json);
		json = new JSONObject();
		if(mapGet.get("CWXYJA")!= null){
			json.put("value", ccount.subtract((BigDecimal) mapGet.get("CWXYJ2")));
		}else {
			json.put("value", ccount.subtract(new BigDecimal("0")));
		}
		jsonArray.add(json);
		json = new JSONObject();
		if(mapGet.get("CWXYJA")!= null){
			json.put("value", ccount.subtract((BigDecimal) mapGet.get("CWXYJ3")));
		}else {
			json.put("value", ccount.subtract(new BigDecimal("0")));
		}
		jsonArray.add(json);
		json = new JSONObject();
		if(mapGet.get("CWXYJA")!= null){
			json.put("value", ccount.subtract((BigDecimal) mapGet.get("CWXYJ4")));
		}else {
			json.put("value", ccount.subtract(new BigDecimal("0")));
		}
		jsonArray.add(json);
		json = new JSONObject();
		if(mapGet.get("CWXYJA")!= null){
			json.put("value", ccount.subtract((BigDecimal) mapGet.get("CWXYJ5")));
		}else {
			json.put("value", ccount.subtract(new BigDecimal("0")));
		}
		jsonArray.add(json);
		json = new JSONObject();
		if(mapGet.get("CWXYJA")!= null){
			json.put("value", ccount.subtract((BigDecimal) mapGet.get("CWXYJ6")));
		}else {
			json.put("value", ccount.subtract(new BigDecimal("0")));
		}
		jsonArray.add(json);
		json = new JSONObject();
		json.put("data", jsonArray);
		json.put("seriesname", "乡村教师未缴纳");
		
		array.add(json);
		
		jsonObject = new JSONObject();
		jsonObject.put("dataset", array);
		fin.add(jsonObject);
		
		mapGet.put("WXYJChart", fin);
		return mapGet;
		
	}

	
	/**
	 * 获取乡村教师荣誉基本情况统计
	 * @param map
	 * @return
	 */
	public Map<Object, Object> getTeacherHonorTable(Map<String, Object> map) {
		Map<Object, Object> mapGet = new HashMap<Object, Object>();
		BaseUser baseUser = (BaseUser) map.get("baus");
		if (StringUtils.isNotBlank(baseUser.getProvinceId())) {
			map.put("provinceId", baseUser.getProvinceId());
		}
		if (StringUtils.isNotBlank(baseUser.getCityId())) {
			map.put("cityId", baseUser.getCityId());
		}
		if (StringUtils.isNotBlank(baseUser.getCountyId())) {
			map.put("countyId", baseUser.getCountyId());
		}
		MMap mmap = null;
		//先计算各个地区的人数
		List listCount = (List) getNamedQuery("misBase::BaseKpiItem::getTeacherCityCount",map);
		//已经存在总人数
		BigDecimal bigDecimalHaveCount = null;
		//新获取的总人数
		BigDecimal bigDecimalNewCount = null;
		for (Iterator iterator = listCount.iterator(); iterator.hasNext();) {
			Map<String, Object> mm = (Map<String, Object>) iterator.next();
			mmap = new MMap();
			bigDecimalNewCount = (BigDecimal) mm.get("COUNT");
			if(bigDecimalNewCount == null){
				bigDecimalNewCount = new BigDecimal("0");
			}
			String areaId = (String) mm.get("AREAID");
			if(areaId == null){
				areaId = "null";
			}
			if (mapGet.keySet().contains(areaId)) {
				// 已经存在该地区，先获取该地区
				mmap = (MMap) mapGet.get(areaId);
			}
			if("1".equals(mm.get("SFXCJS").toString())){
				//乡村总人数---地区
				if(mmap.getObj2()== null){
					bigDecimalHaveCount = new BigDecimal("0");
				}else{
					bigDecimalHaveCount = (BigDecimal) mmap.getObj2();
				}
				mmap.setObj2((bigDecimalHaveCount.add(bigDecimalNewCount)));
				//计算教师的乡村人数总数-总
				if(mapGet.get("CCount") == null){
					bigDecimalHaveCount = new BigDecimal("0");
				}else{
					bigDecimalHaveCount = (BigDecimal) mapGet.get("CCount");
				}
				mapGet.put("CCount",bigDecimalHaveCount.add(bigDecimalNewCount));
			}else{
				//城镇总人数---地区
				if(mmap.getObj12()== null){
					bigDecimalHaveCount = new BigDecimal("0");
				}else{
					bigDecimalHaveCount = (BigDecimal) mmap.getObj12();
				}
				mmap.setObj12((bigDecimalHaveCount.add(bigDecimalNewCount)));
				//计算教师的乡村人数总数-总
				if(mapGet.get("TCount") == null){
					bigDecimalHaveCount = new BigDecimal("0");
				}else{
					bigDecimalHaveCount = (BigDecimal) mapGet.get("TCount");
				}
				mapGet.put("TCount",bigDecimalHaveCount.add(bigDecimalNewCount));
			}
			mapGet.put(areaId, mmap);
		}
		
		
		List list = (List) getNamedQuery("misBase::BaseKpiItem::getTeacherHonorTable",map);
		//已经存在的
		BigDecimal bigDecimalHave = null;
		//新获取的
		BigDecimal bigDecimalNew = null;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> mm = (Map<String, Object>) iterator.next();
			mmap = new MMap();
			bigDecimalNew = (BigDecimal) mm.get("NUM");
			
			String areaId = (String) mm.get("AREAID");
			if(areaId == null){
				areaId = "null";
			}
			if (mapGet.keySet().contains(areaId)) {
				// 已经存在该地区，先获取该地区
				mmap = (MMap) mapGet.get(areaId);
			}
			/**===============================城乡对比======================================**/
			if("1".equals(mm.get("SFXCJS").toString())){
				//乡村
				//乡村荣誉人数---地区
				if(mmap.getObj1()== null){
					bigDecimalHave = new BigDecimal("0");
				}else{
					bigDecimalHave = (BigDecimal) mmap.getObj1();
				}
				mmap.setObj1((bigDecimalHave.add(bigDecimalNew)));
				//乡村荣誉级别
				if("null".equals(mm.get("RYJB"))){
					if(mmap.getObj3()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj3();
					}
					mmap.setObj3((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("CNRYJB") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("CNRYJB");
					}
					mapGet.put("CNRYJB",(bigDecimalHave.add(bigDecimalNew)));
				}else if("RYJB@GJ@0".equals(mm.get("RYJB"))){
					if(mmap.getObj4()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj4();
					}
					mmap.setObj4((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("CRYJB0") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("CRYJB0");
					}
					mapGet.put("CRYJB0",(bigDecimalHave.add(bigDecimalNew)));
				}else if("RYJB@GJ@1".equals(mm.get("RYJB"))){
					if(mmap.getObj5()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj5();
					}
					mmap.setObj5((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("CRYJB1") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("CRYJB1");
					}
					mapGet.put("CRYJB1",(bigDecimalHave.add(bigDecimalNew)));
				}else if("RYJB@GJ@2".equals(mm.get("RYJB"))){
					if(mmap.getObj6()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj6();
					}
					mmap.setObj6((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("CRYJB2") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("CRYJB2");
					}
					mapGet.put("CRYJB2",(bigDecimalHave.add(bigDecimalNew)));
				}else if("RYJB@GJ@3".equals(mm.get("RYJB"))){
					if(mmap.getObj7()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj7();
					}
					mmap.setObj7((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("CRYJB3") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("CRYJB3");
					}
					mapGet.put("CRYJB3",(bigDecimalHave.add(bigDecimalNew)));
				}else if("RYJB@GJ@4".equals(mm.get("RYJB"))){
					if(mmap.getObj8()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj8();
					}
					mmap.setObj8((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("CRYJB4") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("CRYJB4");
					}
					mapGet.put("CRYJB4",(bigDecimalHave.add(bigDecimalNew)));
				}else if("RYJB@GJ@5".equals(mm.get("RYJB"))){
					if(mmap.getObj9()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj9();
					}
					mmap.setObj9((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("CRYJB5") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("CRYJB5");
					}
					mapGet.put("CRYJB5",(bigDecimalHave.add(bigDecimalNew)));
				}else if("RYJB@GJ@6".equals(mm.get("RYJB"))){
					if(mmap.getObj10()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj10();
					}
					mmap.setObj10((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("CRYJB6") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("CRYJB6");
					}
					mapGet.put("CRYJB6",(bigDecimalHave.add(bigDecimalNew)));
				}
				//计算教师的乡村人数荣誉总数-总
				if(mapGet.get("CNum") == null){
					bigDecimalHave = new BigDecimal("0");
				}else{
					bigDecimalHave = (BigDecimal) mapGet.get("CNum");
				}
				mapGet.put("CNum",bigDecimalHave.add(bigDecimalNew));
			}else{
				//城镇
				//城镇荣誉人数---地区
				if(mmap.getObj11()== null){
					bigDecimalHave = new BigDecimal("0");
				}else{
					bigDecimalHave = (BigDecimal) mmap.getObj11();
				}
				mmap.setObj11((bigDecimalHave.add(bigDecimalNew)));
				
				//城镇荣誉级别
				if("null".equals(mm.get("RYJB"))){
					if(mmap.getObj13()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj13();
					}
					mmap.setObj13((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("TNRYJB") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("TNRYJB");
					}
					mapGet.put("TNRYJB",(bigDecimalHave.add(bigDecimalNew)));
				}else if("RYJB@GJ@0".equals(mm.get("RYJB"))){
					if(mmap.getObj14()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj14();
					}
					mmap.setObj14((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("TRYJB0") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("TRYJB0");
					}
					mapGet.put("TRYJB0",(bigDecimalHave.add(bigDecimalNew)));
				}else if("RYJB@GJ@1".equals(mm.get("RYJB"))){
					if(mmap.getObj15()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj15();
					}
					mmap.setObj15((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("TRYJB1") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("TRYJB1");
					}
					mapGet.put("TRYJB1",(bigDecimalHave.add(bigDecimalNew)));
				}else if("RYJB@GJ@2".equals(mm.get("RYJB"))){
					if(mmap.getObj16()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj16();
					}
					mmap.setObj16((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("TRYJB2") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("TRYJB2");
					}
					mapGet.put("TRYJB2",(bigDecimalHave.add(bigDecimalNew)));
				}else if("RYJB@GJ@3".equals(mm.get("RYJB"))){
					if(mmap.getObj17()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj17();
					}
					mmap.setObj17((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("TRYJB3") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("TRYJB3");
					}
					mapGet.put("TRYJB3",(bigDecimalHave.add(bigDecimalNew)));
				}else if("RYJB@GJ@4".equals(mm.get("RYJB"))){
					if(mmap.getObj18()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj18();
					}
					mmap.setObj18((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("TRYJB4") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("TRYJB4");
					}
					mapGet.put("TRYJB4",(bigDecimalHave.add(bigDecimalNew)));
				}else if("RYJB@GJ@5".equals(mm.get("RYJB"))){
					if(mmap.getObj19()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj19();
					}
					mmap.setObj19((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("TRYJB5") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("TRYJB5");
					}
					mapGet.put("TRYJB5",(bigDecimalHave.add(bigDecimalNew)));
				}else if("RYJB@GJ@6".equals(mm.get("RYJB"))){
					if(mmap.getObj20()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj20();
					}
					mmap.setObj20((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("TRYJB6") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("TRYJB6");
					}
					mapGet.put("TRYJB6",(bigDecimalHave.add(bigDecimalNew)));
				}
				//计算教师的城镇人数荣誉总数-总
				if(mapGet.get("TNum") == null){
					bigDecimalHave = new BigDecimal("0");
				}else{
					bigDecimalHave = (BigDecimal) mapGet.get("TNum");
				}
				mapGet.put("TNum",bigDecimalHave.add(bigDecimalNew));
			}
			mapGet.put(areaId, mmap);
		}
		
		return mapGet;
	}
	
	/**
	 * 教师荣誉饼状图
	 * @param mapGet
	 * @return
	 */
	public Map<Object,Object> getTeacherRYCharts(Map<Object, Object> mapGet){
		JSONArray array = new JSONArray();
		JSONObject json = null;
		//城镇
		json = new JSONObject();
		json.put("label", "国家级");
		json.put("value", mapGet.get("TRYJB1"));
		array.add(json);
		json = new JSONObject();
		json.put("label", "省级");
		json.put("value", mapGet.get("TRYJB2"));
		array.add(json);
		json = new JSONObject();
		json.put("label", "地市级");
		json.put("value", mapGet.get("TRYJB3"));
		array.add(json);
		json = new JSONObject();
		json.put("label", "县级");
		json.put("value", mapGet.get("TRYJB4"));
		array.add(json);
		json = new JSONObject();
		json.put("label", "基层单位荣誉称号");
		json.put("value", mapGet.get("TRYJB5"));
		array.add(json);
		json = new JSONObject();
		json.put("label", "国际国外荣誉称号");
		json.put("value", mapGet.get("TRYJB6"));
		array.add(json);
		json = new JSONObject();
		json.put("label", "无");
		json.put("value", mapGet.get("TRYJB0"));
		array.add(json);
		json = new JSONObject();
		json.put("label", "未填报");
		json.put("value", mapGet.get("NTRYJB"));
		array.add(json);
		//计算城镇未获奖人数
		BigDecimal tcount = (BigDecimal) mapGet.get("TCount");
		if(tcount != null){
			if( mapGet.get("TRYJB1") != null){
				tcount = tcount.subtract((BigDecimal) mapGet.get("TRYJB1"));
			}
			if( mapGet.get("TRYJB2") != null){
				tcount = tcount.subtract((BigDecimal) mapGet.get("TRYJB2"));
			}
			if( mapGet.get("TRYJB3") != null){
				tcount = tcount.subtract((BigDecimal) mapGet.get("TRYJB3"));
			}
			if( mapGet.get("TRYJB4") != null){
				tcount = tcount.subtract((BigDecimal) mapGet.get("TRYJB4"));
			}
			if( mapGet.get("TRYJB5") != null){
				tcount = tcount.subtract((BigDecimal) mapGet.get("TRYJB5"));
			}
			if( mapGet.get("TRYJB6") != null){
				tcount = tcount.subtract((BigDecimal) mapGet.get("TRYJB6"));
			}
			if( mapGet.get("TRYJB0") != null){
				tcount = tcount.subtract((BigDecimal) mapGet.get("TRYJB0"));
			}
			if( mapGet.get("NTRYJB") != null){
				tcount = tcount.subtract((BigDecimal) mapGet.get("NTRYJB"));
			}
		}
		json = new JSONObject();
		json.put("label", "未获奖");
		json.put("value", tcount);
		array.add(json);
		mapGet.put("TPieChart", array);
		
		//乡村
		array = new JSONArray();
		json = new JSONObject();
		json.put("label", "国家级");
		json.put("value", mapGet.get("CRYJB1"));
		array.add(json);
		json = new JSONObject();
		json.put("label", "省级");
		json.put("value", mapGet.get("CRYJB2"));
		array.add(json);
		json = new JSONObject();
		json.put("label", "地市级");
		json.put("value", mapGet.get("CRYJB3"));
		array.add(json);
		json = new JSONObject();
		json.put("label", "县级");
		json.put("value", mapGet.get("CRYJB4"));
		array.add(json);
		json = new JSONObject();
		json.put("label", "基层单位荣誉称号");
		json.put("value", mapGet.get("CRYJB5"));
		array.add(json);
		json = new JSONObject();
		json.put("label", "国际国外荣誉称号");
		json.put("value", mapGet.get("CRYJB6"));
		array.add(json);
		json = new JSONObject();
		json.put("label", "无");
		json.put("value", mapGet.get("CRYJB0"));
		array.add(json);
		json = new JSONObject();
		json.put("label", "未填报");
		json.put("value", mapGet.get("CTRYJB"));
		array.add(json);
		//计算城镇未获奖人数
		BigDecimal ccount = (BigDecimal) mapGet.get("CCount");
		if(ccount != null){
			if( mapGet.get("CRYJB1") != null){
				ccount = ccount.subtract((BigDecimal) mapGet.get("CRYJB1"));
			}
			if( mapGet.get("CRYJB2") != null){
				ccount = ccount.subtract((BigDecimal) mapGet.get("CRYJB2"));
			}
			if( mapGet.get("CRYJB3") != null){
				ccount = ccount.subtract((BigDecimal) mapGet.get("CRYJB3"));
			}
			if( mapGet.get("CRYJB4") != null){
				ccount = ccount.subtract((BigDecimal) mapGet.get("CRYJB4"));
			}
			if( mapGet.get("CRYJB5") != null){
				ccount = ccount.subtract((BigDecimal) mapGet.get("CRYJB5"));
			}
			if( mapGet.get("CRYJB6") != null){
				ccount = ccount.subtract((BigDecimal) mapGet.get("CRYJB6"));
			}
			if( mapGet.get("CRYJB0") != null){
				ccount = ccount.subtract((BigDecimal) mapGet.get("CRYJB0"));
			}
			if( mapGet.get("NCRYJB") != null){
				ccount = ccount.subtract((BigDecimal) mapGet.get("NCRYJB"));
			}
		}
		json = new JSONObject();
		json.put("label", "未获奖");
		json.put("value", ccount);
		array.add(json);
		mapGet.put("CPieChart", array);
		
		return mapGet;
	}
	
	/**
	 * 获取文本内容，教师班级学校的数量
	 * @param map
	 * @return
	 */
	public Map<String, Object> getSchoolClassStudentContent(Map<String,Object> map){
		String ssxd = (String) map.get("SSXDmenu");
		String classCount = null;
		String studentCount = null;
		if("ZXX".equals(ssxd)){
			classCount = "sum(nvl(T.CLASS_NUM_XX,0)) + sum(nvl(T.CLASS_NUM_CZ,0)) + sum(nvl(T.CLASS_NUM_GZ,0))";
			studentCount = "sum(nvl(T.STUDENT_NUM_XX,0)) + sum(nvl(T.STUDENT_NUM_CZ,0)) + sum(nvl(T.STUDENT_NUM_GZ,0))";
		}else if("GZ".equals(ssxd)){
			classCount = "sum(nvl(T.CLASS_NUM_GX,0))";
			studentCount = "sum(nvl(T.STUDENT_NUM_GX,0))";
		}else if("TJ".equals(ssxd)){
			classCount = "sum(nvl(T.CLASS_NUM_QT,0))";
			studentCount = "sum(nvl(T.STUDENT_NUM_QT,0))";
		}else if("YEY".equals(ssxd)){
			classCount = "sum(nvl(T.CLASS_NUM_YEY,0))";
			studentCount = "sum(nvl(T.STUDENT_NUM_YEY,0))";
		}else if("ZZ".equals(ssxd)){
			classCount = "sum(nvl(T.CLASS_NUM_ZZ,0))";
			studentCount = "sum(nvl(T.STUDENT_NUM_ZZ,0))";
		}
		map.put("classCount", classCount);
		map.put("studentCount", studentCount);
		Map<String, Object> content = new HashMap<String, Object>();
		List list = (List) getNamedQuery("misBase::BaseKpiItem::getSchoolClassStudentContent",map);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> mm = (Map<String, Object>) iterator.next();
			if("1".equals(mm.get("SFXCXX").toString())){
				//乡村
				content.put("Cschool", mm.get("SCHOOL"));
				content.put("Cclass", mm.get("CLASS"));
				content.put("Cstudent", mm.get("STUDENT"));
			}else{
				content.put("Tschool", mm.get("SCHOOL"));
				content.put("Tclass", mm.get("CLASS"));
				content.put("Tstudent", mm.get("STUDENT"));
			}
		}
		
		return content;
	}
	
	
	/**
	 * 获取优秀教师基本情况统计
	 * @param map
	 * @return
	 */
	public Map<Object, Object> getExcellentTeacherHonorTable(Map<String, Object> map) {
		Map<Object, Object> mapGet = new HashMap<Object, Object>();
		BaseUser baseUser = (BaseUser) map.get("baus");
		if (StringUtils.isNotBlank(baseUser.getProvinceId())) {
			map.put("provinceId", baseUser.getProvinceId());
		}
		if (StringUtils.isNotBlank(baseUser.getCityId())) {
			map.put("cityId", baseUser.getCityId());
		}
		if (StringUtils.isNotBlank(baseUser.getCountyId())) {
			map.put("countyId", baseUser.getCountyId());
		}
		MMap mmap = null;
		//先计算各个地区的人数
		List listCount = (List) getNamedQuery("misBase::BaseKpiItem::getExcellentTeacherCityCount",map);
		//已经存在总人数
		BigDecimal bigDecimalHaveCount = null;
		//新获取的总人数
		BigDecimal bigDecimalNewCount = null;
		for (Iterator iterator = listCount.iterator(); iterator.hasNext();) {
			Map<String, Object> mm = (Map<String, Object>) iterator.next();
			mmap = new MMap();
			bigDecimalNewCount = (BigDecimal) mm.get("COUNT");
			if(bigDecimalNewCount == null){
				bigDecimalNewCount = new BigDecimal("0");
			}
			String areaId = (String) mm.get("AREAID");
			if(areaId == null){
				areaId = "null";
			}
			if (mapGet.keySet().contains(areaId)) {
				// 已经存在该地区，先获取该地区
				mmap = (MMap) mapGet.get(areaId);
			}
			if("XB@GJ@1".equals(mm.get("XB").toString())){
				//性别---男
				if(mmap.getObj1()== null){
					bigDecimalHaveCount = new BigDecimal("0");
				}else{
					bigDecimalHaveCount = (BigDecimal) mmap.getObj1();
				}
				mmap.setObj1((bigDecimalHaveCount.add(bigDecimalNewCount)));
				//计算教师男 总数-总
				if(mapGet.get("MCount") == null){
					bigDecimalHaveCount = new BigDecimal("0");
				}else{
					bigDecimalHaveCount = (BigDecimal) mapGet.get("MCount");
				}
				mapGet.put("MCount",bigDecimalHaveCount.add(bigDecimalNewCount));
			}else if("XB@GJ@2".equals(mm.get("XB").toString())){
				//性别---女
				if(mmap.getObj2()== null){
					bigDecimalHaveCount = new BigDecimal("0");
				}else{
					bigDecimalHaveCount = (BigDecimal) mmap.getObj2();
				}
				mmap.setObj2((bigDecimalHaveCount.add(bigDecimalNewCount)));
				//计算教师女 总数-总
				if(mapGet.get("FMCount") == null){
					bigDecimalHaveCount = new BigDecimal("0");
				}else{
					bigDecimalHaveCount = (BigDecimal) mapGet.get("FMCount");
				}
				mapGet.put("FMCount",bigDecimalHaveCount.add(bigDecimalNewCount));
			}else if("XB@GJ@0".equals(mm.get("XB").toString())){
				//性别---未知
				if(mmap.getObj3()== null){
					bigDecimalHaveCount = new BigDecimal("0");
				}else{
					bigDecimalHaveCount = (BigDecimal) mmap.getObj3();
				}
				mmap.setObj3((bigDecimalHaveCount.add(bigDecimalNewCount)));
				//计算教师男 总数-总
				if(mapGet.get("NCount") == null){
					bigDecimalHaveCount = new BigDecimal("0");
				}else{
					bigDecimalHaveCount = (BigDecimal) mapGet.get("NCount");
				}
				mapGet.put("NCount",bigDecimalHaveCount.add(bigDecimalNewCount));
			}
			mapGet.put(areaId, mmap);
		}
		//已经存在的
		BigDecimal bigDecimalHave = null;
		//新获取的
		BigDecimal bigDecimalNew = null;
		List list = (List) getNamedQuery("misBase::BaseKpiItem::getExcellentTeacherTable",map);
		//==============================================荣誉级别分类
		/*if(!"tt.CXFL".equals(map.get("CXFLRYJB"))){*/
		/**================================================行政区划=======================================**/
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> mm = (Map<String, Object>) iterator.next();
			mmap = new MMap();
			bigDecimalNew = (BigDecimal) mm.get("NUM");
			
			String areaId = (String) mm.get("AREAID");
			if(areaId == null){
				areaId = "null";
			}
			if (mapGet.keySet().contains(areaId)) {
				// 已经存在该地区，先获取该地区
				mmap = (MMap) mapGet.get(areaId);
			}
				//荣誉人数---地区
				if(mmap.getObj4()== null){
					bigDecimalHave = new BigDecimal("0");
				}else{
					bigDecimalHave = (BigDecimal) mmap.getObj4();
				}
				mmap.setObj4((bigDecimalHave.add(bigDecimalNew)));
				//荣誉级别
				if("null".equals(mm.get("RYJB"))){
					if(mmap.getObj5()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj5();
					}
					mmap.setObj5((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("NRYJB") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("NRYJB");
					}
					mapGet.put("NRYJB",(bigDecimalHave.add(bigDecimalNew)));
				}else if("RYJB@GJ@0".equals(mm.get("RYJB"))){
					if(mmap.getObj6()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj6();
					}
					mmap.setObj6((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("RYJB0") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("RYJB0");
					}
					mapGet.put("RYJB0",(bigDecimalHave.add(bigDecimalNew)));
				}else if("RYJB@GJ@1".equals(mm.get("RYJB"))){
					if(mmap.getObj7()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj7();
					}
					mmap.setObj7((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("RYJB1") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("RYJB1");
					}
					mapGet.put("RYJB1",(bigDecimalHave.add(bigDecimalNew)));
				}else if("RYJB@GJ@2".equals(mm.get("RYJB"))){
					if(mmap.getObj8()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj8();
					}
					mmap.setObj8((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("RYJB2") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("RYJB2");
					}
					mapGet.put("RYJB2",(bigDecimalHave.add(bigDecimalNew)));
				}else if("RYJB@GJ@3".equals(mm.get("RYJB"))){
					if(mmap.getObj9()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj9();
					}
					mmap.setObj9((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("RYJB3") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("RYJB3");
					}
					mapGet.put("RYJB3",(bigDecimalHave.add(bigDecimalNew)));
				}else if("RYJB@GJ@4".equals(mm.get("RYJB"))){
					if(mmap.getObj10()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj10();
					}
					mmap.setObj10((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("RYJB4") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("RYJB4");
					}
					mapGet.put("RYJB4",(bigDecimalHave.add(bigDecimalNew)));
				}else if("RYJB@GJ@5".equals(mm.get("RYJB"))){
					if(mmap.getObj11()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj11();
					}
					mmap.setObj11((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("RYJB5") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("RYJB5");
					}
					mapGet.put("RYJB5",(bigDecimalHave.add(bigDecimalNew)));
				}else if("RYJB@GJ@6".equals(mm.get("RYJB"))){
					if(mmap.getObj12()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj12();
					}
					mmap.setObj12((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("RYJB6") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("RYJB6");
					}
					mapGet.put("RYJB6",(bigDecimalHave.add(bigDecimalNew)));
				}
				//计算教师的乡村人数荣誉总数-总
				if(mapGet.get("RYJBNum") == null){
					bigDecimalHave = new BigDecimal("0");
				}else{
					bigDecimalHave = (BigDecimal) mapGet.get("RYJBNum");
				}
				mapGet.put("RYJBNum",bigDecimalHave.add(bigDecimalNew));
		
			mapGet.put(areaId, mmap);
			}
		/*}else{*/
	/**================================================城乡分类=======================================**/
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> mm = (Map<String, Object>) iterator.next();
			mmap = new MMap();
			bigDecimalNew = (BigDecimal) mm.get("NUM");
			
			String areaId = (String) mm.get("AREAID");
			if(areaId == null){
				areaId = "null";
			}
			if (mapGet.keySet().contains(areaId)) {
				// 已经存在该地区，先获取该地区
				mmap = (MMap) mapGet.get(areaId);
			}
				//城乡分类人数---地区
				if(mmap.getObj13()== null){
					bigDecimalHave = new BigDecimal("0");
				}else{
					bigDecimalHave = (BigDecimal) mmap.getObj13();
				}
				mmap.setObj13((bigDecimalHave.add(bigDecimalNew)));
				//城乡分类
				if("111".equals(mm.get("CXFL"))){
					if(mmap.getObj14()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj14();
					}
					mmap.setObj14((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("CXFL111") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("CXFL111");
					}
					mapGet.put("CXFL111",(bigDecimalHave.add(bigDecimalNew)));
				}else if("112".equals(mm.get("CXFL"))){
					if(mmap.getObj15()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj15();
					}
					mmap.setObj15((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("CXFL112") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("CXFL112");
					}
					mapGet.put("CXFL112",(bigDecimalHave.add(bigDecimalNew)));
				}else if("121".equals(mm.get("CXFL"))){
					if(mmap.getObj16()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj16();
					}
					mmap.setObj16((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("CXFL121") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("CXFL121");
					}
					mapGet.put("CXFL121",(bigDecimalHave.add(bigDecimalNew)));
				}else if("122".equals(mm.get("CXFL"))){
					if(mmap.getObj17()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj17();
					}
					mmap.setObj17((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("CXFL122") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("CXFL122");
					}
					mapGet.put("CXFL122",(bigDecimalHave.add(bigDecimalNew)));
				}else if("210".equals(mm.get("CXFL"))){
					if(mmap.getObj18()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj18();
					}
					mmap.setObj18((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("CXFL210") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("CXFL210");
					}
					mapGet.put("CXFL210",(bigDecimalHave.add(bigDecimalNew)));
				}else if("220".equals(mm.get("CXFL"))){
					if(mmap.getObj19()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj19();
					}
					mmap.setObj19((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("CXFL220") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("CXFL220");
					}
					mapGet.put("CXFL220",(bigDecimalHave.add(bigDecimalNew)));
				}else if("123".equals(mm.get("CXFL"))){
					if(mmap.getObj20()== null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal) mmap.getObj20();
					}
					mmap.setObj20((bigDecimalHave.add(bigDecimalNew)));
					
					if(mapGet.get("CXFL123") == null){
						bigDecimalHave = new BigDecimal("0");
					}else{
						bigDecimalHave = (BigDecimal)mapGet.get("CXFL123");
					}
					mapGet.put("CXFL123",(bigDecimalHave.add(bigDecimalNew)));
				}
				//计算教师的城乡分类人数荣誉总数-总
				if(mapGet.get("CXFLNum") == null){
					bigDecimalHave = new BigDecimal("0");
				}else{
					bigDecimalHave = (BigDecimal) mapGet.get("CXFLNum");
				}
				mapGet.put("CXFLNum",bigDecimalHave.add(bigDecimalNew));
		
			mapGet.put(areaId, mmap);
			/*}*/
		}
		return mapGet;
	}
	
	/**
	 * 创建优秀教师金字塔图数据
	 * @return
	 */
	public Map<Object, Object> getPyramidChart(Map<Object, Object> mapGet){
		JSONArray array = new JSONArray();
		JSONObject json = null;
		if("RYJB".equals(mapGet.get("CXFLRYJB"))){
			//荣誉
			json = new JSONObject();
			json.put("label", "国家级");
			json.put("value", mapGet.get("RYJB1"));
			array.add(json);
			json = new JSONObject();
			json.put("label", "省级");
			json.put("value", mapGet.get("RYJB2"));
			array.add(json);
			json = new JSONObject();
			json.put("label", "地市级");
			json.put("value", mapGet.get("RYJB3"));
			array.add(json);
			json = new JSONObject();
			json.put("label", "县级");
			json.put("value", mapGet.get("RYJB4"));
			array.add(json);
			json = new JSONObject();
			json.put("label", "基层单位荣誉");
			json.put("value", mapGet.get("RYJB5"));
			array.add(json);
			json = new JSONObject();
			json.put("label", "国际国外荣誉");
			json.put("value", mapGet.get("RYJB6"));
			array.add(json);
			json = new JSONObject();
			json.put("label", "无");
			json.put("value", mapGet.get("RYJB0"));
			array.add(json);
			json = new JSONObject();
			json.put("label", "未填报");
			json.put("value", mapGet.get("NRYJB"));
			array.add(json);
		}else{
			//城乡
			json = new JSONObject();
			json.put("label", "主城区");
			json.put("value", mapGet.get("CXFL111"));
			array.add(json);
			json = new JSONObject();
			json.put("label", "城乡结合区");
			json.put("value", mapGet.get("CXFL112"));
			array.add(json);
			json = new JSONObject();
			json.put("label", "镇中心区");
			json.put("value", mapGet.get("CXFL121"));
			array.add(json);
			json = new JSONObject();
			json.put("label", "镇乡结合区");
			json.put("value", mapGet.get("CXFL122"));
			array.add(json);
			json = new JSONObject();
			json.put("label", "乡中心区");
			json.put("value", mapGet.get("CXFL210"));
			array.add(json);
			json = new JSONObject();
			json.put("label", "村庄");
			json.put("value", mapGet.get("CXFL220"));
			array.add(json);
			json = new JSONObject();
			json.put("label", "特殊区域");
			json.put("value", mapGet.get("CXFL123"));
			array.add(json);
		}
		
		mapGet.put("pyramidChart",array);
		
		return mapGet;
		
	}
	

	/**
	 * 获取优秀教师基本情况统计
	 * @param map
	 * @return
	 */
	public Page getExcellentTeacherHonorTableSchool(Map<String, Object> map ,int pageSize, int pageNo) {
		Map<Object, Object> mapGet = new HashMap<Object, Object>();
		return getPagedNamedQuery("misBase::BaseKpiItem::getExcellentTeacherTableSchool", pageNo, pageSize, map);
	}
	/**
	 * 获取优秀教师基本情况统计（不分页）
	 * @param map
	 * @return
	 */
	public List getExcellentTeacherHonorTableSchoolNoPage(Map<String, Object> map) {
		List list = (List) getNamedQuery("misBase::BaseKpiItem::getExcellentTeacherTableSchool", map);
		return list;
	}
	
	/**
	 * 创建优秀教师金字塔图数据
	 * @return
	 */
	public Map<String, Object> getPyramidChartSchool(Map<String, Object> map){
		Map<String, Object> chartMap = new HashMap<String, Object>();
		JSONArray ryArray = new JSONArray();
		JSONArray cxArray = new JSONArray();
		JSONObject json = null;
		BigDecimal bigHas = null;
		
		Map<String, Object> mapGet = new HashMap<String, Object>();
		
		List list = (List) getNamedQuery("misBase::BaseKpiItem::getExcellentTeacherPySchool",map);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> mm = (Map<String, Object>) iterator.next();
			String CXFL = (String) mm.get("CXFL");
			String RYJB = (String) mm.get("RYJB");
			BigDecimal bigGet = (BigDecimal) mm.get("NUM");
			
			if(CXFL == null){
				CXFL = "NCXFL";
			}
			
			if(RYJB == null){
				RYJB = "NRYJB";
			}
			
			//城乡分类
			if (mapGet.keySet().contains(CXFL)) {
				// 已经存在,先获取
				bigHas = (BigDecimal) mapGet.get(CXFL);
				if(bigHas == null){
					bigHas = new BigDecimal("0");
				}
				mapGet.put(CXFL, bigHas.add(bigGet));
			}else{
				mapGet.put(CXFL, bigGet);
			}
			
			//荣誉级别
			if (mapGet.keySet().contains(RYJB)) {
				// 已经存在,先获取
				bigHas = (BigDecimal) mapGet.get(RYJB);
				if(bigHas == null){
					bigHas = new BigDecimal("0");
				}
				mapGet.put(RYJB, bigHas.add(bigGet));
			}else{
				mapGet.put(RYJB, bigGet);
			}
		}
		
		
		//荣誉
		json = new JSONObject();
		json.put("label", "国家级");
		json.put("value", mapGet.get("RYJB@GJ@1"));
		ryArray.add(json);
		json = new JSONObject();
		json.put("label", "省级");
		json.put("value", mapGet.get("RYJB@GJ@2"));
		ryArray.add(json);
		json = new JSONObject();
		json.put("label", "地市级");
		json.put("value", mapGet.get("RYJB@GJ@3"));
		ryArray.add(json);
		json = new JSONObject();
		json.put("label", "县级");
		json.put("value", mapGet.get("RYJB@GJ@4"));
		ryArray.add(json);
		json = new JSONObject();
		json.put("label", "基层单位荣誉");
		json.put("value", mapGet.get("RYJB@GJ@5"));
		ryArray.add(json);
		json = new JSONObject();
		json.put("label", "国际国外荣誉");
		json.put("value", mapGet.get("RYJB@GJ@8"));
		ryArray.add(json);
		json = new JSONObject();
		json.put("label", "无");
		json.put("value", mapGet.get("RYJB@GJ@0"));
		ryArray.add(json);
		json = new JSONObject();
		json.put("label", "未填报");
		json.put("value", mapGet.get("NRYJB"));
		ryArray.add(json);
		//城乡
		json = new JSONObject();
		json.put("label", "主城区");
		json.put("value", mapGet.get("111"));
		cxArray.add(json);
		json = new JSONObject();
		json.put("label", "城乡结合区");
		json.put("value", mapGet.get("112"));
		cxArray.add(json);
		json = new JSONObject();
		json.put("label", "镇中心区");
		json.put("value", mapGet.get("121"));
		cxArray.add(json);
		json = new JSONObject();
		json.put("label", "镇乡结合区");
		json.put("value", mapGet.get("122"));
		cxArray.add(json);
		json = new JSONObject();
		json.put("label", "乡中心区");
		json.put("value", mapGet.get("210"));
		cxArray.add(json);
		json = new JSONObject();
		json.put("label", "村庄");
		json.put("value", mapGet.get("220"));
		cxArray.add(json);
		json = new JSONObject();
		json.put("label", "特殊区域");
		json.put("value", mapGet.get("123"));
		cxArray.add(json);
		
		chartMap.put("ryArray", ryArray);
		chartMap.put("cxArray", cxArray);
		return chartMap;
		
	}
	
	/**
	 * 获取文本内容，教师班级学校的数量
	 * @param map
	 * @return
	 */
	public Map<String, Object> getSchoolClassStudentContentSchool(Map<String,Object> mapGet){
		List list = (List) getNamedQuery("misBase::BaseKpiItem::getSchoolClassStudentContentSchool",mapGet);
		Map<String, Object> m = new HashMap<String, Object>();
		BigDecimal classCount = new BigDecimal("0");
		BigDecimal studentCount = new BigDecimal("0");
		BigDecimal teacherCount = new BigDecimal("0");
		if(list.size() > 0){
			Map<String, Object> map = (Map<String, Object>) list.get(0);
			String ssxd = (String) map.get("SSXD");
			if("ZXX".equals(ssxd)){
				classCount = (StringUtil.getRightBigDecimalValue((BigDecimal) map.get("CLASS_NUM_XX"))).add(StringUtil.getRightBigDecimalValue((BigDecimal) map.get("CLASS_NUM_CZ"))).add(StringUtil.getRightBigDecimalValue((BigDecimal) map.get("CLASS_NUM_GZ")));
				studentCount = (StringUtil.getRightBigDecimalValue((BigDecimal) map.get("STUDENT_NUM_XX"))).add(StringUtil.getRightBigDecimalValue((BigDecimal) map.get("STUDENT_NUM_CZ"))).add(StringUtil.getRightBigDecimalValue((BigDecimal) map.get("STUDENT_NUM_GZ")));
			}else if("GZ".equals(ssxd)){
				classCount = (BigDecimal) map.get("CLASS_NUM_GX");
				studentCount = (BigDecimal) map.get("STUDENT_NUM_GX");
			}else if("TJ".equals(ssxd)){
				classCount = (BigDecimal) map.get("CLASS_NUM_QT");
				studentCount = (BigDecimal) map.get("STUDENT_NUM_QT");
			}else if("YEY".equals(ssxd)){
				classCount = (BigDecimal) map.get("CLASS_NUM_YEY");
				studentCount = (BigDecimal) map.get("STUDENT_NUM_YEY");
			}else if("ZZ".equals(ssxd)){
				classCount = (BigDecimal) map.get("CLASS_NUM_ZZ");
				studentCount = (BigDecimal) map.get("STUDENT_NUM_ZZ");
			}else if("GX".equals(ssxd)){
				classCount = (BigDecimal) map.get("CLASS_NUM_GX");
				studentCount = (BigDecimal) map.get("STUDENT_NUM_GX");
			}
		}
		list = (List) getNamedQuery("misBase::BaseKpiItem::getTeacherCountSchool",mapGet);
		if(list.size() > 0){
			Map<String, Object> map = (Map<String, Object>) list.get(0);
			teacherCount = (BigDecimal) map.get("NUM");
		}
		m.put("classCount", classCount);
		m.put("studentCount", studentCount);
		m.put("teacherCount", teacherCount);
		return m;
		
	}
	
	
}
