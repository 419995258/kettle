/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2012
 */

package org.my431.base.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.my431.base.model.BaseNote;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.my431.util.DateFormater;
import org.springframework.stereotype.Repository;

@Repository
public class BaseNoteManager extends HibernateXsqlBuilderQueryDao<BaseNote>{

	public Class getEntityClass() {
		return BaseNote.class;
	}
	
	public Page findPage(BaseNote query, int pageSize, int pageNo) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t.NOTE_ID,t.TITLE,t.CONTENT_VALUE,t.CRE_TIME,t.CRE_USER,t.CRE_USER_NAME,t.NOTE_LEVEL,t.COLOR,t.TO_WHO,t.IS_TOP,t.INTRO,t.NOTE_TYPE,t.SET_TOP_USER,t.SET_TOP_TIME,t.CONTENT_TYPE       "
				+ " from BaseNote t where 1=1 "
			  	+ "/~ and t.title = {title} ~/"
			  	+ "/~ and t.content = {content} ~/"
				+ "/~ and t.creTime >= {creTimeBegin} ~/"
				+ "/~ and t.creTime <= {creTimeEnd} ~/"
			  	+ "/~ and t.creUser = {creUser} ~/"
			  	+ "/~ and t.creUserName = {creUserName} ~/"
			  	+ "/~ and t.noteLevel = {noteLevel} ~/"
			  	+ "/~ and t.color = {color} ~/"
			  	+ "/~ and t.toWho = {toWho} ~/"
			  	+ "/~ and t.noteType = {noteType} ~/"
				+ "/~ order by [sortColumns] ~/";

       
        
