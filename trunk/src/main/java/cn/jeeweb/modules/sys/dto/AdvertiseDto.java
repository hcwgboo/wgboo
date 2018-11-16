package cn.jeeweb.modules.sys.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AdvertiseDto {

	/**主键*/
	private String id;
    /**商户id*/
	private String merchantId;
    /**广告标题*/
	private String title;
    /**奖励规则*/
	private String ruleMain;
    /**内容*/
	private String content;
    /**广告类别*/
	private String type;
    /**转发一次金额*/
	private BigDecimal forwardMoney;
    /**总金额*/
	private BigDecimal totalMoney;
    /**余额*/
	private BigDecimal balance;
    /**最小金额*/
	private BigDecimal minMoney;
    /**当前浏览次数*/
	private Integer nowViewTimes;
    /**当前分享次数*/
	private Integer nowShareTimes;
    /**上下架（0.下架，1.上架）*/
	private String releaseStatus;
    /**状态（0.创建，1.待审核，2.审核通过，3.拒绝）*/
	private String status;
    /**开放区域*/
	private String region;
    /**创建者*/
	private String createBy;
    /**创建时间*/
	private Date createDate;
    /**更新者*/
	private String updateBy;
    /**更新时间*/
	private Date updateDate;
    /**删除标记（0：正常；1：删除）*/
	private String delFlag;
    /**备注信息*/
	private String remarks;
	/**规则id*/
	private String ruleId;
	/**平台佣金*/
	private Double ratio;
	/**规则名称*/
	private String ruleName;
	/**规则类型1.转发，2.浏览*/
	private String ruleType;
	/**最小条数*/
	private Integer minSize;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRuleMain() {
		return ruleMain;
	}
	public void setRuleMain(String ruleMain) {
		this.ruleMain = ruleMain;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getForwardMoney() {
		return forwardMoney;
	}
	public void setForwardMoney(BigDecimal forwardMoney) {
		this.forwardMoney = forwardMoney;
	}
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getMinMoney() {
		return minMoney;
	}
	public void setMinMoney(BigDecimal minMoney) {
		this.minMoney = minMoney;
	}
	public Integer getNowViewTimes() {
		return nowViewTimes;
	}
	public void setNowViewTimes(Integer nowViewTimes) {
		this.nowViewTimes = nowViewTimes;
	}
	public Integer getNowShareTimes() {
		return nowShareTimes;
	}
	public void setNowShareTimes(Integer nowShareTimes) {
		this.nowShareTimes = nowShareTimes;
	}
	public String getReleaseStatus() {
		return releaseStatus;
	}
	public void setReleaseStatus(String releaseStatus) {
		this.releaseStatus = releaseStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public Integer getMinSize() {
		return minSize;
	}

	public void setMinSize(Integer minSize) {
		this.minSize = minSize;
	}
}
