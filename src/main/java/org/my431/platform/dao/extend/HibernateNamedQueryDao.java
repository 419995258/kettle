package org.my431.platform.dao.extend;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.displaytag.pagination.PaginatedList;
import org.hibernate.Query;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.hql.ast.QueryTranslatorImpl;
import org.my431.base.model.BaseQuery;
import org.my431.platform.dao.NamedQueryDao;
import org.my431.platform.dao.support.Page;
import org.my431.platform.utils.SQLParserUtil;
import org.my431.plugin.redis.services.RedisManager;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


@Service("namedQueryService")
@SuppressWarnings("unused")
public class HibernateNamedQueryDao<T> extends HibernateEntityExtendDao<T> implements NamedQueryDao {
	private static Log log = LogFactory.getLog(HibernateNamedQueryDao.class);

	private JdbcTemplate jdbcTemplate;

	/**
	 * 执行查询
	 * 
	 * @param isCustom
	 * @param queryName
	 * @param cql
	 * @param values
	 * @return
	 * @throws DataAccessException
	 */
	public List getNamedQueryImpl(boolean isCustom, String queryName, String cql, Object... values)
			throws DataAccessException {

		BaseQuery define = getBaseQuery(queryName);
		
		if (define != null) {
			
			//SQL语句解析：条件语句分析，参数为空值的条件剔除
			Map<String, Object> map = SQLParserUtil.parseSQL(define.getQueryString(), values);
			String queryString = (String)map.get("sql");
			Object[] vals = (Object[])map.get("values");
			
			if (isCustom) define.setQueryString(cql);
			
			List list = null;
			String key = "";
			
			//执行查询
			if (define.getQueryType().equals("hql")) { //hql, 则调用org.hibernate.Query的list()方法
				Query queryObject = prepareQuery(define, values);
				list = queryObject.list();
			} else { //sql, 则调用 spring JdbcTemplate的 queryForList方法
				//执行前日志输出
				log.info("counter query:" +queryString);
				System.out.println(queryString);
				//执行查询
				list = jdbcTemplate.queryForList(queryString, vals);
			}


			return list;
		}
		return null;
	}

	/**
	 * 获取对象
	 * 
	 * @param isCustom
	 * @param queryName
	 * @param cql
	 * @param values
	 * @return
	 */
	public Object getNamedQueryObjectImpl(boolean isCustom, String queryName, String cql, final Object... values) {
		List list = getNamedQueryImpl(isCustom, queryName, cql, values);
		if (list == null || list.size() == 0)
			return null;
		return list.get(0);
	}

	/**
	 * 执行更新
	 * 
	 * @param isCustom
	 * @param queryName
	 * @param cql
	 * @param values
	 * @return
	 * @throws DataAccessException
	 */
	public int executeNamedUpdateImpl(boolean isCustom, String queryName, String cql, final Object... values)
			throws DataAccessException {
		BaseQuery define = getBaseQuery(queryName);
		if (define != null) {
			if (isCustom)
				define.setQueryString(cql);
			if (define.getQueryType().equals("hql")) {
				Query queryObject = prepareQuery(define, values);
				return queryObject.executeUpdate();
			} else {
				return jdbcTemplate.update(define.getQueryString(), values);
			}
		}
		return 0;
	}

	/**
	 * 查询部分数据
	 * 
	 * @param isCustom
	 * @param queryName
	 * @param cql
	 * @param recordSize
	 * @param values
	 * @return
	 * @throws DataAccessException
	 */
	public List getLimitedNamedQueryImpl(boolean isCustom, String queryName, String cql, int recordSize,
			Object... values) throws DataAccessException {
		BaseQuery define = getBaseQuery(queryName);
		
		if (define != null) {
			
			//SQL语句解析：条件语句分析，参数为空值的条件剔除
			Map<String, Object> map = SQLParserUtil.parseSQL(define.getQueryString(), values);
			String queryString = (String)map.get("sql");
			Object[] vals = (Object[])map.get("values");
			
			if (isCustom) define.setQueryString(cql);
			
			List list = null;
			
			//执行查询
			if (define.getQueryType().equals("hql")) { //HQL，调用Hibernate方法查询
				Query queryObject = prepareQuery(define, values);
				list = queryObject.setFirstResult(0).setMaxResults(recordSize).list();
			} else { //sql, 则调用 spring JdbcTemplate的 queryForList方法
				//执行前日志输出
				log.info("counter query:" +queryString);
				//执行查询
				list = jdbcTemplate.queryForList(
						"select b.* From (Select Row_.*, Rownum as Rownum_ From ( " + queryString
								+ ") Row_ Where Rownum <= " + recordSize + ") b Where b.Rownum_ > 0", vals);
			}


			return list;
		}
		return null;
	}

