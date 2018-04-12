package org.my431.util;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class URLParser {
	private String url;

	private String query;

	private String path;

	private String componentLink;

	Map params = null;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getComponentLink() {
		return componentLink;
	}

	public void setComponentLink(String componentLink) {
		this.componentLink = componentLink;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public URLParser(String link) {
		this.url = link;
		int pos = link.indexOf(".do");
		int k = 3;
		if (pos == -1) {
			pos = link.indexOf(".jsp");
			k = 4;
		}
		this.path = link.substring(0, pos + k);
		this.query = link.substring(pos + k + 1);
		this.params = new HashMap();
//		parseParams(query);
//		if (get("submod") != null) {
//			componentLink = path + "?submod=" + get("submod");
//		} else {
			componentLink = path;
//		}
	}

	public String get(String key) {
		if (params.containsKey(key)) {
			return (String) params.get(key);
		}
		return null;
	}

	private void parseParams(String p) {
		if (p != null && !p.equals("")) {
			String[] array = p.split("&");
			String key, value;
			for (int i = 0; i < array.length; i++) {
				key = array[i].split("=")[0];
				value = array[i].split("=")[1];
				params.put(key, value);
				// System.out.println(key +"=" +value);
			}
		}
	}

	public static void main(String[] args) {
		URLParser parser = new URLParser(
				"/manager/TestAction.do?submod=sys&method=list");
		// URLParser parser = new
		// URLParser("/manager/test.jsp?submod=sys&method=list");
		System.out.println("path:" + parser.getPath());
		System.out.println("query:" + parser.getQuery());
		System.out.println("submod:" + parser.get("submod"));
		System.out.println("method:" + parser.get("method"));
		System.out.println("componentLink:" + parser.getComponentLink());
	}

}
