package com.farmer.fruit.task;

import com.farmer.fruit.commons.pictures.FruitPictureUtil;
import com.farmer.fruit.excel.ExcelUtils;
import com.farmer.fruit.interfaces.fruit.IFruitService;

import com.farmer.fruit.models.farmer.Reserved;
import com.farmer.fruit.models.fruit.Fruit;
import com.farmer.fruit.models.fruit.FruitInformation;
import com.farmer.fruit.models.fruit.FruitInformationQuery;
import com.farmer.fruit.persistence.fruit.IFruitInformationDao;
import com.farmer.fruit.queue.RedisQueue;
import com.farmer.fruit.utils.QRUtil;
import com.farmer.fruit.utils.StringUtils;
import redis.clients.jedis.JedisPool;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by liuzhi on 2016/7/14.
 */
public class AddFruitTask implements Runnable {

    private Reserved reserved;

    private JedisPool jedisPool;

    private IFruitInformationDao fruitInformationDao;

    private IFruitService fruitService;

    public AddFruitTask(Reserved reserved) {
        this.reserved = reserved;
    }

    @Override
    public void run() {
        //先建立 品牌，品种的基础信息才能上传
        FruitInformationQuery query = new FruitInformationQuery();
        query.setFarmerId(reserved.getFarmerId());
        query.setType(reserved.getType());
        query.setBrandId(reserved.getBrandId());
        query.setVarietyId(reserved.getVarietyId());
        FruitInformation fruitInformation = fruitInformationDao.get(query);

        RedisQueue<Fruit> redisQueue = new RedisQueue<Fruit>(jedisPool, "fruit_queue", Fruit.class);
        List<Fruit> fruitList;
        String filePath = reserved.getPicturePath().substring(0, reserved.getPicturePath().lastIndexOf("/") + 1) + reserved.getId() + "/";
        File file = new File(filePath);
        File[] files = file.listFiles();
        try {
            fruitList = getFruitList(reserved.getFilePath());
            for (int i = 0; i < fruitList.size(); i++) {
                Fruit fruit = fruitList.get(i);
                fruit.setFarmerId(reserved.getFarmerId());
                fruit.setBaseCode(fruitInformation.getId());
                String origImage;
                if(Reserved.P_FLAGE.equals(reserved.getPictureFlag())){
                    origImage = files[0].getPath();
                }else{
                    origImage = files[i].getPath();
                }
                fruit.setOrigImage(origImage);

                if(fruit.getFruitCode() == null || "".equals(fruit.getFruitCode())){
                    if(reserved.getBegin()+i >reserved.getEnd()) break;
                    String fruitCode = QRUtil.getFruitCode(reserved.getToken(),reserved.getType(), reserved.getBegin() + i);
                    fruit.setFruitCode(fruitCode);
                }

                fruit.setAddTime(new Date());
                fruit.setAddUserId(reserved.getFarmerId());
                fruit.setCreateTime(new Date());
                fruit.setCheckoutUserId(reserved.getId());
                fruit.setNewRecord(true);
                fruit.setType(reserved.getType());
                fruit.setBrandId(reserved.getBrandId());
                fruit.setVarietyId(reserved.getVarietyId());
                fruit.setTypeName(reserved.getTypeName());
                fruit.setBrandName(reserved.getBrandName());
                fruit.setVarietyName(reserved.getVarietyName());
                fruit.setHarvestTime(reserved.getHarvestTime());
                fruit.setMarketPrice(reserved.getMarketPrice());
                fruit.setReservedId(reserved.getId());
                fruit.setUnit(reserved.getUnit());
                fruit.setFilePath("http://m.sangepg.com/fruit/" + reserved.getFarmerId() + "/" + StringUtils.getYear() + "/"+reserved.getId()+"/" );
                long fruitId = fruitService.save(fruit);

                fruit.setBaseInfoPath("/block/" + fruitInformation.getFarmerId() + "/" + fruitInformation.getFarmerId() + "_" + fruitInformation.getType() + "_" + fruitInformation.getId() + ".html");
                //生成图片
                createPicture(fruitId, fruit.getOrigImage());
                //生成二维码--审核通过时生成
                //createQr(fruitId, fruit.getFruitCode(), reserved.getFarmerId());
                //放入redis中 利用生产者消费者生成静态文件
                redisQueue.add(fruit);
            }
            //更改状态
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Fruit> getFruitList(String filePath) throws IOException {
        List<Map<String, Object>> fruitMapList = null;
        List<Fruit> fruitList = new ArrayList<Fruit>();
        if (".xls".equals(filePath.substring(filePath.lastIndexOf(".") , filePath.length()))) {
            fruitMapList = ExcelUtils.loadExcel2003Info(filePath);
        } else if (".csv".equals(filePath.substring(filePath.lastIndexOf(".") , filePath.length()))) {
            fruitMapList = ExcelUtils.loadCVSInfo(filePath);
        } else {
            fruitMapList = ExcelUtils.loadExcel2007Info(filePath);
        }
        for (Map<String, Object> fm : fruitMapList) {
            Fruit f = new Fruit();
            Double weight = null;
            String qrUrl = (String)fm.get("ID2");
            Map<String,Object> splitUrlMap =  splitQrUrl(qrUrl);
           if(splitUrlMap != null){
               f.setFarmerId((Long)splitUrlMap.get("farmerId"));
               f.setReservedId((Long)splitUrlMap.get("reservedId"));
               f.setFruitCode((String)splitUrlMap.get("fruitCode"));
               String weightStr =  (String)fm.get("ID3");
               weight = Double.parseDouble(weightStr);
           }else{
               weight = (Double) fm.get("ID1");
           }
            //ke -->g
            f.setWeight(weight*1000);
            fruitList.add(f);
        }
        return fruitList;
    }

    private Map<String,Object> splitQrUrl(String url){
        try{
            if(url == null || url.indexOf("http") < 0){
                return null;
            }
            url = url.replace("http://","");
            String[] contents = url.split("/");
            Map<String,Object> contentMap = new HashMap<>();
            contentMap.put("farmerId",Long.parseLong(contents[2]));
            contentMap.put("reservedId",Long.parseLong(contents[4]));
            String fruitCode =contents[5];
            contentMap.put("fruitCode",fruitCode.substring(0,fruitCode.lastIndexOf(".")));
            return contentMap;
        }catch (Exception e){
            return null;
        }


    }


    private boolean createPicture(long fruitId, String sourceFilePath) {
        try {
            FruitPictureUtil.getMainPictureUrl(fruitId, sourceFilePath);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public IFruitInformationDao getFruitInformationDao() {
        return fruitInformationDao;
    }

    public void setFruitInformationDao(IFruitInformationDao fruitInformationDao) {
        this.fruitInformationDao = fruitInformationDao;
    }

    public IFruitService getFruitService() {
        return fruitService;
    }

    public void setFruitService(IFruitService fruitService) {
        this.fruitService = fruitService;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
