package com.farmer.fruit.persistence.fruit;

import com.farmer.fruit.models.fruit.FruitInformation;
import com.farmer.fruit.models.fruit.FruitInformationQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface FruitInformationDao {
    int countByExample(FruitInformationQuery example);

    int deleteByExample(FruitInformationQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(FruitInformation record);

    int insertSelective(FruitInformation record);

    List<FruitInformation> selectByExampleWithRowbounds(FruitInformationQuery example, RowBounds rowBounds);

    List<FruitInformation> selectByExample(FruitInformationQuery example);

    FruitInformation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FruitInformation record, @Param("example") FruitInformationQuery example);

    int updateByExample(@Param("record") FruitInformation record, @Param("example") FruitInformationQuery example);

    int updateByPrimaryKeySelective(FruitInformation record);

    int updateByPrimaryKey(FruitInformation record);
}