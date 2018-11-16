<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>商家资金明细列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="商家资金明细">
<grid:grid id="merCapitalDetailGridId" url="${adminPath}/sys/mercapitaldetail/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" />
    <grid:column label="商家id"  name="merchantId" />
    <grid:column label="资金表id"  name="capitalId" />
    <grid:column label="消费id（广告）"  name="consumeId" />
    <grid:column label="金额"  name="money" />
    <grid:column label="剩余金额"  name="residueMoney" />
    <grid:column label="类型(0.广告消费，1.充值, 2.升级，3.提现)"  name="type" />
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