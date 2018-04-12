
/**    
* @Title: Entity.java  
* @Package org.my431.util  
* @Description: TODO(用一句话描述该文件做什么)  
* @author hyl     
* @date 2017-11-3 下午3:58:31  
* @version V1.0 
* @author user   
*/  

package org.my431.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.my431.base.model.BaseSchool;
import org.my431.base.model.BaseSchoolFsb;


/**  
 * @author hyl  
 * @author user   
 */
public abstract  class CloneEntity implements Cloneable {
	
    /**
     * 对象的克隆方法， 伪深度克隆，也就是说对象里的除常用数据类型外的属性都是 Entity的子类就没问题，
     * 但是StringBuffer，HashMap这些东西就不行了
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object clone() {
        try {
            Object entity = super.clone();
            for (Field field : getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    if( field.get(this) != null){
                        // 对象
                        if (CloneEntity.class.isAssignableFrom(field.getType())) {
                            Object value = field.get(this);
                            field.set(this, ((CloneEntity) value).clone());
                        } else if (List.class.isAssignableFrom(field.getType())) {
                            List<Object> datas = new ArrayList<Object>();
                            for (Object childEntity: (List<Object>)field.get(entity)) {
                                if(CloneEntity.class.isAssignableFrom(childEntity.getClass())){
                                    datas.add(((CloneEntity)childEntity).clone());                                       
                                } else {
                                    datas.add(childEntity);                                     
                                }
                            }
                            field.set(this, datas);
                        }                       
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return entity;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }



    /**
     * 两个相同属性的类的克隆 比如 Data1 和 Data2的属性完全一样，那么就可以这样 Data2 data2 =
     * data1.cloneTo(Data2.class) 当然这里考虑的常用的数据类型 和
     * 继承至这个类的对象，特殊的数据类型可能不支持，但是已经达到我的要求了 这个最好写在一个所有对象的父类里，要不然又有其他问题
     * 
     * 同类克隆用clone()方法， 不同类克隆用cloneTo(Class<?>)方法
     * @param clazz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @SuppressWarnings("unchecked")
    public <T> T cloneTo(Class<T> clazz) {
        T newObj = null;
        try {
            Object entity = super.clone();
            if (entity.getClass().equals(clazz)) {
                newObj = (T) entity;
            } else {
                try {
                    newObj = clazz.newInstance();
                    //已经有值的类的所有属性
                    //List<Field> fields = FieldUtil.getAllFields(getClass());
                    List<Field> fields = HibernateToolsUtil.getAllFieldList(getClass());
                    for (Field field : fields) {
                        field.setAccessible(true);
                        //等待赋值的类的所有属性
                       //List<Field> field2s = FieldUtil.getAllFields(clazz);
                        List<Field> field2s = HibernateToolsUtil.getAllFieldList(clazz);
                        for (Field field2 : field2s) {
                            if (field2.getName().equals(field.getName())) {
                                field2.setAccessible(true);
                                if(field.get(entity) != null){
                                    // Entity是当前类，也就是所有能互相复制的父类
                                    if (CloneEntity.class.isAssignableFrom(field.getType())) {
                                        CloneEntity child = (CloneEntity) field.get(entity);
                                        field2.set(newObj, child.cloneTo(field2.getType()));                                        
                                    } else if (List.class.isAssignableFrom(field.getType())) {
                                        List<Object> datas = new ArrayList<Object>();
                                        for (Object childEntity: (List<Object>)field.get(entity)) {
                                            if(CloneEntity.class.isAssignableFrom(childEntity.getClass())){
                                                datas.add(((CloneEntity)childEntity).clone());                                       
                                            } else {
                                                datas.add(childEntity);                                     
                                            }
                                        }

                                        field2.set(newObj, datas);
                                    } else {
                                        field2.set(newObj, field.get(entity));
                                    }
                                }
                            }
                        }
                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return newObj;
    }



    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        //List<Field> fields = FieldUtil.getAllFields(getClass());
        List<Field> fields = HibernateToolsUtil.getAllFieldList(getClass());
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(this);
                sb.append('\"').append(field.getName()).append("\":");
                if (field.getType().isPrimitive()
                        || field.getType() == String.class || Enum.class.isAssignableFrom(field.getType())) {
                    sb.append('\"').append(value.toString()).append('\"')
                            .append(',');
                } else if (List.class.isAssignableFrom(field.getType())) {
                    List<?> datas = (List<?>) value;
                    sb.append('[');
                    for (int i = 0; i < datas.size(); i++) {
                        sb.append(datas.get(i).toString()).append(',');
                    }
                    if (sb.toString().endsWith(",")) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    sb.append(']').append(',');
                } 
                else {// 对象的值不需要引号
                    sb.append(value.toString()).append(',');
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (sb.toString().endsWith(",")) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("}");
        return sb.toString();
    }
    public static void main(String[] args) {
		BaseSchool baseSchool = new BaseSchool();//BaseSchool和BaseSchoolFsb需要集成接口
		baseSchool.setSchoolName("12345566");
		BaseSchoolFsb baseSchoolFsb = baseSchool.cloneTo(BaseSchoolFsb.class);
		System.out.println(baseSchoolFsb.getSchoolName());
	}
}
