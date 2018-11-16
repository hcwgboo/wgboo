package cn.jeeweb.modules.sys.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import cn.jeeweb.core.common.entity.AbstractEntity;

/**   
 * @Title: 商家资金明细
 * @Description: 商家资金明细
 * @author java
 * @date 2018-11-12 20:01:38
 * @version V1.0   
 *
 */
@TableName("mer_capital_detail")
@SuppressWarnings("serial")
public class MerCapitalDetail extends AbstractEntity<String> {

    /**主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**商家id*/
    @TableField(value = "merchant_id")
	private String merchantId;
    /**资金表id*/
    @TableField(value = "capital_id")
	private String capitalId;
    /**消费id（广告）*/
    @TableField(value = "consume_id")
	private String consumeId;
    /**金额*/
    @TableField(value = "money")
	private BigDecimal money;
    /**剩余金额*/
    @TableField(value = "residue_money")
	private BigDecimal residueMoney;
    /**类型(0.广告消费，1.充值, 2.升级，3.提现)*/
    @TableField(value = "type")
	private String type;
    /**创建者*/
    @TableField(value = "create_by",fill = FieldFill.INSERT)
	private String createBy;
    /**创建时间*/
    @TableField(value = "create_date",fill = FieldFill.INSERT)
	private Date createDate;
    /**更新者*/
    @TableField(value = "update_by",fill = FieldFill.INSERT_UPDATE)
	private String updateBy;
    /**更新时间*/
    @TableField(value = "update_date",fill = FieldFill.INSERT_UPDATE)
	private Date updateDate;
    /**删除标记（0：正常；1：删除）*/
    @TableField(value = "del_flag")
	private String delFlag;
    /**备注信息*/
    @TableField(value = "remarks")
	private String remarks;
	
	/**
	 * 获取  id
	 *@return: String  主键
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  主键
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  merchantId
	 *@return: String  商家id
	 */
	public String getMerchantId(){
		return this.merchantId;
	}

	/**
	 * 设置  merchantId
	 *@param: merchantId  商家id
	 */
	public void setMerchantId(String merchantId){
		this.merchantId = merchantId;
	}
	/**
	 * 获取  capitalId
	 *@return: String  资金表id
	 */
	public String getCapitalId(){
		return this.capitalId;
	}

	/**
	 * 设置  capitalId
	 *@param: capitalId  资金表id
	 */
	public void setCapitalId(String capitalId){
		this.capitalId = capitalId;
	}
	/**
	 * 获取  consumeId
	 *@return: String  消费id（广告）
	 */
	public String getConsumeId(){
		return this.consumeId;
	}

	/**
	 * 设置  consumeId
	 *@param: consumeId  消费id（广告）
	 */
	public void setConsumeId(String consumeId){
		this.consumeId = consumeId;
	}
	/**
	 * 获取  money
	 *@return: Double  金额
	 */
	public BigDecimal getMoney(){
		return this.money;
	}

	/**
	 * 设置  money
	 *@param: money  金额
	 */
	public void setMoney(BigDecimal money){
		this.money = money;
	}
	/**
	 * 获取  residueMoney
	 *@return: String  剩余金额
	 */
	public BigDecimal getResidueMoney(){
		return this.residueMoney;
	}

	/**
	 * 设置  residueMoney
	 *@param: residueMoney  剩余金额
	 */
	public void setResidueMoney(BigDecimal residueMoney){
		this.residueMoney = residueMoney;
	}
	/**
	 * 获取  type
	 *@return: String  类型(0.广告消费，1.充值, 2.升级，3.提现)
	 */
	public String getType(){
		return this.type;
	}

	/**
	 * 设置  type
	 *@param: type  类型(0.广告消费，1.充值, 2.升级，3.提现)
	 */
	public void setType(String type){
		this.type = type;
	}
	/**
	 * 获取  createBy
	 *@return: String  创建者
	 */
	public String getCreateBy(){
		return this.createBy;
	}

	/**
	 * 设置  createBy
	 *@param: createBy  创建者
	 */
	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}
	/**
	 * 获取  createDate
	 *@return: Date  创建时间
	 */
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 * 设置  createDate
	 *@param: createDate  创建时间
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	/**
	 * 获取  updateBy
	 *@return: String  更新者
	 */
	public String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 * 设置  updateBy
	 *@param: updateBy  更新者
	 */
	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 * 获取  updateDate
	 *@return: Date  更新时间
	 */
	public Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 * 设置  updateDate
	 *@param: updateDate  更新时间
	 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 * 获取  delFlag
	 *@return: String  删除标记（0：正常；1：删除）
	 */
	public String getDelFlag(){
		return this.delFlag;
	}

	/**
	 * 设置  delFlag
	 *@param: delFlag  删除标记（0：正常；1：删除）
	 */
	public void setDelFlag(String delFlag){
		this.delFlag = delFlag;
	}
	/**
	 * 获取  remarks
	 *@return: String  备注信息
	 */
	public String getRemarks(){
		return this.remarks;
	}

	/**
	 * 设置  remarks
	 *@param: remarks  备注信息
	 */
	public void setRemarks(String remarks){
		this.remarks = remarks;
	}
	
}
