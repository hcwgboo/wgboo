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
 * @Title: 导入列配置表
 * @Description: 导入列配置表
 * @author zhangyouwei
 * @date 2018-02-14 09:59:27
 * @version V1.0   
 *
 */
@TableName("sys_import_column_mapping")
@SuppressWarnings("serial")
public class ImportColumnMapping extends AbstractEntity<String> {

    /**字段主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
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
    /**导入配置主表id*/
    @TableField(value = "import_mapping_id")
	private String importMappingId;
    /**字段中文名*/
    @TableField(value = "property_cname")
	private String propertyCname;
    /**字段英文名*/
    @TableField(value = "property_ename")
	private String propertyEname;
    /**对应excel模板列序号从0开始*/
    @TableField(value = "column_no")
	private String columnNo;
    /**字段类型例：date，string，number等*/
    @TableField(value = "item_type")
	private String itemType;
    /**是否允许为空0：可以为空即不作空判断（默认值） 1：不可为空 需进行空值判断，若空则算失败件数，不作导入*/
    @TableField(value = "is_null")
	private String isNull;
    /**当类型是字典的时候必填*/
    @TableField(value = "code")
 	private String code;
    
	
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
	/**
	 * 获取  importMappingId
	 *@return: String  导入配置主表id
	 */
	public String getImportMappingId(){
		return this.importMappingId;
	}

	/**
	 * 设置  importMappingId
	 *@param: importMappingId  导入配置主表id
	 */
	public void setImportMappingId(String importMappingId){
		this.importMappingId = importMappingId;
	}
	/**
	 * 获取  propertyCname
	 *@return: String  字段中文名
	 */
	public String getPropertyCname(){
		return this.propertyCname;
	}

	/**
	 * 设置  propertyCname
	 *@param: propertyCname  字段中文名
	 */
	public void setPropertyCname(String propertyCname){
		this.propertyCname = propertyCname;
	}
	/**
	 * 获取  propertyEname
	 *@return: String  字段英文名
	 */
	public String getPropertyEname(){
		return this.propertyEname;
	}

	/**
	 * 设置  propertyEname
	 *@param: propertyEname  字段英文名
	 */
	public void setPropertyEname(String propertyEname){
		this.propertyEname = propertyEname;
	}
	/**
	 * 获取  columnNo
	 *@return: String  对应excel模板列序号从0开始
	 */
	public String getColumnNo(){
		return this.columnNo;
	}

	/**
	 * 设置  columnNo
	 *@param: columnNo  对应excel模板列序号从0开始
	 */
	public void setColumnNo(String columnNo){
		this.columnNo = columnNo;
	}
	/**
	 * 获取  itemType
	 *@return: String  字段类型例：date，string，number等
	 */
	public String getItemType(){
		return this.itemType;
	}

	/**
	 * 设置  itemType
	 *@param: itemType  字段类型例：date，string，number等
	 */
	public void setItemType(String itemType){
		this.itemType = itemType;
	}
	/**
	 * 获取  isNull
	 *@return: String  是否允许为空0：可以为空即不作空判断（默认值） 1：不可为空 需进行空值判断，若空则算失败件数，不作导入
	 */
	public String getIsNull(){
		return this.isNull;
	}

	/**
	 * 设置  isNull
	 *@param: isNull  是否允许为空0：可以为空即不作空判断（默认值） 1：不可为空 需进行空值判断，若空则算失败件数，不作导入
	 */
	public void setIsNull(String isNull){
		this.isNull = isNull;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
