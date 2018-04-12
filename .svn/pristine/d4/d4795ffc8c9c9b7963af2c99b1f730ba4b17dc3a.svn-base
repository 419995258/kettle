package org.my431.taglib;

import java.sql.Clob;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.my431.base.json.JsonUtil;
import org.my431.base.model.BaseArea;
import org.my431.base.model.BaseCreditProject;
import org.my431.base.model.BaseKpiGroup;
import org.my431.base.model.BaseKpiItem;
import org.my431.base.model.BaseModule;
import org.my431.base.model.BaseProperties;
import org.my431.base.model.BaseRole;
import org.my431.base.model.BaseSchool;
import org.my431.base.model.BaseUrl;
import org.my431.base.model.BaseUser;
import org.my431.base.services.BaseBusinessStepManager;
import org.my431.base.services.BaseCreditProjectManager;
import org.my431.base.services.BaseKpiGroupManager;
import org.my431.base.services.BaseModuleManager;
import org.my431.base.services.BaseSchoolManager;
import org.my431.base.services.BaseUserManager;
import org.my431.base.services.CacheBaseAreaManager;
import org.my431.base.services.CacheBasePropertiesManager;
import org.my431.base.services.CacheBaseSchoolManager;
import org.my431.platform.utils.ContextUtils;
import org.my431.plugin.redis.services.RedisManager;
import org.my431.util.DateFormater;
import org.my431.util.HtmlUtil;
import org.my431.util.MMap;
import org.my431.util.StringUtil;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.sun.java_cup.internal.runtime.virtual_parse_stack;

import edu.emory.mathcs.backport.java.util.Collections;

public class My431Function {
	private static String preRoleMapKey = "global.base.BaseRole.map.key.";
	private static String prePropertiesMapKey = "global.base.BaseProperties.key.";
	private static String preBaseSchoolIsWeak = "global.base.BaseSchool.id.IsWeak.";
	public static String listPreKey = "global.base.BaseUrl.key.id.";//模块对应url的list
	public static String urlObjectKey="global.base.BaseUrl.key.urlId.";//单独一个baseurl
	public static String schoolUserPreKey = "global.base.BaseUser.schoolId.";
	private static String ProjectPrekey="global.base.BaseCreditProject.id.";
	private static String BaseCreditTransRulePrekey="global.base.BaseCreditTransRule.id.";
	private static List<String> boshiList = null;
	private static List<String> shuoshiList = null;
	private static List<String> benkeList = null;
	private static List<String> zhuankeList = null;
	private static List<String> gaozhongList = null;
	//根据以下方法快速定位学历
	static{
		boshiList = new ArrayList<String>();
		boshiList.add("XL@GJ@11");boshiList.add("XL@GJ@12");boshiList.add("XL@GJ@13");
		shuoshiList = new ArrayList<String>();
		shuoshiList.add("XL@GJ@14");shuoshiList.add("XL@GJ@15");shuoshiList.add("XL@GJ@16");
		shuoshiList.add("XL@GJ@17");shuoshiList.add("XL@GJ@18");shuoshiList.add("XL@GJ@19");
		benkeList = new ArrayList<String>();
		benkeList.add("XL@GJ@21");benkeList.add("XL@GJ@22");benkeList.add("XL@GJ@23");benkeList.add("XL@GJ@28");
		zhuankeList = new ArrayList<String>();
		zhuankeList.add("XL@GJ@31");zhuankeList.add("XL@GJ@32");zhuankeList.add("XL@GJ@33");zhuankeList.add("XL@GJ@34");
		zhuankeList.add("XL@GJ@35");
		zhuankeList.add("XL@GJ@91");zhuankeList.add("XL@GJ@92");zhuankeList.add("XL@GJ@93");
		zhuankeList.add("XL@GJ@41");zhuankeList.add("XL@GJ@42");zhuankeList.add("XL@GJ@43");
		gaozhongList = new ArrayList<String>();
		gaozhongList.add("XL@GJ@44");gaozhongList.add("XL@GJ@45");gaozhongList.add("XL@GJ@46");gaozhongList.add("XL@GJ@47");
		gaozhongList.add("XL@GJ@48");gaozhongList.add("XL@GJ@49");gaozhongList.add("XL@GJ@61");gaozhongList.add("XL@GJ@62");
		gaozhongList.add("XL@GJ@63");gaozhongList.add("XL@GJ@71");gaozhongList.add("XL@GJ@73");gaozhongList.add("XL@GJ@81");
		gaozhongList.add("XL@GJ@83");gaozhongList.add("XL@GJ@99");gaozhongList.add("XL@GJ@0");
	}
	
