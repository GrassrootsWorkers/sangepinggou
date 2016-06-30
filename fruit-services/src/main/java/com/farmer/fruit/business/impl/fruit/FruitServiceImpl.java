package com.farmer.fruit.business.impl.fruit;

import com.farmer.fruit.interfaces.fruit.IFruitService;
import com.farmer.fruit.models.fruit.Fruit;
import com.farmer.fruit.models.fruit.FruitQuery;
import com.farmer.fruit.persistence.fruit.FruitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuzhi on 2016/6/29.
 */
@Service
public class FruitServiceImpl implements IFruitService<Fruit,FruitQuery> {
    @Autowired
    FruitDao fruitDao;
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
    public Integer save(Fruit entity) {
        if (entity.isNewRecord()) {
            fruitDao.insert(entity);
            return entity.getId().intValue();
        } else {
            return fruitDao.update(entity);
        }
    }

    @Override
    public void delete(FruitQuery entity) {
        fruitDao.delete(entity);
    }
}
