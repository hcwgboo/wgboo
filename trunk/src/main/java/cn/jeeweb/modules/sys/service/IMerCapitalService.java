package cn.jeeweb.modules.sys.service;


import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.exception.ExceptionResultInfo;
import cn.jeeweb.core.query.data.Page;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.sys.dto.MerCapitalDto;
import cn.jeeweb.modules.sys.entity.MerCapital;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @Title: 上架资金
 * @Description: 上架资金
 * @author java
 * @date 2018-11-12 20:01:10
 * @version V1.0   
 *
 */
public interface IMerCapitalService extends ICommonService<MerCapital> {

    Page<MerCapitalDto> selectAdvRuleRelationPage(Queryable queryable, HttpServletRequest request);

    void merCapitalCharge(MerCapitalDto dto) throws ExceptionResultInfo;

}

