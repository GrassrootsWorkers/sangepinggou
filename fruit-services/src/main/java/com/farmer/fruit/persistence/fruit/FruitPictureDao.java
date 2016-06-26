package com.farmer.fruit.persistence.fruit;

import com.farmer.fruit.models.fruit.FruitPicture;
import com.farmer.fruit.models.fruit.FruitPictureQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface FruitPictureDao {
    int countByExample(FruitPictureQuery example);

    int deleteByExample(FruitPictureQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(FruitPicture record);

    int insertSelective(FruitPicture record);

    List<FruitPicture> selectByExampleWithRowbounds(FruitPictureQuery example, RowBounds rowBounds);

    List<FruitPicture> selectByExample(FruitPictureQuery example);

    FruitPicture selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FruitPicture record, @Param("example") FruitPictureQuery example);

    int updateByExample(@Param("record") FruitPicture record, @Param("example") FruitPictureQuery example);

    int updateByPrimaryKeySelective(FruitPicture record);

    int updateByPrimaryKey(FruitPicture record);
}