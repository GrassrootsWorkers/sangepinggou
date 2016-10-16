package com.farmer.fruit.persistence.review;

import com.farmer.fruit.models.review.Review;
import com.farmer.fruit.models.review.ReviewQuery;
import com.farmer.fruit.persistence.CrudDao;
import com.farmer.fruit.persistence.MyBatisDao;

@MyBatisDao
public interface IReviewDao extends CrudDao<Review,ReviewQuery> {

}