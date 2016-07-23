package com.farmer.fruit.interfaces.common;

import com.farmer.fruit.models.address.Address;
import com.farmer.fruit.models.brand.Brand;
import com.farmer.fruit.models.brand.BrandQuery;
import com.farmer.fruit.models.brand.Variety;
import com.farmer.fruit.models.brand.VarietyQuery;
import com.farmer.fruit.models.fruit.FruitType;

import java.util.List;

/**
 * Created by liuzhi on 2016/7/1.
 */
public interface ICommonService {
    /**
     * 查询品牌
     * @param query
     * @return
     */
    List<Brand> getBrands(BrandQuery query);

    /**
     * 获取基础数据
     * @param brandId
     * @return
     */
    String getBrandName(Long brandId);
    /**
     *查询品种
     */
    List<Variety> getVarieties(VarietyQuery query);

    /**
     * 获取基础数据名称
     * @param varietyId
     * @return
     */
    String getVarietyName(Long varietyId);
    /**
     * 查询水果类型
     */
    List<FruitType> getFruitType();

    /**
     * 获取基础数据编码
     * @param typeCode
     * @return
     */
    String getFruitTypeName(String typeCode);
    /**
     * 查询省市区
     * @param address
     * @return
     */
    List<Address> getAddress(Address address);
}
