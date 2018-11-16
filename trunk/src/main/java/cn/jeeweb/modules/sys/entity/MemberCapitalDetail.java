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
 * @author java
 * @date 2018-11-12 19:53:29
 * @version V1.0   
 *
 */
@TableName("dis_member_capital_detail")
@SuppressWarnings("serial")
public class MemberCapitalDetail extends AbstractEntity<String> {

    /**主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**分销会员资金表id*/
    @TableField(value = "me_captial_id")
	private String meCaptialId;
    /**会员id*/
    @TableField(value = "member_id")
	private String memberId;
    /**来源id*/
    @TableField(value = "source_id")
	private String sourceId;
    /**类型(1.直接转发，2.下级转发提成3.提现成功4.提现失败退回5.提现审核中）*/
    @TableField(value = "type")
	private String type;
    /**金额*/
    @TableField(value = "money")
	private Double money;
    /**交易账号*/
    @TableField(value = "pay_account")
	private String payAccount;
    /**微信*/
    @TableField(value = "pay_type")
	private String payType;
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
	 * 获取  meCaptialId
	 *@return: String  分销会员资金表id
	 */
	public String getMeCaptialId(){
		return this.meCaptialId;
	}

	/**
	 * 设置  meCaptialId
	 *@param: meCaptialId  分销会员资金表id
	 */
	public void setMeCaptialId(String meCaptialId){
		this.meCaptialId = meCaptialId;
	}
	/**
	 * 获取  memberId
	 *@return: String  会员id
	 */
	public String getMemberId(){
		return this.memberId;
	}

	/**
	 * 设置  memberId
	 *@param: memberId  会员id
	 */
	public void setMemberId(String memberId){
		this.memberId = memberId;
	}
	/**
	 * 获取  sourceId
	 *@return: String  来源id
	 */
	public String getSourceId(){
		return this.sourceId;
	}

	/**
	 * 设置  sourceId
	 *@param: sourceId  来源id
	 */
	public void setSourceId(String sourceId){
		this.sourceId = sourceId;
	}
	/**
	 * 获取  type
	 *@return: String  类型(1.直接转发，2.下级转发提成3.提现成功4.提现失败退回5.提现审核中）
	 */
	public String getType(){
		return this.type;
	}

	/**
	 * 设置  type
	 *@param: type  类型(1.直接转发，2.下级转发提成3.提现成功4.提现失败退回5.提现审核中）
	 */
	public void setType(String type){
		this.type = type;
	}
	/**
	 * 获取  money
	 *@return: Double  金额
	 */
	public Double getMoney(){
		return this.money;
	}

	/**
	 * 设置  money
	 *@param: money  金额
	 */
	public void setMoney(Double money){
		this.money = money;
	}
	/**
	 * 获取  payAccount
	 *@return: String  交易账号
	 */
	public String getPayAccount(){
		return this.payAccount;
	}

	/**
	 * 设置  payAccount
	 *@param: payAccount  交易账号
	 */
	public void setPayAccount(String payAccount){
		this.payAccount = payAccount;
	}
	/**
	 * 获取  payType
	 *@return: String  微信
	 */
	public String getPayType(){
		return this.payType;
	}

	/**
	 * 设置  payType
	 *@param: payType  微信
	 */
	public void setPayType(String payType){
		this.payType = payType;
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
