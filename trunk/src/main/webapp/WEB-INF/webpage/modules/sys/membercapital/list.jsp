<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>会员资金列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="会员资金">
<grid:grid id="memberCapitalGridId" url="${adminPath}/sys/membercapital/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" />
    <grid:column label="member_id"  name="memberId" />
    <grid:column label="账户余额"  name="balanceMoney" />
    <grid:column label="冻结金额"  name="freezeMoney" />
    <grid:column label="历史总收益"  name="amount" />
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