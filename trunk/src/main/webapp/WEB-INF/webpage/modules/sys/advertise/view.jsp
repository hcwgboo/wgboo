<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
	<title>广告</title>
	<meta name="decorator" content="form"/>
	<html:css name="bootstrap-fileinput" />
	<html:css name="simditor,common" />
</head>

<body class="white-bg"  formid="advertiseForm">
<form:form id="advertiseForm" modelAttribute="data" method="post" class="form-horizontal">
	<span id="introduce" style="color:red;"></span>
	<form:hidden path="id"/>
	<form:hidden path="minMoney" />
	<form:hidden path="minSize" />
	<form:hidden path="ruleId" />
	<form:hidden path="ratio" />

	<table  class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		<tbody>
		<tr>
			<td  class="width-15 active text-right">
				<label><font color="red">*</font>广告标题:</label>
			</td>
			<td class="width-35">
				<form:input path="title" htmlEscape="false" disabled="true" class="form-control"  />
			</td>
			<td  class="width-15 active text-right">
				<label>请选择规则:</label>
			</td>
			<td class="width-35">
				<form:select path="ruleName" htmlEscape="false" disabled="true"  class="form-control"  ></form:select>
			</td>
		</tr>
		<tr>
			<td  class="width-15 active text-right">
				<label><font color="red">*</font>总金额:</label>
			</td>
			<td class="width-35">
				<form:input path="totalMoney" disabled="true"
							htmlEscape="false" style="width: 195px;" class="define-input-class" /><span style="color:red;font-size: 20px;">（元）</span>
			</td>
			<td  class="width-15 active text-right">
				<label><font color="red">*</font>转发次数:</label>
			</td>
			<td class="width-35">
				<form:input path="transpond" disabled="true"  htmlEscape="false"
							class="define-input-class"  style="width: 195px;"      /><span style="color:red;font-size: 20px;">（条）</span>
			</td>
		</tr>
		<tr>
			<td  class="width-15 active text-right">
				<label><font color="red">*</font>转发金额:</label>
			</td>
			<td class="width-35">
				<form:input path="forwardMoney" disabled="true"  htmlEscape="false" nullmsg="请输入转发金额"
							class="define-input-class"  style="width: 195px;"  /><span style="color:red;font-size: 20px;">（分）</span>
			</td>
		</tr>
		<tr>
			<td  class="width-15 active text-right">
				<label><font color="red">*</font>奖励规则:</label>
			</td>
			<td class="width-35" colspan="3">
				<form:textarea path="ruleMain" disabled="true" htmlEscape="false"  class="form-control" style="height: 80px;" ></form:textarea>
			</td>
		</tr>
		<tr>
			<td  class="width-15 active text-right">
				<label><font color="red">*</font>广告内容:</label>
			</td>
			<td class="width-35" colspan="3" >
				<form:textarea path="content" disabled="true" htmlEscape="false"  class="form-control" style="height: 125px;" ></form:textarea>
			</td>
		</tr>
		<tr>
			<td  class="width-12 active text-right">已选城市：</td>
			<td class="width-48" colspan="6">
				<div id="areaList" style='height:60px; overflow-x: auto;'>
				</div>
				<input type="hidden" name="region" id="regionId" />
			</td>
		</tr>
		</tbody>
	</table>
</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor,underscore,jqGrid_curdtools" />
<script type="text/javascript">
    $(document).ready(function() {
        var rule = ${rule};
        var list = rule;
        for(var i=0; i<rule.length; i++ ){
            $("#ruleName").append('<option value="'+rule[i].advRuleId+'">'+rule[i].advRuleName+'</option>') ;
        }
        if("${data.ruleId}" != ""){
            _.each(list,function (item) {
                if(item.advRuleId == "${data.ruleId}"){
                    minMoney = item.minMoney;
                    minSize = item.minSize;
                    $("#ruleId").val(item.advRuleId);
                }
            });
        }else {
            minMoney = list[0].minMoney;
            minSize = list[0].minSize;
            $("#ruleId").val(list[0].advRuleId);
        }
        $("#introduce").text("当前转发规则的最小金额：" + minMoney + "分，转发的最小条数为：" + minSize + "条。");

        //
        var regions = ${regions};
        _.each(regions,function(item){
            $('#areaList').append('<span style="padding: 2px;" >'+item.name+'</span>');
        });
    });

</script>
</body>
</html>