	/**
	 * 分页查询
	 * 
	 * @param isCustom
	 * @param queryName
	 * @param cql
	 * @param pageNo
	 * @param pageSize
	 * @param values
	 * @return
	 * @throws DataAccessException
	 */
	public Page getPagedNamedQueryImpl(boolean isCustom, String queryName, String cql, int pageNo,
			int pageSize, Object... values) throws DataAccessException {
		BaseQuery define = getBaseQuery(queryName);
		if (define != null) {
			
			//SQL语句解析：条件语句分析，参数为空值的条件剔除
			Map<String, Object> map = SQLParserUtil.parseSQL(define.getQueryString(), values);
			String queryString = (String)map.get("sql");
			Object[] vals = (Object[])map.get("values");
			
			
			//自定义查询
			if (isCustom) define.setQueryString(cql);
			String key = "";
			//countQueryString构造
			String countQueryString = "";
			
			
			//HQL
			Query queryObject = null;
			if (define.getQueryType().equalsIgnoreCase("hql")) {
				queryObject = prepareQuery(define, values);
				countQueryString = getCountSqlQueryString(define.getQueryType().equals("hql"), queryObject.getQueryString());
			}
			
			//SQL
			if(define.getQueryType().equalsIgnoreCase("sql")) {
				//计数查询语句构造, removeOrders
				countQueryString = "select count(*) from (" +removeOrders(queryString) + ") tmp_count_table";
			}
			
			//执行前日志输出
			log.info("counter query:" +countQueryString);
			//执行记录条数查询
			long totalCount = jdbcTemplate.queryForLong(countQueryString, vals);

			Page page = new Page();
			if (totalCount >= 1) {
				// 实际查询返回分页对象
				int startIndex = Page.getStartOfPage(pageNo, pageSize);
				List list = null;
				if (define.getQueryType().equals("hql")) //HQL
					list = queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();
				else { //SQL
					//分页查询语句构造
//					String sqlLimitPrefix = "select * From (Select Row_.*, Rownum Rownum_ From ( ";
//					String sqlLimitExt = ") Row_ Where Rownum <= #endIndex) Where Rownum_ > #startIndex";
//					String pagedSQL = sqlLimitPrefix +queryString +sqlLimitExt.replaceAll("#startIndex", startIndex + "").replaceAll("#endIndex", (startIndex + pageSize) + "");
					
					String sqlLimitExt = " limit #startIndex ,#endIndex";
					String pagedSQL = queryString +sqlLimitExt.replaceAll("#startIndex", startIndex + "").replaceAll("#endIndex", pageSize + "");
					
					//执行前日志输出
					log.info("query: " +queryString);
					//执行分页查询
					list = jdbcTemplate.queryForList(pagedSQL, vals);
				}

				page = new Page(startIndex, totalCount, pageSize, list);
			}
			
			return page;
		}
		return null;
	}

	/**
	 * 查询部分排序数据
	 * 
	 * @param isCustom
	 * @param queryName
	 * @param cql
	 * @param recordeSize
	 * @param orderBy
	 * @param isAsc
	 * @param values
	 * @return
	 * @throws DataAccessException
	 */
	public List getOrderedLimitedNamedQueryImpl(boolean isCustom, String queryName, String cql, int recordeSize,
			String orderBy, boolean isAsc, Object... values) throws DataAccessException {
		BaseQuery define = getBaseQuery(queryName);

		if (define != null) {
			if (StringUtils.isNotBlank(orderBy)) {
				if (isCustom)
					define.setQueryString(cql);
				List list = null;
				Query queryObject = prepareQuery(define, values);
				String queryString = removeOrders(queryObject.getQueryString()) + " order by " + orderBy
						+ (isAsc ? "" : " desc");
				if (define.getQueryType().equals("hql")) {

					queryObject = createQuery(queryString);

					// 向查询中赋查询参数
					prepareQueryValues(queryObject, values);
					list = queryObject.setFirstResult(0).setMaxResults(recordeSize).list();
				} else {
					String sqlLimitPrefix = "select * From (Select Row_.*, Rownum Rownum_ From ( ";
					String sqlLimitExt = ") Row_ Where Rownum <= #endIndex) Where Rownum_ > #startIndex";
					// 实际查询返回分页对象
					int pageNo = 1;
					int pageSize = recordeSize;
					int startIndex = Page.getStartOfPage(pageNo, pageSize);
					list = jdbcTemplate.queryForList(sqlLimitPrefix
							+ queryString
							+ sqlLimitExt.replaceAll("#startIndex", startIndex + "").replaceAll("#endIndex",
									(startIndex + pageSize) + ""), values);

				}
				return list;
			}
			return getLimitedNamedQueryImpl(isCustom, queryName, cql, recordeSize, values);
		}
		return null;
	}

