package com.farmer.fruit.business.impl.common;

import com.farmer.fruit.interfaces.common.ICommonService;
import com.farmer.fruit.models.address.Address;
import com.farmer.fruit.models.brand.Brand;
import com.farmer.fruit.models.brand.BrandQuery;
import com.farmer.fruit.models.brand.Variety;
import com.farmer.fruit.models.brand.VarietyQuery;
import com.farmer.fruit.models.fruit.FruitType;
import com.farmer.fruit.persistence.common.ICommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhi on 2016/7/1.
 */
@Service
public class CommonServiceImpl implements ICommonService {
    @Autowired
    ICommonDao commonDao;
    @Override
    public List<Brand> getBrands(BrandQuery query) {
       return commonDao.getBrands(query);
    }

    @Override
    public List<Variety> getVarieties(VarietyQuery query) {
        return commonDao.getVarieties(query);
    }

    @Override
    public List<FruitType> getFruitType() {
        List<FruitType> types = new ArrayList<FruitType>();
        FruitType type = new FruitType();
        type.setCode("PG");
        type.setName("苹果");
        types.add(type);
        FruitType type1 = new FruitType();
        type1.setCode("XG");
        type1.setName("西瓜");
        types.add(type1);
        FruitType type2 = new FruitType();
        type2.setCode("L");
        type2.setName("脆梨");
        types.add(type2);
        return  types;
    }

    @Override
    public List<Address> getAddress(Address address) {
        return commonDao.getAddress(address);
    }
}
