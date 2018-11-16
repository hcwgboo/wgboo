<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>会员资金列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="会员资金">
<grid:grid id="memberCapitalDetailGridId" url="${adminPath}/sys/membercapitaldetail/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" />
    <grid:column label="分销会员资金表id"  name="meCaptialId" />
    <grid:column label="会员id"  name="memberId" />
    <grid:column label="来源id"  name="sourceId" />
    <grid:column label="类型(1.直接转发，2.下级转发提成3.提现成功4.提现失败退回5.提现审核中）"  name="type" />
    <grid:column label="金额"  name="money" />
    <grid:column label="交易账号"  name="payAccount" />
    <grid:column label="微信"  name="payType" />
    <grid:column label="创建者"  name="createBy" />
    <grid:column label="创建时间"  name="createDate" />
    <grid:column label="更新者"  name="updateBy" />
    <grid:column label="更新时间"  name="updateDate" />
    <grid:column label="删除标记（0：正常；1：删除）"  name="delFlag" />
    <grid:column label="备注信息"  name="remarks" />
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>