	/**
	 * 功能描述 ：传入属性值，得到属性值。<br>
	 * 
	 * 
	 * */
	public static String getValueByCode(String code) {
		if (code != null && !code.trim().equals("")) {
			RedisManager redisManager = (RedisManager) org.my431.platform.utils.ContextUtils.getBean("redisManager");
			Object v = redisManager.getOValue(prePropertiesMapKey + code);
			if (v != null) {
				return ((BaseProperties) v).getPropertyValue();
			} else {
				return "&nbsp;";
			}
		} else {
			return "&nbsp;";
		}

	}
	/**通过id获得BaseUrl对象*/
	public static BaseUrl getBaseUrlByUrlId(String urlId) {
		if (urlId != null && !urlId.trim().equals("")) {
			RedisManager redisManager = (RedisManager) org.my431.platform.utils.ContextUtils.getBean("redisManager");
			Object v = redisManager.getOValue(urlObjectKey+urlId);
			if (v != null) {
				return (BaseUrl) v;
			}else {
				return new BaseUrl();
			} 
		} else {
			return new BaseUrl();
		}
	}
	/**通过id获得BaseModule对象*/
	public static BaseModule getBaseModuleBymId(String mId) {
		if (mId != null && !mId.trim().equals("")) {
			BaseModuleManager baseModuleManager = (BaseModuleManager) org.my431.platform.utils.ContextUtils.getBean("baseModuleManager");
			BaseModule v = baseModuleManager.getBaseModuleId(mId);
			if (v != null) {
				return v;
			}else {
				return new BaseModule();
			} 
		} else {
			return new BaseModule();
		}
	}
	
	public static String getAreaNameById(String id) {
		if (id != null && !id.trim().equals("")) {
			BaseArea area = CacheBaseAreaManager.getNodeById(id);
			if (area != null) {
				// Map<String,GeneralTreeNode>
				// ht=McBaseAreaManager.getAllAreaMap();
				if (area.getAreaCode().length() == 3) {
					// return ht.get(area.getAreaCode()).getNodeName();
					return CacheBaseAreaManager.getNodeByCode(area.getAreaCode()).getAreaName();
				} else if (area.getAreaCode().length() == 6) {
					// return ht.get(area.getAreaCode().substring(0,
					// 3)).getNodeName()+"-"+ht.get(area.getAreaCode()).getNodeName();
					return CacheBaseAreaManager.getNodeByCode(area.getAreaCode().substring(0, 3)).getAreaName() + "-" + CacheBaseAreaManager.getNodeByCode(area.getAreaCode()).getAreaName();
				} else if (area.getAreaCode().length() == 9) {
					// return ht.get(area.getAreaCode().substring(0,
					// 3)).getNodeName()+"-"+ht.get(area.getAreaCode().substring(0,
					// 6)).getNodeName()+"-"+ht.get(area.getAreaCode()).getNodeName();
					return CacheBaseAreaManager.getNodeByCode(area.getAreaCode().substring(0, 3)).getAreaName() + "-" + CacheBaseAreaManager.getNodeByCode(area.getAreaCode().substring(0, 6)).getAreaName() + "-" + CacheBaseAreaManager.getNodeByCode(area.getAreaCode()).getAreaName();
				} else if (area.getAreaCode().equals("-1")) {// 全国
					// return ht.get(area.getAreaCode()).getNodeName();
					return CacheBaseAreaManager.getNodeByCode(area.getAreaCode()).getAreaName();
				} else {
					return "&nbsp;";
				}
			} else {
				return "&nbsp;";
			}
		} else {
			return "&nbsp;";
		}

	}

