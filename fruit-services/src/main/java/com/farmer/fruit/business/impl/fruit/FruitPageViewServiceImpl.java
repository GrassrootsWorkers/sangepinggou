package com.farmer.fruit.business.impl.fruit;

import com.farmer.fruit.interfaces.fruit.IFruitPageViewService;
import com.farmer.fruit.models.pageview.PageView;
import com.farmer.fruit.models.pageview.PageViewQuery;
import com.farmer.fruit.persistence.fruit.IFruitPageViewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liuzhi on 2016/6/29.
 */
@Service
@Transactional(readOnly=true)
public class FruitPageViewServiceImpl implements IFruitPageViewService {
    @Autowired
    IFruitPageViewDao fruitPageViewDao;

    @Override
    public PageView getById(Long id) {
        return null;
    }

    @Override
    public PageView get(PageViewQuery entity) {
        return null;
    }

    @Override
    public int findListCount(PageViewQuery entity) {
        return fruitPageViewDao.getTotalCount(entity);
    }

    @Override
    public List<PageView> findList(PageViewQuery entity, int pageNo, int pageSize) {
        entity.setPageNo(pageNo);
        entity.setPageSize(pageSize);
        return fruitPageViewDao.findList(entity);
    }

    @Override
    @Transactional(readOnly=false)
    public Long save(PageView entity) {
        if (entity.isNewRecord()) {
            fruitPageViewDao.insert(entity);
            return entity.getId();
        } else {
            int count = fruitPageViewDao.update(entity);
            return  new Long(count);
        }
    }

    @Override
    public void delete(PageViewQuery entity) {

    }
}
