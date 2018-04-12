package org.my431.base.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.my431.base.model.BaseRole;
import org.my431.base.model.BaseSchool;
import org.my431.base.model.BaseUser;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.util.DateFormater;
import org.my431.util.StringUtil;


public class BaseLoginManager extends HibernateXsqlBuilderQueryDao {
	
	private BaseAreaManager baseAreaManager;
	private BaseRoleManager baseRoleManager;
	public void setBaseRoleManager(BaseRoleManager baseRoleManager) {
		this.baseRoleManager = baseRoleManager;
	}

	public void setSsoAreaManager(BaseAreaManager baseAreaManager) {
		this.baseAreaManager = baseAreaManager;
	}
	private BaseSchoolManager baseSchoolManager;
	
	public void setBaseSchoolManager(BaseSchoolManager baseSchoolManager) {
		this.baseSchoolManager = baseSchoolManager;
	}

	public BaseUser login(String loginName, String password) {

		String hql = "from BaseUser where (loginName = ? or email = ? or mobile = ?) and password = ? and deleteFlag='0' and status='1'";//加入email或者手机号也能登录
		List<BaseUser> list = find(hql, loginName, loginName, loginName, password);
		if (list.size() == 0) { // 用户不存在
			return null;
		} else { // 用户存在
			BaseUser usr = list.get(0);
			return usr;
		}
	}
	
	/**
	 * 生成随机字符串
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length){
		StringBuffer buffer=new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		StringBuffer sb=new StringBuffer();
		Random r=new Random();
		int range=buffer.length();
		for(int i=0;i<length;i++){
		sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	} 
	
	
}