	public static BaseSchool getSchoolById(String id) {
		if (id != null && !id.trim().equals("")) {
			Object v = CacheBaseSchoolManager.getSchool(id);
			if (v != null) {
				return ((BaseSchool) v);
			} else {
				BaseSchoolManager bm = (BaseSchoolManager) ContextUtils.getBean("baseSchoolManager");
				BaseSchool bs = bm.get(id);
				if (bs != null) {
					CacheBaseSchoolManager.setSchool(id, bs);
					return bs;
				} else {
					return null;
				}
			}
		} else {
			return null;
		}
	}
	
	public static String getRoleByCode(String code) {
		if (code != null && !code.trim().equals("")) {
			RedisManager redisManager = (RedisManager) org.my431.platform.utils.ContextUtils.getBean("redisManager");
			Object v = redisManager.getOValue(preRoleMapKey + code);
			if (v != null) {
				return ((BaseRole) v).getRoleName();
			} else {
				return "&nbsp;";
			}
		} else {
			return "&nbsp;";
		}
	}
	
	public static String getSchoolNameById(String id) {
		if (id != null && !id.trim().equals("")) {
			Object v = CacheBaseSchoolManager.getSchool(id);
			if (v != null) {
				return ((BaseSchool) v).getSchoolName();
			} else {
				BaseSchoolManager bm = (BaseSchoolManager) ContextUtils.getBean("baseSchoolManager");
				BaseSchool bs = bm.get(id);
				if (bs != null) {
					CacheBaseSchoolManager.setSchool(id, bs);
					return bs.getSchoolName();
				} else {
					return "";
				}
			}
		} else {
			return "";
		}
	}
	
