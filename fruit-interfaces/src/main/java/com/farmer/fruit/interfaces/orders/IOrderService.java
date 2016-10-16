package com.farmer.fruit.interfaces.orders;

import com.farmer.fruit.interfaces.ICurdService;
import com.farmer.fruit.models.orders.Order;
import com.farmer.fruit.models.orders.OrderQuery;

/**
 * Created by liuzhi on 2016/10/12.
 */
public interface IOrderService  extends ICurdService<Order,OrderQuery> {
}
