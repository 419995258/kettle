package org.my431.base.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.my431.base.model.BaseArea;
import org.my431.base.model.BaseAreaTree;
import org.my431.plugin.redis.services.RedisManager;
import org.my431.util.GeneralTreeNode;
import org.my431.util.MMap;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

/**
 * 封装memcached中area相关操作
 * @author wangzhen
 *
 */
public class CacheBaseAreaManager {
	//所有地区map的key，map结构：Map<String,GeneralTreeNode>
	private static String AreaListkey="global.base.BaseArea.Hashtable.list";
	private static String AreaMapkey="global.base.BaseArea.LinkedHashMap.map";
	private static String AreaMapIdkey="global.base.BaseArea.key.id.";
	private static String AreaMapCodekey="global.base.BaseArea.key.code.";
	private static String MapAreaMapCodekey="global.base.BaseArea.key.mapcode.";
	//hyl20161128数据库查出的地区树形结构放到这里。
	private static String AreaListMapOfAllkey="global.base.BaseArea.key.mapcode";
	//测试mc内存占用情况 开始
	private static String TestMcdataIdkey="TestMcdata.key.id.";
	public static void setTestMcdataIdkeyMap(String id,GeneralTreeNode node){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		redisManager.setOValue(TestMcdataIdkey+id,node);
	}
	public static GeneralTreeNode getTestMcdataById(String id){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		if(redisManager.objectHasKey(TestMcdataIdkey+id)){
			return (GeneralTreeNode)redisManager.getOValue(TestMcdataIdkey+id);
		}else{
			return null;
		}
	}
	//测试mc内存占用情况 结束

	public static LinkedHashMap<String,GeneralTreeNode> getAllAreaMap(){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		if(redisManager.objectHasKey(AreaListkey)){
			return (LinkedHashMap<String,GeneralTreeNode>)redisManager.getOValue(AreaListkey);
		}else{
			return null;
		}
	}
	
	
	public static void setAllAreaMap(Map<String,GeneralTreeNode> map){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		redisManager.setOValue(AreaListkey,map);
	}
	
	public static void setAreaIdKeyMap(String id,BaseArea node){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		redisManager.setOValue(AreaMapIdkey+id,node);
	}
	
	public static void removeNodeById(String id){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		redisManager.removeOValue(AreaMapIdkey+id);
	}
	
	public static BaseArea getNodeById(String id){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		if(redisManager.objectHasKey(AreaMapIdkey+id)){
			return (BaseArea)redisManager.getOValue(AreaMapIdkey+id);
		}else{
			return null;
		}
	}
	
	public static void setAreaCodeKeyMap(String code,BaseArea node){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		redisManager.setOValue(AreaMapCodekey+code,node);
	}
	
