package com.farmer.fruit.models.fruit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FruitQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public FruitQuery() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo=pageNo;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setStartRow(Integer startRow) {
        this.startRow=startRow;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize=pageSize;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setFields(String fields) {
        this.fields=fields;
    }

    public String getFields() {
        return fields;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFruitCodeIsNull() {
            addCriterion("fruit_code is null");
            return (Criteria) this;
        }

        public Criteria andFruitCodeIsNotNull() {
            addCriterion("fruit_code is not null");
            return (Criteria) this;
        }

        public Criteria andFruitCodeEqualTo(String value) {
            addCriterion("fruit_code =", value, "fruitCode");
            return (Criteria) this;
        }

        public Criteria andFruitCodeNotEqualTo(String value) {
            addCriterion("fruit_code <>", value, "fruitCode");
            return (Criteria) this;
        }

        public Criteria andFruitCodeGreaterThan(String value) {
            addCriterion("fruit_code >", value, "fruitCode");
            return (Criteria) this;
        }

        public Criteria andFruitCodeGreaterThanOrEqualTo(String value) {
            addCriterion("fruit_code >=", value, "fruitCode");
            return (Criteria) this;
        }

        public Criteria andFruitCodeLessThan(String value) {
            addCriterion("fruit_code <", value, "fruitCode");
            return (Criteria) this;
        }

        public Criteria andFruitCodeLessThanOrEqualTo(String value) {
            addCriterion("fruit_code <=", value, "fruitCode");
            return (Criteria) this;
        }

        public Criteria andFruitCodeLike(String value) {
            addCriterion("fruit_code like", value, "fruitCode");
            return (Criteria) this;
        }

        public Criteria andFruitCodeNotLike(String value) {
            addCriterion("fruit_code not like", value, "fruitCode");
            return (Criteria) this;
        }

        public Criteria andFruitCodeIn(List<String> values) {
            addCriterion("fruit_code in", values, "fruitCode");
            return (Criteria) this;
        }

        public Criteria andFruitCodeNotIn(List<String> values) {
            addCriterion("fruit_code not in", values, "fruitCode");
            return (Criteria) this;
        }

        public Criteria andFruitCodeBetween(String value1, String value2) {
            addCriterion("fruit_code between", value1, value2, "fruitCode");
            return (Criteria) this;
        }

        public Criteria andFruitCodeNotBetween(String value1, String value2) {
            addCriterion("fruit_code not between", value1, value2, "fruitCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeIsNull() {
            addCriterion("base_code is null");
            return (Criteria) this;
        }

        public Criteria andBaseCodeIsNotNull() {
            addCriterion("base_code is not null");
            return (Criteria) this;
        }

        public Criteria andBaseCodeEqualTo(Integer value) {
            addCriterion("base_code =", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeNotEqualTo(Integer value) {
            addCriterion("base_code <>", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeGreaterThan(Integer value) {
            addCriterion("base_code >", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("base_code >=", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeLessThan(Integer value) {
            addCriterion("base_code <", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeLessThanOrEqualTo(Integer value) {
            addCriterion("base_code <=", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeIn(List<Integer> values) {
            addCriterion("base_code in", values, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeNotIn(List<Integer> values) {
            addCriterion("base_code not in", values, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeBetween(Integer value1, Integer value2) {
            addCriterion("base_code between", value1, value2, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("base_code not between", value1, value2, "baseCode");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(Double value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(Double value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(Double value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(Double value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(Double value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(Double value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<Double> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<Double> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(Double value1, Double value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(Double value1, Double value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andHeightIsNull() {
            addCriterion("height is null");
            return (Criteria) this;
        }

        public Criteria andHeightIsNotNull() {
            addCriterion("height is not null");
            return (Criteria) this;
        }

        public Criteria andHeightEqualTo(Double value) {
            addCriterion("height =", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotEqualTo(Double value) {
            addCriterion("height <>", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThan(Double value) {
            addCriterion("height >", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThanOrEqualTo(Double value) {
            addCriterion("height >=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThan(Double value) {
            addCriterion("height <", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThanOrEqualTo(Double value) {
            addCriterion("height <=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightIn(List<Double> values) {
            addCriterion("height in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotIn(List<Double> values) {
            addCriterion("height not in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightBetween(Double value1, Double value2) {
            addCriterion("height between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotBetween(Double value1, Double value2) {
            addCriterion("height not between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andWidthIsNull() {
            addCriterion("width is null");
            return (Criteria) this;
        }

        public Criteria andWidthIsNotNull() {
            addCriterion("width is not null");
            return (Criteria) this;
        }

        public Criteria andWidthEqualTo(Double value) {
            addCriterion("width =", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotEqualTo(Double value) {
            addCriterion("width <>", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThan(Double value) {
            addCriterion("width >", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThanOrEqualTo(Double value) {
            addCriterion("width >=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThan(Double value) {
            addCriterion("width <", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThanOrEqualTo(Double value) {
            addCriterion("width <=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthIn(List<Double> values) {
            addCriterion("width in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotIn(List<Double> values) {
            addCriterion("width not in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthBetween(Double value1, Double value2) {
            addCriterion("width between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotBetween(Double value1, Double value2) {
            addCriterion("width not between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andHighIsNull() {
            addCriterion("high is null");
            return (Criteria) this;
        }

        public Criteria andHighIsNotNull() {
            addCriterion("high is not null");
            return (Criteria) this;
        }

        public Criteria andHighEqualTo(Double value) {
            addCriterion("high =", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighNotEqualTo(Double value) {
            addCriterion("high <>", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighGreaterThan(Double value) {
            addCriterion("high >", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighGreaterThanOrEqualTo(Double value) {
            addCriterion("high >=", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighLessThan(Double value) {
            addCriterion("high <", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighLessThanOrEqualTo(Double value) {
            addCriterion("high <=", value, "high");
            return (Criteria) this;
        }

        public Criteria andHighIn(List<Double> values) {
            addCriterion("high in", values, "high");
            return (Criteria) this;
        }

        public Criteria andHighNotIn(List<Double> values) {
            addCriterion("high not in", values, "high");
            return (Criteria) this;
        }

        public Criteria andHighBetween(Double value1, Double value2) {
            addCriterion("high between", value1, value2, "high");
            return (Criteria) this;
        }

        public Criteria andHighNotBetween(Double value1, Double value2) {
            addCriterion("high not between", value1, value2, "high");
            return (Criteria) this;
        }

        public Criteria andMaturingStatusIsNull() {
            addCriterion("maturing_status is null");
            return (Criteria) this;
        }

        public Criteria andMaturingStatusIsNotNull() {
            addCriterion("maturing_status is not null");
            return (Criteria) this;
        }

        public Criteria andMaturingStatusEqualTo(Byte value) {
            addCriterion("maturing_status =", value, "maturingStatus");
            return (Criteria) this;
        }

        public Criteria andMaturingStatusNotEqualTo(Byte value) {
            addCriterion("maturing_status <>", value, "maturingStatus");
            return (Criteria) this;
        }

        public Criteria andMaturingStatusGreaterThan(Byte value) {
            addCriterion("maturing_status >", value, "maturingStatus");
            return (Criteria) this;
        }

        public Criteria andMaturingStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("maturing_status >=", value, "maturingStatus");
            return (Criteria) this;
        }

        public Criteria andMaturingStatusLessThan(Byte value) {
            addCriterion("maturing_status <", value, "maturingStatus");
            return (Criteria) this;
        }

        public Criteria andMaturingStatusLessThanOrEqualTo(Byte value) {
            addCriterion("maturing_status <=", value, "maturingStatus");
            return (Criteria) this;
        }

        public Criteria andMaturingStatusIn(List<Byte> values) {
            addCriterion("maturing_status in", values, "maturingStatus");
            return (Criteria) this;
        }

        public Criteria andMaturingStatusNotIn(List<Byte> values) {
            addCriterion("maturing_status not in", values, "maturingStatus");
            return (Criteria) this;
        }

        public Criteria andMaturingStatusBetween(Byte value1, Byte value2) {
            addCriterion("maturing_status between", value1, value2, "maturingStatus");
            return (Criteria) this;
        }

        public Criteria andMaturingStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("maturing_status not between", value1, value2, "maturingStatus");
            return (Criteria) this;
        }

        public Criteria andHarvestTimeIsNull() {
            addCriterion("harvest_time is null");
            return (Criteria) this;
        }

        public Criteria andHarvestTimeIsNotNull() {
            addCriterion("harvest_time is not null");
            return (Criteria) this;
        }

        public Criteria andHarvestTimeEqualTo(Date value) {
            addCriterion("harvest_time =", value, "harvestTime");
            return (Criteria) this;
        }

        public Criteria andHarvestTimeNotEqualTo(Date value) {
            addCriterion("harvest_time <>", value, "harvestTime");
            return (Criteria) this;
        }

        public Criteria andHarvestTimeGreaterThan(Date value) {
            addCriterion("harvest_time >", value, "harvestTime");
            return (Criteria) this;
        }

        public Criteria andHarvestTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("harvest_time >=", value, "harvestTime");
            return (Criteria) this;
        }

        public Criteria andHarvestTimeLessThan(Date value) {
            addCriterion("harvest_time <", value, "harvestTime");
            return (Criteria) this;
        }

        public Criteria andHarvestTimeLessThanOrEqualTo(Date value) {
            addCriterion("harvest_time <=", value, "harvestTime");
            return (Criteria) this;
        }

        public Criteria andHarvestTimeIn(List<Date> values) {
            addCriterion("harvest_time in", values, "harvestTime");
            return (Criteria) this;
        }

        public Criteria andHarvestTimeNotIn(List<Date> values) {
            addCriterion("harvest_time not in", values, "harvestTime");
            return (Criteria) this;
        }

        public Criteria andHarvestTimeBetween(Date value1, Date value2) {
            addCriterion("harvest_time between", value1, value2, "harvestTime");
            return (Criteria) this;
        }

        public Criteria andHarvestTimeNotBetween(Date value1, Date value2) {
            addCriterion("harvest_time not between", value1, value2, "harvestTime");
            return (Criteria) this;
        }

        public Criteria andMarketPriceIsNull() {
            addCriterion("market_price is null");
            return (Criteria) this;
        }

        public Criteria andMarketPriceIsNotNull() {
            addCriterion("market_price is not null");
            return (Criteria) this;
        }

        public Criteria andMarketPriceEqualTo(Double value) {
            addCriterion("market_price =", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotEqualTo(Double value) {
            addCriterion("market_price <>", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceGreaterThan(Double value) {
            addCriterion("market_price >", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("market_price >=", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceLessThan(Double value) {
            addCriterion("market_price <", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceLessThanOrEqualTo(Double value) {
            addCriterion("market_price <=", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceIn(List<Double> values) {
            addCriterion("market_price in", values, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotIn(List<Double> values) {
            addCriterion("market_price not in", values, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceBetween(Double value1, Double value2) {
            addCriterion("market_price between", value1, value2, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotBetween(Double value1, Double value2) {
            addCriterion("market_price not between", value1, value2, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddUserIdIsNull() {
            addCriterion("add_user_id is null");
            return (Criteria) this;
        }

        public Criteria andAddUserIdIsNotNull() {
            addCriterion("add_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andAddUserIdEqualTo(Integer value) {
            addCriterion("add_user_id =", value, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdNotEqualTo(Integer value) {
            addCriterion("add_user_id <>", value, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdGreaterThan(Integer value) {
            addCriterion("add_user_id >", value, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("add_user_id >=", value, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdLessThan(Integer value) {
            addCriterion("add_user_id <", value, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("add_user_id <=", value, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdIn(List<Integer> values) {
            addCriterion("add_user_id in", values, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdNotIn(List<Integer> values) {
            addCriterion("add_user_id not in", values, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdBetween(Integer value1, Integer value2) {
            addCriterion("add_user_id between", value1, value2, "addUserId");
            return (Criteria) this;
        }

        public Criteria andAddUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("add_user_id not between", value1, value2, "addUserId");
            return (Criteria) this;
        }

        public Criteria andCheckoutUserIdIsNull() {
            addCriterion("checkout_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCheckoutUserIdIsNotNull() {
            addCriterion("checkout_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCheckoutUserIdEqualTo(Integer value) {
            addCriterion("checkout_user_id =", value, "checkoutUserId");
            return (Criteria) this;
        }

        public Criteria andCheckoutUserIdNotEqualTo(Integer value) {
            addCriterion("checkout_user_id <>", value, "checkoutUserId");
            return (Criteria) this;
        }

        public Criteria andCheckoutUserIdGreaterThan(Integer value) {
            addCriterion("checkout_user_id >", value, "checkoutUserId");
            return (Criteria) this;
        }

        public Criteria andCheckoutUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("checkout_user_id >=", value, "checkoutUserId");
            return (Criteria) this;
        }

        public Criteria andCheckoutUserIdLessThan(Integer value) {
            addCriterion("checkout_user_id <", value, "checkoutUserId");
            return (Criteria) this;
        }

        public Criteria andCheckoutUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("checkout_user_id <=", value, "checkoutUserId");
            return (Criteria) this;
        }

        public Criteria andCheckoutUserIdIn(List<Integer> values) {
            addCriterion("checkout_user_id in", values, "checkoutUserId");
            return (Criteria) this;
        }

        public Criteria andCheckoutUserIdNotIn(List<Integer> values) {
            addCriterion("checkout_user_id not in", values, "checkoutUserId");
            return (Criteria) this;
        }

        public Criteria andCheckoutUserIdBetween(Integer value1, Integer value2) {
            addCriterion("checkout_user_id between", value1, value2, "checkoutUserId");
            return (Criteria) this;
        }

        public Criteria andCheckoutUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("checkout_user_id not between", value1, value2, "checkoutUserId");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeIsNull() {
            addCriterion("checkout_time is null");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeIsNotNull() {
            addCriterion("checkout_time is not null");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeEqualTo(Date value) {
            addCriterion("checkout_time =", value, "checkoutTime");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeNotEqualTo(Date value) {
            addCriterion("checkout_time <>", value, "checkoutTime");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeGreaterThan(Date value) {
            addCriterion("checkout_time >", value, "checkoutTime");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("checkout_time >=", value, "checkoutTime");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeLessThan(Date value) {
            addCriterion("checkout_time <", value, "checkoutTime");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeLessThanOrEqualTo(Date value) {
            addCriterion("checkout_time <=", value, "checkoutTime");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeIn(List<Date> values) {
            addCriterion("checkout_time in", values, "checkoutTime");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeNotIn(List<Date> values) {
            addCriterion("checkout_time not in", values, "checkoutTime");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeBetween(Date value1, Date value2) {
            addCriterion("checkout_time between", value1, value2, "checkoutTime");
            return (Criteria) this;
        }

        public Criteria andCheckoutTimeNotBetween(Date value1, Date value2) {
            addCriterion("checkout_time not between", value1, value2, "checkoutTime");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNull() {
            addCriterion("delete_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNotNull() {
            addCriterion("delete_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagEqualTo(String value) {
            addCriterion("delete_flag =", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotEqualTo(String value) {
            addCriterion("delete_flag <>", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThan(String value) {
            addCriterion("delete_flag >", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThanOrEqualTo(String value) {
            addCriterion("delete_flag >=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThan(String value) {
            addCriterion("delete_flag <", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThanOrEqualTo(String value) {
            addCriterion("delete_flag <=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLike(String value) {
            addCriterion("delete_flag like", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotLike(String value) {
            addCriterion("delete_flag not like", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIn(List<String> values) {
            addCriterion("delete_flag in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotIn(List<String> values) {
            addCriterion("delete_flag not in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagBetween(String value1, String value2) {
            addCriterion("delete_flag between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotBetween(String value1, String value2) {
            addCriterion("delete_flag not between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andPriceFlagIsNull() {
            addCriterion("price_flag is null");
            return (Criteria) this;
        }

        public Criteria andPriceFlagIsNotNull() {
            addCriterion("price_flag is not null");
            return (Criteria) this;
        }

        public Criteria andPriceFlagEqualTo(String value) {
            addCriterion("price_flag =", value, "priceFlag");
            return (Criteria) this;
        }

        public Criteria andPriceFlagNotEqualTo(String value) {
            addCriterion("price_flag <>", value, "priceFlag");
            return (Criteria) this;
        }

        public Criteria andPriceFlagGreaterThan(String value) {
            addCriterion("price_flag >", value, "priceFlag");
            return (Criteria) this;
        }

        public Criteria andPriceFlagGreaterThanOrEqualTo(String value) {
            addCriterion("price_flag >=", value, "priceFlag");
            return (Criteria) this;
        }

        public Criteria andPriceFlagLessThan(String value) {
            addCriterion("price_flag <", value, "priceFlag");
            return (Criteria) this;
        }

        public Criteria andPriceFlagLessThanOrEqualTo(String value) {
            addCriterion("price_flag <=", value, "priceFlag");
            return (Criteria) this;
        }

        public Criteria andPriceFlagLike(String value) {
            addCriterion("price_flag like", value, "priceFlag");
            return (Criteria) this;
        }

        public Criteria andPriceFlagNotLike(String value) {
            addCriterion("price_flag not like", value, "priceFlag");
            return (Criteria) this;
        }

        public Criteria andPriceFlagIn(List<String> values) {
            addCriterion("price_flag in", values, "priceFlag");
            return (Criteria) this;
        }

        public Criteria andPriceFlagNotIn(List<String> values) {
            addCriterion("price_flag not in", values, "priceFlag");
            return (Criteria) this;
        }

        public Criteria andPriceFlagBetween(String value1, String value2) {
            addCriterion("price_flag between", value1, value2, "priceFlag");
            return (Criteria) this;
        }

        public Criteria andPriceFlagNotBetween(String value1, String value2) {
            addCriterion("price_flag not between", value1, value2, "priceFlag");
            return (Criteria) this;
        }

        public Criteria andLotteryFlagIsNull() {
            addCriterion("lottery_flag is null");
            return (Criteria) this;
        }

        public Criteria andLotteryFlagIsNotNull() {
            addCriterion("lottery_flag is not null");
            return (Criteria) this;
        }

        public Criteria andLotteryFlagEqualTo(String value) {
            addCriterion("lottery_flag =", value, "lotteryFlag");
            return (Criteria) this;
        }

        public Criteria andLotteryFlagNotEqualTo(String value) {
            addCriterion("lottery_flag <>", value, "lotteryFlag");
            return (Criteria) this;
        }

        public Criteria andLotteryFlagGreaterThan(String value) {
            addCriterion("lottery_flag >", value, "lotteryFlag");
            return (Criteria) this;
        }

        public Criteria andLotteryFlagGreaterThanOrEqualTo(String value) {
            addCriterion("lottery_flag >=", value, "lotteryFlag");
            return (Criteria) this;
        }

        public Criteria andLotteryFlagLessThan(String value) {
            addCriterion("lottery_flag <", value, "lotteryFlag");
            return (Criteria) this;
        }

        public Criteria andLotteryFlagLessThanOrEqualTo(String value) {
            addCriterion("lottery_flag <=", value, "lotteryFlag");
            return (Criteria) this;
        }

        public Criteria andLotteryFlagLike(String value) {
            addCriterion("lottery_flag like", value, "lotteryFlag");
            return (Criteria) this;
        }

        public Criteria andLotteryFlagNotLike(String value) {
            addCriterion("lottery_flag not like", value, "lotteryFlag");
            return (Criteria) this;
        }

        public Criteria andLotteryFlagIn(List<String> values) {
            addCriterion("lottery_flag in", values, "lotteryFlag");
            return (Criteria) this;
        }

        public Criteria andLotteryFlagNotIn(List<String> values) {
            addCriterion("lottery_flag not in", values, "lotteryFlag");
            return (Criteria) this;
        }

        public Criteria andLotteryFlagBetween(String value1, String value2) {
            addCriterion("lottery_flag between", value1, value2, "lotteryFlag");
            return (Criteria) this;
        }

        public Criteria andLotteryFlagNotBetween(String value1, String value2) {
            addCriterion("lottery_flag not between", value1, value2, "lotteryFlag");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}