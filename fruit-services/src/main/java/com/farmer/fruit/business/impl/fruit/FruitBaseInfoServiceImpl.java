package com.farmer.fruit.business.impl.fruit;

import com.farmer.fruit.interfaces.fruit.IFruitBaseInfoService;
import com.farmer.fruit.models.fruit.FruitInformation;
import com.farmer.fruit.models.fruit.FruitInformationQuery;
import com.farmer.fruit.persistence.fruit.IFruitInformationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liuzhi on 2016/7/16.
 */
@Service
@Transactional(readOnly = true)
public class FruitBaseInfoServiceImpl implements IFruitBaseInfoService {
    @Autowired
    IFruitInformationDao fruitInformationDao;
    @Override
    public FruitInformation getById(Long id) {
        return fruitInformationDao.getById(id);
    }

    @Override
    public FruitInformation get(FruitInformationQuery entity) {
        return fruitInformationDao.get(entity);
    }

    @Override
    public int findListCount(FruitInformationQuery entity) {
        return fruitInformationDao.getTotalCount(entity);
    }

    @Override
    public List<FruitInformation> findList(FruitInformationQuery entity, int pageNo, int pageSize) {
        entity.setPageNo(pageNo);
        entity.setPageSize(pageSize);
        return fruitInformationDao.findList(entity);
    }

    @Override
    @Transactional(readOnly = false)
    public Long save(FruitInformation entity) {
        if (entity.isNewRecord()) {
            fruitInformationDao.insert(entity);
            return entity.getId();
        } else {
            int count = fruitInformationDao.update(entity);
            return  new Long(count);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(FruitInformationQuery entity) {

    }
}
