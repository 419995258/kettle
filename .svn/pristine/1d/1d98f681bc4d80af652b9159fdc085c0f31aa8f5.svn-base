package org.my431.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * XMLConfigUtil 用于将 XML 配置文件解析之后，转换成 Properties，方便调用。
 * 
 * @version $Revision: 1.2 $
 * @author Krazy Nio
 */
public class XMLConfigUtil {

	/* XML 配置文件转换之后的属性对象。 */
	private Properties props;

	/* XML 配置文件对象。 */
	private File file;

	/**
	 * 无参数构建函数。
	 */
	public XMLConfigUtil() {
		props = new Properties();
	}

	/**
	 * 构建函数。在创建一个 XMLConfigUtil 对象实例的同时，加载 XML 配置文件，转换成 Properties。
	 * 
	 * @param fileRealPath
	 *            XML 配置文件的路径，可以是相对路径，也可以是绝对路径。
	 */
	public XMLConfigUtil(String fileRealPath) {
		this();
		load(fileRealPath);
	}

	/**
	 * 获取配置变量的 Properties 对象。
	 */
	public Properties getProperties() {
		return props;
	}

	/**
	 * 根据给出的 key 属性名，获取相对应的配置变量的属性值，如果没有相应的属性，则返回空字符串。
	 * 
	 * @param key
	 *            配置变量的属性名。
	 */
	public String getConfig(String key) {
		return props.getProperty(key, "");
	}

	/**
	 * 设置属性 key 的属性值为 value。
	 * 
	 * @param key
	 *            配置变量的属性名。
	 * @param value
	 *            配置变量的属性值。
	 */
	public Object setConfig(String key, String value) {
		return props.setProperty(key, value);
	}

	/**
	 * 加载 XML 配置文件，将其转换成 Properties。
	 * 
	 * @param fileRealPath
	 *            XML 配置文件的路径，可以是相对路径，也可以是绝对路径。
	 */
	public void load(String fileRealPath) {
		String key, value;
		Element node;

		File tmp = new File(fileRealPath);
		if (!tmp.exists()) {
			System.out.println("Load error - Can't find the file: " + fileRealPath);
			return;
		} else if (!tmp.isFile()) {
			System.out.println("Load error - " + fileRealPath + " is not a file.");
			return;
		} // end if

		props.clear(); // 清除先前的所有属性
		file = new File(fileRealPath);
		SAXBuilder parser = new SAXBuilder();
		try {
			Document doc = parser.build(file);
			Element root = doc.getRootElement();
			List children = root.getChildren();
			Iterator it = children.iterator();
			while (it.hasNext()) {
				node = (Element) it.next();
				key = node.getName(); // 获取标签名 － 属性名
				value = node.getTextTrim(); // 获取标签包含内容 － 属性值
				props.setProperty(key, value); // 设置属性
			} // end while
		} catch (JDOMException e) {
			System.out.println("Load error - JDOMException: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Load error - IOException: " + e.getMessage());
		} // end try
	} // end load()

	/**
	 * 将 Properties 保存回加载时的 XML 配置文件中。
	 */
	public void save() {
		String key;
		Element node;

		Element root = new Element("configs");
		Enumeration keys = props.keys();
		while (keys.hasMoreElements()) {
			key = (String) keys.nextElement();
			node = new Element(key);
			node.addContent(props.getProperty(key));
			root.addContent(node);
		} // end while
		Document doc = new Document(root);
		Format ft = Format.getPrettyFormat();
		ft.setEncoding("GBK");
		XMLOutputter writer = new XMLOutputter();
		writer.setFormat(ft);
		try {
			FileOutputStream out = new FileOutputStream(file);
			writer.output(doc, out);
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println("Save error - IOException: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		// XMLConfigUtil config = new XMLConfigUtil("WebContent/WEB-INF/conf/DBConfig.xml");
		// Properties props = config.getProperties();
		// System.out.println(props);
		//      
		// config.load("WebContent/WEB-INF/conf/PlatformConfig.xml");
		// props = config.getProperties();
		// System.out.println(props);
		//
		// config.load("WebContent/WEB-INF/conf/RpcConfig.xml");
		// props = config.getProperties();
		XMLConfigUtil config = new XMLConfigUtil("webapp/WEB-INF/conf/PlatformConfig.xml");
		config.setConfig("platformRpcHost", "127.0.0.1");
		config.setConfig("platformRpcPass", "bar");
		config.setConfig("platformRpcPath", "/xmlrpc");
		config.setConfig("platformRpcPort", "8080");
		config.setConfig("platformRpcUser", "foo");
		config.save();
		System.out.println(config.getProperties());
	}
} // /~
