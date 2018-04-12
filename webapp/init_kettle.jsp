<%@page import="org.my431.base.services.*"%>
<%@page import="org.my431.platform.utils.ContextUtils"%>
<%@page import="org.my431.plugin.redis.services.RedisManager"%>
<%@page import="org.my431.base.model.*"%>
<%@page import="java.util.*"%>
<%@page import="org.my431.platform.web.*"%>
<%@page import="org.my431.platform.dao.support.Page"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
BaseSchoolManager baseSchoolManager=(BaseSchoolManager)ContextUtils.getBean("baseSchoolManager");
baseSchoolManager.updateNationData01();
%>