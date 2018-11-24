<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>资金列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="商户资金列表">
<grid:grid id="merCapitalGridId" url="${adminPath}/sys/mercapital/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
    <grid:column label="商家id" hidden="true" name="merchantId" />
    <grid:column label="商家名称" name="merchantName" query="true" queryMode="input" condition="like" />
    <grid:column label="账户余额"  name="balanceMoney" />
    <grid:column label="冻结金额"  name="freezeMoney" />
	<grid:toolbar function="update" title="充值" width="600px" height="400px" />

	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
<script type="text/javascript">
    
</script>
</body>
</html>