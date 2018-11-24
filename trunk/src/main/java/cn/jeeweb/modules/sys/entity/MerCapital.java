package cn.jeeweb.modules.sys.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;

import java.math.BigDecimal;
import java.util.Date;

/**   
 * @Title: 上架资金
 * @Description: 上架资金
 * @author java
 * @date 2018-11-12 20:01:10
 * @version V1.0   
 *
 */
@TableName("mer_capital")
@SuppressWarnings("serial")
public class MerCapital extends AbstractEntity<String> {

    /**主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**商家id*/
    @TableField(value = "merchant_id")
	private String merchantId;
    /**账户余额*/
    @TableField(value = "balance_money")
	private BigDecimal balanceMoney;
    /**冻结金额*/
    @TableField(value = "freeze_money")
	private BigDecimal freezeMoney;
    /**创建者*/
    @TableField(value = "create_by",el="createBy.id",fill = FieldFill.INSERT)
	private User createBy;
    /**创建时间*/
    @TableField(value = "create_date",fill = FieldFill.INSERT)
	private Date createDate;
    /**更新者*/
    @TableField(value = "update_by",el="updateBy.id",fill = FieldFill.INSERT_UPDATE)
	private User updateBy;
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
	 * 获取  balanceMoney
	 *@return: Double  账户余额
	 */
	public BigDecimal getBalanceMoney(){
		return this.balanceMoney;
	}

	/**
	 * 设置  balanceMoney
	 *@param: balanceMoney  账户余额
	 */
	public void setBalanceMoney(BigDecimal balanceMoney){
		this.balanceMoney = balanceMoney;
	}
	/**
	 * 获取  freezeMoney
	 *@return: Double  冻结金额
	 */
	public BigDecimal getFreezeMoney(){
		return this.freezeMoney;
	}

	/**
	 * 设置  freezeMoney
	 *@param: freezeMoney  冻结金额
	 */
	public void setFreezeMoney(BigDecimal freezeMoney){
		this.freezeMoney = freezeMoney;
	}
	/**
	 * 获取  createBy
	 *@return: String  创建者
	 */
	public User getCreateBy(){
		return this.createBy;
	}

	/**
	 * 设置  createBy
	 *@param: createBy  创建者
	 */
	public void setCreateBy(User createBy){
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
	public User getUpdateBy(){
		return this.updateBy;
	}

	/**
	 * 设置  updateBy
	 *@param: updateBy  更新者
	 */
	public void setUpdateBy(User updateBy){
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
