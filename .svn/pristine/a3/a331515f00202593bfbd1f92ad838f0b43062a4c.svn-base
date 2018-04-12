package org.my431.base.job;

import java.util.Date;

import org.apache.log4j.Logger;
import org.my431.util.DateFormater;
import org.my431.util.FileUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
/**
 * 这个方法是删除resources下txt文件的方法，暂时不用，没有加入到项目中
 * */
public class TxtFileDelJob implements Job{
	private Logger _log =Logger.getLogger(TxtFileDelJob.class);
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		_log.info("The time of txt file start to del:"+DateFormater.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		FileUtil.delAllFile("logtimetask");
		FileUtil.delAllFile("qkqd");
		_log.info("The time of txt file end to del:"+DateFormater.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
	}

}
