package com.farmer.fruit.persistence;


import java.util.List;

/**
 * DAO支持类实现
 */
public interface CrudDao<T,Q> {

	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T getById(Integer id);
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(Q entity);
	
	
	/**记录总数
	 * 
	 * @param entity
	 * @return
	 */
	public int getTotalCount(Q entity);
	
	/**
	 * 记录list
	 * @param entity
	 * @return
	 */
	public List<T> findList(Q entity,int pageNo,int pageSize);
	
	/**
	 * 查询所有数据列表
	 * @param entity
	 * @return
	 */
	public List<T> findAllList(Q entity);
	
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insert(T entity);
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public int update(T entity);
	
	/**
	 * 删除数据（一般为逻辑删除，更新delete_flag字段为1）
	 * @param entity
	 * @return
	 */
	public int delete(Q entity);
	
}