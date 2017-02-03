package com.sangepg.fruit.controller.review;

import com.farmer.fruit.interfaces.farmer.IFarmerService;
import com.farmer.fruit.interfaces.review.IReviewService;
import com.farmer.fruit.models.farmer.Farmer;
import com.farmer.fruit.models.review.Review;
import com.farmer.fruit.models.review.ReviewQuery;
import com.farmer.fruit.utils.IpUtils;
import com.farmer.fruit.utils.RedisUtils;
import com.sangepg.fruit.controller.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhi on 2016/10/15.
 */
@Controller()
@RequestMapping(value = "/review")
public class ReviewController extends BaseAction {

    @Autowired
    IReviewService reviewService;
    @Autowired
    IFarmerService farmerService;
    @Autowired
    JedisPool jedisPool;
    @RequestMapping(value = "toReview", method = RequestMethod.GET)
    public ModelAndView toReview(String fruitCode, long farmerId,HttpServletRequest request) {
        String mobile = this.getCookieValue("m",request);
        String openId =  this.getCookieValue("openid",request);
        //没手机号看看有没有openId
        if ((openId == null || "".equals(openId.trim()))&&(mobile == null || "".equals(mobile.trim()))) {
             return loginModel();
        }
        Map<String,Object> dataMap = new HashMap<String,Object>();
        //dataMap.put("farmerId",farmerId);
        dataMap.put("fruitCode",fruitCode);
        //
        ReviewQuery query = new ReviewQuery();
        query.setFarmerId(farmerId);
        //List<Review> reviewList = reviewService.findList(query,0,10);
        //dataMap.put("reviews", reviewList);
        Review review = reviewService.getTotalAvgScore(query);
        dataMap.put("avg", review);
        Farmer farmer = farmerService.getById(farmerId);
        dataMap.put("farmer",farmer);

        //ModelAndView modelAndView = new ModelAndView("review/review_index", dataMap);
        ModelAndView modelAndView = new ModelAndView("review/review_index", dataMap);
        return modelAndView;
    }
    @RequestMapping(value = "praise", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> praise(long farmerId,HttpServletRequest request) {
       /* String mobile = this.getCookieValue("mobile",request);

        if (mobile == null || "".equals(mobile.trim())) {
            resultMap.put("msg","login");
            return resultMap;
        }*/
        Map<String,Object> resultMap = new HashMap<String,Object>();
        int praiseCount = reviewService.praiseFruit(farmerId);
        resultMap.put("msg","success");
        resultMap.put("praiseCount",praiseCount);
        return resultMap;
    }
    @RequestMapping(value = "review", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> review(Review review,HttpServletRequest request) {

        Map<String,Object> resultMap = new HashMap<String,Object>();

        String mobile = this.getCookieValue("m",request);
        String openId = this.getCookieValue("openid",request);
        //没手机号看看有没有openId
        if ((openId == null || "".equals(openId.trim())) &&(mobile == null || "".equals(mobile.trim()))) {
            resultMap.put("msg","login");
            return resultMap;
        }

        if(mobile !=null){
            RedisUtils redisUtils = new RedisUtils(jedisPool);
            mobile = redisUtils.getValueByKey("f_l_u"+mobile);
        }

        if((openId ==null ||"".equals(openId.trim())) && (mobile == null||"".equals(mobile))){
            resultMap.put("msg","login");
            return resultMap;
        }
        review.setUserIp(IpUtils.IpToLong(getRealIP(request)));
        review.setMobile(mobile);
        review.setOpenId(openId);
        review.setNewRecord(true);
        review.setCreateTime(new Date());
        review.setOpenId(openId);
        reviewService.save(review);
        resultMap.put("msg","success");
        return resultMap;
    }
}