	/**
	 * 查询分页排序数据
	 * 
	 * @param isCustom
	 * @param queryName
	 * @param cql
	 * @param orderBy
	 * @param isAsc
	 * @param pageNo
	 * @param pageSize
	 * @param values
	 * @return
	 * @throws DataAccessException
	 */
	public Page getOrderedPagedNamedQueryImpl(boolean isCustom, String queryName, String cql, String orderBy,
			boolean isAsc, int pageNo, int pageSize, Object... values) throws DataAccessException {

		BaseQuery define = getBaseQuery(queryName);

		if (define != null) {
			if (StringUtils.isNotBlank(orderBy)) {
				if (isCustom)
					define.setQueryString(cql);

				Query queryObject = prepareQuery(define, values);
				String queryString = removeOrders(queryObject.getQueryString()) + " order by " + orderBy
						+ (isAsc ? "" : " desc");
				if (define.getQueryType().equals("hql"))
					queryObject = createQuery(queryString);
				else
					queryObject = getSession().createSQLQuery(queryString);

				// 向查询中赋查询参数
				prepareQueryValues(queryObject, values);
				String countQueryString = getCountSqlQueryString(define.getQueryType().equals("hql"), queryObject
						.getQueryString());

				long totalCount = jdbcTemplate.queryForLong(countQueryString, values);

				Page page = new Page();
				if (totalCount >= 1) {

					// 实际查询返回分页对象
					int startIndex = Page.getStartOfPage(pageNo, pageSize);
					List list = null;
					if (define.getQueryType().equals("hql"))
						list = queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();
					else {
						String sqlLimitPrefix = "select * From (Select Row_.*, Rownum Rownum_ From ( ";
						String sqlLimitExt = ") Row_ Where Rownum <= #endIndex) Where Rownum_ > #startIndex";
						list = jdbcTemplate.queryForList(sqlLimitPrefix
								+ queryObject.getQueryString()
								+ sqlLimitExt.replaceAll("#startIndex", startIndex + "").replaceAll("#endIndex",
										(startIndex + pageSize) + ""), values);

					}

					page = new Page(startIndex, totalCount, pageSize, list);
				}

				return page;
			}
			return getPagedNamedQueryImpl(isCustom, queryName, cql, pageNo, pageSize, values);
		}
		return null;
	}

	public String getCountSqlQueryString(boolean isHql, String hql) throws DataAccessException {
		String countHql = removeOrders(hql);
		
		if (isHql) { //HQL
			QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(countHql, countHql, Collections.EMPTY_MAP,
					(SessionFactoryImplementor) this.getSessionFactory());
			queryTranslator.compile(Collections.EMPTY_MAP, false);

			countHql = "select count(*) from (" + queryTranslator.getSQLString() + ") tmp_count_t";
		} else { //SQL
			countHql = "select count(*) from (" + countHql + ") tmp_count_t";
		}
		return countHql;
	}

	/**
	 * 执行更新
	 */
	public int executeNamedUpdate(final String queryName) throws DataAccessException {
		return executeNamedUpdate(queryName, null);
	}

	/**
	 * 执行更新
	 */
	public int executeNamedUpdate(final String queryName, final Object value1) throws DataAccessException {
		return executeNamedUpdate(queryName, new Object[] { value1 });
	}

	/**
	 * 执行更新
	 */
	public int executeNamedUpdate(final String queryName, final Object value1, final Object value2)
			throws DataAccessException {
		return executeNamedUpdate(queryName, new Object[] { value1, value2 });
	}

	/**
	 * 执行更新
	 */
	public int executeNamedUpdate(final String queryName, final Object value1, final Object value2, final Object value3)
			throws DataAccessException {
		return executeNamedUpdate(queryName, new Object[] { value1, value2, value3 });
	}

	/**
	 * 执行更新
	 */
	public int executeNamedUpdate(String queryName, Object value1, Object value2, Object value3, Object value4)
			throws DataAccessException {
		return executeNamedUpdate(queryName, new Object[] { value1, value2, value3, value4 });
	}

	/**
	 * 执行更新
	 */
	public int executeNamedUpdate(String queryName, Object value1, Object value2, Object value3, Object value4,
			Object value5) throws DataAccessException {
		return executeNamedUpdate(queryName, new Object[] { value1, value2, value3, value4, value5 });
	}

	/**
	 * 执行更新
	 */
	public int executeNamedUpdate(String queryName, Object value1, Object value2, Object value3, Object value4,
			Object value5, Object value6) throws DataAccessException {
		return executeNamedUpdate(queryName, new Object[] { value1, value2, value3, value4, value5, value6 });
	}

	/**
	 * 执行更新
	 */
	public int executeNamedUpdate(String queryName, Object value1, Object value2, Object value3, Object value4,
			Object value5, Object value6, Object value7) throws DataAccessException {
		return executeNamedUpdate(queryName, new Object[] { value1, value2, value3, value4, value5, value6, value7 });
	}

	/**
	 * 执行更新
	 */
	public int executeNamedUpdate(String queryName, Object value1, Object value2, Object value3, Object value4,
			Object value5, Object value6, Object value7, Object value8) throws DataAccessException {
		return executeNamedUpdate(queryName, new Object[] { value1, value2, value3, value4, value5, value6, value7,
				value8 });
	}

	/**
	 * 执行更新
	 */
	public int executeNamedUpdate(String queryName, Object value1, Object value2, Object value3, Object value4,
			Object value5, Object value6, Object value7, Object value8, Object value9) throws DataAccessException {
		return executeNamedUpdate(queryName, new Object[] { value1, value2, value3, value4, value5, value6, value7,
				value8, value9 });
	}

	/**
	 * 执行查询
	 */
	public List getNamedQuery(final String queryName) throws DataAccessException {
		return getNamedQuery(queryName, null);
	}

	/**
	 * 执行查询
	 */
	public List getNamedQuery(final String queryName, final Object value1) throws DataAccessException {
		return getNamedQuery(queryName, new Object[] { value1 });
	}

