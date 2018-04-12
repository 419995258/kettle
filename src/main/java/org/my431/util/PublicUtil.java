package org.my431.util;

import java.math.BigDecimal;
import java.util.*;

import org.my431.base.services.CacheBasePropertiesManager;

public class PublicUtil 
{
	
	/**
	 * 根据人数，设备数返回属性范围内的比例值
	 * @param peopleCntStr
	 * @param deviceCntStr
	 * @return
	 */
	public static String getPeopleDeviceRate(String peopleCntStr, String deviceCntStr, String flag)
	{
		int noneMin = -1, noneMax = 999999;
		int rateVar = 0, rateMax = 0, rateScale = 0;
		if("teacher".equals(flag))
		{
			rateVar = 10;
			rateMax = 30;
			rateScale = 1;
		}else if("student".equals(flag))
		{
			rateVar = 100;
			rateMax = 300;
			rateScale = 0;
		}else
		{
			rateVar = 50;
			rateMax = 150;
			rateScale = 0;
		}
		BigDecimal peopleCnt = new BigDecimal((peopleCntStr == null || peopleCntStr.equals(""))?"0":peopleCntStr);
		BigDecimal deviceCnt = new BigDecimal((deviceCntStr == null || deviceCntStr.equals(""))?"0":deviceCntStr);
		if (deviceCnt.signum() <= 0)
			return noneMin+"";
		if (peopleCnt.signum() <= 0)
			return noneMax+"";
		BigDecimal rate = deviceCnt.multiply(new BigDecimal(rateVar)).divide(peopleCnt, rateScale, BigDecimal.ROUND_HALF_UP);
		if (rate.compareTo(new BigDecimal(rateMax)) >= 0)
			return rateMax+"";
		else
			return rate.toString();
	}
	
	/**
	 * 根据学校类型返回过滤条件字符串(base_school)
	 * @param prefix
	 * @param schoolType
	 * @return
	 */
	public static String getSchoolTypeFilter(String prefix, String schoolType)
	{
		schoolType = schoolType == null ? "" : schoolType;
		prefix = (prefix==null || prefix.length()<=0)?"":(prefix+".");
		org.my431.base.model.BaseProperties prop = CacheBasePropertiesManager.getPropertiesByPropertyKey(schoolType);
		String filter = "";
		if (prop != null && prop.getExtra1() != null)
		{
			String[] xxjgbxlxm = prop.getExtra1().split("#");
			for (String s : xxjgbxlxm)
			{
				if (s.length() == 3)
				{
					if (!"".equals(filter))
					{
						filter += " or ";
					}
					filter += (prefix+"xxjgbxlxm3='"+s+"'");
				} else if (s.length() == 2)
				{
					if (!"".equals(filter))
					{
						filter += " or ";
					}
					filter += (prefix+"xxjgbxlxm2='"+s+"'");
				} else if (s.length() == 1)
				{
					if (!"".equals(filter))
					{
						filter += " or ";
					}
					filter += (prefix+"xxjgbxlxm1='"+s+"'");
				}
			}
		}
		return "("+("".equals(filter)?"1=2":filter)+")";
	}
	
	/**
	 * 返回百分比的字符串
	 * @param total
	 * @param theOne
	 * @return
	 */
	public static String getPercentString(Object total, Object theOne)
	{
		int scale = 5;
		BigDecimal theOneBig = new BigDecimal(theOne==null?"0":theOne.toString());
		BigDecimal totalBig = new BigDecimal(total==null?"0":total.toString());
		String result = String.format("%."+(scale-2)+"f", (totalBig.signum()==0?new BigDecimal(0):(theOneBig.multiply(new BigDecimal(100)).divide(totalBig, scale, BigDecimal.ROUND_HALF_UP)).floatValue()));
		return result+"%";
	}
	
	public static void dealInfoValueMap(Map<String, String> m, String infoStr)
	{
		dealInfoValueMap(m, infoStr, null);
	}
	
	/**
	 * 处理格式如：key1:cnt1#key2:cnt2#key3:cnt3的汇总字符串
	 * @param m
	 * @param infoStr
	 */
	public static void dealInfoValueMap(Map<String, String> m, String infoStr, String prefix)
	{
		if (infoStr != null)
		{
			for (String info : infoStr.split("#"))
			{
				String[] s = info.split(":");
				if (s.length == 2)
				{
					String key = (prefix==null?"":prefix)+s[0];
					m.put(key, ((m.containsKey(key)?Long.valueOf(m.get(key)):0)+Long.valueOf(s[1]))+"");
				}
			}
		}
	}
	
	/**
	 * 教材-按地区对出版社进行排名
	 */
	public static void dealTeachingMaterialList(Map<String, List<Object[]>> areaDataMap, String areaCode, String company, long bookCnt)
	{
		if(company == null || bookCnt <=0)	return;
		List<Object[]> list = areaDataMap.get(areaCode);
		if(list == null)
		{
			list = new java.util.ArrayList<Object[]>();
			list.add(new Object[]{"all", bookCnt});
			list.add(new Object[]{company, bookCnt});
			areaDataMap.put(areaCode, list);
		}else
		{
			Object[] row0 = list.get(0);
			row0[1] = Long.valueOf(row0[1].toString()) + bookCnt;
			int xIndex = 0;
			Object[] xRow = null;
			int zIndex = list.size();
			for(int i=1;i<list.size();i++)
			{
				Object[] row = list.get(i);
				if(company.equals(row[0].toString()))
				{
					row[1] = Long.valueOf(row[1].toString()) + bookCnt;
					xIndex = i;
					xRow = row;
					break;
				}
				if(bookCnt > Long.valueOf(row[1].toString()) && zIndex==list.size())
				{
					zIndex = i;
				}
			}
			if(xIndex > 0)
			{
				for(int i=xIndex-1;i>0;i--)
				{
					if(Long.valueOf(xRow[1].toString()) > Long.valueOf(list.get(i)[1].toString()))
					{
						if(i==1 || Long.valueOf(xRow[1].toString())<=Long.valueOf(list.get(i-1)[1].toString()))
						{
							list.remove(xIndex);
							list.add(i, xRow);
						}else
						{
							continue;
						}
					}else
					{
						break;
					}
				}
			}else
			{
				list.add(zIndex, new Object[]{company, bookCnt});
			}
		}
	}
	
	/**
	 * 教材-按出版社对学科进行统计
	 */
	public static void dealTeachingMaterialList2(Map<String, Map<String, Long>> areaDataMap, String areaCode, String subject, long bookCnt)
	{
		if(subject == null || bookCnt <=0)	return;
		Map<String, Long> map = areaDataMap.get(areaCode);
		map = map==null?new HashMap<String, Long>():map;
		map.put("all", (map.containsKey("all")?map.get("all"):0)+bookCnt);
		map.put(subject, (map.containsKey(subject)?map.get(subject):0)+bookCnt);
		areaDataMap.put(areaCode, map);
	}
	
	public static String fmtNumberMoney(String number)
	{
		java.text.DecimalFormat fmt = new java.text.DecimalFormat();
		fmt.applyPattern("##,###.######");
		return fmt.format(Double.valueOf(number));
	}

}
