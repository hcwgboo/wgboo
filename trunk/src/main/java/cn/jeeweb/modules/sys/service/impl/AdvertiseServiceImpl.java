package cn.jeeweb.modules.sys.service.impl;

import java.math.BigDecimal;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.modules.sys.entity.*;
import cn.jeeweb.modules.sys.mapper.AdvertiseMapper;
import cn.jeeweb.modules.sys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.sys.constants.DictConstants;
import cn.jeeweb.modules.sys.utils.UserUtils;

/**   
 * @Title: 广告
 * @Description: 广告
 * @author java
 * @date 2018-11-12 19:58:04
 * @version V1.0   
 *
 */
@Transactional
@Service("advertiseService")
public class AdvertiseServiceImpl  extends CommonServiceImpl<AdvertiseMapper, Advertise> implements IAdvertiseService {

	@Autowired
	private IAdvertiseLogService advertiseLogService;
	@Autowired
	private IMerCapitalService merCapitalService;
	@Autowired
	private IMerCapitalDetailService merCapitalDetailService;
	@Autowired
	private IAdvertiseRuleService advertiseRuleService;

	@Override
	public void insertAdvertise(Advertise advertise) {
		User user = UserUtils.getUser();
		if(user == null ) throw new RuntimeException("用户不存在");
		// 参数校验
		checkAdvertiseParam(advertise);

		advertise.setMerchantId(user.getId());
		advertise.setReleaseStatus(DictConstants.GGSXJ_DICT_VALUE_0);
		advertise.setStatus(DictConstants.GGSH_DICT_VALUE_0);

		baseMapper.insert(advertise);
	}

	// 参数校验
	private void checkAdvertiseParam(Advertise advertise) {
		if (advertise == null ) throw new RuntimeException("系统错误");
		if (StringUtils.isEmpty(advertise.getTitle())) throw new RuntimeException("标题不能为空");
		if (StringUtils.isEmpty(advertise.getContent())) throw new RuntimeException("内容不能为空");
		if (advertise.getTranspond() == null || advertise.getTranspond() == 0)
			throw new RuntimeException("分享次数不能为空");
		if (advertise.getForwardMoney() == null)
			throw new RuntimeException("转发金额不能为空");

		if(StringUtils.isEmpty(advertise.getRuleId())) throw new RuntimeException("规则不存在");
		AdvertiseRule rule = advertiseRuleService.selectById(advertise.getRuleId());
		if(rule == null) throw new RuntimeException("规则不存在");

		// 总金额验证，规则验证 分享一次的金额，最少金额相比较
		BigDecimal forwordMoney = advertise.getForwardMoney();
		if(forwordMoney.compareTo(rule.getMinMoney()) < 0)
			throw new RuntimeException("转发金额小于最小分享金额");
		if(advertise.getTotalMoney().compareTo(new BigDecimal(0)) < 0)
			throw new RuntimeException("分享总金额不得小于0元");
		if(advertise.getTranspond() < rule.getMinSize())
			throw new RuntimeException("转发次数小于最小转发次数");
		BigDecimal minTotal = rule.getMinMoney().multiply(new BigDecimal(rule.getMinSize()));
		if(minTotal.compareTo(advertise.getTotalMoney()) < 0)
			throw new RuntimeException("总金额小于最少转发金额");

		advertise.setMinMoney(rule.getMinMoney());
		advertise.setMinSize(rule.getMinSize());
		advertise.setRuleType(rule.getType());
		advertise.setRuleName(rule.getName());
		advertise.setRatio(rule.getRatio());
		advertise.setType(rule.getTaskType());
		advertise.setReduceMoney(advertise.getTotalMoney().multiply(new BigDecimal(rule.getRatio())));
	}

	@Override
	public void updateAdvertise(Advertise advertise) {
		checkAdvertiseParam(advertise);
		baseMapper.updateById(advertise);
	}

