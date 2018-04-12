/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2015
 */

package org.my431.base.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import org.hibernate.Query;
import org.hibernate.Session;
import org.my431.base.model.BaseAreaTree;
import org.my431.base.model.StatLoginData;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.my431.util.GeneralTreeNode;
import org.springframework.stereotype.Repository;

@Repository
public class StatLoginDataManager extends HibernateXsqlBuilderQueryDao<StatLoginData>{

	public Class getEntityClass() {
		return StatLoginData.class;
	}
	
	public Page findPage(StatLoginData query, int pageSize, int pageNo) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from StatLoginData t where 1=1 "
			  	+ "/~ and t.areaId = {areaId} ~/"
			  	+ "/~ and t.areaName = {areaName} ~/"
			  	+ "/~ and t.areaCode = {areaCode} ~/"
			  	+ "/~ and t.parentCode = {parentCode} ~/"
			  	+ "/~ and t.sumtype = {sumtype} ~/"
			  	+ "/~ and t.mlast1Cnt = {mlast1Cnt} ~/"
			  	+ "/~ and t.mlast2Cnt = {mlast2Cnt} ~/"
			  	+ "/~ and t.mlast3Cnt = {mlast3Cnt} ~/"
			  	+ "/~ and t.mlast4Cnt = {mlast4Cnt} ~/"
			  	+ "/~ and t.mlast5Cnt = {mlast5Cnt} ~/"
			  	+ "/~ and t.mlast6Cnt = {mlast6Cnt} ~/"
			  	+ "/~ and t.mlast6plusCnt = {mlast6plusCnt} ~/"
				+ "/~ order by [sortColumns] ~/";

		Map filters = new HashMap();  
				
		XsqlBuilder xsqlBuilder=new XsqlBuilder();
		
        if(isNotEmpty(query.getId())) {
            filters.put("id", query.getId()); 
        }
        if(isNotEmpty(query.getAreaId())) {
            filters.put("areaId", query.getAreaId()); 
        }
        if(isNotEmpty(query.getAreaName())) {
            filters.put("areaName", query.getAreaName()); 
        }
        if(isNotEmpty(query.getAreaCode())) {
            filters.put("areaCode", query.getAreaCode()); 
        }
        if(isNotEmpty(query.getParentCode())) {
            filters.put("parentCode", query.getParentCode()); 
        }
        if(isNotEmpty(query.getSumtype())) {
            filters.put("sumtype", query.getSumtype()); 
        }
        if(isNotEmpty(query.getMlast1Cnt())) {
            filters.put("mlast1Cnt", query.getMlast1Cnt()); 
        }
        if(isNotEmpty(query.getMlast2Cnt())) {
            filters.put("mlast2Cnt", query.getMlast2Cnt()); 
        }
        if(isNotEmpty(query.getMlast3Cnt())) {
            filters.put("mlast3Cnt", query.getMlast3Cnt()); 
        }
        if(isNotEmpty(query.getMlast4Cnt())) {
            filters.put("mlast4Cnt", query.getMlast4Cnt()); 
        }
        if(isNotEmpty(query.getMlast5Cnt())) {
            filters.put("mlast5Cnt", query.getMlast5Cnt()); 
        }
        if(isNotEmpty(query.getMlast6Cnt())) {
            filters.put("mlast6Cnt", query.getMlast6Cnt()); 
        }
        if(isNotEmpty(query.getMlast6plusCnt())) {
            filters.put("mlast6plusCnt", query.getMlast6plusCnt()); 
        }
        
        XsqlFilterResult result = xsqlBuilder.generateHql(sql,filters); 
        
