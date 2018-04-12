<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>

<body>
<% 
String driver = "oracle.jdbc.driver.OracleDriver";

// URL指向要访问的数据库名test1

String url = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.180.245.6)(PORT = 1521))(CONNECT_DATA = (SERVER = DEDICATED)(SERVICE_NAME = JSGLRAC)))";

//配置时的用户名

String user = "gansu_jsfzjc";

//密码

String password = "gs_jsfzjc";

try {

// 1 加载驱动程序

Class.forName(driver);

// 2 连接数据库

Connection conn = DriverManager.getConnection(url, user, password);

// 3 用来执行SQL语句

Statement statement = conn.createStatement();

// 要执行的SQL语句

String sql = "select * from biz.tb_biz_jzgjbxx t where ROWNUM <10";

ResultSet rs = statement.executeQuery(sql);
String name = null;
String mima=null;
while (rs.next()) { 
name = rs.getString("DABH");	
mima = rs.getString("XM"); 
out.println(name+"\t"+mima); 
}	
rs.close();
conn.close();
} catch (ClassNotFoundException e) {
System.out.println("Sorry,can`t find the Driver!");
e.printStackTrace();
} catch (SQLException e) {
e.printStackTrace();
} catch (Exception e) {
e.printStackTrace();
}

%>
</body>