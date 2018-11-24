<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="sys.user.title" /></title>
  <meta name="decorator" content="list"/>
  <html:component name="bootstrap-treeview"/>
</head>
<body title="<spring:message code="sys.user.title" />">
<div class="row">
	<div  class="col-sm-9 col-md-12">
		<grid:grid id="userGridId" gridSetting="merchantSetting" url="${adminPath}/sys/user/ajaxList?type=2">
			<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
			<grid:column label="sys.common.opt"  name="opt" formatter="customFun" optCallback="merchantAuditOptFun" />
            <grid:column label="用户名"  name="username"  query="true"  condition="like" />
            <grid:column label="名称"  name="realname"  query="true"  condition="like" />
            <grid:column label="公司名称"  name="companyname"  query="true"  condition="like" />
            <grid:toolbar function="merchantCheck" title="审核" icon="fa fa-file-text-o"
                          class="btn btn-sm btn-success" url="${adminPath}/sys/user/{id}/update" />

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

    function merchantAuditOptFun(value, options, row) {
        try{
            if(!row.id) return '';
            var href="";
            if(row.status == "1"){
                href +="<a href=\"#\" class=\"btn btn-xs btn-success\" class=\"btn btn-xs \"";
                href +="onclick=\"submitCheckAdv('审核','${adminPath}/sys/user/submitCheckAdv','advertiseGridIdGrid','"+row.id+"')\"";
                href +="><i></i>&nbsp;审核</a>&nbsp;&nbsp;";
            }else {
                href +="<a href=\"#\" class=\"btn btn-xs btn-success\" class=\"btn btn-xs \"";
                href +="onclick=\"view('查看','${adminPath}/sys/user/"+row.id+"/update','advertiseGridIdGrid','800px','500px')\"";
                href +="><i></i>&nbsp;查看</a>&nbsp;&nbsp;";
            }
            return href;
        }catch(err){}
        return value;
    }

    function merchantCheck(title, url, gridId, width, height) {
        var rowId = $("#"+gridId).jqGrid('getGridParam','selrow');
        var rowData = $("#"+gridId).jqGrid('getRowData',rowId);
    }
</script>
</body>
</html>