package org.my431.phone.servlet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.my431.base.json.JsonUtil;
import org.my431.base.model.BaseArea;
import org.my431.base.model.BaseSchool;
import org.my431.base.model.BaseUser;
import org.my431.base.services.BaseLoginManager;
import org.my431.base.services.BasePropertiesManager;
import org.my431.base.services.BaseSchoolManager;
import org.my431.base.services.BaseUserManager;
import org.my431.base.services.CacheBaseAreaManager;
import org.my431.platform.listener.OnlineModel;
import org.my431.platform.utils.ContextUtils;
import org.my431.plugin.redis.services.RedisManager;
import org.my431.taglib.My431Function;
import org.my431.util.MD5;

public class PhoneLoginServlet extends HttpServlet {
	private static final String redisAppSessionIdPrefix = "app.sessionId.";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1803521179493204609L;

	public void destroy() {
		super.destroy(); 
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("get");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("post");
		sessionValidate(request,response);
	}
	
	public void init() throws ServletException {

	}
	
	public static void sessionValidate(HttpServletRequest request, HttpServletResponse response) throws IOException{

		 StringBuffer info=new java.lang.StringBuffer();
         InputStream in=request.getInputStream();
         BufferedInputStream buf=new BufferedInputStream(in);
         
         String appJson="";
         byte[] buffer=new byte[1024]; 
         int iRead;
         while((iRead=buf.read(buffer))!=-1)   
         {
          info.append(new String(buffer,0,iRead,"UTF-8"));
         } 
         appJson=info.toString();
         
        JSONObject jsonObject = JSONObject.fromObject(appJson);
        Object n= jsonObject.get("loginName");
        Object p= jsonObject.get("passWord");
		
		String loginName=null;
		String passWord=null;
		
		if(n!=null){
			loginName=n.toString();
		}
		if(p!=null){
			passWord=p.toString();
		}
		boolean isLoginSuccess=true;
		BaseLoginManager baseLoginManager=(BaseLoginManager)ContextUtils.getBean("baseLoginManager");
		BaseSchoolManager baseSchoolManager=(BaseSchoolManager)ContextUtils.getBean("baseSchoolManager");
		BaseUserManager baseUserManager=(BaseUserManager)ContextUtils.getBean("baseUserManager");
		BaseUser baseUser = null;
		BaseSchool baseSchool=null;
		if(loginName!=null&&passWord!=null){
			baseUser = baseLoginManager.login(loginName, MD5.getMd5(passWord));
			if(baseUser==null){
				isLoginSuccess=false;
			}else{
				if(baseUser.getSchoolId()!=null){
					baseSchool=baseSchoolManager.get(baseUser.getSchoolId());
				}
				if(baseUser.getStatus()!=null&&baseUser.getStatus().equals("0")){
					isLoginSuccess=false;
				}else{
					BaseArea baseArea =CacheBaseAreaManager.getNodeById(baseUser.getAreaId());
					if(baseUser.getDefaultRoleCode().equals("role.schoolManager")||baseUser.getDefaultRoleCode().equals("role.areaObserve")||baseUser.getDefaultRoleCode().equals("role.provinceManager")||baseUser.getDefaultRoleCode().equals("role.cityManager")||baseUser.getDefaultRoleCode().equals("role.countryManager")){//
						if(baseArea.getDeleteFlag().equals(1)){
							isLoginSuccess=false;
						}
					}else{
						isLoginSuccess=false;
					}
				}
			}

		}else{
			isLoginSuccess=false;
		}
		
		String responseText = null;
		Map<String,Object> map=new HashMap<String,Object>();
		Map<String,String> mapData=new HashMap<String,String>();
		
		if(isLoginSuccess){
			BaseArea baseArea =CacheBaseAreaManager.getNodeById(baseUser.getAreaId());	

			String depName=baseUser.getProvinceId();
			/** 更新在线登录用户信息 开始 **/
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(System.currentTimeMillis());
			request.getSession().setAttribute("loginedPhone", 
					new OnlineModel(request.getSession().getId(),loginName,baseUser.getRealName(),request.getRemoteAddr()
							,time,time,"phone",depName,baseUser.getDefaultRoleCode(),getIpAddr(request),null,baseUser
							,My431Function.getRoleByCode(baseUser.getDefaultRoleCode()),baseArea.getAreaName()));
			/** 更新在线登录用户信息 结束 **/

			
			baseUser.setLastLoginTime(new Date());
			if(baseUser.getLoginCnt()!=null){
				baseUser.setLoginCnt(baseUser.getLoginCnt()+1);
			}else{
				baseUser.setLoginCnt(1);
			}
			baseUserManager.save(baseUser);
			baseUserManager.updateReload(baseUser);
			baseUserManager.flush();
			baseUserManager.evit(baseUser);
			
			RedisManager redisManager = (RedisManager) ContextUtils.getBean("redisManager");
			String sessionId = request.getSession().getId();
			String redisSessionId = redisAppSessionIdPrefix + sessionId;
//			redisManager.expire(redisSessionId, 86400l);
			Map<String, Object> sessionMap = new HashMap<String, Object>();
			sessionMap.put("baseUser", baseUser);
			redisManager.setOValue(redisSessionId, sessionMap);
			
			request.getSession().setAttribute("wsloginName", baseUser.getLoginName());
			
			mapData.put("userId", baseUser.getId());
			mapData.put("userName", baseUser.getRealName());
			mapData.put("areaCode", baseArea.getAreaCode());
			mapData.put("areaName", baseArea.getAreaName());
			mapData.put("roleCode", baseUser.getDefaultRoleCode());
			mapData.put("schoolId", baseUser.getSchoolId()==null?"":baseUser.getSchoolId());
			mapData.put("schoolName", baseSchool==null?"":baseSchool.getSchoolName());
			mapData.put("yearCode", BasePropertiesManager.getValue(BasePropertiesManager.getValue("base.year")));
			
			mapData.put("token", sessionId);
			
			map.put("errorno", 0);
			map.put("message", " ");
			map.put("data", mapData);
			
			
			System.out.println("成功");
		}else{
			map.put("errorno", 1);
			map.put("message", "用户名或密码错误登陆失败！");
			System.out.println("失败");
		}
		
		try {
			responseText=JsonUtil.mapToJSON(map);
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getOutputStream().write(responseText.getBytes("UTF-8"));
		response.getOutputStream().close();
	}
	
//获得客户端真实IP地址的方法二：
public static String getIpAddr(HttpServletRequest request) {  
		  String ip = null;
		  java.util.Enumeration enu = request.getHeaderNames();

		  while (enu.hasMoreElements()) {
		    String name = (String)enu.nextElement();
		    if (name.equalsIgnoreCase("X-Real-IP")) {
		  	  System.out.println("cc:x-real-ip="+request.getHeader("X-Real-IP"));
		    }
		    if (name.equalsIgnoreCase("X-Forwarded-For")) {
		      ip = request.getHeader(name);
		    }
		    else if (name.equalsIgnoreCase("Proxy-Client-IP")) {
		      ip = request.getHeader(name);
		    }
		    else if (name.equalsIgnoreCase("WL-Proxy-Client-IP")) {
		      ip = request.getHeader(name);
		    }

		    if ((ip != null) && (ip.length() != 0)){
		      break;
		    }
		  }
		  System.out.println("a:"+ip);
		  if ((ip == null) || (ip.length() == 0)){
		    ip = request.getRemoteAddr();
		  } 
	      return ip;  
	  }   
}