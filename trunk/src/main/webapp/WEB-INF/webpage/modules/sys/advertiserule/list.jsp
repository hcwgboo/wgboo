<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>广告规则列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="广告规则">
<grid:grid id="advertiseRuleGridId" url="${adminPath}/sys/advertiserule/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
    <grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
    <grid:button groupname="opt" function="delete" />
    <grid:column label="规则名称"  name="name" />
    <grid:column label="平台佣金占比"  name="ratio" />
    <grid:column label="上级分销佣金占比"  name="superiorCommissionRatio" />
    <grid:column label="下级分销佣金占比"  name="subCommissionRatio" />
    <grid:column label="最低金额"  name="minMoney" />
    <grid:column label="最少条数"  name="minSize" />
    <grid:column label="任务类型" dict="rwlx" name="taskType" />
    <grid:column label="商户类型"  name="type" dict="shlx" />
    <grid:column label="描述"  name="describe" />
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>