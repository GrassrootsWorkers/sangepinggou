package com.farmer.fruit.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.farmer.fruit.BaseService;
import com.farmer.fruit.models.BaseEntity;
import com.farmer.fruit.models.QueryDataEntity;
import com.farmer.fruit.persistence.CrudDao;

public class CrudService < D extends CrudDao<T,Q>, Q extends QueryDataEntity, T extends BaseEntity>extends BaseService {
	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T getById(Integer id) {
		return dao.getById(id);
	}
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(Q entity) {
		return dao.get(entity);
	}
	
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<T> findList(Q entity,int pageNo, int pageSize) {
		return dao.findList(entity,pageNo,pageSize);
	}
	
	
	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void save(T entity) {
		if (entity.isNewRecord()){
			dao.insert(entity);
		}else{
			dao.update(entity);
		}
	}
	
	/**
	 * 删除数据
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void delete(Q entity) {
		dao.delete(entity);
	}

}
