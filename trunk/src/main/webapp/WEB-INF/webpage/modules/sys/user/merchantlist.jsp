<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="sys.user.title" /></title>
  <meta name="decorator" content="grid-select"/>
  <html:component name="bootstrap-treeview"/>
</head>
<body title="<spring:message code="sys.user.title" />">
<div class="row">
	<div  class="col-sm-9 col-md-12">
		<grid:grid id="userGridId" gridSetting="merchantSetting" url="${adminPath}/sys/user/ajaxList?type=2">
			<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
            <grid:column label="用户名"  name="username"  query="true"  condition="like" />
            <grid:column label="名称"  name="realname"  query="true"  condition="like" />
            <grid:column label="公司名称"  name="companyname"  query="true"  condition="like" />
			<grid:toolbar  function="search"/>
			<grid:toolbar  function="reset"/>
		</grid:grid>
	</div>
</div>
<script type="text/javascript">
	var merchantSetting = {
        multiselect:true,
        multiboxonly:true,
        beforeSelectRow:beforeSelectRow,
    }
    function beforeSelectRow() {
        $("#userGridId").jqGrid('resetSelection');
        return (true);
    }

    function subCallback() {
        var rowId = $("#userGridIdGrid").jqGrid('getGridParam', 'selrow');
        var rowData = $("#userGridIdGrid").jqGrid('getRowData',rowId);
        return rowData;
    }
</script>
</body>
</html>