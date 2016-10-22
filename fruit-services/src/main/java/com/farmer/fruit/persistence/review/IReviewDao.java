package com.farmer.fruit.persistence.review;

import com.farmer.fruit.models.review.Review;
import com.farmer.fruit.models.review.ReviewQuery;
import com.farmer.fruit.persistence.CrudDao;
import com.farmer.fruit.persistence.MyBatisDao;

@MyBatisDao
public interface IReviewDao extends CrudDao<Review,ReviewQuery> {
    /**
     * 获取评价的平均分
     * @param query
     * @return
     */
    Review getTotalAvgScore(ReviewQuery query);
}