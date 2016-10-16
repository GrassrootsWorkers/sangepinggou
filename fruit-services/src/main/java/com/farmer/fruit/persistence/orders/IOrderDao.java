package com.farmer.fruit.persistence.orders;

import com.farmer.fruit.models.orders.Order;
import com.farmer.fruit.models.orders.OrderQuery;
import com.farmer.fruit.persistence.CrudDao;
import com.farmer.fruit.persistence.MyBatisDao;

/**
 * Created by liuzhi on 2016/10/12.
 */
@MyBatisDao
public interface IOrderDao extends CrudDao<Order,OrderQuery> {
}
