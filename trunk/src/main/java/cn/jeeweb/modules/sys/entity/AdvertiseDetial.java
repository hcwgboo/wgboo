package cn.jeeweb.modules.sys.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import java.util.Date;

/**   
 * @Title: 广告详情
 * @Description: 广告详情
 * @author java
 * @date 2018-11-12 19:53:06
 * @version V1.0   
 *
 */
@TableName("adv_advertise_detial")
@SuppressWarnings("serial")
public class AdvertiseDetial extends AbstractEntity<String> {

    /**主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**广告id*/
    @TableField(value = "adv_id")
	private String advId;
    /**商户id*/
    @TableField(value = "merchant_id")
	private String merchantId;
    /**会员id*/
    @TableField(value = "dis_member_id")
	private String disMemberId;
    /**上级会员id*/
    @TableField(value = "parent_dis_member_id")
	private String parentDisMemberId;
    /**上级会员所得*/
    @TableField(value = "superior_money")
	private Double superiorMoney;
    /**自己所得*/
    @TableField(value = "current_monkey")
	private Double currentMonkey;
    /**转发来源（0.平台1.上级分销）*/
    @TableField(value = "source")
	private String source;
    /**当天的年月日（2018-10-28）*/
    @TableField(value = "the_same_day")
	private String theSameDay;
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
	 * 获取  advId
	 *@return: String  广告id
	 */
	public String getAdvId(){
		return this.advId;
	}

	/**
	 * 设置  advId
	 *@param: advId  广告id
	 */
	public void setAdvId(String advId){
		this.advId = advId;
	}
	/**
	 * 获取  merchantId
	 *@return: String  商户id
	 */
	public String getMerchantId(){
		return this.merchantId;
	}

	/**
	 * 设置  merchantId
	 *@param: merchantId  商户id
	 */
	public void setMerchantId(String merchantId){
		this.merchantId = merchantId;
	}
	/**
	 * 获取  disMemberId
	 *@return: String  会员id
	 */
	public String getDisMemberId(){
		return this.disMemberId;
	}

	/**
	 * 设置  disMemberId
	 *@param: disMemberId  会员id
	 */
	public void setDisMemberId(String disMemberId){
		this.disMemberId = disMemberId;
	}
	/**
	 * 获取  parentDisMemberId
	 *@return: String  上级会员id
	 */
	public String getParentDisMemberId(){
		return this.parentDisMemberId;
	}

	/**
	 * 设置  parentDisMemberId
	 *@param: parentDisMemberId  上级会员id
	 */
	public void setParentDisMemberId(String parentDisMemberId){
		this.parentDisMemberId = parentDisMemberId;
	}
	/**
	 * 获取  superiorMoney
	 *@return: Double  上级会员所得
	 */
	public Double getSuperiorMoney(){
		return this.superiorMoney;
	}

	/**
	 * 设置  superiorMoney
	 *@param: superiorMoney  上级会员所得
	 */
	public void setSuperiorMoney(Double superiorMoney){
		this.superiorMoney = superiorMoney;
	}
	/**
	 * 获取  currentMonkey
	 *@return: Double  自己所得
	 */
	public Double getCurrentMonkey(){
		return this.currentMonkey;
	}

	/**
	 * 设置  currentMonkey
	 *@param: currentMonkey  自己所得
	 */
	public void setCurrentMonkey(Double currentMonkey){
		this.currentMonkey = currentMonkey;
	}
	/**
	 * 获取  source
	 *@return: String  转发来源（0.平台1.上级分销）
	 */
	public String getSource(){
		return this.source;
	}

	/**
	 * 设置  source
	 *@param: source  转发来源（0.平台1.上级分销）
	 */
	public void setSource(String source){
		this.source = source;
	}
	/**
	 * 获取  theSameDay
	 *@return: String  当天的年月日（2018-10-28）
	 */
	public String getTheSameDay(){
		return this.theSameDay;
	}

	/**
	 * 设置  theSameDay
	 *@param: theSameDay  当天的年月日（2018-10-28）
	 */
	public void setTheSameDay(String theSameDay){
		this.theSameDay = theSameDay;
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
