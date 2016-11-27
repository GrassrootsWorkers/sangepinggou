package com.farmer.fruit.business.impl.partner;

import com.farmer.fruit.interfaces.partner.IPartnerService;
import com.farmer.fruit.models.partner.Partner;
import com.farmer.fruit.models.partner.PartnerQuery;
import com.farmer.fruit.persistence.partner.IPartnerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liuzhi on 2016/11/27.
 */
@Service("partnerService")
@Transactional(readOnly=true)
public class PartnerServiceImpl implements IPartnerService {
    @Autowired
    IPartnerDao partnerDao;
    @Override
    public Partner getById(Long id) {
        return partnerDao.getById(id);
    }

    @Override
    public Partner get(PartnerQuery entity) {
        return partnerDao.get(entity);
    }

    @Override
    public int findListCount(PartnerQuery entity) {
        return partnerDao.getTotalCount(entity);
    }

    @Override
    public List<Partner> findList(PartnerQuery entity, int pageNo, int pageSize) {
        entity.setPageNo(pageNo);
        entity.setPageSize(pageSize);
        return partnerDao.findList(entity);
    }

    @Override
    @Transactional(readOnly=false)
    public Long save(Partner entity) {
        if(entity.isNewRecord()){
            partnerDao.insert(entity);
            return entity.getId();
        }else{
            int count =partnerDao.update(entity);
            return new Long(count);
        }
    }

    @Override
    public void delete(PartnerQuery entity) {

    }

    @Override
    public Partner getByOpenId(String openId) {
        return partnerDao.getByOpenId(openId);
    }
}
