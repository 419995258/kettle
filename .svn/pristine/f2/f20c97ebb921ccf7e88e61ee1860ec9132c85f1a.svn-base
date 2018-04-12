package org.my431.plugin.email;

import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 创建人：ice
 * 创建日期:2011-6-9
 */
public class ProertiesConfigUtil {
	private static final Log log = LogFactory.getLog(ProertiesConfigUtil.class);
	private static Configuration config = null;
	private static final String PROERTIES_CONFIG_PAHT = "acms_config.properties";

	public ProertiesConfigUtil() {
	}

	static {
		try {
			config = new PropertiesConfiguration(PROERTIES_CONFIG_PAHT);
		} catch (ConfigurationException e) {
			log.info("查找配置文件失败");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param strConfig
	 * @return
	 */
	public static String getStrConfig(String strConfig) {
		return config.getString(strConfig);
	}

	/**
	 * 
	 * @param booleanConfig
	 * @return
	 */
	public static Boolean getBooleanConfig(String booleanConfig) {
		return config.getBoolean(booleanConfig);
	}

	/**
	 * 
	 * @param intConfig
	 * @return
	 */
	public static int getIntConfig(String intConfig) {
		return config.getInt(intConfig);
	}

	/**
	 * 
	 * @param listConfig
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List getListConfig(String listConfig) {
		return config.getList(listConfig);
	}
}