	/**
	 * 执行查询
	 */
	public List getNamedQuery(final String queryName, final Object value1, final Object value2)
			throws DataAccessException {
		return getNamedQuery(queryName, new Object[] { value1, value2 });
	}

	/**
	 * 执行查询
	 */
	public List getNamedQuery(final String queryName, final Object value1, final Object value2, final Object value3)
			throws DataAccessException {
		return getNamedQuery(queryName, new Object[] { value1, value2, value3 });
	}

	/**
	 * 执行查询
	 */
	public List getNamedQuery(String queryName, Object value1, Object value2, Object value3, Object value4)
			throws DataAccessException {
		return getNamedQuery(queryName, new Object[] { value1, value2, value3, value4 });
	}

	/**
	 * 执行查询
	 */
	public List getNamedQuery(String queryName, Object value1, Object value2, Object value3, Object value4,
			Object value5) throws DataAccessException {
		return getNamedQuery(queryName, new Object[] { value1, value2, value3, value4, value5 });
	}

	/**
	 * 执行查询
	 */
	public List getNamedQuery(String queryName, Object value1, Object value2, Object value3, Object value4,
			Object value5, Object value6) throws DataAccessException {
		return getNamedQuery(queryName, new Object[] { value1, value2, value3, value4, value5, value6 });
	}

	/**
	 * 执行查询
	 */
	public List getNamedQuery(String queryName, Object value1, Object value2, Object value3, Object value4,
			Object value5, Object value6, Object value7) throws DataAccessException {
		return getNamedQuery(queryName, new Object[] { value1, value2, value3, value4, value5, value6, value7 });
	}

	/**
	 * 执行查询
	 */
	public List getNamedQuery(String queryName, Object value1, Object value2, Object value3, Object value4,
			Object value5, Object value6, Object value7, Object value8) throws DataAccessException {
		return getNamedQuery(queryName, new Object[] { value1, value2, value3, value4, value5, value6, value7, value8 });
	}

	/**
	 * 执行查询
	 */
	public List getNamedQuery(String queryName, Object value1, Object value2, Object value3, Object value4,
			Object value5, Object value6, Object value7, Object value8, Object value9) throws DataAccessException {
		return getNamedQuery(queryName, new Object[] { value1, value2, value3, value4, value5, value6, value7, value8,
				value9 });
	}

	/**
	 * 获取对象
	 */
	public Object getNamedQueryObject(final String queryName) throws DataAccessException {
		return getNamedQueryObject(queryName, null);
	}

	/**
	 * 获取对象
	 */
	public Object getNamedQueryObject(final String queryName, final Object value1) throws DataAccessException {
		return getNamedQueryObject(queryName, new Object[] { value1 });
	}

	/**
	 * 获取对象
	 */
	public Object getNamedQueryObject(final String queryName, final Object value1, final Object value2)
			throws DataAccessException {
		return getNamedQueryObject(queryName, new Object[] { value1, value2 });
	}

	/**
	 * 获取对象
	 */
	public Object getNamedQueryObject(final String queryName, final Object value1, final Object value2,
			final Object value3) throws DataAccessException {
		return getNamedQueryObject(queryName, new Object[] { value1, value2, value3 });
	}

	/**
	 * 获取对象
	 */
	public Object getNamedQueryObject(String queryName, Object value1, Object value2, Object value3, Object value4)
			throws DataAccessException {
		return getNamedQueryObject(queryName, new Object[] { value1, value2, value3, value4 });
	}

	/**
	 * 获取对象
	 */
	public Object getNamedQueryObject(String queryName, Object value1, Object value2, Object value3, Object value4,
			Object value5) throws DataAccessException {
		return getNamedQueryObject(queryName, new Object[] { value1, value2, value3, value4, value5 });
	}

	/**
	 * 获取对象
	 */
	public Object getNamedQueryObject(String queryName, Object value1, Object value2, Object value3, Object value4,
			Object value5, Object value6) throws DataAccessException {
		return getNamedQueryObject(queryName, new Object[] { value1, value2, value3, value4, value5, value6 });
	}

	/**
	 * 获取对象
	 */
	public Object getNamedQueryObject(String queryName, Object value1, Object value2, Object value3, Object value4,
			Object value5, Object value6, Object value7) throws DataAccessException {
		return getNamedQueryObject(queryName, new Object[] { value1, value2, value3, value4, value5, value6, value7 });
	}

	/**
	 * 获取对象
	 */
	public Object getNamedQueryObject(String queryName, Object value1, Object value2, Object value3, Object value4,
			Object value5, Object value6, Object value7, Object value8) throws DataAccessException {
		return getNamedQueryObject(queryName, new Object[] { value1, value2, value3, value4, value5, value6, value7,
				value8 });
	}

	/**
	 * 获取对象
	 */
	public Object getNamedQueryObject(String queryName, Object value1, Object value2, Object value3, Object value4,
			Object value5, Object value6, Object value7, Object value8, Object value9) throws DataAccessException {
		return getNamedQueryObject(queryName, new Object[] { value1, value2, value3, value4, value5, value6, value7,
				value8, value9 });
	}

	/**
	 * 查询部分数据
	 */
	public List getLimitedNamedQuery(String queryName, int recordeSize) throws DataAccessException {
		return getLimitedNamedQuery(queryName, recordeSize, null);
	}

