<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>广告规则</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="advertiseRuleRelationForm">
    <form:form id="advertiseRuleRelationForm" modelAttribute="data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="merchantId"   />
		<form:hidden path="advRuleId"   />
		<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		   		<tr>
					<td  class="width-15 active text-right">
						<label>规则名称:</label>
					</td>
					<td class="width-35">
						<form:select path="advRuleName" htmlEscape="false" onchange="advRuleChange(this);" class="form-control"  ></form:select>
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">
						<label>请选择商家:</label>
					</td>
					<td class="width-35">
						<form:input path="merchantName" placeholder="双击选择商家" readonly="true" ondblclick="merchantChoose();" htmlEscape="false" datatype="*" class="form-control" />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
		   		<tr>
					<td class="width-15 active text-right">
						<label>平台佣金占比：</label>
					</td>
					<td class="width-35" >
						<form:input path="ratio" disabled="true" class="form-control"      />
					</td>
					<td class="width-15 active text-right">
						<label>上级分销拥挤占比：</label>
					</td>
					<td class="width-35" >
						<form:input path="superiorCommissionRatio" disabled="true" class="form-control"      />
					</td>
				</tr>
		   		<tr>
					<td class="width-15 active text-right">
						<label>下级分销拥挤占比：</label>
					</td>
					<td class="width-35" >
						<form:input path="subCommissionRatio" disabled="true" class="form-control"      />
					</td>
					<td class="width-15 active text-right">
						<label>最少金额（分）：</label>
					</td>
					<td class="width-35" >
						<form:input path="minMoney" disabled="true" class="form-control"      />
					</td>
				</tr>
				<tr>
					<td class="width-15 active text-right">
						<label>最少条数：</label>
					</td>
					<td class="width-35" >
						<form:input path="minSize" disabled="true" class="form-control"      />
					</td>
				</tr>
		   </tbody>
		</table>   
	</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor,underscore,jqGrid_curdtools" />
<script type="text/javascript">
	var list = [];
    $(document).ready(function() {
        var rule = ${rule};
        list = rule;
        for(var i=0; i<rule.length; i++ ){
            $("#advRuleName").append('<option value="'+rule[i].id+'">'+rule[i].name+'</option>') ;
        }
        if("${data.id}" != ""){
            _.each(list,function (item) {
                if(item.id == "${data.advRuleId}"){
                    $("#ratio").val(item.ratio);
                    $("#superiorCommissionRatio").val(item.superiorCommissionRatio);
                    $("#subCommissionRatio").val(item.subCommissionRatio);
                    $("#minMoney").val(item.minMoney);
                    $("#minSize").val(item.minSize);
                    $("#advRuleId").val(item.id);
                }
            });
        }else {
            $("#ratio").val(list[0].ratio);
            $("#superiorCommissionRatio").val(list[0].superiorCommissionRatio);
            $("#subCommissionRatio").val(list[0].subCommissionRatio);
            $("#minMoney").val(list[0].minMoney);
            $("#minSize").val(list[0].minSize);
            $("#advRuleId").val(list[0].id);
        }
    })

    function advRuleChange() {
        var value = $("#advRuleName").find("option:selected").attr("value");
        _.each(list,function (item) {
            if(item.id == value){
                $("#ratio").val(item.ratio);
                $("#superiorCommissionRatio").val(item.superiorCommissionRatio);
                $("#subCommissionRatio").val(item.subCommissionRatio);
                $("#minMoney").val(item.minMoney);
                $("#minSize").val(item.minSize);
                $("#advRuleId").val(item.id);
            }
        });
    }
    
    function merchantChoose() {
        function callback(data,index){
			$("#merchantName").val(data.realname);
			$("#merchantId").val(data.id);
            setTimeout(function(){top.layer.close(index)}, 100);//延时0.1秒，对应360 7.1版本bug
		}
        commonDialog("请选择商户","${adminPath}/sys/user/merchantList","800px","500px", callback);
    }
</script>
</body>
</html>