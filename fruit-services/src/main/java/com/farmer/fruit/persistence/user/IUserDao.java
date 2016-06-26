package com.farmer.fruit.persistence.user;

import com.farmer.fruit.models.user.User;
import com.farmer.fruit.models.user.UserQuery;
import com.farmer.fruit.persistence.CrudDao;
import com.farmer.fruit.persistence.MyBatisDao;

@MyBatisDao
public interface IUserDao extends CrudDao<User,UserQuery> {
  
}