	/**
	 * 查询部分数据
	 */
	public List getLimitedNamedQuery(String queryName, int recordeSize, final Object value1) throws DataAccessException {
		return getLimitedNamedQuery(queryName, recordeSize, new Object[] { value1 });
	}

	/**
	 * 查询部分数据
	 */
	public List getLimitedNamedQuery(String queryName, int recordeSize, final Object value1, final Object value2)
			throws DataAccessException {
		return getLimitedNamedQuery(queryName, recordeSize, new Object[] { value1, value2 });
	}

	/**
	 * 查询部分数据
	 */
	public List getLimitedNamedQuery(String queryName, int recordeSize, final Object value1, final Object value2,
			final Object value3) throws DataAccessException {
		return getLimitedNamedQuery(queryName, recordeSize, new Object[] { value1, value2, value3 });
	}

	/**
	 * 查询部分数据
	 */
	public List getLimitedNamedQuery(String queryName, int recordeSize, Object value1, Object value2, Object value3,
			Object value4) throws DataAccessException {
		return getLimitedNamedQuery(queryName, recordeSize, new Object[] { value1, value2, value3, value4 });
	}

	/**
	 * 查询部分数据
	 */
	public List getLimitedNamedQuery(String queryName, int recordeSize, Object value1, Object value2, Object value3,
			Object value4, Object value5) throws DataAccessException {
		return getLimitedNamedQuery(queryName, recordeSize, new Object[] { value1, value2, value3, value4, value5 });
	}

	/**
	 * 查询部分数据
	 */
	public List getLimitedNamedQuery(String queryName, int recordeSize, Object value1, Object value2, Object value3,
			Object value4, Object value5, Object value6) throws DataAccessException {
		return getLimitedNamedQuery(queryName, recordeSize, new Object[] { value1, value2, value3, value4, value5,
				value6 });
	}

	/**
	 * 查询部分数据
	 */
	public List getLimitedNamedQuery(String queryName, int recordeSize, Object value1, Object value2, Object value3,
			Object value4, Object value5, Object value6, Object value7) throws DataAccessException {
		return getLimitedNamedQuery(queryName, recordeSize, new Object[] { value1, value2, value3, value4, value5,
				value6, value7 });
	}

	/**
	 * 查询部分数据
	 */
	public List getLimitedNamedQuery(String queryName, int recordeSize, Object value1, Object value2, Object value3,
			Object value4, Object value5, Object value6, Object value7, Object value8) throws DataAccessException {
		return getLimitedNamedQuery(queryName, recordeSize, new Object[] { value1, value2, value3, value4, value5,
				value6, value7, value8 });
	}

	/**
	 * 查询部分数据
	 */
	public List getLimitedNamedQuery(String queryName, int recordeSize, Object value1, Object value2, Object value3,
			Object value4, Object value5, Object value6, Object value7, Object value8, Object value9)
			throws DataAccessException {
		return getLimitedNamedQuery(queryName, recordeSize, new Object[] { value1, value2, value3, value4, value5,
				value6, value7, value8, value9 });
	}

	/**
	 * 查询部分排序数据
	 */
	public List getOrderedLimitedNamedQuery(String queryName, int recordeSize, String orderBy, boolean isAsc)
			throws DataAccessException {
		return getOrderedLimitedNamedQuery(queryName, recordeSize, orderBy, isAsc, null);
	}

	/**
	 * 查询部分排序数据
	 */
	public List getOrderedLimitedNamedQuery(String queryName, int recordeSize, String orderBy, boolean isAsc,
			final Object value1) throws DataAccessException {
		return getOrderedLimitedNamedQuery(queryName, recordeSize, orderBy, isAsc, new Object[] { value1 });
	}

	/**
	 * 查询部分排序数据
	 */
	public List getOrderedLimitedNamedQuery(String queryName, int recordeSize, String orderBy, boolean isAsc,
			final Object value1, final Object value2) throws DataAccessException {
		return getOrderedLimitedNamedQuery(queryName, recordeSize, orderBy, isAsc, new Object[] { value1, value2 });
	}

	/**
	 * 查询部分排序数据
	 */
	public List getOrderedLimitedNamedQuery(String queryName, int recordeSize, String orderBy, boolean isAsc,
			final Object value1, final Object value2, final Object value3) throws DataAccessException {
		return getOrderedLimitedNamedQuery(queryName, recordeSize, orderBy, isAsc, new Object[] { value1, value2,
				value3 });
	}

	/**
	 * 查询部分排序数据
	 */
	public List getOrderedLimitedNamedQuery(String queryName, int recordeSize, String orderBy, boolean isAsc,
			Object value1, Object value2, Object value3, Object value4) throws DataAccessException {
		return getOrderedLimitedNamedQuery(queryName, recordeSize, orderBy, isAsc, new Object[] { value1, value2,
				value3, value4 });
	}

	/**
	 * 查询部分排序数据
	 */
	public List getOrderedLimitedNamedQuery(String queryName, int recordeSize, String orderBy, boolean isAsc,
			Object value1, Object value2, Object value3, Object value4, Object value5) throws DataAccessException {
		return getOrderedLimitedNamedQuery(queryName, recordeSize, orderBy, isAsc, new Object[] { value1, value2,
				value3, value4, value5 });
	}

