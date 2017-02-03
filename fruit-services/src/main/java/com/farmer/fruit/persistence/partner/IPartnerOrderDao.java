package com.farmer.fruit.persistence.partner;

import com.farmer.fruit.models.orders.PartnerOrder;
import com.farmer.fruit.models.orders.PartnerOrderQuery;
import com.farmer.fruit.persistence.CrudDao;
import com.farmer.fruit.persistence.MyBatisDao;

/**
 * Created by liuzhi on 2017/1/2.
 */
@MyBatisDao
public interface IPartnerOrderDao extends CrudDao<PartnerOrder,PartnerOrderQuery> {
    PartnerOrder getByOrderNo(String orderNo);
}
