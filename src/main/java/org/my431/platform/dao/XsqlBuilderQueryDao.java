package org.my431.platform.dao;

import java.util.List;
import java.util.Map;

import org.my431.platform.dao.support.Page;
import org.springframework.stereotype.Service;

@Service
public interface XsqlBuilderQueryDao {
	@SuppressWarnings("rawtypes")
	List getNamedQuery(String queryName);
	
	@SuppressWarnings("rawtypes")
	List getNamedQuery(String queryName, Map values);
	
	@SuppressWarnings("rawtypes")
	Page getPagedNamedQuery(String queryName, int pageNo, int pageSize, Map values);
}
