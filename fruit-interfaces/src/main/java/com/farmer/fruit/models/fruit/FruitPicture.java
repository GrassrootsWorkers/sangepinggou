package com.farmer.fruit.models.fruit;

import java.io.Serializable;

public class FruitPicture implements Serializable {
    private Integer id;

    private String type;

    private String number;

    private String orgImage;

    private String bigImage;

    private String centerImage;

    private String smallImage;

    private String addTime;

    private String ifMain;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getOrgImage() {
        return orgImage;
    }

    public void setOrgImage(String orgImage) {
        this.orgImage = orgImage == null ? null : orgImage.trim();
    }

    public String getBigImage() {
        return bigImage;
    }

    public void setBigImage(String bigImage) {
        this.bigImage = bigImage == null ? null : bigImage.trim();
    }

    public String getCenterImage() {
        return centerImage;
    }

    public void setCenterImage(String centerImage) {
        this.centerImage = centerImage == null ? null : centerImage.trim();
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage == null ? null : smallImage.trim();
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }

    public String getIfMain() {
        return ifMain;
    }

    public void setIfMain(String ifMain) {
        this.ifMain = ifMain == null ? null : ifMain.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", number=").append(number);
        sb.append(", orgImage=").append(orgImage);
        sb.append(", bigImage=").append(bigImage);
        sb.append(", centerImage=").append(centerImage);
        sb.append(", smallImage=").append(smallImage);
        sb.append(", addTime=").append(addTime);
        sb.append(", ifMain=").append(ifMain);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}