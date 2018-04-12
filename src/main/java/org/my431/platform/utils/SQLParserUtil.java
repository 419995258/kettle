package org.my431.platform.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.my431.util.StringUtil;


public class SQLParserUtil {
	private static Log log = LogFactory.getLog(SQLParserUtil.class);
	
	/**
	 * SQL条件语句解析，返回处理后的SQL（过滤掉空值参数的条件语句）和新的参数数组（过滤掉空值条件）
	 * values空值规则：String -> ""; Integer -> -1;
	 * @param sql
	 * @param values
	 * @return Map, key1:sql, key2:values
	 */
	public static Map<String, Object> parseSQL(String sql, Object... values) {
		Map<String, Object> map = new HashMap<String, Object>();
		//转换sql为小写字符
		String strSQL = sql.trim().toLowerCase();
		
		//可变参数为空或者sql中没有where子句，则直接返回原sql
		if (values == null || strSQL.indexOf("where") == -1) {
			map.put("sql", sql);
			map.put("values", values);
			return map;
		}
		
		
		//可变参数实际参数个数
		int counter = values.length;
		//可变参数非空参数个数
		int counterEmpty = 0;
		//sql中?个数
		int counterSQL = 0;
				
		//获取sql语句中?个数
		if (strSQL.indexOf("?") != -1) {
			String[] tempArray = strSQL.split("\\?");
			if(strSQL.endsWith("?")) {
				counterSQL = tempArray.length;
			} else {
				counterSQL = tempArray.length - 1;
			}
		}
		
		//获取参数values中的空值，并存储到clauseToDealArray[]
		String[] clauseToDealArray = new String[counter];
		for (int i=0; i<values.length; i++) {
			Object obj = values[i];
			if (obj instanceof String) {
				//String 空值参数：""
				if (((String)obj).equals("")) {
					clauseToDealArray[i] = ""; //标明对应参数为空，sql条件子句待replace
					counterEmpty++;
				} else {
					clauseToDealArray[i] = "#";//无须处理
				}
			} else if (obj instanceof Integer) {
				//Integer 空值参数：-1
				if (((Integer)obj).intValue() == -1) {
					clauseToDealArray[i] = "";//标明对应参数为空，sql条件子句待replace
					counterEmpty++;
				} else {
					clauseToDealArray[i] = "#";//无须处理
				}
			}	
		}
		
		//命名查询参数个数不匹配，则直接返回sql，并打印错误日志
		if (counter != counterSQL) {
			log.error(">>>>命名查询参数个数不匹配：" +sql);
			map.put("sql", sql);
			map.put("values", values);
			return map;
		}
		
		//可变参数全部不为空，则直接返回原sql
		if(counterEmpty == 0) {
			map.put("sql", sql);
			map.put("values", values);
			return map;
		}
		
		//Object[]构造values返回值->vals
		Object[] vals = new Object[counter-counterEmpty];
		int j = 0;
		for (int i=0; i<counter; i++) {
			 if (clauseToDealArray[i] == "#" ){
				 vals[j] = values[i];
				 j++;
			 }
		}
		
		//临时变量:tempSQL
		String tempSQL = strSQL;
		String groupBySQL = ""; //包含order by子句
		String orderBySQL = "";
		
		//截取group by, order by子句
		if (strSQL.indexOf("group by") != -1) {//获取group by子句
			groupBySQL = strSQL.split("group by")[1];
			tempSQL = strSQL.split("group by")[0]; //剥离group by子句
		} else if (strSQL.indexOf("order by") != -1) { //获取order by子句
			orderBySQL = strSQL.split("order by")[1];
			tempSQL = strSQL.split("order by")[0]; //剥离order by子句
		}
		
		//获取select, where子句
		String selectSQL = tempSQL.split("where")[0];
		String whereSQL = " 1=1 and " +tempSQL.split("where")[1]; //便于解析，where后面添加默认条件1=1
		//将制表符换行符替换成空格
		whereSQL = StringUtil.replaceBlank(whereSQL);
		//便于替换，替换后保留sql中的and
		whereSQL = whereSQL.replaceAll(" and ", " @@and "); //@@在and之前, and前后保留空格，防止误替换
		
		//符号@@作为sql条件分割符
		String[] clauses = whereSQL.split("\\@@");
		
		//StringBuffer构造sql语句返回值
		StringBuffer sb = new StringBuffer();
		sb.append(selectSQL + " where "); 
				
		//子句处理
		/**
		 *  1 = 1
		 *  and m.column_id = c.column_id
		 *  and c.column_code = ?
		 *  and m.article_id = a.article_id
		 *  and a.source_node_id = ?
		 */
		
		//参数数组下标
		int k = 0;
		for (int i=0; i<clauses.length; i++) {
			if(!clauses[i].trim().endsWith("?")) {//固定条件，保留该条件
				sb.append(clauses[i]);
				//参数数组下标不变
			} else if (clauseToDealArray[k] == "" ) {//参数化条件,且参数为空，从sql中剔除该条件
				//参数数组下标+1
				k++; 
			} else {//参数化条件,参数不为空，保留该条件
				sb.append(clauses[i]);
				//参数数组下标+1
				k++; 
			}
		}
		
		//append group by, order by子句
		if (strSQL.indexOf("group by") != -1) {
			sb.append("group by " +groupBySQL);
		} else if (strSQL.indexOf("order by") != -1) {
			sb.append("order by " +orderBySQL);
		}
		
		//返回结果设置
		map.put("sql", sb.toString());
		map.put("values", vals);
		
		return map;
	}
	
	
	public static void main(String[] args) {
		//String basesql = "select a.article_id as id, a.title as title, a.cre_time as publishDate, c.column_id as columnId, c.column_code as columnCode from CMS_ARTICLE a, CMS_COLUMN_ARTICLE_MAP m, CMS_COLUMN c ";
		//String wheresql1 = " c.column_code = ? and a.source_node_id = ? and m.column_id = c.column_id and m.article_id = a.article_id";
		//String wheresql2 = " m.column_id = c.column_id and m.article_id = a.article_id and c.column_code = ? and a.source_node_id = ?";
		//String wheresql3 = " m.column_id = c.column_id and c.column_code = ?  and m.article_id = a.article_id and a.source_node_id = ?";
		//String ordersql = " order by a.cre_time desc";
		
		//wheresql3 = wheresql3.replace("?", "?#");
		//String[] clauses = wheresql1.split("\\#");
		
		//wheresql3 = wheresql3.replace(" and ", " @and ");
		//String[] clauses = wheresql3.split("\\@");
		//System.out.println(">>>>size:" +clauses.length);
		
		
		//String sql = "select a.article_id as id, a.title as title, a.cre_time as publishDate, c.column_id as columnId, c.column_code as columnCode from CMS_ARTICLE a, CMS_COLUMN_ARTICLE_MAP m, CMS_COLUMN c where (1=1 and 2=2) and m.column_id = c.column_id and c.column_code = ?  and m.article_id = a.article_id and a.source_node_id = ? group by column_code order by a.cre_time desc";
		//String sql = "select a.article_id as id, a.title as title, a.cre_time as publishDate, c.column_id as columnId, c.column_code as columnCode from CMS_ARTICLE a, CMS_COLUMN_ARTICLE_MAP m, CMS_COLUMN c where (1=1 and 2=2) and m.column_id = c.column_id and c.column_code = ?  and m.article_id = a.article_id and a.source_node_id = ? order by a.cre_time desc";
		//String sql = "select * from emp where name like ? and age>18 and title = ? and sal > 1500 and comm is not null";
		String sql = "select a.article_id id, a.title, to_char(a.cre_time,'YYYY-MM-DD HH24:MI:SS') publishDate, c.column_id, c.column_code, a.source_node_id site_id from cms_article a, cms_column_article_map m, cms_column c where c.column_code = ? and a.source_node_id = ? and m.column_id = c.column_id and m.article_id = a.article_id order by a.cre_time desc";
		
		Map<String, Object> map1 = SQLParserUtil.parseSQL(sql, "002006", "sgcc");
		System.out.println("sql1:" +(String)map1.get("sql"));
		System.out.println("values1.length:" +((Object[])map1.get("values")).length);
		
		Map<String, Object> map2 = SQLParserUtil.parseSQL(sql, "002006", "");
		System.out.println("sql2:" +(String)map2.get("sql"));
		System.out.println("values2.length:" +((Object[])map2.get("values")).length);
		
		Map<String, Object> map3 = SQLParserUtil.parseSQL(sql, "", "sgcc");
		System.out.println("sql3:" +(String)map3.get("sql"));
		System.out.println("values3.length:" +((Object[])map3.get("values")).length);
		
		Map<String, Object> map4 = SQLParserUtil.parseSQL(sql, "", "");
		System.out.println("sql4:" +(String)map4.get("sql"));
		System.out.println("values4.length:" +((Object[])map4.get("values")).length);
		
		
		String likeSQL = "select t.user_id as userId, t.realname as realName, t.login_name as loginName, t.email as email, t.status as status from core_user t where 1=1 and t.status=? and t.realname like ? order by t.cre_time desc";
		Map<String, Object> likeMap1 = SQLParserUtil.parseSQL(likeSQL, "", "");
		System.out.println("likeSQL1:" +(String)likeMap1.get("sql"));
		
		Map<String, Object> likeMap2 = SQLParserUtil.parseSQL(likeSQL, "", "%单%");
		System.out.println("likeSQL2:" +(String)likeMap2.get("sql"));
		
		Map<String, Object> likeMap3 = SQLParserUtil.parseSQL(likeSQL, "1", "");
		System.out.println("likeSQL3:" +(String)likeMap3.get("sql"));
		
		Map<String, Object> likeMap4 = SQLParserUtil.parseSQL(likeSQL, "1", "%单%");
		System.out.println("likeSQL4:" +(String)likeMap4.get("sql"));
		
		
		//不能使用between,因为参数必须成对
		//不能使用 where t.cre_time < to_char(?,'YYYY-MM-DD HH24:MI:SS')，应该采用where to_char(t.cre_time,'YYYY-MM-DD HH24:MI:SS') > ?
		//String dateSQL1 = "select t.user_id as userId, t.realname as realName, t.login_name as loginName, t.email as email, t.status as status from core_user t where t.cre_time between ? and ?";
		String dateSQL = "select t.user_id, t.realname, t.login_name, t.email, t.status, to_char(t.cre_time,'YYYY-MM-DD HH24:MI:SS') creTime from core_user t where to_char(t.cre_time,'YYYY-MM-DD HH24:MI:SS') > ? and to_char(t.cre_time, 'YYYY-MM-DD HH24:MI:SS') < ?";
		Map<String, Object> dateMap1 = SQLParserUtil.parseSQL(dateSQL, "", "");
		System.out.println("dateSQL1:" +(String)dateMap1.get("sql"));
		
		Map<String, Object> dateMap2 = SQLParserUtil.parseSQL(dateSQL, "2012-06-18", "");
		System.out.println("dateSQL2:" +(String)dateMap2.get("sql"));
		
		Map<String, Object> dateMap3 = SQLParserUtil.parseSQL(dateSQL, "", "2012-08-18");
		System.out.println("dateSQL3:" +(String)dateMap3.get("sql"));
		
		Map<String, Object> dateMap4 = SQLParserUtil.parseSQL(dateSQL, "2012-06-18", "2012-08-18");
		System.out.println("dateSQL4:" +(String)dateMap4.get("sql"));
	}
}
