package cn.jeeweb.modules.sys.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import cn.jeeweb.core.common.entity.AbstractEntity;

/**   
 * @Title: 广告
 * @Description: 广告
 * @author java
 * @date 2018-11-12 19:58:04
 * @version V1.0   
 *
 */
@TableName("adv_advertise")
@SuppressWarnings("serial")
public class Advertise extends AbstractEntity<String> {

    /**主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**商户id*/
    @TableField(value = "merchant_id")
	private String merchantId;
    /**广告标题*/
    @TableField(value = "title")
	private String title;
    /**奖励规则*/
    @TableField(value = "rule_main")
	private String ruleMain;
    /**内容*/
    @TableField(value = "content")
	private String content;
    /**广告类别*/
    @TableField(value = "type")
	private String type;
    /**转发一次金额*/
    @TableField(value = "forward_money")
	private BigDecimal forwardMoney;
    /**总金额*/
    @TableField(value = "total_money")
	private BigDecimal totalMoney;
    /**余额*/
    @TableField(value = "balance")
	private BigDecimal balance;
	/**规则id*/
	@TableField(value = "rule_id")
	private String ruleId;
	/**平台佣金占比*/
	@TableField(value = "ratio")
	private Double ratio;
	/**上级分销佣金占比*/
	@TableField(value = "superior_commission_ratio")
	private Double superiorCommissionRatio;
	/**下级分销佣金占比*/
	@TableField(value = "sub_commission_ratio")
	private Double subCommissionRatio;
	/**规则名称*/
	@TableField(value = "rule_name")
	private String ruleName;
	/**规则类型1.转发，2.浏览*/
	@TableField(value = "rule_type")
	private String ruleType;
	/**最小条数*/
	@TableField(value = "min_size")
	private Integer minSize;
    /**最小金额*/
    @TableField(value = "min_money")
	private BigDecimal minMoney;
    /**转发次数*/
    @TableField(value = "transpond")
    private Integer transpond;
    /**当前浏览次数*/
    @TableField(value = "now_view_times")
	private Integer nowViewTimes;
    /**当前分享次数*/
    @TableField(value = "now_share_times")
	private Integer nowShareTimes;
    /**上下架（0.下架，1.上架）*/
    @TableField(value = "release_status")
	private String releaseStatus;
    /**状态（0.创建，1.待审核，2.审核通过，3.拒绝）*/
    @TableField(value = "status")
	private String status;
    /**开放区域*/
    @TableField(value = "region")
	private String region;
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
    @TableField(value = "reduce_money")
    private BigDecimal reduceMoney;

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
	 * 获取  merchantId
	 *@return: String  商户id
	 */
	public String getMerchantId(){
		return this.merchantId;
	}

	/**
	 * 设置  merchantId
	 *@param: merchantId  商户id
	 */
	public void setMerchantId(String merchantId){
		this.merchantId = merchantId;
	}
	/**
	 * 获取  title
	 *@return: String  广告标题
	 */
	public String getTitle(){
		return this.title;
	}

	/**
	 * 设置  title
	 *@param: title  广告标题
	 */
	public void setTitle(String title){
		this.title = title;
	}
	/**
	 * 获取  ruleMain
	 *@return: String  奖励规则
	 */
	public String getRuleMain(){
		return this.ruleMain;
	}

	/**
	 * 设置  ruleMain
	 *@param: ruleMain  奖励规则
	 */
	public void setRuleMain(String ruleMain){
		this.ruleMain = ruleMain;
	}
	/**
	 * 获取  content
	 *@return: String  内容
	 */
	public String getContent(){
		return this.content;
	}

	/**
	 * 设置  content
	 *@param: content  内容
	 */
	public void setContent(String content){
		this.content = content;
	}
	/**
	 * 获取  type
	 *@return: String  广告类别
	 */
	public String getType(){
		return this.type;
	}

	/**
	 * 设置  type
	 *@param: type  广告类别
	 */
	public void setType(String type){
		this.type = type;
	}
	/**
	 * 获取  forwardMoney
	 *@return: Double  转发一次金额
	 */
	public BigDecimal getForwardMoney(){
		return this.forwardMoney;
	}

