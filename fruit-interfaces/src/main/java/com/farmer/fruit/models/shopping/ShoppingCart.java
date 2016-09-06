package com.farmer.fruit.models.shopping;

import com.farmer.fruit.models.fruit.Fruit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends Cart {

    /**
     * 购物车商品分组 分类_品牌_品种_baseCode(种植人编码)
     */
    private String cartGroup;
    /**
     * 购物车显示名称
     */
    private String cartGroupName;
    /**
     * 意愿要买的水果列表
     */
    private List<Fruit> wishesBuyFruitList;
    /**
     * 暂无意愿要买的水果列表
     */
    private List<Fruit> noWishesBuyFruitList;

    /**
     * 购物车总个数
     */
    private int totalCount;

    /**
     * 购物车总总量
     */
    private BigDecimal totalWeight;
    /**
     * 市场价
     */
    private BigDecimal markPrice;
    /**
     * 市场总价
     */
    private BigDecimal markTotalPrice;
    /**
     * 实际售价
     */
    private BigDecimal salePrice;
    /**
     * 实际应付总价
     */
    private BigDecimal saleTotalPrice;
    /**
     * 优惠总价
     */
    private BigDecimal discountTotalPrice;

    public String getCartGroupName() {
        return cartGroupName;
    }

    public void setCartGroupName(String cartGroupName) {
        this.cartGroupName = cartGroupName;
    }

    public List<Fruit> getWishesBuyFruitList() {
        if(wishesBuyFruitList == null){
            wishesBuyFruitList = new ArrayList<Fruit>();
        }
        return wishesBuyFruitList;
    }

    public void setWishesBuyFruitList(List<Fruit> wishesBuyFruitList) {
        this.wishesBuyFruitList = wishesBuyFruitList;
    }

    public List<Fruit> getNoWishesBuyFruitList() {
        if(noWishesBuyFruitList == null){
            noWishesBuyFruitList = new ArrayList<>();
        }
        return noWishesBuyFruitList;
    }

    public void setNoWishesBuyFruitList(List<Fruit> noWishesBuyFruitList) {
        this.noWishesBuyFruitList = noWishesBuyFruitList;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    public BigDecimal getMarkPrice() {
        return markPrice;
    }

    public void setMarkPrice(BigDecimal markPrice) {
        this.markPrice = markPrice;
    }

    public BigDecimal getMarkTotalPrice() {
        return markTotalPrice;
    }

    public void setMarkTotalPrice(BigDecimal markTotalPrice) {
        this.markTotalPrice = markTotalPrice;
    }

    public BigDecimal getSalePrice() {
        if(salePrice == null) return new BigDecimal(0.00).setScale(2,BigDecimal.ROUND_HALF_UP);
        return salePrice.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getSaleTotalPrice() {
        if(salePrice == null) return new BigDecimal(0.00).setScale(2,BigDecimal.ROUND_HALF_UP);
        return saleTotalPrice.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setSaleTotalPrice(BigDecimal saleTotalPrice) {
        this.saleTotalPrice = saleTotalPrice;
    }

    public BigDecimal getDiscountTotalPrice() {
        return  discountTotalPrice;
    }

    public void setDiscountTotalPrice(BigDecimal discountTotalPrice) {
        this.discountTotalPrice = discountTotalPrice;
    }

    public String getCartGroup() {
        return cartGroup;
    }

    public void setCartGroup(String cartGroup) {
        this.cartGroup = cartGroup;
    }
}