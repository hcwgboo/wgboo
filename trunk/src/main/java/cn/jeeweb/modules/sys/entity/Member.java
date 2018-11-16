package cn.jeeweb.modules.sys.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import java.util.Date;

/**   
 * @Title: 分销会员
 * @Description: 分销会员
 * @author java
 * @date 2018-11-12 19:57:41
 * @version V1.0   
 *
 */
@TableName("dis_member")
@SuppressWarnings("serial")
public class Member extends AbstractEntity<String> {

    /**主键*/
    @TableField(value = "id")
	private String id;
    /**当前用户的标识，小程序用来区分用户*/
    @TableId(value = "id", type = IdType.UUID)
	private Integer userId;
    /**昵称*/
    @TableField(value = "nick_name")
	private String nickName;
    /**头像*/
    @TableField(value = "avatar_url")
	private String avatarUrl;
    /**性别（0：未知、1：男、2：女）*/
    @TableField(value = "gender")
	private String gender;
    /**省*/
    @TableField(value = "province")
	private String province;
    /**市*/
    @TableField(value = "city")
	private String city;
    /**openid*/
    @TableField(value = "openid")
	private String openid;
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
	 * 获取  userId
	 *@return: Integer  当前用户的标识，小程序用来区分用户
	 */
	public Integer getUserId(){
		return this.userId;
	}

	/**
	 * 设置  userId
	 *@param: userId  当前用户的标识，小程序用来区分用户
	 */
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	/**
	 * 获取  nickName
	 *@return: String  昵称
	 */
	public String getNickName(){
		return this.nickName;
	}

	/**
	 * 设置  nickName
	 *@param: nickName  昵称
	 */
	public void setNickName(String nickName){
		this.nickName = nickName;
	}
	/**
	 * 获取  avatarUrl
	 *@return: String  头像
	 */
	public String getAvatarUrl(){
		return this.avatarUrl;
	}

	/**
	 * 设置  avatarUrl
	 *@param: avatarUrl  头像
	 */
	public void setAvatarUrl(String avatarUrl){
		this.avatarUrl = avatarUrl;
	}
	/**
	 * 获取  gender
	 *@return: String  性别（0：未知、1：男、2：女）
	 */
	public String getGender(){
		return this.gender;
	}

	/**
	 * 设置  gender
	 *@param: gender  性别（0：未知、1：男、2：女）
	 */
	public void setGender(String gender){
		this.gender = gender;
	}
	/**
	 * 获取  province
	 *@return: String  省
	 */
	public String getProvince(){
		return this.province;
	}

	/**
	 * 设置  province
	 *@param: province  省
	 */
	public void setProvince(String province){
		this.province = province;
	}
	/**
	 * 获取  city
	 *@return: String  市
	 */
	public String getCity(){
		return this.city;
	}

	/**
	 * 设置  city
	 *@param: city  市
	 */
	public void setCity(String city){
		this.city = city;
	}
	/**
	 * 获取  openid
	 *@return: String  openid
	 */
	public String getOpenid(){
		return this.openid;
	}

	/**
	 * 设置  openid
	 *@param: openid  openid
	 */
	public void setOpenid(String openid){
		this.openid = openid;
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
