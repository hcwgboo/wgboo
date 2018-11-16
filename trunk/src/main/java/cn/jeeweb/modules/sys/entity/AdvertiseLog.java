package cn.jeeweb.modules.sys.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import java.util.Date;

/**   
 * @Title: 广告日志
 * @Description: 广告日志
 * @author cql
 * @date 2018-11-12 19:57:24
 * @version V1.0   
 *
 */
@TableName("adv_advertise_log")
@SuppressWarnings("serial")
public class AdvertiseLog extends AbstractEntity<String> {

    /**主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**广告id*/
    @TableField(value = "adv_id")
	private String advId;
    /**（0.创建，1.待审核，2.审核通过，3.拒绝）*/
    @TableField(value = "status")
	private String status;
    /**上下架（0.下架，1.上架）*/
    @TableField(value = "release")
	private String release;
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
	 * 获取  status
	 *@return: String  （0.创建，1.待审核，2.审核通过，3.拒绝）
	 */
	public String getStatus(){
		return this.status;
	}

	/**
	 * 设置  status
	 *@param: status  （0.创建，1.待审核，2.审核通过，3.拒绝）
	 */
	public void setStatus(String status){
		this.status = status;
	}
	/**
	 * 获取  release
	 *@return: String  上下架（0.下架，1.上架）
	 */
	public String getRelease(){
		return this.release;
	}

	/**
	 * 设置  release
	 *@param: release  上下架（0.下架，1.上架）
	 */
	public void setRelease(String release){
		this.release = release;
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
