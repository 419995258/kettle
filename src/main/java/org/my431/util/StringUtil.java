package org.my431.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("unchecked")
public class StringUtil {
	private final static String regxpForHtml = "<([^>]*)>";

	public static String getRepeatString(int repeatTime, String metaString) {
		String repeatString = null;
		if (repeatTime > 0 && metaString != null) {
			int intMetatStringLength = metaString.length();
			if (intMetatStringLength == 0) {
				repeatString = "";
			} else {
				StringBuffer tempStringBuffer = new StringBuffer(repeatTime * intMetatStringLength);
				for (int i = 0; i < repeatTime; i++)
					tempStringBuffer.append(metaString);

				repeatString = tempStringBuffer.toString();
			}
		}
		return repeatString;
	}

	public static final String getString(HttpServletRequest httpservletrequest, String s) {
		if (s == null)
			return null;
		String s1 = httpservletrequest.getParameter(s);
		if (s1 != null && !s1.equals(""))
			return s1.trim();
		else
			return "";
	}

	public static String[] splitCode(String Code, int splitLength) {
		String[] splitCodes = new String[Code.length() / splitLength];

		for (int i = 0; i < Code.length() / splitLength; i++) {
			splitCodes[i] = Code.substring(0, i * splitLength + splitLength);
		}
		return splitCodes;
	}

	public static String splitString(String Code, int splitLength) {
		StringBuffer splitCodes = new StringBuffer();

		for (int i = 0; i < Code.length() / splitLength; i++) {
			splitCodes.append("'");
			splitCodes.append(Code.substring(0, i * splitLength + splitLength));
			splitCodes.append("',");
		}

		return splitCodes.substring(0, splitCodes.length() - 1);
	}

	// Repeats the passed character 'times' times.
	public static String repeat(int times, char c) {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < times; i++)
			s.append(c);

