package com.farmer.fruit.persistence.partner;

import com.farmer.fruit.models.partner.Partner;
import com.farmer.fruit.models.partner.PartnerQuery;
import com.farmer.fruit.persistence.CrudDao;
import com.farmer.fruit.persistence.MyBatisDao;

/**
 * Created by liuzhi on 2016/11/27.
 */
@MyBatisDao
public interface IPartnerDao extends CrudDao<Partner,PartnerQuery> {
    Partner getByOpenId(String openId);
}
