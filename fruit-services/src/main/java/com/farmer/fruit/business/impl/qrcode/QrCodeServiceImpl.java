package com.farmer.fruit.business.impl.qrcode;

import com.farmer.fruit.interfaces.qrcode.IQrCodeService;
import com.farmer.fruit.models.farmer.Reserved;
import com.farmer.fruit.models.farmer.ReservedQuery;
import com.farmer.fruit.persistence.farmer.IReservedDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by liuzhi on 2016/7/1.
 */
@Service
@Transactional(readOnly=true)
public class QrCodeServiceImpl implements IQrCodeService {
    @Autowired
    IReservedDao reservedDao;
    @Override
    public Reserved getById(Long id) {
        return reservedDao.getById(id);
    }

    /**
     * 获取最新的一条记录
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
    @Transactional(readOnly=false)
    public Long save(Reserved entity) {
        if (entity.isNewRecord()) {
            entity.setApplyTime(new Date());
            ReservedQuery query = new ReservedQuery();
            Reserved maxReserved = reservedDao.get(query);
            int end =1;
            if(maxReserved != null){
              end =maxReserved.getEnd();
            }
            entity.setBegin(end+1);
            entity.setEnd(entity.getApplyCount()+end+1);
            reservedDao.insert(entity);
            return entity.getId();
        }else{
            int count = reservedDao.update(entity);
            return  new Long(count);
        }
    }

    @Override
    public void delete(ReservedQuery entity) {

    }
}
