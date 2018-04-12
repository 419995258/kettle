/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2011
 */

package org.my431.base.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.my431.base.model.BaseProperties;
import org.my431.base.model.BasePropertiesGroup;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.exception.BusinessException;
import org.my431.plugin.redis.services.RedisManager;
import org.springframework.stereotype.Repository;

@Repository
public class BasePropertiesManager extends HibernateXsqlBuilderQueryDao<BaseProperties>{
	private static String NoUseSubjectlistkey="global.base.BaseBookVersion.NoUseSubjectlistkey";
	private static String SchoolTypeFmapkey="global.base.BaseSchoolType.fMap.";
	private RedisManager redisManager;
	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}
	public Class getEntityClass() {
		return BaseProperties.class;
	}
	public static Logger log = Logger.getLogger(BasePropertiesManager.class);

	@Override
	protected void onValid(BaseProperties entity) {
		if (!isUnique(entity, "propertyKey")) {
			throw new BusinessException("属性代码 " + entity.getPropertyKey()
					+ " 重复");
		}

		if (entity.getId() == null) {
			entity.setSeqNo(getNextSeqNo(entityClass.getName()));
		}
	}

	// key: String property_key
	// value: CoreProperties p

	/*
	 * 数据初始化执行 init
	 */
	public void init() {
		
			Map<String, List<BaseProperties>> listhash = new LinkedHashMap<String, List<BaseProperties>>();
	
			List<BaseProperties> properties = getAll("seqNo", true);
	
			for (BaseProperties cp : properties) {
				
				if(listhash.containsKey(cp.getGroupKey())){
					listhash.get(cp.getGroupKey()).add(cp);
				}else{
					List<BaseProperties> list=new ArrayList<BaseProperties>();
					list.add(cp);
					listhash.put(cp.getGroupKey(), list);
				}
				// value hash
				CacheBasePropertiesManager.setProperties(cp);
				
				if(cp.getGroupKey().equals("base.school.type")){
					if(cp.getExtra1()!=null){
						String[] types=cp.getExtra1().split("#");
						for(String type:types){
							redisManager.setOValue(SchoolTypeFmapkey+type, cp);
						}
					}
					
				}
			}
			
			CacheBasePropertiesManager.setAllPropertiesMap(listhash);
			
	}

	/*
	 * 重载所有内存数据
	 */
	public void reload() {
		Map<String, List<BaseProperties>> listhash = new LinkedHashMap<String, List<BaseProperties>>();

		List<BaseProperties> properties = getAll("seqNo", true);

		for (BaseProperties cp : properties) {
			
			if(listhash.containsKey(cp.getGroupKey())){
				listhash.get(cp.getGroupKey()).add(cp);
			}else{
				List<BaseProperties> list=new ArrayList<BaseProperties>();
				list.add(cp);
				listhash.put(cp.getGroupKey(), list);
			}
			// value hash
			CacheBasePropertiesManager.setProperties(cp);
			
			if(cp.getGroupKey().equals("base.school.type")){
				if(cp.getExtra1()!=null){
					String[] types=cp.getExtra1().split("#");
					for(String type:types){
						redisManager.setOValue(SchoolTypeFmapkey+type, cp);
					}
				}
				
			}
		}
		
		CacheBasePropertiesManager.setAllPropertiesMap(listhash);
		
	}

	//重载单个属性
	public void updateOne(BaseProperties baseProperties,String configPath) {
		if(baseProperties!=null){
			CacheBasePropertiesManager.setProperties(baseProperties);
			LinkedHashMap<String, List<BaseProperties>> ht=CacheBasePropertiesManager.getAllPropertiesMap();
			List<BaseProperties> list=ht.get(baseProperties.getGroupKey());
			BaseProperties c=new BaseProperties();
			if(list!=null){
				for(BaseProperties cp:list){
					if(cp.getPropertyKey().equals(baseProperties.getPropertyKey())){
						c=cp;
					}
					if(cp.getGroupKey().equals("base.school.type")){
						if(cp.getExtra1()!=null){
							String[] types=cp.getExtra1().split("#");
							for(String type:types){
								redisManager.setOValue(SchoolTypeFmapkey+type, cp);
							}
						}
					}
				}
				list.remove(c);
			}else{
				list=new ArrayList<BaseProperties>();
			}
			list.add(baseProperties);
			ht.put(baseProperties.getGroupKey(), list);
			
			CacheBasePropertiesManager.setAllPropertiesMap(ht);
		}
	}
	
	//添加单个属性
	public void delOne(BaseProperties baseProperties,String configPath) {
		if(baseProperties!=null){
			CacheBasePropertiesManager.removePropertiesByPropertyKey(baseProperties.getPropertyKey());
			LinkedHashMap<String, List<BaseProperties>> ht=CacheBasePropertiesManager.getAllPropertiesMap();
			List<BaseProperties> list=ht.get(baseProperties.getGroupKey());
			BaseProperties c=new BaseProperties();
			for(BaseProperties cp:list){
				if(cp.getPropertyKey().equals(baseProperties.getPropertyKey())){
					c=cp;
				}
			}
			list.remove(c);
			ht.put(baseProperties.getGroupKey(), list);
			
			CacheBasePropertiesManager.setAllPropertiesMap(ht);
		}
	}
	/*
	 * ͨ��key����CoreProperties����
	 */
	public  BaseProperties getProperty(String hashKey,String configPath) {
		return CacheBasePropertiesManager.getPropertiesByPropertyKey(hashKey);
	}

	/*
	 * ͨ��key����value
	 */
	public static String getValue(String hashKey) {
			return CacheBasePropertiesManager.getValueByPropertyKey(hashKey);
	}

	public String getPropertyLabelByKey(String propertyKey) {
		String propertyValue = getValue(propertyKey);
		return propertyValue == null ? "" : propertyValue;
	}

	public List<BaseProperties> getPropertiesList(String groupKey,String configPath) {
		
		LinkedHashMap<String, List<BaseProperties>> ht=CacheBasePropertiesManager.getAllPropertiesMap();
		return ht.get(groupKey);
	}
	
	public List<BaseProperties> toPropertiesList(String groupKey,String configPath) {
		
		LinkedHashMap<String, List<BaseProperties>> ht=CacheBasePropertiesManager.getAllPropertiesMap();
		return ht.get(groupKey);
	}


	public String SelectOptins(List<BaseProperties> options, String selected) {
		StringBuffer buffer = new StringBuffer();
		if (options.size() == 0)
			return "";
		if (selected == null)
			selected = "";
		for (BaseProperties p : options) {
			if (selected.equals(p.getPropertyKey()))
				buffer.append("\n<option value='" + p.getPropertyKey()
						+ "' selected>" + p.getPropertyValue() + "</option>\n");
			else
				buffer.append("\n<option value='" + p.getPropertyKey() + "' >"
						+ p.getPropertyValue() + "</option>\n");
		}

		return buffer.toString();

	}

