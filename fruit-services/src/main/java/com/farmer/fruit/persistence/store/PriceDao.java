package com.farmer.fruit.persistence.store;

import com.farmer.fruit.models.store.Price;
import com.farmer.fruit.models.store.PriceQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PriceDao {
    int countByExample(PriceQuery example);

    int deleteByExample(PriceQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(Price record);

    int insertSelective(Price record);

    List<Price> selectByExampleWithRowbounds(PriceQuery example, RowBounds rowBounds);

    List<Price> selectByExample(PriceQuery example);

    Price selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Price record, @Param("example") PriceQuery example);

    int updateByExample(@Param("record") Price record, @Param("example") PriceQuery example);

    int updateByPrimaryKeySelective(Price record);

    int updateByPrimaryKey(Price record);
}