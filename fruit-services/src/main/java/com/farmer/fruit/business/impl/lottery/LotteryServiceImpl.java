package com.farmer.fruit.business.impl.lottery;

import com.farmer.fruit.interfaces.lottery.ILotteryService;
import com.farmer.fruit.interfaces.user.IUserService;
import com.farmer.fruit.models.fruit.Fruit;
import com.farmer.fruit.models.lottery.Lottery;
import com.farmer.fruit.models.user.User;
import com.farmer.fruit.models.user.UserQuery;
import com.farmer.fruit.utils.RandomStrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by liuzhi on 2016/9/9.
 */
@Service
public class LotteryServiceImpl implements ILotteryService {
    @Autowired
    IUserService<User,UserQuery> userService;
    @Override
    public Lottery getCartLottery(String mobile, Fruit fruit) {

        if(Lottery.LOTTERY_FLAG.equals(fruit.getLotteryFlag())){
            UserQuery query = new UserQuery();
            query.setMobile(mobile);
            User user = userService.get(query);
            Calendar calendar = new GregorianCalendar(Locale.CHINA);
            int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            calendar.setTime(user.getRegisterTime());
            int registerHour = calendar.get(Calendar.HOUR_OF_DAY);
            if((currentHour - registerHour)>1){
                return null;
            }else{
                Lottery lottery = new Lottery();
                lottery.setId(Lottery.DEFAULT_ID);
                lottery.setType(Lottery.LOTTERY_FLAG_A);
                int allowBuyCount = RandomStrUtil.getRandomInt(10);
                lottery.setAllowBuyCount(allowBuyCount);
                lottery.setLotteryDesc("恭喜您获得85折购买"+allowBuyCount+"个"+fruit.getTypeName()+"的机会");
                return lottery;
            }
        }else{
            return null;
        }

    }
    public static void main(String[]args){
        Calendar calendar = new GregorianCalendar(Locale.CHINA);
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
    }
}
