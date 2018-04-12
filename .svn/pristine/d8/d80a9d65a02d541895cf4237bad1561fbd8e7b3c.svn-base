package org.my431.platform.dao.extend;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.my431.base.model.BaseQuery;
import org.my431.base.services.CacheBasePropertiesManager;
import org.my431.platform.dao.XsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.my431.plugin.redis.services.RedisManager;
import org.my431.util.DateFormater;
import org.my431.util.MD5;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 简单封装了下xsqlBuilder。 <br>
 * 作用为：
 * 
 * <pre>
 * 
 * 	1.动态构造sql条件语句,提供sql拼接与使用占位符两种方式.
 * 	2.数据类型的修饰.
 * 	3.对SQL注入攻击的防范.
 * </pre>
 * 
 * <pre>
 * 	例：
 * String sql= "select * from user where 1=1 " 
 *  + "/~ and username = {username} ~/" 
 * 	+ "/~ and password = {password} ~/";
 * 	+ "/~ order by [seq] ~/"; 
 *  1.中括号会直接替换为其值,用于拼接SQL.
 *  2.大拓号只是起到标记作用,用于占位符 .
 * </pre>
 * 
 * 拼接的SQL如果不对单引号(有些数据库有反斜杠)进行过滤,则会存在SQL注入攻击问题.
 * xsqlBuilder中SafeSqlProcesser类,会进行sql过滤。
 * 
 * @author wangzhen
 * 
 * @param <T>
 */
