package com.sangepg.fruit.controller.cart;

/**
 * Created by liuzhi on 2016/9/1.
 */
public class ShoppingCartVo {
    private String mobile;
    private String cartGroups;
    private String salePrices;
    private String markFlag;
    private String fruitCodes;
    private Double salePrice;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCartGroups() {
        return cartGroups;
    }

    public void setCartGroups(String cartGroups) {
        this.cartGroups = cartGroups;
    }

    public String getSalePrices() {
        return salePrices;
    }

    public void setSalePrices(String salePrices) {
        this.salePrices = salePrices;
    }

    public String getMarkFlag() {
        return markFlag;
    }

    public void setMarkFlag(String markFlag) {
        this.markFlag = markFlag;
    }

    public String getFruitCodes() {
        return fruitCodes;
    }

    public void setFruitCodes(String fruitCodes) {
        this.fruitCodes = fruitCodes;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }
}
