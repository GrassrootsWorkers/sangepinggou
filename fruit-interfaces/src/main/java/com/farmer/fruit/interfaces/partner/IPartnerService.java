package com.farmer.fruit.interfaces.partner;

import com.farmer.fruit.interfaces.ICurdService;
import com.farmer.fruit.models.partner.Partner;
import com.farmer.fruit.models.partner.PartnerQuery;

/**
 * Created by liuzhi on 2016/11/27.
 */
public interface IPartnerService extends ICurdService<Partner,PartnerQuery> {
    Partner getByOpenId(String openId);
}
