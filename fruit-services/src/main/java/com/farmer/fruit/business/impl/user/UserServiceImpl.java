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
	public User getById(Long id) {
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
	public List<User> findList(UserQuery entity,int pageNo,int pageSize) {
		entity.setPageNo(pageNo);
		entity.setPageSize(pageSize);
		return userDao.findList(entity);
	}

	@Override
	@Transactional(readOnly=false)
	public Long save(User entity) {
		if (entity.isNewRecord()) {
			userDao.insert(entity);
			return entity.getId();
		} else {
			int count = userDao.update(entity);
			return new Long(count);
		}

	}

	@Override
	@Transactional(readOnly=false)
	public void delete(UserQuery entity) {
		userDao.delete(entity);

	}

}
