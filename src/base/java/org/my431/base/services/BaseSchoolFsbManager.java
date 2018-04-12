/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package org.my431.base.services;

import java.util.*;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */


import java.util.HashMap;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import org.my431.platform.dao.extend.HibernateEntityExtendDao;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.springframework.stereotype.Repository;
import org.my431.base.model.BaseSchoolFsb;
import org.my431.platform.dao.support.Page;

@Repository
public class BaseSchoolFsbManager extends HibernateXsqlBuilderQueryDao<BaseSchoolFsb>{

	public Class getEntityClass() {
		return BaseSchoolFsb.class;
	}
	
	public Page findPage(BaseSchoolFsb query, int pageSize, int pageNo) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from BaseSchoolFsb t where 1=1 "
			  	+ "/~ and t.schoolNo = {schoolNo} ~/"
			  	+ "/~ and t.schoolName = {schoolName} ~/"
			  	+ "/~ and t.schoolType = {schoolType} ~/"
			  	+ "/~ and t.xxjgjbzm = {xxjgjbzm} ~/"
			  	+ "/~ and t.xxjgxzlbm = {xxjgxzlbm} ~/"
			  	+ "/~ and t.xxjgszdcxflm = {xxjgszdcxflm} ~/"
			  	+ "/~ and t.dlszssmzxxjg = {dlszssmzxxjg} ~/"
			  	+ "/~ and t.sfxslxx = {sfxslxx} ~/"
			  	+ "/~ and t.sfcx = {sfcx} ~/"
			  	+ "/~ and t.cbdxxjgbsm = {cbdxxjgbsm} ~/"
			  	+ "/~ and t.uuid = {uuid} ~/"
			  	+ "/~ and t.ssxd = {ssxd} ~/"
			  	+ "/~ and t.gxxz = {gxxz} ~/"
			  	+ "/~ and t.sfbsxx = {sfbsxx} ~/"
			  	+ "/~ and t.sfssmzsyjx = {sfssmzsyjx} ~/"
			  	+ "/~ and t.cjr = {cjr} ~/"
				+ "/~ and t.cjsj >= {cjsjBegin} ~/"
				+ "/~ and t.cjsj <= {cjsjEnd} ~/"
			  	+ "/~ and t.xgr = {xgr} ~/"
				+ "/~ and t.xgsj >= {xgsjBegin} ~/"
				+ "/~ and t.xgsj <= {xgsjEnd} ~/"
			  	+ "/~ and t.provinceId = {provinceId} ~/"
			  	+ "/~ and t.cityId = {cityId} ~/"
			  	+ "/~ and t.countyId = {countyId} ~/"
			  	+ "/~ and t.areaId = {areaId} ~/"
			  	+ "/~ and t.xxjgdzdm = {xxjgdzdm} ~/"
			  	+ "/~ and t.address = {address} ~/"
			  	+ "/~ and t.zipCode = {zipCode} ~/"
			  	+ "/~ and t.tel = {tel} ~/"
			  	+ "/~ and t.contacter = {contacter} ~/"
			  	+ "/~ and t.email = {email} ~/"
			  	+ "/~ and t.staffCntNb = {staffCntNb} ~/"
			  	+ "/~ and t.teacherCntNb = {teacherCntNb} ~/"
			  	+ "/~ and t.studentNumYey = {studentNumYey} ~/"
			  	+ "/~ and t.studentNumXx = {studentNumXx} ~/"
			  	+ "/~ and t.studentNumCz = {studentNumCz} ~/"
			  	+ "/~ and t.studentNumGz = {studentNumGz} ~/"
			  	+ "/~ and t.studentNumZz = {studentNumZz} ~/"
			  	+ "/~ and t.studentNumGx = {studentNumGx} ~/"
			  	+ "/~ and t.studentNumQt = {studentNumQt} ~/"
			  	+ "/~ and t.deleteFlag = {deleteFlag} ~/"
			  	+ "/~ and t.deleteUser = {deleteUser} ~/"
				+ "/~ and t.deleteTime >= {deleteTimeBegin} ~/"
				+ "/~ and t.deleteTime <= {deleteTimeEnd} ~/"
			  	+ "/~ and t.comment = {comment} ~/"
				+ "/~ order by [sortColumns] ~/";

		Map filters = new HashMap();  
				
		XsqlBuilder xsqlBuilder=new XsqlBuilder();
		