		return pageQuery(sql, pageNo, pageSize);
	}
	/**
	 * 公告的数量
	 * @return
	 */
	public Integer getNoteCount() {
		Session session = this.getSession();
		String sql = " select count(*) from base_note";
		SQLQuery q = session.createSQLQuery(sql);
		int count = Integer.parseInt(q.uniqueResult().toString());
		this.releaseSession(session);
		return count;
	}
	/**
	 * 公告的数量
	 * @return
	 */
	public Integer getNoteCount(String noteType) {
		Session session = this.getSession();
		String sql = " select count(*) from base_note t where t.note_type='"+noteType+"' ";
		SQLQuery q = session.createSQLQuery(sql);
		int count = Integer.parseInt(q.uniqueResult().toString());
		this.releaseSession(session);
		return count;
	}
	public List<Object[]> getPageNote(String noteType,int pageNo, int pageSize) {
		Session session = this.getSession();
		String sql = "select t.* from base_note t where t.note_type='"+noteType+"' order by t.cre_time desc limit :min,:size ";
		SQLQuery q = session.createSQLQuery(sql);
		q.setInteger("min",(pageNo-1)*pageSize);
		q.setInteger("size",pageSize);
		List<Object[]> list = q.list();
		this.releaseSession(session);
		return list;
	}

	//service
	/**
	 * 查询所有的系统通知消息
	 * @author hmc
	 * @param query  		 查询对象
	 * @param pageSize	
	 * @param pageNo
	 * @return
	 */
	public  Page findAllNotePage(BaseNote query, int pageSize, int pageNo,boolean isAdmin){
		Map<String ,Object> map=new HashMap<String ,Object>();
		map.put("PROVINCE_CODE", query.getProvinceCode());
		String str1=null;
		String str2=null;
		if(null==query.getCityCode()){
		}else{
			str1="'"+query.getCityCode()+"'";
		}
		
		if(null==query.getCountyCode()){
		}else{
			str2="'"+query.getCountyCode()+"'";
		}
		
		if(isNotEmpty(query.getJxd())){
			map.put("myJxd", query.getJxd());
		}
		if(isNotEmpty(query.getXx())){
			map.put("myXx", query.getXx());
		}
		if(isNotEmpty(query.getCz())){
			map.put("myCz", query.getCz());
		}
		if(isNotEmpty(query.getGz())){
			map.put("myGz", query.getGz());
		}
		if(isNotEmpty(query.getWz())){
			map.put("myWz", query.getWz());
		}
		if(isNotEmpty(query.getJnygz())){
			map.put("myJn", query.getJnygz());
		}
		if(isNotEmpty(query.getSenygz())){
			map.put("mySen", query.getSenygz());
		}
		if(isNotEmpty(query.getZzhi())){
			map.put("myZz", query.getZzhi());
		}
		if(isNotEmpty(query.getGzhi())){
			map.put("myGz", query.getGzhi());
		}
		if(isNotEmpty(query.getProvinceAdmin())){
			map.put("myPro", query.getProvinceAdmin());
		}
		if(isNotEmpty(query.getCityAdmin())){
			map.put("myCity", query.getCityAdmin());
		}
		if(isNotEmpty(query.getCountyAdmin())){
			map.put("myCountry", query.getCountyAdmin());
		}
		
		
		if(null==query.getCountyCode()||"".equals(query.getCountyCode())){//县
			map.put("AREA_ID", query.getCityCode());
			if(null==query.getCityCode()||"".equals(query.getCityCode())){//市
				map.put("AREA_ID", query.getProvinceCode());
				if(null==query.getProvinceCode()||"".equals(query.getProvinceCode())){//省
					map.put("AREA_ID", "1");
				}
			}
			
		}else{
			if("1".equals(query.getProvinceCode())){//admin
				map.put("AREA_ID", "1");
			}
		}
		//hyl 20150907修改------------------start
		map.put("val", "and ("
				+ " (t.moe_admin=1 or t.province_code='1') "
				+ " or t.area_id='"+query.getProvinceCode()+"'  "
				+ " or t.area_id="+str1
				+ " or t.area_id ="+str2+")");
		//hyl 20150907修改------------------end
		//hyl 20150907注释------------------start
		/*map.put("val", "and ("
				+ " (t.moe_admin=1 or t.province_code='1') "
				+ "or (t.province_code="+"'"+""+query.getProvinceCode()+""+"'"+") "
				+ "or (t.city_code="+str1+") "
				+ "or (t.county_code ="+str2+"))");*/
		//hyl 20150907注释------------------end
/*		map.put("val", "and ("
				+ " (t.moe_admin=1 and t.province_code='1') "
				+ "or (t.province_admin=1 and t.province_code="+"'"+""+query.getProvinceCode()+""+"'"+") "
				+ "or (t.city_admin=1 and t.city_code="+str1+") "
				+ "or (t.county_admin=1 and t.county_code ="+str2+"))");
*/
		Page p=null;
		if(isAdmin){
			p=this.getPagedNamedQuery("misBase::findAdminNotePage::query", pageNo, pageSize, map);
		}else{
			p=this.getPagedNamedQuery("misBase::findAllNotePage::query", pageNo, pageSize, map);
		}
		
		
		
		return p;
	}
	/**
	 * 
     *类的描述:getCurProStatus<br>
     *功能描述 ：项目-项目更新情况-县级下，点击刷新<br>
     *作者:hyl<br>
     *创建日期:2015-07-02<br>
     *修改人：<br>
     *修改日期：<br>
     *修改原因描述:<br>
	 */
	public  Page findObservePage(BaseNote query, int pageSize, int pageNo){
		Map<String ,Object> map=new HashMap<String ,Object>();
		if(StringUtils.isNotBlank(query.getProvinceCode())){
			map.put("province", query.getProvinceCode());
		}
		if(StringUtils.isNotBlank(query.getCityCode())){
			map.put("city", query.getCityCode());
		}
		if(StringUtils.isNotBlank(query.getCountyCode())){
			map.put("country", query.getCountyCode());
		}
		if(StringUtils.isNotBlank(query.getCityAdmin())){
			map.put("ciadmin", query.getCityAdmin());
		}
		if(StringUtils.isNotBlank(query.getCountyAdmin())){
			map.put("coadmin", query.getCountyAdmin());
		}
		if(StringUtils.isNotBlank(query.getAreaId())){
			map.put("areaid", query.getAreaId());
		}
		if(StringUtils.isNotBlank(query.getCreUser())){
			map.put("creuser", query.getCreUser());
		}
		Page p=getPagedNamedQuery("misBase::findObeserveNotePage::query", pageNo, pageSize, map);
		return p;
	}
	public void addNote(BaseNote baseNote) {
		
		
	}
	/**
	 * 新增通知
	 * @author    hmc    2015年4月9日  下午5:13:15
	 * @param baseNote
	 */
	public void addNote(Map<String,Object> m) {
		String tableName="BASE_NOTE";
		this.insertData(m,tableName);
	}

	/**
	 * 
	 * @author    hmc     2015年4月9日  下午5:22:15
	 * @param noteid
	 * @return
	 */
	public Map findNoteById(String noteid) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("NOTE_ID", noteid);
		List list=this.getNamedQuery("misBase::findNoteById::query",map);
		return (Map) list.get(0);
	}
	
	/**
	 * 查询当前没有查看过的通知个数
	 * @author    hmc    2015年5月13日  下午5:27:29
	 * @param sdate
	 * @return
	 */
	public String getNoteIsNotReadNum(Date lastReadTime) {
		Map<String,Object> m=new HashMap<String, Object>();
		if(isNotEmpty(lastReadTime)){
			m.put("LAST_NOTE_TIME",new Date());
		}
		List list=this.getNamedQuery("misBase::getNoteIsNotReadNum::query", m);
		if(list!=null&&list.size()>0){
			
			return  ((Map)(list.get(0))).get("NOT_READ")+"";
		}
		
		return "0";
	}
	
	public  String findNoteIsNotReadNum(BaseNote query,Date lastReadTime){
		
		Map<String ,Object> map=new HashMap<String ,Object>();
		map.put("PROVINCE_CODE", query.getProvinceCode());
		String str1=null;
		String str2=null;
		if(null==query.getCityCode()){
		}else{
			str1="'"+query.getCityCode()+"'";
		}
		
		if(null==query.getCountyCode()){
		}else{
			str2="'"+query.getCountyCode()+"'";
		}
		
		if(isNotEmpty(query.getJxd())){
			map.put("myJxd", query.getJxd());
		}
		if(isNotEmpty(query.getXx())){
			map.put("myXx", query.getXx());
		}
		if(isNotEmpty(query.getCz())){
			map.put("myCz", query.getCz());
		}
		if(isNotEmpty(query.getGz())){
			map.put("myGz", query.getGz());
		}
		if(isNotEmpty(query.getWz())){
			map.put("myWz", query.getWz());
		}
		if(isNotEmpty(query.getJnygz())){
			map.put("myJn", query.getJnygz());
		}
		if(isNotEmpty(query.getSenygz())){
			map.put("mySen", query.getSenygz());
		}
		if(isNotEmpty(query.getZzhi())){
			map.put("myZz", query.getZzhi());
		}
		if(isNotEmpty(query.getGzhi())){
			map.put("myGz", query.getGzhi());
		}
		if(isNotEmpty(query.getProvinceAdmin())){
			map.put("myPro", query.getProvinceAdmin());
		}
		if(isNotEmpty(query.getCityAdmin())){
			map.put("myCity", query.getCityAdmin());
		}
		if(isNotEmpty(query.getCountyAdmin())){
			map.put("myCountry", query.getCountyAdmin());
		}
		
		
		if(null==query.getCountyCode()||"".equals(query.getCountyCode())){//县
			map.put("AREA_ID", query.getCityCode());
			if(null==query.getCityCode()||"".equals(query.getCityCode())){//市
				map.put("AREA_ID", query.getProvinceCode());
				if(null==query.getProvinceCode()||"".equals(query.getProvinceCode())){//省
					map.put("AREA_ID", "1");
				}
			}
			
		}else{
			if("1".equals(query.getProvinceCode())){//admin
				map.put("AREA_ID", "1");
			}
		}
		
		String strTemp="and ("
				+ " (t.moe_admin=1 and t.province_code='1') "
				+ "or (t.province_code="+"'"+""+query.getProvinceCode()+""+"'"+") "
				+ "or (t.city_code="+str1+") "
				+ "or (t.county_code ="+str2+"))";
		
		
		if(isNotEmpty(lastReadTime)){
			//to_date('2015-05-13 17:14:46','yyyy-mm-dd hh24:mi:ss')
			strTemp+=strTemp+" and cre_time > to_date('"+DateFormater.DateTimeToString(lastReadTime)+"','yyyy-mm-dd hh24:mi:ss') ";
		}
		map.put("val", strTemp);
		
		List list=this.getNamedQuery("misBase::getNoteIsNotReadNum::query", map);
		if(list!=null&&list.size()>0){
			return  ((Map)(list.get(0))).get("NOT_READ").toString()+"";
		}
		return "0";
	}

	/**
	 * 加载所有自己发布的通知
	 * @author    hmc    2015年5月18日  下午7:41:13
	 * @param query   查询条件
	 * @param pageSize 一页几条
	 * @param pageNo  当前页
	 * @return
	 */
	public Page findAllMyNotePage(String userid, int pageSize, int pageNo) {
		Map m=new HashMap();
		if(isNotEmpty(userid)){
			m.put("create_userId", userid);
		}
		
		return this.getPagedNamedQuery("misBase::findAllMyNotePage::query", pageNo, pageSize, m);
	}
	/**
	 * 
     *类的描述:findAllNotePage
     *功能描述 ：管理员页面查看所有通知
     *作者:hyl
     *创建日期:2015-06-23
     *修改人：
     *修改日期：
     *修改原因描述;
	 */
	public Page findAllNotePage(BaseNote query, int pageSize, int pageNo) {
		Map m=new HashMap();
		
		return this.getPagedNamedQuery("misBase::findAllMyNotePage::query", pageNo, pageSize, m);
	}
}
