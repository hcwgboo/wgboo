<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>平台资金列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="平台资金">
<grid:grid id="microRadioCapitalDetailGridId" url="${adminPath}/sys/microradiocapitaldetail/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" />
    <grid:column label="商家id"  name="merchantId" />
    <grid:column label="业务源（0.广告1.待定）"  name="source" />
    <grid:column label="类型0.消费，1.提现"  name="type" />
    <grid:column label="余额"  name="balanceMoney" />
    <grid:column label="当前金额"  name="nowMoney" />
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