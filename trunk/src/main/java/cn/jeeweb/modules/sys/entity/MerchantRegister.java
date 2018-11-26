package cn.jeeweb.modules.sys.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

/**
 * @Title: 商家注册
 * @Description: 商家注册
 * @author cql
 * @date 2018-11-24 10:55:30
 * @version V1.0
 *
 */
@TableName("sys_merchant_register")
@SuppressWarnings("serial")
public class MerchantRegister extends AbstractEntity<String> {

	/** id */
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	/** 公司名称 */
	@TableField(value = "companyname")
	private String companyname;
	/** 真实名称 */
	@TableField(value = "realname")
	private String realname;
	/** 用户名 */
	@TableField(value = "username")
	private String username;
	/** 密码 */
	@TableField(value = "password")
	private String password;
	/** salt */
	@TableField(value = "salt")
	private String salt;
	/** 联系电话 */
	@TableField(value = "phone")
	private String phone;
	/** 商家状态（1.待审核，2.审核通过，3.审核失败） */
	@TableField(value = "status")
	private String status;
	/** 附件ids */
	@TableField(value = "ids")
	private String ids;
	/** create_date */
	@TableField(value = "create_date", fill = FieldFill.INSERT)
	private Date createDate;
	/** del_flag */
	@TableField(value = "del_flag")
	private String delFlag;
	@TableField(exist = false)
	private String vpassword;
	@TableField(exist = false)
	private String code;

	/**
	 * 获取 id
	 * 
	 * @return: String id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置 id
	 * 
	 * @param: id
	 *             id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 companyname
	 * 
	 * @return: String 公司名称
	 */
	public String getCompanyname() {
		return this.companyname;
	}

	/**
	 * 设置 companyname
	 * 
	 * @param: companyname
	 *             公司名称
	 */
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	/**
	 * 获取 realname
	 * 
	 * @return: String 真实名称
	 */
	public String getRealname() {
		return this.realname;
	}

	/**
	 * 设置 realname
	 * 
	 * @param: realname
	 *             真实名称
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 * 获取 username
	 * 
	 * @return: String 用户名
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * 设置 username
	 * 
	 * @param: username
	 *             用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取 password
	 * 
	 * @return: String 密码
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * 设置 password
	 * 
	 * @param: password
	 *             密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取 salt
	 * 
	 * @return: String salt
	 */
	public String getSalt() {
		return this.salt;
	}

	/**
	 * 设置 salt
	 * 
	 * @param: salt
	 *             salt
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * 获取 phone
	 * 
	 * @return: String 联系电话
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * 设置 phone
	 * 
	 * @param: phone
	 *             联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取 status
	 * 
	 * @return: String 商家状态（1.审核通过，2.审核通过，3.审核失败）
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * 设置 status
	 * 
	 * @param: status
	 *             商家状态（1.审核通过，2.审核通过，3.审核失败）
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 获取 ids
	 * 
	 * @return: String 附件ids
	 */
	public String getIds() {
		return this.ids;
	}

	/**
	 * 设置 ids
	 * 
	 * @param: ids
	 *             附件ids
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}

	/**
	 * 获取 createDate
	 * 
	 * @return: Date create_date
	 */
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * 设置 createDate
	 * 
	 * @param: createDate
	 *             create_date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 获取 delFlag
	 * 
	 * @return: String del_flag
	 */
	public String getDelFlag() {
		return this.delFlag;
	}

	/**
	 * 设置 delFlag
	 * 
	 * @param: delFlag
	 *             del_flag
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getVpassword() {
		return vpassword;
	}

	public void setVpassword(String vpassword) {
		this.vpassword = vpassword;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
