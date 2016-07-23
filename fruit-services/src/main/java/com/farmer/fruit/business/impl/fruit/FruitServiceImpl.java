package com.farmer.fruit.business.impl.fruit;

import com.farmer.fruit.interfaces.fruit.IFruitService;
import com.farmer.fruit.models.fruit.Fruit;
import com.farmer.fruit.models.fruit.FruitQuery;
import com.farmer.fruit.persistence.fruit.IFruitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liuzhi on 2016/6/29.
 */
@Service
@Transactional(readOnly=true)
public class FruitServiceImpl implements IFruitService {
    @Autowired
    IFruitDao fruitDao;
    @Override
    public Fruit getById(Long id) {
        return fruitDao.getById(id);
    }

    @Override
    public Fruit get(FruitQuery entity) {
        return fruitDao.get(entity);
    }

    @Override
    public int findListCount(FruitQuery entity) {
        return fruitDao.getTotalCount(entity);
    }

    @Override
    public List<Fruit> findList(FruitQuery entity,int pageNo,int pageSize) {
        entity.setPageNo(pageNo);
        entity.setPageSize(pageSize);
        return fruitDao.findList(entity);
    }

    @Override
    @Transactional(readOnly=false)
    public Long save(Fruit entity) {
        if (entity.isNewRecord()) {
            fruitDao.insert(entity);
            return entity.getId();
        } else {
            int count = fruitDao.update(entity);
            return  new Long(count);
        }
    }

    @Override
    public void delete(FruitQuery entity) {
        fruitDao.delete(entity);
    }
}
