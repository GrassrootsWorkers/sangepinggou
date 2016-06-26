package com.farmer.fruit.persistence.review;

import com.farmer.fruit.models.review.Review;
import com.farmer.fruit.models.review.ReviewQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ReviewDao {
    int countByExample(ReviewQuery example);

    int deleteByExample(ReviewQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(Review record);

    int insertSelective(Review record);

    List<Review> selectByExampleWithRowbounds(ReviewQuery example, RowBounds rowBounds);

    List<Review> selectByExample(ReviewQuery example);

    Review selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Review record, @Param("example") ReviewQuery example);

    int updateByExample(@Param("record") Review record, @Param("example") ReviewQuery example);

    int updateByPrimaryKeySelective(Review record);

    int updateByPrimaryKey(Review record);
}