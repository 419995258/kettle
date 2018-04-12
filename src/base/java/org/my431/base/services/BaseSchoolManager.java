/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2011
 */

package org.my431.base.services;

import java.io.File;
import java.io.IOException;
import java.util.Date;


import org.my431.base.model.BaseSchool;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.util.DateFormater;
import org.my431.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.Result;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.RepositoryPluginType;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.repository.RepositoriesMeta;
//import org.pentaho.di.repository.Repository;//跟上面的冲突
import org.pentaho.di.repository.RepositoryDirectoryInterface;
import org.pentaho.di.repository.RepositoryMeta;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepositoryMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

@Repository
public class BaseSchoolManager extends HibernateXsqlBuilderQueryDao<BaseSchool> {
	private static Logger logger = LoggerFactory.getLogger(BaseSchoolManager.class);
	private static String IP="192.168.3.55";
	//private static String IP="10.180.230.6";
	private static String IPPORT="1521";
	private static String SID="orcl";
	private static String DBNAME="KTL_TIM_MIS";
	private static String USERNAME="KTL_TIM_MIS";
	private static String PASSWORD="love431";
	private static String DIR="/ktl_tim_mis/";
	//private static final String KETTLE_PLUGIN_BASE_FOLDER = "F:\\Kettle\\pdi-ce-6.1.0.1-196\\data-integration\\plugins\\";
	private static final String KETTLE_PLUGIN_BASE_FOLDER = "C:\\other\\kattle\\pdi-ce-6.1.0.1-196\\data-integration\\plugins\\";
	//private static final String KETTLE_HOME = "F:\\Kettle\\pdi-ce-6.1.0.1-196\\data-integration\\";
	private static final String KETTLE_HOME = "C:\\other\\kattle\\pdi-ce-6.1.0.1-196\\data-integration\\";
	

