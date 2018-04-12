package org.my431.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.DefaultRequestHelper;
import org.displaytag.util.ParamEncoder;

public class ParamUtils {

	public ParamUtils() {
	}

	public static String getParameter(HttpServletRequest request, String name) {
		return getParameter(request, name, false);
	}

	public static String getParameter(HttpServletRequest request, String name,
			String defaultStr) {
		String result = getParameter(request, name, false);
		if ("".equals(result))
			result = defaultStr;
		return result;
	}

	public static String getParameter(HttpServletRequest request, String name,
			boolean emptyStringsOK) {
		String temp = request.getParameter(name);
		if (temp != null) {
			if (temp.equals("") && !emptyStringsOK)
				return "";
			else
				return temp;
		} else {
			return "";
		}
	}

	public static boolean getBooleanParameter(HttpServletRequest request,
			String name) {
		return getBooleanParameter(request, name, false);
	}

	public static boolean getBooleanParameter(HttpServletRequest request,
			String name, boolean defaultVal) {
		String temp = request.getParameter(name);
		if ("true".equals(temp) || "on".equals(temp))
			return true;
		if ("false".equals(temp) || "off".equals(temp))
			return false;
		else
			return defaultVal;
	}

	public static int getIntParameter(HttpServletRequest request, String name,
			int defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			int num = defaultNum;
			try {
				num = Integer.parseInt(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	public static int[] getIntParameters(HttpServletRequest request,
			String name, int defaultNum) {
		String paramValues[] = request.getParameterValues(name);
		if (paramValues == null)
			return null;
		if (paramValues.length < 1)
			return new int[0];
		int values[] = new int[paramValues.length];
		for (int i = 0; i < paramValues.length; i++)
			try {
				values[i] = Integer.parseInt(paramValues[i]);
			} catch (Exception e) {
				values[i] = defaultNum;
			}

		return values;
	}

	public static double getDoubleParameter(HttpServletRequest request,
			String name, double defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			double num = defaultNum;
			try {
				num = Double.parseDouble(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	public static long getLongParameter(HttpServletRequest request,
			String name, long defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			long num = defaultNum;
			try {
				num = Long.parseLong(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	public static long[] getLongParameters(HttpServletRequest request,
			String name, long defaultNum) {
		String paramValues[] = request.getParameterValues(name);
		if (paramValues == null)
			return null;
		if (paramValues.length < 1)
			return new long[0];
		long values[] = new long[paramValues.length];
		for (int i = 0; i < paramValues.length; i++)
			try {
				values[i] = Long.parseLong(paramValues[i]);
			} catch (Exception e) {
				values[i] = defaultNum;
			}

		return values;
	}

	public static String getAttribute(HttpServletRequest request, String name,
			String defaultStr) {
		String result = getAttribute(request, name, false);
		if (result == null)
			return defaultStr;
		else
			return result;
	}

	public static String getAttribute(HttpServletRequest request, String name) {
		return getAttribute(request, name, false);
	}

	public static String getAttribute(HttpServletRequest request, String name,
			boolean emptyStringsOK) {
		String temp = (String) request.getAttribute(name);
		if (temp != null) {
			if (temp.equals("") && !emptyStringsOK)
				return null;
			else
				return temp;
		} else {
			return null;
		}
	}

	public static boolean getBooleanAttribute(HttpServletRequest request,
			String name) {
		String temp = (String) request.getAttribute(name);
		return temp != null && temp.equals("true");
	}

	public static int getIntAttribute(HttpServletRequest request, String name,
			int defaultNum) {
		String temp = (String) request.getAttribute(name);
		if (temp != null && !temp.equals("")) {
			int num = defaultNum;
			try {
				num = Integer.parseInt(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	public static long getLongAttribute(HttpServletRequest request,
			String name, long defaultNum) {
		String temp = (String) request.getAttribute(name);
		if (temp != null && !temp.equals("")) {
			long num = defaultNum;
			try {
				num = Long.parseLong(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	
	/**
	 * DisplayTag 获取起始记录数
	 * 
	 * @param request
	 * @param response
	 * @param tableId
	 *            标签id
	 * @param pageSize
	 *            每页记录数
	 * @return
	 */
	public static int getStartRecord(HttpServletRequest request, HttpServletResponse response, String tableId,
			int pageSize) {
		DefaultRequestHelper helper = new DefaultRequestHelper(request, response);
		String pageNo = helper.getParameter(getPageParameter(request, response, tableId));
		if (pageNo == null || pageNo.equals("")) {
			pageNo = "1";
		}
		int startRecord = (Integer.parseInt(pageNo) - 1) * pageSize;
		return startRecord;
	}

	/**
	 * DisplayTag 获取页码参数
	 * 
	 * @param request
	 * @param response
	 * @param tableId
	 *            DisplayTag 标签id
	 * @return
	 */
	public static int getPageNo(HttpServletRequest request, HttpServletResponse response, String tableId) {
		DefaultRequestHelper helper = new DefaultRequestHelper(request, response);
		String pageNo = helper.getParameter(getPageParameter(request, response, tableId));
		if (pageNo == null || pageNo.equals("")) {
			pageNo = "1";
		}
		return Integer.valueOf(pageNo);
	}

	/**
	 * DisplayTag 获取排序属性,升序/降序
	 * 
	 * @param request
	 * @param response
	 * @param tableId
	 *            DisplayTag 标签id
	 * @return
	 */
	public static boolean getTableOrder(HttpServletRequest request, HttpServletResponse response, String tableId) {
		return (getTableOrderString(request,response,tableId).equals("2") ? true : false);
	}
	
	/**
	 * DisplayTag 获取排序属性,升序/降序
	 * 
	 * @param request
	 * @param response
	 * @param tableId
	 *            DisplayTag 标签id
	 * @return
	 */
	public static String getTableOrderString(HttpServletRequest request, HttpServletResponse response, String tableId) {
		DefaultRequestHelper helper = new DefaultRequestHelper(request, response);
		String tableOrder = helper.getParameter(getOrderParameter(request, response, tableId));
		if (tableOrder == null || tableOrder.equals("")) {
			tableOrder = "2";
		}

		return tableOrder;
	}

	/**
	 * DisplayTag 获取排序字段,默认返回当前排序column的sortName属性,如未指定column的sortName属性,将返回column的列序号
	 * 
	 * @param request
	 * @param response
	 * @param tableId
	 *            DisplayTag 标签id
	 * @return
	 */
	public static String getTableSort(HttpServletRequest request, HttpServletResponse response, String tableId) {
		DefaultRequestHelper helper = new DefaultRequestHelper(request, response);
		String tableSort = helper.getParameter(getSortParameter(request, response, tableId));
		if (tableSort == null || tableSort.equals("")) {
			tableSort = "";
		}

		return tableSort;
	}

	/**
	 * DisplayTag 获取排序参数名称
	 * 
	 * @param request
	 * @param response
	 * @param tableId
	 *            DisplayTag 标签id
	 * @return
	 */
	public static String getSortParameter(HttpServletRequest request, HttpServletResponse response, String tableId) {
		ParamEncoder encoder = new ParamEncoder(tableId);

		return encoder.encodeParameterName(TableTagParameters.PARAMETER_SORT);
	}

	/**
	 * DisplayTag 获取排序属性参数名称
	 * 
	 * @param request
	 * @param response
	 * @param tableId
	 *            DisplayTag 标签id
	 * @return
	 */
	public static String getOrderParameter(HttpServletRequest request, HttpServletResponse response, String tableId) {
		ParamEncoder encoder = new ParamEncoder(tableId);

		return encoder.encodeParameterName(TableTagParameters.PARAMETER_ORDER);
	}

	/**
	 * DisplayTag 获取分页参数名称
	 * 
	 * @param request
	 * @param response
	 * @param tableId
	 *            DisplayTag 标签id
	 * @return
	 */
	public static String getPageParameter(HttpServletRequest request, HttpServletResponse response, String tableId) {
		ParamEncoder encoder = new ParamEncoder(tableId);

		return encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE);
	}
}