	/**
	 * 设置  forwardMoney
	 *@param: forwardMoney  转发一次金额
	 */
	public void setForwardMoney(BigDecimal forwardMoney){
		this.forwardMoney = forwardMoney;
	}
	/**
	 * 获取  totalMoney
	 *@return: Double  总金额
	 */
	public BigDecimal getTotalMoney(){
		return this.totalMoney;
	}

	/**
	 * 设置  totalMoney
	 *@param: totalMoney  总金额
	 */
	public void setTotalMoney(BigDecimal totalMoney){
		this.totalMoney = totalMoney;
	}
	/**
	 * 获取  balance
	 *@return: Double  余额
	 */
	public BigDecimal getBalance(){
		return this.balance;
	}

	/**
	 * 设置  balance
	 *@param: balance  余额
	 */
	public void setBalance(BigDecimal balance){
		this.balance = balance;
	}
	/**
	 * 获取  minMoney
	 *@return: Double  最小金额
	 */
	public BigDecimal getMinMoney(){
		return this.minMoney;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	public Double getSuperiorCommissionRatio() {
		return superiorCommissionRatio;
	}

	public void setSuperiorCommissionRatio(Double superiorCommissionRatio) {
		this.superiorCommissionRatio = superiorCommissionRatio;
	}

	public Double getSubCommissionRatio() {
		return subCommissionRatio;
	}

	public void setSubCommissionRatio(Double subCommissionRatio) {
		this.subCommissionRatio = subCommissionRatio;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public Integer getMinSize() {
		return minSize;
	}

	public void setMinSize(Integer minSize) {
		this.minSize = minSize;
	}

	public Integer getTranspond() {
		return transpond;
	}

	public void setTranspond(Integer transpond) {
		this.transpond = transpond;
	}

	/**
	 * 设置  minMoney
	 *@param: minMoney  最小金额
	 */
	public void setMinMoney(BigDecimal minMoney){
		this.minMoney = minMoney;
	}
	/**
	 * 获取  nowViewTimes
	 *@return: Integer  当前浏览次数
	 */
	public Integer getNowViewTimes(){
		return this.nowViewTimes;
	}

	/**
	 * 设置  nowViewTimes
	 *@param: nowViewTimes  当前浏览次数
	 */
	public void setNowViewTimes(Integer nowViewTimes){
		this.nowViewTimes = nowViewTimes;
	}
	/**
	 * 获取  nowShareTimes
	 *@return: Integer  当前分享次数
	 */
	public Integer getNowShareTimes(){
		return this.nowShareTimes;
	}

	/**
	 * 设置  nowShareTimes
	 *@param: nowShareTimes  当前分享次数
	 */
	public void setNowShareTimes(Integer nowShareTimes){
		this.nowShareTimes = nowShareTimes;
	}
	/**
	 * 获取  releaseStatus
	 *@return: String  上下架（0.下架，1.上架）
	 */
	public String getReleaseStatus(){
		return this.releaseStatus;
	}

	/**
	 * 设置  releaseStatus
	 *@param: releaseStatus  上下架（0.下架，1.上架）
	 */
	public void setReleaseStatus(String releaseStatus){
		this.releaseStatus = releaseStatus;
	}
	/**
	 * 获取  status
	 *@return: String  状态（0.创建，1.待审核，2.审核通过，3.拒绝）
	 */
	public String getStatus(){
		return this.status;
	}

	/**
	 * 设置  status
	 *@param: status  状态（0.创建，1.待审核，2.审核通过，3.拒绝）
	 */
	public void setStatus(String status){
		this.status = status;
	}
	/**
	 * 获取  region
	 *@return: String  开放区域
	 */
	public String getRegion(){
		return this.region;
	}

	/**
	 * 设置  region
	 *@param: region  开放区域
	 */
	public void setRegion(String region){
		this.region = region;
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

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public BigDecimal getReduceMoney() {
		return reduceMoney;
	}

	public void setReduceMoney(BigDecimal reduceMoney) {
		this.reduceMoney = reduceMoney;
	}
}
