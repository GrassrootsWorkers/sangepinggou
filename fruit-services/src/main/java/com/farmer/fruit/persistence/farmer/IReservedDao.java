package com.farmer.fruit.persistence.farmer;

import com.farmer.fruit.models.farmer.Reserved;
import com.farmer.fruit.models.farmer.ReservedQuery;
import com.farmer.fruit.persistence.CrudDao;
import com.farmer.fruit.persistence.MyBatisDao;

@MyBatisDao
public interface IReservedDao extends CrudDao<Reserved,ReservedQuery> {

}