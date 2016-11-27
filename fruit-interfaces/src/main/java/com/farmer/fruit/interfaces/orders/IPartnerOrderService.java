package com.farmer.fruit.interfaces.orders;

import com.farmer.fruit.interfaces.ICurdService;
import com.farmer.fruit.models.orders.PartnerOrder;
import com.farmer.fruit.models.orders.PartnerOrderQuery;

/**
 * Created by liuzhi on 2016/11/27.
 */
public interface IPartnerOrderService extends ICurdService<PartnerOrder,PartnerOrderQuery> {
    String getPartnerOrderNo(String openId);
}
