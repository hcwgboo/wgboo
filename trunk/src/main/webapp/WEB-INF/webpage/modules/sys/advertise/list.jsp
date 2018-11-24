<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>广告列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="广告">
<grid:grid id="advertiseGridId" url="${adminPath}/sys/advertise/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
    <grid:column label="商户id" hidden="true" name="merchantId" />
    <grid:column label="sys.common.opt"  name="opt" formatter="customFun" optCallback="advertiseOptFun" width="200"/>
    <grid:column label="广告标题"  name="title" query="true" queryMode="input" condition="like" />
    <grid:column label="总金额"  name="totalMoney" />
    <grid:column label="转发一次金额"  name="forwardMoney" />
    <grid:column label="奖励规则"  name="ruleMain" />
    <grid:column label="内容"  name="content" />
    <grid:column label="浏览次数"  name="nowViewTimes" />
    <grid:column label="分享次数"  name="nowShareTimes" />
    <grid:column label="上下架"  name="releaseStatus" dict="sjzt" query="true" queryMode="select"  />
    <grid:column label="状态"  name="status" dict="shzt" query="true" queryMode="select" />
	<grid:toolbar function="advertiseCreate" title="添加" url="${adminPath}/sys/advertise/create" btnclass="btn btn-sm btn-primary" />
	<grid:toolbar function="updateOrView" title="修改" icon="fa fa-file-text-o"
                  btnclass="btn btn-sm btn-success" url="${adminPath}/sys/advertise/{id}/update" />

	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
<script type="text/javascript">

    function advertiseCreate(title, url, gridId, width, height) {
        function callback(data) {
            if(data.data){
                create(title,url,gridId,width,height)
            }else {
                msgDialog("请联系平台申请广告规则");
            }
        }
        ajaxSumbit(null, "${adminPath}/sys/advertise/merchantRule", null, callback);
    }

    function updateOrView(title, url, gridId, width, height) {
        var rowId = $("#"+gridId).jqGrid('getGridParam','selrow');
        var rowData = $("#"+gridId).jqGrid('getRowData',rowId);
        if(rowData && (rowData.status == "1" || rowData.status == "2")){
            var url = chooseVerify(url,gridId);
            if(!url) return;
            view("查看", url, gridId, width, height);
        }else{
            update(title, url, gridId, width, height);
        }
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

    function advertiseOptFun(value, options, row) {
        try{
            if(!row.id) return '';
            var href="";
            if(row.releaseStatus == "0" && (row.status == '0' || row.status == 3)){
                href +="<a href=\"#\" class=\"btn btn-xs btn-danger\" class=\"btn btn-xs \"";
                href +="onclick=\"deleteRowData('删除','${adminPath}/sys/advertise/{id}/delete','advertiseGridIdGrid','"+row.id+"','800px','500px')\"";
                href +="><i class=\"fa fa-trash\"></i>&nbsp;删除</a>&nbsp;&nbsp;";

                href +="<a href=\"#\" class=\"btn btn-xs btn-success\" class=\"btn btn-xs \"";
                href +="onclick=\"submitCheckAdv('上架申请','${adminPath}/sys/advertise/submitCheckAdv','advertiseGridIdGrid','"+row.id+"')\"";
                href +="><i></i>&nbsp;上架申请</a>&nbsp;&nbsp;";
            }else{
                href +="<a href=\"#\" class=\"btn btn-xs btn-success\" class=\"btn btn-xs \"";
                href +="onclick=\"view('查看','${adminPath}/sys/advertise/"+row.id+"/update','advertiseGridIdGrid','800px','500px')\"";
                href +="><i></i>&nbsp;查看</a>&nbsp;&nbsp;";
            }
            return href;
        }catch(err){}
        return value;
    }

    function submitCheckAdv(title, url, gridId, rowId) {
        ajaxSumbit({id:rowId}, url,gridId);
    }
</script>
</body>
</html>