	public static BaseArea getAreaByIdOne(String id) {
		if (id != null && !id.trim().equals("")) {
			BaseArea area = CacheBaseAreaManager.getNodeById(id);
			if (area != null) {
				if (area.getAreaCode().length() > 0) {
					return CacheBaseAreaManager.getNodeByCode(area.getAreaCode());
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}

	}
	
	public static List<BaseUrl> getBaseUrlList(String mId) {
		if (mId != null && !mId.trim().equals("")) {
			RedisManager redisManager = (RedisManager) org.my431.platform.utils.ContextUtils.getBean("redisManager");
			Object v = redisManager.getOValue(listPreKey + mId);
			if (v != null) {
				return (List<BaseUrl>) v;
			} else {
				return null;
			}
		} else {
			return null;
		}

	}
	
	public static List<BaseProperties> getPropertiesByGroupKey(String groupKey) {
		return CacheBasePropertiesManager.getPropertiesByGroupKey(groupKey);
	}
	

	public static BaseArea getAreaByCodeOne(String code) {
		if (code != null && !code.trim().equals("")) {
			BaseArea area = CacheBaseAreaManager.getNodeByCode(code);
			return area;
		} else {
			return null;
		}

	}
	
	public static Object getSqlUseNum(String id, Integer d) {
		RedisManager redisManager = (RedisManager) org.my431.platform.utils.ContextUtils.getBean("redisManager");
		Object o = redisManager.getOValue(id + "::" + DateFormater.DateToString(DateFormater.getAfterDay(new Date(), d), "yyyyMMdd"));
		if (o != null) {
			return o;
		} else {
			return 0;
		}
	}

	/**
	 * 获得学分项目
	 * @author 90
	 * @date    2017-6-8
	 * @param id
	 * @return
	 */
	public static BaseCreditProject getBaseCreditProject(String id){
		BaseCreditProjectManager baseCreditProjectManager=(BaseCreditProjectManager)org.my431.platform.utils.ContextUtils.getBean("baseCreditProjectManager");
		BaseCreditProject bp=baseCreditProjectManager.getCacheBaseCreditProject(id);
		if(bp==null){
			bp=baseCreditProjectManager.reload(id);
		}
		return bp;
	}
	
	/**
	 * 自定义表达式，对象转成json
	 * @author 90
	 * @date    2017-6-14
	 * @param obj
	 * @return
	 */
	public static String toJsonString(Object obj){
        // 将java对象转换为json对象
        JSONObject json = JSONObject.fromObject(obj);
        String str = json.toString();
        return str;
    }
	
	/**
	 * 获取用户
	 * @author 90
	 * @date    2017-6-16
	 * @param userId
	 * @return
	 */
	public static BaseUser getUserById(String userId){
		BaseUser user= null;
		if(userId!=null&&!"".equals(userId)){
			RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
			user= redisManager.getRedisUser(userId);
		}
		return user;
	}
	
	/**
	 * 截取字符串
	 * @author 90
	 * @date    2017-6-16
	 * @param str
	 * @param ln
	 * @return
	 */
	public static String getTitle(String str,Integer ln) {
		return StringUtil.getTitle(str,ln);
	}
	public static String getTitle2(String str,Integer ln) {
		return StringUtil.getTitle2(str,ln);
	}
	
	/**
	 * 获得学分登记进度
	 * @author 90
	 * @date    2017-6-20
	 * @param objectId
	 * @return
	 */
	public static String getXfDjJd(String objectId) {
		String str="";
		BaseBusinessStepManager bsManager=(BaseBusinessStepManager) ContextUtils.getBean("baseBusinessStepManager");
		Map<String,Integer> jdMap=bsManager.getStepStatus(objectId);
		String title="";
		if(jdMap.isEmpty()){
			title="未申请";
			str="<span title='"+title+"'>灰</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span>";
		}else{
			if(jdMap.containsKey("step.type.AnnualCredit.Record")){
				if(jdMap.get("step.type.AnnualCredit.Record")==1){
					title="备案通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span>";
				}else{
					title="备案未通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>红</span>";
				}
			}else if(jdMap.containsKey("step.type.AnnualCredit.Audit")){
				if(jdMap.get("step.type.AnnualCredit.Audit")==1){
					title="审核通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>灰</span>";
				}else{
					title="审核未通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>红</span><span title='"+title+"'>灰</span>";
				}
			}else if(jdMap.containsKey("step.type.AnnualCredit.Registration")){
				if(jdMap.get("step.type.AnnualCredit.Registration")==1){
					title="登记通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span>";
				}else{
					title="登记未通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>红</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span>";
				}
			}else if(jdMap.containsKey("step.type.AnnualCredit.Check")){
				if(jdMap.get("step.type.AnnualCredit.Check")==1){
					title="审查通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span>";
				}else{
					title="审查未通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>红</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span>";
				}
			}else{
				title="已申请";
				str="<span title='"+title+"'>绿</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span>";
			}
		}
		
		return str;
	}
	
	
	/**
	 * 获得教师资格注册进度
	 * @param objectId
	 * @return
	 */
	public static String getTeacherCerifiyJD(String objectId) {
		String str="";
		BaseBusinessStepManager bsManager=(BaseBusinessStepManager) ContextUtils.getBean("baseBusinessStepManager");
		Map<String,Integer> jdMap = new HashMap<String, Integer>();
		if(StringUtils.isNotEmpty(objectId)){
			jdMap=bsManager.getStepStatus(objectId);
		}
		String title="";
		if(jdMap.isEmpty()){
			title="未申请";
			str="<span title='"+title+"'>灰</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span>";
		}else{
			if(jdMap.containsKey("step.type.1stReg.FinalReview") ){
				if(jdMap.get("step.type.1stReg.FinalReview")==1 ){
					title="终审通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span>";
				}else{
					title="终审未通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>红</span>";
				}
			}else if(jdMap.containsKey("step.type.1stReg.2ndReview")){
				if(jdMap.get("step.type.1stReg.2ndReview")==1 ){
					title="复审通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>灰</span>";
				}else{
					title="复审未通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>红</span><span title='"+title+"'>灰</span>";
				}
			}else if(jdMap.containsKey("step.type.1stReg.1stReview") ){
				if(jdMap.get("step.type.1stReg.1stReview")==1 ){
					title="初审通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span>";
				}else{
					title="初审未通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>红</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span>";
				}
			}else if(jdMap.containsKey("step.type.1stReg.Submit")){
				if(jdMap.get("step.type.1stReg.Submit")==1){
					title="报送通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span>";
				}else{
					title="报送未通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>红</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span>";
				}
				//TODO 定期
			}else if( jdMap.containsKey("step.type.RegularReg.FinalReview")){
				if(jdMap.get("step.type.RegularReg.FinalReview")==1){
					title="终审通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span>";
				}else{
					title="终审未通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>红</span>";
				}
			}else if( jdMap.containsKey("step.type.RegularReg.2ndReview")){
				if(jdMap.get("step.type.RegularReg.2ndReview")==1){
					title="复审通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>灰</span>";
				}else{
					title="复审未通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>红</span><span title='"+title+"'>灰</span>";
				}
			}else if( jdMap.containsKey("step.type.RegularReg.1stReview")){
				if( jdMap.get("step.type.RegularReg.1stReview")==1){
					title="初审通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span>";
				}else{
					title="初审未通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>红</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span>";
				}
			}else if( jdMap.containsKey("step.type.RegularReg.Submit")){
				if(jdMap.get("step.type.RegularReg.Submit")==1){
					title="报送通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>绿</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span>";
				}else{
					title="报送未通过";
					str="<span title='"+title+"'>绿</span><span title='"+title+"'>红</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span>";
				}
			}else{
				title="已申请";
				str="<span title='"+title+"'>绿</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span><span title='"+title+"'>灰</span>";
			}
		}
		
		return str;
	}
	/**
	 * 
	* @Description: 获取规则结果类型
	* @author hyl     
	* @date 2017-6-29 下午4:34:53  
	* @version V1.0 
	* @author user
	 */
	public static String getRResultTypeCode(String proLevel,String proType) {
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		Object obj = redisManager.getOValue(BaseCreditTransRulePrekey+proLevel+"."+proType);
		return obj==null?"":obj.toString();
	}
	/**
	 * 不再使用
	* @Description: 获取规则结果类型
	* @author hyl     
	* @date 2017-6-29 下午4:34:53  
	* @version V1.0 
	* @author user
	 */
	@Deprecated
	public static String getRResultTypeName(String proLevel,String proType) {
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		Object obj = redisManager.getOValue(BaseCreditTransRulePrekey+proLevel+"."+proType);
		String str = "";
		if (obj != null) {
			str = getValueByCode(obj.toString());
		}
		return str;
	}
	/**
	 * 
	* @Description: 学分计算
	* @author hyl     
	* @date 2017-6-30 下午3:43:53  
	* @version V1.0 
	 */
	public static String getCreditResult(String inputTime,String outputResult,String costTime) {
		String str = "0";
		if (StringUtils.isNotBlank(costTime)) {
			str = (MMap.isnullDouble(inputTime)/MMap.isnullDouble(outputResult))*MMap.isnullDouble(costTime) + "";
		}
		return str;
	}
	/**
	 * 
	* @Description: 校本项目，到第几步，传回信息 
	* @author hyl     
	 */
	public static String getCreditProjectStatus(String step) {
		String str = "|";
		if ("1".equals(step)) {
			str = "step.type.CreditProject.Apply##项目申请";
		}
		if ("2".equals(step)) {
			str = "step.type.CreditProject.Approval##项目审批";
		}
		if ("3".equals(step)) {
			str = "step.type.CreditProject.AllocNum##名额分配";
		}
		if ("4".equals(step)) {
			str = "step.type.CreditProject.SignUp##报名";
		}
		if ("5".equals(step)) {
			str = "step.type.CreditProject.Offline##线下实施";
		}
		if ("6".equals(step)) {
			str = "step.type.CreditProject.LoadScore##成绩导入";
		}
		if ("7".equals(step)) {
			str = "step.type.CreditProject.ConfirmScore##成绩确认";
		}
		if ("8".equals(step)) {
			str = "step.type.CreditProject.CheckScore##成绩审核";
		}
		return str;
	}
	
	/**
	 * 缓存获取所有的kpiGroup 
	 * @param step
	 * @return
	 */
	public static List<BaseKpiGroup> getKpiGroupListRedis() {
		List<BaseKpiGroup> list = new ArrayList<BaseKpiGroup>();
		RedisManager redisManager = (RedisManager) org.my431.platform.utils.ContextUtils.getBean("redisManager");
		list = (List<BaseKpiGroup>) redisManager.getOValue("baseKpiGroupList");
		if(null == list || list.size() < 1){
			BaseKpiGroupManager baseKpiGroupManager = (BaseKpiGroupManager) org.my431.platform.utils.ContextUtils.getBean("baseKpiGroupManager");
			baseKpiGroupManager.init();
			list = (List<BaseKpiGroup>) redisManager.getOValue("baseKpiGroupList");
		}
		return list;
		
	}
	public static String getXlLevelbyCode(String code) {
		String str = "";
		if (boshiList.contains(code)) {
			str = "boshi";
		}
		if (shuoshiList.contains(code)) {
			str = "shuoshi";
		}
		if (benkeList.contains(code)) {
			str = "benke";
		}
		if (zhuankeList.contains(code)) {
			str = "zhuanke";
		}
		if (gaozhongList.contains(code)) {
			str = "gaozhong";
		}
		return str;
	}
	/**
	 * 
	* @Description: 百分之
	* @author hyl     
	 */
	public static String getPercentFloor(Integer numerator, Integer denominator,String format) {
		String result = "0";
		if (denominator != null && denominator != 0) {
			//result = String.format("%.2f", numerator.floatValue() * 100 / denominator.floatValue());
			result = String.format(format, numerator.floatValue() * 100 / denominator.floatValue());
		}
		return result + "%";
	}
	/**
	 * 
	* @Description: 百分之
	* @author hyl     
	 */
	public static String getPercentFloorV2(Double numerator, Double denominator,String format) {
		String result = "0";
		if (denominator != null && denominator != 0) {
			//result = String.format("%.2f", numerator * 100 / denominator);
			result = String.format(format, numerator * 100 / denominator);
		}
		return result + "%";
	}
	/**
	 * 
	* @Description: 计算班师比||计算生师比
	* @author hyl     
	 */
	public static String computeBSBorSSB(Double teacherCnt, Double cnt,String format) {
		String result = "0";
		if (teacherCnt != null && teacherCnt != 0) {
			if (cnt != null && cnt != 0) {
				//format  "%.1f","%.2f"
				//result = "1:"+String.format(format, teacherCnt/ classCnt);
				result = String.format(format, teacherCnt/ cnt);
			}
		}
		return result;
	}
	public static String computeBSBorSSBV2(Double teacherCnt, Double cnt,String format) {
		String result = "0";
		if (teacherCnt != null && teacherCnt != 0) {
			if (cnt != null && cnt != 0) {
				//format  "%.1f","%.2f"
				//result = "1:"+String.format(format, teacherCnt/ classCnt);
				result = String.format(format, cnt/ teacherCnt);
			}
		}
		return result;
	}
	/**
	 * 
	* @Description: 计算平均工资
	* @author pb  
	 */
	public static String getAVGMoney(Double allMoney, Double cnt,String format) {
		String result = "0";
		if (allMoney != null && cnt != 0) {
			if (cnt != null && cnt != 0) {
				//format  "%.1f","%.2f"
				//result = "1:"+String.format(format, teacherCnt/ classCnt);
				result = String.format(format, allMoney/ cnt);
			}
		}
		return result;
	}
	
	/**
	 * 
	* @Description: 格式化数据
	* @author pb  
	 */
	public static String initDate(Double date) {
		String result = "0";
		if (date != null) {
			result =  String.format("%.0f", date);;
		}
		return result;
	}
	
	/**
	 * 获取年月列表
	 * @return
	 */
	public static List<String> getDatesFromSomeDate() {
		String datestr = CacheBasePropertiesManager.getValueByPropertyKey("STATSTARTTIME.01");
		List<String> list = null;
		try {
			list= DateFormater.getDatesFromSomeDate(datestr);
			Collections.reverse(list);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取年列表
	 * @return
	 */
	public static List<String> getDatesFromSomeDateYear() {
		String datestr = CacheBasePropertiesManager.getValueByPropertyKey("ANALYSTARTYEAR");
		List<String> list = null;
		try {
			list= DateFormater.getDatesFromSomeDateYear(datestr);
			Collections.reverse(list);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	/**
	 * 昵称存在取昵称，否则取真实姓名，如果真是姓名还不存在，取登录名
	 * 
	 * @param userId
	 * @return
	 */
	public static String getUserName(String userId) {
		String name = "";
		if (userId != null && !"".equals(userId)) {
			RedisManager redisManager = (RedisManager) org.my431.platform.utils.ContextUtils.getBean("redisManager");
			BaseUser baseUser = redisManager.getRedisUser(userId);

			if (baseUser != null) {
				if (baseUser.getRealName() != null && !baseUser.getRealName().equals("")) {
					name = baseUser.getRealName();
				}
				/*
				 * if(!(baseUser.getNickname()==null||baseUser.getNickname().equals
				 * (""))){ name = baseUser.getNickname(); }else
				 * if(!(baseUser.getRealname
				 * ()==null||baseUser.getRealname().equals(""))){ name =
				 * baseUser.getRealname(); }else{ name =
				 * baseUser.getLoginName(); }
				 */
			}
		}
		/*
		 * if(name.length()>5){ name=name.substring(0,5); name+="..."; }
		 */
		return name;
	}
	/**
	 * 判断是否为空，如果为空返回真
	 * 
	 * @author hmc 2015年5月8日 下午3:24:02
	 * @param obj
	 * @return
	 */
	private static boolean isEmpty(Object obj) {
		if (obj == null || obj.equals("") || obj.equals("null")) {
			return true;
		}
		return false;
	}
	/**
	 * 将时间字符串处理为常见模式
	 * 
	 * @author hmc 2015年4月29日 下午4:25:20
	 * @param timeString
	 * @return
	 */
	public static String getformatTime(String time) {
		if (isEmpty(time) || !time.contains(".")) {

			return "";
		}
		return time.substring(0, time.lastIndexOf(".") - 3);
	}
	/**
	 * 暂时兼容，clobtostring author 90 on 2015-4-1
	 * 
	 * @param clob
	 * @return
	 * @throws Exception
	 */
	public static String oracleClob2Str(Clob clob) throws Exception {
		return (clob != null ? clob.getSubString(1, (int) clob.length()) : null);
	}
	public static String getFormatHtml(String html) {
		return HtmlUtil.delHTMLTag(html);
	}
	
	/**
	 * 对数据结果进行修改，空或0改为-
	 * @param str
	 * @return
	 */
	public static String changeEmptyToStr(String str){
		String result = "-";
		if(StringUtils.isNotBlank(str)){
			if(!"0".equals(str) && !"0%".equals(str)){
				result = str;
			}
		}
		return result;
	}
	
}