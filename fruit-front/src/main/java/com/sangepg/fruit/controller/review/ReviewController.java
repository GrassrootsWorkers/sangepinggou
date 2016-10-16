package com.sangepg.fruit.controller.review;

import com.farmer.fruit.interfaces.review.IReviewService;
import com.farmer.fruit.models.review.Review;
import com.farmer.fruit.models.review.ReviewQuery;
import com.sangepg.fruit.controller.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhi on 2016/10/15.
 */
@Controller()
@RequestMapping(value = "/front/review")
public class ReviewController extends BaseAction {

    @Autowired
    IReviewService reviewService;
    @RequestMapping(value = "toReview", method = RequestMethod.GET)
    public ModelAndView toReview(String fruitCode, long farmerId,HttpServletRequest request) {
        String mobile = this.getCookieValue("mobile",request);
        if (mobile == null || "".equals(mobile.trim())) {
             return loginModel();
        }
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("farmerId",farmerId);
        dataMap.put("fruitCode",fruitCode);
        //
        ReviewQuery query = new ReviewQuery();
        query.setFarmerId(farmerId);
        List<Review> reviewList = reviewService.findList(query,0,10);
        dataMap.put("reviews", reviewList);
        ModelAndView modelAndView = new ModelAndView("review/review_index", dataMap);
        return modelAndView;
    }
    @RequestMapping(value = "praise", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> praise(long farmerId,HttpServletRequest request) {
        String mobile = this.getCookieValue("mobile",request);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if (mobile == null || "".equals(mobile.trim())) {
            resultMap.put("msg","login");
            return resultMap;
        }
        int praiseCount = reviewService.praiseFruit(farmerId);
        resultMap.put("msg","success");
        resultMap.put("praiseCount",praiseCount);
        return resultMap;
    }
}
