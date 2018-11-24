package cn.jeeweb.modules.excel.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import cn.jeeweb.core.common.entity.AbstractEntity;
import cn.jeeweb.modules.sys.entity.User;

/**   
 * @Title: 导入配置主表
 * @Description: 导入配置主表
 * @author zhangyouwei
 * @date 2018-02-14 09:37:17
 * @version V1.0   
 *
 */
@TableName("sys_import_mapping")
@SuppressWarnings("serial")
public class ImportMapping extends AbstractEntity<String> {

    /**字段主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**配置模板ID*/
    @TableField(value = "mapping_templet")
	private String mappingTemplet;
    /**配置模板名*/
    @TableField(value = "mapping_templet_name")
	private String mappingTempletName;
    /**模板类型*/
    @TableField(value = "templet_type")
	private String templetType;
    /**数据读取开始行号*/
    @TableField(value = "start_row_no")
	private String startRowNo;
    /**数据读取开始行号*/
    @TableField(value = "header_row_no")
	private String headerRowNo;
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
	
	/**
	 * 获取  id
	 *@return: String  字段主键
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  字段主键
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  mappingTemplet
	 *@return: String  配置模板ID
	 */
	public String getMappingTemplet(){
		return this.mappingTemplet;
	}

	/**
	 * 设置  mappingTemplet
	 *@param: mappingTemplet  配置模板ID
	 */
	public void setMappingTemplet(String mappingTemplet){
		this.mappingTemplet = mappingTemplet;
	}
	/**
	 * 获取  mappingTempletName
	 *@return: String  配置模板名
	 */
	public String getMappingTempletName(){
		return this.mappingTempletName;
	}

	/**
	 * 设置  mappingTempletName
	 *@param: mappingTempletName  配置模板名
	 */
	public void setMappingTempletName(String mappingTempletName){
		this.mappingTempletName = mappingTempletName;
	}
	/**
	 * 获取  templetType
	 *@return: String  模板类型
	 */
	public String getTempletType(){
		return this.templetType;
	}

	/**
	 * 设置  templetType
	 *@param: templetType  模板类型
	 */
	public void setTempletType(String templetType){
		this.templetType = templetType;
	}
	/**
	 * 获取  startRowNo
	 *@return: String  数据读取开始行号
	 */
	public String getStartRowNo(){
		return this.startRowNo;
	}

	/**
	 * 设置  startRowNo
	 *@param: startRowNo  数据读取开始行号
	 */
	public void setStartRowNo(String startRowNo){
		this.startRowNo = startRowNo;
	}
	
	public String getHeaderRowNo() {
		return headerRowNo;
	}

	public void setHeaderRowNo(String headerRowNo) {
		this.headerRowNo = headerRowNo;
	}

	/**
	 * 获取  createBy
	 *@return: User  创建者
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
	 *@return: User  更新者
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
	
}
