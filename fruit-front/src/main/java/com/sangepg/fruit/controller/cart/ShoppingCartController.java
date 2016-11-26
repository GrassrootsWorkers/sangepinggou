package com.sangepg.fruit.controller.cart;

import com.farmer.fruit.interfaces.fruit.IFruitPageViewService;
import com.farmer.fruit.interfaces.lottery.ILotteryService;
import com.farmer.fruit.interfaces.review.IReviewService;
import com.farmer.fruit.interfaces.shopping.IShoppingCartService;
import com.farmer.fruit.json.GsonUtil;
import com.farmer.fruit.models.fruit.Fruit;
import com.farmer.fruit.models.lottery.Lottery;
import com.farmer.fruit.models.pageview.PageView;
import com.farmer.fruit.models.shopping.ShoppingCart;
import com.farmer.fruit.utils.IpUtils;
import com.sangepg.fruit.controller.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhi on 2016/8/19.
 */
@Controller()
@RequestMapping(value = "/front/cart")
public class ShoppingCartController extends BaseAction {
    @Autowired
    IShoppingCartService shoppingCartService;
    @Autowired
    ILotteryService lotteryService;
    @Autowired
    IFruitPageViewService fruitPageViewService;
    @Autowired
    IReviewService reviewService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addCart(String fruitCode, HttpServletRequest request) {
        String mobile = this.getCookieValue("mobile",request);
        if (mobile == null || "".equals(mobile.trim())) {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("success", false);
            resultMap.put("msg", "login");
            return resultMap;
        }else{
            Map<String, Object>  resultMap = shoppingCartService.addCart(mobile, fruitCode);
            //计算抽奖功能--做活动时提前录入一批
            Lottery lottery = lotteryService.getCartLottery(mobile,(Fruit)resultMap.get("fruit"));
            if(lottery != null){
                resultMap.put("lottery", GsonUtil.getJSON(lottery));
            }
            resultMap.put("fruit",null);
            return resultMap;
        }
        //验证用户是不是刚注册的

    }

    //购物车删除商品
    @RequestMapping(value = "del", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteCart(String fruitCode, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("success", false);
        String mobile = this.getCookieValue("mobile",request);
        if (mobile == null || "".equals(mobile.trim())) {
            resultMap.put("msg", "login");
            return resultMap;
        }
        boolean flag = shoppingCartService.deleteFromCart(mobile, fruitCode);
        if (flag) {
            resultMap.put("success", true);
            resultMap.put("msg", "成功移出篮子");
            resultMap.put("cartCount", shoppingCartService.getCartGoodsCount(mobile));
        }
        return resultMap;
    }

    //验证是否在购物车中
    @RequestMapping(value = "ifAdd", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> ifAdded(String fruitCode, HttpServletRequest request) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        String mobile = this.getCookieValue("mobile",request);

        if (mobile == null || "".equals(mobile.trim())) {
            resultMap.put("success", false);
            return resultMap;
        }

        boolean ifAdd = shoppingCartService.ifAlreadyAddToCart(mobile, fruitCode);
        resultMap.put("cartCount", shoppingCartService.getCartGoodsCount(mobile));
        resultMap.put("success", ifAdd);
        return resultMap;
    }

    @RequestMapping(value = "pageView", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> recordPageViewCount(String fruitCode,Long farmerId, HttpServletRequest request) {
        //记录访问次数
        String mobile = this.getCookieValue("mobile",request);
        if(StringUtils.isEmpty(mobile)){
            mobile = "11011011011";
        }
        PageView pageView = new PageView();
        pageView.setMobile(mobile);
        pageView.setFruitCode(fruitCode);
        pageView.setIp(IpUtils.IpToLong(getRealIP(request)));
        pageView.setCreateTime(new Date());
        pageView.setNewRecord(true);
        fruitPageViewService.save(pageView);
        int praiseCount = reviewService.getPraiseFruitCount(farmerId);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("praiseCount",praiseCount);
        return resultMap;
    }

    /**
     * 展示购物车商品
     *
     * @param request
     * @return
     */

    @RequestMapping(value = "list")
    public ModelAndView listCart(HttpServletRequest request) {
        String mobile = this.getCookieValue("mobile",request);
        if (mobile == null) {
            return loginModel();
        }
        BigDecimal allPrice = new BigDecimal(0);
        int totalCount = 0;
        List<ShoppingCart> shoppingCartList = shoppingCartService.getUserShoppingCart(mobile,null);
        for(ShoppingCart cart : shoppingCartList){
            allPrice = allPrice.add(cart.getSaleTotalPrice());
            totalCount = totalCount + cart.getTotalCount();
        }
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("carts", shoppingCartList);
        dataMap.put("totalPrice",allPrice.setScale(2, BigDecimal.ROUND_HALF_UP));
        dataMap.put("totalCount",totalCount);
        ModelAndView view = new ModelAndView("/cart/cart_list",dataMap);
        return view;
    }

    //暂时标记商品不要
    @RequestMapping(value = "mark", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView markCartGoodsBuyOrNoBuy(ShoppingCartVo cartVo, HttpServletRequest request) {
        String mobile = this.getCookieValue("mobile",request);
        if (mobile == null || "".equals(mobile.trim())) {
          return loginModel();
        }
        Map<String,BigDecimal>groupMapPrice =  getPriceMapFromStrs(cartVo.getCartGroups(),cartVo.getSalePrices());
        shoppingCartService.markCartGoods(mobile,cartVo.getFruitCodes().split(";"),cartVo.getMarkFlag());
        List<ShoppingCart> shoppingCartList = shoppingCartService.getUserShoppingCart(mobile,groupMapPrice);

        BigDecimal allPrice = new BigDecimal(0);
        int totalCount = 0;
        for(ShoppingCart cart : shoppingCartList){
            allPrice = allPrice.add(cart.getSaleTotalPrice());
            totalCount = totalCount + cart.getTotalCount();
        }
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("carts", shoppingCartList);
        dataMap.put("totalPrice",allPrice.setScale(2, BigDecimal.ROUND_HALF_UP));
        dataMap.put("totalCount",totalCount);
        ModelAndView view = new ModelAndView("/cart/cart_list",dataMap);
        return view;
    }

    /**
     * 计算同类商品的售价
     *
     * @param cartGroups--购物车商品分组--多个用;分割
     * @param salePrices--多个用;分割
     * @param request
     * @return
     */
    @RequestMapping(value = "count/price",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView calculateCartTotalSalePrice(String cartGroups, String salePrices, HttpServletRequest request) {
        String mobile = this.getCookieValue("mobile",request);
        if (mobile == null) return loginModel();
        if(cartGroups == null || "".equals(cartGroups)) return loginModel();
        Map<String,BigDecimal>groupMapPrice =  getPriceMapFromStrs(cartGroups,salePrices);
        List<ShoppingCart> cartList = shoppingCartService.getUserShoppingCart(mobile,groupMapPrice);
        BigDecimal allPrice = new BigDecimal(0);
        int totalCount = 0;
        for(ShoppingCart cart : cartList){
            allPrice = allPrice.add(cart.getSaleTotalPrice());
            totalCount = totalCount + cart.getTotalCount();
        }
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("carts", cartList);
        dataMap.put("totalPrice",allPrice.setScale(2, BigDecimal.ROUND_HALF_UP));
        dataMap.put("totalCount",totalCount);
        ModelAndView view = new ModelAndView("/cart/cart_list",dataMap);
        return view;
    }

}
