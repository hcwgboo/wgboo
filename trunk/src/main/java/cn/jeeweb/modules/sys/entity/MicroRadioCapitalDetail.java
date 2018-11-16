package cn.jeeweb.modules.sys.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import java.util.Date;

/**   
 * @Title: 平台资金
 * @Description: 平台资金
 * @author java
 * @date 2018-11-12 19:54:35
 * @version V1.0   
 *
 */
@TableName("micro_radio_capital_detail")
@SuppressWarnings("serial")
public class MicroRadioCapitalDetail extends AbstractEntity<String> {

    /**主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**商家id*/
    @TableField(value = "merchant_id")
	private String merchantId;
    /**业务源（0.广告1.待定）*/
    @TableField(value = "source")
	private String source;
    /**类型0.消费，1.提现*/
    @TableField(value = "type")
	private String type;
    /**余额*/
    @TableField(value = "balance_money")
	private Double balanceMoney;
    /**当前金额*/
    @TableField(value = "now_money")
	private Double nowMoney;
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
	 * 获取  source
	 *@return: String  业务源（0.广告1.待定）
	 */
	public String getSource(){
		return this.source;
	}

	/**
	 * 设置  source
	 *@param: source  业务源（0.广告1.待定）
	 */
	public void setSource(String source){
		this.source = source;
	}
	/**
	 * 获取  type
	 *@return: String  类型0.消费，1.提现
	 */
	public String getType(){
		return this.type;
	}

	/**
	 * 设置  type
	 *@param: type  类型0.消费，1.提现
	 */
	public void setType(String type){
		this.type = type;
	}
	/**
	 * 获取  balanceMoney
	 *@return: Double  余额
	 */
	public Double getBalanceMoney(){
		return this.balanceMoney;
	}

	/**
	 * 设置  balanceMoney
	 *@param: balanceMoney  余额
	 */
	public void setBalanceMoney(Double balanceMoney){
		this.balanceMoney = balanceMoney;
	}
	/**
	 * 获取  nowMoney
	 *@return: Double  当前金额
	 */
	public Double getNowMoney(){
		return this.nowMoney;
	}

	/**
	 * 设置  nowMoney
	 *@param: nowMoney  当前金额
	 */
	public void setNowMoney(Double nowMoney){
		this.nowMoney = nowMoney;
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
