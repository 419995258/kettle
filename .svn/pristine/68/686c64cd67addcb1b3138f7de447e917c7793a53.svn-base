
/**    
* @Title: MyOracleDialect.java  
* @Package org.my431.hibernate.myDialect  
* @Description: TODO(用一句话描述该文件做什么)  
* @author hyl     
* @date 2017-7-21 下午3:33:35  
* @version V1.0 
* @author user   
*/  

package org.my431.hibernate.myDialect;

import java.sql.Types;

import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.type.StandardBasicTypes;


/**  
 * @author hyl  
 * @author user
 * 由于nvarchar类型，写这个进行hibernate方言拓展
 * http://blog.csdn.net/tongyuehong137/article/details/50055395   
 */
public class MyOracleDialect extends Oracle10gDialect{
	public MyOracleDialect() {
		super();
		registerHibernateType(Types.CHAR, StandardBasicTypes.STRING.getName());          
	    registerHibernateType(Types.NVARCHAR, StandardBasicTypes.STRING.getName());        
	    registerHibernateType(Types.LONGNVARCHAR, StandardBasicTypes.STRING.getName());  
	    registerHibernateType(Types.DECIMAL, StandardBasicTypes.DOUBLE.getName());        
	    registerHibernateType(Types.NCLOB, StandardBasicTypes.STRING.getName());  
	}
}
