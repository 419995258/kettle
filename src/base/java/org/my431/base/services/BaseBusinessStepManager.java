/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package org.my431.base.services;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ognl.SetPropertyAccessor;

import oracle.net.aso.k;

import org.my431.base.model.BaseBusinessStep;
import org.my431.base.model.BaseTeacherCreditsSum;
import org.my431.base.model.BaseToDo;
import org.my431.base.model.BaseUser;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.my431.platform.utils.ContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

@Repository
public class BaseBusinessStepManager extends HibernateXsqlBuilderQueryDao<BaseBusinessStep>{

	@Autowired
	private BaseToDoManager baseToDoManager;
	public Class getEntityClass() {
		return BaseBusinessStep.class;
	}
	
	
	
	/**
	 * 获得最大轮数
	 * @author 90
	 * @date    2017-6-20
	 * @param stepId
	 * @return
	 */
	public Integer getMaxStepRound(String objectId){
		Integer rlt=0;
		Map<String, Object> values=new HashMap<String, Object>();
		values.put("objectId", objectId);
		List<Map<String, Object>> list=this.getNamedQuery("misBase::BaseBusinessStep::getMaxStepRound", values);
		if(list!=null&&list.size()>0){
			Map<String, Object> thisMap=list.get(0);
			rlt=thisMap.get("NUM")==null?0:Integer.valueOf(thisMap.get("NUM").toString());
		}
		return rlt;
	}
	
	
	/**
	 * 更新步骤
	 * @author 90
	 * @date    2017-6-20
	 * @param id
	 * @param stepType
	 * @param result 1：同意，0：拒绝
	 * @return
	 */
	public String updateStep(String id,String stepType,String result,String comment,String userId){
		Integer round=this.getMaxStepRound(id);
		if("step.type.AnnualCredit.apply".equals(stepType)){//教师申请，第一步需要将该值加1
			round++;
		}
		if("step.type.CreditProject.Apply".equals(stepType)){//项目申请，第一步需要将该值加1
			round++;
		}
		if("step.type.1stReg.Apply".equals(stepType)){//教师资格注册，第一步需要将该值加1
			round++;
		}
		if("step.type.RegularReg.Apply".equals(stepType)){//教师资格定期注册，第一步需要将该值加1
			round++;
		}
		BaseBusinessStep step=new BaseBusinessStep();
		step.setBusinessFlowId(id);
		step.setBusinessStepKey(stepType);
		step.setStepDealReulst(Integer.valueOf(result));
		step.setDeleteFlag(0);
		step.setStepDealCommentJason(comment);
		step.setStepDealUserJason(userId);
		step.setStepDealTimeJason(new Date());
		step.setBusinessStepRound(round);
		this.save(step);
		
		//待办事项通知,更新学分登记表的状态
		if(stepType.contains("AnnualCredit")){//学分申请
			this.createXfToList(userId, result, stepType, id);
			BaseTeacherCreditsSumManager baseTeacherCreditsSumManager=(BaseTeacherCreditsSumManager) ContextUtils.getBean("baseTeacherCreditsSumManager");
			if("0".equals(result)){//拒绝
				baseTeacherCreditsSumManager.updateXfStepType(id, "");
			}else{
				baseTeacherCreditsSumManager.updateXfStepType(id, stepType);
			}
		}
		return "success";
	}
	
	/**
	 * 获得步骤状态
	 * @author 90
	 * @date    2017-6-20
	 * @param objectId
	 * @return
	 */
	public Map<String, Integer> getStepStatus(String objectId){
		Map<String, Integer> mapRlt=new HashMap<String, Integer>();
		Integer round=this.getMaxStepRound(objectId);
		Map<String, Object> values=new HashMap<String, Object>();
		values.put("round", round);
		values.put("objectId", objectId);
		List<Map<String,Object>> list=this.getNamedQuery("misBase::BaseBusinessStep::getStepStatus", values);
		if(list!=null&&list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				Map<String,Object> thisMap=list.get(i);
				mapRlt.put(thisMap.get("BUSINESS_STEP_KEY").toString(), Integer.valueOf(thisMap.get("STEP_DEAL_REULST").toString()));
			}
		}
		
