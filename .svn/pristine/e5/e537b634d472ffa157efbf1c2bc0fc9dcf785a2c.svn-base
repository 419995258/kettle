package org.my431.base.web;

import org.my431.base.model.BaseSchool;
import org.my431.base.services.BaseAreaManager;
import org.my431.base.services.BaseSchoolManager;
import org.my431.base.services.StatSchoolManager;
import org.my431.platform.web.StrutsTreeEntityAction;
import org.my431.plugin.redis.services.RedisManager;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class StatSchoolAction  extends StrutsTreeEntityAction<BaseSchool,BaseSchoolManager> implements Preparable,ModelDriven {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String cacheBaseStartSchoolKpi="global.base.BaseStartSchoolKpi.id.";
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	protected static final String QUE_KOU_SB_JN_LIST= "//base/StatSchool/que_kou_sb_jn_list.jsp";//基年
	protected static final String QUE_KOU_TJ_JN_LIST= "//base/StatSchool/que_kou_tj_jn_list.jsp";
	protected static final String QUE_KOU_SB_MQ_LIST= "//base/StatSchool/que_kou_sb_mq_list.jsp";//目前
	protected static final String QUE_KOU_TJ_MQ_LIST= "//base/StatSchool/que_kou_tj_mq_list.jsp";
	protected static final String BAO_BIAO_INDEX_JSP="/base/StatSchool/bao_biao_index.jsp";
	protected static final String QUE_KOU_SB_JN_LIST_PRINT= "//base/StatSchool/que_kou_sb_jn_list_print.jsp";//基年
	protected static final String QUE_KOU_TJ_JN_LIST_PRINT= "//base/StatSchool/que_kou_tj_jn_list_print.jsp";
	protected static final String QUE_KOU_SB_MQ_LIST_PRINT= "//base/StatSchool/que_kou_sb_mq_list_print.jsp";//目前
	protected static final String QUE_KOU_TJ_MQ_LIST_PRINT= "//base/StatSchool/que_kou_tj_mq_list_print.jsp";
	
	//WANGZHEN 统计图
	protected static final String SCHOOL_CHART_INDEX_JSP= "/base/StatSchool/chart/school_chart_index.jsp";
	protected static final String SCHOOL_DETAIL_INDEX_JSP= "/base/StatSchool/chart/school_detail_index.jsp";
	protected static final String EXPORT_DB_JSP= "/base/StatSchool/chart/exportExcel/export_area_db.jsp";
	
	protected static final String BX_HUIZONG_PRINT_JSP= "//base/StatSchool/bx_huizong_print.jsp";
	protected static final String BX_HUIZONG_JSP= "//base/StatSchool/bx_huizong.jsp";
	protected static final String XXGK_LB_TJ_JSP="//base/StatSchool/xxgk_lb_tj.jsp";
	protected static final String XXGK_JZG_TJ_JSP="//base/StatSchool/xxgk_jzg_tj.jsp";
	protected static final String XXGK_XS_TJ_JSP="//base/StatSchool/xxgk_xs_tj.jsp";
	protected static final String XXGK_BJ_TJ_JSP="//base/StatSchool/xxgk_bj_tj.jsp";
	protected static final String XXGK_FWBJ_TJ_JSP="//base/StatSchool/xxgk_fwbj_tj.jsp";
	protected static final String XXGK_STGC_TJ_JSP="//base/StatSchool/xxgk_stgc_tj.jsp";

	//校舍建设情况汇总表
	protected static final String XSJSQK_JSP="//base/StatSchool/xsjsqk.jsp";
	protected static final String XSJSQK_EXCEL_JSP="//base/StatSchool/xsjsqk_excel.jsp";
	//设备购置情况汇总表
	protected static final String SBGZQK_JSP="//base/StatSchool/sbgzqk.jsp";
	protected static final String SBGZQK_EXCEL_JSP="//base/StatSchool/sbgzqk_excel.jsp";
	//办学条件汇总
	protected static final String BXTJHZ_TJ_JSP="//base/StatSchool/bxtjhztj.jsp";
	protected static final String BXTJHZ_SB_JSP="//base/StatSchool/bxtjhzsb.jsp";
	protected static final String BXTJHZ_TJ_EXCEL_JSP="//base/StatSchool/bxtjhz_tj_excel.jsp";
	protected static final String BXTJHZ_SB_EXCEL_JSP="//base/StatSchool/bxtjhz_sb_excel.jsp";
	
	

	protected static final String ZJ_QK_TJ_JSP="//base/StatSchool/zj_qk_tj.jsp";
	protected static final String ZJ_QK_SB_JSP="//base/StatSchool/zj_qk_sb.jsp";
	protected static final String ZJ_QK_TJ_PRINT_JSP="//base/StatSchool/zj_qk_tj_print.jsp";
	protected static final String ZJ_QK_SB_PRINT_JSP="//base/StatSchool/zj_qk_sb_print.jsp";
	private RedisManager redisManager;
	private BaseSchool baseSchool;
	java.lang.String id = null;
	
    private BaseSchoolManager baseSchoolManager;
	private StatSchoolManager statSchoolManager;
	private BaseAreaManager baseAreaManager;
	public void setStatSchoolManager(StatSchoolManager statSchoolManager) {
		this.statSchoolManager = statSchoolManager;
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseAreaManager(BaseAreaManager manager) {
		this.baseAreaManager = manager;
	}

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			baseSchool = new BaseSchool();
		} else {
			baseSchool = (BaseSchool)baseSchoolManager.get(id);
		}
	}
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseSchoolManager(BaseSchoolManager manager) {
		this.baseSchoolManager = manager;
	}	
	public RedisManager getRedisManager() {
		return redisManager;
	}
	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}
	

}
