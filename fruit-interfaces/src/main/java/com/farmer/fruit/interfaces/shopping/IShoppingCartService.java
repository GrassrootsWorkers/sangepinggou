package com.farmer.fruit.interfaces.shopping;

import com.farmer.fruit.models.shopping.ShoppingCart;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhi on 2016/8/21.
 */
public interface IShoppingCartService {

    /**
     * 展示购物车信息
     * @param groupMapPrice -- 购物车分类和价格对应
     * @return
     */
    List<ShoppingCart> getUserShoppingCart(String mobile, Map<String,BigDecimal>groupMapPrice);

    /**
     * 获取用户购物车中的数量
     * @param mobile
     * @return
     */
    int getCartGoodsCount(String mobile);

    /**
     * 添加购物车
     * @param mobile
     * @param fruitCode
     * @return
     */
    Map<String,Object> addCart(String mobile,String fruitCode);

    /**
     *标记购物车是要还是不要
     * @param fruitCodes
     * @param markType -buy --noBuy
     * @return
     */
    boolean markCartGoods(String mobile,String[] fruitCodes,String markType);

    /**
     * 验证是否已经添加购物车
     * @param mobile
     * @param fruitCode
     * @return
     */
    boolean ifAlreadyAddToCart(String mobile,String fruitCode);

    /**
     *
     * @param mobile
     * @param fruitCode
     * @return
     */
    boolean deleteFromCart(String mobile,String fruitCode);
}
