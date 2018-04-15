package org.zyj.shsf.webadmin.dal.base;

import java.util.List;
import java.util.Map;

import org.zyj.shsf.webadmin.exception.DalException;


/**
 * 数据库基础操作接口
 * 
 */
public interface BaseDao<T> {

	/**
	 * 保存一个对象
	 * 
	 * @param entity
	 */
	public void save(T entity) throws DalException;

	/**
	 * 删除一个对象
	 * 
	 * @param entity
	 */
	public void delete(T entity) throws DalException;

	/**
	 * 更新一个对象
	 * 
	 * @param entity
	 */
	public void update(T entity) throws DalException;

	/**
	 * 合并一个对象
	 * 
	 * @param entity
	 */
	public void merge(T entity) throws DalException;

	/**
	 * 添加或更新对象
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(T entity) throws DalException;

	/**
	 * 加载一个对象
	 * 
	 * @param c
	 * @param id
	 * @return
	 */
	public T load(Class<T> c, String id) throws DalException;

	/**
	 * 获取一个对象
	 * 
	 * @param c
	 * @param id
	 * @return
	 */
	public T get(Class<T> c, String id) throws DalException;

	/**
	 * 获取一个对象
	 * 
	 * @param hql
	 * @return
	 */
	public T get(String hql) throws DalException;

	/**
	 * 获取一个对象
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public T get(String hql, Map<String, Object> params);

	/**
	 * 根据HQL语句查询结果集
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> find(String hql);

	/**
	 * 查询对象集合
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> find(String hql, Map<String, Object> params);

	/**
	 * 查询对象集合
	 * 
	 * @param hql
	 * @param page
	 * @param rows
	 * @param params
	 * @return
	 */
	public List<T> find(String hql, int page, int rows,
			Map<String, Object> params);

	/**
	 * Select count(*) from
	 * 
	 * @param hql
	 * @return
	 */
	public Long count(String hql);

	/**
	 * Select count(*) from
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public Long count(String hql, Map<String, Object> params);

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @return
	 */
	public Integer executeHql(String hql);

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public Integer executeHql(String hql, Map<String, Object> params)
			throws Exception;
}
