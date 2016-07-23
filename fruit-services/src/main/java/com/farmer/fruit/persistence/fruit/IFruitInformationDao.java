package com.farmer.fruit.persistence.fruit;

import com.farmer.fruit.models.fruit.FruitInformation;
import com.farmer.fruit.models.fruit.FruitInformationQuery;
import com.farmer.fruit.persistence.CrudDao;
import com.farmer.fruit.persistence.MyBatisDao;

@MyBatisDao
public interface IFruitInformationDao extends CrudDao<FruitInformation,FruitInformationQuery> {

}