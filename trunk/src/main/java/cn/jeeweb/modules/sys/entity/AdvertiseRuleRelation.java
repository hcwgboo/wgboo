package cn.jeeweb.modules.sys.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;

/**   
 * @Title: 广告规则
 * @Description: 广告规则
 * @author cql
 * @date 2018-11-12 19:55:20
 * @version V1.0   
 *
 */
@TableName("adv_advertise_rule_relation")
@SuppressWarnings("serial")
public class AdvertiseRuleRelation extends AbstractEntity<String> {

    /**id*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**广告表id*/
    @TableField(value = "merchant_id")
	private String merchantId;
    /**规则表id*/
    @TableField(value = "adv_rule_id")
	private String advRuleId;
	
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

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * 获取  advRuleId
	 *@return: String  规则表id
	 */
	public String getAdvRuleId(){
		return this.advRuleId;
	}

	/**
	 * 设置  advRuleId
	 *@param: advRuleId  规则表id
	 */
	public void setAdvRuleId(String advRuleId){
		this.advRuleId = advRuleId;
	}
	
}
