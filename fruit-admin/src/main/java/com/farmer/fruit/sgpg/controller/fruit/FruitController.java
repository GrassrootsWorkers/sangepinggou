package com.farmer.fruit.sgpg.controller.fruit;

import com.farmer.fruit.interfaces.farmer.IFarmerService;
import com.farmer.fruit.interfaces.fruit.IFruitService;
import com.farmer.fruit.models.farmer.Farmer;
import com.farmer.fruit.models.farmer.FarmerQuery;
import com.farmer.fruit.models.fruit.Fruit;
import com.farmer.fruit.models.fruit.FruitQuery;
import com.farmer.fruit.sgpg.controller.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhi on 2016/6/30.
 */
@Controller()
@RequestMapping(value = "/admin/fruit")
public class FruitController extends BaseAction {
    @Autowired
    IFruitService fruitService;
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toUserCenter(FruitQuery query, HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        Farmer farmer = null;
        if (query.getFarmerId() == null) {
            String mobile = this.getCookieValue("qr_un");
            FarmerQuery farmerQuery = new FarmerQuery();
            farmerQuery.setMobile(mobile);
            farmer = farmerService.get(farmerQuery);
            Long farmerId = getFarmerId(mobile);
            if (farmerId == null) farmer = null;
        } else {
            farmer = farmerService.getById(query.getFarmerId());
            Long farmerId = getFarmerId(farmer.getMobile());
            if (farmerId == null || query.getFarmerId().longValue() != farmerId.longValue()) {
                farmer = null;
            }
        }
        if (farmer == null) {
            RedirectView redirectView = new RedirectView("/jsp/user/user_login.html");
            ModelAndView view = new ModelAndView(redirectView);
            return view;
        } else {
            query.setAddFarmerId(farmer.getId());
            int count = fruitService.findListCount(query);
            query.setCount(count);
            query.setEndDate(new Date());
            List<Fruit> fruitList = fruitService.findList(query, query.getPageNo(), query.getPageSize());
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("fruits", fruitList);
            dataMap.put("pageIndex", query.getPageIndex());
            dataMap.put("pageCount", query.getTotalPage());
            dataMap.put("count", count);
            dataMap.put("query", query);
            ModelAndView view = new ModelAndView("/usercenter/farmer_fruit_list", dataMap);
            return view;

        }

    }
}
