package org.my431.plugin.email;

import java.io.StringWriter;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import freemarker.template.Template;

/**
 * 邮件模板
 * 创建人：ice
 * 创建日期:2011-6-9
 * </pre>
 */
public class TemplateForMail {
	TemplateUtil template = null;
	private static Template temp = null;
	private static final String MAIL_FILE = "mailTemplate.ftl";
	private static final Log log = LogFactory.getLog(TemplateForMail.class);

	public TemplateForMail() {
	}

	static {
		temp = TemplateUtil.getInstance().getTemplate(MAIL_FILE);
		if (temp == null) {
			try {
				throw new Exception("获取模板文件失败！！！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 发送邮件模板生成
	 * 
	 * @param name
	 * @return
	 */
	public String mailTemp(String name) {
		try {
			HashMap<String, String> root = new HashMap<String, String>();
			root.put("user", name);
			StringWriter writer = new StringWriter();
			temp.process(root, writer);
			return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			log.info("生成模板文件失败！！！");
		}
		return "";
	}
}
