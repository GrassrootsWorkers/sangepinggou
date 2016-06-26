package com.farmer.fruit.business.impl.farmer;

import com.farmer.fruit.interfaces.farmer.IFarmerService;
import com.farmer.fruit.models.farmer.Farmer;
import com.farmer.fruit.models.farmer.FarmerQuery;
import com.farmer.fruit.persistence.farmer.IFarmerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuzhi on 2016/6/26.
 */
@Service
public class FarmerServiceImpl implements IFarmerService {
    @Autowired
    private IFarmerDao farmerDao;
    @Override
    public Farmer getById(Integer id) {
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
    public List<Farmer> findList(FarmerQuery entity) {
        int count =farmerDao.getTotalCount(entity);
        if(count <=0) return null;
        entity.setCount(count);
        return farmerDao.findList(entity,entity.getPageNo(),entity.getPageSize());
    }

    @Override
    public Integer save(Farmer entity) {
        if (entity.isNewRecord()) {
            return farmerDao.insert(entity);
        } else {
            return farmerDao.update(entity);
        }
    }

    @Override
    public void delete(FarmerQuery entity) {

    }
}
