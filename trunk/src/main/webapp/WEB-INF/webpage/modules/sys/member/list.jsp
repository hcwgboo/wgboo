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
    <grid:column label="昵称"  name="nickName" query="true" queryMode="input" condition="like" />
    <grid:column label="性别"  name="gender" dict="xb" />
    <grid:toolbar function="updateOrView" title="查看" icon="fa fa-file-text-o"
                  btnclass="btn btn-sm btn-success" url="${adminPath}/sys/member/{id}/update" />

	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
<script type="text/javascript">

    function updateOrView(title, url, gridId, width, height) {
        var url = chooseVerify(url,gridId);
        if(!url) return;
        view("查看", url, gridId, width, height);
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