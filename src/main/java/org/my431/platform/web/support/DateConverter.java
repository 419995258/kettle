package org.my431.platform.web.support;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.my431.util.DateFormater;

/**
 * 简易DateConverter. 供Apache BeanUtils 做转换,默认时间格式为yyyy-MM-dd,可由构造函数改变.
 * 
 * @author calvin
 * @see Converter
 */
public class DateConverter implements Converter {
	private static final Log log = LogFactory.getLog(DateConverter.class);

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public DateConverter(String formatPattern) {
		if (StringUtils.isNotBlank(formatPattern)) {
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	}

	public Object convert(Class arg0, Object value) {
		try {
			if(value!=null){
				String name=value.getClass().getName();
				if(name.equals("java.util.Date")){
					value=DateFormater.DateTimeToString((Date)value);
				}
				String dateStr =value.toString();
				if (StringUtils.isNotBlank(dateStr)) {
					return format.parse(dateStr);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

}
