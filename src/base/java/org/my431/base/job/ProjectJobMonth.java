package org.my431.base.job;


import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.my431.base.services.StatSchoolManager;
import org.my431.platform.utils.ContextUtils;
import org.my431.util.DateFormater;
import org.my431.util.FileUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ProjectJobMonth implements Job{
	private Logger _log = Logger.getLogger(ProjectJobMonth.class);

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		_log.info("定时任务开始执行：时间："+DateFormater.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		try {
			long t1=0;
			FileUtil.writeLogTxt("log.txt","month==============The Timing task execution start time:"+DateFormater.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			
			
			long t2=0;
			//计算项目统计月报
			FileUtil.writeLogTxt("log.txt","month==============The Timing task execution end time:"+DateFormater.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss")+"==use time ："+(t2-t1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				FileUtil.writeLogTxt("log.txt",e.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		_log.info("定时任务结束执行：时间："+DateFormater.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
	}

}
