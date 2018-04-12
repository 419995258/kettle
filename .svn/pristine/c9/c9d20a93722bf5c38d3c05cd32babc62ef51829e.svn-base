package org.my431.base.thread;

import java.io.IOException;

import org.my431.base.services.BaseSchoolManager;
import org.my431.platform.utils.ContextUtils;
public class StatJhReloadRun implements Runnable{
	
	private String  flag;
	private int  pageNo;
	private int  pageSize;
	
	
	public StatJhReloadRun(String flag, int pageNo, int pageSize) {
		this.flag = flag;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	@Override
	public void run() {
		BaseSchoolManager baseSchoolManager = (BaseSchoolManager) ContextUtils.getBean("baseSchoolManager");
	}

}
