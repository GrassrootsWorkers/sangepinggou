package com.farmer.fruit.persistence.store;

import com.farmer.fruit.models.store.Supermarket;
import com.farmer.fruit.models.store.SupermarketQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SupermarketDao {
    int countByExample(SupermarketQuery example);

    int deleteByExample(SupermarketQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(Supermarket record);

    int insertSelective(Supermarket record);

    List<Supermarket> selectByExampleWithRowbounds(SupermarketQuery example, RowBounds rowBounds);

    List<Supermarket> selectByExample(SupermarketQuery example);

    Supermarket selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Supermarket record, @Param("example") SupermarketQuery example);

    int updateByExample(@Param("record") Supermarket record, @Param("example") SupermarketQuery example);

    int updateByPrimaryKeySelective(Supermarket record);

    int updateByPrimaryKey(Supermarket record);
}