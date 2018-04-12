/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2011
 */

package org.my431.base.services;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.my431.base.model.BaseUser;
import org.my431.platform.dao.extend.HibernateNamedQueryDao;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.my431.plugin.redis.services.RedisManager;
import org.my431.util.MMap;
import org.springframework.stereotype.Repository;

@Repository
public class BaseUserManager extends HibernateNamedQueryDao<BaseUser>{
	
	private RedisManager redisManager;
	
	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}

	
	public List<Object[]> creSql(String sql){
		Session ss=this.getSession();
		List<Object[]> list=ss.createSQLQuery(sql).list();
		this.releaseSession(ss);
		return list;
	}
	 	
	public Class getEntityClass() {
		return BaseUser.class;
	}
	
	public void init(){
		List<BaseUser> userList=this.getAll();
		for(BaseUser user:userList){
			redisManager.saveBaseUser(user);
		}
	}
	
	public void reload(){
		init();
	}
	
	public void addReload(String id){
		BaseUser baseUser=this.get(id);
		if(baseUser!=null){
			redisManager.saveBaseUser(baseUser);
		}
	}
	public void addReload(BaseUser baseUser){
		if(baseUser!=null){
			redisManager.saveBaseUser(baseUser);
		}
	}
	
	public void updateReload(String id){
		BaseUser baseUser=this.get(id);
		if(baseUser!=null){
			redisManager.saveBaseUser(baseUser);
		}
	}
	
	public void updateReload(BaseUser baseUser){
		if(baseUser!=null){
			redisManager.saveBaseUser(baseUser);
		}
	}
	
	public void deleteReload(String id){
		BaseUser baseUser=this.get(id);
		if(baseUser!=null){
			redisManager.removeRedisUser(id);
		}
	}
	public BaseUser getBaseUserByUserId(String userId){
		BaseUser baseUser=new BaseUser();
		if(StringUtils.isNotBlank(userId)){
			baseUser=redisManager.getRedisUser(userId);
			if(StringUtils.isBlank(baseUser.getId())){
				baseUser=get(userId);
			}
		}
		return baseUser;
	}
	public Page findPage(HttpServletRequest request, BaseUser query, int pageSize, int pageNo) {
		String searchType=request.getParameter("searchType")!=null?request.getParameter("searchType"):"";
		String searchText=request.getParameter("searchText")!=null?request.getParameter("searchText"):"";
		String nodeLevel=request.getParameter("nodeLevel");
		String isZhiShu=request.getParameter("isZhiShu")!=null?request.getParameter("isZhiShu"):"";
		
		//String hql="select t from BaseUser t where 1=1 and t.defaultRoleCode not in('role.admin','role.student','role.teacher','role.parents') and t.deleteFlag<>'1' ";
		String hql="select t from BaseUser t where 1=1 and t.defaultRoleCode not in('role.admin') and t.deleteFlag<>'1' ";
		String sql="select count(*) from Base_User t where 1=1 and t.default_Role_Code not in('role.admin') and t.delete_Flag<>'1' ";
		
		String[] userRole = request.getParameterValues("userRole");
		String[] userStatus = request.getParameterValues("userStatus");
		
		request.setAttribute("userRoleArray", Arrays.toString(userRole));
		request.setAttribute("userStatusArray", Arrays.toString(userStatus));
		StringBuffer buffer_hql = new StringBuffer("");
		StringBuffer buffer_sql = new StringBuffer("");
		//用户角色
		if (null != userRole && userRole.length > 0) {
			if (!MMap.loopArrays(userRole, "all")) {//不包含all
				buffer_hql.append(" and ( ");
				buffer_sql.append(" and ( ");
				for (int i = 0; i < userRole.length; i++) {
					if (i == 0) {
						buffer_hql.append(" t.defaultRoleCode = '"+userRole[i]+"' ");
						buffer_sql.append(" t.default_Role_Code = '"+userRole[i]+"' ");
					}else {
						buffer_hql.append(" or t.defaultRoleCode= '"+userRole[i]+"' ");
						buffer_sql.append(" or t.default_Role_Code= '"+userRole[i]+"' ");
					}
				}
				buffer_hql.append(" )");
				buffer_sql.append(" )");
			}
		}else {
			hql=hql+" and t.defaultRoleCode <>'role.editor' ";
			sql=sql+" and t.default_Role_Code <>'role.editor' ";
		}
		//是否初始化
		if (null != userStatus && userStatus.length > 0) {
			if (!MMap.loopArrays(userStatus, "all")) {//不包含all
				buffer_hql.append(" and ( ");
				buffer_sql.append(" and ( ");
				for (int i = 0; i < userStatus.length; i++) {
					if (i == 0) {
						buffer_hql.append(" nvl(t.isIni,0) = '"+userStatus[i]+"' ");
						buffer_sql.append(" nvl(t.IS_INI,0) = '"+userStatus[i]+"' ");
					}else {
						buffer_hql.append(" or nvl(t.isIni,0) = '"+userStatus[i]+"' ");
						buffer_sql.append(" or nvl(t.IS_INI,0) = '"+userStatus[i]+"' ");
					}
				}
				buffer_hql.append(" )");
				buffer_sql.append(" )");
			}
		}
		
		hql = hql + buffer_hql.toString();
		sql = sql + buffer_sql.toString();
		
		/*if(query.getDefaultRoleCode()!=null&&!query.getDefaultRoleCode().equals("")){
			hql = hql + "and t.defaultRoleCode = '"+query.getDefaultRoleCode()+"' ";
			sql = sql + "and t.default_Role_Code = '"+query.getDefaultRoleCode()+"' ";
		}else
		{
			hql=hql+"and t.defaultRoleCode <>'role.editor'";
			sql=sql+"and t.default_Role_Code <>'role.editor'";
		}*/
		if(StringUtils.isNotBlank(searchText)){
			searchText=MMap.replaceSpaceToPercent(searchText);
		}
		if(searchType.equals("realName")&&!searchText.trim().equals("")) hql = hql + "and t.realName like '%"+searchText.trim()+"%' ";
		if(searchType.equals("realName")&&!searchText.trim().equals("")) sql = sql + "and t.REAL_NAME like '%"+searchText.trim()+"%' ";
		if(searchType.equals("loginName")&&!searchText.trim().equals("")) hql = hql + "and t.loginName like '%"+searchText.trim()+"%' ";
		if(searchType.equals("loginName")&&!searchText.trim().equals("")) sql = sql + "and t.login_Name like '%"+searchText.trim()+"%' ";
		if(searchType.equals("email")&&!searchText.trim().equals("")) hql = hql + "and t.email like '%"+searchText.trim()+"%' ";
		if(searchType.equals("email")&&!searchText.trim().equals("")) sql = sql + "and t.email like '%"+searchText.trim()+"%' ";
		if(searchType.equals("msn")&&!searchText.trim().equals("")) hql = hql + "and t.msn like '%"+searchText.trim()+"%' ";
		if(searchType.equals("msn")&&!searchText.trim().equals("")) sql = sql + "and t.msn like '%"+searchText.trim()+"%' ";
		if(searchType.equals("mobile")&&!searchText.trim().equals("")) hql = hql + "and t.mobile like '%"+searchText.trim()+"%' ";
		if(searchType.equals("mobile")&&!searchText.trim().equals("")) sql = sql + "and t.mobile like '%"+searchText.trim()+"%' ";
		if(searchType.equals("qq")&&!searchText.trim().equals("")) hql = hql + "and t.qq like '%"+searchText.trim()+"%' ";
		if(searchType.equals("qq")&&!searchText.trim().equals("")) sql = sql + "and t.qq like '%"+searchText.trim()+"%' ";
		if(searchType.equals("nickName")&&!searchText.trim().equals("")) hql = hql + "and t.nickname like '%"+searchText.trim()+"%' ";
		if(searchType.equals("nickName")&&!searchText.trim().equals("")) sql = sql + "and t.nickname like '%"+searchText.trim()+"%' ";
			
		if(isZhiShu.equals("1")){//只显示直属用户
			hql = hql + "and t.areaId = '"+query.getAreaId()+"' ";
			sql = sql + "and t.area_Id = '"+query.getAreaId()+"' ";
		}else{
			if(isNotEmpty(query.getProvinceId())){//省
				hql = hql + "and t.provinceId = '"+query.getProvinceId()+"' ";
				sql = sql + "and t.province_Id = '"+query.getProvinceId()+"' ";
			}			
			if(isNotEmpty(query.getCityId())){//市
				hql = hql + "and t.cityId = '"+query.getCityId()+"' ";
				sql = sql + "and t.city_Id = '"+query.getCityId()+"' ";
			}			
			if(isNotEmpty(query.getCountyId())){//县
				hql = hql + "and t.countyId = '"+query.getCountyId()+"' ";
				sql = sql + "and t.county_Id = '"+query.getCountyId()+"' ";
			}			
		}
		/*if(query.getIsIni()!=null&&!query.getIsIni().equals("")){
			hql = hql + "and t.isIni = '"+query.getIsIni()+"' ";
			sql = sql + "and t.IS_INI = '"+query.getIsIni()+"' ";
		}*/
		hql = hql + "order by t.defaultRoleCode ";
		Query q = getSession().createSQLQuery(sql);
        List list=q.list();
        Long total=Long.valueOf(list.get(0).toString());
		
		return pageQuery(hql,total, pageNo, pageSize);
	}
	
	
	public String getRandomPassword(int length){
		//StringBuffer buffer=new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()");
//		StringBuffer buffer=new StringBuffer("0123456789");
		StringBuffer sb=new StringBuffer();
//		Random r=new Random();
//		int range=buffer.length();
//		for(int i=0;i<length;i++){
//		sb.append(buffer.charAt(r.nextInt(range)));
//		}
		sb.append("123456");
		return sb.toString();
	}
	public String getRandomPasswordNum(int length){
		StringBuffer buffer=new StringBuffer("0123456789");
		StringBuffer sb=new StringBuffer();
		Random r=new Random();
		int range=buffer.length();
		for(int i=0;i<length;i++){
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}
	
	public Page getListByObject(BaseUser query, int pageSize, int pageNo){
		String hql="from BaseUser t where 1=1 and (t.defaultRoleCode='role.schoolManager' or t.defaultRoleCode='role.deviceManager' or t.defaultRoleCode='role.schoolMaster' )";
		
		if(isNotEmpty(query.getSchoolId())){
			hql=hql+" and t.schoolId=:schoolId";
		}
		
		Map m=new HashMap();
		
		if(isNotEmpty(query.getSchoolId())){
			m.put("schoolId",  query.getSchoolId());
		}
		return pageQuery(hql, pageNo, pageSize,m);
	}
	
	public Integer accountSeq(){
		Integer returnValue;
		String sql="select account_seq.nextval from dual";
		
		Session session = this.getSession();
		Query query = session.createSQLQuery(sql);
		returnValue = ((BigDecimal)query.uniqueResult()).intValue();
		//session.cancelQuery();
		this.releaseSession(session);
		return returnValue;
	}
	
	//得到某地区下，所有未初始化的用户
	public List<BaseUser> getUserNotIni(String areaId,String isIni){
		String hql="from BaseUser t where 1=1 ";
		
		if(isNotEmpty(areaId)){
			hql=hql+" and t.areaId=:areaId";
		}
		
		if(isNotEmpty(isIni)){
			hql=hql+" and t.isIni=:isIni";
		}
		Session session=this.getSession();
		Query q=session.createQuery(hql);
		
		if(isNotEmpty(areaId)){
			q.setParameter("areaId", areaId);
		}
		if(isNotEmpty(isIni)){
			q.setParameter("isIni", Integer.valueOf(isIni));
		}
		List list=q.list();
		this.releaseSession(session);
		return list;
	}
	
	//得到某学校下，所有未初始化的用户
	public List<BaseUser> getStudentUserNotIni(String schoolId,String isIni){
		String hql="from BaseUser t where 1=1 ";
		
		if(isNotEmpty(schoolId)){
			hql=hql+" and t.schoolId=:schoolId";
		}
		
		if(isNotEmpty(isIni)){
			hql=hql+" and t.isIni=:isIni";
		}
		
		Session session=this.getSession();
		Query q=session.createQuery(hql);
		
		if(isNotEmpty(schoolId)){
			q.setParameter("schoolId", schoolId);
		}
		if(isNotEmpty(isIni)){
			q.setParameter("isIni", isIni);
		}
		List list=q.list();
		this.releaseSession(session);
		return list;
	}
	
	//根据学校Id取出 校长 和 设备管理员
	public List<BaseUser> getUserListBySchoolIdAndRoleCode(String schoolId, String roleCode){
		String hql=" from BaseUser t where t.schoolId=:schoolId";
		if(isNotEmpty(roleCode)){
			hql=hql+" and t.defaultRoleCode=:defaultRoleCode";
		}else{
			hql=hql+" and (t.defaultRoleCode='role.schoolMaster' or t.defaultRoleCode='role.deviceManager')";
		}
		hql=hql+" order by t.modTime";

		Session session=this.getSession();
		Query q=session.createQuery(hql);
		q.setParameter("schoolId", schoolId);
		if(isNotEmpty(roleCode)){
			q.setParameter("defaultRoleCode", roleCode);
		}
		List<BaseUser> list=q.list();
		this.releaseSession(session);
		return list;
	}

	//得到某地区下用户
	public List<BaseUser> getUserByAreaId(String areaId,String roleCode){
		String hql="from BaseUser t where 1=1 and t.deleteFlag='0'";
		
		if(isNotEmpty(areaId)){
			hql=hql+" and t.areaId=:areaId";
		}
		
		if(isNotEmpty(roleCode)){
			hql=hql+" and t.defaultRoleCode=:defaultRoleCode";
		}
		
		Session session=this.getSession();
		Query q=session.createQuery(hql);
		
		if(isNotEmpty(areaId)){
			q.setParameter("areaId", areaId);
		}
		if(isNotEmpty(roleCode)){
			q.setParameter("defaultRoleCode", roleCode);
		}
		List list=q.list();
		this.releaseSession(session);
		return list;
	}
	
	/**
	 * 电话唯一判断
	 * @param mobile
	 * @return
	 */
	public String getCountByMobile(String mobile,String id)
	{
		String flag="0";
		String hql="from BaseUser t where t.mobile=:mobile " ;
		if(isNotEmpty(id)){
			hql+=" and t.id<>:id";
		}
		Session session=this.getSession();
		Query q=session.createQuery(hql);
		q.setParameter("mobile", mobile);
		if(isNotEmpty(id)){
			q.setParameter("id", id);
		}
		//int cnt=Integer.valueOf((String)q.uniqueResult());
		int cnt=q.list().size();
		
		if(cnt==0) flag="0";//目前没有这个电话号码
		else flag="1";//已经存在此号码
		this.releaseSession(session);
		return flag;
	}
	/**
	 * 邮箱号码唯一判断
	 * @param mobile
	 * @return
	 */
	public String getCountByEmail(String email,String id)
	{
		String flag="0";
		String hql="from BaseUser t where t.email=:email " ;
		if(isNotEmpty(id)){
			hql+=" and t.id<>:id";
		}
		Session session=this.getSession();
		Query q=session.createQuery(hql);
		q.setParameter("email", email);
		if(isNotEmpty(id)){
			q.setParameter("id", id);
		}
		//int cnt=Integer.valueOf((String)q.uniqueResult());
		int cnt=q.list().size();
		
		if(cnt==0) flag="0";//目前没有这个邮箱 号码
		else flag="1";//已经存在此号码
		this.releaseSession(session);
		return flag;
	}
	
//	@Override
//	public BaseUser get(Serializable id){
//		String hql="from BaseUser t where t.id=:id";
//		
//		Session session = this.getSession();
//		Query q=session.createQuery(hql);
//		q.setParameter("id", id);
//		List list=q.list();
//		if(list!=null&&list.size()==1){
//			BaseUser baseUser=(BaseUser)list.get(0);
//			this.releaseSession(session);
//			return baseUser;
//		}else{
//			return null;
//		}
//	}
	/**
	 * 根据Email查询用户
	 */
	public String validateEmail(String email){
		String hql="select t from BaseUser t where t.email='"+email+"'";
		List result=find(hql);
		if(result.size()>0){
			return "1";
		}
		return "0";
	}
	
	public BaseUser getSchoolManagerBySchoolId(String schoolId)
	{
		String hql=" from BaseUser t where t.schoolId='"+schoolId+"' and t.defaultRoleCode='role.schoolManager'";
		List list=find(hql);
		BaseUser baseUser=null;
		if(null!=list&&list.size()>0){
			baseUser=(BaseUser) list.get(0);
		}
		
		return baseUser;
	}
	/**
	 * 获取用户List根据角色
	 */
	public List<BaseUser> queryListByDefaultCode(){
		String hql="select t from BaseUser t where t.defaultRoleCode='role.teacher' or t.defaultRoleCode='role.student' or t.defaultRoleCode='role.parents' ";
		return find(hql);
	}

	public List<BaseUser> queryList(){
		String hql="select t from BaseUser t where t.defaultRoleCode='role.teacher' or t.defaultRoleCode='role.student' or t.defaultRoleCode='role.parents' and t.isIni=1 and t.deleteFlag = 0";
		return find(hql);
	}
	
	public BaseUser getByCondition(String defaultRoleCode, String condition,String conditionType,String securityCode)
	{
		String hql="select t from BaseUser t where 1=1";
		if(isNotEmpty(defaultRoleCode)){
			 hql=hql+" and t.defaultRoleCode='"+defaultRoleCode+"'";
		 }
		if (conditionType.equals("loginName"))
		{
			hql=hql+" and t.loginName='"+condition+"'";
		}
		else if (conditionType.equals("phone"))
		{
			hql=hql+" and t.mobile='"+condition+"'";
		}
		else if (conditionType.equals("email"))
		{
			hql=hql+" and t.email='"+condition+"'";
		}
		if (isNotEmpty(securityCode))
		 {
	 		hql=hql+" and t.securityCode='"+securityCode+"'";
		 }
		List result=find(hql);
		BaseUser baseUser=null;
		if(result.size()>0){
			baseUser=(BaseUser)find(hql).get(0);
		}
		return baseUser;
	}
	/**
	 * 根据账号,手机,邮箱导入学生
	 * @param condition
	 * @param conditionType
	 * @param securityCode
	 * @return
	 */
	public String getUserIdByCondition(String condition,String conditionType,String securityCode)
	{
		String userId="";
		String hql="select t from BaseUser t where 1=1";
		if (conditionType.equals("loginName"))
		{
			hql=hql+" and t.loginName='"+condition+"'";
		}
		else if (conditionType.equals("phone"))
		{
			hql=hql+" and t.mobile='"+condition+"'";
		}
		else if (conditionType.equals("email"))
		{
			hql=hql+" and t.email='"+condition+"'";
		}
		if (isNotEmpty(securityCode))
		 {
	 		hql=hql+" and t.securityCode='"+securityCode+"'";
		 }
		List result=find(hql);
		BaseUser baseUser=new BaseUser();
		if(result.size()>0){
			baseUser=(BaseUser)find(hql).get(0);
			userId=baseUser.getId();
		}
		return userId;
	}
	/**
	 * 产生4位安全码
	 * @return 返回安全码
	 */
	public String createSecurityCode()
	{
		int x=(int)(Math.random()*(9999-1000+1)+1000);
		String securityCode=""+x;
		return securityCode;
	}
	
	public BaseUser getUserFindPwd(String loginName,String verifyHash){
		if(loginName!=null&&!loginName.equals("")&&verifyHash!=null&&!verifyHash.equals("")){
			String hql="from BaseUser t where 1=1  and t.loginName=:loginName and t.id=:verifyHash";
			
			Session session=this.getSession();
			Query q=session.createQuery(hql);
			q.setParameter("loginName", loginName);
			q.setParameter("verifyHash", verifyHash);
			List<BaseUser> list=q.list();
			this.releaseSession(session);
			if(list!=null&&list.size()==1){
				return list.get(0);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	public BaseUser reloadRedisUser(String userId){
		BaseUser baseUser =get(userId);
		if(baseUser!=null){
			return redisManager.saveBaseUser(baseUser);
		}else{
			return null;
		}
	}



	/**
	 * 群组管理搜索用户
	 * @param user
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public Page getPage(int pageSize, int pageNo,String sortColumns,boolean isAes,String loginName,String mobile,String schoolName) {
			return getOrderedPagedNamedQuery("sdBase::baseUser::query",sortColumns,isAes,pageNo,pageSize,
					loginName,"","1","","","","","","","","","","","",mobile,"","","","","","","","","","","","","","",schoolName,"");
			
	}


	/**
	 * 昵称唯一判断
	 * @param mobile
	 * @return
	 */
	public String getCountByNickname(String nickname,String id)
	{
		String flag="0";
		String hql="from BaseUser t where t.nickname=:nickname and t.id<>:id" ;
		Session session=this.getSession();
		Query q=session.createQuery(hql);
		q.setParameter("nickname", nickname);
		q.setParameter("id", id);
		//int cnt=Integer.valueOf((String)q.uniqueResult());
		int cnt=q.list().size();
		
		if(cnt==0) flag="0";//目前没有这个电话号码
		else flag="1";//已经存在此号码
		this.releaseSession(session);
		return flag;
	}
	
	public BaseUser oneUserByLoginName(String loginname){
		BaseUser baseUser = null;
		List<BaseUser> list = this.getHibernateTemplate().find("from BaseUser b where b.loginName = ?",loginname);
		if(list!=null&&list.size()>0){
			baseUser = list.get(0);
		}
		return baseUser;
	}


	/**
	 * 	根据用户Id跟新用户部分个人信息
	 * @param inBaseUser
	 * @author hmc
	 * @return
	 */
	public String reEditByUserId(BaseUser inBaseUser) {
		this.updateReload(inBaseUser);
		return null;
	}

	/**
	 * 
	 * 通过省市县查询初始化情况
	 * @author    hmc    2015年4月10日  下午6:34:33
	 * @param proCode	省code
	 * @param cityCode	市code
	 * @param countryCode 区,县code
	 * @return
	 */
	public List getAllIniState(String proCode, String cityCode,String countryCode) {
		Map<String,String> m=new HashMap<String, String>();
		if(null!=proCode&&!"".equals(proCode)){
			m.put("PROVINCE_ID", proCode);
		}
		if(null!=cityCode&&!"".equals(cityCode)){
			m.put("CITY_ID", cityCode);
		}
		if(null!=countryCode&&!"".equals(countryCode)){
			m.put("COUNTY_ID", countryCode);
		}
		
		
		//HibernateXsqlBuilderQueryDao<BaseUser> xsql=new HibernateXsqlBuilderQueryDao<BaseUser>();
		
		
		List list=this.getNamedQuery("misBase::getAllIniState::query");
		return null;
	}
	/**
	 * 更新用户数据
	 * @author    hmc    2015年5月7日  下午5:14:33
	 * @param columValues
	 * @param termValues
	 * @param tablesName
	 * @return
	 */
	public String updateData(Map<String, Object> columValues,
			Map<String, Object> termValues, String tablesName) {
		String sql = "update " + tablesName + " set ";
		Session session = this.getSession(true);
		for (String colums : columValues.keySet()) {

			String value = columValues.get(colums).toString();
			if(value.contains("to_date(")){
				sql = sql + colums + "=" + columValues.get(colums) + ",";
			}else if (value.equals("sysdate")) {
				sql = sql + colums + "=" + columValues.get(colums) + ",";
			} else {
				sql = sql + colums + "='" + columValues.get(colums) + "',";
			}
		}
		sql = sql.substring(0, sql.length() - 1);
		sql = sql + " where 1=1 ";
		for (String term : termValues.keySet()) {

			sql = sql + " and " + term + "='" + termValues.get(term) + "' ";
		}
		//System.out.println(sql);
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
		this.releaseSession(session);
		
		return "success";
	}
	
	
	
	
	
}