		return pageQuery(result.getXsql(), pageNo, pageSize,result.getAcceptedFilters());
	}
	

	/**
	 * 查看登录
	 * author  90  
	 * on 2015-5-27
	 * @param areaCode
	 * @param myOrder
	 * @return
	 */
	public List loginUpdateTimeByAreaCode(String areaCode,String myOrder){
		Map<String,String> map=new HashMap<String,String>();
		map.put("areaCode", areaCode);
		if(myOrder!=null){
			map.put("orderBy", myOrder);
		}
		return this.getNamedQuery("misBase::loginUpdateTimeByAreaCode::query2",map);
	}
	
	
	/**
	 * 包含下级
	 * author  90  
	 * on 2015-5-27
	 * @param parentCode
	 * @param myOrder
	 * @return
	 */
	public List loginUpdateTimeByParentCode(String parentCode,String myOrder){
		Map<String,String> map=new HashMap<String,String>();
		/*map.put("areaCode", " (t.area_code='"+parentCode+"' and t.sumtype=0)");		
		map.put("parentCode", " (t.parent_code='"+parentCode+"' and t.sumtype=1)");*/
		map.put("areaCode", parentCode);
		map.put("parentCode"," (t.parent_code='"+parentCode+"')");
		if(myOrder!=null){
			map.put("orderBy", myOrder);
		}
		Map<String,Map<String,Object>> mapRlt=new HashMap<String,Map<String,Object>>();
		List<Map<String,Object> > list=this.getNamedQuery("misBase::loginUpdateTimeByParentCodeNo::query2",map);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				String thisCode=(String) list.get(i).get("AREA_CODE");
				mapRlt.put(thisCode, list.get(i));
			}
		}
		list.removeAll(list);
		
		List<BaseAreaTree> treeList=CacheBaseAreaManager.getTreeByParentCode(parentCode);
		list.add(mapRlt.get(parentCode));
		if(treeList!=null&&treeList.size()>0){
			for (int j = 0; j <treeList.size(); j++) {
				String nowCode=treeList.get(j).getNodeCode();
				//System.out.println(treeList.get(j).getNodeName());
				list.add(mapRlt.get(nowCode));
			}
		}
		
		
		return list;
	}

     /**
      * 获取登录数，根据日期
      * author  90  
      * on 2015-5-27
      * @param areaId
      * @param lastYue
      * @return
      */
	 public  List getDataForUpdateLoginCntList(String areaId,String lastYue){
			HashMap<String, Object> map = new HashMap<String, Object>();
			StringBuffer sqlArea = new StringBuffer(" and ");
			sqlArea.append("(");
			sqlArea.append( "v.area_Id='"+areaId+"' or v.PROVINCE_ID='"+areaId+"' or v.CITY_ID='"+areaId+"' or v.COUNTY_ID='"+areaId+"'");
			sqlArea.append(")");
			if (!areaId.equals("1"))
			{
				map.put("areaStr", sqlArea.toString());
			}
			
			StringBuffer sql = new StringBuffer(" and ");
			sql.delete(sql.lastIndexOf(" "),sql.length());
			sql.append(")");
			StringBuffer lastYueStr = new StringBuffer(" and ");
			if (!lastYue.equals("7"))
			{
				lastYueStr.append("v.LAST_LOGIN_TIME  between last_day(add_months(SYSDATE, -"+lastYue+")) + 1 and last_day(add_months(SYSDATE, -"+(Integer.parseInt(lastYue)-1)+"))"); 
			}else
			{
				lastYueStr.append(" v.LAST_LOGIN_TIME  <last_day(add_months(SYSDATE, -"+(Integer.parseInt(lastYue)-1)+"))+1"); 
			}
			map.put("lastYueStr", lastYueStr.toString());
			return this.getNamedQuery("misBase::getDataForUpdateLoginCntList::query", map);
		}
	  
	/**
	 * 统计定时任务 
	 * author  90  
	 * on 2015-5-27
	 */
	public void loginCntstatistics(){
		//先清空表
		this.clearTableInfo("STAT_LOGIN_DATA");
		Map<String,GeneralTreeNode> areaList=CacheBaseAreaManager.getAllTreeByParentCode("062");
		
		for (GeneralTreeNode area : areaList.values()){
			int schoolCntData = 0;
			int[] cntArray =new int[7];
			for (int i=0;i<7;i++)
			{
				 List listData = this.getDataForUpdateLoginCntList(area.getId(), (i+1)+"");
				 Map<String,Object> map=( Map<String,Object>)listData.get(0);
				 int  cnt = Integer.parseInt(map.get("COUNT(*)").toString() );
				 schoolCntData=schoolCntData+cnt;
				 cntArray[i] = cnt;
			}
			
			StatLoginData data=new StatLoginData();
			data.setAreaCode(area.getNodeCode());
			data.setAreaId(area.getId());
			data.setAreaName(area.getNodeName());
			data.setParentCode(area.getParentCode());
			data.setMlast1Cnt(cntArray[0]);
			data.setMlast2Cnt(cntArray[1]);
			data.setMlast3Cnt(cntArray[2]);
			data.setMlast4Cnt(cntArray[3]);
			data.setMlast5Cnt(cntArray[4]);
			data.setMlast6Cnt(cntArray[5]);
			data.setMlast6plusCnt(cntArray[6]);
			data.setUserCnt(schoolCntData);
			this.save(data);
			
		}
		
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
}
