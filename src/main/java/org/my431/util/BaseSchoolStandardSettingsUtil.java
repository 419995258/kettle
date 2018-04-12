
/**    
* @Title: BaseSchoolStandardSettingsUtil.java  
* @Package org.my431.util  
* @Description: TODO(用一句话描述该文件做什么)  
* @author hyl     
* @date 2016-12-21 下午3:24:50  
* @version V1.0 
* @author user   
*/  

package org.my431.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.my431.base.services.BaseSchoolManager;
import org.my431.plugin.redis.services.RedisManager;

import net.sourceforge.jeval.Evaluator;


/**  
 * @author hyl  
 * @author user   
 */
public class BaseSchoolStandardSettingsUtil {
	private static String cacheBaseSchoolStandardSettingsUtilKey="global.base.BaseSchoolStandardSettings.id.";
	
	public static  void initAllSettings() {
		BaseSchoolManager baseSchoolManager=(BaseSchoolManager)org.my431.platform.utils.ContextUtils.getBean("baseSchoolManager");
		List<HashMap<Object,Object>> list1=baseSchoolManager.findSql("select t.* from base_school_standard_settings t");
		if(list1!=null&&list1.size()>0){
			for (HashMap map : list1) {
				MMap mMap=new MMap();
				mMap.setKey(MMap.isnullStr(map.get("ID")));
				mMap.setObj(MMap.isnullStr(map.get("STANDARD_NAME")));
				mMap.setObj1(MMap.isnullStr(map.get("STANDARD_DEFINEKEY")));
				mMap.setObj2(MMap.isnullStr(map.get("STANDARD_ARITHMETIC")));
				mMap.setObj3(MMap.isnullStr(map.get("STANDARD_FUNC")));
				mMap.setObj4(MMap.isnullStr(map.get("STANDARD_CONTENT")));
				setBaseSchoolStandardSettingsValue(mMap.getObj1().toString(),mMap);
			}
		}
		
		
	}
	
