package org.my431.plugin.email;

import java.util.Locale;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * 创建人：ice
 * 创建日期:2011-6-9
 */
public class TemplateUtil {
	private static final Log log = LogFactory.getLog(TemplateUtil.class);
	private Configuration freemarkerCfg = null;
	private Template template = null;
	private static TemplateUtil tempUtil = new TemplateUtil();

	public TemplateUtil() {
	}

	/**
	 * 
	 * @param templateFile
	 * @return
	 */
	public Template getTemplate(String templateFile) {
		this.init();
		try {
			template = freemarkerCfg.getTemplate(templateFile);
			template.setEncoding("UTF-8");
			return template;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取模板文件失败！！！");
		}
		return null;
	}

	private void init() {
		freemarkerCfg = new Configuration();
		freemarkerCfg.setClassForTemplateLoading(this.getClass(), "/");
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
		Locale.setDefault(Locale.ENGLISH);
	}

	/**
	 * 
	 * @return
	 */
	public static TemplateUtil getInstance() {
		if (tempUtil == null) {
			tempUtil = new TemplateUtil();
		}
		return tempUtil;
	}

}
