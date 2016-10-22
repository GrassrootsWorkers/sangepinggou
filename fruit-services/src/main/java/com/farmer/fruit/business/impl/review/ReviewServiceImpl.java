package com.farmer.fruit.business.impl.review;

import com.farmer.fruit.interfaces.review.IReviewService;
import com.farmer.fruit.models.review.Review;
import com.farmer.fruit.models.review.ReviewQuery;
import com.farmer.fruit.persistence.review.IReviewDao;
import com.farmer.fruit.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * Created by liuzhi on 2016/10/15.
 */
@Service
public class ReviewServiceImpl implements IReviewService {
    @Autowired
    IReviewDao reviewDao;
    @Autowired
    JedisPool jedisPool;
    @Override
    public Review getById(Long id) {
        return null;
    }

    @Override
    public Review get(ReviewQuery entity) {
        return null;
    }

    @Override
    public int findListCount(ReviewQuery entity) {
        return reviewDao.getTotalCount(entity);
    }

    @Override
    public List<Review> findList(ReviewQuery entity, int pageNo, int pageSize) {
        entity.setPageNo(pageNo);
        entity.setPageSize(pageSize);
        return reviewDao.findList(entity);
    }

    @Override
    public Long save(Review entity) {
        if(entity.isNewRecord()){
            reviewDao.insert(entity);
            return entity.getId();
        }else{
            return (long)reviewDao.update(entity);
        }
    }

    @Override
    public void delete(ReviewQuery entity) {

    }

    @Override
    public int praiseFruit(long farmerId) {
       try{
           RedisUtils redisUtils = new RedisUtils(jedisPool);
           String key = "praise_count";
           String field = "F"+farmerId;
           String countStr = redisUtils.getHashValueByKey(key,field);
           if(countStr == null || StringUtils.isEmpty(countStr)){
               countStr = "0";
           }
           int count = Integer.parseInt(countStr)+1;
           redisUtils.setHKey(key,field,count+"");
           return count;
       }catch (Exception e){
           return 0;
       }
    }

    @Override
    public int getPraiseFruitCount(long farmerId) {
        try{
            RedisUtils redisUtils = new RedisUtils(jedisPool);
            String key = "praise_count";
            String field = "F"+farmerId;
            String countStr = redisUtils.getHashValueByKey(key,field);
            if(countStr == null || StringUtils.isEmpty(countStr)){
                return 1;
            }else{
                return Integer.parseInt(countStr);
            }
        }catch (Exception e){
            return 1;
        }
    }

    @Override
    public Review getTotalAvgScore(ReviewQuery query) {
        Review avgReview = reviewDao.getTotalAvgScore(query);
        if(avgReview.getAvgSugarScore()<=0){
            avgReview.setAvgSugarScore(5.0);
        }
        if(avgReview.getAvgTasteScore()<=0){
            avgReview.setAvgTasteScore(5.0);
        }
        if(avgReview.getAvgWaterScore()<=0){
            avgReview.setAvgWaterScore(5.0);
        }
        return avgReview;
    }
}
