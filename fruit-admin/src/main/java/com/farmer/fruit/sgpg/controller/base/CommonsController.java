package com.farmer.fruit.sgpg.controller.base;

import com.farmer.fruit.interfaces.common.ICommonService;
import com.farmer.fruit.models.address.Address;
import com.farmer.fruit.models.brand.Brand;
import com.farmer.fruit.models.brand.BrandQuery;
import com.farmer.fruit.models.brand.Variety;
import com.farmer.fruit.models.brand.VarietyQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by liuzhi on 2016/7/1.
 */
@Controller
@RequestMapping("/admin/commons")
public class CommonsController extends BaseAction {
    @Autowired
    ICommonService commonService;
    @RequestMapping(value = "address")
    @ResponseBody
    public List<Address> getAddress(Address address){
        return commonService.getAddress(address);
    }
    @RequestMapping(value = "brand")
    @ResponseBody
    public List<Brand> getBrand(BrandQuery query){
        return commonService.getBrands(query);
    }
    @RequestMapping(value = "variety")
    @ResponseBody
    public List<Variety> getBrand(VarietyQuery query){
        return commonService.getVarieties(query);
    }
}
