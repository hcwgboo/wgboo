<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>广告列表</title>
    <meta name="decorator" content="list"/>
</head>
<body title="广告审核">
<grid:grid id="advertiseGridId" url="${adminPath}/sys/advertise/checkAjaxList">
    <grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
    <grid:column label="商户id" hidden="true" name="merchantId" />
    <grid:column label="sys.common.opt"  name="opt" formatter="customFun" optCallback="advertiseOptFun" width="100"/>
    <grid:button groupname="opt" function="delete" />
    <grid:column label="广告标题"  name="title" />
    <grid:column label="奖励规则"  name="ruleMain" />
    <grid:column label="内容"  name="content" />
    <grid:column label="广告类别"  name="type" />
    <grid:column label="转发一次金额"  name="forwardMoney" />
    <grid:column label="总金额"  name="totalMoney" />
    <grid:column label="上下架"  name="releaseStatus" dict="sjzt" query="true" queryMode="select" />
    <grid:column label="状态"  name="status" dict="shzt" query="true" queryMode="select" />
    <grid:toolbar function="advCheck" title="审核" url="${adminPath}/sys/advertise/{id}/view"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>
<script type="text/javascript">

    function advertiseOptFun(value, options, row) {
        try{
            if(!row.id) return '';
            var href="";
            if(row.releaseStatus == "0" && row.status == "1"){
                href +="<a href=\"#\" class=\"btn btn-xs btn-danger\" class=\"btn btn-xs \"";
                href +="onclick=\"advertiseCheck('审核','${adminPath}/sys/advertise/{id}/view','advertiseGridIdGrid','"+row.id+"','800px','500px')\"";
                href +="><i></i>&nbsp审核</a>&nbsp&nbsp";
            }else if(row.releaseStatus == "1" || row.status == "3"){
                href +="<a href=\"#\" class=\"btn btn-xs btn-success\" class=\"btn btn-xs \"";
                href +="onclick=\"view('查看','${adminPath}/sys/advertise/"+row.id+"/update','advertiseGridIdGrid','800px','500px')\"";
                href +="><i></i>&nbsp查看</a>&nbsp&nbsp";
            }
            return href;
        }catch(err){}
        return value;
    }

    function advCheck(title, url, gridId, width, height) {
        var rowId = $("#advertiseGridIdGrid").jqGrid('getGridParam', 'selrow');
        if(!rowId) {
            msgDialog("请选择一条数据");
            return false;
        }
        var rowsArray= $("#advertiseGridIdGrid").jqGrid('getGridParam','selarrrow');
        if(rowsArray.length>1){
            msgDialog("只能选择一条数据");
            return false;
        }
        var rowData = $("#"+gridId).jqGrid('getRowData',rowId);
        if(rowData.releaseStatus == "0" && rowData.status == "1"){
            advertiseCheck(title, url, gridId, rowId, width, height);
        }else {
            url = url.replace("{id}", rowId);
            view(title, url, gridId, width, height);
        }
    }

    function advertiseCheck(title, url, gridId, rowId, width, height) {
        url = url.replace("{id}", rowId);
        top.layer.open({
            type: 2,
            area: [width, height],
            title: title,
            maxmin: true, //开启最大化最小化按钮
            content: url ,
            shift: -1,
            btn: ['通过','拒绝','关闭'],
            yes: function(index, layero) {
                var apiUrl= "${adminPath}/sys/advertise/checkSuccessAdv";
                ajaxSumbit({id:rowId},apiUrl,gridId);
                setTimeout(function () { top.layer.close(index)}, 100);
            },
            btn2: function (index,layer) {
                var apiUrl= "${adminPath}/sys/advertise/checkFailAdv";
                ajaxSumbit({id:rowId},apiUrl,gridId);
            },
            btn3: function (index, layer) {
                setTimeout(function () { top.layer.close(index)}, 100);
            }
        });
    }

    function view(title, url, gridId, width, height) {
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