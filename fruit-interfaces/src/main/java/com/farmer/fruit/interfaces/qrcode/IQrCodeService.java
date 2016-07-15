package com.farmer.fruit.interfaces.qrcode;

import com.farmer.fruit.interfaces.ICurdService;
import com.farmer.fruit.models.farmer.Reserved;
import com.farmer.fruit.models.farmer.ReservedQuery;

/**
 * Created by liuzhi on 2016/7/1.
 */
public interface IQrCodeService extends ICurdService<Reserved,ReservedQuery> {
    /**
     * 生成水果记录
     * @param reserved
     * @param farmerId
     */
    boolean createFruit(Reserved reserved,long farmerId);

}
