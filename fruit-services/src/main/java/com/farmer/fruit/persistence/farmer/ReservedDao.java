package com.farmer.fruit.persistence.farmer;

import com.farmer.fruit.models.farmer.Reserved;
import com.farmer.fruit.models.farmer.ReservedQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ReservedDao {
    int countByExample(ReservedQuery example);

    int deleteByExample(ReservedQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(Reserved record);

    int insertSelective(Reserved record);

    List<Reserved> selectByExampleWithRowbounds(ReservedQuery example, RowBounds rowBounds);

    List<Reserved> selectByExample(ReservedQuery example);

    Reserved selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Reserved record, @Param("example") ReservedQuery example);

    int updateByExample(@Param("record") Reserved record, @Param("example") ReservedQuery example);

    int updateByPrimaryKeySelective(Reserved record);

    int updateByPrimaryKey(Reserved record);
}