package cn.jeeweb.modules.sys.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;

/**   
 * @Title: 资金账号
 * @Description: 资金账号
 * @author java
 * @date 2018-11-12 19:56:39
 * @version V1.0   
 *
 */
@TableName("dis_capital_account")
@SuppressWarnings("serial")
public class CapitalAccount extends AbstractEntity<String> {

    /**id*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**账号*/
    @TableField(value = "account")
	private String account;
    /**会员id*/
    @TableField(value = "dis_member_id")
	private String disMemberId;
	
	/**
	 * 获取  id
	 *@return: String  id
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  id
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  account
	 *@return: String  账号
	 */
	public String getAccount(){
		return this.account;
	}

	/**
	 * 设置  account
	 *@param: account  账号
	 */
	public void setAccount(String account){
		this.account = account;
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
	
}
