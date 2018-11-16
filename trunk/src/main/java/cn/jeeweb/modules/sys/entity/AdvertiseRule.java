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
 * @Title: 广告规则
 * @Description: 广告规则
 * @author cql
 * @date 2018-11-12 19:57:01
 * @version V1.0   
 *
 */
@TableName("adv_advertise_rule")
@SuppressWarnings("serial")
public class AdvertiseRule extends AbstractEntity<String> {

    /**主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**规则名称*/
    @TableField(value = "name")
	private String name;
    /**平台佣金比例*/
    @TableField(value = "ratio")
	private Double ratio;
    /**最低金额*/
    @TableField(value = "min_money")
	private BigDecimal minMoney;
    /**任务类型（0,1,2）*/
    @TableField(value = "task_type")
	private String taskType;
    /**描述*/
    @TableField(value = "describe")
	private String describe;
    /**商户类型（0.平台，1.商户）*/
    @TableField(value = "type")
	private String type;
    @TableField(value = "min_size")
    private Integer minSize;
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
	 * 获取  name
	 *@return: String  规则名称
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * 设置  name
	 *@param: name  规则名称
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 获取  ratio
	 *@return: String  平台佣金比例
	 */
	public Double getRatio(){
		return this.ratio;
	}

	/**
	 * 设置  ratio
	 *@param: ratio  平台佣金比例
	 */
	public void setRatio(Double ratio){
		this.ratio = ratio;
	}
	/**
	 * 获取  minMoney
	 *@return: Double  最低金额
	 */
	public BigDecimal getMinMoney(){
		return this.minMoney;
	}

	/**
	 * 设置  minMoney
	 *@param: minMoney  最低金额
	 */
	public void setMinMoney(BigDecimal minMoney){
		this.minMoney = minMoney;
	}
	/**
	 * 获取  taskType
	 *@return: String  任务类型（0,1,2）
	 */
	public String getTaskType(){
		return this.taskType;
	}

	/**
	 * 设置  taskType
	 *@param: taskType  任务类型（0,1,2）
	 */
	public void setTaskType(String taskType){
		this.taskType = taskType;
	}
	/**
	 * 获取  describe
	 *@return: String  描述
	 */
	public String getDescribe(){
		return this.describe;
	}

	/**
	 * 设置  describe
	 *@param: describe  描述
	 */
	public void setDescribe(String describe){
		this.describe = describe;
	}
	/**
	 * 获取  type
	 *@return: String  商户类型（0.平台，1.商户）
	 */
	public String getType(){
		return this.type;
	}

	/**
	 * 设置  type
	 *@param: type  商户类型（0.平台，1.商户）
	 */
	public void setType(String type){
		this.type = type;
	}

	public Integer getMinSize() {
		return minSize;
	}

	public void setMinSize(Integer minSize) {
		this.minSize = minSize;
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
