package org.my431.base.web;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xdevelop.ip.IPLocalizer;

import org.apache.commons.lang.StringUtils;
import org.my431.base.model.BaseArea;
import org.my431.base.model.BaseNote;
import org.my431.base.model.BaseProperties;
import org.my431.base.model.BaseRole;
import org.my431.base.model.BaseSchool;
import org.my431.base.model.BaseSchoolFsb;
import org.my431.base.model.BaseUser;
import org.my431.base.services.BaseAreaManager;
import org.my431.base.services.BaseLoginManager;
import org.my431.base.services.BaseNoteManager;
import org.my431.base.services.BasePropertiesManager;
import org.my431.base.services.BaseRoleManager;
import org.my431.base.services.BaseSchoolFsbManager;
import org.my431.base.services.BaseSchoolManager;
import org.my431.base.services.BaseUserManager;
import org.my431.base.services.CacheBaseAreaManager;
import org.my431.base.services.CacheBasePropertiesManager;
import org.my431.base.services.CacheBaseSchoolManager;
import org.my431.base.services.CacheBaseUserManager;
import org.my431.platform.ObjectIDFactory;
import org.my431.platform.dao.support.Page;
import org.my431.platform.listener.OnlineModel;
import org.my431.platform.web.StrutsTreeEntityAction;
import org.my431.plugin.email.EMailUtils;
import org.my431.plugin.email.MailEntity;
import org.my431.plugin.email.MailPlugin;
import org.my431.plugin.email.ServerEntity;
import org.my431.plugin.redis.services.RedisManager;
import org.my431.taglib.My431Function;
import org.my431.util.DateFormater;
import org.my431.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class LoginAction  extends StrutsTreeEntityAction<BaseArea,BaseAreaManager> implements Preparable,ModelDriven{
	
	protected static final String HOME_PAGE_JSP = "/base/home/homepage.jsp";
	public HttpServletRequest request;  
	private RedisManager redisManager;
	public RedisManager getRedisManager() {
		return redisManager;
	}
	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}
	private Map session;

	public HttpServletResponse response;
	
	private BaseLoginManager baseLoginManager;
	
	private BaseUserManager baseUserManager;
	
	private BaseSchoolManager baseSchoolManager;
	
	@Autowired
	private BaseSchoolFsbManager baseSchoolFsbManager;
	
	private BaseUser baseUser;
	
	private BaseSchool baseSchool;
	
	
	private BasePropertiesManager basePropertiesManager;
	
	
	private BaseAreaManager baseAreaManager;
	
	private BaseNoteManager baseNoteManager;
	
	private BaseRoleManager baseRoleManager;
	
	public void setBaseRoleManager(BaseRoleManager baseRoleManager) {
		this.baseRoleManager = baseRoleManager;
	}
	private List<BaseProperties> dutyList;
	private List<BaseProperties> titleList;
	private List<BaseProperties> degreeList;
	private List<BaseProperties> subjectList;
	private List<BaseProperties> mzList;
	private List<BaseProperties>  schoolTypeList;
	private List<BaseProperties>  schoolEdusystemList;
	private List<BaseProperties>  schoolClassnumList;
	private List<BaseProperties>  schoolMinzuList;
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseNoteManager(BaseNoteManager manager) {
		this.baseNoteManager = manager;
	}	
	public List<BaseProperties> getSchoolMinzuList() {
		return schoolMinzuList;
	}
	public void setSchoolMinzuList(List<BaseProperties> schoolMinzuList) {
		this.schoolMinzuList = schoolMinzuList;
	}
	public List<BaseProperties> getMzList() {
		return mzList;
	}
	public void setMzList(List<BaseProperties> mzList) {
		this.mzList = mzList;
	}
	/**
	 * 验证用户是否正确
	 * @return
	 */
	public String validateUser()
	{
		String loginName = request.getParameter("loginName")==null?"":request.getParameter("loginName").trim();
		String password = request.getParameter("password");
		String returnValue="";
		/** 图形验证码 */
		 String input; 
		 String rand; 
		 input = request.getParameter("rand");
		 rand = (String) request.getSession().getAttribute("rand"); 
		 if(input==null||!input.equals(rand)) { 
			 //request.setAttribute("errorType","ImageChkFailed"); 
			returnValue="ImageChkFailed";
		 } else{
	     // 进行登录
	     BaseUser baseUser = baseLoginManager.login(loginName, MD5.getMd5(password));
	     request.setAttribute("baseUser", baseUser);
		 if(baseUser==null){
			 // request.setAttribute("errorType", "UserInactive");
			 returnValue="UserInactive";
		 }else{
		
			if(baseUser.getStatus()!=null&&baseUser.getStatus().equals("0")){
				//request.setAttribute("errorType", "UserInactiveStatus");
				 returnValue="UserInactiveStatus";
			}else{
				boolean istrue=true;
				BaseArea baseArea =CacheBaseAreaManager.getNodeById(baseUser.getAreaId());
				if(baseUser.getDefaultRoleCode().equals("role.areaManager")){
					if(baseArea.getDeleteFlag().equals(1)){
						istrue=false;
					}
				}
				if(baseUser.getDefaultRoleCode().equals("role.schoolManager")){
					if(baseArea.getDeleteFlag().equals(1)){
						istrue=false;
					}
				}
				if(istrue){
					returnValue="success";
				}else{
					returnValue="UserInactiveAreaStatus";
				}
				
			}
			}
		 }
		  response.setContentType("text/html;charset=utf-8");
			java.io.PrintWriter pw = null;
			try {
				pw = response.getWriter();
				pw.write(returnValue);
			} catch (java.io.IOException e) {
				returnValue = "error";
				e.printStackTrace();
			}
		return null;
	}
	
	public String login(){
		long begin=System.currentTimeMillis();
		String forwardUrl = "/login.jsp";
		String loginName = request.getParameter("loginName")==null?"":request.getParameter("loginName").trim();
		String password = request.getParameter("password");
		String isFSB = "";//是否附设班
		
		/** 图形验证码 */
		String input; 
		String rand; 
		 
		input = request.getParameter("rand");
		rand = (String) request.getSession().getAttribute("rand"); 
		if(input==null||!input.equals(rand)) { 
			request.setAttribute("errorType","ImageChkFailed"); 
			return forwardUrl;
		} 


		// 进行登录
		BaseUser baseUser = null;//(BaseUser) request.getSession().getAttribute("ssoUser");
		if(baseUser==null||isNullOrEmptyString(baseUser.getId())){
			baseUser=baseLoginManager.login(loginName, MD5.getMd5(password));
		}
		request.setAttribute("baseUser", baseUser);
		
		
			if(baseUser==null){
				request.setAttribute("errorType", "UserInactive");
				return forwardUrl;
			}else{
				
				if(baseUser.getStatus()!=null&&baseUser.getStatus().equals("0")){
					request.setAttribute("errorType", "UserInactiveStatus");
					return forwardUrl;
				}else{
					
					BaseArea baseArea =CacheBaseAreaManager.getNodeById(baseUser.getAreaId());
					if(baseUser.getDefaultRoleCode().equals("role.areaManager")){
						if(baseArea.getDeleteFlag().equals(1)){
							request.setAttribute("errorType", "UserInactiveStatus");
							return forwardUrl;
						}
					}
					if(baseUser.getDefaultRoleCode().equals("role.areaObserve")){
						if(baseArea.getDeleteFlag().equals(1)){
							request.setAttribute("errorType", "UserInactiveStatus");
							return forwardUrl;
						}
					}
					if(baseUser.getDefaultRoleCode().equals("role.schoolManager")){
						if(baseArea.getDeleteFlag().equals(1)){
							request.setAttribute("errorType", "UserInactiveStatus");
							return forwardUrl;
						}
						
						if (loginName.endsWith("_FSB")) {
							//BaseSchoolFsb baseSchoolFsb = baseSchoolFsbManager.get(baseUser.getSchoolId());
							isFSB = "1";
						}else { 
							//BaseSchool baseSchool = baseSchoolManager.get(baseUser.getSchoolId());
							isFSB = "0";
						}
						
					}
					//hmc
					/*BaseUser temp=redisManager.getRedisUser(baseUser.getId());
					if(temp==null||isNullOrEmptyString(temp.getId())){
						redisManager.setOValue(baseUser.getId(), baseUser);
					}*/
					redisManager.setOValue(baseUser.getId(), baseUser);
					//end hmc
					if(baseUser.getDefaultRoleCode().equals("role.schoolManager")||baseUser.getDefaultRoleCode().equals("role.cityManager")||baseUser.getDefaultRoleCode().equals("role.countryManager")||baseUser.getDefaultRoleCode().equals("role.provinceManager")){
						if(isNullOrEmptyString(baseUser.getIsIni())||baseUser.getIsIni()==0){
							request.setAttribute("baseUser", baseUser);
							request.setAttribute("areaId", baseUser.getAreaId());
							forwardUrl = "/base/initUser.jsp";
							return forwardUrl;
						}
					}
					
//					 登录成功，赋予session权限
					request.getSession().setAttribute("wsuserId", baseUser.getId());
					request.getSession().setAttribute("wsloginName", baseUser.getLoginName());
					request.getSession().setAttribute("wsrealname", baseUser.getRealName());
					request.getSession().setAttribute("wsRealname", baseUser.getRealName());
					request.getSession().setAttribute("ssoUser",baseUser);
					request.getSession().setAttribute("schoolId", baseUser.getSchoolId());
					request.getSession().setAttribute("wsDefaultRoleCode", baseUser.getDefaultRoleCode());
					request.getSession().setAttribute("isFSB", isFSB);
					
					
					/**********系统的真实姓名初始化是：地区+管理员，若发生了变化，则显示：修改后姓名(区域+角色（管理员|视察员）)，否则只显示：真是姓名****************************/
					String loginIniName="";
					if(baseArea!=null){
						String inirealName="";
						if(baseUser.getDefaultRoleCode().equals("role.areaObserve")){
							inirealName=baseArea.getAreaName()+"视察员";
						}else if(baseUser.getDefaultRoleCode().equals("role.schoolManager")){
							BaseSchool baseSchool= CacheBaseSchoolManager.getSchool(baseUser.getSchoolId());
							if(baseSchool!=null){
								inirealName=baseSchool.getSchoolName()+"管理员";
							}
						}else if(baseUser.getDefaultRoleCode().equals("role.provinceManager")||baseUser.getDefaultRoleCode().equals("role.cityManager")||baseUser.getDefaultRoleCode().equals("role.countryManager")){
							inirealName=baseArea.getAreaName()+"管理员";
						}else{
							inirealName=baseUser.getRealName();
						}
						if(StringUtils.isNotBlank(baseUser.getRealName())){
							if(!baseUser.getRealName().trim().equals(inirealName)){
								loginIniName=baseUser.getRealName()+"("+inirealName+")";
							}else {
								loginIniName=baseUser.getRealName();
							}
						}else {
							loginIniName=inirealName;
						}
						/*if(!baseUser.getRealName().equals(inirealName)){
							loginIniName=baseUser.getRealName()+"("+inirealName+")";
						}else {
							loginIniName=baseUser.getRealName();
						}*/
					}
					if(StringUtils.isNotBlank(loginIniName)){
						request.getSession().setAttribute("loginIniName", loginIniName);
					}else {
						if(StringUtils.isNotBlank(baseUser.getRealName())){
							request.getSession().setAttribute("loginIniName", baseUser.getRealName());
						}
					}
					/**************************************/
					
					if(baseUser.getDefaultRoleCode().equals("role.admin")){
						request.getSession().setAttribute("wsAreaCode","-1");
					}else{
						if(baseArea!=null){
							request.getSession().setAttribute("wsAreaCode", baseArea.getAreaCode());
						}else{
							request.getSession().setAttribute("wsAreaCode","-1");
						}
					}

					String depName=baseUser.getProvinceId();

					
					String strKey=baseLoginManager.getRandomString(20);
					String mk="global.base.BaseUser.tempkey.key."+strKey;
					
					request.setAttribute("key", strKey);
					redisManager.setOValue(mk, baseUser);
					//5秒后过期，单位参照
					redisManager.expire(mk, 300l);
					
					forwardUrl = "/success.jsp";
					
					Date realLastLoginTime = baseUser.getLastLoginTime();
					realLastLoginTime = realLastLoginTime == null ? new Date() : realLastLoginTime;
					request.getSession().setAttribute("realLastLoginTime", realLastLoginTime);
					
					baseUser.setLastLoginTime(new Date());
					baseUserManager.save(baseUser);
					baseUserManager.updateReload(baseUser);
					baseUserManager.flush();
					baseUserManager.evit(baseUser);
					
					/** 更新在线登录用户信息 开始 **/
					java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time = sdf.format(System.currentTimeMillis());
					request.getSession().setAttribute("logined", 
							new OnlineModel(request.getSession().getId(),loginName,baseUser.getRealName(),request.getRemoteAddr()
									,time,time,"",depName,baseUser.getDefaultRoleCode(),this.getIpAddr(request),"",baseUser
									,My431Function.getRoleByCode(baseUser.getDefaultRoleCode()),My431Function.getAreaNameById(baseUser.getAreaId())));
					/** 更新在线登录用户信息 结束 **/
					
					
					String url="";
					
					String rc=baseUser.getDefaultRoleCode();
					if(rc.equals("role.editor")){
						rc="role.admin";
					}
					
					BaseRole role=baseRoleManager.getByCode(rc);
					if(role.getDefaultUrl()!=null){
						url=role.getDefaultUrl();
					}
					if(!url.contains("?")){
						url=url+"?";
					}
					request.setAttribute("url", url);
					
				}
			
			}
			//hmc begin 查询当前用的最后一次通知的时间，用于展示显示通知有几条没有查看过
			BaseNote query= new BaseNote();
			Page page =null;
			int pageSize=10;//
			int pageNo=1;
			//从系统管理员到学校管理员一次一次判断
			String code1=baseUser.getProvinceId();	//当前登录对象的省ID
			String code2=baseUser.getCityId();		//当前登录对象的市id
			String code3=baseUser.getCountyId();	//当前登录对象的县id
			String code4=baseUser.getSchoolId();	//当前对象的学校id
			
			String role_code=baseUser.getDefaultRoleCode();
			if(!isNullOrEmptyString(role_code)&&role_code.equals("role.provinceManager")){
				query.setProvinceCode("1");
			}else if(!isNullOrEmptyString(role_code)&&role_code.equals("role.cityManager")){
				query.setCityAdmin("1");
			}else if(!isNullOrEmptyString(role_code)&&role_code.equals("role.countryManager")){
				query.setCountyAdmin("1");
			}else if(!isNullOrEmptyString(role_code)&&role_code.equals("role.schoolManager")){
				String sid = baseUser.getSchoolId();
				if(!isNullOrEmptyString(sid)){
					String schoolType = "";
					if ("1".equals(isFSB)) {
						BaseSchoolFsb school_temp = baseSchoolFsbManager.get(sid);
						schoolType = school_temp.getSchoolType();
					}else {
						BaseSchool school_temp = baseSchoolManager.get(sid);
						schoolType = school_temp.getSchoolType();
					}
					
					//CacheBaseSchoolManager.setSchool(sid, school_temp);//by90 on 2015-9-7学校不再从内存中取,而是从数据库中取再塞入内存
					//String schoolType = school_temp.getSchoolType();
					if(!isNullOrEmptyString(schoolType)){
						if(schoolType.equals("base.school.type.jxd")){
							query.setJxd("1");
						}else if(schoolType.equals("base.school.type.xx")){
							query.setXx("1");
						}else if(schoolType.equals("base.school.type.cz")){
							query.setCz("1");
						}else if(schoolType.equals("base.school.type.gz")){
							query.setGz("1");
						}else if(schoolType.equals("base.school.type.wz")){
							query.setWz("1");
						}else if(schoolType.equals("base.school.type.9n")){
							query.setJnygz("1");
						}else if(schoolType.equals("base.school.type.12n")){
							query.setSenygz("1");
						}else if(schoolType.equals("base.school.type.zzh")){
							query.setZzhi("1");
						}else if(schoolType.equals("base.school.type.gzh")){
							query.setZzhi("1");
						}
					}
				}
			}
			
			if(null!=code4&&!code4.equals("1")){//登陆的是学校级管理员
				query.setProvinceCode(code1);
				query.setCityCode(code2);
				query.setCountyCode(code3);
			}else if(null!=code3&&!code3.equals("1")){//县级管理员
				query.setProvinceCode(code1);
				query.setCityCode(code2);
			}else if(null!=code2&&!code2.equals("1")){//市级管理员
				query.setProvinceCode(code1);
			}else if(null!=code1&&!code1.equals("1")){//省级管理员,只用查看系统的通知
				query.setProvinceCode("1");
				query.setCityCode("1");
				query.setCountyCode("1");
			}else{//看看是不是系统管理员
				String userRole=baseUser.getDefaultRoleCode();
				if(null!=userRole&&"role.admin".equals(userRole)){
					return forwardUrl;
				}
			}
			//修改登录次数
			Integer loginCnt=baseUser.getLoginCnt();
			if(!isNullOrEmptyString(loginCnt)){
				loginCnt=baseUser.getLoginCnt()+1;
			}else{
				loginCnt=1;
			}
			//最后登录ip 
			baseUser.setLastLoginIp(getIpAddr(request));
			baseUser.setLoginCnt(loginCnt);
			baseUserManager.save(baseUser);
			baseUserManager.updateReload(baseUser);
			request.getSession().removeAttribute("ssoUser");
			request.getSession().setAttribute("ssoUser",baseUser);
			//hmc end
			return forwardUrl;
		}	
	
	public String logout(){
		String appId=request.getParameter("appId");
		if(appId==null||appId.equals("")){
			request.setAttribute("logoutUrl", request.getContextPath());
		}else{
			request.setAttribute("logoutUrl", "login.jsp");
		}
		
		request.getSession().invalidate();
		
		return "/success.jsp";
	}
	
	public String logoutWeb(){
		String appId=request.getParameter("appId");
		if(appId==null||appId.equals("")){
			request.setAttribute("logoutUrl","/base/login_web_success.jsp");
		}else{
			request.setAttribute("logoutUrl", "login_web_success.jsp");
		}
		
		request.getSession().invalidate();
		
		return "/login_web_success.jsp";
	}
	
	public String register(){
		return "/register.jsp";
	}
	
	public String saveRegister(){
		
		BaseUser baseUser=new BaseUser();
		baseUser.setLoginName(request.getParameter("loginName"));
		baseUser.setPassword(MD5.getMd5(request.getParameter("passWord")));
		baseUser.setRealName(request.getParameter("realName"));
		baseUserManager.save(baseUser);
		baseUserManager.addReload(baseUser);
		return "/success.jsp";
	}

	/**
	 * 找回密码
	 */
	public String findPwd(){
		String forwardUrl = "/find_pwd.jsp";
		String email=request.getParameter("email");
		/** 图形验证码 */
		 String input; 
		 String rand; 
//		 String imagechk = ParamUtils.getParameter(request, "imagechk"); 
//		 if (basePropertiesManager.getValue("image.login").equals("1") && !imagechk.equals("false")) 
//		 {
			 input = request.getParameter("rand");
			 rand = (String) request.getSession().getAttribute("rand"); 
			 if(input==null||!input.equals(rand)) { 
				 request.setAttribute("errorType","ImageChkFailed"); 
				 request.setAttribute("email",email );
				 return forwardUrl;
			 } 
//		}
		String result=baseUserManager.validateEmail(email);
		if(result.equals("1")){
			List<BaseUser> userlist=baseUserManager.findBy(BaseUser.class,"email",email);
			BaseUser baseUser=userlist.get(0);
			String mgukey=ObjectIDFactory.getGuid();
			baseUserManager.save(baseUser);
			baseUserManager.addReload(baseUser);
			//发送Email
			List<String> listAddr = new ArrayList<String>();// 收件人
			listAddr.add(baseUser.getEmail());
			String  serverUrl=CacheBasePropertiesManager.getValueByPropertyKey("sys.url");
			String emailContent="<html>" +
			"<head>" +
			" <meta http-equiv='Conten-Type' content='text/html; charset=utf-8' />" +
			"<title>密码找回</title>" +
			"</head>" +
			"<body style='font-size:13px;'>" +
			"<ul style='list-style:none; line-height:30px;'>" +
			"<li>尊敬的用户您好:</li>" +
			"<li>有人（可能是您）以e-mail验证的方式提出更改密码的要求。如果不是您，请忽略此消息，一切照旧。</li>" +
			"<li>如果您要求验证，请访问以下URL来更改您的密码:</li>"+
			"<li><a target='_blank' href='http://"+serverUrl+request.getContextPath()+"/verify.jsp?ssologinName="+baseUser.getLoginName()+"&key="+mgukey+"'>"+"http://"+serverUrl+request.getContextPath()+"/verify.jsp?ssologinName="+baseUser.getLoginName()+"&key="+baseUser.getId()+"</a></li>"+
			"<li>用户名:"+baseUser.getLoginName()+"</li>"+
			"<li>不要回复此邮件！</li>"+
			"</ul>" +
			"</body>" +
			"</html>";
			String title="密码找回";
			String smtp = EMailUtils.host;//邮件服务
			String address = EMailUtils.from;//邮件地址
			
			String password = EMailUtils.pwd;//邮件密码
			System.out.println(smtp+"----------邮件服务");
			System.out.println(address+"----------邮件地址");
			System.out.println(password+"----------邮件密码");
			ServerEntity server = new ServerEntity(smtp,address,password);
			MailEntity mail = new MailEntity(title,emailContent,listAddr);
			Boolean b=MailPlugin.sendMail(server,mail);
			request.setAttribute("baseUser", baseUser);
			//发送完毕
		}
		else{
			request.setAttribute("errorType","emailError"); 
			 return forwardUrl;
		}
		return "/find_pwd_success.jsp";
	}
	/**
	 * 验证用户Email是否存在
	 */
	public String validateEmail(){
		String email=request.getParameter("email");
		String result=baseUserManager.validateEmail(email);
		try {
			PrintWriter out=response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String pwValidateUserSave() {
		String newpassword=request.getParameter("newpassword");
		String userId=request.getParameter("userId");
		String mobile=request.getParameter("mobile");
		
		Boolean isPass=false;
		String mobileIsOpen=My431Function.getValueByCode("base.mobile.isopen");
		if(mobileIsOpen!=null&&mobileIsOpen.equals("1")){
			String mobileRand=request.getParameter("mobileRand");
			String sessionId=request.getSession().getId();
			if(!redisManager.objectHasKey(sessionId+"_mobileRand")){
				response.setContentType("text/html;charset=utf-8");
				java.io.PrintWriter pw = null;
				try {
					pw = response.getWriter();
					pw.write("0");
				} catch (java.io.IOException e) {
					e.printStackTrace();
				}
			}else{
				Object o=redisManager.getOValue(sessionId+"_mobileRand");
				String rand="";
				if(o!=null&&!o.equals("")){
					rand=o.toString();
				}
				if(mobileRand!=null&&mobileRand.trim().equals(rand)){
					isPass=true;
				}
			}
		}else{
			isPass=true;
		}
		
		if(isPass){
			
			BaseUser baseUser=baseUserManager.get(userId);
			baseUser.setModTime(new Date());
			baseUser.setModUser(baseUser.getId());
			baseUser.setMobile(mobile);
			if(newpassword!=null&&!newpassword.equals("")){
				baseUser.setPassword(MD5.getMd5(newpassword));
			}
			baseUserManager.update(baseUser);
			baseUserManager.flush();
			baseUserManager.evit(baseUser);
			request.getSession().removeAttribute("ssoUser");
			request.getSession().setAttribute("ssoUser", baseUser);
			
			response.setContentType("text/html;charset=utf-8");
			java.io.PrintWriter pw = null;
			try {
				pw = response.getWriter();
				pw.write("1");
			} catch (java.io.IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}	
	
	/**
	 * 地区统计信息初始化
	 * @return
	 */
	public String initAreaInfo() {
		try {
			BaseUser baseUser = baseUserManager.get(request.getSession().getAttribute("wsuserId").toString());
			BaseArea baseArea = baseAreaManager.get(baseUser.getAreaId());
			String areaCode = baseArea.getAreaCode();
			String areaType = "";
			if(areaCode.length()/3<2){
				areaType = "provice";
			}else if(areaCode.length()/3==2){
				areaType = "twon";
			}else if(areaCode.length()/3==3){
				areaType = "county";
			}
			if(baseArea.getNodeType().equals("0")){
				areaType = "county";
			}
			request.setAttribute("bean", baseArea);
			request.setAttribute("areaType", areaType);
			request.setAttribute("formUrl", "/login/Login/initAreaInfoSave.jspx");
			request.setAttribute("url", request.getAttribute("url")!=null?request.getAttribute("url"):request.getParameter("url"));
			request.setAttribute("key", request.getAttribute("key")!=null?request.getAttribute("key"):request.getParameter("key"));
			request.setAttribute("ssologinName", request.getParameter("ssologinName"));
			
			return "/initAreaInfo.jsp";//进入填写地区统计信息页面
		} catch (Exception e) {
			return "/error.jsp";
		}
	
	}

	public void setServletRequest(HttpServletRequest arg0) {

		this.request=arg0;
	}

	public void setBaseLoginManager(BaseLoginManager baseLoginManager) {
		this.baseLoginManager = baseLoginManager;
	}

	public void setBaseUserManager(BaseUserManager baseUserManager) {
		this.baseUserManager = baseUserManager;
	}


	public void setSession(Map<String, Object> arg0) {

		this.session = arg0;
	}


	public void setServletResponse(HttpServletResponse arg0) {
		this.response=arg0;
	}


	public BaseUser getBaseUser() {
		return baseUser;
	}


	public void setBaseUser(BaseUser baseUser) {
		this.baseUser = baseUser;
	}


	public BasePropertiesManager getBasePropertiesManager() {
		return basePropertiesManager;
	}


	public void setBasePropertiesManager(BasePropertiesManager basePropertiesManager) {
		this.basePropertiesManager = basePropertiesManager;
	}


	public List<BaseProperties> getDutyList() {
		return dutyList;
	}


	public void setDutyList(List<BaseProperties> dutyList) {
		this.dutyList = dutyList;
	}


	public List<BaseProperties> getTitleList() {
		return titleList;
	}


	public List<BaseProperties> getDegreeList() {
		return degreeList;
	}


	public List<BaseProperties> getSubjectList() {
		return subjectList;
	}


	public void setTitleList(List<BaseProperties> titleList) {
		this.titleList = titleList;
	}


	public void setDegreeList(List<BaseProperties> degreeList) {
		this.degreeList = degreeList;
	}


	public void setSubjectList(List<BaseProperties> subjectList) {
		this.subjectList = subjectList;
	}


	public BaseSchool getBaseSchool() {
		return baseSchool;
	}


	public void setBaseSchool(BaseSchool baseSchool) {
		this.baseSchool = baseSchool;
	}


	public BaseSchoolManager getBaseSchoolManager() {
		return baseSchoolManager;
	}


	public void setBaseSchoolManager(BaseSchoolManager baseSchoolManager) {
		this.baseSchoolManager = baseSchoolManager;
	}


	public List<BaseProperties> getSchoolTypeList() {
		return schoolTypeList;
	}


	public List<BaseProperties> getSchoolEdusystemList() {
		return schoolEdusystemList;
	}


	public List<BaseProperties> getSchoolClassnumList() {
		return schoolClassnumList;
	}


	public void setSchoolTypeList(List<BaseProperties> schoolTypeList) {
		this.schoolTypeList = schoolTypeList;
	}


	public void setSchoolEdusystemList(List<BaseProperties> schoolEdusystemList) {
		this.schoolEdusystemList = schoolEdusystemList;
	}


	public void setSchoolClassnumList(List<BaseProperties> schoolClassnumList) {
		this.schoolClassnumList = schoolClassnumList;
	}

	public BaseAreaManager getBaseAreaManager() {
		return baseAreaManager;
	}


	public void setBaseAreaManager(BaseAreaManager baseAreaManager) {
		this.baseAreaManager = baseAreaManager;
	}

	public void prepare() throws Exception {
	}


	public Object getModel() {
		return null;
	}
	
	public String saveEditUserPassword(){
		String loginName=request.getParameter("loginName");
		String key=request.getParameter("key");
		String password=request.getParameter("password");
		BaseUser baseUser=baseUserManager.getUserFindPwd(loginName, key);
		if(baseUser!=null){
			baseUser.setPassword(MD5.getMd5(password));
			baseUserManager.update(baseUser);
		}
		return "/find_success.jsp";
	}
	
	  //获得客户端真实IP地址的方法二：
	  public String getIpAddr(HttpServletRequest request) {  
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
	  /**
		 * 电话号码是否唯一
		 * 
		 */
		public String checkMobile(){
			String mobile=request.getParameter("mobile");
			String id=request.getParameter("userId");
			//check mobile begin
			String returnValue=baseUserManager.getCountByMobile(mobile,id);	
			//check mobile end	
			// 告诉页面返回处理结果 开始

			response.setContentType("text/html;charset=utf-8");
			java.io.PrintWriter pw = null;
			try {
				pw = response.getWriter();
				pw.write(returnValue);
			} catch (java.io.IOException e) {
				returnValue = "1";//异常默认已经存在
				e.printStackTrace();
			}
			// 告诉页面返回处理结果结束
			return null;
		}

		/**
		 * 邮箱号码是否唯一
		 * 
		 */
		public String checkEmail(){
			String email=request.getParameter("email");
			String id=request.getParameter("userId");
			//check email begin
			String returnValue="1";
			if(null!=email&&!email.equals("")&&!email.equals("null")){
				returnValue=baseUserManager.getCountByEmail(email,id);
			}
			//check email end
			// 告诉页面返回处理结果 开始
			response.setContentType("text/html;charset=utf-8");
			java.io.PrintWriter pw = null;
			try {
				pw = response.getWriter();
				pw.write(returnValue);
			} catch (java.io.IOException e) {
				returnValue = "1";//异常默认已经存在
				e.printStackTrace();
			}
			// 告诉页面返回处理结果结束
			
			
			return null;
		}
		/**
		 * 
		* @Description: 初始化用户保存 
		* @author hyl     
		* @date 2017-5-27 上午11:52:26  
		* @version V1.0 
		* @author user
		 */
		public String saveUserEditV2(){
			String userId=request.getParameter("id");
			String areaId=request.getParameter("areaId");
			BaseArea baseArea =CacheBaseAreaManager.getNodeById(areaId);
			BaseUser baseUser=baseUserManager.get(userId);
			String realName=(String)request.getParameter("urealName");
			//baseUser.setRealName(realName);
			//String nickName=(String) request.getParameter("nickname");
			//baseUser.setNickname(nickName);
			String mobilPhone=(String) request.getParameter("mobile");
			baseUser.setMobile(mobilPhone);
			String tel=(String) request.getParameter("officePhone");
			baseUser.setOfficePhone(tel);
			String addr=(String) request.getParameter("address");
			baseUser.setAddress(addr);
			String zipCode=(String) request.getParameter("zipcode");
			baseUser.setZipcode(zipCode);
			String qq=(String) request.getParameter("qq");
			baseUser.setQq(qq);
			String email=(String) request.getParameter("email");
			baseUser.setEmail(email);
			baseUser.setIsIni(1);
			baseUser.setIniTime(new Date());
			baseUserManager.update(baseUser);
			//登录成功，赋予session权限
			request.getSession().setAttribute("wsuserId", baseUser.getId());
			request.getSession().setAttribute("wsloginName", baseUser.getLoginName());
			request.getSession().setAttribute("wsrealname", baseUser.getRealName());
			request.getSession().setAttribute("wsRealname", baseUser.getRealName());
			//request.getSession().setAttribute("wsNickname", baseUser.getNickname());
			request.getSession().setAttribute("ssoUser",baseUser);
			request.getSession().setAttribute("schoolId", baseUser.getSchoolId());
			request.getSession().setAttribute("wsDefaultRoleCode", baseUser.getDefaultRoleCode());
			if(baseUser.getDefaultRoleCode().equals("role.admin")){
				request.getSession().setAttribute("wsAreaCode","-1");
			}else{
				if(baseArea!=null){
					request.getSession().setAttribute("wsAreaCode", baseArea.getAreaCode());
				}else{
					request.getSession().setAttribute("wsAreaCode","-1");
				}
			}
			
			String strKey=baseLoginManager.getRandomString(20);
			String mk="global.base.BaseUser.tempkey.key."+strKey;
			
			request.setAttribute("key", strKey);
			redisManager.setOValue(mk, baseUser);
			//5秒后过期，单位参照
			redisManager.expire(mk, 300l);
			Date realLastLoginTime = baseUser.getLastLoginTime();
			realLastLoginTime = realLastLoginTime == null ? new Date() : realLastLoginTime;
			request.getSession().setAttribute("realLastLoginTime", realLastLoginTime);
			
			baseUser.setLastLoginTime(new Date());
			baseUserManager.save(baseUser);
			baseUserManager.updateReload(baseUser);
			baseUserManager.flush();
			baseUserManager.evit(baseUser);
			
			String url="";
			
			String rc=baseUser.getDefaultRoleCode();
			if(rc.equals("role.editor")){
				rc="role.admin";
			}
			
			BaseRole role=baseRoleManager.getByCode(rc);
			if(role.getDefaultUrl()!=null){
				url=role.getDefaultUrl();
			}
			if(!url.contains("?")){
				url=url+"?";
			}
			request.setAttribute("url", url);
			
			//hmc begin 查询当前用的最后一次通知的时间，用于展示显示通知有几条没有查看过
			BaseNote query= new BaseNote();
			Page page =null;
			int pageSize=10;//
			int pageNo=1;
			//从系统管理员到学校管理员一次一次判断
			String code1=baseUser.getProvinceId();	//当前登录对象的省ID
			String code2=baseUser.getCityId();		//当前登录对象的市id
			String code3=baseUser.getCountyId();	//当前登录对象的县id
			String code4=baseUser.getSchoolId();	//当前对象的学校id
			if(null!=code4&&!code4.equals("1")){//登陆的是学校级管理员
				query.setProvinceCode(code1);
				query.setCityCode(code2);
				query.setCountyCode(code3);
			}else if(null!=code3&&!code3.equals("1")){//县级管理员
				query.setProvinceCode(code1);
				query.setCityCode(code2);
			}else if(null!=code2&&!code2.equals("1")){//市级管理员
				query.setProvinceCode(code1);
			}else if(null!=code1&&!code1.equals("1")){//省级管理员,只用查看系统的通知
				query.setProvinceCode("1");
				query.setCityCode("1");
				query.setCountyCode("1");
			}else{//看看是不是系统管理员
				String userRole=baseUser.getDefaultRoleCode();
				if(null!=userRole&&"role.admin".equals(userRole)){
					return null;
				}
			}
			String NOT_READ = null;
			/*if(query.getProvinceCode()!=null&&baseUser!=null){
				Date lastReadTime=baseUser.getViewNoteDate();
				NOT_READ = baseNoteManager.findNoteIsNotReadNum(query, lastReadTime);
			}
			
			//String NOT_READ=baseNoteManager.getNoteIsNotReadNum(lastReadTime);
			request.getSession().setAttribute("NOT_READ", NOT_READ);*/
			
			//修改登录次数
			Integer loginCnt=baseUser.getLoginCnt();
			if(!isNullOrEmptyString(loginCnt)){
				loginCnt=baseUser.getLoginCnt()+1;
			}else{
				loginCnt=1;
			}
			//最后登录ip
			request.getRemoteAddr();
			baseUser.setLastLoginIp(request.getRemoteAddr());
			baseUser.setLoginCnt(loginCnt);
			baseUserManager.save(baseUser);
			baseUserManager.updateReload(baseUser);
			CacheBaseUserManager.delBaseUser(baseUser.getId());
			CacheBaseUserManager.setBaseUser(baseUser);
			request.getSession().removeAttribute("ssoUser");
			request.getSession().setAttribute("ssoUser",baseUser);
			//hmc end
			/**********系统的真实姓名初始化是：地区+管理员，若发生了变化，则显示：修改后姓名(区域+角色（管理员|视察员）)，否则只显示：真是姓名****************************/
			String loginIniName="";
			BaseArea baseArea1 =CacheBaseAreaManager.getNodeById(baseUser.getAreaId());
			if(baseArea1!=null){
				String inirealName="";
				if(baseUser.getDefaultRoleCode().equals("role.areaObserve")){
					inirealName=baseArea1.getAreaName()+"视察员";
				}else if(baseUser.getDefaultRoleCode().equals("role.schoolManager")){
					BaseSchool baseSchool= CacheBaseSchoolManager.getSchool(baseUser.getSchoolId());
					if(baseSchool!=null){
						inirealName=baseSchool.getSchoolName()+"管理员";
					}
				}else if(baseUser.getDefaultRoleCode().equals("role.provinceManager")||baseUser.getDefaultRoleCode().equals("role.cityManager")||baseUser.getDefaultRoleCode().equals("role.countryManager")){
					inirealName=baseArea1.getAreaName()+"管理员";
				}else{
					inirealName=baseUser.getRealName();
				}
				if(StringUtils.isNotBlank(baseUser.getRealName())){
					if(!baseUser.getRealName().trim().equals(inirealName)){
						loginIniName=baseUser.getRealName()+"("+inirealName+")";
					}else {
						loginIniName=baseUser.getRealName();
					}
				}else {
					loginIniName=inirealName;
				}
			}
			if(StringUtils.isNotBlank(loginIniName)){
				request.getSession().setAttribute("loginIniName", loginIniName);
			}else {
				if(StringUtils.isNotBlank(baseUser.getRealName())){
					request.getSession().setAttribute("loginIniName", baseUser.getRealName());
				}
			}
			/**************************************/
			return null;
		}
		/**
		 * 
		* @Description: 省市县首页  
		* @author hyl     
		* @date 2017-6-6 上午11:22:27  
		* @version V1.0 
		* @author user
		 */
		public String homePage() {
			request.setAttribute("menuFlag", "sindex");
			Object o=request.getSession().getAttribute("ssoUser");
			if(o!=null){
				BaseUser baseUser=(BaseUser)o;
				request.setAttribute("baseUser", baseUser);
			}
			
			
			return HOME_PAGE_JSP;
		}
}