		return mapRlt;
	}

	/**
	 * 
	* @Description: 保存业务同时新增待办事项
	* @author hyl     
	* @date 2017-6-20 上午11:07:24  
	* @version V1.0 
	* @author user
	 */
	public void saveBusinessStep(BaseBusinessStep baseBusinessStep,BaseToDo baseToDo) {
		save(baseBusinessStep);
		baseToDoManager.save(baseToDo);
	}
	
	/**
	 * 获得学分登记的操作进度
	 * @author 90
	 * @date    2017-6-20
	 * @param map 1可以操作，0,不能操作 
	 * @return
	 */
	public Map<String, Integer> getXfdjCzjd(Map<String, Integer> map){
		Map<String, Integer> mapRlt=new HashMap<String, Integer>();//0可以操作，1,不能操作
		if(map.isEmpty()){//说明可以申请
			mapRlt.put("step.type.AnnualCredit.apply", 1);//教师申请
			mapRlt.put("step.type.AnnualCredit.Check", 0);//学校审查
			mapRlt.put("step.type.AnnualCredit.Registration", 0);//上级登记
			mapRlt.put("step.type.AnnualCredit.Audit", 0);//审核
			mapRlt.put("step.type.AnnualCredit.Record", 0);//省级备案
		}else{
			Integer flag=0;
			for (String key:map.keySet()) {
				Integer thisValue=map.get(key);
				if(thisValue==0){//有拒绝的环节
					flag=1;
					break;
				}
			}
			if(flag==1){//教师可以发起新一轮的申请
				mapRlt.put("step.type.AnnualCredit.apply", 1);//教师申请
				mapRlt.put("step.type.AnnualCredit.Check", 0);//学校审查
				mapRlt.put("step.type.AnnualCredit.Registration", 0);//上级登记
				mapRlt.put("step.type.AnnualCredit.Audit", 0);//审核
				mapRlt.put("step.type.AnnualCredit.Record", 0);//省级备案
			}else{//没有为遭拒绝的
				mapRlt.put("step.type.AnnualCredit.apply", 0);
				if(!map.containsKey("step.type.AnnualCredit.Check")){//该学校审查了
					mapRlt.put("step.type.AnnualCredit.Check", 1);//学校审查
					mapRlt.put("step.type.AnnualCredit.Registration", 0);//上级登记
					mapRlt.put("step.type.AnnualCredit.Audit", 0);//审核
					mapRlt.put("step.type.AnnualCredit.Record", 0);//省级备案
				}else if(map.get("step.type.AnnualCredit.Check")==1&&!map.containsKey("step.type.AnnualCredit.Registration")){//该上级登记了
					mapRlt.put("step.type.AnnualCredit.Check", 0);//学校审查
					mapRlt.put("step.type.AnnualCredit.Registration", 1);//上级登记
					mapRlt.put("step.type.AnnualCredit.Audit", 0);//审核
					mapRlt.put("step.type.AnnualCredit.Record", 0);//省级备案
				}else if(map.get("step.type.AnnualCredit.Registration")==1&&!map.containsKey("step.type.AnnualCredit.Audit")){//该审查了
					mapRlt.put("step.type.AnnualCredit.Check", 0);//学校审查
					mapRlt.put("step.type.AnnualCredit.Registration", 0);//上级登记
					mapRlt.put("step.type.AnnualCredit.Audit", 1);//审核
					mapRlt.put("step.type.AnnualCredit.Record", 0);//省级备案
				}else if(map.get("step.type.AnnualCredit.Audit")==1&&!map.containsKey("step.type.AnnualCredit.Record")){//该省级备案了
					mapRlt.put("step.type.AnnualCredit.Check", 0);//学校审查
					mapRlt.put("step.type.AnnualCredit.Registration", 0);//上级登记
					mapRlt.put("step.type.AnnualCredit.Audit", 0);//审核
					mapRlt.put("step.type.AnnualCredit.Record", 1);//省级备案
				}else if(map.get("step.type.AnnualCredit.Record")==1){//该项目到此结束
					mapRlt.put("step.type.AnnualCredit.Check", 0);//学校审查
					mapRlt.put("step.type.AnnualCredit.Registration", 0);//上级登记
					mapRlt.put("step.type.AnnualCredit.Audit", 0);//审核
					mapRlt.put("step.type.AnnualCredit.Record", 0);//省级备案
				}
			}
		}
		return mapRlt;
	}
	
	/**
	 * 创建学分代办事项
	 * @author 90
	 * @date    2017-6-21
	 * @param formUserId
	 * @param toUserId
	 * @param result
	 * @param stepType
	 * @param objectId
	 * @return
	 */
	public String createXfToList(String formUserId,String result,String stepType,String objectId){
		BaseTeacherCreditsSumManager baseTeacherCreditsSumManager=(BaseTeacherCreditsSumManager) ContextUtils.getBean("baseTeacherCreditsSumManager");
		BaseToDoManager baseToDoManager=(BaseToDoManager) ContextUtils.getBean("baseToDoManager");
		String url="/base/BaseTeacherCreditsSum/viewXfdj.jspx?xfdjId="+objectId;
		BaseTeacherCreditsSum sum=baseTeacherCreditsSumManager.get(objectId);
		if("step.type.AnnualCredit.apply".equals(stepType)){//教师申请
			Map<String, Object> schoolManager=CacheBaseUserManager.getSchoolManagerByAreaIdAndRole(sum.getSchoolId(), "role.schoolManager");
			//此处学校管理员肯定不能为空
			if(schoolManager!=null&&schoolManager.get("USER_ID")!=null){
				baseToDoManager.toDoList(objectId, "step.type.AnnualCredit.Check", formUserId, schoolManager.get("USER_ID").toString(),url);//通知学校
			}
			//清理代办事项
			baseToDoManager.updateBaseToDoStatus(objectId, "step.type.AnnualCredit.apply", formUserId);
		}else if("step.type.AnnualCredit.Check".equals(stepType)){//学校审核
			BaseUser schoolManager=CacheBaseUserManager.getBaseUser(formUserId);//学校管理员
			if("1".equals(result)){//同意 报上级
				Map<String, Object> countryManager=CacheBaseUserManager.getManagerByAreaIdAndRole(schoolManager.getCountyId(), "role.countryManager");//县级管理员
				if(countryManager!=null&&countryManager.get("USER_ID")!=null){
					baseToDoManager.toDoList(objectId, "step.type.AnnualCredit.Registration", formUserId, countryManager.get("USER_ID").toString(),url);//通知县
				}
			}else{//不同意 通知老师
				BaseUser teacher=CacheBaseUserManager.getBaseUser(sum.getTeacherId());//学校老师
				baseToDoManager.toDoList(objectId, "step.type.AnnualCredit.apply", formUserId, teacher.getId(),url);//通知老师
			}
			//这里需要销毁学校的代办事项，标识该代办事项已经结束
			baseToDoManager.updateBaseToDoStatus(objectId, "step.type.AnnualCredit.Check", formUserId);
		}else if("step.type.AnnualCredit.Registration".equals(stepType)){//县级审核
			BaseUser countryManager=CacheBaseUserManager.getBaseUser(formUserId);//县级管理员
			if("1".equals(result)){//同意 报上级
				Map<String, Object> cityManager=CacheBaseUserManager.getManagerByAreaIdAndRole(countryManager.getCityId(), "role.cityManager");//市级管理员
				baseToDoManager.toDoList(objectId, "step.type.AnnualCredit.Audit", formUserId, cityManager.get("USER_ID").toString(),url);//通知市 进行审核
			}else{
				BaseUser teacher=CacheBaseUserManager.getBaseUser(sum.getTeacherId());//学校老师
				baseToDoManager.toDoList(objectId, "step.type.AnnualCredit.apply", formUserId, teacher.getId(),url);//通知老师
			}
			//这里需要销毁县级的代办事项，标识该代办事项已经结束
			baseToDoManager.updateBaseToDoStatus(objectId, "step.type.AnnualCredit.Registration", formUserId);
		}else if("step.type.AnnualCredit.Audit".equals(stepType)){//市区级审核
			BaseUser cityManager=CacheBaseUserManager.getBaseUser(formUserId);//市级管理员
			if("1".equals(result)){//同意 报省级
				Map<String, Object> provinceManager=CacheBaseUserManager.getManagerByAreaIdAndRole(cityManager.getCityId(), "role.cityManager");//市级管理员
				baseToDoManager.toDoList(objectId, "step.type.AnnualCredit.Record", formUserId, provinceManager.get("USER_ID").toString(),url);//通知市 进行审核
			}else{
				BaseUser teacher=CacheBaseUserManager.getBaseUser(sum.getTeacherId());//学校老师
				baseToDoManager.toDoList(objectId, "step.type.AnnualCredit.apply", formUserId, teacher.getId(),url);//通知老师
			}
			//这里需要销毁市级的代办事项，标识该代办事项已经结束
			baseToDoManager.updateBaseToDoStatus(objectId, "step.type.AnnualCredit.Audit", formUserId);
		}else if("step.type.AnnualCredit.Record".equals(stepType)){//省级备案
			baseToDoManager.updateBaseToDoStatus(objectId, "step.type.AnnualCredit.Record", formUserId);//销毁该代办事项
			if("0".equals(result)){//不同意就通知教师新一轮审核
				BaseUser teacher=CacheBaseUserManager.getBaseUser(sum.getTeacherId());//学校老师
				baseToDoManager.toDoList(objectId, "step.type.AnnualCredit.apply", formUserId, teacher.getId(),url);//通知老师
			}
		}
		return "success";
	}
	
	
}
