<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>商户广告规则列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="商户广告规则">
<grid:grid id="advertiseRuleRelationGridId" url="${adminPath}/sys/advertiserulerelation/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" />
    <grid:column label="商家名称"  name="merchantName" />
    <grid:column label="规则名称"  name="advRuleName" />
    <grid:column label="平台比例"  name="ratio" />
    <grid:column label="最低金额"  name="minMoney" />
    <grid:column label="最少条数"  name="minSize" />
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>