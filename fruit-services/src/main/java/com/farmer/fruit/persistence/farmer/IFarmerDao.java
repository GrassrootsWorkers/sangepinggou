package com.farmer.fruit.persistence.farmer;

import com.farmer.fruit.models.farmer.Farmer;
import com.farmer.fruit.models.farmer.FarmerQuery;
import com.farmer.fruit.persistence.CrudDao;
import com.farmer.fruit.persistence.MyBatisDao;

@MyBatisDao
public interface IFarmerDao  extends CrudDao<Farmer,FarmerQuery>{
   
}