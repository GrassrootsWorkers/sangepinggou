package com.farmer.fruit.job.qr;

import com.farmer.fruit.models.farmer.Reserved;
import com.farmer.fruit.models.farmer.ReservedQuery;
import com.farmer.fruit.persistence.farmer.IReservedDao;
import com.farmer.fruit.utils.QRUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by liuzhi on 2016/10/3.
 */
public class CreateQrJobService {
    private static Logger log = LoggerFactory.getLogger(CreateQrJobService.class);
    @Autowired
    IReservedDao reservedDao;
    public  void execute(){
        ReservedQuery query = new ReservedQuery();
        query.setStatus(ReservedQuery.APPLIED);
        List<Reserved> reservedList = reservedDao.findAllList(query);
        log.info("-------------job begin:"+reservedList.size());
        for(Reserved r : reservedList){

            Long formerId = r.getFarmerId();
            String type = r.getType();
            int begin = r.getBegin();
            int end = r.getEnd();
            for(int i=begin;i<=end;i++){
                String fruitCode = QRUtil.getFruitCode(r.getToken(),type,i);
                QRUtil.createQr(r.getId(),fruitCode,formerId);
            }
            r.setStatus(ReservedQuery.WAIT_PRINT);
            reservedDao.update(r);
        }
        log.info("-------------job end:"+reservedList.size());
    }
}
