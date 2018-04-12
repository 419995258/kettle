/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2011
 */

package org.my431.base.services;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.my431.base.model.BaseArea;
import org.my431.base.model.BaseAreaTree;
import org.my431.base.model.BaseSchool;
import org.my431.base.model.BaseUser;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.my431.platform.utils.SetValueUtil;
import org.my431.util.DateFormater;
import org.my431.util.GeneralTreeNode;
import org.my431.util.MMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BaseAreaManager extends HibernateXsqlBuilderQueryDao<BaseArea> {
	private BaseUserManager baseUserManager;
	@Override
	protected void onValid(BaseArea entity) {

		if (entity.getId() == null) {
			// 新增时将departmentCode,nodeLevel,seqNo
			String array[] = getCodeArray(entity.getParentCode());
			entity.setAreaCode(array[0]);
			entity.setNodeLevel(array[0].length() / 3);
			entity.setSeqNo(getNextSeqNo(entityClass.getName(), entity
					.getParentCode()));
		}
	}

	public Class getEntityClass() {
		return BaseArea.class;
	}
	public void init() {
			//List<BaseArea> list = getAllList();
			String hql = "from BaseArea t where 1=1 and t.deleteFlag<>1 ";
			Session session = this.getSession();
			Query query = session.createQuery(hql);
			//q.setParameter("schoolName", schoolName);
			List<BaseArea> list = query.list();
			List<GeneralTreeNode> tree=new ArrayList<GeneralTreeNode>();
			Map<String,GeneralTreeNode> ht=new LinkedHashMap<String,GeneralTreeNode>();
			int i=0;
			for (BaseArea node : list) {
				if(node.getDeleteFlag()==null||node.getDeleteFlag().equals(0)){
					GeneralTreeNode treeNode = new GeneralTreeNode();
					treeNode.setId(node.getId());
					treeNode.setNodeCode(node.getAreaCode());
					treeNode.setParentCode(node.getParentCode());
					treeNode.setNodeName(node.getAreaName());
					treeNode.setNodeDescName(node.getAreaDesc());
					treeNode.setNodeLink(node.getNationalCode());
					treeNode.setNodeType(node.getNodeType());
					treeNode.setNodeLevel(node.getNodeLevel());
					treeNode.setSeqNo(node.getSeqNo());
					tree.add(treeNode);
					ht.put(node.getAreaCode(), treeNode);
					CacheBaseAreaManager.setAreaIdKeyMap(node.getId(), node);
					CacheBaseAreaManager.setAreaCodeKeyMap(node.getAreaCode(), node);
					i++;
					System.out.println("======="+i);
				}
			}
			
			CacheBaseAreaManager.setAllAreaMap(ht);
			
			initAreaTree(CacheBaseAreaManager.getAllAreaMap());
			
			getAreaListMapOfAll();
	}
	/**
	 * 
	* @Description:将国家数据导入到本地  
	* @author hyl     
	* @date 2017-8-8 下午4:01:59  
	 */
	public void initTbMstJyglbmdmToBaseArea() {}
	/**
	 * 
	* @Description: 递归查询 
	* @author hyl     
	* @date 2017-8-8 下午4:55:15  
	 */
	public List<Map<String, Object>> getJyglbmList(String parentCode) {return null;}
	/**
	 * 
	* @Description:导入之后由于areaCode和pareantCode都是6位现在格式化，例如：330000-033，330100-033001，330102-033001002 
	* @author hyl     
	* @date 2017-8-9 下午3:22:09  
	* 直辖市单独处理
	* update base_area t set t.parent_code='011' where t.parent_code='011000';
	* update base_area t set t.parent_code='012' where t.parent_code='012000';
	* update base_area t set t.parent_code='031' where t.parent_code='031000';
	* update base_area t set t.parent_code='050' where t.parent_code='050000';
	 */
	public void init_formateAreaCode() {
		List<BaseArea> area_list = getAll();
		//List<BaseArea> list = new ArrayList<BaseArea>();
		if (MMap.validateList(area_list)) {
			for (BaseArea baseArea : area_list) {
				//nodeLevel 1为省级 2市级 3县级
				if (!"-1".equals(baseArea.getAreaCode()+"")) {
					if ("1".equals(baseArea.getNodeLevel()+"")) {//1为省级
						baseArea.setAreaCode("0"+baseArea.getAreaCode().substring(0, 2));
					}
					if ("2".equals(baseArea.getNodeLevel()+"")) {//2市级
						baseArea.setAreaCode("0"+baseArea.getAreaCode().substring(0, 2)+"0"+baseArea.getAreaCode().substring(2, 4));
						baseArea.setParentCode("0"+baseArea.getParentCode().substring(0, 2));
					}
					if ("3".equals(baseArea.getNodeLevel()+"")) {//3县级
						//直辖市-相当于省，下面直接挂县
						if ("110000".equals(baseArea.getAreaCode()) || "120000".equals(baseArea.getAreaCode()) || "310000".equals(baseArea.getAreaCode()) || "500000".equals(baseArea.getAreaCode())) {
							baseArea.setAreaCode("0"+baseArea.getAreaCode().substring(0, 2)+"0"+baseArea.getAreaCode().substring(2, 4)+"0"+baseArea.getAreaCode().substring(4, 6));
							baseArea.setParentCode("0"+baseArea.getParentCode().substring(0, 2));
						}else {//非直辖市
							baseArea.setAreaCode("0"+baseArea.getAreaCode().substring(0, 2)+"0"+baseArea.getAreaCode().substring(2, 4)+"0"+baseArea.getAreaCode().substring(4, 6));
							baseArea.setParentCode("0"+baseArea.getParentCode().substring(0, 2)+"0"+baseArea.getParentCode().substring(2, 4));
						}
						
					}
					update(baseArea);
				}
			}
		}
	}
	/**
	 * 
	* @Description: code截取后转化为本地表的code 330000-033，330100-033001，330102-033001002  
	* @author hyl     
	 */
	public String  transAreaCode(String areaCode,String nodeLevel) {
		String newAreaCode = "";
		if (StringUtils.isNotBlank(nodeLevel)) {
			if ("1".equals(nodeLevel)) {//1为省级
				newAreaCode = "0" + areaCode.substring(0, 2);
			}
			if ("2".equals(nodeLevel)) {//2市级
				newAreaCode = "0" + areaCode.substring(0, 2) + "0" + areaCode.substring(2, 4);
			}
			if ("3".equals(nodeLevel)) {//3县级
				newAreaCode = "0" + areaCode.substring(0, 2) + "0" + areaCode.substring(2, 4) + "0" + areaCode.substring(4, 6);
			}
		}else {
			if (areaCode.indexOf("00")>-1) {
				if (areaCode.indexOf("0000")>-1) {
					newAreaCode = "0" + areaCode.substring(0, 2);
				}else {
					newAreaCode = "0" + areaCode.substring(0, 2) + "0" + areaCode.substring(2, 4);
				}
			}else {
				newAreaCode = "0" + areaCode.substring(0, 2) + "0" + areaCode.substring(2, 4) + "0" + areaCode.substring(4, 6);
			}
		}
		return newAreaCode;
	}
	
	public void initAreaTree(Map<String,GeneralTreeNode> ht){
		Map<String,BaseAreaTree> areaTreeMap=new LinkedHashMap<String,BaseAreaTree>();
		
		
		Set keys=ht.keySet();
		
		Iterator keyList=keys.iterator();
		
		while(keyList.hasNext()){
			String key=(String)keyList.next();
			GeneralTreeNode node=ht.get(key);
			
			if(node.getNodeCode().length()==3){
				if(!areaTreeMap.containsKey(node.getNodeCode())){
					BaseAreaTree areaTree=new BaseAreaTree();
					
					areaTree.setAreaType(node.getNodeExtra());
					areaTree.setId(node.getId());
					areaTree.setNationalCode(node.getNodeLink());
					areaTree.setNodeCode(node.getNodeCode());
					areaTree.setNodeName(node.getNodeName());
					areaTree.setNodeDescName(node.getNodeDescName());
					areaTree.setNodeType(node.getNodeType());
					areaTree.setParentCode(node.getParentCode());
					areaTree.setSeqNo(node.getSeqNo());
					areaTree.setMapAreaCode(node.getMapCode());
					areaTree.setMapParentCode(node.getMapParentCode());
					areaTree.setNodeLevel(node.getNodeLevel());
					areaTree.setShangBaoStatus2014(node.getShangBaoStatus2014());
					areaTree.setShangBaoStatus2015(node.getShangBaoStatus2015());
					areaTree.setShangBaoStatus2016(node.getShangBaoStatus2016());
					areaTree.setShangBaoStatus2017(node.getShangBaoStatus2017());
					areaTree.setShangBaoStatus2018(node.getShangBaoStatus2018());
					areaTree.setIsZcq(node.getIsZcq());
					areaTree.setIsChx(node.getIsChx());
					areaTree.setIsPkx(node.getIsPkx());
					areaTree.setTbFlag(node.getTbFlag());
					areaTree.setSubAreaTree(new LinkedHashMap<String, Map<String, BaseAreaTree>>());
					
					/////////
					Set keys2=ht.keySet();
					
					Iterator keyList2=keys2.iterator();
					Map<String, BaseAreaTree> m2=new LinkedHashMap<String, BaseAreaTree>();
					while(keyList2.hasNext()){
						String key2=(String)keyList2.next();
						GeneralTreeNode node2=ht.get(key2);
						
						if(node2.getParentCode().equals(node.getNodeCode())){
							BaseAreaTree areaTree2=new BaseAreaTree();
							
							areaTree2.setAreaType(node2.getNodeExtra());
							areaTree2.setId(node2.getId());
							areaTree2.setNationalCode(node2.getNodeLink());
							areaTree2.setNodeCode(node2.getNodeCode());
							areaTree2.setNodeName(node2.getNodeName());
							areaTree2.setNodeDescName(node2.getNodeDescName());
							areaTree2.setNodeType(node2.getNodeType());
							areaTree2.setParentCode(node2.getParentCode());
							areaTree2.setSeqNo(node2.getSeqNo());
							areaTree2.setNodeLevel(node2.getNodeLevel());
							areaTree2.setMapAreaCode(node2.getMapCode());
							areaTree2.setMapParentCode(node2.getMapParentCode());
							areaTree2.setShangBaoStatus2014(node2.getShangBaoStatus2014());
							areaTree2.setShangBaoStatus2015(node2.getShangBaoStatus2015());
							areaTree2.setShangBaoStatus2016(node2.getShangBaoStatus2016());
							areaTree2.setShangBaoStatus2017(node2.getShangBaoStatus2017());
							areaTree2.setShangBaoStatus2018(node2.getShangBaoStatus2018());
							areaTree2.setIsZcq(node2.getIsZcq());
							areaTree2.setIsChx(node2.getIsChx());
							areaTree2.setIsPkx(node2.getIsPkx());
							areaTree2.setTbFlag(node2.getTbFlag());
							areaTree2.setSubAreaTree(new LinkedHashMap<String, Map<String, BaseAreaTree>>());
							

							m2.put(node2.getNodeCode(), areaTree2);
							
							if(!areaTree.getSubAreaTree().containsKey(node2.getNodeCode())){
								
								//////////////
								Set keys3=ht.keySet();
								
								Iterator keyList3=keys3.iterator();
								Map<String, BaseAreaTree> m3=new LinkedHashMap<String, BaseAreaTree>();
								while(keyList3.hasNext()){
									String key3=(String)keyList3.next();
									GeneralTreeNode node3=ht.get(key3);
									
									areaTree.setSubAreaTree(new LinkedHashMap<String, Map<String, BaseAreaTree>>());
									
									if(node3.getParentCode().equals(node2.getNodeCode())){
										BaseAreaTree areaTree3=new BaseAreaTree();
										areaTree3.setAreaType(node3.getNodeExtra());
										areaTree3.setId(node3.getId());
										areaTree3.setNationalCode(node3.getNodeLink());
										areaTree3.setNodeCode(node3.getNodeCode());
										areaTree3.setNodeName(node3.getNodeName());
										areaTree3.setNodeDescName(node3.getNodeDescName());
										areaTree3.setNodeType(node3.getNodeType());
										areaTree3.setParentCode(node3.getParentCode());
										areaTree3.setSeqNo(node3.getSeqNo());
										areaTree3.setSubAreaTree(null);
										areaTree3.setNodeLevel(node3.getNodeLevel());
										areaTree3.setMapAreaCode(node3.getMapCode());
										areaTree3.setMapParentCode(node3.getMapParentCode());
										areaTree3.setShangBaoStatus2014(node3.getShangBaoStatus2014());
										areaTree3.setShangBaoStatus2015(node3.getShangBaoStatus2015());
										areaTree3.setShangBaoStatus2016(node3.getShangBaoStatus2016());
										areaTree3.setShangBaoStatus2017(node3.getShangBaoStatus2017());
										areaTree3.setShangBaoStatus2018(node3.getShangBaoStatus2018());
										areaTree3.setIsZcq(node3.getIsZcq());
										areaTree3.setIsChx(node3.getIsChx());
										areaTree3.setIsPkx(node3.getIsPkx());
										areaTree3.setTbFlag(node3.getTbFlag());
										m3.put(node3.getNodeCode(), areaTree3);
										
										if(!areaTree2.getSubAreaTree().containsKey(node3.getParentCode())){
											areaTree2.getSubAreaTree().put(node3.getParentCode(), m3);
										}
									}
								}
								//////////////
								
								areaTree.getSubAreaTree().put(node2.getParentCode(), m2);
							}
						}
					}
					////////

					
					areaTreeMap.put(node.getNodeCode(), areaTree);
				}
			}
		}
		CacheBaseAreaManager.setAreaMapkey(areaTreeMap);
	}
	/**
	 * 数据库查出的地区树形结构放到这里,不包括全国
	 * */
	public void getAreaListMapOfAll(){
		List<HashMap<Object,Object>> areaList=findSql("select t2.* from base_area t1,base_area t2 where t1.area_code=t2.parent_code and t1.delete_flag='0' and t2.delete_flag='0' order by t2.area_code asc");
		if(areaList!=null&&areaList.size()>0){
			CacheBaseAreaManager.setAreaListMapOfAllkey(areaList);
		}
	}
	
	
	//重载所有
	public void reload(){
		List<BaseArea> list = getAllList();
		List<GeneralTreeNode> tree=new ArrayList<GeneralTreeNode>();
		Map<String,GeneralTreeNode> ht=new LinkedHashMap<String,GeneralTreeNode>();
		for (BaseArea node : list) {
			if(node.getDeleteFlag()==null||node.getDeleteFlag().equals(0)){
			GeneralTreeNode treeNode = new GeneralTreeNode();
			treeNode.setId(node.getId());
			treeNode.setNodeCode(node.getAreaCode());
			treeNode.setParentCode(node.getParentCode());
			treeNode.setNodeName(node.getAreaName());
			treeNode.setNodeLink(node.getNationalCode());
			treeNode.setNodeType(node.getNodeType());
			treeNode.setNodeLevel(node.getNodeLevel());
			treeNode.setSeqNo(node.getSeqNo());
			tree.add(treeNode);
			ht.put(node.getAreaCode(), treeNode);
			CacheBaseAreaManager.setAreaIdKeyMap(node.getId(), node);
			CacheBaseAreaManager.setAreaCodeKeyMap(node.getAreaCode(), node);
			}
		}
		
		CacheBaseAreaManager.setAllAreaMap(ht);
		initAreaTree(CacheBaseAreaManager.getAllAreaMap());
	}
	
	//重载单个
	public void reloadSave(BaseArea node){
			Map<String,GeneralTreeNode> ht=CacheBaseAreaManager.getAllAreaMap();
			if(ht!=null){
				GeneralTreeNode treeNode = new GeneralTreeNode();
				treeNode.setId(node.getId());
				treeNode.setNodeCode(node.getAreaCode());
				treeNode.setParentCode(node.getParentCode());
				treeNode.setNodeName(node.getAreaName());
				treeNode.setNodeLink(node.getNationalCode());
				treeNode.setNodeType(node.getNodeType());
				treeNode.setNodeLevel(node.getNodeLevel());
				treeNode.setSeqNo(node.getSeqNo());
				ht.put(node.getAreaCode(), treeNode);
			
				CacheBaseAreaManager.setAllAreaMap(ht);
				
				CacheBaseAreaManager.setAreaIdKeyMap(node.getId(), node);
				CacheBaseAreaManager.setAreaCodeKeyMap(node.getAreaCode(), node);
			}
			
			LinkedHashMap<String, BaseAreaTree> map=CacheBaseAreaManager.getAreaMapkey();
			if(node.getAreaCode().length()==3){
				BaseAreaTree areaTree=map.get(node.getAreaCode());
				
				if(areaTree==null){
					areaTree=new BaseAreaTree();
				}
				
				areaTree.setId(node.getId());
				areaTree.setNationalCode(node.getNationalCode());
				areaTree.setNodeCode(node.getAreaCode());
				areaTree.setNodeName(node.getAreaName());
				areaTree.setNodeType(node.getNodeType());
				areaTree.setParentCode(node.getParentCode());
				areaTree.setSeqNo(node.getSeqNo());
				areaTree.setNodeLevel(node.getNodeLevel());
				map.put(node.getAreaCode(), areaTree);
				CacheBaseAreaManager.setAreaMapkey(map);
			}
			if(node.getAreaCode().length()==6){
				BaseAreaTree areaTree1=map.get(node.getAreaCode().substring(0, 3));
				Map<String,BaseAreaTree> areaTree=null;
				if(areaTree1!=null&&areaTree1.getSubAreaTree()!=null){
					areaTree=areaTree1.getSubAreaTree().get(node.getAreaCode().substring(0, 3));
				}
				BaseAreaTree baseAreaTree =null;
				if(areaTree!=null){
					baseAreaTree =areaTree.get(node.getAreaCode());
				}else{
					areaTree=new HashMap<String,BaseAreaTree>();
				}
				
				if(baseAreaTree==null){
					baseAreaTree=new BaseAreaTree();
				}
				
				baseAreaTree.setId(node.getId());
				baseAreaTree.setNationalCode(node.getNationalCode());
				baseAreaTree.setNodeCode(node.getAreaCode());
				baseAreaTree.setNodeName(node.getAreaName());
				baseAreaTree.setNodeType(node.getNodeType());
				baseAreaTree.setParentCode(node.getParentCode());
				baseAreaTree.setSeqNo(node.getSeqNo());
				baseAreaTree.setNodeLevel(node.getNodeLevel());
				areaTree.put(node.getAreaCode(), baseAreaTree);
				Map<String,Map<String,BaseAreaTree>> t=new LinkedHashMap<String,Map<String,BaseAreaTree>>();
				t.put(node.getAreaCode().substring(0, 3), areaTree);
				areaTree1.setSubAreaTree(t);
				map.put(node.getAreaCode().substring(0, 3), areaTree1);
				CacheBaseAreaManager.setAreaMapkey(map);
			}
			if(node.getAreaCode().length()==9){
				BaseAreaTree areaTree1=map.get(node.getAreaCode().substring(0, 3));
				Map<String,BaseAreaTree> map2=null;
				if(areaTree1!=null&&areaTree1.getSubAreaTree()!=null){
					map2=areaTree1.getSubAreaTree().get(node.getAreaCode().substring(0, 3));
				}
				if(map2==null){
					map2=new LinkedHashMap<String,BaseAreaTree>();
				}
				BaseAreaTree areaTree2=map2.get(node.getAreaCode().substring(0, 6));
				Map<String, BaseAreaTree> map3=null;
				if(areaTree2!=null&&areaTree2.getSubAreaTree()!=null){
					map3=areaTree2.getSubAreaTree().get(node.getAreaCode().substring(0, 6));
				}
				if(map3==null){
					map3=new LinkedHashMap<String,BaseAreaTree>();
				}
				BaseAreaTree areaTree3=map3.get(node.getAreaCode());
				if(areaTree3==null){
					areaTree3=new BaseAreaTree();
				}
				
				areaTree3.setId(node.getId());
				areaTree3.setNationalCode(node.getNationalCode());
				areaTree3.setNodeCode(node.getAreaCode());
				areaTree3.setNodeName(node.getAreaName());
				areaTree3.setNodeType(node.getNodeType());
				areaTree3.setParentCode(node.getParentCode());
				areaTree3.setSeqNo(node.getSeqNo());
				areaTree3.setNodeLevel(node.getNodeLevel());
				map3.put(node.getAreaCode(), areaTree3);
				map2.put(node.getAreaCode().substring(0, 6), areaTree2);
				map.put(node.getAreaCode().substring(0, 3), areaTree1);
				CacheBaseAreaManager.setAreaMapkey(map);
			}
			
	}
	
	public GeneralTreeNode getByCode(String areaCode){
		Map<String,GeneralTreeNode> ht=CacheBaseAreaManager.getAllAreaMap();
		return ht.get(areaCode);
	}
	
	public String getLabelByCode(String areaCode){
		
		Map<String,GeneralTreeNode> ht=CacheBaseAreaManager.getAllAreaMap();
		if(areaCode.length()==3){
			return ht.get(areaCode).getNodeName();
		}else if(areaCode.length()==6){
			return ht.get(areaCode.substring(0, 3)).getNodeName()+"-"+ht.get(areaCode).getNodeName();
		}else if(areaCode.length()==9){
			return ht.get(areaCode.substring(0, 3)).getNodeName()+"-"+ht.get(areaCode.substring(0, 6)).getNodeName()+"-"+ht.get(areaCode).getNodeName();
		}else if(areaCode.length()==2){
			return ht.get(areaCode).getNodeName();
		}else{
			return null;
		}
		
	}
	
	//重载单个
	public void reloadDel(BaseArea node){
		Map<String,GeneralTreeNode> ht=CacheBaseAreaManager.getAllAreaMap();
		ht.remove(node.getAreaCode());
		CacheBaseAreaManager.setAllAreaMap(ht);
		CacheBaseAreaManager.removeNodeById(node.getId());
		CacheBaseAreaManager.removeNodeByCode(node.getAreaCode());
		initAreaTree(ht);
	}
	
	public Page findPage(BaseArea query, int pageSize, int pageNo) {
		// XsqlBuilder syntax,please see
		// http://code.google.com/p/rapid-xsqlbuilder
		// [column]为字符串拼接, {column}为使用占位符.
		// [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接
		// [column] 为PageRequest的属性
		String sql = "select t from BaseArea t where 1=1 "
				+ "/~ and t.areaCode = {areaCode} ~/"
				+ "/~ and t.parentCode = {parentCode} ~/"
				+ "/~ and t.areaName = {areaName} ~/"
				+ "/~ and t.areaDesc = {areaDesc} ~/"
				+ "/~ and t.areaType = {areaTyp} ~/"
				+ "/~ and t.nodeLevel = {nodeLevel} ~/"
				+ "/~ and t.nationalCode = {nationalCode} ~/"
				+ "/~ order by [sortColumns] ~/";

		Map filters = new HashMap();  
		
		XsqlBuilder xsqlBuilder=new XsqlBuilder();
		

        if(isNotEmpty(query.getAreaCode())) {
            filters.put("areaCode", query.getAreaCode()); 
        }
        if(isNotEmpty(query.getId())) {
            filters.put("id", query.getId()); 
        }
        if(isNotEmpty(query.getParentCode())) {
        	filters.put("parentCode", query.getParentCode()); 
        }
        if(isNotEmpty(query.getAreaName())) {
            filters.put("areaName", query.getAreaName()); 
        }
        if(isNotEmpty(query.getNationalCode())) {
            filters.put("nationalCode", query.getNationalCode()); 
        }
        filters.put("sortColumns", "t.seqNo");
        
        XsqlFilterResult result = xsqlBuilder.generateHql(sql,filters); 
        
		return pageQuery(result.getXsql(), pageNo, pageSize,result.getAcceptedFilters());
	}
	
	public String[] getCodeArray(String departmentCode) {
		String[] array = { "", "" };
		String newDepartmentCode = "";
		String parentDepartmentCode = "";
		if (getMaxChildDepartmentCodeByParent("-1").equals("")) {
			newDepartmentCode = "001";
			parentDepartmentCode = "-1";
		} else if (departmentCode.equals("-1")) {
			String maxDepartmentCode = getMaxChildDepartmentCodeByParent("-1");
			int maxDepartmentCodeInt = Integer.parseInt(maxDepartmentCode);
			maxDepartmentCodeInt++;
			String temp_str = String.valueOf(maxDepartmentCodeInt);
			if (temp_str.length() < 3) {
				int count = temp_str.length();
				while (count < 3) {
					temp_str = "0" + temp_str;
					count++;
				}
				newDepartmentCode = temp_str;
				parentDepartmentCode = "-1";
			}
		} else {
			parentDepartmentCode = departmentCode;
			String maxChildDepartmentCode = getMaxChildDepartmentCodeByParent(parentDepartmentCode);

			if (maxChildDepartmentCode.equals("")) {
				newDepartmentCode = parentDepartmentCode + "001";
			} else {
				int maxChildDepartmentCodeInt = Integer
						.parseInt(maxChildDepartmentCode.substring(
								maxChildDepartmentCode.length() - 3,
								maxChildDepartmentCode.length()));
				maxChildDepartmentCodeInt++;
				String temp_str = String.valueOf(maxChildDepartmentCodeInt);
				if (temp_str.length() < 3) {
					int count = temp_str.length();
					while (count < 3) {
						temp_str = "0" + temp_str;
						count++;
					}
				}
				newDepartmentCode = parentDepartmentCode + temp_str;
			}
		}

		array[0] = newDepartmentCode;
		array[1] = parentDepartmentCode;

		return array;
	}

	Comparator<BaseArea> codeComparator = new Comparator<BaseArea>() {
		public int compare(BaseArea a, BaseArea b) {
			return (a.getAreaCode().compareTo(b.getAreaCode()));
		}
	};
	
	public String getMaxChildDepartmentCodeByParent(String parentCode) {
		String hql="from BaseArea t where t.parentCode=?";
		List<BaseArea> list=find(hql,parentCode);
		if (list != null && list.size() > 0) {
			Collections.sort(list, codeComparator);
			return list.get(list.size() - 1).getAreaCode();
		}
		return "";
	}
	public String getMaxChildCodeByParent(String parentCode) {
		String hql="select max(t.area_code) from Base_Area t where t.parent_code='"+parentCode+"'";
		Session session = this.getSession();
		Query q=session.createSQLQuery(hql);
		List list=q.list();
		return list.get(0).toString();
	}
	
	public String getMaxMapChildCodeByParent(String parentCode) {
		String hql="select max(t.map_area_code) from Base_Area t where t.map_Parent_Code='"+parentCode+"'";
		Session session = this.getSession();
		Query q=session.createSQLQuery(hql);
		List list=q.list();
		return list.get(0).toString();
	}
	
	public List<GeneralTreeNode> getTreeByParentCode(String parentCode) {
		List<GeneralTreeNode> subtree = new ArrayList<GeneralTreeNode>();
		Map<String,GeneralTreeNode> tree=CacheBaseAreaManager.getAllAreaMap();
		if(tree==null){
			reload();
			tree=CacheBaseAreaManager.getAllAreaMap();
		}
		
		if(tree!=null){
			Set<String> key = tree.keySet();
	        for (Iterator it = key.iterator(); it.hasNext();) {
	            String s = (String) it.next();
	            GeneralTreeNode t=tree.get(s);
				if(t.getParentCode().equals(parentCode)){
					subtree.add(t);
				}
	        }
		}
		return subtree;
	}
	
	public List getSchoolListByAreaId(String areaId){
		String hql="select s.id,s.schoolName from BaseSchool s where s.areaId=:areaId";
		Query q=this.getSession().createQuery(hql);
		q.setParameter("areaId", areaId);
		List list=q.list();
		return list;
	}
	
	/**
	 * 判断该地区是否可以删除
	 * @param baseArea
	 * @return
	 */
	public Boolean isDel(BaseArea baseArea){
		Boolean isdel=true;
		List<BaseUser>  list=baseUserManager.getUserNotIni(baseArea.getId(),"1");//某地区下所有已初始化用户
		List<BaseUser>  listb=baseUserManager.findBy("areaId", baseArea.getId());//某地区下所有用户
		if(list!=null&&list.size()>0){//如果地区下有已初始化用户，则不能删除
			isdel=false;
		}else{
		}
		List<GeneralTreeNode> list2=this.getTreeByParentCode(baseArea.getAreaCode());
		if(list2!=null&&list2.size()>0){//如果地区下有子地区，则不能删除
			isdel=false;
		}
		if(isdel){
			//地区下没有已初始化用户，把该地区所有用户删除
			for(BaseUser u:listb){
				u.setDeleteFlag("1");
				u.setDeleteTime(new Date());
				baseUserManager.update(u);
				baseUserManager.updateReload(u);
			}
		}
		return isdel;
	}

	public void setBaseUserManager(BaseUserManager baseUserManager) {
		this.baseUserManager = baseUserManager;
	}
	
	
	
	public List<BaseArea> searchArea(String name){
		String hql="select a from BaseArea a where 1=1 and a.deleteflag=0 ";
		
		if(name!=null){
			hql=hql+" and a.areaName like :name";
		}
		
		Session session=this.getSession();
		Query q=session.createQuery(hql);
		if(name!=null){
			q.setParameter("name", "%"+name+"%");
		}
		List list=q.list();
		return list;
	}
	/**
	 * 按地区查询管理员，视察员
	 * @author    hmc    2015年5月6日  下午2:52:40
	 * @param name
	 * @return
	 */
	public List<BaseArea> searchArea1(String name){
		Map<String,Object> m=new HashMap<String, Object>();
		m.put("areaName", "%"+name+"%");
		
		List<BaseArea> areaList=new ArrayList<BaseArea>();
		List list=this.getNamedQuery("misBase:searchArea1:query", m);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				String realKey[]=SetValueUtil.getKey((Map)(list.get(i)), true);//获得map中的key
				String key[]=SetValueUtil.getKey((Map)(list.get(i)),false);//获得进行自动封装的key
				BaseArea basetemp=new BaseArea();
				for(int j=0;j<key.length;j++){
					if(null==((Map)(list.get(i))).get(realKey[j])){
						continue;
					}
					basetemp=(BaseArea) SetValueUtil.setAttrributeValue(basetemp, key[j], ((Map)(list.get(i))).get(realKey[j]));
				}
				areaList.add(basetemp);
			}
		}
		return areaList;
	}
	
	public void updateOrder(String[] arrays,String userId) {
		int j = 1;
		for (String ary : arrays) {
			BaseArea baseArea = get(ary);
			baseArea.setSeqNo(j++);
			save(baseArea);
		}
		init();
	}
	
	@Override
	public BaseArea get(Serializable id) {
		if (CacheBaseAreaManager.getNodeById(id.toString())!=null) {
				return CacheBaseAreaManager.getNodeById(id.toString());
		}else{
			if(this.getAreaByAreaId(id.toString(),null)!=null){
				CacheBaseAreaManager.setAreaIdKeyMap(id.toString(),getAreaByAreaId(id.toString(),null));
				return CacheBaseAreaManager.getNodeById(id.toString());
			}
		}

		return null;
	}
	
	/**
	 * 从数据库直接取
	 * author  90  
	 * on 2016-3-28
	 * @param id
	 * @return
	 */
	public BaseArea getById(Serializable id) {
        String hql=" from BaseArea a where  a.id=:id";
		Session session=this.getSession();
		Query q=session.createQuery(hql);// where s.areaId=:areaId
		q.setParameter("id",id);
		List list=q.list();
		if(list!=null&&list.size()>0){
			return (BaseArea) list.get(0);
		}else{
			return null;
		}
		
	}
	public static void getTreeJson(BaseAreaTree a,List<Map<String,Object>> dList){
		Map<String,Object> m=new LinkedHashMap<String,Object>();
		Map<String,Object> currentArea=new LinkedHashMap<String,Object>();
		currentArea.put("aid", a.getId());
		currentArea.put("areaType", a.getAreaType());
		currentArea.put("nationalCode", a.getNationalCode());
		currentArea.put("nodeCode", a.getNodeCode());
		currentArea.put("parentCode", a.getParentCode());
		currentArea.put("nodeLevel", a.getNodeLevel());
		currentArea.put("nodeName", a.getNodeName());
		currentArea.put("nodeType", a.getNodeType());
		currentArea.put("seqNo", a.getSeqNo());
		dList.add(currentArea);
		if(a.getSubAreaTree()!=null&&a.getSubAreaTree().size()>0){
			Map<String, Map<String, BaseAreaTree>> subAreaTree=a.getSubAreaTree();
			List<List<Map<String,Object>>> subList=new ArrayList<List<Map<String,Object>>>();
			
	        Set<String> key = subAreaTree.keySet();
	        for (Iterator it = key.iterator(); it.hasNext();) {
	            String s = (String) it.next();
	            Map<String, BaseAreaTree> ssmap=subAreaTree.get(s);
	            Set<String> skey = ssmap.keySet();
	            for (Iterator sit = skey.iterator(); sit.hasNext();) {
	                String ss = (String) sit.next();
	                BaseAreaTree at=ssmap.get(ss);
	                getTreeJson(at,dList);
	            }
	        }
			m.put("subAreaTree",subList);
		}
	}
	
	
	public List<BaseArea> getMapListMapAreaCode(String mapAreaCode){
		String hql="from BaseArea t where t.nodeType=1 and t.deleteFlag=0 ";
		
		if(mapAreaCode!=null){
			hql=hql+" and t.mapParentCode='"+mapAreaCode+"'";
		}
		List<BaseArea> list=find(hql);
		return list;
	}
	
	
	
	public List<BaseArea> getNoManagerArea(){
		String hql="select t from BaseArea t where t.id not in (select p.areaId from BaseUser p where p.deleteFlag= 0 and p.defaultRoleCode='role.areaManager') and t.deleteFlag=0";
		List<BaseArea> list=find(hql);
		return list;
	}
	
	public List<BaseArea> getAllList(){
		String hql="select t from BaseArea t where t.deleteFlag=0 order by t.seqNo,t.areaCode";
		List<BaseArea> list=find(hql);
		return list;
	}
	
	public String getLogJson(String areaCode){
		BaseArea baseArea=CacheBaseAreaManager.getNodeByCode(areaCode);
		Map<String,String> m=new HashMap<String,String>();
		Integer lv=baseArea.getAreaCode().length()/3;
		for(int i=0;i<3;i++){
			if(i<lv){
				BaseArea n=CacheBaseAreaManager.getNodeByCode(baseArea.getAreaCode().substring(0, (i+1)*3));
				if(i==0){
					m.put("provinceId", n.getId());
				}
				if(i==1){
					m.put("cityId", n.getId());
				}
				if(i==2){
					m.put("countyId", n.getId());
				}
			}else{
				if(i==0){
					m.put("provinceId", "");
				}
				if(i==1){
					m.put("cityId","");
				}
				if(i==2){
					m.put("countyId","");
				}
			}
		}
		
		m.put("areaName",baseArea.getAreaName());
		m.put("seqNo",baseArea.getSeqNo()==null?"0":baseArea.getSeqNo()+"");
		m.put("nodeType",baseArea.getNodeType()==null?"":baseArea.getNodeType()+"");
		m.put("nationalCode",baseArea.getNodeType()==null?"":baseArea.getNationalCode()+"");
		JSONObject jsonObject = JSONObject.fromObject(m);  
		return jsonObject.toString();
	}
	/**
	 * 省市级联查询
	 * @author    hmc    2015年4月13日  上午10:50:15
	 * @param areaId
	 * @return
	 */
	public List getArea(String areaId) {
		
		String code;
		if(null==areaId||"".equals(areaId)||"null".equals(areaId)){
			code="-1";
		}else{
			code=areaId;
		}
		Map m=new HashMap();
		m.put("PARENT_CODE", code);
		List list=this.getNamedQuery("misBase::getArea::getArea",m);
		return list;
		
	}

	/**
	 * 通过地区code查询地区的ID号 
	 * @author    hmc    2015年4月13日  下午2:45:25
	 * @param code
	 * @return
	 */
	public String getAreaCodeByAreaId(String code) {
		// TODO Auto-generated method stub
		if(null==code||"".equals(code)){
			return null;
		}else{
			Map<String,Object> m=new HashMap<String,Object>();
			m.put("AREA_CODE", code);
			List list=this.getNamedQuery("misBase::getAreaCodeByAreaId::Query",m);
			return (String) ((Map)(list.get(0))).get("AREA_ID")+","+(String) ((Map)(list.get(0))).get("AREA_NAME");
		}
	}
	/**
	 * 通过地区id查询地区信息
	 * @author    hmc    2015年4月17日  下午3:13:12
	 * @param locationPro
	 * @return
	 */
	public BaseArea getAreaByAreaId(String areaId,String areaCode) {
		Map<String,Object> m=new HashMap<String, Object>();
		if(null!=areaId){
			m.put("areaId",areaId);
		}
		if(null!=areaCode){
			m.put("area_code", areaCode);
		}
		
		//select * from base_area
		//from BaseArea where 1=1 /~and 
		List list=this.getNamedQuery("misBase:getAreaByAreaId:query", m);
		BaseArea basetemp=new BaseArea();
		if(list!=null&&list.size()>0){
			Map m1=(Map) list.get(0);
			
			String realKey[]=SetValueUtil.getKey(m1, true);//获得map中的key
			String key[]=SetValueUtil.getKey(m1,false);//获得进行自动封装的key
			for(int i=0;i<key.length;i++){
				if(null==m1.get(realKey[i])){
					continue;
				}
				basetemp=(BaseArea) SetValueUtil.setAttrributeValue(basetemp, key[i], m1.get(realKey[i]));
			}
			basetemp.setId(m1.get("AREA_ID")+"");
			
		}
		
		return basetemp;
	}
	
	/**
	 * 获取下级地区
	 * author  90  
	 * on 2015-4-28
	 * @param mapAreaCode
	 * @return
	 */
	public List getAllAreaByParentCode(String code){
		String hql="select id from BaseArea t where  t.deleteFlag=0   ";
		
		if(code!=null){
			hql=hql+"  and ( t.parentCode like '"+code+"%'  or t.areaCode='"+code+"')";
		}
		List list=find(hql);
		return list;
	}
	/**
	 * 查询编号下所有的地区
	 * */
	public List<Map<String, Object>> getListByParentCode(String areaCode) {
		Map<String,Object> m=new HashMap<String, Object>();
		if(isNotEmpty(areaCode)){
			m.put("areaCode", areaCode);
		}
		//List<Map<String, Object>> list=getNamedQuery("misBase:getListByParentCode:query", m);//查询所有字段
		List<Map<String, Object>> list=getNamedQuery("misBase:getListByParentCodeV2:query", m);//查询ID,name,code
		return list;
	}
	/**
	 * 查询编号下所有的地区
	 * */
	public List<Map<String, Object>> getListByParentCodeNewLimit(String areaCode,String areaType) {
		Map<String,Object> m=new HashMap<String, Object>();
		String newAreaLimit="";
		String table="";
		if(areaType.equals("14")){
			table=" BASE_AREA t ";
			newAreaLimit="  and t.area_code like '062%' and t.node_level='2' ";
		}else if(areaType.equals("10")){
			table=" BASE_AREA t  ";
			newAreaLimit="   and t.area_code in (select distinct a.parent_code from BASE_AREA a where 1=1 and a.delete_flag='0' and a.area_code like '062%' and a.is_zcq='1') ";
		}else if(areaType.equals("20")){
			table=" BASE_AREA t ";
			newAreaLimit="   and t.area_code in (select distinct a.parent_code from BASE_AREA a where 1=1 and a.delete_flag='0' and a.area_code like '062%' and a.is_chx='1') ";
		}else if(areaType.equals("58")){
			table=" BASE_AREA t ";
			newAreaLimit="   and t.area_code in (select distinct a.parent_code from BASE_AREA a where 1=1 and a.delete_flag='0' and a.area_code like '062%' and a.IS_PKX='1') ";
		}else if(areaType.equals("all")){
			table=" BASE_AREA t ";
			newAreaLimit="  and t.area_code like '062%' and t.node_level='2' ";
		}else{
		}
		m.put("newAreaLimit", newAreaLimit);
		m.put("table", table);
		List<Map<String, Object>> list=getNamedQuery("misBase:getListByParentCodeNewLimit:query", m);//查询ID,name,code
		return list;
	}
	
	/**
	 * 查询编号下所有的地区
	 * */
	public List<Map<String, Object>> getListByParentCodeV2(String areaCode,BaseArea baseArea) {
		Map<String,Object> m=new HashMap<String, Object>();
		StringBuffer buffer=new StringBuffer();
		if(isNotEmpty(areaCode)){
			m.put("areaCode", areaCode);
		}
		if(StringUtils.isNotBlank(buffer.toString())){
			m.put("tiaojian", buffer.toString());
		}
		//List<Map<String, Object>> list=getNamedQuery("misBase:getListByParentCode:query", m);//查询所有字段
		List<Map<String, Object>> list=getNamedQuery("misBase:getListByParentCodeV2:query", m);//查询ID,name,code
		return list;
	}
	/**查县级**/
	public List<BaseArea> getAreaListOfCountry(BaseArea baseArea){
		String hql="from BaseArea t where t.deleteFlag=0 ";
		//hql=hql+" and  t.nodeType='1' ";
		List<BaseArea> list=find(hql);
		return list;
	}
	/**
	 * 查询地区名字是areaName的有几个
	 * @author    hmc    2015年6月12日  下午12:52:43
	 * @param areaName
	 * @return
	 */
	public Integer getAreaByAreaName(String areaName) {
		Map<String,Object> m=new HashMap<String, Object>();
		m.put("areaName_s", areaName);
		List l=getNamedQuery("misBase:getAreaByAreaName:query",m);
		if(l!=null && l.size()>0){
			String stemp=((Map)(getNamedQuery("misBase:getAreaByAreaName:query",m).get(0))).get("COUNT(*)")+"";
			return Integer.parseInt(stemp);
		}
		return 0;
	}
	/**
	 * 
     *类的描述:用于首页
     *功能描述 ：查询省市下县的数量
     *作者:hyl
     *创建日期:2015-06-24
     *修改人：
     *修改日期：
     *修改原因描述;
	 */
	public Integer getAreaCountyCountsByCode(String areacode){
		Map<String,Object> map=new HashMap<String, Object>();
		if(StringUtils.isNotBlank(areacode)){
			map.put("areaCode1", areacode);//areaCode1与xsql语句中中括号和大括号内的字段相对应
		}
		List<Map<String, Object>> list=getNamedQuery("misBase:getAllCountyByCode:query", map);
		if(list!=null &&list.size()>0){
			return Integer.parseInt(((Map)list.get(0)).get("COUNT(*)").toString());
		}
		return 0;
	}
	/**
	 * 
     *类的描述:用于首页<br>
     *功能描述 ：查询省市下县的计划数量<br>
     *作者:hyl<br>
     *创建日期:2015-06-25<br>
     *修改人：<br>
     *修改日期：<br>
     *修改原因描述:<br>
     *参数说明:col要查询的列<br>
     *obj 待上报 obj1 待审批 obj2 已上报
	 */
	public MMap getPlanStatusCounts(String col,String areacode){
		Session session = this.getSession();
		//通过sql语句进行查询
		String sql = "select t."+col+",count(*) from base_area t where t.area_code like '"+areacode+"%' and t.NODE_TYPE='0' and " +
				" t.DELETE_FLAG='0' group by "+col;
		MMap mMap=new MMap();
		try {
			SQLQuery q = session.createSQLQuery(sql);
			List<Object[]> list = q.list();
			if(list!=null&&list.size()>0){
				//-1审核未通过，0未上报，1已上报，2审核通过
				//待上报：-1，0，为空
				//待审批：1
				//已审批：2
				int dsb=0;//待上报
				int dsp=0;//待审批
				int ysp=0;//已上报
				for (Object[] obj : list) {
					Object[] objs = (Object[])obj;
					if(objs[0]==null||objs[0].toString().equals("")||objs[0].toString().equals("-1")||objs[0].toString().equals("0")){
						dsb=dsb+Integer.parseInt(objs[1].toString());
					}
					if(objs[0]!=null && objs[0].toString().equals("1")){
						dsp=dsp+Integer.parseInt(objs[1].toString());
					}
					if(objs[0]!=null && objs[0].toString().equals("2")){
						ysp=ysp+Integer.parseInt(objs[1].toString());
					}
				}
				mMap.setObj(dsb);
				mMap.setObj1(dsp);
				mMap.setObj2(ysp);
			}else {
				mMap.setObj("0");
				mMap.setObj1("0");
				mMap.setObj2("0");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			//session.close();
			//这里不用关闭，这里交给spring来管理session
		}
		return mMap;
	}
	/**
	 * 获取下级地区所属区县数
	 * */
	public List<Map> getAreaCountyCountsByCodeGroupBy(String areaCode) {
		Map<String,Object> map=new HashMap<String, Object>();
		if(StringUtils.isNotBlank(areaCode)){
			map.put("areaCode", areaCode);
		}
		List<Map> list=getNamedQuery("misBase:getAreaCountyCountsByCodeGroupBy:query", map);
		return list;
	}
	/**
	 * 获取下级地区所属区县数,有贫困县的时候重新计算
	 * */
	public List<Map> getAreaCountyCountsByCodeGroupByV2(String areaCode,String areaType) {
		Map<String,Object> map=new HashMap<String, Object>();
		if(StringUtils.isNotBlank(areaCode)){
			map.put("areaCode", areaCode);
		}
		if(StringUtils.isNotBlank(areaType)){
			if("58".equals(areaType)){
				map.put("tiaojian", "and a.IS_PKX=1 ");
			}
			if("20".equals(areaType)){
				map.put("tiaojian", "and a.IS_CHX=1 ");
			}
			if("10".equals(areaType)){
				map.put("tiaojian", "and a.IS_ZCQ=1 ");
			}
		}
		List<Map> list=getNamedQuery("misBase:getAreaCountyCountsByCodeGroupBy:query", map);
		return list;
	}
	public List<Map> getAreaCountyCountsByCodeShangBaoGroupBy(String areaCode,String year,String shangbao) {
		Map<String,Object> map=new HashMap<String, Object>();
		if(StringUtils.isNotBlank(areaCode)){
			map.put("areaCode", areaCode);
		}
		if(StringUtils.isNotBlank(year)){
			String sy=year.substring(5, year.length());
			map.put("shangbao"+sy, shangbao);
		}
		List<Map> list=getNamedQuery("misBase:getAreaCountyCountsByCodeShangBaoGroupBy:query", map);
		return list;
	}

	
	/**
	 * 查询编号下所有的地区
	 * */
	public List<Map<String, Object>> getAllByParentCode(String areaCode) {
		Map<String,Object> m=new HashMap<String, Object>();
		if(isNotEmpty(areaCode)){
			m.put("areaCodep", areaCode+"%");
			m.put("areaCode", areaCode);
		}
		List<Map<String, Object>> list=getNamedQuery("misBase:getAllByParentCode:query", m);//查询ID,name,code
		return list;
	}
	/**
	 * 查询编号下所有的地区
	 * select * from BASE_AREA t 
	 *	where t.delete_flag=0 /~ and t.area_code like {areaCodep}~/ /~ and t.area_code <>{areaCode}~/   /~ [tiaojian] ~/ order by t.seq_no,t.area_code
	 * */
	public List<Map<String, Object>> getAllByParentCodeV3(String areaCode) {
		Map<String,Object> m=new HashMap<String, Object>();
		if(isNotEmpty(areaCode)){
			m.put("areaCodep", areaCode+"%");
			m.put("areaCode", areaCode);
		}
		List<Map<String, Object>> list=getNamedQuery("misBase:getAllByParentCodeV2:query", m);//查询ID,name,code
		return list;
	}
	/**
	 * 查询编号下所有的地区V2方法
	 * */
	public List<Map<String, Object>> getAllByParentCodeV2(String areaCode,BaseArea baseArea) {
		Map<String,Object> m=new HashMap<String, Object>();
		if(isNotEmpty(areaCode)){
			m.put("areaCodep", areaCode+"%");
			m.put("areaCode", areaCode);
		}
		StringBuffer buffer=new StringBuffer();
		
		if(StringUtils.isNotBlank(buffer.toString())){
			m.put("tiaojian", buffer.toString());
		}
		List<Map<String, Object>> list=getNamedQuery("misBase:getAllByParentCode:query", m);//查询ID,name,code
		return list;
	}
	public List<BaseArea> getAreaListByAreaCode(String areaCode){
		String hql="from BaseArea t where t.deleteFlag=0 ";
		
		if(areaCode!=null){
			hql=hql+" and ( t.parentCode like '"+areaCode+"%'  or t.areaCode='"+areaCode+"')";
		}
		List<BaseArea> list=find(hql);
		return list;
	}
	
	/**
	 * gdw
	 * @param areaCode
	 * @param areaType
	 * @return
	 * 原sql
	 * select distinct t.* from /~[table]~/ where 1=1 /~[newAreaLimit]~/ order by t.area_code
	 */
	public List<Map<String, Object>> getAllByParentCodeNewLimit(String areaCode, String areaType) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();

	/*	Map<String,Object> m=new HashMap<String, Object>();
		String newAreaLimit="";
		String table="";
		if(areaType.equals("14")){
			table=" BASE_AREA t , ( select a.* from base_area a where 1=1 and a.delete_flag='0' and a.node_level='2' and a.parent_code='062'  ) p ";
			newAreaLimit="   and (t.area_code=p.parent_code or t.area_code=p.area_code)  and t.area_code!='062'  ";
		}else if(areaType.equals("10")){
			table=" BASE_AREA t , ( select a.* from base_area a where 1=1 and a.delete_flag='0' and a.is_zcq = '1' ) p ";
			newAreaLimit="   and (t.area_code=p.parent_code or t.area_code=p.area_code) and t.parent_code like '062%' ";
		}else if(areaType.equals("20")){
			table=" BASE_AREA t , ( select a.* from base_area a where 1=1 and a.delete_flag='0' and a.IS_CHX = '1' ) p ";
			newAreaLimit="   and (t.area_code=p.parent_code or t.area_code=p.area_code) and t.parent_code like '062%' ";
		}else if(areaType.equals("58")){
			table=" BASE_AREA t , ( select a.* from base_area a where 1=1 and a.delete_flag='0' and a.IS_PKX = '1' ) p ";
			newAreaLimit="   and (t.area_code=p.parent_code or t.area_code=p.area_code) and t.parent_code like '062%' ";
		}else if(areaType.equals("all")){
			table=" BASE_AREA t ";
			newAreaLimit=" and t.area_code !='"+areaCode+"' and t.area_code like '"+areaCode+"%' ";
		}else{
		}
		m.put("newAreaLimit", newAreaLimit);
		m.put("table", table);
		
		List<Map<String, Object>> list=getNamedQuery("misBase:getAllByParentCodeNewLimit:query", m);//查询ID,name,code
		*/
		Map<String,List>map =new LinkedHashMap<String,List>();
		Map<String,Integer> mapSize=new HashMap<String,Integer>();
		List<BaseAreaTree>  tree=CacheBaseAreaManager.getTreeByParentCode(areaCode);
		List dataList=new ArrayList();
		
		if(tree!=null&&tree.size()>0){
			if(areaType.equals("all")){
				
				for (int i = 0; i <tree.size(); i++) {//市区
					//县
					Map<String,Object> thisMap=new HashMap<String,Object>();
					thisMap.put("AREA_ID", tree.get(i).getId());
					thisMap.put("AREA_CODE", tree.get(i).getNodeCode());
					thisMap.put("PARENT_CODE", tree.get(i).getParentCode());
					thisMap.put("AREA_NAME", tree.get(i).getNodeName());
					thisMap.put("AREA_DESC_NAME", tree.get(i).getNodeDescName());
					list.add(thisMap);
					List<BaseAreaTree>  xiantree=CacheBaseAreaManager.getTreeByParentCode(tree.get(i).getNodeCode());
					
					for (int j = 0; j < xiantree.size(); j++) {//市区
						thisMap=new HashMap<String,Object>();
						thisMap.put("AREA_ID", xiantree.get(j).getId());
						thisMap.put("AREA_CODE", xiantree.get(j).getNodeCode());
						thisMap.put("PARENT_CODE", xiantree.get(j).getParentCode());
						thisMap.put("AREA_NAME", xiantree.get(j).getNodeName());
						thisMap.put("AREA_DESC_NAME", xiantree.get(j).getNodeDescName());
						list.add(thisMap);
					}
					
				}
			}else if(areaType.equals("10")){
				for (int i = 0; i <tree.size(); i++) {//市区
					//县
					Map<String,Object> thisMap=new HashMap<String,Object>();
					thisMap.put("AREA_ID", tree.get(i).getId());
					thisMap.put("AREA_CODE", tree.get(i).getNodeCode());
					thisMap.put("PARENT_CODE", tree.get(i).getParentCode());
					thisMap.put("AREA_NAME", tree.get(i).getNodeName());
					thisMap.put("AREA_DESC_NAME", tree.get(i).getNodeDescName());
					list.add(thisMap);
					List<BaseAreaTree>  xiantree=CacheBaseAreaManager.getTreeByParentCode(tree.get(i).getNodeCode());
					//List dataList=new ArrayList();
					for (int j = 0; j < xiantree.size(); j++) {//市区
						BaseAreaTree xianTreeNode=xiantree.get(j);
						if(xianTreeNode.getIsZcq()!=null&&xianTreeNode.getIsZcq()==1){
							thisMap=new HashMap<String,Object>();
							thisMap.put("AREA_ID", xiantree.get(j).getId());
							thisMap.put("AREA_CODE", xiantree.get(j).getNodeCode());
							thisMap.put("PARENT_CODE", xiantree.get(j).getParentCode());
							thisMap.put("AREA_NAME", xiantree.get(j).getNodeName());
							thisMap.put("AREA_DESC_NAME", xiantree.get(j).getNodeDescName());
							list.add(thisMap);
						}
						
					}
					
				}
			}else if(areaType.equals("58")){
				for (int i = 0; i <tree.size(); i++) {//市区
					//县
					Map<String,Object> thisMap=new HashMap<String,Object>();
					thisMap.put("AREA_ID", tree.get(i).getId());
					thisMap.put("AREA_CODE", tree.get(i).getNodeCode());
					thisMap.put("PARENT_CODE", tree.get(i).getParentCode());
					thisMap.put("AREA_NAME", tree.get(i).getNodeName());
					thisMap.put("AREA_DESC_NAME", tree.get(i).getNodeDescName());
					list.add(thisMap);
					List<BaseAreaTree>  xiantree=CacheBaseAreaManager.getTreeByParentCode(tree.get(i).getNodeCode());
					//List dataList=new ArrayList();
					for (int j = 0; j < xiantree.size(); j++) {//市区
						BaseAreaTree xianTreeNode=xiantree.get(j);
						if(xianTreeNode.getIsPkx()!=null&&xianTreeNode.getIsPkx()==1){
							thisMap=new HashMap<String,Object>();
							thisMap.put("AREA_ID", xiantree.get(j).getId());
							thisMap.put("AREA_CODE", xiantree.get(j).getNodeCode());
							thisMap.put("PARENT_CODE", xiantree.get(j).getParentCode());
							thisMap.put("AREA_NAME", xiantree.get(j).getNodeName());
							thisMap.put("AREA_DESC_NAME", xiantree.get(j).getNodeDescName());
							list.add(thisMap);
						}
						
					}
					
				}
			}else if(areaType.equals("20")){
				for (int i = 0; i <tree.size(); i++) {//市区
					//县
					Map<String,Object> thisMap=new HashMap<String,Object>();
					thisMap.put("AREA_ID", tree.get(i).getId());
					thisMap.put("AREA_CODE", tree.get(i).getNodeCode());
					thisMap.put("PARENT_CODE", tree.get(i).getParentCode());
					thisMap.put("AREA_NAME", tree.get(i).getNodeName());
					thisMap.put("AREA_DESC_NAME", tree.get(i).getNodeDescName());
					list.add(thisMap);
					List<BaseAreaTree>  xiantree=CacheBaseAreaManager.getTreeByParentCode(tree.get(i).getNodeCode());
					//List dataList=new ArrayList();
					for (int j = 0; j < xiantree.size(); j++) {//市区
						BaseAreaTree xianTreeNode=xiantree.get(j);
						if(xianTreeNode.getIsChx()!=null&&xianTreeNode.getIsChx()==1){
							thisMap=new HashMap<String,Object>();
							thisMap.put("AREA_ID", xiantree.get(j).getId());
							thisMap.put("AREA_CODE", xiantree.get(j).getNodeCode());
							thisMap.put("PARENT_CODE", xiantree.get(j).getParentCode());
							thisMap.put("AREA_NAME", xiantree.get(j).getNodeName());
							thisMap.put("AREA_DESC_NAME", xiantree.get(j).getNodeDescName());
							list.add(thisMap);
						}
						
					}
					
				}
			}else if(areaType.equals("14")){
				for (int i = 0; i <tree.size(); i++) {//市区
					//县
					Map<String,Object> thisMap=new HashMap<String,Object>();
					thisMap.put("AREA_ID", tree.get(i).getId());
					thisMap.put("AREA_CODE", tree.get(i).getNodeCode());
					thisMap.put("PARENT_CODE", tree.get(i).getParentCode());
					thisMap.put("AREA_NAME", tree.get(i).getNodeName());
					thisMap.put("AREA_DESC_NAME", tree.get(i).getNodeDescName());
					list.add(thisMap);
				}
			}
		}
		return list;
	}
	/**
	 * 根据地区code得到数据
	 * */
	public List<Map<String, Object>> getAllByParentCodeNewLimitV2(String areaCode) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		if(areaCode.length()==3){
			List<BaseAreaTree>  tree=CacheBaseAreaManager.getTreeByParentCode(areaCode);
			for (int i = 0; i <tree.size(); i++) {//市区
				Map<String,Object> thisMap=new HashMap<String,Object>();
				thisMap.put("AREA_ID", tree.get(i).getId());
				thisMap.put("AREA_CODE", tree.get(i).getNodeCode());
				thisMap.put("PARENT_CODE", tree.get(i).getParentCode());
				thisMap.put("AREA_NAME", tree.get(i).getNodeName());
				thisMap.put("AREA_DESC_NAME", tree.get(i).getNodeDescName());
				list.add(thisMap);
				List<BaseAreaTree>  xiantree=CacheBaseAreaManager.getTreeByParentCode(tree.get(i).getNodeCode());
				for (int j = 0; j < xiantree.size(); j++) {//县
					thisMap=new HashMap<String,Object>();
					thisMap.put("AREA_ID", xiantree.get(j).getId());
					thisMap.put("AREA_CODE", xiantree.get(j).getNodeCode());
					thisMap.put("PARENT_CODE", xiantree.get(j).getParentCode());
					thisMap.put("AREA_NAME", xiantree.get(j).getNodeName());
					thisMap.put("AREA_DESC_NAME", xiantree.get(j).getNodeDescName());
					list.add(thisMap);
				}
				
			}
		}
		if(areaCode.length()==6){
			//市
			BaseArea bat=CacheBaseAreaManager.getNodeByCode(areaCode);
			Map<String,Object> thisMap=new HashMap<String,Object>();
			thisMap.put("AREA_ID",bat.getId());
			thisMap.put("AREA_CODE", bat.getAreaCode());
			thisMap.put("PARENT_CODE", bat.getParentCode());
			thisMap.put("AREA_NAME", bat.getAreaName());
			thisMap.put("AREA_DESC_NAME", bat.getAreaDesc());
			list.add(thisMap);
			List<BaseAreaTree>  xiantree=CacheBaseAreaManager.getTreeByParentCode(areaCode);
			for (int j = 0; j < xiantree.size(); j++) {//县
				thisMap=new HashMap<String,Object>();
				thisMap.put("AREA_ID", xiantree.get(j).getId());
				thisMap.put("AREA_CODE", xiantree.get(j).getNodeCode());
				thisMap.put("PARENT_CODE", xiantree.get(j).getParentCode());
				thisMap.put("AREA_NAME", xiantree.get(j).getNodeName());
				thisMap.put("AREA_DESC_NAME", xiantree.get(j).getNodeDescName());
				list.add(thisMap);
			}
		}
		if(areaCode.length()==9){
			//县
			BaseArea bat=CacheBaseAreaManager.getNodeByCode(areaCode);
			Map<String,Object> thisMap=new HashMap<String,Object>();
			thisMap.put("AREA_ID",bat.getId());
			thisMap.put("AREA_CODE", bat.getAreaCode());
			thisMap.put("PARENT_CODE", bat.getParentCode());
			thisMap.put("AREA_NAME", bat.getAreaName());
			thisMap.put("AREA_DESC_NAME", bat.getAreaDesc());
			list.add(thisMap);
		}
		return list;
	}
	/**
	 * gdw
	 * @param areaCode
	 * @param areaType
	 * select a.parent_code,count(*) 
	 * from BASE_AREA a 
	 * where a.parent_code in (select t.area_code from BASE_AREA t where 1=1/~ and t.parent_code={areaCode}~/) /~[newAreaLimit]~/ 
	 * group by a.parent_code
	 * @return
	 */
	public List<Map<String, Object>> getAreaCountyCountsByCodeGroupByNewLimit(String areaCode, String areaType) {
		Map<String,Object> m=new HashMap<String, Object>();
		String newAreaLimit="";
		String table="";
		if(areaType.equals("14")){
			table=" BASE_AREA t , ( select a.* from base_area a where 1=1 and a.delete_flag='0' and a.node_level='2' and a.parent_code='062' ) p ";
			newAreaLimit="   and (t.area_code=p.parent_code or t.area_code=p.area_code)  and t.area_code!='062'  ";
		}else if(areaType.equals("10")){
			table=" BASE_AREA t , ( select a.* from base_area a where 1=1 and a.delete_flag='0' and a.is_zcq = '1' ) p ";
			newAreaLimit="   and (t.area_code=p.parent_code or t.area_code=p.area_code) and t.parent_code like '062%' ";
		}else if(areaType.equals("20")){
			table=" BASE_AREA t , ( select a.* from base_area a where 1=1 and a.delete_flag='0' and a.IS_CHX = '1' ) p ";
			newAreaLimit="   and (t.area_code=p.parent_code or t.area_code=p.area_code) and t.parent_code like '062%' ";
		}else if(areaType.equals("58")){
			table=" BASE_AREA t , ( select a.* from base_area a where 1=1 and a.delete_flag='0' and a.IS_PKX = '1' ) p ";
			newAreaLimit="   and (t.area_code=p.parent_code or t.area_code=p.area_code) and t.parent_code like '062%' ";
		}else if(areaType.equals("all")){
			table=" BASE_AREA t ";
			newAreaLimit=" and t.area_code !='"+areaCode+"' and t.area_code like '"+areaCode+"%' ";
		}else{
		}
		m.put("newAreaLimit", newAreaLimit);
		m.put("table", table);
		List<Map<String, Object>> list=getNamedQuery("misBase:getAreaCountyCountsByCodeGroupByNewLimit:query", m);//查询ID,name,code
		return list;
	}
	/**
	 * 
     *类的描述:getBaseAreaMapList<br/>
     *功能描述 ：得到地区List<Map<String, Object>><br/>
     *作者:hyl<br/>
     *创建日期:2016-06-12<br/>
     *修改人：<br/>
     *修改日期：<br/>
     *修改原因描述:<br/>
	 * @throws ParseException 
	 */
	public List<Map<String, Object>> getBaseAreaMapList(BaseArea baseArea,String tablenames,String otherQuery) {
		Map<String,Object> map=new HashMap<String, Object>();
		if(StringUtils.isBlank(tablenames)){
			map.put("tablenames"," base_area  t ");
		}else {
			map.put("tablenames",tablenames +" t ");
		}
		if (StringUtils.isNotBlank(baseArea.getAreaCode())) {
			map.put("areaCode", baseArea.getAreaCode());
		}
		if (StringUtils.isNotBlank(baseArea.getId())) {
			map.put("areaId", baseArea.getId());
		}
		if (StringUtils.isNotBlank(baseArea.getParentCode())) {
			map.put("parentCode", baseArea.getParentCode());
		}
		map.put("chaxuntiaojian1", " t.* ");
		if(StringUtils.isNotBlank(otherQuery)){
			map.put("tiaojian", otherQuery );
		}
		return this.getNamedQuery("misBase::getBaseAreaListCommon::query", map);
	}
	/**
	 * 
     *类的描述:getAreaInfo<br/>
     *功能描述 ：传入code得到地区的省市县信息<br/>
     *作者:hyl<br/>
     *创建日期:2017-03-15<br/>
     *修改人：<br/>
     *修改日期：<br/>
     *修改原因描述:<br/>
	 * 
	 */
	public MMap getAreaInfo(String areaCode) {
		MMap mMap = new MMap();
		if(StringUtils.isNotBlank(areaCode)){
			if(areaCode.length()==3){//省
				BaseArea baseArea_sheng=CacheBaseAreaManager.getNodeByCode(areaCode);
				mMap.setObj0(baseArea_sheng.getAreaName());
				mMap.setObj1(baseArea_sheng.getId());
				mMap.setObj2(baseArea_sheng.getAreaCode());
			}
			if(areaCode.length()==6){//市
				BaseArea baseArea_shi=CacheBaseAreaManager.getNodeByCode(areaCode);
				BaseArea baseArea_sheng=CacheBaseAreaManager.getNodeByCode(baseArea_shi.getParentCode());
				mMap.setObj0(baseArea_sheng.getAreaName());
				mMap.setObj1(baseArea_sheng.getId());
				mMap.setObj2(baseArea_sheng.getAreaCode());
				
				mMap.setObj3(baseArea_shi.getAreaName());
				mMap.setObj4(baseArea_shi.getId());
				mMap.setObj5(baseArea_shi.getAreaCode());
			}
			if(areaCode.length()==9){//县
				BaseArea baseArea_xian=CacheBaseAreaManager.getNodeByCode(areaCode);
				BaseArea baseArea_shi=CacheBaseAreaManager.getNodeByCode(baseArea_xian.getParentCode());
				BaseArea baseArea_sheng=CacheBaseAreaManager.getNodeByCode(baseArea_shi.getParentCode());
				mMap.setObj0(baseArea_sheng.getAreaName());
				mMap.setObj1(baseArea_sheng.getId());
				mMap.setObj2(baseArea_sheng.getAreaCode());
				
				mMap.setObj3(baseArea_shi.getAreaName());
				mMap.setObj4(baseArea_shi.getId());
				mMap.setObj5(baseArea_shi.getAreaCode());
				
				mMap.setObj6(baseArea_xian.getAreaName());
				mMap.setObj7(baseArea_xian.getId());
				mMap.setObj8(baseArea_xian.getAreaCode());
			}
		}
		return mMap;
	}
	
	public List getUserByAreaCode(String areaCode){
		String hql="select a.areaName, b.realName,b.loginName,b.password from BaseArea a,BaseUser b where 1=1 and b.areaId=a.id and b.defaultRoleCode='role.provinceManager' and b.deleteFlag='0' and a.deleteFlag='0' ";
		
		if(areaCode!=null){
			if(areaCode.length()==3){
				hql="select a.areaName, b.realName,b.loginName,b.password from BaseArea a,BaseUser b where 1=1 and b.areaId=a.id and b.defaultRoleCode='role.cityManager' and b.deleteFlag='0' and a.deleteFlag='0' ";
			}
			if(areaCode.length()==6){
				hql="select a.areaName, b.realName,b.loginName,b.password from BaseArea a,BaseUser b where 1=1 and b.areaId=a.id and b.defaultRoleCode='role.countryManager' and b.deleteFlag='0' and a.deleteFlag='0' ";
			}
			if(areaCode.length()==9){
				hql="select a.areaName, b.realName,b.loginName,b.password from BaseArea a,BaseUser b where 1=1 and b.areaId=a.id and b.defaultRoleCode='role.schoolManager' and b.deleteFlag='0' and a.deleteFlag='0' ";
			}
			hql=hql+" and a.parentCode=:parentCode";
		}
		Session session=this.getSession();	
		Query q=session.createQuery(hql);
		if(areaCode!=null){
			q.setParameter("parentCode", areaCode);//(areaCode.substring(0, areaCode.length()-3)).equals("")?"-1":areaCode.subSequence(0, areaCode.length()-3)
		}
		List list=q.list();
		return list;
	}
}
