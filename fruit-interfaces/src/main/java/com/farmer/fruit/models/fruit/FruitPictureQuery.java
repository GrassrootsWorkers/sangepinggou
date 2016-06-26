package com.farmer.fruit.models.fruit;

import java.util.ArrayList;
import java.util.List;

public class FruitPictureQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public FruitPictureQuery() {
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(String value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(String value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(String value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(String value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(String value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(String value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLike(String value) {
            addCriterion("number like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotLike(String value) {
            addCriterion("number not like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<String> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<String> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(String value1, String value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(String value1, String value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andOrgImageIsNull() {
            addCriterion("org_image is null");
            return (Criteria) this;
        }

        public Criteria andOrgImageIsNotNull() {
            addCriterion("org_image is not null");
            return (Criteria) this;
        }

        public Criteria andOrgImageEqualTo(String value) {
            addCriterion("org_image =", value, "orgImage");
            return (Criteria) this;
        }

        public Criteria andOrgImageNotEqualTo(String value) {
            addCriterion("org_image <>", value, "orgImage");
            return (Criteria) this;
        }

        public Criteria andOrgImageGreaterThan(String value) {
            addCriterion("org_image >", value, "orgImage");
            return (Criteria) this;
        }

        public Criteria andOrgImageGreaterThanOrEqualTo(String value) {
            addCriterion("org_image >=", value, "orgImage");
            return (Criteria) this;
        }

        public Criteria andOrgImageLessThan(String value) {
            addCriterion("org_image <", value, "orgImage");
            return (Criteria) this;
        }

        public Criteria andOrgImageLessThanOrEqualTo(String value) {
            addCriterion("org_image <=", value, "orgImage");
            return (Criteria) this;
        }

        public Criteria andOrgImageLike(String value) {
            addCriterion("org_image like", value, "orgImage");
            return (Criteria) this;
        }

        public Criteria andOrgImageNotLike(String value) {
            addCriterion("org_image not like", value, "orgImage");
            return (Criteria) this;
        }

        public Criteria andOrgImageIn(List<String> values) {
            addCriterion("org_image in", values, "orgImage");
            return (Criteria) this;
        }

        public Criteria andOrgImageNotIn(List<String> values) {
            addCriterion("org_image not in", values, "orgImage");
            return (Criteria) this;
        }

        public Criteria andOrgImageBetween(String value1, String value2) {
            addCriterion("org_image between", value1, value2, "orgImage");
            return (Criteria) this;
        }

        public Criteria andOrgImageNotBetween(String value1, String value2) {
            addCriterion("org_image not between", value1, value2, "orgImage");
            return (Criteria) this;
        }

        public Criteria andBigImageIsNull() {
            addCriterion("big_image is null");
            return (Criteria) this;
        }

        public Criteria andBigImageIsNotNull() {
            addCriterion("big_image is not null");
            return (Criteria) this;
        }

        public Criteria andBigImageEqualTo(String value) {
            addCriterion("big_image =", value, "bigImage");
            return (Criteria) this;
        }

        public Criteria andBigImageNotEqualTo(String value) {
            addCriterion("big_image <>", value, "bigImage");
            return (Criteria) this;
        }

        public Criteria andBigImageGreaterThan(String value) {
            addCriterion("big_image >", value, "bigImage");
            return (Criteria) this;
        }

        public Criteria andBigImageGreaterThanOrEqualTo(String value) {
            addCriterion("big_image >=", value, "bigImage");
            return (Criteria) this;
        }

        public Criteria andBigImageLessThan(String value) {
            addCriterion("big_image <", value, "bigImage");
            return (Criteria) this;
        }

        public Criteria andBigImageLessThanOrEqualTo(String value) {
            addCriterion("big_image <=", value, "bigImage");
            return (Criteria) this;
        }

        public Criteria andBigImageLike(String value) {
            addCriterion("big_image like", value, "bigImage");
            return (Criteria) this;
        }

        public Criteria andBigImageNotLike(String value) {
            addCriterion("big_image not like", value, "bigImage");
            return (Criteria) this;
        }

        public Criteria andBigImageIn(List<String> values) {
            addCriterion("big_image in", values, "bigImage");
            return (Criteria) this;
        }

        public Criteria andBigImageNotIn(List<String> values) {
            addCriterion("big_image not in", values, "bigImage");
            return (Criteria) this;
        }

        public Criteria andBigImageBetween(String value1, String value2) {
            addCriterion("big_image between", value1, value2, "bigImage");
            return (Criteria) this;
        }

        public Criteria andBigImageNotBetween(String value1, String value2) {
            addCriterion("big_image not between", value1, value2, "bigImage");
            return (Criteria) this;
        }

        public Criteria andCenterImageIsNull() {
            addCriterion("center_image is null");
            return (Criteria) this;
        }

        public Criteria andCenterImageIsNotNull() {
            addCriterion("center_image is not null");
            return (Criteria) this;
        }

        public Criteria andCenterImageEqualTo(String value) {
            addCriterion("center_image =", value, "centerImage");
            return (Criteria) this;
        }

        public Criteria andCenterImageNotEqualTo(String value) {
            addCriterion("center_image <>", value, "centerImage");
            return (Criteria) this;
        }

        public Criteria andCenterImageGreaterThan(String value) {
            addCriterion("center_image >", value, "centerImage");
            return (Criteria) this;
        }

        public Criteria andCenterImageGreaterThanOrEqualTo(String value) {
            addCriterion("center_image >=", value, "centerImage");
            return (Criteria) this;
        }

        public Criteria andCenterImageLessThan(String value) {
            addCriterion("center_image <", value, "centerImage");
            return (Criteria) this;
        }

        public Criteria andCenterImageLessThanOrEqualTo(String value) {
            addCriterion("center_image <=", value, "centerImage");
            return (Criteria) this;
        }

        public Criteria andCenterImageLike(String value) {
            addCriterion("center_image like", value, "centerImage");
            return (Criteria) this;
        }

        public Criteria andCenterImageNotLike(String value) {
            addCriterion("center_image not like", value, "centerImage");
            return (Criteria) this;
        }

        public Criteria andCenterImageIn(List<String> values) {
            addCriterion("center_image in", values, "centerImage");
            return (Criteria) this;
        }

        public Criteria andCenterImageNotIn(List<String> values) {
            addCriterion("center_image not in", values, "centerImage");
            return (Criteria) this;
        }

        public Criteria andCenterImageBetween(String value1, String value2) {
            addCriterion("center_image between", value1, value2, "centerImage");
            return (Criteria) this;
        }

        public Criteria andCenterImageNotBetween(String value1, String value2) {
            addCriterion("center_image not between", value1, value2, "centerImage");
            return (Criteria) this;
        }

        public Criteria andSmallImageIsNull() {
            addCriterion("small_image is null");
            return (Criteria) this;
        }

        public Criteria andSmallImageIsNotNull() {
            addCriterion("small_image is not null");
            return (Criteria) this;
        }

        public Criteria andSmallImageEqualTo(String value) {
            addCriterion("small_image =", value, "smallImage");
            return (Criteria) this;
        }

        public Criteria andSmallImageNotEqualTo(String value) {
            addCriterion("small_image <>", value, "smallImage");
            return (Criteria) this;
        }

        public Criteria andSmallImageGreaterThan(String value) {
            addCriterion("small_image >", value, "smallImage");
            return (Criteria) this;
        }

        public Criteria andSmallImageGreaterThanOrEqualTo(String value) {
            addCriterion("small_image >=", value, "smallImage");
            return (Criteria) this;
        }

        public Criteria andSmallImageLessThan(String value) {
            addCriterion("small_image <", value, "smallImage");
            return (Criteria) this;
        }

        public Criteria andSmallImageLessThanOrEqualTo(String value) {
            addCriterion("small_image <=", value, "smallImage");
            return (Criteria) this;
        }

        public Criteria andSmallImageLike(String value) {
            addCriterion("small_image like", value, "smallImage");
            return (Criteria) this;
        }

        public Criteria andSmallImageNotLike(String value) {
            addCriterion("small_image not like", value, "smallImage");
            return (Criteria) this;
        }

        public Criteria andSmallImageIn(List<String> values) {
            addCriterion("small_image in", values, "smallImage");
            return (Criteria) this;
        }

        public Criteria andSmallImageNotIn(List<String> values) {
            addCriterion("small_image not in", values, "smallImage");
            return (Criteria) this;
        }

        public Criteria andSmallImageBetween(String value1, String value2) {
            addCriterion("small_image between", value1, value2, "smallImage");
            return (Criteria) this;
        }

        public Criteria andSmallImageNotBetween(String value1, String value2) {
            addCriterion("small_image not between", value1, value2, "smallImage");
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

        public Criteria andAddTimeEqualTo(String value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(String value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(String value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(String value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(String value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(String value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLike(String value) {
            addCriterion("add_time like", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotLike(String value) {
            addCriterion("add_time not like", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<String> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<String> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(String value1, String value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(String value1, String value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andIfMainIsNull() {
            addCriterion("if_main is null");
            return (Criteria) this;
        }

        public Criteria andIfMainIsNotNull() {
            addCriterion("if_main is not null");
            return (Criteria) this;
        }

        public Criteria andIfMainEqualTo(String value) {
            addCriterion("if_main =", value, "ifMain");
            return (Criteria) this;
        }

        public Criteria andIfMainNotEqualTo(String value) {
            addCriterion("if_main <>", value, "ifMain");
            return (Criteria) this;
        }

        public Criteria andIfMainGreaterThan(String value) {
            addCriterion("if_main >", value, "ifMain");
            return (Criteria) this;
        }

        public Criteria andIfMainGreaterThanOrEqualTo(String value) {
            addCriterion("if_main >=", value, "ifMain");
            return (Criteria) this;
        }

        public Criteria andIfMainLessThan(String value) {
            addCriterion("if_main <", value, "ifMain");
            return (Criteria) this;
        }

        public Criteria andIfMainLessThanOrEqualTo(String value) {
            addCriterion("if_main <=", value, "ifMain");
            return (Criteria) this;
        }

        public Criteria andIfMainLike(String value) {
            addCriterion("if_main like", value, "ifMain");
            return (Criteria) this;
        }

        public Criteria andIfMainNotLike(String value) {
            addCriterion("if_main not like", value, "ifMain");
            return (Criteria) this;
        }

        public Criteria andIfMainIn(List<String> values) {
            addCriterion("if_main in", values, "ifMain");
            return (Criteria) this;
        }

        public Criteria andIfMainNotIn(List<String> values) {
            addCriterion("if_main not in", values, "ifMain");
            return (Criteria) this;
        }

        public Criteria andIfMainBetween(String value1, String value2) {
            addCriterion("if_main between", value1, value2, "ifMain");
            return (Criteria) this;
        }

        public Criteria andIfMainNotBetween(String value1, String value2) {
            addCriterion("if_main not between", value1, value2, "ifMain");
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