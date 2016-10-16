package com.farmer.fruit.business.impl.orders;

import com.farmer.fruit.interfaces.orders.IOrderService;
import com.farmer.fruit.models.orders.Order;
import com.farmer.fruit.models.orders.OrderQuery;
import com.farmer.fruit.persistence.orders.IOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuzhi on 2016/10/12.
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    IOrderDao orderDao;
    @Override
    public Order getById(Long id) {
        return orderDao.getById(id);
    }

    @Override
    public Order get(OrderQuery entity) {
        return orderDao.get(entity);
    }

    @Override
    public int findListCount(OrderQuery entity) {
        return orderDao.getTotalCount(entity);
    }

    @Override
    public List<Order> findList(OrderQuery entity, int pageNo, int pageSize) {
        entity.setPageNo(pageNo);
        entity.setPageSize(pageSize);
        return orderDao.findList(entity);
    }

    @Override
    public Long save(Order entity) {
        if(entity.isNewRecord()){
            orderDao.insert(entity);
            return entity.getId();
        }else {
           return  (long)orderDao.update(entity);
        }

    }

    @Override
    public void delete(OrderQuery entity) {
        orderDao.delete(entity);
    }
}
