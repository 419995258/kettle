package org.my431.platform.web;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.my431.platform.dao.EntityDao;
import org.my431.platform.utils.BeanUtils;
import org.my431.platform.utils.GenericsUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("unchecked")
public abstract class StrutsTreeEntityAction<T, M extends EntityDao<T>> extends
		StrutsAction implements Preparable, ModelDriven, InitializingBean {

	private static final long serialVersionUID = 6564831649517618075L;

	protected String codeName;

	protected Class<T> entityClass; // Action所管理的Entity类型.

	protected Class idClass; // Action所管理的Entity的主键类型.

	protected String idName = "id"; // Action所管理的Entity的主键名.

	private M entityManager; // Action管理Entity所用的manager.

	/**
	 * 取得entityClass的函数. JDK1.4不支持泛型的子类可以抛开Class<T> entityClass,重载此函数达到相同效果。
	 */
	/*protected Class<T> getEntityClass() {
		if (entityClass == null) {
			afterPropertiesSet();
		}
		return entityClass;
	}*/

	/**
	 * 获得EntityManager类进行CRUD操作,可以在子类重载.
	 */
	/*protected M getEntityManager() {
		if (entityManager == null) {
			afterPropertiesSet();
		}
		Assert.notNull(entityManager, "Manager未能成功初始化");
		return entityManager;
	}*/

	/**
	 * Init回调函数,初始化一系列泛型参数.
	 */
	public void afterPropertiesSet() {
		// 根据T,反射获得entityClass
		/*entityClass = GenericsUtils.getSuperClassGenricType(getClass());

		// 根据M,反射获得符合M类型的manager
		List<Field> fields = BeanUtils.getFieldsByType(this, GenericsUtils
				.getSuperClassGenricType(getClass(), 1));
		Assert.isTrue(fields.size() == 1,
				"subclass's has not only one entity manager property.");
		try {
			entityManager = (M) BeanUtils.forceGetProperty(this, fields.get(0)
					.getName());
			Assert.notNull(entityManager,
					"subclass not inject manager to action sucessful.");
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}

		// 反射获得entity的主键类型
		try {
			idName = entityManager.getIdName(entityClass);
			idClass = BeanUtils.getPropertyType(entityClass, idName);
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}*/
	}

	/**
	 * 获取业务对象树的函数.
	 */
	protected void doTreeList(HttpServletRequest request) {

	}

	/**
	 * 重载业务对象树的函数.
	 */
	protected void doReload() {

	}

	/**
	 * 获取业务对象列表的函数.
	 */
	protected void doListEntity(HttpServletRequest request) {
	}

	/**
	 * 新建业务对象的函数.
	 */
	/*protected T doNewEntity(HttpServletRequest request) {
		T object = null;
		try {
			object = getEntityClass().newInstance();
		} catch (Exception e) {
			log.error("Can't new Instance of entity.", e);
		}
		return object;
	}*/

	/**
	 * 从数据库获取业务对象的函数.
	 */
	/*protected T doGetEntity(HttpServletRequest request) {
		Serializable id = getEntityId(request);
		return getEntityManager().get(id);
	}*/

	/**
	 * 保存业务对象的函数.
	 */
	/*protected void doSaveEntity(HttpServletRequest request, T object) {
		getEntityManager().save(object);
	}
*/
	/**
	 * 删除业务对象的函数.
	 */
	/*protected void doDeleteEntity(HttpServletRequest request) {
		Serializable[] ids = getEntityIds(request);
		if (ids != null) {
			for (Serializable id : ids) {
				getEntityManager().removeById(id);
			}
			return;
		}

		Serializable id = getEntityId(request);
		getEntityManager().removeById(id);
	}*/

	/**
	 * 从request中获得Entity的id，并判断其有效性.
	 */
	protected Serializable[] getEntityIds(HttpServletRequest request) {
		String[] idString = request.getParameterValues(idName);

		if (idString != null) {
			Serializable[] idSerializable = new Serializable[idString.length];
			for (int i = 0; i < idString.length; i++) {
				idSerializable[i] = (Serializable) ConvertUtils.convert(
						idString[i], idClass);
			}
			return idSerializable;
		}
		return null;
	}

	/**
	 * 排序业务对象的回调函数,在子类重载.
	 */
	protected void doSortEntity(Serializable[] ids) {

	}

	/**
	 * form与list界面所需的参考对象注入.如categoryList,在子类重载.
	 */
	protected void refrenceData(HttpServletRequest request) {
	}

	/**
	 * 显示Form表单时的回调函数.为Form对象添加更多属性,在子类重载.
	 */
	protected void onInitForm(HttpServletRequest request, T object) {
	}

	/**
	 * 保存Form表单时,初始化Entity对象的属性.
	 */
	/*protected void initEntity(HttpServletRequest request, T object) {
		bindEntity(request.getAttribute(this.getEntityName()), object);
		onInitEntity(request, object);
	}*/

	/**
	 * 保存Form表单时的回调函数.为业务对象添加更多属性，在子类重载.
	 */
	protected void onInitEntity(HttpServletRequest request, T object) {
	}

	/**
	 * 生成保存成功的信息.
	 */
	protected void savedMessage(HttpServletRequest request, T object) {
		addActionMessage("entity.saved");
	}

	/**
	 * 生成删除成功的信息.
	 */
	protected void deletedMessage(HttpServletRequest request) {
		addActionMessage("entity.deleted");
	}

	/**
	 * 获取所管理的对象名. 首字母小写，如"user".
	 */
	/*protected String getEntityName() {
		return StringUtils.uncapitalize(ClassUtils
				.getShortName(getEntityClass()));
	}

	*//**
	 * 获取所管理的对象列表名. 首字母小写，如"users".
	 *//*
	protected String getEntityListName() {
		return StringUtils.uncapitalize(ClassUtils
				.getShortName(getEntityClass()))
				+ "s";
	}*/

	/**
	 * 从request中获得Entity的id，并判断其有效性.
	 */
	protected Serializable getEntityId(HttpServletRequest request) {
		String idString = request.getParameter(idName);
		try {
			return (Serializable) ConvertUtils.convert(idString, idClass);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Wrong when get id from request");
		}
	}
}
