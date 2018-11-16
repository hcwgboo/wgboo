<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>广告列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="广告">
<grid:grid id="advertiseAttaRelationGridId" url="${adminPath}/sys/advertiseattarelation/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
	<grid:button groupname="opt" function="delete" />
    <grid:column label="广告id"  name="advId" />
    <grid:column label="附件id"  name="attachmentId" />
    <grid:column label="图片url"  name="imgUrl" />
    <grid:column label="创建人"  name="createBy" />
    <grid:column label="创建时间"  name="createDate" />
    <grid:column label="修改人"  name="updateBy" />
    <grid:column label="修改时间"  name="updateDate" />
    <grid:column label="备注"  name="remarks" />
    <grid:column label="是否删除（0否 1是）"  name="delFlag" />
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>