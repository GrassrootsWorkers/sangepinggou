package com.farmer.fruit.persistence.fruit;

import com.farmer.fruit.models.fruit.Fruit;
import com.farmer.fruit.models.fruit.FruitQuery;
import com.farmer.fruit.persistence.CrudDao;
import com.farmer.fruit.persistence.MyBatisDao;

@MyBatisDao
public interface IFruitDao extends CrudDao<Fruit,FruitQuery>{
}