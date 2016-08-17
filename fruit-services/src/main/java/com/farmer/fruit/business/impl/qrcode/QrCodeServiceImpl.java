package com.farmer.fruit.business.impl.qrcode;

import com.farmer.fruit.excel.ExcelUtils;
import com.farmer.fruit.file.CompressFile;
import com.farmer.fruit.interfaces.common.ICommonService;
import com.farmer.fruit.interfaces.fruit.IFruitService;
import com.farmer.fruit.interfaces.qrcode.IQrCodeService;
import com.farmer.fruit.models.farmer.Reserved;
import com.farmer.fruit.models.farmer.ReservedQuery;
import com.farmer.fruit.persistence.farmer.IReservedDao;
import com.farmer.fruit.persistence.fruit.IFruitInformationDao;
import com.farmer.fruit.task.AddFruitTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhi on 2016/7/1.
 */
@Service
@Transactional(readOnly = true)
public class QrCodeServiceImpl implements IQrCodeService {
    @Autowired
    IReservedDao reservedDao;
    @Autowired
    ICommonService commonService;
    @Autowired
    JedisPool jedisPool;
    @Autowired
    IFruitInformationDao fruitInformationDao;
    @Autowired
    ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    IFruitService fruitService;

    @Override
    public Reserved getById(Long id) {
        return reservedDao.getById(id);
    }

    /**
     * 获取最新的一条记录
     *
     * @param entity
     * @return
     */
    @Override
    public Reserved get(ReservedQuery entity) {
        return null;
    }

    @Override
    public int findListCount(ReservedQuery entity) {
        return reservedDao.getTotalCount(entity);
    }

    @Override
    public List<Reserved> findList(ReservedQuery entity, int pageNo, int pageSize) {
        entity.setPageNo(pageNo);
        entity.setPageSize(pageSize);
        return reservedDao.findList(entity);
    }

    @Override
    @Transactional(readOnly = false)
    public Long save(Reserved entity) {
        if (entity.isNewRecord()) {
            entity.setApplyTime(new Date());
            ReservedQuery query = new ReservedQuery();
            Reserved maxReserved = reservedDao.get(query);
            int end = 1;
            if (maxReserved != null) {
                end = maxReserved.getEnd();
            }
            entity.setBegin(end + 1);
            entity.setEnd(entity.getApplyCount() + end + 1);
            reservedDao.insert(entity);
            return entity.getId();
        } else {
            int count = reservedDao.update(entity);
            return new Long(count);
        }
    }

    @Override
    public void delete(ReservedQuery entity) {

    }

    @Override
    @Transactional(readOnly = false)
    public boolean createFruit(Reserved reserved, long farmerId) {
        List<Map<String, Object>> fruitMapList = null;
        ExcelUtils utils = new ExcelUtils();
        String fileSuffix = reserved.getFilePath().substring(reserved.getFilePath().lastIndexOf("."), reserved.getFilePath().length());
        try {
            if (".xls".equals(fileSuffix)) {
                fruitMapList = utils.loadExcel2003Info(reserved.getFilePath());
            } else {
                fruitMapList = utils.loadExcel2007Info(reserved.getFilePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        int length = fruitMapList.size();
        String picturePath = reserved.getPicturePath();
        String suffix = picturePath.substring(picturePath.lastIndexOf("."), picturePath.length());
        String filePath = picturePath.substring(0, picturePath.lastIndexOf("/") + 1) + reserved.getId() + "/";
        CompressFile compressFile = new CompressFile();
        if (".zip".equalsIgnoreCase(suffix)) {
            compressFile.unZipFiles(new File(picturePath), filePath);
        } else if (".rar".equalsIgnoreCase(suffix)) {
            compressFile.unRarFile(picturePath, filePath);
        }
        File file = new File(filePath);
        File[] files = file.listFiles();
        if (files == null) return false;
        if (files.length == length) {
            //保存上传信息
            save(reserved);
            reserved.setBrandName(commonService.getBrandName(reserved.getBrandId()));
            reserved.setTypeName(commonService.getFruitTypeName(reserved.getType()));
            reserved.setVarietyName(commonService.getVarietyName(reserved.getVarietyId()));
            System.out.println("begin pool size :" + taskExecutor.getPoolSize());
            AddFruitTask task = new AddFruitTask(reserved);
            task.setJedisPool(jedisPool);
            task.setFruitInformationDao(fruitInformationDao);
            task.setFruitService(fruitService);
            //task.run();
            taskExecutor.execute(task);
            System.out.println("end pool size :" + taskExecutor.getPoolSize());
            return true;
        } else {
            return false;
        }
    }


}
