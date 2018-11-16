package cn.jeeweb.modules.sys.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import java.util.Date;

/**   
 * @Title: 广告
 * @Description: 广告
 * @author java
 * @date 2018-11-12 19:52:25
 * @version V1.0   
 *
 */
@TableName("adv_advertise_atta_relation")
@SuppressWarnings("serial")
public class AdvertiseAttaRelation extends AbstractEntity<String> {

    /**主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**广告id*/
    @TableField(value = "adv_id")
	private String advId;
    /**附件id*/
    @TableField(value = "attachment_id")
	private String attachmentId;
    /**图片url*/
    @TableField(value = "img_url")
	private String imgUrl;
    /**创建人*/
    @TableField(value = "create_by",fill = FieldFill.INSERT)
	private String createBy;
    /**创建时间*/
    @TableField(value = "create_date",fill = FieldFill.INSERT)
	private Date createDate;
    /**修改人*/
    @TableField(value = "update_by",fill = FieldFill.INSERT_UPDATE)
	private String updateBy;
    /**修改时间*/
    @TableField(value = "update_date",fill = FieldFill.INSERT_UPDATE)
	private Date updateDate;
    /**备注*/
    @TableField(value = "remarks")
	private String remarks;
    /**是否删除（0否 1是）*/
    @TableField(value = "del_flag")
	private String delFlag;
	
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
	 * 获取  attachmentId
	 *@return: String  附件id
	 */
	public String getAttachmentId(){
		return this.attachmentId;
	}

	/**
	 * 设置  attachmentId
	 *@param: attachmentId  附件id
	 */
	public void setAttachmentId(String attachmentId){
		this.attachmentId = attachmentId;
	}
	/**
	 * 获取  imgUrl
	 *@return: String  图片url
	 */
	public String getImgUrl(){
		return this.imgUrl;
	}

	/**
	 * 设置  imgUrl
	 *@param: imgUrl  图片url
	 */
	public void setImgUrl(String imgUrl){
		this.imgUrl = imgUrl;
	}
	/**
	 * 获取  createBy
	 *@return: String  创建人
	 */
	public String getCreateBy(){
		return this.createBy;
	}

	/**
	 * 设置  createBy
	 *@param: createBy  创建人
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
	 *@return: String  修改人
	 */
	public String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 * 设置  updateBy
	 *@param: updateBy  修改人
	 */
	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 * 获取  updateDate
	 *@return: Date  修改时间
	 */
	public Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 * 设置  updateDate
	 *@param: updateDate  修改时间
	 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 * 获取  remarks
	 *@return: String  备注
	 */
	public String getRemarks(){
		return this.remarks;
	}

	/**
	 * 设置  remarks
	 *@param: remarks  备注
	 */
	public void setRemarks(String remarks){
		this.remarks = remarks;
	}
	/**
	 * 获取  delFlag
	 *@return: String  是否删除（0否 1是）
	 */
	public String getDelFlag(){
		return this.delFlag;
	}

	/**
	 * 设置  delFlag
	 *@param: delFlag  是否删除（0否 1是）
	 */
	public void setDelFlag(String delFlag){
		this.delFlag = delFlag;
	}
	
}