	/**
	 * 查询部分排序数据
	 */
	public List getOrderedLimitedNamedQuery(String queryName, int recordeSize, String orderBy, boolean isAsc,
			Object value1, Object value2, Object value3, Object value4, Object value5, Object value6)
			throws DataAccessException {
		return getOrderedLimitedNamedQuery(queryName, recordeSize, orderBy, isAsc, new Object[] { value1, value2,
				value3, value4, value5, value6 });
	}

	/**
	 * 查询部分排序数据
	 */
	public List getOrderedLimitedNamedQuery(String queryName, int recordeSize, String orderBy, boolean isAsc,
			Object value1, Object value2, Object value3, Object value4, Object value5, Object value6, Object value7)
			throws DataAccessException {
		return getOrderedLimitedNamedQuery(queryName, recordeSize, orderBy, isAsc, new Object[] { value1, value2,
				value3, value4, value5, value6, value7 });
	}

	/**
	 * 查询部分排序数据
	 */
	public List getOrderedLimitedNamedQuery(String queryName, int recordeSize, String orderBy, boolean isAsc,
			Object value1, Object value2, Object value3, Object value4, Object value5, Object value6, Object value7,
			Object value8) throws DataAccessException {
		return getOrderedLimitedNamedQuery(queryName, recordeSize, orderBy, isAsc, new Object[] { value1, value2,
				value3, value4, value5, value6, value7, value8 });
	}

	/**
	 * 查询部分排序数据
	 */
	public List getOrderedLimitedNamedQuery(String queryName, int recordeSize, String orderBy, boolean isAsc,
			Object value1, Object value2, Object value3, Object value4, Object value5, Object value6, Object value7,
			Object value8, Object value9) throws DataAccessException {
		return getOrderedLimitedNamedQuery(queryName, recordeSize, orderBy, isAsc, new Object[] { value1, value2,
				value3, value4, value5, value6, value7, value8, value9 });
	}

	/**
	 * 查询分页排序数据
	 */
	public Page getOrderedPagedNamedQuery(String queryName, String orderBy, boolean isAsc, int pageNo,
			int pageSize) throws DataAccessException {
		return getOrderedPagedNamedQuery(queryName, orderBy, isAsc, pageNo, pageSize, null);
	}

	/**
	 * 查询分页排序数据
	 */
	public Page getOrderedPagedNamedQuery(String queryName, String orderBy, boolean isAsc, int pageNo,
			int pageSize, final Object value1) throws DataAccessException {
		return getOrderedPagedNamedQuery(queryName, orderBy, isAsc, pageNo, pageSize, new Object[] { value1 });
	}

	/**
	 * 查询分页排序数据
	 */
	public Page getOrderedPagedNamedQuery(String queryName, String orderBy, boolean isAsc, int pageNo,
			int pageSize, final Object value1, final Object value2) throws DataAccessException {
		return getOrderedPagedNamedQuery(queryName, orderBy, isAsc, pageNo, pageSize, new Object[] { value1, value2 });
	}

	/**
	 * 查询分页排序数据
	 */
	public Page getOrderedPagedNamedQuery(String queryName, String orderBy, boolean isAsc, int pageNo,
			int pageSize, final Object value1, final Object value2, final Object value3) throws DataAccessException {
		return getOrderedPagedNamedQuery(queryName, orderBy, isAsc, pageNo, pageSize, new Object[] { value1, value2,
				value3 });
	}

	/**
	 * 查询分页排序数据
	 */
	public Page getOrderedPagedNamedQuery(String queryName, String orderBy, boolean isAsc, int pageNo,
			int pageSize, Object value1, Object value2, Object value3, Object value4) throws DataAccessException {
		return getOrderedPagedNamedQuery(queryName, orderBy, isAsc, pageNo, pageSize, new Object[] { value1, value2,
				value3, value4 });
	}

	/**
	 * 查询分页排序数据
	 */
	public Page getOrderedPagedNamedQuery(String queryName, String orderBy, boolean isAsc, int pageNo,
			int pageSize, Object value1, Object value2, Object value3, Object value4, Object value5)
			throws DataAccessException {
		return getOrderedPagedNamedQuery(queryName, orderBy, isAsc, pageNo, pageSize, new Object[] { value1, value2,
				value3, value4, value5 });
	}

	/**
	 * 查询分页排序数据
	 */
	public Page getOrderedPagedNamedQuery(String queryName, String orderBy, boolean isAsc, int pageNo,
			int pageSize, Object value1, Object value2, Object value3, Object value4, Object value5, Object value6)
			throws DataAccessException {
		return getOrderedPagedNamedQuery(queryName, orderBy, isAsc, pageNo, pageSize, new Object[] { value1, value2,
				value3, value4, value5, value6 });
	}

	/**
	 * 查询分页排序数据
	 */
	public Page getOrderedPagedNamedQuery(String queryName, String orderBy, boolean isAsc, int pageNo,
			int pageSize, Object value1, Object value2, Object value3, Object value4, Object value5, Object value6,
			Object value7) throws DataAccessException {
		return getOrderedPagedNamedQuery(queryName, orderBy, isAsc, pageNo, pageSize, new Object[] { value1, value2,
				value3, value4, value5, value6, value7 });
	}