       /* if(isNotEmpty(query.getSchoolId())) {
            filters.put("schoolId", query.getSchoolId()); 
        }*/
        if(isNotEmpty(query.getSchoolNo())) {
            filters.put("schoolNo", query.getSchoolNo()); 
        }
        if(isNotEmpty(query.getSchoolName())) {
            filters.put("schoolName", query.getSchoolName()); 
        }
        if(isNotEmpty(query.getSchoolType())) {
            filters.put("schoolType", query.getSchoolType()); 
        }
        if(isNotEmpty(query.getXxjgjbzm())) {
            filters.put("xxjgjbzm", query.getXxjgjbzm()); 
        }
        if(isNotEmpty(query.getXxjgxzlbm())) {
            filters.put("xxjgxzlbm", query.getXxjgxzlbm()); 
        }
        if(isNotEmpty(query.getXxjgszdcxflm())) {
            filters.put("xxjgszdcxflm", query.getXxjgszdcxflm()); 
        }
        if(isNotEmpty(query.getDlszssmzxxjg())) {
            filters.put("dlszssmzxxjg", query.getDlszssmzxxjg()); 
        }
        if(isNotEmpty(query.getSfxslxx())) {
            filters.put("sfxslxx", query.getSfxslxx()); 
        }
        if(isNotEmpty(query.getSfcx())) {
            filters.put("sfcx", query.getSfcx()); 
        }
        if(isNotEmpty(query.getCbdxxjgbsm())) {
            filters.put("cbdxxjgbsm", query.getCbdxxjgbsm()); 
        }
        if(isNotEmpty(query.getUuid())) {
            filters.put("uuid", query.getUuid()); 
        }
        if(isNotEmpty(query.getSsxd())) {
            filters.put("ssxd", query.getSsxd()); 
        }
        if(isNotEmpty(query.getGxxz())) {
            filters.put("gxxz", query.getGxxz()); 
        }
        if(isNotEmpty(query.getSfbsxx())) {
            filters.put("sfbsxx", query.getSfbsxx()); 
        }
        if(isNotEmpty(query.getSfssmzsyjx())) {
            filters.put("sfssmzsyjx", query.getSfssmzsyjx()); 
        }
        if(isNotEmpty(query.getCjr())) {
            filters.put("cjr", query.getCjr()); 
        }
        if(isNotEmpty(query.getCjsj())) {
            filters.put("cjsj", query.getCjsj()); 
        }
        if(isNotEmpty(query.getXgr())) {
            filters.put("xgr", query.getXgr()); 
        }
        if(isNotEmpty(query.getXgsj())) {
            filters.put("xgsj", query.getXgsj()); 
        }
        if(isNotEmpty(query.getProvinceId())) {
            filters.put("provinceId", query.getProvinceId()); 
        }
        if(isNotEmpty(query.getCityId())) {
            filters.put("cityId", query.getCityId()); 
        }
        if(isNotEmpty(query.getCountyId())) {
            filters.put("countyId", query.getCountyId()); 
        }
        if(isNotEmpty(query.getAreaId())) {
            filters.put("areaId", query.getAreaId()); 
        }
        if(isNotEmpty(query.getXxjgdzdm())) {
            filters.put("xxjgdzdm", query.getXxjgdzdm()); 
        }
        if(isNotEmpty(query.getAddress())) {
            filters.put("address", query.getAddress()); 
        }
        if(isNotEmpty(query.getZipCode())) {
            filters.put("zipCode", query.getZipCode()); 
        }
        if(isNotEmpty(query.getTel())) {
            filters.put("tel", query.getTel()); 
        }
        if(isNotEmpty(query.getContacter())) {
            filters.put("contacter", query.getContacter()); 
        }
        if(isNotEmpty(query.getEmail())) {
            filters.put("email", query.getEmail()); 
        }
        if(isNotEmpty(query.getStaffCntNb())) {
            filters.put("staffCntNb", query.getStaffCntNb()); 
        }
        if(isNotEmpty(query.getTeacherCntNb())) {
            filters.put("teacherCntNb", query.getTeacherCntNb()); 
        }
        if(isNotEmpty(query.getStudentNumYey())) {
            filters.put("studentNumYey", query.getStudentNumYey()); 
        }
        if(isNotEmpty(query.getStudentNumXx())) {
            filters.put("studentNumXx", query.getStudentNumXx()); 
        }
        if(isNotEmpty(query.getStudentNumCz())) {
            filters.put("studentNumCz", query.getStudentNumCz()); 
        }
        if(isNotEmpty(query.getStudentNumGz())) {
            filters.put("studentNumGz", query.getStudentNumGz()); 
        }
        if(isNotEmpty(query.getStudentNumZz())) {
            filters.put("studentNumZz", query.getStudentNumZz()); 
        }
        if(isNotEmpty(query.getStudentNumGx())) {
            filters.put("studentNumGx", query.getStudentNumGx()); 
        }
        if(isNotEmpty(query.getStudentNumQt())) {
            filters.put("studentNumQt", query.getStudentNumQt()); 
        }
        if(isNotEmpty(query.getDeleteFlag())) {
            filters.put("deleteFlag", query.getDeleteFlag()); 
        }
        if(isNotEmpty(query.getDeleteUser())) {
            filters.put("deleteUser", query.getDeleteUser()); 
        }
        if(isNotEmpty(query.getDeleteTime())) {
            filters.put("deleteTime", query.getDeleteTime()); 
        }
        if(isNotEmpty(query.getComments())) {
            filters.put("comment", query.getComments()); 
        }
        
        XsqlFilterResult result = xsqlBuilder.generateHql(sql,filters); 
        
		return pageQuery(result.getXsql(), pageNo, pageSize,result.getAcceptedFilters());
	}
	

}
