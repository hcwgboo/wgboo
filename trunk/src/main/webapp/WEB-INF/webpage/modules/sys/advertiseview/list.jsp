<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>广告浏览列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="广告浏览">
<grid:grid id="advertiseViewGridId" url="${adminPath}/sys/advertiseview/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" />
    <grid:column label="广告id"  name="advId" />
    <grid:column label="商户id"  name="merchantId" />
    <grid:column label="会员id"  name="userId" />
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