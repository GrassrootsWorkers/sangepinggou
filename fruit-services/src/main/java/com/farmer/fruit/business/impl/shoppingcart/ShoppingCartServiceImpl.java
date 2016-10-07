package com.farmer.fruit.business.impl.shoppingcart;

import com.farmer.fruit.Constants;
import com.farmer.fruit.interfaces.common.ICommonService;
import com.farmer.fruit.interfaces.fruit.IFruitService;
import com.farmer.fruit.interfaces.shopping.IShoppingCartService;
import com.farmer.fruit.json.GsonUtil;
import com.farmer.fruit.models.fruit.Fruit;
import com.farmer.fruit.models.shopping.ShoppingCart;
import com.farmer.fruit.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by liuzhi on 2016/8/21.
 */
@Service
public class ShoppingCartServiceImpl implements IShoppingCartService {
    @Autowired
    IFruitService fruitService;
    @Autowired
    JedisPool jedisPool;
    @Autowired
    ICommonService commonService;
    @Override
    public List<ShoppingCart> getUserShoppingCart(String mobile,Map<String,BigDecimal>groupMapPrice) {
        RedisUtils redisUtils = new RedisUtils(jedisPool);
        List<ShoppingCart> shoppingCartList = new ArrayList<>();
        Map<String, ShoppingCart> shoppingCartMap = new HashMap<>();
        if (redisUtils.getFieldSize(getUserBuyCartKey(mobile)) > 0) {
            Map<String, String> buyGoods = redisUtils.getUserCartStr(getUserBuyCartKey(mobile));
            Set<Map.Entry<String, String>> cartSet = buyGoods.entrySet();
            for (Map.Entry<String, String> entry : cartSet) {
                String fruitJson = entry.getValue();
                Fruit fruit = GsonUtil.getObject(fruitJson, Fruit.class);
                fruit.setBrandName(commonService.getBrandName(fruit.getBrandId()));
                fruit.setVarietyName(commonService.getVarietyName(fruit.getVarietyId()));
                fruit.setTypeName(commonService.getFruitTypeName(fruit.getType()));
                String cartGroupKey = getCartGroupKey(fruit);
                BigDecimal salePrice = null;
                if(groupMapPrice  == null){
                    salePrice = getSalePrice(cartGroupKey);
                    if(salePrice == null){
                        salePrice = new BigDecimal(fruit.getMarketPrice());
                    }
                }else{
                    salePrice = groupMapPrice.get(cartGroupKey);
                    if(salePrice == null){
                        salePrice = new BigDecimal(fruit.getMarketPrice());
                    }
                }
                fruit.setSalePrice(salePrice);
                ShoppingCart shoppingCart = shoppingCartMap.get(cartGroupKey);
                if (shoppingCart == null) {

                    shoppingCart = new ShoppingCart();

                    shoppingCart.setCartGroup(cartGroupKey);
                    shoppingCart.setMarkPrice(new BigDecimal(fruit.getMarketPrice()));
                    shoppingCart.setSalePrice(salePrice);

                    shoppingCartMap.put(cartGroupKey,shoppingCart);

                    shoppingCartList.add(shoppingCart);
                }
                shoppingCart.getWishesBuyFruitList().add(fruit);
            }
        }
        //获取标记暂时不要的商品列表
        if (redisUtils.getFieldSize(getUserNoBuyCartKey(mobile)) > 0) {
            Map<String, String> noBuyGoods = redisUtils.getUserCartStr(getUserNoBuyCartKey(mobile));
            Set<Map.Entry<String, String>> noBuySet = noBuyGoods.entrySet();
            for (Map.Entry<String, String> entry : noBuySet) {
                String fruitJson = entry.getValue();
                Fruit fruit = GsonUtil.getObject(fruitJson, Fruit.class);
                fruit.setBrandName(commonService.getBrandName(fruit.getBrandId()));
                fruit.setVarietyName(commonService.getVarietyName(fruit.getVarietyId()));
                fruit.setTypeName(commonService.getFruitTypeName(fruit.getType()));
                String cartGroupKey = getCartGroupKey(fruit);
                BigDecimal salePrice = null;
                if(groupMapPrice  == null){
                    salePrice = getSalePrice(cartGroupKey);
                    if(salePrice == null){
                        salePrice = new BigDecimal(fruit.getMarketPrice());
                    }
                }else{
                    salePrice = groupMapPrice.get(cartGroupKey);
                    if(salePrice == null){
                        salePrice = new BigDecimal(fruit.getMarketPrice());
                    }
                }
                fruit.setSalePrice(salePrice);
                ShoppingCart shoppingCart = shoppingCartMap.get(cartGroupKey);
                if (shoppingCart == null) {
                    shoppingCart = new ShoppingCart();

                    shoppingCart.setCartGroup(cartGroupKey);
                    shoppingCart.setMarkPrice(new BigDecimal(fruit.getMarketPrice()));
                    shoppingCart.setSalePrice(salePrice);

                    shoppingCartMap.put(cartGroupKey,shoppingCart);
                    shoppingCartList.add(shoppingCart);
                }
                shoppingCart.getNoWishesBuyFruitList().add(fruit);
            }
        }

        for (ShoppingCart cart : shoppingCartList) {

            cart.setTotalCount(cart.getWishesBuyFruitList().size());

            cart.setCartGroupName(getCartGroupKeyName(cart.getCartGroup()));

            setCartSummaryValue(cart,cart.getWishesBuyFruitList(),"buy");
            setCartSummaryValue(cart,cart.getNoWishesBuyFruitList(),"noBuy");
        }
        return shoppingCartList;
    }


