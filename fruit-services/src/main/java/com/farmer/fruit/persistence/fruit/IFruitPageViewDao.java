package com.farmer.fruit.persistence.fruit;

import com.farmer.fruit.models.pageview.PageView;
import com.farmer.fruit.models.pageview.PageViewQuery;
import com.farmer.fruit.persistence.CrudDao;
import com.farmer.fruit.persistence.MyBatisDao;

@MyBatisDao
public interface IFruitPageViewDao extends CrudDao<PageView,PageViewQuery>{

}