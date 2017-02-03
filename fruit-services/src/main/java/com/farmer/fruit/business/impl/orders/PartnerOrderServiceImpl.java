package com.farmer.fruit.business.impl.orders;

import com.farmer.fruit.interfaces.orders.IPartnerOrderService;
import com.farmer.fruit.models.orders.PartnerOrder;
import com.farmer.fruit.models.orders.PartnerOrderQuery;
import com.farmer.fruit.utils.IdWorker;
import com.farmer.fruit.utils.RandomStrUtil;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by liuzhi on 2016/11/27.
 */
@Service("partnerOrderService")
public class PartnerOrderServiceImpl implements IPartnerOrderService {
    @Override
    public String getPartnerOrderNo(String openId) {
        IdWorker idWorker = new IdWorker(5L,5L);
        return Long.toString(idWorker.nextId()).substring(1,12);
    }

    @Override
    public PartnerOrder getById(Long id) {
        return null;
    }

    @Override
    public PartnerOrder get(PartnerOrderQuery entity) {
        return null;
    }

    @Override
    public int findListCount(PartnerOrderQuery entity) {
        return 0;
    }

    @Override
    public List<PartnerOrder> findList(PartnerOrderQuery entity, int pageNo, int pageSize) {
        return null;
    }

    @Override
    public Long save(PartnerOrder entity) {
        return null;
    }

    @Override
    public void delete(PartnerOrderQuery entity) {

    }
}
