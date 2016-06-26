package com.farmer.fruit.persistence.shopping;

import com.farmer.fruit.models.shopping.ShoppingCart;
import com.farmer.fruit.models.shopping.ShoppingCartQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ShoppingCartDao {
    int countByExample(ShoppingCartQuery example);

    int deleteByExample(ShoppingCartQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShoppingCart record);

    int insertSelective(ShoppingCart record);

    List<ShoppingCart> selectByExampleWithRowbounds(ShoppingCartQuery example, RowBounds rowBounds);

    List<ShoppingCart> selectByExample(ShoppingCartQuery example);

    ShoppingCart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShoppingCart record, @Param("example") ShoppingCartQuery example);

    int updateByExample(@Param("record") ShoppingCart record, @Param("example") ShoppingCartQuery example);

    int updateByPrimaryKeySelective(ShoppingCart record);

    int updateByPrimaryKey(ShoppingCart record);
}