	private static org.pentaho.di.repository.Repository repository;
	public void init() {
		try {
			//这里是设置我们pentaho的运行环境,实际上我们加入的maven依赖只是为了让代码编译通过,实际真正干活的还是pentaho自己
			//也可以自己建个maven的私服把pentaho里面的运行环境的jar丢上去,就能通过java调了
			// 获得执行类的当前路径  
			String user_dir = System.getProperty("user.dir");
			System.setProperty("KETTLE_PLUGIN_BASE_FOLDERS", KETTLE_PLUGIN_BASE_FOLDER);
			System.setProperty("KETTLE_HOME", KETTLE_HOME);
			System.setProperty("user.dir", KETTLE_HOME);
	        // 运行环境初始化（设置主目录、注册必须的插件等）  
	        KettleEnvironment.init();  
	        // Kettle初始化完毕，还原执行类的当前路径  
	        System.setProperty("user.dir", user_dir); 
			
			//KettleEnvironment.init();
			//创建资源库对象，此时的对象还是一个空对象
			KettleDatabaseRepository repository = new KettleDatabaseRepository();
			//创建资源库数据库对象，类似我们在spoon里面创建资源库
			//（数据库连接名称，数据库类型，连接方式，IP，数据库名，端口，用户名，密码）
			DatabaseMeta dataMeta = null;
			try {
				System.out.println("--------------------------1");
				dataMeta = new DatabaseMeta(DBNAME,"Oracle","Native",IP,SID,IPPORT,USERNAME,PASSWORD); 
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("--------------------------2");
			}
			//资源库元对象,选择资源库(ID,名称，描述)
			//http://blog.csdn.net/huangyanlong/article/details/46117065
			KettleDatabaseRepositoryMeta kettleDatabaseMeta =new KettleDatabaseRepositoryMeta("KTL_TIM_MIS", "KTL_TIM_MIS", "KTL_TIM_MIS",dataMeta);
			//给资源库赋值
			repository.init(kettleDatabaseMeta);
			//连接资源库
			repository.connect("admin","admin");
			BaseSchoolManager.repository = repository;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
    /** 
     * Kettle执行Job 
     * <br/>
     * dir="/myDemo/" 资源库目录
     * @throws KettleException 
     */  
    public void executeJob(String dir, String jobName) throws KettleException {  
    	long t1=0;
		long t2=0;
    	/*****第一种方式****************/  
        /*RepositoriesMeta repositoriesMeta = new RepositoriesMeta();  
        // 从文件读取登陆过的资源库信息  
        repositoriesMeta.readData();  
        // 选择登陆过的资源库  
        RepositoryMeta repositoryMeta = repositoriesMeta.findRepository("4_2");  
        // 获得资源库实例  
        Repository repository = PluginRegistry.getInstance().loadClass(RepositoryPluginType.class, repositoryMeta, Repository.class);  
        repository.init(repositoryMeta);  
        // 连接资源库  
        repository.connect("admin", "admin");  
  
        RepositoryDirectoryInterface tree = repository.loadRepositoryDirectoryTree();  
        RepositoryDirectoryInterface fooBar = tree.findDirectory(dir);  
        JobMeta jobMeta = repository.loadJob(jobname, fooBar, null, null);  
        // 执行指定作业  
        Job job = new Job(repository, jobMeta);  
        job.start();  
        job.waitUntilFinished();  
        Result result = job.getResult();  
        result.getRows();  
        if (job.getErrors() > 0) {  
            throw new RuntimeException("There were errors during transformation execution.");  
        }  
        repository.disconnect();*/  
    	/*****第二种方式****************/
    	//根据变量查找到模型所在的目录对象
    	RepositoryDirectoryInterface directory = repository.findDirectory(dir);
    	String excuteType = jobName.substring(jobName.lastIndexOf(".")+1,jobName.length());
    	jobName = jobName.substring(0,jobName.lastIndexOf("."));
    	try {
    		if (excuteType.equals("job")) {
    			JobMeta meta = ((org.pentaho.di.repository.Repository) repository).loadJob(jobName,directory, null, null);
    			Job job = new Job(repository, meta);
    			job.run();
    			job.waitUntilFinished();
    			if (job.getErrors() > 0) {
    				System.err.println("JobName:"+jobName+"#########"+excuteType+":Transformation run Failure!");
    			} else {
    				System.out.println("JobName:"+jobName+"#########"+excuteType+":Transformation run successfully!");
    			}
    		} else {
    			TransMeta transformationMeta = ((org.pentaho.di.repository.Repository) repository).loadTransformation(jobName, directory, null, true,null);
    			Trans trans = new Trans(transformationMeta);
    			trans.execute(null);
    			t1=System.currentTimeMillis();
    			trans.waitUntilFinished();
    			t2=System.currentTimeMillis();
    			if (trans.getErrors() > 0) {
    				System.out.println("KtrName:"+jobName+"#########"+excuteType+":Transformation run Failure!");
    				FileUtil.writeLogTxt("log.txt","Failure!!!The "+jobName+" use time :"+((t2-t1)/1000)+"s");
    			} else {
    				System.out.println("KtrName:"+jobName+"#########"+excuteType+":Transformation run successfully!");
    				FileUtil.writeLogTxt("log.txt","successfully!!!The "+jobName+" use time :"+((t2-t1)/1000)+"s");
    			}
    		}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			//repository.disconnect();
		}
    } 
	/**
	 * 
	* @Description: 教职工基本信息表
	* @author hyl     
	* @date 2017-11-22 上午10:24:59  
	* @version V1.0 
	* @author user
	 */
	public void updateNationData01(){
		try {
			FileUtil.writeLogTxt("log.txt","The Timing task execution start time:"+DateFormater.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			init();
			executeJob(DIR, "TB_BIZ_JSDQZCXX_教师定期注册信息表.ktr");
			executeJob(DIR, "TB_BIZ_JZGQTJNXX_教职工其他技能信息表.ktr");
			executeJob(DIR, "TB_BIZ_JZGGWPRXX_教职工岗位聘任信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGZLCGXX_教职工专利成果信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JSSCZCXX_教师首次注册信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JSZGXX_教师资格信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGGJBZHHYBZXX_教职工国家标准或行业标准信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGGJYYZSXX_教职工国家医药证书信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGGNPXXX_教职工国内培训信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGGZJLXX_教职工工作经历信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGHWYXXX_教职工海外研修信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGJBDYXX_教职工基本待遇信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGJLLGXX_教职工交流轮岗信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGJXKYHJXX_教职工获奖信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGJYJXXX_教职工教育教学信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGKJLWXX_教职工论文信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGKJXMXX_教职工科技项目信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGKJZZXX_教职工著作信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGLXFSXX_教职工联系方式信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGSDCFXX_教职工师德处分信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGNDKHXX_教职工年度考核信息表.ktr");
        	
        	executeJob(DIR, "TB_BIZ_JZGRXRCXMXX_教职工入选人才项目信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGSDKHXX_教职工师德考核信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGWYZPXX_教职工文艺作品信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGSDRYXX_教职工师德荣誉信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGXXJLXX_教职工学习经历信息表.ktr");
        	
        	executeJob(DIR, "TB_BIZ_JZGYYNLXX_教职工语言能力信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGZDXSCJJSHJXX_教职工指导学生参加竞赛获奖信息表.ktr");
        	executeJob(DIR, "TB_BIZ_PXJG_培训结果表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGZSXX_教职工证书信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGZXBGHYJBGXX_教职工咨询报告或研究报告信息表.ktr");
        	executeJob(DIR, "TB_BIZ_JZGZYJSZWPRXX_教职工专业技术职务聘任信息表.ktr");
        	executeJob(DIR, "TB_CFG_ZDXB_字典项表.ktr");
        	executeJob(DIR, "TB_BIZ_PXJGBL_培训结果补录表.ktr");
        	executeJob(DIR, "TB_BIZ_PXXM_培训项目表.ktr");
        	executeJob(DIR, "TB_CFG_CONF_DIC_配置信息字典表.ktr");
        	executeJob(DIR, "TB_CFG_ZDB_字典表.ktr");
        	executeJob(DIR, "TB_CFG_ZDXXB_字段信息表.ktr");
        	executeJob(DIR, "XXJGBSM_附设班代码表_前置表.ktr");
        	executeJob(DIR, "TB_EDI_JYGLBMDM_教育管理部门代码表_前置表.ktr");
        	executeJob(DIR, "TB_EDI_XXJGDM_学校机构代码表_前置表.ktr");
        	executeJob(DIR, "TB_MST_FSBDM_附设班代码表.ktr");
        	executeJob(DIR, "TB_MST_JYGLBMDM_教育管理部门代码表.ktr");
        	executeJob(DIR, "TB_MST_XXJGDM_学校(机构)代码表.ktr");
        	executeJob(DIR, "TB_MST_ZXDFZYJYGLBMDM_最新地方自用教育管理部门代码表.ktr");
        	FileUtil.writeLogTxt("log.txt","The Timing task execution end time:"+DateFormater.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		} catch (KettleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			repository.disconnect();
		}
	}
}
