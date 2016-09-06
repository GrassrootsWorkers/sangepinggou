package com.farmer.fruit.business.impl.common;

import com.farmer.fruit.interfaces.common.ICommonService;
import com.farmer.fruit.models.address.Address;
import com.farmer.fruit.models.brand.Brand;
import com.farmer.fruit.models.brand.BrandQuery;
import com.farmer.fruit.models.brand.Variety;
import com.farmer.fruit.models.brand.VarietyQuery;
import com.farmer.fruit.models.fruit.FruitType;
import com.farmer.fruit.persistence.common.ICommonDao;
import com.farmer.fruit.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhi on 2016/7/1.
 */
@Service
public class CommonServiceImpl implements ICommonService {
    @Autowired
    ICommonDao commonDao;
    @Autowired
    JedisPool jedisPool;
    @Override
    public List<Brand> getBrands(BrandQuery query) {
        List<Brand> brands = commonDao.getBrands(query);
        RedisUtils redisUtils = new RedisUtils(jedisPool);
        if(redisUtils.getFieldSize(Brand.CACHE_KEY)<=0){
            brands.forEach(n->redisUtils.setHKey(Brand.CACHE_KEY,n.getId().toString(),n.getName()));
        }
        return brands;
    }

    @Override
    public String getBrandName(Long brandId) {
        RedisUtils redisUtils = new RedisUtils(jedisPool);
        String brandName =  redisUtils.getHashValueByKey(Brand.CACHE_KEY,brandId.toString());
        if(brandName == null){
            getBrands(new BrandQuery() );
            brandName =  redisUtils.getHashValueByKey(Brand.CACHE_KEY,brandId.toString());
        }
        return brandName;
    }

    @Override
    public List<Variety> getVarieties(VarietyQuery query) {
        List<Variety> varieties = commonDao.getVarieties(query);
        RedisUtils redisUtils = new RedisUtils(jedisPool);
        if(redisUtils.getFieldSize(Variety.CACHE_KEY)<=0){
            varieties.forEach(n->redisUtils.setHKey(Variety.CACHE_KEY,n.getId().toString(),n.getName()));
        }
        return varieties;
    }

    @Override
    public String getVarietyName(Long varietyId) {
        RedisUtils redisUtils = new RedisUtils(jedisPool);
        String name =  redisUtils.getHashValueByKey(Variety.CACHE_KEY,varietyId.toString());
        if(name == null){
            getVarieties(new VarietyQuery());
            name =  redisUtils.getHashValueByKey(Variety.CACHE_KEY,varietyId.toString());
        }
        return name;
    }

    @Override
    public List<FruitType> getFruitType() {
      List<FruitType> fruitTypes =  commonDao.getFruitType();
        RedisUtils redisUtils = new RedisUtils(jedisPool);
        if(redisUtils.getFieldSize(FruitType.CACHE_KEY)<=0){
            fruitTypes.forEach(n->redisUtils.setHKey(FruitType.CACHE_KEY,n.getCode(),n.getName()));
        }
        return fruitTypes;
    }

    @Override
    public String getFruitTypeName(String typeCode) {
        RedisUtils redisUtils = new RedisUtils(jedisPool);
        String name = redisUtils.getHashValueByKey(FruitType.CACHE_KEY,typeCode);
        if(name == null ){
            getFruitType();
            name = redisUtils.getHashValueByKey(FruitType.CACHE_KEY,typeCode);
        }
        return name;
    }

    @Override
    public List<Address> getAddress(Address address) {
        return commonDao.getAddress(address);
    }
}
