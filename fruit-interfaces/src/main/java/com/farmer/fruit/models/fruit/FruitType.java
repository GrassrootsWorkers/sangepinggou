package com.farmer.fruit.models.fruit;

import com.farmer.fruit.models.BaseEntity;

/**
 * Created by liuzhi on 2016/7/1.
 */
public class FruitType extends BaseEntity {
    private String code;
    private String name;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
