/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2015
 */

package org.my431.base.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.my431.base.model.BaseProjectFile;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.my431.util.DateFormater;
import org.my431.util.MMap;
import org.springframework.stereotype.Repository;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@Repository
public class BaseProjectFileManager extends HibernateXsqlBuilderQueryDao<BaseProjectFile>{

	public Class getEntityClass() {
		return BaseProjectFile.class;
	}
	
	public Page findPage(BaseProjectFile query, int pageSize, int pageNo) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from BaseProjectFile t where 1=1 "
			  	+ "/~ and t.title = {title} ~/"
			  	+ "/~ and t.fileName = {fileName} ~/"
			  	+ "/~ and t.fileSize = {fileSize} ~/"
			  	+ "/~ and t.fileExt = {fileExt} ~/"
				+ "/~ and t.creTime >= {creTimeBegin} ~/"
				+ "/~ and t.creTime <= {creTimeEnd} ~/"
			  	+ "/~ and t.creUser = {creUser} ~/"
			  	+ "/~ and t.sourceUrl = {sourceUrl} ~/"
			  	+ "/~ and t.fileType = {fileType} ~/"
			  	+ "/~ and t.proId = {proId} ~/"
				+ "/~ order by [sortColumns] ~/";

		Map filters = new HashMap();  
				
		XsqlBuilder xsqlBuilder=new XsqlBuilder();
		
        if(isNotEmpty(query.getId())) {
            filters.put("fileId", query.getId()); 
        }
        if(isNotEmpty(query.getTitle())) {
            filters.put("title", query.getTitle()); 
        }
        if(isNotEmpty(query.getFileName())) {
            filters.put("fileName", query.getFileName()); 
        }
        if(isNotEmpty(query.getFileSize())) {
            filters.put("fileSize", query.getFileSize()); 
        }
        if(isNotEmpty(query.getFileExt())) {
            filters.put("fileExt", query.getFileExt()); 
        }
        if(isNotEmpty(query.getCreTime())) {
            filters.put("creTime", query.getCreTime()); 
        }
        if(isNotEmpty(query.getCreUser())) {
            filters.put("creUser", query.getCreUser()); 
        }
        if(isNotEmpty(query.getSourceUrl())) {
            filters.put("sourceUrl", query.getSourceUrl()); 
        }
        if(isNotEmpty(query.getFileType())) {
            filters.put("fileType", query.getFileType()); 
        }
        if(isNotEmpty(query.getProId())) {
            filters.put("proId", query.getProId()); 
        }
        
        XsqlFilterResult result = xsqlBuilder.generateHql(sql,filters); 
        
