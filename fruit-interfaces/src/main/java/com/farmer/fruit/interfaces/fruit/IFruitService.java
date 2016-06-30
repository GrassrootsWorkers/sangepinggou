package com.farmer.fruit.interfaces.fruit;

import com.farmer.fruit.interfaces.ICurdService;
import com.farmer.fruit.models.BaseEntity;
import com.farmer.fruit.models.QueryDataEntity;

/**
 * Created by liuzhi on 2016/6/29.
 */
public interface IFruitService<T extends BaseEntity,Q extends QueryDataEntity> extends ICurdService<T, Q> {
}