	/**
	 * 查询分页排序数据
	 */
	public Page getOrderedPagedNamedQuery(String queryName, String orderBy, boolean isAsc, int pageNo,
			int pageSize, Object value1, Object value2, Object value3, Object value4, Object value5, Object value6,
			Object value7, Object value8) throws DataAccessException {
		return getOrderedPagedNamedQuery(queryName, orderBy, isAsc, pageNo, pageSize, new Object[] { value1, value2,
				value3, value4, value5, value6, value7, value8 });
	}

	/**
	 * 查询分页排序数据
	 */
	public Page getOrderedPagedNamedQuery(String queryName, String orderBy, boolean isAsc, int pageNo,
			int pageSize, Object value1, Object value2, Object value3, Object value4, Object value5, Object value6,
			Object value7, Object value8, Object value9) throws DataAccessException {
		return getOrderedPagedNamedQuery(queryName, orderBy, isAsc, pageNo, pageSize, new Object[] { value1, value2,
				value3, value4, value5, value6, value7, value8, value9 });
	}

	/**
	 * 查询部分排序数据
	 */
	public Page getPagedNamedQuery(String queryName, int pageNo, int pageSize) throws DataAccessException {
		return getPagedNamedQuery(queryName, pageNo, pageSize, null);
	}

	/**
	 * 查询部分排序数据
	 */
	public Page getPagedNamedQuery(String queryName, int pageNo, int pageSize, final Object value1)
			throws DataAccessException {
		return getPagedNamedQuery(queryName, pageNo, pageSize, new Object[] { value1 });
	}

	/**
	 * 查询部分排序数据
	 */
	public Page getPagedNamedQuery(String queryName, int pageNo, int pageSize, final Object value1,
			final Object value2) throws DataAccessException {
		return getPagedNamedQuery(queryName, pageNo, pageSize, new Object[] { value1, value2 });
	}

	/**
	 * 查询部分排序数据
	 */
	public Page getPagedNamedQuery(String queryName, int pageNo, int pageSize, final Object value1,
			final Object value2, final Object value3) throws DataAccessException {
		return getPagedNamedQuery(queryName, pageNo, pageSize, new Object[] { value1, value2, value3 });
	}

	/**
	 * 查询部分排序数据
	 */
	public Page getPagedNamedQuery(String queryName, int pageNo, int pageSize, Object value1, Object value2,
			Object value3, Object value4) throws DataAccessException {
		return getPagedNamedQuery(queryName, pageNo, pageSize, new Object[] { value1, value2, value3, value4 });
	}

	/**
	 * 查询部分排序数据
	 */
	public Page getPagedNamedQuery(String queryName, int pageNo, int pageSize, Object value1, Object value2,
			Object value3, Object value4, Object value5) throws DataAccessException {
		return getPagedNamedQuery(queryName, pageNo, pageSize, new Object[] { value1, value2, value3, value4, value5 });
	}

	/**
	 * 查询部分排序数据
	 */
	public Page getPagedNamedQuery(String queryName, int pageNo, int pageSize, Object value1, Object value2,
			Object value3, Object value4, Object value5, Object value6) throws DataAccessException {
		return getPagedNamedQuery(queryName, pageNo, pageSize, new Object[] { value1, value2, value3, value4, value5,
				value6 });
	}

	/**
	 * 查询部分排序数据
	 */
	public Page getPagedNamedQuery(String queryName, int pageNo, int pageSize, Object value1, Object value2,
			Object value3, Object value4, Object value5, Object value6, Object value7) throws DataAccessException {
		return getPagedNamedQuery(queryName, pageNo, pageSize, new Object[] { value1, value2, value3, value4, value5,
				value6, value7 });
	}

	/**
	 * 查询部分排序数据
	 */
	public Page getPagedNamedQuery(String queryName, int pageNo, int pageSize, Object value1, Object value2,
			Object value3, Object value4, Object value5, Object value6, Object value7, Object value8)
			throws DataAccessException {
		return getPagedNamedQuery(queryName, pageNo, pageSize, new Object[] { value1, value2, value3, value4, value5,
				value6, value7, value8 });
	}

	/**
	 * 查询部分排序数据
	 */
	public Page getPagedNamedQuery(String queryName, int pageNo, int pageSize, Object value1, Object value2,
			Object value3, Object value4, Object value5, Object value6, Object value7, Object value8, Object value9)
			throws DataAccessException {
		return getPagedNamedQuery(queryName, pageNo, pageSize, new Object[] { value1, value2, value3, value4, value5,
				value6, value7, value8, value9 });
	}

	public int executeCustomNamedUpdate(String queryName, String cql) throws DataAccessException {
		return executeCustomNamedUpdate(queryName, cql, null);
	}

	public List getCustomLimitedNamedQuery(String queryName, String cql, int recordeSize) throws DataAccessException {
		return getCustomLimitedNamedQuery(queryName, cql, recordeSize, null);
	}

	public List getCustomNamedQuery(String queryName, String cql) throws DataAccessException {
		return getCustomNamedQuery(queryName, cql, null);
	}

