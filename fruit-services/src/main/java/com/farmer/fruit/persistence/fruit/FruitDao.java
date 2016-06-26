package com.farmer.fruit.persistence.fruit;

import com.farmer.fruit.models.fruit.Fruit;
import com.farmer.fruit.models.fruit.FruitQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface FruitDao {
    int countByExample(FruitQuery example);

    int deleteByExample(FruitQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(Fruit record);

    int insertSelective(Fruit record);

    List<Fruit> selectByExampleWithRowbounds(FruitQuery example, RowBounds rowBounds);

    List<Fruit> selectByExample(FruitQuery example);

    Fruit selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Fruit record, @Param("example") FruitQuery example);

    int updateByExample(@Param("record") Fruit record, @Param("example") FruitQuery example);

    int updateByPrimaryKeySelective(Fruit record);

    int updateByPrimaryKey(Fruit record);
}