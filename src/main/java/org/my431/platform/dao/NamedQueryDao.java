package org.my431.platform.dao;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.my431.platform.dao.support.Page;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * 命名查询接口类
 * 
 * @author blizzard
 * 
 */

@Service
public interface NamedQueryDao {

	List getNamedQuery(String queryName) throws DataAccessException;
	
	/**
	 * 命名查询获取对象列表
	 * 
	 * @param queryName
	 *            命名查询名称
	 * @param values
	 * @return
	 * @throws DataAccessException
	 */
	List getNamedQuery(String queryName, Object... values) throws DataAccessException;

	/**
	 * 根据命名查询获取分页对象列表
	 * 
	 * @param queryName
	 *            命名查询名称
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页记录数
	 * @param values
	 * @return
	 * @throws DataAccessException
	 */
	Page getPagedNamedQuery(String queryName, int pageNo, int pageSize, Object... values)
			throws DataAccessException;

	/**
	 * 根据命名查询获取分页对象列表
	 * 
	 * @param queryName
	 *            命名查询名称
	 * @param orderBy
	 *            排序字段
	 * @param isAsc
	 *            是否升序
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页记录数
	 * @param values
	 * @return
	 * @throws DataAccessException
	 */
	Page getOrderedPagedNamedQuery(String queryName, String orderBy, boolean isAsc, int pageNo, int pageSize,
			Object... values) throws DataAccessException;

	/**
	 * 根据命名查询获取部分对象列表
	 * 
	 * @param queryName
	 *            命名查询名称
	 * @param recordSize
	 *            记录数
	 * @param values
	 * @return
	 * @throws DataAccessException
	 */
	List getLimitedNamedQuery(String queryName, int recordSize, Object... values) throws DataAccessException;

	/**
	 * 根据命名查询获取部分对象列表
	 * 
	 * @param queryName
	 *            命名查询名称
	 * @param recordSize
	 *            记录数
	 * @param orderBy
	 *            排序字段
	 * @param isAsc
	 *            是否升序
	 * @param values
	 * @return
	 * @throws DataAccessException
	 */
	List getOrderedLimitedNamedQuery(String queryName, int recordSize, String orderBy, boolean isAsc, Object... values)
			throws DataAccessException;
	
	

	/**
	 * 查询单一对象
	 * 
	 * @param queryName
	 *            命名查询名称
	 * @param values
	 * @return
	 * @throws DataAccessException
	 */
	Object getNamedQueryObject(String queryName, Object... values) throws DataAccessException;

	

	/**
	 * 根据命名查询执行更新操作
	 * 
	 * @param queryName
	 *            命名查询名称
	 * @return
	 * @throws DataAccessException
	 */
	int executeNamedUpdate(String queryName, Object... values) throws DataAccessException;

	
	
	/**
	 * 根据命名查询自定义sql获取对象
	 * 
	 * @param queryName
	 *            命名查询名称
	 * @param values
	 * @return
	 * @throws DataAccessException
	 */
	Object getCustomNamedQueryObject(String queryName, String cql, Object... values) throws DataAccessException;

	/**
	 * 根据命名查询自定义sql获取对象列表
	 * 
	 * @param queryName
	 *            命名查询名称
	 * @param values
	 * @return
	 * @throws DataAccessException
	 */
	List getCustomNamedQuery(String queryName, String cql, Object... values) throws DataAccessException;

	/**
	 * 根据命名查询自定义sql获取分页对象列表
	 * 
	 * @param queryName
	 *            命名查询名称
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页记录数
	 * @param values
	 * @return
	 * @throws DataAccessException
	 */
	Page getCustomPagedNamedQuery(String queryName, String cql, int pageNo, int pageSize, Object... values)
			throws DataAccessException;

	/**
	 * 根据命名查询自定义sql获取分页对象列表
	 * 
	 * @param queryName
	 *            命名查询名称
	 * @param orderBy
	 *            排序字段
	 * @param isAsc
	 *            是否升序
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页记录数
	 * @param values
	 * @return
	 * @throws DataAccessException
	 */
	Page getCustomOrderedPagedNamedQuery(String queryName, String cql, String orderBy, boolean isAsc,
			int pageNo, int pageSize, Object... values) throws DataAccessException;

	/**
	 * 根据命名查询自定义sql获取部分对象列表
	 * 
	 * @param queryName
	 *            命名查询名称
	 * @param recordeSize
	 *            记录数
	 * @param values
	 * @return
	 * @throws DataAccessException
	 */
	List getCustomLimitedNamedQuery(String queryName, String cql, int recordeSize, Object... values)
			throws DataAccessException;

	/**
	 * 根据命名查询自定义sql获取部分对象列表
	 * 
	 * @param queryName
	 *            命名查询名称
	 * @param recordeSize
	 *            记录数
	 * @param orderBy
	 *            排序字段
	 * @param isAsc
	 *            是否升序
	 * @param values
	 * @return
	 * @throws DataAccessException
	 */
	List getCustomOrderedLimitedNamedQuery(String queryName, String cql, int recordeSize, String orderBy,
			boolean isAsc, Object... values) throws DataAccessException;

	/**
	 * 根据命名查询自定义sql执行更新操作
	 * 
	 * @param queryName
	 *            命名查询名称
	 * @return
	 * @throws DataAccessException
	 */
	int executeCustomNamedUpdate(String queryName, String cql, Object... values) throws DataAccessException;

}
