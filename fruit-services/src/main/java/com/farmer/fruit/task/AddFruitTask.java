package com.farmer.fruit.task;

import com.farmer.fruit.excel.ExcelUtils;
import com.farmer.fruit.models.farmer.Reserved;
import com.farmer.fruit.models.fruit.Fruit;
import com.farmer.fruit.models.fruit.FruitInformation;
import com.farmer.fruit.models.fruit.FruitInformationQuery;
import com.farmer.fruit.persistence.fruit.IFruitInformationDao;
import com.farmer.fruit.queue.RedisQueue;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhi on 2016/7/14.
 */
public class AddFruitTask{
    private Reserved reserved;
    JedisPool jedisPool;
    IFruitInformationDao fruitInformationDao;
    public AddFruitTask(Reserved reserved){
        this.reserved = reserved;
    }

    public void run() {
        FruitInformationQuery query = new FruitInformationQuery();
        query.setFarmerId(reserved.getFarmerId());
        query.setType(reserved.getType());
        query.setBrandId(reserved.getBrandId().longValue());
        query.setVarietyId(reserved.getVarietyId().longValue());
        FruitInformation fruitInformation = fruitInformationDao.get(query);
        RedisQueue<Fruit> redisQueue = new RedisQueue<Fruit>(jedisPool,"fruit_queue",Fruit.class);
        List<Fruit> fruitList = null;
        String filePath = reserved.getPicturePath().substring(0, reserved.getPicturePath().lastIndexOf("/") + 1) + reserved.getId() + "/";
        File file = new File(filePath);
        File[] files = file.listFiles();
        try {
            fruitList = getFruitList(reserved.getFilePath());
            for(int i=0;i<fruitList.size();i++){
                Fruit fruit = fruitList.get(i);
                fruit.setBaseCode(fruitInformation.getId());
                fruit.setImage(files[i].getPath());
                fruit.setFruitCode(getFruitCode(reserved,reserved.getBegin()+i));
            }
            //更改状态
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<Fruit> getFruitList(String filePath) throws IOException {
        List<Map<String,Object>> fruitMapList = null;
        List<Fruit> fruitList = new ArrayList<Fruit>();
        ExcelUtils utils = new ExcelUtils();
        if (filePath.contains("xls")) {
            fruitMapList  =utils.loadExcel2003Info(filePath);
        } else {
            fruitMapList  =utils.loadExcel2007Info(filePath);
        }
        for(Map<String,Object> fm : fruitMapList){
            Fruit f = new Fruit();
            f.setWeight((Double)fm.get("ID1"));
            fruitList.add(f);
        }
        return fruitList;
    }
    private String getFruitCode(Reserved reserved,int index){
        //token+类型+年+10000000'
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return reserved.getToken()+reserved.getType()+year+(10000000+index);
    }

    public IFruitInformationDao getFruitInformationDao() {
        return fruitInformationDao;
    }

    public void setFruitInformationDao(IFruitInformationDao fruitInformationDao) {
        this.fruitInformationDao = fruitInformationDao;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
