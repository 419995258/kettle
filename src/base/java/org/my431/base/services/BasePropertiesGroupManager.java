/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2011
 */

package org.my431.base.services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.my431.base.model.BasePropertiesGroup;
import org.my431.platform.dao.extend.HibernateEntityExtendDao;
import org.my431.platform.exception.BusinessException;
import org.my431.util.MMap;
import org.springframework.stereotype.Repository;


@Repository
public class BasePropertiesGroupManager extends HibernateEntityExtendDao<BasePropertiesGroup>{

	public Class getEntityClass() {
		return BasePropertiesGroup.class;
	}
	
	@Override
	protected void onValid(BasePropertiesGroup entity) {
		if (!isUnique(entity, "groupKey")) {
			throw new BusinessException("属性组 " + entity.getGroupKey() + " 重复");
		}

		if (entity.getId() == null) {
			// 新增时将GroupCode,seqNo赋值
			//国家系统数据导入code另外赋值，不需要自动生成
			if (!"NATION".equals(entity.getItemType())) {
				String array[] = getCodeArray(entity.getParentCode());
				entity.setGroupCode(array[0]);
			}
			entity.setSeqNo(getNextSeqNo(entityClass.getName(), entity.getParentCode()));
		}
	}

	public void init() {
		List<BasePropertiesGroup> list = getPropertiesGroupList();
		CacheBasePropertiesManager.setAllPropertiesGroupList(list);
		
		//生产厂家set
		List<BasePropertiesGroup> groupList=getCorePropertiesGroupList("device.detail.sccj.");
		CacheBasePropertiesManager.setProducerGroupList(groupList);
		
	}
	
	public void reload() {
		init();
	}
	
	public List getList(String parentCode) {

		Query q = createQuery("from BasePropertiesGroup where parentCode = '" + parentCode + "'" + " order by seqNo");
		List list = q.list();

		return (list == null) ? null : list;
	}

	public List<BasePropertiesGroup> getTreeNodeList() {
		return getAll("seqNo", true);
	}

	public String getMaxChildPropertyGroupCodeByParent(String parentCode) {

		Query q = createQuery("select max(groupCode) from BasePropertiesGroup where parentCode = '" + parentCode + "'");

		List<String> list = q.list();
		return (list.get(0) == null) ? "" : list.get(0).trim();
	}

	public String getGroupNameByCode(String groupCode) {

		if (groupCode.equals("-1"))
			return "全部";
		Query q = createQuery("select groupName from BasePropertiesGroup where groupCode = '" + groupCode + "'");

		List<String> list = q.list();
		return (list.get(0) == null) ? "" : list.get(0).trim();
	}

	public String getGroupNameByGroupKey(String groupKey) {

		if (groupKey.equals("-1"))
			return "全部";
		Session session = this.getSession();
		Query q = session.createQuery("select groupName from BasePropertiesGroup where groupKey = '" + groupKey + "'");

		List<String> list = q.list();
		this.releaseSession(session);
		return (list.get(0) == null) ? "" : list.get(0).trim();
	}
	public String[] getCodeArray(String groupCode) {
		String[] array = { "", "" };
		String newPropertyGroupCode = "";
		String parentPropertyGroupCode = "";
		if (getMaxChildPropertyGroupCodeByParent("-1").equals("")) {
			newPropertyGroupCode = "001";
			parentPropertyGroupCode = "-1";
		} else if (groupCode.equals("-1")) {//
			String maxPropertyGroupCode = getMaxChildPropertyGroupCodeByParent("-1");
			int maxPropertyGroupCodeInt = Integer.parseInt(maxPropertyGroupCode);
			maxPropertyGroupCodeInt++;
			String temp_str = String.valueOf(maxPropertyGroupCodeInt);
			if (temp_str.length() < 3) {
				int count = temp_str.length();
				while (count < 3) {
					temp_str = "0" + temp_str;
					count++;
				}
				newPropertyGroupCode = temp_str;
				parentPropertyGroupCode = "-1";
			}
		} else {
			parentPropertyGroupCode = groupCode;
			String maxChildPropertyGroupCode = getMaxChildPropertyGroupCodeByParent(parentPropertyGroupCode);

			if (maxChildPropertyGroupCode.equals("")) {
				newPropertyGroupCode = parentPropertyGroupCode + "001";
			} else {
				int maxChildPropertyGroupCodeInt = Integer.parseInt(maxChildPropertyGroupCode.substring(
						maxChildPropertyGroupCode.length() - 3, maxChildPropertyGroupCode.length()));
				maxChildPropertyGroupCodeInt++;
				String temp_str = String.valueOf(maxChildPropertyGroupCodeInt);
				if (temp_str.length() < 3) {
					int count = temp_str.length();
					while (count < 3) {
						temp_str = "0" + temp_str;
						count++;
					}
					newPropertyGroupCode = parentPropertyGroupCode + temp_str;
				}
			}
		}

		array[0] = newPropertyGroupCode;
		array[1] = parentPropertyGroupCode;

		return array;
	}

	public void updateOrder(String[] arrays) {

		int j = 1;
		for (String ary : arrays) {
			BasePropertiesGroup basePropertiesGroup = get(ary);
			basePropertiesGroup.setSeqNo(j++);
			save(basePropertiesGroup);
		}
	}

	public List<BasePropertiesGroup> getCorePropertiesGroupList(String groupKey) {
		String hql = "from BasePropertiesGroup a where a.groupKey like '" + groupKey + "%' order by a.seqNo";
		return find(hql);
	}
	
	
	public BasePropertiesGroup getUniquePropertiesGroup(String groupKey) {
		String hql = "from BasePropertiesGroup a where a.groupKey='" + groupKey + "'";
		List<BasePropertiesGroup> list = find(hql);
		if (MMap.validateList(list)) {
			return list.get(0);
		}else {
			return null;
		}
	}
	
	public List<BasePropertiesGroup> getPropertiesGroupList() {
		String hql = "from BasePropertiesGroup a where a.nodeType='0' and a.groupKey like 'device.detail%' order by a.parentCode";
		return find(hql);
	}
	
	public String getCountByGroupId(String groupCode)
	{
		String flag="0";
		String hql="from BasePropertiesGroup  a where a.parentCode=:groupCode";
		Session session=this.getSession();
		Query q=session.createQuery(hql);
		q.setParameter("groupCode", groupCode);
		int cnt=q.list().size();
		if (cnt<=0) flag="1";
		return flag;
	}

}