	@Override
	public void submitCheckAdv(String id) {
		if(StringUtils.isEmpty(id)) throw new RuntimeException("参数错误");
		Advertise advertise = baseMapper.selectById(id);
		if (advertise == null) throw new RuntimeException("参数错误");
		if (!DictConstants.GGSH_DICT_VALUE_0.equals(advertise.getStatus())
				|| !DictConstants.GGSH_DICT_VALUE_3.equals(advertise.getStatus()))
			throw new RuntimeException("不可以提交审核");
		advertise.setStatus(DictConstants.GGSH_DICT_VALUE_1);
		baseMapper.updateById(advertise);

		// 资金
		User user = UserUtils.getUser();
		if(user == null ) throw new RuntimeException("用户不存在");
		MerCapital merCapital = merCapitalService.selectOne(new EntityWrapper<MerCapital>()
				.eq("merchant_id", user.getId()));
		if (merCapital.getBalanceMoney().compareTo(advertise.getTotalMoney()) < 0)
			throw new RuntimeException("余额不足");
		BigDecimal balance = merCapital.getBalanceMoney().subtract(advertise.getTotalMoney());
		merCapital.setBalanceMoney(balance);
		BigDecimal freezeMoney = merCapital.getFreezeMoney().add(advertise.getTotalMoney());
		merCapital.setFreezeMoney(freezeMoney);
		merCapitalService.updateById(merCapital);

		// 插入日志
		insertAdvertiseLog(advertise);
	}

	private void insertAdvertiseLog(Advertise advertise) {
		AdvertiseLog log = new AdvertiseLog();
		log.setAdvId(advertise.getId());
		log.setStatus(advertise.getStatus());
		log.setRelease(advertise.getReleaseStatus());
		advertiseLogService.insert(log);
	}

	@Override
	public void checkSuccessAdv(String id)  {
		if (StringUtils.isEmpty(id)) throw new RuntimeException("参数错误");
		Advertise advertise = baseMapper.selectById(id);
		if (advertise == null) throw new RuntimeException("参数错误");
		if (!DictConstants.GGSH_DICT_VALUE_1.equals(advertise.getStatus()))
			throw new RuntimeException("不在待审状态");

		advertise.setStatus(DictConstants.GGSH_DICT_VALUE_2);
		advertise.setReleaseStatus(DictConstants.GGSXJ_DICT_VALUE_1);
		baseMapper.updateById(advertise);

		// 资金
		User user = UserUtils.getUser();
		if(user == null ) throw new RuntimeException("用户不存在");
		MerCapital merCapital = merCapitalService.selectOne(new EntityWrapper<MerCapital>()
				.eq("merchant_id", user.getId()));

		BigDecimal freezeMoney = merCapital.getFreezeMoney().subtract(advertise.getTotalMoney());
		merCapital.setFreezeMoney(freezeMoney);
		merCapitalService.updateById(merCapital);

		AdvertiseRule rule = advertiseRuleService.selectById(advertise.getRuleId());

		// 资金明细
		MerCapitalDetail capitalDetail = new MerCapitalDetail();
		capitalDetail.setMerchantId(user.getId());
		capitalDetail.setCapitalId(merCapital.getId());
		capitalDetail.setConsumeId(advertise.getId());
		capitalDetail.setMoney(advertise.getTotalMoney());
		capitalDetail.setResidueMoney(advertise.getTotalMoney().multiply(new BigDecimal(1- rule.getRatio())));	// TODO 扣除平台佣金
		capitalDetail.setType(DictConstants.SJZJMX_DICT_VALUE_0);
		merCapitalDetailService.insert(capitalDetail);

		// TODO 平台资金



		// 插入日志
		insertAdvertiseLog(advertise);
	}

	@Override
	public void checkFailAdv(String id) {
		if (StringUtils.isEmpty(id)) throw new RuntimeException("参数错误");
		Advertise advertise = baseMapper.selectById(id);
		if (advertise == null) throw new RuntimeException("参数错误");
		if (!DictConstants.GGSH_DICT_VALUE_1.equals(advertise.getStatus()))
			throw new RuntimeException("不在待审状态");
		advertise.setStatus(DictConstants.GGSH_DICT_VALUE_3);
		baseMapper.updateById(advertise);

		// 资金
		User user = UserUtils.getUser();
		if(user == null ) throw new RuntimeException("用户不存在");
		MerCapital merCapital = merCapitalService.selectOne(new EntityWrapper<MerCapital>()
				.eq("merchant_id", user.getId()));

		BigDecimal balanceMoney = merCapital.getBalanceMoney().add(advertise.getTotalMoney());
		merCapital.setBalanceMoney(balanceMoney);
		BigDecimal freezeMoney = merCapital.getFreezeMoney().subtract(advertise.getTotalMoney());
		merCapital.setFreezeMoney(freezeMoney);
		merCapitalService.updateById(merCapital);

		// 插入日志
		insertAdvertiseLog(advertise);
	}

}
