package cn.jeeweb.modules.sys.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class MerCapitalDto implements Serializable {

    /**主键*/
    private String id;
    /**商家id*/
    private String merchantId;
    /**商家name*/
    private String merchantName;
    /**账户余额*/
    private BigDecimal balanceMoney;
    /**冻结金额*/
    private BigDecimal freezeMoney;

    private String chargeMoney;

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

    public BigDecimal getBalanceMoney() {
        return balanceMoney;
    }

    public void setBalanceMoney(BigDecimal balanceMoney) {
        this.balanceMoney = balanceMoney;
    }

    public BigDecimal getFreezeMoney() {
        return freezeMoney;
    }

    public void setFreezeMoney(BigDecimal freezeMoney) {
        this.freezeMoney = freezeMoney;
    }

    public String getChargeMoney() {
        return chargeMoney;
    }

    public void setChargeMoney(String chargeMoney) {
        this.chargeMoney = chargeMoney;
    }
}
