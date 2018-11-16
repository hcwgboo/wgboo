<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>广告</title>
    <meta name="decorator" content="form"/>
    <html:css name="bootstrap-fileinput" />
    <html:css name="simditor" />
</head>

<body class="white-bg"  formid="advertiseForm">
    <form:form id="advertiseForm" modelAttribute="data" method="post" class="form-horizontal">
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
						<form:input path="title" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">
						<label>请选择规则:</label>
					</td>
					<td class="width-35">
						<form:select path="ruleName" htmlEscape="false" onchange="ruleChange();" class="form-control"  datatype="*"    ></form:select>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">
						<label><font color="red">*</font>总金额:</label>
					</td>
					<td class="width-35">
						<form:input path="totalMoney" onblur="totalMoneyChange();" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">
						<label>扣除费用:</label>
					</td>
					<td class="width-35">
						<form:input path="totalMoney" disabled="true" htmlEscape="false" class="form-control"      />
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">
						<label><font color="red">*</font>转发次数:</label>
					</td>
					<td class="width-35">
						<form:input path="transpond" onblur="transpondChange();" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
					<td  class="width-15 active text-right">
						<label><font color="red">*</font>转发金额:</label>
					</td>
					<td class="width-35">
						<form:input path="forwardMoney" onblur="forwardMoneyChange();" htmlEscape="false" class="form-control"      />
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">
						<label><font color="red">*</font>奖励规则:</label>
					</td>
					<td class="width-35" colspan="3">
						<form:textarea path="ruleMain" htmlEscape="false" class="form-control" style="height: 80px;"      ></form:textarea>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td  class="width-15 active text-right">
						<label><font color="red">*</font>广告内容:</label>
					</td>
					<td class="width-35" colspan="3" >
						<form:textarea path="content" htmlEscape="false" class="form-control" style="height: 125px;"     ></form:textarea>
						<label class="Validform_checktip"></label>
					</td>
				</tr>
		   </tbody>
		</table>   
	</form:form>
<html:js name="bootstrap-fileinput" />
<html:js name="simditor" />
<script type="text/javascript">
	var list = [];
    $(document).ready(function() {
        var rule = ${rule};
        list = rule;
        for(var i=0; i<rule.length; i++ ){
            $("#ruleName").append('<option value="'+rule[i].advRuleId+'">'+rule[i].advRuleName+'</option>') ;
        }
        if("${data.ruleId}" != ""){
            _.each(list,function (item) {
                if(item.advRuleId == "${data.ruleId}"){
                    $("#ratio").val(item.ratio);
                    $("#minMoney").val(item.minMoney);
                    $("#minSize").val(item.minSize);
                    $("#ruleId").val(item.advRuleId);
                }
            });
        }else {
            $("#ratio").val(list[0].ratio);
            $("#minMoney").val(list[0].minMoney);
            $("#minSize").val(list[0].minSize);
            $("#ruleId").val(list[0].advRuleId);
        }
    });

	function ruleChange() {
        var value = $("#ruleName").find("option:selected").attr("value");
        _.each(list,function (item) {
            if(item.id == value){
                $("#ratio").val(item.ratio);
                $("#minMoney").val(item.minMoney);
                $("#minSize").val(item.minSize);
                $("#advRuleId").val(item.advRuleId);
            }
        });
    }
    
    function totalMoneyChange() {
		
    }
    
    function transpondChange() {
		
    }
    
    function forwardMoneyChange() {
		
    }
</script>
</body>
</html>