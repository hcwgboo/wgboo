<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>商家注册列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="商家审核">
<grid:grid id="merchantRegisterGridId" gridSetting="merchantSetting" url="${adminPath}/sys/merchantregister/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
    <grid:column label="sys.common.opt"  name="opt" formatter="customFun" optCallback="merchantAuditOptFun" />
    <grid:column label="公司名称"  name="companyname" />
    <grid:column label="真实名称"  name="realname" />
    <grid:column label="用户名"  name="username" />
    <grid:column label="商家状态"  name="status" dict="sjsh" />
    <grid:toolbar function="merchantCheck" title="审核" icon="fa fa-file-text-o"
                  class="btn btn-sm btn-success" url="${adminPath}/sys/merchantregister/{id}/view" />
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
<script type="text/javascript">

    var merchantSetting = {
        multiselect:true,
        multiboxonly:true,
        beforeSelectRow:beforeSelectRow,
    }
    function beforeSelectRow() {
        $("#merchantRegisterGridId").jqGrid('resetSelection');
        return (true);
    }

    function merchantAuditOptFun(value, options, row) {
        try{
            if(!row.id) return '';
            var href="";
            if(row.status == "1"){
                href +="<a href=\"#\" class=\"btn btn-xs btn-danger\" class=\"btn btn-xs \"";
                href +="onclick=\"submitCheck('审核','${adminPath}/sys/merchantregister/{id}/view','merchantRegisterGridIdGrid','"+row.id+"')\"";
                href +="><i></i>&nbsp;审核</a>&nbsp;&nbsp;";
            }else {
                href +="<a href=\"#\" class=\"btn btn-xs btn-success\" class=\"btn btn-xs \"";
                href +="onclick=\"view('查看','${adminPath}/sys/merchantregister/"+row.id+"/view','merchantRegisterGridIdGrid','800px','500px')\"";
                href +="><i></i>&nbsp;查看</a>&nbsp;&nbsp;";
            }
            return href;
        }catch(err){}
        return value;
    }

    function merchantCheck(title, url, gridId, width, height) {
        var rowId = $("#"+gridId).jqGrid('getGridParam','selrow');
        var rowData = $("#"+gridId).jqGrid('getRowData',rowId);
        if(rowData && (rowData.status != "1")){
            var url = chooseVerify(url,gridId);
            if(!url) return;
            view("查看", url, gridId, width, height);
        }else{
            submitCheck(title, url, gridId, rowId);
        }
    }

    function submitCheck(title, url, gridId, rowId) {
        url = url.replace("{id}", rowId);
        top.layer.open({
            type: 2,
            area: ['800px', '500px'],
            title: title,
            maxmin: true, //开启最大化最小化按钮
            content: url ,
            shift: -1,
            btn: ['通过','拒绝','关闭'],
            yes: function(index, layero) {
                var apiUrl= "${adminPath}/sys/merchantregister/checkMerSuccess";
                ajaxSumbit({id:rowId},apiUrl,gridId);
                setTimeout(function () { top.layer.close(index)}, 100);
            },
            btn2: function (index,layer) {
                var apiUrl= "${adminPath}/sys/merchantregister/checkMerFail";
                ajaxSumbit({id:rowId},apiUrl,gridId);
            },
            btn3: function (index, layer) {
                setTimeout(function () { top.layer.close(index)}, 100);
            }
        });
    }

    function view(title, url, gridId,width, height) {
        top.layer.open({
            type: 2,
            area: [width, height],
            title: title,
            maxmin: true, //开启最大化最小化按钮
            content: url ,
            shift: -1,
            btn: ['关闭'],
            cancel: function(index){

            }
        });
    }

</script>
</body>
</html>