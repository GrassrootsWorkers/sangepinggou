package com.farmer.fruit.interfaces.user;

import com.farmer.fruit.interfaces.ICurdService;
import com.farmer.fruit.models.BaseEntity;
import com.farmer.fruit.models.QueryDataEntity;

public interface IUserService<T extends BaseEntity,Q extends QueryDataEntity> extends ICurdService<T, Q>{
	
}