    private ShoppingCart setCartSummaryValue(ShoppingCart cart, List<Fruit> fruitList,String flag) {

        BigDecimal totalWeight = new BigDecimal(0);
        for (Fruit f : fruitList) {
            totalWeight = totalWeight.add(f.getWeight());
        }
        cart.setTotalWeight(totalWeight);

        BigDecimal totalMartPrice = totalWeight.divide(new BigDecimal(1000)).multiply(cart.getMarkPrice());
        cart.setMarkTotalPrice(totalMartPrice);
        if("buy".equals(flag) ){
            BigDecimal totalSalePrice = totalWeight.divide(new BigDecimal(1000)).multiply(cart.getSalePrice());
            cart.setSaleTotalPrice(totalSalePrice);

            cart.setDiscountTotalPrice(totalMartPrice.subtract(totalSalePrice));
        }

        return cart;
    }
    private String getCartGroupKey(Fruit fruit) {
        StringBuffer sb = new StringBuffer();
        sb.append(fruit.getType()).append("_").append(fruit.getBrandId());
        return sb.toString();
    }
    private String getCartGroupKeyName(String key) {
        String[] keys = key.split("_");
        StringBuffer sb = new StringBuffer();
        sb.append(commonService.getBrandName(Long.parseLong(keys[1]))).append(commonService.getFruitTypeName(keys[0]));
        return sb.toString();
    }

    /**
     * 网购时根据市场行情实施取售价--不能高于添加时的市场价
     * @param type --水果类型-品牌
     * @return
     */
    private BigDecimal getSalePrice(String type){
        return  null;
    }
    @Override
    public int getCartGoodsCount(String mobile) {
        RedisUtils redisUtils = new RedisUtils(jedisPool);
        return redisUtils.getFieldSize(getUserBuyCartKey(mobile));
    }


    @Override
    public Map<String, Object> addCart(String mobile, String fruitCode) {
        Fruit fruit = fruitService.getByCode(fruitCode);
        fruit.setTypeName(commonService.getFruitTypeName(fruit.getType()));
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (fruit == null) {
            resultMap.put("msg", "login");
            return resultMap;
        }
        RedisUtils redisUtils = new RedisUtils(jedisPool);
        //记录被扫描次数
        redisUtils.setTimes(fruitCode);

        String addedUserMobile = redisUtils.getValueByKey(fruitCode);
        if (addedUserMobile == null) {
            redisUtils.setKey(fruitCode, mobile, Constants.FRUIT_EXPIRE_SECOND);
            redisUtils.setHKey(getUserBuyCartKey(mobile), fruitCode, GsonUtil.getJSON(fruit));
        } else {
            if (!addedUserMobile.equals(mobile)) {
                redisUtils.setKey(fruitCode, mobile, Constants.FRUIT_EXPIRE_SECOND);
                redisUtils.delHField(getUserBuyCartKey(addedUserMobile), fruitCode);
                redisUtils.setHKey(getUserBuyCartKey(mobile), fruitCode, GsonUtil.getJSON(fruit));
            } else {
                resultMap.put("msg", "已放入篮子");
            }
        }
        resultMap.put("cartCount", redisUtils.getFieldSize(getUserBuyCartKey(mobile)));
        resultMap.put("success", true);
        resultMap.put("fruit", fruit);
        return resultMap;
    }

    @Override
    public boolean markCartGoods(String mobile ,String[] fruitCodes, String markType) {
        for(int i=0;i<fruitCodes.length;i++){
            Fruit fruit = fruitService.getByCode(fruitCodes[i]);
            try{
                RedisUtils redisUtils = new RedisUtils(jedisPool);
                if("buy".equals(markType)){
                    redisUtils.delHField(getUserNoBuyCartKey(mobile), fruitCodes[i]);
                    redisUtils.setHKey(getUserBuyCartKey(mobile), fruitCodes[i], GsonUtil.getJSON(fruit));
                }else{
                    redisUtils.delHField(getUserBuyCartKey(mobile), fruitCodes[i]);
                    redisUtils.setHKey(getUserNoBuyCartKey(mobile), fruitCodes[i], GsonUtil.getJSON(fruit));
                }
            }catch (Exception e){
            }
        }
        return true;
    }

    @Override
    public boolean ifAlreadyAddToCart(String mobile, String fruitCode) {
        RedisUtils redisUtils = new RedisUtils(jedisPool);
        String addedMobile = redisUtils.getValueByKey(fruitCode);
        if (addedMobile == null || !addedMobile.equals(mobile)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteFromCart(String mobile, String fruitCode) {
        RedisUtils redisUtils = new RedisUtils(jedisPool);
        String addedMobile = redisUtils.getValueByKey(fruitCode);
        if (addedMobile == null || !addedMobile.equals(mobile)) {
            return false;
        } else {
            redisUtils.del(fruitCode);
            redisUtils.delHField(getUserBuyCartKey(mobile), fruitCode);
        }
        return true;
    }
    private String getUserBuyCartKey(String mobile){
        String prefix = "buy_";
        return prefix+mobile;
    }
    private String getUserNoBuyCartKey(String mobile){
        String prefix = "noBuy_";
        return prefix+mobile;
    }
}
