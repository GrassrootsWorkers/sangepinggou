package com.farmer.fruit.models.fruit;

import java.util.ArrayList;
import java.util.List;

public class FruitInformationQuery {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public FruitInformationQuery() {
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

        public Criteria andTypeEqualTo(Byte value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Byte value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Byte value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Byte value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Byte value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Byte> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Byte> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Byte value1, Byte value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andGrowthPeriodIsNull() {
            addCriterion("growth_period is null");
            return (Criteria) this;
        }

        public Criteria andGrowthPeriodIsNotNull() {
            addCriterion("growth_period is not null");
            return (Criteria) this;
        }

        public Criteria andGrowthPeriodEqualTo(Byte value) {
            addCriterion("growth_period =", value, "growthPeriod");
            return (Criteria) this;
        }

        public Criteria andGrowthPeriodNotEqualTo(Byte value) {
            addCriterion("growth_period <>", value, "growthPeriod");
            return (Criteria) this;
        }

        public Criteria andGrowthPeriodGreaterThan(Byte value) {
            addCriterion("growth_period >", value, "growthPeriod");
            return (Criteria) this;
        }

        public Criteria andGrowthPeriodGreaterThanOrEqualTo(Byte value) {
            addCriterion("growth_period >=", value, "growthPeriod");
            return (Criteria) this;
        }

        public Criteria andGrowthPeriodLessThan(Byte value) {
            addCriterion("growth_period <", value, "growthPeriod");
            return (Criteria) this;
        }

        public Criteria andGrowthPeriodLessThanOrEqualTo(Byte value) {
            addCriterion("growth_period <=", value, "growthPeriod");
            return (Criteria) this;
        }

        public Criteria andGrowthPeriodIn(List<Byte> values) {
            addCriterion("growth_period in", values, "growthPeriod");
            return (Criteria) this;
        }

        public Criteria andGrowthPeriodNotIn(List<Byte> values) {
            addCriterion("growth_period not in", values, "growthPeriod");
            return (Criteria) this;
        }

        public Criteria andGrowthPeriodBetween(Byte value1, Byte value2) {
            addCriterion("growth_period between", value1, value2, "growthPeriod");
            return (Criteria) this;
        }

        public Criteria andGrowthPeriodNotBetween(Byte value1, Byte value2) {
            addCriterion("growth_period not between", value1, value2, "growthPeriod");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceIsNull() {
            addCriterion("production_place is null");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceIsNotNull() {
            addCriterion("production_place is not null");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceEqualTo(String value) {
            addCriterion("production_place =", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceNotEqualTo(String value) {
            addCriterion("production_place <>", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceGreaterThan(String value) {
            addCriterion("production_place >", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("production_place >=", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceLessThan(String value) {
            addCriterion("production_place <", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceLessThanOrEqualTo(String value) {
            addCriterion("production_place <=", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceLike(String value) {
            addCriterion("production_place like", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceNotLike(String value) {
            addCriterion("production_place not like", value, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceIn(List<String> values) {
            addCriterion("production_place in", values, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceNotIn(List<String> values) {
            addCriterion("production_place not in", values, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceBetween(String value1, String value2) {
            addCriterion("production_place between", value1, value2, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceNotBetween(String value1, String value2) {
            addCriterion("production_place not between", value1, value2, "productionPlace");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceDescIsNull() {
            addCriterion("production_place_desc is null");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceDescIsNotNull() {
            addCriterion("production_place_desc is not null");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceDescEqualTo(String value) {
            addCriterion("production_place_desc =", value, "productionPlaceDesc");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceDescNotEqualTo(String value) {
            addCriterion("production_place_desc <>", value, "productionPlaceDesc");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceDescGreaterThan(String value) {
            addCriterion("production_place_desc >", value, "productionPlaceDesc");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceDescGreaterThanOrEqualTo(String value) {
            addCriterion("production_place_desc >=", value, "productionPlaceDesc");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceDescLessThan(String value) {
            addCriterion("production_place_desc <", value, "productionPlaceDesc");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceDescLessThanOrEqualTo(String value) {
            addCriterion("production_place_desc <=", value, "productionPlaceDesc");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceDescLike(String value) {
            addCriterion("production_place_desc like", value, "productionPlaceDesc");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceDescNotLike(String value) {
            addCriterion("production_place_desc not like", value, "productionPlaceDesc");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceDescIn(List<String> values) {
            addCriterion("production_place_desc in", values, "productionPlaceDesc");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceDescNotIn(List<String> values) {
            addCriterion("production_place_desc not in", values, "productionPlaceDesc");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceDescBetween(String value1, String value2) {
            addCriterion("production_place_desc between", value1, value2, "productionPlaceDesc");
            return (Criteria) this;
        }

        public Criteria andProductionPlaceDescNotBetween(String value1, String value2) {
            addCriterion("production_place_desc not between", value1, value2, "productionPlaceDesc");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNull() {
            addCriterion("company is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("company is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(String value) {
            addCriterion("company =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(String value) {
            addCriterion("company <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(String value) {
            addCriterion("company >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("company >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(String value) {
            addCriterion("company <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(String value) {
            addCriterion("company <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLike(String value) {
            addCriterion("company like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotLike(String value) {
            addCriterion("company not like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<String> values) {
            addCriterion("company in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<String> values) {
            addCriterion("company not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(String value1, String value2) {
            addCriterion("company between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(String value1, String value2) {
            addCriterion("company not between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andEnvironmentDescIsNull() {
            addCriterion("environment_desc is null");
            return (Criteria) this;
        }

        public Criteria andEnvironmentDescIsNotNull() {
            addCriterion("environment_desc is not null");
            return (Criteria) this;
        }

        public Criteria andEnvironmentDescEqualTo(String value) {
            addCriterion("environment_desc =", value, "environmentDesc");
            return (Criteria) this;
        }

        public Criteria andEnvironmentDescNotEqualTo(String value) {
            addCriterion("environment_desc <>", value, "environmentDesc");
            return (Criteria) this;
        }

        public Criteria andEnvironmentDescGreaterThan(String value) {
            addCriterion("environment_desc >", value, "environmentDesc");
            return (Criteria) this;
        }

        public Criteria andEnvironmentDescGreaterThanOrEqualTo(String value) {
            addCriterion("environment_desc >=", value, "environmentDesc");
            return (Criteria) this;
        }

        public Criteria andEnvironmentDescLessThan(String value) {
            addCriterion("environment_desc <", value, "environmentDesc");
            return (Criteria) this;
        }

        public Criteria andEnvironmentDescLessThanOrEqualTo(String value) {
            addCriterion("environment_desc <=", value, "environmentDesc");
            return (Criteria) this;
        }

        public Criteria andEnvironmentDescLike(String value) {
            addCriterion("environment_desc like", value, "environmentDesc");
            return (Criteria) this;
        }

        public Criteria andEnvironmentDescNotLike(String value) {
            addCriterion("environment_desc not like", value, "environmentDesc");
            return (Criteria) this;
        }

        public Criteria andEnvironmentDescIn(List<String> values) {
            addCriterion("environment_desc in", values, "environmentDesc");
            return (Criteria) this;
        }

        public Criteria andEnvironmentDescNotIn(List<String> values) {
            addCriterion("environment_desc not in", values, "environmentDesc");
            return (Criteria) this;
        }

        public Criteria andEnvironmentDescBetween(String value1, String value2) {
            addCriterion("environment_desc between", value1, value2, "environmentDesc");
            return (Criteria) this;
        }

        public Criteria andEnvironmentDescNotBetween(String value1, String value2) {
            addCriterion("environment_desc not between", value1, value2, "environmentDesc");
            return (Criteria) this;
        }

        public Criteria andBrandIdIsNull() {
            addCriterion("brand_id is null");
            return (Criteria) this;
        }

        public Criteria andBrandIdIsNotNull() {
            addCriterion("brand_id is not null");
            return (Criteria) this;
        }

        public Criteria andBrandIdEqualTo(Integer value) {
            addCriterion("brand_id =", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotEqualTo(Integer value) {
            addCriterion("brand_id <>", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThan(Integer value) {
            addCriterion("brand_id >", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("brand_id >=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThan(Integer value) {
            addCriterion("brand_id <", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThanOrEqualTo(Integer value) {
            addCriterion("brand_id <=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdIn(List<Integer> values) {
            addCriterion("brand_id in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotIn(List<Integer> values) {
            addCriterion("brand_id not in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdBetween(Integer value1, Integer value2) {
            addCriterion("brand_id between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotBetween(Integer value1, Integer value2) {
            addCriterion("brand_id not between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andVarietyIdIsNull() {
            addCriterion("variety_id is null");
            return (Criteria) this;
        }

        public Criteria andVarietyIdIsNotNull() {
            addCriterion("variety_id is not null");
            return (Criteria) this;
        }

        public Criteria andVarietyIdEqualTo(Integer value) {
            addCriterion("variety_id =", value, "varietyId");
            return (Criteria) this;
        }

        public Criteria andVarietyIdNotEqualTo(Integer value) {
            addCriterion("variety_id <>", value, "varietyId");
            return (Criteria) this;
        }

        public Criteria andVarietyIdGreaterThan(Integer value) {
            addCriterion("variety_id >", value, "varietyId");
            return (Criteria) this;
        }

        public Criteria andVarietyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("variety_id >=", value, "varietyId");
            return (Criteria) this;
        }

        public Criteria andVarietyIdLessThan(Integer value) {
            addCriterion("variety_id <", value, "varietyId");
            return (Criteria) this;
        }

        public Criteria andVarietyIdLessThanOrEqualTo(Integer value) {
            addCriterion("variety_id <=", value, "varietyId");
            return (Criteria) this;
        }

        public Criteria andVarietyIdIn(List<Integer> values) {
            addCriterion("variety_id in", values, "varietyId");
            return (Criteria) this;
        }

        public Criteria andVarietyIdNotIn(List<Integer> values) {
            addCriterion("variety_id not in", values, "varietyId");
            return (Criteria) this;
        }

        public Criteria andVarietyIdBetween(Integer value1, Integer value2) {
            addCriterion("variety_id between", value1, value2, "varietyId");
            return (Criteria) this;
        }

        public Criteria andVarietyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("variety_id not between", value1, value2, "varietyId");
            return (Criteria) this;
        }

        public Criteria andFarmerIdIsNull() {
            addCriterion("farmer_id is null");
            return (Criteria) this;
        }

        public Criteria andFarmerIdIsNotNull() {
            addCriterion("farmer_id is not null");
            return (Criteria) this;
        }

        public Criteria andFarmerIdEqualTo(Integer value) {
            addCriterion("farmer_id =", value, "farmerId");
            return (Criteria) this;
        }

        public Criteria andFarmerIdNotEqualTo(Integer value) {
            addCriterion("farmer_id <>", value, "farmerId");
            return (Criteria) this;
        }

        public Criteria andFarmerIdGreaterThan(Integer value) {
            addCriterion("farmer_id >", value, "farmerId");
            return (Criteria) this;
        }

        public Criteria andFarmerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("farmer_id >=", value, "farmerId");
            return (Criteria) this;
        }

        public Criteria andFarmerIdLessThan(Integer value) {
            addCriterion("farmer_id <", value, "farmerId");
            return (Criteria) this;
        }

        public Criteria andFarmerIdLessThanOrEqualTo(Integer value) {
            addCriterion("farmer_id <=", value, "farmerId");
            return (Criteria) this;
        }

        public Criteria andFarmerIdIn(List<Integer> values) {
            addCriterion("farmer_id in", values, "farmerId");
            return (Criteria) this;
        }

        public Criteria andFarmerIdNotIn(List<Integer> values) {
            addCriterion("farmer_id not in", values, "farmerId");
            return (Criteria) this;
        }

        public Criteria andFarmerIdBetween(Integer value1, Integer value2) {
            addCriterion("farmer_id between", value1, value2, "farmerId");
            return (Criteria) this;
        }

        public Criteria andFarmerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("farmer_id not between", value1, value2, "farmerId");
            return (Criteria) this;
        }

        public Criteria andStorageWayIsNull() {
            addCriterion("storage_way is null");
            return (Criteria) this;
        }

        public Criteria andStorageWayIsNotNull() {
            addCriterion("storage_way is not null");
            return (Criteria) this;
        }

        public Criteria andStorageWayEqualTo(String value) {
            addCriterion("storage_way =", value, "storageWay");
            return (Criteria) this;
        }

        public Criteria andStorageWayNotEqualTo(String value) {
            addCriterion("storage_way <>", value, "storageWay");
            return (Criteria) this;
        }

        public Criteria andStorageWayGreaterThan(String value) {
            addCriterion("storage_way >", value, "storageWay");
            return (Criteria) this;
        }

        public Criteria andStorageWayGreaterThanOrEqualTo(String value) {
            addCriterion("storage_way >=", value, "storageWay");
            return (Criteria) this;
        }

        public Criteria andStorageWayLessThan(String value) {
            addCriterion("storage_way <", value, "storageWay");
            return (Criteria) this;
        }

        public Criteria andStorageWayLessThanOrEqualTo(String value) {
            addCriterion("storage_way <=", value, "storageWay");
            return (Criteria) this;
        }

        public Criteria andStorageWayLike(String value) {
            addCriterion("storage_way like", value, "storageWay");
            return (Criteria) this;
        }

        public Criteria andStorageWayNotLike(String value) {
            addCriterion("storage_way not like", value, "storageWay");
            return (Criteria) this;
        }

        public Criteria andStorageWayIn(List<String> values) {
            addCriterion("storage_way in", values, "storageWay");
            return (Criteria) this;
        }

        public Criteria andStorageWayNotIn(List<String> values) {
            addCriterion("storage_way not in", values, "storageWay");
            return (Criteria) this;
        }

        public Criteria andStorageWayBetween(String value1, String value2) {
            addCriterion("storage_way between", value1, value2, "storageWay");
            return (Criteria) this;
        }

        public Criteria andStorageWayNotBetween(String value1, String value2) {
            addCriterion("storage_way not between", value1, value2, "storageWay");
            return (Criteria) this;
        }

        public Criteria andDeratingRateIsNull() {
            addCriterion("derating_rate is null");
            return (Criteria) this;
        }

        public Criteria andDeratingRateIsNotNull() {
            addCriterion("derating_rate is not null");
            return (Criteria) this;
        }

        public Criteria andDeratingRateEqualTo(Byte value) {
            addCriterion("derating_rate =", value, "deratingRate");
            return (Criteria) this;
        }

        public Criteria andDeratingRateNotEqualTo(Byte value) {
            addCriterion("derating_rate <>", value, "deratingRate");
            return (Criteria) this;
        }

        public Criteria andDeratingRateGreaterThan(Byte value) {
            addCriterion("derating_rate >", value, "deratingRate");
            return (Criteria) this;
        }

        public Criteria andDeratingRateGreaterThanOrEqualTo(Byte value) {
            addCriterion("derating_rate >=", value, "deratingRate");
            return (Criteria) this;
        }

        public Criteria andDeratingRateLessThan(Byte value) {
            addCriterion("derating_rate <", value, "deratingRate");
            return (Criteria) this;
        }

        public Criteria andDeratingRateLessThanOrEqualTo(Byte value) {
            addCriterion("derating_rate <=", value, "deratingRate");
            return (Criteria) this;
        }

        public Criteria andDeratingRateIn(List<Byte> values) {
            addCriterion("derating_rate in", values, "deratingRate");
            return (Criteria) this;
        }

        public Criteria andDeratingRateNotIn(List<Byte> values) {
            addCriterion("derating_rate not in", values, "deratingRate");
            return (Criteria) this;
        }

        public Criteria andDeratingRateBetween(Byte value1, Byte value2) {
            addCriterion("derating_rate between", value1, value2, "deratingRate");
            return (Criteria) this;
        }

        public Criteria andDeratingRateNotBetween(Byte value1, Byte value2) {
            addCriterion("derating_rate not between", value1, value2, "deratingRate");
            return (Criteria) this;
        }

        public Criteria andWaterRateIsNull() {
            addCriterion("water_rate is null");
            return (Criteria) this;
        }

        public Criteria andWaterRateIsNotNull() {
            addCriterion("water_rate is not null");
            return (Criteria) this;
        }

        public Criteria andWaterRateEqualTo(Byte value) {
            addCriterion("water_rate =", value, "waterRate");
            return (Criteria) this;
        }

        public Criteria andWaterRateNotEqualTo(Byte value) {
            addCriterion("water_rate <>", value, "waterRate");
            return (Criteria) this;
        }

        public Criteria andWaterRateGreaterThan(Byte value) {
            addCriterion("water_rate >", value, "waterRate");
            return (Criteria) this;
        }

        public Criteria andWaterRateGreaterThanOrEqualTo(Byte value) {
            addCriterion("water_rate >=", value, "waterRate");
            return (Criteria) this;
        }

        public Criteria andWaterRateLessThan(Byte value) {
            addCriterion("water_rate <", value, "waterRate");
            return (Criteria) this;
        }

        public Criteria andWaterRateLessThanOrEqualTo(Byte value) {
            addCriterion("water_rate <=", value, "waterRate");
            return (Criteria) this;
        }

        public Criteria andWaterRateIn(List<Byte> values) {
            addCriterion("water_rate in", values, "waterRate");
            return (Criteria) this;
        }

        public Criteria andWaterRateNotIn(List<Byte> values) {
            addCriterion("water_rate not in", values, "waterRate");
            return (Criteria) this;
        }

        public Criteria andWaterRateBetween(Byte value1, Byte value2) {
            addCriterion("water_rate between", value1, value2, "waterRate");
            return (Criteria) this;
        }

        public Criteria andWaterRateNotBetween(Byte value1, Byte value2) {
            addCriterion("water_rate not between", value1, value2, "waterRate");
            return (Criteria) this;
        }

        public Criteria andSugerRateIsNull() {
            addCriterion("suger_rate is null");
            return (Criteria) this;
        }

        public Criteria andSugerRateIsNotNull() {
            addCriterion("suger_rate is not null");
            return (Criteria) this;
        }

        public Criteria andSugerRateEqualTo(Byte value) {
            addCriterion("suger_rate =", value, "sugerRate");
            return (Criteria) this;
        }

        public Criteria andSugerRateNotEqualTo(Byte value) {
            addCriterion("suger_rate <>", value, "sugerRate");
            return (Criteria) this;
        }

        public Criteria andSugerRateGreaterThan(Byte value) {
            addCriterion("suger_rate >", value, "sugerRate");
            return (Criteria) this;
        }

        public Criteria andSugerRateGreaterThanOrEqualTo(Byte value) {
            addCriterion("suger_rate >=", value, "sugerRate");
            return (Criteria) this;
        }

        public Criteria andSugerRateLessThan(Byte value) {
            addCriterion("suger_rate <", value, "sugerRate");
            return (Criteria) this;
        }

        public Criteria andSugerRateLessThanOrEqualTo(Byte value) {
            addCriterion("suger_rate <=", value, "sugerRate");
            return (Criteria) this;
        }

        public Criteria andSugerRateIn(List<Byte> values) {
            addCriterion("suger_rate in", values, "sugerRate");
            return (Criteria) this;
        }

        public Criteria andSugerRateNotIn(List<Byte> values) {
            addCriterion("suger_rate not in", values, "sugerRate");
            return (Criteria) this;
        }

        public Criteria andSugerRateBetween(Byte value1, Byte value2) {
            addCriterion("suger_rate between", value1, value2, "sugerRate");
            return (Criteria) this;
        }

        public Criteria andSugerRateNotBetween(Byte value1, Byte value2) {
            addCriterion("suger_rate not between", value1, value2, "sugerRate");
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