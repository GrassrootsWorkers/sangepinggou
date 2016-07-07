package com.farmer.fruit.business.impl.farmer;

import com.farmer.fruit.interfaces.farmer.IFarmerService;
import com.farmer.fruit.models.farmer.Farmer;
import com.farmer.fruit.models.farmer.FarmerQuery;
import com.farmer.fruit.persistence.farmer.IFarmerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liuzhi on 2016/6/26.
 */
@Service
@Transactional(readOnly=true)
public class FarmerServiceImpl implements IFarmerService {
    @Autowired
    private IFarmerDao farmerDao;
    @Override
    public Farmer getById(Long id) {
        return farmerDao.getById(id);
    }

    @Override
    public Farmer get(FarmerQuery entity) {
        return  farmerDao.get(entity);
    }

    @Override
    public int findListCount(FarmerQuery entity) {
        return farmerDao.getTotalCount(entity);
    }

    @Override
    public List<Farmer> findList(FarmerQuery entity,int pageNo,int pageSize) {
        entity.setPageNo(pageNo);
        entity.setPageSize(pageSize);
        int count =farmerDao.getTotalCount(entity);
        if(count <=0) return null;
        entity.setCount(count);
        return farmerDao.findList(entity);
    }

    @Override
    @Transactional(readOnly=false)
    public Long save(Farmer entity) {
        if (entity.isNewRecord()) {
            return farmerDao.insert(entity);
        } else {
            int count= farmerDao.update(entity);
            return new Long(count);
        }
    }

    @Override
    public void delete(FarmerQuery entity) {

    }
}