//	public int getNextSeqNoByGroupKey(String groupKey) {
//		List list = getPropertiesList(groupKey);
//		return (list == null ? 0 : list.size());
//	}

	public void updateOrder(String[] arrays,String userId) {

		int j = 0;
		for (String ary : arrays) {
			BaseProperties baseProperties = get(ary);
			baseProperties.setSeqNo(j++);
			save(baseProperties);
		}
		reload();
	}
	//根据名字判断是否存在该属性
	public BaseProperties queryObjByPropertyPID(String value,String name){
		 String hql="from BaseProperties prop where (prop.id='"+value+"' or prop.propertyValue='"+name+"') and prop.groupKey='base.subject'";
		 List result=find(hql);
		 if(result.size()>0){
			 return (BaseProperties)result.get(0);
		 }
		 return null;
		 
	}
	public Integer maxSeqNo(){
		String hql=" select nvl(max(seq_no),0)+1 from base_properties";
		Session session=this.getSession();
		 Object maxSeqno=session.createSQLQuery(hql).uniqueResult();
		this.releaseSession(session);
		return Integer.valueOf(maxSeqno.toString());
	}
	//查询最大的Code
	public String queryMaxCode(){
		Integer maxNo=0;
		String hql="select prop.propertyKey from BaseProperties prop where prop.groupKey='base.subject'";
		List<String> result=find(hql);
		for(String No : result){
			String one=No.substring(10,No.length());
			Integer number_one=Integer.valueOf(one);
			if(number_one>maxNo){
				maxNo=number_one;
			}
		}
		if(maxNo==0){
			maxNo=00;
		}
		else{
			maxNo++;
		}
		String strResult="subject.00"+maxNo;
		return strResult;
	}
	public void persistenceEntityBySql(BaseProperties bp,String version_id){
		StringBuffer insertSql=new StringBuffer();
		insertSql.append(" insert into base_properties bp ");
		insertSql.append(" (bp.pid, property_key,bp.property_value,bp.seq_no,bp.group_key,bp.item_type, bp.cre_time) ");
		insertSql.append("values('"+version_id+"',");
		insertSql.append("'"+bp.getPropertyKey()+"',");
		insertSql.append("'"+bp.getPropertyValue()+"',");
		insertSql.append("'"+bp.getSeqNo()+"',");
		insertSql.append("'"+bp.getGroupKey()+"',");
		insertSql.append("'"+bp.getItemType()+"',");
		insertSql.append("(select sysdate from dual)");
		insertSql.append(")");
		Session session=this.getSession();
		Query query=session.createSQLQuery(insertSql.toString());
		Integer result=query.executeUpdate();
		this.releaseSession(session);
	}
	public void updateEntity(){
		
	}
	
	public List<BaseProperties> getAllBasePropertiesByUseFlag(int useFlag){
		String hql="from BaseProperties bk where bk.useFlag='"+useFlag+"' order by bk.seqNo";
		return find(hql);
	}
	
	public List<BaseProperties> getAllBasePropertiesByGroupKey(String  groupKey){
		String hql="from BaseProperties t where t.groupKey ='"+groupKey+"' order by t.seqNo";
		return find(hql);
	}
	
	public String getSchoolTypeTreeGb(String xxjgbxlxm3)
	{
		StringBuffer buffer = new StringBuffer();
		Map valueMap=new HashMap();
	    List<BasePropertiesGroup> listGroup=this.getNamedQuery("sdBase::getSchoolTypeGroup::query",valueMap);
	    for(BasePropertiesGroup basePropertiesGroup:listGroup)
		{
	    	String htmlIndent = getRepeatString(basePropertiesGroup.getGroupCode().length()-6, "-");
	    	buffer.append("\n<option value='"
					+ basePropertiesGroup.getGroupKey()+ "' >" + htmlIndent
					+ basePropertiesGroup.getGroupName() + "</option>\n");
	    	if (basePropertiesGroup.getGroupCode().length()==12)
	    	{
	    		List<BaseProperties>  listBaseProperties=this.getAllBasePropertiesByGroupKey(basePropertiesGroup.getGroupKey());
	    		  for(BaseProperties baseProperties:listBaseProperties)
	    			{
	    			  String htmlIndent1 = getRepeatString(9, "-");
	    			  if (xxjgbxlxm3.equals(baseProperties.getPropertyKey()) ){
	    				  buffer.append("\n<option value='"
		    						+ baseProperties.getPropertyKey()+ "' selected=\"selected\" >" + htmlIndent1
		    						+ baseProperties.getPropertyValue() + "</option>\n");
	    			  }else {
	    		    	buffer.append("\n<option value='"
	    						+ baseProperties.getPropertyKey()+ "' >" + htmlIndent1
	    						+ baseProperties.getPropertyValue() + "</option>\n");
	    			  }
	    			}
	    	}
		}
	    
	    return buffer.toString();
	    
	}
	
	public String getInitSchoolTypeTreeGb(String xxjgbxlxm3)
	{
		StringBuffer buffer = new StringBuffer();
		Map valueMap=new HashMap();
	    List<BasePropertiesGroup> listGroup=this.getNamedQuery("sdBase::getSchoolTypeGroup::query",valueMap);
	    for(BasePropertiesGroup basePropertiesGroup:listGroup)
		{
	    	String htmlIndent = getRepeatString(basePropertiesGroup.getGroupCode().length()-6, "-");
	    	buffer.append("\n<option value='"
					+ basePropertiesGroup.getGroupKey()+ "' >" + htmlIndent
					+ basePropertiesGroup.getGroupName() + "</option>\n");
	    	if (basePropertiesGroup.getGroupCode().length()==12)
	    	{
	    		List<BaseProperties>  listBaseProperties=this.getAllBasePropertiesByGroupKey(basePropertiesGroup.getGroupKey());
	    		  for(BaseProperties baseProperties:listBaseProperties)
	    			{
	    			  String htmlIndent1 = getRepeatString(9, "-");
	    			  if (xxjgbxlxm3.equals(baseProperties.getPropertyKey()) ){
	    				  buffer.append("\n<option value='"
		    						+ baseProperties.getPropertyKey()+ "'  >" + htmlIndent1
		    						+ baseProperties.getPropertyValue() + "</option>\n");
	    			  }else {
	    		    	buffer.append("\n<option value='"
	    						+ baseProperties.getPropertyKey()+ "' >" + htmlIndent1
	    						+ baseProperties.getPropertyValue() + "</option>\n");
	    			  }
	    			}
	    	}
		}
	    
	    return buffer.toString();
	    
	}
	
	//制作层次关系
	  public String getRepeatString(int repeatTime, String metaString) {
			String repeatString = "";
			if (repeatTime > 0 && metaString != null) {
				int intMetatStringLength = metaString.length();
				if (intMetatStringLength == 0) {
					repeatString = "";
				} else {
					StringBuffer tempStringBuffer = new StringBuffer(repeatTime
							* intMetatStringLength);
					for (int i = 0; i < repeatTime; i++)
						tempStringBuffer.append(metaString);

					repeatString ="&nbsp;&nbsp;|"+tempStringBuffer.toString();
				}
			}
			return repeatString;
		}
	  
	
	public static String getSchoolTypeByGB(String gbCode){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		if(gbCode!=null){
			String type="";
			for(String t:gbCode.split("#")){
				if(redisManager.objectHasKey(SchoolTypeFmapkey+t)){
					if(type.equals("")){
						type=((BaseProperties)redisManager.getOValue(SchoolTypeFmapkey+t)).getPropertyKey();
					}else{
						type=type+","+ ((BaseProperties)redisManager.getOValue(SchoolTypeFmapkey+t)).getPropertyKey();
					}
				}
			}
			return type;
		}else{
			return null;
		}
	}
}