@Service("xsqlBuilderQueryService")
public class HibernateXsqlBuilderQueryDao<T> extends
		HibernateEntityExtendDao<T> implements XsqlBuilderQueryDao {
	@SuppressWarnings({ "rawtypes", "unused" })
	
	public List getNamedQuery(String queryName) {
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		BaseQuery define = getBaseQuery(queryName);

		XsqlBuilder xsqlBuilder = new XsqlBuilder();
		XsqlFilterResult result = xsqlBuilder.generateHql(
				define.getQueryString(), new HashMap());
		if (define != null) {

			/** 记录sql每天执行次数 */
			String nowDate = DateFormater.DateToString(new Date(), "yyyyMMdd");
			String sqlQueryCntKey = define.getId() + "::" + nowDate;
			Integer cnt = 0;
			if (redisManager.objectHasKey(sqlQueryCntKey)) {
				Object o = redisManager.getOValue(sqlQueryCntKey);
				if (o != null) {
					cnt = (Integer) o;
				}
			}
			redisManager.setOValue(sqlQueryCntKey, cnt + 1);
			// //////

			List list = new ArrayList();
			if (define.getQueryType().equals("hql")) {
				Integer sqlCacheKey=define.getActiveTime();//sql缓存级别
				Integer activeTime=0;//真实的缓存时间，分钟
				if(isNotEmpty(sqlCacheKey)){
					String activeTimeStr=CacheBasePropertiesManager.getValueByPropertyKey(sqlCacheKey+"");
					if(isNotEmpty(activeTimeStr)){
						activeTime=Integer.valueOf(activeTimeStr);
					}
				}
				//取出缓存的缓存时间
				if (activeTime.equals(0)) {
					org.apache.log4j.Logger.getRootLogger().debug(
							"=================sql无缓存===" + queryName
									+ "=======================");
					// 执行查询
					list = find(result.getXsql());
				} else {
					String vstr = "";

					String sqlMd5 = MD5.getMd5(result.getXsql());
					String valMd5 = MD5.getMd5(vstr);
					String sqlKey = sqlMd5 + "::" + valMd5;

					if (redisManager.objectHasKey(sqlKey)) {
						org.apache.log4j.Logger
								.getRootLogger()
								.debug("=================缓存获取================================");
						if (redisManager.getOValue(sqlKey) != null) {
							list = (List) redisManager.getOValue(sqlKey);
						}
					} else {
						org.apache.log4j.Logger
								.getRootLogger()
								.debug("=================sql获取================================");
						// 执行查询
						list = find(result.getXsql());

						redisManager.setOValue(sqlKey, list);
						redisManager.expire(sqlKey,
								Long.valueOf(activeTime* 60));
					}
				}

				return list;
			} else {
				Integer sqlCacheKey=define.getActiveTime();//sql缓存级别
				Integer activeTime=0;//真实的缓存时间，分钟
				if(isNotEmpty(sqlCacheKey)){
					String activeTimeStr=CacheBasePropertiesManager.getValueByPropertyKey(sqlCacheKey+"");
					if(isNotEmpty(activeTimeStr)){
						activeTime=Integer.valueOf(activeTimeStr);
					}
				}
				//取出缓存的缓存时间
				if (activeTime.equals(0)) {
					org.apache.log4j.Logger.getRootLogger().debug(
							"=================sql无缓存===" + queryName
									+ "=======================");
					// 执行查询
					list = findSql(result.getXsql());
				} else {
					String vstr = "";

					String sqlMd5 = MD5.getMd5(result.getXsql());
					String valMd5 = MD5.getMd5(vstr);
					String sqlKey = sqlMd5 + "::" + valMd5;

					if (redisManager.objectHasKey(sqlKey)) {
						org.apache.log4j.Logger
								.getRootLogger()
								.debug("=================缓存获取================================");
						if (redisManager.getOValue(sqlKey) != null) {
							list = (List) redisManager.getOValue(sqlKey);
						}
					} else {
						org.apache.log4j.Logger
								.getRootLogger()
								.debug("=================sql获取================================");
						// 执行查询
						list = findSql(result.getXsql());

						redisManager.setOValue(sqlKey, list);
						redisManager.expire(sqlKey, activeTime* 60);
					}
				}

				return list;
			}
		} else {
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public List getNamedQuery(String queryName, Map values) {
		BaseQuery define = getBaseQuery(queryName);

		XsqlBuilder xsqlBuilder = new XsqlBuilder();
		XsqlFilterResult result = xsqlBuilder.generateHql(
				define.getQueryString(), values);
		if (define != null) {
			RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
			/** 记录sql每天执行次数 */
			String nowDate = DateFormater.DateToString(new Date(), "yyyyMMdd");
			String sqlQueryCntKey = define.getId() + "::" + nowDate;
			Integer cnt = 0;
			if (redisManager.objectHasKey(sqlQueryCntKey)) {
				Object o = redisManager.getOValue(sqlQueryCntKey);
				if (o != null) {
					cnt = (Integer) o;
				}
			}
			redisManager.setOValue(sqlQueryCntKey, cnt + 1);
			// //////

			List list = new ArrayList();
			if (define.getQueryType().equals("hql")) {
				Integer sqlCacheKey=define.getActiveTime();//sql缓存级别
				Integer activeTime=0;//真实的缓存时间，分钟
				if(isNotEmpty(sqlCacheKey)){
					String activeTimeStr=CacheBasePropertiesManager.getValueByPropertyKey(sqlCacheKey+"");
					if(isNotEmpty(activeTimeStr)){
						activeTime=Integer.valueOf(activeTimeStr);
					}
				}
				//取出缓存的缓存时间
				if (activeTime.equals(0)) {
					org.apache.log4j.Logger.getRootLogger().debug(
							"=================sql无缓存===" + queryName
									+ "=======================");
					// 执行查询
					list = find(result.getXsql(), result.getAcceptedFilters());
				} else {
					String vstr = "";
					if (result.getAcceptedFilters() != null) {
						Map<String, Object> mapVs = result.getAcceptedFilters();
						for (Map.Entry<String, Object> o : mapVs.entrySet()) {
							if (vstr.equals("")) {
								vstr = o.toString();
							} else {
								vstr = vstr + "##" + o.toString();
							}
						}
					}

					String sqlMd5 = MD5.getMd5(result.getXsql());
					String valMd5 = MD5.getMd5(vstr);
					String sqlKey = sqlMd5 + "::" + valMd5;

					if (redisManager.objectHasKey(sqlKey)) {
						org.apache.log4j.Logger
								.getRootLogger()
								.debug("=================缓存获取================================");
						if (redisManager.getOValue(sqlKey) != null) {
							list = (List) redisManager.getOValue(sqlKey);
						}
					} else {
						org.apache.log4j.Logger
								.getRootLogger()
								.debug("=================sql获取================================");
						// 执行查询
						list = find(result.getXsql(),
								result.getAcceptedFilters());

						redisManager.setOValue(sqlKey, list);
						redisManager.expire(sqlKey, activeTime* 60);
					}
				}
				return list;
			} else {

				Integer sqlCacheKey=define.getActiveTime();//sql缓存级别
				Integer activeTime=0;//真实的缓存时间，分钟
				if(isNotEmpty(sqlCacheKey)){
					String activeTimeStr=CacheBasePropertiesManager.getValueByPropertyKey(sqlCacheKey+"");
					if(isNotEmpty(activeTimeStr)){
						activeTime=Integer.valueOf(activeTimeStr);
					}
				}
				//取出缓存的缓存时间
				
				if (activeTime.equals(0)) {
					org.apache.log4j.Logger.getRootLogger().debug(
							"=================sql无缓存===" + queryName
									+ "=======================");
					// 执行查询
					list = findSql(result.getXsql(),
							result.getAcceptedFilters());
				} else {
					String vstr = "";
					if (result.getAcceptedFilters() != null) {
						Map<String, Object> mapVs = result.getAcceptedFilters();
						for (Map.Entry<String, Object> o : mapVs.entrySet()) {
							if (vstr.equals("")) {
								vstr = o.toString();
							} else {
								vstr = vstr + "##" + o.toString();
							}
						}
					}

					String sqlMd5 = MD5.getMd5(result.getXsql());
					String valMd5 = MD5.getMd5(vstr);
					String sqlKey = sqlMd5 + "::" + valMd5;

					if (redisManager.objectHasKey(sqlKey)) {
						org.apache.log4j.Logger
								.getRootLogger()
								.debug("=================缓存获取================================");
						if (redisManager.getOValue(sqlKey) != null) {
							list = (List) redisManager.getOValue(sqlKey);
						}
					} else {
						org.apache.log4j.Logger
								.getRootLogger()
								.debug("=================sql获取================================");
						// 执行查询
						list = findSql(result.getXsql(),
								result.getAcceptedFilters());

						redisManager.setOValue(sqlKey, list);
						//获取缓存时间
						redisManager.expire(sqlKey, activeTime* 60);
					}
				}
				return list;
			}
		} else {
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public Page getPagedNamedQuery(String queryName, int pageNo, int pageSize,
			Map values) {
		BaseQuery define = getBaseQuery(queryName);

		XsqlBuilder xsqlBuilder = new XsqlBuilder();
		XsqlFilterResult result = xsqlBuilder.generateHql(
				define.getQueryString(), values);
		
		/** 记录sql每天执行次数 */
		String nowDate = DateFormater.DateToString(new Date(), "yyyyMMdd");
		String sqlQueryCntKey = define.getId() + "::" + nowDate;
		Integer cnt = 0;
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		if (redisManager.objectHasKey(sqlQueryCntKey)) {
			Object o = redisManager.getOValue(sqlQueryCntKey);
			if (o != null) {
				cnt = (Integer) o;
			}
		}
		redisManager.setOValue(sqlQueryCntKey, cnt + 1);
		// //////

		Page page = null;
		if (define != null) {
			if (define.getQueryType().equals("hql")) {
				Integer sqlCacheKey=define.getActiveTime();//sql缓存级别
				Integer activeTime=0;//真实的缓存时间，分钟
				if(isNotEmpty(sqlCacheKey)){
					String activeTimeStr=CacheBasePropertiesManager.getValueByPropertyKey(sqlCacheKey+"");
					if(isNotEmpty(activeTimeStr)){
						activeTime=Integer.valueOf(activeTimeStr);
					}
				}
				//取出缓存的缓存时间
				if (activeTime.equals(0)) {
					org.apache.log4j.Logger.getRootLogger().debug(
							"=================sql无缓存===" + queryName
									+ "=======================");
					// 执行查询
					page = pageQuery(result.getXsql(), pageNo, pageSize,
							result.getAcceptedFilters());
				} else {
					String vstr = "";
					if (result.getAcceptedFilters() != null) {
						Map<String, Object> mapVs = result.getAcceptedFilters();
						for (Map.Entry<String, Object> o : mapVs.entrySet()) {
							if (vstr.equals("")) {
								vstr = o.toString();
							} else {
								vstr = vstr + "##" + o.toString();
							}
						}
					}
					vstr = vstr + "##" + pageNo + "##" + pageSize;
					String sqlMd5 = MD5.getMd5(result.getXsql());
					String valMd5 = MD5.getMd5(vstr);
					String sqlKey = sqlMd5 + "::" + valMd5;

					if (redisManager.objectHasKey(sqlKey)) {
						org.apache.log4j.Logger
								.getRootLogger()
								.debug("=================缓存获取================================");
						if (redisManager.getOValue(sqlKey) != null) {
							page = (Page) redisManager.getOValue(sqlKey);
						}
					} else {
						org.apache.log4j.Logger
								.getRootLogger()
								.debug("=================sql获取================================");
						// 执行查询
						page = pageQuery(result.getXsql(), pageNo, pageSize,
								result.getAcceptedFilters());

						redisManager.setOValue(sqlKey, page);
						redisManager
								.expire(sqlKey, activeTime * 60);
					}
				}
				return page;
			} else {
				Integer sqlCacheKey=define.getActiveTime();//sql缓存级别
				Integer activeTime=0;//真实的缓存时间，分钟
				if(isNotEmpty(sqlCacheKey)){
					String activeTimeStr=CacheBasePropertiesManager.getValueByPropertyKey(sqlCacheKey+"");
					if(isNotEmpty(activeTimeStr)){
						activeTime=Integer.valueOf(activeTimeStr);
					}
				}
				//取出缓存的缓存时间
				if (activeTime.equals(0)) {
					org.apache.log4j.Logger.getRootLogger().debug(
							"=================sql无缓存===" + queryName
									+ "=======================");
					// 执行查询
					page = pageSQLQuery(result.getXsql(), pageNo, pageSize,
							result.getAcceptedFilters());
				} else {
					String vstr = "";
					if (result.getAcceptedFilters() != null) {
						Map<String, Object> mapVs = result.getAcceptedFilters();
						for (Map.Entry<String, Object> o : mapVs.entrySet()) {
							if (vstr.equals("")) {
								vstr = o.toString();
							} else {
								vstr = vstr + "##" + o.toString();
							}
						}
					}
					vstr = vstr + "##" + pageNo + "##" + pageSize;
					String sqlMd5 = MD5.getMd5(result.getXsql());
					String valMd5 = MD5.getMd5(vstr);
					String sqlKey = sqlMd5 + "::" + valMd5;

					if (redisManager.objectHasKey(sqlKey)) {
						org.apache.log4j.Logger
								.getRootLogger()
								.debug("=================缓存获取================================");
						if (redisManager.getOValue(sqlKey) != null) {
							page = (Page) redisManager.getOValue(sqlKey);
						}
					} else {
						org.apache.log4j.Logger
								.getRootLogger()
								.debug("=================sql获取================================");
						// 执行查询
						page = pageSQLQuery(result.getXsql(), pageNo, pageSize,
								result.getAcceptedFilters());

						redisManager.setOValue(sqlKey, page);
						redisManager.expire(sqlKey, activeTime* 60);
					}
				}

				return page;
			}
		} else {
			return null;
		}
	}

	/**
	 * 预备查询
	 * 
	 * @param queryName
	 * @param values
	 * @param types
	 * @return
	 */
	protected BaseQuery getBaseQuery(final String queryName) {
		BaseQuery define = null;
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		if (redisManager.objectHasKey(queryName)) {
			define = (BaseQuery) redisManager.getOValue(queryName);
		}
		return define;
	}

	@SuppressWarnings("rawtypes")
	public Query createSQLQuery(String sql, Map values) {
		Assert.hasText(sql);
		Query query = getSession().createSQLQuery(sql).setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP);

		Set s = values.keySet();
		for (Iterator it = s.iterator(); it.hasNext();) {
			Object key = it.next();
			query.setParameter(key.toString(), values.get(key));
		}
		return query;
	}
	
	@SuppressWarnings("rawtypes")
	public Query createSQLQueryObj(String sql, Map values) {
		Assert.hasText(sql);
		Query query = getSession().createSQLQuery(sql);
		
		Set s = values.keySet();
		for (Iterator it = s.iterator(); it.hasNext();) {
			Object key = it.next();
			query.setParameter(key.toString(), values.get(key));
		}
		return query;
	}

	@SuppressWarnings("rawtypes")
	public Page pageSQLQuery(String sql, int pageNo, int pageSize, Map values) {
		Assert.hasText(sql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");

		Query query = createSQLQuery(sql, values).setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP);
		
		String count="COUNT(*)";
		String sqllow=sql.toLowerCase();
		String totalSql = sql.substring( sqllow.indexOf("from"), sql.length()); 
		if(sqllow.indexOf("distinct")!=-1){
			sqllow=sqllow.replace("select ", "");
			sqllow=sqllow.replace(sqllow.substring(sqllow.indexOf("from"), sqllow.length()), "");
			String[] sqls=sqllow.split(",");
			for(String s:sqls){
				if(s.indexOf("distinct")!=-1){
					count="COUNT("+s+")";
				}
			}
		}
		totalSql = "SELECT "+count+" "+totalSql;
		
		Query queryCount = createSQLQueryObj(totalSql, values);
		
		Object ob = (queryCount.uniqueResult());
		if(null == ob){
			ob = 0;
		}
		int num=((Number)ob).intValue(); 
		
		long totalCount = Long.valueOf(num);

		if (totalCount < 1)
			return new Page();

		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List list = query.setFirstResult(startIndex).setMaxResults(pageSize)
				.list();

		return new Page(startIndex, totalCount, pageSize, list);
	}

	@SuppressWarnings("rawtypes")
	public Page pageSQLQuery(String sql, int pageNo, int pageSize, Map values,
			Long totalCount) {
		Assert.hasText(sql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");

		Query query = createSQLQuery(sql, values).setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP);

		if (totalCount < 1)
			return new Page();

		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List list = query.setFirstResult(startIndex).setMaxResults(pageSize)
				.list();

		return new Page(startIndex, totalCount, pageSize, list);
	}

	@SuppressWarnings("rawtypes")
	public List findSql(String sql) {
		Query query = getSession().createSQLQuery(sql).setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@SuppressWarnings("rawtypes")
	public List findSql(String sql, Map values) {
		Query query = getSession().createSQLQuery(sql).setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP);
		Set s = values.keySet();
		for (Iterator it = s.iterator(); it.hasNext();) {
			Object key = it.next();
			query.setParameter(key.toString(), values.get(key));
		}
		return query.list();
	}

	@SuppressWarnings("rawtypes")
	public List find(String hql, Map values) {
		Query query = getSession().createQuery(hql);

		Set s = values.keySet();
		for (Iterator it = s.iterator(); it.hasNext();) {
			Object key = it.next();
			query.setParameter(key.toString(), values.get(key));
		}

		return query.list();
	}

	/**
	 * 根据表的名称，字段和值，更新用户信息，使用纯sql
	 * 
	 * @author 刘威
	 * @return
	 */
	public String updateData(Map<String, Object> columValues,
			Map<String, Object> termValues, String tablesName) {
		String sql = "update " + tablesName + " set ";
		Session session = this.getSession();
		for (String colums : columValues.keySet()) {
			if(columValues.get(colums)!=null){
				String value = columValues.get(colums).toString();
				if(value.contains("to_date(")){
					sql = sql + colums + "=" + columValues.get(colums) + ",";
				}else if (value.equals("sysdate")) {
					sql = sql + colums + "=" + columValues.get(colums) + ",";
				} else {
					sql = sql + colums + "='" + columValues.get(colums) + "',";
				}
			}else {
				sql = sql + colums + "=null,";
			}
		}
		sql = sql.substring(0, sql.length() - 1);
		sql = sql + " where 1=1 ";
		for (String term : termValues.keySet()) {

			sql = sql + " and " + term + "='" + termValues.get(term) + "' ";
		}
		System.out.println(sql);
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
		session.clear();
		this.releaseSession(session);
		
		return "success";
	}

	/**
	 * 插入新数据
	 * 
	 * @param columValues
	 *            字段以及对应的值
	 * @author 刘威
	 * @param tablesName
	 *            表名
	 * @return
	 */
	public String insertData(Map<String, Object> columValues, String tablesName) {
		String sql = "insert into " + tablesName + "(";
		Session session = this.getSession();
		for (String colums : columValues.keySet()) {

			sql = sql + colums + ",";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql = sql + ") values(";
		for (Object value : columValues.values()) {

			sql = sql + value + ",";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql = sql + ")";

		org.apache.log4j.Logger.getRootLogger().debug(sql);
		// insert into
		// BASE_NOTE(COUNTY_CODE,CITY_CODE,PROVINCE_ADMIN,IS_TOP,CITY_ADMIN,CRE_TIME,INTRO,SET_TOP_TIME,SET_TOP_USER,MOE_ADMIN,CRE_USER,COUNTY_ADMIN,CONTENT,NOTE_TYPE,CRE_USER_NAME,NOTE_LEVEL,COLOR,PROVINCE_CODE,TITLE)
		// values(null,null,0,1,0,Thu Apr 09 17:39:11 CST 2015,测试通知,Thu Apr 09
		// 17:39:11 CST
		// 2015,itu022113433initu02211,1,itu022113433initu02211,0,阿什否克到,base.note.sys,系统管理员,3,#FF0000,1,测试通知标题1)>

		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
		session.flush();
		session.clear();
		this.releaseSession(session);
		return "success";
	}
	/**
	 * 插入的都是varchar2类型数据           
	 * 
	 * @param columValues
	 *            字段以及对应的值
	 * @author hyl
	 * @param tablesName
	 *            表名
	 * @return
	 */
	public String insertDataV2(Map<String, Object> columValues, String tablesName) {
		String sql = "insert into " + tablesName + "(";
		Session session = this.getSession();
		for (String colums : columValues.keySet()) {

			sql = sql + colums + ",";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql = sql + ") values(";
		for (Object value : columValues.values()) {
			if (value != null) {
				if(value.toString().contains("to_date(")){
					sql = sql + value + ",";
				}else if (value.equals("sysdate")) {
					sql = sql + value + ",";
				} else {
					sql = sql + "'"+value + "',";
				}
			}else {
				sql = sql + "'',";
			}
		}
		sql = sql.substring(0, sql.length() - 1);
		sql = sql + ")";

		org.apache.log4j.Logger.getRootLogger().debug(sql);
		// insert into
		// BASE_NOTE(COUNTY_CODE,CITY_CODE,PROVINCE_ADMIN,IS_TOP,CITY_ADMIN,CRE_TIME,INTRO,SET_TOP_TIME,SET_TOP_USER,MOE_ADMIN,CRE_USER,COUNTY_ADMIN,CONTENT,NOTE_TYPE,CRE_USER_NAME,NOTE_LEVEL,COLOR,PROVINCE_CODE,TITLE)
		// values(null,null,0,1,0,Thu Apr 09 17:39:11 CST 2015,测试通知,Thu Apr 09
		// 17:39:11 CST
		// 2015,itu022113433initu02211,1,itu022113433initu02211,0,阿什否克到,base.note.sys,系统管理员,3,#FF0000,1,测试通知标题1)>

		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
		session.flush();
		session.clear();
		this.releaseSession(session);
		return "success";
	}
	/**
	 * 
	 * author 90 on 2015-3-31
	 * 
	 * @param id
	 *            主键的值
	 * @param idName
	 *            主键字段名
	 * @param tableName
	 *            表名
	 * @param map
	 *            map的key为字段名，value为字段值,map不能为空
	 * @return
	 */
	public boolean updateObject(String id, String idName, String tableName,
			Map<String, Object> map) {
		boolean flag = false;
		String updateSqlHeader = "update " + tableName + " set ";
		String updateSqlField = "";
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if(entry.getValue()!=null&&entry.getValue().toString().contains("to_date(")){
				updateSqlField += entry.getKey() + " = " + entry.getValue() + ",";
			}else{
				updateSqlField += entry.getKey() + " = '" + entry.getValue() + "',";
			}
		}
		updateSqlField = updateSqlField.substring(0,
				updateSqlField.length() - 1);
		String updateSqlLimit = " where " + idName + " = '" + id+"'";
		String updateSql = updateSqlHeader + updateSqlField + updateSqlLimit;
		Session session = this.getSession();
		session.createSQLQuery(updateSql).executeUpdate();
		session.flush();
		session.clear();
		this.releaseSession(session);
		return flag;
	}
	
	/**
	 * 
	 * author 90 on 2015-3-31  备注为了和insertData 的数据格式保持一致
	 * 
	 * @param id
	 *            主键的值
	 * @param idName
	 *            主键字段名
	 * @param tableName
	 *            表名
	 * @param map
	 *            map的key为字段名，value为字段值,map不能为空  
	 * @return
	 */
	public boolean updateObjectMis(String id, String idName, String tableName,
			Map<String, Object> map) {
		boolean flag = false;
		String updateSqlHeader = "update " + tableName + " set ";
		String updateSqlField = "";
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			updateSqlField += entry.getKey() + " = " + entry.getValue() + ",";
		}
		updateSqlField = updateSqlField.substring(0,
				updateSqlField.length() - 1);
		String updateSqlLimit = " where " + idName + " = '" + id+"'";
		String updateSql = updateSqlHeader + updateSqlField + updateSqlLimit;
		Session session = this.getSession();
		session.createSQLQuery(updateSql).executeUpdate();
		session.flush();
		session.clear();
		this.releaseSession(session);
		return flag;
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
	public void executeUpdateByName(String queryName,Map values) {
		BaseQuery define = getBaseQuery(queryName);

		XsqlBuilder xsqlBuilder = new XsqlBuilder();
		XsqlFilterResult result = xsqlBuilder.generateHql(
				define.getQueryString(), values);
		if (define != null) {
			RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
			/** 记录sql每天执行次数 */
			String nowDate = DateFormater.DateToString(new Date(), "yyyyMMdd");
			String sqlQueryCntKey = define.getId() + "::" + nowDate;
			Integer cnt = 0;
			if (redisManager.objectHasKey(sqlQueryCntKey)) {
				Object o = redisManager.getOValue(sqlQueryCntKey);
				if (o != null) {
					cnt = (Integer) o;
				}
			}
			redisManager.setOValue(sqlQueryCntKey, cnt + 1);
			// //////

			List list = new ArrayList();
			if (define.getQueryType().equals("hql")) {
					Session session = this.getSession();
					// 执行查询
					Query query=session.createQuery(result.getXsql());
					Set s = result.getAcceptedFilters().keySet();
					for (Iterator it = s.iterator(); it.hasNext();) {
						Object key = it.next();
						query.setParameter(key.toString(), values.get(key));
					}
					query.executeUpdate();
					this.releaseSession(session);

			} else {
					Session session = this.getSession();
					// 执行查询
					SQLQuery query=session.createSQLQuery(result.getXsql());
					Set s = result.getAcceptedFilters().keySet();
					for (Iterator it = s.iterator(); it.hasNext();) {
						Object key = it.next();
						query.setParameter(key.toString(), values.get(key));
					}
					query.executeUpdate();
					this.releaseSession(session);

			}
		}
	}
	
	/**
	 * 删除表中的数据直接删除
	 * author  90  
	 * on 2015-5-14
	 * @param id
	 * @param idName
	 * @param tableName
	 * @param map
	 * @return
	 */
	public boolean delObject(String id, String idName, String tableName) {
		boolean flag = false;
		String updateSqlHeader = "delete  " + tableName ;
		String updateSqlLimit = " where " + idName + " = '" + id+"'";
		String updateSql=updateSqlHeader+updateSqlLimit;
		Session session = this.getSession();
		session.createSQLQuery(updateSql).executeUpdate();
		session.flush();
		session.clear();
		this.releaseSession(session);
		return flag;
	}
	/**
	 * 执行一条更新或者删除的sql语句
	 * author  90  
	 * on 2015-5-14
	 * @param id
	 * @param idName
	 * @param tableName
	 * @param map
	 * @return
	 */
	public void exeSql(String updateSql) {
		Session session = this.getSession();
		session.createSQLQuery(updateSql).executeUpdate();
		session.flush();
		session.clear();
		this.releaseSession(session);
	}
	
	
	/**
	 * 更新多个字段
	 * @author    hmc    2015年6月11日  下午1:56:02
	 * @param columValues
	 * @param termValues
	 * @param tablesName
	 * @return
	 */
	public String updateDatas(Map<String, Object> columValues, String[] mWhere,String updateZd[], String tablesName) {
		String sql = "update " + tablesName + " set ";
		Session session = this.getSession();
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
		int i=0;
		for (String term : mWhere) {
			if(i>0){
				sql+=" or   AREA_ID='" + term + "' ";
			}else{
				sql = sql + " and  ( AREA_ID='" + term + "' ";
			}
			i++;
			
		}
		sql+=" )";
		System.out.println(sql);
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
		session.flush();
		session.clear();
		this.releaseSession(session);
		
		return "success";
	}
	
	
	/**
	 * 执行一条更新语句
	 * @author 90
	 * @date    2017-6-29
	 * @param sql
	 * @return
	 */
	public String updateSql(String sql) {
		Session session = this.getSession();
		org.apache.log4j.Logger.getRootLogger().debug(sql);
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
		session.flush();
		session.clear();
		this.releaseSession(session);
		return "success";
	}
	
}
