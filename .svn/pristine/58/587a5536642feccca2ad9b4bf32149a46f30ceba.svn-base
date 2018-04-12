/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2015
 */

package org.my431.base.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.my431.base.model.BaseProTeacherMap;
import org.my431.base.model.BaseProjectFile;
import org.my431.base.model.BaseUser;
import org.my431.base.services.BaseProTeacherMapManager;
import org.my431.base.services.BaseProjectFileManager;
import org.my431.base.services.BaseUserManager;
import org.my431.base.services.PageManager;
import org.my431.platform.dao.support.Page;
import org.my431.platform.web.StrutsTreeEntityAction;
import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */


public class BaseProjectFileAction extends StrutsTreeEntityAction<BaseProjectFile,BaseProjectFileManager> implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "//base/BaseProjectFile/query.jsp";
	protected static final String LIST_JSP= "//base/BaseProjectFile/list.jsp";
	protected static final String CREATE_JSP = "//base/BaseProjectFile/create.jsp";
	protected static final String EDIT_JSP = "//base/BaseProjectFile/edit.jsp";
	protected static final String SHOW_JSP = "//base/BaseProjectFile/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!//base/BaseProjectFile/list.jspx";
	protected static final String GET_PROJECTFILE_JSP="//base/BaseProjectFile/get_projectfile.jsp";
	protected static final String GET_PROJECTFILE_LIST_JSP="/base/BaseProjectFile/get_projectfile_list.jsp";
	protected static final String TEACHER_SCORE_PROJECTFILE_LIST_JSP="/base/BaseProjectFile/teacher_score_projectfile_list.jsp";
	//protected static final String GET_PROJECTFILE_LIST_JSP="/base/BaseProjectFile/get_projectfile_list.jsp";
	protected static final String GET_ACOUNTFILE_LIST_JSP="/base/BaseProjectFile/get_acountfile_list.jsp";
	protected static final String VIEW_ACOUNTFILE_JSP="/base/BaseProjectFile/view_acountfile.jsp";
	protected static final String EDIT_FILE_JSP="/base/BaseProjectFile/edit_file.jsp";
	protected static final String SHOW_PIC_LIST_JSP="/base/BaseProjectFile/show_pic_list.jsp";
	protected static final String SHOW_FGB_PIC_LIST_JSP="/base/BaseProjectFile/show_fgbpic_list.jsp";
	protected static final String GET_FGB_FILE_JSP="/base/BaseProjectFile/get_fgb_file.jsp";
	private BaseProjectFileManager baseProjectFileManager;
	@Autowired
	private BaseProTeacherMapManager baseProTeacherMapManager;
	@Autowired
	private BaseUserManager baseUserManager;
	
	
	private BaseProjectFile baseProjectFile;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			baseProjectFile = new BaseProjectFile();
		} else {
			baseProjectFile = (BaseProjectFile)baseProjectFileManager.get(id);
		}
	}
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseProjectFileManager(BaseProjectFileManager manager) {
		this.baseProjectFileManager = manager;
	}	
	
	public Object getModel() {
		return baseProjectFile;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		BaseProjectFile query = new BaseProjectFile();
		
		int pageNo=1;
		if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
			pageNo=Integer.valueOf(request.getParameter("pageNo"));
		}
		int pageSize=10;
		if(request.getParameter("pageSize")!=null&&!request.getParameter("pageSize").equals("")){
			pageSize=Integer.valueOf(request.getParameter("pageSize"));
		}
		Page page = baseProjectFileManager.findPage(query,pageSize,pageNo);
		PageManager pm = new PageManager((int)page.getTotalCount(), pageSize,pageSize);
		pm.goToPage(pageNo);
		request.setAttribute("dataList", page.getResult());   
	    request.setAttribute("pageNo", pageNo);
	    request.setAttribute("pageSize", pageSize);
	    request.setAttribute("pageHtml", pm.getPageCode());
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/**
	 * 增加文件
	 * author  90  
	 * on 2015-4-1
	 * @return
	 */
	public String create() {
		return CREATE_JSP;
	}
	
	/**
	 * 保存商业文件
	 * author  90  
	 * on 2015-4-3
	 * @return
	 */
	public String save() {
		String fileType=request.getParameter("fileType");
		String userId=String.valueOf(request.getSession().getAttribute("wsuserId"));
		String pid=request.getParameter("pid");
//		String fileName=request.getParameter("fileName");
//		String title=request.getParameter("title");
//		String sourceUrl=request.getParameter("sourceUrl");
//		String fileExt=request.getParameter("fileExt");
//		String fileSize=request.getParameter("fileSize");
//		BaseProjectFile bf=new BaseProjectFile();
//		bf.setFileType(fileType);
//		bf.setCreTime(new Date());
//		bf.setProId(pid);
//		bf.setCreUser(userId);
//		bf.setSourceUrl(sourceUrl);
//		fileExt=fileExt.toLowerCase();
//		if(fileExt.indexOf(".")!=-1){
//			bf.setFileExt(fileExt.substring(1, fileExt.length()));
//		}else{
//			bf.setFileExt(fileExt);
//		}
//		
//		bf.setFileName(title);//文件名
//		bf.setTitle(fileName);
//		bf.setFileSize(Integer.valueOf(fileSize));
//		baseProjectFileManager.save(bf);
		
		String[] mUpUrls=request.getParameterValues("mUpUrl");
		String[] mUpNames=request.getParameterValues("mUpName");
		String[] mUpSizes=request.getParameterValues("mUpSize");
		String[] mUpExts=request.getParameterValues("mUpExt");
		if(mUpUrls!=null&&mUpUrls.length>0){
			for(int i=0;i<mUpUrls.length;i++){
				String sourceUrl=mUpUrls[i];
				String fileExt=mUpExts[i];
				String fileName=mUpNames[i];
				String fileSize=mUpSizes[i];
				BaseProjectFile bf=new BaseProjectFile();
				bf.setFileType(fileType);
				bf.setCreTime(new Date());
				bf.setProId(pid);
				bf.setCreUser(userId);
				bf.setSourceUrl(sourceUrl);
				if(fileExt!=null&&fileExt.length()>1){
					bf.setFileExt(fileExt);
				}
				
				bf.setFileName(fileName);//文件名
				bf.setTitle(fileName);
				bf.setFileSize(Integer.valueOf(fileSize));
				baseProjectFileManager.save(bf);
			}
		}
		
		
		return null;
	}
	/**
	 * 
	* @Description:校本项目成绩导入
	* @author hyl     
	* @date 2017-6-29 下午5:11:12  
	* @version V1.0 
	* @author user
	 */
	public void saveTeacherScores() {
		Object o=request.getSession().getAttribute("ssoUser");
		if(o!=null){
			BaseUser baseUser=(BaseUser)o;
			String fileType=request.getParameter("fileType");
			String proId=request.getParameter("proId");
			
			String[] mUpUrls=request.getParameterValues("mUpUrl");
			String[] mUpNames=request.getParameterValues("mUpName");
			String[] mUpSizes=request.getParameterValues("mUpSize");
			String[] mUpExts=request.getParameterValues("mUpExt");
			if(mUpUrls!=null&&mUpUrls.length>0){
				for(int i=0;i<mUpUrls.length;i++){
					String sourceUrl=mUpUrls[i];
					String fileExt=mUpExts[i];
					String fileName=mUpNames[i];
					String fileSize=mUpSizes[i];
					BaseProjectFile bf=new BaseProjectFile();
					bf.setFileType(fileType);
					bf.setCreTime(new Date());
					bf.setProId(proId);
					bf.setCreUser(baseUser.getId());
					bf.setSourceUrl(sourceUrl);
					if(fileExt != null && fileExt.length() > 1){
						bf.setFileExt(fileExt);
					}
					bf.setFileName(fileName);//文件名
					bf.setTitle(fileName);
					bf.setFileSize(Integer.valueOf(fileSize));
					try {
						baseProjectFileManager.save(bf);
						renderHtml(response, "1");
					} catch (Exception e) {
						// TODO: handle exception
						renderHtml(response, "0");
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	
	/**进入更新页面*/
	public String edit() {
		request.setAttribute("baseProjectFile", baseProjectFile);
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		baseProjectFileManager.update(this.baseProjectFile);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		if(!isNullOrEmptyString(id)){
			baseProjectFileManager.removeById(id);
		}
		return null;
	}

	/**
	 * 获得项目文件
	 * author  90  
	 * on 2015-4-3
	 * @return
	 */
	public String getProjectFile(){
		String pid=request.getParameter("pid");//项目id
		request.setAttribute("pid",pid);
		Map<String ,List> map=this.baseProjectFileManager.getProjectFileByType(pid);
		request.setAttribute("map", map);	
		return GET_PROJECTFILE_JSP;
	}
	
	/**
	 * 项目文件
	 * author  90  
	 * on 2015-5-12
	 * @return
	 */
	public String getProjectFileList(){
		String sb=request.getParameter("sb");//如果该值存在就是设备类型
		request.setAttribute("sb",sb);
		String pid=request.getParameter("pid");
		request.setAttribute("pid", pid);
		String flag=request.getParameter("flag");
		request.setAttribute("flag", flag);
		if(StringUtils.isNotBlank(pid)){
			//BaseAreaProject baseAreaProject=baseAreaProjectManager.get(pid);
			//setAttribute("baseAreaProject", baseAreaProject);
		}
		
		String type=request.getParameter("type");
		
		if(isNullOrEmptyString(type)){
            type="project.file.type.shenpi";
		}
		request.setAttribute("type",type);
		int pageNo=1;
		if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
			pageNo=Integer.valueOf(request.getParameter("pageNo"));
		}
		int pageSize=500;
		if(request.getParameter("pageSize")!=null&&!request.getParameter("pageSize").equals("")){
			pageSize=Integer.valueOf(request.getParameter("pageSize"));
		}
		/*//PageManager pm = new PageManager((int)page.getTotalCount(), pageSize,pageSize);
		//pm.goToPage(pageNo);
		request.setAttribute("dataList", page.getResult());*/
		Map<String,List> mrlt=baseProjectFileManager.getProjectFileV2(pid, type);
		request.setAttribute("mrlt", mrlt);
		if (StringUtils.isNotBlank(flag)) {
			if ("teacher.score".equals(flag)) {
				return TEACHER_SCORE_PROJECTFILE_LIST_JSP;
			}
		}else {
			return GET_PROJECTFILE_LIST_JSP;
		}
		return null;
	}
	
	
	
	/**
	 * 项目文件
	 * author  90  
	 * on 2015-5-12
	 * @return
	 */
	public String getAcountFileList(){
		String tab=request.getParameter("tab");
		request.setAttribute("tab", tab);
		String acountId=request.getParameter("acountId");
		request.setAttribute("acountId", acountId);
		int pageNo=1;
		if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
			pageNo=Integer.valueOf(request.getParameter("pageNo"));
		}
		int pageSize=500;
		if(request.getParameter("pageSize")!=null&&!request.getParameter("pageSize").equals("")){
			pageSize=Integer.valueOf(request.getParameter("pageSize"));
		}
		Page page =new Page();
		if(!isNullOrEmptyString(acountId)){
			page= baseProjectFileManager.getProjectFile(acountId, "", pageSize, pageNo);
		}
		
		//PageManager pm = new PageManager((int)page.getTotalCount(), pageSize,pageSize);
		//pm.goToPage(pageNo);
		request.setAttribute("dataList", page.getResult());
		//request.setAttribute("pageHtml", pm.getPageCode());
		return GET_ACOUNTFILE_LIST_JSP;
	}
	
	/**
	 * 修改文件
	 * author  90  
	 * on 2015-6-8
	 * @return
	 */
	public String editFile(){
		String fileId=request.getParameter("fileId");
		request.setAttribute("file", this.baseProjectFileManager.get(fileId));
		return EDIT_FILE_JSP;
	}
	
	/**
	 * 更新文件
	 * author  90  
	 * on 2015-6-8
	 * @return
	 */
	public String updateFile(){
		String fileId=request.getParameter("fileId");
		BaseProjectFile bf=this.baseProjectFileManager.get(fileId);
		String fileType=request.getParameter("fileType");
		String fileName=request.getParameter("fileName");
		String title=request.getParameter("title");
		String sourceUrl=request.getParameter("sourceUrl");
		String fileExt=request.getParameter("fileExt");
		String fileSize=request.getParameter("fileSize");
		bf.setFileType(fileType);
		bf.setSourceUrl(sourceUrl);
		if(fileExt.indexOf(".")>-1){
			bf.setFileExt(fileExt.substring(1, fileExt.length()));
		}else{
			bf.setFileExt(fileExt);
		}
		bf.setFileName(title);//文件名
		bf.setTitle(fileName);
		bf.setFileSize(Integer.valueOf(fileSize));
		baseProjectFileManager.update(bf);
		return null;
	}
	
	/**
	 * 获取图片列表预览
	 * author  90  
	 * on 2016-3-15
	 * @return
	 */
	public String showPicList(){
		String pid=request.getParameter("pid");
		//BaseAreaProject bp=baseAreaProjectManager.get(pid);
		BaseProTeacherMap baseProTeacherMap = baseProTeacherMapManager.get(pid);
		if(baseProTeacherMap != null){
			BaseUser baseUser = baseUserManager.getBaseUserByUserId(baseProTeacherMap.getTid());
			String foderName=baseUser.getRealName();
			request.setAttribute("foderName", foderName);
			String type=request.getParameter("type");
			String dateStr=request.getParameter("dateStr");
			String picId=request.getParameter("picId");
			Map<String,List> mrlt=baseProjectFileManager.getProjectFileV2(pid, type);
			List list=mrlt.get(dateStr);
			String downLoadStr="";
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					Map<String,Object> thisMap=(Map<String, Object>) list.get(i);
					if(isNullOrEmptyString(downLoadStr)){
						downLoadStr=thisMap.get("SOURCE_URL").toString();//+","+thisMap.get("FILE_NAME").toString();;
					}else{
						downLoadStr+=";"+thisMap.get("SOURCE_URL").toString();//+","+thisMap.get("FILE_NAME").toString();;
					}
				}
				
			}
			request.setAttribute("downLoadStr", downLoadStr);
			request.setAttribute("dataList", list);
			request.setAttribute("picId", picId);
		}
		return SHOW_PIC_LIST_JSP;
	}
	
	/**
	 * 获取图片列表预览
	 * author  90  
	 * on 2016-3-15
	 * @return
	 */
	public String showAccountPicList(){
		return SHOW_PIC_LIST_JSP;
	}
}
