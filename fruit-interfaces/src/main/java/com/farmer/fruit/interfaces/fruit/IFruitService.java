package com.farmer.fruit.interfaces.fruit;

import com.farmer.fruit.interfaces.ICurdService;
import com.farmer.fruit.models.fruit.Fruit;
import com.farmer.fruit.models.fruit.FruitQuery;

/**
 * Created by liuzhi on 2016/6/29.
 */
public interface IFruitService extends ICurdService<Fruit, FruitQuery> {
    /**
     * 获取单条数据
     * @param fruitCode
     * @return
     */
    Fruit getByCode(String fruitCode);
}
