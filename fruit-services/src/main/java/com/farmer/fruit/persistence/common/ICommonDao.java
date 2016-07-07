package com.farmer.fruit.persistence.common;

import com.farmer.fruit.models.address.Address;
import com.farmer.fruit.models.brand.Brand;
import com.farmer.fruit.models.brand.BrandQuery;
import com.farmer.fruit.models.brand.Variety;
import com.farmer.fruit.models.brand.VarietyQuery;
import com.farmer.fruit.models.fruit.FruitType;
import com.farmer.fruit.persistence.MyBatisDao;

import java.util.List;

/**
 * Created by liuzhi on 2016/7/1.
 */
@MyBatisDao
public interface ICommonDao {
    /**
     * 查询品牌
     * @param query
     * @return
     */
    List<Brand> getBrands(BrandQuery query);
    /**
     *查询品种
     */
    List<Variety> getVarieties(VarietyQuery query);
    /**
     * 查询水果类型
     */
    List<FruitType> getFruitType();

    /**
     * 查询省市区
     * @param address
     * @return
     */
    List<Address> getAddress(Address address);
}
