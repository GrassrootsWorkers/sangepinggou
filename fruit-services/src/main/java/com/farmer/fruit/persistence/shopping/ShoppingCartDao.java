package com.farmer.fruit.persistence.shopping;

import com.farmer.fruit.models.shopping.ShoppingCart;

public interface ShoppingCartDao {

    int deleteByPrimaryKey(Integer id);

    int insert(ShoppingCart record);

    int insertSelective(ShoppingCart record);

}