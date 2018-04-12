package org.my431.phone.servlet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.my431.base.json.JsonUtil;
import org.my431.platform.listener.PhoneOnlineListener;
import org.my431.plugin.redis.services.RedisManager;

public class PhoneLogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1803521179493204609L;
	private static final String redisAppSessionIdPrefix = "app.sessionId.";

	public void init() throws ServletException {

	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("get");
		//sessionValidate(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("post");
		sessionValidate(request, response);
	}

	private void sessionValidate(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		StringBuffer info = new java.lang.StringBuffer();
		InputStream in = request.getInputStream();
		BufferedInputStream buf = new BufferedInputStream(in);
		
		String token=request.getParameter("token");
		
		if(token==null||token.equals("")){
			String appJson = "";
			byte[] buffer = new byte[1024];
			int iRead;
			while ((iRead = buf.read(buffer)) != -1) {
				info.append(new String(buffer, 0, iRead, "UTF-8"));
			}
			appJson = info.toString();
	
			System.out.println("appLogoutJson:" + appJson);
	
			JSONObject jsonObject = JSONObject.fromObject(appJson);
			Object sessionId = jsonObject.get("token");
			
			if (sessionId != null) {
				System.out.println("注销=================="+sessionId);
				String redisSessionId = redisAppSessionIdPrefix + sessionId.toString();
				RedisManager redisManager = (RedisManager) org.my431.platform.utils.ContextUtils.getBean("redisManager");
				if(redisManager.objectHasKey(redisSessionId)){
					redisManager.removeOValue(redisSessionId);
				}
				PhoneOnlineListener.removeSession(sessionId.toString());
			}else{
				System.out.println("注销==================token空");
			}
		}else{
			Object sessionId = token;
			
			if (sessionId != null) {
				System.out.println("注销=================="+sessionId);
				String redisSessionId = redisAppSessionIdPrefix + sessionId.toString();
				RedisManager redisManager = (RedisManager) org.my431.platform.utils.ContextUtils.getBean("redisManager");
				if(redisManager.objectHasKey(redisSessionId)){
					redisManager.removeOValue(redisSessionId);
				}
				PhoneOnlineListener.removeSession(sessionId.toString());
			
			}else{
				System.out.println("注销==================token空");
			}
		}
		String responseText = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errorno", 0);
		map.put("message", " ");

		try {
			responseText = JsonUtil.mapToJSON(map);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getOutputStream().write(responseText.getBytes("UTF-8"));
		response.getOutputStream().close();
	}

}