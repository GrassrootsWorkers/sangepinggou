package com.sangepg.fruit.controller.cart;

import com.farmer.fruit.Constants;
import com.farmer.fruit.interfaces.fruit.IFruitService;
import com.farmer.fruit.interfaces.shopping.IShoppingCartService;
import com.farmer.fruit.json.GsonUtil;
import com.farmer.fruit.models.fruit.Fruit;
import com.farmer.fruit.models.shopping.ShoppingCart;
import com.farmer.fruit.utils.RedisUtils;
import com.sangepg.fruit.controller.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addCart(String fruitCode, HttpServletRequest request) {
        String mobile = this.getCookieValue("mobile",request);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("success", false);

        if (mobile == null || "".equals(mobile.trim())) {
            resultMap.put("msg", "login");
            return resultMap;
        }
        return shoppingCartService.addCart(mobile, fruitCode);
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
