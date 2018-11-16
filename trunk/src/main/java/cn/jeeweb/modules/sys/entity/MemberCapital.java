package cn.jeeweb.modules.sys.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import java.util.Date;

/**   
 * @Title: 会员资金
 * @Description: 会员资金
 * @author cql
 * @date 2018-11-12 19:54:58
 * @version V1.0   
 *
 */
@TableName("dis_member_capital")
@SuppressWarnings("serial")
public class MemberCapital extends AbstractEntity<String> {

    /**主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**member_id*/
    @TableField(value = "member_id")
	private String memberId;
    /**账户余额*/
    @TableField(value = "balance_money")
	private Double balanceMoney;
    /**冻结金额*/
    @TableField(value = "freeze_money")
	private Double freezeMoney;
    /**历史总收益*/
    @TableField(value = "amount")
	private Double amount;
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
	 * 获取  memberId
	 *@return: String  member_id
	 */
	public String getMemberId(){
		return this.memberId;
	}

	/**
	 * 设置  memberId
	 *@param: memberId  member_id
	 */
	public void setMemberId(String memberId){
		this.memberId = memberId;
	}
	/**
	 * 获取  balanceMoney
	 *@return: Double  账户余额
	 */
	public Double getBalanceMoney(){
		return this.balanceMoney;
	}

	/**
	 * 设置  balanceMoney
	 *@param: balanceMoney  账户余额
	 */
	public void setBalanceMoney(Double balanceMoney){
		this.balanceMoney = balanceMoney;
	}
	/**
	 * 获取  freezeMoney
	 *@return: Double  冻结金额
	 */
	public Double getFreezeMoney(){
		return this.freezeMoney;
	}

	/**
	 * 设置  freezeMoney
	 *@param: freezeMoney  冻结金额
	 */
	public void setFreezeMoney(Double freezeMoney){
		this.freezeMoney = freezeMoney;
	}
	/**
	 * 获取  amount
	 *@return: Double  历史总收益
	 */
	public Double getAmount(){
		return this.amount;
	}

	/**
	 * 设置  amount
	 *@param: amount  历史总收益
	 */
	public void setAmount(Double amount){
		this.amount = amount;
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
