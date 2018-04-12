/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package org.my431.base.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import org.my431.base.model.BaseModule;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.my431.plugin.redis.services.RedisManager;
import org.my431.util.GeneralTreeNode;
import org.my431.util.MMap;
import org.springframework.stereotype.Repository;

@Repository
public class BaseModuleManager extends HibernateXsqlBuilderQueryDao<BaseModule>{
	public static String objCodePreKey="global.base.BaseModule.key.code.";
	public static String objIdPreKey="global.base.BaseModule.key.id.";
	public static List<GeneralTreeNode> tree;
	public Class getEntityClass() {
		return BaseModule.class;
	}
	private RedisManager redisManager;
	
	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}
	
	@Override
	protected void onValid(BaseModule entity) {

		if (entity.getId() == null) {
			String array[] = getCodeArray(entity.getParentCode());
			entity.setCode(array[0]);
		}
	}
	
	public void init() {
		List<BaseModule> list = this.getAll("code",true);
		tree=new ArrayList<GeneralTreeNode>();
		tree.clear();
		for (BaseModule node : list) {
			GeneralTreeNode treeNode = new GeneralTreeNode();
			treeNode.setId(node.getId());
			treeNode.setNodeCode(node.getCode());
			treeNode.setParentCode(node.getParentCode());
			treeNode.setNodeName(node.getModuleName());
			treeNode.setNodeType(node.getNodeType());
			tree.add(treeNode);
			redisManager.setOValue(objCodePreKey+node.getCode(), node);
			redisManager.setOValue(objIdPreKey+node.getId(), node);
		}
	}
	
	public void reload(){
		List<BaseModule> list = this.getAll("code",true);
		tree=new ArrayList<GeneralTreeNode>();
		tree.clear();
		for (BaseModule node : list) {
			GeneralTreeNode treeNode = new GeneralTreeNode();
			treeNode.setId(node.getId());
			treeNode.setNodeCode(node.getCode());
			treeNode.setParentCode(node.getParentCode());
			treeNode.setNodeName(node.getModuleName());
			treeNode.setNodeType(node.getNodeType());
			tree.add(treeNode);
		}
	}
	
	@Override
	public void save(Object o){
		super.save(o);
		redisManager.setOValue(objCodePreKey+((BaseModule)o).getCode(), o);
		redisManager.setOValue(objIdPreKey+((BaseModule)o).getId(), o);
		reload();
	}
	
	@Override
	public void update(Object o){
		super.update(o);
		redisManager.setOValue(objCodePreKey+((BaseModule)o).getCode(), o);
		redisManager.setOValue(objIdPreKey+((BaseModule)o).getId(), o);
		reload();
	}
	
	@Override
	public void remove(Object o){
		super.remove(o);
		if(redisManager.objectHasKey(objCodePreKey+((BaseModule)o).getCode())){
			redisManager.removeOValue(objCodePreKey+((BaseModule)o).getCode());
		}
		reload();
	}
	
	@Override
	public void removeById(Serializable id){
		super.removeById(id);
		if(redisManager.objectHasKey(objIdPreKey+id)){
			redisManager.removeOValue(objIdPreKey+id);
		}
		reload();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page findPage(BaseModule query, int pageSize, int pageNo) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from BaseModule t where 1=1 "
			  	+ "/~ and t.code = {code} ~/"
			  	+ "/~ and t.parentCode = {parentCode} ~/"
			  	+ "/~ and t.moduleName = {moduleName} ~/"
			  	+ "/~ and t.moduleDesc = {moduleDesc} ~/"
			  	+ "/~ and t.nodeType = {nodeType} ~/"
			  	+ "/~ and t.creUser = {creUser} ~/"
				+ "/~ and t.creTime >= {creTimeBegin} ~/"
				+ "/~ and t.creTime <= {creTimeEnd} ~/"
			  	+ "/~ and t.modUser = {modUser} ~/"
				+ "/~ and t.modTime >= {modTimeBegin} ~/"
				+ "/~ and t.modTime <= {modTimeEnd} ~/"
				+ "/~ order by [sortColumns] ~/";

		Map filters = new HashMap(); 
		XsqlBuilder xsqlBuilder=new XsqlBuilder();
		if(isNotEmpty(query.getCode())) {
			filters.put("code", query.getCode());
		}
		if(isNotEmpty(query.getParentCode())) {
			filters.put("parentCode", query.getParentCode());
		}
        filters.put("sortColumns", "t.code ");
        XsqlFilterResult result = xsqlBuilder.generateHql(sql,filters); 
		return pageQuery(result.getXsql(), pageNo, pageSize,result.getAcceptedFilters());
	}
	
	/**
	 * redis获取对象
	 * @param id
	 * @return
	 */
	public BaseModule getBaseModuleCode(String queryParentCode) {
		if(queryParentCode!=null&&!queryParentCode.trim().equals("")&&!queryParentCode.equals("-1")){
			if(redisManager.objectHasKey(objCodePreKey+queryParentCode)){
				return (BaseModule)redisManager.getOValue(objCodePreKey+queryParentCode);
			}
			return null;
		}
		return null;
	}
	
	/**
	 * redis获取对象
	 * @param id
	 * @return
	 */
	public BaseModule getBaseModuleId(String id) {
		if(id!=null&&!id.trim().equals("")&&!id.equals("-1")){
			if(redisManager.objectHasKey(objIdPreKey+id)){
				return (BaseModule)redisManager.getOValue(objIdPreKey+id);
			}
			return null;
		}
		return null;
	}
	public String[] getCodeArray(String departmentCode) {
		String[] array = { "", "" };
		String newDepartmentCode = "";
		String parentDepartmentCode = "";
		if (getMaxChildDepartmentCodeByParent("-1").equals("")) {
			newDepartmentCode = "000001";
			parentDepartmentCode = "-1";
		} else if (departmentCode.equals("-1")) {
			String maxDepartmentCode = getMaxChildDepartmentCodeByParent("-1");
			int maxDepartmentCodeInt = Integer.parseInt(maxDepartmentCode);
			maxDepartmentCodeInt++;
			String temp_str = String.valueOf(maxDepartmentCodeInt);
			if (temp_str.length() < 6) {
				int count = temp_str.length();
				while (count < 6) {
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
				newDepartmentCode = parentDepartmentCode + "000001";
			} else {
				int maxChildDepartmentCodeInt = Integer
						.parseInt(maxChildDepartmentCode.substring(
								maxChildDepartmentCode.length() - 6,
								maxChildDepartmentCode.length()));
				maxChildDepartmentCodeInt++;
				String temp_str = String.valueOf(maxChildDepartmentCodeInt);
				if (temp_str.length() < 6) {
					int count = temp_str.length();
					while (count < 6) {
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
	
	public String getMaxChildDepartmentCodeByParent(String parentCode) {
		List<GeneralTreeNode> list = getTreeByParentCode(parentCode);
		if (list != null && list.size() > 0) {
			Collections.sort(list, codeComparator);
			return list.get(list.size() - 1).getNodeCode();
		}
		return "";
	}
	
	Comparator<GeneralTreeNode> codeComparator = new Comparator<GeneralTreeNode>() {
		public int compare(GeneralTreeNode a, GeneralTreeNode b) {
			return (a.getNodeCode().compareTo(b.getNodeCode()));
		}
	};
	
	public List<GeneralTreeNode> getTreeByParentCode(String parentCode) {
		List<GeneralTreeNode> subtree = new ArrayList<GeneralTreeNode>();
		String hql="from BaseModule lg where lg.parentCode='"+parentCode+"'";
		List<BaseModule> list=find(hql);
		for( BaseModule  labBookClass:list){
			GeneralTreeNode treeNode = new GeneralTreeNode();
			treeNode.setId(labBookClass.getId());
			treeNode.setNodeCode(labBookClass.getCode());
			treeNode.setParentCode(labBookClass.getParentCode());
			treeNode.setNodeName(labBookClass.getModuleName());
			treeNode.setNodeType(labBookClass.getNodeType());
			subtree.add(treeNode);
		}
		return subtree;
	}
	/**获取已经加日志url的模块列表*/
	public List<Map<String, String>> getModuleListOfUrlLog(){
		
		return getNamedQuery("misBase::getModuleOfUrlLog::query");
	}
}
