package com.farmer.fruit.models.lottery;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by liuzhi on 2016/8/21.
 */
public class Lottery implements Serializable {
    public static final int DEFAULT_ID = 1;

    /**
     * 抽奖规则编码
     */
    private Long id;
    /**
     * 精品类型 送、折、资格
     * 送：买大的送小的，送券
     * 折：买多少斤给个折扣价
     * 资格：买多少有资格买奇缺的水果
     */
    private String type;
    /**
     * 送的个数
     */
    private int giveCount;
    /**
     * 折扣率 0.85
     */
    private BigDecimal discount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGiveCount() {
        return giveCount;
    }

    public void setGiveCount(int giveCount) {
        this.giveCount = giveCount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
