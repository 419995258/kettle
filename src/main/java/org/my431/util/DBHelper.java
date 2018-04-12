
/**    
* @Title: DBHelper.java  
* @Package org.my431.util  
* @Description: TODO(用一句话描述该文件做什么)  
* @author hyl     
* @date 2017-7-18 上午9:50:17  
* @version V1.0 
* @author user   
*/  

package org.my431.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;




/**  
 * @author hyl  
 */
public class DBHelper {
	private static Logger logger =  Logger.getLogger(DBHelper.class);
	//private static DataSource dataSource;
	private static BasicDataSource dataSourceVPN = new BasicDataSource();
	private static BasicDataSource dataSource2 = new BasicDataSource();
    static {
        try {
            //Class.forName(DRIVER_CLASS_NAME); // 1.加载驱动
            //Properties props = new Properties();
            //props.load(DBHelper.class.getClassLoader().getResourceAsStream("db.properties"));

            //使用连接池技术, 数据源DBCP
            //dataSource = BasicDataSourceFactory.createDataSource(props);
        	dataSourceVPN.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        	dataSourceVPN.setUsername("gansu_jsfzjc");
        	dataSourceVPN.setPassword("gs_jsfzjc");
        	dataSourceVPN.setUrl("jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.180.245.6)(PORT = 1521))(CONNECT_DATA = (SERVER = DEDICATED)(SERVICE_NAME = JSGLRAC)))");
    	    
        	dataSource2.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        	dataSource2.setUsername("tim_mis_base");
        	dataSource2.setPassword("love431");
        	dataSource2.setUrl("jdbc:oracle:thin:@192.168.0.55:1521:orcl");
        	
        	//System.out.println(dataSource.getMaxIdle());// 最大空闲时间。如果一个用户获取一个连接，不用，多长时间会被强行收回
            //System.out.println(dataSource.getMaxWait());// 在抛出异常之前,池等待连接被回收的最长时间（当没有可用连接时）。设置为-1表示无限等待。
            //System.out.println(dataSource.getInitialSize());// 初始化时有几个Connection
            //System.out.println(dataSource.getMaxTotal());// 最多能有多少个Connection

            // pool.setMaxTotal(20);//可以我们自己设置池的相关参数，如最大连接数

            System.out.println("加载数据库属性元素构建数据源成功...");
        } catch (Exception e) {
            logger.error("加载数据库属性元素构建数据源！！！", e);
        }
    }
    /**
     * 建立连接
     * 
     * @return
     */
    public static Connection getConnVPN() {
        Connection con = null;
        try {
            // 2.建立与数据库的 连接
            //使用连接池技术, 数据源DBCP
            con = dataSourceVPN.getConnection();
            //System.out.println("数据库连接成功...");
        } catch (Exception e) {
            logger.error("数据库连接失败-getConnVPN！！！", e);
        }
        return con;
    }
    /**
     * 建立连接
     * 
     * @return
     */
    public static Connection getConn2() {
        Connection con = null;
        try {
            // 2.建立与数据库的 连接
            //使用连接池技术, 数据源DBCP
            con = dataSource2.getConnection();
            //System.out.println("数据库连接成功...");
        } catch (Exception e) {
            logger.error("数据库连接失败-getConn2！！！", e);
        }
        return con;
    }
    /**
     * 关闭操作
     * 
     * @param con
     *            数据库连接
     * @param st
     *            sql执行工具
     * @param rs
     *            返回结果集
     */
    public static void close(Connection con, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                //System.out.println("关闭结果集完成...");
            } catch (SQLException e) {
                logger.error("关闭结果集失败！！！", e);
            }
        }
        if (st != null) {
            try {
                st.close();
                //System.out.println("关闭执行工具完成...");
            } catch (SQLException e) {
                logger.error("关闭执行工具失败！！！", e);
            }
        }

        if (con != null) {
            try {
                con.close();
                //System.out.println("关闭数据库连接完成...");
            } catch (SQLException e) {
                logger.error("关闭数据库连接失败！！！", e);
            }
        }
    }

    /**
     * 
     * @param sql
     *            要执行的sql语句 insert, update, delete)
     * @param params
     *            执行sql语句需要的参数
     * @return 执行sql语句受影响的行数
     */
    public static int doUpdate_1(String sql, Object... params) {
        Connection con = null;
        PreparedStatement st = null;
        int result = 0;
        try {
            con = getConn2();
            System.out.println("要执行sql语句：" + sql);
            st = con.prepareStatement(sql);
            setParams(st, params); // 设置参数
            System.out.println("sql执行工具创建成功...");
        } catch (SQLException e) {
            logger.error("sql执行工具创建失败！！！", e);
        }

        try {
            result = st.executeUpdate(); // 执行sql , 针对insert, delete,
                                                // update, 返回结果是受影响行数
            System.out.println("插入数据成功，插入数据的条数是：：" + result);
        } catch (SQLException e) {
            logger.error("插入数据失败!!!", e);
        }finally{
            // 5.关闭连接
            DBHelper.close(con, st, null);
        }
        return result;
    }
    /**
     * 
     * @param sql  要执行的sql语句
     * @return 
     */
    public static List<Map<String, Object>> doQuery(String sql,Connection con){
        //Connection con = null;
        Statement st = null;
        ResultSet rs = null;        
        List<Map<String, Object>> results = null;
        try {
            //con = getConn();
            st = con.createStatement();
            //System.out.println("sql执行工具创建成功...");
        } catch (SQLException e) {
            logger.error("sql执行工具创建失败！！！", e);
        }

        try {
            rs = st.executeQuery(sql);
            //System.out.println("执行sql取到返回数据成功...");
        } catch (SQLException e) {
            logger.error("执行sql取到返回数据失败！！！", e);
        }

        try {
            ResultSetMetaData rsmd = rs.getMetaData(); // 元数据; 对象取取到的结果集数据的描述
            int cloumCount = rsmd.getColumnCount();
            results = new ArrayList<Map<String, Object>>();
            while (rs.next()) { // 判断结果集是否还有数据 (数据是一条记录的方式取出)
                Map<String, Object> record = new HashMap<String, Object>();
                for (int i = 1; i <= cloumCount; i++) {
                    //rsmd.getColumnName(i) ：表的字段名或字段别名
                    //rs.getObject(i) : 取到字段对应的值
                    record.put(rsmd.getColumnName(i).toUpperCase(), rs.getObject(i));
                }
                results.add(record);
            }
            //System.out.println("取出结果集数据完成...");
        } catch (SQLException e) {
            logger.error("取出结果集数据失败！！！", e);
        } finally {
            DBHelper.close(con, st, rs);
        }
        return results;
    }
    /**
     * 
     * @param sql  要执行的sql语句
     * @param objs 执行sql语句需要的参数
     * @return  取出数据库的数据,每一条记录是一个map : key是字段名或字段别名(小写字母), value应对字段的值
     */
    public static List<Map<String, Object>> doQuery_1(String sql, Object...objs){
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;        
        List<Map<String, Object>> results = null;
        try {
            con = getConn2();
            st = con.prepareStatement(sql); // 3.sql执行工具
            setParams(st, objs);
            System.out.println("sql执行工具创建成功...");
        } catch (SQLException e) {
            logger.error("sql执行工具创建失败！！！", e);
        }

        try {
            rs = st.executeQuery(); // 4.执行sql取到返回数据白结果集
            System.out.println("执行sql取到返回数据成功...");
        } catch (SQLException e) {
            logger.error("执行sql取到返回数据失败！！！", e);
        }

        try {
            ResultSetMetaData rsmd = rs.getMetaData(); // 元数据; 对象取取到的结果集数据的描述
            int cloumCount = rsmd.getColumnCount();
            results = new ArrayList<Map<String, Object>>();
            while (rs.next()) { // 判断结果集是否还有数据 (数据是一条记录的方式取出)
                Map<String, Object> record = new HashMap<String, Object>();
                for (int i = 1; i <= cloumCount; i++) {
                    //rsmd.getColumnName(i) ：表的字段名或字段别名
                    //rs.getObject(i) : 取到字段对应的值
                    record.put(rsmd.getColumnName(i).toLowerCase(), rs.getObject(i));
                }
                results.add(record);
            }
            System.out.println("取出结果集数据完成...");
        } catch (SQLException e) {
            logger.error("取出结果集数据失败！！！", e);
        } finally {
            DBHelper.close(con, st, rs);
        }
        return results;
    }
    /**
     * 
     * @param sql  要执行的sql语句
     * @return  
     */
    public static void doUpdate(String sql,Connection con){
        //Connection con = null;
        Statement statement = null;
        try {
        	//con = getConn();
        	statement = con.createStatement();
        	statement.execute(sql);
            System.out.println("执行update sql成功..."+sql);
        } catch (SQLException e) {
            logger.error("执行update sql失败！！！", e);
        }finally{
        	 DBHelper.close(con, statement, null);
        }
    }
    /**
     * 
     * @param sql  要执行的sql语句
     */
    public static Map<String, Object> doQueryOne(String sql,Connection con){
        //Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Map<String, Object> results = null;
        try {
            //con = getConn();
            st = con.createStatement();
            //System.out.println("sql执行工具创建成功...");
        } catch (SQLException e) {
            logger.error("sql执行工具创建失败！！！", e);
        }

        try {
            rs = st.executeQuery(sql); // 4.执行sql取到返回数据白结果集
            //System.out.println("执行sql取到返回数据成功...");
        } catch (SQLException e) {
            logger.error("执行sql取到返回数据失败！！！", e);
        }

        try {
            ResultSetMetaData rsmd = rs.getMetaData(); // 元数据; 对象取取到的结果集数据的描述
            int cloumCount = rsmd.getColumnCount();

            if (rs.next()) { // 判断结果集是否还有数据 (数据是一条记录的方式取出)
                results = new HashMap<String, Object>();
                for (int i = 1; i <= cloumCount; i++) {
                    //rsmd.getColumnName(i) ：表的字段名或字段别名
                    //rs.getObject(i) : 取到字段对应的值
                    results.put(rsmd.getColumnName(i).toUpperCase(), rs.getObject(i));
                }
            }
            //System.out.println("取出结果集数据完成...");
        } catch (SQLException e) {
            logger.error("取出结果集数据失败！！！", e);
        } finally {
            DBHelper.close(con, st, rs);
        }
        return results;
    }
    /**
     * 
     * @param sql  要执行的sql语句
     * @param objs 执行sql语句需要的参数
     * @return  取出数据库的数据, key是字段名或字段别名(大写字母), value应对字段的值
     */
    public static Map<String, Object> doQueryOne(String sql, Object...objs){
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Map<String, Object> results = null;
        try {
            con = getConn2();
            st = con.prepareStatement(sql); // 3.sql执行工具
            setParams(st, objs);
            System.out.println("sql执行工具创建成功...");
        } catch (SQLException e) {
            logger.error("sql执行工具创建失败！！！", e);
        }

        try {
            rs = st.executeQuery(); // 4.执行sql取到返回数据白结果集
            System.out.println("执行sql取到返回数据成功...");
        } catch (SQLException e) {
            logger.error("执行sql取到返回数据失败！！！", e);
        }

        try {
            ResultSetMetaData rsmd = rs.getMetaData(); // 元数据; 对象取取到的结果集数据的描述
            int cloumCount = rsmd.getColumnCount();

            if (rs.next()) { // 判断结果集是否还有数据 (数据是一条记录的方式取出)
                results = new HashMap<String, Object>();
                for (int i = 1; i <= cloumCount; i++) {
                    //rsmd.getColumnName(i) ：表的字段名或字段别名
                    //rs.getObject(i) : 取到字段对应的值
                    results.put(rsmd.getColumnName(i).toUpperCase(), rs.getObject(i));
                }
            }
            System.out.println("取出结果集数据完成...");
        } catch (SQLException e) {
            logger.error("取出结果集数据失败！！！", e);
        } finally {
            DBHelper.close(con, st, rs);
        }
        return results;
    }

    private static void setParams(PreparedStatement st, Object... objs) {
        // 判断是否有参数
        if (objs == null || objs.length == 0) {
            return;
        }
        int flag = 0;
        try {
            for (int i = 0; i < objs.length; i++) {
                flag = i + 1;
            	String paramType = objs[i].getClass().getName(); // 获得参数的类型
            	if (Integer.class.getName().equals(paramType)) { // 判断是否是int类型
            		st.setInt(i + 1,Integer.parseInt(objs[i].toString()));
            	} else if (Double.class.getName().equals(paramType)) { // 判断是否是dobule类型
            		st.setDouble(i + 1, Double.parseDouble(objs[i].toString()));
            	} else if (String.class.getName().equals(paramType)) { // 判断是否是string类型
            		st.setString(i + 1, (String) objs[i]);
            	} else {
            		st.setObject(i + 1, objs[i]);
            	}
            }
        } catch (SQLException e) {
            logger.error(String.format("注入第%d值时失败!!!", flag),e);
        }
    }
    /**
     * 
    * @Description: 插入数据  
    * @author hyl     
     */
    public void insertDataV2(Map<String, Object> columValues, String tablesName, Connection con) {
		String sql = "insert into " + tablesName + "(";
		for (String colums : columValues.keySet()) {
			sql = sql + colums + ",";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql = sql + ") values(";
		for (Map.Entry<String, Object> entry : columValues.entrySet()) {
			String key  =  entry.getKey().toString();
			String value = "";
			if (entry.getValue() != null && !"".equals(entry.getValue().toString())) {
				value =   entry.getValue().toString();
			}
			if (key.equals("CJSJ") || key.equals("XGSJ")) {
				sql = sql + "to_timestamp('"+value.toString()+"','yyyy-MM-dd hh24:mi:ss.ff6')" + ",";
			}else {
				sql = sql + "'"+value + "',";
			}
		}
		/*for (Object value : columValues.values()) {
			if(value.toString().contains("to_date(")){
				sql = sql + value + ",";
			}if(value.toString().contains("to_timestamp(")){
				sql = sql + value + ",";
			}if(value.toString().equals("CJSJ") || value.toString().equals("XGSJ")){
				sql = sql + "to_timestamp('"+value.toString()+"','yyyy-MM-dd hh24:mi:ss.ff6')" + ",";
			}else if (value.equals("sysdate")) {
				sql = sql + value + ",";
			} else {
				sql = sql + "'"+value + "',";
			}
		}*/
		sql = sql.substring(0, sql.length() - 1);
		sql = sql + ")";
		System.out.println("update sql 为："+sql);
		Statement statement = null;
		try {
        	statement = con.createStatement();
        	statement.execute(sql);
            System.out.println("执行update sql成功...");
	    } catch (SQLException e) {
	            logger.error("执行update sql失败！！！", e);
	    }finally{
	        	 DBHelper.close(con, statement, null);
	   }
		
	}
    
    public static void main(String[] args) {
    	//添加数据到BASE_TABLE_UPDATE_LOG表
    	doUpdate("delete from BASE_TABLE_UPDATE_LOG", getConn2());
    	saveBaseTableUpdateLogV1();
    	saveBaseTableUpdateLogV2();
    	//DBHelper dbHelper = new DBHelper();
    	//dbHelper.updateVPNDataToLocal();
	}
    /**
     * 
    * @Description: 连接vpn同步最新数据到本地  
    * @author hyl     
     */
    public  void updateVPNDataToLocal() {
    	List<String> list = new ArrayList<String>();
		list.add("TB_BIZ_JSDQZCXX");
		list.add("TB_BIZ_JSSCZCXX");
		list.add("TB_BIZ_JSZGXX");
		list.add("TB_BIZ_JZGGJBZHHYBZXX"); 
		list.add("TB_BIZ_JZGJBXX");
		list.add("TB_BIZ_JZGGJYYZSXX"); 
		list.add("TB_BIZ_JZGGNPXXX");
		list.add("TB_BIZ_JZGGWPRXX");
		list.add("TB_BIZ_JZGGZJLXX");
		list.add("TB_BIZ_JZGJBDYXX");
		list.add("TB_BIZ_JZGJLLGXX");
		list.add("TB_BIZ_JZGJXKYHJXX");
		list.add("TB_BIZ_JZGJYJXXX");
		list.add("TB_BIZ_JZGKJLWXX");
		list.add("TB_BIZ_JZGKJXMXX");
		list.add("TB_BIZ_JZGKJZZXX");
		list.add("TB_BIZ_JZGLXFSXX");
		list.add("TB_BIZ_JZGNDKHXX");
		list.add("TB_BIZ_JZGQTJNXX");
		list.add("TB_BIZ_JZGRXRCXMXX"); 
		list.add("TB_BIZ_JZGSDCFXX");
		list.add("TB_BIZ_JZGSDKHXX"); 
		list.add("TB_BIZ_JZGSDRYXX");
		list.add("TB_BIZ_JZGWYZPXX");
		list.add("TB_BIZ_JZGXXJLXX");
		list.add("TB_BIZ_JZGYYNLXX");
		list.add("TB_BIZ_JZGZDXSCJJSHJXX");
		list.add("TB_BIZ_JZGZLCGXX");
		list.add("TB_BIZ_JZGZSXX");
		list.add("TB_BIZ_JZGZXBGHYJBGXX"); 
		list.add("TB_BIZ_JZGZYJSZWPRXX");  
		list.add("TB_BIZ_PXJG");
		list.add("TB_BIZ_PXJGBL");
		list.add("TB_BIZ_PXXM");
		list.add("TB_CFG_ZDB");
		list.add("TB_CFG_ZDXB");
		list.add("TB_CFG_ZDXXB");
		list.add("TB_MST_FSBDM");
		list.add("TB_MST_JYGLBMDM");
		list.add("TB_MST_XXJGDM");
		list.add("TB_MST_ZXDFZYJYGLBMDM");
		for (int i = 0; i < list.size(); i++) {
			String tableName = list.get(i);
			//查询本地数据
			Map<String, Object> map1 = doQueryOne("select t.* from BASE_TABLE_UPDATE_LOG t where t.TABLE_NAME = '"+tableName+"' ",getConn2());
			if (map1 != null && !map1.isEmpty()) {
				//得到VPN上更新的数据
				List<Map<String, Object>> list_GX = doQuery("select t.* from BIZ."+ tableName +" t  where t.XGSJ>to_timestamp('"+map1.get("LAST_UPDATE_TIME").toString()+"','yyyy-MM-dd hh24:mi:ss.ff6')",getConnVPN());
				if (list_GX != null && list_GX.size() > 0) {
					System.out.println("表："+ tableName + "  从"+map1.get("LAST_UPDATE_TIME").toString()+"到现在，共"+ list_GX.size() +"条更新数据");
					String ids = "";
					if (list_GX.size() < 1000) {
						for (Map<String, Object> map : list_GX) {
							ids = ids + "'" +map.get("ID").toString() + "',";
						}
						//先删除
						doUpdate("delete from "+tableName+" where ID in("+ids.substring(0, ids.length()-1)+") ", getConn2());
					}else {
						StringBuffer sql = new StringBuffer("");
						sql.append(" ").append("ID").append(" IN ( ");
						int count = 0;
						for (Map<String, Object> map : list_GX) {
							sql.append("'").append(map.get("ID").toString() + "',");
							if ((count + 1) % 500 == 0 && (count + 1) < list_GX.size()) {
								sql.deleteCharAt(sql.length() - 1);
								sql.append(" ) OR ").append("ID").append(" IN (");
							}
							count++;
						}
						sql.deleteCharAt(sql.length() - 1);
						sql.append(" )");
						//先删除
						doUpdate("delete from "+tableName+" where "+sql.toString(), getConn2());
						
					}
					
					//后新增
					try {
						for (Map<String, Object> map : list_GX) {
							insertDataV2(map, tableName, getConn2());
						}
						System.out.println("表："+ tableName + "更新成功");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else {
					System.out.println("表："+ tableName + " 无更新数据");
				}
				//得到VPN上新增的数据
				List<Map<String, Object>> list_XZ = doQuery("select t.* from BIZ."+ tableName +" t  where t.CJSJ>to_timestamp('"+map1.get("LAST_ADD_TIME").toString()+"','yyyy-MM-dd hh24:mi:ss.ff6')",getConnVPN());
				if (list_XZ != null && list_XZ.size() > 0) {
					System.out.println("表："+ tableName + " 从"+map1.get("LAST_ADD_TIME").toString()+"到现在，共"+ list_XZ.size() +"条新增数据");
					/*try {
						for (Map<String, Object> map : list_XZ) {
							insertDataV2(map, tableName, getConn2());
						}
						System.out.println("表："+ tableName + "新增成功");
					} catch (Exception e) {
						e.printStackTrace();
					}*/
				}else {
					System.out.println("表："+ tableName + " 无新增数据");
				}
			}
		}
		//执行完数据更新和新增之后，更新BASE_TABLE_UPDATE_LOG表
		/*doUpdate("delete from BASE_TABLE_UPDATE_LOG", getConn2());
		saveBaseTableUpdateLogV1();
		saveBaseTableUpdateLogV2();*/
	}
    /**
     * 
    * @Description: 第一次读取表的创建时间和更新时间
    * @author hyl     
     */
    public static void saveBaseTableUpdateLogV1() {
    	//BaseTableUpdateLogManager baseTableUpdateLogManager= (BaseTableUpdateLogManager)org.my431.platform.utils.ContextUtils.getBean("baseTableUpdateLogManager");
		List<String> list = new ArrayList<String>();
		list.add("TB_BIZ_JSDQZCXX");
		list.add("TB_BIZ_JSSCZCXX");
		list.add("TB_BIZ_JSZGXX");
		list.add("TB_BIZ_JZGGJBZHHYBZXX"); 
		list.add("TB_BIZ_JZGJBXX");
		list.add("TB_BIZ_JZGGJYYZSXX"); 
		list.add("TB_BIZ_JZGGNPXXX");
		list.add("TB_BIZ_JZGGWPRXX");
		list.add("TB_BIZ_JZGGZJLXX");
		list.add("TB_BIZ_JZGJBDYXX");
		list.add("TB_BIZ_JZGJLLGXX");
		list.add("TB_BIZ_JZGJXKYHJXX");
		list.add("TB_BIZ_JZGJYJXXX");
		list.add("TB_BIZ_JZGKJLWXX");
		list.add("TB_BIZ_JZGKJXMXX");
		list.add("TB_BIZ_JZGKJZZXX");
		list.add("TB_BIZ_JZGLXFSXX");
		list.add("TB_BIZ_JZGNDKHXX");
		list.add("TB_BIZ_JZGQTJNXX");
		list.add("TB_BIZ_JZGRXRCXMXX"); 
		list.add("TB_BIZ_JZGSDCFXX");
		list.add("TB_BIZ_JZGSDKHXX"); 
		list.add("TB_BIZ_JZGSDRYXX");
		list.add("TB_BIZ_JZGWYZPXX");
		list.add("TB_BIZ_JZGXXJLXX");
		list.add("TB_BIZ_JZGYYNLXX");
		list.add("TB_BIZ_JZGZDXSCJJSHJXX");
		list.add("TB_BIZ_JZGZLCGXX");
		list.add("TB_BIZ_JZGZSXX");
		list.add("TB_BIZ_JZGZXBGHYJBGXX"); 
		list.add("TB_BIZ_JZGZYJSZWPRXX");  
		list.add("TB_BIZ_PXJG");
		list.add("TB_BIZ_PXJGBL");
		list.add("TB_BIZ_PXXM");
		list.add("TB_CFG_ZDB");
		list.add("TB_CFG_ZDXB");
		list.add("TB_CFG_ZDXXB");
		list.add("TB_MST_FSBDM");
		list.add("TB_MST_JYGLBMDM");
		list.add("TB_MST_XXJGDM");
		list.add("TB_MST_ZXDFZYJYGLBMDM");
		int count = 1;
		for (String tableName : list) {
			Map<String, Object> map_cjsj = doQueryOne("select  max(cjsj) as cjsj from "+ tableName,getConn2());
			Map<String, Object> map_xgsj = doQueryOne("select  max(xgsj) as xgsj from "+ tableName,getConn2());
			Map<String, Object> map_cnt = doQueryOne("select  count(*) as cnt from "+ tableName,getConn2());
			try {
				if (map_xgsj != null && !map_xgsj.isEmpty()) {
					if (map_cjsj != null && !map_cjsj.isEmpty()) {
						doUpdate("insert into BASE_TABLE_UPDATE_LOG values('"+RandomGuid.getUuid()+"','"+tableName+"',to_timestamp('"+map_xgsj.get("XGSJ").toString()+"','yyyy-MM-dd hh24:mi:ss.ff6'),to_timestamp('"+map_cjsj.get("CJSJ").toString()+"','yyyy-MM-dd hh24:mi:ss.ff6'),"+MMap.isnullInt(map_cnt.get("CNT"))+")",getConn2());
					}else {
						doUpdate("insert into BASE_TABLE_UPDATE_LOG(ID,TABLE_NAME,LAST_UPDATE_TIME,DATA_CNT) values('"+RandomGuid.getUuid()+"','"+tableName+"',to_timestamp('"+map_xgsj.get("XGSJ").toString()+"','yyyy-MM-dd hh24:mi:ss.ff6'),"+MMap.isnullInt(map_cnt.get("CNT"))+")",getConn2());
					}
				}else {
					if (map_cjsj != null && !map_cjsj.isEmpty()) {
						doUpdate("insert into BASE_TABLE_UPDATE_LOG(ID,TABLE_NAME,LAST_ADD_TIME,DATA_CNT) values('"+RandomGuid.getUuid()+"','"+tableName+"',to_timestamp('"+map_xgsj.get("CJSJ").toString()+"','yyyy-MM-dd hh24:mi:ss.ff6'),"+MMap.isnullInt(map_cnt.get("CNT"))+")",getConn2());
					}else {
						doUpdate("insert into BASE_TABLE_UPDATE_LOG(ID,TABLE_NAME,DATA_CNT) values('"+RandomGuid.getUuid()+"','"+tableName+"',"+MMap.isnullInt(map_cnt.get("CNT"))+")",getConn2());
					}
				}
				System.out.println("第" + count + ":" + tableName + "成功");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("第" + count + ":" + tableName + "失败");
				e.printStackTrace();
			}
			count++;
		}
	}
    /**
     * 
    * @Description: 无创建时间和空表
    * @author hyl     
     */
    public static void saveBaseTableUpdateLogV2() {
    	//BaseTableUpdateLogManager baseTableUpdateLogManager= (BaseTableUpdateLogManager)org.my431.platform.utils.ContextUtils.getBean("baseTableUpdateLogManager");
		List<String> list = new ArrayList<String>();
		list.add("TB_CFG_CONF_DIC");
		list.add("TB_EDI_FSBDM");
		list.add("TB_EDI_JYGLBMDM");
		list.add("TB_EDI_XXJGDM");
		int count = 1;
		for (String tableName : list) {
			Map<String, Object> map_cnt = doQueryOne("select  count(*) as cnt from "+ tableName,getConn2());
			try {
				doUpdate("insert into BASE_TABLE_UPDATE_LOG(ID,TABLE_NAME,DATA_CNT) values('"+RandomGuid.getUuid()+"','"+tableName+"',"+MMap.isnullInt(map_cnt.get("CNT"))+")",getConn2());
				System.out.println("第" + count + ":" + tableName + "成功");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("第" + count + ":" + tableName + "失败");
				e.printStackTrace();
			}
			count++;
		}
	}
    /**
    * Example: List sqhlist=[aa,bb,cc,dd,ee,ff,gg] ;
    * Test.getSqlStrByList(sqhList,3,"SHENQINGH")= "SHENQING IN
    * ('aa','bb','cc') OR SHENQINGH IN ('dd','ee','ff') OR SHENQINGH IN ('g
    *
    * 把超过1000的in条件集合拆分成数量splitNum的多组sql的in 集合。
    *
    * @param sqhList
    *            in条件的List
    * @param splitNum
    *            拆分的间隔数目,例如： 1000
    * @param columnName
    *            SQL中引用的字段名例如： Z.SHENQINGH
    * @return
    */
	private static String getSqlStrByList(List sqhList, int splitNum,String columnName) {
		if (splitNum > 1000) // 因为数据库的列表sql限制，不能超过1000.
			return null;
		StringBuffer sql = new StringBuffer("");
		if (sqhList != null) {
			sql.append(" ").append(columnName).append(" IN ( ");
			for (int i = 0; i < sqhList.size(); i++) {
				sql.append("'").append(sqhList.get(i) + "',");
				if ((i + 1) % splitNum == 0 && (i + 1) < sqhList.size()) {
					sql.deleteCharAt(sql.length() - 1);
					sql.append(" ) OR ").append(columnName).append(" IN (");
				}
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(" )");
		}
		return sql.toString();
	}
}
