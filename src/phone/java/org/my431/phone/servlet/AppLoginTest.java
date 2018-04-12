package org.my431.phone.servlet;

import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.my431.util.sortUrl.MD5BaseShortUrlService;
import org.my431.util.sortUrl.SequenceBaseShortUrlService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class AppLoginTest {
	public static String BASE_URL = "http://gb.35kj.cn:8081/gb_mis/";
	//public static String BASE_URL = "http://localhost:8080/gbmis/";
	 public static String LOGIN_URL = BASE_URL+"/phoneLoginServlet";
	 public static String LOGOUT_URL = BASE_URL+"/phoneLogoutServlet";
	 public static String USER_INFO_URL = BASE_URL+"/phone/User/getUserInfo.jspx";
	 
	 public static String USER_UPDATA_URL = BASE_URL+"/phone/User/updataUserInfo.jspx";
	 public static String USER_UPPWD_URL = BASE_URL+"/phone/User/updataPassWord.jspx";
	 public static String USER_AREA_URL = BASE_URL+"/phone/User/getAreaList.jspx";
	 public static String USER_YEAR_URL = BASE_URL+"/phone/User/getYearList.jspx";
	 public static String USER_TOOL_URL = BASE_URL+"/phone/User/getToolMenu.jspx";
	 public static String USER_PHOTO_URL = BASE_URL+"/phone/User/getPhotoTypes.jspx";
	 public static String USER_SCHOOL_PHOTO_URL = BASE_URL+"/phone/User/getSchoolPhotos.jspx";
		
	  public static String test(String strURL,Map<String,String> mapParams) {  
	        try {
	        	
		        DefaultHttpClient httpclient = new DefaultHttpClient();
				HttpRequestBase httpRequest = null;
				HttpPost httpPost = new HttpPost(strURL);
				AbstractHttpEntity entity = null;
				System.out.println(JSONObject.fromObject(mapParams).toString());
				entity = new StringEntity(JSONObject.fromObject(mapParams).toString());
				entity.setContentType("application/json");
				if (entity != null) {
					entity.setContentEncoding("UTF-8");
				}
				httpPost.setEntity(entity);
				httpPost.addHeader("charset", "UTF-8"); 
				httpRequest = httpPost;
				HttpResponse httpResponse = httpclient.execute(httpRequest);
				
				Integer status=httpResponse.getStatusLine().getStatusCode();
				
				if(status==200){
					String resultStr = EntityUtils.toString(httpResponse.getEntity()); 
					System.out.println(resultStr);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		        
		    return "error"; // 自定义错误信息  
	    }  
	  
	  
		public static String jsonFormatter(String uglyJSONString){
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse(uglyJSONString);
			String prettyJsonString = gson.toJson(je);
			return prettyJsonString;
		}
		
	    public static void main(String[] args) {
	    	
	    	
	    	//登陆接口
	    	/*
	        Map<String,String> mapParams=new LinkedHashMap<String,String>();
	        mapParams.put("loginName", "6206233162012385");
	        mapParams.put("passWord", "123456");
	    	test(LOGIN_URL,mapParams); 
	    	*/
	    	
	    	//注销 
	    	/*
	    	Map<String,String> mapParams=new LinkedHashMap<String,String>();
	        mapParams.put("userId", "402891814c72dcf3014c73164d300e85");
	        mapParams.put("token", "E1C6C37112B0744BBABAC81EA8C395FA");
	    	test(LOGOUT_URL,mapParams); 
	    	*/
	    	
	    	//获取个人信息
	    	
	        Map<String,String> mapParams=new LinkedHashMap<String,String>();
	        mapParams.put("userId", "ff8080814eb8b564014eb8f60c574cd3");
	        mapParams.put("token", "2A668A19ACD2CD1583A5550BEAC9DE7C");
	    	test(USER_INFO_URL,mapParams);  
	    	
	    	
	    	//修改个人信息
	    	/*
	        Map<String,String> mapParams=new LinkedHashMap<String,String>();
	        mapParams.put("userId", "402891814c72dcf3014c73164d670e86");
	        mapParams.put("token", "BB2A2ACCCAEE0196EAF5716A3E23A27A");
	    	test(USER_UPDATA_URL,mapParams);  
	    	*/ 
	    	
	    	//修改密码
	    	/*
	        Map<String,String> mapParams=new LinkedHashMap<String,String>();
	        mapParams.put("userId", "402891814c72dcf3014c73164d300e85");
	        mapParams.put("token", "85D09592A018C183C737FAE02BFE3B45");
	    	test(USER_UPPWD_URL,mapParams);  
	    	 */
	    	
	    	//获取地区列表
	    	/*
	        Map<String,String> mapParams=new LinkedHashMap<String,String>();
	        mapParams.put("userId", "402891814c72dcf3014c73164d670e86");
	        mapParams.put("token", "B3229469160426BA7BAE617759797BF7");
	    	test(USER_AREA_URL,mapParams);  
	    	*/
	    	
	    	//获取年份列表
	    	/*
	        Map<String,String> mapParams=new LinkedHashMap<String,String>();
	        mapParams.put("userId", "402891814c72dcf3014c73164d300e85");
	        mapParams.put("token", "A2523EF7EA4CCA5B508009BB4CFFDFE3");
	    	test(USER_YEAR_URL,mapParams);  
	    	*/
	    	
	    	//获取工具菜单
	    	/*
	        Map<String,String> mapParams=new LinkedHashMap<String,String>();
	        mapParams.put("userId", "402891814c72dcf3014c73164d300e85");
	        mapParams.put("token", "A2523EF7EA4CCA5B508009BB4CFFDFE3");
	    	test(USER_TOOL_URL,mapParams);  
	    	*/
	    	
	    	//获取相册
	    	/*
	        Map<String,String> mapParams=new LinkedHashMap<String,String>();
	        mapParams.put("token", "BB2A2ACCCAEE0196EAF5716A3E23A27A");
	        mapParams.put("photoValues", "viewType:project;parentPhotoType:ht;schoolId:BA39A9A7A6DF4A378463926A8F08A00D;pid:001B55D9-CA83-E2BB-71FA-7B412F8E656C");
	    	test(USER_PHOTO_URL,mapParams);  
	    	*/
	    	//获取学校照片
	    	/*
	        Map<String,String> mapParams=new LinkedHashMap<String,String>();
	        mapParams.put("token", "BB2A2ACCCAEE0196EAF5716A3E23A27A");
	        mapParams.put("photoValues", "viewType:item;parentPhotoType:item_type_xs_cs;schoolId:BA39A9A7A6DF4A378463926A8F08A00D;step:gb_itemstatus_ztwg");
	    	test(USER_SCHOOL_PHOTO_URL,mapParams);  
	    	*/
	    }  



}
