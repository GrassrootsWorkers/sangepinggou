package com.farmer.fruit.interfaces;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface ICurdService<T,Q>{
	 
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
	

	/**
	 * 查询列表数据 总记录数
	 * @param entity
	 * @return
	 */
	public int findListCount(Q entity);
	
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<T> findList(Q entity);
	
	
	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public Integer save(T entity);
	
	/**
	 * 删除数据
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void delete(Q entity);

}
