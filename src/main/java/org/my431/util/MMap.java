package org.my431.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import net.sourceforge.jeval.function.string.Substring;


public class MMap implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String key;
	private Object obj;
	private Object obj0;
	private Object obj1;
	private Object obj2;
	private Object obj3;
	private Object obj4;
	private Object obj5;
	private Object obj6;
	private Object obj7;
	private Object obj8;
	private Object obj9;
	private Object obj10;
	private Object obj11;
	private Object obj12;
	private Object obj13;
	private Object obj14;
	private Object obj15;
	private Object obj16;
	private Object obj17;
	private Object obj18;
	private Object obj19;
	private Object obj20;
	private Object obj21;
	private Object obj22;
	private Object obj23;
	private Object obj24;
	private Object obj25;
	private Object obj26;
	private Object obj27;
	private Object obj28;
	private Object obj29;
	private Object obj30;
	private Object obj31;
	private Object obj32;
	private Object obj33;
	private Object obj34;
	private Object obj35;
	private Object obj36;
	private Object obj37;
	private Object obj38;
	private Object obj39;
	private Object obj40;
	private Object obj41;
	private Object obj42;
	private Object obj43;
	private Object obj44;
	private Object obj45;
	private Object obj46;
	private Object obj47;
	private Object obj48;
	private Object obj49;
	private Object obj50;
	
	private List   list;
	private List   list1;
	private Object nobNull;
	private Object nobOther;
	
	
	
	public MMap() {
		super();
	}
	public MMap(String key, Object obj, Object obj1, Object obj2, Object obj3) {
		super();
		this.key = key;
		this.obj = obj;
		this.obj1 = obj1;
		this.obj2 = obj2;
		this.obj3 = obj3;
	}
	
	public MMap(String key, Object obj, Object obj1, Object obj2, Object obj3,
			Object obj4) {
		super();
		this.key = key;
		this.obj = obj;
		this.obj1 = obj1;
		this.obj2 = obj2;
		this.obj3 = obj3;
		this.obj4 = obj4;
	}


	public MMap(String key, Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5,Object obj6) {
		super();
		this.key = key;
		this.obj = obj;
		this.obj1 = obj1;
		this.obj2 = obj2;
		this.obj3 = obj3;
		this.obj4 = obj4;
		this.obj5 = obj5;
		this.obj6 = obj6;
	}

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public Object getObj1() {
		return obj1;
	}
	public void setObj1(Object obj1) {
		this.obj1 = obj1;
	}
	public Object getObj2() {
		return obj2;
	}
	public void setObj2(Object obj2) {
		this.obj2 = obj2;
	}
	public Object getObj3() {
		return obj3;
	}
	public void setObj3(Object obj3) {
		this.obj3 = obj3;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Object getObj4() {
		return obj4;
	}

	public void setObj4(Object obj4) {
		this.obj4 = obj4;
	}

	public Object getObj5() {
		return obj5;
	}

	public void setObj5(Object obj5) {
		this.obj5 = obj5;
	}

	public Object getObj6() {
		return obj6;
	}

	public void setObj6(Object obj6) {
		this.obj6 = obj6;
	}


	public Object getObj7() {
		return obj7;
	}


	public void setObj7(Object obj7) {
		this.obj7 = obj7;
	}


	public Object getObj8() {
		return obj8;
	}


	public void setObj8(Object obj8) {
		this.obj8 = obj8;
	}


	public Object getObj9() {
		return obj9;
	}


	public void setObj9(Object obj9) {
		this.obj9 = obj9;
	}


	public Object getObj10() {
		return obj10;
	}


	public void setObj10(Object obj10) {
		this.obj10 = obj10;
	}
	
	
	public Object getObj11() {
		return obj11;
	}


	public void setObj11(Object obj11) {
		this.obj11 = obj11;
	}


	public Object getObj12() {
		return obj12;
	}


	public void setObj12(Object obj12) {
		this.obj12 = obj12;
	}
	

	public Object getObj13() {
		return obj13;
	}


	public void setObj13(Object obj13) {
		this.obj13 = obj13;
	}


	public Object getObj14() {
		return obj14;
	}


	public void setObj14(Object obj14) {
		this.obj14 = obj14;
	}


	public Object getObj15() {
		return obj15;
	}


	public void setObj15(Object obj15) {
		this.obj15 = obj15;
	}


	public Object getObj16() {
		return obj16;
	}


	public void setObj16(Object obj16) {
		this.obj16 = obj16;
	}


	public Object getObj17() {
		return obj17;
	}


	public void setObj17(Object obj17) {
		this.obj17 = obj17;
	}


	public Object getObj18() {
		return obj18;
	}


	public void setObj18(Object obj18) {
		this.obj18 = obj18;
	}


	public Object getObj19() {
		return obj19;
	}


	public void setObj19(Object obj19) {
		this.obj19 = obj19;
	}


	public Object getObj20() {
		return obj20;
	}


	public void setObj20(Object obj20) {
		this.obj20 = obj20;
	}


	public Object getObj21() {
		return obj21;
	}


	public void setObj21(Object obj21) {
		this.obj21 = obj21;
	}


	public Object getObj22() {
		return obj22;
	}


	public void setObj22(Object obj22) {
		this.obj22 = obj22;
	}


	public Object getObj23() {
		return obj23;
	}


	public void setObj23(Object obj23) {
		this.obj23 = obj23;
	}


	public Object getObj24() {
		return obj24;
	}


	public void setObj24(Object obj24) {
		this.obj24 = obj24;
	}


	public Object getObj25() {
		return obj25;
	}


	public void setObj25(Object obj25) {
		this.obj25 = obj25;
	}


	public Object getObj26() {
		return obj26;
	}


	public void setObj26(Object obj26) {
		this.obj26 = obj26;
	}


	public Object getObj27() {
		return obj27;
	}


	public void setObj27(Object obj27) {
		this.obj27 = obj27;
	}


	public Object getObj28() {
		return obj28;
	}


	public void setObj28(Object obj28) {
		this.obj28 = obj28;
	}


	public Object getObj29() {
		return obj29;
	}


	public void setObj29(Object obj29) {
		this.obj29 = obj29;
	}


	public Object getObj30() {
		return obj30;
	}


	public void setObj30(Object obj30) {
		this.obj30 = obj30;
	}


	public Object getObj31() {
		return obj31;
	}


	public void setObj31(Object obj31) {
		this.obj31 = obj31;
	}


	public Object getObj32() {
		return obj32;
	}


	public void setObj32(Object obj32) {
		this.obj32 = obj32;
	}


	public Object getObj33() {
		return obj33;
	}


	public void setObj33(Object obj33) {
		this.obj33 = obj33;
	}


	public Object getObj34() {
		return obj34;
	}


	public void setObj34(Object obj34) {
		this.obj34 = obj34;
	}


	public Object getObj35() {
		return obj35;
	}


	public void setObj35(Object obj35) {
		this.obj35 = obj35;
	}


	public Object getObj36() {
		return obj36;
	}


	public void setObj36(Object obj36) {
		this.obj36 = obj36;
	}


	public Object getObj37() {
		return obj37;
	}


	public void setObj37(Object obj37) {
		this.obj37 = obj37;
	}


	public Object getObj38() {
		return obj38;
	}


	public void setObj38(Object obj38) {
		this.obj38 = obj38;
	}


	public Object getObj39() {
		return obj39;
	}


	public void setObj39(Object obj39) {
		this.obj39 = obj39;
	}


	public Object getObj40() {
		return obj40;
	}


	public void setObj40(Object obj40) {
		this.obj40 = obj40;
	}


	public Object getObj41() {
		return obj41;
	}


	public void setObj41(Object obj41) {
		this.obj41 = obj41;
	}


	public Object getObj42() {
		return obj42;
	}


	public void setObj42(Object obj42) {
		this.obj42 = obj42;
	}


	public Object getObj43() {
		return obj43;
	}


	public void setObj43(Object obj43) {
		this.obj43 = obj43;
	}
	
	public Object getObj44() {
		return obj44;
	}
	public void setObj44(Object obj44) {
		this.obj44 = obj44;
	}
	public Object getObj45() {
		return obj45;
	}
	public void setObj45(Object obj45) {
		this.obj45 = obj45;
	}
	public Object getObj46() {
		return obj46;
	}
	public void setObj46(Object obj46) {
		this.obj46 = obj46;
	}
	public Object getObj47() {
		return obj47;
	}
	public void setObj47(Object obj47) {
		this.obj47 = obj47;
	}
	public Object getObj48() {
		return obj48;
	}
	public void setObj48(Object obj48) {
		this.obj48 = obj48;
	}
	public Object getObj49() {
		return obj49;
	}
	public void setObj49(Object obj49) {
		this.obj49 = obj49;
	}
	public Object getObj50() {
		return obj50;
	}
	public void setObj50(Object obj50) {
		this.obj50 = obj50;
	}
	public Object getObj0() {
		return obj0;
	}


	public void setObj0(Object obj0) {
		this.obj0 = obj0;
	}
	

	public List getList1() {
		return list1;
	}


	public void setList1(List list1) {
		this.list1 = list1;
	}



	
	public Object getNobNull() {
		return nobNull;
	}
	public void setNobNull(Object nobNull) {
		this.nobNull = nobNull;
	}
	public Object getNobOther() {
		return nobOther;
	}
	public void setNobOther(Object nobOther) {
		this.nobOther = nobOther;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "key:"+key+"|obj:"+obj+"|obj1:"+obj1+"|obj2:"+obj2+"|obj3:"+obj3+"|obj4:"+obj4+"|obj5:"+obj5+"|obj6:"+obj6+"|obj7:"+obj7+"|obj8:"+obj8+"|obj9:"+obj9+"|obj10:"+obj10;
	}
	/**
	 * obj1到obj2相加<br>
	 * 整型
	 * */
	public static int add1To8(MMap mMap){
		int i=Integer.parseInt(isnull(mMap.getObj1()))+Integer.parseInt(isnull(mMap.getObj2()));
		return i;
	}
	/**
	 * obj1到obj10相加<br>
	 * 整型
	 * */
	public static int add1To10(MMap mMap){
		int i=Integer.parseInt(isnull(mMap.getObj1()))+Integer.parseInt(isnull(mMap.getObj2()))+Integer.parseInt(isnull(mMap.getObj3()))+Integer.parseInt(isnull(mMap.getObj4()))
				+Integer.parseInt(isnull(mMap.getObj5()))+Integer.parseInt(isnull(mMap.getObj6()))+Integer.parseInt(isnull(mMap.getObj7()))+Integer.parseInt(isnull(mMap.getObj8()))
				+Integer.parseInt(isnull(mMap.getObj9()))+Integer.parseInt(isnull(mMap.getObj10()));
		return i;
	}
	/**
	 * 判空返回值
	 * */
	public static String isnull(Object obj){
		String str="0";
		if(obj!=null && !"".equals(obj)){
			str=obj.toString();
		}
		return str;
	}
	/**
	 * 判空返回值
	 * */
	public static String isnullStr(Object obj){
		String str="";
		if(obj!=null){
			str=obj.toString();
		}
		return str;
	}
	/**如果为null则变为0*/
	public static Integer isnullInt(Object obj){
		Integer sint=0;
		if(obj!=null && !"".equals(obj)){
			sint=Integer.parseInt(obj.toString());
		}
		return sint;
	}
	/**如果为null则变为0*/
	public static double isnullDouble(Object obj){
		double sdou=0;
		if(obj!=null && !"".equals(obj)){
			sdou=Double.parseDouble(obj.toString());
		}
		return sdou;
	}
	/**强制转成Integer类型*/
	public static Integer changeForceToInteger(Object obj){
		Integer ss=0;
		if(obj!=null && !"".equals(obj)){
			if(obj instanceof Double){
				double d = ((Double) obj).doubleValue();
				ss=(int)Math.rint(d);//四舍五入取整
			}
			if(obj instanceof Integer){
				ss=((Integer) obj).intValue();
			}
		}
		return ss;
	}
	/**
	 * map工具
	 * @param map
	 * @param key
	 * @param value
	 * @return
	 */
	public static Map<String, Double> setMapValuesOfDouble(Map<String, Double> map, String key, Object obj) {
		if(obj!=null&&!obj.toString().trim().equals("")){
			Double value=Double.valueOf(obj.toString());
			if(map.containsKey(key)){
				value=value+map.get(key);
			}
			map.put(key, value);
		}
		return map;
	}
	/**
	 * map工具
	 * @param map
	 * @param key
	 * @param value
	 * @return
	 * 当小于0的时候累加
	 */
	public static Map<String, Double> setMapValuesOfDoubleV2(Map<String, Double> map, String key, Object obj) {
		if(obj!=null&&!obj.toString().trim().equals("")){
			Double value=Double.valueOf(obj.toString());
			if(value<0){
				if(map.containsKey(key)){
					value=value+map.get(key);
				}
				map.put(key, value);
			}
		}
		return map;
	}
	/**
	 * map工具
	 * @param map
	 * @param key
	 * @param value
	 * @return
	 */
	public static Map<String, Integer> setMapValuesOfInteger(Map<String, Integer> map, String key, Object obj) {
		if(obj!=null&&!obj.toString().trim().equals("")){
			Integer value=Integer.valueOf(obj.toString());
			if(map.containsKey(key)){
				value=value+map.get(key);
			}
			map.put(key, value);
		}
		return map;
	}
	/**
	 * 
	* @Description:Map value排序 
	* @author hyl     
	* @date 2017-9-27 下午4:37:03  
	* @version V1.0 
	* @author user
	 */
	public static List<Entry<String, Integer>> sortMap(Map<String, Integer> map_sort){
		Set<Entry<String, Integer>> ks = map_sort.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(ks);
        Collections.sort(list, new Comparator<Entry<String, Integer>>() {
 
            @Override
            public int compare(Entry<String, Integer> o1,
                    Entry<String, Integer> o2) {
                if (o1.getValue() < o2.getValue())
                    return -1;
                else if (o1.getValue() > o2.getValue())
                    return 1;
                return 0;
            }
        });
        //System.out.println(list);
        return list;
	}
	/**
	 * 分隔字符串
	 * */
	public String  subMystring(String str){
		String ss="";
		if(StringUtils.isNotBlank(str)){
			ss=str.substring(0, str.length()-1);
		}
		return ss;
	}
	/**
	 * 判断是否是数字
	 * */
	public static boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(str).matches();    
	 }
	/**判断为空或者为0，真则返回true**/
	public static boolean isNullOrZero(Double num){
		boolean flag=false;
		if(num==null){
			flag=true;
		}
		return flag;
	}
	/**
	 * 将一个或者多个空格替换为%
	 * */
	public static String replaceSpaceToPercent(String replacestr){
		replacestr=replacestr.trim();
		if(StringUtils.isNotBlank(replacestr)){
			replacestr=replacestr.replaceAll(" +","%");
		}
		return replacestr;
	}
	/**判断List，不为null且size>0返回true，为空返回false**/
	public static boolean validateList(List list) {
		if(list != null && list.size() > 0){
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 
	* @Description: 检查数组是否包含某个值 
	* @author hyl     
	 */
	public static boolean loopArrays(String[] arr, String targetValue) {
        for (String s : arr) {
            if (s.equals(targetValue))
                return true;
        }
        return false;
    }
	/**
	 * 
	* @Description: 数组转成字符串  
	* @author hyl     
	 */
	public static String arraysToString(Object[] a) {
        if (a == null)
            return null;

        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(String.valueOf(a[i]));
            if (i == iMax)
                return b.append(']').toString();
            b.append(",");
        }
    }
	public static void main(String[] args) {
		System.out.println(changeForceToInteger(0.4)+"-----1");
		System.out.println(changeForceToInteger(3)+"-----2");
		System.out.println(changeForceToInteger(3.75)+"-----3");
	}
}