	public Object getCustomNamedQueryObject(String queryName, String cql) throws DataAccessException {
		return getCustomNamedQueryObject(queryName, cql, null);
	}

	public List getCustomOrderedLimitedNamedQuery(String queryName, String cql, int recordeSize, String orderBy,
			boolean isAsc) throws DataAccessException {
		return getCustomOrderedLimitedNamedQuery(queryName, cql, recordeSize, orderBy, isAsc, null);
	}

	public Page getCustomOrderedPagedNamedQuery(String queryName, String cql, String orderBy, boolean isAsc,
			int pageNo, int pageSize) throws DataAccessException {
		return getCustomOrderedPagedNamedQuery(queryName, cql, orderBy, isAsc, pageNo, pageSize, null);
	}

	public Page getCustomPagedNamedQuery(String queryName, String cql, int pageNo, int pageSize)
			throws DataAccessException {
		return getCustomPagedNamedQuery(queryName, cql, pageNo, pageSize, null);
	}

	/**
	 * 去除hql的select 子句，未考虑union的情况,用于pagedQuery.
	 * 
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
		return hql.substring(beginPos);
	}

	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 * 
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
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
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		BaseQuery define =null;
		if(redisManager.objectHasKey(queryName)){
			define =(BaseQuery)redisManager.getOValue(queryName);
		}
		if (define == null) {
			log.debug(queryName + "-查询未定义");
		}
		return define;
	}


	/**
	 * 预备查询
	 * 
	 * @param queryName
	 * @param values
	 * @param types
	 * @return
	 */
	protected Query prepareQuery(final BaseQuery define, final Object... values) {
		Query queryObject = null;
		if (define != null) {
			if (define.getQueryType().equals("hql"))
				queryObject = createQuery(define.getQueryString());
			else
				queryObject = getSession().createSQLQuery(define.getQueryString());
		}
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;
	}

	/**
	 * 预备查询
	 * 
	 * @param queryName
	 * @param values
	 * @param types
	 * @return
	 */
	protected Query prepareQueryValues(final Query queryObject, final Object... values) {
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;
	}

	public int executeCustomNamedUpdate(String queryName, String cql, Object... values) throws DataAccessException {
		return executeNamedUpdateImpl(true, queryName, cql, values);
	}

	public List getCustomLimitedNamedQuery(String queryName, String cql, int recordeSize, Object... values)
			throws DataAccessException {
		return getLimitedNamedQueryImpl(true, queryName, cql, recordeSize, values);
	}

	public List getCustomNamedQuery(String queryName, String cql, Object... values) throws DataAccessException {
		return getNamedQueryImpl(true, queryName, cql, values);
	}

	public Object getCustomNamedQueryObject(String queryName, String cql, Object... values) throws DataAccessException {
		return getNamedQueryObjectImpl(true, queryName, cql, values);
	}

	public List getCustomOrderedLimitedNamedQuery(String queryName, String cql, int recordeSize, String orderBy,
			boolean isAsc, Object... values) throws DataAccessException {
		return getOrderedLimitedNamedQueryImpl(true, queryName, cql, recordeSize, orderBy, isAsc, values);
	}

	public Page getCustomOrderedPagedNamedQuery(String queryName, String cql, String orderBy, boolean isAsc,
			int pageNo, int pageSize, Object... values) throws DataAccessException {
		return getOrderedPagedNamedQueryImpl(true, queryName, cql, orderBy, isAsc, pageNo, pageSize, values);
	}

	public Page getCustomPagedNamedQuery(String queryName, String cql, int pageNo, int pageSize,
			Object... values) throws DataAccessException {
		return getPagedNamedQueryImpl(true, queryName, cql, pageNo, pageSize, values);
	}

	public int executeNamedUpdate(String queryName, Object... values) throws DataAccessException {
		return executeNamedUpdateImpl(false, queryName, "", values);
	}

	public List getLimitedNamedQuery(String queryName, int recordeSize, Object... values) throws DataAccessException {
		return getLimitedNamedQueryImpl(false, queryName, "", recordeSize, values);
	}

	public List getNamedQuery(String queryName, Object... values) throws DataAccessException {
		return getNamedQueryImpl(false, queryName, "", values);
	}

	public Object getNamedQueryObject(String queryName, Object... values) throws DataAccessException {
		return getNamedQueryObjectImpl(false, queryName, "", values);
	}

	public List getOrderedLimitedNamedQuery(String queryName, int recordeSize, String orderBy, boolean isAsc,
			Object... values) throws DataAccessException {
		return getOrderedLimitedNamedQueryImpl(false, queryName, "", recordeSize, orderBy, isAsc, values);
	}

	public Page getOrderedPagedNamedQuery(String queryName, String orderBy, boolean isAsc, int pageNo,
			int pageSize, Object... values) throws DataAccessException {
		return getOrderedPagedNamedQueryImpl(false, queryName, "", orderBy, isAsc, pageNo, pageSize, values);
	}

	public Page getPagedNamedQuery(String queryName, int pageNo, int pageSize, Object... values)
			throws DataAccessException {
		return getPagedNamedQueryImpl(false, queryName, "", pageNo, pageSize, values);
	}

	private String parseSql(String sql, Object... values) {
		String retval = "";
		
		return retval;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
