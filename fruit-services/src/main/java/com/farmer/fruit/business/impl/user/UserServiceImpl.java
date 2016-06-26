package com.farmer.fruit.business.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farmer.fruit.interfaces.user.IUserService;
import com.farmer.fruit.models.user.User;
import com.farmer.fruit.models.user.UserQuery;
import com.farmer.fruit.persistence.user.IUserDao;

@Service
@Transactional(readOnly=true)
public class UserServiceImpl implements IUserService<User, UserQuery> {

	@Autowired
	private IUserDao userDao;

	@Override
	public User getById(Integer id) {
		return userDao.getById(id);
	}

	@Override
	public User get(UserQuery entity) {
		return userDao.get(entity);
	}

	@Override
	public int findListCount(UserQuery entity) {
		return userDao.getTotalCount(entity);
	}

	@Override
	public List<User> findList(UserQuery entity) {
		return userDao.findList(entity, entity.getPageNo(), entity.getPageSize());
	}

	@Override
	@Transactional(readOnly=false)
	public Integer save(User entity) {
		if (entity.isNewRecord()) {
			return userDao.insert(entity);
		} else {
			return userDao.update(entity);
		}

	}

	@Override
	@Transactional(readOnly=false)
	public void delete(UserQuery entity) {
		userDao.delete(entity);

	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

}
