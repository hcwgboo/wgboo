package cn.jeeweb.modules.sys.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.exception.ExceptionResultInfo;
import cn.jeeweb.modules.sys.entity.MerchantRegister;

/**   
 * @Title: 商家注册
 * @Description: 商家注册
 * @author cql
 * @date 2018-11-24 10:55:30
 * @version V1.0   
 *
 */
public interface IMerchantRegisterService extends ICommonService<MerchantRegister> {

    /**
     * 商家审核通过
     * @param id
     * @throws ExceptionResultInfo
     */
    void checkMerSuccess(String id) throws ExceptionResultInfo;

    /**
     * 商家审核不通过
     * @param id
     * @throws ExceptionResultInfo
     */
    void checkMerFail(String id) throws ExceptionResultInfo;
}