		return pageQuery(result.getXsql(), pageNo, pageSize,result.getAcceptedFilters());
	}
	
	
	/**
	 * 商业项目文件
	 * author  90  
	 * on 2015-4-2
	 * @param projectId
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public Page getProjectFile(String projectId,String type,int pageSize,int pageNo){
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("pid", projectId);
		if(isNotEmpty(type)){
			map.put("type", type);
		}
		return this.getPagedNamedQuery("misBase::getProjectFileListPage::query", pageNo, pageSize, map);
	}
    

	/**
	 * 商业项目文件 数
	 * author  90  
	 * on 2015-4-2
	 * @param projectId
	 * @return
	 */
	public List getFileCnt(String projectId,String type){
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("pid", projectId);
		if(isNotEmpty(type)){
			map.put("type", type);
		}
		return this.getNamedQuery("misBase::getProjectFileCntList::query",map);
	}

	/**
	 * 获得项目文件
	 * author  90  
	 * on 2015-4-3
	 * @param projectId
	 * @return
	 */
	public Map<String ,List> getProjectFileByType(String projectId){
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("pid", projectId);
		Page page= this.getPagedNamedQuery("misBase::getProjectFileListPage::query", 1, 100000, map);
		//一个项目最大十万个文件应该就足够了
		Map<String ,List> mapFileList=new HashMap<String ,List>();//文件类型
		List zbList =new ArrayList();//招标
		List htList =new ArrayList();//合同
		List ysList =new ArrayList();//验收
		List qtList =new ArrayList();//其他
		List sjList =new ArrayList();//审计
		List list=(List) page.getResult();
		if(list!=null&&list.size()>0){
			for(Object o:list){
				Map<String ,Object> mapObject=(Map<String, Object>) o;
				if(mapObject.get("FILE_TYPE")!=null){//
					if(mapObject.get("FILE_TYPE").equals("project_file_type_zhaobiao")){
						//zbMap.put("project_file_type_zhaobiao",(List) o);
						zbList.add(o);
					}if(mapObject.get("FILE_TYPE").equals("project_file_type_hetong")){
						htList.add(o);
					}if(mapObject.get("FILE_TYPE").equals("project_file_type_yanshou")){
						ysList.add(o);
					}if(mapObject.get("FILE_TYPE").equals("project_file_type_qita")){
						qtList.add(o);
					}if(mapObject.get("FILE_TYPE").equals("project_file_type_shenji")){
						sjList.add(o);
					}
				}
			}
		}
		mapFileList.put("ht", htList);
		mapFileList.put("zb", zbList);
		mapFileList.put("qt", qtList);
		mapFileList.put("ys", ysList);
		mapFileList.put("sj", sjList);
		return mapFileList;
	}
	/**
	 * 获得项目文件
	 * author  90  
	 * on 2015-4-3
	 * @param projectId
	 * @return
	 * hyl修改为只取最新时间上传的照片
	 */
	public Map<String ,List> getProjectFileByTypeV2(String projectId){
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("pid", projectId);
		Page page= this.getPagedNamedQuery("misBase::getProjectFileListPage::query", 1, 100000, map);
		//一个项目最大十万个文件应该就足够了
		Map<String ,List> mapFileList=new HashMap<String ,List>();//文件类型
		List zbList =new ArrayList();//招标
		List htList =new ArrayList();//合同
		List ysList =new ArrayList();//验收
		List qtList =new ArrayList();//其他
		List sjList =new ArrayList();//其他
		List list=(List) page.getResult();
		Map<String, String> map_now=new HashMap<String, String>();
		if(list!=null&&list.size()>0){
			for(Object o:list){
				Map<String ,Object> mapObject=(Map<String, Object>) o;
				Object objtime=mapObject.get("CRE_TIME");
				if(objtime!=null){
					String time=objtime.toString().substring(0, 10);
					if(mapObject.get("FILE_TYPE")!=null){
						if(mapObject.get("FILE_TYPE").equals("project_file_type_zhaobiao")){
							//zbMap.put("project_file_type_zhaobiao",(List) o);
							if(zbList.size()==0){
								map_now.put("time_zb", time);
							}
							if(map_now.get("time_zb").equals(time)){
								zbList.add(o);
							}
						}if(mapObject.get("FILE_TYPE").equals("project_file_type_hetong")){
							if(htList.size()==0){
								map_now.put("time_ht", time);
							}
							if(map_now.get("time_ht").equals(time)){
								htList.add(o);
							}
							//htList.add(o);
						}if(mapObject.get("FILE_TYPE").equals("project_file_type_yanshou")){
							if(ysList.size()==0){
								map_now.put("time_ys", time);
							}
							if(map_now.get("time_ys").equals(time)){
								ysList.add(o);
							}
							//ysList.add(o);
						}if(mapObject.get("FILE_TYPE").equals("project_file_type_qita")){
							if(qtList.size()==0){
								map_now.put("time_qt", time);
							}
							if(map_now.get("time_qt").equals(time)){
								qtList.add(o);
							}
							//qtList.add(o);
						}if(mapObject.get("FILE_TYPE").equals("project_file_type_shenji")){
							if(sjList.size()==0){
								map_now.put("time_sj", time);
							}
							if(map_now.get("time_sj").equals(time)){
								sjList.add(o);
							}
							//sjList.add(o);
						}
					}
					
				}
			}
		}
		mapFileList.put("ht", htList);
		mapFileList.put("zb", zbList);
		mapFileList.put("qt", qtList);
		mapFileList.put("ys", ysList);
		mapFileList.put("sj", sjList);
		return mapFileList;
	}
	/**
	 * 查看项目是否有该名字
	 * author  90  
	 * on 2015-5-19
	 * @param name
	 * @param areaId
	 * @return
	 */
	public boolean getProjectByNameAndArea(String name,String pid,String fileType){
		boolean flag=true;
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name", name);
		map.put("pid", pid);
		map.put("fileType", fileType);
		List list=this.getNamedQuery("mis::getProjectFileByNameAndAreaList::query", map);
		if(list!=null&&list.size()>0){
			flag=false;
		}
		return flag;
	}
	
	
	/**
	 * 获取
	 * author  90  
	 * on 2016-3-15
	 * @param projectId
	 * @param type
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public Map<String,List> getProjectFileV2(String projectId,String type){
		Map<String,List> mrlt= new LinkedHashMap<String,List>();
		/*Map<String ,Object> map=new HashMap<String,Object>();
		map.put("pid", projectId);
		if(isNotEmpty(type)){
			map.put("type", type);
		}*/
		Page page= this.getProjectFile(projectId, type, 100000, 1);
		List<Map<String,Object>> list= (List<Map<String,Object>>) page.getResult();
		if(list!=null&& list.size()>0){
			for (int i = 0; i <list.size(); i++) {
				Map<String,Object> thisMap=list.get(i);
				Date date=(Date) thisMap.get("CRE_TIME");
				String dateStr=DateFormater.DateToString(date, "yyyy-MM-dd");;
			    if(mrlt.containsKey(dateStr)){
			    	List thisList=mrlt.get(dateStr);//当下map所含的List
			    	thisList.add(thisMap);
			    	mrlt.put(dateStr, thisList);
			    }else{
			    	List thisList =new ArrayList();
			    	thisList.add(thisMap);
			    	mrlt.put(dateStr, thisList);
			    }
			}
		}
		return mrlt;
	}
	
	/**
	 * 删除文件
	 * author  90  
	 * on 2016-7-26
	 * @param proId
	 * @return
	 */
	public String deleteFile(String proId){//ProId
		Session session =this.getSession();
		Query query=session.createSQLQuery("delete BASE_PROJECT_FILE where PRO_ID='"+proId+"'");
		query.executeUpdate();
		this.releaseSession(session);
		return "success";
	}
	/**
	 * 传入账户id，获取对应的文件数,这里获取的是全部的文件数目
	 * author  hyl  
	 * on 2016-07-27
	 * @param projectId
	 * @param type
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public Map<String, String> getProjectFileCountByProId(String rootAccountIds) {
		Map<String, String> mapV=new HashMap<String, String>();
		Map<String, String> map=new HashMap<String, String>();
		if(StringUtils.isNotBlank(rootAccountIds)){
			map.put("otherQuery", " and t.PRO_ID in("+rootAccountIds+") ");
		}
		List<Map<String, Object>> list=this.getNamedQuery("misBase::getProjectFileCountByProId::query", map);
		if(list!=null&&list.size()>0){
			for (Map<String, Object> map2 : list) {
				String proid=map2.get("PROID").toString();
				String count=MMap.isnullInt(map2.get("COUNT"))+"";
				mapV.put(proid, count);
			}
		}
		return mapV;
	}
	/**
	 * 传入账户id，获取对应的文件数,这里获取的是最近一次上传的文件数
	 * author  hyl  
	 * on 2016-08-01
	 * @param projectId
	 * @param type
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public MMap getProjectFileCountByProIdV2(String rootAccountIds) {
		Map<String, String> map=new HashMap<String, String>();
		if(StringUtils.isNotBlank(rootAccountIds)){
			map.put("otherQuery", " and t.PRO_ID in("+rootAccountIds+") ");
		}
		Page page= this.getPagedNamedQuery("misBase::getProjectFileListPage::query", 1, 100000, map);
		List<Map<String,Object>> accountPhotoist =new ArrayList<Map<String,Object>>();//
		//一个项目最大十万个文件应该就足够了
		List<Map<String,Object>> list=(List) page.getResult();
		Map<String, Map<String,List>> map1=new HashMap<String, Map<String,List>>();
		Map<String, String> mapCount=new HashMap<String,String>();
		Map<String, List<Map<String,Object>>> mapList=new HashMap<String,List<Map<String,Object>>>();
		if(list!=null&& list.size()>0){
			for (int i = 0; i <list.size(); i++) {
				Map<String,Object> thisMap=list.get(i);
				Object objtime=thisMap.get("CRE_TIME");
				String accountId=thisMap.get("PRO_ID").toString();
				if(objtime!=null){
					Date date=(Date) thisMap.get("CRE_TIME");
					String time=DateFormater.DateToString(date, "yyyy-MM-dd");
					String key=accountId;
					if(map1.get(key)!=null){
						Map<String,List> mapvv=map1.get(key);
						if(mapvv.get(time)!=null){
							List listvv=(List) mapvv.get(time);
							listvv.add(thisMap);
							mapCount.put(key,(MMap.isnullInt(mapCount.get(key))+1)+"");
							mapList.put(key, listvv);
						}
					}else {
						Map<String,List> mapvv=new HashMap<String, List>();
						List listvv=new ArrayList();
						listvv.add(thisMap);
						mapvv.put(time, listvv);
						map1.put(key,mapvv);
						mapCount.put(key, "1");
						mapList.put(key, listvv);
					}
				}
			}
		}
		MMap mMap=new MMap();
		mMap.setObj(mapCount);
		mMap.setObj1(mapList);
		return mMap;
	}
	
	
	public List<BaseProjectFile> getUploadFile(String projectId,String type){
		Session session = this.getSession();  
		Criteria cri = session.createCriteria(BaseProjectFile.class);
		cri.add(Restrictions.eq("proId", projectId));  
		cri.add(Restrictions.eq("fileType", type));  
		List<BaseProjectFile> list = cri.list();  
		return list;
		
	}
	
	
}