		return s.toString();
	}

	public static String right(String in, int startpos) {
		return in.substring(startpos, in.length());
	}

	public static String numberTString(int num, int len) {
		String seq = String.valueOf(num);
		return repeat(len - seq.length(), '0') + seq;
	}

	public static String valueOf(Object obj) {
		return obj != null ? obj.toString() : "";
	}

	public static String replace(String source, String from, String to) {
		if ((source == null) || source.equals("") || (from == null) || (to == null) || from.equals("") || from.equals(to)) {
			return source;
		}

		StringBuffer sb = new StringBuffer(source.length());
		String s = source;
		int index = s.indexOf(from);
		int fromLen = from.length();

		while (index != -1) {
			sb.append(s.substring(0, index));
			sb.append(to);
			s = s.substring(index + fromLen);
			index = s.indexOf(from);
		}

		return sb.append(s).toString();
	}

	public static String[] split(String source, String div) {
		int arynum = 0;
		int intIdx = 0;
		int intIdex = 0;
		int div_length = div.length();
		if (source.compareTo("") != 0) {
			if (source.indexOf(div) != -1) {
				intIdx = source.indexOf(div);
				int intCount = 1;
				do {
					if (source.indexOf(div, intIdx + div_length) != -1) {
						intIdx = source.indexOf(div, intIdx + div_length);
						arynum = intCount;
					} else {
						arynum += 2;
						break;
					}
					intCount++;
				} while (true);
			} else {
				arynum = 1;
			}
		} else {
			arynum = 0;
		}
		intIdx = 0;
		intIdex = 0;
		String returnStr[] = new String[arynum];
		if (source.compareTo("") != 0) {
			if (source.indexOf(div) != -1) {
				intIdx = source.indexOf(div);
				returnStr[0] = source.substring(0, intIdx);
				int intCount = 1;
				do {
					if (source.indexOf(div, intIdx + div_length) != -1) {
						intIdex = source.indexOf(div, intIdx + div_length);
						returnStr[intCount] = source.substring(intIdx + div_length, intIdex);
						intIdx = source.indexOf(div, intIdx + div_length);
					} else {
						returnStr[intCount] = source.substring(intIdx + div_length, source.length());
						break;
					}
					intCount++;
				} while (true);
			} else {
				returnStr[0] = source.substring(0, source.length());
				return returnStr;
			}
		} else {
			return returnStr;
		}
		return returnStr;
	}

	/**
	 * 截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位
	 * 
	 * @author patriotlml
	 * @param String
	 *            origin, 原始字符串
	 * @param int len, 截取长度(一个汉字长度按2算的)
	 * @return String, 返回的字符串
	 */
	public static String getTitle(String origin, int len) {
		if (origin == null || origin.equals("") || len < 1)
			return "";
		byte[] strByte = new byte[len];
		if (len > StringUtil.length(origin)) {
			return origin;
		}
		System.arraycopy(origin.getBytes(), 0, strByte, 0, len);
		int count = 0;
		for (int i = 0; i < len; i++) {
			int value = (int) strByte[i];
			if (value < 0) {
				count++;
			}
		}
		if (count % 2 != 0) {
			len = (len == 1) ? ++len : --len;
		}
		return new String(strByte, 0, len) + "…";
	}

	/**
	 * * 以byte为单位截取定长的String，用于显示的美观
	 * 
	 * @param String
	 *            str 将被截取的源字符串
	 * @param int byteLength 以byte为单位的字符串长度
	 * @param boolean isFillNeeded 源字符串长度不够时，是否需要填充空格
	 */
	public static String getTitle2(String origin, int len) {
		if (origin == null || origin.equals("") || len < 1)
			return "";
		byte[] strByte = new byte[len];
		if (len > StringUtil.length(origin)) {
			return origin;
		}
		System.arraycopy(origin.getBytes(), 0, strByte, 0, len);
		int count = 0;
		for (int i = 0; i < len; i++) {
			int value = (int) strByte[i];
			if (value < 0) {
				count++;
			}
		}
		if (count % 2 != 0) {
			len = (len == 1) ? ++len : --len;
		}
		return new String(strByte, 0, len);
	}

	/**
	 * 判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符）
	 * 
	 * @param char c, 需要判断的字符
	 * @return boolean, 返回true,Ascill字符
	 */
	public static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}

	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * 
	 * @param String
	 *            s ,需要得到长度的字符串
	 * @return int, 得到的字符串长度
	 */
	public static int length(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}

	public static String getTitle(String str, int byteLength, String light) {
		byteLength = byteLength * 2;
		String[] l = light.split(",");
		try {
			if (str.getBytes("UTF-8").length < byteLength) {
				if (l != null && l.length > 0) {
					for (String lig : l) {
						str = str.replace(lig, "<font color=\"red\"/>" + lig + "</font>");
					}
				}

				return str;
			} else {
				while (str.getBytes("UTF-8").length > (byteLength - 2)) {
					str = str.substring(0, str.length() - 1);
				}
				StringBuffer sb = new StringBuffer(byteLength);
				sb.append(str).append("…");
				str = sb.toString();
				if (l != null && l.length > 0) {
					for (String lig : l) {
						str = str.replace(lig, "<font color=\"red\"/>" + lig + "</font>");
					}
				}
				return str;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static void setCookie(HttpServletResponse httpservletresponse, String s, String s1, int i) throws UnsupportedEncodingException {
		Cookie cookie = new Cookie(s, URLEncoder.encode(s1, "utf-8"));
		cookie.setMaxAge(i);
		cookie.setPath("/");
		httpservletresponse.addCookie(cookie);
		return;
	}

	public static String getCookie(HttpServletRequest httpservletrequest, String s) throws UnsupportedEncodingException {
		Cookie acookie[] = httpservletrequest.getCookies();
		if (acookie == null)
			return null;
		for (int i = 0; i < acookie.length; i++)
			if (acookie[i].getName().equals(s))
				return URLDecoder.decode(acookie[i].getValue(), "utf-8");

		return null;
	}

	public static String[] getCookie(HttpServletRequest httpservletrequest) throws UnsupportedEncodingException {
		Cookie acookie[] = httpservletrequest.getCookies();
		ArrayList arraylist = new ArrayList();
		int i;
		if (acookie == null)
			return null;
		i = 0;
		while (i < acookie.length) {
			arraylist.add(acookie[i].getName() + " = " + URLDecoder.decode(acookie[i].getValue(), "utf-8"));
			i++;
		}
		return (String[]) (String[]) arraylist.toArray(new String[0]);
	}

	public static String filterString(String in) {
		String out = null;
		if (in != null) {
			out = in.replace('/', '／');
			out = out.replace('\'', '’');
			out = out.replace('\"', '”');
			out = out.replace('`', '｀');
			out = out.replace('-', '－');
			out = out.replace('-', '－');

			out = out.replace('\\', '＼');
			out = out.replace(' ', '　');
			out = out.replace(':', '：');
			out = out.replace('?', '？');
			out = out.replace('*', '＊');
			out = out.replace('&', '＆');
			out = out.replace('|', 'Ⅰ');
			out = out.replace('<', '＜');
			out = out.replace('>', '＞');
		}

		return out;
	}

	// 将_str转化为double
	public static double StringToDouble(String _str) {
		try {
			return Double.parseDouble(_str);

		} catch (Exception e) {
			System.out.println("double字符串格式错误");
			return Double.MIN_VALUE;
		}
	}

	public static String getNotNullString(String _str) {
		if (_str == null)
			return "";
		return _str;
	}

	public static String getFileSize(float fs) {

		String fstr = "";
		if (fs / (1024 * 1024) >= 1)
			fstr = new DecimalFormat("0.00").format(fs / (1024 * 1024)) + "M";
		else
			fstr = new DecimalFormat("0.00").format(fs / 1024) + "K";

		return fstr;
	}

	public static String filterHtml(String str) {
		Pattern pattern = Pattern.compile(regxpForHtml);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result1 = matcher.find();
		while (result1) {
			matcher.appendReplacement(sb, "");
			result1 = matcher.find();
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	public static String textAreaView(String sp, String sp1, String sp2) {
		String sTemp = sp;
		String str = "";
		if (sp == null || sp.equals("")) {
			return str;
		}
		try {
			int iIndex = sTemp.indexOf(sp1);
			int iStar = 0;
			while (iIndex != -1) {
				String s1 = sTemp.substring(iStar, iIndex);
				if (iIndex < sTemp.length()) {
					sTemp = sTemp.substring(iIndex + 1, sTemp.length());
					s1 = s1 + sp2;
					str = str + s1;
					iIndex = sTemp.indexOf(sp1);
				}
			}
			str = str + sTemp;
		} catch (Exception e) {

		}
		str = str.replaceAll(" ", "&nbsp;&nbsp;");
		return str;
	}

	// 将换行符制表符和回车替换为空格
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll(" ");
		}
		return dest;
	}

	/**
	 * 保留小数点后2位，四舍五入
	 * 
	 * @param ft
	 * @return
	 */
	public static Float formaterFloat(Float ft) {
		float returnf = 0;
		int scale = 2;// 设置位数
		int roundingMode = 4;// 表示四舍五入，可以选择其他舍值方式，例如去尾，等等.
		BigDecimal bd = new BigDecimal((double) ft);
		bd = bd.setScale(scale, roundingMode);
		returnf = bd.floatValue();
		return returnf;
	}

	public static Map<String, Object> transBean2Map(Object obj) {

		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value);
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}

		return map;

	}

	public static Object transMap2Bean(Map<String, Object> map, Object obj) {

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				if (map.containsKey(key)) {
					Object value = map.get(key);
					// 得到property对应的setter方法
					Method setter = property.getWriteMethod();
					setter.invoke(obj, value);
				}

			}

		} catch (Exception e) {
			System.out.println("transMap2Bean Error " + e);
		}

		return obj;

	}

	public static boolean isNumber(Object obj) {
		Pattern pattern = Pattern.compile("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$"); 
		Pattern pattern2 = Pattern.compile("^((-?\\d+.?\\d*)[Ee]{1}(-?\\d+))$"); 
		String str="";
		if(obj!=null){
			str=obj.toString();
		}
		if(str.equals("")){
			return false;
		}
		Matcher isNum = pattern.matcher(str);
		if(!isNum.matches() ){
			Matcher isNum2 = pattern2.matcher(str);
			if(!isNum2.matches()){
				return false; 
			}
		  
		} 
		//System.out.println(str+":true");
		return true; 
	}
	
	public static void main(String[] args) {
		System.out.println(isNumber("1啊1"));
	}
	
	/**
	 * 某字符出现次数
	 * @param str
	 * @param target
	 * @return
	 * @throws Throwable
	 */
	public static Integer count(String a,String b){
		 
        if (a.length() < b.length()) {
            return 0;
        }
        char[] a_t = a.toCharArray();
        char[] b_t = b.toCharArray();
        int count = 0, temp = 0, j = 0;
 
        for (int i = 0; i < a_t.length; i++) {
            // 保证一个连续的字符串 b 跟 a中某段相匹配
            if (a_t[i] == b_t[j] && j < b_t.length) {
                temp++;
                j++;
                // 此时连续的字符串 b 跟 已跟 a 中某段相匹配
                if (temp == b_t.length) {
                    count++;
                    temp = 0;
                    j = 0;
                }
            }
            // 只要有一个字符不匹配，temp计数从来
            else {
                temp = 0;
                j = 0;
            }
        }
 
        return count;
	}
	/**
	 * Int值如果为null则返回0，否则返回原值。
	 * @param str
	 * @param target
	 * @return
	 * @throws Throwable
	 */
	public static Integer getRightIntValue(Integer intValue){
		Integer ii=0;
		if(intValue!=null){
			ii=intValue;
		}
		return ii;
	}
	
	/**
	 * 如果BigDecimal为null则返回0
	 * @param bigDecimal
	 * @return
	 */
	public static BigDecimal getRightBigDecimalValue(BigDecimal bigDecimal){
		BigDecimal big = new BigDecimal("0");
		if(bigDecimal!=null){
			big=bigDecimal;
		}
		return big;
	}
	
	
	public static String toUpperCaseFirstOne(String s)
    {
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
	
	public static String StringArray2String(String[] ss){
		
		if(ss!=null){
			StringBuffer sb=new StringBuffer();
			int i=0;
			for(String str:ss){
				if(!str.trim().equals("")){
					if(i==0){
						sb.append(str);
					}else{
						sb.append(","+str);
					}
					i=i+1;
				}
			}
			return sb.toString()+",";
		}
		
		return "";
	}
	
	public static double objectToDouble(Object o) {
		if(o==null||o.toString().equals("")){
			return 0d;
		}else{
			try {
				return Double.parseDouble(o.toString());
			} catch (Exception e) {
				System.out.println("double字符串格式错误");
				return Double.MIN_VALUE;
			}
		}
		
	}
}
