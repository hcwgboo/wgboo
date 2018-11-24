package cn.jeeweb.modules.sys.dto;

import java.math.BigDecimal;

public class AdvertiseRuleRelationDto {

    /**主键*/
    private String id;
    /**商户id*/
    private String merchantId;
    /**商户名称*/
    private String merchantName;
    /**规则id*/
    private String advRuleId;
    /**规则名称*/
    private String advRuleName;
    /**平台佣金占比*/
    private Double ratio;
    /**上级分销佣金占比*/
    private Double superiorCommissionRatio;
    /**下级分销佣金占比*/
    private Double subCommissionRatio;
    /**最低金额*/
    private BigDecimal minMoney;
    /**最少条数*/
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

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getAdvRuleId() {
        return advRuleId;
    }

    public void setAdvRuleId(String advRuleId) {
        this.advRuleId = advRuleId;
    }

    public String getAdvRuleName() {
        return advRuleName;
    }

    public void setAdvRuleName(String advRuleName) {
        this.advRuleName = advRuleName;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Double getSuperiorCommissionRatio() {
        return superiorCommissionRatio;
    }

    public void setSuperiorCommissionRatio(Double superiorCommissionRatio) {
        this.superiorCommissionRatio = superiorCommissionRatio;
    }

    public Double getSubCommissionRatio() {
        return subCommissionRatio;
    }

    public void setSubCommissionRatio(Double subCommissionRatio) {
        this.subCommissionRatio = subCommissionRatio;
    }

    public BigDecimal getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(BigDecimal minMoney) {
        this.minMoney = minMoney;
    }

    public Integer getMinSize() {
        return minSize;
    }

    public void setMinSize(Integer minSize) {
        this.minSize = minSize;
    }
}
