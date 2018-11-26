package cn.jeeweb.modules.sys.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import cn.jeeweb.modules.sys.entity.User;
import java.util.Date;

/**
 * @Title: 短信日志表
 * @Description: 短信日志表
 * @author yuanw
 * @date 2018-04-13 11:05:40
 * @version V1.0
 *
 */
@TableName("log_smscode")
@SuppressWarnings("serial")
public class LogSmscode extends AbstractEntity<String> {

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
	/**短信类型(1.注册验证码2.修改手机号验证码)*/
	@TableField(value = "type")
	private Integer type;
	/**手机号*/
	@TableField(value = "telphone")
	private String telphone;
	/**验证码*/
	@TableField(value = "code")
	private String code;
	/**返回结果集*/
	@TableField(value = "result")
	private String result;

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
	 * 获取  type
	 *@return: String  短信类型(1.注册验证码2.修改手机号验证码)
	 */
	public Integer getType(){
		return this.type;
	}

	/**
	 * 设置  type
	 *@param: type  短信类型(1.注册验证码2.修改手机号验证码)
	 */
	public void setType(Integer type){
		this.type = type;
	}
	/**
	 * 获取  telphone
	 *@return: String  手机号
	 */
	public String getTelphone(){
		return this.telphone;
	}

	/**
	 * 设置  telphone
	 *@param: telphone  手机号
	 */
	public void setTelphone(String telphone){
		this.telphone = telphone;
	}
	/**
	 * 获取  code
	 *@return: String  验证码
	 */
	public String getCode(){
		return this.code;
	}

	/**
	 * 设置  code
	 *@param: code  验证码
	 */
	public void setCode(String code){
		this.code = code;
	}
	/**
	 * 获取  result
	 *@return: String  返回结果集
	 */
	public String getResult(){
		return this.result;
	}

	/**
	 * 设置  result
	 *@param: result  返回结果集
	 */
	public void setResult(String result){
		this.result = result;
	}

}