	public static void setMapAreaCodeKeyMap(String code,BaseArea node){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		redisManager.setOValue(MapAreaMapCodekey+code,node);
	}
	/**根据code取到地区*/
	public static BaseArea getNodeByCode(String code){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		if(redisManager.objectHasKey(AreaMapCodekey+code)){
			return (BaseArea)redisManager.getOValue(AreaMapCodekey+code);
		}else{
			return null;
		}
	}
	public static void setAreaListMapOfAllkey(List<HashMap<Object,Object>> list){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		redisManager.setOValue(AreaListMapOfAllkey,list);
	}
	public static List<HashMap<Object,Object>> getAreaListMapOfAllkey(){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		if(redisManager.objectHasKey(AreaListMapOfAllkey)){
			return (List<HashMap<Object, Object>>) redisManager.getOValue(AreaListMapOfAllkey);
		}else{
			return null;
		}
	}
	/**
	 * 
     *类的描述:getAddressStr<br>
     *功能描述 ：areaID 地区id,type:省级=3，市级=6，县级=9<br>
     *作者:hyl<br>
     *创建日期:2015-07-02<br>
     *修改人：<br>
     *修改日期：<br>
     *修改原因描述:<br>
	 */
	public static String getAddressStr(String areaID,String type){
		String addresssStr="";
		try {
			if("3".equals(type)){
				addresssStr=getNodeById(areaID).getAreaName();
			}
			if("6".equals(type)){
				addresssStr=getNodeByCode(getNodeById(areaID).getParentCode()).getAreaName()+">>"+getNodeById(areaID).getAreaName();
			}
			if("9".equals(type)){
				String citycode=getNodeByCode(getNodeById(areaID).getParentCode()).getParentCode();
				addresssStr=getNodeByCode(citycode).getAreaName()+">>"+getNodeByCode(getNodeById(areaID).getParentCode()).getAreaName()+">>"+getNodeById(areaID).getAreaName();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("传入参数错误。");
			addresssStr="传入参数错误";
		}
		return addresssStr;
	}
	/**
	 * 
     *类的描述:getAddressStr<br>
     *功能描述 ：areaID 地区id,type:省级=3，市级=6，县级=9；obj存省，obj1存市,obj2存县<br>
     *作者:hyl<br>
     *创建日期:2015-07-02<br>
     *修改人：<br>
     *修改日期：<br>
     *修改原因描述:<br>
	 */
	public static MMap getDetailAddressMMap(String areaID,String type){
		MMap mMap=new MMap();
		try {
			if("3".equals(type)){
				mMap.setObj(getNodeById(areaID).getAreaName());
				//addresssStr=getNodeById(areaID).getAreaName();
			}
			if("6".equals(type)){
				mMap.setObj(getNodeByCode(getNodeById(areaID).getParentCode()).getAreaName());
				mMap.setObj1(getNodeById(areaID).getAreaName());
				//addresssStr=getNodeByCode(getNodeById(areaID).getParentCode()).getAreaName()+">>"+getNodeById(areaID).getAreaName();
			}
			if("9".equals(type)){
				String citycode=getNodeByCode(getNodeById(areaID).getParentCode()).getParentCode();
				mMap.setObj(getNodeByCode(citycode).getAreaName());
				mMap.setObj1(getNodeByCode(getNodeById(areaID).getParentCode()).getAreaName());
				mMap.setObj2(getNodeById(areaID).getAreaName());
				//addresssStr=getNodeByCode(citycode).getAreaName()+">>"+getNodeByCode(getNodeById(areaID).getParentCode()).getAreaName()+">>"+getNodeById(areaID).getAreaName();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("传入参数错误。");
			//addresssStr="传入参数错误";
		}
		return mMap;
	}
	
	/**传入地区编号，得到具体的地址*/
	public static String getDetailAddrByAreaCode(String areaCode){
		String str="";
		if(StringUtils.isNotBlank(areaCode)){
			if(areaCode.length()==3){
				str=getNodeByCode(areaCode).getAreaName();
			}
			if(areaCode.length()==6){
				String provinceCode=getNodeByCode(areaCode).getParentCode();
				str=getNodeByCode(provinceCode).getAreaName()+">"+getNodeByCode(areaCode).getAreaName();
			}
			if(areaCode.length()==9){
				String cityCode=getNodeByCode(areaCode).getParentCode();
				String provinceCode=getNodeByCode(cityCode).getParentCode();
				str=getNodeByCode(provinceCode).getAreaName()+">"+getNodeByCode(cityCode).getAreaName()+">"+getNodeByCode(areaCode).getAreaName();
			}
		}
		return str;
	}
	public static BaseArea getMapAreaCodeKeyMap(String code){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		if(redisManager.objectHasKey(MapAreaMapCodekey+code)){
			return (BaseArea)redisManager.getOValue(MapAreaMapCodekey+code);
		}else{
			return null;
		}
	}
	public static void removeNodeByCode(String code){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		redisManager.removeOValue(AreaMapCodekey+code);
	}
	public static void setAreaMapkey(Map<String,BaseAreaTree> map){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		redisManager.setOValue(AreaMapkey,map);
	}
	
	public static LinkedHashMap<String,BaseAreaTree> getAreaMapkey(){
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		if(redisManager.objectHasKey(AreaMapkey)){
			return (LinkedHashMap<String,BaseAreaTree>)redisManager.getOValue(AreaMapkey);
		}else{
			return null;
		}
	}
	
	public static List<BaseAreaTree> getTreeByParentCode(String parentCode) {
		List<BaseAreaTree> tree=new ArrayList<BaseAreaTree>();
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		if(redisManager.objectHasKey(AreaMapkey)){
			Map<String,BaseAreaTree> list=new LinkedHashMap<String,BaseAreaTree>();
			Map<String,BaseAreaTree> map=(LinkedHashMap<String,BaseAreaTree>)redisManager.getOValue(AreaMapkey);
			if(parentCode!=null){
				if(parentCode.equals("-1")){
					Set set=map.keySet();
					for (Iterator it = set.iterator(); it.hasNext();) {
			            String s = (String) it.next();
			            BaseAreaTree map2=map.get(s);
			            tree.add(map2);
			        }
				}
				if(parentCode.length()==3){
					if(CacheBaseAreaManager.getNodeByCode(parentCode).getNodeType().equals("0")){
						 tree=null;
					}else{
						if(map.get(parentCode)!=null&&map.get(parentCode).getSubAreaTree()!=null){
							list=map.get(parentCode).getSubAreaTree().get(parentCode);
							if(list!=null){
							Set set=list.keySet();
								for (Iterator it = set.iterator(); it.hasNext();) {
						            String s = (String) it.next();
						            BaseAreaTree map2=list.get(s);
						            tree.add(map2);
						        }
							}
						}
					}
					
				}
				if(parentCode.length()==6){
					if(CacheBaseAreaManager.getNodeByCode(parentCode).getNodeType().equals("0")){
						 tree=null;
					}else{
						 Map<String,Map<String,BaseAreaTree>>   list2=new LinkedHashMap<String,Map<String,BaseAreaTree>>();
						 list2=map.get(parentCode.substring(0, 3)).getSubAreaTree().get(parentCode.substring(0, 3)).get(parentCode).getSubAreaTree();
						 if(list2!=null){
							 Map<String, BaseAreaTree> m=list2.get(parentCode);
							 if(m!=null){
								 Set set=m.keySet();
								 for (Iterator it = set.iterator(); it.hasNext();) {
									 String s = (String) it.next();
					            	 tree.add(m.get(s));
								 }
							 }
						 }
					}
				}
				if(parentCode.length()==9){
					tree=null;
				}
			}
			return tree;
		}else{
			return null;
		}
	}
	
	public static GeneralTreeNode baseAreaTreeToGeneralTreeNode(BaseAreaTree baseAreaTree){
		GeneralTreeNode treeNode = new GeneralTreeNode();
		treeNode.setId(baseAreaTree.getId());
		treeNode.setNodeCode(baseAreaTree.getNodeCode());
		treeNode.setParentCode(baseAreaTree.getParentCode());
		treeNode.setNodeName(baseAreaTree.getNodeName());
		treeNode.setNodeDescName(baseAreaTree.getNodeDescName());
		treeNode.setNodeLink(baseAreaTree.getNationalCode());
		treeNode.setNodeType(baseAreaTree.getNodeType());
		treeNode.setNodeExtra(baseAreaTree.getAreaType());
		treeNode.setNodeLevel(baseAreaTree.getNodeLevel());
		treeNode.setMapCode(baseAreaTree.getMapAreaCode());
		treeNode.setIsChx(baseAreaTree.getIsChx());
		treeNode.setTbFlag(baseAreaTree.getTbFlag());
		treeNode.setIsPkx(baseAreaTree.getIsPkx());
		treeNode.setIsZcq(baseAreaTree.getIsZcq());
		treeNode.setMapParentCode(baseAreaTree.getMapParentCode());
		return treeNode;
	}
	
	public static Map<String,GeneralTreeNode> getAllTreeByParentCode(String parentCode) {
		
		Map<String,GeneralTreeNode> map=new LinkedHashMap<String,GeneralTreeNode>();
		if(parentCode!=null){
			if(parentCode.equals("-1")){
				map=CacheBaseAreaManager.getAllAreaMap();
			}else{
				map.put(parentCode, CacheBaseAreaManager.getAllAreaMap().get(parentCode));
					List<BaseAreaTree> list=getTreeByParentCode(parentCode);
					if(list!=null){
						for(BaseAreaTree baseAreaTree:list){
							GeneralTreeNode treeNode =baseAreaTreeToGeneralTreeNode(baseAreaTree);
							map.put(treeNode.getNodeCode(), treeNode);
							if(baseAreaTree.getSubAreaTree()!=null&&baseAreaTree.getSubAreaTree().size()>0){
								Map<String, Map<String, BaseAreaTree>> submap1=baseAreaTree.getSubAreaTree();
								Map<String, BaseAreaTree> m1=submap1.get(treeNode.getNodeCode());
								Set set=m1.keySet();
								 for (Iterator it = set.iterator(); it.hasNext();) {
									String s = (String) it.next();
									BaseAreaTree baseAreaTree2 =m1.get(s);
					            	GeneralTreeNode treeNode2 =baseAreaTreeToGeneralTreeNode(baseAreaTree2);
									map.put(treeNode2.getNodeCode(), treeNode2);
									if(baseAreaTree.getSubAreaTree()!=null&&baseAreaTree.getSubAreaTree().size()>0){
										Map<String, Map<String, BaseAreaTree>> submap2=baseAreaTree.getSubAreaTree();
										Map<String, BaseAreaTree> m2=submap2.get(treeNode.getNodeCode());
										Set set2=m2.keySet();
										 for (Iterator it2 = set2.iterator(); it2.hasNext();) {
											String s2 = (String) it2.next();
											BaseAreaTree baseAreaTree3 =m2.get(s2);
								            GeneralTreeNode treeNode3 =baseAreaTreeToGeneralTreeNode(baseAreaTree3);
											map.put(treeNode3.getNodeCode(), treeNode3);
										}
									}
								 }
							}
						}
					}
			}
		}
		return map;
	}
	
	/**
	 * 通过地区id查询父亲地区信息
	 * @author    hmc    2015年4月29日  下午6:43:02
	 * @return
	 */
	public BaseArea getBaseAreaBySonId(String sonAreaId){
		
		return null;
	}
}