	public static void setBaseSchoolStandardSettingsValue(String key,MMap mMap) {
		RedisManager redisManager = (RedisManager) org.my431.platform.utils.ContextUtils.getBean("redisManager");
		redisManager.setOValue(cacheBaseSchoolStandardSettingsUtilKey+key, mMap);
	}
	public static MMap getetBaseSchoolStandardSettingsValue(String key) {
		RedisManager redisManager = (RedisManager) org.my431.platform.utils.ContextUtils.getBean("redisManager");
		return (MMap) redisManager.getOValue(cacheBaseSchoolStandardSettingsUtilKey+key);
	}
	/**
	 * 达标算法工具方法
	 * */
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
		return pk;
	}
	/**
	 * 校舍_生均面积(㎡)
	 * 
	 * */
	public String getXSSJMJ(String jsxss,String jszbs,String fjsxss,String fjszbs){
		MMap mMap=getetBaseSchoolStandardSettingsValue("XSSJMJ");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("jsxss", jsxss);//寄宿生数量
		maps.put("jszbs", jszbs);//寄宿指标
		
		maps.put("fjsxss", fjsxss);//非寄宿生
		maps.put("fjszbs", fjszbs);//非寄宿指标
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 校舍_学生宿舍(㎡/寄宿生)
	 * */
	public String  getXSSSMJ(String jsxss,String xssszbs) {
		MMap mMap=getetBaseSchoolStandardSettingsValue("XSSSMJ");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("jsxss", jsxss);
		maps.put("xssszbs", xssszbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 校舍_食堂
	 * */
	public String  getSTMJ(String jsxss,String sxsstzbs) {
		MMap mMap=getetBaseSchoolStandardSettingsValue("STMJ");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("jsxss", jsxss);
		maps.put("sxsstzbs", sxsstzbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 校舍_男蹲位数
	 * */
	public String  getNDWS(String psnn,String jsnn,String sxsndwszbs) {
		MMap mMap=getetBaseSchoolStandardSettingsValue("NDWS");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("psnn", psnn);
		maps.put("jsnn", jsnn);
		maps.put("sxsndwszbs", sxsndwszbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 校舍_女蹲位数
	 * */
	public String  getNVDWS(String psnvn,String jsnvn,String sxsnvdwszbs) {
		MMap mMap=getetBaseSchoolStandardSettingsValue("NVDWS");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("psnvn", psnvn);
		maps.put("jsnvn", jsnvn);
		maps.put("sxsnvdwszbs", sxsnvdwszbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 校舍_男小便池长度
	 * */
	public String  getNXBCCD(String psnn,String jsnn,String sxsnxbccdzbs) {
		MMap mMap=getetBaseSchoolStandardSettingsValue("NXBCCD");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("psnn", psnn);
		maps.put("jsnn", jsnn);
		maps.put("sxsnxbccdzbs", sxsnxbccdzbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 校舍_浴室(间)
	 * */
	public String  getYSSL(String ysslzbs) {
		MMap mMap=getetBaseSchoolStandardSettingsValue("YSSL");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("ysslzbs", ysslzbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 校舍_D级危房
	 * */
	public String  getDJWF() {
		
		return "0";
	}
	/**
	 * 运动场地_田径场(m)
	 * */
	public String  getTJYDCSL(String ydcdtjczbs){
		MMap mMap=getetBaseSchoolStandardSettingsValue("TJYDCSL");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("ydcdtjczbs", ydcdtjczbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 课桌椅_课桌椅(套/人)
	 * */
	public String  getKZYSL(String psn,String jsn,String kzyzbs) {
		MMap mMap=getetBaseSchoolStandardSettingsValue("KZYSL");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("psn", psn);
		maps.put("jsn", jsn);
		maps.put("kzyzbs", kzyzbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 图书_图书(册/人)
	 * */
	public String  getTSSL(String psn,String jsn,String tsslzbs) {
		MMap mMap=getetBaseSchoolStandardSettingsValue("TSSL");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("psn", psn);
		maps.put("jsn", jsn);
		maps.put("tsslzbs", tsslzbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 生活设施_学生用床(张/寄宿生)
	 * */
	public String  getXSYCSL(String jsxss,String xsycslzbs) {
		MMap mMap=getetBaseSchoolStandardSettingsValue("XSYCSL");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("jsxss", jsxss);
		maps.put("xsycslzbs", xsycslzbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 生活设施_食堂设备(含炊具、餐具和餐桌椅)(套)
	 * */
	public String  getSTSBSL(String stsbslzbs){
		MMap mMap=getetBaseSchoolStandardSettingsValue("STSBSL");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("stsbslzbs", stsbslzbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 生活设施_饮水设施(含净化器设备)生设施比
	 * */
	public String  getYSSSSL(String psn,String jsn,String shsssssszbs){
		MMap mMap=getetBaseSchoolStandardSettingsValue("YSSSSL");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("psn", psn);
		maps.put("jsn", jsn);
		maps.put("shsssssszbs", shsssssszbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 生活设施_安保设备
	 * */
	public String  getABSBSL(String shssabsbzbs){
		MMap mMap=getetBaseSchoolStandardSettingsValue("ABSBSL");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("shssabsbzbs", shssabsbzbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 生活设施_宿舍采暖设施(套/每间宿舍)
	 * */
	public String  getSSCNSSSL(String shssSscnss,String sssl){
		MMap mMap=getetBaseSchoolStandardSettingsValue("SSCNSSSL");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("shssSscnss", shssSscnss);
		maps.put("sssl", sssl);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 生活设施_教室采暖设施(套/教室)	
	 * */
	public String  getJSCNSSSL(String shssjscnss,String jssl){
		MMap mMap=getetBaseSchoolStandardSettingsValue("JSCNSSSL");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("shssjscnss", shssjscnss);
		maps.put("jssl", jssl);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 信息化_接入宽带(M)
	 * */
	public String  getHASWIDEBAND(String xxhJrdk){
		MMap mMap=getetBaseSchoolStandardSettingsValue("HASWIDEBAND");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("xxhJrdk", xxhJrdk);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 信息化_多媒体教室(班)
	 * */
	public String  getDMTJSSL(String pcn,String jcn,String xxhdmtjszbs,String flag){
		MMap mMap=getetBaseSchoolStandardSettingsValue("DMTJSSL");
		if("1".equals(flag)){//是教学点
			return mMap.getObj2().toString();
		}else {
			Map<String,String> maps=new HashMap<String,String>();
			maps.put("pcn", pcn);
			maps.put("jcn", jcn);
			maps.put("xxhdmtjszbs", xxhdmtjszbs);
			return getEval(mMap.getObj2().toString(), maps);
		}
	}
	/**
	 * 信息化_网络多媒体教室(套/班)	
	 * */
	public String  getXXHWLDMT() {
		
		return "0";
	}
	/**
	 * 实验仪器_小学数学科学实验仪器（套/校）
	 * */
	public String  getXXSXKXSYYQSL(String xxsskxsyyqzbs){
		MMap mMap=getetBaseSchoolStandardSettingsValue("XXSXKXSYYQSL");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("xxsskxsyyqzbs", xxsskxsyyqzbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 实验仪器_初中数学仪器（套/校）
	 * */
	public String  getCZSXYQSL(String czsxyqzbs){
		MMap mMap=getetBaseSchoolStandardSettingsValue("CZSXYQSL");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("czsxyqzbs", czsxyqzbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 实验仪器_初中物理仪器（套/校）
	 * */
	public String  getCZWLYQSL(String czwlyqzbs){
		MMap mMap=getetBaseSchoolStandardSettingsValue("CZWLYQSL");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("czwlyqzbs", czwlyqzbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 *实验仪器_初中化学仪器（套/校） 
	 * */
	public String  getCZHXYQSL(String czhxyqzbs){
		MMap mMap=getetBaseSchoolStandardSettingsValue("CZHXYQSL");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("czhxyqzbs", czhxyqzbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 实验仪器_初中生物仪器（套/校）
	 * */
	public String  getCZSWYQSL(String czswyqzbs){
		MMap mMap=getetBaseSchoolStandardSettingsValue("CZSWYQSL");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("czswyqzbs", czswyqzbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 实验仪器_初中地理仪器（套/校）
	 * */
	public String  getCZDLYQSL(String czdlyqzbs){
		MMap mMap=getetBaseSchoolStandardSettingsValue("CZDLYQSL");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("czdlyqzbs", czdlyqzbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 *音体美_音乐器材（套/校） 
	 * */
	public String  getYYQCSL(String yyqczbs){
		MMap mMap=getetBaseSchoolStandardSettingsValue("YYQCSL");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("yyqczbs", yyqczbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 音体美_体育器材（套/校）
	 * */
	public String  getTYQCSL(String tyqczbs){
		MMap mMap=getetBaseSchoolStandardSettingsValue("TYQCSL");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("tyqczbs", tyqczbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 音体美_美术器材（套/校）
	 * */
	public String  getMSQCSL(String msqczbs){
		MMap mMap=getetBaseSchoolStandardSettingsValue("MSQCSL");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("msqczbs", msqczbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 生机比
	 * */
	public String  getXSJSJSL(String psn,String jsn,String sjbzbs){
		MMap mMap=getetBaseSchoolStandardSettingsValue("XSJSJSL");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("psn", psn);
		maps.put("jsn", jsn);
		maps.put("sjbzbs", sjbzbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
	/**
	 * 师机比
	 * */
	public String  getJSJSJSL(String tn,String shjbzbs){
		MMap mMap=getetBaseSchoolStandardSettingsValue("JSJSJSL");
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("tn", tn);
		maps.put("shjbzbs", shjbzbs);
		return getEval(mMap.getObj2().toString(), maps);
	}
}
