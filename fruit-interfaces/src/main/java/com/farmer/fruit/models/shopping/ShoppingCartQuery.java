package com.farmer.fruit.models.shopping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCartQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public ShoppingCartQuery() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserFlagIsNull() {
            addCriterion("user_flag is null");
            return (Criteria) this;
        }

        public Criteria andUserFlagIsNotNull() {
            addCriterion("user_flag is not null");
            return (Criteria) this;
        }

        public Criteria andUserFlagEqualTo(String value) {
            addCriterion("user_flag =", value, "userFlag");
            return (Criteria) this;
        }

        public Criteria andUserFlagNotEqualTo(String value) {
            addCriterion("user_flag <>", value, "userFlag");
            return (Criteria) this;
        }

        public Criteria andUserFlagGreaterThan(String value) {
            addCriterion("user_flag >", value, "userFlag");
            return (Criteria) this;
        }

        public Criteria andUserFlagGreaterThanOrEqualTo(String value) {
            addCriterion("user_flag >=", value, "userFlag");
            return (Criteria) this;
        }

        public Criteria andUserFlagLessThan(String value) {
            addCriterion("user_flag <", value, "userFlag");
            return (Criteria) this;
        }

        public Criteria andUserFlagLessThanOrEqualTo(String value) {
            addCriterion("user_flag <=", value, "userFlag");
            return (Criteria) this;
        }

        public Criteria andUserFlagLike(String value) {
            addCriterion("user_flag like", value, "userFlag");
            return (Criteria) this;
        }

        public Criteria andUserFlagNotLike(String value) {
            addCriterion("user_flag not like", value, "userFlag");
            return (Criteria) this;
        }

        public Criteria andUserFlagIn(List<String> values) {
            addCriterion("user_flag in", values, "userFlag");
            return (Criteria) this;
        }

        public Criteria andUserFlagNotIn(List<String> values) {
            addCriterion("user_flag not in", values, "userFlag");
            return (Criteria) this;
        }

        public Criteria andUserFlagBetween(String value1, String value2) {
            addCriterion("user_flag between", value1, value2, "userFlag");
            return (Criteria) this;
        }

        public Criteria andUserFlagNotBetween(String value1, String value2) {
            addCriterion("user_flag not between", value1, value2, "userFlag");
            return (Criteria) this;
        }

        public Criteria andBuyFlagIsNull() {
            addCriterion("buy_flag is null");
            return (Criteria) this;
        }

        public Criteria andBuyFlagIsNotNull() {
            addCriterion("buy_flag is not null");
            return (Criteria) this;
        }

        public Criteria andBuyFlagEqualTo(String value) {
            addCriterion("buy_flag =", value, "buyFlag");
            return (Criteria) this;
        }

        public Criteria andBuyFlagNotEqualTo(String value) {
            addCriterion("buy_flag <>", value, "buyFlag");
            return (Criteria) this;
        }

        public Criteria andBuyFlagGreaterThan(String value) {
            addCriterion("buy_flag >", value, "buyFlag");
            return (Criteria) this;
        }

        public Criteria andBuyFlagGreaterThanOrEqualTo(String value) {
            addCriterion("buy_flag >=", value, "buyFlag");
            return (Criteria) this;
        }

        public Criteria andBuyFlagLessThan(String value) {
            addCriterion("buy_flag <", value, "buyFlag");
            return (Criteria) this;
        }

        public Criteria andBuyFlagLessThanOrEqualTo(String value) {
            addCriterion("buy_flag <=", value, "buyFlag");
            return (Criteria) this;
        }

        public Criteria andBuyFlagLike(String value) {
            addCriterion("buy_flag like", value, "buyFlag");
            return (Criteria) this;
        }

        public Criteria andBuyFlagNotLike(String value) {
            addCriterion("buy_flag not like", value, "buyFlag");
            return (Criteria) this;
        }

        public Criteria andBuyFlagIn(List<String> values) {
            addCriterion("buy_flag in", values, "buyFlag");
            return (Criteria) this;
        }

        public Criteria andBuyFlagNotIn(List<String> values) {
            addCriterion("buy_flag not in", values, "buyFlag");
            return (Criteria) this;
        }

        public Criteria andBuyFlagBetween(String value1, String value2) {
            addCriterion("buy_flag between", value1, value2, "buyFlag");
            return (Criteria) this;
        }

        public Criteria andBuyFlagNotBetween(String value1, String value2) {
            addCriterion("buy_flag not between", value1, value2, "buyFlag");
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