<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>广告详情列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="广告详情">
<grid:grid id="advertiseDetialGridId" url="${adminPath}/sys/advertisedetial/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" />
    <grid:column label="广告id"  name="advId" />
    <grid:column label="商户id"  name="merchantId" />
    <grid:column label="会员id"  name="disMemberId" />
    <grid:column label="上级会员id"  name="parentDisMemberId" />
    <grid:column label="上级会员所得"  name="superiorMoney" />
    <grid:column label="自己所得"  name="currentMonkey" />
    <grid:column label="转发来源（0.平台1.上级分销）"  name="source" />
    <grid:column label="当天的年月日（2018-10-28）"  name="theSameDay" />
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