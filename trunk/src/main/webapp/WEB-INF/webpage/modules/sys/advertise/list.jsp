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
	<grid:column label="sys.common.opt"  name="opt" formatter="customFun" optCallback="advertiseOptFun" width="100"/>
	<grid:button groupname="opt" function="delete" />
    <grid:column label="商户id" hidden="true" name="merchantId" />
    <grid:column label="广告标题"  name="title" />
    <grid:column label="奖励规则"  name="ruleMain" />
    <grid:column label="内容"  name="content" />
    <grid:column label="广告类别"  name="type" />
    <grid:column label="转发一次金额"  name="forwardMoney" />
    <grid:column label="总金额"  name="totalMoney" />
    <grid:column label="余额"  name="balance" />
    <grid:column label="最小金额"  name="minMoney" />
    <grid:column label="当前浏览次数"  name="nowViewTimes" />
    <grid:column label="当前分享次数"  name="nowShareTimes" />
    <grid:column label="上下架"  name="releaseStatus" />
    <grid:column label="状态"  name="status" />
    <grid:column label="开放区域"  name="region" />
	<grid:toolbar function="create"/>
	<grid:toolbar function="update"/>
	<grid:toolbar function="delete"/>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
<script type="text/javascript">

    function advertiseOptFun(value, options, row) {debugger
        try{
            if(!row.id) return '';
            var href="";
            href +="<a href=\"#\" class=\"btn btn-xs btn-success\" class=\"btn btn-xs \"";
            href +="onclick=\"allotMenu('分配权限','${adminPath}/sys/user/{id}/allotMenu','userGridIdGrid','"+row.id+"','1000px','500px')\"";
            href +="><i class=\"fa fa-plus\"></i>&nbsp分配权限</a>&nbsp&nbsp";
            return href;
        }catch(err){}
        return value;
    }
    function submitCheck() {
        debugger
    }
</script>
</body>
</html>