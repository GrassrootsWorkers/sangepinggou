package com.farmer.fruit.persistence.brand;

import com.farmer.fruit.models.brand.Variety;
import com.farmer.fruit.models.brand.VarietyQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface VarietyDao {
    int countByExample(VarietyQuery example);

    int deleteByExample(VarietyQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(Variety record);

    int insertSelective(Variety record);

    List<Variety> selectByExampleWithRowbounds(VarietyQuery example, RowBounds rowBounds);

    List<Variety> selectByExample(VarietyQuery example);

    Variety selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Variety record, @Param("example") VarietyQuery example);

    int updateByExample(@Param("record") Variety record, @Param("example") VarietyQuery example);

    int updateByPrimaryKeySelective(Variety record);

    int updateByPrimaryKey(Variety record);
}