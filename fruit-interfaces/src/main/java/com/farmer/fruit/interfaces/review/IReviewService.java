package com.farmer.fruit.interfaces.review;

import com.farmer.fruit.interfaces.ICurdService;
import com.farmer.fruit.models.review.Review;
import com.farmer.fruit.models.review.ReviewQuery;

/**
 * Created by liuzhi on 2016/10/15.
 */
public interface IReviewService extends ICurdService<Review,ReviewQuery> {

    /**
     * 水果点赞
     * @param farmerId
     * @return
     */
    int praiseFruit(long farmerId);

    /**
     * 获取点赞量
     * @param farmerId
     * @return
     */
    int getPraiseFruitCount(long farmerId);
}
