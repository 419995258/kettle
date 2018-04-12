/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2015
 */

package org.my431.base.web;

import java.util.Map;

import org.apache.log4j.Logger;
import org.my431.base.model.StatLoginData;
import org.my431.base.services.BaseAreaManager;
import org.my431.base.services.BaseSchoolManager;
import org.my431.base.services.CacheBaseAreaManager;
import org.my431.base.services.StatLoginDataManager;
import org.my431.base.services.StatSchoolManager;
import org.my431.platform.web.StrutsTreeEntityAction;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author hyl
 * @version 1.0
 * @since 1.0
 * 功能：总体情况报表，项目与单体
 */


public class StatTotalConditionOfProAndItemAction extends StrutsTreeEntityAction<StatLoginData,StatLoginDataManager> implements Preparable,ModelDriven{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log =  Logger.getLogger(StatTotalConditionOfProAndItemAction.class);
	
	//forward paths
	protected static final String TOBASICREPORTINDEX_JSP = "/base/statProAndItem/tobasicreportindex.jsp";
	protected static final String PROJECTCOUNTS_JSP = "/base/statProAndItem/projectcounts.jsp";
	protected static final String PROITEMSECOND_JSP = "/base/statProAndItem/proitemsecond.jsp";
	protected static final String PROITEMSECONDOFSIMPLE_JSP = "/base/statProAndItem/proitemsecondofsimple.jsp";
	protected static final String PROJECTESTABLISHOFSIMPLE_JSP = "/base/statProAndItem/projectestablishofsimple.jsp";
	protected static final String PROITEMTHIRD_JSP = "/base/statProAndItem/proitemthird.jsp";
	protected static final String PROITEMFOURTH_JSP = "/base/statProAndItem/proitemfourth.jsp";
	protected static final String ND_JH_ITEM_STAT = "/base/statProAndItem/ndJhItemStat.jsp";
	protected static final String EXPORT_ND_JH_ITEM_STAT = "/base/statProAndItem/excel/excel_ndJhItemStat.jsp";
	//统计-基础报表-项目学校数-按学校类型
	protected static final String BASICPROSCHOOLBYSCHOOLTYPE_JSP = "/base/statProAndItem/basicproschoolbyschooltype.jsp";
	//统计-基础报表-项目学校数-按城乡类型
	protected static final String BASICPROSCHOOLBYSCHOOLDX_JSP = "/base/statProAndItem/basicproschoolbyschooldx.jsp";
	//统计-基础报表-单体数量统计表简表
	protected static final String SCHOOLITEMCOUNTSOFSIMPLE_JSP = "/base/statProAndItem/schoolitemcountsofsimple.jsp";
	//统计-基础报表-单体数量统计表详表
	protected static final String SCHOOLITEMCOUNTSOFDETAIL_JSP = "/base/statProAndItem/schoolitemcountsofdetail.jsp";
	//统计-单体报表-单体统计表
	protected static final String ITEMSTAT_JSP = "/base/statProAndItem/itemstat.jsp";
	protected static final String ITEMSTAT_EXCEL_JSP = "/base/statProAndItem/excel/excel_itemstat.jsp";
	//EXCEL
	protected static final String PROJECTCOUNTS_EXCEL_JSP = "/base/statProAndItem/excel/excel_projectcounts.jsp";
	protected static final String PROITEMSECOND_EXCEL_JSP = "/base/statProAndItem/excel/excel_proitemsecond.jsp";
	protected static final String PROITEMSECONDOFSIMPLE_EXCEL_JSP = "/base/statProAndItem/excel/excel_proitemsecondofsimple.jsp";
	protected static final String PROJECTESTABLISHOFSIMPLE_EXCEL_JSP = "/base/statProAndItem/excel/excel_projectestablishofsimple.jsp";
	protected static final String PROITEMTHIRD_EXCEL_JSP = "/base/statProAndItem/excel/excel_proitemthird.jsp";
	protected static final String PROITEMFOURTH_EXCEL_JSP = "/base/statProAndItem/excel/excel_proitemfourth.jsp";
	protected static final String BASICPROSCHOOLBYSCHOOLTYPE_EXCEL_JSP = "/base/statProAndItem/excel/excel_basicproschoolbyschooltype.jsp";
	protected static final String BASICPROSCHOOLBYSCHOOLDX_EXCEL_JSP = "/base/statProAndItem/excel/excel_basicproschoolbyschooldx.jsp";
	
	protected static final String SCHOOLITEMCOUNTSOFSIMPLE_EXCEL_JSP = "/base/statProAndItem/excel/excel_schoolitemcountsofsimple.jsp";
	
	protected static final String SCHOOLITEMCOUNTSOFDETAIL_EXCEL_JSP = "/base/statProAndItem/excel/excel_schoolitemcountsofdeail.jsp";
	protected static final String ITEM_TJ_JSP="/base/statProAndItem/item_tj.jsp";
	protected static final String ITEM_TJ_V2_JSP="/base/statProAndItem/item_tj_v2.jsp";
	protected static final String ITEM_TJ_EXPORT_JSP="/base/statProAndItem/item_tj_export.jsp";
	protected static final String ITEM_TJ_EXPORT_V2_JSP="/base/statProAndItem/item_tj_export_v2.jsp";
	private BaseAreaManager baseAreaManager;
	private BaseSchoolManager baseSchoolManager;
	private StatSchoolManager statSchoolManager;
	private CacheBaseAreaManager cacheBaseAreaManager;
	
	public void setStatSchoolManager(StatSchoolManager statSchoolManager) {
		this.statSchoolManager = statSchoolManager;
	}

	public BaseAreaManager getBaseAreaManager() {
		return baseAreaManager;
	}

	public void setBaseAreaManager(BaseAreaManager baseAreaManager) {
		this.baseAreaManager = baseAreaManager;
	}
	
	public BaseSchoolManager getBaseSchoolManager() {
		return baseSchoolManager;
	}

	public void setBaseSchoolManager(BaseSchoolManager baseSchoolManager) {
		this.baseSchoolManager = baseSchoolManager;
	}
	
	
	public CacheBaseAreaManager getCacheBaseAreaManager() {
		return cacheBaseAreaManager;
	}

	public void setCacheBaseAreaManager(CacheBaseAreaManager cacheBaseAreaManager) {
		this.cacheBaseAreaManager = cacheBaseAreaManager;
	}

	/**
	 * map工具
	 * @param map
	 * @param key
	 * @param value
	 * @return
	 */
	public Map<String, Double> setMapValues(Map<String, Double> map, String key, Object obj) {
		if(obj!=null&&!obj.toString().trim().equals("")){
			Double value=Double.valueOf(obj.toString());
			if(map.containsKey(key)){
				value=value+map.get(key);
			}
			map.put(key, value);
		}
		return map;
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}