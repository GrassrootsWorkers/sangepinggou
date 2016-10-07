package com.farmer.fruit.interfaces.lottery;

import com.farmer.fruit.models.fruit.Fruit;
import com.farmer.fruit.models.lottery.Lottery;

/**
 * Created by liuzhi on 2016/9/9.
 */
public interface ILotteryService {
    /**
     * 加购物车获取抽奖接口
     * @param fruit
     * @param mobile
     * @return
     */
    Lottery getCartLottery(String mobile,Fruit fruit);
}
