<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>分销会员列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="分销会员">
<grid:grid id="memberGridId" url="${adminPath}/sys/member/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" />
    <grid:column label="当前用户的标识，小程序用来区分用户"  name="userId" />
    <grid:column label="昵称"  name="nickName" />
    <grid:column label="头像"  name="avatarUrl" />
    <grid:column label="性别（0：未知、1：男、2：女）"  name="gender" />
    <grid:column label="省"  name="province" />
    <grid:column label="市"  name="city" />
    <grid:column label="openid"  name="openid" />
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