package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.exception.ExceptionResultInfo;
import cn.jeeweb.core.query.data.Page;
import cn.jeeweb.core.query.data.PageImpl;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.parse.QueryToWrapper;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.sys.constants.DictConstants;
import cn.jeeweb.modules.sys.dto.MerCapitalDto;
import cn.jeeweb.modules.sys.entity.MerCapital;
import cn.jeeweb.modules.sys.entity.MerCapitalDetail;
import cn.jeeweb.modules.sys.mapper.MerCapitalMapper;
import cn.jeeweb.modules.sys.service.IMerCapitalDetailService;
import cn.jeeweb.modules.sys.service.IMerCapitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

/**   
 * @Title: 上架资金
 * @Description: 上架资金
 * @author java
 * @date 2018-11-12 20:01:10
 * @version V1.0   
 *
 */
@Transactional(rollbackFor = Exception.class)
@Service("merCapitalService")
public class MerCapitalServiceImpl  extends CommonServiceImpl<MerCapitalMapper, MerCapital> implements IMerCapitalService {

    @Autowired
    private IMerCapitalDetailService merCapitalDetailService;

    @Override
    public Page<MerCapitalDto> selectAdvRuleRelationPage(Queryable queryable, HttpServletRequest request) {
        QueryToWrapper<MerCapitalDto> queryToWrapper = new QueryToWrapper<>();
        Map<String, Object> map = queryToWrapper.conditionToMap(queryable);

        Pageable pageable = queryable.getPageable();
        com.baomidou.mybatisplus.plugins.Page<MerCapitalDto> page = new com.baomidou.mybatisplus.plugins.Page<>(
                pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(baseMapper.selectMerCaptialPage(page, map));
        return new PageImpl<>(page.getRecords(), queryable.getPageable(), page.getTotal());
    }

    @Override
    public void merCapitalCharge(MerCapitalDto dto) throws ExceptionResultInfo {
        if(StringUtils.isEmpty(dto.getId())) throw new ExceptionResultInfo("参数错误");
        if(StringUtils.isEmpty(dto.getChargeMoney())) throw new ExceptionResultInfo("参数错误");
        MerCapital merCapital = baseMapper.selectById(dto.getId());
        if(merCapital == null) throw new ExceptionResultInfo("参数错误");
        BigDecimal money = new BigDecimal(dto.getChargeMoney());
        if(money.compareTo(new BigDecimal(0)) < 0) throw new ExceptionResultInfo("充值金额不得小于0");

        merCapital.setBalanceMoney(merCapital.getBalanceMoney().add(money));
        baseMapper.updateById(merCapital);

        // 资金详情
        addMerCapitalDetail(merCapital, money);
    }

    private void addMerCapitalDetail(MerCapital merCapital, BigDecimal money){
        MerCapitalDetail detail = new MerCapitalDetail();
        detail.setCapitalId(merCapital.getId());
        detail.setMerchantId(merCapital.getMerchantId());
        detail.setType(DictConstants.SJZJMX_DICT_VALUE_1);
        detail.setMoney(money);
        merCapitalDetailService.insert(detail);
    }
}
